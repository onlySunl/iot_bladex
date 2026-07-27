package org.springblade.modules.iot.vo.query.script;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.mqttsnet.basic.utils.StrPool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * GroovyScript Query Object
 *
 * @author mqttsnet 2025/03/18 12:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "GroovyScriptQuery")
public class RuleGroovyScriptQuery {
    private static final Splitter KEY_SPLITTER = Splitter.on(StrPool.COLON).trimResults();

    /**
     * Required parts: scriptType + channelCode + productIdentification + topicPattern
     */
    private static final int REQUIRED_PARTS = 4;

    /**
     * Unique key composed of scriptType:channelCode:productIdentification:topicPattern
     * separated by StrPool.COLON
     */
    @Schema(description = "Unique Key")
    private String uniqueKey;

    @Schema(description = "Script Type")
    private String scriptType;

    @Schema(description = "Channel Code")
    private String channelCode;

    @Schema(description = "Product Identification")
    private String productIdentification;

    @Schema(description = "Topic Pattern")
    private String topicPattern;

    public RuleGroovyScriptQuery(String uniqueKey) {
        validateUniqueKey(uniqueKey);
        List<String> parts = KEY_SPLITTER.splitToList(uniqueKey);
        this.scriptType = parts.get(0);
        this.channelCode = parts.get(1);
        this.productIdentification = parts.get(2);
        this.topicPattern = parts.get(3);
        this.uniqueKey = uniqueKey;
    }

    private void validateUniqueKey(String uniqueKey) {
        Preconditions.checkArgument(StringUtils.isNotBlank(uniqueKey), "uniqueKey must not be blank");

        List<String> parts = KEY_SPLITTER.splitToList(uniqueKey);

        Preconditions.checkArgument(parts.size() == REQUIRED_PARTS,
                "uniqueKey must contain exactly %d parts separated by colon, but got %d parts: %s",
                REQUIRED_PARTS, parts.size(), uniqueKey);

        IntStream.range(0, REQUIRED_PARTS).forEach(i -> {
            String part = parts.get(i);
            Preconditions.checkArgument(StringUtils.isNotBlank(part),
                    "Part %d of uniqueKey must not be blank (0=scriptType, 1=channelCode, 2=productIdentification, 3=topicPattern): %s",
                    i + 1, uniqueKey);
        });
    }

}
