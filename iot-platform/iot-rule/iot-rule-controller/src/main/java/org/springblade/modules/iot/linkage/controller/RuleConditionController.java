package org.springblade.modules.iot.linkage.controller;

import org.springblade.basic.base.R;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.ConditionParamResult;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springblade.core.condition.operator.ConditionOperator;
import org.springblade.core.condition.utils.ConditionConfigOutputUtil;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.modules.iot.entity.linkage.RuleCondition;
import org.springblade.modules.iot.service.linkage.RuleConditionService;
import org.springblade.modules.iot.vo.query.linkage.RuleConditionPageQuery;
import org.springblade.modules.iot.vo.result.linkage.RuleConditionResultVO;
import org.springblade.modules.iot.vo.save.linkage.RuleConditionSaveVO;
import org.springblade.modules.iot.vo.update.linkage.RuleConditionUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 规则条件表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-07-19 23:36:30
 * @create [2023-07-19 23:36:30] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/ruleCondition")
@Tag(name = "规则条件")
public class RuleConditionController extends SuperController<RuleConditionService, Long, RuleCondition, RuleConditionSaveVO,
        RuleConditionUpdateVO, RuleConditionPageQuery, RuleConditionResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @GetMapping("/operator/operateList")
    @Operation(summary = "加载所有的预置操作符", description = "加载所有的预置操作符")
    @WebLog(value = "加载所有的预置操作符", request = false)
    public R<List<ConditionOperator>> listAllOperator() {
        return R.success(superService.getAllOperator());
    }

    @GetMapping("/operator/connectList")
    @Operation(summary = "加载所有的预置连接符", description = "加载所有的预置连接符")
    @WebLog(value = "加载所有的预置连接符", request = false)
    public R<List<ConditionOperator>> listAllOperatorConnect() {
        return R.success(superService.getAllOperatorConnect());
    }

    @PostMapping("/transfer")
    @Operation(summary = "将条件翻译成可读性显示值", description = "将条件翻译成可读性显示值")
    @WebLog(value = "将条件翻译成可读性显示值", request = false)
    public R<String> transfer(@RequestBody List<ConditionInfoDTO> condition) {
        return R.success(superService.transfer(condition));
    }

    @PostMapping("/check")
    @Operation(summary = "校验数据是否合法", description = "校验数据是否合法")
    public R<Boolean> check(@RequestBody String condition) {
        Boolean flag = ConditionConfigOutputUtil.checkLegal(condition);
        return R.success(flag);
    }

    @PostMapping("/selectSingleCondition")
    @Operation(summary = " 获取真正的条件列表（从分组中拍平）", description = " 获取真正的条件列表（从分组中拍平）")
    public R<List<SingleConditionDTO>> selectSingleCondition(@RequestBody String conditionJsonStr) {
        List<SingleConditionDTO> singleConditionDTOs = ConditionConfigOutputUtil.selectSingleCondition(conditionJsonStr);
        return R.success(singleConditionDTOs);
    }

    @PostMapping("/selectVariableParam")
    @Operation(summary = "获取变量参数", description = "获取变量参数")
    public R<List<ConditionParamResult>> selectVariableParam(@RequestBody List<ConditionInfoDTO> conditionInfos) {
        return R.success(superService.selectVariableParam(conditionInfos));
    }


    /**
     * 新增 规则条件动作信息
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "新增规则条件动作信息", description = "新增规则条件动作信息，包含条件和动作")
    @PostMapping("/saveRuleConditionAction")
    @WebLog(value = "保存全部规则信息", request = false)
    public R saveRuleConditionAction(@RequestBody RuleConditionSaveVO saveVO) {
        return R.success(superService.saveRuleConditionAction(saveVO));
    }


    /**
     * Updates rule condition action information.
     *
     * @param updateVO The update parameters.
     * @return Result of the update operation.
     */
    @Operation(summary = "修改规则条件动作信息", description = "修改规则条件动作信息，包含条件和动作")
    @PutMapping("/updateRuleConditionAction")
    @WebLog(value = "更新规则条件动作信息", request = false)
    public R updateRuleConditionAction(@RequestBody RuleConditionUpdateVO updateVO) {
        return R.success(superService.updateRuleConditionAction(updateVO));
    }

}


