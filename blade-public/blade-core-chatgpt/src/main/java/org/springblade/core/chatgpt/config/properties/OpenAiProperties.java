package org.springblade.core.chatgpt.config.properties;

import lombok.Data;
import org.springblade.basic.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
 * -----------------------------------------------------------------------------
 * File Name: OpenAiProperties
 * -----------------------------------------------------------------------------
 * Description:
 * ChatGpt config
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2023/12/9       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023/12/9 17:24
 */

@Data
@ConfigurationProperties(OpenAiProperties.PREFIX)
public class OpenAiProperties {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".openai";

    // List of API keys for OpenAI service
    private List<String> apiKey = new ArrayList<>();

    // API host for the OpenAI service
    private String apiHost = "https://api.openai.com/";

}
