

package org.springblade.modules.iot.ossm.oss.factory;

import cn.hutool.core.lang.Assert;
import org.springblade.modules.iot.common.utils.SpringUtils;
import org.springblade.modules.iot.ossm.oss.enumd.CloudServiceEnumd;
import org.springblade.modules.iot.ossm.oss.service.ICloudStorageService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 文件上传Factory @Author Lion Li */
public class OssFactory {
//
//    private static ISysConfigService sysConfigService;
//
//    static {
//      OssFactory.sysConfigService = SpringUtils.getBean(ISysConfigService.class);
//    }

  private static final Map<String, ICloudStorageService> SERVICES = new ConcurrentHashMap<>();

  public static ICloudStorageService instance() {
//        String type = sysConfigService.selectConfigByKey(CloudConstant.CLOUD_STORAGE_CONFIG_KEY);
    return instance("qiniu");
  }

  public static ICloudStorageService instance(String type) {
    ICloudStorageService service = SERVICES.get(type);
    if (service == null) {
      service = (ICloudStorageService) SpringUtils.getBean(CloudServiceEnumd.getServiceClass(type));
    }
    return service;
  }

  public static void register(String type, ICloudStorageService iCloudStorageService) {
    Assert.notNull(type, "type can't be null");
    SERVICES.put(type, iCloudStorageService);
  }
}
