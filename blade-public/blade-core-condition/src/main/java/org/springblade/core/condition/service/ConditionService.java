package org.springblade.core.condition.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.core.condition.enumeration.OperatorEnum;
import org.springblade.core.condition.enumeration.ParamTypeEnum;
import org.springblade.core.condition.model.dto.BaseConditionDTO;
import org.springblade.core.condition.model.dto.ConditionGroupDTO;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.ConditionParamResult;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springblade.core.condition.operator.ConditionOperator;
import org.springblade.core.condition.operator.ConditionOperatorLoader;
import org.springblade.core.condition.utils.ConditionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConditionService {

    private static final String NOT_SUPPORT_TYPE = "暂不支持这种类型的条件:";

    /**
     * 加载操作符列表
     */
    public List<ConditionOperator> getAllOperator() {
        return ConditionOperatorLoader.getAllOperators();
    }

    /**
     * 加载连接符列表
     */
    public List<ConditionOperator> getAllOperatorConnect() {
        return ConditionOperatorLoader.getAllOperatorConnect();
    }

    /**
     * 校验数据
     *
     * @param condition
     * @return
     */
    public boolean check(List<ConditionInfoDTO> condition) {
        List<BaseConditionDTO> conditions = ConditionUtil.getBaseConditionDTOs(condition);
        // 校验
        checkDetail(conditions);
        return true;
    }

    private void checkDetail(List<BaseConditionDTO> conditionDTOs) {
        Optional.ofNullable(conditionDTOs).ifPresent(conditions -> conditions.forEach(condition -> {
            switch (condition.getType()) {
                case GROUP:
                    // 是条件组
                    ConditionGroupDTO groupDTO = condition.cast();
                    // 检查分组内condition条件是否合法
                    checkInGroupConditions(groupDTO.getConditions());
                    // 递归获取组内条件变量
                    checkDetail(groupDTO.getConditions());
                    break;
                case CONDITION:
                    SingleConditionDTO conditionDTO = condition.cast();
                    // 多态，jsr303注解没有生效，暂时手动校验非空
                    checkConditionLegal(conditionDTO);
                    break;
                default:
                    throw new BizException(NOT_SUPPORT_TYPE + condition.getType());
            }
        }));
    }

    /**
     * 校验分组内condition是否合法
     *
     * @param conditions
     */
    private void checkInGroupConditions(List<BaseConditionDTO> conditions) {
        if (CollectionUtils.isNotEmpty(conditions)) {
            for (BaseConditionDTO conditionDTO : conditions) {
                if (conditionDTO.getLogicalOperator() == null) {
                    throw new BizException("条件组内条件logicOperator不能为空");
                }
            }

        }
    }

    /**
     * 校验左值、操作符、右值
     *
     * @param conditionDTO
     */
    private void checkConditionLegal(SingleConditionDTO conditionDTO) {
        SingleConditionDTO.LeftParamDTO leftParam = conditionDTO.getLeftParam();
        SingleConditionDTO.ConditionOperatorDTO operator = conditionDTO.getOperator();
        List<SingleConditionDTO.RightParamDTO> rightParams = conditionDTO.getRightParams();

        checkNull(leftParam, "左值不允许为空", conditionDTO);
        checkNull(operator, "操作符不允许为空", conditionDTO);
        checkNull(rightParams, "右值不允许为空", conditionDTO);
    }

    /**
     * 校验为空
     *
     * @param obj
     * @param errorCode
     * @param conditionDTO
     */
    private void checkNull(Object obj, String errorCode, SingleConditionDTO conditionDTO) {
        if (Objects.isNull(obj)) {
            log.warn("the condition is illegality: {}", JsonUtil.toJson(conditionDTO));
            throw new BizException(errorCode);
        }
    }

    /**
     * 获取真正的条件列表（从分组中拍平）
     *
     * @param conditionInfos 条件信息
     * @return List<SingleConditionDTO>
     */
    public List<SingleConditionDTO> selectSingleCondition(List<ConditionInfoDTO> conditionInfos) {

        List<BaseConditionDTO> conditionDTOs = ConditionUtil.getBaseConditionDTOs(conditionInfos);

        List<SingleConditionDTO> singleConditionDTOs = Lists.newLinkedList();

        addSingleCondition(conditionDTOs, singleConditionDTOs);

        return singleConditionDTOs;
    }

    /**
     * 拍平单个条件
     *
     * @param conditionDTOs       条件DTO列表
     * @param singleConditionDTOs 条件列表
     */
    private void addSingleCondition(List<BaseConditionDTO> conditionDTOs, List<SingleConditionDTO> singleConditionDTOs) {

        Optional.ofNullable(conditionDTOs).ifPresent(conditions -> conditions.forEach(condition -> {
            switch (condition.getType()) {
                case GROUP:
                    // 是条件组
                    ConditionGroupDTO groupDTO = condition.cast();
                    // 递归获取组内条件变量
                    addSingleCondition(groupDTO.getConditions(), singleConditionDTOs);
                    break;
                case CONDITION:
                    SingleConditionDTO conditionDTO = condition.cast();
                    singleConditionDTOs.add(conditionDTO);
                    break;
                default:
                    throw new BizException("暂不支持这种类型的条件:" + condition.getType());
            }
        }));
    }

    /**
     * 解析条件，提取变量参数，方便使用者组装数据
     *
     * @param conditionInfos 条件数据
     */
    public List<ConditionParamResult> selectVariableParam(List<ConditionInfoDTO> conditionInfos) {

        if (CollectionUtils.isEmpty(conditionInfos)) {
            throw new BizException("");
        }

        List<BaseConditionDTO> conditions = ConditionUtil.getBaseConditionDTOs(conditionInfos);

        List<ConditionParamResult> params = Lists.newLinkedList();

        // 将条件都获取到
        addVariableParam(conditions, params);

        return params;
    }

    /**
     * 过去变量参数放入列表中
     *
     * @param conditionDTOs  条件
     * @param variableParams 变量参数列表
     */
    private void addVariableParam(List<BaseConditionDTO> conditionDTOs, List<ConditionParamResult> variableParams) {

        Optional.ofNullable(conditionDTOs).ifPresent(conditions -> conditions.forEach(condition -> {
            switch (condition.getType()) {
                case GROUP:
                    // 是条件组
                    ConditionGroupDTO groupDTO = condition.cast();
                    // 递归获取组内条件变量
                    addVariableParam(groupDTO.getConditions(), variableParams);
                    break;
                case CONDITION:
                    SingleConditionDTO conditionDTO = condition.cast();
                    SingleConditionDTO.ConditionOperatorDTO operator = conditionDTO.getOperator();
                    List<SingleConditionDTO.RightParamDTO> rightParams = conditionDTO.getRightParams();
                    // 没有右值直接返回
                    if (CollectionUtils.isEmpty(rightParams)) {
                        break;
                    }
                    OperatorEnum operatorEnum = OperatorEnum.getByValue(operator.getValue());
                    rightParams.stream().filter(rightParam -> ParamTypeEnum.CONTEXT_VARIABLE.equals(rightParam.getType())).forEach(rightParam -> {
                        // FIXME 先认为变量都是自定义的， 后面看界面原型如何扩展
                        ConditionParamResult paramResult = new ConditionParamResult();
                        paramResult.setParamType(ConditionParamResult.ParamTypeEnum.CUSTOM);
                        paramResult.setProperty(rightParam.getValue());

                        // 如果是IN, 右值接收一个列表, 否则接收单对象
                        if (OperatorEnum.IN.equals(operatorEnum)) {
                            paramResult.setType(ConditionParamResult.ObjectTypeEnum.LIST);
                        } else {
                            paramResult.setType(ConditionParamResult.ObjectTypeEnum.VALUE);
                        }
                        variableParams.add(paramResult);
                    });
                    break;
                default:
                    throw new BizException("暂不支持这种类型的条件:" + condition.getType());
            }
        }));

    }


    /**
     * 判定条件是否是单条件结构
     *
     * @param conditionInfos
     */
    public boolean isSingleCondition(List<ConditionInfoDTO> conditionInfos) {
        List<BaseConditionDTO> conditionDTOs = ConditionUtil.getBaseConditionDTOs(conditionInfos);
        return checkSingleCondition(conditionDTOs);
    }


    /**
     * 判定是否是单个条件
     *
     * @param conditionDTOs 条件DTO列表
     * @return boolean
     * @author shisen
     * @date 2021/8/19
     */
    private boolean checkSingleCondition(List<BaseConditionDTO> conditionDTOs) {
        if (conditionDTOs.size() == 1) {
            BaseConditionDTO cond = conditionDTOs.get(0);
            // 如果是分组条件，需要递归看一下
            if (BaseConditionDTO.ConditionExpTypeEnum.GROUP.equals(cond.getType())) {
                ConditionGroupDTO groupDTO = cond.cast();
                return checkSingleCondition(groupDTO.getConditions());
            } else {
                // 是单个条件
                return true;
            }
        }
        return false;
    }

    /**
     * 将条件组件结构化数据翻译成可读性的显示值
     *
     * @param condition
     * @return
     */
    public String transfer(List<ConditionInfoDTO> condition) {

        List<BaseConditionDTO> conditions = ConditionUtil.getBaseConditionDTOs(condition);

        StringBuilder translationInfo = new StringBuilder();

        transferCondition(null, conditions, translationInfo);

        return translationInfo.toString();
    }

    private void transferCondition(BaseConditionDTO.LogicalOperator logicalOperator, List<BaseConditionDTO> conditionInfos, StringBuilder translationInfo) {

        boolean isFirst = true;

        for (BaseConditionDTO conditionDTO : conditionInfos) {

            if (!isFirst && Objects.nonNull(logicalOperator)) {
                translationInfo.append(logicalOperator.getValue()).append(" ");
            }

            isFirst = false;

            switch (conditionDTO.getType()) {
                case GROUP:
                    ConditionGroupDTO groupDTO = conditionDTO.cast();
                    // 增加(
                    translationInfo.append("( ");
                    // 递归buildSql拼接子条件
                    transferCondition(groupDTO.getLogicalOperator(), groupDTO.getConditions(), translationInfo);
                    // 增加)
                    translationInfo.append(") ");
                    break;
                case CONDITION:
                    SingleConditionDTO condition = conditionDTO.cast();

                    SingleConditionDTO.LeftParamDTO leftParam = condition.getLeftParam();
                    SingleConditionDTO.ConditionOperatorDTO operator = condition.getOperator();
                    List<SingleConditionDTO.RightParamDTO> rightParams = condition.getRightParams();

                    // 拼接出类似  leftValue operator rightValue的形式
                    // leftValue 需要区分出表实体.表字段
                    translationInfo.append(leftParam.getDesc()).append(" ");
                    // 处理operator
                    translationInfo.append(operator.getDesc()).append(" ");
                    // rightValue 需要在rightData中转换一下，如果VARIABLE类型没有获取到就返回配置原值
                    SingleConditionDTO.RightParamDTO rightParam = rightParams.get(0);
                    translationInfo.append(rightParam.getDesc()).append(" ");

                    break;
                default:
            }
        }
    }
}
