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
import org.springblade.modules.iot.pojo.entity.SceneLinkage;
import org.springblade.modules.iot.pojo.vo.SceneLinkageVO;
import org.springblade.modules.iot.service.ISceneLinkageService;
import org.springblade.modules.iot.wrapper.SceneLinkageWrapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/scene-linkage")
@Tag(name = "场景联动", description = "场景联动管理接口")
public class SceneLinkageController extends BladeController {

	private final ISceneLinkageService sceneLinkageService;

	@GetMapping("/list")
	@Operation(summary = "场景联动列表")
	public R<IPage<SceneLinkageVO>> page(Map<String, Object> sceneLinkage, Query query) {
		QueryWrapper<SceneLinkage> queryWrapper = Condition.getQueryWrapper(sceneLinkage, SceneLinkage.class);
		IPage<SceneLinkage> pages = sceneLinkageService.page(Condition.getPage(query), queryWrapper);
		return R.data(SceneLinkageWrapper.build().pageVO(pages));
	}

	@GetMapping("/detail")
	@Operation(summary = "场景联动详情")
	public R<SceneLinkageVO> detail(@Parameter(name = "id") @RequestParam Long id) {
		return R.data(SceneLinkageWrapper.build().entityVO(sceneLinkageService.getById(id)));
	}

	@PostMapping("/save")
	@Operation(summary = "新增或修改场景联动")
	public R<Boolean> save(@RequestBody SceneLinkage entity) {
		return R.data(sceneLinkageService.saveOrUpdate(entity));
	}

	@PostMapping("/remove")
	@Operation(summary = "删除场景联动")
	public R<Boolean> remove(@RequestBody List<Long> ids) {
		return R.data(sceneLinkageService.removeByIds(ids));
	}

	@PostMapping("/enable")
	@Operation(summary = "启用场景联动")
	public R<Boolean> enable(@RequestParam Long id) {
		return R.data(sceneLinkageService.enable(id));
	}

	@PostMapping("/disable")
	@Operation(summary = "禁用场景联动")
	public R<Boolean> disable(@RequestParam Long id) {
		return R.data(sceneLinkageService.disable(id));
	}
}
