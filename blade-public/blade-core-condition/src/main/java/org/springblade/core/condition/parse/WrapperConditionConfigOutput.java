package org.springblade.core.condition.parse;

import cn.hutool.core.collection.CollectionUtil;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.utils.StringUtils;
import org.springblade.core.condition.enumeration.OperatorEnum;
import org.springblade.core.condition.model.dto.BaseConditionDTO;
import org.springblade.core.condition.model.dto.ConditionGroupDTO;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.ConditionOutputParam;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springblade.core.condition.utils.ConditionConfigOutputUtil;
import org.springblade.core.condition.utils.ConditionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.database.mybatis.conditions.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Wrapper方式实现条件配置输出
 * 注：目前只能实现一层实体的模式单表
 **/
@Slf4j
@Component
public class WrapperConditionConfigOutput extends AbstractConditionConfigOutput<Wrapper> {

    @Override
    public Wrapper getContent(ConditionOutputParam param) {
        List<ConditionInfoDTO> infos = param.getConditionInfos();
        Wrapper wrapper = param.getWrapper();
        Map<String, Object> rightData = param.getRightData();
        if (CollectionUtils.isEmpty(infos)) {
            log.warn("条件组件输出Wrapper方式时传入参数不正确, infos为空，将直接返回null");
            return null;
        }

        if (wrapper == null) {
            log.warn("条件组件输出Wrapper方式时传入参数不正确，wrapper为null, 将直接返回null");
            return null;
        }

        List<BaseConditionDTO> conditionInfos = ConditionUtil.getBaseConditionDTOs(infos);
        buildWrapper(conditionInfos, wrapper, rightData, null);
        return wrapper;
    }

    /**
     *
     * 将条件表达式构建成对应的Wrapper
     * 注意： Wrapper 最多支持两个层级，暂不支持多个层级，如 （a=1 and b=2） or (a=3 and b=4)
     * @param conditionInfos 条件表达式列表
     * @param wrapper Wrapper
     * @param rightData Wrapper
     * @param criteria Wrapper
     * @return Example.Criteria
     */
    public Example.Criteria buildWrapper(List<BaseConditionDTO> conditionInfos,
                                         Wrapper wrapper,
                                         Map<String, Object> rightData,
                                         Example.Criteria criteria) {
        // 拼接逻辑操作符
        if (CollectionUtil.isNotEmpty(conditionInfos)) {

            for (BaseConditionDTO condition : conditionInfos) {

                switch (condition.getType()) {
                    case GROUP:
                        ConditionGroupDTO groupDTO = (ConditionGroupDTO) condition;
                        BaseConditionDTO.LogicalOperator logicalOperator = groupDTO.getLogicalOperator();
                        // 默认and
                        if (Objects.isNull(logicalOperator)) {
                            logicalOperator = BaseConditionDTO.LogicalOperator.AND;
                        }
                        criteria = dealOperator(wrapper, logicalOperator);
                        // 递归处理下面conditions(此处用递归一是好理解，而是层次不深对性能影响不大)
                        buildWrapper(groupDTO.getConditions(), wrapper, rightData, criteria);

                        break;
                    case CONDITION:
                        // 默认and
                        if (Objects.isNull(criteria)) {
                            criteria = wrapper.and();
                        }
                        SingleConditionDTO conditionExpDTO = (SingleConditionDTO) condition;
                        // 处理条件
                        dealCondition(wrapper, conditionExpDTO, rightData, criteria);

                        break;
                    default:

                }
            }
        }
        return criteria;
    }

    private Example.Criteria dealOperator(Wrapper wrapper, BaseConditionDTO.LogicalOperator opt) {
        Example.Criteria criteria;
        switch (opt) {
            case OR:
                criteria = wrapper.or();
                break;
            case AND:
            default:
                // 默认采用and
                criteria = wrapper.and();
                break;
        }

        return criteria;
    }

    /**
     *
     * 处理条件
     * @param wrapper Wrapper
     * @param conditionExpDTO 条件
     */
    private void dealCondition(Wrapper wrapper,
                               SingleConditionDTO conditionExpDTO,
                               Map<String, Object> rightData,
                               Example.Criteria criteria) {

        SingleConditionDTO.ConditionOperatorDTO operator = conditionExpDTO.getOperator();
        SingleConditionDTO.LeftParamDTO leftParam = conditionExpDTO.getLeftParam();
        List<SingleConditionDTO.RightParamDTO> rightParams = conditionExpDTO.getRightParams();
        BaseConditionDTO.LogicalOperator logicalOperator = conditionExpDTO.getLogicalOperator();
        // 普通的类型 leftParam operator rightParam
        OperatorEnum operatorEnum = OperatorEnum.valueOf(operator.getValue());
        SingleConditionDTO.RightParamDTO rightParam = rightParams.isEmpty() ? null : rightParams.get(0);
        Object rightValue = rightParam == null ? null : ConditionConfigOutputUtil.getRightValue(leftParam, rightParam, rightData);

        buildWrapper(criteria, operatorEnum, leftParam, rightValue, logicalOperator, rightParams, rightData);

    }

