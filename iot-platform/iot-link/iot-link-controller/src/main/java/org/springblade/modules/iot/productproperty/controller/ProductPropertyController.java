package org.springblade.modules.iot.productproperty.controller;

import java.util.concurrent.TimeUnit;

import org.springblade.core.tool.api.R;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.common.lock.link.LinkLockKeyBuilder;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.service.ProductPropertyService;
import org.springblade.modules.iot.productproperty.vo.query.ProductPropertyPageQuery;
import org.springblade.modules.iot.productproperty.vo.result.ProductPropertyResultVO;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.modules.iot.productproperty.vo.update.ProductPropertyUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 产品模型服务属性表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/productProperty")
@Tag(name = "产品模型服务属性")
public class ProductPropertyController extends BladeController<ProductPropertyService, Long, ProductProperty, ProductPropertySaveVO,
        ProductPropertyUpdateVO, ProductPropertyPageQuery, ProductPropertyResultVO> {
    private final EchoService echoService;
    private final DistributedLock distributedLock;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<ProductProperty> handlerWrapper(ProductProperty model, Query params) {
        QueryWrap<ProductProperty> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("product_property");
        return queryWrap;
    }

    /**
     * 新增 产品模型服务属性信息表
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存产品模型服务属性")
    @PostMapping("/saveProductProperty")
    public R<ProductProperty> saveProductProperty(@Valid @RequestBody ProductPropertySaveVO saveVO) {
        try {
            CacheKey lockCacheKey = LinkLockKeyBuilder.forSaveProductPropertyByUserId(AuthUtil.getUserId());
            LockRunResult<ProductProperty> lockRunResult = distributedLock.tryLockAndRun(
                    lockCacheKey.getKey(),
                    lockCacheKey.getExpire().getSeconds(),
                    TimeUnit.SECONDS,
                    () -> superService.saveProductProperty(saveVO));
            if (!lockRunResult.isLocked()) {
                return R.fail(R.LOCK_ACQUIRE_ERROR_MESSAGE);
            }
            return R.success(lockRunResult.getResult());
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("产品模型服务属性保存失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }

    /**
     * 修改 产品模型服务属性信息表
     *
     * @param updateVO 更新参数
     * @return 实体
     */
    @Operation(summary = "修改产品模型服务属性")
    @PutMapping("/updateProductProperty")
    public R<ProductProperty> updateProductProperty(@Valid @RequestBody ProductPropertyUpdateVO updateVO) {
        try {
            CacheKey lockCacheKey = LinkLockKeyBuilder.forUpdateProductPropertyByUserId(AuthUtil.getUserId());
            LockRunResult<ProductProperty> lockRunResult = distributedLock.tryLockAndRun(
                    lockCacheKey.getKey(),
                    lockCacheKey.getExpire().getSeconds(),
                    TimeUnit.SECONDS,
                    () -> superService.updateProductProperty(updateVO));
            if (!lockRunResult.isLocked()) {
                return R.fail(R.LOCK_ACQUIRE_ERROR_MESSAGE);
            }
            return R.success(lockRunResult.getResult());
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("修改产品模型服务属性失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }

    /**
     * 删除产品模型服务属性
     *
     * @param id 产品模型服务属性ID
     * @return 删除结果
     */
    @Operation(summary = "删除产品模型服务属性", description = "根据产品模型服务属性ID删除产品模型服务属性")
    @Parameters({
            @Parameter(name = "id", description = "产品模型服务属性ID", required = true, example = "1"),
    })
    @DeleteMapping("/deleteProductProperty/{id}")
    public R<Boolean> deleteProductProperty(@PathVariable("id") Long id) {
        log.info("deleteProductProperty id:{}", id);
        return R.success(superService.deleteProductProperty(id));
    }

}