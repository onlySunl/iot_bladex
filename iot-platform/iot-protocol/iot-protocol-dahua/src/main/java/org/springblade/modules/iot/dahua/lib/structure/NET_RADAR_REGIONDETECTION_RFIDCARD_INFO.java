package org.springblade.modules.iot.dahua.lib.structure;


/**
 * @author 251823
 * @description 雷达区域检测事件的RFID卡片信息
 * @date 2021/07/21
 */
public class NET_RADAR_REGIONDETECTION_RFIDCARD_INFO extends SdkStructure {
	/**
     *  卡片ID
     */
    public byte[]           szCardID = new byte[24];
    /**
     *  保留字节
     */
    public byte[]           byReserved = new byte[256];
}

