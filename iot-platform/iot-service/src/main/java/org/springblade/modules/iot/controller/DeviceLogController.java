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
import org.springblade.core.mp.support.Condition;
import org.springblade.modules.iot.pojo.entity.DeviceLog;
import org.springblade.modules.iot.pojo.vo.DeviceLogVO;
import org.springblade.modules.iot.service.IDeviceLogService;
import org.springblade.modules.iot.wrapper.DeviceLogWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/device-log")
@Tag(name = "设备日志", description = "设备日志查询接口")
public class DeviceLogController extends BladeController {

	private final IDeviceLogService deviceLogService;

	@GetMapping("/list")
	@Operation(summary = "设备日志列表")
	public R<IPage<DeviceLogVO>> page(Map<String, Object> deviceLog, Query query) {
		QueryWrapper<DeviceLog> queryWrapper = Condition.getQueryWrapper(deviceLog, DeviceLog.class);
		queryWrapper.orderByDesc(DeviceLog::getCreateTime);
		IPage<DeviceLog> pages = deviceLogService.page(Condition.getPage(query), queryWrapper);
		return R.data(DeviceLogWrapper.build().pageVO(pages));
	}

	@GetMapping("/detail")
	@Operation(summary = "设备日志详情")
	public R<DeviceLogVO> detail(@Parameter(name = "id") @RequestParam Long id) {
		return R.data(DeviceLogWrapper.build().entityVO(deviceLogService.getById(id)));
	}
}
