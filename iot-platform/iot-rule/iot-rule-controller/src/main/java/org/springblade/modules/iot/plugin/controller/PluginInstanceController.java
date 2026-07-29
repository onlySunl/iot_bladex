package org.springblade.modules.iot.plugin.controller;

import org.springblade.basic.annotation.log.WebLog;
import org.springblade.basic.base.R;
import org.springblade.basic.base.controller.SuperController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.entity.plugin.PluginInstance;
import org.springblade.modules.iot.service.plugin.PluginInstanceService;
import org.springblade.modules.iot.vo.query.plugin.PluginInstancePageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceResultVO;
import org.springblade.modules.iot.vo.result.plugin.PluginNacosInstanceResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInstanceUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 * @create [2024-08-27 16:02:17] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/pluginInstance")
@Tag(name = "插件实例信息")
public class PluginInstanceController extends SuperController<PluginInstanceService, Long, PluginInstance, PluginInstanceSaveVO,
        PluginInstanceUpdateVO, PluginInstancePageQuery, PluginInstanceResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Override
    public QueryWrap<PluginInstance> handlerWrapper(PluginInstance model, PageParams<PluginInstancePageQuery> params) {
        QueryWrap<PluginInstance> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("plugin_instance");
        return queryWrap;
    }


    /**
     * 新增 插件实例信息
     *
     * @param saveVO 保存参数
     * @return 插件实例实体
     */
    @Operation(summary = "保存插件实例信息", description = "保存插件实例信息")
    @PostMapping("/savePluginInstance")
    @WebLog(value = "保存插件实例信息", request = false)
    public R<PluginInstanceSaveVO> savePluginInstance(@RequestBody PluginInstanceSaveVO saveVO) {
        log.info("savePluginInstance saveVO:{}:", JsonUtil.toJson(saveVO));
        try {
            return R.success(superService.savePluginInstance(saveVO));
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("保存插件实例信息失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }

    /**
     * 修改 插件实例信息
     *
     * @param updateVO 更新参数
     * @return 插件实例更新结果
     */
    @Operation(summary = "修改插件实例信息", description = "修改插件实例信息")
    @PutMapping("/updatePluginInstance")
    @WebLog(value = "修改插件实例信息", request = false)
    public R<PluginInstanceUpdateVO> updatePluginInstance(@RequestBody PluginInstanceUpdateVO updateVO) {
        log.info("updatePluginInstance updateVO:{}:", JsonUtil.toJson(updateVO));
        try {
            return R.success(superService.updatePluginInstance(updateVO));
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("保修改插件实例信息失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }


    /**
     * 获取可用服务实例列表
     *
     * @return 可用的服务实例列表
     */
    @Operation(summary = "获取可用服务实例列表", description = "获取所有可用的服务实例信息")
    @GetMapping("/getAvailableInstances")
    public R<List<PluginNacosInstanceResultVO>> getAvailableInstances() {
        try {
            return R.success(superService.getAvailableInstances());
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("获取可用服务实例列表失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }

    /**
     * 删除插件实例
     *
     * @param id 插件实例ID
     * @return 删除结果
     */
    @Operation(summary = "删除插件实例", description = "根据插件实例ID删除插件实例")
    @Parameters({@Parameter(name = "id", description = "插件实例ID", required = true)})
    @DeleteMapping("/deletePluginInstance/{id}")
    @WebLog(value = "删除插件实例", request = false)
    public R<Boolean> deletePluginInstance(@PathVariable("id") Long id) {
        log.info("deletePluginInstance id:{}", id);
        try {
            return R.success(superService.deletePluginInstance(id));
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("删除插件实例失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }


    /**
     * 批量删除插件实例
     *
     * @param ids 插件实例ID列表
     * @return 删除结果
     */
    @Operation(summary = "批量删除插件实例", description = "根据插件实例ID列表批量删除插件实例")
    @Parameters({@Parameter(name = "ids", description = "插件实例ID列表", required = true)})
    @DeleteMapping("/deletePluginInstances")
    @WebLog(value = "批量删除插件实例", request = false)
    public R<Boolean> deletePluginInstances(@RequestBody List<Long> ids) {
        log.info("deletePluginInstances ids:{}", ids);
        try {
            boolean allDeleted = ids.stream().distinct().allMatch(id -> superService.deletePluginInstance(id));
            return R.success(allDeleted);
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("批量删除插件实例失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }

    }


}


