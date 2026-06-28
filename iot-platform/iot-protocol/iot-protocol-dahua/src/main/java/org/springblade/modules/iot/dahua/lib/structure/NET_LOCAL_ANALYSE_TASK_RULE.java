package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 本地分析任务分析规则
*/
public class NET_LOCAL_ANALYSE_TASK_RULE extends SdkStructure
{
    /**
     * 分析大类类型,参见枚举定义 {@link EM_SCENE_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 分析规则类型, 详见dhnetsdk.h中"智能分析事件类型"
    */
    public int              dwRuleType;
    /**
     * 检测的物体类型列表个数
    */
    public int              nObjectTypesCount;
    /**
     * 检测的物体类型列表,参见枚举定义 {@link EM_ANALYSE_OBJECT_TYPE}
    */
    public int[]            emObjectTypes = new int[16];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[516];

    public NET_LOCAL_ANALYSE_TASK_RULE() {
    }
}

