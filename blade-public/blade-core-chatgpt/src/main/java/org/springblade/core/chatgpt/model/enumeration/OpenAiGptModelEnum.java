package org.springblade.core.chatgpt.model.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: OpenAiGptModelEnum
 * -----------------------------------------------------------------------------
 * Description:
 * OpenAiGptModel
 * 最新模型参考官方文档：
 * <a href="https://platform.openai.com/docs/models/model-endpoint-compatibility">官方稳定模型列表</a>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2023/12/12       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023/12/12 23:43
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "OpenAiGptModelEnum", description = "Enumerates the various GPT model versions available from OpenAI")
public enum OpenAiGptModelEnum {
    GPT_3_5_TURBO("gpt-3.5-turbo", "Standard model"),
    GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613", "Supports functions"),
    GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k", "Extended context"),
    GPT_3_5_TURBO_16K_0613("gpt-3.5-turbo-16k-0613", "Extended context with functions support"),
    GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106", "Improved instruction following, JSON parsing, reproducible outputs, and parallel function calls"),
    GPT_4("gpt-4", "Fourth-generation model"),
    GPT_4_32K("gpt-4-32k", "Fourth-generation model with extended context"),
    GPT_4_0613("gpt-4-0613", "Supports functions"),
    GPT_4_32K_0613("gpt-4-32k-0613", "Extended context with functions support"),
    GPT_4_1106_PREVIEW("gpt-4-1106-preview", "Supports array mode, function calls, and reproducible outputs"),
    GPT_4_VISION_PREVIEW("gpt-4-vision-preview", "Supports images");

    private String modelName;
    private String description;

    public static Optional<OpenAiGptModelEnum> fromValue(String modelName) {
        return Stream.of(OpenAiGptModelEnum.values())
                .filter(e -> e.getModelName().equals(modelName))
                .findFirst();
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
