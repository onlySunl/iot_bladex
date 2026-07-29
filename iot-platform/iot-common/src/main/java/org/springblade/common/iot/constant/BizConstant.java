package org.springblade.common.iot.constant;

/**
 * 业务常量
 *
 * @author mqttsnet
 * @date 2019/08/06
 */
public interface BizConstant {
    /**
     * 工具类 需要扫描的包
     */
    String UTIL_PACKAGE = "org.springblade.basic";
    /**
     * 业务项目 需要扫描的包
     */
    String BUSINESS_PACKAGE = "org.springblade.modules.iot";
    /**
     * 被T
     */
    String LOGIN_STATUS = "T";

    String BASE = "iot-base-server";
    String FILE = "iot-file-server";
    String MSG = "iot-msg-server";
    String OAUTH = "iot-oauth-server";
    String GATE = "iot-gateway-server";
    String TENANT = "iot-system-server";
    String BASE_EXECUTOR = "iot-base-executor";
    String EXTEND_EXECUTOR = "iot-extend-executor";
    String ORDER = "iot-example-server";
    String DEMO = "iot-demo-server";

    /**
     * 初始化数据源时json的参数，
     * method 的可选值为 {INIT_DS_PARAM_METHOD_INIT} 和 {INIT_DS_PARAM_METHOD_REMOVE}
     */
    String INIT_DS_PARAM_METHOD = "method";
    /**
     * 初始化数据源时json的参数，
     * tenant 的值为 需要初始化的租户编码
     */
    String INIT_DS_PARAM_TENANT = "tenant";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_INIT = "init";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_REMOVE = "remove";
    /**
     * 框架布局
     */
    String IFRAME = "IFRAME";
    /**
     * 页面布局
     */
    String LAYOUT = "LAYOUT";
    /**
     * 绑定范围类型 机构
     */
    String SCOPE_TYPE_ORG = "2";
    /**
     * 绑定范围类型 员工
     */
    String SCOPE_TYPE_EMPLOYEE = "1";
    /**
     * 绑定范围 已绑定
     */
    String SCOPE_BIND = "1";
    /**
     * 绑定范围 未绑定
     */
    String SCOPE_UN_BIND = "2";


    /**
     * ALL (所有)
     */
    String ALL = "all";
}
