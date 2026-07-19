

package org.springblade.modules.iot.dal.mysql;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.ota.vo.OtaPackagePageReq;
import org.springblade.modules.iot.entity.OtaPackageDO;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.iot.mybatis.core.query.LambdaQueryWrapperX;

/**
 * ota包 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface OtaPackageMapper extends BaseMapperX<OtaPackageDO> {

    default PageResult<OtaPackageDO> selectPage(OtaPackagePageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OtaPackageDO>()
                .eqIfPresent(OtaPackageDO::getProductKey, reqVO.getProductKey())
                .likeIfPresent(OtaPackageDO::getName, reqVO.getName())
                .eqIfPresent(OtaPackageDO::getVersion, reqVO.getVersion())
                .eqIfPresent(OtaPackageDO::getIsDiff, reqVO.getIsDiff())
                .eqIfPresent(OtaPackageDO::getModule, reqVO.getModule())
//                .betweenIfPresent(OtaPackageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OtaPackageDO::getId));
    }

}
