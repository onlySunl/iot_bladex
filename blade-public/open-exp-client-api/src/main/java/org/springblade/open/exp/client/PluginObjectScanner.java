package org.springblade.open.exp.client;

import java.util.List;

/**
 * @author mqttsnet
 * 插件返回的的注册器.
 **/
public interface PluginObjectScanner {

    /**
     * 注册
     */
    List<Class<?>> scan() throws Exception;
}
