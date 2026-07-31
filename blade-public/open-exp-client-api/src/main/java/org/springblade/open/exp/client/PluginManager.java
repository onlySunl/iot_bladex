package org.springblade.open.exp.client;

/**
 * @author mqttsnet
 * @Description
 * @date 2023/8/28
 * @version 1.0
 **/
public interface PluginManager {

    void start();

    void stop();

    class PluginManagerMock implements PluginManager {

        @Override
        public void start() {

        }

        @Override
        public void stop() {

        }
    }
}
