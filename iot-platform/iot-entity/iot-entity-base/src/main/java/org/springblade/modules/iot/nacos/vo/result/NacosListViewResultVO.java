package org.springblade.modules.iot.nacos.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: NacosListView
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/14       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/14 11:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "NacosListViewResultVO", description = "NacosListViewResultVO")
public class NacosListViewResultVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 实例数据列表，类型为泛型 T，通常为多个实例对象的集合。
     */
    @Schema(description = "Nacos 实例数据列表", required = true)
    private List<T> data;

    /**
     * 数据总数，表示数据列表的数量或分页的总记录数。
     */
    @Schema(description = "数据的总数", example = "100")
    private Integer count;


}
