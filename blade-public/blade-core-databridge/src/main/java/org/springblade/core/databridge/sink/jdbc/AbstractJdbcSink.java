package org.springblade.core.databridge.sink.jdbc;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sql.DataSource;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.util.JdbcUtils;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator;
import org.springblade.core.databridge.constant.BridgeNamingConstant;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JDBC 类 Sink 公共基类（Strategy + Template Method 模式）。
 *
 * <p>5 个 JDBC 协议（MySQL / PostgreSQL / ClickHouse / TDengine / Apache IoTDB）继承本类，
 * <b>子类只需实现 ~30 行</b>：连接配置解析 + DataSourceProperty 构造 + 行数据组装。
 * 通用的 testConnection / send / 占位符渲染 / 连接池托管全在基类。
 *
 * <h3>复用栈</h3>
 * <ul>
 *   <li>{@link com.alibaba.druid.util.JdbcUtils#insertToTable} ── 自动构造 INSERT SQL +
 *       PreparedStatement，子类不写 JDBC 模板代码</li>
 *   <li>{@link com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator} ──
 *       直接 new 即可，把 {@link DataSourceProperty} 转成带连接池的 {@link HikariDataSource}</li>
 *   <li>{@link com.alibaba.druid.util.JdbcUtils#getDriverClassName} ── 按 JDBC URL 自动识别 driver class</li>
 *   <li>{@link org.springblade.core.databridge.pool.ConnectionPoolManager} ── 缓存 DataSource 实例</li>
 * </ul>
 *
 * <h3>子类职责（{@code @Component} 装配后由 {@code DatabridgeAutoConfiguration} 注册）</h3>
 * <ol>
 *   <li>{@link #parseConnectionParams(ConnectorConfig)} ── 解析 connection_json 为子类专属
 *       的 {@link JdbcParams}（含 jdbcUrl / username / table / columnMapping / driverClassName）</li>
 *   <li>{@link #parseCredentialPassword(ConnectorConfig)} ── 解析 credential_json 取出 password</li>
 *   <li>{@link Sink#supports()} ── 返回对应 ConnectorType</li>
 * </ol>
 *
 * <h3>数据流</h3>
 * <pre>{@code
 * send(payload, config)
 *   └─ buildDataSource(config)            ── 通过 ConnectionPoolManager 拿 / 建 DataSource
 *   └─ row = renderRow(columnMapping, payload)   ── 占位符替换
 *   └─ JdbcUtils.insertToTable(ds, table, row)   ── 通用 insert
 *
 * testConnection(config)
 *   └─ buildDataSource(config)
 *   └─ ds.getConnection().isValid(3)
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractJdbcSink implements Sink {

    /**
     * 占位符正则：${xxx}
     */
    protected static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{([^}]+)}");

    protected final ConnectionPoolManager pool;

    private static String autoDetectDriver(String jdbcUrl) {
        try {
            return JdbcUtils.getDriverClassName(jdbcUrl);
        } catch (Exception e) {
            log.warn("auto-detect driver failed for url={}", jdbcUrl);
            return null;
        }
    }

    /**
     * 解析 connection_json 为子类专属参数。
     * <p>子类各自从 connection_json 中提取本协议需要的字段（jdbcUrl / username / table / 子表 /
     * columnMapping / driverClassName 等）。
     *
     * @param config 通用 ConnectorConfig（含 connectionJson 原始字符串）
     * @return 已规范化的 JDBC 参数
     */
    protected abstract JdbcParams parseConnectionParams(ConnectorConfig config);

    /**
     * 从 credential_json 取出密码。
     */
    protected abstract String parseCredentialPassword(ConnectorConfig config);

    // ============================== 模板方法 ==============================

    /**
     * 是否禁用 p6spy（默认 false 启用 p6spy 拦截 SQL 日志；TDengine 等驱动可能不兼容 p6spy 需关闭）。
     */
    protected boolean disableP6spy() {
        return false;
    }

    @Override
    public final SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            JdbcParams params = parseConnectionParams(config);
            if (StrUtil.isBlank(params.jdbcUrl)
                || StrUtil.isBlank(params.table)
                || MapUtil.isEmpty(params.columnMapping)) {
                throw new IllegalArgumentException("[" + getClass().getSimpleName()
                    + "] missing jdbcUrl / table / columnMapping");
            }

            DataSource ds = buildOrGetDataSource(config);

            // 渲染列 → 值
            Map<String, Object> row = new LinkedHashMap<>();
            for (Map.Entry<String, String> e : params.columnMapping.entrySet()) {
                row.put(e.getKey(), renderTemplate(e.getValue(), payload));
            }

            // 用 Druid JdbcUtils 通用 insert（自动构造 INSERT INTO ... + PreparedStatement）
            JdbcUtils.insertToTable(ds, params.table, row);

            return SendResult.success(
                "rows-1",
                System.currentTimeMillis() - start,
                Map.of("rowsAffected", 1, "table", params.table));
        } catch (Exception e) {
            // SQLException 经常套 IOException / 内部驱动 cause(Druid CommunicationsException)
            log.warn("[{}] send failed identifier={} cause={}",
                getClass().getSimpleName(), config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public final boolean testConnection(ConnectorConfig config) {
        try {
            DataSource ds = buildOrGetDataSource(config);
            try (Connection c = ds.getConnection()) {
                return c.isValid(3);
            }
        } catch (Exception e) {
            log.warn("[{}] testConnection failed identifier={} cause={}",
                getClass().getSimpleName(), config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    // ============================== 通用：DataSource 构造 ==============================

    @Override
    public final void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    private DataSource buildOrGetDataSource(ConnectorConfig config) {
        return pool.getOrCreate(config.getIdentifier(), config, this::buildDataSource);
    }

    /**
     * 通用 DataSource 构造逻辑：用 baomidou {@link HikariDataSourceCreator}。
     * <p>子类一般不需要重写；若协议有特殊连接池策略（如 TDengine 需要特殊的 properties），
     * 可以 override 此方法。
     */
    protected DataSource buildDataSource(ConnectorConfig config) {
        JdbcParams params = parseConnectionParams(config);
        String password = StrUtil.nullToDefault(parseCredentialPassword(config), "");

        DataSourceProperty prop = new DataSourceProperty();
        prop.setUrl(params.jdbcUrl);
        prop.setUsername(params.username);
        prop.setPassword(password);
        // 优先用子类显式提供的 driverClassName；否则用 Druid 自动识别
        String driverClass = StrUtil.isNotBlank(params.driverClassName)
            ? params.driverClassName
            : autoDetectDriver(params.jdbcUrl);
        prop.setDriverClassName(driverClass);
        prop.setPoolName(BridgeNamingConstant.JDBC_POOL_PREFIX + getClass().getSimpleName().toLowerCase()
            + "-" + config.getIdentifier());
        if (disableP6spy()) {
            prop.setP6spy(false);
        }
        // 可选 HikariCP 微调参数
        applyHikariOverrides(prop, params);

        log.info("[{}] building datasource identifier={} url={} driver={}",
            getClass().getSimpleName(), config.getIdentifier(), params.jdbcUrl, driverClass);
        return new HikariDataSourceCreator().createDataSource(prop);
    }

    /**
     * 子类可以覆盖此方法注入 HikariCP 性能调参（poolMaxActive / idleTimeout 等）。
     */
    protected void applyHikariOverrides(DataSourceProperty prop, JdbcParams params) {
        // 默认实现为空；ClickHouse / TDengine 这类有特殊 properties 的协议可重写
    }

    // ============================== 通用：占位符渲染 ==============================

    /**
     * 渲染 {@code ${...}} 模板表达式。
     * <ul>
     *   <li>{@code ${body}} ── payload.body 字符串（UTF-8 解码）</li>
     *   <li>{@code ${routingKey}} ── payload.routingKey</li>
     *   <li>{@code ${ts}} ── payload.ts (long)</li>
     *   <li>{@code ${header.XXX}} ── payload.header("XXX")</li>
     * </ul>
     */
    protected Object renderTemplate(String expr, ConnectorPayload payload) {
        if (StrUtil.isBlank(expr)) {
            return null;
        }
        Matcher m = PLACEHOLDER.matcher(expr);
        // 全占位符 ${xxx} → 直接返回 raw 值（保留类型，long / byte[] 等）
        if (expr.startsWith("${") && expr.endsWith("}") && m.matches()) {
            return resolveRaw(m.group(1), payload);
        }
        // 否则按字符串拼接
        m.reset();
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            Object v = resolveRaw(m.group(1), payload);
            m.appendReplacement(sb, Matcher.quoteReplacement(v == null ? "" : v.toString()));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private Object resolveRaw(String placeholder, ConnectorPayload payload) {
        if ("body".equals(placeholder)) {
            return payload.getBody() == null
                ? null
                : new String(payload.getBody(), StandardCharsets.UTF_8);
        }
        if ("routingKey".equals(placeholder)) {
            return payload.getRoutingKey();
        }
        if ("ts".equals(placeholder)) {
            return payload.getTs();
        }
        if (placeholder.startsWith("header.")) {
            return payload.header(placeholder.substring("header.".length()));
        }
        return null;
    }

    // ============================== 子类用的 DTO ==============================

    /**
     * 通用 JDBC 参数（子类 parseConnectionParams 返回此对象）。
     * <p>避免每个子类重定义相同字段。columnMapping 是 LinkedHashMap，<b>保持插入顺序</b>。
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class JdbcParams {
        /**
         * JDBC URL，如 jdbc:postgresql://host:5432/db
         */
        public String jdbcUrl;
        /**
         * 用户名
         */
        public String username;
        /**
         * 目标表名（已渲染好；带 schema 前缀如需，如 "public.iot_data"）
         */
        public String table;
        /**
         * 列名 → 模板表达式映射（顺序保留）
         */
        public Map<String, String> columnMapping;
        /**
         * 显式 driver class（可空，空则用 JdbcUtils.getDriverClassName 自动识别）
         */
        public String driverClassName;
    }
}
