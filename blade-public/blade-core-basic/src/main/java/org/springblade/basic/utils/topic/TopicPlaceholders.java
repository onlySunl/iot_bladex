package org.springblade.basic.utils.topic;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Topic 模板占位符替换工具。新增占位符只需往 {@link Placeholder} 枚举加一行。
 *
 * <p>{@code KEY_*} 是字符串字面量(compile-time constant),可用于 {@code switch case};
 * {@link Placeholder} 枚举反向引用这些常量,保持单一真相源。
 *
 * <h3>性能</h3>
 * 主入口 {@link #replace(String, Function)} 走"单次扫描 + StringBuilder"路线:
 * <ul>
 *   <li>O(N) 一次走完整个 pattern,不做"对每个 Placeholder 调一次 String.replace"那种 O(N×P) 操作</li>
 *   <li>仅产生一个 final String,不再有"5 次 replace 中间产生 4 个临时 String"的 GC 压力</li>
 *   <li>未注册占位符(KEY_SET 之外的 {@code ${xyz}})保留字面量 ── 与原行为一致</li>
 * </ul>
 *
 * @author mqttsnet
 * @since 2026-05-06
 */
@Slf4j
public final class TopicPlaceholders {

    public static final String KEY_APP_ID                 = "app_id";
    public static final String KEY_USER_NAME              = "user_name";
    public static final String KEY_DEVICE_IDENTIFICATION  = "device_identification";
    public static final String KEY_PRODUCT_IDENTIFICATION = "product_identification";
    public static final String KEY_DEVICE_SDK_VERSION     = "device_sdk_version";

    /** MQTT 单层通配符 ── 匹配 topic 中单个层级。占位符替换语义对应此值。 */
    public static final String SINGLE_LEVEL_WILDCARD = "+";

    /**
     * MQTT 多层通配符 ── 匹配剩余所有层级,只能出现在 topic 末尾。
     * <p>用于 PATTERN 模式用户手填模板(如 {@code "$thing/up/#"}),不参与占位符替换 ──
     * 因为占位符 {@code ${x}} 表示单层值,替换为 {@code #} 会吞掉后续层级破坏分层结构。
     */
    public static final String MULTI_LEVEL_WILDCARD = "#";

    /** 占位符前缀,用于短路扫描 */
    private static final String TOKEN_PREFIX = "${";
    private static final char TOKEN_PREFIX_DOLLAR = '$';
    private static final char TOKEN_PREFIX_LBRACE = '{';
    private static final char TOKEN_SUFFIX_RBRACE = '}';

    /** 平台预定义占位符;新增只需加一行枚举值。 */
    public enum Placeholder {
        APP_ID(KEY_APP_ID),
        USER_NAME(KEY_USER_NAME),
        DEVICE_IDENTIFICATION(KEY_DEVICE_IDENTIFICATION),
        PRODUCT_IDENTIFICATION(KEY_PRODUCT_IDENTIFICATION),
        DEVICE_SDK_VERSION(KEY_DEVICE_SDK_VERSION);

        private final String key;
        private final String token;

        Placeholder(String key) {
            this.key = key;
            this.token = "${" + key + "}";
        }

        public String key() {
            return key;
        }

        public String token() {
            return token;
        }

        private static final List<Placeholder> ALL = List.of(values());

        public static List<Placeholder> all() {
            return ALL;
        }
    }

    /**
     * 已注册占位符的 key 快速查找集合 ── 单次扫描时 O(1) 判断 {@code ${key}} 是否要替换。
     * <p>使用 {@code HashSet} 而非 {@code Set.of(...)},是为了未来 {@link Placeholder} 改用动态注册时无需改这里。
     */
    private static final Set<String> KEY_SET;

    static {
        Set<String> s = new HashSet<>(Placeholder.all().size() * 2);
        for (Placeholder p : Placeholder.all()) {
            s.add(p.key());
        }
        KEY_SET = Set.copyOf(s);
    }

    private TopicPlaceholders() {
        throw new UnsupportedOperationException("static util, do not instantiate");
    }

    /**
     * 用 resolver 替换 pattern 中所有已注册占位符。
     * <p>实现走"单次扫描 + StringBuilder",O(N) 完成全部替换:
     * <ol>
     *   <li>从 pattern 起头线性走</li>
     *   <li>遇 {@code "${"} → 找下一个 {@code "}"} 提取 key</li>
     *   <li>key 在 {@link #KEY_SET} 内 → 调 resolver,append 替换值</li>
     *   <li>key 不在 → 保留 {@code "${key}"} 字面量(向前兼容,未注册占位符不被吞)</li>
     *   <li>非 {@code "${"} 起头的字符直接 append</li>
     * </ol>
     * resolver 单 key 抛异常时该项 fallback 空串 + warn 日志。
     */
    public static String replace(String pattern, Function<String, String> resolver) {
        if (StrUtil.isBlank(pattern)) {
            return "";
        }
        if (resolver == null) {
            return pattern;
        }
        int firstToken = pattern.indexOf(TOKEN_PREFIX);
        if (firstToken < 0) {
            return pattern;
        }

        final int len = pattern.length();
        StringBuilder out = new StringBuilder(len + 16);
        // 把第一个 ${ 之前的内容直接拷贝
        out.append(pattern, 0, firstToken);

        int i = firstToken;
        while (i < len) {
            char c = pattern.charAt(i);
            // 探测 ${
            if (c == TOKEN_PREFIX_DOLLAR
                    && i + 1 < len
                    && pattern.charAt(i + 1) == TOKEN_PREFIX_LBRACE) {
                int end = pattern.indexOf(TOKEN_SUFFIX_RBRACE, i + 2);
                if (end < 0) {
                    // ${ 未闭合 ── 把剩余全部 append 后退出(保持原文)
                    out.append(pattern, i, len);
                    return out.toString();
                }
                String key = pattern.substring(i + 2, end);
                if (KEY_SET.contains(key)) {
                    String value;
                    try {
                        value = resolver.apply(key);
                    } catch (Exception e) {
                        log.warn("[TopicPlaceholders] resolver threw on key={} pattern={}, fallback empty: {}",
                                key, pattern, e.getMessage());
                        value = "";
                    }
                    if (value != null) {
                        out.append(value);
                    }
                } else {
                    // 未注册占位符 → 保留字面量
                    out.append(pattern, i, end + 1);
                }
                i = end + 1;
            } else {
                out.append(c);
                i++;
            }
        }
        return out.toString();
    }

    /** 便捷重载:从 Map 取替换值。 */
    public static String replace(String pattern, Map<String, String> valueByKey) {
        if (valueByKey == null) {
            return replace(pattern, k -> null);
        }
        return replace(pattern, valueByKey::get);
    }

    /** Optional 重载:resolver 返回 empty 时替换为空串。 */
    public static String replaceOptional(String pattern, Function<String, Optional<String>> resolver) {
        if (resolver == null) {
            return replace(pattern, (Function<String, String>) null);
        }
        return replace(pattern, key -> resolver.apply(key).orElse(""));
    }

    /** 全部占位符替换为 MQTT 单层通配符 {@code +}。桥接规则匹配场景用。 */
    public static String replaceWithWildcard(String pattern) {
        return replace(pattern, k -> SINGLE_LEVEL_WILDCARD);
    }

    /**
     * 是否含至少一个**已注册**占位符。
     * <p>同样走单次扫描:遇 {@code "${"} → 提取 key → 命中 {@link #KEY_SET} 立刻 short-circuit 返回 true。
     * 比起原先"对每个 Placeholder 调一次 contains"(O(N×P)),这是 O(N) 一遍。
     */
    public static boolean containsPlaceholders(String pattern) {
        if (pattern == null || pattern.length() < 4) {  // 最短 ${a} 也要 4 字符
            return false;
        }
        int idx = pattern.indexOf(TOKEN_PREFIX);
        while (idx >= 0) {
            int end = pattern.indexOf(TOKEN_SUFFIX_RBRACE, idx + 2);
            if (end < 0) {
                return false;
            }
            if (KEY_SET.contains(pattern.substring(idx + 2, end))) {
                return true;
            }
            idx = pattern.indexOf(TOKEN_PREFIX, end + 1);
        }
        return false;
    }
}
