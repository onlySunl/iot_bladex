package org.springblade.modules.iot.cacert.manager.license.impl;

import cn.hutool.core.text.CharSequenceUtil;
import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.modules.iot.cacert.entity.license.CaCertLicense;
import org.springblade.modules.iot.cacert.manager.license.CaCertLicenseManager;
import org.springblade.modules.iot.cacert.mapper.license.CaCertLicenseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * CA许可证证书表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-27 15:48:10
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CaCertLicenseManagerImpl extends SuperManagerImpl<CaCertLicenseMapper, CaCertLicense> implements CaCertLicenseManager {

    private final CaCertLicenseMapper caCertLicenseMapper;

    @Override
    public CaCertLicense getByCertSerialNumber(String certSerialNumber) {
        QueryWrap<CaCertLicense> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(CharSequenceUtil.isNotBlank(certSerialNumber), CaCertLicense::getSerialNumber, certSerialNumber);
        return caCertLicenseMapper.selectOne(queryWrap);
    }
}


