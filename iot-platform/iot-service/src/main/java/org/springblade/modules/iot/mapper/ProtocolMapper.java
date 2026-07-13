package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.Protocol;
import org.apache.ibatis.annotations.Mapper;

/**
 * 协议定义 Mapper
 *
 * @author blade-iot
 */
@Mapper
public interface ProtocolMapper extends BaseMapper<Protocol> {
}
