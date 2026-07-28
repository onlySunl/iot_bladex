package org.springblade.modules.iot.inner.controller;

import com.alibaba.fastjson2.JSON;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.exception.BizException;
import com.mqttsnet.basic.interfaces.echo.EchoService;
import com.mqttsnet.basic.utils.ArgumentAssert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.bridge.scheduler.BridgeMaintenanceScheduler;
import org.springblade.modules.iot.service.linkage.RuleService;
import org.springblade.modules.iot.service.plugin.PluginInfoService;
import org.springblade.modules.iot.service.script.RuleGroovyScriptService;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptDirectCompileParam;
import org.springblade.modules.iot.vo.param.script.RuleGroovyScriptExecuteScriptParam;
import org.springblade.modules.iot.vo.result.linkage.RuleDetailsResultVO;
import org.springblade.modules.iot.vo.result.script.GroovyScriptEngineExecutorResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Rule 规则内部接口(inner)：Feign 服务间 RPC(Nacos 直连、不过网关),透传 TenantId、无需 Token；网关拒绝外部访问。处理时注意 ContextUtil.setTenantId(tenantId)。
 *
 * @author xiaonannet
 * @version 1.0
 * @email
 * @date 2024/7/21 00:20
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/inner/ruleOpen")
@Tag(name = "inner-规则API")
public class RuleOpenInnerController {


    private final RuleService ruleService;

    private final RuleGroovyScriptService ruleGroovyScriptService;

    private final PluginInfoService pluginInfoService;

    private final EchoService echoService;

    private final BridgeMaintenanceScheduler bridgeMaintenanceScheduler;

    /**
     * 触发指定租户下某规则的策略,返回触发后的 {@link RuleDetailsResultVO}。
     */
    @Operation(summary = "触发规则策略", description = "Triggers the policy of a specific rule for a given tenant.")
    @Parameters({
            @Parameter(name = "tenantId", description = "tenantId", required = true),
            @Parameter(name = "ruleIdentification", description = "Rule Identification", required = true),
    })
    @PostMapping("/triggerRulePolicy")
    public R<RuleDetailsResultVO> triggerRulePolicy(@RequestParam("tenantId") Long tenantId, @RequestParam("ruleIdentification") String ruleIdentification) {
        ArgumentAssert.notNull(tenantId, "tenantId  Cannot be null");
        ArgumentAssert.notEmpty(ruleIdentification, "ruleIdentification Cannot be null");
        log.info("Trigger Rule Policy - tenantId: {}, Rule Identification: {}", tenantId, ruleIdentification);
        try {
            RuleDetailsResultVO ruleDetailsResultVO = ruleService.triggerRulePolicy(tenantId, ruleIdentification);
            log.info("Successfully triggered rule policy - tenantId: {}, Rule Identification: {}, Result: {}", tenantId, ruleIdentification, ruleDetailsResultVO);
            echoService.action(ruleDetailsResultVO);
            return R.data(ruleDetailsResultVO);
        }  catch (Exception e) {
            log.error("Unexpected error while triggering rule policy - tenantId: {}, Rule Identification: {}", tenantId, ruleIdentification, e);
            return R.fail("Unexpected error triggering rule policy: " + e.getMessage());
        }
    }


