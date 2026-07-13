package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.Protocol;

/**
 * 协议定义 VO
 *
 * @author blade-iot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "协议定义 VO")
public class ProtocolVO extends Protocol {

    @Schema(description = "状态名称")
    private String statusName;
}
