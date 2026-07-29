package org.springblade.core.cache.repository;

import java.util.*;
import java.util.function.Function;

import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.core.cache.redis2.CacheResult;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 缓存操作公共接口
 *
 * @author mqttsnet
 * @date 2019/08/07
 */
public interface CacheOps {

    /**
     * 删除指定的key
     *
     * @param keys 多个key
     * @return 删除个数
     */
    Long del(@NonNull CacheKey... keys);

    /**
     * 删除指定的key
     *
     * @param keys 多个key
     * @return 删除个数
     */
    Long del(@NonNull Collection<CacheKey> keys);

    /**
     * 删除指定的key
     *
     * @param keys 多个key
     * @return 删除个数
     */
    Long del(@NonNull String... keys);

    /**
     * 判断指定的key 是否存在
     *
     * @param key key
     * @return 是否存在
     */
    Boolean exists(@NonNull CacheKey key);

    /**
     * 添加到带有 过期时间的  缓存
     *
     * @param key             redis主键
     * @param value           值
     * @param cacheNullValues 是否缓存null对象
     */
    void set(@NonNull CacheKey key, Object value, boolean... cacheNullValues);

    /**
     * 根据key获取对象
     *
     * @param key             redis主键
     * @param cacheNullValues 是否缓存null对象
     * @return 值 不存在时，返回null
     */
    <T> CacheResult<T> get(@NonNull CacheKey key, boolean... cacheNullValues);

    /**
     * 根据key获取对象
     *
     * @param key             redis主键
     * @param cacheNullValues 是否缓存null对象
     * @return 值 不存在时，返回null
     */
    <T> CacheResult<T> get(@NonNull String key, boolean... cacheNullValues);

    /**
     * 根据keys获取对象
     *
     * @param keys redis主键
     * @return 值 不存在时，返回空集合
     */
    <T> List<CacheResult<T>> find(@NonNull Collection<CacheKey> keys);

    /**
     * 根据key获取对象
     * 不存在时，调用function回调获取数据，并set进入，然后返回
     *
     * @param key             redis主键
     * @param loader          加载器
     * @param cacheNullValues 是否缓存null对象
     * @return 值
     */
    <T> CacheResult<T> get(@NonNull CacheKey key, Function<CacheKey, ? extends T> loader, boolean... cacheNullValues);

    /**
     * 清空所有存储的数据
     */
    void flushDb();

    /**
     * 为键 key 储存的数字值加上一。
     *
     * @param key 一定不能为 {@literal null}.
     * @return 返回键 key 在执行加一操作之后的值。
     */
    Long incr(@NonNull CacheKey key);

    /**
     * 获取key中存放的Long值
     *
     * @param key    一定不能为 {@literal null}.
     * @param loader 加载
     * @return key中存储的的数字
     */
    Long getCounter(@NonNull CacheKey key, Function<CacheKey, Long> loader);

    /**
     * 为键 key 储存的数字值加上increment。
     *
     * @param key       一定不能为 {@literal null}.
     * @param increment 增量值
     * @return 返回键 key 在执行加一操作之后的值。
     */
    Long incrBy(@NonNull CacheKey key, long increment);

    /**
     * 为键 key 储存的数字值加上一。
     *
     * @param key       一定不能为 {@literal null}.
     * @param increment 增量值
     * @return 返回键 key 在执行加一操作之后的值。
     */
    Double incrByFloat(@NonNull CacheKey key, double increment);

    /**
     * 为键 key 储存的数字值减去一。
     *
     * @param key 一定不能为 {@literal null}.
     * @return 在减去增量 1 之后， 键 key 的值。
     */
    Long decr(@NonNull CacheKey key);

