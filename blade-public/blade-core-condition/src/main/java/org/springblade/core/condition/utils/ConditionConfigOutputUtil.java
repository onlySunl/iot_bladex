package org.springblade.core.condition.utils;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.jackson.JsonCoreUtils;
import org.springblade.basic.utils.StringUtils;
import org.springblade.core.condition.model.dto.BaseConditionDTO;
import org.springblade.core.condition.model.dto.ConditionInfoDTO;
import org.springblade.core.condition.model.dto.ConditionOutputParam;
import org.springblade.core.condition.model.dto.ConditionParamResult;
import org.springblade.core.condition.model.dto.SingleConditionDTO;
import org.springblade.core.condition.parse.ConditionConfigOutputFactory;
import org.springblade.core.condition.parse.RightParamParserFactory;
import org.springblade.core.condition.parse.WrapperConditionConfigOutput;
import org.springblade.core.condition.service.ConditionService;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.database.mybatis.conditions.Wrapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 再封装条件表达式的输出，方便使用
 **/
@Slf4j
public final class ConditionConfigOutputUtil {

    private static ConditionConfigOutputFactory outputFactory;

    private static ConditionService conditionService;

    private static RightParamParserFactory rightParamParserFactory;


    static {
        outputFactory = new ConditionConfigOutputFactory();
        conditionService = new ConditionService();
        rightParamParserFactory = new RightParamParserFactory();
    }

    private ConditionConfigOutputUtil() {
    }

    /**
     * 条件组件按照Wrapper方式输出
     *
     * @param conditionJsonStr 条件json字符串
     * @param wrapper          Wrapper
     */
    public static Wrapper getWrapperContent(String conditionJsonStr, Wrapper wrapper) {
        return getWrapperContent(conditionJsonStr, wrapper, (Map<String, Object>) null);
    }

    /**
     * 条件组件按照Wrapper方式输出
     *
     * @param conditionJsonStr 条件json字符串
     * @param wrapper          Wrapper
     * @param rightData        右值数据
     */
    public static Wrapper getWrapperContent(String conditionJsonStr, Wrapper wrapper, Map<String, Object> rightData) {

        checkParam(conditionJsonStr);

        WrapperConditionConfigOutput wrapperOutput = outputFactory.getOutputImpl(WrapperConditionConfigOutput.class);

        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(conditionJsonStr, ConditionInfoDTO.class);

        ConditionOutputParam param = new ConditionOutputParam();
        param.setConditionInfos(conditionInfos);
        param.setWrapper(wrapper);
        param.setRightData(rightData);

        return wrapperOutput.getContent(param);
    }

    /**
     * 条件组件按照Wrapper方式输出 接收参数List<Map> 循环拼接OR 如 （a=arr[0].x1 and b = arr[0].x2）or （a=arr[1].x1 and b = arr[1].x2）... 目前主要是数据流使用这个场景
     *
     * @param conditionJsonStr 条件json字符串
     * @param wrapper          Wrapper
     * @param rightData        右值数据
     */
    public static Wrapper getWrapperContent(String conditionJsonStr, Wrapper wrapper, List<Map<String, Object>> rightData) {
        checkParam(conditionJsonStr);
        WrapperConditionConfigOutput wrapperOutput = outputFactory.getOutputImpl(WrapperConditionConfigOutput.class);
        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(conditionJsonStr, ConditionInfoDTO.class);
        List<BaseConditionDTO> baseConditionDTOs = ConditionUtil.getBaseConditionDTOs(conditionInfos);
        if (CollectionUtils.isNotEmpty(rightData)) {
            for (Map<String, Object> data : rightData) {
                Example.Criteria criteria = wrapperOutput.buildWrapper(baseConditionDTOs, wrapper, data, null);
                // 外部需要控制，强制OR
                criteria.setAndOr("or");
            }
        }
        return wrapper;
    }

    /**
     * 获取条件中的变量参数
     *
     * @param conditionJsonStr 条件信息JSON字符串
     * @return {@link List<ConditionParamResult>} 变量参数列表
     */
    public static List<ConditionParamResult> selectVariableParams(String conditionJsonStr) {

        checkParam(conditionJsonStr);

        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(conditionJsonStr, ConditionInfoDTO.class);
        return conditionService.selectVariableParam(conditionInfos);
    }

    private static void checkParam(String conditionJsonStr) {
        if (StringUtils.isBlank(conditionJsonStr)) {
            // 参数不合法
            throw new BizException("");
        }
    }

    /**
     * 获取右值
     *
     * @param rightParam 右边参数
     * @return java.lang.Object
     */
    public static Object getRightValue(SingleConditionDTO.LeftParamDTO leftParamDTO, SingleConditionDTO.RightParamDTO rightParam, Map<String, Object> data) {
        return rightParamParserFactory.getValue(leftParamDTO, rightParam, data);
    }

    /**
     * 判定条件是不是只配置了一个条件
     *
     * @param conditionJsonStr 条件信息JSON字符串
     */
    public static boolean isSingleCondition(String conditionJsonStr) {
        checkParam(conditionJsonStr);

        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(conditionJsonStr, ConditionInfoDTO.class);
        return conditionService.isSingleCondition(conditionInfos);
    }


    /**
     * 获取真正的条件列表（从分组中拍平）
     *
     * @param conditionJsonStr 条件信息JSON字符串
     * @return List<SingleConditionDTO>
     */
    public static List<SingleConditionDTO> selectSingleCondition(String conditionJsonStr) {
        checkParam(conditionJsonStr);
        List<ConditionInfoDTO> conditionInfos = JSONUtil.toList(conditionJsonStr, ConditionInfoDTO.class);
        return conditionService.selectSingleCondition(conditionInfos);
    }

    /**
     * 校验是否合法
     *
     * @param conditionJsonStr 条件信息JSON字符串
     * @return Boolean true:合法，否则抛出异常
     */
    public static Boolean checkLegal(String conditionJsonStr) {
        checkParam(conditionJsonStr);
        List<ConditionInfoDTO> conditionInfos = JsonCoreUtils.toList(conditionJsonStr, ConditionInfoDTO.class);
        return conditionService.check(conditionInfos);
    }
}
