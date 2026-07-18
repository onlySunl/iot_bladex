

package org.springblade.modules.iot.convert;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.category.vo.Category;
import org.springblade.modules.iot.entity.CategoryDO;
import org.springblade.modules.iot.entity.ProductDO;

import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */


@Mapper(builder = @Builder(disableBuilder = true))
public interface ProductConvert {
    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    Product convert(ProductDO channelConfigDO);

    PageResult<Product> convertPage(PageResult<ProductDO> selectPage);


    Category convertCategory(CategoryDO selectById);

    List<Category> convertCategoryList(List<CategoryDO> selectList);
}
