package org.springblade.modules.iot.domain;

import org.springblade.modules.iot.hook.HookData;
import lombok.Data;

@Data
public class OpenRTPServerResult {

    private SSRCInfo ssrcInfo;
    private HookData hookData;
}
