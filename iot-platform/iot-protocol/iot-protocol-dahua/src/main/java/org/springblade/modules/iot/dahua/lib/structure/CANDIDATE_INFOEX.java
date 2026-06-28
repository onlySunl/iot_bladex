package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * @author 251823
 * @description 候选目标信息扩展
 * @date 2021/08/30
 */
public class CANDIDATE_INFOEX extends SdkStructure {
    /**
     * 候选目标类别,参见枚举定义
     */
    public int              emClassType;
    /**
     * 候选目标颜色,参见枚举定义
     */
    public byte[]           szColor = new byte[32];
    /**
     * 相似度,0~1
     */
    public float            fSimilarity;
    /**
     * 特征信息长度
     */
    public int              nFeatureLength;
    /**
     * 特征信息
     */
    public byte[]           szFeatureInfo = new byte[512];
    /**
     * 特征信息扩展
     */
    public byte[]           szFeatureInfoEx = new byte[1024];
    /**
     * 特征信息扩展长度
     */
    public int              nFeatureInfoExLen;
    /**
     * 预留字节
     */
    public byte[]           byReserved = new byte[128];
}
