

package org.springblade.modules.iot.dal.mysql.virtualdevice;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualSaveScriptVo;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.VirtualDevicePageReqVO;
import org.springblade.modules.iot.entity.VirtualDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则引擎 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotVirtualDeviceMapper extends BaseMapperX<VirtualDeviceDO> {


    default PageResult<VirtualDeviceDO> selectPage(VirtualDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VirtualDeviceDO>()
                .eqIfPresent(VirtualDeviceDO::getState, reqVO.getState())
                .eqIfPresent(VirtualDeviceDO::getProductKey, reqVO.getProductKey())
                .orderByDesc(VirtualDeviceDO::getId));
    }

    default int updateScriptById(EiotVirtualSaveScriptVo saveScriptVo) {
        LambdaUpdateWrapper<VirtualDeviceDO> up = new LambdaUpdateWrapper<>();
        up.set(VirtualDeviceDO::getScript, saveScriptVo.getScript());
        up.eq(VirtualDeviceDO::getId, saveScriptVo.getId());
        return update(up);
    }

}
