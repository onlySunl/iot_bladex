

package org.springblade.modules.iot.message.kafka.partitioner;


import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 根据 produceKey进行分区
 *
 * @author clickear
 */
public class ProductKeyPartitioner implements Partitioner {

    public void ProductPartitioner() {

    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 返回分区数量
        int partitionCount = cluster.partitionsForTopic(topic).size();

        if (value instanceof ThingModelMessage) {
            ThingModelMessage message = (ThingModelMessage) value;
            if (message == null || message.getProductKey() == null) {
                return 0;
            }

            // 使用murmur2算法计算 hashcode。
            int hashCode = Utils.toPositive(Utils.murmur2(message.getProductKey().getBytes(StandardCharsets.UTF_8)));
            return hashCode % partitionCount;
        }

        // 默认分区
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
