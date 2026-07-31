package org.springblade.core.condition.model.dto;

import org.springblade.core.condition.enumeration.ParamTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * 条件表达式DTO
 **/
@Setter
@Getter
public class SingleConditionDTO extends BaseConditionDTO {

    private static final long serialVersionUID = -4823724414401041768L;

    /**
     * 左值
     */
    @NotNull
    private LeftParamDTO leftParam;

    /**
     * 操作符
     */
    @NotNull
    private SingleConditionDTO.ConditionOperatorDTO operator;

    /**
     * 右值
     */
    @NotNull
    private List<RightParamDTO> rightParams;


    @Setter
    @Getter
    public static class ConditionOperatorDTO {

        private static final long serialVersionUID = 7569791391945301451L;

        /**
         * 操作符值
         */
        @NotNull
        private String value;

        /**
         * 操作符描述
         */
        @NotNull
        private String desc;

    }

    @Setter
    @Getter
    public static class LeftParamDTO {

        private static final long serialVersionUID = -3436203279038480623L;

        private String id;

        /**
         * 产品标识
         */
        private String productIdentification;

        /**
         * 设备标识
         */
        private String deviceIdentification;

        /**
         * 服务标识
         */
        private String serviceCode;

        /**
         * 条件字段
         */
        private String field;

        /**
         * 条件字段值
         */
        private Objects value;

        /**
         * 描述（显示名称）
         */
        private String desc;

        /**
         * 类型
         */
        private String dataType;

        /**
         * 多选
         */
        private Boolean multiSelect = false;

    }


    @Setter
    @Getter
    public static class RightParamDTO {

        private static final long serialVersionUID = -2172162814194014145L;

        private String id;

        private ParamTypeEnum type;

        /**
         * 参数值
         */
        private String value;

        /**
         * 参数显示值
         */
        private String desc;


        /**
         * 类型
         */
        private String dataType;

    }

}
