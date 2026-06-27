package org.springblade.modules.iot.zlm.domain;

import org.springblade.modules.iot.zlm.hook.HookData;
import lombok.Data;

@Data
public class OpenRTPServerResult {

    private SSRCInfo ssrcInfo;
    private HookData hookData;
}
