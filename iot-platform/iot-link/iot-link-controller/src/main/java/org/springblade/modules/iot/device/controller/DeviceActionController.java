package org.springblade.modules.iot.device.controller;

import org.springblade.common.annotation.log.WebLog;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.service.DeviceActionService;
import org.springblade.modules.iot.device.vo.query.DeviceActionPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceActionResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceActionUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 设备动作数据
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 * @create [2023-06-10 16:38:09] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceAction")
@Tag(name = "设备动作数据")
public class DeviceActionController extends BaseController<DeviceActionService, Long, DeviceAction, DeviceActionSaveVO,
        DeviceActionUpdateVO, DeviceActionPageQuery, DeviceActionResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 根据设备唯一标识断开设备连接
     *
     * @param deviceIdentification 设备唯一标识
     * @return 操作结果
     */
    @Operation(summary = "断开设备连接", description = "根据设备唯一标识断开设备连接")
    @DeleteMapping("/disconnect/{deviceIdentification}")
    @Parameters({
            @Parameter(name = "deviceIdentification", description = "设备唯一标识", required = true)
    })
    @WebLog(value = "断开设备连接")
    public R<?> disconnect(@PathVariable("deviceIdentification") String deviceIdentification) {
        log.info("disconnect deviceIdentification:{}", deviceIdentification);
        try {
            boolean result = superService.disconnectDevice(deviceIdentification);
            return result ? R.success() : R.fail("断开连接失败");
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("断开设备连接失败，设备标识[{}]，异常信息: {}", deviceIdentification, e.getMessage(), e);
            return R.fail("系统异常，断开连接失败");
        }
    }
}


