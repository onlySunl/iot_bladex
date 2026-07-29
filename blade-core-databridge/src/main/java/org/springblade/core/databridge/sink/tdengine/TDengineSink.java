package org.springblade.core.databridge.sink.tdengine;

import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.sink.jdbc.AbstractJdbcSink;
import lombok.extern.slf4j.Slf4j;

/**
 * TDengine 出站 Sink（继承 {@link AbstractJdbcSink} 复用通用 JDBC 逻辑）。
 *
 * <p>用 taos-jdbcdriver <b>JDBC-RESTful 模式</b>（不依赖本地 taosc 库，跨平台部署友好）。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "host":               "tdengine-host",
 *   "port":               6041,                       // RESTful 端口，默认 6041
 *   "database":           "iot",
 *   "superTable":         "device_data",              // 超表名（STable）
 *   "childTableTemplate": "d_${routingKey}",          // 子表命名模板
 *   "tagsMapping":        { "product": "${header.X-Product}", "device": "${routingKey}" },
 *   "columnMapping":      { "ts": "${ts}", "value": "${body}" }   // 时序字段（含 ts）
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "username": "root", "password": "<your-password>" }}</pre>
 * <p>占位 {@code <your-password>} 仅文档示意,请按部署环境配置真实凭据,严禁提交真实密码到代码库。</p>
 *
 * <p><b>注</b>：TDengine 的子表 INSERT SQL 复杂（{@code INSERT INTO d_xxx USING super_table TAGS(...) VALUES(...)})
 * 暂时降级为直接 INSERT INTO superTable，TDengine 自动建子表的能力（auto create table）由 SQL 保证。
 * 如需精细子表控制，后续可重写 send 方法。
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
public class TDengineSink extends AbstractJdbcSink {

    public TDengineSink(ConnectionPoolManager pool) {
        super(pool);
    }

    @Override
    public ConnectorType supports() {
        return ConnectorType.TDENGINE;
    }

    /**
     * TDengine 驱动与 p6spy 拦截器组合可能不稳定；关闭 p6spy。
     */
    @Override
    protected boolean disableP6spy() {
        return true;
    }

    @Override
    protected JdbcParams parseConnectionParams(ConnectorConfig config) {
        TdConnRaw raw = StrUtil.isBlank(config.getConnectionJson())
            ? new TdConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), TdConnRaw.class);
        String url = buildJdbcUrl(raw);
        // 表名 = database.superTable（TDengine SQL 风格）
        String fullTable = StrUtil.isBlank(raw.database)
            ? raw.superTable
            : raw.database + "." + raw.superTable;
        return JdbcParams.builder()
            .jdbcUrl(url)
            .username(null)   // username 从 credential 来；下面覆写 buildDataSource 时再注入
            .table(fullTable)
            .columnMapping(raw.columnMapping)
            .driverClassName("com.taosdata.jdbc.rs.RestfulDriver")
            .build();
    }

    @Override
    protected String parseCredentialPassword(ConnectorConfig config) {
        if (StrUtil.isBlank(config.getCredentialJson())) {
            return null;
        }
        TdCredRaw raw = JsonUtil.parse(config.getCredentialJson(), TdCredRaw.class);
        return raw == null ? null : raw.password;
    }

    /**
     * TDengine 默认账号 root；用户名一般在 credential_json，先简化为静态默认。
     */
    private String buildJdbcUrl(TdConnRaw raw) {
        int port = raw.port == null ? 6041 : raw.port;
        return "jdbc:TAOS-RS://" + raw.host + ":" + port + "/" + StrUtil.nullToDefault(raw.database, "");
    }

    // ============================== 内部 raw POJO ==============================

    public static class TdConnRaw {
        public String host;
        public Integer port;
        public String database;
        public String superTable;
        public String childTableTemplate;
        public Map<String, String> tagsMapping;
        public Map<String, String> columnMapping;
    }

    public static class TdCredRaw {
        public String username;
        public String password;
    }
}
