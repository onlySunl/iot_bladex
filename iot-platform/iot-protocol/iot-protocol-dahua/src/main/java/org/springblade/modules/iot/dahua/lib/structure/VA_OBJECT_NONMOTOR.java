package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 非机动车目标信息
 */
public class VA_OBJECT_NONMOTOR extends SdkStructure {
    public int              nObjectId;
    /**
     * 候选目标数量
     */
    public int              nCandidateNum;
    /**
     * 候选目标信息,参见结构体定义 {@link CANDIDATE_INFOEX}
     */
    public CANDIDATE_INFOEX[] stuCandidates = new CANDIDATE_INFOEX[32];
    /**
     * 包围盒相对坐标,参见结构体定义 {@link NET_RECT}
     */
    public NET_RECT         stuBoundingBox;
    /**
     * 置信度
     */
    public float            fConfidence;
    /**
     * 跟踪时间戳,单位ms
     */
    public double           dbTS;
    /**
     * 颜色向量数量
     */
    public int              nColorNum;
    /**
     * 颜色向量
     */
    public byte[]           szColors = new byte[32];
    /**
     * 非机动车类型,参见枚举定义 {@link EM_VA_OBJECT_NONMOTOR_TYPE}
     */
    public int              emType;
    /**
     * 速度,km/h
     */
    public float            fSpeed;
    /**
     * 方向,参见枚举定义 {@link EM_OBJECT_MOVING_DIRECTION}
     */
    public int              emDirection;
    /**
     * 预留字节
     */
    public byte[]           byReserved = new byte[248];

    public VA_OBJECT_NONMOTOR() {
        for (int i = 0; i < stuCandidates.length; i++) {
            stuCandidates[i] = new CANDIDATE_INFOEX();
        }
    }
}
