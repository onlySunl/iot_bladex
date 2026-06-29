package org.springblade.modules.iot.zlm.hook;

import org.springblade.modules.iot.hook.ABLHookParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRecordMp4ABLHookParam extends ABLHookParam {
    private String fileName;
    private String startTime;
    private String endTime;
    private long fileSize;
}
