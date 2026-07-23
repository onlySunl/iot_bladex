package org.springblade.modules.iot.controller.admin.modbus;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.api.modbus.dto.ModbusInfo;
import org.springblade.modules.iot.api.modbus.dto.ModbusThingModel;
import org.springblade.modules.iot.common.annotation.ApiAccessLog;
import org.springblade.modules.iot.common.constant.ErrorCode;
import org.springblade.modules.iot.common.constant.GlobalErrorCodeConstants;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusInfoRespVo;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusInfoVo;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusThingModelImportVo;
import org.springblade.modules.iot.controller.admin.modbus.vo.ModbusThingModelVo;
import org.springblade.modules.iot.excel.core.util.ExcelUtils;
import org.springblade.modules.iot.service.modbus.ModbusInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springblade.modules.iot.common.enums.OperateTypeEnum.IMPORT;
import static org.springblade.modules.iot.common.utils.ServiceExceptionUtil.exception;


@Tag(name = "管理后台 - Modbus管理")
@Slf4j
@RestController
@RequestMapping("/eiot/modbus")
@Validated
public class ModbusController {

    @Resource
    private ModbusInfoService modbusInfoService;


    @Operation(summary = "ModbusInfo模版列表")
    @PostMapping("/list")
    public CommonResult<PageResult<ModbusInfoRespVo>> getDevices(@RequestBody ModbusInfoVo data) {
        PageResult<ModbusInfo> result = modbusInfoService.selectPageList(data);
        return CommonResult.success(BeanUtils.toBean(result, ModbusInfoRespVo.class));
    }


    @Operation(summary = "新建ModbusInfo")
    @PostMapping("/add")
    public CommonResult<ModbusInfoRespVo> create(@RequestBody ModbusInfoVo data) {
        ModbusInfo modbus = modbusInfoService.createModbus(data);
        return CommonResult.success(BeanUtils.toBean(modbus, ModbusInfoRespVo.class));
    }

    @Operation(summary = "编辑ModbusInfo")
    @PostMapping("/edit")
    public CommonResult<Boolean> edit(@RequestBody ModbusInfoVo data) {
        modbusInfoService.updateModbus(data);
        return CommonResult.success(true);
    }

    @Operation(summary = "查看ModbusInfo详情")
    @PostMapping("/getDetail")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ModbusInfoRespVo> getDetail(@RequestParam("id") Long id) {
        ModbusInfo modbus = modbusInfoService.getModbus(id);
        return CommonResult.success(BeanUtils.toBean(modbus, ModbusInfoRespVo.class));
    }

    @Operation(summary = "删除ModbusInfo")
    @PostMapping("/deleteModbus")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        modbusInfoService.deleteModbus(id);
        return CommonResult.success(true);
    }

    /**
     * 导入点位模型
     */
    @SneakyThrows
    @Operation(summary = "导入点位模型")
    @ApiAccessLog(operateType = IMPORT)
    @PostMapping("/importData")
    @Parameter(name = "productKey", description = "productKey", required = true, example = "1024")
    public CommonResult<String> importData(@RequestPart("file") MultipartFile file, String productKey) {
        if(StrUtil.isBlank(productKey)){
           throw exception(GlobalErrorCodeConstants.BAD_REQUEST, "缺少productKey");
        }
        List<ModbusThingModelImportVo> objects = ExcelUtils.read(file, ModbusThingModelImportVo.class);
        return CommonResult.success(modbusInfoService.importData(objects,productKey));
    }

    @SneakyThrows
    @Operation(summary = "下载点位模版")
    @GetMapping("/exportData")
    public void exportDeviceTemplate(HttpServletResponse response) {
        ExcelUtils.write(response, "点位模版.xls","点位", ModbusThingModelImportVo.class ,null);
    }


    @Operation(summary = "查看点位物模型")
    @PostMapping("/getThingModelByProductKey")
    @Parameter(name = "pk", description = "productKey", required = true, example = "1024")
    public CommonResult<ModbusThingModel> getThingModelByProductKey(@RequestParam("pk") String productKey) {
        return CommonResult.success(modbusInfoService.getThingModelByProductKey(productKey));
    }

    @Operation(summary = "保存点位物模型")
    @PostMapping("/thingModel/save")
    public CommonResult<Boolean> saveThingModel(@RequestBody ModbusThingModelVo modbusThingModelVo) {
        modbusInfoService.saveThingModel(modbusThingModelVo);
        return CommonResult.success(true);
    }


    @Operation(summary = "同步点位物模型到产品")
    @PostMapping("/syncToProduct")
    public CommonResult<Boolean> syncToProduct(@RequestBody ModbusThingModelVo modbusThingModelVo) {
        modbusInfoService.syncToProduct(modbusThingModelVo);
        return CommonResult.success(true);
    }



}
