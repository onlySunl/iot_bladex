

package org.springblade.modules.iot.service.device;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.TenantIgnore;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.device.dto.DeviceShortInfo;
import org.springblade.modules.iot.api.device.dto.RegisterDevice;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.common.validate.ValidationUtils;
import org.springblade.modules.iot.controller.admin.device.vo.*;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.DeviceImportRespVO;
import org.springblade.modules.iot.convert.DeviceInfoConvert;
import org.springblade.modules.iot.convert.ProductConvert;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotIotDeviceGroupMapper;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotDeviceInfoMapper;
import org.springblade.modules.iot.dal.mysql.product.ProductMapper;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import org.springblade.modules.iot.dal.redis.no.EiotRedisDAO;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;
import org.springblade.modules.iot.entity.ProductDO;
import org.springblade.modules.iot.service.product.IProductService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static org.springblade.modules.iot.common.utils.ServiceExceptionUtil.exception;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotDeviceInfoMapper;

/**
 * 设备信息 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
@Slf4j
public class DeviceInfoServiceImpl extends BaseServiceImpl<EiotDeviceInfoMapper, EiotDeviceInfoDO> implements IDeviceInfoService {

    @Resource
    private EiotDeviceInfoMapper deviceInfoMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private EiotIotDeviceGroupMapper deviceGroupMapper;

    @Resource
    private IProductService productService;

    @Resource
    private EiotRedisDAO eiotRedisDAO;

    @Resource
    private CacheManager cacheManager;


    @Override
    public Long createDeviceInfo(DeviceInfoSaveReqVO createReqVO) {

        String productKey = createReqVO.getProductKey();

        ProductDO productDO = productMapper.getByProductKey(productKey);
        if (ObjectUtils.isNull(productDO)) {
            throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }
        // 若未显式传递机构，则默认使用当前登录用户机构，确保数据权限生效
        if (createReqVO.getDeptId() == null) {
            createReqVO.setDeptId(Func.toLong(AuthUtil.getDeptId()));
        }
        // 插入
        EiotDeviceInfoDO deviceInfo = BeanUtils.toBean(createReqVO, EiotDeviceInfoDO.class);
        deviceInfo.setId(IdUtil.getSnowflakeNextId());
        deviceInfo.setNodeType(productDO.getNodeType());
        deviceInfo.setState(DeviceInfo.STATE_NOT_ACTIVE);
        deviceInfo.setTenantId(productDO.getTenantId());
        deviceInfo.setTransparent(productDO.getTransparent());
        deviceInfoMapper.insert(deviceInfo);
        // 返回
        return deviceInfo.getId();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = RedisKeyConstants.DEVICE, key = "#updateReqVO.productKey+':'+#updateReqVO.dn")
                    ,
                    @CacheEvict(cacheNames = RedisKeyConstants.DEVICE_ID, key = "#updateReqVO.id")
            }
    )
    public void updateDeviceInfo(@Validated DeviceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceInfoExists(updateReqVO.getId());

        ProductDO productDO = productMapper.getByProductKey(updateReqVO.getProductKey());
        if (ObjectUtils.isNull(productDO)) {
            throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }
        // 更新
        EiotDeviceInfoDO updateObj = BeanUtils.toBean(updateReqVO, EiotDeviceInfoDO.class);
        updateObj.setNodeType(productDO.getNodeType());
        deviceInfoMapper.updateById(updateObj);
    }

    @CacheEvict(cacheNames = RedisKeyConstants.DEVICE_ID, key = "#deviceId")
    @Override
    public Boolean deleteDeviceInfo(Long deviceId) {
        EiotDeviceInfoDO deviceInfo = validateDeviceInfoExists(deviceId);
        // 删除
        Boolean ret = deviceInfoMapper.deleteById(deviceId) > 0;
        if (ret) {
            clearCache(RedisKeyConstants.DEVICE, deviceInfo.getProductKey() + ":" + deviceInfo.getDn());
        }
        return ret;
    }

    void clearDeviceCache(Long deviceId, String productKey, String deviceName) {
        DeviceInfo deviceInfo = null;
        if (deviceId != null){
            deviceInfo = getDeviceInfoFromCache(deviceId);
        }
        if (ObjectUtil.isNull(deviceInfo) && StringUtils.isNotBlank(productKey)&& StringUtils.isNotBlank(deviceName)) {
            deviceInfo = getDeviceByPkDnByCache(productKey, deviceName);
        }
        if (deviceInfo == null) {
            return;
        }
        Long id = deviceInfo.getId();

        clearCache(RedisKeyConstants.DEVICE_ID, id.toString());
        clearCache(RedisKeyConstants.DEVICE, deviceInfo.getProductKey() + "_" + deviceInfo.getDn());

        return;
    }

    private void clearCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }


    @Override
    public DeviceInfo getDeviceInfo(Long id) {
        return DeviceInfoConvert.INSTANCE.convert(deviceInfoMapper.selectById(id));
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.DEVICE, key = "#pk+':'+#dn",
            unless = "#result == null")
    @TenantIgnore
    public DeviceInfo getDeviceByPkDnByCache(String pk, String dn) {
        return DeviceInfoConvert.INSTANCE.convert(deviceInfoMapper.selectOne(
                new LambdaQueryWrapper<EiotDeviceInfoDO>()
                        .eq(EiotDeviceInfoDO::getProductKey, pk)
                        .eq(EiotDeviceInfoDO::getDn, dn)));
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.DEVICE_ID, key = "#deviceId",
            unless = "#result == null")
    @TenantIgnore
    public DeviceInfo getDeviceInfoFromCache(Long deviceId) {
        return getDeviceInfo(deviceId);
    }

    @Override
    public Map<String, DevicePropertyCache> getPropertiesFromCache(Long deviceId) {
        return eiotRedisDAO.getProperties(deviceId);
    }

    @Override
    public PageResult<DeviceShortInfo> getDeviceInfoPage(DeviceInfoPageReqVO pageReqVO) {
        return PageResult.from(deviceInfoMapper.selectPage(new Page<DeviceShortInfo>(pageReqVO.getPageNo(), pageReqVO.getPageSize()), pageReqVO));
    }

    @Override
    public DeviceInfo getDeviceBySerialNo(String serialNumber) {
        return DeviceInfoConvert.INSTANCE.convert(deviceInfoMapper.selectOne(
                new LambdaQueryWrapper<EiotDeviceInfoDO>()
                        .eq(EiotDeviceInfoDO::getSerialNo, serialNumber)));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        deviceInfoMapper.deleteByIds(ids);
        // TODO: 删除缓存
        deviceGroupMapper.deleteByDeviceIds(ids);
        return false;
    }

    @Override
    public DeviceImportRespVO importDevice(List<DeviceInfoImportVo> list, Long productId) {
        // 1.1 参数校验
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException("导入数据不许为空");
        }
        Product product = ProductConvert.INSTANCE.convert(productMapper.selectById(productId));
        if (Objects.isNull(product)) {
            throw new ServiceException("产品不存在");
        }

        // 2. 遍历，逐个创建 or 更新
        DeviceImportRespVO respVO = DeviceImportRespVO.builder().createKey(new ArrayList<>())
                .updateKey(new ArrayList<>()).failureKeyMap(new LinkedHashMap<>()).build();

        list.forEach(o -> {
            DeviceInfoSaveReqVO saveReqVO = BeanUtils.toBean(o, DeviceInfoSaveReqVO.class);
            saveReqVO.setProductKey(product.getProductKey());

            try {
                ValidationUtils.validate(saveReqVO);
            } catch (ConstraintViolationException ex) {
                respVO.getFailureKeyMap().put(saveReqVO.getName(), ex.getMessage());
                return;
            }

            try {
                validateDeviceForCreateOrUpdate(saveReqVO);
            } catch (ConstraintViolationException ex) {
                respVO.getFailureKeyMap().put(saveReqVO.getName(), ex.getMessage());
                return;
            }
            Long deviceId = createDeviceInfo(saveReqVO);
            if (ObjectUtil.isNotNull(deviceId)) {
                respVO.getCreateKey().add(saveReqVO.getName());
            }

        });

        return respVO;
    }

    @Override
    public List<DeviceInfo> findSubDeviceList(String productKey, String deviceName) {
        DeviceInfo deviceParent = getDeviceByPkDnByCache(productKey, deviceName);
        if (ObjectUtil.isNull(deviceParent)) {
            return Collections.EMPTY_LIST;
        }
        LambdaQueryWrapper<EiotDeviceInfoDO> q = new LambdaQueryWrapper<EiotDeviceInfoDO>();
        q.eq(EiotDeviceInfoDO::getParentId, deviceParent.getId());
        return DeviceInfoConvert.INSTANCE.convertList(deviceInfoMapper.selectList(q));
    }

    private EiotDeviceInfoDO validateDeviceInfoExists(Long id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }
        EiotDeviceInfoDO device = deviceInfoMapper.selectById(id);
        if (device == null) {
            throw exception(ErrorCodeConstants.DEVICE_INFO_NOT_EXISTS);
        }
        return device;
    }

    public void validateDeviceForCreateOrUpdate(DeviceInfoSaveReqVO saveReqVO) {
        Long id = saveReqVO.getId();
        validateDeviceInfoExists(id);
        validateDeviceDnUnique(id, saveReqVO.getDn(), saveReqVO.getProductKey());
        validateDeviceSerialNo(id, saveReqVO.getSerialNo());
    }

    private void validateDeviceSerialNo(Long id, String serialNo) {
        if (StringUtils.isBlank(serialNo)) {
            return;
        }
        DeviceInfo obj = getDeviceBySerialNo(serialNo);
        if (ObjectUtil.isNotNull(obj) && !obj.getId().equals(id)) {
            throw exception(ErrorCodeConstants.DEVICE_SERIAL_REPEAT);
        }

    }

    private void validateDeviceDnUnique(Long id, String dn, String productKey) {
        if (StringUtils.isBlank(dn)) {
            return;
        }
        DeviceInfo deviceByPkDnByCache = getDeviceByPkDnByCache(productKey, dn);
        if (ObjectUtil.isNotNull(deviceByPkDnByCache) && !deviceByPkDnByCache.getId().equals(id)) {
            throw exception(ErrorCodeConstants.DEVICE_DN_REPEAT);
        }

    }

    public PageResult<DeviceShortInfo> getUnbindPage(DeviceUnbindPageReqVO pageReqVO) {
        DeviceInfoPageReqVO deviceInfoPageReqVO = new DeviceInfoPageReqVO();
        deviceInfoPageReqVO.setPageNo(pageReqVO.getPageNo());
        deviceInfoPageReqVO.setPageSize(pageReqVO.getPageSize());
        deviceInfoPageReqVO.setBindStatus(false);
        deviceInfoPageReqVO.setName(pageReqVO.getName());
        deviceInfoPageReqVO.setDn(pageReqVO.getDn());

        // 产品名称查询
        if (StringUtils.isNotBlank(pageReqVO.getProductName())) {
            List<ProductDO> productList = productMapper.selectList(new LambdaQueryWrapper<ProductDO>().like(ProductDO::getName, pageReqVO.getProductName()));
            deviceInfoPageReqVO.setProductKeyList(productList.stream().map(ProductDO::getProductKey).collect(Collectors.toList()));
        }

        return this.getDeviceInfoPage(deviceInfoPageReqVO);
    }

    @Override
    public Page<DeviceShortInfo> getDeviceInfoPageByIds(Page<DeviceShortInfo> page, List<Long> deviceIds, String dn, String name) {
        if (CollUtil.isEmpty(deviceIds)) {
            return new Page<>(page.getCurrent(), page.getSize());
        }
        Page<EiotDeviceInfoDO> doPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<EiotDeviceInfoDO> q = new LambdaQueryWrapper<EiotDeviceInfoDO>()
                .in(EiotDeviceInfoDO::getId, deviceIds);
        if (StringUtils.isNotBlank(dn)) {
            q.like(EiotDeviceInfoDO::getDn, dn);
        }
        if (StringUtils.isNotBlank(name)) {
            q.like(EiotDeviceInfoDO::getName, name);
        }
        IPage<EiotDeviceInfoDO> result = deviceInfoMapper.selectPage(doPage, q);
        Page<DeviceShortInfo> shortPage = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        shortPage.setRecords(result.getRecords().stream().map(do_ -> BeanUtils.toBean(do_, DeviceShortInfo.class)).collect(Collectors.toList()));
        return shortPage;
    }

    @Override
    public Page<DeviceShortInfo> getAvailableDevicePageList(Page<DeviceShortInfo> page, List<Long> excludeIds, String dn, String name) {
        Page<EiotDeviceInfoDO> doPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<EiotDeviceInfoDO> q = new LambdaQueryWrapper<>();
        if (CollUtil.isNotEmpty(excludeIds)) {
            q.notIn(EiotDeviceInfoDO::getId, excludeIds);
        }
        if (StringUtils.isNotBlank(dn)) {
            q.like(EiotDeviceInfoDO::getDn, dn);
        }
        if (StringUtils.isNotBlank(name)) {
            q.like(EiotDeviceInfoDO::getName, name);
        }
        IPage<EiotDeviceInfoDO> result = deviceInfoMapper.selectPage(doPage, q);
        Page<DeviceShortInfo> shortPage = new Page<>(result.getCurrent(), result.getSize(), result.getTotal());
        shortPage.setRecords(result.getRecords().stream().map(do_ -> BeanUtils.toBean(do_, DeviceShortInfo.class)).collect(Collectors.toList()));
        return shortPage;
    }

    @Override
    public void bindParent(@Validated DeviceBindReqVO bindReqVO) {
        LambdaUpdateWrapper<EiotDeviceInfoDO> up = new LambdaUpdateWrapper<>();
        up.in(EiotDeviceInfoDO::getId, bindReqVO.getIdList());
        up.set(EiotDeviceInfoDO::getParentId, bindReqVO.getParentId());
        deviceInfoMapper.update(null, up);
    }

    @Override
    public void unbindParent(DeviceUnbindReqVO unbindReqVO) {
        LambdaUpdateWrapper<EiotDeviceInfoDO> up = new LambdaUpdateWrapper<>();
        up.in(EiotDeviceInfoDO::getId, unbindReqVO.getIdList());
        up.set(EiotDeviceInfoDO::getParentId, null);
        deviceInfoMapper.update(null, up);
    }


    @Override
    public DeviceInfo registerDevice(RegisterDevice registerDevice) {
        String productKey = registerDevice.getProductKey();
        Product product = productService.getByPk(productKey);
        if (Objects.isNull(product)) {
            throw exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }

        DeviceInfo device = getDeviceByPkDnByCache(productKey, registerDevice.getDeviceName());
        if (ObjectUtil.isNotNull(device)) {
            return device;
        }

        DeviceInfoSaveReqVO deviceInfo = new DeviceInfoSaveReqVO();
        deviceInfo.setProductKey(productKey);
        deviceInfo.setName(registerDevice.getDeviceName());
        deviceInfo.setDn(registerDevice.getDeviceName());
        deviceInfo.setModel(registerDevice.getModel());
        deviceInfo.setParentId(registerDevice.getParentId());
        deviceInfo.setSecret(RandomStringUtils.randomAlphabetic(16));
        deviceInfo.setSerialNo(registerDevice.getDeviceName());

        Long deviceId = createDeviceInfo(deviceInfo);
        return getDeviceInfo(deviceId);
    }

    @Override
    public long getLastTimeCache(Long deviceId) {
        return eiotRedisDAO.getLastTime(deviceId);
    }

    @Override
    public void updateDeviceLastTimeCache(Long deviceId, long lastTime) {
        eiotRedisDAO.setLastTime(deviceId, lastTime);
    }

    @CacheEvict(cacheNames = RedisKeyConstants.DEVICE_ID, key = "#deviceId")
    @Override
    public Boolean updateDeviceState(Long deviceId, boolean online) {
        LambdaUpdateWrapper<EiotDeviceInfoDO> up = new LambdaUpdateWrapper<>();
        up.set(EiotDeviceInfoDO::getState, online ? DeviceInfo.ONLINE : DeviceInfo.OFFLINE);
        if (online) {
            up.set(EiotDeviceInfoDO::getOnlineTime, System.currentTimeMillis());
        } else {
            up.set(EiotDeviceInfoDO::getOfflineTime, System.currentTimeMillis());
        }
        up.eq(EiotDeviceInfoDO::getId, deviceId);
        // TODO: mybatis 拦截器会设置更新时间,所以应该是
        boolean b = deviceInfoMapper.update(null, up) > 0;
        if (b) {
            DeviceInfo deviceInfo = getDeviceInfo(deviceId);
            clearCache(RedisKeyConstants.DEVICE, deviceInfo.getProductKey() + ":" + deviceInfo.getDn());
        }
        return b;
    }

    @Override
    public void savePropertiesCache(Long deviceId, Map<String, DevicePropertyCache> properties) {
        eiotRedisDAO.saveProperties(deviceId, properties);
    }

    @Override
    public void clearPropertiesCache(String productKey) {
        List<EiotDeviceInfoDO> deviceList = deviceInfoMapper.selectList(
                new LambdaQueryWrapper<EiotDeviceInfoDO>()
                        .eq(EiotDeviceInfoDO::getProductKey, productKey));
        if (deviceList != null && !deviceList.isEmpty()) {
            List<Long> deviceIds = deviceList.stream().map(EiotDeviceInfoDO::getId).collect(Collectors.toList());
            eiotRedisDAO.clearProperties(deviceIds);
        }
    }

    @Override
    public List<DeviceInfo> getDeviceInfoList(List<Long> subDeviceIds) {
        return DeviceInfoConvert.INSTANCE.convertList(deviceInfoMapper.selectByIds(subDeviceIds));
    }

    @Override
    public Boolean subDeRegisterDevice(String pk, String dn, String subPkDeregister, String subDnDeregister) {
        DeviceInfo subDevice = getDeviceByPkDnByCache(subPkDeregister, subDnDeregister);
        if (ObjectUtil.isNull(subDevice)){
            return Boolean.TRUE;
        }
        deviceInfoMapper.update(null, new LambdaUpdateWrapper<EiotDeviceInfoDO>().set(EiotDeviceInfoDO::getParentId, null).eq(EiotDeviceInfoDO::getId, subDevice.getId()));

        clearDeviceCache(null, subPkDeregister, subDnDeregister);
        return Boolean.TRUE;
    }

}
