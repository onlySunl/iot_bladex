package org.springblade.modules.iot.cacert.manager.license;

import org.springblade.core.mvc.manager.SuperManager;
import org.springblade.modules.iot.cacert.entity.license.CaCertLicense;

/**
 * <p>
 * 通用业务接口
 * CA许可证证书表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-27 15:48:10
 */
public interface CaCertLicenseManager extends SuperManager<CaCertLicense> {

    /**
     * 根据证书序列号查询证书
     * @param certSerialNumber 证书序列号
     * @return {@link CaCertLicense} 证书信息
     */
    CaCertLicense getByCertSerialNumber(String certSerialNumber);
}


