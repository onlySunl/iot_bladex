package org.springblade.modules.iot.dto.linkage.execution;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * -----------------------------------------------------------------------------
 * File Name: PolicyPair.java
 * -----------------------------------------------------------------------------
 * Description:
 * PolicyPair
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-26 17:45
 */
@Data
public class PolicyPair<T1, T2> {
    private final T1 first;
    private final T2 second;
    private final Map<String, Object> additionalAttributes = new HashMap<>();

    public PolicyPair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public void addAttribute(String key, Object value) {
        additionalAttributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return additionalAttributes.get(key);
    }
}
