package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 智能分析图片信息
 */
public class NET_INTELLIGENCE_IMAGE_INFO extends SdkStructure {
    /**
     * 图片是否有效
     */
    public int              bEnabled;
    /**
     * 图片ID
     */
    public int              nImageID;
    /**
     * 图片类型
     */
    public int              emImageType;
    /**
     * 图片数据
     */
    public byte[]           szFilePath = new byte[260];
    /**
     * 图片宽度
     */
    public int              nWidth;
    /**
     * 图片高度
     */
    public int              nHeight;
    /**
     * 图片质量
     */
    public double           dbPTS;
    /**
     * 保留字段
     */
    public byte[]           byReserved = new byte[252];
}
