package org.springblade.modules.iot.system.facade;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.interfaces.echo.LoadService;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserDetailsResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserResultVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
public interface DefUserFacade extends LoadService {
    /**
     * 查询所有的用户id
     *
     * @return 用户id
     */

    R<List<Long>> findAllUserId();

    R<List<DefUserResultVO>> queryIds(List<Long> ids);

    /**
     * 根据id查询实体
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    Map<Serializable, Object> findByIds(Set<Serializable> ids);


    /**
     * 根据用户id集合批量获取用户详情信息
     *
     * @param ids 用户ID
     * @return {@link R<List<DefUserDetailsResultVO>>} 用户详情VO集合
     */
    R<List<DefUserDetailsResultVO>> getDefUserByIds(List<Long> ids);
}
