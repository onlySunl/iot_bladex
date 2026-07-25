package org.springblade.modules.iot.controller.admin.device;

import cn.hutool.core.util.ObjectUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceInfoRespVO;
import org.springblade.modules.iot.controller.admin.device.vo.*;
import org.springblade.modules.iot.controller.admin.device.vo.deviceconfig.DeviceConfigAddBo;
import org.springblade.modules.iot.controller.admin.device.vo.deviceconfig.DeviceConfigVo;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.*;
import org.springblade.modules.iot.controller.admin.iot.vo.DeviceIdReqVo;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelMessageBo;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.device.IDeviceCtrlService;
import org.springblade.modules.iot.service.device.IDeviceInfoService;
import org.springblade.modules.iot.service.device.IDeviceManagerService;
import org.springblade.modules.iot.service.sip.ISipRelationService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;



import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;



@Tag(name = "管理后台 - 设备信息")
@RestController
@RequestMapping("/eiot/device")
@Validated
public class DeviceInfoController extends BladeController {

    @Resource
    private IDeviceInfoService deviceInfoService;

    @Resource
    private IDeviceManagerService deviceManagerService;

    @Resource
    private ISipRelationService sipRelationService;

    @Resource
    private IDeviceCtrlService deviceCtrlService;

    @PostMapping("/create")
    @Operation(summary = "创建设备信息")
    public R<Long> createDeviceInfo(@Valid @RequestBody DeviceInfoSaveReqVO createReqVO) {
        return data(deviceInfoService.createDeviceInfo(createReqVO));
    }

    /**
     * 导入设备-批量添加设备
     */
    @Operation(summary = "导入设备")
    @PostMapping("/importData")
    public R<DeviceImportRespVO> importDevice(@RequestPart("file") MultipartFile file, @RequestParam("productId") Long productId) throws IOException {
        List<DeviceInfoImportVo> list = ExcelUtils.read(file, DeviceInfoImportVo.class);

        return data(deviceInfoService.importDevice(list, productId));
    }

    /**
     * 获取导入设备模板
     */
    @Operation(summary ="下载设备模板")
    @GetMapping("/exportData")
    public void exportDeviceTemplate(HttpServletResponse response) throws IOException {
        List<DeviceInfoImportVo> list = Arrays.asList(
                DeviceInfoImportVo.builder().name("测试").dn("00:00:00:00:00").serialNo("1111").build()
        );
        ExcelUtils.write(response, "设备模板.xls","设备", DeviceInfoImportVo.class ,list);
    }


