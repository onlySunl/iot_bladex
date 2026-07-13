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
import org.springblade.modules.iot.pojo.entity.IoTUser;
import org.springblade.modules.iot.pojo.bo.IoTUserBO;
import java.util.List;
 

public interface IoTUserMapper extends BaseMapper<IoTUser> {

  /**
   * 更新登录时间
   *
   * @param id 用户ID
   */
  int updateLoginDate(Long id);

  List<IoTUser> selectList(IoTUser iotUser);

  /**
   * 根据条件分页查询未已配用户角色列表
   *
   * @param user 用户信息
   * @return 用户信息集合信息
   */
  public List<IoTUser> selectAllocatedList(IoTUserBO user);

  /**
   * 通过用户手机号查询用户
   *
   * @param mobile 用户手机号
   * @return 用户对象信息
   */
  public IoTUser selectUserByMobile(String mobile);

  /**
   * 根据条件分页查询未分配用户角色列表
   *
   * @param user 用户信息
   * @return 用户信息集合信息
   */
  public List<IoTUser> selectUnallocatedList(IoTUserBO user);

  

  int doAccountDisable();
}
