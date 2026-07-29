package org.springblade.core.databridge.sink.dm;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * 达梦数据库 DM 出站 Sink（继承 {@link AbstractJdbcSink} 复用通用 JDBC 逻辑）。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "jdbcUrl":     "jdbc:dm://host:5236/SCHEMA",
 *   "username":    "SYSDBA",
 *   "schemaName":  "SYSDBA",                    // 可选
 *   "table":       "iot_data",                  // 必填
 *   "columnMapping": { "device_id": "${routingKey}", ... },
 *   "onDuplicate": "INSERT"
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "password": "..." }}</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
public class DmSink extends AbstractJdbcSink {

    public DmSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.DM;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        DmConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new DmConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), DmConnRaw.class);
        // DM 表名可加 schema 前缀
        String fullTable = StrUtil.isBlank(raw.schemaName)
            ? raw.table
            : raw.schemaName + "." + raw.table;
        return JdbcParams.builder()
            .jdbcUrl(raw.jdbcUrl)
            .username(raw.username)
            .table(fullTable)
            .columnMapping(raw.columnMapping)
            .driverClassName("dm.jdbc.driver.DmDriver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        DmCredRaw raw = JsonUtil.parse(config.getCredentialJson(), DmCredRaw.class);
        return raw == null ? null : raw.password;
    }

    public static class DmConnRaw {
        public String jdbcUrl;
        public String username;
        public String schemaName;
        public String table;
        public Map<String, String> columnMapping;
        public String onDuplicate;
    }

    public static class DmCredRaw {
        public String password;
    }
}
