package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 删除录像二次分析任务输出参数
*/
public class NET_OUT_SECONDARY_ANALYSE_REMOVETASK extends SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;

    public NET_OUT_SECONDARY_ANALYSE_REMOVETASK() {
        this.dwSize = this.size();
    }
}

