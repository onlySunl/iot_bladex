package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.TenantCache;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.Certificate;
import org.springblade.modules.iot.pojo.vo.CertificateVO;
import org.springblade.modules.iot.service.ICertificateService;
import org.springblade.modules.iot.wrapper.CertificateWrapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/certificate")
@Tag(name = "设备证书", description = "设备证书管理接口")
public class CertificateController extends BladeController {

	private final ICertificateService certificateService;
	private final TenantCache tenantCache;

	@GetMapping("/list")
	@Operation(summary = "证书列表")
	public R<IPage<CertificateVO>> list(Certificate certificate, com.baomidou.mybatisplus.extension.plugins.pagination.Page page) {
		com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Certificate> qw = new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();
		qw.eq(Certificate::getIsDeleted, 0);
		qw.eq(Func.isNotEmpty(certificate.getTenantId()), Certificate::getTenantId, certificate.getTenantId());
		IPage<Certificate> pages = certificateService.page(page, qw);
		return R.data(CertificateWrapper.build().pageVO(pages));
	}

	@GetMapping("/detail")
	@Operation(summary = "证书详情")
	public R<CertificateVO> detail(@Parameter(name = "id") @RequestParam Long id) {
		return R.data(CertificateWrapper.build().getVO(certificateService.getById(id)));
	}

	@PostMapping("/save")
	@Operation(summary = "新增或修改证书")
	public R<Boolean> save(@RequestBody Certificate entity) {
		if (Func.isEmpty(entity.getId())) {
			entity.setTenantId(tenantCache.getTenantId());
		}
		return R.data(certificateService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@Operation(summary = "删除证书")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(certificateService.removeByIds(ids));
	}
}
