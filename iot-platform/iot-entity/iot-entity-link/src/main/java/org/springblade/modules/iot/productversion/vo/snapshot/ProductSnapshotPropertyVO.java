package org.springblade.modules.iot.productversion.vo.snapshot;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import org.springblade.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品快照 ── 物模型属性节点。
 *
 * @author mqttsnet
 * @see ProductSnapshotVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "ProductSnapshotPropertyVO", description = "快照属性节点")
public class ProductSnapshotPropertyVO implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字典 / 用户 / 组织等回显结果集合 ── EchoService 把 @Echo 字段的查回值写入此处。
     */
    @Builder.Default
    @Schema(description = "回显结果集合(字典 / 用户 / 组织等翻译值)")
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "属性编码")
    private String propertyCode;

    @Schema(description = "属性名称")
    private String propertyName;

    @Schema(description = "数据类型")
    private String datatype;

    @Schema(description = "枚举可选值")
    private String enumlist;

    @Schema(description = "最小值")
    private String min;

    @Schema(description = "最大值")
    private String max;

    @Schema(description = "步长")
    private String step;

    @Schema(description = "最大字符长度")
    private String maxlength;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "读写权限")
    private String method;

    @Schema(description = "是否必填")
    private Integer required;

    @Schema(description = "属性描述")
    private String description;
}
