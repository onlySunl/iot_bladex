package org.springblade.core.databridge.spi;

/**
 * Sink 错误信息工具。
 * <p>
 * 解决一个普遍问题:很多 client 库(RocketMQ {@code MQClientException} /
 * Kafka {@code TimeoutException} / Spring AMQP / OkHttp 等)的外层 message
 * 经常只写"Send failed"或"Operation timed out"这种语义贫乏的描述,
 * 真正的根因(连接被拒/认证失败/找不到队列等)藏在 {@link Throwable#getCause()} 链里。
 * 之前 Sink 一律 {@code log.warn(... e.getMessage())} 把 cause chain 吞掉,
 * 排查时第一眼看不到根因。
 *
 * <h3>使用规约</h3>
 * <ul>
 *   <li>Sink 的 catch 块统一调 {@link #causeChain(Throwable)} 拿完整文本</li>
 *   <li>同时把 {@code e} 整体作为最后一个参数传给 SLF4J logger,触发 stack trace 打印</li>
 *   <li>{@code SendResult.fail(e, latency)} 自动透传 ── {@code SendResult.errorMessage()}
 *       内部也调 cause chain 逻辑,前端 / 日志表 errorMsg 字段自动展开</li>
 * </ul>
 *
 * @author mqttsnet
 * @since 2026-05-08
 */
public final class SinkErrors {

    /**
     * cause chain 最大递归深度(防极端循环引用 / 反射代理深嵌)。
     */
    private static final int MAX_DEPTH = 8;

    private SinkErrors() {
        // 工具类禁实例化
    }

    /**
     * 把 throwable 的整个 cause chain 拼成可读多行字符串。
     * <p>
     * 第一层:{@code t.getMessage()};后续每层前加 {@code "\nCaused by: <ClassName>: "},
     * 跟 JVM stack trace 里 "Caused by:" 风格对齐,排查时直接复制即可对照源码。
     *
     * @param ex 异常对象(允许 null,返回空串)
     * @return 完整 cause chain message;ex 为 null 返回空串
     */
    public static String causeChain(Throwable ex) {
        if (ex == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(256);
        Throwable t = ex;
        int depth = 0;
        while (t != null && depth < MAX_DEPTH) {
            if (depth > 0) {
                sb.append("\nCaused by: ").append(t.getClass().getName()).append(": ");
            }
            sb.append(t.getMessage() == null ? t.toString() : t.getMessage());
            t = t.getCause();
            depth++;
        }
        return sb.toString();
    }
}
