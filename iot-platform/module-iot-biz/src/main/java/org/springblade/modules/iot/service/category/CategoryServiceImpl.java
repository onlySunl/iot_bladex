

package org.springblade.modules.iot.service.category;


import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.category.vo.Category;
import org.springblade.modules.iot.controller.admin.category.vo.CategoryListReqVO;
import org.springblade.modules.iot.controller.admin.category.vo.CategorySaveReqVO;
import org.springblade.modules.iot.convert.ProductConvert;
import org.springblade.modules.iot.dal.mysql.category.CategoryMapper;
import org.springblade.modules.iot.dal.mysql.product.ProductMapper;
import org.springblade.modules.iot.entity.CategoryDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.CategoryDO;
import org.springblade.modules.iot.dal.mysql.category.CategoryMapper;


/**
 * IOT产品分类 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, CategoryDO> implements ICategoryService {


    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public Long createCategory(CategorySaveReqVO createReqVO) {
        // 校验父分类id的有效性
        validateParentCategory(null, createReqVO.getParentId());
        // 校验分类名称的唯一性
        validateCategoryNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        CategoryDO category = BeanUtil.copy(createReqVO, CategoryDO.class);
        categoryMapper.insert(category);
        // 返回
        return category.getId();
    }

    @Override
    public void updateCategory(CategorySaveReqVO updateReqVO) {
        // 校验存在
        validateCategoryExists(updateReqVO.getId());
        // 校验父分类id的有效性
        validateParentCategory(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验分类名称的唯一性
        validateCategoryNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        CategoryDO updateObj = BeanUtil.copy(updateReqVO, CategoryDO.class);
        categoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        validateCategoryExists(id);
        // 校验是否有子IOT产品分类
        if (categoryMapper.selectCountByParentId(id) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_EXITS_CHILDREN);
        }
        // 判断是否有产品关联
        if (productMapper.selectCountByCategoryId(id) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_EXITS_PRODUCT);
        }

        // 删除
        categoryMapper.deleteById(id);
    }

    private void validateCategoryExists(Long id) {
        if (categoryMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_NOT_EXISTS);
        }
    }

    private void validateParentCategory(Long id, Long parentId) {
        if (parentId == null || CategoryDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父IOT产品分类
        if (Objects.equals(id, parentId)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_PARENT_ERROR);
        }
        // 2. 父IOT产品分类不存在
        CategoryDO parentCategory = categoryMapper.selectById(parentId);
        if (parentCategory == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父IOT产品分类，如果父IOT产品分类是自己的子IOT产品分类，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentCategory.getParentId();
            if (Objects.equals(id, parentId)) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父IOT产品分类
            if (parentId == null || CategoryDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentCategory = categoryMapper.selectById(parentId);
            if (parentCategory == null) {
                break;
            }
        }
    }

    private void validateCategoryNameUnique(Long id, Long parentId, String name) {
        CategoryDO category = categoryMapper.selectByParentIdAndName(parentId, name);
        if (category == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的IOT产品分类
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_NAME_DUPLICATE);
        }
        if (!Objects.equals(category.getId(), id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.CATEGORY_NAME_DUPLICATE);
        }
    }

    @Override
    public Category getCategory(Long id) {
        return ProductConvert.INSTANCE.convertCategory(categoryMapper.selectById(id));
    }

    @Override
    public List<Category> getCategoryList(CategoryListReqVO listReqVO) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<CategoryDO>();
        if (listReqVO.getStatus() != null) {
            wrapper.eq(CategoryDO::getStatus, listReqVO.getStatus());
        }
        if (listReqVO.getParentId() != null) {
            wrapper.eq(CategoryDO::getParentId, listReqVO.getParentId());
        }
        return ProductConvert.INSTANCE.convertCategoryList(categoryMapper.selectList(wrapper));
    }

}
