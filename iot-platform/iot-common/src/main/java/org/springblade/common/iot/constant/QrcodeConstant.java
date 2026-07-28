package org.springblade.common.iot.constant;

/**
 * -----------------------------------------------------------------------------
 * File Name: QrcodeConstant
 * -----------------------------------------------------------------------------
 * Description:
 * 二维码全局常量
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/9/27       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/9/27 14:33
 */
public interface QrcodeConstant {

    /**
     * 二维码 LOGO 资源地址 (默认Logo存储在resources目录下的images文件夹中)
     * images/logo.png
     */
    String LOGO_RESOURCE_LOCATION = "images/logo.png";


    /**
     * 二维码尺寸
     */
    int QR_CODE_LENGTH = 400;


    //=======================业务参数============================

    /**
     * 设备二维码场景
     */
    String DEVICE_SCENE = "deviceScene";


    /**
     * 空间二维码场景
     */
    String SPACE_SCENE = "spaceScene";

}
