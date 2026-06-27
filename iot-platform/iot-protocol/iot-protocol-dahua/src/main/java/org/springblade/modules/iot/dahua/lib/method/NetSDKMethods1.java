package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 1 部分
 */
public interface NetSDKMethods1 {
    public static class SdkStructure extends Structure {
        @Override
        protected  List<String> getFieldOrder(){
            List<String> fieldOrderList = new ArrayList<String>();
            for (Class<?> cls = getClass();
                 !cls.equals(SdkStructure.class);
                 cls = cls.getSuperclass()) {
                Field[] fields = cls.getDeclaredFields();
                int modifiers;
                for (Field field : fields) {
                    modifiers = field.getModifiers();
                    if (Modifier.isStatic(modifiers) || !Modifier.isPublic(modifiers)) {
                        continue;
                    }
                    fieldOrderList.add(field.getName());
                }
            }
            //            System.out.println(fieldOrderList);

            return fieldOrderList;
        }

        @Override
        public int fieldOffset(String name){
            return super.fieldOffset(name);
        }
    }

        /************************************************************************
    ** 常量定义
    ***********************************************************************/
    public static final int POINTERSIZE                         = new PointerSize().size(); // JNA指针长度
    public static final int MAX_INSIDEOBJECT_NUM                = 32;           // 最大包裹内物品个数
    public static final int NET_SERIALNO_LEN                    = 48;           // 设备序列号字符长度
    public static final int NET_CFG_Max_VideoColor              = 24;           // 每个通道最大视频输入颜色配置数量
    public static final int NET_CFG_Custom_Title_Len            = 1024;         // 自定义标题名称长度(扩充到1024)
    public static final int NET_CFG_Custom_TitleType_Len        = 32;           // 自定义标题类型长度
    public static final int NET_CFG_Max_Video_Widget_Cover      = 16;           // 编码区域覆盖最大数量
    public static final int NET_CFG_Max_Video_Widget_Custom_Title = 8;          // 编码物件自定义标题最大数量
    public static final int NET_CFG_Max_Video_Widget_Sensor_Info = 2;           // 编码物件叠加传感器信息的最大数目
    public static final int NET_CFG_Max_Description_Num         = 4;            // 叠加区域描述信息的最大个数
    public static final int NET_GATEWAY_MAX_SIM_NUM             = 8;            // 网关SIM卡最大个数
    public static final int NET_MAX_MAIL_ADDR_LEN               = 128;          // 邮件发(收)地址最大长度
    public static final int NET_MAX_MAIL_SUBJECT_LEN            = 64;           // 邮件主题最大长度
    public static final int NET_MAX_IPADDR_LEN                  = 16;           // IP地址字符串长度
    public static final int NET_MAX_IPADDR_LEN_EX               = 40;           // 扩展IP地址字符串长度, 支持IPV6
    public static final int NET_USER_NAME_LEN_EX                = 32;           // 用户名长度,用于新平台扩展
    public static final int NET_USER_PSW_LEN_EX                 = 32;           // 用户密码长度,用于新平台扩展
    public static final int NET_MAX_DEV_ID_LEN                  = 48;           // 机器编号最大长度
    public static final int NET_MAX_HOST_NAMELEN                = 64;           // 主机名长度,
    public static final int NET_MAX_HOST_PSWLEN                 = 32;           // 密码长度
    public static final int NET_MAX_ETHERNET_NUM                = 2;            // 以太网口最大个数
    public static final int NET_MAX_ETHERNET_NUM_EX             = 10;           // 扩展以太网口最大个数
    public static final int NET_DEV_CLASS_LEN                   = 16;           // 设备类型字符串（如"IPC"）长度
    public static final int NET_N_WEEKS                         = 7;            // 一周的天数
    public static final int NET_N_TSECT                         = 6;            // 通用时间段个数
    public static final int NET_N_REC_TSECT                     = 6;            // 录像时间段个数
    public static final int NET_N_COL_TSECT                     = 2;            // 颜色时间段个数
    public static final int NET_N_ENCODE_AUX                    = 3;            // 扩展码流个数
    public static final int NET_N_TALK                          = 1;            // 最多对讲通道个数
    public static final int NET_N_COVERS                        = 1;            // 遮挡区域个数
    public static final int NET_N_CHANNEL                       = 16;           // 最大通道个数
    public static final int NET_N_ALARM_TSECT                   = 2;            // 报警提示时间段个数
    public static final int NET_MAX_ALARMOUT_NUM                = 16;           // 报警输出口个数上限
    public static final int NET_MAX_AUDIO_IN_NUM                = 16;           // 音频输入口个数上限
    public static final int NET_MAX_VIDEO_IN_NUM                = 16;           // 视频输入口个数上限
    public static final int NET_MAX_ALARM_IN_NUM                = 16;           // 报警输入口个数上限
    public static final int NET_MAX_DISK_NUM                    = 16;           // 硬盘个数上限,暂定为16
    public static final int NET_MAX_DECODER_NUM                 = 16;           // 解码器(485)个数上限
    public static final int NET_MAX_232FUNCS                    = 10;           // 232串口功能个数上限
    public static final int NET_MAX_232_NUM                     = 2;            // 232串口个数上限
    public static final int NET_MAX_232_NUM_EX                  = 16;           // 扩展串口配置个数上限
    public static final int NET_MAX_DECPRO_LIST_SIZE            = 100;          // 解码器协议列表个数上限
    public static final int NET_FTP_MAXDIRLEN                   = 240;          // FTP文件目录最大长度
    public static final int NET_MATRIX_MAXOUT                   = 16;           // 矩阵输出口最大个数
    public static final int NET_TOUR_GROUP_NUM                  = 6;            // 矩阵输出组最大个数
    public static final int NET_MAX_DDNS_NUM                    = 10;           // 设备支持的ddns服务器最大个数
    public static final int NET_MAX_SERVER_TYPE_LEN             = 32;           // ddns服务器类型,最大字符串长度
    public static final int NET_MAX_DOMAIN_NAME_LEN             = 256;          // ddns域名,最大字符串长度
    public static final int NET_MAX_DDNS_ALIAS_LEN              = 32;           // ddns服务器别名,最大字符串长度
    public static final int NET_MAX_DEFAULT_DOMAIN_LEN          = 60;           // ddns默认域名,最大字符串长度
    public static final int NET_MOTION_ROW                      = 32;           // 动态检测区域的行数
    public static final int NET_MOTION_COL                      = 32;           // 动态检测区域的列数
    public static final int NET_STATIC_ROW                      = 32;           // 静态检测区域的行数
    public static final int NET_STATIC_COL                      = 32;           // 静态检测区域的列数
    public static final int NET_FTP_USERNAME_LEN                = 64;           // FTP配置,用户名最大长度
    public static final int NET_FTP_PASSWORD_LEN                = 64;           // FTP配置,密码最大长度
    public static final int NET_TIME_SECTION                    = 2;            // FTP配置,每天时间段个数
    public static final int NET_FTP_MAX_PATH                    = 240;          // FTP配置,文件路径名最大长度
    public static final int NET_FTP_MAX_SUB_PATH                = 128;          // FTP配置,文件路径名最大长度
    public static final int NET_INTERVIDEO_UCOM_CHANID          = 32;           // 平台接入配置,U网通通道ID
    public static final int NET_INTERVIDEO_UCOM_DEVID           = 32;           // 平台接入配置,U网通设备ID
    public static final int NET_INTERVIDEO_UCOM_REGPSW          = 16;           // 平台接入配置,U网通注册密码
    public static final int NET_INTERVIDEO_UCOM_USERNAME        = 32;           // 平台接入配置,U网通用户名
    public static final int NET_INTERVIDEO_UCOM_USERPSW         = 32;           // 平台接入配置,U网通密码
    public static final int NET_INTERVIDEO_NSS_IP               = 32;           // 平台接入配置,力维IP
    public static final int NET_INTERVIDEO_NSS_SERIAL           = 32;           // 平台接入配置,力维serial
    public static final int NET_INTERVIDEO_NSS_USER             = 32;           // 平台接入配置,力维user
    public static final int NET_INTERVIDEO_NSS_PWD              = 50;           // 平台接入配置,力维password
    public static final int NET_MAX_VIDEO_COVER_NUM             = 16;           // 遮挡区域最大个数
    public static final int NET_MAX_WATERMAKE_DATA              = 4096;         // 水印图片数据最大长度
    public static final int NET_MAX_WATERMAKE_LETTER            = 128;          // 水印文字最大长度
    public static final int NET_MAX_WLANDEVICE_NUM              = 10;           // 最多搜索出的无线设备个数
    public static final int NET_MAX_WLANDEVICE_NUM_EX           = 32;           // 最多搜索出的无线设备个数
    public static final int NET_MAX_ALARM_NAME                  = 64;           // 地址长度
    public static final int NET_MAX_REGISTER_SERVER_NUM         = 10;           // 主动注册服务器个数
    public static final int NET_SNIFFER_FRAMEID_NUM             = 6;            // 6个FRAME ID 选项
    public static final int NET_SNIFFER_CONTENT_NUM             = 4;            // 每个FRAME对应的4个抓包内容
    public static final int NET_SNIFFER_CONTENT_NUM_EX          = 8;            // 每个FRAME对应的8个抓包内容
    public static final int NET_SNIFFER_PROTOCOL_SIZE           = 20;           // 协议名字长度
    public static final int NET_MAX_PROTOCOL_NAME_LENGTH        = 20;
    public static final int NET_SNIFFER_GROUP_NUM               = 4;            // 4组抓包设置
    public static final int NET_ALARM_OCCUR_TIME_LEN            = 40;           // 新的报警上传时间的长度
    public static final int NET_VIDEO_OSD_NAME_NUM              = 64;           // 叠加的名称长度,目前支持32个英文,16个中文
    public static final int NET_VIDEO_CUSTOM_OSD_NUM            = 8;            // 支持的自定义叠加的数目,不包含时间和通道
    public static final int NET_VIDEO_CUSTOM_OSD_NUM_EX         = 256;          // 支持的自定义叠加的数目,不包含时间和通道
    public static final int NET_CONTROL_AUTO_REGISTER_NUM       = 100;          // 支持定向主动注册服务器的个数
    public static final int NET_MMS_RECEIVER_NUM                = 100;          // 支持短信接收者的个数
    public static final int NET_MMS_SMSACTIVATION_NUM           = 100;          // 支持短信发送者的个数
    public static final int NET_MMS_DIALINACTIVATION_NUM        = 100;          // 支持拨号发送者的个数
    public static final int NET_MAX_ALARM_IN_NUM_EX             = 32;           // 报警输入口个数上限
    public static final int NET_MAX_IPADDR_OR_DOMAIN_LEN        = 64;           // IP地址字符串长度
    public static final int NET_MAX_CALLID                      = 32;           // 呼叫ID
    public static final int DH_MAX_OBJECT_NUM                   = 32;           // 最大object个数
    public static final int NET_MAX_OBJECT_NUM                  = 32;           // 最大object个数
    public static final int NET_MAX_FENCE_LINE_NUM              = 2;            // 围栏最大曲线数
    public static final int MAX_SMART_VALUE_NUM                 = 30;           // 最多的smart信息个数
    public static final int NET_INTERVIDEO_AMP_DEVICESERIAL     = 48;           // 平台接入配置,天地阳光 设备序列号字符串长度
    public static final int NET_INTERVIDEO_AMP_DEVICENAME       = 16;           // 平台接入配置,天地阳光 设备名称字符串长度
    public static final int NET_INTERVIDEO_AMP_USER             = 32;           // 平台接入配置,天地阳光 注册用户名字符串长度
    public static final int NET_INTERVIDEO_AMP_PWD              = 32;           // 平台接入配置,天地阳光 注册密码字符串长度
    public static final int MAX_SUBMODULE_NUM                   = 32;           // 最多子模块信息个数
    public static final int NET_MAX_CARWAY_NUM                  = 8;            // 交通抓拍,最大车道数
    public static final int NET_MAX_SNAP_SIGNAL_NUM             = 3;            // 一个车道的最大抓拍张数
    public static final int NET_MAX_CARD_NUM                    = 128;          // 卡号的最大个数
    public static final int NET_MAX_CARDINFO_LEN                = 32;           // 每条卡号最长字符数
    public static final int NET_MAX_CONTROLER_NUM               = 64;           // 最大支持控制器数目
    public static final int NET_MAX_LIGHT_NUM                   = 32;           // 最多控制灯组数
    public static final int NET_MAX_SNMP_COMMON_LEN             = 64;           // snmp 读写数据长度
    public static final int NET_MAX_DDNS_STATE_LEN              = 128;          // DDNS 状态信息长度
    public static final int NET_MAX_PHONE_NO_LEN                = 16;           // 电话号码长度
    public static final int NET_MAX_MSGTYPE_LEN                 = 32;           // 导航类型或短信息类型长度
    public static final int NET_MAX_MSG_LEN                     = 256;          // 导航和短信息长度
    public static final int NET_MAX_GRAB_INTERVAL_NUM           = 4;            // 多张图片抓拍个数
    public static final int NET_MAX_FLASH_NUM                   = 5;            // 最多支持闪光灯个数
    public static final int NET_MAX_ISCSI_PATH_NUM              = 64;           // ISCSI远程目录最大数量
    public static final int NET_MAX_WIRELESS_CHN_NUM            = 256;          // 无线路由最大信道数
    public static final int NET_PROTOCOL3_BASE                  = 100;          // 三代协议版本基数
    public static final int NET_PROTOCOL3_SUPPORT               = 11;           // 只支持3代协议
    public static final int NET_MAX_STAFF_NUM                   = 20;           // 浓缩视频配置信息中标尺数上限
    public static final int NET_MAX_CALIBRATEBOX_NUM            = 10;           // 浓缩视频配置信息中标定区域数上限
    public static final int NET_MAX_EXCLUDEREGION_NUM           = 10;           // 浓缩视频配置信息中排除区域数上限
    public static final int NET_MAX_POLYLINE_NUM                = 20;           // 浓缩视频配置信息中标尺线数
    public static final int NET_MAX_COLOR_NUM                   = 16;           // 最大颜色数目
    public static final int MAX_OBJFILTER_NUM                   = 16;           // 最大过滤种类个数
    public static final int NET_MAX_SYNOPSIS_STATE_NAME         = 64;           // 视频浓缩状态字符串长度
    public static final int NET_MAX_SYNOPSIS_QUERY_FILE_COUNT   = 10;           // 视频浓缩相关原始文件按照路径查找时文件个数上限
    public static final int NET_MAX_SSID_LEN                    = 36;           // SSID长度
    public static final int NET_MAX_APPIN_LEN                   = 16;           // PIN码长度
    public static final int NET_NETINTERFACE_NAME_LEN           = 260;          // 网口名称长度
    public static final int NET_NETINTERFACE_TYPE_LEN           = 260;          // 网络类型长度
    public static final int NET_MAX_CONNECT_STATUS_LEN          = 260;          // 连接状态字符串长度
    public static final int NET_MAX_MODE_LEN                    = 64;           // 3G支持的网络模式长度
    public static final int NET_MAX_MODE_NUM                    = 64;           // 3G支持的网络模式个数
    public static final int NET_MAX_COMPRESSION_TYPES_NUM       = 16;           // 视频编码格式最多种类个数
    public static final int NET_MAX_CAPTURE_SIZE_NUM            = 64;           // 视频分辨率个数
    public static final int NET_NODE_NAME_LEN                   = 64;           // 组织结构节点名称长度
    public static final int MAX_CALIBPOINTS_NUM                 = 256;          // 支持最大标定点数
    public static final int NET_MAX_ATTR_NUM                    = 32;           // 显示单元属性最大数量
    public static final int NET_MAX_CLOUDCONNECT_STATE_LEN      = 128;          // 云注册连接状态信息长度
    public static final int NET_MAX_IPADDR_EX_LEN               = 128;          // 扩展IP地址最大长度
    public static final int MAX_EVENT_NAME                      = 128;          // 最长事件名
    public static final int NET_MAX_ETH_NAME                    = 64;           // 最大网卡名
    public static final int NET_N_SCHEDULE_TSECT                = 8;            // 时间表元素个数
    public static final int NET_MAX_URL_NUM                     = 8;            // URL最大个数
    public static final int NET_MAX_LOWER_MITRIX_NUM            = 16;           // 最大下位矩阵数
    public static final int NET_MAX_BURN_CHANNEL_NUM            = 32;           // 最大刻录通道数
    public static final int NET_MAX_NET_STRORAGE_BLOCK_NUM      = 64;           // 最大远程存储区块数量
    public static final int NET_MAX_CASE_PERSON_NUM             = 32;           // 案件人员最大数量
    public static final int NET_MAX_MULTIPLAYBACK_CHANNEL_NUM   = 64;           // 最大多通道预览回放通道数
    public static final int NET_MAX_MULTIPLAYBACK_SPLIT_NUM     = 32;           // 最大多通道预览回放分割模式数
    public static final int NET_MAX_AUDIO_ENCODE_TYPE           = 64;           // 最大语音编码类型个数
    public static final int MAX_CARD_RECORD_FIELD_NUM           = 16;           // 卡号录像最大域数量
    public static final int NET_BATTERY_NUM_MAX                 = 16;           // 最大电池数量
    public static final int NET_POWER_NUM_MAX                   = 16;           // 最大电源数量
    public static final int NET_MAX_AUDIO_PATH                  = 260;          // 最大音频文件路长度
    public static final int NET_MAX_DOORNAME_LEN                = 128;          // 最大门禁名称长度
    public static final int NET_MAX_CARDPWD_LEN                 = 64;           // 最大门禁名称长度
    public static final int NET_MAX_FISHEYE_MOUNTMODE_NUM       = 4;            // 最大鱼眼安装模式个数
    public static final int NET_MAX_FISHEYE_CALIBRATEMODE_NUM   = 16;           // 最大鱼眼矫正模式个数
    public static final int NET_MAX_FISHEYE_EPTZCMD_NUM         = 64;           // 最大鱼眼电子云台操作个数
    public static final int POINT_NUM_IN_PAIR                   = 2;            // 标定点对中的点数量
    public static final int MAX_POINT_PAIR_NUM                  = 128;          // 标定点最大数量
    public static final int CHANNEL_NUM_IN_POINT_GROUP          = 2;            // 标定点中的视频通道数
    public static final int MAX_POINT_GROUP_NUM                 = 32;           // 标定点组最大数量, 每两个通道进行拼接需要一组标定点
    public static final int MAX_LANE_INFO_NUM                   = 32;           // 最大车道信息数
    public static final int MAX_LANE_DIRECTION_NUM              = 8;            // 车道方向总数
    public static final int NET_MAX_MONITORWALL_NUM             = 32;           // 电视墙最大数量
    public static final int NET_MAX_OPTIONAL_URL_NUM            = 8;            // 备用url最大数量
    public static final int NET_MAX_CAMERA_CHANNEL_NUM          = 1024;         // 最大摄像机通道数
    public static final int MAX_FILE_SUMMARY_NUM                = 32;           // 最大文件摘要数
    public static final int MAX_AUDIO_ENCODE_NUM                = 64;           // 最大支持音频编码个数
    public static final int MAX_FLASH_LIGHT_NUM                 = 8;            // 最大支持的爆闪灯(闪光灯)个数
    public static final int MAX_STROBOSCOPIC_LIGHT_NUM          = 8;            // 最大支持的频闪灯个数
    public static final int MAX_MOSAIC_NUM                      = 8;            // 最大支持的马赛克数量
    public static final int MAX_MOSAIC_CHANNEL_NUM              = 256;          // 支持马赛克叠加的最多通道数量
    public static final int MAX_FIREWARNING_INFO_NUM            = 4;            // 最大热成像着火点报警信息个数
    public static final int MAX_AXLE_NUM                        = 8;            // 最大车轴数量
    public static final int MAX_ACCESSDOOR_NUM                  = 128;          // 最大门数量
    public static final int MAX_SIMILARITY_COUNT                = 1024;         // 最大人脸对比库阈值个数
    public static final int MAX_FEATURESTATE_NUM                = 4;            // 最大人脸组建模状态个数
    public static final int NET_MAX_BULLET_HOLES                = 10;           // 最大的弹孔数
    public static final int MAX_NTP_SERVER                      = 4;            // 最大备用NTP服务器地址
    public static final int MAX_PLATE_NUM                       = 64;           // 每张图片中包含的最大车牌个数
    public static final int MAX_PREVIEW_CHANNEL_NUM             = 64;           // 最大导播预览的通道数量
    public static final int MAX_ADDRESS_LEN                     = 256;          // 最大的地址长度
    public static final int MAX_DNS_SERVER_NUM                  = 2;            // DNS最大数量
    public static final int MAX_NETWORK_INTERFACE_NUM           = 32;           // 最大网卡数量
    public static final int MAX_EVENT_RESTORE_UUID              = 36;           // 事件重传uuid数组大小
    public static final int MAX_EVENT_RESTORE_CODE_NUM          = 8;            // 最大事件重传类型个数
    public static final int MAX_EVENT_RESOTER_CODE_TYPE         = 32;           // 事件重传类型数组大小
    public static final int MAX_SNAP_TYPE                       = 3;            // 抓图类型数量
    public static final int MAX_MAINFORMAT_NUM                  = 4;            // 最大支持主码流类型数量
    public static final int CUSTOM_TITLE_LEN                    = 1024;         // 自定义标题名称长度(扩充到1024)
    public static final int MAX_CUSTOM_TITLE_NUM                = 8;            // 编码物件自定义标题最大数量
    public static final int FORMAT_TYPE_LEN                     = 16;           // 编码类型名最大长度
    public static final int MAX_CHANNEL_NAME_LEN                = 256;          // 通道名称最大长度
    public static final int MAX_VIRTUALINFO_DOMAIN_LEN          = 64;           // 虚拟身份上网域名长度
    public static final int MAX_VIRTUALINFO_TITLE_LEN           = 64;           // 虚拟身份上网标题长度
    public static final int MAX_VIRTUALINFO_USERNAME_LEN        = 32;           // 虚拟身份用户名长度
    public static final int MAX_VIRTUALINFO_PASSWORD_LEN        = 32;           // 虚拟身份密码长度
    public static final int MAX_VIRTUALINFO_PHONENUM_LEN        = 12;           // 虚拟身份手机号长度
    public static final int MAX_VIRTUALINFO_IMEI_LEN            = 16;           // 虚拟身份国际移动设备标识长度
    public static final int MAX_VIRTUALINFO_IMSI_LEN            = 16;           // 虚拟身份国际移动用户识别码长度
    public static final int MAX_VIRTUALINFO_LATITUDE_LEN        = 16;           // 虚拟身份经度长度
    public static final int MAX_VIRTUALINFO_LONGITUDE_LEN       = 16;           // 虚拟身份纬度长度
    public static final int MAX_VIRTUALINFO_NUM                 = 1024;         // 最大虚拟身份信息个数
    public static final int MAX_SCREENTIME_COUNT                = 8;            // 诱导屏最大开关屏时间个数
    public static final int MAX_PLAYDATES_COUNT                 = 32;           // 最大日期个数
    public static final int MAX_ELEMENTS_COUNT                  = 8;            // 诱导屏窗口支持的最大元素个数
    public static final int MAX_ELEMENTTEXT_LENGTH              = 512;          // 文本元素最大文本长度
    public static final int MAX_NOTE_COUNT                      = 4;            // 诱导屏窗口元素注释信息最大个数
    public static final int MAX_PROGRAMMES_COUNT                = 32;           // 最多支持的节目个数
    public static final int MAX_CALL_ID_LEN                     = 64;           // 呼叫ID长度
    public static final int MAX_GD_COUNT                        = 170;
    public static final int MAX_DOOR_TIME_SECTION               = 4;            // 门禁每天分时时间段最大个数
    public static final int MAX_SCADA_POINT_LIST_INDEX          = 8;            // 最大SCADADev配置下标个数, 即最大通道
    public static final int MAX_SCADA_YX_NUM                    = 128;          // 最大遥信个数
    public static final int MAX_SCADA_YC_NUM                    = 128;          // 最大遥测个数
    public static final int MAX_SCADA_POINT_INFO_NUM            = 8;            // 最大点表个数
    public static final int MAX_NET_SCADA_CAPS_TYPE             = 16;
    public static final int MAX_NET_SCADA_CAPS_NAME             = 16;
    public static final int MAX_SCADA_ID_NUM                    = 1024;         // 监测点位id的最大个数
    public static final int MAX_SCADA_ID_OF_SENSOR_NUM          = 128;          // 最大检测点位ID个数
    public static final int MAX_REMOTEDEVICEINFO_IPADDR_LEN     = 128;          // 远程设备IP地址最大长度
    public static final int MAX_REMOTEDEVICEINFO_USERNAME_LEN   = 128;          // 远程设备用户名最大长度
    public static final int MAX_REMOTEDEVICEINFO_USERPSW_LENGTH = 128;          // 远程设备密码最大长度
    public static final int MAX_MANUFACTURER_LEN                = 32;           // 最大的 MAC地址所属制造商长度
    public static final int MAX_MACHISTORY_SSID_LEN             = 24;           // 最大的历史SSID长度
    public static final int MAX_MACHISTORY_SSID_NUM             = 5;            // 历史SSID的最大个数
    public static final int CFG_MAX_SN_LEN                      = 32;           // 最大设备序列号长度
    public static final int CFG_MAX_ACCESS_CONTROL_ADDRESS_LEN  = 64;           // 最大的地址长度
    public static final int MAX_MACADDR_NUM                     = 8;            // 最大物理地址个数
    public static final int MAX_ADD_DEVICE_NUM                  = 16;           // 最大添加设备个数
    public static final int MAX_LINK_DEVICE_NUM                 = 1024;         // 最大连接设备个数
    public static final int MAX_DEVICE_CHANNEL_NUM              = 1024;         // 设备最大通道个数
    public static final int NET_CFG_MAX_CTRLTYPE_NUM            = 16;           // 最大道闸控制方式
    public static final int NET_MAX_ALL_SNAP_CAR_COUNT          = 32;           // 所有车开闸种类个数
    public static final int NET_MAX_BURNING_DEV_NUM             = 32;           // 最大刻录设备个数
    public static final int NET_BURNING_DEV_NAMELEN             = 32;           // 刻录设备名字最大长度
    public static final int PTZ_PRESET_NAME_LEN                 = 64;           // 云台预置点名称长度
    public static final int NET_RADIOMETRY_DOFIND_MAX           = 32;           // 热成像温度统计最大个数
    public static final int CFG_MAX_PTZTOUR_NUM                 = 64;           // 巡航路径数量
    public static final int CFG_MAX_PTZTOUR_PRESET_NUM          = 64;           // 巡航路径包含的预置点数量
    public static final int MAX_PTZ_PRESET_NAME_LEN             = 64;           // 云台预置点名称长度
    public static final int MAX_COMPANY_NAME_LEN                = 200;          // 单位名称最大长度
    public static final int NET_MAX_PLAYAUDIO_COUNT             = 16;           // 最大播报内容数目
    public static final int MAX_TARGET_OBJECT_NUM               = 100;          // 最大目标物体信息个数
    public static final int MAX_CROWD_DETECTION_NAME_LEN        = 128;          // 最大人群密度检测事件名称长度
    public static final int MAX_CROWD_LIST_NUM                  = 5;            // 最大全局拥挤人群密度列表个数
    public static final int MAX_REGION_LIST_NUM                 = 8;            // 最大人数超限的报警区域ID列表个数
    public static final int MAX_CROWD_RECT_LIST                 = 5;            // 矩形描述信息的最大个数
    public static final int RECT_POINT                          = 2;            // 表示矩形的2个点（左上角与右下角）
    public static final int MAX_OBJECT_NUM                      = 32;           // 最大物体个数
    public static final int NET_MATRIX_INTERFACE_LEN            = 16;           // 信号接口名称长度
    public static final int NET_MATRIX_MAX_CARDS                = 128;          // 矩阵子卡最大数量
    public static final int NET_SPLIT_PIP_BASE                  = 1000;         // 分割模式画中画的基础值
    public static final int NET_MAX_SPLIT_MODE_NUM              = 64;           // 最大分割模式数
    public static final int NET_MATRIX_MAX_CHANNEL_IN           = 1500;         // 矩阵最大输入通道数
    public static final int NET_MATRIX_MAX_CHANNEL_OUT          = 256;          // 矩阵最大输出通道数
    public static final int NET_DEVICE_NAME_LEN                 = 64;           // 设备名称长度
    public static final int NET_MAX_CPU_NUM                     = 16;           // 最大CPU数量
    public static final int NET_MAX_FAN_NUM                     = 16;           // 最大风扇数量
    public static final int NET_MAX_POWER_NUM                   = 16;           // 最大电源数量
    public static final int NET_MAX_BATTERY_NUM                 = 16;           // 最大电池数量
    public static final int NET_MAX_RAID_DEVICE_NAME            = 16;           // RAID异常信息-RAID设备名称
    public static final int NET_MAX_TEMPERATURE_NUM             = 256;          // 最大温度传感器数量
    public static final int NET_MAX_ISCSI_NAME_LEN              = 128;          // ISCSI名称长度
    public static final int NET_VERSION_LEN                     = 64;           // 版本信息长度
    public static final int NET_MAX_STORAGE_PARTITION_NUM       = 32;           // 存储分区最大数量
    public static final int NET_STORAGE_MOUNT_LEN               = 64;           // 挂载点长度
    public static final int NET_STORAGE_FILE_SYSTEM_LEN         = 16;           // 文件系统名称长度
    public static final int NET_MAX_MEMBER_PER_RAID             = 32;           // RAID成员最大数量
    public static final int NET_DEV_ID_LEN_EX                   = 128;          // 设备ID最大长度
    public static final int NET_MAX_BLOCK_NUM                   = 32;           // 最大区块数量
    public static final int NET_MAX_SPLIT_WINDOW                = 128;          // 最大分割窗口数量
    public static final int NET_FILE_TYPE_LEN                   = 64;           // 文件类型长度
    public static final int NET_DEV_ID_LEN                      = 128;          // 设备ID最大长度
    public static final int NET_DEV_NAME_LEN                    = 128;          // 设备名称最大长度
    public static final int NET_TSCHE_DAY_NUM                   = 8;            // 时间表第一维大小, 表示天数
    public static final int NET_TSCHE_SEC_NUM                   = 6;            // 时间表第二维大小, 表示时段数
    public static final int NET_SPLIT_INPUT_NUM                 = 256;          // 设备二级切换时第一级split支持的输入通道数
    public static final String NET_DEVICE_ID_LOCAL              = "Local";      // 本地设备ID
    public static final String NET_DEVICE_ID_REMOTE             = "Remote";     // 远程设备ID
    public static final String NET_DEVICE_ID_UNIQUE             = "Unique";     // 设备内统一编
    public static final int NET_MAX_NAME_LEN                    = 16;           // 通用名字字符串长度
    public static final int NET_MAX_PERSON_ID_LEN               = 32;           // 人员id最大长度
    public static final int NET_MAX_PERSON_IMAGE_NUM            = 48;           // 每个人员对应的最大人脸图片数
    public static final int NET_MAX_PROVINCE_NAME_LEN           = 64;           // 省份名称最大长度
    public static final int NET_MAX_CITY_NAME_LEN               = 64;           // 城市名称最大长度
    public static final int NET_MAX_PERSON_NAME_LEN             = 64;           // 人员名字最大长度
    public static final int MAX_FACE_AREA_NUM                   = 8;            // 最大人脸区域个数
    public static final int MAX_PATH                            = 260;
    public static final int MAX_FACE_DB_NUM                     = 8;            // 最大人脸数据库个数
    public static final int MAX_GOURP_NUM                       = 128;          // 人脸库最大个数
    public static final int MAX_AGE_NUM                         = 2;            // 最大年龄个数
    public static final int MAX_EMOTION_NUM                     = 8;            // 最大表情条件的个数
    public static final int MAX_FIND_COUNT                      = 20;
    public static final int NET_MAX_POLYGON_NUM                 = 16;           // 多边形最大顶点个数
    public static final int NET_MAX_CANDIDATE_NUM               = 50;           // 目标识别最大匹配数
    public static final int MAX_POLYGON_NUM                     = 20;           // 视频分析设备区域顶点个数上限
    public static final int MAX_CALIBRATEBOX_NUM                = 10;           // 智能分析校准框个数上限
    public static final int MAX_NAME_LEN                        = 128;          // 通用名字字符串长度
    public static final int MAX_EXCLUDEREGION_NUM               = 10;           // 智能分析检测区域中需要排除的区域个数上限
    public static final int MAX_SCENE_LIST_SIZE                 = 32;           // 视频分析设备支持的场景类型列表个数上限
    public static final int MAX_OBJECT_LIST_SIZE                = 16;           // 视频分析设备支持的检测物体类型列表个数上限
    public static final int MAX_RULE_LIST_SIZE                  = 128;          // 视频分析设备支持的规则列表个数上限
    public static final int MAX_ACTION_LIST_SIZE                = 16;           // 视频分析设备支持的规则的动作类型列表个数上限
    public static final int MAX_SPECIALDETECT_NUM               = 10;           // 智能分析特殊检测区域上限
    public static final int MAX_OBJECT_ATTRIBUTES_SIZE          = 16;           // 视频分析设备支持的检测物体属性类型列表个数上限
    public static final int MAX_CATEGORY_TYPE_NUMBER            = 128;          // 子类别类型数
    public static final int MAX_ANALYSE_MODULE_NUM              = 16;           // 视频分析设备最大检测模块个数
    public static final int MAX_LOG_PATH_LEN                    = 260;          // 日志路径名最大长度
    public static final int MAX_CHANNELNAME_LEN                 = 64;           // 最大通道名称长度
    public static final int MAX_VIDEO_CHANNEL_NUM               = 256;          // 最大通道数256
    public static final int MAX_PSTN_SERVER_NUM                 = 8;            // 最大报警电话服务器数
    public static final int MAX_TIME_SCHEDULE_NUM               = 8;            // 时间表元素个数
    public static final int MAX_REC_TSECT                       = 6;            // 录像时间段个数
    public static final int MAX_REC_TSECT_EX                    = 10;           // 录像时间段扩展个数
    public static final int MAX_NAS_TIME_SECTION                = 2;            // 网络存储时间段个数
    public static final int MAX_CHANNEL_COUNT                   = 16;
    public static final int MAX_ACCESSCONTROL_NUM               = 8;            // 最大门禁操作的组合数
    public static final int MAX_DBKEY_NUM                       = 64;           // 数据库关键字最大值
    public static final int MAX_SUMMARY_LEN                     = 1024;         // 叠加到JPEG图片的摘要信息最大长度
    public static final int WEEK_DAY_NUM                        = 7;            // 一周的天数
    public static final int NET_MAX_FACEDETECT_FEATURE_NUM      = 32;           // 人脸特征最大个数
    public static final int NET_MAX_OBJECT_LIST                 = 16;           // 智能分析设备检测到的物体ID个数上限
    public static final int NET_MAX_RULE_LIST                   = 16;           // 智能分析设备规则个数上限
    public static final int MAX_HUMANFACE_LIST_SIZE             = 8;            // 视频分析设备支持的目标检测类型列表个数上限
    public static final int MAX_FEATURE_LIST_SIZE               = 32;           // 视频分析设备支持的人脸属性列表个数上限
    public static final int NET_MAX_DETECT_REGION_NUM           = 20;           // 规则检测区域最大顶点数
    public static final int NET_MAX_DETECT_LINE_NUM             = 20;           // 规则检测线最大顶点数
    public static final int NET_MAX_TRACK_LINE_NUM              = 20;           // 物体运动轨迹最大顶点数
    public static final int NET_MACADDR_LEN                     = 40;           // MAC地址字符串长度
    public static final int NET_DEV_TYPE_LEN                    = 32;           // 设备型号字符串（如"IPC-F725"）长度
    public static final int NET_DEV_SERIALNO_LEN                = 48;           // 序列号字符串长度
    public static final int NET_MAX_URL_LEN                     = 128;          // URL字符串长度
    public static final int NET_MAX_STRING_LEN                  = 128;
    public static final int NET_MACHINE_NAME_NUM                = 64;           // 机器名称长度
    public static final int NET_USER_NAME_LENGTH_EX             = 16;           // 用户名长度
    public static final int NET_USER_NAME_LENGTH                = 8;            // 用户名长度
    public static final int NET_USER_PSW_LENGTH                 = 8;            // 用户密码长度
    public static final int NET_EVENT_NAME_LEN                  = 128;          // 事件名称长度
    public static final int NET_MAX_LANE_NUM                    = 8;            // 视频分析设备每个通道对应车道数上限
    public static final int MAX_DRIVING_DIR_NUM                 = 16;           // 车道行驶方向最大个数
    public static final int FLOWSTAT_ADDR_NAME                  = 16;           // 上下行地点名长
    public static final int NET_MAX_DRIVINGDIRECTION            = 256;          // 行驶方向字符串长度
    public static final int COMMON_SEAT_MAX_NUMBER              = 8;            // 默认检测最大座驾个数
    public static final int NET_MAX_ATTACHMENT_NUM              = 8;            // 最大车辆物件数量
    public static final int NET_MAX_ANNUUALINSPECTION_NUM       = 8;            // 最大年检标识位置
    public static final int NET_MAX_EVENT_PIC_NUM               = 6;            // 最大原始图片张数
    public static final int NET_COMMON_STRING_4                 = 4;            // 通用字符串长度4
    public static final int NET_COMMON_STRING_8                 = 8;            // 通用字符串长度8
    public static final int NET_COMMON_STRING_16                = 16;           // 通用字符串长度16
    public static final int NET_COMMON_STRING_20                = 20;           // 通用字符串长度20
    public static final int NET_COMMON_STRING_32                = 32;           // 通用字符串长度32
    public static final int NET_COMMON_STRING_64                = 64;           // 通用字符串长度64
    public static final int NET_COMMON_STRING_128               = 128;          // 通用字符串长度128
    public static final int NET_COMMON_STRING_256               = 256;          // 通用字符串长度256
    public static final int NET_COMMON_STRING_512               = 512;          // 通用字符串长度512
    public static final int NET_COMMON_STRING_1024              = 1024;         // 通用字符串长度1024
    public static final int NET_COMMON_STRING_2048              = 2048;         // 通用字符串长度2048
    public static final int MAX_VIDEOSTREAM_NUM                 = 4;            // 最大码流个数
    public static final int MAX_VIDEO_COVER_NUM                 = 16;           // 最大遮挡区域个数
    public static final int MAX_VIDEO_IN_ZOOM                   = 32;           // 单通道最大变速配置个数
    public static final int NET_EVENT_CARD_LEN                  = 36;           // 卡片名称长度
    public static final int NET_EVENT_MAX_CARD_NUM              = 16;           // 事件上报信息包含最大卡片个数
    public static final int MAX_STATUS_NUM                      = 16;           // 交通设备状态最大个数
    public static final int NET_MAX_CHANMASK                    = 64;           // 通道掩码最大值
    public static final int NET_CHAN_NAME_LEN                   = 32;           // 通道名长度,DVR DSP能力限制,最多32字节
    public static final int MAX_LANE_NUM                        = 8;            // 视频分析设备每个通道对应车道数上限
    public static final int MAX_STAFF_NUM                       = 20;           // 视频分析设备每个通道对应的标尺数上限
    public static final int MAX_ANALYSE_RULE_NUM                = 32;           // 视频分析设备最大规则个数
    public static final int MAX_POLYLINE_NUM                    = 20;           // 视频分析设备折线顶点个数上限
    public static final int MAX_TEMPLATEREGION_NUM              = 32;           // 视频分析设备模拟区域信息点对个数上限
    public static final int POINT_PAIR_NUM                      = 2;            // 视频分析设备模拟区域点对包含的点个数
    public static final int MAX_VEHICLE_SIZE_LIST               = 4;            // 视频分析设备车辆大小个数上限
    public static final int MAX_VEHICLE_TYPE_LIST               = 4;            // 视频分析设备车辆类型个数上限
    public static final int MAX_PLATE_TYPE_LIST                 = 32;           // 视频分析设备车牌类型个数上限
    public static final int MAX_CALIBRATEAREA_NUM               = 20;           // 视频分析设备标定区域的上限
    public static final int MAX_ANALYSE_SCENE_NUM               = 32;           // 视频分析全局配置场景最大数量
    public static final int MAX_PLATEHINT_NUM                   = 8;            // 车牌字符暗示个数上限
    public static final int MAX_LIGHT_NUM                       = 8;            // 交通灯个数上限
    public static final int MAX_LIGHTGROUP_NUM                  = 8;            // 交通灯组个数上限
    public static final int MAX_LIGHT_TYPE                      = 8;            // 交通灯类型上限
    public static final int MAX_PARKING_SPACE_NUM               = 6;            // 最多配置6个车位信息
    public static final int MAX_SHIELD_AREA_NUM                 = 16;           // 1个车位最多对应16个屏蔽区域
    public static final int MAX_SCENE_TYPE_LIST_SIZE            = 8;            // 场景列表中最多支持的场景个数
    public static final int MAX_LIGHT_DIRECTION                 = 8;            // 交通灯指示方向数上限
    public static final int CFG_FLOWSTAT_ADDR_NAME              = 16;           //上下行地点名长
    public static final int MAX_ACCESS_READER_NUM               = 32;           // 门禁单个门最大读卡器数量
    public static final int MAX_ACCESSSUBCONTROLLER_NUM         = 64;           // 最大门禁分控器数量
    public static final int MAX_BACKPIC_COUNT                   = 8;            // 最大背景图片个数
    public static final int NET_WIRELESS_DEVICE_SERIAL_NUMBER_MAX_LEN = 32;     // 无线设备序列号最大长度
    public static final int NET_MAX_CUSTOM_PERSON_INFO_NUM      = 4;            // 注册人员信息扩展最大个数
    public static final int NET_MAX_PERSON_INFO_LEN             = 64;           // 人员扩展信息最大长度
    public static final int MAX_ALARMEXT_MODULE_NUM             = 256;          // 最大扩展模块数目
    public static final int MAX_CALIBRATEAREA_TYPE_NUM          = 4;            // 标定区域类型上限
    public static final int MAX_SCENE_SUBTYPE_LEN               = 64;           // 场景子类型字符串长度
    public static final int MAX_SCENE_SUBTYPE_NUM               = 32;           // 场景子类型最大个数
    public static final int MAX_SUPPORTED_COMP_SIZE             = 4;            // 最大支持的场景组合项
    public static final int MAX_SUPPORTED_COMP_DATA             = 8;            // 每个组合项里最多支持的场景个数
    public static final int MAX_NUMBER_STAT_MAULT_NUM           = 32;           // 最大客流量统计场景PD个数
    public static final int NET_NEW_MAX_RIGHT_NUM               = 1024;         // 用户权限个数上限
    public static final int NET_MAX_GROUP_NUM                   = 20;           // 用户组个数上限
    public static final int NET_MAX_USER_NUM                    = 200;          // 用户个数上限
    public static final int NET_RIGHT_NAME_LENGTH               = 32;           // 权限名长度
    public static final int NET_MEMO_LENGTH                     = 32;           // 备注长度
    public static final int NET_NEW_USER_NAME_LENGTH            = 128;          // 用户名长度
    public static final int NET_NEW_USER_PSW_LENGTH             = 128;          // 密码
    public static final int NET_MAX_RIGHT_NUM                   = 100;          // 用户权限个数上限
    public static final int NET_COMMENT_LENGTH                  = 100;          // 备注信息长度
    public static final int NET_GROUPID_LENGTH                  = 64;           // group id 信息长度
    public static final int NET_GROUPNAME_LENGTH                = 128;          // group name 信息长度
    public static final int NET_FEATUREVALUE_LENGTH             = 128;          // 人脸特征 信息长度
    public static final int MAX_GROUP_ID_LEN                    = 64;           // 最大布控组ID长度
    public static final int MAX_COLOR_NAME_LEN                  = 32;           // 最大颜色名长度
    public static final int MAX_COLOR_HEX_LEN                   = 8;            // 最大HEX颜色长度
    public static final int MAX_LINK_GROUP_NUM                  = 20;           // 联动的布控组最大数量
    public static final int MAX_PATH_LEN                        = 260;          // 最大路径长度
    public static final int MAX_RIDER_NUM                       = 16;           // 骑车人数组上限
    public static final int MAX_ALARM_CHANNEL_NAME_LEN          = 64;           // 最大报警名称长度
    public static final int MAX_ATTACHMENT_NUM                  = 8;            // 最大车内物品个数
    public static final int NET_MAX_FRAMESEQUENCE_NUM           = 2;            // 最大帧序号个数
    public static final int NET_MAX_TIMESTAMP_NUM               = 2;            // 最大时间戳个数
    public static final int NET_VIDEOANALYSE_SCENES             = 32;           // 最大场景个数
    public static final int NET_VIDEOANALYSE_RULES              = 64;           // 最大规则个数
    public static final int SDK_EVENT_NAME_LEN                  = 128;          // 事件名称长度
    public static final int NET_USER_PSW_LENGTH_EX              = 16;           // 密码
    public static final int AV_CFG_Device_ID_Len                = 64;           // 设备ID长度
    public static final int AV_CFG_Channel_Name_Len             = 64;           // 通道名称长度
    public static final int AV_CFG_Monitor_Name_Len             = 64;           // 电视墙名称长度
    public static final int AV_CFG_Max_TV_In_Block              = 128;          // 区块中TV的最大数量
    public static final int AV_CFG_Max_Block_In_Wall            = 128;          // 电视墙中区块的最大数量
    public static final int AV_CFG_IP_Address_Len               = 32;           // IP 长度
    public static final int AV_CFG_Protocol_Len                 = 32;           // 协议名长度
    public static final int AV_CFG_User_Name_Len                = 64;           // 用户名长度
    public static final int AV_CFG_Password_Len                 = 64;           // 密码长度
    public static final int AV_CFG_Serial_Len                   = 32;           // 序列号长度
    public static final int AV_CFG_Device_Class_Len             = 16;           // 设备类型长度
    public static final int AV_CFG_Device_Type_Len              = 32;           // 设备具体型号长度
    public static final int AV_CFG_Device_Name_Len              = 128;          // 机器名称
    public static final int AV_CFG_Address_Len                  = 128;          // 机器部署地点
    public static final int AV_CFG_Max_Path                     = 260;          // 路径长度
    public static final int AV_CFG_Group_Name_Len               = 64;           // 分组名称长度
    public static final int AV_CFG_DeviceNo_Len                 = 32;           // 设备编号长度
    public static final int AV_CFG_Group_Memo_Len               = 128;          // 分组说明长度
    public static final int AV_CFG_Max_Channel_Num              = 1024;         // 最大通道数量
    public static final int MAX_DEVICE_NAME_LEN                 = 64;           // 机器名称
    public static final int MAX_DEV_ID_LEN_EX                   = 128;          // 设备ID最大长度
    public static final int MAX_PATH_STOR                       = 240;          // 远程目录的长度
    public static final int MAX_REMOTE_DEV_NUM                  = 256;          // 最大远程设备数量
    public static final int NET_MAX_PLATE_NUMBER_LEN            = 32;           // 车牌字符长度
    public static final int NET_MAX_AUTHORITY_LIST_NUM          = 16;           // 权限列表最大个数
    public static final int NET_MAX_ALARMOUT_NUM_EX             = 32;           //报警输出口个数上限扩展
    public static final int NET_MAX_VIDEO_IN_NUM_EX             = 32;           //视频输入口个数上限扩展
    public static final int NET_MAX_SAERCH_IP_NUM               = 256;          // 最大搜索IP个数
    public static final int NET_MAX_POS_MAC_NUM                 = 8;            // 刷卡机Mac码最大长度
    public static final int NET_MAX_BUSCARD_NUM                 = 64;           // 公交卡号最大长度
    public static final int NET_STORAGE_NAME_LEN                = 128;          // 存储设备名称长度
    public static final int NET_MAX_DOOR_NUM                    = 32;           // 最大有权限门禁数目
    public static final int NET_MAX_TIMESECTION_NUM             = 32;           // 最大有效时间段数目
    public static final int NET_MAX_CARDNAME_LEN                = 64;           // 门禁卡命名最大长度
    public static final int NET_MAX_CARDNO_LEN                  = 32;           // 门禁卡号最大长度
    public static final int NET_MAX_USERID_LEN                  = 32;           // 门禁卡用户ID最大长度
    public static final int NET_MAX_IC_LEN                      = 32;           // 证件最大长度
    public static final int NET_MAX_QRCODE_LEN                  = 128;          // QRCode 最大长度
    public static final int NET_MAX_CARD_INFO_LEN               = 256;          // 卡号信息最大长度
    public static final int NET_MAX_SIM_LEN                     = 16;           // SIM卡的值的最大长度
    public static final int NET_MAX_DISKNUM                     = 256;          // 最大硬盘个数
    public static final int MAX_FACE_DATA_NUM                   = 20;           // 人脸模版最大个数
    public static final int MAX_FINGERPRINT_NUM                 = 10;           // 最大信息个数
    public static final int MAX_FACE_DATA_LEN                   = 2 * 1024;     // 人脸模版数据最大长度
    public static final int MAX_COMMON_STRING_8                 = 8;            // 通用字符串长度8
    public static final int MAX_COMMON_STRING_16                = 16;           // 通用字符串长度16
    public static final int MAX_COMMON_STRING_32                = 32;           // 通用字符串长度32
    public static final int MAX_COMMON_STRING_64                = 64;           // 通用字符串长度64
    public static final int MAX_COMMON_STRING_128               = 128;          // 通用字符串长度128
    public static final int MAX_USER_NAME_LEN                   = 128;          // 最大用户名长度
    public static final int MAX_ROOMNUM_COUNT                   = 32;           // 房间最大个数
    public static final int MAX_FACE_COUTN                      = 20;           // 人脸模板数据最大个数
    public static final int MAX_PHOTO_COUNT                     = 5;            // 人脸照片最大个数
    public static final int MAX_WINDOWS_COUNT                   = 16;           // 诱导屏最大窗口个数
    public static final int MAX_CLASS_NUMBER_LEN                = 32;           // 最大班级长度
    public static final int MAX_PHONENUMBER_LEN                 = 16;           // 最大电话长度
    public static final int MAX_NASFILE_NUM                     = 8;            // 最大NAS文件个数
    public static final int MAX_CELL_PHONE_NUMBER_LEN           = 32;           // 最大手机号长度
    public static final int MAX_MAIL_LEN                        = 64;           // 邮箱最大长度
    public static final int MAX_PWD_LEN                         = 128;          // 最大密码长度
    public static final int MAX_ACCESS_FLOOR_NUM                = 64;           // 最大楼层数量
    public static final int MAX_ORDER_NUMBER                    = 6;            // 排序规则的最大数量
    public static final int MAX_NUMBER_REGISTER_INFO            = 32;
    public static final int MAX_COMPANION_CARD_NUM              = 6;            // 陪同者卡号最大个数
    public static final int CFG_COMMON_STRING_8                 = 8;            // 通用字符串长度8
    public static final int CFG_COMMON_STRING_16                = 16;           // 通用字符串长度16
    public static final int CFG_COMMON_STRING_32                = 32;           // 通用字符串长度32
    public static final int CFG_COMMON_STRING_64                = 64;           // 通用字符串长度64
    public static final int CFG_COMMON_STRING_128               = 128;          // 通用字符串长度128
    public static final int CFG_COMMON_STRING_256               = 256;          // 通用字符串长度256
    public static final int CFG_COMMON_STRING_512               = 512;          // 通用字符串长度512
    public static final int MAX_COILCONFIG                      = 3;            // 智能交通车检器线圈配置上限
    public static final int MAX_DETECTOR                        = 6;            // 智能交通车检器配置上限
    public static final int MAX_VIOLATIONCODE                   = 16;           // 智能交通违章代码长度上限
    public static final int MAX_LANE_CONFIG_NUMBER              = 32;           // 车道最大个数
    public static final int MAX_VIOLATIONCODE_DESCRIPT          = 64;           // 智能交通违章代码长度上限
    public static final int MAX_ROADWAYNO                       = 128;          // 道路编号	由32个数字和字母构成
    public static final int MAX_PRIORITY_NUMBER                 = 256;          // 违章优先级包含违章最大个数
    public static final int MAX_DRIVINGDIRECTION                = 256;          // 行驶方向字符串长度
    public static final int MAX_OSD_CUSTOM_SORT_NUM             = 8;
    public static final int MAX_OSD_CUSTOM_SORT_ELEM_NUM        = 8;
    public static final int MAX_OSD_CUSTOM_GENERAL_NUM          = 8;
    public static final int MAX_OSD_ITEM_ATTR_NUM               = 8;
    public static final int MAX_PRE_POX_STR_LEN                 = 32;
    public static final int MAX_OSD_CUSTOM_NAME_LEN             = 32;
    public static final int MAX_OSD_CUSTOM_VALUE_LEN            = 256;
    public static final int MAX_CONF_CHAR                       = 256;
    public static final int MAX_IVS_EVENT_NUM                   = 256;
    public static final int MAX_QUERY_USER_NUM                  = 4;            // 最大查询用户个数
    public static final int MAX_DEVICE_ADDRESS                  = 256;          // TrafficSnapshot智能交通设备地址
    public static final int MAX_STORAGE_NUM                     = 8;            // 存储设备最大个数
    public static final int MAX_PARTITION_NUM                   = 8;            // 最大分区个数
    public static final int MAX_SCADA_POINT_LIST_INFO_NUM       = 256;          // 最大点位表路径个数
    public static final int MAX_SCADA_POINT_LIST_ALARM_INFO_NUM = 256;          // 最大点位表报警个数
    public static final int MAX_LABEL_ARRAY                     = 1024;
    public static final int MAX_DELIVERY_FILE_NUM               = 128;          // 最大投放文件数量
    public static final int DELIVERY_FILE_URL_LEN               = 128;          // 投放文件的URL长度
    public static final int MAX_COMMON_STRING_512               = 512;          // 通用字符串长度512
    public static final int MAX_RFIDELETAG_CARDID_LEN           = 16;           // RFID 电子车牌标签信息中卡号最大长度
    public static final int MAX_RFIDELETAG_DATE_LEN             = 16;           // RFID 电子车牌标签信息中时间最大长度
    public static final int MAX_REPEATENTERROUTE_NUM            = 12;           //反潜路径个数
    public static final int ECK_SCREEN_NUM_MAX                  = 8;            // 智能停车系统出入口机最大屏数量
    public static final int MAX_CAR_CANDIDATE_NUM               = 50;
    public static final int MAX_REGISTER_NUM                    = 10;           // 主动注册配置最大个数
    public static final int MAX_SERVER_NUM                      = 10;           // 服务器最大个数
    public static final int NET_COUNTRY_LENGTH                  = 3;            // 国家缩写长度
    public static final int MAX_ATTENDANCE_USERNAME_LEN         = 36;           // 考勤用户名长度
    public static final int NET_MAX_FINGER_PRINT                = 10;           // 信息最大个数
    public static final int MAX_EVENT_ID_LEN                    = 52;           // 国标事件ID最大长度
    public static final int MAX_HUMANTRAIT_EVENT_LEN            = 36;           // 补充人体特征上报事件最大长度
    public static final int MAX_EXIT_MAN_NUM                    = 32;           // 最大支持的离开人员数量
    public static final int NET_MAX_CALLTYPE_LIST_NUM           = 16;           // 呼叫类型查询条件列表最大个数
    public static final int NET_MAX_ENDSTATE_LIST_NUM           = 16;           // 最终状态查询条件列表最大个数
    public static final int MAX_CHAN_NUM                        = 256;          // 最大通道数上限
    public static final int AV_CFG_Max_ChannelRule              = 32;           // 通道存储规则最大长度, 仅通道部分
    public static final int MAX_DEV_NUM                         = 16;           // 最大设备上限
    public static final int CFG_MAX_CHANNEL_NAME_LEN            = 256;          // 通道名称最大长度
    public static final int HDBJ_MAX_OBJECTS_NUM                = 200;          // 检测到目标的最大个数
    public static final int NET_MAX_RAID_NUM                    = 16;           // Raid最大个数
    public static final int MAX_PLATE_NUMBER_LEN                = 64;           // 最大车牌号码长度
    public static final int MAX_MASTER_OF_CAR_LEN               = 32;           // 最大车主姓名长度
    public static final int MAX_USER_TYPE_LEN                   = 32;           // 最大用户类型长度
    public static final int MAX_SUB_USER_TYPE_LEN               = 64;           // 最大用户子类型长度
    public static final int MAX_REMARKS_LEN                     = 64;           // 最大备注信息长度
    public static final int MAX_PARK_CHARGE_LEN                 = 32;           // 最大停车费长度
    public static final int MAX_CUSTOM_LEN                      = 128;          // 最大自定义显示长度
    public static final int MAX_RESOURCE_LEN                    = 64;           // 最大资源文件长度
    public static final int MAX_PARKINGLOCK_STATE_NUM           = 6;            // 最大车位锁状态个数
    public static final int MAX_SMALLPIC_NUM                    = 32;           // 最大小图张数
    public static final int MAX_PASSWORD_LEN                    = 64;           // 最大密码长度
    public static final int MAX_OSD_SUMMARY_LEN                 = 256;          // osd叠加内容最大长度
    public static final int MAX_OSD_TITLE_LEN                   = 128;          // osd叠加标题最大长度
    public static final int MAX_CUSTOMCASE_NUM                  = 16;           // 自定义案件最大个数
    public static final int MAX_CARGO_CHANNEL_NUM               = 8;            // 最大货物通道数
    public static final int MAX_MAN_LIST_COUNT                  = 64;           // 人员列表最大数量
    public static final int MAX_SNAP_SHOT_NUM                   = 8;            // 最大抓拍张数
    public static final int MAX_TEMPERATUREEX_POINT_NUM         = 12;           //最大监测温度点的个数
    public static final int NET_DATA_CALL_BACK_VALUE            = 1000;         // 配合EM_REAL_DATA_TYPE使用,码流转换后的数据回调函数(fRealDataCallBackEx,fDataCallBack)中的参数dwDataType的值
    public static final int DH_MAX_PERSON_INFO_LEN              = 64;           // 人员扩展信息最大长度
    public static final int ARM_DISARM_ZONE_MAX                 = 256;          // 防区最大个数
    public static final int MAX_AREA_NUMBER                     = 8;            //最大area数量
    public static final int MAX_AREA_NUMBER_EX                  = 64;           //最大area数量扩展
    public static final int MAX_SECONDARY_ANALYSE_TASK_NUM      = 32;           // 二次分析任务支持的最大个数
    public static final int MAX_SECONDARY_ANALYSE_RULE_NUM      = 8;            // 二次分析规则支持的最大个数
    public static final int MAX_SECONDARY_ANALYSE_EVENT_NUM     = 8;            // 二次分析事件支持的最大个数
    public static final int NET_MAX_WINDOWS_NUMBER              = 64;           // 录播主机窗口最大个数
    public static final int NET_MAX_MODE_NUMBER                 = 64;           // 录播主机模式最大个数
    public static final int MAX_COURSE_LOGIC_CHANNEL            = 64;           // 录播主机最大逻辑通道数
    public static final int MAX_UPGRADER_SERIAL_INFO            = 8;
    public static final int NET_UPGRADE_COUNT_MAX               = 256;          // 最大升级个数
    public static final int MAX_PIC_PATH_NUM                    = 16;
    public static final int UAV_MAX_SENSOR_NUM                  = 32;           // 最大传感器个数
    public static final int UAV_MAX_SATELLITE_NUM               = 20;           // 最多支持卫星个数
    public static final int CFG_MAX_USER_ID_LEN                 = 32;           // 门禁卡用户ID最大长度
    public static final int CFG_MAX_METHODEX_NUM                = 4;            // 开门方式扩展最大个数
    public static final int CFG_MAX_OPEN_DOOR_GROUP_DETAIL_NUM  = 64;           // 每一组多人开门组合的最大人数
    public static final int CFG_MAX_OPEN_DOOR_GROUP_NUM         = 4;            // 多人开门组合的最大组合数
    public static final int MAX_ANALYSE_REMAIN_CAPACITY_NUM     = 32;           // 智能分析最大剩余数量
    public static final int MAX_ANALYSE_RULE_COUNT              = 32;           // 最大分析规则条数
    public static final int MAX_ANALYSE_PICTURE_FILE_NUM        = 32;           // 图片文件最大数量
    public static final int MAX_ANALYSE_TASK_NUM                = 64;           // 最大智能分析任务个数
    public static final int MAX_ANALYSE_FILTER_EVENT_NUM        = 64;           // 最大支持过滤的事件个数
    public static final int MAX_ANALYSE_ALGORITHM_NUM           = 16;           // 最大算法数量
    public static final int MAX_ANALYSE_TOTALCAPS_NUM           = 32;           // 最大的智能分析总能力数量
    public static final int MAX_COAXIAL_CONTROL_IO_COUNT        = 8;            // 同轴IO信息最大个数
    public static final int MAX_FIREWARNING_DETECTRGN_NUM       = 32;           // 最大火警区域检测的个数
    public static final int MAX_FIREWARNING_RULE_NUM            = 32;           // 最大火警规则
    public static final int MAX_FIREWARNING_DETECTWND_NUM       = 8;            // 最大火警检测窗口个数
    public static final int MAX_LANES_NUM                       = 64;           // 灯组监管车位的最多个数
    public static final int MAX_LIGHT_GROUP_INFO_NUM            = 8;            // 车位指示灯本机配置的最多个数
    public static final int MAX_ADDRESS_NUM                     = 16;           // 最大串口地址个数
    public static final int MAX_DEVICE_ID_LEN                   = 48;           // 最大设备编码长度
    public static final int MAX_DEVICE_MARK_LEN                 = 64;           // 最大设备描述长度
    public static final int MAX_BRAND_NAME_LEN                  = 64;           // 最大设备品牌长度
    public static final int MAX_LIGHTING_NUM                    = 16;
    public static final int MAX_LIGHTING_DETAIL_NUM             = 16;
    public static final int NET_MAX_PLATEENABLE_NUM             = 16;           // 最大使能过车车牌播报个数
    public static final int NET_MAX_BROADCAST_ELEMENT_NUM       = 64;           // 最大语音播报元素个数
    // CLIENT_StartListenEx报警事件
    public static final int NET_ALARM_ALARM_EX                  = 0x2101;       // 外部报警，数据字节数与设备报警通道个数相同，每个字节表示一个报警通道的报警状态，1为有报警，0为无报警。
    public static final int NET_MOTION_ALARM_EX                 = 0x2102;       // 动态检测报警，数据字节数与设备视频通道个数相同，每个字节表示一个视频通道的动态检测报警状态，1为有报警，0为无报警。
    public static final int NET_VIDEOLOST_ALARM_EX              = 0x2103;       // 视频丢失报警，数据字节数与设备视频通道个数相同，每个字节表示一个视频通道的视频丢失报警状态，1为有报警，0为无报警。
    public static final int NET_SHELTER_ALARM_EX                = 0x2104;       // 视频遮挡报警，数据字节数与设备视频通道个数相同，每个字节表示一个视频通道的遮挡(黑屏)报警状态，1为有报警，0为无报警。
    public static final int NET_DISKFULL_ALARM_EX               = 0x2106;       // 硬盘满报警，数据为1个字节，1为有硬盘满报警，0为无报警。
    public static final int NET_DISKERROR_ALARM_EX              = 0x2107;       // 坏硬盘报警，数据为32个字节，每个字节表示一个硬盘的故障报警状态，1为有报警，0为无报警。
    public static final int NET_TRAF_CONGESTION_ALARM_EX        = 0x211A;       // 交通阻塞报警(车辆出现异常停止或者排队)(对应结构体 ALARM_TRAF_CONGESTION_INFO)
    public static final int NET_ALARM_ACC_POWEROFF              = 0x211E;       // ACC断电报警，数据为 DWORD 0：ACC通电 1：ACC断电
    public static final int NET_ALARM_3GFLOW_EXCEED             = 0x211F;       // 3G流量超出阈值报警(对应结构体 DHDEV_3GFLOW_EXCEED_STATE_INFO)
    public static final int NET_PTZ_LOCATION_EX                 = 0x2123;       // 云台定位信息(对应结构体 DH_PTZ_LOCATION_INFO)
    public static final int NET_ALARM_ENCLOSURE                 = 0x2126;       // 电子围栏报警(对应结构体 ALARM_ENCLOSURE_INFO)
    public static final int NET_ALARM_RAID_STATE                = 0x2128;       // RAID异常报警(对应结构体 ALARM_RAID_INFO)
    public static final int NET_ALARM_TRAFFIC_FLUX_STAT         = 0x212E;       // 交通流量统计报警(对应结构体ALARM_TRAFFIC_FLUX_LANE_INFO)
    public static final int NET_ALARM_FRONTDISCONNECT           = 0x2132;       // 前端IPC断网报警(对应结构体 ALARM_FRONTDISCONNET_INFO)
    public static final int NET_ALARM_BATTERYLOWPOWER           = 0x2134;       // 电池电量低报警(对应结构体 ALARM_BATTERYLOWPOWER_INFO)
    public static final int NET_ALARM_TEMPERATURE               = 0x2135;       // 温度异常报警(对应结构体 ALARM_TEMPERATURE_INFO)
    public static final int NET_ALARM_STORAGE_LOW_SPACE         = 0x2145;       // 存储容量不足事件(对应 ALARM_STORAGE_LOW_SPACE_INFO)
    public static final int NET_ALARM_FAN_SPEED                 = 0x2162;       // 风扇转速异常事件(对应 ALARM_FAN_SPEED)
    public static final int NET_ALARM_STORAGE_FAILURE_EX        = 0x2163;       // 存储错误报警(对应结构体 ALARM_STORAGE_FAILURE_EX)
    public static final int NET_ALARM_TALKING_INVITE            = 0x2171;       // 设备请求对方发起对讲事件(对应结构体  ALARM_TALKING_INVITE_INFO)
    public static final int NET_ALARM_ALARM_EX2                 = 0x2175;       // 本地报警事件(对应结构体ALARM_ALARM_INFO_EX2,对NET_ALARM_ALARM_EX升级)
    public static final int NET_EVENT_LEFT_DETECTION            = 0x218a;       // 物品遗留事件( 对应结构体 ALARM_EVENT_LEFT_INFO )
    public static final int NET_ALARM_IPC                       = 0x218c;       // IPC报警,IPC通过DVR或NVR上报的本地报警(对应结构体 ALARM_IPC_INFO)
    public static final int NET_EVENT_TAKENAWAYDETECTION        = 0x218d;       // 物品搬移事件(对应结构体 ALARM_TAKENAWAY_DETECTION_INFO)
    public static final int NET_EVENT_VIDEOABNORMALDETECTION    = 0x218e;       // 视频异常事件(对应ALARM_VIDEOABNORMAL_DETECTION_INFO)
    public static final int NET_ALARM_MOVEDETECTION             = 0x2193;       // 移动事件(对应ALARM_MOVE_DETECTION_INFO)
    public static final int NET_ALARM_WANDERDETECTION           = 0x2194;       // 徘徊事件(对应ALARM_WANDERDETECTION_INFO)
    public static final int NET_ALARM_KEYPAD_TAMPER             = 0x2199;       // 键盘防拆报警/恢复(对应ALARM_KEYPAD_TAMPER_INFO)
    public static final int NET_ALARM_USER_PASS_CONFIRM         = 0x21A2;       // 用户通过闸机进入或离开事件(对应结构体 NET_ALARM_USER_PASS_CONFIRM_INFO)
    public static final int NET_ALARM_REID_CLUSTER_STATE        = 0x21A5;       // 聚档状态事件(对应结构体 NET_ALARM_REID_CLUSTER_STATE_INFO)
    public static final int NET_CONFIG_RESULT_EVENT_EX          = 0x3000;       // 修改配置的返回码；返回结构见 DEV_SET_RESULT
    public static final int NET_ALARM_GYROABNORMALATTITUDE      = 0x3011;       // 车辆的紧急制动、侧翻等状态导致的姿态异常进行报警(对应 ALARM_GYROABNORMALATTITUDE_INFO)
    public static final int NET_START_LISTEN_FINISH_EVENT       = 0x300c;       // 订阅事件接口完成异步通知事件, 信息为 START_LISTEN_FINISH_RESULT_INFO
    public static final int NET_ALARM_STORAGE_NOT_EXIST         = 0x3167;       // 存储组不存在事件(对应结构体 ALARM_STORAGE_NOT_EXIST_INFO)
    public static final int NET_ALARM_SCADA_DEV_ALARM           = 0x31a2;       // 检测采集设备报警事件(对应结构体 ALARM_SCADA_DEV_INFO)
    public static final int NET_ALARM_PARKING_CARD              = 0x31a4;       // 停车刷卡事件(对应结构体  ALARM_PARKING_CARD)
    public static final int NET_ALARM_VEHICLE_ACC               = 0x31a6;       // 车辆ACC报警事件(对应结构体 ALARM_VEHICLE_ACC_INFO)
    public static final int NET_ALARM_HEATIMG_TEMPER            = 0x31aa;       // 热成像测温点温度异常报警事件(对应结构体 ALARM_HEATIMG_TEMPER_INFO)
    public static final int NET_ALARM_NEW_FILE                  = 0x31b3;       // 新文件事件(对应 ALARM_NEW_FILE_INFO)
    public static final int NET_ALARM_HUMAM_NUMBER_STATISTIC    = 0x31cc;       // 人数量/客流量统计事件 (对应结构体 ALARM_HUMAN_NUMBER_STATISTIC_INFO)
    public static final int NET_ALARM_IP_CONFLICT               = 0x3170;       // IP冲突事件(对应结构体 ALARM_IP_CONFLICT_INFO)
    public static final int NET_ALARM_ARMMODE_CHANGE_EVENT      = 0x3175;       // 布撤防状态变化事件(对应结构体 ALARM_ARMMODE_CHANGE_INFO)
    public static final int NET_ALARM_ACCESS_CTL_NOT_CLOSE      = 0x3177;       // 门禁未关事件(对应结构体 ALARM_ACCESS_CTL_NOT_CLOSE_INFO)
    public static final int NET_ALARM_ACCESS_CTL_BREAK_IN       = 0x3178;       // 闯入事件(对应结构体 ALARM_ACCESS_CTL_BREAK_IN_INFO)
    public static final int NET_ALARM_ACCESS_CTL_EVENT          = 0x3181;       // 门禁事件(对应结构体 ALARM_ACCESS_CTL_EVENT_INFO)
    public static final int NET_URGENCY_ALARM_EX2               = 0x3182;       // 紧急报警EX2(对 NET_URGENCY_ALARM_EX 的升级,对应结构体 ALARM_URGENCY_ALARM_EX2, 人为触发的紧急事件, 一般处理是联动外部通讯功能请求帮助
    public static final int NET_ALARM_ACCESS_CTL_STATUS         = 0x3185;       // 门禁状态事件(对应结构体 ALARM_ACCESS_CTL_STATUS_INFO)
    public static final int NET_ALARM_ALARMCLEAR                = 0x3187;       // 消警事件(对应结构体  ALARM_ALARMCLEAR_INFO )
    public static final int NET_ALARM_TALKING_HANGUP            = 0x3189;       // 设备主动挂断对讲事件(对应结构体ALARM_TALKING_HANGUP_INFO)
    public static final int NET_ALARM_RCEMERGENCY_CALL          = 0x318b;       // 紧急呼叫报警事件(对应结构体 ALARM_RCEMERGENCY_CALL_INFO)
    public static final int NET_ALARM_FINGER_PRINT              = 0x318d;       // 获取信息事件(对应结构体 ALARM_CAPTURE_FINGER_PRINT_INFO)
    public static final int NET_ALARM_LOGIN_FAILIUR             = 0x3194;       // 登陆失败事件(对应结构体ALARM_LOGIN_FAILIUR_INFO)
    public static final int NET_ALARM_MODULE_LOST               = 0x3195;       // 扩展模块掉线事件(对应结构体 ALARM_MODULE_LOST_INFO)
    public static final int NET_ALARM_ENCLOSURE_ALARM           = 0x319B;       // 电子围栏事件(对应结构体 ALARM_ENCLOSURE_ALARM_INFO)
    public static final int NET_ALARM_BUS_SHARP_ACCELERATE      = 0x31ae;       // 车辆急加速事件(对应结构体 ALARM_BUS_SHARP_ACCELERATE_INFO)
    public static final int NET_ALARM_BUS_SHARP_DECELERATE      = 0x31af;       // 车辆急减速事件(对应结构体 ALARM_BUS_SHARP_DECELERATE_INFO)
    public static final int NET_ALARM_ACCESS_CARD_OPERATE       = 0x31b0;       // 门禁卡数据操作事件(对应结构体ALARM_ACCESS_CARD_OPERATE_INFO)
    public static final int NET_ALARM_FIREWARNING               = 0x31b5;       // 热成像着火点事件 (对应结构体 ALARM_FIREWARNING_INFO)
    public static final int NET_ALARM_WIFI_SEARCH               = 0x31c7;       // 获取到周围环境中WIFI设备上报事件(对应结构体 ALARM_WIFI_SEARCH_INFO)
    public static final int NET_ALARM_HOTSPOT_WARNING           = 0X31d8;       // 热成像热点异常报警(对应结构体 ALARM_HOTSPOT_WARNING_INFO)
    public static final int NET_ALARM_COLDSPOT_WARNING          = 0X31d9;       // 热成像冷点异常报警(对应结构体 ALARM_COLDSPOT_WARNING_INFO)
    public static final int NET_ALARM_FIREWARNING_INFO          = 0X31da;       // 热成像火情事件信息上报(对应结构体 ALARM_FIREWARNING_INFO_DETAIL)
    public static final int NET_ALARM_RADAR_HIGH_SPEED          = 0x31df;       // 雷达监测超速报警事件 智能楼宇专用 (对应结构体 ALARM_RADAR_HIGH_SPEED_INFO)
    public static final int NET_ALARM_RAID_STATE_EX             = 0x31fc;       // RAID异常报警(对应结构体 ALARM_RAID_INFO_EX)
    public static final int NET_ALARM_STORAGE_IPC_FAILURE       = 0x31fd;       // IPC的存储介质故障事件(IPC SD卡异常)(对应结构体 ALARM_STORAGE_IPC_FAILURE_INFO)
    public static final int NET_ALARM_POLLING_ALARM             = 0x31e0;       // 设备巡检报警事件 智能楼宇专用 (对应结构体 ALARM_POLLING_ALARM_INFO)
    public static final int NET_ALARM_TRAFFICSTROBESTATE        = 0x31e2;       // 道闸栏状态事件(对应结构体 ALARM_TRAFFICSTROBESTATE_INFO)
    public static final int NET_ALARM_WIFI_VIRTUALINFO_SEARCH   = 0x31ef;       // WIFI虚拟身份上报事件(对应结构体 ALARM_WIFI_VIRTUALINFO_SEARCH_INFO)
    public static final int NET_ALARM_USER_LOCK_EVENT           = 0x31f9;       // 用户锁定报警事件(对应结构体 ALARM_USER_LOCK_EVENT_INFO)
    public static final int NET_ALARM_GPS_NOT_ALIGNED           = 0x321d;       // GPS未定位报警(对应结构体 ALARM_GPS_NOT_ALIGNED_INFO)
    public static final int NET_ALARM_TRAFFIC_VEHICLE_POSITION  = 0x323c;       // 车辆位置事件(对应的结构体 ALARM_TRAFFIC_VEHICLE_POSITION)
    public static final int NET_ALARM_VIDEOBLIND                = 0x323e;       // 视频遮挡事件(对应结构体 ALARM_VIDEO_BLIND_INFO)
    public static final int NET_ALARM_AUDIO_ANOMALY             = 0x2178;       // 音频异常事件(对应结构体ALARM_AUDIO_ANOMALY)
    public static final int NET_ALARM_DRIVER_NOTCONFIRM         = 0x323f;       // 司机未按确认按钮报警事件(对应结构体 ALARM_DRIVER_NOTCONFIRM_INFO)
    public static final int NET_ALARM_FACEINFO_COLLECT          = 0x3240;       // 人脸信息录入事件(对应 ALARM_FACEINFO_COLLECT_INFO)
    public static final int NET_ALARM_HIGH_SPEED                = 0x3241;       // 车辆超速报警事件(对应 ALARM_HIGH_SPEED_INFO )
    public static final int NET_ALARM_VIDEO_LOSS                = 0x3242;       // 视频丢失事件(对应 ALARM_VIDEO_LOSS_INFO )
    public static final int NET_ALARM_DOWNLOAD_REMOTE_FILE      = 0x3301;       // 下载远程文件事件(对应 ALARM_DOWNLOAD_REMOTE_FILE_INFO)
    public static final int NET_ALARM_TRAFFIC_LINKAGEALARM      = 0x3353;       // 各种违章事件联动报警输出事件(对应结构体 ALARM_TRAFFIC_LINKAGEALARM_INFO)
    public static final int NET_ALARM_LABELINFO                 = 0x3233;       // IPC新增(2017.4),RFID标签信息采集事件(对应结构体 ALARM_LABELINFO)
    public static final int NET_ALARM_FLOATINGOBJECT_DETECTION  = 0x3442;       // 漂浮物检测事件(对应结构体 ALARM_FLOATINGOBJECT_DETECTION_INFO)
    public static final int NET_ALARM_WATER_LEVEL_DETECTION     = 0x3443;       // 水位检测事件(对应结构体 ALARM_WATER_LEVEL_DETECTION_INFO)
    public static final int NET_ALARM_TRAFFIC_JUNCTION          = 0x3446;       // 交通路口事件(对应结构体 ALARM_TAFFIC_JUNCTION_INFO)
    public static final int NET_EVENT_CROSSLINE_DETECTION       = 0x2188;       // 警戒线事件( 对应结构体 ALARM_EVENT_CROSSLINE_INFO )
    public static final int NET_EVENT_CROSSREGION_DETECTION     = 0x2189;       // 警戒区事件( 对应结构体 ALARM_EVENT_CROSSREGION_INFO )
    public static final int NET_ALARM_POWERFAULT                = 0x3172;       // 电源故障事件(对应结构体ALARM_POWERFAULT_INFO)
    public static final int NET_ALARM_CHASSISINTRUDED           = 0x3173;       // 机箱入侵(防拆)报警事件(对应结构体ALARM_CHASSISINTRUDED_INFO)
    public static final int NET_ALARM_BYPASSMODE_CHANGE_EVENT   = 0x3176;       // 旁路状态变化事件(对应结构体ALARM_BYPASSMODE_CHANGE_INFO)
    public static final int NET_ALARM_ACCESS_CTL_REPEAT_ENTER   = 0x3179;       // 反复进入事件(对应结构体ALARM_ACCESS_CTL_REPEAT_ENTER_INFO)
    public static final int NET_ALARM_ACCESS_CTL_DURESS         = 0x3180;       // 胁迫卡刷卡事件(对应结构体ALARM_ACCESS_CTL_DURESS_INFO)
    public static final int NET_ALARM_INPUT_SOURCE_SIGNAL       = 0x3183;       // 报警输入源信号事件(只要有输入就会产生该事件, 不论防区当前的模式,无法屏蔽, 对应 ALARM_INPUT_SOURCE_SIGNAL_INFO )
    public static final int NET_ALARM_OPENDOORGROUP             = 0x318c;       // 多人组合开门事件(对应结构体ALARM_OPEN_DOOR_GROUP_INFO)
    public static final int NET_ALARM_SUBSYSTEM_STATE_CHANGE    = 0x318f;       // 子系统状态改变事件(对应结构体ALARM_SUBSYSTEM_STATE_CHANGE_INFO)
    public static final int NET_ALARM_PSTN_BREAK_LINE           = 0x3196;       // PSTN掉线事件(对应结构体ALARM_PSTN_BREAK_LINE_INFO)
    public static final int NET_ALARM_DEFENCE_ARMMODE_CHANGE    = 0x31d2;       // 防区布撤防状态改变事件(对应结构体 ALARM_DEFENCE_ARMMODECHANGE_INFO)
    public static final int NET_ALARM_SUBSYSTEM_ARMMODE_CHANGE  = 0x31d3;       // 子系统布撤防状态改变事件(对应结构体 ALARM_SUBSYSTEM_ARMMODECHANGE_INFO)
    public static final int NET_ALARM_SENSOR_ABNORMAL           = 0x31dc;       // 探测器异常报警(对应结构体 ALARM_SENSOR_ABNORMAL_INFO)
    public static final int NET_ALARM_CROWD_DETECTION           = 0x3305;       // 人群密度检测事件(对应结构体 ALARM_CROWD_DETECTION_INFO)
    public static final int NET_ALARM_FACE_FEATURE_ABSTRACT     = 0x3306;       // 目标特征向量重建结果事件(对应结构体 ALARM_FACE_FEATURE_ABSTRACT_INFO)
    public static final int NET_ALARM_CITIZEN_PICTURE_COMPARE   = 0x330d;       // 人证比对事件(对应结构体 ALARM_CITIZEN_PICTURE_COMPARE_INFO)
    public static final int NET_ALARM_MAN_NUM_DETECTION         = 0x3223;       // 立体视觉区域内人数统计报警(对应结构体ALARM_MAN_NUM_INFO)
    public static final int NET_ALARM_ENGINE_FAILURE_STATUS     = 0x344F;       // 发动机故障状态上报(对应 ALARM_ENGINE_FAILURE_STATUS_INFO)
    public static final int NET_ALARM_ANATOMY_TEMP_DETECT       = 0x3454;       // 人体温智能检测事件(对应结构体 ALARM_ANATOMY_TEMP_DETECT_INFO)
    public static final int NET_ALARM_REGULATOR_ABNORMAL        = 0x3455;       // 标准黑体源异常报警事件(对应结构体 ALARM_REGULATOR_ABNORMAL_INFO)
    public static final int NET_ALARM_MINIINDOOR_RADAR_ALARM    = 0x34A7;       // Mini雷达报警事件(对应结构体 NET_ALARM_MINIINDOOR_RADAR_ALARM_INFO)
    public static final int NET_ALARM_QR_CODE_CHECK             = 0x335a;       // 二维码上报事件(对应结构体 ALARM_QR_CODE_CHECK_INFO)
    public static final int NET_ALARM_TRAFFIC_XINKONG           = 0x335f;       // 交通态势报警事件（对接结构体 ALARM_TRAFFIC_XINKONG_INFO）
    public static final int NET_ALARM_WIRELESSDEV_LOWPOWER      = 0x31C8;       // 获取无线设备低电量上报事件(对应结构体ALARM_WIRELESSDEV_LOWPOWER_INFO)
    public static final int NET_ALARM_INTELLI_MODULE_HIGH_TEMP  = 0x34BB;       // 智能模块温度异常事件(对应结构体 NET_ALARM_INTELLI_MODULE_HIGH_TEMP_INFO)
    public static final int NET_ALARM_INTELLI_MODULE_OFFLINE    = 0x34BC;       // 智能模块断线事件(对应结构体 NET_ALARM_INTELLI_MODULE_OFFLINE_INFO)
    public static final int NET_ALARM_RF_JAMMING                = 0x34C0;       // RF干扰事件(对应结构体 NET_ALARM_RF_JAMMING_INFO)
    public static final int NET_ALARM_ARMING_FAILURE            = 0x34C1;       // 布防失败事件(对应结构体 NET_ALARM_ARMING_FAILURE_INFO)
    public static final int NET_ALARM_USER_MODIFIED             = 0x34C2;       // 用户信息被修改(增加、删除、修改)事件(对应结构体 NET_ALARM_USER_MODIFIED_INFO)
    public static final int NET_ALARM_MANUAL_TEST               = 0x34C3;       // 手动测试事件(对应结构体 NET_ALARM_MANUAL_TEST_INFO)
    public static final int NET_ALARM_DEVICE_MODIFIED           = 0x34C4;       // 设备设息修改(增加、删除、修改)事件(对应结构体 NET_ALARM_DEVICE_MODIFIED_INFO)
    public static final int NET_ALARM_ATS_FAULT                 = 0x34C5;       // 报警传输系统故障事件(对应结构体 NET_ALARM_ATS_FAULT_INFO)
    public static final int NET_ALARM_ARC_OFFLINE               = 0x34C6;       // 报警接收中心离线事件(对应结构体 NET_ALARM_ARC_OFFLINE_INFO)
    public static final int NET_ALARM_WIFI_FAILURE              = 0x34C7;       // wifi故障事件(对应结构体 NET_ALARM_WIFI_FAILURE_INFO)
    public static final int NET_ALARM_OVER_TEMPERATURE          = 0x34C8;       // 超温报警事件(对应结构体 NET_ALARM_OVER_TEMPERATURE_INFO)
    public static final int NET_ALARM_WIRELESSDEV_POWERLESS     = 0x3498;       // 探测器主电丢失事件 (对应结构体 ALARM_WIRELESSDEV_POWERLESS_INFO)
    public static final int NET_ALARM_KEYPAD_LOCK               = 0x219E;       // 键盘锁定事件(对应结构体 ALARM_KEYPAD_LOCK_INFO)
    public static final int NET_ALARM_BETWEENRULE_TEMP_DIFF     = 0x31d6;       // 热成像规则间温差异常报警(对应结构体 ALARM_BETWEENRULE_DIFFTEMPER_INFO)
    public static final int DH_ALARM_USERLOCK                   = 0x3300;       // 用户锁定报警事件(对应 ALARM_USERLOCK_INFO)
    public static final int NET_ALARM_AREAARM_MODECHANGE        = 0x330e;       // 区域防区模式改变(对应结构体ALARM_AREAARM_MODECHANGE_INFO)
    public static final int NET_ALARM_AREAALARM                 = 0x3310;       // 区域报警(对应结构体ALARM_AREAALARM_INFO)
    public static final int NET_ALARM_RADAR_REGIONDETECTION     = 0x3370;       // 雷达区域检测事件(对应结构体 ALARM_RADAR_REGIONDETECTION_INFO)
    public static final int NET_ALARM_TRAFFIC_PARKING_TIMEOUT   = 0x334C;       // 停车时长超限事件（对应的结构体 ALARM_TRAFFIC_PARKING_TIMEOUT_INFO）
    public static final int NET_ALARM_TRAFFIC_SUSPICIOUSCAR     = 0x31a7;       // 嫌疑车辆上报事件(对应结构体 ALARM_TRAFFIC_SUSPICIOUSCAR_INFO)
    public static final int NET_ALARM_PARKING_LOT_STATUS_DETECTION = 0x3451;    // 室外停车位状态检测事件 (对应结构体 ALARM_REGION_PARKING_TIMEOUT_INFO)
    public static final int NET_ALARM_REGION_PARKING_TIMEOUT    = 0x3460;       // 区间车位停车超时（对应结构体 ALARM_REGION_PARKING_TIMEOUT_INFO）
    public static final int NET_ALARM_REGION_PARKING_NO_ENTRY_RECORD = 0x3461;  // 区间车位停车，检测到车辆驶出区域时没有匹配到入场信息(对应结构体 ALARM_REGION_PARKING_NO_ENTRY_RECORD_INFO)
    public static final int NET_ALARM_TRAFFIC_LIGHT_STATE       = 0x3458;       // 交通灯状态报警(对应 ALARM_TRAFFIC_LIGHT_STATE_INFO)
    public static final int NET_ALARM_VEHICLE_INOUT             = 0x346A;       // 车辆出入事件(对应结构体 ALARM_VEHICLE_INOUT_INFO)
    public static final int NET_ALARM_FIRE_DETECTION            = 0x343D;       // 火警事件（对于的结构体 ALARM_FIRE_DETECTION_INFO）
    public static final int NET_ALARM_WORKSUIT_FEATURE_ABSTRACT = 0x3481;       // 工装特征向量建模结果上报事件(对应结构体ALARM_WORKSUIT_FEATURE_ABSTRACT_INFO)
    public static final int NET_ALARM_TRAFFIC_FLOW_QUEUE        = 0x349C;       // 交通路口排队事件(对应结构体 ALARM_TRAFFIC_FLOW_QUEUE_INFO)
    public static final int NET_ALARM_TRAFFIC_FLOW_JUNTION      = 0x349D;       // 交通路口过车事件(对应结构体 ALARM_TRAFFIC_FLOW_JUNTION_INFO)
    public static final int NET_ALARM_TRAFFIC_FLOW_VEHICLE_STOP = 0x349E;       // 交通路口停车事件(对应结构体 ALARM_TRAFFIC_FLOW_VEHICLE_STOP_INFO)
    public static final int NET_ALARM_TRAFFIC_FLOW_STAT         = 0x349F;       // 交通路口车道统计事件(对应结构体 ALARM_TRAFFIC_FLOW_STAT_INFO)
    public static final int NET_ALARM_TRAFFIC_FLOW_STAT_EX      = 0x34A0;       // 交通路口车道统计拓展事件(对应结构体 ALARM_TRAFFIC_FLOW_STAT_EX_INFO)
    public static final int NET_ALARM_DYNAMIC_PWDLOCK_UPLOAD_RANDOMCODE = 0x34B5; // 密码锁上报随机码事件(对应结构体 NET_ALARM_DYNAMIC_LOCK_UPLOAD_RANDOMCODE_INFO)
    public static final int NET_ALARM_DYNAMIC_PWDLOCK_CLOSE     = 0x34B7;       // 密码锁闭锁事件(对应结构体 NET_ALARM_DYNAMIC_PWDLOCK_CLOSE_INFO)
    public static final int NET_ALARM_HUMIDITY_ALARM            = 0x3490;       /// 湿度报警事件(对应结构体 ALARM_HUMIDITY_ALARM_INFO)
    public static final int NET_ALARM_RTSC_PHASE_RUNING         = 0x5001;       // RTSC跑动暂停事件(对应ALARM_RTSC_PHASE_RUNNING_INFO)
    public static final int NET_ALARM_RTSC_LAMP_RUNING          = 0x5002;       // RTSC灯暂停事件(对应ALARM_RTSC_LAMP_RUNNING_INFO)
    public static final int NET_ALARM_RTSC_RUNING               = 0x5003;       // 信号机运行事件(对应ALARM_RTSC_RUNNING_INFO)
    public static final int NET_ALARM_RTSC_TRAFFIC              = 0x5004;       // 信号机交通信息事件(对应ALARM_RTSC_TRAFFIC_INFO)
    public static final int NET_ALARM_DRASTIC_MOTION            = 0x34F4;       //剧烈运动报警(对应结构体 NET_ALARM_DRASTIC_MOTION_INFO)
    public static final int NET_ALARM_OPEN_CLOSE_DOOR_DETECTION = 0x34F0;       //反复开关门事件(对应结构体 NET_ALARM_OPEN_CLOSE_DOOR_DETECTION_INFO)
    public static final int NET_ALARM_TRAPPED_IN_LIFT_DETECTION = 0x34E3;       //电梯困人检测(对应 NET_ALARM_TRAPPED_IN_LIFT_DETECTION_INFO)
    public static final int NET_ALARM_DOOR_STATE_DETECTION      = 0x34E0;       //开关门检测事件(对应 NET_ALARM_DOOR_STATE_DETECTION_INFO)
    public static final int NET_ALARM_CITY_MOTORPARKING         = 0x3477;       //城市机动车违停事件(对应结构体 ALARM_CITY_MOTORPARKING_INFO)
    public static final int NET_ALARM_NONMOTOR_ENTRYING         = 0x3474;       //非机动车进入电梯事件(对应 ALARM_NONMOTOR_ENTRYING_INFO)
    public static final int NET_ALARM_REQUEST_IDLE_MODE         = 0x34B1;       //请求进入休眠模式事件(对应结构体 NET_ALARM_REQUEST_IDLE_MODE)
    public static final int NET_ALARM_FORCE_INTO_IDLE_MODE      = 0x34B2;       //强制进入休眠模式通知(对应结构体 NET_ALARM_FORCE_INTO_IDLE_MODE)
    public static final int NET_ALARM_XRAY_PIP_COMMUNICATION_ABNORMITY = 0x7001; //X射线画中画通讯异常事件(对应结构体ALARM_XRAY_PIP_COMMUNICATION_ABNORMITY_INFO)
    public static final int NET_ALARM_XRAY_ACQUISITION_SYSTEM_ABNORMITY = 0x7002; //X光机图像采集系统通信异常事件(对应结构体ALARM_XRAY_ACQUISITION_SYSTEM_ABNORMITY_INFO)
    public static final int NET_ALARM_XRAY_EMERGENCY_STOP       = 0x7003;       //X光机紧急停止事件(对应结构体ALARM_XRAY_EMERGENCY_STOP_INFO)
    public static final int NET_ALARM_XRAY_DETECTOR_PROTECTION_OPEN = 0x7004;   //X光机接收器件防护装置打开事件(对应结构体ALARM_XRAY_DETECTOR_PROTECTION_OPEN_INFO)
    public static final int NET_ALARM_XRAY_MACHINE_SHELL_OPEN   = 0x7005;       //X光机外罩打开事件(对应结构体ALARM_XRAY_MACHINE_SHELL_OPEN_INFO)
    public static final int NET_ALARM_XRAY_KEYBOARD_ABNORMITY   = 0x7006;       //X光机操作键盘通信异常事件(对应结构体ALARM_XRAY_KEYBOARD_ABNORMITY_INFO)
    public static final int NET_ALARM_XRAY_TRANSMISSION_BELT_ABNORMITY = 0x7007; //X光机图像传送带状态异常事件(对应结构体ALARM_XRAY_TRANSMISSION_BELT_ABNORMITY_INFO)
    public static final int NET_ALARM_POWER_GROUND_DETECTION_ABNORMAL = 0x7008; //X光机接地异常检测事件(对应结构体ALARM_POWER_GROUND_DETECTION_ABNORMAL_INFO)
    public static final int NET_ALARM_XRAY_SOURCE_ABNORMITY     = 0x7009;       //X光机X射线发生器故障事件(对应结构体ALARM_XRAY_SOURCE_ABNORMITY_INFO)
    public static final int NET_ALARM_TALKING_CANCELCALL        = 0x3303;       //设备呼叫中取消呼叫事件(对应结构体 ALARM_TALKING_CANCELCALL_INFO)
    public static final int NET_ALARM_INVITE_TIMEOUT            = 0x34FC;       //语音呼叫超时事件(对应结构体 NET_ALARM_INVITE_TIMEOUT_INFO)
    public static final int NET_ALARM_XRAY_EMERGENCY_ALARM      = 0x34DE;       //X光机一键报警事件(对应 NET_ALARM_XRAY_EMERGENCY_ALARM_INFO)
    public static final int NET_ALARM_TRAFFIC_PARKING           = 0x31f4;       //违章停车事件(对应结构体 ALARM_TRAFFIC_PARKING_INFO)
    public static final int NET_ALARM_HY_FIRE_CONTROL_DISMANTLE_FAULT = 0x6008; //消防设备拆除事件(对应ALARM_HY_FIRE_CONTROL_DISMANTLE_FAULT_INFO)
    public static final int NET_ALARM_HUMAN_TRAIT               = 0x34F6;       //人员信息事件(对应结构体 NET_ALARM_HUMAN_TRAIT_INFO)
    public static final int NET_ALARM_WATER_SPEED_DETECTION     = 0x348F;       //水流速检测事件(对应结构体 ALARM_WATER_SPEED_DETECTION_INFO)
    public static final int NET_EVENT_MOTIONDETECT              = 0x218f;       //视频移动侦测事件(对应结构体 ALARM_MOTIONDETECT_INFO)
    public static final int NET_ALARM_PROFILE_ALARM_TRANSMIT    = 0x31a5;       //报警传输事件(对应结构体ALARM_PROFILE_ALARM_TRANSMIT_INFO)
    public static final int NET_ALARM_RAIN_FALL_MSG             = 0x3485;       //雨量数据上报事件(对应结构体 ALARM_RAIN_FALL_MSG_INFO)
    public static final int NET_ALARM_GRAIN_HEIGHT_DETECTION    = 0x34EF;       //动粮检测事件(对应结构体 NET_ALARM_GRAIN_HEIGHT_DETECTION_INFO)
    public static final int NET_ALARM_ASGFOLLOWING              = 0x3022;       //闸机尾随报警事件( 对应结构体 ALARM_ASGFOLLOWING_INFO )
    public static final int NET_ALARM_ASGCLIMBOVER              = 0x3023;       //闸机翻越报警事件( 对应结构体 ALARM_ASGCLIMBOVER_INFO )
    public static final int NET_EVENT_FACE_DETECTION            = 0x218b;       //目标检测事件( 对应结构体 ALARM_EVENT_FACE_INFO )
    public static final int NET_ALARM_ELEVATOR_ALARM            = 0x34F1;       //电梯异常报警事件(对应结构体 NET_ALARM_ELEVATOR_ALARM_INFO)
    public static final int NET_ALARM_ELEVATOR_WORK             = 0x21AC;       //上报电梯运行数据报警(对应结构体 NET_ALARM_ELEVATOR_WORK_INFO)
    public static final int NET_ALARM_TRAFFIC_CAR_PASSING       = 0x34A5;       //车辆进出虚拟线圈状态事件(对应结构体 ALARM_TRAFFIC_CAR_PASSING_INFO)
    public static final int NET_ALARM_BATTERY_ABNORMAL_STATE_ALARM = 0x34F3;    //大电池拆除报警(对应结构体 NET_ALARM_BATTERY_ABNORMAL_STATE_ALARM_INFO)
    // 内部常量定义
    public static final int NET_ALARM_WIFI_SEARCH_EX            = 0x8000;       // 获取到周围环境中WIFI设备上报事件(对应结构体 ALARM_WIFI_SEARCH_INFO_EX)
    public static final int NET_EM_CFG_RADIO_REGULATOR          = 11102;        // 人体测温标准黑体配置, 对应结构体 NET_CFG_RADIO_REGULATOR，通道号不能为-1
    public static final int MAX_SUNTIME_COUNT                   = 12;           // 日出日落时间个数
    // 报警上传功能的报警类型,对应CLIENT_StartService接口、NEW_ALARM_UPLOAD结构体.
    public static final int NET_UPLOAD_RCEMERGENCY_CALL         = 0x4023;       // 紧急呼叫报警事件, 对应结构体 ALARM_RCEMERGENCY_CALL_INFO
    public static final int NET_UPLOAD_FS_RECOGNITION           = 0x402F;       //目标识别事件, 对应结构体 NET_ALARM_UPLOAD_FS_RECOGNITION_INFO
    // 订阅Bus状态对应事件上报(CLIENT_AttachBusState)
    public static final int NET_ALARM_BUS_PASSENGER_CARD_CHECK  = 0x0009;       // 乘客刷卡事件(对应结构体 ALARM_PASSENGER_CARD_CHECK )
    // 帧类型掩码定义
    public static final int FRAME_TYPE_MOTION                   = 0x00000001;   // 动检帧
    // CLIENT_RealLoadPictureEx 智能抓图事件
    public static final int EVENT_IVS_ALL                       = 0x00000001;   // 订阅所有事件
    public static final int EVENT_IVS_CROSSLINEDETECTION        = 0x00000002;   // 警戒线事件(对应 DEV_EVENT_CROSSLINE_INFO)
    public static final int EVENT_IVS_CROSSREGIONDETECTION      = 0x00000003;   // 警戒区事件(对应 DEV_EVENT_CROSSREGION_INFO)
    public static final int EVENT_IVS_LEFTDETECTION             = 0x00000005;   // 物品遗留事件(对应 DEV_EVENT_LEFT_INFO)
    public static final int EVENT_IVS_STAYDETECTION             = 0x00000006;   // 停留事件(对应 DEV_EVENT_STAY_INFO)
    public static final int EVENT_IVS_WANDERDETECTION           = 0x00000007;   // 徘徊事件(对应  DEV_EVENT_WANDER_INFO)
    public static final int EVENT_IVS_PRESERVATION              = 0x00000008;   // 物品保全事件(对应 DEV_EVENT_PRESERVATION_INFO)
    public static final int EVENT_IVS_MOVEDETECTION             = 0x00000009;   // 移动事件(对应 DEV_EVENT_MOVE_INFO)
    public static final int EVENT_IVS_NUMBERSTAT                = 0x00000010;   // 数量统计事件(对应 DEV_EVENT_NUMBERSTAT_INFO)
    public static final int EVENT_IVS_RIOTERDETECTION           = 0x0000000B;   // 聚众事件(对应 DEV_EVENT_RIOTERL_INFO)
    public static final int EVENT_IVS_FIREDETECTION             = 0x0000000C;   // 火警事件(对应 DEV_EVENT_FIRE_INFO)
    public static final int EVENT_IVS_SMOKEDETECTION            = 0x0000000D;   // 烟雾报警事件(对应 DEV_EVENT_SMOKE_INFO)
    public static final int EVENT_IVS_FIGHTDETECTION            = 0x0000000E;   // 斗殴事件(对应 DEV_EVENT_FIGHT_INFO)
    public static final int EVENT_IVS_VIDEOABNORMALDETECTION    = 0x00000013;   // 视频异常事件(对应 DEV_EVENT_VIDEOABNORMALDETECTION_INFO)
    public static final int EVENT_IVS_TRAFFICACCIDENT           = 0x00000016;   /// 交通事故事件(对应 DEV_EVENT_TRAFFICACCIDENT_INFO)
    public static final int EVENT_IVS_TRAFFICJUNCTION           = 0x00000017;   // 交通路口事件----老规则(对应 DEV_EVENT_TRAFFICJUNCTION_INFO)
    public static final int EVENT_IVS_TRAFFICGATE               = 0x00000018;   // 交通卡口事件----老规则(对应 DEV_EVENT_TRAFFICGATE_INFO)
    public static final int EVENT_IVS_FACEDETECT                = 0x0000001A;   // 目标检测事件 (对应 DEV_EVENT_FACEDETECT_INFO)(智能规则对应  EVENT_IVS_FACEDETECT)
    public static final int EVENT_IVS_TRAFFICJAM                = 0x0000001B;   // 交通拥堵事件(对应 DEV_EVENT_TRAFFICJAM_INFO)
    public static final int EVENT_IVS_TRAFFIC_RUNREDLIGHT       = 0x00000100;   // 交通违章-闯红灯事件(对应 DEV_EVENT_TRAFFIC_RUNREDLIGHT_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERLINE          = 0x00000101;   // 交通违章-压车道线事件(对应 DEV_EVENT_TRAFFIC_OVERLINE_INFO)
    public static final int EVENT_IVS_TRAFFIC_RETROGRADE        = 0x00000102;   // 交通违章-逆行事件(对应  DEV_EVENT_TRAFFIC_RETROGRADE_INFO)
    public static final int EVENT_IVS_TRAFFIC_TURNLEFT          = 0x00000103;   // 交通违章-违章左转(对应 DEV_EVENT_TRAFFIC_TURNLEFT_INFO)
    public static final int EVENT_IVS_TRAFFIC_TURNRIGHT         = 0x00000104;   // 交通违章-违章右转(对应 DEV_EVENT_TRAFFIC_TURNRIGHT_INFO)
    public static final int EVENT_IVS_TRAFFIC_UTURN             = 0x00000105;   // 交通违章-违章掉头(对应 DEV_EVENT_TRAFFIC_UTURN_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERSPEED         = 0x00000106;   // 交通违章-超速(对应 DEV_EVENT_TRAFFIC_OVERSPEED_INFO)
    public static final int EVENT_IVS_TRAFFIC_UNDERSPEED        = 0x00000107;   // 交通违章-低速(对应 DEV_EVENT_TRAFFIC_UNDERSPEED_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKING           = 0x00000108;   // 交通违章-违章停车(对应 DEV_EVENT_TRAFFIC_PARKING_INFO)
    public static final int EVENT_IVS_TRAFFIC_WRONGROUTE        = 0x00000109;   // 交通违章-不按车道行驶(对应 DEV_EVENT_TRAFFIC_WRONGROUTE_INFO)
    public static final int EVENT_IVS_TRAFFIC_CROSSLANE         = 0x0000010A;   // 交通违章-违章变道(对应 DEV_EVENT_TRAFFIC_CROSSLANE_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERYELLOWLINE    = 0x0000010B;   // 交通违章-压黄线 (对应 DEV_EVENT_TRAFFIC_OVERYELLOWLINE_INFO)
    public static final int EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE = 0x0000010E;   // 交通违章-黄牌车占道事件(对应 DEV_EVENT_TRAFFIC_YELLOWPLATEINLANE_INFO)
    public static final int EVENT_IVS_TRAFFIC_PEDESTRAINPRIORITY = 0x0000010F;  // 交通违章-斑马线行人优先事件(对应 DEV_EVENT_TRAFFIC_PEDESTRAINPRIORITY_INFO)
    public static final int EVENT_IVS_TRAFFIC_NOPASSING         = 0x00000111;   // 交通违章-禁止通行事件(对应 DEV_EVENT_TRAFFIC_NOPASSING_INFO)
    public static final int EVENT_IVS_ABNORMALRUNDETECTION      = 0x00000112;   // 异常奔跑事件(对应 DEV_EVENT_ABNORMALRUNDETECTION_INFO)
    public static final int EVENT_IVS_RETROGRADEDETECTION       = 0x00000113;   // 人员逆行事件(对应 DEV_EVENT_RETROGRADEDETECTION_INFO)
    public static final int EVENT_IVS_TAKENAWAYDETECTION        = 0x00000115;   // 物品搬移事件(对应 DEV_EVENT_TAKENAWAYDETECTION_INFO)
    public static final int EVENT_IVS_PARKINGDETECTION          = 0x00000116;   // 非法停车事件(对应 DEV_EVENT_PARKINGDETECTION_INFO)
    public static final int EVENT_IVS_FACERECOGNITION           = 0x00000117;   // 目标识别事件(对应 DEV_EVENT_FACERECOGNITION_INFO, (对应的智能规则配置  CFG_FACERECOGNITION_INFO)
    public static final int EVENT_IVS_TRAFFIC_MANUALSNAP        = 0x00000118;   // 交通手动抓拍事件(对应  DEV_EVENT_TRAFFIC_MANUALSNAP_INFO)
    public static final int EVENT_IVS_TRAFFIC_FLOWSTATE         = 0x00000119;   // 交通流量统计事件(对应 DEV_EVENT_TRAFFIC_FLOW_STATE)
    public static final int EVENT_IVS_TRAFFIC_VEHICLEINROUTE    = 0x0000011B;   // 有车占道事件(对应 DEV_EVENT_TRAFFIC_VEHICLEINROUTE_INFO)
    public static final int EVENT_ALARM_LOCALALARM              = 0x0000011D;   // 外部报警事件(对应 DEV_EVENT_ALARM_INFO)
    public static final int EVENT_IVS_PSRISEDETECTION           = 0x0000011E;   // 囚犯起身事件(对应 DEV_EVENT_PSRISEDETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_TOLLGATE          = 0x00000120;   // 交通违章--卡口事件----新规则(对应 DEV_EVENT_TRAFFICJUNCTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_VEHICLEINBUSROUTE = 0x00000124;   // 交通违章--占用公交车道事件(对应 DEV_EVENT_TRAFFIC_VEHICLEINBUSROUTE_INFO)
    public static final int EVENT_IVS_TRAFFIC_BACKING           = 0x00000125;   // 交通违章--违章倒车事件(对应 DEV_EVENT_IVS_TRAFFIC_BACKING_INFO)
    public static final int EVENT_IVS_AUDIO_ABNORMALDETECTION   = 0x00000126;   // 声音异常检测(对应 DEV_EVENT_IVS_AUDIO_ABNORMALDETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_RUNYELLOWLIGHT    = 0x00000127;   // 交通违章-闯黄灯事件(对应 DEV_EVENT_TRAFFIC_RUNYELLOWLIGHT_INFO)
    public static final int EVENT_IVS_CLIMBDETECTION            = 0x00000128;   // 攀高检测事件(对应 DEV_EVENT_IVS_CLIMB_INFO)
    public static final int EVENT_IVS_LEAVEDETECTION            = 0x00000129;   // 离岗检测事件(对应 DEV_EVENT_IVS_LEAVE_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKINGONYELLOWBOX = 0x0000012A;  // 交通违章--黄网格线抓拍事件(对应 DEV_EVENT_TRAFFIC_PARKINGONYELLOWBOX_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING = 0x0000012B; // 车位有车事件(对应 DEV_EVENT_TRAFFIC_PARKINGSPACEPARKING_INFO )
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING = 0x0000012C; // 车位无车事件(对应  DEV_EVENT_TRAFFIC_PARKINGSPACENOPARKING_INFO )
    public static final int EVENT_IVS_TRAFFIC_PEDESTRAIN        = 0x0000012D;   // 交通行人事件(对应 DEV_EVENT_TRAFFIC_PEDESTRAIN_INFO)
    public static final int EVENT_IVS_TRAFFIC_THROW             = 0x0000012E;   // 交通抛洒物品事件(对应 DEV_EVENT_TRAFFIC_THROW_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACEOVERLINE = 0x00000134; // 车位压线事件(对应 DEV_EVENT_TRAFFIC_PARKINGSPACEOVERLINE_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERSTOPLINE      = 0X00000137;   // 交通违章--压停止线事件(对应 DEV_EVENT_TRAFFIC_OVERSTOPLINE)
    public static final int EVENT_IVS_TRAFFIC_WITHOUT_SAFEBELT  = 0x00000138;   // 交通违章--交通未系安全带事件(对应 DEV_EVENT_TRAFFIC_WITHOUT_SAFEBELT)
    public static final int EVENT_IVS_TRAFFIC_DRIVER_SMOKING    = 0x00000139;   // 驾驶员抽烟事件(对应 DEV_EVENT_TRAFFIC_DRIVER_SMOKING)
    public static final int EVENT_IVS_TRAFFIC_DRIVER_CALLING    = 0x0000013A;   // 驾驶员打电话事件(对应 DEV_EVENT_TRAFFIC_DRIVER_CALLING)
    public static final int EVENT_IVS_TRAFFIC_PASSNOTINORDER    = 0x0000013C;   // 交通违章--未按规定依次通行(对应 DEV_EVENT_TRAFFIC_PASSNOTINORDER_INFO)
    public static final int EVENT_IVS_CROSSLINEDETECTION_EX     = 0x00000151;   // 警戒线扩展事件
    public static final int EVENT_ALARM_VIDEOBLIND              = 0x00000153;   // 视频遮挡事件(对应 DEV_EVENT_ALARM_VIDEOBLIND)
    public static final int EVENT_IVS_TRAFFIC_JAM_FORBID_INTO   = 0x00000163;   // 交通违章--车辆拥堵禁入事件(对应 DEV_EVENT_ALARM_JAMFORBIDINTO_INFO)
    public static final int EVENT_IVS_TRAFFIC_FCC               = 0x0000016B;   // 加油站提枪、挂枪事件(对应  DEV_EVENT_TRAFFIC_FCC_INFO)
    public static final int EVENT_IVS_TUMBLE_DETECTION          = 0x00000177;   // 倒地报警事件(对应 DEV_EVENT_TUMBLE_DETECTION_INFO)
    public static final int EVENT_IVS_DISTANCE_DETECTION        = 0x0000024A;   // 异常间距事件 (对应 DEV_EVENT_DISTANCE_DETECTION_INFO)
    public static final int EVENT_IVS_ACCESS_CTL                = 0x00000204;   // 门禁事件 (对应 DEV_EVENT_ACCESS_CTL_INFO)
    public static final int EVENT_IVS_SNAPMANUAL                = 0x00000205;   // SnapManual事件(对应 DEV_EVENT_SNAPMANUAL)
    public static final int EVENT_IVS_TRAFFIC_ELETAGINFO        = 0x00000206;   // RFID电子车牌标签事件(对应 DEV_EVENT_TRAFFIC_ELETAGINFO_INFO)
    public static final int EVENT_IVS_TRAFFIC_TIREDPHYSIOLOGICAL = 0x00000207;  // 生理疲劳驾驶事件(对应 DEV_EVENT_TIREDPHYSIOLOGICAL_INFO)
    public static final int EVENT_IVS_CITIZEN_PICTURE_COMPARE   = 0x00000209;   // 人证比对事件(对应  DEV_EVENT_CITIZEN_PICTURE_COMPARE_INFO )
    public static final int EVENT_IVS_TRAFFIC_TIREDLOWERHEAD    = 0x0000020A;   // 开车低头报警事件(对应DEV_EVENT_TIREDLOWERHEAD_INFO)
    public static final int EVENT_IVS_TRAFFIC_DRIVERLOOKAROUND  = 0x0000020B;   // 开车左顾右盼报警事件(对应DEV_EVENT_DRIVERLOOKAROUND_INFO)
    public static final int EVENT_IVS_TRAFFIC_DRIVERLEAVEPOST   = 0x0000020C;   // 开车离岗报警事件(对应DEV_EVENT_DRIVERLEAVEPOST_INFO)
    public static final int EVENT_IVS_MAN_STAND_DETECTION       = 0x0000020D;   // 立体视觉站立事件(对应DEV_EVENT_MANSTAND_DETECTION_INFO)
    public static final int EVENT_IVS_MAN_NUM_DETECTION         = 0x0000020E;   // 立体视觉区域内人数统计事件(对应DEV_EVENT_MANNUM_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_DRIVERYAWN        = 0x00000210;   // 开车打哈欠事件(对应DEV_EVENT_DRIVERYAWN_INFO)
    public static final int EVENT_IVS_HUMANTRAIT                = 0x00000215;   // 人体特征事件(对应 DEV_EVENT_HUMANTRAIT_INFO)
    public static final int EVENT_IVS_FACEANALYSIS              = 0x00000217;   // 人脸分析事件 (暂未有具体事件)
    public static final int EVENT_IVS_TRAFFIC_QUEUEJUMP         = 0x0000021C;   // 车辆加塞事件(对应 DEV_EVENT_TRAFFIC_QUEUEJUMP_INFO)
    public static final int EVENI_IVS_XRAY_DETECTION            = 0x00000223;   // X光检测事件 (对应 DEV_EVENT_XRAY_DETECTION_INFO)
    public static final int EVENT_IVS_HIGHSPEED                 = 0x0000022B;   // 车辆超速报警事件(对应 DEV_EVENT_HIGHSPEED_INFO)
    public static final int EVENT_IVS_CROWDDETECTION            = 0x0000022C;   // 人群密度检测事件(对应结构体 DEV_EVENT_CROWD_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_CARDISTANCESHORT  = 0x0000022D;   // 车间距过小报警事件(对应 DEV_EVENT_TRAFFIC_CARDISTANCESHORT_INFO)
    public static final int EVENT_IVS_PEDESTRIAN_JUNCTION       = 0x00000230;   // 行人卡口事件(对应 DEV_EVENT_PEDESTRIAN_JUNCTION_INFO)
    public static final int EVENT_IVS_VEHICLE_RECOGNITION       = 0x00000231;   // 车牌对比事件(对应 DEV_EVENT_VEHICLE_RECOGNITION_INFO)
    public static final int EVENT_IVS_BANNER_DETECTION          = 0x0000023B;   // 拉横幅事件(对应 DEV_EVENT_BANNER_DETECTION_INFO)
    public static final int EVENT_IVS_ELEVATOR_ABNORMAL         = 0x0000023D;   // 电动扶梯运行异常事件 (对应DEV_EVENT_ELEVATOR_ABNORMAL_INFO)
    public static final int EVENT_IVS_VEHICLEDETECT             = 0x0000023F;   // 机动车检测 (只用于规则配置，事件采用EVENT_IVS_TRAFFICJUNCTION)
    public static final int EVENT_IVSS_FACEATTRIBUTE            = 0x00000243;   // IVSS目标检测事件 (暂未有具体事件)
    public static final int EVENT_IVSS_FACECOMPARE              = 0x00000244;   // IVSS目标识别事件 (暂未有具体事件)
    public static final int EVENT_IVS_FIREWARNING               = 0x00000245;   // 火警事件(对应 DEV_EVENT_FIREWARNING_INFO)
    public static final int EVENT_IVS_SHOPPRESENCE              = 0x00000246;   // 商铺占道经营事件(对应 DEV_EVENT_SHOPPRESENCE_INFO)
    public static final int EVENT_IVS_FLOWBUSINESS              = 0x0000024E;   // 流动摊贩事件 (对应 DEV_EVENT_FLOWBUSINESS_INFO)
    public static final int EVENT_IVS_LANEDEPARTURE_WARNNING    = 0X00000251;   // 车道偏移预警(对应 DEV_EVENT_LANEDEPARTURE_WARNNING_INFO)
    public static final int EVENT_IVS_FORWARDCOLLISION_WARNNING = 0x00000252;   // 前向碰撞预警(对应 DEV_EVENT_FORWARDCOLLISION_WARNNING_INFO)
    public static final int EVENT_IVS_FLOATINGOBJECT_DETECTION  = 0x00000257;   // 漂浮物检测事件 (对应 DEV_EVENT_FLOATINGOBJECT_DETECTION_INFO)
    public static final int EVENT_IVS_PHONECALL_DETECT          = 0x0000025A;   // 打电话检测事件(对应 DEV_EVENT_PHONECALL_DETECT_INFO)
    public static final int EVENT_IVS_SMOKING_DETECT            = 0x0000025B;   // 吸烟检测事件(对应 DEV_EVENT_SMOKING_DETECT_INFO)
    public static final int EVENT_IVS_RADAR_SPEED_LIMIT_ALARM   = 0x0000025C;   // 雷达限速报警事件(对应 DEV_EVENT_RADAR_SPEED_LIMIT_ALARM_INFO)
    public static final int EVENT_IVS_WATER_LEVEL_DETECTION     = 0x0000025D;   // 水位检测事件 (对应 DEV_EVENT_WATER_LEVEL_DETECTION_INFO)
    public static final int EVENT_IVS_CITY_MOTORPARKING         = 0x0000024F;   // 城市机动车违停事件 (对应 DEV_EVENT_CITY_MOTORPARKING_INFO)
    public static final int EVENT_IVS_CITY_NONMOTORPARKING      = 0x00000250;   // 城市机非动车违停事件 (对应 DEV_EVENT_CITY_NONMOTORPARKING_INFO)
    public static final int EVENT_IVS_HOLD_UMBRELLA             = 0x0000025E;   // 违规撑伞检测事件 (对应 DEV_EVENT_HOLD_UMBRELLA_INFO)
    public static final int EVENT_IVS_GARBAGE_EXPOSURE          = 0x0000025F;   // 垃圾暴露检测事件 (对应 DEV_EVENT_GARBAGE_EXPOSURE_INFO)
    public static final int EVENT_IVS_DUSTBIN_OVER_FLOW         = 0x00000260;   // 垃圾桶满溢检测事件 (对应 DEV_EVENT_DUSTBIN_OVER_FLOW_INFO)
    public static final int EVENT_IVS_DOOR_FRONT_DIRTY          = 0x00000261;   // 门前脏乱检测事件 (对应 DEV_EVENT_DOOR_FRONT_DIRTY_INFO)
    public static final int EVENT_IVS_QUEUESTAY_DETECTION       = 0X00000262;   // 排队滞留时间报警事件 (对应 DEV_EVENT_QUEUESTAY_DETECTION_INFO)
    public static final int EVENT_IVS_QUEUENUM_DETECTION        = 0X00000263;   // 排队人数异常报警事件（对应 DEV_EVENT_QUEUENUM_DETECTION_INFO）
    public static final int EVENT_IVS_GENERATEGRAPH_DETECTION   = 0X00000264;   // 生成图规则事件（对应 DEV_EVENT_GENERATEGRAPH_DETECTION_INFO）
    public static final int EVENT_IVS_TRAFFIC_PARKING_MANUAL    = 0x00000265;   // 交通违章-手动取证(对应  DEV_EVENT_TRAFFIC_PARKING_MANUAL_INFO)
    public static final int EVENT_IVS_HELMET_DETECTION          = 0x00000266;   // 安全帽检测事件(对应 DEV_EVENT_HELMET_DETECTION_INFO)
    public static final int EVENT_IVS_DEPOSIT_DETECTION         = 0x00000267;   // 包裹堆积程度检测事件(对应 DEV_EVENT_DEPOSIT_DETECTION_INFO)
    public static final int EVENT_IVS_HOTSPOT_WARNING           = 0x00000268;   // 热点异常报警事件(对应 DEV_EVENT_HOTSPOT_WARNING_INFO)
    public static final int EVENT_IVS_WEIGHING_PLATFORM_DETECTION = 0x00000269; // 称重平台检测事件(对应 DEV_EVENT_WEIGHING_PLATFORM_DETECTION_INFO)
    public static final int EVENT_IVS_CLASSROOM_BEHAVIOR        = 0x0000026A;   // 课堂行为分析事件(对应 DEV_EVENT_CLASSROOM_BEHAVIOR_INFO)
    public static final int EVENT_IVS_VEHICLE_DISTANCE_NEAR     = 0x0000026B;   // 安全驾驶车距过近报警事件(对应 DEV_EVENT_VEHICLE_DISTANCE_NEAR_INFO)
    public static final int EVENT_IVS_TRAFFIC_DRIVER_ABNORMAL   = 0x0000026C;   // 驾驶员异常报警事件(对应 DEV_EVENT_TRAFFIC_DRIVER_ABNORMAL_INFO)
    public static final int EVENT_IVS_WORKCLOTHES_DETECT        = 0x0000026E;   // 工装(安全帽/工作服等)检测事件(对应 DEV_EVENT_WORKCLOTHES_DETECT_INFO)
    public static final int EVENT_IVS_SECURITYGATE_PERSONALARM  = 0x0000026F;   // 安检门人员报警事件(对应 DEV_EVENT_SECURITYGATE_PERSONALARM_INFO)
    public static final int EVENT_IVS_STAY_ALONE_DETECTION      = 0x00000270;   // 单人独处事件(对应 DEV_EVENT_STAY_ALONE_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_ROAD_CONSTRUCTION = 0x00000272;   // 交通道路施工检测事件(对应 DEV_EVENT_TRAFFIC_ROAD_CONSTRUCTION_INFO)
    public static final int EVENT_IVS_WORKSTATDETECTION         = 0x00000274;   // 作业统计事件(对应 DEV_EVENT_WORKSTATDETECTION_INFO)
    public static final int EVENT_IVS_INTELLI_SHELF             = 0x00000277;   // 智能补货事件(对应 DEV_EVENT_INTELLI_SHELF_INFO)
    public static final int EVENT_IVS_CAR_DRIVING_IN_OUT        = 0x0000027B;   // 车辆驶入驶出状态事件(对应 DEV_EVENT_CAR_DRIVING_IN_OUT_INFO)
    public static final int EVENT_IVS_VIOLENT_THROW_DETECTION   = 0x0000027D;   // 暴力抛物检测(对应 DEV_EVENT_VIOLENT_THROW_DETECTION_INFO)
    public static final int EVENT_IVS_GASSTATION_VEHICLE_DETECT = 0x00000283;   // 加油站车辆检测事件 (对应 DEV_EVENT_GASSTATION_VEHICLE_DETECT_INFO)
    public static final int EVENT_IVS_HIGH_TOSS_DETECT          = 0x0000028D;   // 高空抛物检测(对应DEV_EVENT_HIGH_TOSS_DETECT_INFO)
    public static final int EVENT_IVS_BREED_DETECTION           = 0x00000289;   // 智慧养殖检测事件 (对应 DEV_EVENT_BREED_DETECTION_INFO)
    public static final int EVENT_IVS_PARKING_LOT_STATUS_DETECTION = 0x00000297; // 室外停车位状态检测 (对应 DEV_EVENT_PARKING_LOT_STATUS_DETECTION_INFO)
    public static final int EVENT_IVS_SMART_KITCHEN_CLOTHES_DETECTION = 0x0000029D; // 智慧厨房穿着检测事件（对不戴口罩、厨师帽以及颜色不符合规定的厨师服进行报警）（对应 DEV_EVENT_SMART_KITCHEN_CLOTHES_DETECTION_INFO）
    public static final int EVENT_IVS_ANATOMY_TEMP_DETECT       = 0x00000303;   // 人体温智能检测事件(对应 DEV_EVENT_ANATOMY_TEMP_DETECT_INFO)
    public static final int EVENT_IVS_FOG_DETECTION             = 0x00000308;   // 起雾检测事件(对应 DEV_EVENT_FOG_DETECTION)
    public static final int EVENT_IVS_TRAFFIC_VEHICLE_BC        = 0x00000309;   // 飙车事件（对应 DEV_EVENT_TRAFFIC_VEHICLE_BC ）
    public static final int EVENT_IVS_WATER_STAGE_MONITOR       = 0x0000030D;   // 水位监测事件
    public static final int EVENT_IVS_NONMOTOR_ENTRYING         = 0x0000030C;   // 非机动车进入电梯(对应 DEV_EVENT_NONMOTOR_ENTRYING_INFO)
    public static final int EVENT_IVS_TRAFFIC_ROAD_ALERT        = 0x0000030E;   // 道路安全预警(对应 DEV_EVENT_TRAFFIC_ROAD_ALERT_INFO)
    public static final int EVENT_IVS_BREAK_RULE_BUILDING_DETECTION = 0x0000030F; // 违章建筑检测事件(对应 DEV_EVENT_BREAK_RULE_BUILDIING_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_CAR_MEASUREMENT   = 0x00000320;   // 交通卡口测量(车辆长、宽、高度、重量等)事件 (对应 DEV_EVENT_TRAFFIC_CAR_MEASUREMENT_INFO)
    public static final int EVENT_IVS_CAR_DRIVING_IN            = 0x00000330;   // 车辆驶入事件(对应 DEV_EVENT_CAR_DRIVING_IN_INFO)
    public static final int EVENT_IVS_CAR_DRIVING_OUT           = 0x00000331;   // 车辆驶出事件(对应 DEV_EVENT_CAR_DRIVING_OUT_INFO)
    public static final int EVENT_IVS_TRUCKNOTCLEAN_FOR_PRMA    = 0x0000033A;   // 工程车未清洗 对应 DEV_EVENT_TRUCKNOTCLEAN_FOR_PRMA_INFO
    public static final int EVENT_IVS_DRIVE_ACTION_ANAYLSE      = 0x00000342;   // 驾驶行为分析 (只用于规则配置)
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACE_MANUALSNAP = 0x00000346; // 路侧停车位手动抓图 (对应 DEV_EVENT_PARKINGSPACE_MANUALSNAP_INFO )
    public static final int EVENT_IVS_CONVEYER_BELT_BULK        = 0x00000351;   // 传送带大块异物检测事件(对应DEV_EVENT_CONVEYER_BELT_BULK_INFO )
    public static final int EVENT_IVS_CONVEYER_BELT_NONLOAD     = 0x00000352;   // 传送带空载检测事件(对应DEV_EVENT_CONVEYER_BELT_NONLOAD_INFO )
    public static final int EVENT_IVS_CONVEYER_BELT_RUNOFF      = 0x00000353;   // 传送带跑偏检测事件(对应 DEV_EVENT_CONVEYER_BELT_RUNOFF_INFO )
    public static final int EVENT_IVS_OBJECT_REMOVAL_DETECTION  = 0x0000036A;   // 物品拿取检测事件(对应DEV_EVENT_OBJECT_REMOVAL_DETECTION_INFO)
    public static final int EVENT_IVS_WATERCOLOR_DETECTION      = 0x00000363;   // 水体颜色事件（对应 DEV_EVENT_WATERCOLOR_DETECTION_INFO）
    public static final int EVENT_IVS_SEWAGE_DETECTION          = 0x00000362;   // 排污检测事件（对应 DEV_EVENT_SEWAGE_DETECTION_INFO)
    public static final int EVENT_IVS_OBJECT_PLACEMENT_DETECTION = 0x00000369;  // 物品放置检测事件(对应DEV_EVENT_OBJECT_PLACEMENT_DETECTION_INFO)
    public static final int EVENT_IVS_DIALRECOGNITION           = 0x00000371;   // 仪表检测事件(对应DEV_EVENT_DIALRECOGNITION_INFO)
    public static final int EVENT_IVS_ELECTRICFAULT_DETECT      = 0x00000372;   // 仪表类缺陷检测事件(对应DEV_EVENT_ELECTRICFAULTDETECT_INFO)
    public static final int EVENT_IVS_TRASH_WITHOUT_COVER_DETECTION = 0x00000373; // 垃圾桶未盖盖子检测事件(对应DEV_EVENT_TRASH_WITHOUT_COVER_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKING_BACKING   = 0x0000037C;   // 出入口倒车驶离事件(对应DEV_EVENT_TRAFFIC_PARKING_BACKING_INFO)
    public static final int EVENT_IVS_BARELAND_DETECTION        = 0x00000380;   // 裸土检测事件(DEV_EVENT_BARELAND_DETECTION_INFO)
    public static final int EVENT_IVS_CONSUMPTION_EVENT         = 0x00000381;   // 消费事件(对应 DEV_EVENT_CONSUMPTION_EVENT_INFO)
    public static final int EVENT_IVS_XRAY_UNPACKING_CHECK      = 0x00000384;   // X光开包检查事件(对应DEV_EVENT_XRAY_UPACKING_CHECK_INFO)
    public static final int EVENT_IVS_TRAFFIC_CHANGE_LANE_CONTINUES = 0x00000387; //机动车连续变道违法事件(对应 DEV_EVENT_TRAFFIC_CHANGE_LANE_CONTINUES_INFO)
    public static final int EVENT_IVS_FISHING_DETECTION         = 0x00000390;   // 钓鱼检测事件(对应 DEV_EVENT_FISHING_DETECTION_INFO )
    public static final int EVENT_IVS_ROAD_CONDITIONS_DETECTION = 0x0000039A;   // 路面检测事件(对应DEV_EVENT_ROAD_CONDITIONS_DETECTION_INFO)
    public static final int EVENT_IVS_VIDEO_NORMAL_DETECTION    = 0x00000365;   // 视频正常事件,在视频诊断检测周期结束时,将未报错的诊断项上报正常事件 DEV_EVENT_VIDEO_NORMAL_DETECTION_INFO
    public static final int EVENT_IVS_OPEN_INTELLI              = 0x0000039D;   // 开放智能事件(对应 DEV_EVENT_OPEN_INTELLI_INFO)
    public static final int EVENT_IVS_TRAFFIC_SERPENTINE_CHANGE_LANE = 0x0000040F; // 蛇形变道事件(对应 DEV_EVENT_TRAFFIC_SERPENTINE_CHANGE_LANE_INFO)
    public static final int EVENT_IVS_TRAFFIC_SPEED_DROP_SHARPLY = 0x00000404;  // 车辆速度剧减事件(对应 DEV_EVENT_TRAFFIC_SPEED_DROP_SHARPLY_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERTAKE_ONRIGHT  = 0x0000040A;   // 右侧超车事件(对应 DEV_EVENT_TRAFFIC_OVERTAKE_ONRIGHT_INFO)
    public static final int EVENT_IVS_TRAFFIC_TRUCK_OCCUPIED    = 0x0000040B;   // 大车占道事件(对应 DEV_EVENT_TRAFFIC_TRUCK_OCCUPIED_INFO)
    public static final int EVENT_IVS_REMOTE_APPROVAL_ALARM     = 0x00000438;   // 金融远程审批事件(对应 NET_DEV_EVENT_REMOTE_APPROVAL_ALARM_INFO)
    public static final int EVENT_IVS_ANTI_COUNTERFEIT          = 0x00000439;   // 防造假检测事件(对应 NET_DEV_EVENT_ANTI_COUNTERFEIT_INFO)
    public static final int EVENT_IVS_USERMANAGER_FOR_TWSDK     = 0x00000441;   // 用户信息上报事件(对应 NET_DEV_EVENT_USERMANAGER_FOR_TWSDK_INFO)
    public static final int EVENT_IVS_POSITION_SNAP             = 0x00000447;   // 按位置抓图事件(对应 NET_DEV_EVENT_POSITION_SNAP_INFO)
    public static final int EVENT_IVS_CIGARETTE_CASE_DETECTION  = 0x00000450;   // 烟盒检测事件(对应 NET_DEV_EVENT_CIGARETTE_CASE_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_ACCELERATION_RAPID = 0x00000457;  // 急加速事件(对应 NET_DEV_EVENT_TRAFFIC_ACCELERATION_RAPID_INFO)
    public static final int EVENT_IVS_TRAFFIC_TURN_SHARP        = 0x00000458;   // 急转弯事件(对应 NET_DEV_EVENT_TRAFFIC_TURN_SHARP_INFO)
    public static final int EVENT_IVS_COLLISION_CONFLICT        = 0x0000045B;   // 碰撞冲突事件(对应 NET_DEV_EVENT_COLLISION_CONFLICT_INFO)
    public static final int EVENT_IVS_SAME_OBJECT_SEARCH_DETECT = 0x00000472;   // 按图索骥物品检测事件(对应 NET_DEV_EVENT_SAME_OBJECT_SEARCH_DETECT_INFO)
    public static final int EVENT_IVS_SAME_OBJECT_SEARCH_COUNT  = 0x00000480;   // 按图索骥物品计数事件(对应 NET_DEV_EVENT_SAME_OBJECT_SEARCH_COUNT_INFO)
    public static final int EVENT_IVS_GRANARY_TRANS_ACTION_DETECTION = 0x00000484; // 粮面异动检测事件上报(对应 NET_DEV_EVENT_GRANARY_TRANS_ACTION_DETECTION_INFO)
    public static final int EVENT_IVS_REGION_PROPORTION_DETECTION = 0x00000485; // 区域占比检测事件(对应 NET_DEV_EVENT_REGION_PROPORTION_DETECTION_INFO)
    public static final int EVENT_IVS_NONMOTORDETECT            = 0x0000023E;   //非机动车检测 (对应结构体 DEV_EVENT_NONMOTORDETECT_INFO)
    public static final int EVENT_IVS_TRAFFIC_TRUST_CAR         = 0x00000499;   //信任车辆事件(对应NET_DEV_EVENT_TRAFFIC_TRUST_CAR_INFO )
    public static final int EVENT_IVS_TRAFFIC_SUSPICIOUS_CAR    = 0x0000049A;   //嫌疑车辆事件(对应NET_DEV_EVENT_TRAFFIC_SUSPICIOUS_CAR_INFO )
    public static final int EVENT_IVS_DOOR_STATE_DETECTION      = 0x00000424;   //开关门检测事件(对应 NET_DEV_EVENT_DOOR_STATE_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_MOTORCYCLE_FORBID = 0x00000364;   //禁摩事件 (对应 DEV_EVENT_TRAFFIC_MOTORCYCLE_FORBID)
    public static final int EVENT_IVS_AUDIO_MUTATION            = 0x0000045E;   //声强突变事件(对应 NET_DEV_EVENT_AUDIO_MUTATION_INFO),(注意该事件暂时不支持单独使用，仅作为“开放智能事件(EVENT_IVS_OPEN_INTELLI)”中的一种规则))
    public static final int EVENT_IVS_STEREO_MANNUM_DETECTION   = 0x00000249;   //立体行为分析人数异常检测 (仅用于规则配置，对应事件 EVENT_IVS_MAN_NUM_DETECTION)
    public static final int EVENT_IVS_WRITE_ON_THE_BOARD_DETECTION = 0x0000029C; //板书检测事件(对应 DEV_EVENT_WRITE_ON_THE_BOARD_DETECTION_INFO)
    public static final int EVENT_IVS_BACK_TO_DETECTION         = 0x0000029B;   //背对检测事件(对应 DEV_EVENT_BACK_TO_DETECTION_INFO)
    public static final int EVENT_IVS_WALK_DETECTION            = 0x0000029A;   //走动检测事件(对应 DEV_EVENT_WALK_DETECTION_INFO)
    public static final int EVENT_IVS_STEREO_STEREOFALLDETECTION = 0x00000239;  //立体行为分析跌倒检测规则(仅用于规则配置，对应事件 EVENT_IVS_TUMBLE_DETECTION)
    public static final int EVENT_IVS_STEREO_FIGHTDETECTION     = 0x00000237;   //立体行为分析打架/剧烈运动检测规则(仅用于规则配置，对应事件 EVENT_IVS_FIGHTDETECTION)
    public static final int EVENT_IVS_PARKINGSPACE_STATUS       = 0x0000027C;   //停车位状态事件(对应 DEV_EVENT_PARKINGSPACE_STATUS_INFO)
    public static final int EVENT_IVS_OCR_DETECTION             = 0x00000399;   //OCR检测事件(对应 DEV_EVENT_OCR_DETECTION_INFO)
    public static final int EVENT_IVS_DIALRECOGNITION_EX        = 0x00000398;   //仪表检测事件(对应DEV_EVENT_DIALRECOGNITION_INFO)
    public static final int EVENT_IVS_GROUND_THING_DETECTION    = 0x000004A4;   //地物识别(对应 NET_DEV_EVENT_GROUND_THING_DETECTION_INFO)
    public static final int EVENT_IVS_CONVEYOR_BELT_STATUS      = 0x00000451;   //传送带运动状态检测报警事件(对应 NET_DEV_EVENT_CONVEYOR_BELT_STATUS_INFO)
    public static final int EVENT_IVS_CONVEYORBLOCK_DETECTION   = 0x0000033E;   //传送带阻塞报警事件 （对应DEV_EVENT_CONVEYORBLOCK_DETECTION_INFO ）
    public static final int EVENT_ALARM_MOTIONDETECT            = 0x0000011C;   //视频移动侦测事件(对应 DEV_EVENT_ALARM_INFO)
    public static final int EVENT_IVS_WATER_SPEED_DETECTION     = 0x0000037B;   //水流速检测事件(对应 DEV_EVENT_WATER_SPEED_DETECTION_INFO)
    public static final int EVENT_IVS_RAILING_PASS_DETECTION    = 0x0000043E;   //隔栏传物事件(对应结构体 NET_DEV_EVENT_RAILING_PASS_DETECTION_INFO)
    public static final int EVENT_IVS_CROSSFENCEDETECTION       = 0x0000011F;   //翻越围栏事件(对应 DEV_EVENT_CROSSFENCEDETECTION_INFO)
    public static final int EVENT_IVS_VEHICLE_STATE             = 0x00000422;   //车辆状态事件(对应 NET_DEV_EVENT_VEHICLE_STATE_INFO)
    public static final int EVENT_IVS_TICKET_EVADE_DETECTION    = 0x00000316;   //逃票检测事件 (对应 DEV_EVENT_TICKET_EVADE_DETECTION_INFO )
    public static final int EVENT_IVS_LEAKAGE_DETECTION         = 0x0000038E;   //渗漏检测事件(对应 DEV_EVENT_LEAKAGE_DETECTION_INFO)
    public static final int EVENT_IVS_STEREO_PRAM_DETECTION     = 0x00000313;   //立体行为婴儿车检测事件 (只用于规则配置)
    public static final int EVENT_IVS_STEREO_BIG_BAGGAGE_DETECTION = 0x00000315; //立体行为大件行李箱检测事件 (只用于规则配置)
    public static final int EVENT_IVS_PRAM_DETECTION            = 0x00000312;   //婴儿车检测事件 (对应 DEV_EVENT_PRAM_DETECTION_INFO )
    public static final int EVENT_IVS_BIG_BAGGAGE_DETECTION     = 0x00000314;   //大件行李箱检测事件 (对应 DEV_EVENT_BIG_BAGGAGE_DETECTION_INFO )
    public static final int EVENT_IVS_PERSONNEL_CATEGORY_COUNT  = 0x000004BE;   //人员类型统计事件(对应结构体 NET_DEV_EVENT_PERSONNEL_CATEGORY_COUNT_INFO )
    public static final int EVENT_IVS_SMART_MOTION_EQUIPMENT    = 0x000004BF;   //智能动检事件(对应结构体 NET_DEV_EVENT_SMART_MOTION_EQUIPMENT_INFO)
    public static final int EVENT_IVS_WASTE_MIXED_INVEST        = 0x00000425;   //垃圾混投事件(对应 NET_DEV_EVENT_WASTE_MIXED_INVEST_INFO)
    public static final int EVENT_IVS_PERSON_CARRY_TRASHBAG     = 0x00000427;   //人员拎袋报警事件(对应 NET_DEV_EVENT_PERSON_CARRY_TRASHBAG_INFO)
    public static final int EVENT_IVS_UNBROKEN_TRASHBAG         = 0x00000426;   //垃圾袋未破袋检测事件(对应 NET_DEV_EVENT_UNBROKEN_TRASHBAG_INFO)
    public static final int EVENT_IVS_DUSTBIN_DETECTION         = 0x00000397;   //垃圾桶检测事件(对应 DEV_EVENT_DUSTBIN_DETECTION_INFO)
    public static final int EVENT_IVS_DHOP_CUSTOM               = 0x00000306;   //Dhop自定义事件(start/stop, 对应 DEV_EVENT_DHOP_CUSTOM_INFO)
    public static final int EVENT_IVS_VEHICLEANALYSE            = 0x00000202;   //车辆特征检测分析(对应DEV_EVENT_VEHICLEANALYSE)
    public static final int EVENT_IVS_HUDDLE_MATERIAL           = 0x00000349;   //乱堆物料检测事件 （对应DEV_EVENT_HUDDLE_MATERIAL_INFO ）
    public static final int EVENT_IVS_NATURAL_DISASTER_DETECTION = 0x000004AE;  //自然灾害检测(对应 NET_DEV_EVENT_NATURAL_DISASTER_DETECTION_INFO)
    public static final int EVENT_IVS_HIGHWAY_DISASTER_DETECTION = 0x000004CC;  //公路灾害检测(对应结构体 NET_DEV_HIGHWAY_DISASTER_DETECTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_DRIVER_NO_BELT    = 0x00000400;   //未系安全带报警事件(对应DEV_EVENT_TRAFFIC_DRIVER_NO_BELT_INFO)
    public static final int EVENT_IVS_GENERAL_ATTITUDE_DETECTION = 0x00000385;  //姿态检测事件(对应 DEV_EVENT_GENERAL_ATTITUDE_DETECTION_INFO)
    public static final int EVENT_IVS_MULTI_MAN_NUM_DETECTION   = 0x0000043F;   //讯问会见室人数报警事件(对应 NET_DEV_EVENT_MULTI_MAN_NUM_DETECTION_INFO)
    public static final int EVENT_IVS_OBJECT_QUANTITY_DETECTION = 0x00000440;   //目标类型和数量检测报警事件(对应 NET_DEV_EVENT_OBJECT_QUANTITY_DETECTION_INFO)
    public static final int EVENT_IVS_DISTRESS_DETECTION        = 0x0000034C;   //求救检测事件 ( 对应 DEV_EVENT_DISTRESS_DETECTION_INFO )
    public static final int EVENT_IVS_GRAIN_HEIGHT_DETECTION    = 0x0000048F;   //动粮检测事件(对应 NET_DEV_EVENT_GRAIN_HEIGHT_DETECTION_INFO)
    public static final int EVENT_IVS_TAILDETECTION             = 0x0000000A;   //尾随事件(对应 DEV_EVENT_TAIL_INFO)
    public static final int EVENT_IVS_COLD_SPOT_WARNING         = 0x00000455;   //冷点报警 (对应 NET_DEV_EVENT_COLD_SPOT_WARNING_INFO)
    public static final int EVENT_IVS_TRAPPED_IN_LIFT_DETECTION = 0x00000462;   //电梯困人检测(对应 NET_DEV_EVENT_TRAPPED_IN_LIFT_DETECTION_INFO)
    public static final int EVENT_IVS_ELEVATOR_WORK_INFO        = 0x00000493;   //上报电梯运行数据事件(对应 NET_DEV_EVENT_ELEVATOR_WORK_INFO_INFO)
    public static final int EVENT_IVS_ELEVATOR_ALARM            = 0x00000494;   //电梯异常报警(对应 NET_DEV_EVENT_ELEVATOR_ALARM_INFO)
    // CLIENT_GetNewDevConfig / CLIENT_SetNewDevConfig 配置项
    public static final String CFG_CMD_MOTIONDETECT             = "MotionDetect"; // 动态检测报警配置(对应 CFG_MOTION_INFO)
    public static final String CFG_CMD_VIDEOWIDGET              = "VideoWidget"; // 视频编码物件配置(对应 NET_CFG_VideoWidget )
    public static final String CFG_CMD_ANALYSEGLOBAL            = "VideoAnalyseGlobal"; // 视频分析全局配置(对应 CFG_ANALYSEGLOBAL_INFO)
    public static final String CFG_CMD_ANALYSEMODULE            = "VideoAnalyseModule"; // 物体的检测模块配置(对应 CFG_ANALYSEMODULES_INFO)
    public static final String CFG_CMD_ANALYSERULE              = "VideoAnalyseRule"; // 视频分析规则配置(对应 CFG_ANALYSERULES_INFO)
    public static final String CFG_CMD_VIDEOINOPTIONS           = "VideoInOptions"; // 视频输入前端选项(对应CFG_VIDEO_IN_OPTIONS)
    public static final String CFG_CMD_RTSP                     = "RTSP";       // RTSP的配置( 对应 CFG_RTSP_INFO_IN和CFG_RTSP_INFO_OUT )
    public static final String CFG_CMD_RAINBRUSHMODE            = "RainBrushMode"; // 雨刷模式相关配置(对应CFG_RAINBRUSHMODE_INFO数组)
    public static final String CFG_CMD_RAINBRUSH                = "RainBrush";  // 雨刷配置(对应CFG_RAINBRUSH_INFO)
    public static final String CFG_CMD_ENCODE                   = "Encode";     // 图像通道属性配置(对应CFG_ENCODE_INFO)
    public static final String CFG_CMD_VIDEOIN_FOCUS            = "VideoInFocus"; // 聚焦设置(对应 CFG_VIDEO_IN_FOCUS)
    public static final String CFG_CMD_VIDEO_IN_ZOOM            = "VideoInZoom"; // 云台通道变倍配置(对应CFG_VIDEO_IN_ZOOM)
    public static final String CFG_CMD_REMOTEDEVICE             = "RemoteDevice"; // 远程设备信息(对应  AV_CFG_RemoteDevice 数组, 通道无关)
    public static final String CFG_CMD_ANALYSESOURCE            = "VideoAnalyseSource"; // 视频分析资源配置(对应 CFG_ANALYSESOURCE_INFO)
    public static final String CFG_CMD_TRAFFICGLOBAL            = "TrafficGlobal"; // 智能交通全局配置(CFG_TRAFFICGLOBAL_INFO)
    public static final String CFG_CMD_RECORDMODE               = "RecordMode"; // 录像模式(对应  AV_CFG_RecordMode )
    public static final String CFG_CMD_ALARMLAMP                = "AlarmLamp";  // 警灯配置(对应 CFG_ALARMLAMP_INFO)
    public static final String CFG_CMD_ALARMOUT                 = "AlarmOut";   // 报警输出通道配置(对应  CFG_ALARMOUT_INFO )
    public static final String CFG_CMD_INTELLECTIVETRAFFIC      = "TrafficSnapshot"; // 智能交通抓拍(对应 CFG_TRAFFICSNAPSHOT_INFO )
    public static final String CFG_CMD_TRAFFICSNAPSHOT_MULTI    = "TrafficSnapshotNew"; // 智能交通抓拍( CFG_TRAFFICSNAPSHOT_NEW_INFO )
    public static final String CFG_CMD_NTP                      = "NTP";        // 时间同步服务器(对应  CFG_NTP_INFO )
    public static final String CFG_CMD_ALARMINPUT               = "Alarm";      // 外部输入报警配置(对应 CFG_ALARMIN_INFO)
    public static final String CFG_CMD_DVRIP                    = "DVRIP";      // 网络协议配置(对应 CFG_DVRIP_INFO)
    public static final String CFG_CMD_NETWORK                  = "Network";    // 网络配置(对应 CFG_NETWORK_INFO)
    public static final String CFG_CMD_NASEX                    = "NAS";        // 网络存储服务器配置, 多服务器(对应 CFG_NAS_INFO_EX)
    public static final String CFG_CMD_MONITORWALL              = "MonitorWall"; // 电视墙配置(对应  AV_CFG_MonitorWall 数组, 通道无关)
    public static final String CFG_CMD_RTMP                     = "RTMP";       // RTMP配置(对应  CFG_RTMP_INFO)
    public static final String CFG_CMD_ACCESS_EVENT             = "AccessControl"; // 门禁事件配置(对应 CFG_ACCESS_EVENT_INFO 数组)
    public static final String CFG_CMD_ACCESSTIMESCHEDULE       = "AccessTimeSchedule"; // 门禁刷卡时间段(对应 CFG_ACCESS_TIMESCHEDULE_INFO)
    public static final String CFG_CMD_DEV_GENERRAL             = "General";    // 普通配置 (对应 CFG_DEV_DISPOSITION_INFO)
    public static final String CFG_CMD_VIDEODIAGNOSIS_PROFILE   = "VideoDiagnosisProfile"; // 视频诊断参数表(CFG_VIDEODIAGNOSIS_PROFILE)
    public static final String CFG_CMD_VIDEODIAGNOSIS_TASK      = "VideoDiagnosisTask"; // 视频诊断任务表(CFG_VIDEODIAGNOSIS_TASK)
    public static final String CFG_CMD_VIDEODIAGNOSIS_TASK_ONE  = "VideoDiagnosisTask.x"; // 视频诊断任务表(CFG_VIDEODIAGNOSIS_TASK)
    public static final String CFG_CMD_VIDEODIAGNOSIS_PROJECT   = "VideoDiagnosisProject"; // 视频诊断计划表(CFG_VIDEODIAGNOSIS_PROJECT)
    public static final String CFG_CMD_GUIDESCREEN              = "GuideScreen"; // 诱导屏系统配置(CFG_GUIDESCREEN_INFO)
    public static final String CFG_CMD_THERMO_GRAPHY            = "ThermographyOptions"; // 热成像摄像头属性配置(CFG_THERMOGRAPHY_INFO)
    public static final String CFG_CMD_THERMOMETRY_RULE         = "ThermometryRule"; // 热成像测温规则配置(对应 CFG_RADIOMETRY_RULE_INFO)
    public static final String CFG_CMD_TEMP_STATISTICS          = "TemperatureStatistics"; // 温度统计配置(CFG_TEMP_STATISTICS_INFO)
    public static final String CFG_CMD_THERMOMETRY              = "HeatImagingThermometry"; // 热成像测温全局配置(CFG_THERMOMETRY_INFO)
    public static final String CFG_CMD_DEVRECORDGROUP           = "DevRecordGroup"; // 通道录像组状态(对应 CFG_DEVRECORDGROUP_INFO)
    public static final String CFG_CMD_STORAGEGROUP             = "StorageGroup"; // 存储组信息(对应 AV_CFG_StorageGroup数组, 通道无关)
    public static final String CFG_CMD_PTZTOUR                  = "PtzTour";    // 云台巡航路径配置(对应 CFG_PTZTOUR_INFO)
    public static final String CFG_CMD_PTZ_PRESET               = "PtzPreset";  // 云台预置点配置(对应结构 PTZ_PRESET_INFO)
    public static final String CFG_CMD_VIDEOIN                  = "VideoIn";    // 输入通道配置(对应 CFG_VIDEO_IN_INFO)
    public static final String CFG_CMD_CHANNELTITLE             = "ChannelTitle"; // 通道名称(对应 AV_CFG_ChannelName)
    public static final String CFG_CMD_WIFI_SEARCH              = "AroudWifiSearch"; // 设备通过Wifi模块扫描周围无线设备配置(CFG_WIFI_SEARCH_INFO)
    public static final String CFG_CMD_RECORD                   = "Record";     // 定时录像配置(对应 CFG_RECORD_INFO)
    public static final String CFG_CMD_SCADA_DEV                = "SCADADev";   // 检测采集设备配置(CFG_SCADA_DEV_INFO)
    public static final String CFG_CMD_ALARM_SHIELD_RULE        = "AlarmShieldRule"; // 告警屏蔽规则( CFG_ALARM_SHIELD_RULE_INFO)
    public static final String CFG_CMD_JUDICATURE               = "Judicature"; // 刻录配置(对应 CFG_JUDICATURE_INFO)
    public static final String CFG_CMD_PTZ                      = "Ptz";        // 云台配置(对应 CFG_PTZ_INFO)
    public static final String CFG_CMD_PTZ_AUTO_MOVEMENT        = "PtzAutoMovement"; //云台定时动作配置(对应 CFG_PTZ_AUTOMOVE_INFO)
    public static final String CFG_CMD_OPEN_DOOR_GROUP          = "OpenDoorGroup"; // 多人多开门方式组合配置(CFG_OPEN_DOOR_GROUP_INFO)
    public static final String CFG_CMD_PARKING_SPACE_LIGHT_GROUP = "ParkingSpaceLightGroup"; // 车位指示灯本机配置(对应 CFG_PARKING_SPACE_LIGHT_GROUP_INFO_ALL)
    public static final String CFG_CMD_LIGHT                    = "Light";      // 灯光设备配置 (对应结构体 CFG_LIGHT_INFO)
    public static final String CFG_CMD_LIGHTING                 = "Lighting";   // 灯光设置(CFG_LIGHTING_INFO)
    public static final String CFG_CMD_COMPOSE_CHANNEL          = "ComposeChannel"; // 合成通道配置(对应 CFG_COMPOSE_CHANNEL)
    public static final String CFG_CMD_PICINPIC                 = "PicInPic";   // 审讯画中画(对应 CFG_PICINPIC_INFO)改为数组方式，兼容以前单个配置，根据长度区分
    public static final String CFG_CMD_IDLEMOTION_INFO          = "IdleMotion"; // 空闲动作配置(CFG_IDLE_MOTION_INFO)
    public static final String CFG_CMD_INFRARED_CONFIG          = "InfraredSet"; // 红外功能配置 (对应 CFG_INFRARED_INFO, 手持设备使用)
    public static final String CFG_CMD_REGULATOR_DETECT         = "RegulatorDetect"; // 标准黑体源异常报警，对应结构体 CFG_REGULATOR_DETECT_INFO. 热成像通道有效
    public static final String CFG_CMD_RECORD_STORAGEPOINT_EX   = "RecordStoragePoint"; // 录像存储点映射配置扩展 (对应 CFG_RECORDTOSTORAGEPOINT_EX_INFO)
    public static final String CFG_CMD_WATERMARK                = "WaterMark";  //视频水印配置(对应 CFG_WATERMARK_INFO)
    public static final String CFG_CMD_AUDIOINPUT               = "AudioInput"; //音频输入配置(CFG_AUDIO_INPUT)
    public static final String CFG_CMD_REMOTE_ANALYSERULE       = "RemoteVideoAnalyseRule"; //远程视频分析规则配置(对应 CFG_ANALYSERULES_INFO)
    public static final String CFG_CMD_VIDEO_WIDGET2            = "VideoWidget2"; //视频编码物件配置(对应结构体 CFG_VIDEO_WIDGET2_INFO)
    public static final String CFG_CMD_UPNP                     = "UPnP";       //UPnP配置(对应CFG_UPNP_INFO)
    // 命令类型, 对应 CLIENT_QueryNewSystemInfo 接口
    public static final String CFG_CAP_CMD_SPEAK                = "speak.getCaps";
    public static final String CFG_CAP_CMD_DEVICE_STATE         = "trafficSnap.getDeviceStatus"; // 获取设备状态信息 (对应 CFG_CAP_TRAFFIC_DEVICE_STATUS)
    public static final String CFG_CAP_CMD_RECORDFINDER         = "RecordFinder.getCaps"; // 获取查询记录能力集, (对应结构体 CFG_CAP_RECORDFINDER_INFO)
    public static final String CFG_CMD_VIDEODIAGNOSIS_GETSTATE  = "videoDiagnosisServer.getState"; // 获取视频诊断进行状态(CFG_VIDEODIAGNOSIS_STATE_INFO)
    public static final String CFG_CAP_CMD_PTZ_ENABLE           = "ptz.factory.instance"; // 获取云台支持信息(CFG_CAP_PTZ_ENABLEINFO)
    // CLIENT_FileTransmit接口传输文件类型
    public static final int NET_DEV_BLACKWHITETRANS_START       = 0x0003;       // 开始发送禁止/允许名单(对应结构体 NETDEV_BLACKWHITE_LIST_INFO)
    public static final int NET_DEV_BLACKWHITETRANS_SEND        = 0x0004;       // 发送禁止/允许名单
    public static final int NET_DEV_BLACKWHITETRANS_STOP        = 0x0005;       // 停止发送禁止/允许名单
    // 配置类型,对应CLIENT_GetDevConfig和CLIENT_SetDevConfig接口
    public static final int NET_DEV_DEVICECFG                   = 0x0001;       // 设备属性配置
    public static final int NET_DEV_VIDEO_OSD_CFG               = 0x0023;       // 视频OSD叠加配置(对应结构体 NET_DVR_VIDEOOSD_CFG)
    public static final int NET_DEV_NETCFG_EX                   = 0x005b;       // 网络扩展配置(对应结构体 NETDEV_NET_CFG_EX )
    public static final int NET_DEV_TIMECFG                     = 0x0008;       // DVR时间配置
    public static final int NET_DEV_AUTOMTCFG                   = 0x000A;       // 自动维护配置(对应结构体NETDEV_AUTOMT_CFG)
    public static final int NET_DEV_ENCLOSURE_CFG               = 0x0066;       // 电子围栏配置(对应结构体 NETDEV_ENCLOSURE_CFG)
    public static final int NET_DEV_ENCLOSURE_VERSION_CFG       = 0x0067;       // 电子围栏版本号配置(对应结构体 NETDEV_ENCLOSURE_VERSION_CFG)
    public static final int NET_DEV_ENCODER_CFG                 = 0x0040;       // 数字通道的前端编码器信息（混合DVR使用,结构体DEV_ENCODER_CFG）
    public static final int NET_DEV_MULTI_DDNS                  = 0x000C;       //多ddns服务器配置
    // 查询类型,对应CLIENT_QueryDevState接口
    public static final int NET_DEVSTATE_COMM_ALARM             = 0x0001;       // 查询普通报警状态(包括外部报警,视频丢失,动态检测)
    public static final int NET_DEVSTATE_SHELTER_ALARM          = 0x0002;       // 查询遮挡报警状态
    public static final int NET_DEVSTATE_RECORDING              = 0x0003;       // 查询录象状态
    public static final int NET_DEVSTATE_DISK                   = 0x0004;       // 查询硬盘信息
    public static final int NET_DEVSTATE_RESOURCE               = 0x0005;       // 查询系统资源状态
    public static final int NET_DEVSTATE_BITRATE                = 0x0006;       // 查询通道码流
    public static final int NET_DEVSTATE_CONN                   = 0x0007;       // 查询设备连接状态
    public static final int NET_DEVSTATE_PROTOCAL_VER           = 0x0008;       // 查询网络协议版本号,pBuf = int*
    public static final int NET_DEVSTATE_TALK_ECTYPE            = 0x0009;       // 查询设备支持的语音对讲格式列表,见结构体NETDEV_TALKFORMAT_LIST
    public static final int NET_DEVSTATE_SD_CARD                = 0x000A;       // 查询SD卡信息(IPC类产品)
    public static final int NET_DEVSTATE_BURNING_DEV            = 0x000B;       // 查询刻录机信息,见结构体NET_BURNING_DEVINFO
    public static final int NET_DEVSTATE_BURNING_PROGRESS       = 0x000C;       // 查询刻录进度
    public static final int NET_DEVSTATE_PLATFORM               = 0x000D;       // 查询设备支持的接入平台
    public static final int NET_DEVSTATE_CAMERA                 = 0x000E;       // 查询摄像头属性信息(IPC类产品),pBuf = NETDEV_CAMERA_INFO *,可以有多个结构体
    public static final int NET_DEVSTATE_SOFTWARE               = 0x000F;       // 查询设备软件版本信息  NETDEV_VERSION_INFO
    public static final int NET_DEVSTATE_LANGUAGE               = 0x0010;       // 查询设备支持的语音种类
    public static final int NET_DEVSTATE_DSP                    = 0x0011;       // 查询DSP能力描述(对应结构体NET_DEV_DSP_ENCODECAP)
    public static final int NET_DEVSTATE_OEM                    = 0x0012;       // 查询OEM信息
    public static final int NET_DEVSTATE_NET                    = 0x0013;       // 查询网络运行状态信息
    public static final int NET_DEVSTATE_TYPE                   = 0x0014;       // 查询设备类型
    public static final int NET_DEVSTATE_SNAP                   = 0x0015;       // 查询功能属性(IPC类产品)
    public static final int NET_DEVSTATE_RECORD_TIME            = 0x0016;       // 查询最早录像时间和最近录像时间
    public static final int NET_DEVSTATE_NET_RSSI               = 0x0017;       // 查询无线网络信号强度,见结构体NETDEV_WIRELESS_RSS_INFO
    public static final int NET_DEVSTATE_BURNING_ATTACH         = 0x0018;       // 查询附件刻录选项
    public static final int NET_DEVSTATE_BACKUP_DEV             = 0x0019;       // 查询备份设备列表
    public static final int NET_DEVSTATE_BACKUP_DEV_INFO        = 0x001a;       // 查询备份设备详细信息 NETDEV_BACKUP_INFO
    public static final int NET_DEVSTATE_BACKUP_FEEDBACK        = 0x001b;       // 备份进度反馈
    public static final int NET_DEVSTATE_ATM_QUERY_TRADE        = 0x001c;       // 查询ATM交易类型
    public static final int NET_DEVSTATE_SIP                    = 0x001d;       // 查询sip状态
    public static final int NET_DEVSTATE_VICHILE_STATE          = 0x001e;       // 查询车载wifi状态
    public static final int NET_DEVSTATE_TEST_EMAIL             = 0x001f;       // 查询邮件配置是否成功
    public static final int NET_DEVSTATE_SMART_HARD_DISK        = 0x0020;       // 查询硬盘smart信息 ,(见结构体 DHDEV_SMART_HARDDISK)
    public static final int NET_DEVSTATE_TEST_SNAPPICTURE       = 0x0021;       // 查询抓图设置是否成功
    public static final int NET_DEVSTATE_STATIC_ALARM           = 0x0022;       // 查询静态报警状态
    public static final int NET_DEVSTATE_SUBMODULE_INFO         = 0x0023;       // 查询设备子模块信息
    public static final int NET_DEVSTATE_DISKDAMAGE             = 0x0024;       // 查询硬盘坏道能力
    public static final int NET_DEVSTATE_IPC                    = 0x0025;       // 查询设备支持的IPC能力, 见结构体NET_DEV_IPC_INFO
    public static final int NET_DEVSTATE_ALARM_ARM_DISARM       = 0x0026;       // 查询报警布撤防状态
    public static final int NET_DEVSTATE_ACC_POWEROFF_ALARM     = 0x0027;       // 查询ACC断电报警状态(返回一个DWORD, 1表示断电,0表示通电)
    public static final int NET_DEVSTATE_TEST_FTP_SERVER        = 0x0028;       // 测试FTP服务器连接
    public static final int NET_DEVSTATE_3GFLOW_EXCEED          = 0x0029;       // 查询3G流量超出阈值状态,(见结构体 NETDEV_3GFLOW_EXCEED_STATE_INFO)
    public static final int NET_DEVSTATE_3GFLOW_INFO            = 0x002a;       // 查询3G网络流量信息,见结构体 NET_DEV_3GFLOW_INFO
    public static final int NET_DEVSTATE_VIHICLE_INFO_UPLOAD    = 0x002b;       // 车载自定义信息上传(见结构体 ALARM_VEHICLE_INFO_UPLOAD)
    public static final int NET_DEVSTATE_SPEED_LIMIT            = 0x002c;       // 查询限速报警状态(见结构体ALARM_SPEED_LIMIT)
    public static final int NET_DEVSTATE_DSP_EX                 = 0x002d;       // 查询DSP扩展能力描述(对应结构体 NET_DEV_DSP_ENCODECAP_EX)
    public static final int NET_DEVSTATE_3GMODULE_INFO          = 0x002e;       // 查询3G模块信息(对应结构体NET_DEV_3GMODULE_INFO)
    public static final int NET_DEVSTATE_MULTI_DDNS             = 0x002f;       // 查询多DDNS状态信息(对应结构体NET_DEV_MULTI_DDNS_INFO)
    public static final int NET_DEVSTATE_CONFIG_URL             = 0x0030;       // 查询设备配置URL信息(对应结构体NET_DEV_URL_INFO)
    public static final int NET_DEVSTATE_HARDKEY                = 0x0031;       // 查询HardKey状态（对应结构体NETDEV_HARDKEY_STATE)
    public static final int NET_DEVSTATE_ISCSI_PATH             = 0x0032;       // 查询ISCSI路径列表(对应结构体NETDEV_ISCSI_PATHLIST)
    public static final int NET_DEVSTATE_DLPREVIEW_SLIPT_CAP    = 0x0033;       // 查询设备本地预览支持的分割模式(对应结构体DEVICE_LOCALPREVIEW_SLIPT_CAP)
    public static final int NET_DEVSTATE_WIFI_ROUTE_CAP         = 0x0034;       // 查询无线路由能力信息(对应结构体NETDEV_WIFI_ROUTE_CAP)
    public static final int NET_DEVSTATE_ONLINE                 = 0x0035;       // 查询设备的在线状态(返回一个DWORD, 1表示在线, 0表示断线)
    public static final int NET_DEVSTATE_PTZ_LOCATION           = 0x0036;       // 查询云台状态信息(对应结构体 NET_PTZ_LOCATION_INFO)
    public static final int NET_DEVSTATE_MONITOR_INFO           = 0x0037;       // 画面监控辅助信息(对应结构体NETDEV_MONITOR_INFO)
    public static final int NET_DEVSTATE_SUBDEVICE              = 0x0300;       // 查询子设备(电源, 风扇等)状态(对应结构体CFG_DEVICESTATUS_INFO)
    public static final int NET_DEVSTATE_RAID_INFO              = 0x0038;       // 查询RAID状态(对应结构体ALARM_RAID_INFO)
    public static final int NET_DEVSTATE_TEST_DDNSDOMAIN        = 0x0039;       // 测试DDNS域名是否可用
    public static final int NET_DEVSTATE_VIRTUALCAMERA          = 0x003a;       // 查询虚拟摄像头状态(对应 NETDEV_VIRTUALCAMERA_STATE_INFO)
    public static final int NET_DEVSTATE_TRAFFICWORKSTATE       = 0x003b;       // 获取设备工作视频/线圈模式状态等(对应NETDEV_TRAFFICWORKSTATE_INFO)
    public static final int NET_DEVSTATE_ALARM_CAMERA_MOVE      = 0x003c;       // 获取摄像机移位报警事件状态(对应ALARM_CAMERA_MOVE_INFO)
    public static final int NET_DEVSTATE_ALARM                  = 0x003e;       // 获取外部报警状态(对应 NET_CLIENT_ALARM_STATE)
    public static final int NET_DEVSTATE_VIDEOLOST              = 0x003f;       // 获取视频丢失报警状态(对应 NET_CLIENT_VIDEOLOST_STATE)
    public static final int NET_DEVSTATE_MOTIONDETECT           = 0x0040;       // 获取动态监测报警状态(对应 NET_CLIENT_MOTIONDETECT_STATE)
    public static final int NET_DEVSTATE_DETAILEDMOTION         = 0x0041;       // 获取详细的动态监测报警状态(对应 NET_CLIENT_DETAILEDMOTION_STATE)
    public static final int NET_DEVSTATE_VEHICLE_INFO           = 0x0042;       // 获取车载自身各种硬件信息(对应 NETDEV_VEHICLE_INFO)
    public static final int NET_DEVSTATE_VIDEOBLIND             = 0x0043;       // 获取视频遮挡报警状态(对应 NET_CLIENT_VIDEOBLIND_STATE)
    public static final int NET_DEVSTATE_3GSTATE_INFO           = 0x0044;       // 查询3G模块相关信息(对应结构体NETDEV_VEHICLE_3GMODULE)
    public static final int NET_DEVSTATE_NETINTERFACE           = 0x0045;       // 查询网络接口信息(对应 NETDEV_NETINTERFACE_INFO)
    public static final int NET_DEVSTATE_PICINPIC_CHN           = 0x0046;       // 查询画中画通道号(对应DWORD数组)
    public static final int NET_DEVSTATE_COMPOSITE_CHN          = 0x0047;       // 查询融合屏通道信息(对应 NET_COMPOSITE_CHANNEL数组)
    public static final int NET_DEVSTATE_WHOLE_RECORDING        = 0x0048;       // 查询设备整体录像状态(对应BOOL), 只要有一个通道在录像,即为设备整体状态为录像
    public static final int NET_DEVSTATE_WHOLE_ENCODING         = 0x0049;       // 查询设备整体编码状态(对应BOOL), 只要有一个通道在编码,即为设备整体状态为编码
    public static final int NET_DEVSTATE_DISK_RECORDE_TIME      = 0x004a;       // 查询设备硬盘录像时间信息(pBuf = DEV_DISK_RECORD_TIME*,可以有多个结构体)
    public static final int NET_DEVSTATE_BURNER_DOOR            = 0x004b;       // 是否已弹出刻录机光驱门(对应结构体 NET_DEVSTATE_BURNERDOOR)
    public static final int NET_DEVSTATE_GET_DATA_CHECK         = 0x004c;       // 查询光盘数据校验进度(对应 NET_DEVSTATE_DATA_CHECK)
    public static final int NET_DEVSTATE_ALARM_IN_CHANNEL       = 0x004f;       // 查询报警输入通道信息(对应NET_ALARM_IN_CHANNEL数组)
    public static final int NET_DEVSTATE_ALARM_CHN_COUNT        = 0x0050;       // 查询报警通道数(对应NET_ALARM_CHANNEL_COUNT)
    public static final int NET_DEVSTATE_PTZ_VIEW_RANGE         = 0x0051;       // 查询云台可视域状态(对应 NET_OUT_PTZ_VIEW_RANGE_STATUS	)
    public static final int NET_DEVSTATE_DEV_CHN_COUNT          = 0x0052;       // 查询设备通道信息(对应NET_DEV_CHN_COUNT_INFO)
    public static final int NET_DEVSTATE_RTSP_URL               = 0x0053;       // 查询设备支持的RTSP URL列表,见结构体DEV_RTSPURL_LIST
    public static final int NET_DEVSTATE_LIMIT_LOGIN_TIME       = 0x0054;       // 查询设备登录的在线超时时间,返回一个BTYE,（单位：分钟） ,0表示不限制,非零正整数表示限制的分钟数
    public static final int NET_DEVSTATE_GET_COMM_COUNT         = 0x0055;       // 获取串口数 见结构体NET_GET_COMM_COUNT
    public static final int NET_DEVSTATE_RECORDING_DETAIL       = 0x0056;       // 查询录象状态详细信息(pBuf = NET_RECORD_STATE_DETAIL*)
    public static final int NET_DEVSTATE_PTZ_PRESET_LIST        = 0x0057;       // 获取当前云台的预置点列表(对应结构NET_PTZ_PRESET_LIST)
    public static final int NET_DEVSTATE_EXTERNAL_DEVICE        = 0x0058;       // 外接设备信息(pBuf = NET_EXTERNAL_DEVICE*)
    public static final int NET_DEVSTATE_GET_UPGRADE_STATE      = 0x0059;       // 获取设备升级状态(对应结构 NETDEV_UPGRADE_STATE_INFO)
    public static final int NET_DEVSTATE_MULTIPLAYBACK_SPLIT_CAP = 0x005a;      // 获取多通道预览分割能力( 对应结构体 NET_MULTIPLAYBACK_SPLIT_CAP )
    public static final int NET_DEVSTATE_BURN_SESSION_NUM       = 0x005b;       // 获取刻录会话总数(pBuf = int*)
    public static final int NET_DEVSTATE_PROTECTIVE_CAPSULE     = 0X005c;       // 查询防护舱状态(对应结构体ALARM_PROTECTIVE_CAPSULE_INFO)
    public static final int NET_DEVSTATE_GET_DOORWORK_MODE      = 0X005d;       // 获取门锁控制模式( 对应 NET_GET_DOORWORK_MODE)
    public static final int NET_DEVSTATE_PTZ_ZOOM_INFO          = 0x005e;       // 查询云台获取光学变倍信息(对应 NET_OUT_PTZ_ZOOM_INFO )
    public static final int NET_DEVSTATE_POWER_STATE            = 0x0152;       // 查询电源状态(对应结构体NET_POWER_STATUS)
    public static final int NET_DEVSTATE_ALL_ALARM_CHANNELS_STATE = 0x153;      // 查询报警通道状态(对应结构体 NET_CLIENT_ALARM_CHANNELS_STATE)
    public static final int NET_DEVSTATE_ALARMKEYBOARD_COUNT    = 0x0154;       // 查询串口上连接的报警键盘数(对应结构体NET_ALARMKEYBOARD_COUNT)
    public static final int NET_DEVSTATE_EXALARMCHANNELS        = 0x0155;       // 查询扩展报警模块通道映射关系(对应结构体 NET_EXALARMCHANNELS)
    public static final int NET_DEVSTATE_GET_BYPASS             = 0x0156;       // 查询通道旁路状态(对应结构体 NET_DEVSTATE_GET_BYPASS)
    public static final int NET_DEVSTATE_ACTIVATEDDEFENCEAREA   = 0x0157;       // 获取激活的防区信息(对应结构体 NET_ACTIVATEDDEFENCEAREA)
    public static final int NET_DEVSTATE_DEV_RECORDSET          = 0x0158;       // 查询设备记录集信息(对应 NET_CTRL_RECORDSET_PARAM)
    public static final int NET_DEVSTATE_DOOR_STATE             = 0x0159;       // 查询门禁状态(对应NET_DOOR_STATUS_INFO)
    public static final int NET_DEVSTATE_ANALOGALARM_CHANNELS   = 0x1560;       // 模拟量报警输入通道映射关系(对应NET_ANALOGALARM_CHANNELS)
    public static final int NET_DEVSTATE_GET_SENSORLIST         = 0x1561;       // 获取设备支持的传感器列表(对应 NET_SENSOR_LIST)
    public static final int NET_DEVSTATE_ALARM_CHANNELS         = 0x1562;       // 查询开关量报警模块通道映射关系(对应结构体 NET_ALARM_CHANNELS)
    public static final int NET_DEVSTATE_GET_ALARM_SUBSYSTEM_ACTIVATESTATUS = 0x1563; // 获取当前子系统启用状态( 对应 NET_GET_ALARM_SUBSYSTEM_ACTIVATE_STATUES)
    public static final int NET_DEVSTATE_AIRCONDITION_STATE     = 0x1564;       // 获取空调工作状态(对应 NET_GET_AIRCONDITION_STATE)
    public static final int NET_DEVSTATE_ALARMSUBSYSTEM_STATE   = 0x1565;       // 获取子系统状态(对应NET_ALARM_SUBSYSTEM_STATE)
    public static final int NET_DEVSTATE_ALARM_FAULT_STATE      = 0x1566;       // 获取故障状态(对应 NET_ALARM_FAULT_STATE_INFO)
    public static final int NET_DEVSTATE_DEFENCE_STATE          = 0x1567;       // 获取防区状态(对应 NET_DEFENCE_STATE_INFO, 和旁路状态变化事件、本地报警事件、报警信号源事件的状态描述有区别,不能混用,仅个别设备使用)
    public static final int NET_DEVSTATE_CLUSTER_STATE          = 0x1568;       // 获取集群状态(对应 NET_CLUSTER_STATE_INFO)
    public static final int NET_DEVSTATE_SCADA_POINT_LIST       = 0x1569;       // 获取点位表路径信息(对应 NET_SCADA_POINT_LIST_INFO)
    public static final int NET_DEVSTATE_SCADA_INFO             = 0x156a;       // 获取监测点位信息(对应 NET_SCADA_INFO)
    public static final int NET_DEVSTATE_SCADA_CAPS             = 0X156b;       // 获取SCADA能力集(对应 NET_SCADA_CAPS)
    public static final int NET_DEVSTATE_GET_CODEID_COUNT       = 0x156c;       // 获取对码成功的总条数(对应 NET_GET_CODEID_COUNT)
    public static final int NET_DEVSTATE_GET_CODEID_LIST        = 0x156d;       // 查询对码信息(对应 NET_GET_CODEID_LIST)
    public static final int NET_DEVSTATE_ANALOGALARM_DATA       = 0x156e;       // 查询模拟量通道数据(对应 NET_GET_ANALOGALARM_DATA)
    public static final int NET_DEVSTATE_VTP_CALLSTATE          = 0x156f;       // 获取视频电话呼叫状态(对应 NET_GET_VTP_CALLSTATE)
    public static final int NET_DEVSTATE_SCADA_INFO_BY_ID       = 0x1570;       // 通过设备、获取监测点位信息(对应 NET_SCADA_INFO_BY_ID)
    public static final int NET_DEVSTATE_SCADA_DEVICE_LIST      = 0x1571;       // 获取当前主机所接入的外部设备ID(对应 NET_SCADA_DEVICE_LIST)
    public static final int NET_DEVSTATE_DEV_RECORDSET_EX       = 0x1572;       // 查询设备记录集信息(带二进制数据)(对应NET_CTRL_RECORDSET_PARAM)
    public static final int NET_DEVSTATE_ACCESS_LOCK_VER        = 0x1573;       // 获取门锁软件版本号(对应 NET_ACCESS_LOCK_VER)
    public static final int NET_DEVSTATE_MONITORWALL_TVINFO     = 0x1574;       // 获取电视墙显示信息(对应 NET_CTRL_MONITORWALL_TVINFO)
    public static final int NET_DEVSTATE_GET_ALL_POS            = 0x1575;       // 获取所有用户可用Pos设备配置信息(对应 NET_POS_ALL_INFO)
    public static final int NET_DEVSTATE_GET_ROAD_LIST          = 0x1576;       // 获取城市及路段编码信息(对应 NET_ROAD_LIST_INFO)
    public static final int NET_DEVSTATE_GET_HEAT_MAP           = 0x1577;       // 获取热度统计信息(对应 NET_QUERY_HEAT_MAP)
    public static final int NET_DEVSTATE_GET_WORK_STATE         = 0x1578;       // 获取盒子工作状态信息(对应 NET_QUERY_WORK_STATE )
    public static final int NET_DEVSTATE_GET_WIRESSLESS_STATE   = 0x1579;       // 获取无线设备状态信息(对应 NET_GET_WIRELESS_DEVICE_STATE)
    public static final int NET_DEVSTATE_GET_REDUNDANCE_POWER_INFO = 0x157a;    // 获取冗余电源信息(对应 NET_GET_REDUNDANCE_POWER_INFO)
    public static final int NET_DEVSTATE_GET_ACCESSORY_INFO     = 0x157e;       // 获取配件信息(对应 NET_GET_ACCESSORY_INFO)
    public static final int NET_DEVSTATE_GET_UPNP_STATUS        = 0x157f;       //获取UPnP映射状态(对应 NET_GET_UPNPSTATUS_INFO)
    // 查询设备信息类型, 对应接口 CLIENT_QueryDevInfo
    public static final int NET_QUERY_DEV_STORAGE_NAMES         = 0x01;         // 查询设备的存储模块名列表 , pInBuf=NET_IN_STORAGE_DEV_NAMES *, pOutBuf=NET_OUT_STORAGE_DEV_NAMES *
    public static final int NET_QUERY_DEV_STORAGE_INFOS         = 0x02;         // 查询设备的存储模块信息列表, pInBuf=NET_IN_STORAGE_DEV_INFOS*, pOutBuf= NET_OUT_STORAGE_DEV_INFOS *
    public static final int NET_QUERY_RECENCY_JNNCTION_CAR_INFO = 0x03;         // 查询最近的卡口车辆信息接口, pInBuf=NET_IN_GET_RECENCY_JUNCTION_CAR_INFO*, pOutBuf=NET_OUT_GET_RECENCY_JUNCTION_CAR_INFO*
    public static final int NET_QUERY_LANES_STATE               = 0x04;         // 查询车道信息,pInBuf = NET_IN_GET_LANES_STATE , pOutBuf = NET_OUT_GET_LANES_STATE
    public static final int NET_QUERY_DEV_FISHEYE_WININFO       = 0x05;         // 查询鱼眼窗口信息 , pInBuf= NET_IN_FISHEYE_WININFO*, pOutBuf=NET_OUT_FISHEYE_WININFO *
    public static final int NET_QUERY_DEV_REMOTE_DEVICE_INFO    = 0x06;         ;               // 查询远程设备信息 , pInBuf= NET_IN_GET_DEVICE_INFO*, pOutBuf= NET_OUT_GET_DEVICE_INFO *
    public static final int NET_QUERY_SYSTEM_INFO               = 0x07;         // 查询设备系统信息 , pInBuf= NET_IN_SYSTEM_INFO*, pOutBuf= NET_OUT_SYSTEM_INFO*
    public static final int NET_QUERY_REG_DEVICE_NET_INFO       = 0x08;         // 查询主动注册设备的网络连接 , pInBuf=NET_IN_REGDEV_NET_INFO * , pOutBuf=NET_OUT_REGDEV_NET_INFO *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_PRESET  = 0x09;         // 查询热成像预设信息 , pInBuf= NET_IN_THERMO_GET_PRESETINFO*, pOutBuf= NET_OUT_THERMO_GET_PRESETINFO *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_OPTREGION = 0x0a;       // 查询热成像感兴趣区域信息,pInBuf= NET_IN_THERMO_GET_OPTREGION*, pOutBuf= NET_OUT_THERMO_GET_OPTREGION *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_EXTSYSINFO = 0x0b;      // 查询热成像外部系统信息, pInBuf= NET_IN_THERMO_GET_EXTSYSINFO*, pOutBuf= NET_OUT_THERMO_GET_EXTSYSINFO *
    public static final int NET_QUERY_DEV_RADIOMETRY_POINT_TEMPER = 0x0c;       // 查询测温点的参数值, pInBuf= NET_IN_RADIOMETRY_GETPOINTTEMPER*, pOutBuf= NET_OUT_RADIOMETRY_GETPOINTTEMPER *
    public static final int NET_QUERY_DEV_RADIOMETRY_TEMPER     = 0x0d;         // 查询测温项的参数值, pInBuf= NET_IN_RADIOMETRY_GETTEMPER*, pOutBuf= NET_OUT_RADIOMETRY_GETTEMPER *
    public static final int NET_QUERY_GET_CAMERA_STATE          = 0x0e;         // 获取摄像机状态, pInBuf= NET_IN_GET_CAMERA_STATEINFO*, pOutBuf= NET_OUT_GET_CAMERA_STATEINFO *
    public static final int NET_QUERY_GET_REMOTE_CHANNEL_AUDIO_ENCODE = 0x0f;   // 获取远程通道音频编码方式, pInBuf= NET_IN_GET_REMOTE_CHANNEL_AUDIO_ENCODEINFO*, pOutBuf= NET_OUT_GET_REMOTE_CHANNEL_AUDIO_ENCODEINFO *
    public static final int NET_QUERY_GET_COMM_PORT_INFO        = 0x10;         // 获取设备串口信息, pInBuf=NET_IN_GET_COMM_PORT_INFO* , pOutBuf=NET_OUT_GET_COMM_PORT_INFO*
    public static final int NET_QUERY_GET_LINKCHANNELS          = 0x11;         // 查询某视频通道的关联通道列表,pInBuf=NET_IN_GET_LINKCHANNELS* , pOutBuf=NET_OUT_GET_LINKCHANNELS*
    public static final int NET_QUERY_GET_VIDEOOUTPUTCHANNELS   = 0x12;         // 获取解码通道数量统计信息, pInBuf=NET_IN_GET_VIDEOOUTPUTCHANNELS*, pOutBuf=NET_OUT_GET_VIDEOOUTPUTCHANNELS*
    public static final int NET_QUERY_GET_VIDEOINFO             = 0x13;         // 获取解码通道信息, pInBuf=NET_IN_GET_VIDEOINFO*, pOutBuf=NET_OUT_GET_VIDEOINFO*
    public static final int NET_QUERY_GET_ALLLINKCHANNELS       = 0x14;         // 查询全部视频关联通道列表,pInBuf=NET_IN_GET_ALLLINKCHANNELS* , pOutBuf=NET_OUT_GET_ALLLINKCHANNELS*
    public static final int NET_QUERY_VIDEOCHANNELSINFO         = 0x15;         // 查询视频通道信息,pInBuf=NET_IN_GET_VIDEOCHANNELSINFO* , pOutBuf=NET_OUT_GET_VIDEOCHANNELSINFO*
    public static final int NET_QUERY_TRAFFICRADAR_VERSION      = 0x16;         // 查询雷达设备版本,pInBuf=NET_IN_TRAFFICRADAR_VERSION* , pOutBuf=NET_OUT_TRAFFICRADAR_VERSION*
    public static final int NET_QUERY_WORKGROUP_NAMES           = 0x17;         // 查询所有的工作目录组名,pInBuf=NET_IN_WORKGROUP_NAMES* , pOutBuf=NET_OUT_WORKGROUP_NAMES*
    public static final int NET_QUERY_WORKGROUP_INFO            = 0x18;         // 查询工作组信息,pInBuf=NET_IN_WORKGROUP_INFO* , pOutBuf=NET_OUT_WORKGROUP_INFO*
    public static final int NET_QUERY_WLAN_ACCESSPOINT          = 0x19;         // 查询无线网络接入点信息,pInBuf=NET_IN_WLAN_ACCESSPOINT* , pOutBuf=NET_OUT_WLAN_ACCESSPOINT*
    public static final int NET_QUERY_GPS_INFO                  = 0x1a;         // 查询设备GPS信息,pInBuf=NET_IN_DEV_GPS_INFO* , pOutBuf=NET_OUT_DEV_GPS_INFO*
    public static final int NET_QUERY_IVS_REMOTE_DEVICE_INFO    = 0x1b;         // 查询IVS的前端设备所关联的远程设备信息, pInBuf = NET_IN_IVS_REMOTE_DEV_INFO*, pOutBuf = NET_OUT_IVS_REMOTE_DEV_INFO*
    public static final int NET_QUERY_VIDEO_ENCODE_CAPS         = 0x1e;         // 获取视频编码能力集, pInBuf = NET_IN_VIDEO_ENCODE_CAPS*, pOutBuf = NET_OUT_VIDEO_ENCODE_CAPS*
    public static final int NET_QUERY_HARDDISK_TEMPERATURE      = 0x22;         // 获取硬盘温度,pInBuf = NET_IN_HDD_TEMPERATURE*, pOutBuf = NET_OUT_HDD_TEMPERATURE*
    public static final int NET_QUERY_AUDIO_DECODE_CAPS         = 0x25;         // 获取音频解码能力集, pInBuf = NET_IN_AUDIO_DECODE_CAPS*, pOutBuf = NET_OUT_AUDIO_DECODE_CAPS*
    public static final int NET_QUERY_REMOTE_DEVICE_CAPS        = 0x27;         // 获取远程设备管理能力集, pInBuf = NET_IN_REMOTEDEVICE_CAP*， pOutBuf= NET_OUT_REMOTEDEVICE_CAP
    public static final int NET_QUERY_TRAFFIC_SNAP_RADAR        = 0x28;         // 获取智能交通雷达信息, pInfo = NET_IN_TRAFFIC_SNAP_RADAR_INFO*,  pOutBuf = NET_OUT_TRAFFIC_SNAP_RADAR_INFO*
    public static final int NET_QUERY_TRAFFIC_SNAP_STROBE       = 0x29;         // 获取智能交通道闸信息, pInfo = NET_IN_TRAFFIC_SNAP_STROBE_INFO*, pOutBuf = NET_OUT_TRAFFIC_SNAP_STROBE_INFO*
    public static final int NET_QUERY_PTZ_CURRENT_FOV_VALUE     = 0x36;         // 获取镜头当前倍率下水平视场角参数,pInBuf = NET_IN_PTZ_CURRENT_FOV_VALUE*,pOutBuf = NET_OUT_PTZ_CURRENT_FOV_VALUE*
    public static final int NET_QUERY_DEV_IO_STATS              = 0x37;         // 获取所有存储设备的io信息参数,pInBuf = NET_IN_DEV_IO_STATS*,pOutBuf = NET_OUT_DEV_IO_STAT*
    public static final int NET_QUERY_PTZBASE_GET_HFOV_VALUE    = 0x39;         // 获取镜头不同倍率下水平视场角参数，pInBuf = NET_IN_PTZBASE_GET_HFOV_VALUE*,pOutBuf = NET_OUT_PTZBASE_GET_HFOV_VALUE*
    public static final int NET_QUERY_PTZBASE_GET_CENTER_GPS    = 0x3a;         // 获取中心位置GPS信息，pInBuf = NET_IN_PTZBASE_GET_CENTER_GPS*,pOutBuf = NET_OUT_PTZBASE_GET_CENTER_GPS*
    public static final int NET_QUERY_PTZBASE_GET_VFOV_VALUE    = 0x3f;         // 获取镜头不同倍率下垂直视场角参数，pInBuf = NET_IN_PTZBASE_GET_VFOV_VALUE*,pOutBuf = NET_OUT_PTZBASE_GET_VFOV_VALUE*
    public static final int NET_QUERY_TRAFFIC_RADAR_GET_OBJECT  = 0x35;         // 获取雷达物体目标信息,pInBuf = NET_IN_TRAFFIC_RADAR_GET_OBJECT_INFO*,pOutBuf = NET_OUT_TRAFFIC_RADAR_GET_OBJECT_INFO*
    public static final int NET_QUERY_DEV_STORAGE_INFOS_SP      = 0x40;         //查询设备的存储模块信息列表，结构体精简版本, pInBuf=NET_IN_STORAGE_DEV_INFOS*, pOutBuf= NET_OUT_STORAGE_DEV_INFOS_SP *
    public static final int NET_QUERY_GET_ALL_PARKING_SPACE_STATUS = 0x32;      //获取当前车位检测状态, pInBuf = NET_IN_GET_ALL_PARKING_SPACE_STATUS_INFO*, pOutBuf = NET_OUT_GET_ALL_PARKING_SPACE_STATUS_INFO*
    // 设备能力类型, 对应CLIENT_GetDevCaps接口
    public static final int NET_ACCESSCONTROL_CAPS              = 0x20;         // 获取门禁能力, pInBuf = NET_IN_AC_CAPS*, pOutBuf = NET_OUT_AC_CAPS*
    public static final int NET_THERMO_GRAPHY_CAPS              = 0x06;         // 热成像摄像头属性能力,pInBuf=NET_IN_THERMO_GETCAPS*, pOutBuf=NET_OUT_THERMO_GETCAPS*
    public static final int NET_RADIOMETRY_CAPS                 = 0x07;         // 热成像测温全局配置能力,pInBuf=NET_IN_RADIOMETRY_GETCAPS*, pOutBuf=NET_OUT_RADIOMETRY_GETCAPS*
    public static final int NET_MEDIAMANAGER_CAPS               = 0x0a;         //获取 VideoInput 的各个能力项,pInBuf=NET_IN_MEDIAMANAGER_GETCAPS*, pOutBuf=NET_OUT_MEDIAMANAGER_GETCAPS*
    public static final int NET_REMOTE_SPEAK_CAPS               = 0x38;         //获取前端音频文件路径和能力集 pInBuf = NET_IN_REMOTE_SPEAK_CAPS*, pOutBuf = NET_OUT_REMOTE_SPEAK_CAPS*
    // 视频诊断上报结果检测类型定义
    public static final String NET_DIAGNOSIS_DITHER             = "VideoDitherDetection"; // 视频抖动检测 对应结构体(NET_VIDEO_DITHER_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_STRIATION          = "VideoStriationDetection"; // 视频条纹检测 对应结构体(NET_VIDEO_STRIATION_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_LOSS               = "VideoLossDetection"; // 视频丢失检测 对应结构体(NET_VIDEO_LOSS_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_COVER              = "VideoCoverDetection"; // 视频遮挡检测 对应结构体(NET_VIDEO_COVER_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_FROZEN             = "VideoFrozenDetection"; // 视频冻结检测 对应结构体(NET_VIDEO_FROZEN_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_BRIGHTNESS         = "VideoBrightnessDetection"; // 视频亮度异常检测 对应结构体(NET_VIDEO_BRIGHTNESS_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_CONTRAST           = "VideoContrastDetection"; // 视频对比度异常检测 对应结构体(NET_VIDEO_CONTRAST_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_UNBALANCE          = "VideoUnbalanceDetection"; // 视频偏色检测 对应结构体(NET_VIDEO_UNBALANCE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_NOISE              = "VideoNoiseDetection"; // 视频噪声检测 对应结构体(NET_VIDEO_NOISE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_BLUR               = "VideoBlurDetection"; // 视频模糊检测 对应结构体(NET_VIDEO_BLUR_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_SCENECHANGE        = "VideoSceneChangeDetection"; // 视频场景变化检测 对应结构体(NET_VIDEO_SCENECHANGE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_VIDEO_DELAY        = "VideoDelay"; // 视频延时检测 对应结构体(NET_VIDEO_DELAY_DETECTIONRESUL)
    public static final String NET_DIAGNOSIS_PTZ_MOVING         = "PTZMoving";  // 云台移动检测 对应结构体(NET_PTZ_MOVING_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_BLACK_WHITE        = "VideoBlackAndWhite"; // 黑白图像检测, 对应结构体(NET_BLACK_WHITE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_DRAMATIC_CHANGE    = "VideoDramaticChange"; // 场景剧变检测, 对应结构体(NET_DIAGNOSIS_DRAMATIC_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_VIDEO_AVAILABILITY = "VideoAvailability"; // 视频完好率监测, 对应结构体(NET_VIDEO_AVAILABILITY_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_SNOWFLAKE          = "SnowflakeDetection"; // 雪花屏检测, 对应结构体(NET_VIDEO_SNOWFLAKE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_VIDEO_ALGORITHMTYPE = "VideoAlgorithmType"; // 视频算法类型检测,对应结构体(NET_VIDEO_ALGORITHMTYPE_DETECTIONRESULT)
    public static final String NET_DIAGNOSIS_VIDEO_FILCKERING_DETECTION = "VideoFilckeringDetection"; // 视频闪频检测, 对应结构体(NET_VIDEO_FILCKERING_DETECTION_RESULT)
    public static final String NET_DIAGNOSIS_VIDEO_LOSS_FRAME_DETECTION = "VideoLossFrameDetection"; // 视频丢帧检测, 对应结构体(NET_VIDEO_LOSS_FRAME_DETECTION_RESULT)
    // 矩阵子卡类型, 多种类型可以组合
    public static final int NET_MATRIX_CARD_MAIN                = 0x10000000;   // 主卡
    public static final int NET_MATRIX_CARD_INPUT               = 0x00000001;   // 输入卡
    public static final int NET_MATRIX_CARD_OUTPUT              = 0x00000002;   // 输出卡
    public static final int NET_MATRIX_CARD_ENCODE              = 0x00000004;   // 编码卡
    public static final int NET_MATRIX_CARD_DECODE              = 0x00000008;   // 解码卡
    public static final int NET_MATRIX_CARD_CASCADE             = 0x00000010;   // 级联卡
    public static final int NET_MATRIX_CARD_INTELLIGENT         = 0x00000020;   // 智能卡
    public static final int NET_MATRIX_CARD_ALARM               = 0x00000040;   // 报警卡
    public static final int NET_MATRIX_CARD_RAID                = 0x00000080;   // 硬Raid卡
    public static final int NET_MATRIX_CARD_NET_DECODE          = 0x00000100;   // 网络解码卡
    // 保留数据类型
    public static final int RESERVED_TYPE_FOR_INTEL_BOX         = 0x00000001;
    public static final int RESERVED_TYPE_FOR_COMMON            = 0x00000010;
    public static final int RESERVED_TYPE_FOR_PATH              = 0x00000100;
    /************************************************************************
     ** 结构体
     ***********************************************************************/
    // 设置登入时的相关参数
    public static class NET_PARAM extends SdkStructure
    {
        public int              nWaittime;                            // 等待超时时间(毫秒为单位)，为0默认5000ms
        public int              nConnectTime;                         // 连接超时时间(毫秒为单位)，为0默认1500ms
        public int              nConnectTryNum;                       // 连接尝试次数，为0默认1次
        public int              nSubConnectSpaceTime;                 // 子连接之间的等待时间(毫秒为单位)，为0默认10ms
        public int              nGetDevInfoTime;                      // 获取设备信息超时时间，为0默认1000ms
        public int              nConnectBufSize;                      // 每个连接接收数据缓冲大小(字节为单位)，为0默认250*1024
        public int              nGetConnInfoTime;                     // 获取子连接信息超时时间(毫秒为单位)，为0默认1000ms
        public int              nSearchRecordTime;                    // 按时间查询录像文件的超时时间(毫秒为单位),为0默认为3000ms
        public int              nsubDisconnetTime;                    // 检测子链接断线等待时间(毫秒为单位)，为0默认为60000ms
        public byte             byNetType;                            // 网络类型, 0-LAN, 1-WAN
        public byte             byPlaybackBufSize;                    // 回放数据接收缓冲大小（M为单位），为0默认为4M
        public byte             bDetectDisconnTime;                   // 心跳检测断线时间(单位为秒),为0默认为60s,最小时间为2s
        public byte             bKeepLifeInterval;                    // 心跳包发送间隔(单位为秒),为0默认为10s,最小间隔为2s
        public int              nPicBufSize;                          // 实时图片接收缓冲大小（字节为单位），为0默认为2*1024*1024
        public short            wBSIDLowPowerSubDisconnTime;          //BSID低功耗子链接心跳检测断线时间(单位为秒), 为0默认为60s, 最小时间为2s
        public byte[]           bReserved = new byte[2];              // 保留字段字段
    }

    // 设备设备参数
    public static class NET_DEVICE_SEARCH_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        /**
         * 是否使用默认配置,默认为TRUE
         */
        public int              bUseDefault;
        /**
         * 广播本地端口, 默认5050, 值为0时使用最近一次配置
         */
        public short            wBroadcastLocalPort;
        /**
         * 广播远程端口, 默认5050, 值为0时使用最近一次配置
         */
        public short            wBroadcastRemotePort;
        /**
         * 组播远程端口, 默认37810, 值为0时使用最近一次配置
         */
        public short            wMulticastRemotePort;
        /**
         * 组播修改设备时是否只支持组播回复,默认FALSE表示单播或组播回复
         */
        public int              bMulticastModifyRespond;
        /**
         * 组播本地端口, 默认37810, 值为0时使用最近一次配置
         */
        public short            wMulticastLocalPort;
        /**
         * 端口不可用时自动更新端口次数,默认50次，范围[0-65534]
         */
        public int              iAutoUpdatePortTimes;
        /**
         * AOL 组播远程端口, 默认8087, 值为0时使用最近一次配置
         */
        public short            wAOLMulticastRemotePort;
        /**
         * AOL 组播本地端口, 默认37811, 值为0时使用最近一次配置
         */
        public short            wAOLMulticastLocalPort;

        public NET_DEVICE_SEARCH_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 设备信息
    public static class NET_DEVICEINFO extends SdkStructure {
        public byte[]           sSerialNumber = new byte[NET_SERIALNO_LEN]; // 序列号
        public byte             byAlarmInPortNum;                     // DVR报警输入个数
        public byte             byAlarmOutPortNum;                    // DVR报警输出个数
        public byte             byDiskNum;                            // DVR硬盘个数
        public byte             byDVRType;                            // DVR类型, 见枚举NET_DEV_DEVICE_TYPE
        public union            union = new union();
        public static class union extends Union {
            public byte                byChanNum;                // DVR通道个数
            public byte                byLeftLogTimes;           // 当登陆失败原因为密码错误时,通过此参数通知用户,剩余登陆次数,为0时表示此参数无效
        }
    }

    // 设备信息扩展///////////////////////////////////////////////////
    public static class NET_DEVICEINFO_Ex extends SdkStructure {
        public byte[]           sSerialNumber = new byte[NET_SERIALNO_LEN]; // 序列号
        public int              byAlarmInPortNum;                     // DVR报警输入个数
        public int              byAlarmOutPortNum;                    // DVR报警输出个数
        public int              byDiskNum;                            // DVR硬盘个数
        public int              byDVRType;                            // DVR类型,见枚举NET_DEVICE_TYPE
        public int              byChanNum;                            // DVR通道个数
        public byte             byLimitLoginTime;                     // 在线超时时间,为0表示不限制登陆,非0表示限制的分钟数
        public byte             byLeftLogTimes;                       // 当登陆失败原因为密码错误时,通过此参数通知用户,剩余登陆次数,为0时表示此参数无效
        public byte[]           bReserved = new byte[2];              // 保留字节,字节对齐
        public int              byLockLeftTime;                       // 当登陆失败,用户解锁剩余时间（秒数）, -1表示设备未设置该参数
        public byte[]           Reserved = new byte[4];               // 保留
        public int              nNTlsPort;                            //国密TLS登录端口,仅登录错误码为24时有效
        public byte[]           Reserved2 = new byte[16];             //保留
    }

    // 对应接口 CLIENT_LoginEx2/////////////////////////////////////////////////////////
    public static class EM_LOGIN_SPAC_CAP_TYPE extends SdkStructure {
        public static final int   EM_LOGIN_SPEC_CAP_TCP = 0;            // TCP登陆, 默认方式
        public static final int   EM_LOGIN_SPEC_CAP_ANY = 1;            // 无条件登陆
        public static final int   EM_LOGIN_SPEC_CAP_SERVER_CONN = 2;    // 主动注册的登入
        public static final int   EM_LOGIN_SPEC_CAP_MULTICAST = 3;      // 组播登陆
        public static final int   EM_LOGIN_SPEC_CAP_UDP = 4;            // UDP方式下的登入
        public static final int   EM_LOGIN_SPEC_CAP_MAIN_CONN_ONLY = 6; // 只建主连接下的登入
        public static final int   EM_LOGIN_SPEC_CAP_SSL = 7;            // SSL加密方式登陆
        public static final int   EM_LOGIN_SPEC_CAP_INTELLIGENT_BOX = 9; // 登录智能盒远程设备
        public static final int   EM_LOGIN_SPEC_CAP_NO_CONFIG = 10;     // 登录设备后不做取配置操作
        public static final int   EM_LOGIN_SPEC_CAP_U_LOGIN = 11;       // 用U盾设备的登入
        public static final int   EM_LOGIN_SPEC_CAP_LDAP = 12;          // LDAP方式登录
        public static final int   EM_LOGIN_SPEC_CAP_AD = 13;            // AD（ActiveDirectory）登录方式
        public static final int   EM_LOGIN_SPEC_CAP_RADIUS = 14;        // Radius 登录方式
        public static final int   EM_LOGIN_SPEC_CAP_SOCKET_5 = 15;      // Socks5登陆方式
        public static final int   EM_LOGIN_SPEC_CAP_CLOUD = 16;         // 云登陆方式
        public static final int   EM_LOGIN_SPEC_CAP_AUTH_TWICE = 17;    // 二次鉴权登陆方式
        public static final int   EM_LOGIN_SPEC_CAP_TS = 18;            // TS码流客户端登陆方式
        public static final int   EM_LOGIN_SPEC_CAP_P2P = 19;           // 为P2P登陆方式
        public static final int   EM_LOGIN_SPEC_CAP_MOBILE = 20;        // 手机客户端登陆
    }

    // 时间
    public static class NET_TIME extends SdkStructure {
        public int              dwYear;                               // 年
        public int              dwMonth;                              // 月
        public int              dwDay;                                // 日
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒

        public NET_TIME() {
            this.dwYear = 0;
            this.dwMonth = 0;
            this.dwDay = 0;
            this.dwHour = 0;
            this.dwMinute = 0;
            this.dwSecond = 0;
        }

        public void setTime(int year, int month, int day, int hour, int minute, int second) {
            this.dwYear = year;
            this.dwMonth = month;
            this.dwDay = day;
            this.dwHour = hour;
            this.dwMinute = minute;
            this.dwSecond = second;
        }

        public NET_TIME(NET_TIME other) {
            this.dwYear = other.dwYear;
            this.dwMonth = other.dwMonth;
            this.dwDay = other.dwDay;
            this.dwHour = other.dwHour;
            this.dwMinute = other.dwMinute;
            this.dwSecond = other.dwSecond;
        }

        public String toStringTime() {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        public String toStringTimeEx() {
            return  String.format("%02d-%02d-%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        public String toString() {
            return String.format("%02d%02d%02d%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    public static class NET_TIME_EX extends SdkStructure
    {
        public int              dwYear;                               // 年
        public int              dwMonth;                              // 月
        public int              dwDay;                                // 日
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒
        public int              dwMillisecond;                        // 毫秒
        public int              dwUTC;                                // utc时间(获取时0表示无效，非0有效   下发无效)
        public int[]            dwReserved = new int[1];              // 保留字段

        public void setTime(int year, int month, int day, int hour, int minute, int second) {
            this.dwYear = year;
            this.dwMonth = month;
            this.dwDay = day;
            this.dwHour = hour;
            this.dwMinute = minute;
            this.dwSecond = second;
            this.dwMillisecond = 0;
        }

        public String toString() {
            return dwYear + "/" + dwMonth + "/" + dwDay + " " + dwHour + ":" + dwMinute + ":" + dwSecond;
        }

        public String toStringTime()
        {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        public String toStringTitle()
        {
            return  String.format("Time_%02d%02d%02d_%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    // 区域
    public static class NET_CFG_Rect extends SdkStructure
    {
        public int              nStructSize;
        public int              nLeft;
        public int              nTop;
        public int              nRight;
        public int              nBottom;

        public NET_CFG_Rect()
        {
            this.nStructSize = this.size();
        }
    }

    // 颜色
    public static class NET_CFG_Color extends SdkStructure
    {
        public int              nStructSize;
        public int              nRed;                                 // 红
        public int              nGreen;                               // 绿
        public int              nBlue;                                // 蓝
        public int              nAlpha;                               // 透明

        public NET_CFG_Color()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-通道标题
    public static class NET_CFG_VideoWidgetChannelTitle extends SdkStructure
    {
        public int              nStructSize;
        public int              bEncodeBlend;                         // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra1;                   // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra2;                   // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra3;                   // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendSnapshot;                 // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color    stuFrontColor = new NET_CFG_Color();  // 前景色
        public NET_CFG_Color    stuBackColor = new NET_CFG_Color();   // 背景色
        public NET_CFG_Rect     stuRect = new NET_CFG_Rect();         // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int              bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL， 取值0或者1

        public NET_CFG_VideoWidgetChannelTitle()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-时间标题
    public static class NET_CFG_VideoWidgetTimeTitle extends SdkStructure
    {
        public int              nStructSize;
        public int              bEncodeBlend;                         // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra1;                   // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra2;                   // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra3;                   // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendSnapshot;                 // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color    stuFrontColor = new NET_CFG_Color();  // 前景色
        public NET_CFG_Color    stuBackColor = new NET_CFG_Color();   // 背景色
        public NET_CFG_Rect     stuRect = new NET_CFG_Rect();         // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int              bShowWeek;                            // 是否显示星期, 类型为BOOL, 取值0或者1
        public int              bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL, 取值0或者1

        public NET_CFG_VideoWidgetTimeTitle()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-区域覆盖配置
    public static class NET_CFG_VideoWidgetCover extends SdkStructure
    {
        public int              nStructSize;
        public int              bEncodeBlend;                         // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra1;                   // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra2;                   // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra3;                   // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendSnapshot;                 // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color    stuFrontColor = new NET_CFG_Color();  // 前景色
        public NET_CFG_Color    stuBackColor = new NET_CFG_Color();   // 背景色
        public NET_CFG_Rect     stuRect = new NET_CFG_Rect();         // 区域, 坐标取值0~8191
        public int              bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL, 取值0或者1

        public NET_CFG_VideoWidgetCover()
        {
            this.nStructSize = this.size();
        }
    }

    public static class EM_TITLE_TEXT_ALIGN
    {
        public static final int   EM_TEXT_ALIGN_INVALID = 0;            // 无效的对齐方式
        public static final int   EM_TEXT_ALIGN_LEFT = 1;               // 左对齐
        public static final int   EM_TEXT_ALIGN_XCENTER = 2;            // X坐标中对齐
        public static final int   EM_TEXT_ALIGN_YCENTER = 3;            // Y坐标中对齐
        public static final int   EM_TEXT_ALIGN_CENTER = 4;             // 居中
        public static final int   EM_TEXT_ALIGN_RIGHT = 5;              // 右对齐
        public static final int   EM_TEXT_ALIGN_TOP = 6;                // 按照顶部对齐
        public static final int   EM_TEXT_ALIGN_BOTTOM = 7;             // 按照底部对齐
        public static final int   EM_TEXT_ALIGN_LEFTTOP = 8;            // 按照左上角对齐
        public static final int   EM_TEXT_ALIGN_CHANGELINE = 9;         // 换行对齐
    }

    // 编码物件-自定义标题
    public static class NET_CFG_VideoWidgetCustomTitle extends SdkStructure
    {
        public int              nStructSize;
        public int              bEncodeBlend;                         // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra1;                   // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra2;                   // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendExtra3;                   // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int              bEncodeBlendSnapshot;                 // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color    stuFrontColor = new NET_CFG_Color();  // 前景色
        public NET_CFG_Color    stuBackColor = new NET_CFG_Color();   // 背景色
        public NET_CFG_Rect     stuRect = new NET_CFG_Rect();         // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public byte[]           szText = new byte[NET_CFG_Custom_Title_Len]; // 标题内容
        public int              bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL, 取值0或者1
        public byte[]           szType = new byte[NET_CFG_Custom_TitleType_Len]; // 标题类型 "Rtinfo" 实时刻录信息 "Custom" 自定义叠加、温湿度叠加 "Title" :片头信息 "Check"  校验码
        // 地理信息 "Geography"  ATM卡号信息 "ATMCardInfo" 摄像机编号 "CameraID"
        public int              emTextAlign;                          // 标题对齐方式 (参见EM_TITLE_TEXT_ALIGN)
        public int              bUpdate;                              // 是否需要设备端更新叠加内容 true:更新  false:不更新, 类型为BOOL, 取值0或者1

        public NET_CFG_VideoWidgetCustomTitle()
        {
            this.nStructSize = this.size();
        }
    }

    //  编码物件-叠加传感器信息-叠加内容描述
    public static class NET_CFG_VideoWidgetSensorInfo_Description extends SdkStructure
    {
        public int              nStructSize;
        public int              nSensorID;                            // 需要描述的传感器的ID(即模拟量报警通道号)
        public byte[]           szDevID = new byte[CFG_COMMON_STRING_32]; // 设备ID
        public byte[]           szPointID = new byte[CFG_COMMON_STRING_32]; // 测点ID
        public byte[]           szText = new byte[CFG_COMMON_STRING_256]; // 需要叠加的内容

        public NET_CFG_VideoWidgetSensorInfo_Description()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-叠加传感器信息
    public static class NET_CFG_VideoWidgetSensorInfo extends SdkStructure
    {
        public int              nStructSize;
        public int              bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL, 取值0或者1
        public int              bEncodeBlend;                         // 叠加到主码流视频编码, 类型为BOOL, 取值0或者1
        public NET_CFG_Rect     stuRect = new NET_CFG_Rect();         // 区域, 坐标取值0~8191
        public int              nDescriptionNum;                      // 叠加区域描述数目
        public NET_CFG_VideoWidgetSensorInfo_Description[] stuDescription = (NET_CFG_VideoWidgetSensorInfo_Description[])new NET_CFG_VideoWidgetSensorInfo_Description().toArray(NET_CFG_Max_Description_Num); // 叠加区域描述信息

        public NET_CFG_VideoWidgetSensorInfo()
        {
            this.nStructSize = this.size();
        }
    }

    // 视频编码物件配置
    public static class NET_CFG_VideoWidget extends SdkStructure
    {
        public int              nStructSize;
        public NET_CFG_VideoWidgetChannelTitle stuChannelTitle = new NET_CFG_VideoWidgetChannelTitle(); // 通道标题
        public NET_CFG_VideoWidgetTimeTitle stuTimeTitle = new NET_CFG_VideoWidgetTimeTitle(); // 时间标题
        public int              nConverNum;                           // 区域覆盖数量
        public NET_CFG_VideoWidgetCover[] stuCovers = new NET_CFG_VideoWidgetCover[NET_CFG_Max_Video_Widget_Cover]; // 覆盖区域
        public int              nCustomTitleNum;                      // 自定义标题数量
        public NET_CFG_VideoWidgetCustomTitle[] stuCustomTitle = new NET_CFG_VideoWidgetCustomTitle[NET_CFG_Max_Video_Widget_Custom_Title]; // 自定义标题
        public int              nSensorInfo;                          // 传感器信息叠加区域数目
        public NET_CFG_VideoWidgetSensorInfo[] stuSensorInfo = new NET_CFG_VideoWidgetSensorInfo[NET_CFG_Max_Video_Widget_Sensor_Info]; // 传感器信息叠加区域信息
        public double           fFontSizeScale;                       //叠加字体大小放大比例
        //当fFontSizeScale≠0时,nFontSize不起作用
        //当fFontSizeScale=0时,nFontSize起作用
        //设备默认fFontSizeScale=1.0
        //如果需要修改倍数，修改该值
        //如果需要按照像素设置，则置该值为0，nFontSize的值生效
        public int              nFontSize;                            //叠加到主码流上的全局字体大小,单位 px.
        //和fFontSizeScale共同作用
        public int              nFontSizeExtra1;                      //叠加到辅码流1上的全局字体大小,单位 px
        public int              nFontSizeExtra2;                      //叠加到辅码流2上的全局字体大小,单位 px
        public int              nFontSizeExtra3;                      //叠加到辅码流3上的全局字体大小,单位 px
        public int              nFontSizeSnapshot;                    //叠加到抓图流上的全局字体大小, 单位 px
        public int              nFontSizeMergeSnapshot;               //叠加到抓图流上合成图片的字体大小,单位 px
        public int              emFontSolutionSnapshot;               //叠加到抓图流上的字体方案   // 0 未知 ; 1- 默认字体 "default-font" 2-楷体 "simkai" 3- 宋体"simsun"
        public NET_CFG_VideoWidgetCover stuGPSTitle;                  //GPS标题显示, 车载设备用
        public NET_CFG_VideoWidgetCover stuCarNoTitle;                //车牌标题显示, 车载设备用
        public byte[]           szChannelName = new byte[256];        // 通道名称(只为Onvif使用)

        public NET_CFG_VideoWidget()
        {
            this.nStructSize = this.size();
            for (int i = 0; i < stuCustomTitle.length; i++) {
                stuCustomTitle[i] = new NET_CFG_VideoWidgetCustomTitle();
            }

            for (int i = 0; i < stuCovers.length; i++) {
                stuCovers[i] = new NET_CFG_VideoWidgetCover();
            }

            for (int i = 0; i < stuSensorInfo.length; i++) {
                stuSensorInfo[i] = new NET_CFG_VideoWidgetSensorInfo();
            }
        }
    }

    // 报警事件类型 NET_EVENT_VIDEOABNORMALDETECTION 对应的数据描述信息
    public static class ALARM_VIDEOABNORMAL_DETECTION_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public int              nType;                                // 检测类型,0-视频丢失, 1-视频遮挡, 2-画面冻结, 3-过亮, 4-过暗, 5-场景变化
        // 6-条纹检测 , 7-噪声检测 , 8-偏色检测 , 9-视频模糊检测 , 10-对比度异常检测
        // 11-视频运动, 12-视频闪烁, 13-视频颜色, 14-虚焦检测, 15-过曝检测
        public int              nValue;                               // 检测值,值越高表示视频质量越差, GB30147定义
        public int              nOccurrenceCount;                     // 规则被触发生次数

        public ALARM_VIDEOABNORMAL_DETECTION_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 停车发卡刷卡类型
    public static class NET_PARKING_CARD_TYPE extends SdkStructure
    {
        public static final int   NET_PARKING_CARD_TYPE_UNKNOWN = 0;
        public static final int   NET_PARKING_CARD_TYPE_SEND = 1;       // 发卡
        public static final int   NET_PARKING_CARD_TYPE_DETECT = 2;     // 刷卡
    }

    // 报警事件类型 NET_ALARM_PARKING_CARD (停车刷卡事件)对应的数据描述信息
    public static class ALARM_PARKING_CARD extends SdkStructure {
        public int              dwSize;
        public int              emType;                               // 类型, 参考 NET_PARKING_CARD_TYPE
        public int              dwCardNo;                             // 卡号
        public byte[]           szPlate = new byte[NET_COMMON_STRING_16]; // 车牌

        public ALARM_PARKING_CARD() {
            this.dwSize = this.size();
        }
    }

    // 报警事件类型 NET_ALARM_NEW_FILE 对应的数据描述信息
    public static class ALARM_NEW_FILE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 抓图通道号
        public int              nEventID;                             // 事件ID
        public int              dwEvent;                              // 事件类型
        public int              FileSize;                             // 文件大小,单位是字节
        public int              nIndex;                               // 事件源通道
        public int              dwStorPoint;                          // 存储点
        public byte[]           szFileName = new byte[128];           // 文件名
        public int              nGroupID;                             //事件组ID，同一物体抓拍过程内GroupID相同
        public int              nCountInGroup;                        //一个事件组内的抓拍张数
        public int              nMailTimeout;                         //邮件模块收到一组图片的第一张，开始计时。超时时间到即使没有收全整组图片也发邮件，单位秒
        public byte[]           szDeviceIP = new byte[40];            //上报事件的设备IP
        public int              nPoliceIDNum;                         //上报录像所录制的警员编号列表数量
        public BYTE_ARRAY_32[]  szPoliceID = new BYTE_ARRAY_32[32];   //上报录像所录制的警员编号列表
        public byte[]           szReserved = new byte[1024];          //保留字节

        public ALARM_NEW_FILE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 人数越上限类型
    public static class EM_UPPER_LIMIT_TYPE extends SdkStructure
    {
        public static final int   EM_UPPER_LIMIT_TYPE_UNKNOWN = 0;
        public static final int   EM_UPPER_LIMIT_TYPE_ENTER_OVER = 1;   // 进入越上限
        public static final int   EM_UPPER_LIMIT_TYPE_EXIT_OVER = 2;    // 出来越上限
        public static final int   EM_UPPER_LIMIT_TYPE_INSIDE_OVER = 3;  // 内部越上限
        public static final int   EM_UPPER_LIMIT_TYPE_PASS_OVER = 4;    // 经过越上限
    }

    // 事件类型 NET_ALARM_HUMAM_NUMBER_STATISTIC (人数量/客流量统计事件NumberStat对应的数据描述信息)
    public static class ALARM_HUMAN_NUMBER_STATISTIC_INFO extends SdkStructure
    {
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventAction;                         // 事件动作,0-事件持续, 1-表示事件开始, 2-表示事件结束;
        public int              nNumber;                              // 区域内物体的个数
        public int              nEnteredNumber;                       // 进入区域或者出入口内的物体个数
        public int              nExitedNumber;                        // 出来区域或者出入口内的物体个数
        public int              emUpperLimitType;                     // 人数越上限类型,参见EM_UPPER_LIMIT_TYPE定义
        public int              nChannelID;                           // 通道号
        public int              nPassedNumber;                        // 经过区域物体的个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           reserved = new byte[504];             // 预留
    }

    /////////////////////////////////智能支持/////////////////////////////////
    //物体对应图片文件信息,对应DH_PIC_INFO
    public static class NET_PIC_INFO extends SdkStructure
    {
        public int              dwOffSet;                             // 文件在二进制数据块中的偏移位置,单位:字节
        public int              dwFileLenth;                          // 文件大小,单位:字节
        public short            wWidth;                               // 图片宽度,单位:像素
        public short            wHeight;                              // 图片高度,单位:像素
        public Pointer          pszFilePath;                          // 鉴于历史原因,该成员只在事件上报时有效， char *
        // 文件路径
        // 用户使用该字段时需要自行申请空间进行拷贝保存
        public byte             bIsDetected;                          // 图片是否算法检测出来的检测过的提交识别服务器时,
        // 则不需要再时检测定位抠图,1:检测过的,0:没有检测过
        public byte[]           bReserved = new byte[2];              // 预留字节数
        public byte             byQulityScore;                        // 人脸抓拍质量分数, 0-100
        public int              nFilePathLen;                         // 文件路径长度 既pszFilePath 用户申请的大小
        public NET_POINT        stuPoint;                             // 小图左上角在大图的位置，使用绝对坐标系
        public	int              nIndexInData;                         // 在上传图片数据中的图片序号

        public NET_PIC_INFO() {
        }
    }

    // 人员类型
    public static class EM_PERSON_TYPE extends SdkStructure
    {
        public static final int   PERSON_TYPE_UNKNOWN = 0;
        public static final int   PERSON_TYPE_NORMAL = 1;               //普通人员
        public static final int   PERSON_TYPE_SUSPICION = 2;            //
    }

    // 证件类型
    public static class EM_CERTIFICATE_TYPE extends SdkStructure
    {
        public static final int   CERTIFICATE_TYPE_UNKNOWN = 0;
        public static final int   CERTIFICATE_TYPE_IC = 1;              //证件
        public static final int   CERTIFICATE_TYPE_PASSPORT = 2;        //护照
        public static final int   CERTIFICATE_TYPE_OUTERGUARD = 3;      //军官证
        public static final int   CERTIFICATE_TYPE_STUDENT = 4;         //学生证
        public static final int   CERTIFICATE_TYPE_POLICE = 5;          //警官证
        public static final int   CERTIFICATE_TYPE_LAWYER = 6;          //律师
    }

    //人员信息
    public static class FACERECOGNITION_PERSON_INFO extends SdkStructure
    {
        public byte[]           szPersonName = new byte[NET_MAX_NAME_LEN]; // 姓名,此参数作废
        public short            wYear;                                // 出生年,作为查询条件时,此参数填0,则表示此参数无效
        public byte             byMonth;                              // 出生月,作为查询条件时,此参数填0,则表示此参数无效
        public byte             byDay;                                // 出生日,作为查询条件时,此参数填0,则表示此参数无效
        public byte[]           szID = new byte[NET_MAX_PERSON_ID_LEN]; // 人员唯一标示(证件号码,工号,或其他编号)
        public byte             bImportantRank;                       // 人员重要等级,1~10,数值越高越重要,作为查询条件时,此参数填0,则表示此参数无效
        public byte             bySex;                                // 性别,1-男,2-女,作为查询条件时,此参数填0,则表示此参数无效
        public short            wFacePicNum;                          // 图片张数
        public NET_PIC_INFO[]   szFacePicInfo = new NET_PIC_INFO[NET_MAX_PERSON_IMAGE_NUM]; //当前人员对应的图片信息
        public byte             byType;                               // 人员类型,详见EM_PERSON_TYPE
        public byte             byIDType;                             // 证件类型,详见EM_CERTIFICATE_TYPE
        public byte             byGlasses;                            // 是否戴眼镜，0-未知 1-不戴 2-戴
        public byte             byAge;                                // 年龄,0表示未知
        public byte[]           szProvince = new byte[NET_MAX_PROVINCE_NAME_LEN]; // 省份
        public byte[]           szCity = new byte[NET_MAX_CITY_NAME_LEN]; // 城市
        public byte[]           szPersonNameEx = new byte[NET_MAX_PERSON_NAME_LEN]; // 姓名,因存在姓名过长,16字节无法存放问题,故增加此参数,
        public byte[]           szUID = new byte[NET_MAX_PERSON_ID_LEN]; // 人员唯一标识符,首次由服务端生成,区别于ID字段
        // 修改,删除操作时必填
        public byte[]           szCountry = new byte[NET_COUNTRY_LENGTH]; // 国籍,符合ISO3166规范
        public byte             byIsCustomType;                       // 人员类型是否为自定义: 0 使用Type规定的类型 1 自定义,使用szPersonName字段
        public Pointer          pszComment;                           // 备注信息, 用户自己申请内存的情况时,
        // 下方bCommentLen需填写对应的具体长度值，推荐长度 NET_COMMENT_LENGTH
        public Pointer          pszGroupID;                           // 人员所属组ID, 用户自己申请内存的情况时,
        // 下方bGroupIdLen需填写对应的具体长度值，推荐长度 NET_GROUPID_LENGTH
        public Pointer          pszGroupName;                         // 人员所属组名, 用户自己申请内存的情况时,
        // 下方bGroupNameLen需填写对应的具体长度值，推荐长度 NET_GROUPNAME_LENGTH
        public Pointer          pszFeatureValue;                      // 人脸特征, 用户自己申请内存的情况时,
        // 下方bFeatureValueLen需填写对应的具体长度值，推荐长度 NET_FEATUREVALUE_LENGTH
        public byte             bGroupIdLen;                          // pszGroupID的长度
        public byte             bGroupNameLen;                        // pszGroupName的长度
        public byte             bFeatureValueLen;                     // pszFeatureValue的长度
        public byte             bCommentLen;                          // pszComment的长度
        public int              emEmotion;                            // 表情, 参考 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE

        public FACERECOGNITION_PERSON_INFO() {
            for(int i=0;i<szFacePicInfo.length;i++){
                szFacePicInfo[i]=new NET_PIC_INFO();
            }

        }
    }

    ///////////////////////////////////目标识别模块相关结构体///////////////////////////////////////
    public static class NET_UID_CHAR extends SdkStructure
    {
        public byte[]           szUID = new byte[NET_MAX_PERSON_ID_LEN]; //UID内容
    }

    public static class NET_UUID_CHAR extends SdkStructure
    {
        public byte[]           szUUID = new byte[64];                //UUID内容
    }

    //目标识别数据库操作
    public static class EM_OPERATE_FACERECONGNITIONDB_TYPE
    {
        public static final int   NET_FACERECONGNITIONDB_UNKOWN = 0;
        public static final int   NET_FACERECONGNITIONDB_ADD = 1;       //添加人员信息和人脸样本，如果人员已经存在，图片数据和原来的数据合并
        public static final int   NET_FACERECONGNITIONDB_DELETE = 2;    //删除人员信息和人脸样本
        public static final int   NET_FACERECONGNITIONDB_MODIFY = 3;    //修改人员信息和人脸样本,人员的UID标识必填
        public static final int   NET_FACERECONGNITIONDB_DELETE_BY_UID = 4; //通过UID删除人员信息和人脸样本
    }

    //CLIENT_OperateFaceRecognitionDB接口输入参数
    public static class NET_IN_OPERATE_FACERECONGNITIONDB extends SdkStructure
    {
        public int              dwSize;
        public int              emOperateType;                        //操作类型, 取EM_OPERATE_FACERECONGNITIONDB_TYPE中的值
        public FACERECOGNITION_PERSON_INFO stPersonInfo;              //人员信息
        //emOperateType操作类型为ET_FACERECONGNITIONDB_DELETE_BY_UID时使用,stPeronInfo字段无效
        public int              nUIDNum;                              //UID个数
        public Pointer          stuUIDs;                              //人员唯一标识符,首次由服务端生成,区别于ID字段, NET_UID_CHAR[]
        // 图片二进制数据
        public Pointer          pBuffer;                              //缓冲地址, char *
        public int              nBufferLen;                           //缓冲数据长度
        public int              bUsePersonInfoEx;                     // 使用人员扩展信息, 1:true   0:false
        public FACERECOGNITION_PERSON_INFOEX stPersonInfoEx;          // 人员信息扩展
        //emOperateType操作类型为NET_FACERECONGNITIONDB_DELETE_BY_UUID时使用,stPeronInfo字段无效
        public int              nUUIDNum;                             //UUID个数
        public Pointer          stuUUIDs;                             //人员唯一标识符,由平台端下发，区别于UID字段. 由用户申请内存,大小为sizeof(NET_UUID_CHAR)*nUUIDNum

        public NET_IN_OPERATE_FACERECONGNITIONDB()
        {
            this.dwSize = this.size();
        }
    }

    // 错误代码，emOperateType操作类型为ET_FACERECONGNITIONDB_DELETE_BY_UID时使用
    public static class EM_ERRORCODE_TYPE extends SdkStructure
    {
        public static final int   EM_ERRORCODE_TYPE_UNKNOWN = -1;       // 未知错误
        public static final int   EM_ERRORCODE_TYPE_SUCCESS = 0;        // 成功
        public static final int   EM_ERRORCODE_TYPE_PERSON_NOT_EXIST = 1; // 人员不存在
        public static final int   EM_ERRORCODE_TYPE_DATABASE_ERROR = 2; //  数据库操作失败
    }

    //CLIENT_OperateFaceRecognitionDB接口输出参数
    public static class NET_OUT_OPERATE_FACERECONGNITIONDB extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUID = new byte[NET_MAX_PERSON_ID_LEN]; // 人员唯一标识符, 只有在操作类型为NET_FACERECONGNITIONDB_ADD时有效
        //emOperateType操作类型为ET_FACERECONGNITIONDB_DELETE_BY_UID时使用
        public int              nErrorCodeNum;                        // 错误码个数
        public int[]            emErrorCodes = new int[512];          // 错误码
        // emOperateType操作类型为ET_FACERECONGNITIONDB_DELETE_BY_UID时使用
        public byte[]           szUID2 = new byte[64];                //添加人员UID
        public int              nUIDType;                             //指定UID使用字段, 0使用UID字段，1使用UID2字段

        public NET_OUT_OPERATE_FACERECONGNITIONDB()
        {
            this.dwSize = this.size();
        }
    }

    //人脸对比模式
    public static class EM_FACE_COMPARE_MODE extends SdkStructure
    {
        public static final int   NET_FACE_COMPARE_MODE_UNKOWN = 0;
        public static final int   NET_FACE_COMPARE_MODE_NORMAL = 1;     //正常
        public static final int   NET_FACE_COMPARE_MODE_AREA = 2;       //指定人脸区域组合区域
        public static final int   NET_FACE_COMPARE_MODE_AUTO = 3;       //智能模式,算法根据人脸各个区域情况自动选取组合
    }

    //人脸区域
    public static class EM_FACE_AREA_TYPE extends SdkStructure
    {
        public static final int   NET_FACE_AREA_TYPE_UNKOWN = 0;
        public static final int   NET_FACE_AREA_TYPE_EYEBROW = 1;       //眉毛
        public static final int   NET_FACE_AREA_TYPE_EYE = 2;           //眼睛
        public static final int   NET_FACE_AREA_TYPE_NOSE = 3;          //鼻子
        public static final int   NET_FACE_AREA_TYPE_MOUTH = 4;         //嘴巴
        public static final int   NET_FACE_AREA_TYPE_CHEEK = 5;         //脸颊
    }

    public static class NET_FACE_MATCH_OPTIONS extends SdkStructure
    {
        public int              dwSize;
        public int              nMatchImportant;                      // 人员重要等级1~10,数值越高越重要,(查询重要等级大于等于此等级的人员)， 类型为unsigned int
        public int              emMode;                               // 人脸比对模式,详见EM_FACE_COMPARE_MODE, 取EM_FACE_COMPARE_MODE中的值
        public int              nAreaNum;                             // 人脸区域个数
        public int[]            szAreas = new int[MAX_FACE_AREA_NUM]; // 人脸区域组合,emMode为NET_FACE_COMPARE_MODE_AREA时有效, 数组元素取EM_FACE_AREA_TYPE中的值
        public int              nAccuracy;                            // 识别精度(取值1~10,随着值增大,检测精度提高,检测速度下降。最小值为1表示检测速度优先,最大值为10表示检测精度优先。暂时只对目标检测有效)
        public int              nSimilarity;                          // 相似度(必须大于该相识度才报告;百分比表示,1~100)
        public int              nMaxCandidate;                        // 报告的最大候选个数(根据相似度进行排序,取相似度最大的候选人数报告)
        public int              emQueryMode;                          // 以图搜图查询模式，详见EM_FINDPIC_QUERY_MODE
        public int              emOrdered;                            // 以图搜图结果上报排序方式，详见EM_FINDPIC_QUERY_ORDERED

        public NET_FACE_MATCH_OPTIONS()
        {
            this.dwSize = this.size();
        }
    }

    // 以图搜图查询模式
    public static class EM_FINDPIC_QUERY_MODE extends SdkStructure
    {
        public static final int   EM_FINDPIC_QUERY_UNKNOWN = 0;         // 未知
        public static final int   EM_FINDPIC_QUERY_PASSIVE = 1;         // 被动查询
        public static final int   EM_FINDPIC_QUERY_ACTIVE = 2;          // 主动推送
    }

    // 以图搜图结果上报排序方式
    public static class EM_FINDPIC_QUERY_ORDERED extends SdkStructure
    {
        public static final int   EM_FINDPIC_QUERY_BY_SIMILARITY = 0;   // 按相似度从高到底
        public static final int   EM_FINDPIC_QUERY_BY_TIME_FORWARD = 1; // 按时间正序
        public static final int   EM_FINDPIC_QUERY_BY_TIME_REVERSE = 2; // 按时间倒序
    }

    //目标识别人脸类型
    public static class EM_FACERECOGNITION_FACE_TYPE extends SdkStructure
    {
        public static final int   EM_FACERECOGNITION_FACE_TYPE_UNKOWN = 0;
        public static final int   EM_FACERECOGNITION_FACE_TYPE_ALL = 1; // 所有人脸
        public static final int   EM_FACERECOGNITION_FACE_TYPE_REC_SUCCESS = 2; // 识别成功
        public static final int   EM_FACERECOGNITION_FACE_TYPE_REC_FAIL = 3; // 识别失败
    }

    public static class NET_FACE_FILTER_CONDTION extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stStartTime = new NET_TIME();         // 开始时间
        public NET_TIME         stEndTime = new NET_TIME();           // 结束时间
        public byte[]           szMachineAddress = new byte[MAX_PATH]; // 地点,支持模糊匹配
        public int              nRangeNum;                            // 实际数据库个数
        public byte[]           szRange = new byte[MAX_FACE_DB_NUM];  // 待查询数据库类型,详见EM_FACE_DB_TYPE
        public int              emFaceType;                           // 待查询人脸类型,详见EM_FACERECOGNITION_FACE_TYPE， 取EM_FACERECOGNITION_FACE_TYPE中的值
        public int              nGroupIdNum;                          // 人员组数
        public GROUP_ID[]       szGroupIdArr = new GROUP_ID[MAX_GOURP_NUM]; // 人员组ID
        public NET_TIME         stBirthdayRangeStart = new NET_TIME(); // 生日起始时间
        public NET_TIME         stBirthdayRangeEnd = new NET_TIME();  // 生日结束时间
        public byte[]           byAge = new byte[MAX_AGE_NUM];        // 年龄区间，当byAge[0]=0与byAge[1]=0时，表示查询全年龄
        public byte[]           byReserved = new byte[2];             // 保留字节对齐
        public int[]            emEmotion = new int[MAX_EMOTION_NUM]; // 表情条件
        public int              nEmotionNum;                          // 表情条件的个数
        public int              nUIDNum;                              // 人员唯一标识数
        public byte[]           szUIDs = new byte[64*32];             // 人员唯一标识列表
        public int              nUUIDNum;                             // 平台端人员唯一标识数
        public byte[]           szUUIDs = new byte[64*32];            // 平台端人员唯一标识列表，根据faceRecognitionServer.getCaps获取到的能力是否存在字段SupportIDFromServer且值为true时有效
        public int              bIsUsingRealUTCTime;                  // 是否使用UTC格式的开始、结束时间
        public NET_TIME         stuStartTimeRealUTC = new NET_TIME(); // 开始时间（UTC时间格式）
        public NET_TIME         stuEndTimeRealUTC = new NET_TIME();   // 结束时间（UTC时间格式）备注：与StartTimeRealUTC配对使用
        public int              bIsUsingRegisterStorageTime;          // 是否使用注册库人员的开始、结束时间
        public NET_TIME         stuStartRegisterStorageTime = new NET_TIME(); // 注册库人员的入库开始时间
        public NET_TIME         stuEndRegisterStorageTime = new NET_TIME(); // 注册库人员的入库结束时间
        public int              bIsUsingModifyTime;                   // 是否使用注册库人员的修改开始、结束时间
        public NET_TIME         stuStartModifyTime = new NET_TIME();  // 注册库人员的修改开始时间
        public NET_TIME         stuEndModifyTime = new NET_TIME();    // 注册库人员的修改结束时间
        public int              bFindObjRecord;                       //是否查询目标时间记录

        public NET_FACE_FILTER_CONDTION()
        {
            for(int i=0;i<szGroupIdArr.length;i++){
                szGroupIdArr[i]=new GROUP_ID();
            }

            this.dwSize = this.size();

        }
    }

    // 人员组ID
    public static class GROUP_ID extends SdkStructure
    {
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; //人员组ID
    }

    //CLIENT_StartFindFaceRecognition接口输入参数
    public static class NET_IN_STARTFIND_FACERECONGNITION extends SdkStructure
    {
        public int              dwSize;
        public int              bPersonEnable;                        // 人员信息查询条件是否有效, BOOL类型，取值0或1
        public FACERECOGNITION_PERSON_INFO stPerson = new FACERECOGNITION_PERSON_INFO(); // 人员信息查询条件
        public NET_FACE_MATCH_OPTIONS stMatchOptions = new NET_FACE_MATCH_OPTIONS(); // 人脸匹配选项
        public NET_FACE_FILTER_CONDTION stFilterInfo = new NET_FACE_FILTER_CONDTION(); // 查询过滤条件
        // 图片二进制数据
        public Pointer          pBuffer;                              // 缓冲地址, char *
        public int              nBufferLen;                           // 缓冲数据长度
        public int              nChannelID;                           // 通道号
        public int              bPersonExEnable;                      // 人员信息查询条件是否有效, 并使用扩展结构体
        public FACERECOGNITION_PERSON_INFOEX stPersonInfoEx = new FACERECOGNITION_PERSON_INFOEX(); // 人员信息扩展
        public int              nSmallPicIDNum;                       // 小图ID数量
        public int[]            nSmallPicID = new int[MAX_SMALLPIC_NUM]; // 小图ID
        public int              emObjectType;                         // 搜索的目标类型,参考EM_OBJECT_TYPE
        public byte[]           szChannel = new byte[NET_COMMON_STRING_32]; // 通道号(使用)

        public NET_IN_STARTFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }

    //CLIENT_StartFindFaceRecognition接口输出参数
    public static class NET_OUT_STARTFIND_FACERECONGNITION extends SdkStructure {
        public int              dwSize;
        public int              nTotalCount;                          // 返回的符合查询条件的记录个数
        // -1表示总条数未生成,要推迟获取
        // 使用CLIENT_AttachFaceFindState接口状态
        public LLong            lFindHandle;                          // 查询句柄
        public int              nToken;                               // 获取到的查询令牌

        public NET_OUT_STARTFIND_FACERECONGNITION() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_DoFindFaceRecognition 接口输入参数
    public static class NET_IN_DOFIND_FACERECONGNITION extends SdkStructure {
        public int              dwSize;
        public LLong            lFindHandle;                          // 查询句柄
        public int              nBeginNum;                            // 查询起始序号
        public int              nCount;                               // 当前想查询的记录条数
        public int              emDataType;                           // 指定查询结果返回图片的格式. 参考  EM_NEEDED_PIC_RETURN_TYPE

        public NET_IN_DOFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }

    // 查询结果返回图片的格式
    public static class EM_NEEDED_PIC_RETURN_TYPE extends SdkStructure {
        public static final int   EM_NEEDED_PIC_TYPE_UNKOWN = 0;        // 未知类型
        public static final int   EM_NEEDED_PIC_TYPE_HTTP_URL = 1;      // 返回图片HTTP链接
        public static final int   EM_NEEDED_PIC_TYPE_BINARY_DATA = 2;   // 返回图片二进制数据
        public static final int   EM_NEEDED_PIC_TYPE_HTTP_AND_BINARY = 3; // 返回二进制和HTTP链接
    }

    //候选人员信息
    public static class CANDIDATE_INFO extends SdkStructure {
        public FACERECOGNITION_PERSON_INFO stPersonInfo;              // 人员信息
        // 布控（禁止名单）库,指布控库中人员信息；
        // 历史库,指历史库中人员信息
        // 报警库,指布控库的人员信息
        public byte             bySimilarity;                         // 和查询图片的相似度,百分比表示,1~100
        public byte             byRange;                              // 人员所属数据库范围,详见EM_FACE_DB_TYPE
        public byte[]           byReserved1 = new byte[2];
        public NET_TIME         stTime;                               // 当byRange为历史数据库时有效,表示查询人员出现的时间
        public byte[]           szAddress = new byte[MAX_PATH];       // 当byRange为历史数据库时有效,表示查询人员出现的地点
        public int              bIsHit;                               // BOOL类型,是否有识别结果,指这个检测出的人脸在库中有没有比对结果
        public NET_PIC_INFO_EX3 stuSceneImage;                        // 人脸全景图
        public int              nChannelID;                           // 通道号
        public byte[]           byReserved = new byte[32];            // 保留字节

        public CANDIDATE_INFO() {}
    }

    // 物体对应图片文件信息(包含图片路径)，对应C++头文件DH_PIC_INFO_EX3
    public static class NET_PIC_INFO_EX3 extends SdkStructure
    {
        public int              dwOffSet;                             // 文件在二进制数据块中的偏移位置, 单位:字节
        public int              dwFileLenth;                          // 文件大小, 单位:字节
        public short            wWidth;                               // 图片宽度, 单位:像素
        public short            wHeight;                              // 图片高度, 单位:像素
        public byte[]           szFilePath = new byte[64];            // 文件路径
        public byte             bIsDetected;                          // 图片是否算法检测出来的检测过的提交识别服务器时,
        // 则不需要再时检测定位抠图,1:检测过的,0:没有检测过
        public byte[]           bReserved = new byte[11];             // 保留
    }

    //CLIENT_DoFindFaceRecognition接口输出参数
    public static class NET_OUT_DOFIND_FACERECONGNITION extends SdkStructure
    {
        public int              dwSize;
        public int              nCadidateNum;                         // 实际返回的候选信息结构体个数
        public CANDIDATE_INFO[] stCadidateInfo = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(MAX_FIND_COUNT); //候选信息数组
        // 图片二进制数据
        public Pointer          pBuffer;                              // 缓冲地址, char *
        public int              nBufferLen;                           // 缓冲数据长度
        public int              bUseCandidatesEx;                     // 是否使用候选对象扩展结构体,
        // 若为1-true, 则表示使用stuCandidatesEx, 且stuCandidates无效, 否则相反
        public int              nCadidateExNum;                       // 实际返回的候选信息结构体个数
        public CANDIDATE_INFOEX[] stuCandidatesEx = (CANDIDATE_INFOEX[])new CANDIDATE_INFOEX().toArray(MAX_FIND_COUNT); // 当前人脸匹配到的候选对象信息, 实际返回个数同nCandidateNum

        public NET_OUT_DOFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }

    /////////////////////////////////智能支持/////////////////////////////////
    //CLIENT_DetectFace接口输入参数
    public static class NET_IN_DETECT_FACE extends SdkStructure
    {
        public int              dwSize;
        public NET_PIC_INFO     stPicInfo;                            //大图信息
        // 图片二进制数据
        public Pointer          pBuffer;                              //缓冲地址, char *
        public int              nBufferLen;                           //缓冲数据长度

        public NET_IN_DETECT_FACE()
        {
            this.dwSize = this.size();
        }
    }

    //CLIENT_DetectFace接口输出参数
    public static class NET_OUT_DETECT_FACE extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pPicInfo;                             //检测出的人脸图片信息,由用户申请空间, NET_PIC_INFO*
        public int              nMaxPicNum;                           //最大人脸图片信息个数
        public int              nRetPicNum;                           //实际返回的人脸图片个数
        // 图片二进制数据
        public Pointer          pBuffer;                              //缓冲地址,由用户申请空间,存放检测出的人脸图片数据, char *
        public int              nBufferLen;                           //缓冲数据长度

        public NET_OUT_DETECT_FACE()
        {
            this.dwSize = this.size();
        }
    }

    // 目标识别事件类型
    public static class EM_FACERECOGNITION_ALARM_TYPE extends SdkStructure
    {
        public static final int   NET_FACERECOGNITION_ALARM_TYPE_UNKOWN = 0;
        public static final int   NET_FACERECOGNITION_ALARM_TYPE_ALL = 1; //禁止/允许名单
        public static final int   NET_FACERECOGNITION_ALARM_TYPE_BLACKLIST = 2; //禁止名单
        public static final int   NET_FACERECOGNITION_ALARM_TYPE_WHITELIST = 3; //允许名单
    }

    // NET_FILE_QUERY_FACE 对应的目标识别服务查询参数
    public static class MEDIAFILE_FACERECOGNITION_PARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        //////// 查询过滤条件
        public NET_TIME         stStartTime;                          // 开始时间
        public NET_TIME         stEndTime;                            // 结束时间
        public byte[]           szMachineAddress = new byte[MAX_PATH]; //地点,支持模糊匹配
        public int              nAlarmType;                           // 待查询报警类型,详见 EM_FACERECOGNITION_ALARM_TYPE
        public int              abPersonInfo;                         // 人员信息是否有效, BOOL类型，取值0或1
        public FACERECOGNITION_PERSON_INFO stPersonInfo;              // 人员信息
        public int              nChannelId;                           // 通道号
        public int              nGroupIdNum;                          // 人员组数
        public GROUP_ID[]       szGroupIdArr = (GROUP_ID[])new GROUP_ID().toArray(MAX_GOURP_NUM); // 人员组ID
        public int              abPersonInfoEx;                       // 人员信息扩展是否有效
        public FACERECOGNITION_PERSON_INFOEX stPersonInfoEx;          // 人员信息扩展
        public int              bSimilaryRangeEnable;                 //相似度是否有效
        public int[]            nSimilaryRange = new int[2];          //相似度范围
        public int              nFileType;                            // 文件类型,0:查询任意类型,1:查询jpg图片,2:查询dav
        public int              bOnlySupportRealUTC;                  // 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stStartTime, stEndTime), 为FALSE表示仅下发stStartTime, stEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stStartTime, stEndTime)互斥
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stStartTime, stEndTime)互斥
        public int[]            nChannels = new int[256];             //通道号数组
        public int              nChannelsNum;                         //通道号数组有效个数

        public MEDIAFILE_FACERECOGNITION_PARAM()
        {
            this.dwSize = this.size();
        }
    }

    // 图片类型
    public static class EM_FACEPIC_TYPE extends SdkStructure
    {
        public static final int   NET_FACEPIC_TYPE_UNKOWN = 0;          // 未知类型
        public static final int   NET_FACEPIC_TYPE_GLOBAL_SENCE = 1;    // 人脸全景大图
        public static final int   NET_FACEPIC_TYPE_SMALL = 2;           // 人脸小图
    }

    // 参数详细信息
    public static class MEDIAFILE_FACE_DETECTION_DETAIL_PARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              dwObjectId;                           // 物体ID
        public int              dwFrameSequence;                      // 帧序号
        public NET_TIME_EX      stTime;                               // 发生时间

        public MEDIAFILE_FACE_DETECTION_DETAIL_PARAM()
        {
            this.dwSize = this.size();
        }
    }

    // 目标检测人脸戴眼镜特征类型
    public static class EM_FACEDETECT_GLASSES_TYPE extends SdkStructure
    {
        public static final int   EM_FACEDETECT_GLASSES_UNKNOWN = 0;    // 未知
        public static final int   EM_FACEDETECT_WITH_GLASSES = 1;       // 戴眼镜
        public static final int   EM_FACEDETECT_WITHOUT_GLASSES = 2;    // 不戴眼镜
    }

    //体温类型
    public static class EM_TEMPERATURE_TYPE extends SdkStructure
    {
        public static final int   EM_TEMPERATURE_TYPE_UNKNOWN = -1;     // 未知
        public static final int   EM_TEMPERATURE_TYPE_UNDEFINE = 0;     // 未识别
        public static final int   EM_TEMPERATURE_TYPE_LOW = 1;          // 体温过低
        public static final int   EM_TEMPERATURE_TYPE_NORMAL = 2;       // 体温正常
        public static final int   EM_TEMPERATURE_TYPE_HIGH = 3;         // 体温过高
    }

    // NET_FILE_QUERY_FACE_DETECTION 对应的目标识别服务查询参数
    public static class MEDIAFILE_FACE_DETECTION_PARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        ////////查询过滤条件
        public int              nChannelID;                           // 通道号
        public NET_TIME         stuStartTime;                         // 起始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              emPicType;                            // 图片类型,对应枚举 EM_FACEPIC_TYPE
        public int              bDetailEnable;                        // 是否有详细信息,boolean类型,为1或者0
        public MEDIAFILE_FACE_DETECTION_DETAIL_PARAM stuDetail;       // 参数详细信息
        public int              emSex;                                // 性别类型, 参考 EM_DEV_EVENT_FACEDETECT_SEX_TYPE
        public int              bAgeEnable;                           // 是否指定年龄段
        public int[]            nAgeRange = new int[2];               // 年龄范围
        public int              nEmotionValidNum;                     // 人脸特征数组有效个数,与 emFeature 结合使用, 如果为0则表示查询所有表情
        public int[]            emEmotion = new int[NET_MAX_FACEDETECT_FEATURE_NUM]; // 人脸特征数组,与 byFeatureValidNum 结合使用,对应 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public int              emGlasses;                            // 是否戴眼镜,参考 EM_FACEDETECT_GLASSES_TYPE
        public int              emMask;                               // 是否带口罩,参考 EM_MASK_STATE_TYPE
        public int              emBeard;                              // 是否有胡子,参考 EM_BEARD_STATE_TYPE
        public int              nIsStranger;                          // 0-都查询；1-仅查未开启陌生人模式；2-仅查开启陌生人模式
        public int              bOnlySupportRealUTC;                  // 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stuStartTime, stuEndTime), 为FALSE表示仅下发stuStartTime, stuEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥
        public int[]            nChannelIDs = new int[256];           // 通道号数组
        public int              nChannelNum;                          // 通道号数组数量
        public int              bUnEnableSupportGlasses;              //是否不下发Glasses字段

        public MEDIAFILE_FACE_DETECTION_PARAM()
        {
            this.dwSize = this.size();
        }
    }

    // NET_FILE_QUERY_FACE_DETECTION对应的目标识别服务FINDNEXT查询返回参数
    public static class MEDIAFILE_FACE_DETECTION_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              ch;                                   // 通道号
        public byte[]           szFilePath = new byte[128];           // 文件路径
        public int              size;                                 // 文件长度,该字段废弃，请使用sizeEx
        public NET_TIME         starttime;                            // 开始时间
        public NET_TIME         endtime;                              // 结束时间
        public int              nWorkDirSN;                           // 工作目录编号
        public byte             nFileType;                            // 文件类型  1：jpg图片
        public byte             bHint;                                // 文件定位索引
        public byte             bDriveNo;                             // 磁盘号
        public byte             byPictureType;                        // 图片类型, 0-普通, 1-合成, 2-抠图
        public int              nCluster;                             // 簇号
        public int              emPicType;                            // 图片类型,详见  EM_FACEPIC_TYPE
        public int              dwObjectId;                           // 物体ID
        public int[]            dwFrameSequence = new int[NET_MAX_FRAMESEQUENCE_NUM]; // 帧序号,数组有2个元素时,第一个表示小图,第二个表示大图
        public int              nFrameSequenceNum;                    // 帧序号个数
        public NET_TIME_EX[]    stTimes = (NET_TIME_EX[])new NET_TIME_EX().toArray(NET_MAX_TIMESTAMP_NUM); // 发生时间,数组有2个元素时,第一个表示小图,第二个表示大图
        public int              nTimeStampNum;                        // 表示在簇中的图片序号
        public int              nPicIndex;                            // 表示在簇中的图片序号
        // 对于同一个簇中打包多张图片,提供索引方式定位图片
        public int              emSex;                                // 性别类型,参考 EM_DEV_EVENT_FACEDETECT_SEX_TYPE
        public int              nAge;                                 // 年龄
        public int              emEmotion;                            // 人脸表情,参考 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public int              emGlasses;                            // 是否戴眼镜,参考 EM_FACEDETECT_GLASSES_TYPE
        public long             sizeEx;                               // 文件长度扩展，支持文件长度大于4G，单位字节
        public int              emMask;                               // 是否带口罩,参考 EM_MASK_STATE_TYPE
        public int              emBeard;                              // 是否有胡子,参考 EM_BEARD_STATE_TYPE
        public byte[]           szReserved = new byte[4];
        public int              emEye;                                // 眼睛状态,参考 EM_EYE_STATE_TYPE
        public int              emMouth;                              // 嘴巴状态,参考 EM_MOUTH_STATE_TYPE
        public int              nAttractive;                          // 魅力值
        public int              nIsStranger;                          // 0-未知；1-未开启陌生人模式；2-开启了陌生人模式
        public byte[]           szFaceObjectUrl = new byte[128];      // 当 emPicType 为 NET_FACEPIC_TYPE_GLOBAL_SENCE 时, 代表人脸小图路径
        public NET_EULER_ANGLE  stuFaceCaptureAngle;                  // 人脸在抓拍图片中的角度信息, nPitch:抬头低头的俯仰角, nYaw左右转头的偏航角, nRoll头在平面内左偏右偏的翻滚角
        // 角度值取值范围[-90,90], 三个角度值都为999表示此角度信息无效
        public int              nFaceQuality;                         // 人脸抓拍质量分数
        public NET_FACEDETECT_IMAGE_INFO stuSceneImage;               // 大图信息
        public NET_POINT        stuFaceCenter;                        // 人脸型心(不是包围盒中心), 0-8191相对坐标, 相对于小图
        public int              bRealUTC;                             // 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅starttime和endtime有效(仅使用starttime和endtime)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用

        public MEDIAFILE_FACE_DETECTION_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // NET_MEDIA_QUERY_TRAFFICCAR对应的查询条件
    public static class MEDIA_QUERY_TRAFFICCAR_PARAM extends SdkStructure
    {
        public int              nChannelID;                           // 通道号从0开始,-1表示查询所有通道
        public NET_TIME         StartTime;                            // 开始时间
        public NET_TIME         EndTime;                              // 结束时间
        public int              nMediaType;                           // 文件类型,0:任意类型, 1:jpg图片, 2:dav文件
        public int              nEventType;                           // 事件类型,详见"智能分析事件类型", 0:表示查询任意事件,此参数废弃,请使用pEventTypes
        public byte[]           szPlateNumber = new byte[32];         // 车牌号, "\0"则表示查询任意车牌号
        public int              nSpeedUpperLimit;                     // 查询的车速范围; 速度上限 单位: km/h
        public int              nSpeedLowerLimit;                     // 查询的车速范围; 速度下限 单位: km/h
        public int              bSpeedLimit;                          // 是否按速度查询; TRUE:按速度查询,nSpeedUpperLimit和nSpeedLowerLimit有效。
        public int              dwBreakingRule;                       // 违章类型：
        // 当事件类型为 EVENT_IVS_TRAFFICGATE时
        //        第一位:逆行;  第二位:压线行驶; 第三位:超速行驶;
        //        第四位：欠速行驶; 第五位:闯红灯;
        // 当事件类型为 EVENT_IVS_TRAFFICJUNCTION
        //        第一位:闯红灯;  第二位:不按规定车道行驶;
        //        第三位:逆行; 第四位：违章掉头;
        //        第五位:压线行驶;
        public byte[]           szPlateType = new byte[32];           // 车牌类型,"Unknown" 未知,"Normal" 蓝牌黑牌,"Yellow" 黄牌,"DoubleYellow" 双层黄尾牌,"Police" 警牌 ,
        // "SAR" 港澳特区号牌,"Trainning" 教练车号牌
        // "Personal" 个性号牌,"Agri" 农用牌,"Embassy" 使馆号牌,"Moto" 摩托车号牌,"Tractor" 拖拉机号牌,"Other" 其他号牌
        public byte[]           szPlateColor = new byte[16];          // 车牌颜色, "Blue"蓝色,"Yellow"黄色, "White"白色,"Black"黑色
        public byte[]           szVehicleColor = new byte[16];        // 车身颜色:"White"白色, "Black"黑色, "Red"红色, "Yellow"黄色, "Gray"灰色, "Blue"蓝色,"Green"绿色
        public byte[]           szVehicleSize = new byte[16];         // 车辆大小类型:"Light-duty":小型车;"Medium":中型车; "Oversize":大型车; "Unknown": 未知
        public int              nGroupID;                             // 事件组编号(此值>=0时有效)
        public short            byLane;                               // 车道号(此值>=0时表示具体车道,-1表示所有车道,即不下发此字段)
        public byte             byFileFlag;                           // 文件标志, 0xFF-使用nFileFlagEx, 0-表示所有录像, 1-定时文件, 2-手动文件, 3-事件文件, 4-重要文件, 5-合成文件
        public byte             byRandomAccess;                       // 是否需要在查询过程中随意跳转,0-不需要,1-需要
        public int              nFileFlagEx;                          // 文件标志, 按位表示: bit0-定时文件, bit1-手动文件, bit2-事件文件, bit3-重要文件, bit4-合成文件, bit5-禁止名单图片 0xFFFFFFFF-所有录像
        public int              nDirection;                           // 车道方向（车开往的方向）    0-北 1-东北 2-东 3-东南 4-南 5-西南 6-西 7-西北 8-未知 -1-所有方向
        public Pointer          szDirs;                               // 工作目录列表,一次可查询多个目录,为空表示查询所有目录。目录之间以分号分隔,如“/mnt/dvr/sda0;/mnt/dvr/sda1”,szDirs==null 或"" 表示查询所有
        public Pointer          pEventTypes;                          // 待查询的事件类型数组指针,事件类型,详见"智能分析事件类型",若为NULL则认为查询所有事件（缓冲需由用户申请）
        public int              nEventTypeNum;                        // 事件类型数组大小
        public Pointer          pszDeviceAddress;                     // 设备地址, NULL表示该字段不起作用
        public Pointer          pszMachineAddress;                    // 机器部署地点, NULL表示该字段不起作用
        public Pointer          pszVehicleSign;                       // 车辆标识, 例如 "Unknown"-未知, "Audi"-奥迪, "Honda"-本田... NULL表示该字段不起作用
        public short            wVehicleSubBrand;                     // 车辆子品牌 需要通过映射表得到真正的子品牌 映射表详见开发手册
        public short            wVehicleYearModel;                    // 车辆品牌年款 需要通过映射表得到真正的年款 映射表详见开发手册
        public int              emSafeBeltState;                      // 安全带状态, 参考枚举  EM_SAFE_BELT_STATE
        public int              emCallingState;                       // 打电话状态, 参考枚举 EM_CALLING_STATE
        public int              emAttachMentType;                     // 车内饰品类型, 参考枚举 EM_ATTACHMENT_TYPE
        public int              emCarType;                            // 车辆类型, 参考枚举  EM_CATEGORY_TYPE
        public Pointer          pstuTrafficCarParamEx;                // 参数扩展,对应结构体NET_MEDIA_QUERY_TRAFFICCAR_PARAM_EX
        public int[]            bReserved = new int[4];               // 保留字段
    }

    // NET_MEDIA_QUERY_TRAFFICCAR_EX对应的查询条件
    public static class MEDIA_QUERY_TRAFFICCAR_PARAM_EX extends SdkStructure
    {
        public int              dwSize;
        public MEDIA_QUERY_TRAFFICCAR_PARAM stuParam;                 // 基本查询参数
        public int[]            nChannels = new int[256];             //通道号数组
        public int              nChannelsNum;                         //通道号数组有效个数

        public MEDIA_QUERY_TRAFFICCAR_PARAM_EX() {
            this.dwSize = this.size();
        }
    }

    // NET_MEDIA_QUERY_TRAFFICCAR查询出来的media文件信息
    public static class MEDIAFILE_TRAFFICCAR_INFO extends SdkStructure
    {
        public int              ch;                                   // 通道号
        public byte[]           szFilePath = new byte[128];           // 文件路径
        public int              size;                                 // 文件长度
        public NET_TIME         starttime;                            // 开始时间
        public NET_TIME         endtime;                              // 结束时间
        public int              nWorkDirSN;                           // 工作目录编号
        public byte             nFileType;                            // 文件类型  1：jpg图片
        public byte             bHint;                                // 文件定位索引
        public byte             bDriveNo;                             // 磁盘号
        public byte             bReserved2;
        public int              nCluster;                             // 簇号
        public byte             byPictureType;                        // 图片类型, 0-普通, 1-合成, 2-抠图
        public byte[]           bReserved = new byte[3];              // 保留字段
        //以下是交通车辆信息
        public byte[]           szPlateNumber = new byte[32];         // 车牌号码
        public byte[]           szPlateType = new byte[32];           // 号牌类型"Unknown" 未知; "Normal" 蓝牌黑牌; "Yellow" 黄牌; "DoubleYellow" 双层黄尾牌
        // "Police" 警牌;
        // "SAR" 港澳特区号牌; "Trainning" 教练车号牌; "Personal" 个性号牌; "Agri" 农用牌
        // "Embassy" 使馆号牌; "Moto" 摩托车号牌; "Tractor" 拖拉机号牌; "Other" 其他号牌
        public byte[]           szPlateColor = new byte[16];          // 车牌颜色:"Blue","Yellow", "White","Black"
        public byte[]           szVehicleColor = new byte[16];        // 车身颜色:"White", "Black", "Red", "Yellow", "Gray", "Blue","Green"
        public int              nSpeed;                               // 车速,单位 Km/H
        public int              nEventsNum;                           // 关联的事件个数
        public int[]            nEvents = new int[32];                // 关联的事件列表,数组值表示相应的事件,详见"智能分析事件类型"
        public int              dwBreakingRule;                       // 具体违章类型掩码,第一位:闯红灯; 第二位:不按规定车道行驶;
        // 第三位:逆行; 第四位：违章掉头;否则默认为:交通路口事件
        public byte[]           szVehicleSize = new byte[16];         // 车辆大小类型:"Light-duty":小型车;"Medium":中型车; "Oversize":大型车
        public byte[]           szChannelName = new byte[NET_CHAN_NAME_LEN]; // 本地或远程的通道名称
        public byte[]           szMachineName = new byte[NET_MAX_NAME_LEN]; // 本地或远程设备名称
        public int              nSpeedUpperLimit;                     // 速度上限 单位: km/h
        public int              nSpeedLowerLimit;                     // 速度下限 单位: km/h
        public int              nGroupID;                             // 事件里的组编号
        public byte             byCountInGroup;                       // 一个事件组内的抓拍张数
        public byte             byIndexInGroup;                       // 一个事件组内的抓拍序号
        public byte             byLane;                               // 车道,参见MEDIA_QUERY_TRAFFICCAR_PARAM
        public byte[]           bReserved1 = new byte[21];            // 保留
        public NET_TIME         stSnapTime;                           // 抓拍时间
        public int              nDirection;                           // 车道方向,参见MEDIA_QUERY_TRAFFICCAR_PARAM
        public byte[]           szMachineAddress = new byte[MAX_PATH]; // 机器部署地点
        public long             sizeEx;                               // 文件长度扩展，支持文件长度大于4G，单位字节
    }

    // NET_MEDIA_QUERY_TRAFFICCAR_EX查询出来的文件信息
    public static class MEDIAFILE_TRAFFICCAR_INFO_EX extends SdkStructure
    {
        public int              dwSize;
        /**
         * 基本信息
         */
        public MEDIAFILE_TRAFFICCAR_INFO stuInfo = new MEDIAFILE_TRAFFICCAR_INFO();
        /**
         * 设备地址
         */
        public byte[]           szDeviceAddr = new byte[256];
        /**
         * 车辆标识, 例如 "Unknown"-未知, "Audi"-奥迪, "Honda"-本田...
         */
        public byte[]           szVehicleSign = new byte[32];
        /**
         * 自定义车位号（停车场用）
         */
        public byte[]           szCustomParkNo = new byte[64];
        /**
         * 车辆子品牌，需要通过映射表得到真正的子品牌
         */
        public short            wVehicleSubBrand;
        /**
         * 车辆年款，需要通过映射表得到真正的年款
         */
        public short            wVehicleYearModel;
        /**
         * 对应电子车牌标签信息中的过车时间(ThroughTime)
         */
        public NET_TIME         stuEleTagInfoUTC = new NET_TIME();
        /**
         * 录像或抓图文件标志
         */
        public int[]            emFalgLists = new int[EM_RECORD_SNAP_FLAG_TYPE.FLAG_TYPE_MAX];
        /**
         * 标志总数
         */
        public int              nFalgCount;
        /**
         * 安全带状态 {@link EM_SAFE_BELT_STATE}
         */
        public int              emSafeBelSate;
        /**
         * 打电话状态 {@link EM_CALLING_STATE}
         */
        public int              emCallingState;
        /**
         * 车内物品个数
         */
        public int              nAttachMentNum;
        /**
         * 车内物品信息
         */
        public NET_ATTACH_MENET_INFO[] stuAttachMent = new NET_ATTACH_MENET_INFO[8];
        /**
         * 车牌所属国家和地区
         */
        public byte[]           szCountry = new byte[32];
        /**
         * 车辆类型 {@link EM_CATEGORY_TYPE}
         */
        public int              emCarType;
        /**
         * 遮阳板状态 {@link NET_SUNSHADE_STATE}
         */
        public int              emSunShadeState;
        /**
         * 是否抽烟 {@link EM_SMOKING_STATE}
         */
        public int              emSmokingState;
        /**
         * 年检标个数
         */
        public int              nAnnualInspection;
        /**
         * 字节对齐
         */
        public byte[]           byReserved = new byte[4];
        /**
         * PictureID高四字节
         */
        public int              nPicIDHigh;
        /**
         * PictureID低四字节
         */
        public int              nPicIDLow;
        /**
         * 平台客户端1上传信息
         */
        public NET_UPLOAD_CLIENT_INFO stuClient1 = new NET_UPLOAD_CLIENT_INFO();
        /**
         * 平台客户端2上传信息
         */
        public NET_UPLOAD_CLIENT_INFO stuClient2 = new NET_UPLOAD_CLIENT_INFO();
        /**
         * 三地车牌
         */
        public byte[]           szExtraPlateNumber = new byte[3 * 32];
        /**
         * 车牌个数
         */
        public int              nExtraPlateNumberNum;
        /**
         * 车辆进站时间，时间格式：UTC时间(IVSS, 用于加油站场景)
         */
        public int              nEntranceTime;
        /**
         * 车辆加油时间，时间格式：UTC时间(IVSS, 用于加油站场景)
         */
        public int              nOilTime;
        /**
         * 车辆出站时间，时间格式：UTC时间(IVSS, 用于加油站场景)
         */
        public int              nExitTime;
        /**
         * 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅starttime和endtime有效(仅使用starttime和endtime, starttime和endtime在MEDIAFILE_TRAFFICCAR_INFO中)
         */
        public int              bRealUTC;
        /**
         * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用
         */
        public NET_TIME         stuStartTimeRealUTC = new NET_TIME();
        /**
         * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用
         */
        public NET_TIME         stuEndTimeRealUTC = new NET_TIME();
        /**
         * 字节补齐
         */
        public byte[]           byReserved1 = new byte[784];

        public MEDIAFILE_TRAFFICCAR_INFO_EX() {
            for (int i = 0; i < stuAttachMent.length; i++) {
                stuAttachMent[i] = new NET_ATTACH_MENET_INFO();
            }
            this.dwSize = this.size();
        }
    }

    // 车内饰品信息
    public static class NET_ATTACH_MENET_INFO extends SdkStructure
    {
        public int              emAttachMentType;                     // 车内物品类型, 对应枚举  EM_ATTACHMENT_TYPE
        public byte[]           bReserved1 = new byte[128];           // 保留字节
    }

    // 安全带状态
    public static class EM_SAFE_BELT_STATE extends SdkStructure
    {
        public static final int   EM_SAFE_BELT_UNKNOWN = 0;             // 未知
        public static final int   EM_SAFE_BELT_OTHER = 1;               // 未识别
        public static final int   EM_SAFE_BELT_WITH = 2;                // 有安全带
        public static final int   EM_SAFE_BELT_WITHOUT = 3;             // 无安全带
    }

    // 车内饰品类型
    public static class EM_ATTACHMENT_TYPE extends SdkStructure
    {
        public static final int   EM_ATTACHMENT_UNKNOWN = 0;            // 未知
        public static final int   EM_ATTACHMENT_OTHER = 1;              // 其他类型
        public static final int   EM_ATTACHMENT_FURNITURE = 2;          // 摆件
        public static final int   EM_ATTACHMENT_PENDANT = 3;            // 挂件
        public static final int   EM_ATTACHMENT_TISSUEBOX = 4;          // 纸巾盒
        public static final int   EM_ATTACHMENT_DANGER = 5;             // 危险品
        public static final int   EM_ATTACHMENT_PERFUMEBOX = 6;         // 香水
    }

    // 打电话状态
    public static class EM_CALLING_STATE extends SdkStructure
    {
        public static final int   EM_CALLING_UNKNOWN = 0;               // 未知
        public static final int   EM_CALLING_OTHER = 1;                 // 未识别
        public static final int   EM_CALLING_NO = 2;                    // 未打电话
        public static final int   EM_CALLING_YES = 3;                   // 打电话
    }

    // 车辆类型
    public static class EM_CATEGORY_TYPE extends SdkStructure
    {
        public static final int   EM_CATEGORY_UNKNOWN = 0;              // 未知
        public static final int   EM_CATEGORY_OTHER = 1;                // 其他
        public static final int   EM_CATEGORY_MOTOR = 2;                // 机动车
        public static final int   EM_CATEGORY_BUS = 3;                  // 公交车
        public static final int   EM_CATEGORY_UNLICENSED_MOTOR = 4;     // 无牌机动车
        public static final int   EM_CATEGORY_LARGE_CAR = 5;            // 大型汽车
        public static final int   EM_CATEGORY_MICRO_CAR = 6;            // 小型汽车
        public static final int   EM_CATEGORY_EMBASSY_CAR = 7;          // 使馆汽车
        public static final int   EM_CATEGORY_MARGINAL_CAR = 8;         // 领馆汽车
        public static final int   EM_CATEGORY_AREAOUT_CAR = 9;          // 境外汽车
        public static final int   EM_CATEGORY_FOREIGN_CAR = 10;         // 外籍汽车
        public static final int   EM_CATEGORY_FARMTRANSMIT_CAR = 11;    // 农用运输车
        public static final int   EM_CATEGORY_TRACTOR = 12;             // 拖拉机
        public static final int   EM_CATEGORY_TRAILER = 13;             // 挂车
        public static final int   EM_CATEGORY_COACH_CAR = 14;           // 教练汽车
        public static final int   EM_CATEGORY_TRIAL_CAR = 15;           // 试验汽车
        public static final int   EM_CATEGORY_TEMPORARY_ENTRY_CAR = 16; // 临时入境汽车
        public static final int   EM_CATEGORY_TEMPORARY_ENTRY_MOTORCYCLE = 17; // 临时入境摩托
        public static final int   EM_CATEGORY_TEMPORARY_STEER_CAR = 18; // 临时行驶车
        public static final int   EM_CATEGORY_LARGE_TRUCK = 19;         // 大货车
        public static final int   EM_CATEGORY_MID_TRUCK = 20;           // 中货车
        public static final int   EM_CATEGORY_MICRO_TRUCK = 21;         // 小货车
        public static final int   EM_CATEGORY_MICROBUS = 22;            // 面包车
        public static final int   EM_CATEGORY_SALOON_CAR = 23;          // 轿车
        public static final int   EM_CATEGORY_CARRIAGE = 24;            // 小轿车
        public static final int   EM_CATEGORY_MINI_CARRIAGE = 25;       // 微型轿车
        public static final int   EM_CATEGORY_SUV_MPV = 26;             // SUV或者MPV
        public static final int   EM_CATEGORY_SUV = 27;                 // SUV
        public static final int   EM_CATEGORY_MPV = 28;                 // MPV
        public static final int   EM_CATEGORY_PASSENGER_CAR = 29;       // 客车
        public static final int   EM_CATEGORY_MOTOR_BUS = 30;           // 大客
        public static final int   EM_CATEGORY_MID_PASSENGER_CAR = 31;   // 中客车
        public static final int   EM_CATEGORY_MINI_BUS = 32;            // 小客车
        public static final int   EM_CATEGORY_PICKUP = 33;              // 皮卡车
        public static final int   EM_CATEGORY_OILTANK_TRUCK = 34;       // 油罐车
        public static final int   EM_CATEGORY_TANK_CAR = 35;            // 危化品车辆
        public static final int   EM_CATEGORY_SLOT_TANK_CAR = 36;       // 槽罐车
        public static final int   EM_CATEGORY_DREGS_CAR = 37;           // 渣土车
        public static final int   EM_CATEGORY_CONCRETE_MIXER_TRUCK = 38; // 混凝土搅拌车
        public static final int   EM_CATEGORY_TAXI = 39;                // 出租车
        public static final int   EM_CATEGORY_POLICE = 40;              // 警车
        public static final int   EM_CATEGORY_AMBULANCE = 41;           // 救护车
        public static final int   EM_CATEGORY_GENERAL = 42;             // 普通车
        public static final int   EM_CATEGORY_WATERING_CAR = 43;        // 洒水车
        public static final int   EM_CATEGORY_FIRE_ENGINE = 44;         // 消防车
        public static final int   EM_CATEGORY_MACHINE_TRUCK = 45;       // 工程车
        public static final int   EM_CATEGORY_POWER_LOT_VEHICLE = 46;   // 粉粒物料车
        public static final int   EM_CATEGORY_SUCTION_SEWAGE_TRUCK = 47; // 吸污车
        public static final int   EM_CATEGORY_NORMAL_TANK_TRUCK = 48;   // 普通罐车
    }

    // 是否抽烟
    public static class EM_SMOKING_STATE
    {
        public static final int   EM_SMOKING_UNKNOWN = 0;               // 未知
        public static final int   EM_SMOKING_NO = 1;                    // 未抽烟
        public static final int   EM_SMOKING_YES = 2;                   // 抽烟
    }

    // 对应C++头文件DH_PIC_INFO_EX
    public static class NET_PIC_INFO_EX extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public int              dwFileLenth;                          //文件大小,单位:字节
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径

        public NET_PIC_INFO_EX()
        {
            this.dwSize = this.size();
        }
    }

    //区域；各边距按整长8192的比例
    public static class NET_RECT extends SdkStructure
    {
        public int              left;
        public int              top;
        public int              right;
        public int              bottom;

        public String toString() {
            return "[" + left + " " + top + " " + right + " " + bottom + "]";
        }
    }

    // 时间段结构
    public static class NET_TSECT extends SdkStructure
    {
        public int              bEnable;                              // 当表示录像时间段时,按位表示四个使能,从低位到高位分别表示动检录象、报警录象、普通录象、动检和报警同时发生才录像
                                               // 当表示布撤防时间段时, 表示使能
                                               // 当表示推送时间段时, 表示使能：1表示使能，0表示非使能
        public int              iBeginHour;
        public int              iBeginMin;
        public int              iBeginSec;
        public int              iEndHour;
        public int              iEndMin;
        public int              iEndSec;

        public String startTime() {
            return iBeginHour + ":" + iBeginMin + ":" + iBeginSec;
        }

        public String endTime() {
            return iEndHour + ":" + iEndMin + ":" + iEndSec;
        }
    }

    public static class DH_RECT extends SdkStructure
    {
        public NativeLong       left;
        public NativeLong       top;
        public NativeLong       right;
        public NativeLong       bottom;
    }

    //二维空间点
    public static class NET_POINT extends SdkStructure
    {
        public short            nx;
        public short            ny;

        @Override
        public String toString() {
            return "NET_POINT{" +
                    "nx=" + nx +
                    ", ny=" + ny +
                    '}';
        }

        public NET_POINT() {
        }
    }

    //二维空间点
    public static class DH_POINT extends SdkStructure
    {
        public short            nx;
        public short            ny;

        public DH_POINT(){}

        public DH_POINT(short x, short y){
            this.nx = x;
            this.ny = y;
        }
    }

    public static class NET_CANDIDAT_PIC_PATHS extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public int              nFileCount;                           //实际文件个数
        public NET_PIC_INFO_EX[] stFiles = (NET_PIC_INFO_EX[])new NET_PIC_INFO_EX().toArray(NET_MAX_PERSON_IMAGE_NUM); //文件信息

        public NET_CANDIDAT_PIC_PATHS()
        {
            this.dwSize = this.size();
        }

        @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }
    }

    //颜色类型
    public static class EM_COLOR_TYPE extends SdkStructure
    {
        public static final int   NET_COLOR_TYPE_RED = 0;               //红色
        public static final int   NET_COLOR_TYPE_YELLOW = 1;            //黄色
        public static final int   NET_COLOR_TYPE_GREEN = 2;             //绿色
        public static final int   NET_COLOR_TYPE_CYAN = 3;              //青色
        public static final int   NET_COLOR_TYPE_BLUE = 4;              //蓝色
        public static final int   NET_COLOR_TYPE_PURPLE = 5;            //紫色
        public static final int   NET_COLOR_TYPE_BLACK = 6;             //黑色
        public static final int   NET_COLOR_TYPE_WHITE = 7;             //白色
        public static final int   NET_COLOR_TYPE_MAX = 8;
    }

    //视频分析物体信息结构体
    public static class NET_MSG_OBJECT extends SdkStructure
    {
        public int              nObjectID;                            //物体ID,每个ID表示一个唯一的物体
        public byte[]           szObjectType = new byte[128];         //物体类型
        public int              nConfidence;                          //置信度(0~255),值越大表示置信度越高
        public int              nAction;                              //物体动作:1:Appear2:Move3:Stay
        public DH_RECT          BoundingBox = new DH_RECT();          //包围盒
        public NET_POINT        Center = new NET_POINT();             //物体型心
        public int              nPolygonNum;                          //多边形顶点个数
        public NET_POINT[]      Contour = new NET_POINT[NET_MAX_POLYGON_NUM]; //较精确的轮廓多边形
        public int              rgbaMainColor;                        //表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[]           szText = new byte[128];               // 物体上相关的带0结束符文本,比如车牌,集装箱号等等
        // "ObjectType"为"Vehicle"或者"Logo"时（尽量使用Logo。Vehicle是为了兼容老产品）表示车标,支持：
        // "Unknown"未知
        // "Audi" 奥迪
        // "Honda" 本田
        // "Buick" 别克
        // "Volkswagen" 大众
        // "Toyota" 丰田
        // "BMW" 宝马
        // "Peugeot" 标致
        // "Ford" 福特
        // "Mazda" 马自达
        // "Nissan" 尼桑
        // "Hyundai" 现代
        // "Suzuki" 铃木
        // "Citroen" 雪铁龙
        // "Benz" 奔驰
        // "BYD" 比亚迪
        // "Geely" 吉利
        // "Lexus" 雷克萨斯
        // "Chevrolet" 雪佛兰
        // "Chery" 奇瑞
        // "Kia" 起亚
        // "Charade" 夏利
        // "DF" 东风
        // "Naveco" 依维柯
        // "SGMW" 五菱
        // "Jinbei" 金杯
        // "JAC" 江淮
        // "Emgrand" 帝豪
        // "ChangAn" 长安
        // "Great Wall" 长城
        // "Skoda" 斯柯达
        // "BaoJun" 宝骏
        // "Subaru" 斯巴鲁
        // "LandWind" 陆风
        // "Luxgen" 纳智捷
        // "Renault" 雷诺
        // "Mitsubishi" 三菱
        // "Roewe" 荣威
        // "Cadillac" 凯迪拉克
        // "MG" 名爵
        // "Zotye" 众泰
        // "ZhongHua" 中华
        // "Foton" 福田
        // "SongHuaJiang" 松花江
        // "Opel" 欧宝
        // "HongQi" 一汽红旗
        // "Fiat" 菲亚特
        // "Jaguar" 捷豹
        // "Volvo" 沃尔沃
        // "Acura" 讴歌
        // "Porsche" 保时捷
        // "Jeep" 吉普
        // "Bentley" 宾利
        // "Bugatti" 布加迪
        // "ChuanQi" 传祺
        // "Daewoo" 大宇
        // "DongNan" 东南
        // "Ferrari" 法拉利
        // "Fudi" 福迪
        // "Huapu" 华普
        // "HawTai" 华泰
        // "JMC" 江铃
        // "JingLong" 金龙客车
        // "JoyLong" 九龙
        // "Karry" 开瑞
        // "Chrysler" 克莱斯勒
        // "Lamborghini" 兰博基尼
        // "RollsRoyce" 劳斯莱斯
        // "Linian" 理念
        // "LiFan" 力帆
        // "LieBao" 猎豹
        // "Lincoln" 林肯
        // "LandRover" 路虎
        // "Lotus" 路特斯
        // "Maserati" 玛莎拉蒂
        // "Maybach" 迈巴赫
        // "Mclaren" 迈凯轮
        // "Youngman" 青年客车
        // "Tesla" 特斯拉
        // "Rely" 威麟
        // "Lsuzu" 五十铃
        // "Yiqi" 一汽
        // "Infiniti" 英菲尼迪
        // "YuTong" 宇通客车
        // "AnKai" 安凯客车
        // "Canghe" 昌河
        // "HaiMa" 海马
        // "Crown" 丰田皇冠
        // "HuangHai" 黄海
        // "JinLv" 金旅客车
        // "JinNing" 精灵
        // "KuBo" 酷博
        // "Europestar" 莲花
        // "MINI" 迷你
        // "Gleagle" 全球鹰
        // "ShiDai" 时代
        // "ShuangHuan" 双环
        // "TianYe" 田野
        // "WeiZi" 威姿
        // "Englon" 英伦
        // "ZhongTong" 中通客车
        // "Changan" 长安轿车
        // "Yuejin" 跃进
        // "Taurus" 金牛星
        // "Alto" 奥拓
        // "Weiwang" 威旺
        // "Chenglong" 乘龙
        // "Haige" 海格
        // "Shaolin" 少林客车
        // "Beifang" 北方客车
        // "Beijing" 北京汽车
        // "Hafu" 哈弗
        public byte[]           szObjectSubType = new byte[62];       // 物体子类别,根据不同的物体类型,可以取以下子类型：
        // Vehicle Category:"Unknown"  未知,"Motor" 机动车,"Non-Motor":非机动车,"Bus": 公交车,"Bicycle" 自行车,"Motorcycle":摩托车,"PassengerCar":客车,
        // "LargeTruck":大货车,    "MidTruck":中货车,"SaloonCar":轿车,"Microbus":面包车,"MicroTruck":小货车,"Tricycle":三轮车,    "Passerby":行人
        // Plate Category："Unknown" 未知,"Normal" 蓝牌黑牌,"Yellow" 黄牌,"DoubleYellow" 双层黄尾牌,"Police" 警牌,
        // "SAR" 港澳特区号牌,"Trainning" 教练车号牌
        // "Personal" 个性号牌,"Agri" 农用牌,"Embassy" 使馆号牌,"Moto" 摩托车号牌,"Tractor" 拖拉机号牌,"Other" 其他号牌
        // HumanFace Category:"Normal" 普通人脸,"HideEye" 眼部遮挡,"HideNose" 鼻子遮挡,"HideMouth" 嘴部遮挡
        public short            wColorLogoIndex;                      // 车标索引
        public short            wSubBrand;                            // 车辆子品牌 需要通过映射表得到真正的子品牌 映射表详见开发手册
        public byte             byReserved1;
        public byte             bPicEnble;                            //是否有物体对应图片文件信息, 类型小bool, 取值0或者1
        public NET_PIC_INFO     stPicInfo = new NET_PIC_INFO();       //物体对应图片信息
        public byte             bShotFrame;                           //是否是抓拍张的识别结果,  类型小bool, 取值0或者1
        public byte             bColor;                               //物体颜色(rgbaMainColor)是否可用, 类型小bool, 取值0或者1
        public byte             byReserved2;
        public byte             byTimeType;                           //时间表示类型,详见 EM_TIME_TYPE 说明
        public NET_TIME_EX      stuCurrentTime = new NET_TIME_EX();   //针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX      stuStartTime = new NET_TIME_EX();     //开始时间戳（物体开始出现时）
        public NET_TIME_EX      stuEndTime = new NET_TIME_EX();       //结束时间戳（物体最后出现时）
        public DH_RECT          stuOriginalBoundingBox = new DH_RECT(); //包围盒(绝对坐标)
        public DH_RECT          stuSignBoundingBox = new DH_RECT();   //车标坐标包围盒
        public int              dwCurrentSequence;                    //当前帧序号（抓下这个物体时的帧）
        public int              dwBeginSequence;                      //开始帧序号（物体开始出现时的帧序号）
        public int              dwEndSequence;                        //结束帧序号（物体消逝时的帧序号）
        public long             nBeginFileOffse;                      //开始时文件偏移,单位:字（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long             nEndFileOffset;                       //结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[]           byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见 EM_COLOR_TYPE
        public byte[]           byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //上半身物体颜色相似度(物体类型为人时有效)
        public byte[]           byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //下半身物体颜色相似度(物体类型为人时有效)
        public int              nRelativeID;                          //相关物体ID
        public byte[]           szSubText = new byte[20];             //"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public short            wBrandYear;                           // 车辆品牌年款 需要通过映射表得到真正的年款 映射表详见开发手册

        protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
            int alignment = super.getNativeAlignment(type, value, isFirstElement);
            return Math.min(4, alignment);
        }

        public NET_MSG_OBJECT(){

        for(int i=0;i<Contour.length;i++){
            Contour[i]=new NET_POINT();
        }
        }
    }

    // 物体截图信息
    public static class IMAGE_INFO extends SdkStructure
    {
        public int              nLength;                              // 图片大小,单位:字节
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public byte[]           szFilePath = new byte[260];           // 文件路径
        public byte[]           byReserved = new byte[512];           // 预留字节
    }

    // 目标人脸信息
    public static class FACE_INFO_OBJECT extends SdkStructure
    {
        public IMAGE_INFO       stuImageInfo;                         // 物体截图信息
        public int              emSex;                                // 性别类型
        public int              nAge;                                 // 年龄
        public int              emGlasses;                            // 是否戴眼镜
        public int              emEmotion;                            // 人脸表情
        public byte[]           szReserved = new byte[4];
        public int              emEye;                                // 眼睛状态
        public int              emMouth;                              // 嘴巴状态
        public int              emMask;                               // 是否带口罩
        public int              emBeard;                              // 是否有胡子
        public int              nAttractive;                          // 魅力值,0表示未识别,识别时范围1-100,得分高魅力高
        public NET_EULER_ANGLE  stuFaceCaptureAngle;                  // 人脸在抓拍图片中的角度信息, nPitch:抬头低头的俯仰角, nYaw左右转头的偏航角, nRoll头在平面内左偏右偏的翻滚角
        // 角度值取值范围[-90,90], 三个角度值都为999表示此角度信息无效
        public int              nFaceQuality;                         // 人脸抓拍质量分数
        public double           fMaxTemp;                             // 温度信息
        public int              nIsOverTemp;                          // 是否温度异常
        public int              nIsUnderTemp;                         // 是否温度异常
        public int              emTempUnit;                           // 温度单位 EM_TEMPERATURE_UNIT
        public byte[]           byReserved = new byte[2012];          // 保留字段
    }

    public static class EM_TEMPERATURE_UNIT extends SdkStructure
    {
        public final static int   EM_TEMPERATURE_CENTIGRADE = 0;        // 摄氏度
        public final static int   EM_TEMPERATURE_FAHRENHEIT = 1;        // 华氏度
        public final static int   EM_TEMPERATURE_KELVIN = 2;            // 开尔文
    }

    // NET_FILE_QUERY_FACE 对应的目标识别服务FINDNEXT查询返回参数
    public static class MEDIAFILE_FACERECOGNITION_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bGlobalScenePic;                      // 全景图是否存在, BOOL类型，取值0或1
        public NET_PIC_INFO_EX  stGlobalScenePic;                     // 全景图片文件路径
        public NET_MSG_OBJECT   stuObject;                            // 目标人脸物体信息
        public NET_PIC_INFO_EX  stObjectPic;                          // 目标人脸文件路径
        public int              nCandidateNum;                        // 当前人脸匹配到的候选对象数量
        public CANDIDATE_INFO[] stuCandidates = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(NET_MAX_CANDIDATE_NUM); //当前人脸匹配到的候选对象信息
        public NET_CANDIDAT_PIC_PATHS[] stuCandidatesPic = (NET_CANDIDAT_PIC_PATHS[])new NET_CANDIDAT_PIC_PATHS().toArray(NET_MAX_CANDIDATE_NUM); // 当前人脸匹配到的候选对象图片文件路径
        public NET_TIME         stTime;                               // 报警发生时间
        public byte[]           szAddress = new byte[MAX_PATH];       // 报警发生地点
        public int              nChannelId;                           // 通道号
        public int              bUseCandidatesEx;                     // 是否使用候选对象扩展结构体, 1-true, 0-false
        // 若为TRUE, 则表示使用stuCandidatesEx, 且stuCandidates无效, 否则相反
        public int              nCandidateExNum;                      // 当前人脸匹配到的候选对象(扩展结构体) 数量
        public CANDIDATE_INFOEX[] stuCandidatesEx = (CANDIDATE_INFOEX[])new CANDIDATE_INFOEX().toArray(NET_MAX_CANDIDATE_NUM); // 当前人脸匹配到的候选对象信息, 实际返回个数同nCandidateNum
        public FACE_INFO_OBJECT stuFaceInfoObject;                    // 目标人脸信息
        public NET_POINT        stuFaceCenter;                        // 人脸型心(不是包围盒中心), 0-8191相对坐标, 相对于小图
        public NET_MEDIAFILE_GENERAL_INFO stuGeneralInfo;             // 通用信息
        public int              nRecNo;                               // 数据库记录号
        public int              bRealUTC;                             // 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示stuStartTimeRealUTC和stuEndTimeRealUTC无效
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用

        public MEDIAFILE_FACERECOGNITION_INFO()
        {
            this.dwSize = this.size();
        }

        @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }
    }

    //每个视频输入通道对应的所有事件规则：缓冲区pRuleBuf填充多个事件规则信息，每个事件规则信息内容为 CFG_RULE_INFO + "事件类型对应的规则配置结构体"。
    public static class CFG_ANALYSERULES_INFO extends SdkStructure
    {
        public int              nRuleCount;                           // 事件规则个数
        public Pointer          pRuleBuf;                             // 每个视频输入通道对应的视频分析事件规则配置缓冲, 对应  CFG_RULE_INFO[]
        public int              nRuleLen;                             // 缓冲大小
    }

    // 规则通用信息
    public static class CFG_RULE_COMM_INFO extends SdkStructure
    {
        public byte             bRuleId;                              // 规则编号
        public int              emClassType;                          // 规则所属的场景, EM_SCENE_TYPE
        public byte[]           bReserved = new byte[512];            // 保留字节
    }

    public static class CFG_RULE_INFO extends SdkStructure
    {
        public int              dwRuleType;                           // 事件类型，详见dhnetsdk.h中"智能分析事件类型"
        public int              nRuleSize;                            // 该事件类型规则配置结构体大小
        public CFG_RULE_COMM_INFO stuRuleCommInfo;                    // 规则通用信息
    }

    // 应用场景, 内容与 EM_SCENE_CLASS_TYPE 一致
    public static class EM_SCENE_TYPE extends SdkStructure
    {
        public static final int   EM_SCENE_UNKNOW = 0;                  // 未知
        public static final int   EM_SCENE_NORMAL = 1;                  // "Normal" 普通场景
        public static final int   EM_SCENE_TRAFFIC = 2;                 // "Traffic" 交通场景
        public static final int   EM_SCENE_TRAFFIC_PATROL = 3;          // "TrafficPatrol" 交通巡视
        public static final int   EM_SCENE_FACEDETECTION = 4;           // "FaceDetection" 目标检测/目标识别
        public static final int   EM_SCENE_ATM = 5;                     // "ATM"
        public static final int   EM_SCENE_INDOOR = 6;                  // "Indoor"  室内行为分析，和普通规则相同，对室内场景的算法优化版
        public static final int   EM_SCENE_FACERECOGNITION = 7;         // "FaceRecognition" 目标识别
        public static final int   EM_SCENE_PS = 8;
        public static final int   EM_SCENE_NUMBERSTAT = 9;              // "NumberStat" 客流量统计
        public static final int   EM_SCENE_HEAT_MAP = 10;               // "HeatMap" 热度图
        public static final int   EM_SCENE_VIDEODIAGNOSIS = 11;         // "VideoDiagnosis" 视频诊断
        public static final int   EM_SCENE_VEHICLEANALYSE = 12;         // "VehicleAnalyse" 车辆特征检测分析
        public static final int   EM_SCENE_COURSERECORD = 13;           // "CourseRecord" 自动录播
        public static final int   EM_SCENE_VEHICLE = 14;                // "Vehicle" 车载场景(车载行业用，不同于智能交通的Traffic)
        public static final int   EM_SCENE_STANDUPDETECTION = 15;       // "StandUpDetection" 起立检测
        public static final int   EM_SCENE_GATE = 16;                   // "Gate" 卡口
        public static final int   EM_SCENE_SDFACEDETECTION = 17;        // "SDFaceDetect"  多预置点目标检测，配置一条规则但可以在不同预置点下生效
        public static final int   EM_SCENE_HEAT_MAP_PLAN = 18;          // "HeatMapPlan" 球机热度图计划
        public static final int   EM_SCENE_NUMBERSTAT_PLAN = 19;        // "NumberStatPlan"	球机客流量统计计划
        public static final int   EM_SCENE_ATMFD = 20;                  // "ATMFD"金融目标检测，包括正常人脸、异常人脸、相邻人脸、头盔人脸等针对ATM场景特殊优化
        public static final int   EM_SCENE_HIGHWAY = 21;                // "Highway" 高速交通事件检测
        public static final int   EM_SCENE_CITY = 22;                   // "City" 城市交通事件检测
        public static final int   EM_SCENE_LETRACK = 23;                // "LeTrack" 民用简易跟踪
        public static final int   EM_SCENE_SCR = 24;                    // "SCR"打靶相机
        public static final int   EM_SCENE_STEREO_VISION = 25;          // "StereoVision"立体视觉(双目)
        public static final int   EM_SCENE_HUMANDETECT = 26;            // "HumanDetect"人体检测
        public static final int   EM_SCENE_FACEANALYSIS = 27;           // "FaceAnalysis" 人脸分析(同时支持目标检测、目标识别、人脸属性等综合型业务)
        public static final int   EM_SCENE_XRAY_DETECTION = 28;         // "XRayDetection" X光检测
        public static final int   EM_SCENE_STEREO_NUMBER = 29;          // "StereoNumber" 双目相机客流量统计
        public static final int   EM_SCENE_CROWD_DISTRI_MAP = 30;       // "CrowdDistriMap" 人群分布图
        public static final int   EM_SCENE_CLASS_OBJECTDETECT = 31;     // "ObjectDetect"目标检测
        public static final int   EM_SCENE_FACEATTRIBUTE = 32;          // "FaceAttribute" IVSS目标检测
        public static final int   EM_SCENE_FACECOMPARE = 33;            // "FaceCompare" IVSS目标识别
        public static final int   EM_SCENE_STEREO_BEHAVIOR = 34;        // "StereoBehavior" 立体行为分析(典型场景ATM舱)
        public static final int   EM_SCENE_INTELLICITYMANAGER = 35;     // "IntelliCityMgr" 智慧城管
        public static final int   EM_SCENE_PROTECTIVECABIN = 36;        // "ProtectiveCabin" 防护舱（ATM舱内）
        public static final int   EM_SCENE_AIRPLANEDETECT = 37;         // "AirplaneDetect" 飞机行为检测
        public static final int   EM_SCENE_CROWDPOSTURE = 38;           // "CrowdPosture" 人群态势（人群分布图服务）
        public static final int   EM_SCENE_PHONECALLDETECT = 39;        // "PhoneCallDetect"  打电话检测
        public static final int   EM_SCENE_SMOKEDETECTION = 40;         // "SmokeDetection"   烟雾检测
        public static final int   EM_SCENE_BOATDETECTION = 41;          // "BoatDetection"	  船只检测
        public static final int   EM_SCENE_SMOKINGDETECT = 42;          // "SmokingDetect"	  吸烟检测
        public static final int   EM_SCENE_WATERMONITOR = 43;           // "WaterMonitor"	  水利监测
        public static final int   EM_SCENE_GENERATEGRAPHDETECTION = 44; // GenerateGraphDetection 生成图规则
        public static final int   EM_SCENE_TRAFFIC_PARK = 45;           // "TrafficPark"		交通停车
        public static final int   EM_SCENE_OPERATEMONITOR = 46;         // "OperateMonitor"     作业检测
        public static final int   EM_SCENE_INTELLI_RETAIL = 47;         // "IntelliRetail" 智慧零售大类
        public static final int   EM_SCENE_CLASSROOM_ANALYSE = 48;      // "ClassroomAnalyse" 教育智慧课堂
        public static final int   EM_SCENE_FEATURE_ABSTRACT = 49;       // "FeatureAbstract" 特征向量提取大类
        public static final int   EM_SCENE_FACEBODY_DETECT = 50;        // "FaceBodyDetect"人体检测
        public static final int   EM_SCENE_FACEBODY_ANALYSE = 51;       // "FaceBodyAnalyse"人体识别
        public static final int   EM_SCENE_VEHICLES_DISTRI = 52;        // "VehiclesDistri"车辆密度
        public static final int   EM_SCENE_INTELLI_BREED = 53;          // "IntelliBreed" 智慧养殖检测
        public static final int   EM_SCENE_INTELLI_PS = 54;
        public static final int   EM_SCENE_ELECTRIC_DETECT = 55;        // "ElectricDetect" 电力检测
        public static final int   EM_SCENE_RADAR_DETECT = 56;           // "RadarDetect" 雷检检测
        public static final int   EM_SCENE_PARKINGSPACE = 57;           // "ParkingSpace" 车位检测大类
        public static final int   EM_SCENE_INTELLI_FINANCE = 58;        // "IntelliFinance" 智慧金融
        public static final int   EM_SCENE_CROWD_ABNORMAL = 59;         // "CrowdAbnormal"
        public static final int   EM_SCENE_ANATOMYTEMP_DETECT = 60;     // "AnatomyTempDetect" 超温检测
        public static final int   EM_SCENE_WEATHER_MONITOR = 61;        // "WeatherMonitor"天气监控
        public static final int   EM_SCENE_ELEVATOR_ACCESS_CONTROL = 62; // "ElevatorAccessControl" 电梯门禁
        public static final int   EM_SCENE_BREAK_RULE_BUILDING = 63;    //  "BreakRuleBuilding"违章建筑
        public static final int   EM_SCENE_PANORAMA_TRAFFIC = 64;       // "PanoramaTraffic"全景交通
        public static final int   EM_SCENE_PORTRAIT_DETECT = 65;        // "PortraitDetect"人像检测
        public static final int   EM_SCENE_CONVEY_OR_BLOCK = 66;        // "ConveyorBlock" 传送带阻塞
        public static final int   EM_SCENE_KITCHEN_ANIMAL = 67;         // "KitchenAnimal" 厨房有害动物检测
        public static final int   EM_SCENE_ALLSEEINGEYE = 68;           // "AllSeeingEye" 万物检测
        public static final int   EM_SCENE_DRIVE = 69;                  // "Drive" 驾驶行为分析
        public static final int   EM_SCENE_DRIVEASSISTANT = 70;         // "DriveAssistant" 高级驾驶辅助系统
        public static final int   EM_SCENE_INCABINMONITOR = 71;         // "InCabinMonitor" 车内驾驶舱监测
        public static final int   EM_SCENE_BLINDSPOTDETECTION = 72;     // "BlindSpotDetection" 盲区检测
        public static final int   EM_SCENE_CONVERYER_BELT = 73;         // "ConveyerBelt" 传送带检测
        public static final int   EM_SCENE_INTELLI_LOGISTICS = 74;      // "IntelliLogistics"  智慧物流
        public static final int   EM_SCENE_SMOKE_FIRE = 75;             // "SmokeFire" 烟火检测
        public static final int   EM_SCENE_OBJECT_MONITOR = 76;         // "ObjectMonitor" 物品监控
        public static final int   EM_SCENE_FIRE_FACILITIES = 77;        // "FireFacilities" 消防设施检测
        public static final int   EM_SCENE_FIRE_CONTROL = 78;           // "IntelliFireControl" 智慧消防
        public static final int   EM_SCENE_INTELLI_PARKING = 79;        // "IntelliParking" 智能停车
        public static final int   EM_SCENE_FINANCE_REGULATION = 80;     // "FinanceRegulation" 金融常规
        public static final int   EM_SCENE_ENERGY = 81;                 // "Energy" 智慧能源
        public static final int   EM_SCENE_FIRE_CONTROL_EX = 82;        // "FireControl" 智慧消防
        public static final int   EM_SCENE_ANIMAL_DETECTION = 83;       // "AnimalDetection" 动物检测
        public static final int   EM_SCENE_FIRE_CONTROL_MONITOR = 84;   // "FireControlMonitor" 火警监控
        public static final int   EM_SCENE_PROTECTIVE_SUIT = 85;        // "ProtectiveSuit" 防护服或工作服检测
        public static final int   EM_SCENE_FISH_MONITOR = 86;           // "FishMonitor" 鱼群监测
        public static final int   EM_SCENE_SHOPTRUCK_DETECT = 87;       // "ShopTruckDetect" 工程车检测
    }

    public static class EM_SCENE_CLASS_TYPE extends SdkStructure
    {
        public static final int   EM_SCENE_CLASS_UNKNOW = 0;            // 未知
        public static final int   EM_SCENE_CLASS_NORMAL = 1;            // "Normal" 普通场景
        public static final int   EM_SCENE_CLASS_TRAFFIC = 2;           // "Traffic" 交通场景
        public static final int   EM_SCENE_CLASS_TRAFFIC_PATROL = 3;    // "TrafficPatrol" 交通巡视
        public static final int   EM_SCENE_CLASS_FACEDETECTION = 4;     // "FaceDetection" 目标检测/目标识别
        public static final int   EM_SCENE_CLASS_ATM = 5;               // "ATM"
        public static final int   EM_SENCE_CLASS_INDOOR = 6;            // "Indoor"  室内行为分析，和普通规则相同，对室内场景的算法优化版
        public static final int   EM_SENCE_CLASS_FACERECOGNITION = 7;   // "FaceRecognition" 目标识别
        public static final int   EM_SENCE_CLASS_PS = 8;
        public static final int   EM_SENCE_CLASS_NUMBERSTAT = 9;        // "NumberStat" 客流量统计
        public static final int   EM_SENCE_CLASS_HEAT_MAP = 10;         // "HeatMap" 热度图
        public static final int   EM_SENCE_CLASS_VIDEODIAGNOSIS = 11;   // "VideoDiagnosis" 视频诊断
        public static final int   EM_SENCE_CLASS_VEHICLEANALYSE = 12;   // "VehicleAnalyse" 车辆特征检测分析
        public static final int   EM_SENCE_CLASS_COURSERECORD = 13;     // "CourseRecord" 自动录播
        public static final int   EM_SENCE_CLASS_VEHICLE = 14;          // "Vehicle" 车载场景(车载行业用，不同于智能交通的Traffic)
        public static final int   EM_SENCE_CLASS_STANDUPDETECTION = 15; // "StandUpDetection" 起立检测
        public static final int   EM_SCENE_CLASS_GATE = 16;             // "Gate" 卡口
        public static final int   EM_SCENE_CLASS_SDFACEDETECTION = 17;  // "SDFaceDetect"  多预置点目标检测，配置一条规则但可以在不同预置点下生效
        public static final int   EM_SCENE_CLASS_HEAT_MAP_PLAN = 18;    // "HeatMapPlan" 球机热度图计划
        public static final int   EM_SCENE_CLASS_NUMBERSTAT_PLAN = 19;  // "NumberStatPlan"	球机客流量统计计划
        public static final int   EM_SCENE_CLASS_ATMFD = 20;            // "ATMFD"金融目标检测，包括正常人脸、异常人脸、相邻人脸、头盔人脸等针对ATM场景特殊优化
        public static final int   EM_SCENE_CLASS_HIGHWAY = 21;          // "Highway" 高速交通事件检测
        public static final int   EM_SCENE_CLASS_CITY = 22;             // "City" 城市交通事件检测
        public static final int   EM_SCENE_CLASS_LETRACK = 23;          // "LeTrack" 民用简易跟踪
        public static final int   EM_SCENE_CLASS_SCR = 24;              // "SCR" 打靶相机
        public static final int   EM_SCENE_CLASS_STEREO_VISION = 25;    // "StereoVision" 立体视觉(双目)
        public static final int   EM_SCENE_CLASS_HUMANDETECT = 26;      // "HumanDetect"人体检测
        public static final int   EM_SCENE_CLASS_FACEANALYSIS = 27;     // "FaceAnalysis" 人脸分析
        public static final int   EM_SCENE_CLASS_XRAY_DETECTION = 28;   // "XRayDetection" X光检测
        public static final int   EM_SCENE_CLASS_STEREO_NUMBER = 29;    // "StereoNumber" 双目相机客流量统计
        public static final int   EM_SCENE_CLASS_CROWDDISTRIMAP = 30;   // "CrowdDistriMap"人群分布图
        public static final int   EM_SCENE_CLASS_OBJECTDETECT = 31;     // "ObjectDetect"目标检测
        public static final int   EM_SCENE_CLASS_FACEATTRIBUTE = 32;    // "FaceAttribute" IVSS目标检测
        public static final int   EM_SCENE_CLASS_FACECOMPARE = 33;      // "FaceCompare" IVSS目标识别
        public static final int   EM_SCENE_CLASS_STEREO_BEHAVIOR = 34;  // "StereoBehavior" 立体行为分析(典型场景ATM舱)
        public static final int   EM_SCENE_CLASS_INTELLICITYMANAGER = 35; // "IntelliCityMgr" 智慧城管
        public static final int   EM_SCENE_CLASS_PROTECTIVECABIN = 36;  // "ProtectiveCabin" 防护舱（ATM舱内）
        public static final int   EM_SCENE_CLASS_AIRPLANEDETECT = 37;   // "AirplaneDetect" 飞机行为检测
        public static final int   EM_SCENE_CLASS_CROWDPOSTURE = 38;     // "CrowdPosture" 人群态势（人群分布图服务）
        public static final int   EM_SCENE_CLASS_PHONECALLDETECT = 39;  // "PhoneCallDetect"  打电话检测
        public static final int   EM_SCENE_CLASS_SMOKEDETECTION = 40;   // "SmokeDetection"   烟雾检测
        public static final int   EM_SCENE_CLASS_BOATDETECTION = 41;    // "BoatDetection"	  船只检测
        public static final int   EM_SCENE_CLASS_SMOKINGDETECT = 42;    // "SmokingDetect"	  吸烟检测
        public static final int   EM_SCENE_CLASS_WATERMONITOR = 43;     // "WaterMonitor"	  水利监测
        public static final int   EM_SCENE_CLASS_GENERATEGRAPHDETECTION = 44; // "GenerateGraphDetection" 生成图规则
        public static final int   EM_SCENE_CLASS_TRAFFIC_PARK = 45;     // "TrafficPark"		  交通停车
        public static final int   EM_SCENE_CLASS_OPERATEMONITOR = 46;   // "OperateMonitor" 作业检测
        public static final int   EM_SCENE_CLASS_INTELLI_RETAIL = 47;   // IntelliRetail" 智慧零售大类
        public static final int   EM_SCENE_CLASS_CLASSROOM_ANALYSE = 48; // "ClassroomAnalyse" 教育智慧课堂
        public static final int   EM_SCENE_CLASS_FEATURE_ABSTRACT = 49; // "FeatureAbstract" 特征向量提取大类
        public static final int   EM_SCENE_CLASS_FACEBODY_DETECT = 50;  // "FaceBodyDetect" 人像检测大类
        public static final int   EM_SCENE_CLASS_FACEBODY_ANALYSE = 51; // "FaceBodyAnalyse"人像识别大类
        public static final int   EM_SCENE_CLASS_VEHICLES_DISTRI = 52;  // "VehiclesDistri" 车辆密度
        public static final int   EM_SCENE_CLASS_INTELLI_BREED = 53;    // "IntelliBreed"智慧养殖检测
        public static final int   EM_SCENE_CLASS_INTELLI_PS = 54;
        public static final int   EM_SCENE_CLASS_ELECTRIC_DETECT = 55;  // "ElectricDetect" 电力检测
        public static final int   EM_SCENE_CLASS_RADAR_DETECT = 56;     // "RadarDetect"雷达检测
        public static final int   EM_SCENE_CLASS_PARKINGSPACE = 57;     // "ParkingSpace" 车位检测大类
        public static final int   EM_SCENE_CLASS_INTELLI_FINANCE = 58;  // "IntelliFinance"智慧金融
        public static final int   EM_SCENE_CLASS_CROWD_ABNORMAL = 59;   // "CrowdAbnormal" 人群异常检测
        public static final int   EM_SCENE_CLASS_ANATOMYTEMP_DETECT = 60; // "AnatomyTempDetect"
        public static final int   EM_CLASS_WEATHER_MONITOR = 61;        // 天气监控 "WeatherMonitor"
        public static final int   EM_SCENE_CLASS_ELEVATOR_ACCESS_CONTROL = 62; // "ElevatorAccessControl" 电梯门禁
        public static final int   EM_SCENE_CLASS_BREAK_RULE_BUILDING = 63; // 	"BreakRuleBuilding"违章建筑
        public static final int   EM_SCENE_CLASS_PANORAMA_TRAFFIC = 64; // "PanoramaTraffic"全景交通
        public static final int   EM_SCENE_CLASS_PORTRAIT_DETECT = 65;  // "PortraitDetect"人像检测
        public static final int   EM_SCENE_CLASS_CONVEY_OR_BLOCK = 66;  // "ConveyorBlock" 传送带阻塞
        public static final int   EM_SCENE_CLASS_KITCHEN_ANIMAL = 67;   // "KitchenAnimal" 厨房有害动物检测
        public static final int   EM_SCENE_CLASS_ALLSEEINGEYE = 68;     // "AllSeeingEye" 万物检测
        public static final int   EM_SCENE_CLASS_DRIVE = 69;            // "Drive" 驾驶行为分析
        public static final int   EM_SCENE_CLASS_DRIVEASSISTANT = 70;   // "DriveAssistant" 高级驾驶辅助系统
        public static final int   EM_SCENE_CLASS_INCABINMONITOR = 71;   // "InCabinMonitor" 车内驾驶舱监测
        public static final int   EM_SCENE_CLASS_BLINDSPOTDETECTION = 72; // "BlindSpotDetection" 盲区检测
        public static final int   EM_SCENE_CLASS_CONVERYER_BELT = 73;   // "ConveyerBelt" 传送带检测
        public static final int   EM_SCENE_CLASS_INTELLI_LOGISTICS = 74; // "IntelliLogistics"  智慧物流
        public static final int   EM_SCENE_CLASS_SMOKE_FIRE = 75;       // "SmokeFire" 烟火检测
        public static final int   EM_SCENE_CLASS_OBJECT_MONITOR = 76;   // "ObjectMonitor" 物品监控
        public static final int   EM_SCENE_CLASS_FIRE_FACILITIES = 77;  // "FireFacilities" 消防设施检测
        public static final int   EM_SCENE_CLASS_FIRE_CONTROL = 78;     //  "IntelliFireControl" 智慧消防
        public static final int   EM_SCENE_CLASS_INTELLI_PARKING = 79;  // "IntelliParking" 智能停车
        public static final int   EM_SCENE_CLASS_FINANCE_REGULATION = 80; // "FinanceRegulation" 金融常规
        public static final int   EM_SCENE_CLASS_ENERGY = 81;           // "Energy" 智慧能源
        public static final int   EM_SCENE_CLASS_FIRE_CONTROL_EX = 82;  // "FireControl" 智慧消防
        public static final int   EM_SCENE_CLASS_ANIMAL_DETECTION = 83; // "AnimalDetection" 动物检测
        public static final int   EM_SCENE_CLASS_FIRE_CONTROL_MONITOR = 84; // "FireControlMonitor" 火警监控
        public static final int   EM_SCENE_CLASS_PROTECTIVE_SUIT = 85;  // "ProtectiveSuit" 防护服或工作服检测
        public static final int   EM_SCENE_CLASS_FISH_MONITOR = 86;     // "FishMonitor" 鱼群监测
        public static final int   EM_SCENE_CLASS_SHOPTRUCK_DETECT = 87; // "ShopTruckDetect"工程车检测
        public static final int   EM_SCENE_CLASS_GENEAL_ATTITUDE = 88;  ///// "GenealAttitude" 姿态检测
        public static final int   EM_SCENE_CLASS_TRAFFIC_MAINTENANCE = 89; ///// "TrafficMaintenance" 交通运维
        public static final int   EM_SCENE_CLASS_TRAFFIC_THROW = 90;    ///// "TrafficThrow" 交通抛洒物品检测
        public static final int   EM_SCENE_CLASS_GROUND_THING = 91;     ///// "GroundThing" 地物检测
        public static final int   EM_SCENE_CLASS_OCR_DETECTION = 92;    ///// "OCRDetection"
    }

    //区域顶点信息
    public static class CFG_POLYGON extends SdkStructure
    {
        public int              nX;                                   //0~8191
        public int              nY;
    }

    //区域信息
    public static class CFG_REGION extends SdkStructure
    {
        public int              nPointNum;
        public CFG_POLYGON[]    stuPolygon = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM);
    }

    public static class CFG_SIZE_Attribute extends Union
    {
        public float            nWidth;                               //宽
        public float            nArea;                                //面积
    }

    //Size
    public static class CFG_SIZE extends SdkStructure
    {
        public CFG_SIZE_Attribute attr = new CFG_SIZE_Attribute();
        public float            nHeight;                              //高
    }

    public static class NET_SIZE extends SdkStructure
    {
        public int              nWidth;                               // 宽度
        public int              nHeight;                              // 高度
    }

    //校准框信息
    public static class CFG_CALIBRATEBOX_INFO extends SdkStructure
    {
        public CFG_POLYGON      stuCenterPoint;                       //校准框中心点坐标(点的坐标归一化到[0,8191]区间)
        public float            fRatio;                               //相对基准校准框的比率(比如1表示基准框大小，0.5表示基准框大小的一半)
    }

    //尺寸过滤器
    public static class CFG_SIZEFILTER_INFO extends SdkStructure
    {
        public int              nCalibrateBoxNum;                     //校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //校准框(远端近端标定模式下有效)
        public byte             bMeasureModeEnable;                   //计量方式参数是否有效， 类型bool, 取值0或1
        public byte             bMeasureMode;                         //计量方式,0-像素，不需要远端、近端标定,1-实际长度，单位：米,2-远端近端标定后的像素
        public byte             bFilterTypeEnable;                    //过滤类型参数是否有效， 类型bool, 取值0或1
        // ByArea,ByRatio仅作兼容，使用独立的ByArea和ByRatio选项代替 2012/03/06
        public byte             bFilterType;                          //过滤类型:0:"ByLength",1:"ByArea",2"ByWidthHeight"
        public byte             bCfgSizeUseUInt;                      //兼容字段,置为true则CFG_SIZE结构体内部使用UINT类型下发
        public byte[]           bReserved = new byte[1];              // 保留字段
        public byte             bFilterMinSizeEnable;                 //物体最小尺寸参数是否有效， 类型bool, 取值0或1
        public byte             bFilterMaxSizeEnable;                 //物体最大尺寸参数是否有效， 类型bool, 取值0或1
        public CFG_SIZE         stuFilterMinSize;                     //物体最小尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效(远端近端标定模式下表示基准框的宽高尺寸)。
        public CFG_SIZE         stuFilterMaxSize;                     //物体最大尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效(远端近端标定模式下表示基准框的宽高尺寸)。
        public byte             abByArea;                             //类型bool, 取值0或1
        public byte             abMinArea;                            //类型bool, 取值0或1
        public byte             abMaxArea;                            //类型bool, 取值0或1
        public byte             abMinAreaSize;                        //类型bool, 取值0或1
        public byte             abMaxAreaSize;                        //类型bool, 取值0或1
        public byte             bByArea;                              //是否按面积过滤通过能力ComplexSizeFilter判断是否可用， 类型bool, 取值0或1
        public byte[]           bReserved1 = new byte[2];             // 补齐
        public float            nMinArea;                             //最小面积
        public float            nMaxArea;                             //最大面积
        public CFG_SIZE         stuMinAreaSize;                       //最小面积矩形框尺寸"计量方式"为"像素"时，表示最小面积矩形框的宽高尺寸；"计量方式"为"远端近端标定模式"时，表示基准框的最小宽高尺寸；
        public CFG_SIZE         stuMaxAreaSize;                       //最大面积矩形框尺寸,同上
        public byte             abByRatio;                            //类型bool, 取值0或1
        public byte             abMinRatio;                           //类型bool, 取值0或1
        public byte             abMaxRatio;                           //类型bool, 取值0或1
        public byte             abMinRatioSize;                       //类型bool, 取值0或1
        public byte             abMaxRatioSize;                       //类型bool, 取值0或1
        public byte             bByRatio;                             //是否按宽高比过滤通过能力ComplexSizeFilter判断是否可用， 类型bool, 取值0或1
        public byte[]           bReserved2 = new byte[6];
        public double           dMinRatio;                            //最小宽高比
        public double           dMaxRatio;                            //最大宽高比
        public CFG_SIZE         stuMinRatioSize;                      //最小宽高比矩形框尺寸，最小宽高比对应矩形框的宽高尺寸。
        public CFG_SIZE         stuMaxRatioSize;                      //最大宽高比矩形框尺寸，同上
        public byte             nAreaCalibrateBoxNum;                 //面积校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuAreaCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //面积校准框
        public byte             nRatioCalibrateBoxs;                  //宽高校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuRatioCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //宽高校准框
        public byte             abBySize;                             //长宽过滤使能参数是否有效， 类型bool, 取值0或1
        public byte             bBySize;                              //长宽过滤使能， 类型bool, 取值0或1
        public byte[]           bReserved3 = new byte[6];             // 保留字段
    }

    //各种物体特定的过滤器
    public static class CFG_OBJECT_SIZEFILTER_INFO extends SdkStructure
    {
        public byte[]           szObjectType = new byte[MAX_NAME_LEN]; //物体类型
        public CFG_SIZEFILTER_INFO stSizeFilter;                      //对应的尺寸过滤器
    }

    //特殊区域的属性类型
    public static class EM_SEPCIALREGION_PROPERTY_TYPE extends SdkStructure
    {
        public static final int   EM_SEPCIALREGION_PROPERTY_TYPE_HIGHLIGHT = 1; //高亮，键盘检测区域具有此特性
        public static final int   EM_SEPCIALREGION_PROPERTY_TYPE_REGULARBLINK = 2; //规律的闪烁，插卡区域具有此特性
        public static final int   EM_SEPCIALREGION_PROPERTY_TYPE_IREGULARBLINK = 3; //不规律的闪烁，屏幕区域具有此特性
        public static final int   EM_SEPCIALREGION_PROPERTY_TYPE_NUM = 4;
    }

    //特殊检测区，是指从检测区中区分出来，有特殊检测属性的区域
    public static class CFG_SPECIALDETECT_INFO extends SdkStructure
    {
        public int              nDetectNum;                           //检测区域顶点数
        public CFG_POLYGON[]    stDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); //检测区域
        public int              nPropertyNum;                         //特殊检测区属性个数
        public int[]            nPropertys = new int[EM_SEPCIALREGION_PROPERTY_TYPE.EM_SEPCIALREGION_PROPERTY_TYPE_NUM]; //特殊检测区属性
    }

    //各类物体的子类型
    public static class CFG_CATEGORY_TYPE extends SdkStructure
    {
        public static final int   CFG_CATEGORY_TYPE_UNKNOW = 0;         //未知类型
        //车型相关子类别
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MOTOR = 1;  //"Motor"机动车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_NON_MOTOR = 2; //"Non-Motor"非机动车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_BUS = 3;    //"Bus"公交车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_BICYCLE = 4; //"Bicycle"自行车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MOTORCYCLE = 5; //"Motorcycle"摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_UNLICENSEDMOTOR = 6; //"UnlicensedMotor":无牌机动车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_LARGECAR = 7; //"LargeCar"大型汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MICROCAR = 8; //"MicroCar"小型汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_EMBASSYCAR = 9; //"EmbassyCar"使馆汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MARGINALCAR = 10; //"MarginalCar"领馆汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_AREAOUTCAR = 11; //"AreaoutCar"境外汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_FOREIGNCAR = 12; //"ForeignCar"外籍汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_DUALTRIWHEELMOTORCYCLE = 13; //"DualTriWheelMotorcycle"两、三轮摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_LIGHTMOTORCYCLE = 14; //"LightMotorcycle"轻便摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_EMBASSYMOTORCYCLE = 15; //"EmbassyMotorcycle"使馆摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MARGINALMOTORCYCLE = 16; //"MarginalMotorcycle"领馆摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_AREAOUTMOTORCYCLE = 17; //"AreaoutMotorcycle"境外摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_FOREIGNMOTORCYCLE = 18; //"ForeignMotorcycle"外籍摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_FARMTRANSMITCAR = 19; //"FarmTransmitCar"农用运输车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TRACTOR = 20; //"Tractor"拖拉机
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TRAILER = 21; //"Trailer"挂车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_COACHCAR = 22; //"CoachCar"教练汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_COACHMOTORCYCLE = 23; //"CoachMotorcycle"教练摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TRIALCAR = 24; //"TrialCar"试验汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TRIALMOTORCYCLE = 25; //"TrialMotorcycle"试验摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYENTRYCAR = 26; //"TemporaryEntryCar"临时入境汽车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYENTRYMOTORCYCLE = 27; //"TemporaryEntryMotorcycle"临时入境摩托车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYSTEERCAR = 28; //"TemporarySteerCar"临时行驶车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_PASSENGERCAR = 29; //"PassengerCar"客车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_LARGETRUCK = 30; //"LargeTruck"大货车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MIDTRUCK = 31; //"MidTruck"中货车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_SALOONCAR = 32; //"SaloonCar"轿车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MICROBUS = 33; //"Microbus"面包车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_MICROTRUCK = 34; //"MicroTruck"小货车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_TRICYCLE = 35; //"Tricycle"三轮车
        public static final int   CFG_CATEGORY_VEHICLE_TYPE_PASSERBY = 36; //"Passerby"行人
        //车牌相关子类别
        public static final int   CFG_CATEGORY_PLATE_TYPE_NORMAL = 41;  //"Normal"蓝牌黑字
        public static final int   CFG_CATEGORY_PLATE_TYPE_YELLOW = 42;  //"Yellow"黄牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_DOUBLEYELLOW = 43; //"DoubleYellow"双层黄尾牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_POLICE = 44;  //"Police"警牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_WJ = 45;      //
        public static final int   CFG_CATEGORY_PLATE_TYPE_OUTERGUARD = 46; //
        public static final int   CFG_CATEGORY_PLATE_TYPE_DOUBLEOUTERGUARD = 47; //
        public static final int   CFG_CATEGORY_PLATE_TYPE_SAR = 48;     //"SAR"港澳特区号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_TRAINNING = 49; //"Trainning"教练车号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_PERSONAL = 50; //"Personal"个性号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_AGRI = 51;    //"Agri"农用牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_EMBASSY = 52; //"Embassy"使馆号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_MOTO = 53;    //"Moto"摩托车号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_TRACTOR = 54; //"Tractor"拖拉机号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_OFFICIALCAR = 55; //"OfficialCar"公务车
        public static final int   CFG_CATEGORY_PLATE_TYPE_PERSONALCAR = 56; //"PersonalCar"私家车
        public static final int   CFG_CATEGORY_PLATE_TYPE_WARCAR = 57;  //
        public static final int   CFG_CATEGORY_PLATE_TYPE_OTHER = 58;   //"Other"其他号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_CIVILAVIATION = 59; //"Civilaviation"民航号牌
        public static final int   CFG_CATEGORY_PLATE_TYPE_BLACK = 60;   //"Black"黑牌
    }

    //不同区域各种类型物体的检测模块配置
    public static class CFG_MODULE_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szObjectType = new byte[MAX_NAME_LEN]; //默认物体类型,详见"支持的检测物体类型列表"
        public byte             bSnapShot;                            //是否对识别物体抓图，类型bool，取值0或1
        public byte             bSensitivity;                         //灵敏度,取值1-10，值越小灵敏度越低
        public byte             bMeasureModeEnable;                   //计量方式参数是否有效，类型bool，取值0或1
        public byte             bMeasureMode;                         //计量方式,0-像素，不需要远端、近端标定,1-实际长度，单位：米,2-远端近端标定后的像素
        public int              nDetectRegionPoint;                   //检测区域顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); //检测区域
        public int              nTrackRegionPoint;                    //跟踪区域顶点数
        public CFG_POLYGON[]    stuTrackRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); //跟踪区域
        public byte             bFilterTypeEnable;                    //过滤类型参数是否有效，类型bool，取值0或1
        // ByArea,ByRatio仅作兼容使枚懒⒌腂yArea和ByRatio选项代替 2012/03/06
        public byte             nFilterType;                          //过滤类型:0:"ByLength",1:"ByArea",2:"ByWidthHeight",3:"ByRatio":
        public byte             bBackgroudEnable;                     //区域的背景类型参数是否有效，类型bool，取值0或1
        public byte             bBackgroud;                           //区域的背景类型,0-普通类型,1-高光类型
        public byte             abBySize;                             //长宽过滤使能参数是否有效，类型bool，取值0或1
        public byte             bBySize;                              //长宽过滤使能，类型bool，取值0或1
        public byte             bFilterMinSizeEnable;                 //物体最小尺寸参数是否有效，类型bool，取值0或1
        public byte             bFilterMaxSizeEnable;                 //物体最大尺寸参数是否有效，类型bool，取值0或1
        public CFG_SIZE         stuFilterMinSize;                     //物体最小尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效。
        public CFG_SIZE         stuFilterMaxSize;                     //物体最大尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效。
        public int              nExcludeRegionNum;                    //排除区域数
        public CFG_REGION[]     stuExcludeRegion = (CFG_REGION[])new CFG_REGION().toArray(MAX_EXCLUDEREGION_NUM); //排除区域
        public int              nCalibrateBoxNum;                     //校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //校准框(远端近端标定模式下有效)
        public byte             bAccuracy;                            //检测精度是否有效，类型bool，取值0或1
        public byte             byAccuracy;                           //检测精度
        public byte             bMovingStep;                          //算法移动步长是否有效，类型bool，取值0或1
        public byte             byMovingStep;                         //算法移动步长
        public byte             bScalingFactor;                       //算法缩放因子是否有效，类型bool，取值0或1
        public byte             byScalingFactor;                      //算法缩放因子
        public byte[]           bReserved2 = new byte[1];             //保留字段
        public byte             abDetectBalance;                      //漏检和误检平衡参数是否有效，类型bool，取值0或1
        public int              nDetectBalance;                       //漏检和误检平衡0-折中模式(默认)1-漏检更少2-误检更少
        public byte             abByRatio;                            //类型bool，取值0或1
        public byte             abMinRatio;                           ;//类型bool，取值0或1
        public byte             abMaxRatio;                           ;//类型bool，取值0或1
        public byte             abMinAreaSize;                        ;//类型bool，取值0或1
        public byte             abMaxAreaSize;                        ;//类型bool，取值0或1
        public byte             bByRatio;                             //是否按宽高比过滤通过能力ComplexSizeFilter判断是否可用可以和nFilterType复用，类型bool，取值0或1
        public byte             byLowDetectSensitivity;               // 车辆检测低灵敏度, 取值1~100
        public byte             byHighDetectSensitivity;              // 车辆检测高灵敏度, 取值1~100
        public double           dMinRatio;                            //最小宽高比
        public double           dMaxRatio;                            //最大宽高比
        public CFG_SIZE         stuMinAreaSize;                       //最小面积矩形框尺寸"计量方式"为"像素"时，表示最小面积矩形框的宽高尺寸；"计量方式"为"远端近端标定模式"时，表示基准框的最小宽高尺寸；
        public CFG_SIZE         stuMaxAreaSize;                       //最大面积矩形框尺寸,同上
        public byte             abByArea;                             //类型bool，取值0或1
        public byte             abMinArea;                            //类型bool，取值0或1
        public byte             abMaxArea;                            //类型bool，取值0或1
        public byte             abMinRatioSize;                       //类型bool，取值0或1
        public byte             abMaxRatioSize;                       //类型bool，取值0或1
        public byte             bByArea;                              //是否按面积过滤通过能力ComplexSizeFilter判断是否可用可以和nFilterType复用，类型bool，取值0或1
        public byte[]           bReserved3 = new byte[2];
        public float            nMinArea;                             //最小面积
        public float            nMaxArea;                             //最大面积
        public CFG_SIZE         stuMinRatioSize;                      //最小宽高比矩形框尺寸，最小宽高比对应矩形框的宽高尺寸。
        public CFG_SIZE         stuMaxRatioSize;                      //最大宽高比矩形框尺寸，同上
        public int              nAreaCalibrateBoxNum;                 //面积校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuAreaCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //面积校准框
        public int              nRatioCalibrateBoxs;                  //比例校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuRatioCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM); //比例校准框个数
        public byte             bAntiDisturbance;                     //是否开启去扰动模块，类型bool，取值0或1
        public byte             bBacklight;                           //是否有逆光，类型bool，取值0或1
        public byte             bShadow;                              //是否有阴影，类型bool，取值0或1
        public byte             bContourAssistantTrack;               //是否开启轮廓辅助跟踪，例：在目标识别时可以通过跟踪人体来辅助识别脸，类型bool，取值0或1
        public int              nPtzPresetId;                         //云台预置点，0~255，0表示固定场景，忽略预置点。大于0表示在此预置点时模块有效
        public int              nObjectFilterNum;                     //物体特定的过滤器个数
        public CFG_OBJECT_SIZEFILTER_INFO[] stObjectFilter = (CFG_OBJECT_SIZEFILTER_INFO[])new CFG_OBJECT_SIZEFILTER_INFO().toArray(MAX_OBJECT_LIST_SIZE); //物体特定的过滤器信息
        public int              abObjectImageSize;                    //BOOL类型，取值0或1
        public CFG_SIZE         stObjectImageSize;                    //保证物体图像尺寸相同,单位是像素,不支持小数，取值：>=0,0表示自动调整大小
        public int              nSpecailDetectNum;                    //特殊检测区域个数
        public CFG_SPECIALDETECT_INFO[] stSpecialDetectRegions = (CFG_SPECIALDETECT_INFO[])new CFG_SPECIALDETECT_INFO().toArray(MAX_SPECIALDETECT_NUM); //特殊检测区信息
        public int              nAttribute;                           //需要识别物体的属性个数， 类型为unsigned int
        public byte[]           szAttributes = new byte[MAX_OBJECT_ATTRIBUTES_SIZE*MAX_NAME_LEN]; //需要识别物体的属性列表，“Category”
        public int              abPlateAnalyseMode;                   //nPlateAnalyseMode是否有效, BOOL类型，取值0或1
        public int              nPlateAnalyseMode;                    //车牌识别模式，0-只识别车头牌照1-只识别车尾牌照2-车头牌照优先（场景中大部分车均是车头牌照）3-车尾牌照优先（场景中大部分车均是车尾牌照）
        //szAttributes属性存在"Category"时生效
        public int              nCategoryNum;                         //需要识别物体的子类型总数
        public int[]            emCategoryType = new int[MAX_CATEGORY_TYPE_NUMBER]; //子类型信息, 元素取CFG_CATEGORY_TYPE中的值
        public byte[]           szSceneType = new byte[CFG_COMMON_STRING_16]; // 检测区参数用于的场景类型
        public CFG_LENGTH_FILETER_INFO stuLengthFilter;               // 物体类型过滤器，如果指定新的过滤器以新的为准
        public int              bSceneTypeEx;                         // szSceneTypeEx 是否有效
        public byte[]           szSceneTypeEx = new byte[128];        // 检测区参数用于的场景类型扩展
        public int              nDetectLineMode;                      //辅助线模式，开关门检测智能大类使用        0:未知 1:中线 2:边线
        public int              nDetectLineNum;                       //警戒线数量
        public CFG_POLYGON[]    stuDetectLine = new CFG_POLYGON[20];  //警戒线折线类型，折线中每个端点的坐标归一化到[0,8192)区间。,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.CFG_POLYGON}
        public byte[]           byReserved = new byte[1024];          //预留
    }

    // 计量方式
    public static class EM_CFG_MEASURE_MODE_TYPE extends SdkStructure
    {
        public static final int   EM_CFG_NEASURE_MODE_TYPE_UNKNOW = 0;  // 未知
        public static final int   EM_CFG_NEASURE_MODE_TYPE_PIXEL = 1;   // 像素
        public static final int   EM_CFG_NEASURE_MODE_TYPE_METRIC = 2;  // 实际长度
    }

    // 过滤类型
    public static class CFG_FILTER_HEIGHT_TYPE extends SdkStructure
    {
        public static final int   CFG_FILTER_HEIGHT_TYPE_UNKNOW = 0;    // 未知
        public static final int   CFG_FILTER_HEIGHT_TYPE_BYHEIGHT = 1;  // 高度
    }

    // 物体类型长度过滤器
    public static class CFG_LENGTH_FILETER_INFO extends SdkStructure
    {
        public int              emMeasureMode;                        // 计量方式
        public int              emFilterType;                         // 过滤类型
        public int              nDetectType;                          // 0:大于且小于 1:大于或等于且小于或等于 2:大于且小于或等于 3:大于或等于且小于
        public int              nMinLen;                              // 最小检测长度，单位：cm
        public int              nMaxLen;                              // 最大检测长度，单位：cm
    }

    public static class CFG_ANALYSEMODULES_INFO extends SdkStructure
    {
        public int              nMoudlesNum;                          //检测模块数
        public CFG_MODULE_INFO[] stuModuleInfo = (CFG_MODULE_INFO[])new CFG_MODULE_INFO().toArray(MAX_ANALYSE_MODULE_NUM); //每个视频输入通道对应的各种类型物体的检测模块配置
    }

    // CLIENT_FindGroupInfo接口输入参数
    public static class NET_IN_FIND_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; //人员组ID,唯一标识一组人员,为空表示查询全部人员组信息

        public NET_IN_FIND_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 人脸数据类型
    public static class EM_FACE_DB_TYPE extends SdkStructure
    {
        public static final int   NET_FACE_DB_TYPE_UNKOWN = 0;
        public static final int   NET_FACE_DB_TYPE_HISTORY = 1;         // 历史数据库,存放的是检测出的人脸信息,一般没有包含人脸对应人员信息
        public static final int   NET_FACE_DB_TYPE_BLACKLIST = 2;       // 禁止名单数据库
        public static final int   NET_FACE_DB_TYPE_WHITELIST = 3;       // 允许名单数据库,废弃
        public static final int   NET_FACE_DB_TYPE_ALARM = 4;           // 报警库, 废弃
        public static final int   NET_FACE_DB_TYPE_PASSERBY = 5;        // 路人库
    }

    // 人员组信息
    public static class NET_FACERECONGNITION_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emFaceDBType;                         // 人员组类型,详见EM_FACE_DB_TYPE, 取值为EM_FACE_DB_TYPE中的值
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; // 人员组ID,唯一标识一组人员(不可修改,添加操作时无效)
        public byte[]           szGroupName = new byte[NET_COMMON_STRING_128]; // 人员组名称
        public byte[]           szGroupRemarks = new byte[NET_COMMON_STRING_256]; // 人员组备注信息
        public int              nGroupSize;                           // 当前组内人员数
        public int              nRetSimilarityCount;                  // 实际返回的库相似度阈值个数
        public int[]            nSimilarity = new int[MAX_SIMILARITY_COUNT]; // 库相似度阈值，人脸比对高于阈值认为匹配成功
        public int              nRetChnCount;                         // 实际返回的通道号个数
        public int[]            nChannel = new int[NET_MAX_CAMERA_CHANNEL_NUM]; // 当前组绑定到的视频通道号列表
        public int[]            nFeatureState = new int[MAX_FEATURESTATE_NUM]; // 人脸组建模状态信息:
        // [0]-准备建模的人员数量，不保证一定建模成功
        // [1]-建模失败的人员数量，图片不符合算法要求，需要更换图片
        // [2]-已建模成功人员数量，数据可用于算法进行目标识别
        // [3]-曾经建模成功，但因算法升级变得不可用的数量，重新建模就可用
        public int              emRegisterDbType;                     // 注册库类型，详见EM_REGISTER_DB_TYPE
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public NET_PASSERBY_DB_CONFIG_INFO stuPasserbyDBConfig;       // 路人库配置（选填）
        public int              nGroupSimilarity;                     // 组相似度阈值，人脸比对高于阈值认为匹配成功
        public int              nMaskSimilarity;                      // 库口罩相似度阈值，取值范围0-100，可用于口罩检测
        public int              dwCapacity;                           // 最大注册数目
        public int              nOverWrite;                           // 注册库满覆盖策略: 0:未知, 1:满停止, 2:满覆盖

        public NET_FACERECONGNITION_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 注册库属性
    public static class EM_REGISTER_DB_TYPE extends SdkStructure
    {
        public static final int   EM_REGISTER_DB_TYPE_UNKNOWN = 0;      // 未知
        public static final int   EM_REGISTER_DB_TYPE_NORMAL = 1;       // 普通库
        public static final int   EM_REGISTER_DB_TYPE_BLACKLIST = 2;    // 禁止名单
        public static final int   EM_REGISTER_DB_TYPE_WHITELIST = 3;    // 允许名单
        public static final int   EM_REGISTER_DB_TYPE_VIP = 4;          // 库
        public static final int   EM_REGISTER_DB_TYPE_STAFF = 5;        // 员工库
        public static final int   EM_REGISTER_DB_TYPE_LEADER = 6;       // 领导库
    }

    // CLIENT_FindGroupInfo接口输出参数
    public static class NET_OUT_FIND_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pGroupInfos;                          // 人员组信息,由用户申请空间， 指向 NET_FACERECONGNITION_GROUP_INFO 的指针
        public int              nMaxGroupNum;                         // 当前申请的数组大小
        public int              nRetGroupNum;                         // 设备返回的人员组个数

        public NET_OUT_FIND_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 人员组操作枚举
    public static class EM_OPERATE_FACERECONGNITION_GROUP_TYPE extends SdkStructure
    {
        public static final int   NET_FACERECONGNITION_GROUP_UNKOWN = 0;
        public static final int   NET_FACERECONGNITION_GROUP_ADD = 1;   // 添加人员组信息, 对应结构体为 NET_ADD_FACERECONGNITION_GROUP_INFO
        public static final int   NET_FACERECONGNITION_GROUP_MODIFY = 2; // 修改人员组信息, 对应结构体为 NET_MODIFY_FACERECONGNITION_GROUP_INFO
        public static final int   NET_FACERECONGNITION_GROUP_DELETE = 3; // 删除人员组信息, 对应结构体为 NET_DELETE_FACERECONGNITION_GROUP_INFO
    }

    // CLIENT_OperateFaceRecognitionGroup 接口输入参数
    public static class NET_IN_OPERATE_FACERECONGNITION_GROUP extends SdkStructure
    {
        public int              dwSize;
        public int              emOperateType;                        // 操作类型, 取值为 EM_OPERATE_FACERECONGNITION_GROUP_TYPE 中的值
        public Pointer          pOPerateInfo;                         // 相关操作信息，指向void *

        public NET_IN_OPERATE_FACERECONGNITION_GROUP()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_OperateFaceRecognitionGroup接口输出参数
    public static class NET_OUT_OPERATE_FACERECONGNITION_GROUP extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; // 新增记录的人员组ID,唯一标识一组人员

        public NET_OUT_OPERATE_FACERECONGNITION_GROUP()
        {
            this.dwSize = this.size();
        }
    }

    // 添加人员组信息
    public static class NET_ADD_FACERECONGNITION_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_FACERECONGNITION_GROUP_INFO stuGroupInfo;          // 人员组信息

        public NET_ADD_FACERECONGNITION_GROUP_INFO() {
            this.dwSize = this.size();
        }
    }

    // 修改人员组信息
    public static class NET_MODIFY_FACERECONGNITION_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_FACERECONGNITION_GROUP_INFO stuGroupInfo;          // 人员组信息

        public NET_MODIFY_FACERECONGNITION_GROUP_INFO() {
            this.dwSize = this.size();
        }
    }

    // 删除人员组信息
    public static class NET_DELETE_FACERECONGNITION_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; // 人员组ID,唯一标识一组人员

        public NET_DELETE_FACERECONGNITION_GROUP_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SetGroupInfoForChannel接口输入参数
    public static class NET_IN_SET_GROUPINFO_FOR_CHANNEL extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public int              nGroupIdNum;                          // 人员组数
        public GROUP_ID[]       szGroupIdArr = (GROUP_ID[])new GROUP_ID().toArray(MAX_GOURP_NUM); // 人员组ID
        public int              nSimilaryNum;                         // 相似度阈值个数, 与人员组数相同
        public int[]            nSimilary = new int[MAX_GOURP_NUM];   // 每个人脸组的相似度阈值, 0-100

        public NET_IN_SET_GROUPINFO_FOR_CHANNEL()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SetGroupInfoForChannel接口输出参数
    public static class NET_OUT_SET_GROUPINFO_FOR_CHANNEL extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_GROUPINFO_FOR_CHANNEL()
        {
            this.dwSize = this.size();
        }
    }

    // 人脸查询状态信息回调函数, lAttachHandle是CLIENT_AttachFaceFindState的返回值
    public static class NET_CB_FACE_FIND_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              nToken;                               // 视频浓缩任务数据库主键ID
        public int              nProgress;                            // 正常取值范围：0-100,-1,表示查询token不存在(当订阅一个不存在或结束的查询时)
        public int              nCurrentCount;                        // 目前符合查询条件的人脸数量

        public NET_CB_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }

    //CLIENT_AttachFaceFindState接口输入参数
    public static class NET_IN_FACE_FIND_STATE extends SdkStructure
    {
        public int              dwSize;                               //结构体大小,必须填写
        public int              nTokenNum;                            //查询令牌数,为0时,表示订阅所有的查询任务
        public IntByReference   nTokens;                              //查询令牌, 指向int的指针
        public Callback         cbFaceFindState;                      //回调函数 fFaceFindState 回调
        public Pointer          dwUser;                               //用户数据

        public NET_IN_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }

    //CLIENT_AttachFaceFindState接口输入参数
    public static class NET_OUT_FACE_FIND_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }

    // SDK全局日志打印信息
    public static class LOG_SET_PRINT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              bSetFilePath;                         //是否重设日志路径, BOOL类型，取值0或1
        public byte[]           szLogFilePath = new byte[MAX_LOG_PATH_LEN]; //日志路径(默认"./sdk_log/sdk_log.log")
        public int              bSetFileSize;                         //是否重设日志文件大小, BOOL类型，取值0或1
        public int              nFileSize;                            //每个日志文件的大小(默认大小10240),单位:比特, 类型为unsigned int
        public int              bSetFileNum;                          //是否重设日志文件个数, BOOL类型，取值0或1
        public int              nFileNum;                             //绕接日志文件个数(默认大小10), 类型为unsigned int
        public int              bSetPrintStrategy;                    //是否重设日志打印输出策略, BOOL类型，取值0或1
        public int              nPrintStrategy;                       //日志输出策略,0:输出到文件(默认); 1:输出到窗口, 类型为unsigned int
        public byte[]           byReserved = new byte[4];             // 字节对齐
        public Pointer          cbSDKLogCallBack;                     // 日志回调，需要将sdk日志回调出来时设置，默认为NULL
        public Pointer          dwUser;                               // 用户数据

        public LOG_SET_PRINT_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // media文件查询条件
    public static class EM_FILE_QUERY_TYPE extends SdkStructure
    {
        public static final int   NET_FILE_QUERY_TRAFFICCAR = 0;        // 交通车辆信息, 对应结构体为 MEDIA_QUERY_TRAFFICCAR_PARAM
        public static final int   NET_FILE_QUERY_ATM = 1;               // ATM信息
        public static final int   NET_FILE_QUERY_ATMTXN = 2;            // ATM交易信息
        public static final int   NET_FILE_QUERY_FACE = 3;              // 人脸信息 MEDIAFILE_FACERECOGNITION_PARAM 和 MEDIAFILE_FACERECOGNITION_INFO
        public static final int   NET_FILE_QUERY_FILE = 4;              // 文件信息对应 NET_IN_MEDIA_QUERY_FILE 和 NET_OUT_MEDIA_QUERY_FILE
        public static final int   NET_FILE_QUERY_TRAFFICCAR_EX = 5;     // 交通车辆信息,扩展 NET_FILE_QUERY_TRAFFICCAR,支持更多的字段, 对应结构体为 MEDIA_QUERY_TRAFFICCAR_PARAM_EX
        public static final int   NET_FILE_QUERY_FACE_DETECTION = 6;    // 目标检测事件信息MEDIAFILE_FACE_DETECTION_PARAM和 MEDIAFILE_FACE_DETECTION_INFO
        public static final int   NET_FILE_QUERY_IVS_EVENT = 7;         ///// 智能事件信息 MEDIAFILE_IVS_EVENT_PARAM 和 MEDIAFILE_IVS_EVENT_INFO
        public static final int   NET_FILE_QUERY_NONMOTOR = 12;         ///// 非机动车查询,  MEDIAFILE_NONMOTOR_PARAM 和 MEDIAFILE_NONMOTOR_INFO
        public static final int   NET_FILE_QUERY_SNAPSHOT_WITH_MARK = 17; // 标记抓图查询, 对应MEDIAFILE_SNAPSHORT_WITH_MARK_PARAM 和MEDIAFILE_SNAPSHORT_WITH_MARK_INFO
        public static final int   NET_FILE_QUERY_ANATOMY_TEMP_DETECT = 18; // 人体测温信息查询， 对应 MEDIAFILE_ANATOMY_TEMP_DETECT_PARAM 和 MEDIAFILE_ANATOMY_TEMP_DETECT_INFO
        public static final int   NET_FILE_QUERY_SMOKE_FIRE = 20;       ///// 烟火检测查询，对应 MEDIAFILE_SMOKE_FIRE_PARAM 和 MEDIAFILE_SMOKE_FIRE_INFO
        public static final int   NET_FILE_QUERY_FIRE_CONTROL_MONITOR = 21; //消控室值班行为检测事件查询，对应 MEDIAFILE_FIRE_CONTROL_MONITOR_PARAM 和 MEDIAFILE_FIRE_CONTROL_MONITOR_INFO
        public static final int   NET_FILE_QUERY_WORK_CLOTHES_DETECTION = 24; ///// 作业管控查询，对应 MEDIAFILE_WORK_CLOTHES_DETECTION_PARAM 和 MEDIAFILE_WORK_CLOTHES_DETECTION_INFO
        public static final int   NET_FILE_QUERY_MOBILE_ENFORCE = 27;   // 采集站和手持终端文件信息查询,对应 MEDIAFILE_MOBILE_ENFORCE_PARAM 和 MEDIAFILE_MOBILE_ENFORCE_INFO
        public static final int   NET_FILE_QUERY_SMART_KITCHEN_CLOTHES_DETECTION = 28; // 智慧厨房查询,对应 MEDIAFILE_SMART_KITCHEN_CLOTHES_DETECTION_PARAM 和 MEDIAFILE_SMART_KITCHEN_CLOTHES_DETECTION_INFO
        public static final int   NET_FILE_QUERY_EXAM = 33;             //按照考生或考场信息查找录像, 对应 NET_MEDIAFILE_EXAM_PARAM 和 NET_MEDIAFILE_EXAM_INFO
        public static final int   NET_FILE_QUERY_ANIMAL_DETECTION = 37; ///// 动物检测智能回放查询, 对应 NET_MEDIAFILE_ANIMAL_DETECTION_PARAM和 NET_MEDIA_ANIMAL_DETECTION_INFO
    }

    // 查询跳转条件
    public static class NET_FINDING_JUMP_OPTION_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nOffset;                              //查询结果偏移量,是相对于当前查询的第一条查询结果的位置偏移

        public NET_FINDING_JUMP_OPTION_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 云台联动类型
    public static class CFG_LINK_TYPE extends SdkStructure
    {
        public static final int   LINK_TYPE_NONE = 0;                   //无联动
        public static final int   LINK_TYPE_PRESET = 1;                 //联动预置点
        public static final int   LINK_TYPE_TOUR = 2;                   //联动巡航
        public static final int   LINK_TYPE_PATTERN = 3;                //联动轨迹
        public static final int   LINK_TYPE_ZOOM = 4;                   // 联动变倍
        public static final int   LINK_TYPE_SINGLESCENE = 5;            // 联动智能单场景
        public static final int   LINK_TYPE_QUICKFOCUS = 6;             // 热成像云台联动快速定位
    }

    // 联动云台信息
    public static class CFG_PTZ_LINK extends SdkStructure
    {
        public int              emType;                               //联动类型, 取值为CFG_LINK_TYPE中的值
        public int              nValue;                               //联动取值分别对应预置点号，巡航号等等
    }

    // 联动云台信息扩展
    public static class CFG_PTZ_LINK_EX extends SdkStructure
    {
        public int              emType;                               //联动类型, 取值为CFG_LINK_TYPE中的值
        public int              nParam1;                              //联动参数1
        public int              nParam2;                              //联动参数2
        public int              nParam3;                              //联动参数3
        public int              nChannelID;                           //所联动云台通道
    }

    // RGBA信息
    public static class CFG_RGBA extends SdkStructure
    {
        public int              nRed;
        public int              nGreen;
        public int              nBlue;
        public int              nAlpha;
    }

    // 事件标题内容结构体
    public static class CFG_EVENT_TITLE extends SdkStructure
    {
        public			byte[]         szText = new byte[64];
        /**
         标题左上角坐标, 采用0-8191相对坐标系
         */
        public			CFG_POLYGON    stuPoint = new CFG_POLYGON();
        /**
         标题的宽度和高度,采用0-8191相对坐标系，某项或者两项为0表示按照字体自适应宽高
         */
        public CFG_SIZE         stuSize = new CFG_SIZE();
        /**
         前景颜色
         */
        public			CFG_RGBA       stuFrontColor = new CFG_RGBA();
        /**
         背景颜色
         */
        public			CFG_RGBA       stuBackColor = new CFG_RGBA();
    }

    public static class NET_CFG_EVENT_TITLE extends SdkStructure
    {
        public byte[]           szText = new byte[MAX_CHANNELNAME_LEN];
        public NET_POINT        stuPoint;                             //标题左上角坐标,采用0-8191相对坐标系
        public NET_SIZE         stuSize;                              //标题的宽度和高度,采用0-8191相对坐标系，某项或者两项为0表示按照字体自适应宽高
        public CFG_RGBA         stuFrontColor;                        //前景颜色
        public CFG_RGBA         stuBackColor;                         //背景颜色
    }

    // 邮件附件类型
    public static class CFG_ATTACHMENT_TYPE extends SdkStructure
    {
        public static final int   ATTACHMENT_TYPE_PIC = 0;              //图片附件
        public static final int   ATTACHMENT_TYPE_VIDEO = 1;            //视频附件
        public static final int   ATTACHMENT_TYPE_NUM = 2;              //附件类型总数
    }

    // 分割模式
    public static class CFG_SPLITMODE extends SdkStructure
    {
        public static final int   SPLITMODE_1 = 1;                      //1画面
        public static final int   SPLITMODE_2 = 2;                      //2画面
        public static final int   SPLITMODE_4 = 4;                      //4画面
        public static final int   SPLITMODE_5 = 5;                      //5画面
        public static final int   SPLITMODE_6 = 6;                      //6画面
        public static final int   SPLITMODE_8 = 8;                      //8画面
        public static final int   SPLITMODE_9 = 9;                      //9画面
        public static final int   SPLITMODE_3 = 10;                     // 3画面
        public static final int   SPLITMODE_3B = 11;                    // 3画面倒品
        public static final int   SPLITMODE_12 = 12;                    //12画面
        public static final int   SPLITMODE_16 = 16;                    //16画面
        public static final int   SPLITMODE_20 = 20;                    //20画面
        public static final int   SPLITMODE_25 = 25;                    //25画面
        public static final int   SPLITMODE_36 = 36;                    //36画面
        public static final int   SPLITMODE_64 = 64;                    //64画面
        public static final int   SPLITMODE_144 = 144;                  //144画面
        public static final int   SPLITMODE_PIP = 1000;                 //画中画分割模式基础值
        public static final int   SPLITMODE_PIP1 = SPLITMODE_PIP+1;     //画中画模式, 1个全屏大画面+1个小画面窗口
        public static final int   SPLITMODE_PIP3 = SPLITMODE_PIP+3;     //画中画模式, 1个全屏大画面+3个小画面窗口
        public static final int   SPLITMODE_FREE = SPLITMODE_PIP*2;     //自由开窗模式，可以自由创建、关闭窗口，自由设置窗口位置和Z轴次序
        public static final int   SPLITMODE_COMPOSITE_1 = SPLITMODE_PIP * 3 + 1; // 融合屏成员1分割
        public static final int   SPLITMODE_COMPOSITE_4 = SPLITMODE_PIP * 3 + 4; // 融合屏成员4分割
    }

    // 轮巡联动配置
    public static class CFG_TOURLINK extends SdkStructure
    {
        public int              bEnable;                              //轮巡使能, BOOL类型，取值0或1
        public int              emSplitMode;                          //轮巡时的分割模式,取值范围为CFG_SPLITMODE中的值
        public int[]            nChannels = new int[MAX_VIDEO_CHANNEL_NUM]; //轮巡通道号列表
        public int              nChannelCount;                        //轮巡通道数量
    }

    // 门禁操作类型
    public static class EM_CFG_ACCESSCONTROLTYPE extends SdkStructure
    {
        public static final int   EM_CFG_ACCESSCONTROLTYPE_NULL = 0;    //不做操作
        public static final int   EM_CFG_ACCESSCONTROLTYPE_AUTO = 1;    //自动
        public static final int   EM_CFG_ACCESSCONTROLTYPE_OPEN = 2;    //开门
        public static final int   EM_CFG_ACCESSCONTROLTYPE_CLOSE = 3;   //关门
        public static final int   EM_CFG_ACCESSCONTROLTYPE_OPENALWAYS = 4; //永远开启
        public static final int   EM_CFG_ACCESSCONTROLTYPE_CLOSEALWAYS = 5; //永远关闭
    }

    // 邮件详细内容
    public static class CFG_MAIL_DETAIL extends SdkStructure
    {
        public int              emAttachType;                         //附件类型, 取值范围为CFG_ATTACHMENT_TYPE中的值
        public int              nMaxSize;                             //文件大小上限，单位kB
        public int              nMaxTimeLength;                       //最大录像时间长度，单位秒，对video有效
    }

    // 语音呼叫发起方
    public static class EM_CALLER_TYPE extends SdkStructure
    {
        public static final int   EM_CALLER_DEVICE = 0;                 //设备发起
    }

    // 呼叫协议
    public static class EM_CALLER_PROTOCOL_TYPE extends SdkStructure
    {
        public static final int   EM_CALLER_PROTOCOL_CELLULAR = 0;      //手机方式
    }

    // 语音呼叫联动信息
    public static class CFG_TALKBACK_INFO extends SdkStructure
    {
        public int              bCallEnable;                          //语音呼叫使能, BOOL类型，取值0或1
        public int              emCallerType;                         //语音呼叫发起方, 取值范围为EM_CALLER_TYPE中的值
        public int              emCallerProtocol;                     //语音呼叫协议, 取值范围为EM_CALLER_PROTOCOL_TYPE中的值
    }

    // 电话报警中心联动信息
    public static class CFG_PSTN_ALARM_SERVER extends SdkStructure
    {
        public int              bNeedReport;                          //是否上报至电话报警中心, BOOL类型，取值0或1
        public int              nServerCount;                         //电话报警服务器个数
        public byte[]           byDestination = new byte[MAX_PSTN_SERVER_NUM]; //上报的报警中心下标,详见配置CFG_PSTN_ALARM_CENTER_INFO
    }

    // 时间表信息
    public static class CFG_TIME_SCHEDULE extends SdkStructure
    {
        /**
         是否支持节假日配置，默认为不支持，除非获取配置后返回为TRUE，不要使能假日配置
         */
        public			int            bEnableHoliday;
        /**
         第一维前7个元素对应每周7天，第8个元素对应节假日，每天最多6个时间段
         */
        public			CFG_TIME_SECTION[] stuTimeSectionWeekDay = new CFG_TIME_SECTION[8*6];

        public			CFG_TIME_SCHEDULE(){
            for(int i=0;i<stuTimeSectionWeekDay.length;i++){
                stuTimeSectionWeekDay[i]=new CFG_TIME_SECTION();
            }
        }
    }

    // 报警联动信息
    public static class CFG_ALARM_MSG_HANDLE extends SdkStructure
    {
        /**
         能力
         */
        public			byte           abRecordMask;
        public			byte           abRecordEnable;
        public			byte           abRecordLatch;
        public			byte           abAlarmOutMask;
        public			byte           abAlarmOutEn;
        public			byte           abAlarmOutLatch;
        public			byte           abExAlarmOutMask;
        public			byte           abExAlarmOutEn;
        public			byte           abPtzLinkEn;
        public			byte           abTourMask;
        public			byte           abTourEnable;
        public			byte           abSnapshot;
        public			byte           abSnapshotEn;
        public			byte           abSnapshotPeriod;
        public			byte           abSnapshotTimes;
        public			byte           abTipEnable;
        public			byte           abMailEnable;
        public			byte           abMessageEnable;
        public			byte           abBeepEnable;
        public			byte           abVoiceEnable;
        public			byte           abMatrixMask;
        public			byte           abMatrixEnable;
        public			byte           abEventLatch;
        public			byte           abLogEnable;
        public			byte           abDelay;
        public			byte           abVideoMessageEn;
        public			byte           abMMSEnable;
        public			byte           abMessageToNetEn;
        public			byte           abTourSplit;
        public			byte           abSnapshotTitleEn;
        public			byte           abChannelCount;
        public			byte           abAlarmOutCount;
        public			byte           abPtzLinkEx;
        public			byte           abSnapshotTitle;
        public			byte           abMailDetail;
        public			byte           abVideoTitleEn;
        public			byte           abVideoTitle;
        public			byte           abTour;
        public			byte           abDBKeys;
        public			byte           abJpegSummary;
        public			byte           abFlashEn;
        public			byte           abFlashLatch;
        /**
         补齐
         */
        public			byte[]         byReserved1 = new byte[2];
        /**
         设备的视频通道数
         */
        public			int            nChannelCount;
        /**
         设备的报警输出个数
         */
        public			int            nAlarmOutCount;
        /**
         录像通道掩码(按位)
         */
        public			int[]          dwRecordMask = new int[16];
        /**
         录像使能
         */
        public			int            bRecordEnable;
        /**
         录像延时时间(秒)
         */
        public			int            nRecordLatch;
        /**
         报警输出通道掩码
         */
        public			int[]          dwAlarmOutMask = new int[16];
        /**
         报警输出使能
         */
        public			int            bAlarmOutEn;
        /**
         报警输出延时时间(秒)
         */
        public			int            nAlarmOutLatch;
        /**
         扩展报警输出通道掩码
         */
        public			int[]          dwExAlarmOutMask = new int[16];
        /**
         扩展报警输出使能
         */
        public			int            bExAlarmOutEn;
        /**
         云台联动项
         */
        public			CFG_PTZ_LINK[] stuPtzLink = new CFG_PTZ_LINK[256];
        /**
         云台联动使能
         */
        public			int            bPtzLinkEn;
        /**
         轮询通道掩码
         */
        public			int[]          dwTourMask = new int[16];
        /**
         轮询使能
         */
        public			int            bTourEnable;
        /**
         快照通道号掩码
         */
        public			int[]          dwSnapshot = new int[16];
        /**
         快照使能
         */
        public			int            bSnapshotEn;
        /**
         连拍周期(秒)
         */
        public			int            nSnapshotPeriod;
        /**
         连拍次数
         */
        public			int            nSnapshotTimes;
        /**
         本地消息框提示
         */
        public			int            bTipEnable;
        /**
         发送邮件，如果有图片，作为附件
         */
        public			int            bMailEnable;
        /**
         上传到报警服务器
         */
        public			int            bMessageEnable;
        /**
         蜂鸣
         */
        public			int            bBeepEnable;
        /**
         语音提示
         */
        public			int            bVoiceEnable;
        /**
         联动视频矩阵通道掩码
         */
        public			int[]          dwMatrixMask = new int[16];
        /**
         联动视频矩阵
         */
        public			int            bMatrixEnable;
        /**
         联动开始延时时间(秒)，0－15
         */
        public			int            nEventLatch;
        /**
         是否记录日志
         */
        public			int            bLogEnable;
        /**
         设置时先延时再生效，单位为秒
         */
        public			int            nDelay;
        /**
         叠加提示字幕到视频。叠加的字幕包括事件类型，通道号，秒计时。
         */
        public			int            bVideoMessageEn;
        /**
         发送彩信使能
         */
        public			int            bMMSEnable;
        /**
         消息上传给网络使能
         */
        public			int            bMessageToNetEn;
        /**
         轮巡时的分割模式 0: 1画面; 1: 8画面
         */
        public			int            nTourSplit;
        /**
         是否叠加图片标题
         */
        public			int            bSnapshotTitleEn;
        /**
         云台配置数
         */
        public			int            nPtzLinkExNum;
        /**
         扩展云台信息
         */
        public			CFG_PTZ_LINK_EX[] stuPtzLinkEx = new CFG_PTZ_LINK_EX[256];
        /**
         图片标题内容数
         */
        public			int            nSnapTitleNum;
        /**
         图片标题内容
         */
        public			CFG_EVENT_TITLE[] stuSnapshotTitle = new CFG_EVENT_TITLE[256];
        /**
         邮件详细内容
         */
        public			CFG_MAIL_DETAIL stuMailDetail = new CFG_MAIL_DETAIL();
        /**
         是否叠加视频标题，主要指主码流
         */
        public			int            bVideoTitleEn;
        /**
         视频标题内容数目
         */
        public			int            nVideoTitleNum;
        /**
         视频标题内容
         */
        public			CFG_EVENT_TITLE[] stuVideoTitle = new CFG_EVENT_TITLE[256];
        /**
         轮询联动数目
         */
        public			int            nTourNum;
        /**
         轮询联动配置
         */
        public			CFG_TOURLINK[] stuTour = new CFG_TOURLINK[256];
        /**
         指定数据库关键字的有效数
         */
        public			int            nDBKeysNum;
        /**
         指定事件详细信息里需要写到数据库的关键字
         */
        public			byte[]         szDBKeys = new byte[64*64];
        /**
         叠加到JPEG图片的摘要信息
         */
        public			byte[]         byJpegSummary = new byte[1024];
        /**
         是否使能补光灯
         */
        public			int            bFlashEnable;
        /**
         补光灯延时时间(秒),延时时间范围：[10,300]
         */
        public			int            nFlashLatch;
        public			byte           abAudioFileName;
        public			byte           abAlarmBellEn;
        public			byte           abAccessControlEn;
        public			byte           abAccessControl;
        /**
         联动语音文件绝对路径
         */
        public			byte[]         szAudioFileName = new byte[MAX_PATH];
        /**
         警号使能
         */
        public			int            bAlarmBellEn;
        /**
         门禁使能
         */
        public			int            bAccessControlEn;
        /**
         门禁组数
         */
        public			int            dwAccessControl;
        /**
         门禁联动操作信息
         */
        public			int[]          emAccessControlType = new int[8];
        public			byte           abTalkBack;
        /**
         补齐
         */
        public			byte[]         byReserved2 = new byte[3];
        /**
         语音呼叫联动信息
         */
        public			CFG_TALKBACK_INFO stuTalkback = new CFG_TALKBACK_INFO();
        public			byte           abPSTNAlarmServer;
        /**
         补齐
         */
        public			byte[]         byReserved3 = new byte[3];
        /**
         电话报警中心联动信息
         */
        public			CFG_PSTN_ALARM_SERVER stuPSTNAlarmServer = new CFG_PSTN_ALARM_SERVER();
        /**
         事件响应时间表
         */
        public			CFG_TIME_SCHEDULE stuTimeSection = new CFG_TIME_SCHEDULE();
        public			byte           abAlarmBellLatch;
        /**
         补齐
         */
        public			byte[]         byReserved4 = new byte[3];
        /**
         警号输出延时时间(10-300秒)
         */
        public			int            nAlarmBellLatch;
        public			byte           abAudioPlayTimes;
        public			byte           abAudioLinkTime;
        /**
         补齐
         */
        public			byte[]         byReserved5 = new byte[2];
        /**
         联动语音播放次数
         */
        public			int            nAudioPlayTimes;
        /**
         联动语音播放的时间, 单位：秒
         */
        public			int            nAudioLinkTime;
        /**
         nAlarmOutTime 是否有效
         */
        public			byte           abAlarmOutTime;
        /**
         报警输出持续时间,单位秒, 如果无此字段，按设备原来的方式实现
         */
        public			int            nAlarmOutTime;
        /**
         nBeepTime 是否有效
         */
        public			byte           abBeepTime;
        /**
         蜂鸣时长，单位秒，最大值为3600，0代表持续蜂鸣
         */
        public			int            nBeepTime;

        public			CFG_ALARM_MSG_HANDLE(){
            for(int i=0;i<stuPtzLink.length;i++){
                stuPtzLink[i]=new CFG_PTZ_LINK();
            }
            for(int i=0;i<stuPtzLinkEx.length;i++){
                stuPtzLinkEx[i]=new CFG_PTZ_LINK_EX();
            }
            for(int i=0;i<stuSnapshotTitle.length;i++){
                stuSnapshotTitle[i]=new CFG_EVENT_TITLE();
            }
            for(int i=0;i<stuVideoTitle.length;i++){
                stuVideoTitle[i]=new CFG_EVENT_TITLE();
            }
            for(int i=0;i<stuTour.length;i++){
                stuTour[i]=new CFG_TOURLINK();
            }
        }
    }

    // 报警联动信息
    public static class NET_ALARM_MSG_HANDLE extends SdkStructure
    {
        //能力
        public byte             abChannelCount;                       // 是否支持通道数量 bool类型，取值0或1
        public byte             abAlarmOutCount;                      // 是否支持报警输出数量 bool类型，取值0或1
        public byte             abRecordMask;                         // 是否支持录像通道 bool类型，取值0或1
        public byte             abRecordEnable;                       // 是否支持录像使能 bool类型，取值0或1
        public byte             abRecordLatch;                        // 是否支持录像延时 bool类型，取值0或1
        public byte             abAlarmOutMask;                       // 是否支持报警输出通道 bool类型，取值0或1
        public byte             abAlarmOutEn;                         // 是否支持报警输出使能 bool类型，取值0或1
        public byte             abAlarmOutLatch;                      // 是否支持报警输出延时 bool类型，取值0或1
        public byte             abExAlarmOutMask;                     // 是否支持扩展报警输出通道 bool类型，取值0或1
        public byte             abExAlarmOutEn;                       // 是否支持扩展报警输出使能 bool类型，取值0或1
        public byte             abPtzLinkEn;                          // 是否支持云台联动使能 bool类型，取值0或1
        public byte             abTourMask;                           // 是否支持轮巡掩码 bool类型，取值0或1
        public byte             abTourEnable;                         // 是否支持轮巡使能 bool类型，取值0或1
        public byte             abSnapshot;                           // 是否支持快照 bool类型，取值0或1
        public byte             abSnapshotEn;                         // 是否支持快照使能 bool类型，取值0或1
        public byte             abSnapshotPeriod;                     // 是否支持帧间隔，每隔多少帧抓一张图片 bool类型，取值0或1
        public byte             abSnapshotTimes;                      // 是否支持连拍次数 bool类型，取值0或1
        public byte             abTipEnable;                          // 是否支持本地消息框提示 bool类型，取值0或1
        public byte             abMailEnable;                         // 是否支持发送邮件 bool类型，取值0或1
        public byte             abMessageEnable;                      // 是否支持上传到报警中心服务器 bool类型，取值0或1
        public byte             abBeepEnable;                         // 是否支持蜂鸣 bool类型，取值0或1
        public byte             abVoiceEnable;                        // 是否支持语音提示 bool类型，取值0或1
        public byte             abMatrixMask;                         // 是否支持联动视频矩阵掩码 bool类型，取值0或1
        public byte             abMatrixEnable;                       // 是否支持联动视频矩阵使能 bool类型，取值0或1
        public byte             abEventLatch;                         // 是否支持联动开始延时时间 bool类型，取值0或1
        public byte             abLogEnable;                          // 是否支持日志使能 bool类型，取值0或1
        public byte             abDelay;                              // 是否支持报警延时 bool类型，取值0或1
        public byte             abVideoMessageEn;                     // 是否支持叠加提示字幕到视频 bool类型，取值0或1
        public byte             abMMSEnable;                          // 是否支持发送短消息 bool类型，取值0或1
        public byte             abMessageToNetEn;                     // 是否支持消息上传给网络使能 bool类型，取值0或1
        public byte             abTourSplit;                          // 是否支持换面分割轮巡 bool类型，取值0或1
        public byte             abSnapshotTitleEn;                    // 是否支持叠加图片标题使能 bool类型，取值0或1
        public byte             abPtzLinkEx;                          // 是否支持云台联动使能 bool类型，取值0或1
        public byte             abSnapshotTitle;                      // 是否支持叠加图片标题 bool类型，取值0或1
        public byte             abMailDetail;                         // 是否支持邮件详情 bool类型，取值0或1
        public byte             abVideoTitleEn;                       // 是否支持叠加视频标题，主要指主码流 bool类型，取值0或1
        public byte             abVideoTitle;                         // 是否支持视频标题内容 bool类型，取值0或1
        public byte             abTour;                               // 是否支持轮巡 bool类型，取值0或1
        public byte             abDBKeys;                             // 是否支持指定事件详细信息里需要写到数据库的关键字 bool类型，取值0或1
        public byte             abJpegSummary;                        // 是否支持叠加到JPEG图片的摘要信息 bool类型，取值0或1
        public byte             abFlashEn;                            // 是否支持补光灯使能 bool类型，取值0或1
        public byte             abFlashLatch;                         // 是否支持补光灯延时bool类型，取值0或1
        public byte             abAudioFileName;                      // 是否支持联动语音文件绝对路径 bool类型，取值0或1
        public byte             abAlarmBellEn;                        // 是否支持警号使能 bool类型，取值0或1
        public byte             abAccessControlEn;                    // 是否支持门禁控制使能 bool类型，取值0或1
        public byte             abAccessControl;                      // 是否支持门禁控制 bool类型，取值0或1
        public byte             abTalkBack;                           // 是否支持语音呼叫 bool类型，取值0或1
        public byte             abPSTNAlarmServer;                    // 是否支持电话报警中心 bool类型，取值0或1
        public byte             abAlarmBellLatch;                     // 是否支持警号输出延时 bool类型，取值0或1
        public byte             abPlayTimes;                          // 是否支持联动语音播放次数 bool类型，取值0或1
        public byte             abReboot;                             // 是否支持重启使能 bool类型，取值0或1
        public byte             abBeepTime;                           // 是否支持蜂鸣时长 bool类型，取值0或1
        public byte[]           byReserved = new byte[68];            // 能力保留字段
        //信息
        public CFG_TIME_SCHEDULE stuTimeSection;                      // 事件响应时间表
        public int              nChannelCount;                        // 设备的视频通道数
        public int              nAlarmOutCount;                       // 设备的报警输出个数
        public int[]            dwRecordMask = new int[MAX_CHANNEL_COUNT]; // 录像通道掩码(按位)
        public int              bRecordEnable;                        // 录像使能
        public int              nRecordLatch;                         // 录像延时时间(秒)
        public int[]            dwAlarmOutMask = new int[MAX_CHANNEL_COUNT]; // 报警输出通道掩码
        public int              bAlarmOutEn;                          // 报警输出使能
        public int              nAlarmOutLatch;                       // 报警输出延时时间(秒)
        public int[]            dwExAlarmOutMask = new int[MAX_CHANNEL_COUNT]; // 扩展报警输出通道掩码
        public int              bExAlarmOutEn;                        // 扩展报警输出使能
        public CFG_PTZ_LINK[]   stuPtzLink = (CFG_PTZ_LINK [])new CFG_PTZ_LINK().toArray(MAX_VIDEO_CHANNEL_NUM); // 云台联动项		//这个参数并没有被解析，应该是被扩展替代
        public int              bPtzLinkEn;                           // 云台联动使能
        public int[]            dwTourMask = new int[MAX_CHANNEL_COUNT]; // 轮询通道掩码
        public int              bTourEnable;                          // 轮询使能
        public int[]            dwSnapshot = new int[MAX_CHANNEL_COUNT]; // 快照通道号掩码
        public int              bSnapshotEn;                          // 快照使能
        public int              nSnapshotPeriod;                      // 连拍周期(秒)
        public int              nSnapshotTimes;                       // 连拍次数
        public int              bTipEnable;                           // 本地消息框提示
        public int              bMailEnable;                          // 发送邮件，如果有图片，作为附件
        public int              bMessageEnable;                       // 上传到报警服务器
        public int              bBeepEnable;                          // 蜂鸣
        public int              bVoiceEnable;                         // 语音提示
        public int              nPlayTimes;                           // 联动语音播放次数bVoiceEnable=TRUE时生效
        public int[]            dwMatrixMask = new int[MAX_CHANNEL_COUNT]; // 联动视频矩阵通道掩码
        public int              bMatrixEnable;                        // 联动视频矩阵
        public int              nEventLatch;                          // 联动开始延时时间(秒)，0－15
        public int              bLogEnable;                           // 是否记录日志
        public int              nDelay;                               // 设置时先延时再生效，单位为秒
        public int              bVideoMessageEn;                      // 叠加提示字幕到视频。叠加的字幕包括事件类型，通道号，秒计时。
        public int              bMMSEnable;                           // 发送彩信使能
        public int              bMessageToNetEn;                      // 消息上传给网络使能
        public int              nTourSplit;                           // 轮巡时的分割模式 0: 1画面; 1: 8画面
        public int              bSnapshotTitleEn;                     // 是否叠加图片标题
        public int              nPtzLinkExNum;                        // 云台配置数
        public CFG_PTZ_LINK_EX[] stuPtzLinkEx = (CFG_PTZ_LINK_EX [])new CFG_PTZ_LINK_EX().toArray(MAX_VIDEO_CHANNEL_NUM); // 扩展云台信息
        public int              nSnapTitleNum;                        // 图片标题内容数
        public NET_CFG_EVENT_TITLE[] stuSnapshotTitle = (NET_CFG_EVENT_TITLE [])new NET_CFG_EVENT_TITLE().toArray(MAX_VIDEO_CHANNEL_NUM); // 图片标题内容
        public CFG_MAIL_DETAIL  stuMailDetail;                        // 邮件详细内容
        public int              bVideoTitleEn;                        // 是否叠加视频标题，主要指主码流
        public int              nVideoTitleNum;                       // 视频标题内容数目
        public NET_CFG_EVENT_TITLE[] stuVideoTitle = (NET_CFG_EVENT_TITLE [])new NET_CFG_EVENT_TITLE().toArray(MAX_VIDEO_CHANNEL_NUM); // 视频标题内容
        public int              nTourNum;                             // 轮询联动数目
        public CFG_TOURLINK[]   stuTour = (CFG_TOURLINK [])new CFG_TOURLINK().toArray(MAX_VIDEO_CHANNEL_NUM); // 轮询联动配置
        public int              nDBKeysNum;                           // 指定数据库关键字的有效数
        public byte[]           szDBKeys = new byte[MAX_DBKEY_NUM * MAX_CHANNELNAME_LEN]; // 指定事件详细信息里需要写到数据库的关键字
        public byte[]           byJpegSummary = new byte[MAX_SUMMARY_LEN]; // 叠加到JPEG图片的摘要信息
        public int              bFlashEnable;                         // 是否使能补光灯
        public int              nFlashLatch;                          // 补光灯延时时间(秒),延时时间范围：[10,300]
        public byte[]           szAudioFileName = new byte[MAX_PATH]; // 联动语音文件绝对路径
        public int              bAlarmBellEn;                         // 警号使能
        public int              bAccessControlEn;                     // 门禁使能
        public int              dwAccessControl;                      // 门禁组数
        public int[]            emAccessControlType = new int[MAX_ACCESSCONTROL_NUM]; // 门禁联动操作信息,详见EM_CFG_ACCESSCONTROLTYPE
        public CFG_TALKBACK_INFO stuTalkback;                         // 语音呼叫联动信息
        public CFG_PSTN_ALARM_SERVER stuPSTNAlarmServer;              // 电话报警中心联动信息
        public int              nAlarmBellLatch;                      // 警号输出延时时间(10-300秒)
        public int              bReboot;                              //重启使能TRUE:使能 FALSE:不使能
        public int              nBeepTime;                            //蜂鸣时长最大值为3600，0代表持续蜂鸣
        public int              abAudioLinkTime;                      //联动语音时间使能
        public int              nAudioLinkTime;                       // 联动语音播放的时间, 单位：秒
        public int              abAudioPlayTimes;                     // 联动语音播放使能
        public int              nAudioPlayTimes;                      // 联动语音播放次数
        public int              abLightingLink;                       //云台补光灯联动项使能
        public NET_CFG_LIGHTING_LINK_INFO stuLightingLink = new NET_CFG_LIGHTING_LINK_INFO(); //云台补光灯联动项,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_CFG_LIGHTING_LINK_INFO}
        public byte[]           byReserve = new byte[828];            // 预留字节
    }

    // 时间段信息
    public static class CFG_TIME_SECTION extends SdkStructure
    {
        public int              dwRecordMask;                         //录像掩码，按位分别为动态检测录像、报警录像、定时录像、Bit3~Bit15保留、Bit16动态检测抓图、Bit17报警抓图、Bit18定时抓图
                                     // 当表示推送时间段时, 表示使能：1表示使能，0表示非使能
        public int              nBeginHour;
        public int              nBeginMin;
        public int              nBeginSec;
        public int              nEndHour;
        public int              nEndMin;
        public int              nEndSec;

        public void setStartTime(int nBeginHour, int nBeginMin, int nBeginSec) {
            this.nBeginHour = nBeginHour;
            this.nBeginMin = nBeginMin;
            this.nBeginSec = nBeginSec;
        }

        public void setEndTime(int nEndHour, int nEndMin, int nEndSec) {
            this.nEndHour = nEndHour;
            this.nEndMin = nEndMin;
            this.nEndSec = nEndSec;
        }

        public String startTime() {
            return nBeginHour + ":" + nBeginMin + ":" + nBeginSec;
        }

        public String endTime() {
            return nEndHour + ":" + nEndMin + ":" + nEndSec;
        }
    }

    // 事件类型EVENT_IVS_FACERECOGNITION(目标识别)对应的规则配置
    public static class CFG_FACERECOGNITION_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能,bool类型，取值0或1
        public byte[]           bReserved = new byte[2];              // 保留字段
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; //相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号0~65535
        public byte             bySimilarity;                         // 相似度，必须大于该相识度才报告(1~100)
        public byte             byAccuracy;                           // 识别精度(取值1~10，随着值增大，检测精度提高，检测速度下降。最小值为1表示检测速度优先，最大值为10表示检测精度优先)
        public byte             byMode;                               // 对比模式,0-正常,1-指定人脸区域组合,
        public byte             byImportantRank;                      // 查询重要等级大于等于此等级的人员(1~10,数值越高越重要)
        public int              nAreaNum;                             // 区域数
        public byte[]           byAreas = new byte[8];                // 人脸区域组合,0-眉毛，1-眼睛，2-鼻子，3-嘴巴，4-脸颊(此参数在对比模式为1时有效)
        public int              nMaxCandidate;                        // 报告的最大匹配图片个数
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public TIME_SECTION_WEEK_DAY_10[] stuTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_10[])new TIME_SECTION_WEEK_DAY_10().toArray(WEEK_DAY_NUM); // 事件响应时间段
    }

    // 事件类型EVENT_IVSS_FACEATTRIBUTE(IVSS目标检测事件) 对应的规则配置
    public static class CFG_FACEATTRIBUTE_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能,bool类型，取值0或1
        public byte[]           bReserved = new byte[3];              // 保留字
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public TIME_SECTION_WEEK_DAY_10[] stuTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_10[])new TIME_SECTION_WEEK_DAY_10().toArray(WEEK_DAY_NUM); // 事件响应时间段
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public int              nMinDuration;                         // 最短触发时间,单位：秒
        public int              nTriggerTargetsNumber;                // 触发报警的人脸个数
        public int              nSensitivity;                         // 灵敏度,范围[1,10],灵敏度越高越容易检测
        public int              nReportInterval;                      // 重复报警间隔,单位:秒,[0,600](等于0表示不重复报警)
        public int              bSizeFileter;                         // 规则特定的尺寸过滤器是否有效,bool类型 取值0或1
        public CFG_SIZEFILTER_INFO stuSizeFileter;                    // 规则特定的尺寸过滤器
        public int              nFaceFeatureNum;                      // 需要检测的人脸属性个数
        public int[]            emFaceFeatureType = new int[MAX_FEATURE_LIST_SIZE]; // 需检测的人脸属性, 参考  EM_FACEFEATURE_TYPE
        public int              bFeatureFilter;                       // 在人脸属性开启前提下，如果人脸图像质量太差，是否不上报属性
        public int              nMinQuality;                          // 人脸图片质量阈值,和bFeatureFilter一起使用 范围[0,100]
    }

    // 联动的布控组
    public static class CFG_LINKGROUP_INFO extends SdkStructure
    {
        public int              bEnable;                              // 布控组是否启用,bool类型 取值0或1
        public byte[]           szGroupID = new byte[MAX_GROUP_ID_LEN]; // 布控组ID
        public byte             bySimilarity;                         // 相似度阈值
        public byte[]           szColorName = new byte[MAX_COLOR_NAME_LEN]; // 事件触发时绘制人脸框的颜色
        public int              bShowTitle;                           // 事件触发时规则框上是否显示报警标题,bool类型 取值0或1
        public int              bShowPlate;                           // 事件触发时是否显示比对面板	,bool类型 取值0或1
        public byte[]           bReserved = new byte[511];            // 保留字段
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
    }

    // 陌生人布防模式
    public static class CFG_STRANGERMODE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 模式是否启用,bool类型 取值0或1
        public byte[]           szColorHex = new byte[MAX_COLOR_HEX_LEN]; // 事件触发时绘制人脸框的颜色
        public int              bShowTitle;                           // 事件触发时规则框上是否显示报警标题,bool类型 取值0或1
        public int              bShowPlate;                           // 事件触发时是否显示比对面板,bool类型 取值0或1
        public byte[]           bReserved = new byte[512];            // 保留字段
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
    }

    // 事件类型EVENT_IVS_FACEANALYSIS(人脸分析事件) 对应的规则配置
    public static class CFG_FACEANALYSIS_INFO extends SdkStructure
    {
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能,bool类型，取值0或1
        public byte[]           bReserved = new byte[3];              // 保留字
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public TIME_SECTION_WEEK_DAY_10[] stuTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_10[])new TIME_SECTION_WEEK_DAY_10().toArray(WEEK_DAY_NUM); // 事件响应时间段
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public int              nSensitivity;                         // 灵敏度,范围[1,10],灵敏度越高越容易检测
        public int              nLinkGroupNum;                        // 联动布控个数
        public CFG_LINKGROUP_INFO[] stuLinkGroupArr = (CFG_LINKGROUP_INFO[])new CFG_LINKGROUP_INFO().toArray(MAX_LINK_GROUP_NUM); // 联动的布控组
        public CFG_STRANGERMODE_INFO stuStrangerMode;                 // 陌生人布防模式
        public int              bSizeFileter;                         // 规则特定的尺寸过滤器是否有效
        public CFG_SIZEFILTER_INFO stuSizeFileter;                    // 规则特定的尺寸过滤器, 1-true  0-false
        public int              bFeatureEnable;                       // 是否开启人脸属性识别
        public int              nFaceFeatureNum;                      // 需要检测的人脸属性个数
        public int[]            emFaceFeatureType = new int[MAX_FEATURE_LIST_SIZE]; // 需检测的人脸属性, 参考  EM_FACEFEATURE_TYPE
        public int              bFeatureFilter;                       // 在人脸属性开启前提下，如果人脸图像质量太差，是否不上报属性
        // true-图像太差不上报属性 false-图像很差也上报属性(可能会非常不准，影响用户体验)
        public int              nMinQuality;                          // 人脸图片质量阈值,和bFeatureFilter一起使用 范围[1,100]
        public CFG_FACE_BEAUTIFICATION stuFaceBeautification = new CFG_FACE_BEAUTIFICATION(); //人Lian美化,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_FACE_BEAUTIFICATION}
        public byte[]           szReserved = new byte[1024];          //保留字段
    }

    // 事件类型EVENT_IVSS_FACECOMPARE(IVSS目标识别事件) 对应的规则配置
    public static class CFG_FACECOMPARE_INFO extends SdkStructure
    {
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能,bool类型，取值0或1
        public byte[]           bReserved = new byte[3];              // 保留字
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public TIME_SECTION_WEEK_DAY_10[] stuTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_10[])new TIME_SECTION_WEEK_DAY_10().toArray(WEEK_DAY_NUM); // 事件响应时间段
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public int              nLinkGroupNum;                        // 联动布控个数
        public CFG_LINKGROUP_INFO[] stuLinkGroupArr = (CFG_LINKGROUP_INFO[])new CFG_LINKGROUP_INFO().toArray(MAX_LINK_GROUP_NUM); // 联动的布控组
        public CFG_STRANGERMODE_INFO stuStrangerMode;                 // 陌生人布防模式
    }

    // 智能报警事件公共信息
    public static class EVENT_INTELLI_COMM_INFO extends SdkStructure
    {
        public int              emClassType;                          // 智能事件所属大类， 取值为  EM_CLASS_TYPE 中的值
        public int              nPresetID;                            // 该事件触发的预置点，对应该设置规则的预置点
        public byte[]           bReserved = new byte[124];            // 保留字节,留待扩展.
    }

    // 事件类型EVENT_IVS_FACERECOGNITION(目标识别)对应的数据块描述信息
    public static class DEV_EVENT_FACERECOGNITION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public int              nEventID;                             // 事件ID
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nCandidateNum;                        // 当前人脸匹配到的候选对象数量
        public CANDIDATE_INFO[] stuCandidates = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(NET_MAX_CANDIDATE_NUM); //当前人脸匹配到的候选对象信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byImageIndex;                         // 图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public byte[]           byReserved1 = new byte[2];            // 对齐
        public int              bGlobalScenePic;                      // 全景图是否存在, 类型为BOOL, 取值为0或者1
        public NET_PIC_INFO     stuGlobalScenePicInfo;                // 全景图片信息
        public byte[]           szSnapDevAddress = new byte[MAX_PATH]; // 抓拍当前人脸的设备地址
        public int              nOccurrenceCount;                     // 事件触发累计次数， 类型为unsigned int
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_FACE_DATA    stuFaceData;                          // 人脸数据
        public byte[]           szUID = new byte[NET_COMMON_STRING_32]; // 抓拍人员写入数据库的唯一标识符
        public NET_FEATURE_VECTOR stuFeatureVector;                   // 特征值信息
        public byte[]           szFeatureVersion = new byte[32];      // 特征值算法版本
        public int              emFaceDetectStatus;                   // 人脸在摄像机画面中的状态,详见EM_FACE_DETECT_STATUS
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public NET_PASSERBY_INFO stuPasserbyInfo;                     // 路人库信息
        public int              nStayTime;                            // 路人逗留时间 单位：秒
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           szCameraID = new byte[64];            // 国标编码
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              nPerFlag;                             // ICC人脸子系统, 人脸标签标志位, -1:未知, 0:授权人脸, 1:未授权人脸, 2:陌生人
        public byte[]           bReserved = new byte[360];            // 保留字节,留待扩展.
        public int              nRetCandidatesExNum;                  // 当前人脸匹配到的候选对象数量实际返回值
        public CANDIDATE_INFOEX[] stuCandidatesEx = (CANDIDATE_INFOEX[])new CANDIDATE_INFOEX().toArray(NET_MAX_CANDIDATE_NUM); // 当前人脸匹配到的候选对象信息扩展
        public byte[]           szSerialUUID = new byte[22];          // 级联物体ID唯一标识
            																		// 格式如下：前2位%d%d:01-视频片段,02-图片,03-文件,99-其他;
    																				// 中间14位YYYYMMDDhhmmss:年月日时分秒;后5位%u%u%u%u%u：物体ID，如00001
        public byte[]           byReserved = new byte[2];             // 对齐
        public NET_CUSTOM_PROJECTS_INFO stuCustomProjects;            // 项目信息
        public int              bIsDuplicateRemove;                   // 智慧零售，是否符合去重策略（TRUE：符合 FALSE：不符合）
        public byte[]           byReserved2 = new byte[4];            // 字节对齐
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); // 图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_MSG_OBJECT_SUPPLEMENT stuObjectSupplement;         // 检测到的物体补充字段
        public int              nMode;                                //0-普通  1-开启陌生人模式
        public	SCENE_IMAGE_INFO stuThumImageInfo = new SCENE_IMAGE_INFO(); //大图（全景图）的缩略图信息
        public	SCENE_IMAGE_INFO stuHumanImageInfo = new SCENE_IMAGE_INFO(); //人体图片信息
        public	byte[]           szVideoPath = new byte[256];          //违章关联视频FTP上传路径
        public  int             bIsHighFrequencyAlarm;                // 是否是高频次报警
        public byte[]           szFrequencyAlarmName = new byte[32];  // 频次报警名称, 当bIsHighFrequencyAlarm字段值为TRUE时才有效，表示高频次报警名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public byte[]           szSubCode = new byte[32];             // 触发人脸抓拍的事件类型
        public int              nRegionCode;                          // 区域编码
        public	byte[]           byReserved3 = new byte[236];          //保留字节

        @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }
    }

    // 路人信息
    public static class NET_PASSERBY_INFO extends SdkStructure
    {
        public byte[]           szPasserbyUID = new byte[MAX_COMMON_STRING_32]; // 路人唯一标识符
        public byte[]           szPasserbyGroupId = new byte[MAX_COMMON_STRING_64]; // 路人库ID
        public byte[]           szPasserbyGroupName = new byte[MAX_COMMON_STRING_128]; // 路人库名称
        public byte[]           byReserved = new byte[128];           // 保留
    }

    // 候选人员信息扩展结构体
    public static class CANDIDATE_INFOEX extends SdkStructure
    {
        public FACERECOGNITION_PERSON_INFOEX stPersonInfo;            // 1 人员信息扩展
        // 布控（禁止名单）库,指布控库中人员信息；
        // 历史库,指历史库中人员信息
        // 报警库,指布控库的人员信息
        public byte             bySimilarity;                         // 1 和查询图片的相似度,百分比表示,1~100
        public byte             byRange;                              // 人员所属数据库范围,详见EM_FACE_DB_TYPE
        public byte[]           byReserved1 = new byte[2];
        public NET_TIME         stTime = new NET_TIME();              // 当byRange为历史数据库时有效,表示查询人员出现的时间
        public byte[]           szAddress = new byte[MAX_PATH];       // 当byRange为历史数据库时有效,表示查询人员出现的地点
        public int              bIsHit;                               // 是否有识别结果,指这个检测出的人脸在库中有没有比对结果
        public NET_PIC_INFO_EX3 stuSceneImage = new NET_PIC_INFO_EX3(); // 人脸全景图
        public int              nChannelID;                           // 通道号
        public byte[]           szFilePathEx = new byte[256];         // 文件路径
        public NET_HISTORY_HUMAN_INFO stuHistoryHumanInfo = new NET_HISTORY_HUMAN_INFO(); //历史库人体信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_HISTORY_HUMAN_INFO}
        public byte[]           szChannelString = new byte[32];       //视频通道号
        public NET_TIME         stuTimeRealUTC = new NET_TIME();      //历史库中人员出现的时间(UTC时间格式),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME}
        public int              bIsTimeRealUTCValid;                  //stuTimeRealUTC字段是否有效
        public int              nTaskID;                              //任务流ID
        public byte[]           byReserved = new byte[72];            // 保留字节

        @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }

        @Override
        public String toString() {
            return "CANDIDATE_INFOEX{" +
                    "stPersonInfo=" + stPersonInfo +
                    ", bySimilarity=" + bySimilarity +
                    ", byRange=" + byRange +
                    ", stTime=" + stTime +
                    ", szAddress=" + new String(szAddress) +
                    ", bIsHit=" + bIsHit +
                    ", stuSceneImage=" + stuSceneImage +
                    ", nChannelID=" + nChannelID +
                    ", szFilePathEx=" + new  String(szFilePathEx) +
                    '}';
        }
    }

    // 存储IVSS需求,特征值信息
    public static class NET_FEATURE_VECTOR extends SdkStructure
    {
        public int              dwOffset;                             // 人脸小图特征值在二进制数据块中的偏移
        public int              dwLength;                             // 人脸小图特征值长度，单位:字节
        public int              bFeatureEnc;                          //标识特征值是否加密
        public byte[]           byReserved = new byte[116];           // 保留
    }

    // 人员信息扩展结构体
    public static class FACERECOGNITION_PERSON_INFOEX extends SdkStructure
    {
        public byte[]           szPersonName = new byte[NET_MAX_PERSON_NAME_LEN]; // ---姓名
        public short            wYear;                                // 出生年,作为查询条件时,此参数填0,则表示此参数无效
        public byte             byMonth;                              // 出生月,作为查询条件时,此参数填0,则表示此参数无效
        public byte             byDay;                                // 出生日,作为查询条件时,此参数填0,则表示此参数无效
        public byte             bImportantRank;                       // 人员重要等级,1~10,数值越高越重要,作为查询条件时,此参数填0,则表示此参数无效
        public byte             bySex;                                // 性别,1-男,2-女,作为查询条件时,此参数填0,则表示此参数无效
        public byte[]           szID = new byte[NET_MAX_PERSON_ID_LEN]; // ---人员唯一标示(证件号码,工号,或其他编号)
        public short            wFacePicNum;                          // 图片张数
        public NET_PIC_INFO[]   szFacePicInfo = new NET_PIC_INFO[NET_MAX_PERSON_IMAGE_NUM]; // 当前人员对应的图片信息
        public byte             byType;                               // 人员类型,详见 EM_PERSON_TYPE
        public byte             byIDType;                             // 证件类型,详见 EM_CERTIFICATE_TYPE
        public byte             byGlasses;                            // 是否戴眼镜，0-未知 1-不戴 2-戴
        public byte             byAge;                                // 年龄,0表示未知
        public byte[]           szProvince = new byte[NET_MAX_PROVINCE_NAME_LEN]; // 省份
        public byte[]           szCity = new byte[NET_MAX_CITY_NAME_LEN]; // 城市
        public byte[]           szUID = new byte[NET_MAX_PERSON_ID_LEN]; // ---人员唯一标识符,首次由服务端生成,区别于ID字段
        // 修改,删除操作时必填
        public byte[]           szCountry = new byte[NET_COUNTRY_LENGTH]; // 国籍,符合ISO3166规范
        public byte             byIsCustomType;                       // 人员类型是否为自定义: 0 使用Type规定的类型 1 自定义,使用szCustomType字段
        public byte[]           szCustomType = new byte[NET_COMMON_STRING_16]; // 人员自定义类型
        public byte[]           szComment = new byte[NET_COMMENT_LENGTH]; // ---备注信息
        public byte[]           szGroupID = new byte[NET_GROUPID_LENGTH]; // 人员所属组ID
        public byte[]           szGroupName = new byte[NET_GROUPNAME_LENGTH]; // ---人员所属组名, 用户自己申请内存的情况时,
        public int              emEmotion;                            // 表情, 参考  EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public byte[]           szHomeAddress = new byte[NET_COMMON_STRING_128]; // 注册人员家庭地址
        public int              emGlassesType;                        // 眼镜类型, 参考 EM_GLASSES_TYPE
        public byte[]           szReserved1 = new byte[4];            //
        public int              emEye;                                // 眼睛状态, 参考 EM_EYE_STATE_TYPE
        public int              emMouth;                              // 嘴巴状态, 参考  EM_MOUTH_STATE_TYPE
        public int              emMask;                               // 口罩状态, 参考 EM_MASK_STATE_TYPE
        public int              emBeard;                              // 胡子状态, 参考  EM_BEARD_STATE_TYPE
        public int              nAttractive;                          // 魅力值, -1表示无效, 0未识别，识别时范围1-100,得分高魅力高
        public int              emFeatureState;                       // 人员建模状态, 参考 EM_PERSON_FEATURE_STATE
        public int              bAgeEnable;                           // 是否指定年龄段, 1-true; 0-false
        public int[]            nAgeRange = new int[2];               // 年龄范围
        public int              nEmotionValidNum;                     // 人脸特征数组有效个数,与 emFeature 结合使用, 如果为0则表示查询所有表情
        public int[]            emEmotions = new int[NET_MAX_FACEDETECT_FEATURE_NUM]; // 人脸特征数组,与 byFeatureValidNum 结合使用  设置查询条件的时候使用, 参考 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public int              nCustomPersonInfoNum;                 // 注册人员信息扩展个数
        public CUSTOM_PERSON_INFO[] szCustomPersonInfo = new CUSTOM_PERSON_INFO[NET_MAX_CUSTOM_PERSON_INFO_NUM]; //注册人员信息扩展
        public int              emRegisterDbType;                     // 注册库类型，详见EM_REGISTER_DB_TYPE
        public NET_TIME         stuEffectiveTime = new NET_TIME();    // 有效期时间
        /**
         * 建模失败原因,对应{@link EM_PERSON_FEATURE_ERRCODE}
         */
        public int              emFeatureErrCode;
        public int              wFacePicNumEx;                        // 人脸图片张数
        public NET_FACE_PIC_INFO[] szFacePicInfoEx = new NET_FACE_PIC_INFO[6]; // 当前人员对应的图片信息
        public NET_PERSON_FEATURE_VALUE_INFO stuPersonFeatureValue = new NET_PERSON_FEATURE_VALUE_INFO(); // 人员特征信息
        public   int            bFrozenStatus;                        // 人员冻结状态
        public byte[]           szReserved = new byte[4];             // 保留字节
        public NET_PERSON_FREQUENCY_INFO stuFrequencyInfo = new NET_PERSON_FREQUENCY_INFO(); // 频次报警信息
        public  byte[]          szUUID = new byte[64];
        public Pointer          pstuCustomPasserbyInfo;               //路人信息,由用户申请内存, 一次申请一个,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.FACERECOGNITION_CUSTOM_PASSER_BY_INFO}
        public Pointer          pstuCustomExamineeInfo;               //考生考试信息, 由用户申请内存, 一次申请一个,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.FACERECOGNITION_CUSTOM_EXAMINEE_INFO}
        public Pointer          pstuCustomResidentsInfo;              //住户信息, 由用户申请内存, 一次申请一个,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.FACERECOGNITION_CUSTOM_RESIDENTS_INFO}
        public byte[]           szRecordID = new byte[64];            //对应抓拍的唯一id
        public Pointer          pstuPersonInfoEx2;                    //人员信息扩展2, 由用户申请内存, 一次申请一个,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.FACERECOGNITION_PERSON_INFOEX2}
        public int              bPersonFeatureValue;                  //人员特征信息使能
        public byte[]           byReserved = new byte[120-POINTERSIZE*4]; // 保留字节

        public FACERECOGNITION_PERSON_INFOEX(){
            for(int i=0;i<szFacePicInfo.length;i++){
                szFacePicInfo[i]=new NET_PIC_INFO();
            }
            for(int i=0;i<szCustomPersonInfo.length;i++){
                szCustomPersonInfo[i]=new CUSTOM_PERSON_INFO();
            }
            for(int i=0;i<szFacePicInfoEx.length;i++){
                szFacePicInfoEx[i]=new NET_FACE_PIC_INFO();
            }

        }
    }

    //注册人员信息扩展结构体
    public static class CUSTOM_PERSON_INFO extends SdkStructure
    {
        public byte[]           szPersonInfo = new byte[NET_MAX_PERSON_INFO_LEN]; //人员扩展信息
        public byte[]           byReserved = new byte[124];           // 保留字节
    }

    // 人体测温温度单位
    public static class EM_HUMAN_TEMPERATURE_UNIT extends SdkStructure
    {
        public static final int   EM_HUMAN_TEMPERATURE_UNKNOWN = -1;    // 未知
        public static final int   EM_HUMAN_TEMPERATURE_CENTIGRADE = 0;  // 摄氏度
        public static final int   EM_HUMAN_TEMPERATURE_FAHRENHEIT = 1;  // 华氏度
        public static final int   EM_HUMAN_TEMPERATURE_KELVIN = 2;      // 开尔文
    }

    // 人脸数据
    public static class NET_FACE_DATA extends SdkStructure
    {
        public int              emSex;                                // 性别, 参考 EM_DEV_EVENT_FACEDETECT_SEX_TYPE
        public int              nAge;                                 // 年龄,-1表示该字段数据无效
        public int              nFeatureValidNum;                     // 人脸特征数组有效个数,与 emFeature 结合使用
        public int[]            emFeature = new int[NET_MAX_FACEDETECT_FEATURE_NUM]; // 人脸特征数组,与 nFeatureValidNum 结合使用, 参考 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public byte[]           szReserved1 = new byte[4];            //
        public int              emEye;                                // 眼睛状态, 参考 EM_EYE_STATE_TYPE
        public int              emMouth;                              // 嘴巴状态, 参考 EM_MOUTH_STATE_TYPE
        public int              emMask;                               // 口罩状态, 参考 EM_MASK_STATE_TYPE
        public int              emBeard;                              // 胡子状态, 参考 EM_BEARD_STATE_TYPE
        public int              nAttractive;                          // 魅力值, -1表示无效, 0未识别，识别时范围1-100,得分高魅力高
        public byte[]           bReserved1 = new byte[4];             // 保留字节
        public NET_EULER_ANGLE  stuFaceCaptureAngle;                  // 人脸在抓拍图片中的角度信息, nPitch:抬头低头的俯仰角, nYaw左右转头的偏航角, nRoll头在平面内左偏右偏的翻滚角
        // 角度值取值范围[-90,90], 三个角度值都为999表示此角度信息无效
}
