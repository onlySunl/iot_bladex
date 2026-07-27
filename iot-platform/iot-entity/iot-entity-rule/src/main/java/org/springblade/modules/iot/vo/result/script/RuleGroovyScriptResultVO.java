package org.springblade.modules.iot.vo.result.script;

import com.google.common.base.Joiner;

import com.mqttsnet.basic.model.cache.CacheKey;
import com.mqttsnet.basic.utils.StrPool;
import org.springblade.common.cache.rule.groovy.GroovyScriptCacheKeyBuilder;
import org.springblade.model.vo.AuditableResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * <p>
 * RuleGroovyScript Result VO
 * </p>
 *
 * @author mqttsnet
 * @date 2025-03-24 09:54:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "RuleGroovyScriptResultVO")
public class RuleGroovyScriptResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Script Name")
    private String name;

    @Schema(description = "App ID")
    private String appId;

    @Schema(description = "Script Type")
    private String scriptType;

    @Schema(description = "Channel Code")
    private String channelCode;

    @Schema(description = "Product Identification")
    private String productIdentification;

    @Schema(description = "Topic Pattern")
    private String topicPattern;

    @Schema(description = "Enable [0-disable 1-enable]")
    private Boolean enable;

    @Schema(description = "Script Content")
    private String scriptContent;

    @Schema(description = "Extend Params")
    private String extendParams;

    @Schema(description = "Object Version")
    private String objectVersion;

    @Schema(description = "Remark")
    private String remark;

    /**
     * Get CacheHashKey
     *
     * @return {@link CacheKey} cache key
     */
    public CacheKey getCacheKey() {
        return GroovyScriptCacheKeyBuilder.builder(buildOnlyKey());
    }

    /**
     * Build unique key from fields
     *
     * @return unique key
     */
    public String buildOnlyKey() {
        return Joiner.on(StrPool.COLON).join(scriptType, channelCode, productIdentification, topicPattern);
    }

}
