package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * 旧的日志结构体,为了和新的对齐
 * @author 47081
 */
public class NET_STU_OLD_LOG extends SdkStructure {
    /**
     * 旧的日志结构体
     */
    public SDK_LOG_ITEM     stuLog;
    /**
     * 保留
     */
    public byte[]           bReserved = new byte[48];
}

