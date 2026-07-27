package org.springblade.modules.iot.link.facade;

import cn.hutool.core.util.StrUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.JsonUtil;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author tangyh
 * @since 2024/12/24 17:02
 */
@Service
@Slf4j
public class DeviceFacadeImpl implements DeviceFacade {
    @Autowired
    private DeviceService deviceService;

    @Override
    public R<Boolean> updateDeviceConnectionStatus(Long id, Integer connectionStatus) {
        try {
            return R.success(deviceService.updateDeviceConnectionStatusById(id, connectionStatus));
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("修改设备连接状态失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }

    @Override
    public R<List<DeviceDetailsResultVO>> getDeviceDetailsByIdentifications(List<String> deviceIdentifications) {
        log.info("getDeviceDetailsByIdentifications for deviceIdentifications: {}", JsonUtil.toJson(deviceIdentifications));
        try {
            List<DeviceDetailsResultVO> deviceDetailsList = deviceIdentifications.stream().filter(StrUtil::isNotBlank).distinct().map(id -> {
                try {
                    return Optional.ofNullable(deviceService.findOneByDeviceIdentification(id));
                } catch (Exception e) {
                    log.error("Error retrieving device details for identification: {}", id, e);
                    return Optional.<DeviceDetailsResultVO>empty();
                }
            }).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

            return R.success(deviceDetailsList);
        } catch (BizException be) {
            return R.fail(be);
        } catch (Exception e) {
            log.error("根据多个设备标识获取设备详情失败，系统异常: {}", e.getMessage(), e);
            return R.fail();
        }
    }
}
