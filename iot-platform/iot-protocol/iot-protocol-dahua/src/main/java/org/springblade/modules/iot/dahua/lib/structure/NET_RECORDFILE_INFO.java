package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 录像文件信息
 */
public class NET_RECORDFILE_INFO extends SdkStructure {
    public int nChannel;                 // 通道号
    public byte[] szFileName = new byte[128];   // 文件名
    public int nFileSize;               // 文件大小
    public int nStartTime;              // 开始时间
    public int nStopTime;               // 结束时间
    public int nDriveNo;                // 盘号
    public byte[] byReserved = new byte[256];  // 保留字段
}
