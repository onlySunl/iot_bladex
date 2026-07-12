package org.springblade.modules.iot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.pojo.entity.RuleModel;
import org.springblade.modules.iot.pojo.vo.RuleModelVO;
import org.springblade.modules.iot.service.IRuleModelService;
import org.springblade.modules.iot.wrapper.RuleModelWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
	public R<IPage<RuleModelVO>> page(@RequestParam Map<String, Object> ruleModel, Query query) {
		QueryWrapper<RuleModel> queryWrapper = Condition.getQueryWrapper(ruleModel, RuleModel.class);
		IPage<RuleModel> pages = ruleModelServiceImpl.page(Condition.getPage(query),  queryWrapper);

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
		return R.data(RuleModelWrapper.build().entityVO(ruleModel));
	}

	/**
	 * 新增规则
	 */
	@PostMapping("/save")
	@Operation(summary = "新增规则", description = "新增规则")
	@ApiOperationSupport(order = 3)
	public R<Boolean> save(@RequestBody RuleModel ruleModel) {
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
