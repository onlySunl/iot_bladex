package org.springblade.modules.iot.bridge.scheduler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.basic.context.ContextUtil;
import org.springblade.common.constant.Constants;
import org.springblade.common.constant.ContextConstants;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.registry.ConnectorRegistry;
import org.springblade.modules.iot.entity.bridge.DataSource;
import org.springblade.modules.iot.service.bridge.BridgeExecutionStepService;
import org.springblade.modules.iot.service.bridge.BridgeExecutionTraceService;
import org.springblade.modules.iot.service.bridge.DataSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 桥接运维任务集合(健康检查 + trace 历史清理)。
 * xxl-job 调度线程无 LocalMap,需 set tenantId 后再切库。
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BridgeMaintenanceScheduler {

    private static final int DEFAULT_TRACE_RETENTION_DAYS = 90;
    private static final String STATUS_HEALTHY = "HEALTHY";
    private static final String STATUS_DOWN = "DOWN";
    private static final String STATUS_UNKNOWN = "UNKNOWN";

    private final DataSourceService dataSourceService;
    private final BridgeExecutionTraceService traceService;
    private final BridgeExecutionStepService stepService;
    private final ConnectorRegistry connectorRegistry;

    /**
     * 启动扫描的租户 ID 列表(逗号分隔)。
     * <pre>iot.bridge.bootstrap.tenant-ids: 1,2,3</pre>
     */
    @Value("${iot.bridge.bootstrap.tenant-ids:1}")
    private String bootstrapTenantIdsRaw;

    /**
     * 健康检查 ── 按租户扫描启用数据源,testConnection 更新 health_status。
     */
    public void runHealthCheck(boolean force) {
        parseTenantIds().forEach(this::runHealthCheckForTenant);
    }

    private void runHealthCheckForTenant(String tenantId) {
        long start = System.currentTimeMillis();
        try {
            ContextUtil.setTenantId(tenantId);
            List<DataSource> all = dataSourceService.list(
                    Wrappers.<DataSource>lambdaQuery().eq(DataSource::getEnable, Boolean.TRUE));
            if (all.isEmpty()) {
                return;
            }
            int healthy = 0, down = 0, unknown = 0;
            LocalDateTime now = LocalDateTime.now();
            for (DataSource ds : all) {
                String status = probeOne(ds);
                switch (status) {
                    case STATUS_HEALTHY -> healthy++;
                    case STATUS_DOWN -> down++;
                    default -> unknown++;
                }
                ds.setHealthStatus(status);
                ds.setLastHealthCheckTime(now);
                try {
                    dataSourceService.updateById(ds);
                } catch (Exception e) {
                    log.warn("[HealthCheck] update failed tenant={} dsId={} status={}",
                            tenantId, ds.getId(), status, e);
                }
            }
            log.info("[HealthCheck] tenant={} scanned={} healthy={} down={} unknown={} latencyMs={}",
                    tenantId, all.size(), healthy, down, unknown, System.currentTimeMillis() - start);
        } finally {
            ContextUtil.remove();
        }
    }

    /**
     * 探活单数据源,任何异常视为 DOWN,不让慢的拖垮整批扫描。
     */
    private String probeOne(DataSource ds) {
        ConnectorType type = parseType(ds.getSourceType());
        if (type == null) {
            return STATUS_UNKNOWN;
        }
        ConnectorConfig cfg = ConnectorConfig.builder()
                .type(type)
                .identifier("healthcheck-" + ds.getId())
                .connectionJson(ds.getConnectionJson())
                .credentialJson(ds.getCredentialJson())
                .extraConfigJson(ds.getExtendParams())
                .serialization(StrUtil.nullToDefault(ds.getSerialization(), "JSON"))
                .build();
        try {
            // 双向数据源优先 Sink 探活;入站源走 Source
            boolean ok = "20".equals(StrUtil.nullToDefault(ds.getDirection(), "10"))
                    ? Optional.ofNullable(connectorRegistry.getSource(type))
                            .map(s -> s.testConnection(cfg)).orElse(false)
                    : Optional.ofNullable(connectorRegistry.getSink(type))
                            .map(s -> s.testConnection(cfg)).orElse(false);
            return ok ? STATUS_HEALTHY : STATUS_DOWN;
        } catch (Exception e) {
            log.debug("[HealthCheck] probe failed dsId={} type={} err={}",
                    ds.getId(), type, e.getMessage());
            return STATUS_DOWN;
        }
    }

    private ConnectorType parseType(String sourceType) {
        if (StrUtil.isBlank(sourceType)) {
            return null;
        }
        try {
            return ConnectorType.valueOf(sourceType.trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * trace + step 历史清理。先 step 后 trace,避免孤儿状态。
     */
    public void cleanupOldTraces(Integer retentionDaysOverride) {
        int days = Optional.ofNullable(retentionDaysOverride).orElse(DEFAULT_TRACE_RETENTION_DAYS);
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        parseTenantIds().forEach(t -> cleanupOldTracesForTenant(t, cutoff, days));
    }

    private void cleanupOldTracesForTenant(String tenantId, LocalDateTime cutoff, int days) {
        long start = System.currentTimeMillis();
        try {
            ContextUtil.setTenantId(tenantId);
            long stepDeleted = stepService.removeBefore(cutoff) ? -1L : 0L;
            long traceDeleted = traceService.removeBefore(cutoff) ? -1L : 0L;
            log.info("[TraceCleanup] tenant={} cutoff={} retentionDays={} stepDeleted={} traceDeleted={} latencyMs={}",
                    tenantId, cutoff, days, stepDeleted, traceDeleted,
                    System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("[TraceCleanup] cleanup failed tenant={}", tenantId, e);
        } finally {
            ContextUtil.remove();
        }
    }

    /**
     * 逗号分隔 → Long 列表;空 / 全非法兜底 [1]。
     */
    private List<String> parseTenantIds() {
        if (StrUtil.isBlank(bootstrapTenantIdsRaw)) {
            return List.of(Constants.DEFAULT_TENANT_ID);
        }
        List<String> ids = Arrays.stream(bootstrapTenantIdsRaw.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::parseLongSafe)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .distinct()
                .toList();
        return ids.isEmpty() ? List.of(Constants.DEFAULT_TENANT_ID) : ids;
    }

    private Optional<String> parseLongSafe(String s) {
        try {
            return Optional.of(String.valueOf(s));
        } catch (NumberFormatException e) {
            log.warn("[BridgeMaintenance] invalid tenantId in config: {}", s);
            return Optional.empty();
        }
    }
}
