package org.springblade.modules.iot.protocol.vo.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: OpenAiChatRequestParam
 * -----------------------------------------------------------------------------
 * Description:
 * OpenAiChatRequest
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
 * @date 2023/12/12 23:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "OpenAiChatResponseResultVO", description = "Response data from OpenAI Chat API")
public class OpenAiChatResponseResultVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier for the response")
    private String id;

    @Schema(description = "Type of the object returned")
    private String object;

    @Schema(description = "Timestamp of creation")
    private long created;

    @Schema(description = "Model used for generating the response")
    private String model;

    @Schema(description = "List of choices provided by OpenAI")
    private List<ChatChoice> choices;

    @Schema(description = "Usage information of tokens")
    private Usage usage;

    @Schema(description = "Any warning returned by the API")
    private String warning;

    @Schema(description = "System fingerprint")
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Usage implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "Number of tokens used in the prompt")
        @JsonProperty("prompt_tokens")
        private long promptTokens;

        @Schema(description = "Number of tokens used in the completion")
        @JsonProperty("completion_tokens")
        private long completionTokens;

        @Schema(description = "Total number of tokens used")
        @JsonProperty("total_tokens")
        private long totalTokens;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class ChatChoice implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "Index of the choice")
        private long index;

        @Schema(description = "Message content in delta format (when stream is true)")
        @JsonProperty("delta")
        private Message delta;

        @Schema(description = "Message content (when stream is false)")
        @JsonProperty("message")
        private Message message;

        @Schema(description = "Reason for completion")
        @JsonProperty("finish_reason")
        private String finishReason;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @EqualsAndHashCode
        public static class Message implements Serializable {
            private static final long serialVersionUID = 1L;

            @Schema(description = "Content of the message")
            private String content;

            @Schema(description = "Role of the message sender (e.g., 'user', 'system')")
            private String role;
        }
    }

}
