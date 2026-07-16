
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
 * 流媒体服务器配置 DO
 *
 * @author EnjoyIot
 */
@TableName("media_server")
// @KeySequence("media_server_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaServerDO extends CustomBaseEntity {

    /**
     * 服务器标识
     */
    @AutoColumn(comment = "服务器标识")
    @TableField("server_id")
    private String serverId;
    /**
     * 租户名称
     */
    @AutoColumn(comment = "租户名称")
    @TableField("tenant_name")
    private String tenantName;
    /**
     * 使能开关
     */
    @AutoColumn(comment = "使能开关")
    @TableField("enabled")
    private Boolean enabled;
    /**
     * 默认播放协议
     */
    @AutoColumn(comment = "默认播放协议")
    @TableField("protocol")
    private String protocol;
    /**
     * 服务器ip
     */
    @AutoColumn(comment = "服务器ip")
    @TableField("ip")
    private String ip;
    /**
     * 服务器域名
     */
    @AutoColumn(comment = "服务器域名")
    @TableField("domain")
    private String domain;
    /**
     * 回调服务器地址
     */
    @AutoColumn(comment = "回调服务器地址")
    @TableField("hookurl")
    private String hookurl;
    /**
     * 流媒体密钥
     */
    @AutoColumn(comment = "流媒体密钥")
    @TableField("secret")
    private String secret;
    /**
     * http端口
     */
    @AutoColumn(comment = "http端口")
    @TableField("port_http")
    private Integer portHttp;
    /**
     * https端口
     */
    @AutoColumn(comment = "https端口")
    @TableField("port_https")
    private Integer portHttps;
    /**
     * rtmp端口
     */
    @AutoColumn(comment = "rtmp端口")
    @TableField("port_rtmp")
    private Integer portRtmp;
    /**
     * rtsp端口
     */
    @AutoColumn(comment = "rtsp端口")
    @TableField("port_rtsp")
    private Integer portRtsp;
    /**
     * RTP收流端口
     */
    @AutoColumn(comment = "RTP收流端口")
    @TableField("rtp_proxy_port")
    private Integer rtpProxyPort;
    /**
     * 是否使用多端口模式
     */
    @AutoColumn(comment = "是否使用多端口模式")
    @TableField("rtp_enable")
    private Boolean rtpEnable;
    /**
     * rtp端口范围
     */
    @AutoColumn(comment = "rtp端口范围")
    @TableField("rtp_port_range")
    private String rtpPortRange;
    /**
     * 录像服务端口
     */
    @AutoColumn(comment = "录像服务端口")
    @TableField("record_port")
    private Integer recordPort;
    /**
     * 是否自动同步配置ZLM
     */
    @AutoColumn(comment = "是否自动同步配置ZLM")
    @TableField("auto_config")
    private Boolean autoConfig;
    /**
     * ws端口
     */
    @AutoColumn(comment = "ws端口")
    @TableField("port_ws")
    private Integer portWs;

}
