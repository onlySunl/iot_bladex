package org.springblade.modules.nvr.domain;

import org.springblade.modules.nvr.hook.HookData;
import lombok.Data;

@Data
public class OpenRTPServerResult {

    private SSRCInfo ssrcInfo;
    private HookData hookData;
}
