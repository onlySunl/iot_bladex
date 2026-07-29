package org.springblade.core.databridge.sink.kingbase;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * 人大金仓 KingBase 出站 Sink（PG 兼容生态；继承 {@link AbstractJdbcSink}）。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "jdbcUrl":     "jdbc:kingbase8://host:54321/dbname",
 *   "username":    "system",
 *   "schemaName":  "public",
 *   "table":       "iot_data",
 *   "columnMapping": { ... },
 *   "onConflict":  "INSERT"
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
public class KingBaseSink extends AbstractJdbcSink {

    public KingBaseSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.KINGBASE;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        KbConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new KbConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), KbConnRaw.class);
        String fullTable = StrUtil.isBlank(raw.schemaName)
            ? raw.table
            : raw.schemaName + "." + raw.table;
        return JdbcParams.builder()
            .jdbcUrl(raw.jdbcUrl)
            .username(raw.username)
            .table(fullTable)
            .columnMapping(raw.columnMapping)
            .driverClassName("com.kingbase8.Driver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        KbCredRaw raw = JsonUtil.parse(config.getCredentialJson(), KbCredRaw.class);
        return raw == null ? null : raw.password;
    }

    public static class KbConnRaw {
        public String jdbcUrl;
        public String username;
        public String schemaName;
        public String table;
        public Map<String, String> columnMapping;
        public String onConflict;
    }

    public static class KbCredRaw {
        public String password;
    }
}
