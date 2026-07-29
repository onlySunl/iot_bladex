package org.springblade.core.databridge.sink.mysql;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * MySQL 出站 Sink。
 *
 * <p>继承 {@link AbstractJdbcSink} ── 通用 send / testConnection / 占位符渲染 / 连接池托管全在
 * 基类，本类只负责把 connection_json / credential_json 翻译成 JdbcParams + password。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "jdbcUrl":      "jdbc:mysql://host:3306/db?useSSL=false&serverTimezone=Asia/Shanghai",
 *   "username":     "root",
 *   "table":        "iot_data",
 *   "columnMapping": {                       // MySQL 列名 → 业务模板表达式
 *     "device_id":   "${routingKey}",
 *     "ts":          "${ts}",
 *     "payload":     "${body}",
 *     "trace_id":    "${header.X-Trace}"
 *   },
 *   "onDuplicate":  "INSERT"                 // ⚠ 简化：UPDATE / IGNORE 模式由数据库表 unique key 处理
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
public class MysqlSink extends AbstractJdbcSink {

    public MysqlSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.MYSQL;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        MysqlConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new MysqlConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), MysqlConnRaw.class);
        return JdbcParams.builder()
            .jdbcUrl(raw.jdbcUrl)
            .username(raw.username)
            .table(raw.table)
            .columnMapping(raw.columnMapping)
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        MysqlCredRaw raw = JsonUtil.parse(config.getCredentialJson(), MysqlCredRaw.class);
        return raw == null ? null : raw.password;
    }

    // ============================== 内部 raw POJO（仅 JSON 反序列化用） ==============================

    /**
     * connection_json raw 视图。字段名严格对齐 Cloud 端 {@code MysqlConnectionDto}。
     */
    public static class MysqlConnRaw {
        public String jdbcUrl;
        public String username;
        public String table;
        public Map<String, String> columnMapping;
        public String onDuplicate;
    }

    /**
     * credential_json raw 视图。字段名对齐 {@code MysqlCredentialDto}。
     */
    public static class MysqlCredRaw {
        public String password;
    }
}
