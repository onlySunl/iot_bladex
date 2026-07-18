

package org.springblade.modules.iot.dal.mysql.product;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.controller.admin.product.vo.ProductPageReqVO;
import org.springblade.modules.iot.entity.ProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物联网产品 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ProductMapper extends BaseMapperX<ProductDO> {

    default PageResult<ProductDO> selectPage(ProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductDO>()
                .likeIfPresent(ProductDO::getName, reqVO.getName())
                .eqIfPresent(ProductDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ProductDO::getProductKey, reqVO.getProductKey())
                .eqIfPresent(ProductDO::getMcuCode, reqVO.getMcuCode())
                .eqIfPresent(ProductDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ProductDO::getNodeType, reqVO.getNodeType())
                .eqIfPresent(ProductDO::getProtocolCode, reqVO.getProtocolCode())
                .eqIfPresent(ProductDO::getTransparent, reqVO.getTransparent())
                .orderByDesc(ProductDO::getId));
    }

    default ProductDO getByProductKey(String productKey){
        return selectOne(ProductDO::getProductKey, productKey);
    }

    default Long selectCountByCategoryId(Long categoryId){
        return selectCount(ProductDO::getCategoryId, categoryId);
    };
}
