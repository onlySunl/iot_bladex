package org.springblade.modules.iot.product.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.result.ProductModelJsonResultVO;
import org.springblade.modules.iot.productcommand.vo.param.ProductCommandParamVO;
import org.springblade.modules.iot.productcommand.vo.result.ProductModelCommandJsonResultVO;
import org.springblade.modules.iot.productproperty.vo.param.ProductPropertyParamVO;
import org.springblade.modules.iot.productproperty.vo.result.ProductModelPropertyJsonResultVO;
import org.springblade.modules.iot.productservice.vo.param.ProductServiceParamVO;
import org.springblade.modules.iot.productservice.vo.result.ProductModelServiceJsonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 产品模型转换器
 * <p>用于将产品模型的各种VO进行相互转换</p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026-01-21
 */
@Slf4j
public class ProductModelConverter {

    /**
     * 将ProductParamVO转换为ProductModelJsonResultVO
     *
     * @param productParamVO 产品参数VO
     * @return {@link ProductModelJsonResultVO} 产品模型JSON结果VO
     */
    public static ProductModelJsonResultVO toProductModelJsonResultVO(ProductParamVO productParamVO) {
        if (Objects.isNull(productParamVO)) {
            throw new ServiceException("productParamVO Cannot be null");
        }
        ProductModelJsonResultVO resultVO = new ProductModelJsonResultVO(); BeanUtils.copyProperties(productParamVO, resultVO);
        resultVO.setServices(convertServices(productParamVO.getServices()));
        return resultVO;
    }

    /**
     * 转换服务列表
     *
     * @param serviceParamVOList 服务参数VO列表
     * @return {@link List<ProductModelServiceJsonResultVO>} 服务JSON结果VO列表
     */
    private static List<ProductModelServiceJsonResultVO> convertServices(List<ProductServiceParamVO> serviceParamVOList) {
        return Optional.ofNullable(serviceParamVOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(ProductModelConverter::convertService)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 转换单个服务
     * <p>使用BeanPlusUtil自动复制基本字段，手动处理嵌套的commands和properties列表转换</p>
     *
     * @param serviceParamVO 服务参数VO
     * @return {@link ProductModelServiceJsonResultVO} 服务JSON结果VO
     */
    private static ProductModelServiceJsonResultVO convertService(ProductServiceParamVO serviceParamVO) {
        if (Objects.isNull(serviceParamVO)) {
            return null;
        }
        try {
            ProductModelServiceJsonResultVO serviceResultVO = new ProductModelServiceJsonResultVO(); BeanUtils.copyProperties(serviceParamVO, serviceResultVO);
            // 手动转换嵌套的命令列表
            serviceResultVO.setCommands(convertCommands(serviceParamVO.getCommands()));
            // 手动转换嵌套的属性列表
            serviceResultVO.setProperties(convertProperties(serviceParamVO.getProperties()));
            return serviceResultVO;
        } catch (Exception e) {
            log.error("转换服务失败 - 服务编码: {}, 错误: {}", serviceParamVO.getServiceCode(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * 转换命令列表
     *
     * @param commandParamVOList 命令参数VO列表
     * @return {@link List<ProductModelCommandJsonResultVO>} 命令JSON结果VO列表
     */
    private static List<ProductModelCommandJsonResultVO> convertCommands(List<ProductCommandParamVO> commandParamVOList) {
        return Optional.ofNullable(commandParamVOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(command -> { ProductModelCommandJsonResultVO vo = new ProductModelCommandJsonResultVO(); BeanUtils.copyProperties(command, vo); return vo; })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 转换属性列表
     *
     * @param propertyParamVOList 属性参数VO列表
     * @return {@link List<ProductModelPropertyJsonResultVO>} 属性JSON结果VO列表
     */
    private static List<ProductModelPropertyJsonResultVO> convertProperties(List<ProductPropertyParamVO> propertyParamVOList) {
        return Optional.ofNullable(propertyParamVOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(property -> { ProductModelPropertyJsonResultVO vo = new ProductModelPropertyJsonResultVO(); BeanUtils.copyProperties(property, vo); return vo; })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}