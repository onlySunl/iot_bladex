package org.springblade.modules.iot.protocol.gb28181.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.Gb28181Platform;
import org.springblade.modules.iot.protocol.gb28181.mapper.Gb28181PlatformMapper;
import org.springframework.stereotype.Service;

/**
 * 国标GB28181平台配置Service
 *
 * @author BladeX
 */
@Service
public class Gb28181PlatformService extends ServiceImpl<Gb28181PlatformMapper, Gb28181Platform> implements IService<Gb28181Platform> {

    /**
     * 根据平台国标编码查询
     */
    public Gb28181Platform getByServerGbId(String serverGbId) {
        return getOne(new LambdaQueryWrapper<Gb28181Platform>()
            .eq(Gb28181Platform::getServerGbId, serverGbId));
    }

    /**
     * 根据设备国标编码查询平台
     */
    public Gb28181Platform getByDeviceGbId(String deviceGbId) {
        return getOne(new LambdaQueryWrapper<Gb28181Platform>()
            .eq(Gb28181Platform::getDeviceGbId, deviceGbId));
    }

    /**
     * 分页查询平台列表
     */
    public IPage<Gb28181Platform> pagePlatforms(Page<Gb28181Platform> page, Gb28181Platform platform) {
        LambdaQueryWrapper<Gb28181Platform> wrapper = new LambdaQueryWrapper<>();
        if (platform.getName() != null) {
            wrapper.like(Gb28181Platform::getName, platform.getName());
        }
        if (platform.getServerGbId() != null) {
            wrapper.eq(Gb28181Platform::getServerGbId, platform.getServerGbId());
        }
        if (platform.getEnabled() != null) {
            wrapper.eq(Gb28181Platform::getEnabled, platform.getEnabled());
        }
        wrapper.orderByDesc(Gb28181Platform::getCreateTime);
        return page(page, wrapper);
    }

}
