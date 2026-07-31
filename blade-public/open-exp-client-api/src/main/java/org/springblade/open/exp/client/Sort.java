package org.springblade.open.exp.client;

/**
 * @version 1.0
 * @author mqttsnet
 * @date 2023/8/10
 **/
public interface Sort extends Comparable<Sort> {

    int getSort();

    @Override
    default int compareTo(Sort o) {
        return o.getSort() - getSort();
    }
}
