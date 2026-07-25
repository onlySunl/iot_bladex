package org.springblade.modules.iot.dal.mysql.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.controller.admin.product.vo.ProductPageReqVO;
import org.springblade.modules.iot.entity.ProductDO;

/**
 * 物联网产品 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ProductMapper extends BladeMapper<ProductDO> {

    IPage<ProductDO> selectPage(IPage<ProductDO> page, @Param("reqVO") ProductPageReqVO reqVO);

    ProductDO getByProductKey(@Param("productKey") String productKey);

    Long selectCountByCategoryId(@Param("categoryId") Long categoryId);
}
