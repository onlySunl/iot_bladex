package org.springblade.modules.iot.wrapper;

import org.springblade.modules.iot.pojo.entity.Certificate;
import org.springblade.modules.iot.pojo.vo.CertificateVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.wrapper.BaseEntityWrapper;

public class CertificateWrapper extends BaseEntityWrapper<Certificate, CertificateVO> {

	public static CertificateWrapper build() {
		return new CertificateWrapper();
	}

	@Override
	public CertificateVO entityVO(Certificate entity) {
		CertificateVO vo = new CertificateVO();
		Func.copyProperties(entity, vo);
		return vo;
	}
}
