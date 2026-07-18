package org.springblade.modules.iot.api.enums;

import org.springblade.core.tool.api.IResultCode;

/**
 * IoT 模块错误码枚举，遵循 BladeX IResultCode 规范
 */
public enum ErrorCodeConstants implements IResultCode {
    NOT_EXISTS(2006003000, "信息不存在"),
    // ========== 产品分类 2_006_000_000 补充编号 ==========
    CATEGORY_NOT_EXISTS(2006000000, "产品分类不存在"),
    CATEGORY_EXITS_CHILDREN(2006000001, "存在下级产品分类，无法删除"),
    CATEGORY_PARENT_NOT_EXITS(2006000002, "父级产品分类不存在"),
    CATEGORY_PARENT_ERROR(2006000003, "不能设置自己为父产品分类"),
    CATEGORY_NAME_DUPLICATE(2006000004, "已经存在该分类名称的产品分类"),
    CATEGORY_PARENT_IS_CHILD(2006000005, "不能设置自己的子Category为父Category"),
    CATEGORY_EXITS_PRODUCT(2006000006, "分类下存在产品"),

    // ========== 产品信息 2_006_001_000 ==========
    PRODUCT_NOT_EXISTS(2006001000, "物联网产品不存在"),
    PRODUCT_KEY_REPEAT(2006001001, "productKey重复"),
    PRODUCT_DEVICE_EXISTS(2006001002, "产品下存在设备"),

    // ========== 设备信息 2_006_002_000 ==========
    DEVICE_INFO_NOT_EXISTS(2006002000, "设备信息不存在"),
    DEVICE_DN_REPEAT(2006002001, "设备DN重复"),
    DEVICE_SERIAL_REPEAT(2006002002, "设备序列号重复"),

    // ========== 产品物模型 2_006_003_000 ==========
    THING_MODEL_NOT_EXISTS(2006003000, "物模型信息不存在"),

    // ========== 规则引擎 / 通道配置 2_006_004_000 ==========
    RULE_INFO_NOT_EXISTS(2006004000, "规则引擎不存在"),
    CHANNEL_CONFIG_NOT_EXISTS(2006004001, "通道配置不存在"),
    CHANNEL_CONFIG_USED(2006004002, "通道配置已被使用"),
    CHANNEL_TEMPLATE_USED(2006004003, "通道模板已被使用"),
    FILE_NOT_NULL(2006004004, "文件不许为空"),
    DATA_NOT_EXIST(2006004005, "数据不存在"),
    TEMPLATE_NAME_ALREADY(2006004006, "模板名称已存在"),
    RELATED_PRODUCTS_EXIST(2006004007, "关联产品已存在"),

    // ========== SMS模板相关 2_006_005_000 ==========
    CHANNEL_CONFIG_PARAM_ERROR(2006005000, "通道配置参数错误"),
    SMS_TEMPLATE_CREATE_FAILED(2006005001, "短信模板创建失败"),
    SMS_TEMPLATE_UPDATE_FAILED(2006005002, "短信模板更新失败"),
    SMS_TEMPLATE_DELETE_FAILED(2006005003, "短信模板删除失败"),
    SMS_TEMPLATE_UPDATE_ERROR_IN_AUDIT(2006005004, "待审核状态无法更新模板内容"),
    SMS_TEMPLATE_DELETE_ERROR_IN_AUDIT(2006005005, "待审核状态无法删除"),
    SMS_SEND_FAILED(2006005006, "短信发送失败");

    private final int code;
    private final String msg;

    ErrorCodeConstants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return getMsg();
    }

    /**
     * 实现 IResultCode 接口方法
     */
    @Override
    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}