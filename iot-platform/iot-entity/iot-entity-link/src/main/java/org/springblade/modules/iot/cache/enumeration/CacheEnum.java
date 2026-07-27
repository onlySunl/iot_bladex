package org.springblade.modules.iot.cache.enumeration;


import org.springblade.core.log.exception.ServiceException;

/**
 * 缓存业务类型
 */
public enum CacheEnum {
    DEVICE("device"),
    PRODUCT("product"),
    PRODUCT_MODEL("productModel"),
    ;
    private String code;

    CacheEnum(String code) {
        this.code = code;
    }

    public static CacheEnum fromCode(String code) {
        for (CacheEnum cacheEnum : CacheEnum.values()) {
            if (cacheEnum.getCode().equals(code)) {
                return cacheEnum;
            }
        }
        throw new ServiceException("Invalid code: " + code);
    }

    public String getCode() {
        return code;
    }
}
