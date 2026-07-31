package org.springblade.open.exp.client;

/**
 * @author mqttsnet
 **/
public class ExpAppContextSpiFactory {

    public static ExpAppContext getFirst() {
        return SpiFactory.get(ExpAppContext.class);
    }
}
