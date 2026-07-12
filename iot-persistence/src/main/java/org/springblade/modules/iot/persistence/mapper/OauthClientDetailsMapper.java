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

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.persistence.common.BaseMapper;
import org.springblade.modules.iot.persistence.entity.OAuth2ClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OAuth2ClientDetails> {

  @Select("SELECT * FROM oauth_client_details WHERE client_id = #{clientId}")
  OAuth2ClientDetails findByClientId(@Param("clientId") String clientId);

  int deleteByClientIds(String[] ids);
}
