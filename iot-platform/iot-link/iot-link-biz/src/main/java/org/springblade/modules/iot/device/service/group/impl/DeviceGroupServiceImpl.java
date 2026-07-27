package org.springblade.modules.iot.device.service.group.impl;

import java.util.Collection;
import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.group.DeviceGroup;
import org.springblade.modules.iot.device.manager.group.DeviceGroupManager;
import org.springblade.modules.iot.device.service.group.DeviceGroupRelService;
import org.springblade.modules.iot.device.service.group.DeviceGroupService;
import org.springblade.modules.iot.device.vo.query.group.DeviceGroupPageQuery;
import org.springblade.modules.iot.device.vo.result.group.DeviceGroupResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 * @create [2025-06-19 18:05:14] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class DeviceGroupServiceImpl extends BaseServiceImpl<DeviceGroupMapper, DeviceGroup> implements DeviceGroupService {

    private final DeviceGroupRelService deviceGroupRelService;

    @Override
    public List<DeviceGroupResultVO> findTree(DeviceGroupPageQuery query) {
        List<DeviceGroup> list = superManager.list(Wrappers.<DeviceGroup>lbQ().orderByAsc(DeviceGroup::getSortValue));
        return BeanUtil.toBeanList(TreeUtil.buildTree(list), DeviceGroupResultVO.class);
    }

    @Override
    public List<DeviceGroupResultVO> getDeviceGroupResultVOList(DeviceGroupPageQuery query) {
        List<DeviceGroup> list = superManager.getList(query);
        return BeanUtil.toBeanList(list, DeviceGroupResultVO.class);
    }

    @Override
    public boolean removeByIds(Collection<Long> idList) {
        boolean flag = super.removeByIds(idList);
        if (flag) {
            // 删除分组资源
            deviceGroupRelService.removeByGroupIds(idList);
        }
        return flag;
    }
}

