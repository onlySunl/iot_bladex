package org.springblade.modules.iot.base.mapper.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.base.entity.user.BaseEmployee;
import org.springblade.modules.iot.base.vo.query.user.BaseEmployeePageQuery;
import org.springblade.modules.iot.base.vo.result.user.BaseEmployeeResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 员工
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
@Repository
public interface BaseEmployeeMapper extends SuperMapper<BaseEmployee> {
    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 查询条件
     * @param model   参数
     * @return 分页用户数据
     */
    IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page,
                                                   @Param(Constants.WRAPPER) Wrapper<BaseEmployee> wrapper,
                                                   @Param("model") BaseEmployeePageQuery model);


}
