package org.springblade.modules.iot.service.execution.service;

import org.springblade.common.utils.DateUtil;
import org.springblade.modules.iot.dto.linkage.AntiShakeSchemePolicyDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * -----------------------------------------------------------------------------
 * File Name: AntiShakeSchemeEvaluator
 * -----------------------------------------------------------------------------
 * Description:
 * 防抖策略评估
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/1/1       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/1/1 21:53
 */
@Slf4j
public class AntiShakeSchemeEvaluatorService<T> {
    /**
     * Applies the anti-shake policy to the provided data collection if the policy is provided,
     * otherwise returns the latest data based on the max timestamp key.
     *
     * @param dataCollection           The data collection to evaluate.
     * @param antiShakeSchemePolicyDTO The optional anti-shake policy to apply.
     * @return The data that satisfies the anti-shake policy, or the latest data if no policy is provided.
     */
    public T applyAntiShakePolicy(Map<Long, T> dataCollection, Optional<AntiShakeSchemePolicyDTO> antiShakeSchemePolicyDTO) {
        if (antiShakeSchemePolicyDTO.isPresent()) {
            log.info("Anti-shake policy is provided, applying policy...");
            return applyPolicy(dataCollection, antiShakeSchemePolicyDTO.get());
        } else {
            log.info("No anti-shake policy provided, returning the latest data entry...");
            return dataCollection.entrySet().stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElse(null);
        }
    }

    /**
     * Applies the provided anti-shake policy to the data collection.
     *
     * @param dataCollection           The data collection to evaluate.
     * @param antiShakeSchemePolicyDTO The anti-shake policy to apply.
     * @return The data that satisfies the anti-shake policy, or null if the policy conditions are not met.
     */
    private T applyPolicy(Map<Long, T> dataCollection, AntiShakeSchemePolicyDTO antiShakeSchemePolicyDTO) {
        final Long currentMicro = DateUtil.microsecondStampL();

        // 统一时间单位到微秒（policyDTO frequency 的时间单位为秒）
        long frameMicro = TimeUnit.SECONDS.toMicros(antiShakeSchemePolicyDTO.getFrequency().getTimeValue());

        // Filter entries based on the time frame and frequency count
        List<T> resultsWithinTimeFrame = dataCollection.entrySet().stream()
                .filter(entry -> (currentMicro - entry.getKey()) <= frameMicro)
                .map(Map.Entry::getValue)
                .toList();

        if (resultsWithinTimeFrame.size() >= antiShakeSchemePolicyDTO.getFrequency().getCount()) {
            log.info("Frequency count condition is met, checking occurrences...");
            if (antiShakeSchemePolicyDTO.getOccurrence().getFirst()) {
                return resultsWithinTimeFrame.get(0);
            } else if (antiShakeSchemePolicyDTO.getOccurrence().getLast()) {
                return resultsWithinTimeFrame.get(resultsWithinTimeFrame.size() - 1);
            }
        }

        log.warn("The anti-shake policy conditions are not met, returning null.");
        return null;
    }
}
