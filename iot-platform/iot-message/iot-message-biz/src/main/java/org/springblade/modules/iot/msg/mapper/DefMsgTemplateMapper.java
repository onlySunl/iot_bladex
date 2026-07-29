package org.springblade.modules.iot.msg.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.msg.entity.DefMsgTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 消息模板
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefMsgTemplateMapper extends SuperMapper<DefMsgTemplate> {

}


