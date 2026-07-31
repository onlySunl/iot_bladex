package org.springblade.open.exp.client;

/**
 * @author mqttsnet
 * @Description
 * @date 2023/8/28
 * @version 1.0
 **/
public interface ConfigSpi {

    String getProperty(String key, String def);

    class MockConfigSpi implements ConfigSpi {

        @Override
        public String getProperty(String key, String def) {
            return def;
        }

    }
}
