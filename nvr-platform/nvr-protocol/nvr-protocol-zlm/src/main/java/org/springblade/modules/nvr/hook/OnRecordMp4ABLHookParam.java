package org.springblade.modules.nvr.hook;

import org.springblade.modules.nvr.hook.ABLHookParam;
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
