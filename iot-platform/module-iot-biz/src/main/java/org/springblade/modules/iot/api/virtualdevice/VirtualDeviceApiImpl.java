

package org.springblade.modules.iot.api.virtualdevice;

import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.service.virtualdevice.IVirtualDeviceService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class VirtualDeviceApiImpl implements VirtualDeviceApi {

    @Resource
    private IVirtualDeviceService virtualDeviceService;

    @Override
    public List<VirtualDevice> findByTriggerAndState(String trigger, String state) {
        return virtualDeviceService.findByTriggerAndState(trigger, state);
    }

}
