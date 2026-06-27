
package org.springblade.modules.iot.common.utils;

/**
 * 雪花算法ID生成器
 */
public class SnowflakeIdWorker {
    /**
     * 开始时间：2020-01-01 00:00:00
     */
    private final long beginTs = 1577808000000L;

    private final long workerIdBits = 10;

    /**
     * 2^10 - 1 = 1023
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    private final long sequenceBits = 12;

    /**
     * 2^12 - 1 = 4095
     */
    private final long maxSequence = -1L ^ (-1L << sequenceBits);

    /**
     * 时间戳左移22位
     */
    private final long timestampLeftOffset = workerIdBits + sequenceBits;

    /**
     * 业务ID左移12位
     */
    private final long workerIdLeftOffset = sequenceBits;

    /**
     * 合并了机器ID和数据标示ID，统称业务ID，10位
     */
    private long workerId;

    /**
     * 毫秒内序列，12位
     */
    private long sequence = 0L;

    /**
     * 上一次生成的ID的时间戳
     */
    private long lastTimestamp = -1L;

    public SnowflakeIdWorker(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("WorkerId必须大于或等于0且小于或等于%d", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退了%d毫秒", (lastTimestamp - ts)));
        }
        if (ts == lastTimestamp) {
            if (++sequence > maxSequence) {
                ts = tilNextMillis(lastTimestamp);
                sequence = 0L;
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = ts;
        return (ts - beginTs) << timestampLeftOffset | workerId << workerIdLeftOffset | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long ts = System.currentTimeMillis();
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis();
        }
        return ts;
    }
}
