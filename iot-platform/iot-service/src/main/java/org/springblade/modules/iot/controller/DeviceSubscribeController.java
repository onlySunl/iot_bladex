package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.api.Query;
import org.springblade.core.mp.support.Condition;
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
	public R<IPage<DeviceSubscribeVO>> page(Map<String, Object> deviceSubscribe, Query query) {
		QueryWrapper<DeviceSubscribe> queryWrapper = Condition.getQueryWrapper(deviceSubscribe, DeviceSubscribe.class);
		IPage<DeviceSubscribe> pages = deviceSubscribeService.page(Condition.getPage(query), queryWrapper);
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
