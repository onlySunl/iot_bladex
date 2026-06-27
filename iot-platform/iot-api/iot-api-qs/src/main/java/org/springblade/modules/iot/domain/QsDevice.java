package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

import java.math.BigDecimal;

/**
 * 视频监控设备对象 qs_device
 * 
 * @author fengcheng
 * @date 2026-03-27
 */
@Data
@TableName("qs_device")
@EqualsAndHashCode(callSuper = true)
@Table(value = "qs_device", comment = "视频监控设备表")
public class QsDevice extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableField(value = "id")
    @AutoColumn(comment = "主键ID", length = 20)
    private Long id;

    /** 设备唯一标识 */
    @TableField(value = "device_code")
    @AutoColumn(comment = "设备唯一标识", length = 100)
    private String deviceCode;

    /** 设备名称 */
    @TableField(value = "device_name")
    @AutoColumn(comment = "设备名称", length = 255)
    private String deviceName;

    /** IP地址 */
    @TableField(value = "ip_address")
    @AutoColumn(comment = "IP地址", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String ipAddress;

    /** 端口号 */
    @TableField(value = "port")
    @AutoColumn(comment = "端口号", length = 10, defaultValueType = DefaultValueEnum.NULL)
    private Integer port;

    /** 用户名 */
    @TableField(value = "user_name")
    @AutoColumn(comment = "用户名", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String userName;

    /** 密码 */
    @TableField(value = "password")
    @AutoColumn(comment = "密码", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String password;

    /** 直播流接入类型(1=RTSP,2=RTMP,3=FLV,4=HLS,5=ONVIF,6=视频文件,7=海康SDK,8=海康ISUP,9=大华SDK,10=宇视SDK,11=天地伟业SDK,12=国标28181,13=PUSH,14=部标1078) */
    @TableField(value = "type")
    @AutoColumn(comment = "直播流接入类型", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String type;

    /** 设备类型(0=IPC, 1=NVR) */
    @TableField(value = "device_type")
    @AutoColumn(comment = "设备类型(0=IPC, 1=NVR)", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer deviceType;

    /** 直播流地址 */
    @TableField(value = "live_address")
    @AutoColumn(comment = "直播流地址", length = 1024, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String liveAddress;

    /** 通道号 */
    @TableField(value = "channel")
    @AutoColumn(comment = "通道号", length = 5, defaultValueType = DefaultValueEnum.NULL)
    private Integer channel;

    /** 报警通道号 */
    @TableField(value = "alarm_channel_id")
    @AutoColumn(comment = "报警通道号", length = 10, defaultValueType = DefaultValueEnum.NULL)
    private Integer alarmChannelId;


    /** 码流类型(1=主码流,2=子码流,3=第三码流) */
    @TableField(value = "stream_type")
    @AutoColumn(comment = "码流类型", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String streamType;

    /** 经度 */
    @TableField(value = "longitude")
    @AutoColumn(comment = "经度", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String longitude;

    /** 纬度 */
    @TableField(value = "latitude")
    @AutoColumn(comment = "纬度", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String latitude;

    /** 国标编码 */
    @TableField(value = "gb_code")
    @AutoColumn(comment = "国标编码", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbCode;

    /** 传输协议(UDP/TCP) */
    @TableField(value = "protocol")
    @AutoColumn(comment = "传输协议(UDP/TCP)", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String protocol;

    /** 设备状态(OFFLINE=离线,ON=在线) */
    @TableField(value = "device_status")
    @AutoColumn(comment = "设备状态(OFFLINE=离线,ON=在线)", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceStatus;

    /** 上线类型(1=主动添加, 2=主动注册) */
    @TableField(value = "online_type")
    @AutoColumn(comment = "上线类型", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String onlineType;

    /** 开启音频(0=关闭, 1=开启) */
    @TableField(value = "enable_audio")
    @AutoColumn(comment = "开启音频(0=关闭, 1=开启)", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String enableAudio;

    /** 开启mp4录制(0=关闭, 1=开启) */
    @TableField(value = "enable_mp4")
    @AutoColumn(comment = "开启mp4录制(0=关闭, 1=开启)", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String enableMp4;

    /** 流状态(0=停止,1=直播中) */
    @TableField(value = "stream_status")
    @AutoColumn(comment = "流状态(0=停止,1=直播中)", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String streamStatus;

    /** 当前拉流使用的流媒体服务ID */
    @TableField(value = "media_serverId")
    @AutoColumn(comment = "当前拉流使用的流媒体服务ID", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String mediaServerId;

    /** 拉流代理时zlm返回的key，用于停止拉流代理 */
    @TableField(value = "stream_key")
    @AutoColumn(comment = "拉流代理时zlm返回的key", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String streamKey;

    /** 是否 无人观看时自动停用 */
    @TableField(value = "enable_disable_none_reader")
    @AutoColumn(comment = "无人观看时自动停用", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String enableDisableNoneReader;

    /** 截图路径 */
    @TableField(value = "snap")
    @AutoColumn(comment = "截图路径", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String snap;

    /** flv 类型（ws/flv） */
    @TableField(value = "flv_type")
    @AutoColumn(comment = "flv类型（ws/flv）", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String flvType;

    /** omvif 验证类型（1=WS-UsernameToken,2=Digest */
    @TableField(value = "onvif_auth")
    @AutoColumn(comment = "onvif验证类型", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String onvifAuth;

    /** onvif 主机名 */
    @TableField(value = "onvif_host_name")
    @AutoColumn(comment = "onvif主机名", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String onvifHostName;

    /** 录制计划id */
    @TableField(value = "record_plan_id")
    @AutoColumn(comment = "录制计划id", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long recordPlanId;

    /** 国标-行政区域 */
    @TableField(value = "gb_civil_code")
    @AutoColumn(comment = "国标-行政区域", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbCivilCode;

    /** 国标-义务分组 */
    @TableField(value = "gb_business_group_id")
    @AutoColumn(comment = "国标-义务分组", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbBusinessGroupId;

    /** 国标-父节点ID */
    @TableField(value = "gb_parent_id")
    @AutoColumn(comment = "国标-父节点ID", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbParentId;

    /** 生产厂商 */
    @TableField(value = "manufacturer")
    @AutoColumn(comment = "生产厂商", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String manufacturer;

    /** 安装地址 */
    @TableField(value = "address")
    @AutoColumn(comment = "安装地址", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String address;

    /**
     * 摄像机结构类型,标识摄像机类型: 1-球机; 2-半球; 3-固定枪机; 4-遥控枪机;5-遥控半球;6-多目设备的全景/拼接通道;7-多目设备的分割通道
     */
    @TableField(value = "ptz_type")
    @AutoColumn(comment = "摄像机结构类型", length = 10, defaultValueType = DefaultValueEnum.NULL)
    private Integer ptzType;

    /**
     * gb设备id
     */
    @TableField(value = "gb_device_id")
    @AutoColumn(comment = "gb设备id", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbDeviceId;

    /**
     * gb通道id
     */
    @TableField(value = "gb_channel_id")
    @AutoColumn(comment = "gb通道id", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbChannelId;

    /**
     * 数据流传输模式
     * UDP:udp传输
     * TCP-ACTIVE：tcp主动模式
     * TCP-PASSIVE：tcp被动模式
     */
    @TableField(value = "stream_mode")
    @AutoColumn(comment = "数据流传输模式", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String streamMode;

    /** JT1078-手机号 */
    @TableField(value = "jt_mobile_no")
    @AutoColumn(comment = "JT1078-手机号", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String jtMobileNo;

    /** JT1078-车牌号 */
    @TableField(value = "jt_plate_no")
    @AutoColumn(comment = "JT1078-车牌号", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String jtPlateNo;

    /** JT1078-车牌颜色 */
    @TableField(value = "jt_plate_color")
    @AutoColumn(comment = "JT1078-车牌颜色", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String jtPlateColor;

    /** 回放流状态(0=停止,1=回放中) */
    @TableField(value = "playback_stream_status")
    @AutoColumn(comment = "回放流状态(0=停止,1=回放中)", length = 10, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String playbackStreamStatus;

    /** 回放使用的流媒体服务ID */
    @TableField(value = "playback_media_server_id")
    @AutoColumn(comment = "回放使用的流媒体服务ID", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String playbackMediaServerId;

    /** 回放时zlm返回的key，用于停止回放 */
    @TableField(value = "playback_stream_key")
    @AutoColumn(comment = "回放时zlm返回的key", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String playbackStreamKey;

    /** 国标-设备厂商 */
    @TableField(value = "gb_manufacturer")
    @AutoColumn(comment = "国标-设备厂商", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbManufacturer;

    /** 国标-设备型号 */
    @TableField(value = "gb_model")
    @AutoColumn(comment = "国标-设备型号", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbModel;

    /** 国标-设备归属 */
    @TableField(value = "gb_owner")
    @AutoColumn(comment = "国标-设备归属", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbOwner;

    /** 国标-警区 */
    @TableField(value = "gb_block")
    @AutoColumn(comment = "国标-警区", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbBlock;

    /** 国标-安装地址 */
    @TableField(value = "gb_address")
    @AutoColumn(comment = "国标-安装地址", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbAddress;

    /** 国标-是否有子设备(必选)1有,0没有 */
    @TableField(value = "gb_parental")
    @AutoColumn(comment = "国标-是否有子设备", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbParental;

    /** 国标-信令安全模式 */
    @TableField(value = "gb_safety_way")
    @AutoColumn(comment = "国标-信令安全模式", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbSafetyWay;

    /** 国标-注册方式 */
    @TableField(value = "gb_register_way")
    @AutoColumn(comment = "国标-注册方式", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbRegisterWay;

    /** 国标-证书序列号 */
    @TableField(value = "gb_cert_num")
    @AutoColumn(comment = "国标-证书序列号", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbCertNum;

    /** 国标-证书有效标识, 缺省为0;证书有效标识:0:无效 1:有效 */
    @TableField(value = "gb_certifiable")
    @AutoColumn(comment = "国标-证书有效标识", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbCertifiable;

    /** 国标-无效原因码(有证书且证书无效的设备必选) */
    @TableField(value = "gb_err_code")
    @AutoColumn(comment = "国标-无效原因码", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbErrCode;

    /** 国标-证书终止有效期(有证书且证书无效的设备必选) */
    @TableField(value = "gb_end_time")
    @AutoColumn(comment = "国标-证书终止有效期", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbEndTime;

    /** 国标-保密属性(必选)缺省为0;0-不涉密,1-涉密 */
    @TableField(value = "gb_secrecy")
    @AutoColumn(comment = "国标-保密属性", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbSecrecy;

    /** 国标-设备/系统IPv4/IPv6地址 */
    @TableField(value = "gb_ip_address")
    @AutoColumn(comment = "国标-设备IP地址", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbIpAddress;

    /** 国标-设备/系统端口 */
    @TableField(value = "gb_port")
    @AutoColumn(comment = "国标-设备端口", length = 10, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbPort;

    /** 国标-设备口令 */
    @TableField(value = "gb_password")
    @AutoColumn(comment = "国标-设备口令", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbPassword;

    /** 国标-设备状态 */
    @TableField(value = "gb_status")
    @AutoColumn(comment = "国标-设备状态", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbStatus;

    /** 国标-经度 WGS-84坐标系 */
    @TableField(value = "gb_longitude_double")
    @AutoColumn(comment = "国标-经度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double gbLongitudeDouble;

    /** 国标-纬度 WGS-84坐标系 */
    @TableField(value = "gb_latitude_double")
    @AutoColumn(comment = "国标-纬度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double gbLatitudeDouble;

    /** 国标-摄像机位置类型扩展 */
    @TableField(value = "gb_position_type")
    @AutoColumn(comment = "国标-摄像机位置类型", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbPositionType;

    /** 国标-摄像机安装位置室外、室内属性 */
    @TableField(value = "gb_room_type")
    @AutoColumn(comment = "国标-摄像机安装位置属性", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbRoomType;

    /** 国标-用途属性， 1-治安、2=交通、3-重点 */
    @TableField(value = "gb_use_type")
    @AutoColumn(comment = "国标-用途属性", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbUseType;

    /** 国标-摄像机补光属性 */
    @TableField(value = "gb_supply_light_type")
    @AutoColumn(comment = "国标-摄像机补光属性", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbSupplyLightType;

    /** 国标-摄像机监视方位(光轴方向)属性 */
    @TableField(value = "gb_direction_type")
    @AutoColumn(comment = "国标-摄像机监视方位", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbDirectionType;

    /** 国标-摄像机支持的分辨率,可多值 */
    @TableField(value = "gb_resolution")
    @AutoColumn(comment = "国标-摄像机支持的分辨率", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbResolution;

    /** 国标-下载倍速(可选),可多值 */
    @TableField(value = "gb_download_speed")
    @AutoColumn(comment = "国标-下载倍速", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gbDownloadSpeed;

    /** 国标-空域编码能力 */
    @TableField(value = "gb_svc_space_support_mod")
    @AutoColumn(comment = "国标-空域编码能力", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbSvcSpaceSupportMod;

    /** 国标-时域编码能力 */
    @TableField(value = "gb_svc_time_support_mode")
    @AutoColumn(comment = "国标-时域编码能力", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer gbSvcTimeSupportMode;

    /** GPS高度 */
    @TableField(value = "gps_altitude")
    @AutoColumn(comment = "GPS高度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double gpsAltitude;

    /** GPS速度 */
    @TableField(value = "gps_speed")
    @AutoColumn(comment = "GPS速度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double gpsSpeed;

    /** GPS方向 */
    @TableField(value = "gps_direction")
    @AutoColumn(comment = "GPS方向", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double gpsDirection;

    /** GPS更新时间 */
    @TableField(value = "gps_time")
    @AutoColumn(comment = "GPS更新时间", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String gpsTime;

    /** 是否支持对讲 1支持,0不支持 */
    @TableField(value = "enable_broadcast")
    @AutoColumn(comment = "是否支持对讲", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer enableBroadcast;

    /** 目录订阅状态 0=未订阅，1=订阅 */
    @TableField(value = "subscribe_catalog_status")
    @AutoColumn(comment = "目录订阅状态", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer subscribeCatalogStatus;

    /** 报警订阅状态 0=未订阅，1=订阅 */
    @TableField(value = "subscribe_alarm_status")
    @AutoColumn(comment = "报警订阅状态", length = 2, defaultValueType = DefaultValueEnum.NULL)
    private Integer subscribeAlarmStatus;

    /** 订阅时间 */
    @TableField(value = "subscribe_time")
    @AutoColumn(comment = "订阅时间", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String subscribeTime;
}
