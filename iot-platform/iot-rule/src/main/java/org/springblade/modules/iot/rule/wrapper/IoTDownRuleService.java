

package org.springblade.modules.iot.rule.wrapper;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.domain.R;
import org.springblade.modules.iot.common.message.DownRequest;
import org.springblade.modules.iot.persistence.base.IoTDownWrapper;
import org.springblade.modules.iot.pojo.entity.IoTDevice;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.rule.fence.service.FenceService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/8/10
 */
@Service("iotDownRuleService")
public class IoTDownRuleService implements IoTDownWrapper {

  @Resource private FenceService fenceService;

  @Override
  public R beforeFunctionOrConfigDown(
      IoTProduct product, IoTDevice ioTDevice, DownRequest downRequest) {
    // 处理电子围栏
    if (StringUtils.isNotEmpty(product.getConfiguration())) {
      JSONObject jsonObject = JSONUtil.parseObj(product.getConfiguration());
      Boolean isGps = jsonObject.getBool("isGps");
      return isGps != null && isGps
          ? fenceService.callFenceFunction(product, ioTDevice, downRequest)
          : null;
    }
    return null;
  }
}
