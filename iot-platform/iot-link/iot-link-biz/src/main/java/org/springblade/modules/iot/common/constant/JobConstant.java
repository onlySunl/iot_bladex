package org.springblade.modules.iot.common.constant;

/**
 * 定时任务 常量
 *
 * @author mqttsnet
 * @date 2021/1/8 10:16 上午
 */
public interface JobConstant {

    /**
     * 默认基础库的定时任务组
     */
    String DEF_BASE_JOB_GROUP_NAME = "thinglinks-base-executor";

    String DEF_EXTEND_JOB_GROUP_NAME = "thinglinks-extend-executor";

    /**
     * 物联网业务系统的定时任务组
     */
    String DEF_IOT_JOB_GROUP_NAME = "thinglinks-iot-executor";

    /**
     * 短信发送处理器
     */
    String SMS_SEND_JOB_HANDLER = "smsSendJobHandler";

    /**
     * 推送消息处理器
     */
    String PUBLISH_MSG_JOB_HANDLER = "publishMsgJobHandler";

    /**
     * 场景联动规则处理器
     */
    String SCENE_LINKAGE_RULE_JOB_HANDLER = "sceneLinkageRuleJobHandler";
}
