package org.springblade.modules.iot.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.Certificate;
import org.springblade.modules.iot.pojo.vo.CertificateVO;

public class CertificateWrapper extends BaseEntityWrapper<Certificate, CertificateVO> {

	public static CertificateWrapper build() {
		return new CertificateWrapper();
	}

	@Override
	public CertificateVO entityVO(Certificate entity) {
		return Func.copyProperties(entity, CertificateVO.class);
	}
}
