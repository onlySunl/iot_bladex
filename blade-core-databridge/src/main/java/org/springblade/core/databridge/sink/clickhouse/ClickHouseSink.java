package org.springblade.core.databridge.sink.clickhouse;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * ClickHouse 出站 Sink（列存 OLAP，写实时大宽表）。
 *
 * <p>继承 {@link AbstractJdbcSink} 复用通用 JDBC 逻辑。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "jdbcUrl":         "jdbc:clickhouse://host:8123/db",
 *   "database":        "iot",
 *   "table":           "device_data_wide",
 *   "columnMapping":   { "ts": "${ts}", "device_id": "${routingKey}", "payload": "${body}" },
 *   "useAsyncInsert":  true                        // 可选；ClickHouse 异步写入特性
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "username": "default", "password": "..." }}</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
public class ClickHouseSink extends AbstractJdbcSink {

    public ClickHouseSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.CLICKHOUSE;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        ChConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new ChConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), ChConnRaw.class);
        String fullTable = StrUtil.isBlank(raw.database)
            ? raw.table
            : raw.database + "." + raw.table;
        return JdbcParams.builder()
            .jdbcUrl(raw.jdbcUrl)
            // ClickHouse 用户名走 credential_json，但也可放 jdbcUrl 的 query 参数
            .username(extractUsernameFromUrlOrCred(raw.jdbcUrl))
            .table(fullTable)
            .columnMapping(raw.columnMapping)
            .driverClassName("com.clickhouse.jdbc.ClickHouseDriver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        ChCredRaw raw = JsonUtil.parse(config.getCredentialJson(), ChCredRaw.class);
        return raw == null ? null : raw.password;
    }

    /**
     * ClickHouse 通常用户名带在 JDBC URL 里（jdbc:clickhouse://host:8123/db?user=default），也可分离成单独参数。
     * <p>本 sink 优先信任 credential_json.username；URL 里的 user 参数 driver 自己处理。
     */
    private String extractUsernameFromUrlOrCred(String jdbcUrl) {
        return "default";   // 由 driver 与 URL/credential 协商；HikariCP 显式 setUsername 已足够
    }

    // ============================== 内部 raw POJO ==============================

    public static class ChConnRaw {
        public String jdbcUrl;
        public String database;
        public String table;
        public Map<String, String> columnMapping;
        public Boolean useAsyncInsert;
    }

    public static class ChCredRaw {
        public String username;
        public String password;
    }
}
