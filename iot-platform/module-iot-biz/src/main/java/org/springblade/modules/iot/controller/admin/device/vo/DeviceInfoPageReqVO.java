

package org.springblade.modules.iot.controller.admin.device.vo;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.modules.iot.common.entity.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 设备信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceInfoPageReqVO extends PageParam {

    @Schema(description = "设备唯一标识")
    private String dn;

    @Schema(description = "产品key")
    private String productKey;

    @Schema(description = "产品key列表")
    private List<String> productKeyList;

    @Schema(description = "机构id", example = "13057")
    private Long deptId;

    @Schema(description = "设备类型", example = "2")
    private Integer nodeType;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime[] createTime;

    @Schema(description = "别名", example = "李四")
    private String name;

    @Schema(description = "设备状态(0:否, 1:在线, 2-未激活，3-禁用)", example = "2")
    private Integer state;



    @Schema(description = "设备序列号")
    private String serialNo;


    @Schema(description = "位置信息")

    private String addr;
    @Schema(description = "固件版本")

    private String firmVersion;

    @Schema(description = "分组id")

    private Long groupId;

    @Schema(description = "父级id")
    private Long parentId;

    @Schema(description = "绑定状态")
    private Boolean bindStatus;

}
