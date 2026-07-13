package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_certificate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTCertificate extends CustomBaseEntity {


  @TableField(value = "ssl_key")
  @AutoColumn(comment = "sslKey", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String sslKey;

  @TableField(value = "name")
  @AutoColumn(comment = "name", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;

  @TableField(value = "cert_content")
  @ColumnType("text")
  @AutoColumn(comment = "certContent", defaultValueType = DefaultValueEnum.NULL)
  private String certContent; // PEM内容或加密内容

  @TableField(value = "key_content")
  @ColumnType("text")
  @AutoColumn(comment = "keyContent", defaultValueType = DefaultValueEnum.NULL)
  private String keyContent; // PEM内容或加密内容

  @TableField(value = "cert_password")
  @AutoColumn(comment = "certPassword", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String certPassword;

  @TableField(value = "key_password")
  @AutoColumn(comment = "keyPassword", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String keyPassword;

  @TableField(value = "expire_time")
  @AutoColumn(comment = "expireTime", defaultValueType = DefaultValueEnum.NULL)
  private Date expireTime;

  @TableField(value = "remark")
  @ColumnType("text")
  @AutoColumn(comment = "remark", defaultValueType = DefaultValueEnum.NULL)
  private String remark;

  @TableField(value = "create_time")
  @AutoColumn(comment = "createTime", defaultValueType = DefaultValueEnum.NULL)
  private Date createTime;

  @TableField(value = "update_time")
  @AutoColumn(comment = "updateTime", defaultValueType = DefaultValueEnum.NULL)
  private Date updateTime;

  @TableField(value = "create_user")
  @AutoColumn(comment = "createUser", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String createUser;

  @TableField(value = "cert_info")
  @AutoColumn(comment = "certInfo", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String certInfo; // 证书详细信息（JSON）
}
