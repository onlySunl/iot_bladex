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
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.pojo.vo.RuleModelVO;
import org.springblade.modules.iot.service.IRuleModelService;
import org.springblade.modules.iot.wrapper.RuleModelWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * IoT规则引擎 控制器
 * 迁移自 NexIoT - RuleController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/rule")
@Tag(name = "IoT规则引擎", description = "IoT规则引擎管理接口")
public class RuleController extends BladeController {

	private final IRuleModelService ruleModelServiceImpl;

	/**
	 * 分页查询规则列表
	 */
	@GetMapping("/page")
	@Operation(summary = "分页查询规则", description = "分页查询规则列表")
	@ApiOperationSupport(order = 1)
	public R<IPage<RuleModelVO>> page(RuleModel ruleModel, QueryWrapper<RuleModel> queryWrapper) {
		QueryWrapper<RuleModel> qw = Func.toQueryWrapper(queryWrapper);
		if (StrUtil.isNotBlank(ruleModel.getRuleName())) {
			qw.lambda().like(RuleModel::getRuleName, ruleModel.getRuleName());
		}
		if (StrUtil.isNotBlank(ruleModel.getProductKey())) {
			qw.lambda().eq(RuleModel::getProductKey, ruleModel.getProductKey());
		}
		if (StrUtil.isNotBlank(ruleModel.getStatus())) {
			qw.lambda().eq(RuleModel::getStatus, ruleModel.getStatus());
		}
		if (StrUtil.isNotBlank(ruleModel.getDataLevel())) {
			qw.lambda().eq(RuleModel::getDataLevel, ruleModel.getDataLevel());
		}
		qw.lambda().orderByDesc(TenantEntity::getCreateTime);
		IPage<RuleModel> pages = ruleModelServiceImpl.page(Condition.getPage(queryWrapper), qw);
		return R.data(RuleModelWrapper.build().pageVO(pages));
	}

	/**
	 * 查询规则详情
	 */
	@GetMapping("/detail")
	@Operation(summary = "查询规则详情", description = "根据ID查询规则详情")
	@ApiOperationSupport(order = 2)
	public R<RuleModelVO> detail(@Parameter(description = "规则ID", required = true) @RequestParam Long id) {
		RuleModel ruleModel = ruleModelServiceImpl.getById(id);
		return R.data(RuleModelWrapper.build().toVO(ruleModel));
	}

	/**
	 * 新增规则
	 */
	@PostMapping("/save")
	@Operation(summary = "新增规则", description = "新增规则")
	@ApiOperationSupport(order = 3)
	public R<Boolean> save(@RequestBody RuleModel ruleModel) {
		ruleModel.setStatus("stop");
		ruleModel.setCreateTime(new Date());
		return R.data(ruleModelServiceImpl.save(ruleModel));
	}

	/**
	 * 修改规则
	 */
	@PostMapping("/update")
	@Operation(summary = "修改规则", description = "修改规则")
	@ApiOperationSupport(order = 4)
	public R<Boolean> update(@RequestBody RuleModel ruleModel) {
		ruleModel.setUpdateTime(new Date());
		return R.data(ruleModelServiceImpl.updateById(ruleModel));
	}

	/**
	 * 删除规则
	 */
	@PostMapping("/remove")
	@Operation(summary = "删除规则", description = "根据ID删除规则")
	@ApiOperationSupport(order = 5)
	public R<Boolean> remove(@Parameter(description = "规则ID", required = true) @RequestParam Long id) {
		return R.data(ruleModelServiceImpl.removeById(id));
	}

	/**
	 * 启动/停止规则
	 */
	@PostMapping("/change-status")
	@Operation(summary = "启停规则", description = "启动或停止规则")
	@ApiOperationSupport(order = 6)
	public R<Boolean> changeStatus(@RequestBody RuleModel ruleModel) {
		RuleModel update = new RuleModel();
		update.setId(ruleModel.getId());
		update.setStatus(ruleModel.getStatus());
		update.setUpdateTime(new Date());
		return R.data(ruleModelServiceImpl.updateById(update));
	}
}
