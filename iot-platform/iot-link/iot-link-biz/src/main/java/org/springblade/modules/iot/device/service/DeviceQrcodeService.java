package org.springblade.modules.iot.device.service;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceQrcodeService
 * -----------------------------------------------------------------------------
 * Description:
 * 设备二维码 service
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
 * @date 2024/8/19 17:57
 */
public interface DeviceQrcodeService {

    /**
     * 根据设备唯一标识生成带有Logo的二维码
     *
     * @param deviceIdentification 设备唯一标识
     * @return 生成的二维码字节数组
     * @throws Exception 如果生成过程中发生错误
     */
    byte[] generateQrcode(String deviceIdentification) throws Exception;

}
