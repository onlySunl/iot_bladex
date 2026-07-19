

package org.springblade.modules.iot.api.virtualdevice;


import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;

import java.util.List;

public interface VirtualDeviceApi {

    /**
     * 根据表达式获取虚拟设备信息
     *
     * @param trigger
     * @param state
     * @return
     */
    List<VirtualDevice> findByTriggerAndState(String trigger, String state);


}
