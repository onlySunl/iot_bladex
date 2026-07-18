

package org.springblade.modules.iot.message.spring;

public interface ReceiveMessage<T> {
    void onMessage(SpringEventMessage<T> event);
}
