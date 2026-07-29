package org.springblade.core.uid.generator;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.uid.UidGenerator;
import org.springblade.core.uid.config.UidProperties;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 雪花算法 UID 生成器
 * 
 * ID 结构 (64位):
 * - 符号位: 1位 (始终为0)
 * - 时间戳: 41位 (毫秒级, 可使用约69年)
 * - 工作机器ID: 10位 (最多1024个节点)
 * - 序列号: 12位 (每毫秒最多4096个ID)
 *
 * @author Chill
 */
@Slf4j
public class SnowflakeUidGenerator implements UidGenerator {

    /**
     * 起始时间戳 (2020-01-01 00:00:00)
     */
    private static final long EPOCH = LocalDateTime.of(2020, 1, 1, 0, 0, 0)
            .toInstant(ZoneOffset.UTC).toEpochMilli();

    /**
     * 工作机器ID位数
     */
    private static final long WORKER_ID_BITS = 10L;

    /**
     * 序列号位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 最大工作机器ID (1023)
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 工作机器ID左移位数
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 时间戳左移位数
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 序列号掩码
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作机器ID
     */
    private final long workerId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    public SnowflakeUidGenerator(UidProperties properties) {
        this.workerId = properties.getWorkerId();
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException(
                    String.format("Worker ID must be between 0 and %d", MAX_WORKER_ID));
        }
        log.info("初始化雪花算法 UID 生成器, workerId={}", workerId);
    }

    @Override
    public synchronized long getUid() {
        long timestamp = currentTimeMillis();

        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    wait(offset << 1);
                    timestamp = currentTimeMillis();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(
                                String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                                        lastTimestamp - timestamp));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Clock moved backwards", e);
                }
            } else {
                throw new RuntimeException(
                        String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                                lastTimestamp - timestamp));
            }
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    @Override
    public String parseUid(long uid) {
        long sequence = uid & SEQUENCE_MASK;
        long workerId = (uid >> WORKER_ID_SHIFT) & MAX_WORKER_ID;
        long timestamp = (uid >> TIMESTAMP_SHIFT) + EPOCH;

        return String.format("UID: %d, Timestamp: %d, WorkerId: %d, Sequence: %d",
                uid, timestamp, workerId, sequence);
    }

    /**
     * 等待下一毫秒
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     */
    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