    /**
     * 刷新 Groovy 脚本缓存。
     */
    @Operation(summary = "刷新Groovy脚本缓存", description = "Flushes the Groovy script cache")
    @GetMapping("/flushGroovyScriptCache")
    public R<Boolean> flushGroovyScriptCache() {
        log.info("Flushing Groovy Script Cache - tenantId: {}", ContextUtil.getTenantIdStr());
        try {
            return R.data(ruleGroovyScriptService.flushGroovyScriptCache());
        } catch (BizException bizException) {
            log.warn("Business exception while flushing Groovy script cache - tenantId: {}, Message: {}", ContextUtil.getTenantIdStr(), bizException.getMessage(), bizException);
            return R.fail("Business error flushing Groovy script cache: " + bizException.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while flushing Groovy script cache - tenantId: {}", ContextUtil.getTenantIdStr(), e);
            return R.fail("Unexpected error flushing Groovy script cache: " + e.getMessage());
        }
    }


    /**
     * 安装插件并保存安装结果。
     */
    @Operation(summary = "安装插件", description = "Installs a plugin based on pluginId and saves the result")
    @Parameter(name = "tenantId", description = "tenantId", required = true)
    @Parameter(name = "pluginId", description = "pluginId", required = true)
    @Parameter(name = "instanceId", description = "instanceId", required = true)
    @PostMapping("/installPlugin")
    public R<?> installPlugin(@RequestParam("tenantId") Long tenantId,
                              @RequestParam("pluginId") Long pluginId,
                              @RequestParam("instanceId") Long instanceId) {

        log.info("Initiating installation instanceId:{} for pluginId: {} under tenantId: {}", instanceId, pluginId, tenantId);
        try {
            ContextUtil.setTenantId(tenantId);
            Boolean result = pluginInfoService.installPlugin(pluginId, instanceId);
            return result ? R.data(true) : R.fail("Plugin installation failed.");
        } catch (Exception e) {
            log.error("Failed to install plugin for pluginId: {} under tenantId: {}. Error: {}", pluginId, tenantId, e.getMessage(), e);
            return R.fail("Failed to install plugin. Error: " + e.getMessage());
        }
    }

    /**
     * 卸载插件并保存卸载结果。
     */
    @Operation(summary = "卸载插件", description = "Uninstalls a plugin based on pluginId and saves the result")
    @Parameter(name = "tenantId", description = "tenantId", required = true)
    @Parameter(name = "pluginId", description = "pluginId", required = true)
    @Parameter(name = "instanceId", description = "instanceId", required = true)
    @DeleteMapping("/uninstallPlugin")
    public R<?> unInstallPlugin(@RequestParam("tenantId") Long tenantId,
                                @RequestParam("pluginId") Long pluginId,
                                @RequestParam("instanceId") Long instanceId) {

        log.info("Initiating uninstallation  instanceId:{} for pluginId: {} under tenantId: {}", instanceId, pluginId, tenantId);
        try {
            ContextUtil.setTenantId(tenantId);
            Boolean result = pluginInfoService.unInstallPlugin(pluginId, instanceId);
            return result ? R.data(true) : R.fail("Plugin uninstallation failed.");
        } catch (Exception e) {
            log.error("Failed to uninstall plugin for pluginId: {} under tenantId: {}. Error: {}", pluginId, tenantId, e.getMessage(), e);
            return R.fail("Failed to uninstall plugin. Error: " + e.getMessage());
        }
    }


    /**
     * 直接编译执行脚本:动态编译脚本内容 → 绑定执行参数 → 执行返回结果。
     */
    @Operation(summary = "直接编译脚本", description = "实时编译脚本内容并执行，适用于动态脚本编译场景")
    @PostMapping("/executeScript")
    public R<GroovyScriptEngineExecutorResultVO> executeScript(@RequestBody RuleGroovyScriptExecuteScriptParam param) {
        log.info("Executing script param: {}", JSON.toJSONString(param));
        ArgumentAssert.isTrue(JSON.isValid(param.getExecuteParams()), "执行参数格式不正确");
        try {
            return R.data(ruleGroovyScriptService.executeScript(param));
        } catch (Exception e) {
            log.error("Failed to execute script", e);
            throw BizException.wrap("编译执行脚本失败", e.getMessage());
        }
    }

    /**
     * 直接编译执行脚本内容:动态编译 scriptContent → 绑定 executeParams → 执行返回结果。
     * 供 mqs「设备上行前置转换」运行时 + 在线调试经 Feign({@code RuleOpenInnerApi#executeScriptContent})调用。
     */
    @Operation(summary = "直接编译执行脚本内容", description = "实时编译脚本内容并执行,供 mqs 前置转换 / 在线调试 Feign 调用")
    @PostMapping("/executeScriptContent")
    public R<GroovyScriptEngineExecutorResultVO> executeScriptContent(@RequestBody RuleGroovyScriptDirectCompileParam param) {
        try {
            return R.data(ruleGroovyScriptService.runDirectCompile(param));
        } catch (Exception e) {
            log.error("Failed to execute script content", e);
            throw BizException.wrap("编译执行脚本内容失败", e.getMessage());
        }
    }

    /**
     * 触发桥接数据源健康检查（xxl-job 入口）。
     */
    @Operation(summary = "运行桥接数据源健康检查", description = "扫描所有启用数据源调 testConnection 更新 health_status")
    @PostMapping("/runBridgeHealthCheck")
    public R<Boolean> runBridgeHealthCheck() {
        try {
            bridgeMaintenanceScheduler.runHealthCheck(true);
            return R.data(true);
        } catch (Exception e) {
            log.error("Bridge health check failed", e);
            return R.fail("Bridge health check failed: " + e.getMessage());
        }
    }

    /**
     * 触发桥接 trace 历史清理（xxl-job 入口）。retentionDays 可空,空则用配置默认值。
     */
    @Operation(summary = "运行桥接 trace 历史清理", description = "删除超过保留期的 bridge_execution_trace + step")
    @PostMapping("/runBridgeTraceCleanup")
    public R<Boolean> runBridgeTraceCleanup(@RequestParam(value = "retentionDays", required = false) Integer retentionDays) {
        try {
            bridgeMaintenanceScheduler.cleanupOldTraces(retentionDays);
            return R.data(true);
        } catch (Exception e) {
            log.error("Bridge trace cleanup failed", e);
            return R.fail("Bridge trace cleanup failed: " + e.getMessage());
        }
    }

}
