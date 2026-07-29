package org.springblade.modules.iot.productproperty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.base.R;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.basic.model.cache.CacheKey;
import org.springblade.common.iot.lock.link.LinkLockKeyBuilder;
import org.springblade.core.annotation.log.WebLog;
import org.springblade.core.cache.lock.DistributedLock;
import org.springblade.core.cache.lock.LockRunResult;
import org.springblade.core.database.mybatis.conditions.query.QueryWrap;
import org.springblade.core.mvc.controller.SuperController;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.service.ProductPropertyService;
import org.springblade.modules.iot.productproperty.vo.query.ProductPropertyPageQuery;
import org.springblade.modules.iot.productproperty.vo.result.ProductPropertyResultVO;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.modules.iot.productproperty.vo.update.ProductPropertyUpdateVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/productProperty")
@Tag(name = "产品模型服务属性")
public class ProductPropertyController extends SuperController<ProductPropertyService, Long, ProductProperty, ProductPropertySaveVO,
        ProductPropertyUpdateVO, ProductPropertyPageQuery, ProductPropertyResultVO> {
    private final EchoService echoService;
    private final DistributedLock distributedLock;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    /**
     * 新增 产品模型服务属性信息表
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存产品模型服务属性")
    @PostMapping("/saveProductProperty")
    @WebLog(value = "保存产品模型服务属性", request = false)
    public R<ProductProperty> saveProductProperty(@Valid @RequestBody ProductPropertySaveVO saveVO) {
        try {
            CacheKey lockCacheKey = LinkLockKeyBuilder.forSaveProductPropertyByUserId(ContextUtil.getUserId());
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
    @WebLog(value = "修改产品模型服务属性", request = false)
    public R<ProductProperty> updateProductProperty(@Valid @RequestBody ProductPropertyUpdateVO updateVO) {
        try {
            CacheKey lockCacheKey = LinkLockKeyBuilder.forUpdateProductPropertyByUserId(ContextUtil.getUserId());
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
    @WebLog(value = "删除产品模型服务属性", request = false)
    public R<Boolean> deleteProductProperty(@PathVariable("id") Long id) {
        log.info("deleteProductProperty id:{}", id);
        return R.success(superService.deleteProductProperty(id));
    }

}