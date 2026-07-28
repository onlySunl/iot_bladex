package org.springblade.core.cache.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.springblade.basic.model.cache.CacheHashKey;
import org.springblade.basic.model.cache.CacheKey;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.cache.redis2.CacheResult;
import org.springblade.core.cache.redis2.RedisOps;
import org.springblade.core.cache.repository.CacheOps;
import org.springblade.core.cache.repository.CachePlusOps;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.NonNull;

/**
 * Redis Repository
 * redis 基本操作 可扩展,基本够用了
 *
 * @author mqttsnet
 * @date 2019-08-06 10:42
 */
@Slf4j
public class RedisOpsImpl implements CacheOps, CachePlusOps {

    /**
     * Spring Redis Template
     */
    private final RedisOps redisOps;

    public RedisOpsImpl(RedisOps redisOps) {
        this.redisOps = redisOps;
    }

    /**
     * 获取 RedisTemplate对象
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisOps.getRedisTemplate();
    }

    @Override
    public void publish(@NonNull String channel, @NonNull Object message) {
        // RedisTemplate.convertAndSend 内部走 Redis PUBLISH 命令;订阅方通过
        // RedisMessageListenerContainer 监听 channel 收到消息。
        redisOps.getRedisTemplate().convertAndSend(channel, message);
    }

    @Override
    public Long del(@NonNull CacheKey... keys) {
        return redisOps.del(keys);
    }

    @Override
    public Long del(@NonNull Collection<CacheKey> keys) {
        return redisOps.del(keys);
    }

    @Override
    public Long del(String... keys) {
        return redisOps.del(keys);
    }

    @Override
    public Boolean exists(@NonNull CacheKey key) {
        return redisOps.exists(key.getKey());
    }

    @Override
    public void set(@NonNull CacheKey key, Object value, boolean... cacheNullValues) {
        redisOps.set(key, value, cacheNullValues);
    }

    @Override
    public <T> CacheResult<T> get(@NonNull CacheKey key, boolean... cacheNullValues) {
        return redisOps.get(key, cacheNullValues);
    }

    @Override
    public <T> CacheResult<T> get(@NonNull String key, boolean... cacheNullValues) {
        return redisOps.get(key, cacheNullValues);
    }

    @Override
    public <T> List<CacheResult<T>> find(@NonNull Collection<CacheKey> keys) {
        return redisOps.mGetByCacheKey(keys);
    }

    @Override
    public <T> CacheResult<T> get(@NonNull CacheKey key, Function<CacheKey, ? extends T> loader, boolean... cacheNullValues) {
        return redisOps.get(key, loader, cacheNullValues);
    }

    /**
     * 清空redis存储的数据
     */
    @Override
    public void flushDb() {
        redisOps.getRedisTemplate().execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    @Override
    public Long incr(@NonNull CacheKey key) {
        return redisOps.incr(key);
    }

    @Override
    public Long getCounter(CacheKey key, Function<CacheKey, Long> loader) {
        return redisOps.getCounter(key, loader);
    }

    @Override
    public Long incrBy(@NonNull CacheKey key, long increment) {
        return redisOps.incrBy(key, increment);
    }

    @Override
    public Double incrByFloat(@NonNull CacheKey key, double increment) {
        return redisOps.incrByFloat(key, increment);
    }

    @Override
    public Long decr(@NonNull CacheKey key) {
        return redisOps.decr(key);
    }

    @Override
    public Long decrBy(@NonNull CacheKey key, long decrement) {
        return redisOps.decrBy(key, decrement);
    }

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     * score 值可以是整数值或双精度浮点数。
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member 值
     * @param score  得分
     * @return 是否成功
     * @see <a href="https://redis.io/commands/zadd">Redis Documentation: ZADD</a>
     */
    @Override
    public Boolean zAdd(CacheKey key, Object member, double score) {
        return redisOps.zAdd(key.getKey(), member, score);
    }

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
    @Override
    public Long zAdd(CacheKey key, Map<Object, Double> scoreMembers) {
        return redisOps.zAdd(key.getKey(), scoreMembers);
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值。
     * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member the value.
     * @return member 成员的 score 值，以字符串形式表示
     * @see <a href="https://redis.io/commands/zscore">Redis Documentation: ZSCORE</a>
     */
    @Override
    public Double zScore(CacheKey key, Object member) {
        return redisOps.zScore(key.getKey(), member);
    }

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
     * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
     * 当 key 不是有序集类型时，返回一个错误。
     * score 值可以是整数值或双精度浮点数。
     *
     * @param key    一定不能为 {@literal null}.
     * @param member the value.
     * @param score  得分
     * @return member 成员的新 score 值
     * @see <a href="https://redis.io/commands/zincrby">Redis Documentation: ZINCRBY</a>
     */
    @Override
    public Double zIncrBy(CacheKey key, Object member, double score) {
        return redisOps.zIncrBy(key.getKey(), member, score);
    }

    /**
     * 返回有序集 key 的基数。
     *
     * @param key 一定不能为 {@literal null}.
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
     * @see <a href="https://redis.io/commands/zcard">Redis Documentation: ZCARD</a>
     */
    @Override
    public Long zCard(CacheKey key) {
        return redisOps.zCard(key.getKey());
    }

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
     *
     * @param key 一定不能为 {@literal null}.
     * @param min 最小值
     * @param max 最大值
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="https://redis.io/commands/zcount">Redis Documentation: ZCOUNT</a>
     */
    @Override
    public Long zCount(CacheKey key, double min, double max) {
        return redisOps.zCount(key.getKey(), min, max);
    }

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
    @Override
    public Set<Object> zRange(CacheKey key, long start, long end) {
        return redisOps.zRange(key.getKey(), start, end);
    }

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
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(CacheKey key, long start, long end) {
        return redisOps.zRangeWithScores(key.getKey(), start, end);
    }

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
    @Override
    public Set<Object> zRevrange(CacheKey key, long start, long end) {
        return redisOps.zRevrange(key.getKey(), start, end);
    }

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
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRevrangeWithScores(CacheKey key, long start, long end) {
        return redisOps.zRevrangeWithScores(key.getKey(), start, end);
    }

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
    @Override
    public Set<Object> zRangeByScore(CacheKey key, double min, double max) {
        return redisOps.zRangeByScore(key.getKey(), min, max);
    }

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
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(CacheKey key, double min, double max) {
        return redisOps.zRangeByScoreWithScores(key.getKey(), min, max);
    }

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
    @Override
    public Set<Object> zReverseRange(CacheKey key, double min, double max) {
        return redisOps.zReverseRange(key.getKey(), min, max);
    }

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
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(CacheKey key, double min, double max) {
        return redisOps.zReverseRangeByScoreWithScores(key.getKey(), min, max);
    }

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
    @Override
    public Long zRank(CacheKey key, Object member) {
        return redisOps.zRank(key.getKey(), member);
    }

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
    @Override
    public Long zRevrank(CacheKey key, Object member) {
        return redisOps.zRevrank(key.getKey(), member);
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     *
     * @param key     一定不能为 {@literal null}.
     * @param members 一定不能为 {@literal null}.
     * @return 被成功移除的成员的数量，不包括被忽略的成员
     * @see <a href="https://redis.io/commands/zrem">Redis Documentation: ZREM</a>
     */
    @Override
    public Long zRem(CacheKey key, Object... members) {
        return redisOps.zRem(key.getKey(), members);
    }

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
    @Override
    public Long zRem(CacheKey key, long start, long end) {
        return redisOps.zRem(key.getKey(), start, end);
    }

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
    @Override
    public Long zRemRangeByScore(@NonNull CacheKey key, double min, double max) {
        return redisOps.zRemRangeByScore(key.getKey(), min, max);
    }

    /**
     * 增加指定 key 的计数。
     * 如果 key 不存在，它会被创建并初始化为 1。
     * 如果 key 存在，其值会增加 1。
     *
     * @param key 用于计数的 key，一定不能为 {@literal null}.
     * @return 增加后的计数值
     */
    @Override
    public Long incrementCounter(@NonNull CacheKey key) {
        return redisOps.incrementCounter(key);
    }


    /**
     * 增加指定 Hash 结构中 field 的计数。
     * 如果 key 或 field 不存在，会被创建并初始化为 1。
     * 如果 field 存在，其值会增加 1。
     *
     * @param hashKey Hash 结构的 key 和 field，一定不能为 {@literal null}.
     * @return 增加后的计数值
     * @see <a href="https://redis.io/commands/hincrby">Redis Documentation: HINCRBY</a>
     */
    @Override
    public Long incrementHashCounter(@NonNull CacheHashKey hashKey) {
        return redisOps.incrementHashCounter(hashKey);
    }


    /**
     * 增加指定 Hash 结构中 field 的计数（支持自定义增量值）。
     * 如果 key 或 field 不存在，会被创建并初始化为 delta。
     * 如果 field 存在，其值会增加 delta。
     *
     * @param hashKey Hash 结构的 key 和 field，一定不能为 {@literal null}.
     * @param delta   增量值，可以为负数
     * @return 增加后的计数值
     * @see <a href="https://redis.io/commands/hincrby">Redis Documentation: HINCRBY</a>
     */
    @Override
    public Long incrementHashCounter(@NonNull CacheHashKey hashKey, long delta) {
        return redisOps.incrementHashCounter(hashKey, delta);
    }


    /**
     * 获取指定 key 的当前计数值。
     * 如果 key 不存在或其值不是数字，将返回 Optional.empty()。
     *
     * @param key 要查询计数的 key，一定不能为 {@literal null}.
     * @return 包含 key 当前计数值的 Optional 对象，如果 key 不存在或值不是数字，则为 Optional.empty()
     */
    @Override
    public Optional<Long> getCounter(CacheKey key) {
        return redisOps.getCounter(key.getKey());
    }

    @Override
    public Set<String> keys(@NonNull String pattern) {
        return redisOps.keys(pattern);
    }

    @Override
    public List<String> scan(@NonNull String pattern) {
        return redisOps.scan(pattern);
    }

    @Override
    public void scanUnlink(@NonNull String pattern) {
        redisOps.scanUnlink(pattern);
    }

    @Override
    public Boolean expire(@NonNull CacheKey key) {
        assert key.getExpire() != null;
        return redisOps.expire(key.getKey(), key.getExpire());
    }

    @Override
    public Boolean persist(@NonNull CacheKey key) {
        return redisOps.persist(key.getKey());
    }

    @Override
    public String type(@NonNull CacheKey key) {
        return redisOps.type(key.getKey());
    }

    @Override
    public Long ttl(@NonNull CacheKey key) {
        return redisOps.ttl(key.getKey());
    }

    @Override
    public Long pTtl(@NonNull CacheKey key) {
        return redisOps.pTtl(key.getKey());
    }

    @Override
    public void hSet(@NonNull CacheHashKey key, Object value, boolean... cacheNullValues) {
        redisOps.hSet(key, value, cacheNullValues);
    }

    @Override
    public void hMSet(@NonNull CacheKey hashKey, @NonNull Map<? extends Object, ? extends Object> fieldValues) {
        redisOps.hMSet(hashKey.getKey(), fieldValues);
    }

    @Override
    public <T> CacheResult<T> hGet(@NonNull CacheHashKey key, boolean... cacheNullValues) {
        return redisOps.hGet(key, cacheNullValues);
    }

    @Override
    public <T> CacheResult<T> hGet(@NonNull CacheHashKey key, Function<CacheHashKey, T> loader, boolean... cacheNullValues) {
        return redisOps.hGet(key, loader, cacheNullValues);
    }

    @Override
    public Boolean hExists(@NonNull CacheHashKey cacheHashKey) {
        return redisOps.hExists(cacheHashKey);
    }

    @Override
    public Long hDel(@NonNull String key, Object... fields) {
        return redisOps.hDel(key, fields);
    }

    @Override
    public Long hDel(@NonNull CacheHashKey cacheHashKey) {
        return redisOps.hDel(cacheHashKey.getKey(), cacheHashKey.getField());
    }

    @Override
    public Long hLen(@NonNull CacheHashKey key) {
        return redisOps.hLen(key.getKey());
    }

    @Override
    public Long hIncrBy(@NonNull CacheHashKey key, long increment) {
        return redisOps.hIncrBy(key, increment);
    }

    @Override
    public Double hIncrBy(@NonNull CacheHashKey key, double increment) {
        return redisOps.hIncrByFloat(key, increment);
    }

    @Override
    public <HK> Set<HK> hKeys(@NonNull CacheHashKey key) {
        return redisOps.hKeys(key.getKey());
    }

    @Override
    public <HV> List<CacheResult<HV>> hVals(@NonNull CacheHashKey key) {
        return redisOps.hVals(key.getKey());
    }


    @Override
    public <K, V> Map<K, CacheResult<V>> hGetAll(@NonNull CacheKey key) {
        return redisOps.hGetAll(key);
    }

    @Override
    public <K, V> Map<K, CacheResult<V>> hGetAll(@NonNull CacheKey key, Function<CacheKey, Map<K, V>> loader, boolean... cacheNullValues) {
        return redisOps.hGetAll(key, loader, cacheNullValues);
    }


    @Override
    public Long sAdd(@NonNull CacheKey key, Object value) {
        Long result = redisOps.sAdd(key, value);
        if (key.getExpire() != null) {
            redisOps.expire(key.getKey(), key.getExpire());
        }
        return result;
    }

    @Override
    public Long sRem(@NonNull CacheKey key, Object... members) {
        return redisOps.sRem(key, members);
    }

    @Override
    public Set<Object> sMembers(@NonNull CacheKey key) {
        return redisOps.sMembers(key);
    }

    @Override
    public <T> T sPop(@NonNull CacheKey key) {
        return redisOps.sPop(key);
    }

    @Override
    public Long sCard(@NonNull CacheKey key) {
        return redisOps.sCard(key);
    }
}
