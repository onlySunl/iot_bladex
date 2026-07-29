package org.springblade.modules.iot.base.manager.system;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.base.entity.system.BaseOperationLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务接口
 * 操作日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-08
 */
public interface BaseOperationLogManager extends SuperManager<BaseOperationLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
