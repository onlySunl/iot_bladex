

package org.springblade.modules.iot.service.iot;


import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tenant.annotation.TenantIgnore;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DeviceShortInfo;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.common.constant.Constants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceInfoPageReqVO;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.service.device.DeviceInfoService;
import org.springblade.modules.iot.service.product.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/23 15:16
 * @Version: V1.0
 * @Description: 设备在线状态检测
 */
@Slf4j
@Component
public class DeviceStateCheckTask {

    @Resource
    private ProductService productService;

    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private MqProducer<ThingModelMessage> producer;

    @Scheduled(fixedDelay = 10, initialDelay = 20, timeUnit = TimeUnit.SECONDS)
    @TenantIgnore
    public void syncState() {
        int pn = 1;
        int pageSize = 100;
        DeviceInfoPageReqVO pageReqVO = new DeviceInfoPageReqVO();
        pageReqVO.setPageNo(pn);
        pageReqVO.setPageSize(pageSize);
        while (true) {
            pageReqVO.setState(1);
            pageReqVO.setPageNo(pn);

            //取出数据库中所有在线设备
            // TODO: 从数据库库中取出到redis比较时,会有一定的时间间隔
            PageResult<DeviceShortInfo> result = deviceInfoService.getDeviceInfoPage(pageReqVO);
            if (result.getTotal() == 0) {
                return;
            }
            //判断属性更新时间是否大于产品定义保活时长
            for (DeviceShortInfo device : result.getList()) {
                Product product = productService.getProductByPkFromCache(device.getProductKey());
                Long keepAliveTime = product.getKeepAliveTime();
                // 如果没有设置保活时长或为0，则跳过
                if (keepAliveTime == null || keepAliveTime <= 0) {
                    continue;
                }
                Long deviceId = device.getId();
                long lastTime = deviceInfoService.getLastTimeCache(deviceId);
                //最后更新时间超时保活时长1.1倍认为设备离线了
                if (System.currentTimeMillis() - lastTime > keepAliveTime * 1000 * 1.1) {
                    DeviceInfo realTimeDevice = deviceInfoService.getDeviceInfoFromCache(deviceId);
                    if (!realTimeDevice.isOnline()) {
                        continue;
                    }
                    log.info("device state check offline,{}", deviceId);

                    // 发送设备离线物模型消息
                    sendDeviceOfflineMessage(device);

                }
            }

            if (result.getList().size() < pageSize) {
                break;
            }
            pn++;
        }
    }

    private void sendDeviceOfflineMessage(DeviceShortInfo device) {
        // TODO: 提却到某个公共类中

        ThingModelMessage msg = ThingModelMessage.builder()
                .id(IdUtil.fastSimpleUUID())
                .time(System.currentTimeMillis())
                .dn(device.getDn())
                .productKey(device.getProductKey())
                .occurred(System.currentTimeMillis())
                .deviceId(device.getId())
                .identifier(ThingModelMessage.ID_OFFLINE)
                .type(ThingModelMessage.TYPE_STATE)
                .build();
        producer.publish(Constants.THING_MODEL_MESSAGE_TOPIC, msg);

    }


}
