package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.api.Query;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.mp.support.Condition;
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

	@GetMapping("/list")
	@Operation(summary = "证书列表")
	public R<IPage<CertificateVO>> page(Map<String, Object> certificate, Query query) {
		QueryWrapper<Certificate> queryWrapper = Condition.getQueryWrapper(certificate, Certificate.class);
		IPage<Certificate> pages = certificateService.page(Condition.getPage(query), queryWrapper);
		return R.data(CertificateWrapper.build().pageVO(pages));
	}

	@GetMapping("/detail")
	@Operation(summary = "证书详情")
	public R<CertificateVO> detail(@Parameter(name = "id") @RequestParam Long id) {
		return R.data(CertificateWrapper.build().entityVO(certificateService.getById(id)));
	}

	@PostMapping("/save")
	@Operation(summary = "新增或修改证书")
	public R<Boolean> save(@RequestBody Certificate entity) {
		return R.data(certificateService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@Operation(summary = "删除证书")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(certificateService.removeByIds(ids));
	}
}
