package org.springblade.modules.iot.base.manager.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.base.manager.SuperCacheManager;
import org.springblade.modules.iot.base.entity.user.BaseEmployee;
import org.springblade.modules.iot.base.vo.query.user.BaseEmployeePageQuery;
import org.springblade.modules.iot.base.vo.result.user.BaseEmployeeResultVO;

/**
 * <p>
 * 通用业务接口
 * 员工
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
public interface BaseEmployeeManager extends SuperCacheManager<BaseEmployee> {
    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 查询条件
     * @param model   参数
     * @return 分页用户数据
     */
    IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page, Wrapper<BaseEmployee> wrapper, BaseEmployeePageQuery model);

}
