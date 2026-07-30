package org.springblade.core.rocketmq.listener;

/**
 * RocketMQ 消息 header key 常量集中定义。
 * <p>
 * 用于 {@code thinglinks-rocketmq-starter} 在 Producer / Consumer 之间透传业务上下文。
 * 业务侧请勿硬编码 header key 字面量，统一引用本接口常量。
 * </p>
 *
 * <h3>核心契约</h3>
 * <ul>
 *   <li>Producer 端通过 {@code RocketmqTemplate} 发送消息时，自动把整个
 *       {@code ContextUtil.getLocalMap()} 序列化为 JSON 塞入 header {@link #LOCAL_MAP}。</li>
 *   <li>Consumer 端继承 {@code AbstractTenantAwareRocketmqListener} 时，框架自动反序列化
 *       恢复到 {@code ContextUtil}，业务方法 {@code onTenantMessage} 入口处即可使用。</li>
 * </ul>
 *
 * <h3>为什么整体传 LocalMap 而非逐字段塞 header</h3>
 * <ul>
 *   <li><b>新增上下文字段 0 改动</b>：未来 ContextUtil 加 requestId / sessionId 等字段，
 *       starter 与业务代码都不需要改，自动跟随。</li>
 *   <li><b>消除"漏字段"事故</b>：业务代码不会因为遗漏某个 setHeader 导致下游消费时
 *       拿不到 traceId / tenantId 等关键字段。</li>
 *   <li><b>跨组件对称</b>：与现有 Feign / xxl-job 等组件的 LocalMap 透传规约保持一致。</li>
 * </ul>
 *
 * <h3>体积约束</h3>
 * 实际场景下 LocalMap 通常 &lt; 1KB；本 starter 设置 {@link #MAX_LOCAL_MAP_SIZE_BYTES} = 8KB
 * 上限，超出仅打 warn 日志不阻断发送（远小于 RocketMQ 消息体默认 4MB 上限，开销可忽略）。
 *
 * <h3>扩展规约</h3>
 * 新增 header 字段：
 * <ol>
 *   <li>仅需在本接口扩常量（命名以 {@link #PREFIX} 开头）</li>
 *   <li>业务侧把对应字段塞进 {@code ContextUtil} 后正常 send 即可，无需改 starter</li>
 * </ol>
 *
 * @author mqttsnet
 * @since 2026-04-28
 */
public interface MessageHeaders {

    /**
     * 自定义 header 名统一前缀。
     * <p>所有 thinglinks 框架级 header 必须以此开头，避免与 RocketMQ 原生 header（系统属性）
     * 或第三方组件 header 命名冲突。
     */
    String PREFIX = "X-Thinglinks-";

    /**
     * 业务上下文 LocalMap 整体序列化 header（JSON 字符串）。
     * <p>
     * 包含 {@code traceId}、{@code tenantId}、{@code userId}、{@code employeeId}、
     * {@code currentDeptId}、{@code tenantBasePoolName} 等所有 ContextUtil 字段。
     * </p>
     *
     * <h4>Header value 样例</h4>
     * <pre>
     * {"TenantId":"123","UserId":"456","traceId":"a1b2c3d4","CurrentDeptId":"789"}
     * </pre>
     *
     * <p>消费端框架会反序列化此 JSON 并整体恢复到 {@code ContextUtil.setLocalMap(...)}，
     * 业务方法即可像在主线程一样使用 {@code ContextUtil.getTenantId()} 等 API。
     */
    String LOCAL_MAP = PREFIX + "LocalMap";

    /**
     * LocalMap header value 体积上限（字节）。
     * <p>超过此值仅打印 warn 日志，不阻断发送。
     * <p>选择 8KB 的依据：
     * <ul>
     *   <li>正常业务场景 LocalMap JSON &lt; 1KB（10-20 个字段，平均每字段 30 字节）</li>
     *   <li>8KB 留出 8 倍冗余，覆盖极端场景（多租户嵌套 / 请求链路追踪扩展字段等）</li>
     *   <li>超过 8KB 通常意味着误把大对象塞进了 LocalMap，应在业务侧修正</li>
     * </ul>
     */
    int MAX_LOCAL_MAP_SIZE_BYTES = 8 * 1024;
}
