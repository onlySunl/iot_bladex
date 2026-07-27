package org.springblade.modules.iot.record.script;

import com.google.common.base.Joiner;
import org.springblade.common.cache.CacheKey;
import org.springblade.common.utils.ArgumentAssert;
import org.springblade.common.utils.StrPool;
import org.springblade.common.cache.rule.groovy.GroovyScriptCacheKeyBuilder;
import org.springblade.modules.iot.vo.query.script.RuleGroovyScriptQuery;

/**
 * ScriptIdentifier Record
 * <p>
 * Identifies a groovy script by its unique composite key.
 *
 * @param scriptType             Script type
 * @param channelCode            Channel code
 * @param productIdentification  Product identification
 * @param topicPattern           Topic pattern
 * @author Sun Shihuan
 * @version 1.0.0
 * @date 2025/4/15 14:51
 */
public record ScriptIdentifier(
        String scriptType,
        String channelCode,
        String productIdentification,
        String topicPattern
) {

    /**
     * Compact constructor with validation
     */
    public ScriptIdentifier {
        ArgumentAssert.notBlank(scriptType, "scriptType must not be blank");
        ArgumentAssert.notBlank(channelCode, "channelCode must not be blank");
        ArgumentAssert.notBlank(productIdentification, "productIdentification must not be blank");
        ArgumentAssert.notBlank(topicPattern, "topicPattern must not be blank");
    }

    // Cache methods

    /**
     * Build CacheKey from query
     *
     * @param query query object
     * @return {@link CacheKey}
     */
    public static CacheKey buildCacheKey(RuleGroovyScriptQuery query) {
        String keyPart = Joiner.on(StrPool.COLON)
                .join(query.getScriptType(), query.getChannelCode(),
                        query.getProductIdentification(), query.getTopicPattern());
        return GroovyScriptCacheKeyBuilder.builder(keyPart);
    }

    // Factory methods

    /**
     * Create ScriptIdentifier from query
     */
    public static ScriptIdentifier fromQuery(RuleGroovyScriptQuery query) {
        ArgumentAssert.notNull(query, "query must not be null");
        return new ScriptIdentifier(
                query.getScriptType(),
                query.getChannelCode(),
                query.getProductIdentification(),
                query.getTopicPattern()
        );
    }
}
