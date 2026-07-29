package org.springblade.basic.utils.topic;

import cn.hutool.core.util.StrUtil;

/**
 * MQTT 主题通配符匹配器(项目通用)。
 *
 * <h3>支持的通配符</h3>
 * <ul>
 *   <li>{@code +} —— 单层通配符,匹配单个 topic 段(非空)</li>
 *   <li>{@code #} —— 多层通配符,匹配剩余所有段(必须出现在末尾)</li>
 * </ul>
 *
 * <h3>使用场景</h3>
 * <ul>
 *   <li>BifroMQ 认证插件的 ACL 主题匹配({@code AclMatcherUtil})</li>
 *   <li>桥接规则的 topic 过滤({@code TopicMatchStrategy})</li>
 *   <li>未来其它需要 MQTT 通配符判定的业务</li>
 * </ul>
 *
 * <h3>与占位符的协作</h3>
 * 调用方在使用本类前,通常先调 {@link TopicPlaceholders#replaceWithWildcard(String)} 把
 * {@code ${app_id}} 等业务占位符替换为 MQTT 单层通配符 {@code +},再传入本类做匹配。
 *
 * <h3>设计权衡</h3>
 * 本算法走"按 / 切分逐段比较"路线,无 Pattern 编译 / 无缓存,**单次匹配仅 N 次字符串比较**;
 * 适用于:
 * <ul>
 *   <li>规则数量级有限(千级)且上层已有规则缓存的场景(如桥接 {@code BridgeRuleCache})</li>
 *   <li>不依赖 caffeine,可下沉到 blade-core-basic 给所有模块用</li>
 * </ul>
 * 如对 ACL 鉴权这种高 QPS 热点路径有缓存编译需求,业务侧可在外层套 caffeine
 *
 * <h3>例子</h3>
 * <pre>
 *   match("$thing/up/property/+/+", "$thing/up/property/p1/d1") = true
 *   match("$thing/up/property/+/+", "$thing/up/property/p1/d1/extra") = false
 *   match("$thing/up/#",            "$thing/up/property/p1/d1") = true
 *   match("$thing/up/#",            "$thing/down/command/...") = false
 *   match("a/b/c",                  "a/b/c") = true
 * </pre>
 *
 * @author mqttsnet
 * @since 2026-05-09
 */
public final class MqttTopicMatcher {

    private static final String LEVEL_SEPARATOR = "/";
    private static final String SINGLE_WILDCARD = "+";
    private static final String MULTI_WILDCARD = "#";

    private MqttTopicMatcher() {
        // util class, no instance
    }

    /**
     * 判断给定 topic 是否匹配 pattern。
     *
     * @param pattern MQTT 主题模式(含 + / # 通配符;空 / blank 表示不限制 → 默认匹配)
     * @param topic   实际 topic(不含通配符);空 / blank 时返回 false
     * @return true=匹配 / false=不匹配
     */
    public static boolean match(String pattern, String topic) {
        // pattern 为空 = 不约束 topic = 默认放行
        if (StrUtil.isBlank(pattern)) {
            return true;
        }
        if (StrUtil.isBlank(topic)) {
            return false;
        }
        // 完全相等捷径
        if (pattern.equals(topic)) {
            return true;
        }
        // 不含通配符则只有完全相等才匹配(已在上面排除)
        if (!pattern.contains(SINGLE_WILDCARD) && !pattern.contains(MULTI_WILDCARD)) {
            return false;
        }
        // 单一 # 匹配一切非空 topic
        if (MULTI_WILDCARD.equals(pattern)) {
            return true;
        }

        String[] patternSegs = pattern.split(LEVEL_SEPARATOR);
        String[] topicSegs = topic.split(LEVEL_SEPARATOR);

        int pi = 0;
        int ti = 0;
        while (pi < patternSegs.length && ti < topicSegs.length) {
            String p = patternSegs[pi];
            if (MULTI_WILDCARD.equals(p)) {
                // # 必须出现在末尾,且匹配所有剩余段
                return pi == patternSegs.length - 1;
            }
            if (!SINGLE_WILDCARD.equals(p) && !p.equals(topicSegs[ti])) {
                return false;
            }
            pi++;
            ti++;
        }

        // 边界:pattern 用完且 topic 也用完 → 匹配
        if (pi == patternSegs.length && ti == topicSegs.length) {
            return true;
        }
        // pattern 多一段且是 # → 也匹配(如 a/b/# 匹配 a/b)
        return pi == patternSegs.length - 1
                && MULTI_WILDCARD.equals(patternSegs[pi])
                && ti == topicSegs.length;
    }

    /**
     * 批量匹配:topic 是否命中 patterns 中**任一**模式。
     *
     * <p>典型场景 ── 桥接规则配置多个 topic 模板,只要 topic 匹配其中之一即视为命中。
     *
     * @param patterns 模式列表;空集合或 null 表示"不约束",直接放行
     * @param topic    实际 topic
     * @return true=至少有一个 pattern 命中
     */
    public static boolean matchAny(java.util.Collection<String> patterns, String topic) {
        if (patterns == null || patterns.isEmpty()) {
            return true;
        }
        for (String p : patterns) {
            if (match(p, topic)) {
                return true;
            }
        }
        return false;
    }
}
