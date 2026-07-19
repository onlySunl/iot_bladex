

package org.springblade.modules.iot.api.component;

import org.springblade.modules.iot.api.component.dto.ComponentInfo;

public interface ComponentApi {

    ComponentInfo getInfo(String type);

}
