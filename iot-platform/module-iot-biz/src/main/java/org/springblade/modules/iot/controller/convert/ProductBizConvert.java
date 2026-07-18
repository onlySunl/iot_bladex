

package org.springblade.modules.iot.controller.convert;


import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.controller.admin.product.vo.ProductConfigBo;
import org.springblade.modules.iot.controller.admin.product.vo.ProductConfigVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */
@Mapper
public interface ProductBizConvert {
    ProductBizConvert INSTANCE = Mappers.getMapper(ProductBizConvert.class);
    
    ProductConfigVo convertVO(ProductConfig configByPk);

    ProductConfig convert(ProductConfigBo request);
}
