package org.springblade.core.databridge.registry;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springblade.core.databridge.model.ConnectorType;
import org.springblade.core.databridge.spi.Serializer;
import org.springblade.core.databridge.spi.Sink;
import org.springblade.core.databridge.spi.Source;
import lombok.extern.slf4j.Slf4j;

/**
 * 连接器注册表（Registry Pattern）。
 * <p>
 * Spring 启动时把所有 {@link Sink} / {@link Source} / {@link Serializer} Bean 注入构造器，
 * 按 {@link Sink#supports()} / {@link Source#supports()} / {@link Serializer#name()}
 * 落 Map，对外提供 O(1) 查找。
 * </p>
 *
 * <h3>OCP 闭环</h3>
 * <ul>
 *   <li>新增协议 = 加 Sink/Source 实现 + ConnectorType 枚举值，本类<b>0 改动</b></li>
 *   <li>新增序列化 = 加 Serializer 实现，本类<b>0 改动</b></li>
 *   <li>所有"按类型查实现"逻辑都收敛到本类，业务侧<b>不应</b>自己 instanceof 判断</li>
 * </ul>
 *
 * <h3>线程安全</h3>
 * 构造器装配后 Map 内容不再变化（不可变快照），并发查询无需锁。
 *
 * <h3>典型用法（业务侧）</h3>
 * <pre>{@code
 * @Service @RequiredArgsConstructor
 * public class SinkDispatcher {
 *     private final ConnectorRegistry registry;
 *
 *     public SendResult dispatch(DataSource ds, ConnectorPayload payload) {
 *         Sink sink = registry.getSink(ConnectorType.valueOf(ds.getSourceType()));
 *         ConnectorConfig cfg = toConfig(ds);
 *         return sink.send(payload, cfg);
 *     }
 * }
 * }</pre>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
@Slf4j
public class ConnectorRegistry {

    private final Map<ConnectorType, Sink> sinks;
    private final Map<ConnectorType, Source> sources;
    private final Map<String, Serializer> serializers;

    public ConnectorRegistry(List<Sink> sinkList,
                             List<Source> sourceList,
                             List<Serializer> serializerList) {
        this.sinks = buildSinkMap(sinkList);
        this.sources = buildSourceMap(sourceList);
        this.serializers = buildSerializerMap(serializerList);
        log.info("[ConnectorRegistry] initialized: sinks={} sources={} serializers={}",
            this.sinks.keySet(), this.sources.keySet(), this.serializers.keySet());
    }

    // ============================== Sink 查询 ==============================

    /**
     * 按协议类型取 Sink 实现。
     *
     * @param type 协议类型
     * @return 对应 Sink 实例；若未注册返回 null
     */
    public Sink getSink(ConnectorType type) {
        if (type == null) {
            return null;
        }
        return sinks.get(type);
    }

    /**
     * 按协议类型取 Sink，未注册时抛 IllegalStateException。
     * <p>用于业务侧明确依赖某个 Sink 的场景，配置错误时早失败。
     */
    public Sink requireSink(ConnectorType type) {
        Sink sink = getSink(type);
        if (sink == null) {
            throw new IllegalStateException(
                "[ConnectorRegistry] no Sink registered for type=" + type
                    + "; available=" + sinks.keySet());
        }
        return sink;
    }

    // ============================== Source 查询 ==============================

    public Source getSource(ConnectorType type) {
        if (type == null) {
            return null;
        }
        return sources.get(type);
    }

    public Source requireSource(ConnectorType type) {
        Source source = getSource(type);
        if (source == null) {
            throw new IllegalStateException(
                "[ConnectorRegistry] no Source registered for type=" + type
                    + "; available=" + sources.keySet());
        }
        return source;
    }

    // ============================== Serializer 查询 ==============================

    /**
     * 按 {@link Serializer#name()} 取实现。
     *
     * @param name 大小写敏感（建议大写：JSON / STRING / BINARY / AVRO）
     * @return 对应 Serializer；未注册返回 null
     */
    public Serializer getSerializer(String name) {
        if (name == null) {
            return null;
        }
        return serializers.get(name);
    }

    public Serializer requireSerializer(String name) {
        Serializer s = getSerializer(name);
        if (s == null) {
            throw new IllegalStateException(
                "[ConnectorRegistry] no Serializer registered for name=" + name
                    + "; available=" + serializers.keySet());
        }
        return s;
    }

    // ============================== 内省（监控 / 调试用）==============================

    public java.util.Set<ConnectorType> registeredSinkTypes() {
        return Collections.unmodifiableSet(sinks.keySet());
    }

    public java.util.Set<ConnectorType> registeredSourceTypes() {
        return Collections.unmodifiableSet(sources.keySet());
    }

    public java.util.Set<String> registeredSerializerNames() {
        return Collections.unmodifiableSet(serializers.keySet());
    }

    // ============================== 内部装配 ==============================

    private Map<ConnectorType, Sink> buildSinkMap(List<Sink> sinkList) {
        if (sinkList == null || sinkList.isEmpty()) {
            log.warn("[ConnectorRegistry] no Sink beans found; outbound bridging disabled");
            return new EnumMap<>(ConnectorType.class);
        }
        Map<ConnectorType, Sink> map = new EnumMap<>(ConnectorType.class);
        for (Sink sink : sinkList) {
            ConnectorType type = sink.supports();
            if (type == null) {
                log.warn("[ConnectorRegistry] Sink {} returns null supports() — skipped",
                    sink.getClass().getName());
                continue;
            }
            Sink prev = map.put(type, sink);
            if (prev != null) {
                log.warn("[ConnectorRegistry] duplicate Sink for type={}: {} overrides {}",
                    type, sink.getClass().getName(), prev.getClass().getName());
            }
        }
        return map;
    }

    private Map<ConnectorType, Source> buildSourceMap(List<Source> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            log.warn("[ConnectorRegistry] no Source beans found; inbound bridging disabled");
            return new EnumMap<>(ConnectorType.class);
        }
        Map<ConnectorType, Source> map = new EnumMap<>(ConnectorType.class);
        for (Source source : sourceList) {
            ConnectorType type = source.supports();
            if (type == null) {
                log.warn("[ConnectorRegistry] Source {} returns null supports() — skipped",
                    source.getClass().getName());
                continue;
            }
            Source prev = map.put(type, source);
            if (prev != null) {
                log.warn("[ConnectorRegistry] duplicate Source for type={}: {} overrides {}",
                    type, source.getClass().getName(), prev.getClass().getName());
            }
        }
        return map;
    }

    private Map<String, Serializer> buildSerializerMap(List<Serializer> serializerList) {
        if (serializerList == null || serializerList.isEmpty()) {
            log.warn("[ConnectorRegistry] no Serializer beans found; only raw byte[] supported");
            return new HashMap<>();
        }
        Map<String, Serializer> map = new HashMap<>();
        for (Serializer s : serializerList) {
            String name = s.name();
            if (name == null || name.isEmpty()) {
                log.warn("[ConnectorRegistry] Serializer {} returns blank name() — skipped",
                    s.getClass().getName());
                continue;
            }
            Serializer prev = map.put(name, s);
            if (prev != null) {
                log.warn("[ConnectorRegistry] duplicate Serializer for name={}: {} overrides {}",
                    name, s.getClass().getName(), prev.getClass().getName());
            }
        }
        return map;
    }
}
