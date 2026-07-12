package org.springblade.modules.iot.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.TenantCache;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.DeviceTags;
import org.springblade.modules.iot.pojo.vo.DeviceTagsVO;
import org.springblade.modules.iot.service.IDeviceTagsService;
import org.springblade.modules.iot.wrapper.DeviceTagsWrapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device-tags")
@Tag(name = "设备标签", description = "设备标签管理接口")
public class DeviceTagsController extends BladeController {

	private final IDeviceTagsService deviceTagsService;
	private final TenantCache tenantCache;

	@GetMapping("/list")
	@Operation(summary = "设备标签列表")
	public R<List<DeviceTagsVO>> list(@Parameter(name = "deviceId") @RequestParam Long deviceId) {
		List<DeviceTags> list = deviceTagsService.getByDeviceId(deviceId);
		return R.data(DeviceTagsWrapper.build().listVO(list));
	}

	@PostMapping("/save")
	@Operation(summary = "新增或修改设备标签")
	public R<Boolean> save(@RequestBody DeviceTags entity) {
		if (Func.isEmpty(entity.getId())) {
			entity.setTenantId(tenantCache.getTenantId());
		}
		return R.data(deviceTagsService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@Operation(summary = "删除设备标签")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(deviceTagsService.removeByIds(ids));
	}
}
