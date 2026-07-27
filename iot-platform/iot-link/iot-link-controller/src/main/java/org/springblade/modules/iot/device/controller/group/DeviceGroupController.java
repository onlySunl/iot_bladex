package org.springblade.modules.iot.device.controller.group;

import java.util.List;

import org.springblade.common.annotation.log.WebLog;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseController;
import org.springblade.common.base.request.PageParams;
import org.springblade.common.database.mybatis.conditions.query.QueryWrap;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.device.entity.group.DeviceGroup;
import org.springblade.modules.iot.device.service.group.DeviceGroupService;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupPageQuery;
import org.springblade.modules.iot.device.vo.result.group.DeviceGroupResultVO;
import org.springblade.modules.iot.device.vo.save.group.DeviceGroupSaveVO;
import org.springblade.modules.iot.device.vo.update.group.DeviceGroupUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 * @create [2025-06-19 18:05:14] [mqttsnet] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceGroup")
@Tag(name = "设备分组")
public class DeviceGroupController extends BaseController<DeviceGroupService, Long, DeviceGroup
        , DeviceGroupSaveVO, DeviceGroupUpdateVO, DeviceGroupPageQuery, DeviceGroupResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<DeviceGroup> handlerWrapper(DeviceGroup model, PageParams<DeviceGroupPageQuery> params) {
        QueryWrap<DeviceGroup> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("device_group");
        return queryWrap;
    }

    /**
     * 按树结构查询
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询", description = "按树结构查询")
    @PostMapping("/tree")
    @WebLog("级联查询")
    public R<List<DeviceGroupResultVO>> tree(@RequestBody DeviceGroupPageQuery pageQuery) {
        List<DeviceGroupResultVO> result = superService.findTree(pageQuery);
        echoService.action(result);
        return success(result);
    }
}


