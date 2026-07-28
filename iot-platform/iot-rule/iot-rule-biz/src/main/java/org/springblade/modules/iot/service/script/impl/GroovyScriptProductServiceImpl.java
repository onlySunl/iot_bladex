package org.springblade.modules.iot.service.script.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.dto.script.ProductInfoDTO;
import org.springblade.modules.iot.service.script.GroovyScriptProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ============================================================================
 * Description:
 * <p>
 * ============================================================================
 *
 * @author Sun Shihuan
 * @version 1.0.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2025/3/30      Sun Shihuan        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2025/3/30 11:35
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class GroovyScriptProductServiceImpl implements GroovyScriptProductService {

    @Override
    public ProductInfoDTO getProductById(Integer id) {
        // 直接创建一个商品
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        productInfoDTO.setId(id);
        productInfoDTO.setName(UUID.randomUUID().toString());
        return productInfoDTO;
    }


    @Override
    public void updateProduct(ProductInfoDTO productInfo) {
        // 修改商品名称
        productInfo.setName("update-" + productInfo.getName());
    }


}
