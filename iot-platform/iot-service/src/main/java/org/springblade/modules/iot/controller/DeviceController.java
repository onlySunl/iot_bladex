package org.springblade.modules.iot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.core.annotation.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.pojo.entity.Device;
import org.springblade.modules.iot.pojo.vo.DeviceVO;
import org.springblade.modules.iot.service.IDeviceService;
import org.springblade.modules.iot.wrapper.DeviceWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * IoT设备管理 控制器
 * 迁移自 NexIoT - IoTDeviceController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/device")
@Tag(name = "IoT设备管理", description = "IoT设备管理接口")
public class DeviceController extends BladeController {

	private final IDeviceService deviceServiceImpl;

	/**
	 * 分页查询设备列表
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询设备", description = "分页查询设备列表")
	@ApiOperationSupport(order = 1)
	public R<IPage<DeviceVO>> page(Device device, QueryWrapper<Device> queryWrapper) {
		QueryWrapper<Device> qw = Func.toQueryWrapper(queryWrapper);
		if (StrUtil.isNotBlank(device.getDeviceName())) {
			qw.lambda().like(Device::getDeviceName, device.getDeviceName());
		}
		if (StrUtil.isNotBlank(device.getProductKey())) {
			qw.lambda().eq(Device::getProductKey, device.getProductKey());
		}
		if (StrUtil.isNotBlank(device.getIotId())) {
			qw.lambda().eq(Device::getIotId, device.getIotId());
		}
		if (device.getState() != null) {
			qw.lambda().eq(Device::getState, device.getState());
		}
		qw.lambda().orderByDesc(TenantEntity::getCreateTime);
		IPage<Device> pages = deviceServiceImpl.page(Condition.getPage(queryWrapper), qw);
		return R.data(DeviceWrapper.build().pageVO(pages));
	}

	/**
	 * 查询设备详情
	 */
	@GetMapping("/detail")
	@Operation(summary = "查询设备详情", description = "根据ID查询设备详情")
	@ApiOperationSupport(order = 2)
	public R<DeviceVO> detail(@Parameter(description = "设备ID", required = true) @RequestParam Long id) {
		Device device = deviceServiceImpl.getById(id);
		return R.data(DeviceWrapper.build().toVO(device));
	}

	/**
	 * 根据IoTId查询设备
	 */
	@GetMapping("/get-by-iot-id")
	@Operation(summary = "根据IoTId查询", description = "根据IoTId查询设备详情")
	@ApiOperationSupport(order = 3)
	public R<DeviceVO> getByIotId(@Parameter(description = "IoTId", required = true) @RequestParam String iotId) {
		QueryWrapper<Device> qw = new QueryWrapper<>();
		qw.lambda().eq(Device::getIotId, iotId);
		Device device = deviceServiceImpl.getOne(qw);
		return R.data(DeviceWrapper.build().toVO(device));
	}

	/**
	 * 新增设备
	 */
	@PostMapping("/save")
	@Operation(summary = "新增设备", description = "新增设备")
	@ApiOperationSupport(order = 4)
	public R<Boolean> save(@RequestBody Device device) {
		device.setCreateTime(new Date());
		device.setState(0);
		return R.data(deviceServiceImpl.save(device));
	}

	/**
	 * 修改设备
	 */
	@PostMapping("/update")
	@Operation(summary = "修改设备", description = "修改设备")
	@ApiOperationSupport(order = 5)
	public R<Boolean> update(@RequestBody Device device) {
		device.setUpdateTime(new Date());
		return R.data(deviceServiceImpl.updateById(device));
	}

	/**
	 * 删除设备
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除设备", description = "根据ID删除设备")
	@ApiOperationSupport(order = 6)
	public R<Boolean> remove(@Parameter(description = "设备ID", required = true) @RequestParam Long id) {
		return R.data(deviceServiceImpl.removeById(id));
	}

	/**
	 * 更新设备在线状态
	 */
	@PostMapping("/update-state")
	@Operation(summary = "更新设备在线状态", description = "更新设备在线状态")
	@ApiOperationSupport(order = 7)
	public R<Boolean> updateState(@RequestBody Device device) {
		Device update = new Device();
		update.setId(device.getId());
		update.setState(device.getState());
		if (device.getState() != null && device.getState() == 1) {
			update.setOnlineTime(System.currentTimeMillis());
		}
		update.setUpdateTime(new Date());
		return R.data(deviceServiceImpl.updateById(update));
	}
}
