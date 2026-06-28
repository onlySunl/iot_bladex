package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 子模块信息
*/
public class NET_SUBMODULES_INFO extends SdkStructure
{
    /**
     * 模块名称
    */
    public byte[]           szModuleName = new byte[32];
    /**
     * 硬件版本信息
    */
    public byte[]           szHardwareVersion = new byte[64];
    /**
     * 软件版本信息
    */
    public byte[]           szSoftwareVersion = new byte[64];
    /**
     * 模块状态,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_STATE_SUBMODULE}
    */
    public int              emState;
    public byte[]           byReserve = new byte[512];

    public NET_SUBMODULES_INFO() {
    }
}

