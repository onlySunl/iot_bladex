

package org.springblade.modules.iot.api.device;

import cn.hutool.core.util.ObjectUtil;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.core.tenant.annotation.TenantIgnore;
import org.springblade.modules.iot.api.product.service.RemoteIotProductService;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.utils.TenantUtils;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springblade.modules.iot.common.utils.CodecUtil;

import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotDeviceInfoMapper;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;
import org.springblade.modules.iot.service.device.IDeviceConfigService;
import org.springblade.modules.iot.service.device.IDeviceCtrlService;
import org.springblade.modules.iot.service.device.IDeviceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springblade.modules.iot.common.constant.GlobalErrorCodeConstants.BAD_REQUEST;

@Slf4j
@Service
public class DeviceApiImpl extends BladeServiceImpl<EiotDeviceInfoMapper, EiotDeviceInfoDO> implements DeviceApi {

    @Resource
    private IDeviceInfoService deviceInfoService;

    @Resource
    private IDeviceConfigService deviceConfigService;

    @Resource
    private IDeviceCtrlService deviceCtrlService;

    @Resource
    private RemoteIotProductService productApi;

    @Override
    public DeviceInfo getDeviceByPkDnByCache(String pk, String dn) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.getDeviceByPkDnByCache(pk, dn));
    }

    @Override
    public DeviceInfo getDeviceInfoFromCache(Long deviceId) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.getDeviceInfoFromCache(deviceId));
    }



    @Override
    public DeviceInfo registerDevice(RegisterDevice registerDevice) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.registerDevice(registerDevice));
    }

    @Override
    @TenantIgnore
    public CommonResult<DeviceInfo> auth(DeviceAuth authDTO) {

        return TenantUtils.executeIgnoreResult(()->{
            return doAuth(authDTO);
        });
    }

    @NotNull
    private CommonResult<DeviceInfo> doAuth(DeviceAuth authDTO) {
        String clientId = authDTO.getClientId();
        String[] parts = clientId.split("_");
        String productKey = parts[0];
        String deviceName = parts[1];
        String gwModel = parts[2];
        if (!authDTO.getUserName().equals(deviceName)) {
            log.error("username:{}不正确", deviceName);
            return CommonResult.error(BAD_REQUEST.getCode(), "deviceName不正确");

        }


        DeviceInfo device = deviceInfoService.getDeviceByPkDnByCache(productKey, deviceName);
        if (Objects.isNull(device)) {
            if (!authDTO.isCanRegister()) {
                return CommonResult.error(BAD_REQUEST.getCode(), "设备未注册");
            }
            Product product = productApi.getProduct(productKey);
            if (Objects.isNull(product)) {
                return CommonResult.error(BAD_REQUEST.getCode(), "产品信息不存在");
            }
            String validPasswd = CodecUtil.md5Str(product.getProductSecret() + clientId);
            if (!validPasswd.equalsIgnoreCase(authDTO.getPassword())) {
                log.info("deviceName:{}, validPasswd:{}", deviceName, validPasswd);
                return CommonResult.error(BAD_REQUEST.getCode(), "密码验证识别");
            }

            RegisterDevice registerDeviceDTO = RegisterDevice.builder().deviceName(deviceName).productKey(productKey)
                    .tenantId(authDTO.getTenantId()).build();

            DeviceInfo registerDevice =   this.registerDevice(registerDeviceDTO);
            if(ObjectUtil.isNull(registerDevice)){
                return CommonResult.error(BAD_REQUEST.getCode(), "设备注册失败");
            }
            return CommonResult.success(registerDevice);
        }


        return CommonResult.success(device);
    }
    @Override
    public Map<String, DevicePropertyCache> getPropertiesFromCache(Long deviceId) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.getPropertiesFromCache(deviceId));
    }

    @Override
    public void updateDeviceLastTimeCache(Long deviceId, long lastTime) {
        TenantUtils.executeIgnore(() -> deviceInfoService.updateDeviceLastTimeCache(deviceId, lastTime));
    }

    @Override
    public Boolean updateDeviceState(Long deviceId, boolean online) {
        return TenantUtils.executeIgnoreWithResult(() -> deviceInfoService.updateDeviceState(deviceId, online));
    }

    @Override
    public void savePropertiesCache(Long deviceId, Map<String, DevicePropertyCache> properties) {
        TenantUtils.executeIgnore(() -> deviceInfoService.savePropertiesCache(deviceId, properties));
    }

    @Override
    public void clearPropertiesCache(String productKey) {
        TenantUtils.executeIgnore(() -> deviceInfoService.clearPropertiesCache(productKey));
    }

    @Override
    public DeviceConfig getDeviceConfig(Long deviceId) {
        return TenantUtils.executeIgnoreResult(() -> deviceConfigService.findByDeviceId(deviceId));
    }

    @Override
    public DeviceConfig getDeviceConfig(String productKey, String dn) {
        return TenantUtils.executeIgnoreResult(() -> deviceConfigService.findByPkDn(productKey, dn));
    }

    @Override
    public void invoke(ThingService<?> service) {
        TenantUtils.executeIgnore(()->{
            DeviceInfo device = deviceInfoService.getDeviceByPkDnByCache(service.getProductKey(), service.getDn());
            deviceCtrlService.invokeService(device.getId(),service.getIdentifier(), (Map<String, Object>) service.getParams());
        });
    }

    @Override
    public List<DeviceInfo> getSubDevicesByProductKeAndDeviceName(String pk, String dn) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.findSubDeviceList(pk, dn));
    }

    @Override
    public Boolean deregisterSubDevice(String pk, String dn, String model, String subPkDeregister, String subDnDeregister) {
        return TenantUtils.executeIgnoreResult(() -> deviceInfoService.subDeRegisterDevice(pk, dn, subPkDeregister,subDnDeregister));
    }
}
