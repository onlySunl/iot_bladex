package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 47081
 * @version 1.0
 * @description 设备硬盘信息
 * @date 2021/1/27
 */
public class SDK_HARDDISK_STATE extends SdkStructure {
  /** 个数 */
    public int              dwDiskNum;
  /** 硬盘或分区信息 */
    public NET_DEV_DISKSTATE[] stDisks = (NET_DEV_DISKSTATE[]) new NET_DEV_DISKSTATE().toArray(256);
}

