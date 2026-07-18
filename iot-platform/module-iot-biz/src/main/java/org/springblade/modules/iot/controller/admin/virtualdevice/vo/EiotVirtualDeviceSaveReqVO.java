

package org.springblade.modules.iot.controller.admin.virtualdevice.vo;

import org.springblade.modules.iot.api.rule.dto.FilterConfig;
import org.springblade.modules.iot.api.task.dto.RuleAction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "管理后台 - 规则引擎新增/修改 Request VO")
@Data
public class EiotVirtualDeviceSaveReqVO {

    /**
     * 虚拟设备id
     */
    @Schema(description = "虚拟设备id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12640")
    private Long id;

    /**
     * 所属用户
     */
    private String uid;

    /**
     * 虚拟设备名称
     */
    @Schema(description = "虚拟设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "虚拟设备名称不能为空")
    private String name;

    /**
     * 产品key
     */
    private String productKey;

    /**
     * 虚拟的目标设备列表
     */
    @Schema(description = "虚拟的目标设备列表")
    private List<Long> devices = new ArrayList<>();

    /**
     * 虚拟类型
     */
    @Schema(description = "虚拟类型", example = "1")
    private String type;

    /**
     * 设备行为脚本
     */
    private String script;

    /**
     * 触发方式执行方式
     */
    @Schema(description = "触发方式执行方式", example = "1")
    private String trigger;

    /**
     * 触发表达式
     */
    @Schema(description = "触发表达式", example = "1")
    private String triggerExpression;

    /**
     * 运行状态
     */
    @Schema(description = "状态(0启用 1禁用)", example = "1")
    private Integer state;


}
