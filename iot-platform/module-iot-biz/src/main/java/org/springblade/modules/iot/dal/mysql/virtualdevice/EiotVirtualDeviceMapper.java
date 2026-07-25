package org.springblade.modules.iot.dal.mysql.virtualdevice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.VirtualDeviceDO;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.VirtualDevicePageReqVO;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualSaveScriptVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 虚拟设备 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotVirtualDeviceMapper extends BladeMapper<VirtualDeviceDO> {

    IPage<VirtualDeviceDO> selectPage(IPage<VirtualDeviceDO> page, @Param("reqVO") VirtualDevicePageReqVO reqVO);

    int updateScriptById(@Param("saveScriptVo") EiotVirtualSaveScriptVo saveScriptVo);

}
