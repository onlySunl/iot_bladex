package org.springblade.core.databridge.sink.mongodb;

import cn.hutool.core.util.StrUtil;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.databridge.model.ConnectorConfig;
import org.springblade.core.databridge.model.ConnectorPayload;
import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.model.SendResult;
import org.springblade.core.databridge.pool.ConnectionPoolManager;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.SinkErrors;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * MongoDB 出站 Sink（mongodb-driver-sync；存原始 JSON / 复杂业务对象）。
 *
 * <h3>类签名约束（关键）</h3>
 * 本类所有 public / package-private 方法的 <b>参数类型 + 返回类型 + throws 类型</b>
 * 不直接引用 com.mongodb / org.bson 任何类，全部用 Object 兜底。
 * mongo / bson 类型仅出现在 <b>方法体局部变量</b> 中。
 * <p>
 * 这样保证：mongodb-driver-sync 不在 classpath 时（{@code <optional>true</optional>}），
 * 任何反射式扫描器（如 blade-core-database 的 TenantLineAnnotationRegister
 * 通过 Class.forName + getDeclaredMethods() 触发的 method signature 解析）不会
 * 因为找不到 Bson 等类抛 NoClassDefFoundError。
 *
 * <h3>connection_json 字段</h3>
 * <pre>{@code
 * {
 *   "uri":          "mongodb://host:27017",
 *   "database":     "iot",
 *   "collection":   "device_data",       // 支持模板 ${productId}
 *   "writeMode":    "INSERT",            // INSERT / UPSERT
 *   "upsertKey":    "deviceId",          // writeMode=UPSERT 时必填
 *   "writeConcern": "ACKNOWLEDGED"
 * }
 * }</pre>
 *
 * <h3>credential_json 字段</h3>
 * <pre>{@code { "username":"...", "password":"...", "authDatabase":"admin" }}</pre>
 *
 * @author mqttsnet
 * @since 2026-04-29
 */
@Slf4j
@RequiredArgsConstructor
public class MongoDbSink implements Sink {

    private final ConnectionPoolManager pool;

    @Override
    public ConnectorType supports() {
        return ConnectorType.MONGODB;
    }

    @Override
    public SendResult send(ConnectorPayload payload, ConnectorConfig config) {
        long start = System.currentTimeMillis();
        try {
            // 用 Object 接住，method signature 不暴露 MongoClient 类型
            Object client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            MongoConnRaw conn = parseConnection(config);
            // mongo 类型仅在方法体内出现
            MongoDatabase db = ((MongoClient) client).getDatabase(conn.database);
            MongoCollection<Document> coll = db.getCollection(conn.collection);

            Document doc = (Document) buildDocument(payload);
            coll.insertOne(doc);
            String objId = doc.getObjectId("_id") == null ? null : doc.getObjectId("_id").toHexString();
            return SendResult.success(
                objId, System.currentTimeMillis() - start, Map.of("collection", conn.collection));
        } catch (Exception e) {
            log.warn("[MongoDbSink] send failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return SendResult.fail(e, System.currentTimeMillis() - start);
        }
    }

    @Override
    public boolean testConnection(ConnectorConfig config) {
        try {
            Object client = pool.getOrCreate(config.getIdentifier(), config, this::buildClient);
            MongoConnRaw conn = parseConnection(config);
            // 探活：列举 collection name（method signature 不含 Bson 类型）。
            // 不用 db.runCommand({ping: 1})，因为 runCommand(Bson) 的 method ref 会把 Bson
            // 放进 constant pool，导致 JVM bytecode verification 阶段强制加载 Bson；
            // mongodb-driver 是 optional 依赖时（driver 缺失），Class.forName 加载本类
            // 就会抛 NoClassDefFoundError: org/bson/conversions/Bson。
            ((MongoClient) client).getDatabase(conn.database).listCollectionNames().first();
            return true;
        } catch (Exception e) {
            log.warn("[MongoDbSink] testConnection failed identifier={} cause={}",
                config.getIdentifier(),
                SinkErrors.causeChain(e), e);
            return false;
        }
    }

    @Override
    public void close(ConnectorConfig config) {
        pool.invalidate(config.getIdentifier());
    }

    /**
     * 构建 MongoClient。
     * <p>返回类型用 Object，避免 method signature 引用 com.mongodb.client.MongoClient ──
     * 反射式扫描器在 driver 缺失时不会抛 NoClassDefFoundError。
     */
    private Object buildClient(ConnectorConfig config) {
        MongoConnRaw conn = parseConnection(config);
        log.info("[MongoDbSink] building client identifier={} uri={}",
            config.getIdentifier(), conn.uri);
        return MongoClients.create(conn.uri);
    }

    private MongoConnRaw parseConnection(ConnectorConfig config) {
        return StrUtil.isBlank(config.getConnectionJson())
            ? new MongoConnRaw()
            : JsonUtil.parse(config.getConnectionJson(), MongoConnRaw.class);
    }

    /**
     * payload → BSON Document。
     * <p>返回 Object，把 Document 类型限制在方法体内 ── 保证 method signature 干净。
     */
    private Object buildDocument(ConnectorPayload payload) {
        Document doc = new Document();
        doc.put("ts", payload.getTs());
        doc.put("routingKey", payload.getRoutingKey());
        if (payload.getBody() != null) {
            String body = new String(payload.getBody(), StandardCharsets.UTF_8);
            String trimmed = body.trim();
            if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
                try {
                    doc.put("payload", Document.parse(trimmed));
                } catch (Exception ignore) {
                    doc.put("payload", body);
                }
            } else {
                doc.put("payload", body);
            }
        }
        if (payload.getHeaders() != null) {
            doc.put("headers", new Document(payload.getHeaders()));
        }
        return doc;
    }

    /**
     * Plain POJO，无任何第三方类型引用，可放外层。
     */
    public static class MongoConnRaw {
        public String uri;
        public String database;
        public String collection;
        public String writeMode;
        public String upsertKey;
        public String writeConcern;
    }
}
