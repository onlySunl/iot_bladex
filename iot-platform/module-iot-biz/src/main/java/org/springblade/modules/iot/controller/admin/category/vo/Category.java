

package org.springblade.modules.iot.controller.admin.category.vo;


import org.springblade.modules.iot.api.TenantModel;
import lombok.*;

/**
 * IOT产品分类 DO
 *
 * @author EnjoyIot
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends TenantModel {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 分类id
     */
    private Long id;
    /**
     * 父分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类排序
     */
    private Integer sort;
    /**
     * 开启状态
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 是否系统通用（0-否，1-是）
     */
    private Integer isSys;

}
