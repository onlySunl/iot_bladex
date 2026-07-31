package org.springblade.open.exp.client;

/**
 * @author mqttsnet
 **/
public interface ExpBoot {

    PluginObjectScanner getRegister() throws Throwable;

    default void start(String pluginId) {

    }

    default void stop() {

    }
}
