package org.springblade.modules.iot.msg.manager;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;

/**
 * <p>
 * 通用业务接口
 * 消息模板
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
public interface ExtendMsgTemplateManager extends SuperManager<ExtendMsgTemplate> {
    /**
     * 根据消息模板编码查询消息模板
     *
     * @param code
     * @return
     */
    ExtendMsgTemplate getByCode(String code);
}


