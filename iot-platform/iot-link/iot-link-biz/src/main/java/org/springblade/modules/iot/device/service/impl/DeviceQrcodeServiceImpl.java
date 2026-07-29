package org.springblade.modules.iot.device.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.jackson.JsonUtil;
import org.springblade.basic.utils.qrcode.QrcodeUtils;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.common.iot.constant.QrcodeConstant;
import org.springblade.modules.iot.device.service.DeviceQrcodeService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceQrcodeResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceQrcodeServiceImpl
 * -----------------------------------------------------------------------------
 * Description:
 * 设备二维码 实现
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/19       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/19 17:58
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceQrcodeServiceImpl implements DeviceQrcodeService {

    private final DeviceService deviceService;

    /**
     * 根据设备唯一标识生成带有Logo的二维码
     *
     * @param deviceIdentification 设备唯一标识
     * @return 生成的二维码字节数组
     * @throws Exception 如果生成过程中发生错误
     */
    @Override
    public byte[] generateQrcode(String deviceIdentification) throws Exception {
        log.info("开始生成设备 [{}] 的二维码", deviceIdentification);

        // 定义Logo文件路径，这里假设Logo存储在resources目录下的images文件夹中
        try (InputStream logoStream = getClass().getClassLoader().getResourceAsStream(QrcodeConstant.LOGO_RESOURCE_LOCATION)) {
            if (logoStream == null) {
                log.error("Logo文件未找到，请确保logo.png存储在resources/images目录下");
                throw new FileNotFoundException("Logo文件未找到，请确保logo.png存储在resources/images目录下");
            }
            DeviceDetailsResultVO deviceDetailsResultVO = deviceService.findOneByDeviceIdentification(deviceIdentification);

            DeviceQrcodeResultVO deviceQrcodeResultVO = new DeviceQrcodeResultVO()
                    .setScene(QrcodeConstant.DEVICE_SCENE)
                    .setDeviceIdentification(deviceDetailsResultVO.getDeviceIdentification())
                    .setProductIdentification(deviceDetailsResultVO.getProductIdentification())
                    .setTenantId(ContextUtil.getTenantIdStr());

            // 生成二维码字节数组
            return QrcodeUtils.createQrcode(JsonUtil.toJson(deviceQrcodeResultVO), QrcodeConstant.QR_CODE_LENGTH, logoStream);

        } catch (FileNotFoundException e) {
            log.error("生成设备 [{}] 的二维码时发生文件未找到错误: {}", deviceIdentification, e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error("读取Logo文件时发生I/O错误: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("生成二维码时发生错误: {}", e.getMessage(), e);
            throw e;
        }
    }

}
