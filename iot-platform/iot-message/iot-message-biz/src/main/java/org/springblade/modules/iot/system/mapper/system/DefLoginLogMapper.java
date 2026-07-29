package org.springblade.modules.iot.system.mapper.system;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.system.DefLoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 登录日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-12
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefLoginLogMapper extends SuperMapper<DefLoginLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param idList          待删除
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("idList") List<Long> idList);
}
