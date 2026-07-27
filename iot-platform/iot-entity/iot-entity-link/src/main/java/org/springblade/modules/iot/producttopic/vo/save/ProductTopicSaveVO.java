package org.springblade.modules.iot.producttopic.vo.save;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单保存方法VO
 * 产品Topic信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "ProductTopicSaveVO", description = "产品Topic信息表")
public class ProductTopicSaveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    @NotNull(message = "请填写产品标识")
    @Size(max = 100, message = "产品标识长度不能超过{max}")
    private String productIdentification;

    /**
     * 功能类型
     */
    @Schema(description = "功能类型")
    @NotNull(message = "请填写功能类型")
    private Integer functionType;

    /**
     * Topic类型(0:基础Topic,1:自定义Topic)
     */
    @Schema(description = "Topic类型(0:基础Topic,1:自定义Topic)")
    private Integer topicType;
    /**
     * topic
     */
    @Schema(description = "topic")
    @Size(max = 100, message = "topic长度不能超过{max}")
    private String topic;
    /**
     * 发布者
     */
    @Schema(description = "发布者")
    @NotNull(message = "请填写发布者")
    private Integer publisher;
    /**
     * 订阅者
     */
    @Schema(description = "订阅者")
    @NotNull(message = "请填写订阅者")
    private Integer subscriber;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
