

package org.springblade.modules.iot.component.core;


import org.springblade.modules.iot.common.thing.ThingModelMessage;

/**
 * 设备组件接口
 */
public interface Component {

    /**
     * 组件实例id
     */
    String getId();

    /**
     * 获取组件类型
     */
    String getType();

    /**
     * 组件名
     */
    String getName();

    /**
     * 发送消息
     */
    void sendMessage(ThingModelMessage message);

    /**
     * 接收消息
     */
    void onMessage(ThingModelMessage message);
}