package org.springblade.modules.iot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.pojo.entity.DeviceShadow;
import org.springblade.modules.iot.pojo.vo.DeviceShadowVO;
import org.springblade.modules.iot.service.IDeviceShadowService;
import org.springblade.modules.iot.wrapper.DeviceShadowWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/device-shadow")
@Tag(name = "设备影子", description = "设备影子管理接口")
public class DeviceShadowController extends BladeController {

	private final IDeviceShadowService deviceShadowService;
	@GetMapping("/detail")
	@Operation(summary = "获取设备影子")
	public R<DeviceShadowVO> detail(@Parameter(name = "deviceId") @RequestParam Long deviceId) {
		DeviceShadow shadow = deviceShadowService.getByDeviceId(deviceId);
		return R.data(DeviceShadowWrapper.build().entityVO(shadow));
	}

	@PostMapping("/save")
	@Operation(summary = "更新设备影子")
	public R<Boolean> save(@RequestBody DeviceShadow entity) {
		return R.data(deviceShadowService.saveOrUpdate(entity));
	}
}
