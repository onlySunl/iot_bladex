

package org.springblade.modules.iot.service.category;

import org.springblade.modules.iot.controller.admin.category.vo.Category;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryListReqVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategorySaveReqVO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * IOT产品分类 Service 接口
 *
 * @author EnjoyIot
 */
public interface CategoryService {

    /**
     * 创建IOT产品分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid CategorySaveReqVO createReqVO);

    /**
     * 更新IOT产品分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid CategorySaveReqVO updateReqVO);

    /**
     * 删除IOT产品分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得IOT产品分类
     *
     * @param id 编号
     * @return IOT产品分类
     */
    Category getCategory(Long id);

    /**
     * 获得IOT产品分类列表
     *
     * @param listReqVO 查询条件
     * @return IOT产品分类列表
     */
    List<Category> getCategoryList(CategoryListReqVO listReqVO);

}
