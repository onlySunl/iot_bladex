package org.springblade.modules.iot.productcommand.vo.result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import org.springblade.modules.iot.productcommandrequest.vo.result.ProductModelCommandRequestJsonResultVO;
import org.springblade.modules.iot.productcommandresponse.vo.result.ProductModelCommandResponseJsonResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
@Schema(title = "ProductModelCommandJsonResultVO", description = "产品模型服务命令JSON参数VO")
public class ProductModelCommandJsonResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。
     */
    @Schema(description = "指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。")
    @NotEmpty(message = "请填写指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。")
    @Size(max = 255, message = "指示命令的编码，如门磁的LOCK命令、摄像头的VIDEO_RECORD命令，命令名与参数共同构成一个完整的命令。支持英文大小写、数字及下划线，长度[2,50]。长度不能超过{max}")
    private String commandCode;
    /**
     * 指示命令名称
     */
    @Schema(description = "指示命令名称")
    @Size(max = 255, message = "指示命令名称长度不能超过{max}")
    private String commandName;
    /**
     * 命令描述。
     */
    @Schema(description = "命令描述")
    @Size(max = 255, message = "命令描述。长度不能超过{max}")
    private String description;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;

    @Schema(description = "产品请求服务命令属性")
    private List<ProductModelCommandRequestJsonResultVO> requests;

    @Schema(description = "产品响应服务命令属性")
    private List<ProductModelCommandResponseJsonResultVO> responses;

}
