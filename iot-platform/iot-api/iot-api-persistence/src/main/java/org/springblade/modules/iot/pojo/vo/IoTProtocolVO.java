/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IoTProtocolVO implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private Byte state;
  private String type;
  private String configuration;
  private String example;
  private String fileName;
  private String url;
  private String jscript;
  private String needBs4Decode;

  /** 创建时间 */

  /** 版本号 */
    private String version;
}
