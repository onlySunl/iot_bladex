

package org.springblade.modules.iot.service.virtualdevice;


import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualDeviceSaveReqVO;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualSaveDevicesMappingVo;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualSaveScriptVo;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.VirtualDevicePageReqVO;

import java.util.List;

/**
 * 虚拟设备服务
 *
 * @author clickear@163.com
 */
public interface VirtualDeviceService {

    /**
     * 添加虚拟设备
     *
     * @param virtualDevice
     * @return 虚拟设备id
     */
    Long saveVirtualDevice(EiotVirtualDeviceSaveReqVO virtualDevice);

    /**
     * 更新虚拟设备
     *
     * @param virtualDevice
     */
    void updateVirtualDevice(VirtualDevice virtualDevice);

    /**
     * 保存关联信息
     * @param data
     */
    void saveVirtualDeviceMapping(EiotVirtualSaveDevicesMappingVo data);

    /**
     * 更新脚本信息
     * @param saveScriptVo
     */
    void saveScript(EiotVirtualSaveScriptVo saveScriptVo);

    /**
     * 删除虚拟设备
     *
     * @param virtualDeviceId 虚拟设备id
     */
    void deleteVirtualDevice(Long virtualDeviceId);

    /**
     * 获取虚拟设备信息
     *
     * @param virtualDeviceId
     * @return
     */
    VirtualDevice getVirtualDevice(Long virtualDeviceId);

    /**
     * 设置状态
     *
     * @param id
     * @param state
     */
    void setState(Long id, String state);

    /**
     * 手动执行虚拟设备
     *
     * @param id
     */
    void run(Long id);


    /**
     * 根据表达式获取虚拟设备信息
     *
     * @param trigger
     * @param state
     * @return
     */
    List<VirtualDevice> findByTriggerAndState(String trigger, String state);

    /**
     * 分页查询虚拟设备
     * @param reqVO
     * @return
     */
    PageResult<VirtualDevice> selectPage(VirtualDevicePageReqVO reqVO);



    /**
     * 根据虚拟设备id分页查询日志
     *
     * @param virtualDeviceId
     * @param page
     * @param size
     * @return
     */
    PageResult<VirtualDeviceLog> findByVirtualDeviceId(Long virtualDeviceId, int page, int size);


}
