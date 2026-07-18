

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * IOT产品分类 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_category")
// @KeySequence("eiot_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDO extends CustomBaseEntity {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 父分类id
     */
    @AutoColumn(comment = "父分类id")
    @TableField("parent_id")
    private Long parentId;
    /**
     * 分类名称
     */
    @AutoColumn(comment = "分类名称")
    @TableField("name")
    private String name;
    /**
     * 分类排序
     */
    @AutoColumn(comment = "分类排序")
    @TableField("sort")
    private Integer sort;
    /**
     * 图片地址
     */
    @AutoColumn(comment = "图片地址")
    @TableField("img_url")
    private String imgUrl;
    /**
     * 是否系统通用（0-否，1-是）
     */
    @AutoColumn(comment = "是否系统通用（0-否，1-是）")
    @TableField("is_sys")
    private Integer isSys;

}