    @PutMapping("/update")
    @Operation(summary = "更新设备信息")
    public R<Boolean> updateDeviceInfo(@Valid @RequestBody DeviceInfoSaveReqVO updateReqVO) {
        deviceInfoService.updateDeviceInfo(updateReqVO);
        return data(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备信息")
    @Parameter(name = "id", description = "编号", required = true)
    public R<Boolean> deleteDeviceInfo(@RequestParam("id") Long id) {
        deviceInfoService.deleteDeviceInfo(id);
        return data(true);
    }

    @DeleteMapping("/deleteBatch")
    @Operation(summary = "删除设备信息")
    @Parameter(name = "ids", description = "编号", required = true)
    public R<Boolean> deleteByIds(@RequestParam("ids") List<Long> ids) {
        deviceInfoService.deleteByIds(ids);
        return data(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public R<DeviceInfoRespVO> getDeviceInfo(@RequestParam("id") Long id) {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(id);
        return data(BeanUtils.toBean(deviceInfo, DeviceInfoRespVO.class));
    }

    @Operation(summary = "获得设备信息")
    @GetMapping(value = "/getDeviceBySerialNumber/{serialNumber}")
    public R<DeviceInfoRespVO> getDeviceBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
        DeviceInfo deviceInfo = deviceInfoService.getDeviceBySerialNo(serialNumber);
        DeviceInfoRespVO ret = BeanUtil.copy(deviceInfo, DeviceInfoRespVO.class);

        if (ObjectUtil.isNotNull(deviceInfo)){
            //查询关联的监控设备
            SipRelation sipRelation = new SipRelation();
            sipRelation.setReDeviceId(deviceInfo.getId());
            List<SipRelation> sipRelationList = sipRelationService.selectSipRelationList(sipRelation);
            ret.setSipRelationList(sipRelationList);
        }

        return data(ret);
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备信息分页")
    public R<PageResult<DeviceShortRespVO>> getDeviceInfoPage(@Valid DeviceInfoPageReqVO pageReqVO) {
        PageResult<DeviceShortInfo> pageResult = deviceInfoService.getDeviceInfoPage(pageReqVO);
        PageResult<DeviceShortRespVO> result = BeanUtils.toBean(pageResult, DeviceShortRespVO.class);
        // 填充设备所属分组名称
        deviceManagerService.fillGroupNames(result.getList());
        return data(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备信息 Excel")
    public void exportDeviceInfoExcel(@Valid DeviceInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceShortInfo> list = deviceInfoService.getDeviceInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备信息.xls", "数据", DeviceInfoRespVO.class,
                BeanUtils.toBean(list, DeviceInfoRespVO.class));
    }

    @Operation(summary = "子设备列表")
    @PostMapping("/children/list")
    @Parameter(name = "nodeType", description = "设备类型", required = true, example = "1")
    public R<PageResult<DeviceShortRespVO>> getChildrenPage(@RequestBody @Valid DeviceInfoPageReqVO pageReqVO) {
        PageResult<DeviceShortInfo> pageResult = deviceInfoService.getDeviceInfoPage(pageReqVO);
        return data(BeanUtils.toBean(pageResult, DeviceShortRespVO.class));
    }

    @Operation(summary = "未绑定的子设备列表")
    @PostMapping("/children/unbindList")
    @Parameter(name = "nodeType", description = "设备类型", required = true, example = "1")
    public R<PageResult<DeviceShortRespVO>> getUnbindPage(@RequestBody @Valid DeviceUnbindPageReqVO pageReqVO) {
        PageResult<DeviceShortInfo> pageResult = deviceInfoService.getUnbindPage(pageReqVO);
        return data(BeanUtils.toBean(pageResult, DeviceShortRespVO.class));
    }

    @Operation(summary = "子设备解绑")
    @PostMapping("/bind")
    @Parameter(name = "bind", description = "设备类型", required = true, example = "1")
    public R<Void> bind(@RequestBody @Valid DeviceBindReqVO bindReqVO) {
        deviceCtrlService.bindDevice(bindReqVO.getIdList(), bindReqVO.getParentId());
        deviceInfoService.bindParent(bindReqVO);
        return success("操作成功");
    }

    @Operation(summary = "子设备解绑")
    @PostMapping("/unbind")
    @Parameter(name = "unbind", description = "设备类型", required = true, example = "1")
    public R<Void> unbind(@RequestBody @Valid DeviceUnbindReqVO unbindReqVO) {
        deviceCtrlService.unbindDevice(unbindReqVO.getIdList());
        deviceInfoService.unbindParent(unbindReqVO);
        return success("操作成功");
    }

    @Operation(summary = "设备物模型日志")
    @PostMapping("/deviceLogs/list")
    public R<PageResult<ThingModelMessage>> logs(@Validated @RequestBody DeviceLogPageReqVo request) {
        return data( deviceManagerService.logs(request));
    }

    @Operation(summary = "获取设备属性历史数据")
    @PostMapping("/deviceProperty/log/list")
    public R<List<DeviceProperty>> getPropertyHistory(@Validated @RequestBody
                                                                 DevicePropertyLogQueryBo data) {
        Long deviceId = data.getDeviceId();
        String name = data.getName();
        long start = data.getStart();
        long end = data.getEnd();
        return data(deviceManagerService.getPropertyHistory(deviceId, name, start, end, data.getPageNo(), data.getPageSize()));
    }

    @Operation(summary = "添加标签")
    @PostMapping("/tag/add")
    public R<Boolean>  addTag(@Validated @RequestBody DeviceTagAddBo bo) {
        return data( deviceManagerService.addTag(bo));
    }

    @Operation(summary = "模拟设备上报")
    @PostMapping("/simulateSend")
    public R<Boolean>  simulateSend(
            @Validated @RequestBody ThingModelMessageBo bo) {
        ThingModelMessage message = BeanUtils.toBean(bo, ThingModelMessage.class);
        return data( deviceManagerService.simulateSend(message));
    }

    /**
     * 消费设备信息消息（实时推送设备信息）
     */
//    @Operation(summary = "消费设备信息消息（实时推送设备信息）")
//
//    @PostMapping("/consumer")
//    public R< DeferredResult<ThingModelMessage> consumerDeviceInfo(
//            @Validated @RequestBody DeviceConsumerBo> bo
//    ) {
//        DeviceConsumerBo data = bo.getData();
//        return data( deviceManagerService.addConsumer(data.getDeviceId(), data.getClientId());
//    }

    /**
     * 获取分组列表
     */
    @Operation(summary = "获取分组列表")
    @PostMapping("/groups/list")
    public R< PageResult<DeviceGroup>> getDeviceGroups(
            @Validated @RequestBody DeviceGroupPageReqVO pageRequest) {
        return data( deviceManagerService.selectGroupPageList(pageRequest));
    }

    /**
     * 添加设备分组
     */
    @Operation(summary = "添加设备分组")
    @PostMapping("/group/add")
    public R<Boolean>  addGroup(@Validated @RequestBody DeviceGroupBo group) {
        return data( deviceManagerService.addGroup(group));
    }


    /**
     * 导入设备分组-批量添加设备分组
     */
    @Operation(summary = "导入设备分组")
    @PostMapping("/group/importData")
    public R<GroupImportRespVO>  importGroup(@RequestPart("file") MultipartFile file, @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws IOException {
        List<DeviceGroupImportVo> list = ExcelUtils.read(file, DeviceGroupImportVo.class);
        return data( deviceManagerService.importGroup(list, updateSupport));
    }

    /**
     * 获取导入模板
     */
    @Operation(summary = "下载设备分组模板")
    @GetMapping("/group/exportData")
    public  void exportGroupTemplate(HttpServletResponse response) throws IOException {
        List<DeviceGroupImportVo> list = Arrays.asList(
                DeviceGroupImportVo.builder().name("测试分组").groupOrder(99).remark("测试分组").build()
        );
        ExcelUtils.write(response, "设备分组模板.xls","设备分组", DeviceGroupImportVo.class ,list);
    }

    /**
     * 修改设备分组
     */
    @Operation(summary = "修改设备分组")
    @PostMapping("/group/edit")
    public R<Boolean> editGroup(@RequestBody @Validated DeviceGroupBo bo) {
        return data( deviceManagerService.updateGroup(bo));

    }

    /**
     * 删除分组
     */
    @Operation(summary = "删除分组")
    @PostMapping("/group/delete")
    public R<Boolean> deleteGroup(@Validated @RequestBody IdReqVo request) {

        return data( deviceManagerService.deleteGroup(request.getId()));
    }

    /**
     * 清空组下所有设备
     */
    @Operation(summary = "清空组下所有设备")
    @PostMapping("/group/clear")
    public R<Boolean> clearGroup(@Validated @RequestBody IdReqVo req) {
        return data( deviceManagerService.clearGroup(req.getId()));
    }

    /**
     * 查询分组内设备列表
     */
    @Operation(summary = "查询分组内设备列表")
    @PostMapping("/group/deviceList")
    public R<PageResult<DeviceShortRespVO>> getGroupDevices(@Validated @RequestBody DeviceGroupPageReqVO req) {
        PageResult<DeviceShortInfo> pageResult = deviceManagerService.selectGroupDevicePageList(req);
        return data(BeanUtils.toBean(pageResult, DeviceShortRespVO.class));
    }

    /**
     * 查询可添加到分组的设备列表（排除已分组的设备）
     */
    @Operation(summary = "查询可添加到分组的设备列表")
    @PostMapping("/group/availableDevices")
    public R<PageResult<DeviceShortRespVO>> getAvailableDevices(@Validated @RequestBody DeviceGroupPageReqVO req) {
        PageResult<DeviceShortInfo> pageResult = deviceManagerService.selectAvailableDevicePageList(req);
        return data(BeanUtils.toBean(pageResult, DeviceShortRespVO.class));
    }

    /**
     * 添加设备到组
     */
    @Operation(summary = "添加设备到组")
    @PostMapping("/group/addDevices")
    public R<Boolean> addToGroup(@Validated @RequestBody DeviceAddGroupBo bo) {
        return data( deviceManagerService.addDevice2Group(bo));
    }

    /**
     * 将设备从组中移除
     */
    @Operation(summary = "将设备从组中移除")
    @PostMapping("/group/removeDevices")
    public R<Boolean> removeDevices(@Validated @RequestBody DeviceAddGroupBo bo) {

        return data( deviceManagerService.removeDevicesInGroup(bo.getGroupId(), bo.getDeviceIds()));
    }

    /**
     * 保存设备配置
     */
    @Operation(summary = "保存设备配置")
    @PostMapping("/config/save")
    public R<Boolean> saveConfig(@Validated @RequestBody DeviceConfigAddBo request) {
        DeviceConfig data = BeanUtils.toBean(request, DeviceConfig.class);
        return data( deviceManagerService.saveConfig(data));
    }

    /**
     * 获取设备配置
     */
    @Operation(summary = "获取设备配置")
    @PostMapping("/config/get")
    public R<DeviceConfigVo> getConfig(@Validated @RequestBody DeviceIdReqVo request) {
        Long deviceId = request.getDeviceId();
        return data( deviceManagerService.getConfig(deviceId));
    }


    @Operation(summary = "查询指定设备的属性信息")
    @PostMapping("/getDeviceWithProperty")
    public R<DeviceInfoWithPropertyVO> getDeviceInfoWithProperty(@RequestBody @Validated DeviceIdReqVo bo) {
        return data(deviceManagerService.getDeviceInfoWithProperty(bo.getDeviceId()));
    }

    @Operation(summary = "获取序列号")
    @GetMapping("/genSerialNO")
    @Parameter(name = "nodeType", description = "设备类型", required = true, example = "1")
    public R<String> genSerialNO(Integer nodeType) {
        return data(deviceManagerService.genSerialNO(nodeType));
    }


}