    /**
     * 将 key 所储存的值减去减量 decrement 。
     *
     * @param key       一定不能为 {@literal null}.
     * @param decrement 增量值
     * @return 在减去增量 decrement 之后， 键 key 的值。
     */
    Long decrBy(@NonNull CacheKey key, long decrement);

// ---------------------------- zSet start ----------------------------

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     * score 值可以是整数值或双精度浮点数。
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key    一定不能为 {@literal null}.
     * @param score  得分
     * @param member 值
     * @return 是否成功
     * @see <a href="https://redis.io/commands/zadd">Redis Documentation: ZADD</a>
     */
    Boolean zAdd(@NonNull CacheKey key, Object member, double score);


    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     * score 值可以是整数值或双精度浮点数。
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key          一定不能为 {@literal null}.
     * @param scoreMembers 一定不能为 {@literal null}.
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     * @see <a href="https://redis.io/commands/zadd">Redis Documentation: ZADD</a>
     */
    Long zAdd(@NonNull CacheKey key, Map<Object, Double> scoreMembers);


    /**
     * 返回有序集 key 中，成员 member 的 score 值。
     * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member the value.
     * @return member 成员的 score 值，以字符串形式表示
     * @see <a href="https://redis.io/commands/zscore">Redis Documentation: ZSCORE</a>
     */
    Double zScore(@NonNull CacheKey key, Object member);

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
     * 当 key 不是有序集类型时，返回一个错误。
     * score 值可以是整数值或双精度浮点数。
     *
     * @param key    一定不能为 {@literal null}.
     * @param score  得分
     * @param member the value.
     * @return member 成员的新 score 值
     * @see <a href="https://redis.io/commands/zincrby">Redis Documentation: ZINCRBY</a>
     */
    Double zIncrBy(@NonNull CacheKey key, Object member, double score);


    /**
     * 返回有序集 key 的基数。
     *
     * @param key 一定不能为 {@literal null}.
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
     * @see <a href="https://redis.io/commands/zcard">Redis Documentation: ZCARD</a>
     */
    Long zCard(@NonNull CacheKey key);

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小值
     * @param max 最大值
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/zcount">Redis Documentation: ZCOUNT</a>
     */
    Long zCount(@NonNull CacheKey key, double min, double max);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * <p>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * <p>
     * 超出范围的下标并不会引起错误。 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
     * <p>
     * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等
     *
     * @param key   一定不能为 {@literal null}.
     * @param start 索引
     * @param end   索引
     * @return 指定区间内，不带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrange">Redis Documentation: ZRANGE</a>
     */
    @Nullable
    Set<Object> zRange(@NonNull CacheKey key, long start, long end);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * <p>
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * <p>
     * 超出范围的下标并不会引起错误。 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
     * <p>
     * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等
     *
     * @param key   一定不能为 {@literal null}.
     * @param start 索引
     * @param end   索引
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrange">Redis Documentation: ZRANGE</a>
     */
    @Nullable
    Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(@NonNull CacheKey key, long start, long end);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递减(从大到小)来排列。 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE key start stop [WITHSCORES] 命令一样。
     *
     * @param key   一定不能为 {@literal null}.
     * @param start 索引
     * @param end   索引
     * @return 指定区间内，不带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrevrange">Redis Documentation: ZREVRANGE</a>
     */
    @Nullable
    Set<Object> zRevrange(@NonNull CacheKey key, long start, long end);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递减(从大到小)来排列。 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE key start stop [WITHSCORES] 命令一样。
     *
     * @param key   一定不能为 {@literal null}.
     * @param start 索引
     * @param end   索引
     * @return 指定区间内，不带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrevrange">Redis Documentation: ZREVRANGE</a>
     */
    @Nullable
    Set<ZSetOperations.TypedTuple<Object>> zRevrangeWithScores(@NonNull CacheKey key, long start, long end);

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     * 有序集成员按 score 值递增(从小到大)次序排列。
     * 具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小得分
     * @param max 最大得分
     * @return 指定区间内 不带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrangebyscore">Redis Documentation: ZRANGEBYSCORE</a>
     */
    Set<Object> zRangeByScore(@NonNull CacheKey key, double min, double max);

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     * 有序集成员按 score 值递增(从小到大)次序排列。
     * 具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小得分
     * @param max 最大得分
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrangebyscore">Redis Documentation: ZRANGEBYSCORE</a>
     */
    Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(@NonNull CacheKey key, double min, double max);

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小得分
     * @param max 最大得分
     * @return 指定区间内 不带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrevrange">Redis Documentation: ZRANGEBYSCORE</a>
     */
    Set<Object> zReverseRange(@NonNull CacheKey key, double min, double max);

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小得分
     * @param max 最大得分
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
     * @see <a href="https://redis.io/commands/zrevrangebyscore">Redis Documentation: ZRANGEBYSCORE</a>
     */
    Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(@NonNull CacheKey key, double min, double max);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     * 使用 ZREVRANK key member 命令可以获得成员按 score 值递减(从大到小)排列的排名。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member the value.
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key 的成员，返回 nil 。
     * @see <a href="https://redis.io/commands/zrank">Redis Documentation: ZRANK</a>
     */
    @Nullable
    Long zRank(@NonNull CacheKey key, Object member);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
     * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member the value.
     * @return 如果 member 是有序集 key 的成员，返回 member 的排名。 如果 member 不是有序集 key 的成员，返回 nil 。
     * @see <a href="https://redis.io/commands/zrevrank">Redis Documentation: ZREVRANK</a>
     */
    Long zRevrank(@NonNull CacheKey key, Object member);

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key     一定不能为 {@literal null}.
     * @param members 一定不能为 {@literal null}.
     * @return 被成功移除的成员的数量，不包括被忽略的成员
     * @see <a href="https://redis.io/commands/zrem">Redis Documentation: ZREM</a>
     */
    Long zRem(@NonNull CacheKey key, Object... members);

