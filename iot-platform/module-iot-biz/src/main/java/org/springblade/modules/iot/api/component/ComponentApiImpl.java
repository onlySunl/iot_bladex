

package org.springblade.modules.iot.api.component;

import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.service.component.IComponentService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class ComponentApiImpl implements ComponentApi {

    @Resource
    private IComponentService componentService;


    @Override
    public ComponentInfo getInfo(String type) {
        return componentService.getComponent(type);
    }

}
