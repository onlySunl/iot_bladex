package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * @author 251823
 * @description 人员名单信息
 * @date 2021/08/30
 */
public class MAN_NUM_LIST_INFO extends SdkStructure {
    /**
     * 人员ID
     */
    public int              nPersonId;
    /**
     * 人员类型,参见枚举定义 {@link EM_PERSON_TYPE}
     */
    public int              emPersonType;
    /**
     * 姓名
     */
    public byte[]           szName = new byte[32];
    /**
     * 工种
     */
    public byte[]           szWorkType = new byte[32];
    /**
     * 所属部门
     */
    public byte[]           szDepartment = new byte[64];
    /**
     * 性别,参见枚举定义 {@link EM_GENDER_TYPE}
     */
    public int              emGender;
    /**
     * 年龄
     */
    public int              nAge;
    /**
     * 人员数量
     */
    public int              nPersonNum;
    /**
     * 预留字节
     */
    public byte[]           byReserved = new byte[252];
}
