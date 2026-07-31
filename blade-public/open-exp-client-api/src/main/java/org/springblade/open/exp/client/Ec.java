package org.springblade.open.exp.client;

/**
 * @version 1.0
 * @author mqttsnet
 * @Description
 * @date 2023/8/14
 **/
public interface Ec<R, P> {

    R run(P p);
}
