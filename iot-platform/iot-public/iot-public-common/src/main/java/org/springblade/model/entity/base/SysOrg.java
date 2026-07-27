package org.springblade.model.entity.base;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.annotation.echo.Echo;
import org.springblade.common.echo.EchoVO;
import org.springblade.model.constant.EchoApi;
import org.springblade.model.constant.EchoDictType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.model.constant.Condition;


/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author mqttsnet
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(title = "SysOrg", description = "组织")
@TableName("base_org")
public class SysOrg extends CustomBaseEntity implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private final Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "name", condition = Condition.LIKE)
    private String name;
    /**
     * 类型;[10-单位 20-部门]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ORG_TYPE)
     */
    @Schema(description = "类型")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ORG_TYPE)
    @TableField(value = "type_", condition = Condition.LIKE)
    private String type;
    /**
     * 简称
     */
    @Schema(description = "简称")
    @TableField(value = "short_name", condition = Condition.LIKE)
    private String shortName;
    /**
     * 父ID
     */
    @Schema(description = "父ID")
    @TableField(value = "parent_id")
    private Long parentId;
    /**
     * 树层级
     */
    @Schema(description = "树层级")
    @TableField(value = "tree_grade")
    private Integer treeGrade;
    /**
     * 树路径;用id拼接树结构
     */
    @Schema(description = "树路径")
    @TableField(value = "tree_path", condition = Condition.LIKE)
    private String treePath;
    /**
     * 排序
     */
    @Schema(description = "排序")
    @TableField(value = "sort_value")
    private Integer sortValue;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    @TableField(value = "state")
    private Boolean state;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @TableField(value = "remarks", condition = Condition.LIKE)
    private String remarks;

}
