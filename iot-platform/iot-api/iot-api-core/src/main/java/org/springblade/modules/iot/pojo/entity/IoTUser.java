

package org.springblade.modules.iot.pojo.entity;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IoTUser extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 归属组织机构 */
  @TableField(value = "org_id")
  @AutoColumn(comment = "归属组织机构", defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "归属组织机构")
  private Long orgId;

  /** 用户名 */
  @Excel(name = "用户名")
  private String username;

  /** 密码 */
  @JsonIgnore
  @Excel(name = "密码")
  private String password;

  /** 别名 */
  @Excel(name = "别名")
  private String alias;

  /** 用户唯一标识 */
  @Excel(name = "用户唯一标识")
  @TableField(value = "union_id")
  @AutoColumn(comment = "用户唯一标识", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String unionId;

  /** 邮箱 */
  @Excel(name = "邮箱")
  private String email;

  /** 密码加盐 */
  @Excel(name = "密码加盐")
  private String salt;

  /** 手机号 */
  @Excel(name = "手机号")
  private String mobile;

  /** 账号状态（0正常，1停用） */
  @Excel(name = "账号状态（0正常，1停用")

  /** 头像 */
  @Excel(name = "头像")
  private String avatar;

  /** 上级用户唯一id */
  @Excel(name = "上级用户唯一id")
  private String parentUnionId;

  /** 账号身份 0.超级管理员 1.普通用户 2.子用户 */
  @Excel(name = "账号身份 0.超级管理员 1.普通用户 2.子用户")
  private Integer identity;

  /** 登录IP */
  @TableField(value = "login_ip")
  @AutoColumn(comment = "登录IP", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "登录IP")
  private String loginIp;

  /** 登录时间 */
@TableField(value = "login_date")
  @Excel(name = "登录时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date loginDate;

  /** 创建者 */
  @TableField(value = "create_by")
  @AutoColumn(comment = "创建者", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "创建者")

  /** 创建时间 */
@TableField(value = "create_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "创建时间")
  private Date createDate;

  @TableField(value = "update_by")
  @AutoColumn(comment = "updateBy", length = 255, defaultValueType = DefaultValueEnum.NULL)
  @Excel(name = "更新者")

@TableField(value = "update_date")
  @Excel(name = "更新时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateDate;

  /** 备注 */
  @Excel(name = "备注")
  private String remark;

  private String cfg;

  

  /** 0-正常，1-删除 */
  private Integer deleted;

  /** 请求参数 */
  @Builder.Default private Map<String, Object> params = new HashMap<>();

  public IoTUser(Long userId) {
    this.id = userId;
  }

  public boolean isAdmin() {
    return identity != null && identity == 0;
  }

  public boolean viewAllProduct() {
    if (StrUtil.isBlank(cfg)) {
      return true;
    }
    try {
      JSONObject object = JSONUtil.parseObj(cfg);
      return object.getBool("viewAllProduct", true);
    } catch (Exception e) {
      return false;
    }
  }
}
