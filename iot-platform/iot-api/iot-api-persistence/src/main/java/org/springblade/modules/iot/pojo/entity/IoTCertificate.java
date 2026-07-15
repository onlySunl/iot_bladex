package org.springblade.modules.iot.pojo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_certificate")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTCertificate extends CustomBaseEntity {

  @TableField("ssl_key")
  private String sslKey;

  @TableField("name")
  private String name;

  @TableField("cert_content")
  private String certContent; // PEM内容或加密内容

  @TableField("key_content")
  private String keyContent; // PEM内容或加密内容

  @TableField("cert_password")
  private String certPassword;

  @TableField("key_password")
  private String keyPassword;

  @TableField("expire_time")
  private Date expireTime;

  @TableField("cert_info")
  private String certInfo; // 证书详细信息（JSON）
}
