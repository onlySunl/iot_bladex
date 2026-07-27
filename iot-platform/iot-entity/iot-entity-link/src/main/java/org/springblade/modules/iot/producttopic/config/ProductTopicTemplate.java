package org.springblade.modules.iot.producttopic.config;

import lombok.Data;

/**
 * Description:
 * 产品Topic模板配置信息
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Data
public class ProductTopicTemplate {

    /**
     * 产品Topic
     */
    private String topic;
    /**
     * 发布者
     */
    private Integer publisher;
    /**
     * 订阅者
     */
    private Integer subscriber;
    /**
     * 备注
     */
    private String remark;
    /**
     * 主题类型
     */
    private Integer topicType;

     /**
      * 主题功能类型
      */
    private Integer functionType;
}
