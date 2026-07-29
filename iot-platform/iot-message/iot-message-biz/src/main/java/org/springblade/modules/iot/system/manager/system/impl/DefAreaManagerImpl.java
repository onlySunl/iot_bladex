package org.springblade.modules.iot.system.manager.system.impl;

import com.mqttsnet.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.system.entity.system.DefArea;
import org.springblade.modules.iot.system.manager.system.DefAreaManager;
import org.springblade.modules.iot.system.mapper.system.DefAreaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 地区表
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 * @create [2021-10-13] [mqttsnet] 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefAreaManagerImpl extends SuperManagerImpl<DefAreaMapper, DefArea> implements DefAreaManager {
}
