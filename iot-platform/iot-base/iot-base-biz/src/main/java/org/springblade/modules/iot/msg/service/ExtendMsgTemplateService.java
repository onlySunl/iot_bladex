package org.springblade.modules.iot.msg.service;

import com.mqttsnet.basic.base.service.SuperService;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;


/**
 * <p>
 * 业务接口
 * 消息模板
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet]
 */
public interface ExtendMsgTemplateService extends SuperService<Long, ExtendMsgTemplate> {
    /**
     * 检测 模板标识 是否存在
     *
     * @param code
     * @param id
     * @return
     */
    Boolean check(String code, Long id);

    /**
     * 根据消息模板编码查询消息模板
     *
     * @param code
     * @return
     */
    ExtendMsgTemplate getByCode(String code);
}


