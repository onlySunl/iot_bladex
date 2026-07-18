

package org.springblade.modules.iot.dal.mysql;


import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;
import org.springblade.modules.iot.entity.AlertRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警记录 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface AlertRecordMapper extends BaseMapperX<AlertRecordDO> {

    default PageResult<AlertRecordDO> selectPage(AlertRecordPageReq reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlertRecordDO>()

                .eqIfPresent(AlertRecordDO::getLevel, reqVO.getLevel())
                .likeIfPresent(AlertRecordDO::getName, reqVO.getName())
                .eqIfPresent(AlertRecordDO::getReadFlg, reqVO.getReadFlg())
                .orderByDesc(AlertRecordDO::getId));
    }

}
