package org.springblade.common.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kv<K, V> {
    private K key;
    private V value;
}
