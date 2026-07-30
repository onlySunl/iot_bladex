package org.springblade.modules.iot.msg.mapper;

import org.springblade.core.mvc.mapper.SuperMapper;
import org.springblade.modules.iot.msg.entity.ExtendInterfaceLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 接口执行日志
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [mqttsnet] 
 */
@Repository
public interface ExtendInterfaceLogMapper extends SuperMapper<ExtendInterfaceLog> {
    /**
     * 递增成功次数
     *
     * @param id  日志ID
     * @param now 当前时间
     * @return
     */
    int incrSuccessCount(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 递增失败次数
     *
     * @param id  日志ID
     * @param now 当前时间
     * @return
     */
    int incrFailCount(@Param("id") Long id, @Param("now") LocalDateTime now);
}