    private void buildWrapper(Example.Criteria wrapper,
                              OperatorEnum opt,
                              SingleConditionDTO.LeftParamDTO leftParam,
                              Object rightValue,
                              BaseConditionDTO.LogicalOperator logicalOperator,
                              List<SingleConditionDTO.RightParamDTO> rightParams,
                              Map<String, Object> rightData) {
        String leftField = StringUtils.uncapitalize(leftParam.getField());
        // 默认and (最后一个条件可能会不传递)
        if (Objects.isNull(logicalOperator)) {
            logicalOperator = BaseConditionDTO.LogicalOperator.AND;
        }
        // OR/AND 有点难受，方法名不一样，看能否重构一下减少代码行数
        switch (logicalOperator) {
            case OR:
                switch (opt) {
                    case EQ:
                        if (rightValue == null) {
                            wrapper.orIsNull(leftField);
                        } else {
                            wrapper.orEqualTo(leftField, rightValue);
                        }
                        break;
                    case NEQ:
                        wrapper.orNotEqualTo(leftField, rightValue);
                        break;
                    case GT:
                        wrapper.orGreaterThan(leftField, rightValue);
                        break;
                    case GE:
                        wrapper.orGreaterThanOrEqualTo(leftField, rightValue);
                        break;
                    case LT:
                        wrapper.orLessThan(leftField, rightValue);
                        break;
                    case LE:
                        wrapper.orLessThanOrEqualTo(leftField, rightValue);
                        break;
                    case LIKE:
                        wrapper.orLike(leftField, "%" + rightValue + "%");
                        break;
                    case NOTLIKE:
                        wrapper.orNotLike(leftField, "%" + rightValue + "%");
                        break;
                    case RLIKE:
                        wrapper.orLike(leftField, rightValue + "%");
                        break;
                    case LLIKE:
                        wrapper.orLike(leftField, "%" + rightValue);
                        break;
                    case IN:
                        wrapper.orIn(leftField, ((Collection) rightValue));
                        break;
                    case NOTIN:
                        wrapper.orNotIn(leftField, ((Collection) rightValue));
                        break;
                    case NULL:
                        wrapper.orIsNull(leftField);
                        break;
                    case NOTNULL:
                        wrapper.orIsNotNull(leftField);
                        break;
                    case ZERO:
                        wrapper.orEqualTo(leftField, 0);
                        break;
                    case NOTZERO:
                        wrapper.orNotEqualTo(leftField, 0);
                        break;
                    case BETWEEN:
                        SingleConditionDTO.RightParamDTO rightParam2 = rightParams.get(1);
                        Object rightValue2 = ConditionConfigOutputUtil.getRightValue(leftParam, rightParam2, rightData);
                        wrapper.orBetween(leftField, rightValue, rightValue2);
                        break;
                    case NOTBETWEEN:
                        rightParam2 = rightParams.get(1);
                        rightValue2 = ConditionConfigOutputUtil.getRightValue(leftParam, rightParam2, rightData);
                        wrapper.orNotBetween(leftField, rightValue, rightValue2);
                        break;
                    default:
                        throw new BizException("Wrapper方式暂不支持该操作符");
                }
                break;
            case AND:
            default:
                // 默认采用and
                switch (opt) {
                    case EQ:
                        if (rightValue == null) {
                            wrapper.andIsNull(leftField);
                        } else {
                            wrapper.andEqualTo(leftField, rightValue);
                        }
                        break;
                    case NEQ:
                        wrapper.andNotEqualTo(leftField, rightValue);
                        break;
                    case GT:
                        wrapper.andGreaterThan(leftField, rightValue);
                        break;
                    case GE:
                        wrapper.andGreaterThanOrEqualTo(leftField, rightValue);
                        break;
                    case LT:
                        wrapper.andLessThan(leftField, rightValue);
                        break;
                    case LE:
                        wrapper.andLessThanOrEqualTo(leftField, rightValue);
                        break;
                    case LIKE:
                        wrapper.andLike(leftField, "%" + rightValue + "%");
                        break;
                    case NOTLIKE:
                        wrapper.andNotLike(leftField, "%" + rightValue + "%");
                        break;
                    case RLIKE:
                        wrapper.andLike(leftField, rightValue + "%");
                        break;
                    case LLIKE:
                        wrapper.andLike(leftField, "%" + rightValue);
                        break;
                    case IN:
                        wrapper.andIn(leftField, ((Collection) rightValue));
                        break;
                    case NOTIN:
                        wrapper.andNotIn(leftField, ((Collection) rightValue));
                        break;
                    case NULL:
                        wrapper.andIsNull(leftField);
                        break;
                    case NOTNULL:
                        wrapper.andIsNotNull(leftField);
                        break;
                    case ZERO:
                        wrapper.andEqualTo(leftField, 0);
                        break;
                    case NOTZERO:
                        wrapper.andNotEqualTo(leftField, 0);
                        break;
                    case BETWEEN:
                        SingleConditionDTO.RightParamDTO rightParam2 = rightParams.get(1);
                        Object rightValue2 = ConditionConfigOutputUtil.getRightValue(leftParam, rightParam2, rightData);
                        wrapper.andBetween(leftField, rightValue, rightValue2);
                        break;
                    case NOTBETWEEN:
                        rightParam2 = rightParams.get(1);
                        rightValue2 = ConditionConfigOutputUtil.getRightValue(leftParam, rightParam2, rightData);
                        wrapper.andNotBetween(leftField, rightValue, rightValue2);
                        break;
                    default:
                        throw new BizException("Wrapper方式暂不支持该操作符");
                }
                break;
        }
    }

}
