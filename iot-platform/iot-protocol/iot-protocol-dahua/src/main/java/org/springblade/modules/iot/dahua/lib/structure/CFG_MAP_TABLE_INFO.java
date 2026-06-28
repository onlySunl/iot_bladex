package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 映射表信息
*/
public class CFG_MAP_TABLE_INFO extends SdkStructure
{
    /**
     * 使能开关
    */
    public int              bEnable;
    /**
     * 服务名称，OldUPnP实现依赖该字段
    */
    public byte[]           szServiceName = new byte[64];
    /**
     * 服务类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_SERVICE_TYPE}
    */
    public int              emServiceType;
    /**
     * 协议类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_PROTOCOL_TYPE}
    */
    public int              emProtocol;
    /**
     * 内部端口
    */
    public int              nInnerPort;
    /**
     * 外部端口
    */
    public int              nOuterPort;

    public CFG_MAP_TABLE_INFO() {
    }
}

