package org.springblade.core.databridge.sink.postgresql;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * PostgreSQL 出站 Sink（继承 {@link AbstractJdbcSink} 复用通用 JDBC 逻辑）。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "jdbcUrl":     "jdbc:postgresql://host:5432/db",
 *   "username":    "postgres",
 *   "schemaName":  "public",                    // 可选，默认 public
 *   "table":       "iot_data",                  // 必填
 *   "columnMapping": { "device_id": "${routingKey}", "ts": "${ts}", "payload": "${body}" },
 *   "onConflict":  "INSERT",                    // INSERT / UPSERT / IGNORE（暂用 PG ON CONFLICT 简化版）
 *   "conflictKeys": "device_id,ts"              // UPSERT 时冲突键
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
public class PostgreSqlSink extends AbstractJdbcSink {

    public PostgreSqlSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.POSTGRESQL;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        PgConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new PgConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), PgConnRaw.class);
        // 表名带 schema 前缀（如 public.iot_data），让 JdbcUtils.insertToTable 正确生成 SQL
        String fullTable = StrUtil.isBlank(raw.schemaName)
            ? raw.table
            : raw.schemaName + "." + raw.table;
        return JdbcParams.builder()
            .jdbcUrl(raw.jdbcUrl)
            .username(raw.username)
            .table(fullTable)
            .columnMapping(raw.columnMapping)
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        PgCredRaw raw = JsonUtil.parse(config.getCredentialJson(), PgCredRaw.class);
        return raw == null ? null : raw.password;
    }

    // ============================== 内部 raw POJO ==============================

    public static class PgConnRaw {
        public String jdbcUrl;
        public String username;
        public String schemaName;
        public String table;
        public Map<String, String> columnMapping;
        public String onConflict;
        public String conflictKeys;
    }

    public static class PgCredRaw {
        public String password;
    }
}
