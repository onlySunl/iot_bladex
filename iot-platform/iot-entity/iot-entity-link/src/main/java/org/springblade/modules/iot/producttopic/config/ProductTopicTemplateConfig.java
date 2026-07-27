package org.springblade.modules.iot.producttopic.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Description:
 * 产品Topic模板配置类
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = ProductTopicTemplateConfig.PREFIX)
public class ProductTopicTemplateConfig {
    public static final String PREFIX = "thinglinks" + ".topic-templates";
    private Map<String, List<ProductTopicTemplate>> productTopicTemplates = new HashMap<>();

    public Map<String, List<ProductTopicTemplate>> getProductTopicTemplates() {
        return productTopicTemplates;
    }

    public void setProductTopicTemplates(Map<String, List<ProductTopicTemplate>> productTopicTemplates) {
        this.productTopicTemplates = productTopicTemplates;
    }
}

