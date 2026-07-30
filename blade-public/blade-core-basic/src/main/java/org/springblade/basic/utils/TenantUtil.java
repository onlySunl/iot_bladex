package org.springblade.basic.utils;

import cn.hutool.core.util.StrUtil;
import org.springblade.basic.context.ContextConstants;
import org.springblade.basic.context.ContextUtil;


import java.util.Optional;

/**
 * @program: blade-core-basic
 * @description: 租户操作工具类
 * @packagename: org.springblade.basic.utils
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-20 11:14
 **/
public class TenantUtil {
    private static final String SEPARATOR = ContextConstants.SPECIAL_CHARACTER.toString();

    /**
     * 提取给定 自定义元素 中的租户ID，默认为默认租户 ID
     *
     * @param optionalItem 自定义元素
     * @return 租户 Id
     */
    public static String extractTenantIdWithDefault(String optionalItem) {
        return extractTenantIdSafely(optionalItem).orElse(ContextConstants.BUILT_IN_TENANT_ID_STR);
    }

    /**
     * 提取给定 自定义元素 中的租户ID
     *
     * @param optionalItem 自定义元素
     * @return 租户ID
     */
    public static String extractTenantId(String optionalItem) {
        return extractTenantIdSafely(optionalItem).orElse("");
    }

    /**
     * 安全提取租户ID
     *
     * @param compositeId 组合ID(格式：uniqueId@tenantId)
     * @return Optional包装的租户ID(自动trim)
     */
    public static Optional<String> extractTenantIdSafely(String compositeId) {
        return Optional.ofNullable(compositeId)
                .filter(StrUtil::isNotBlank)
                .filter(id -> id.contains(SEPARATOR))
                .map(id -> {
                    int separatorIndex = id.lastIndexOf(SEPARATOR);
                    return separatorIndex < id.length() - 1 ? id.substring(separatorIndex + 1).trim() : "";
                })
                .filter(tenantId -> !StrUtil.isBlank(tenantId));
    }


    /**
     * 构建带租户ID的自定义元素
     *
     * @param uniqueIdentifier 唯一标识（非空）
     * @param tenantId         租户ID（非空）
     * @return 格式为 "uniqueId@tenantId"
     * @throws IllegalArgumentException 参数无效时抛出
     */
    public static String buildOptionalItem(String uniqueIdentifier, String tenantId) {
        if (StrUtil.isBlank(uniqueIdentifier)) {
            throw new IllegalArgumentException("Unique identifier cannot be blank");
        }
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalArgumentException("Tenant ID cannot be blank");
        }

        return StrUtil.join(SEPARATOR, uniqueIdentifier, tenantId);
    }


    /**
     * 验证租户ID是否匹配当前上下文租户ID
     *
     * @param optionalItem 待验证的自定义元素
     * @return true=匹配，false=不匹配或格式错误
     */
    public static Boolean validateTenantConsistency(String optionalItem) {
        String extractedTenantId = extractTenantId(optionalItem);
        if (StrUtil.isBlank(extractedTenantId)) {
            return false;
        }
        return StrUtil.equals(extractedTenantId, ContextUtil.getTenantIdStr());
    }


}
