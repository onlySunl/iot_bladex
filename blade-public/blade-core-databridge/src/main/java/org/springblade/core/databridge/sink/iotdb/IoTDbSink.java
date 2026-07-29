package org.springblade.core.databridge.sink.iotdb;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * Apache IoTDB 出站 Sink（jdbc 模式；session 模式留待后续）。
 *
 * <p>继承 {@link AbstractJdbcSink} 复用通用 JDBC 逻辑。
 *
 * <p><b>差异点</b>：IoTDB 是时序库，没有传统"表"概念，写入是 INSERT INTO root.iot.${productId}.${deviceId}(timestamp, value) VALUES(...)。
 * 为了对齐 {@link AbstractJdbcSink} 的 INSERT SQL 模型，本 Sink 把 storage_group + timeseriesTemplate 渲染后
 * 当作 "table"，columnMapping 列就是 IoTDB 的 measurement 字段。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "host":               "iotdb-host",
 *   "port":               6667,
 *   "storageGroup":       "root.iot",
 *   "timeseriesTemplate": "root.iot.${routingKey}",     // 设备维度的时序路径
 *   "columnMapping":      { "value": "${body}" }
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "username": "root", "password": "root" }}</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
public class IoTDbSink extends AbstractJdbcSink {

    public IoTDbSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.IOTDB;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        IoTConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new IoTConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), IoTConnRaw.class);

        // IoTDB JDBC URL：jdbc:iotdb://host:6667/
        int port = raw.port == null ? 6667 : raw.port;
        String url = "jdbc:iotdb://" + raw.host + ":" + port + "/";

        // IoTDB 的 timestamp 字段必填；自动注入到 columnMapping 第一位
        Map<String, String> mapping = new LinkedHashMap<>();
        mapping.put("Time", "${ts}");
        if (raw.columnMapping != null) {
            mapping.putAll(raw.columnMapping);
        }

        return JdbcParams.builder()
            .jdbcUrl(url)
            .username(null)
            .table(StrUtil.nullToDefault(raw.timeseriesTemplate, raw.storageGroup))
            .columnMapping(mapping)
            .driverClassName("org.apache.iotdb.jdbc.IoTDBDriver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        IoTCredRaw raw = JsonUtil.parse(config.getCredentialJson(), IoTCredRaw.class);
        return raw == null ? null : raw.password;
    }

    /**
     * IoTDB 不兼容 p6spy 拦截。
     */
    @Override
    protected boolean disableP6spy() {
        return true;
    }

    // ============================== 内部 raw POJO ==============================

    public static class IoTConnRaw {
        public String host;
        public Integer port;
        public String storageGroup;
        public String timeseriesTemplate;
        public Map<String, String> columnMapping;
    }

    public static class IoTCredRaw {
        public String username;
        public String password;
    }
}
