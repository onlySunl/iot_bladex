package org.springblade.modules.iot.zlm.domain;

import com.ruoyi.zlm.hook.HookData;
import lombok.Data;

@Data
public class OpenRTPServerResult {

    private SSRCInfo ssrcInfo;
    private HookData hookData;
}
