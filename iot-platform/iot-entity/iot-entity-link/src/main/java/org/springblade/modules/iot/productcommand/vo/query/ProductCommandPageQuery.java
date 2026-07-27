package org.springblade.modules.iot.productcommand.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 * 表单查询条件VO
 * 产品模型设备服务命令表
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
@Schema(title = "ProductCommandPageQuery", description = "产品模型设备服务命令表")
public class ProductCommandPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "命令id")
    private Long id;

    /**
     * 服务ID
     */
    @Schema(description = "服务ID")
    private Long serviceId;
    /**
     * 指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。
     */
    @Schema(description = "指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。")
    private String commandCode;
    /**
     * 指示命令名称
     */
    @Schema(description = "指示命令名称")
    private String commandName;
    /**
     * 命令描述。
     */
    @Schema(description = "命令描述。")
    private String description;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
