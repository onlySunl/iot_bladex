package org.springblade.modules.iot.service.plugin.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.modules.iot.service.plugin.PluginScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginScanServiceImpl
 * -----------------------------------------------------------------------------
 * Description:
 * PluginScanService 实现类
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/25       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/25 17:44
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginScanServiceImpl implements PluginScanService {


}
