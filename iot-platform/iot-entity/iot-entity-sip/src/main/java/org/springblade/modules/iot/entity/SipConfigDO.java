
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.entity;

import com.tangzc.mybatisflex.autotable.annotations.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * sip系统配置 DO
 *
 * @author EnjoyIot
 */
@TableName("sip_config")
// @KeySequence("sip_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SipConfigDO extends CustomBaseEntity {

    /**
     * 产品ID
     */
    @AutoColumn(comment = "产品ID")
    @TableField("product_key")
    private String productKey;
    /**
     * 产品名称
     */
    @AutoColumn(comment = "产品名称")
    @TableField("product_name")
    private String productName;
    /**
     * 使能开关
     */
    @AutoColumn(comment = "使能开关")
    @TableField("enabled")
    private Integer enabled;
    /**
     * 系统默认配置
     */
    @AutoColumn(comment = "系统默认配置")
    @TableField("isdefault")
    private Integer isdefault;
    /**
     * 拓展sdp
     */
    @AutoColumn(comment = "拓展sdp")
    @TableField("senior_sdp")
    private Integer seniorSdp;
    /**
     * 服务器域
     */
    @AutoColumn(comment = "服务器域")
    @TableField("domain")
    private String domain;
    /**
     * 服务器sipid
     */
    @AutoColumn(comment = "服务器sipid")
    @TableField("server_sipid")
    private String serverSipid;
    /**
     * sip认证密码
     */
    @AutoColumn(comment = "sip认证密码")
    @TableField("password")
    private String password;
    /**
     * sip接入IP
     */
    @AutoColumn(comment = "sip接入IP")
    @TableField("ip")
    private String ip;
    /**
     * sip接入端口号
     */
    @AutoColumn(comment = "sip接入端口号")
    @TableField("port")
    private Long port;

}
