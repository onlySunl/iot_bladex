package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.DeviceSubscribe;
import org.springblade.modules.iot.pojo.vo.DeviceSubscribeVO;
import org.springblade.modules.iot.service.IDeviceSubscribeService;
import org.springblade.modules.iot.wrapper.DeviceSubscribeWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device-subscribe")
@Tag(name = "设备订阅", description = "设备订阅管理接口")
public class DeviceSubscribeController extends BladeController {

	private final IDeviceSubscribeService deviceSubscribeService;

	@GetMapping("/list")
	@Operation(summary = "设备订阅列表")
	public R<IPage<DeviceSubscribeVO>> list(DeviceSubscribe subscribe, com.baomidou.mybatisplus.extension.plugins.pagination.Page page) {
		com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<DeviceSubscribe> qw = new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();
		qw.eq(DeviceSubscribe::getIsDeleted, 0);
		qw.eq(Func.isNotEmpty(subscribe.getDeviceId()), DeviceSubscribe::getDeviceId, subscribe.getDeviceId());
		IPage<DeviceSubscribe> pages = deviceSubscribeService.page(page, qw);
		return R.data(DeviceSubscribeWrapper.build().pageVO(pages));
	}

	@PostMapping("/save")
	@Operation(summary = "新增或修改设备订阅")
	public R<Boolean> save(@RequestBody DeviceSubscribe entity) {
		return R.data(deviceSubscribeService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@Operation(summary = "删除设备订阅")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(deviceSubscribeService.removeByIds(ids));
	}
}
