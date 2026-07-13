package org.springblade.modules.iot.service.impl;

import org.springblade.core.mp.service.impl.BladeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.mapper.CertificateMapper;
import org.springblade.modules.iot.pojo.entity.Certificate;
import org.springblade.modules.iot.service.ICertificateService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CertificateServiceImpl extends BladeServiceImpl<CertificateMapper, Certificate> implements ICertificateService {
}
