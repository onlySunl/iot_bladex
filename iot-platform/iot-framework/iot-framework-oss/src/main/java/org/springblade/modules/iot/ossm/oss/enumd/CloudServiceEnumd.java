

package org.springblade.modules.iot.ossm.oss.enumd;

import org.springblade.modules.iot.ossm.oss.service.impl.AliyunCloudStorageServiceImpl;
import org.springblade.modules.iot.ossm.oss.service.impl.QiniuCloudStorageServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** 云存储服务商枚举 @Author Lion Li */
@Getter
@AllArgsConstructor
public enum CloudServiceEnumd {

  /** 七牛云 */
  QINIU("qiniu", QiniuCloudStorageServiceImpl.class),

  /** 阿里云 */
  ALIYUN("aliyun", AliyunCloudStorageServiceImpl.class);

  /** 腾讯云 */
  //  QCLOUD("qcloud", QcloudCloudStorageServiceImpl.class),

  /** minio */
  //  MINIO("minio", MinioCloudStorageServiceImpl.class);

  private final String value;

  private final Class<?> serviceClass;

  public static Class<?> getServiceClass(String value) {
    for (CloudServiceEnumd clazz : values()) {
      if (clazz.getValue().equals(value)) {
        return clazz.getServiceClass();
      }
    }
    return null;
  }
}