    /**
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
     * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     *
     * @param key   一定不能为 {@literal null}.
     * @param start 下标
     * @param end   下标
     * @return 被移除成员的数量。
     * @see <a href="https://redis.io/commands/zremrangebyrank">Redis Documentation: ZREMRANGEBYRANK</a>
     */
    Long zRem(@NonNull CacheKey key, long start, long end);

    /**
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     * 自版本2.1.6开始， score 值等于 min 或 max 的成员也可以不包括在内
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小得分
     * @param max 最大得分
     * @return 被移除成员的数量。
     * @see <a href="https://redis.io/commands/zremrangebyscore">Redis Documentation: ZREMRANGEBYSCORE</a>
     */
    Long zRemRangeByScore(@NonNull CacheKey key, double min, double max);

    // ---------------------------- zSet end ----------------------------


    // ---------------------------- Counter start ----------------------------

    /**
     * 增加指定 key 的计数。
     * 如果 key 不存在，它会被创建并初始化为 1。
     * 如果 key 存在，其值会增加 1。
     *
     * @param key 用于计数的 key，一定不能为 {@literal null}.
     * @return 增加后的计数值
     */
    Long incrementCounter(@NonNull CacheKey key);

    /**
     * 获取指定 key 的当前计数值。
     * 如果 key 不存在或其值不是数字，将返回 Optional.empty()。
     *
     * @param key 要查询计数的 key，一定不能为 {@literal null}.
     * @return 包含 key 当前计数值的 Optional 对象，如果 key 不存在或值不是数字，则为 Optional.empty()
     */
    Optional<Long> getCounter(@NonNull CacheKey key);


    /**
     * 增加指定 hashKey 的计数。
     * 如果 hashKey 不存在，它会被创建并初始化为 1。
     * 如果 hashKey 存在，其值会增加 1。
     *
     * @param hashKey 用于计数的 hashKey，一定不能为 {@literal null}.
     * @return 增加后的计数值
     */
    Long incrementHashCounter(@NonNull CacheHashKey hashKey);

    /**
     * 增加指定 hashKey 的计数（支持自定义增量值）。
     * 如果 hashKey 不存在，它会被创建并初始化为 delta。
     * 如果 hashKey 存在，其值会增加 delta。
     *
     * @param hashKey 用于计数的 hashKey，一定不能为 {@literal null}.
     * @param delta   增量值，可以为负数
     * @return 增加后的计数值
     */
    Long incrementHashCounter(@NonNull CacheHashKey hashKey, long delta);

    // ---------------------------- Counter end ----------------------------


}
