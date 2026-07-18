package org.springblade.modules.iot.common.constant;

import org.springblade.core.tool.api.IResultCode;

/**
 * IoT 公共模块错误码枚举，适配BladeX IResultCode规范
 */
public enum ErrorCodeConstants implements IResultCode {

    // ========== 产品信息 2007001xxx ==========
    PRODUCT_NOT_EXISTS(2007001000, "产品不存在"),

    // ========== 设备信息 2007002xxx ==========
    DEVICE_INFO_NOT_EXISTS(2007002000, "设备信息不存在"),
    DN_NOT_EXISTS(2007002001, "设备DN不存在"),
    DEVICE_CLIENT_WRONG(2007002002, "client_id异常"),
    DN_WRONG(2007002003, "DN错误"),
    DEVICE_PASSWORD_WRONG(2007002004, "密码错误"),
    DEVICE_AUTH_EXCEPTION(2007002005, "认证异常"),
    PARAMS_EXCEPTION(2007002006, "参数错误"),
    DEVICE_ACTION_FAILED(2007002007, "设备动作执行失败"),
    ROUTER_NOT_EXISTS(2007002008, "未找到设备路由"),
    COMPONENT_NOT_EXISTS(2007002009, "设备组件未找到"),

    // ========== 产品物模型 2007003xxx ==========
    THING_MODEL_NOT_EXISTS(2007003000, "物模型信息不存在"),

    // ========== 规则引擎、时序库、请求发送 2007007xxx ==========
    SEND_REQUEST_ERROR(2007007000, "发送请求失败"),
    DATA_BLANK(2007007001, "规则引擎符号为空"),
    FILED_DEFINE(2007007002, "字段定义异常"),
    TABLE_DEFINE(2007007003, "表定义异常"),
    TABLE_DELETE(2007007004, "表删除异常"),
    TABLE_GET(2007007005, "表获取异常"),
    COLUMN_ADD(2007007006, "添加字段异常"),
    COLUMN_UPDATE(2007007007, "字段修改异常"),
    COLUMN_DEL(2007007008, "字段删除异常"),
    INIT_PRODUCER_ERROR(2007001000,"初始化MQ生产者失败"),
    SEND_MSG_ERROR(2007001001,"发送消息失败");

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

    @Override
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}