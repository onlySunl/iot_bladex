package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 7 部分
 */
public interface NetSDKMethods7 {
        public static final int   EM_UAVINFO_TYPE_MISSION_CURRENT = 11; // 当前航点 *pInfo = NET_UAV_MISSION_CURRENT
        public static final int   EM_UAVINFO_TYPE_MOUNT_STATUS = 12;    // 云台姿态 *pInfo = NET_UAV_MOUNT_STATUS
        public static final int   EM_UAVINFO_TYPE_HOME_POSITION = 13;   // Home点位置信息 *pInfo = NET_UAV_HOME_POSITION
        public static final int   EM_UAVINFO_TYPE_MISSION_REACHED = 14; // 到达航点 *pInfo = NET_UAV_MISSION_REACHED
    }

    // 无人机实时回调数据
    public static class NET_UAVINFO extends SdkStructure
    {
        public int              emType;                               // 消息类型，详见EM_UAVINFO_TYPE
        public Pointer          pInfo;                                // 消息内容，指向void
        public int              dwInfoSize;                           // 消息大小
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 无人机实时数据回调
    public interface fUAVInfoCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_UAVINFO pstuUAVInfo,int dwUAVInfoSize,Pointer dwUser);
    }

    // 订阅无人机实时消息入参
    public static class NET_IN_ATTACH_UAVINFO extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbNotify;                             // 实时回调函数，实现fUAVInfoCallBack
        public Pointer          dwUser;                               // 用户信息

        public NET_IN_ATTACH_UAVINFO()
        {
            this.dwSize = this.size();
        }
    }

    // 订阅无人机实时消息入参
    public static class NET_OUT_ATTACH_UAVINFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTACH_UAVINFO()
        {
            this.dwSize = this.size();
        }
    }

    // 无人机通用设置命令类型 结构体大小与 NET_UAVCMD_PARAM_BUFFER 保持一致
    public static class ENUM_UAVCMD_TYPE extends SdkStructure
    {
        public static final int   ENUM_UAVCMD_UNKNOWN = -1;
        public static final int   ENUM_UAVCMD_NAV_TAKEOFF = 0;          // 地面起飞或手抛起飞 NET_UAVCMD_TAKEOFF
        public static final int   ENUM_UAVCMD_NAV_LOITER_UNLIM = 1;     // 悬停 NET_UAVCMD_LOITER_UNLIMITED
        public static final int   ENUM_UAVCMD_NAV_RETURN_TO_LAUNCH = 2; // 返航降落 NET_UAVCMD_RETURN_TO_LAUNCH
        public static final int   ENUM_UAVCMD_NAV_LAND = 3;             // 设定点着陆  NET_UAVCMD_LAND
        public static final int   ENUM_UAVCMD_CONDITION_YAW = 4;        // 变换航向 NET_UAVCMD_CONDITION_YAW
        public static final int   ENUM_UAVCMD_DO_CHANGE_SPEED = 5;      // 改变速度 NET_UAVCMD_CHANGE_SPEED
        public static final int   ENUM_UAVCMD_DO_SET_HOME = 6;          // 设置返航点 NET_UAVCMD_SET_HOME
        public static final int   ENUM_UAVCMD_DO_FLIGHTTERMINATION = 7; // 立即停转电机, 飞机锁定 NET_UAVCMD_FLIGHT_TERMINATION
        public static final int   ENUM_UAVCMD_MISSION_START = 8;        // 开始航点任务 NET_UAVCMD_MISSION_START
        public static final int   ENUM_UAVCMD_COMPONENT_ARM_DISARM = 9; // 电调解锁, 电调锁定 NET_UAVCMD_COMPONENT_ARM_DISARM
        public static final int   ENUM_UAVCMD_PREFLIGHT_REBOOT_SHUTDOWN = 10; // 重启飞行器 NET_UAVCMD_REBOOT_SHUTDOWN
        public static final int   ENUM_UAVCMD_DO_SET_RELAY = 11;        // 继电器控制 NET_UAVCMD_SET_RELAY
        public static final int   ENUM_UAVCMD_DO_REPEAT_RELAY = 12;     // 继电器循环控制 NET_UAVCMD_REPEAT_RELAY
        public static final int   ENUM_UAVCMD_DO_FENCE_ENABLE = 13;     // 电子围栏启用禁用 NET_UAVCMD_FENCE_ENABLE
        public static final int   ENUM_UAVCMD_MOUNT_CONFIGURE = 14;     // 云台模式配置 NET_UAVCMD_MOUNT_CONFIGURE
        public static final int   ENUM_UAVCMD_GET_HOME_POSITION = 15;   // 异步获取Home点位置, 实时数据回调中返回 NET_UAVCMD_GET_HOME_POSITION
        public static final int   ENUM_UAVCMD_IMAGE_START_CAPTURE = 16; // 开始抓拍 NET_UAVCMD_IMAGE_START_CAPTURE
        public static final int   ENUM_UAVCMD_IMAGE_STOP_CAPTURE = 17;  // 停止抓拍 NET_UAVCMD_IMAGE_STOP_CAPTURE
        public static final int   ENUM_UAVCMD_VIDEO_START_CAPTURE = 18; // 开始录像 NET_UAVCMD_VIDEO_START_CAPTURE
        public static final int   ENUM_UAVCMD_VIDEO_STOP_CAPTURE = 19;  // 停止录像 NET_UAVCMD_VIDEO_STOP_CAPTURE
        public static final int   ENUM_UAVCMD_NAV_WAYPOINT = 20;        // 航点 NET_UAVCMD_NAV_WAYPOINT
        public static final int   ENUM_UAVCMD_NAV_LOITER_TURNS = 21;    // 循环绕圈 NET_UAVCMD_NAV_LOITER_TURNS
        public static final int   ENUM_UAVCMD_NAV_LOITER_TIME = 22;     // 固定时间等待航点 NET_UAVCMD_NAV_LOITER_TIME
        public static final int   ENUM_UAVCMD_NAV_SPLINE_WAYPOINT = 23; // 曲线航点 NET_UAVCMD_NAV_SPLINE_WAYPOINT
        public static final int   ENUM_UAVCMD_NAV_GUIDED_ENABLE = 24;   // 引导模式开关 NET_UAVCMD_NAV_GUIDED_ENABLE
        public static final int   ENUM_UAVCMD_DO_JUMP = 25;             // 跳转到任务单某个位置. 并执行N次 NET_UAVCMD_DO_JUMP
        public static final int   ENUM_UAVCMD_DO_GUIDED_LIMITS = 26;    // 引导模式执行控制限制 NET_UAVCMD_DO_GUIDED_LIMITS
        public static final int   ENUM_UAVCMD_CONDITION_DELAY = 27;     // 动作延时 NET_UAVCMD_CONDITION_DELAY
        public static final int   ENUM_UAVCMD_CONDITION_DISTANCE = 28;  // 动作距离. 前往设定距离(到下一航点),然后继续 NET_UAVCMD_CONDITION_DISTANCE
        public static final int   ENUM_UAVCMD_DO_SET_ROI = 29;          // 相机兴趣点 NET_UAVCMD_DO_SET_ROI
        public static final int   ENUM_UAVCMD_DO_DIGICAM_CONTROL = 30;  // 相机控制 NET_UAVCMD_DO_DIGICAM_CONTROL
        public static final int   ENUM_UAVCMD_DO_MOUNT_CONTROL = 31;    // 云台角度控制 NET_UAVCMD_DO_MOUNT_CONTROL
        public static final int   ENUM_UAVCMD_DO_SET_CAM_TRIGG_DIST = 32; // 聚焦距离 NET_UAVCMD_DO_SET_CAM_TRIGG_DIST
        public static final int   ENUM_UAVCMD_SET_MODE = 33;            // 设置模式 NET_UAVCMD_SET_MODE
        public static final int   ENUM_UAVCMD_NAV_GUIDED = 34;          // 设定引导点 NET_UAVCMD_NAV_GUIDED
        public static final int   ENUM_UAVCMD_MISSION_PAUSE = 35;       // 飞行任务暂停 NET_UAVCMD_MISSION_PAUSE
        public static final int   ENUM_UAVCMD_MISSION_STOP = 36;        // 飞行任务停止 NET_UAVCMD_MISSION_STOP
        public static final int   ENUM_UAVCMD_LOAD_CONTROL = 37;        // 负载控制 NET_UAVCMD_LOAD_CONTROL
        public static final int   ENUM_UAVCMD_RC_CHANNELS_OVERRIDE = 38; // 模拟摇杆 NET_UAVCMD_RC_CHANNELS_OVERRIDE
        public static final int   ENUM_UAVCMD_HEART_BEAT = 39;          // 心跳 NET_UAVCMD_HEART_BEAT
    }

    // 无人机命令通用信息
    public static class NET_UAVCMD_COMMON extends SdkStructure
    {
        public int              nTargetSystem;                        // 目标系统
        public int              nTargetComponent;                     // 目标部件, 0 - 所有部件
        public int              nConfirmation;                        // 确认次数, 0 - 为首次命令. 用于航点任务为0
        public byte[]           byReserved = new byte[4];             // 保留
    }

    // 地面起飞命令 ENUM_UAVCMD_NAV_TAKEOFF
    public static class NET_UAVCMD_TAKEOFF extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fMinimumPitch;                        // 最小爬升率（有空速传感器时适用）设定的爬升率（无传感器）
        public float            fYawAngle;                            // 指向设定.（有罗盘）如无罗盘, 则忽略此参数.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 悬停命令 ENUM_UAVCMD_NAV_LOITER_UNLIM
    public static class NET_UAVCMD_LOITER_UNLIMITED extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fRadius;                              // 盘旋半径(m), 正值顺时针, 负值逆时针.
        public float            fYawAngle;                            // 指向设定, 仅适用可悬停机型
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 返航降落 ENUM_UAVCMD_NAV_RETURN_TO_LAUNCH
    public static class NET_UAVCMD_RETURN_TO_LAUNCH extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public byte[]           byReserved = new byte[28];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 设定点着陆 ENUM_UAVCMD_NAV_LAND
    public static class NET_UAVCMD_LAND extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fYawAngle;                            // 指向设定, 仅适用可悬停机型.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[12];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 变换航向 ENUM_UAVCMD_CONDITION_YAW
    public static class NET_UAVCMD_CONDITION_YAW extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fTargetAngle;                         // 目标角度: [0-360], 0为北
        public float            fSpeed;                               // 转向速率: [度/秒]
        public float            fDirection;                           // 指向: 负值逆时针, 正值顺时针
        public float            fRelativeOffset;                      // 相对偏置或绝对角[1,0]
        public byte[]           byReserved = new byte[12];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 改变速度 ENUM_UAVCMD_DO_CHANGE_SPEED
    public static class NET_UAVCMD_CHANGE_SPEED extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fSpeedType;                           // 速度类型（0=空速, 1=地速）
        public float            fSpeed;                               // 速度（米/秒, -1表示维持原来速度不变）
        public float            fThrottle;                            // 油门开度, 百分比数据，-1表示维持原来数值不变
        public byte[]           byReserved = new byte[16];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 设置返航点 ENUM_UAVCMD_DO_SET_HOME
    public static class NET_UAVCMD_SET_HOME extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nLocation;                            // 返航点: 1 = 使用当前点, 0 - 设定点
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[12];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 立即停转电机 ENUM_UAVCMD_DO_FLIGHTTERMINATION
    public static class NET_UAVCMD_FLIGHT_TERMINATION extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fActivated;                           // 触发值: 大于0.5 被触发
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 开始航点任务  ENUM_UAVCMD_MISSION_START
    public static class NET_UAVCMD_MISSION_START extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nFirstItem;                           // 第一项 n, 起始点的任务号
        public int              nLastItem;                            // 最后一项 m, 终点的任务号
        public byte[]           byReserved = new byte[20];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 飞行任务暂停  ENUM_UAVCMD_MISSION_PAUSE
    public static class NET_UAVCMD_MISSION_PAUSE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public byte[]           byReserved = new byte[28];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 飞行任务停止  ENUM_UAVCMD_MISSION_STOP
    public static class NET_UAVCMD_MISSION_STOP extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public byte[]           byReserved = new byte[28];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 负载类型
    public static class EM_LOAD_CONTROL_TYPE extends SdkStructure
    {
        public static final int   EM_LOAD_CONTROL_COMMON = 0;           // 通用设备 NET_LOAD_CONTROL_COMMON
        public static final int   EM_LOAD_CONTROL_PHOTO = 1;            // 拍照设备 NET_LOAD_CONTROL_PHOTO
        public static final int   EM_LOAD_CONTROL_VIDEO = 2;            // 视频设备 NET_LOAD_CONTROL_VIDEO
        public static final int   EM_LOAD_CONTROL_AUDIO = 3;            // 音频设备 NET_LOAD_CONTROL_AUDIO
        public static final int   EM_LOAD_CONTROL_LIGHT = 4;            // 灯光设备 NET_LOAD_CONTROL_LIGHT
        public static final int   EM_LOAD_CONTROL_RELAY = 5;            // 继电器设备NET_LOAD_CONTROL_RELAY
        public static final int   EM_LOAD_CONTROL_TIMING = 6;           // 定时拍照设备NET_LOAD_CONTROL_TIMING
        public static final int   EM_LOAD_CONTROL_DISTANCE = 7;         // 定距拍照设备NET_LOAD_CONTROL_DISTANCE
    }

    // 通用设备
    public static class NET_LOAD_CONTROL_COMMON extends SdkStructure
    {
        public byte[]           byReserved = new byte[24];            // 实际请使用对应负载填入
    }

    // 拍照设备
    public static class NET_LOAD_CONTROL_PHOTO extends SdkStructure
    {
        public float            fCycle;                               // 拍照周期 单位s
        public byte[]           byReserved = new byte[20];            // 对齐 NET_MISSION_ITEM_COMMON
    }

    // 视频设备
    public static class NET_LOAD_CONTROL_VIDEO extends SdkStructure
    {
        public int              nSwitch;                              // 开关 0-结束录像 1-开始录像
        public byte[]           byReserved = new byte[20];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 音频设备
    public static class NET_LOAD_CONTROL_AUDIO extends SdkStructure
    {
        public byte[]           byReserved = new byte[24];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 灯光设备
    public static class NET_LOAD_CONTROL_LIGHT extends SdkStructure
    {
        public int              nSwitch;                              // 开关 0-关闭 1-打开
        public byte[]           byReserved = new byte[20];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 继电器设备
    public static class NET_LOAD_CONTROL_RELAY extends SdkStructure
    {
        public int              nSwitch;                              // 开关 0-关闭 1-打开
        public byte[]           byReserved = new byte[20];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 定时拍照设备
    public static class NET_LOAD_CONTROL_TIMING extends SdkStructure
    {
        public int              nInterval;                            // 拍照时间间隔 单位:s
        public int              nSwitch;                              // 起停控制 0-停止 1-启用
        public byte[]           byReserved = new byte[16];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 定距拍照设备
    public static class NET_LOAD_CONTROL_DISTANCE extends SdkStructure
    {
        public int              nInterval;                            // 拍照距离间隔 单位:m
        public int              nSwitch;                              // 起停控制 0-停止 1-启用
        public byte[]           byReserved = new byte[16];            // 对齐 NET_LOAD_CONTROL_COMMON
    }

    // 负载控制
    public static class NET_UAVCMD_LOAD_CONTROL extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              emLoadType;                           // 负载类型，详见EM_LOAD_CONTROL_TYPE
        public NET_LOAD_CONTROL_COMMON stuLoadInfo;                   // 负载控制信息
    }

    // 电调解锁/锁定 ENUM_UAVCMD_COMPONENT_ARM_DISARM,
    public static class NET_UAVCMD_COMPONENT_ARM_DISARM extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              bArm;                                 // TRUE - 解锁, FALSE - 锁定
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 重启飞行器 ENUM_UAVCMD_PREFLIGHT_REBOOT_SHUTDOWN
    public static class NET_UAVCMD_REBOOT_SHUTDOWN extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nCtrlAutopilot;                       // 控制飞控 0 - 空 1 - 重启 2 - 关机
        public int              nCtrlOnboardComputer;                 // 控制机载计算机 0 - 空 1 - 机载计算机重启 2 - 机载计算机关机
        public byte[]           byReserved = new byte[20];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 继电器控制 ENUM_UAVCMD_DO_SET_RELAY
    public static class NET_UAVCMD_SET_RELAY extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nRelayNumber;                         // 继电器号
        public int              nCtrlRelay;                           // 0=关，1=开。
        public byte[]           byReserved = new byte[20];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 继电器循环控制 ENUM_UAVCMD_DO_REPEAT_RELAY
    public static class NET_UAVCMD_REPEAT_RELAY extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nRelayNumber;                         // 继电器号
        public int              nCycleCount;                          // 循环次数
        public int              nCycleTime;                           // 周期（十进制，秒）
        public byte[]           byReserved = new byte[16];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 电子围栏启用禁用 ENUM_UAVCMD_DO_FENCE_ENABLE
    public static class NET_UAVCMD_FENCE_ENABLE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nEnableState;                         // 启用状态 0 - 禁用 1 - 启用 2 - 仅地面禁用
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 云台模式设置 ENUM_UAVCMD_MOUNT_CONFIGURE
    public static class NET_UAVCMD_MOUNT_CONFIGURE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nMountMode;                           // 云台模式
        // 0 - 预留; 1 - 水平模式, RC 不可控; 2 - UAV模式, RC 不可控 ;
        // 3 - 航向锁定模式, RC可控; 4 - 预留; 5-垂直90度模式, RC不可控 6 - 航向跟随模式, RC可控
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 异步获取Home点位置 ENUM_UAVCMD_GET_HOME_POSITION
    public static class NET_UAVCMD_GET_HOME_POSITION extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public byte[]           byReserved = new byte[28];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 开始抓拍 ENUM_UAVCMD_IMAGE_START_CAPTURE Start image capture sequence
    public static class NET_UAVCMD_IMAGE_START_CAPTURE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nDurationTime;                        // 连拍持续时间
        public int              nTatolNumber;                         // 抓拍数量 0 - 表示无限制
        public int              emResolution;                         // 分辨率为 CAPTURE_SIZE_NR时, 表示自定义。目前仅支持 CAPTURE_SIZE_VGA 和 CAPTURE_SIZE_720
        public int              nCustomWidth;                         // 自定义水平分辨率 单位: 像素 pixel
        public int              nCustomHeight;                        // 自定义垂直分辨率 单位: 像素 pixel
        public int              nCameraID;                            // 相机ID
        public byte[]           byReserved = new byte[4];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 分辨率枚举
    public static class CAPTURE_SIZE extends SdkStructure
    {
        public static final int   CAPTURE_SIZE_D1 = 0;                  // 704*576(PAL)  704*480(NTSC),兼容WWxHH,下同
        public static final int   CAPTURE_SIZE_HD1 = 1;                 // 352*576(PAL)  352*480(NTSC)
        public static final int   CAPTURE_SIZE_BCIF = 2;                // 704*288(PAL)  704*240(NTSC)
        public static final int   CAPTURE_SIZE_CIF = 3;                 // 352*288(PAL)  352*240(NTSC)
        public static final int   CAPTURE_SIZE_QCIF = 4;                // 176*144(PAL)  176*120(NTSC)
        public static final int   CAPTURE_SIZE_VGA = 5;                 // 640*480
        public static final int   CAPTURE_SIZE_QVGA = 6;                // 320*240
        public static final int   CAPTURE_SIZE_SVCD = 7;                // 480*480
        public static final int   CAPTURE_SIZE_QQVGA = 8;               // 160*128
        public static final int   CAPTURE_SIZE_SVGA = 9;                // 800*592
        public static final int   CAPTURE_SIZE_XVGA = 10;               // 1024*768
        public static final int   CAPTURE_SIZE_WXGA = 11;               // 1280*800
        public static final int   CAPTURE_SIZE_SXGA = 12;               // 1280*1024
        public static final int   CAPTURE_SIZE_WSXGA = 13;              // 1600*1024
        public static final int   CAPTURE_SIZE_UXGA = 14;               // 1600*1200
        public static final int   CAPTURE_SIZE_WUXGA = 15;              // 1920*1200
        public static final int   CAPTURE_SIZE_LTF = 16;                // 240*192,ND1
        public static final int   CAPTURE_SIZE_720 = 17;                // 1280*720
        public static final int   CAPTURE_SIZE_1080 = 18;               // 1920*1080
        public static final int   CAPTURE_SIZE_1_3M = 19;               // 1280*960
        public static final int   CAPTURE_SIZE_2M = 20;                 // 1872*1408,2_5M
        public static final int   CAPTURE_SIZE_5M = 21;                 // 3744*1408
        public static final int   CAPTURE_SIZE_3M = 22;                 // 2048*1536
        public static final int   CAPTURE_SIZE_5_0M = 23;               // 2432*2050
        public static final int   CPTRUTE_SIZE_1_2M = 24;               // 1216*1024
        public static final int   CPTRUTE_SIZE_1408_1024 = 25;          // 1408*1024
        public static final int   CPTRUTE_SIZE_8M = 26;                 // 3296*2472
        public static final int   CPTRUTE_SIZE_2560_1920 = 27;          // 2560*1920(5_1M)
        public static final int   CAPTURE_SIZE_960H = 28;               // 960*576(PAL) 960*480(NTSC)
        public static final int   CAPTURE_SIZE_960_720 = 29;            // 960*720
        public static final int   CAPTURE_SIZE_NHD = 30;                // 640*360
        public static final int   CAPTURE_SIZE_QNHD = 31;               // 320*180
        public static final int   CAPTURE_SIZE_QQNHD = 32;              // 160*90
        public static final int   CAPTURE_SIZE_960_540 = 33;            // 960*540
        public static final int   CAPTURE_SIZE_640_352 = 34;            // 640*352
        public static final int   CAPTURE_SIZE_640_400 = 35;            // 640*400
        public static final int   CAPTURE_SIZE_320_192 = 36;            // 320*192
        public static final int   CAPTURE_SIZE_320_176 = 37;            // 320*176
        public static final int   CAPTURE_SIZE_SVGA1 = 38;              // 800*600
        public static final int   CAPTURE_SIZE_NR = 255;
    }

    // 停止抓拍 ENUM_UAVCMD_IMAGE_STOP_CAPTURE
    public static class NET_UAVCMD_IMAGE_STOP_CAPTURE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nCameraID;                            // 相机ID
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 开始录像 ENUM_UAVCMD_VIDEO_START_CAPTURE
    public static class NET_UAVCMD_VIDEO_START_CAPTURE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nCameraID;                            // 相机ID 0 - 表示所有相机
        public int              nFrameSpeed;                          // 帧率 单位: 秒 -1 表示: 最高帧率
        public int              emResolution;                         // 分辨率 为 CAPTURE_SIZE_NR时, 表示自定义。目前仅支持 CAPTURE_SIZE_VGA 和 CAPTURE_SIZE_720
        public int              nCustomWidth;                         // 自定义水平分辨率 单位: 像素 pixel
        public int              nCustomHeight;                        // 自定义垂直分辨率 单位: 像素 pixel
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 停止录像 ENUM_UAVCMD_VIDEO_STOP_CAPTURE
    public static class NET_UAVCMD_VIDEO_STOP_CAPTURE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nCameraID;                            // 相机ID
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 航点 ENUM_UAVCMD_NAV_WAYPOINT
    public static class NET_UAVCMD_NAV_WAYPOINT extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nHoldTime;                            // 驻留时间. 单位: 秒
        public float            fAcceptanceRadius;                    // 触发半径. 单位: 米. 进入此半径, 认为该航点结束.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 循环绕圈 ENUM_UAVCMD_NAV_LOITER_TURNS
    public static class NET_UAVCMD_NAV_LOITER_TURNS extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nTurnNumber;                          // 圈数.
        public float            fRadius;                              // 盘旋半径(m), 正值顺时针, 负值逆时针.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 在航点盘旋N秒  ENUM_UAVCMD_NAV_LOITER_TIME
    public static class NET_UAVCMD_NAV_LOITER_TIME extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nTime;                                // 时间. 单位: 秒
        public float            fRadius;                              // 盘旋半径(m), 正值顺时针, 负值逆时针.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[8];             // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 曲线航点 ENUM_UAVCMD_NAV_SPLINE_WAYPOINT
    public static class NET_UAVCMD_NAV_SPLINE_WAYPOINT extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nHoldTime;                            // 驻留时间 Hold time in decimal seconds.
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[12];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 引导模式开关 ENUM_UAVCMD_NAV_GUIDED_ENABLE
    public static class NET_UAVCMD_NAV_GUIDED_ENABLE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              bEnable;                              // 使能
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 跳转 ENUM_UAVCMD_DO_JUMP
    public static class NET_UAVCMD_DO_JUMP extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nSequenceNumber;                      // 任务序号
        public int              nRepeatCount;                         // 重复次数
        public byte[]           byReserved = new byte[20];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 引导模式执行控制限制 ENUM_UAVCMD_DO_GUIDED_LIMITS
    public static class NET_UAVCMD_DO_GUIDED_LIMITS extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nMaxTime;                             // 最大时间. 单位: 秒
        public float            fMinAltitude;                         // 最低限制高度. 单位: 米
        public float            fMaxAltitude;                         // 最大限制高度. 单位: 米
        public float            fHorizontalDistance;                  // 水平限制距离. 单位: 米
        public byte[]           byReserved = new byte[12];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 动作延时 ENUM_UAVCMD_CONDITION_DELAY
    public static class NET_UAVCMD_CONDITION_DELAY extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              nDelay;                               // 延迟时间. 单位: 秒
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 动作距离 ENUM_UAVCMD_CONDITION_DISTANCE
    public static class NET_UAVCMD_CONDITION_DISTANCE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fDistance;                            // 距离. 单位: 米
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 无人机兴趣点类型
    public static class ENUM_UAV_ROI_MODE extends SdkStructure
    {
        public static final int   ENUM_UAV_ROI_MODE_NONE = 0;           // 无兴趣点
        public static final int   ENUM_UAV_ROI_MODE_WPNEXT = 1;         // 面向下一航点
        public static final int   ENUM_UAV_ROI_MODE_WPINDEX = 2;        // 面向指定兴趣点
        public static final int   ENUM_UAV_ROI_MODE_LOCATION = 3;       // 当前航点
    }

    // 相机兴趣点 ENUM_UAVCMD_DO_SET_ROI
    public static class NET_UAVCMD_DO_SET_ROI extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              emROIMode;                            // 兴趣点模式，详见ENUM_UAV_ROI_MODE
        public int              nId;                                  // 指定航点或编号, 根据emROIMode而定
        public int              nROIIndex;                            // ROI 编号
        public byte[]           byReserved = new byte[16];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 相机控制 ENUM_UAVCMD_DO_DIGICAM_CONTROL
    public static class NET_UAVCMD_DO_DIGICAM_CONTROL extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public byte[]           byReserved = new byte[28];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 云台角度控制 ENUM_UAVCMD_DO_MOUNT_CONTROL
    public static class NET_UAVCMD_DO_MOUNT_CONTROL extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fPitchAngle;                          // 俯仰角, 单位: 度. 0: 一键回中, -90 : 一键置90度
        public float            fYawAngle;                            // 航向角, 单位: 度. 0: 一键回中, -90 : 一键置90度
        public byte[]           byReserved = new byte[20];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 聚焦距离 ENUM_UAVCMD_DO_SET_CAM_TRIGG_DIST
    public static class NET_UAVCMD_DO_SET_CAM_TRIGG_DIST extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fDistance;                            // 聚焦距离
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 设置模式 ENUM_UAVCMD_SET_MODE
    public static class NET_UAVCMD_SET_MODE extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public int              emUAVMode;                            // 飞行模式，详见ENUM_UAV_MODE
        public byte[]           byReserved = new byte[24];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 设定引导点 ENUM_UAVCMD_NAV_GUIDED
    public static class NET_UAVCMD_NAV_GUIDED extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 通用信息
        public float            fLatitude;                            // 纬度
        public float            fLongitude;                           // 经度
        public float            fAltitude;                            // 高度
        public byte[]           byReserved = new byte[16];            // 对齐 NET_UAVCMD_PARAM_BUFFER
    }

    // 航点命令对应的通用参数, 需要转换成 ENUM_UAVCMD_TYPE 对应的结构体
    public static class NET_UAVCMD_PARAM_BUFFER extends SdkStructure
    {
        public NET_UAVCMD_COMMON stuCommon;                           // 命令通用信息
        public byte[]           byParamBuffer = new byte[28];         // 参数缓存
    }

    // 摇杆模拟：输入参数
    public static class NET_UAVCMD_RC_CHANNELS_OVERRIDE extends SdkStructure
    {
        public short            nChan1;                               // 滚转角，范围[1091, 1937]，如果未改变，填 UINT16_MAX
        public short            nChan2;                               // 俯仰角，范围[1091, 1937]，如果未改变，填 UINT16_MAX
        public short            nChan3;                               // 油门，范围[1091, 1937]，如果未改变，填 UINT16_MAX
        public short            nChan4;                               // 偏航角，[1091, 1937]，如果未改变，填 UINT16_MAX
        public short            nChan5;                               // 模式切换：取值1091,1514，1937，如果未改变，填 UINT16_MAX
        public short            nChan6;                               // 云台航向，范围[1091,1937]，如果未改变，填 UINT16_MAX
        public short            nChan7;                               // 云台俯仰，范围[1091,1937]，如果未改变，填 UINT16_MAX
        public short            nChan8;                               // 起落架，取值1091，1937，如果未改变，填 UINT16_MAX
        public short            nChan9;                               // 云台模式， 取值1091,1514，1937，如果未改变，填 UINT16_MAX
        public short            nChan10;                              // 一键返航，取值1091,1937，如果未改变，填 UINT16_MAX
        public short            nChan11;                              // 一键起降，取值1091,1937，如果未改变，填 UINT16_MAX
        public short            nChan12;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan13;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan14;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan15;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan16;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan17;                              // 当前没有用到，填 UINT16_MAX
        public short            nChan18;                              // 当前没有用到，填 UINT16_MAX
        public byte             nTargetSystem;                        // 目标系统
        public byte             nTargetComponent;                     // 目标组件
        public byte[]           szReserved = new byte[6];             // 保留字段，对齐NET_UAVCMD_PARAM_BUFFER
    }

    // 心跳结构体
    public static class NET_UAVCMD_HEART_BEAT extends SdkStructure
    {
        public int              nCustomMode;                          // 自动驾驶仪用户自定义模式
        public byte             nType;                                // MAV 类型
        public byte             nAutoPilot;                           // 自动驾驶仪类型
        public byte             nBaseMode;                            // 系统模式
        public byte             nSystemStatus;                        // 系统状态值
        public byte             nMavlinkVersion;                      // MAVLink 版本信息
        public byte[]           szReserved = new byte[35];            // 保留字段，对齐NET_UAVCMD_PARAM_BUFFER
    }

    // 订阅无人机实时消息 pstuInParam 和 pstuOutParam 由设备申请释放
    public LLong CLIENT_AttachUAVInfo(LLong lLoginID,NET_IN_ATTACH_UAVINFO pstuInParam,NET_OUT_ATTACH_UAVINFO pstuOutParam,int nWaitTime);

    // 退订无人机实时消息 lAttachHandle 是 CLIENT_AttachUAVInfo 返回值
    public boolean CLIENT_DetachUAVInfo(LLong lAttachHandle);

    // 警戒区入侵方向
    public static class NET_CROSSREGION_DIRECTION_INFO extends SdkStructure
    {
        public static final int   EM_CROSSREGION_DIRECTION_UNKNOW = 0;
        public static final int   EM_CROSSREGION_DIRECTION_ENTER = 1;   // 进入
        public static final int   EM_CROSSREGION_DIRECTION_LEAVE = 2;   // 离开
        public static final int   EM_CROSSREGION_DIRECTION_APPEAR = 3;  // 出现
        public static final int   EM_CROSSREGION_DIRECTION_DISAPPEAR = 4; // 消失
    }

    // 电源类型
    public static class EM_POWER_TYPE extends SdkStructure
    {
        public static final int   EM_POWER_TYPE_MAIN = 0;               // 主电源
        public static final int   EM_POWER_TYPE_BACKUP = 1;             // 备用电源
    }

    // 电源故障事件类型
    public static class EM_POWERFAULT_EVENT_TYPE extends SdkStructure
    {
        public static final int   EM_POWERFAULT_EVENT_UNKNOWN = -1;     // 未知
        public static final int   EM_POWERFAULT_EVENT_LOST = 0;         // 掉电、电池不在位
        public static final int   EM_POWERFAULT_EVENT_LOST_ADAPTER = 1; // 适配器不在位
        public static final int   EM_POWERFAULT_EVENT_LOW_BATTERY = 2;  // 电池欠压
        public static final int   EM_POWERFAULT_EVENT_LOW_ADAPTER = 3;  // 适配器欠压
    }

    // 电源故障事件
    public static class ALARM_POWERFAULT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emPowerType;                          // 电源类型，详见EM_POWER_TYPE
        public int              emPowerFaultEvent;                    // 电源故障事件，详见EM_POWERFAULT_EVENT_TYPE
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              nAction;                              // 0:开始 1:停止
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_POWERFAULT_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 防拆报警事件
    public static class ALARM_CHASSISINTRUDED_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nAction;                              // 0:开始 1:停止
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              nChannelID;                           // 通道号
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 读卡器ID
        public int              nEventID;                             // 事件ID
        public byte[]           szSN = new byte[32];                  // 无线设备序列号
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public int              emDevType;                            //设备类型,参考EM_ALARM_CHASSISINTRUDED_DEV_TYPE

        public ALARM_CHASSISINTRUDED_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 传感器感应方式枚举类型
    public static class NET_SENSE_METHOD extends SdkStructure
    {
        public static final int   NET_SENSE_UNKNOWN = -1;               // 未知类型
        public static final int   NET_SENSE_DOOR = 0;                   // 门磁
        public static final int   NET_SENSE_PASSIVEINFRA = 1;           // 被动红外
        public static final int   NET_SENSE_GAS = 2;                    // 气感
        public static final int   NET_SENSE_SMOKING = 3;                // 烟感
        public static final int   NET_SENSE_WATER = 4;                  // 水感
        public static final int   NET_SENSE_ACTIVEFRA = 5;              // 主动红外
        public static final int   NET_SENSE_GLASS = 6;                  // 玻璃破碎
        public static final int   NET_SENSE_EMERGENCYSWITCH = 7;        // 紧急开关
        public static final int   NET_SENSE_SHOCK = 8;                  // 震动
        public static final int   NET_SENSE_DOUBLEMETHOD = 9;           // 双鉴(红外+微波)
        public static final int   NET_SENSE_THREEMETHOD = 10;           // 三技术
        public static final int   NET_SENSE_TEMP = 11;                  // 温度
        public static final int   NET_SENSE_HUMIDITY = 12;              // 湿度
        public static final int   NET_SENSE_WIND = 13;                  // 风速
        public static final int   NET_SENSE_CALLBUTTON = 14;            // 呼叫按钮
        public static final int   NET_SENSE_GASPRESSURE = 15;           // 气体压力
        public static final int   NET_SENSE_GASCONCENTRATION = 16;      // 燃气浓度
        public static final int   NET_SENSE_GASFLOW = 17;               // 气体流量
        public static final int   NET_SENSE_OTHER = 18;                 // 其他
        public static final int   NET_SENSE_OIL = 19;                   // 油量检测,汽油、柴油等车辆用油检测
        public static final int   NET_SENSE_MILEAGE = 20;               // 里程数检测
        public static final int   NET_SENSE_URGENCYBUTTON = 21;         // 紧急按钮
        public static final int   NET_SENSE_STEAL = 22;                 // 盗窃
        public static final int   NET_SENSE_PERIMETER = 23;             // 周界
        public static final int   NET_SENSE_PREVENTREMOVE = 24;         // 防拆
        public static final int   NET_SENSE_DOORBELL = 25;              // 门铃
        public static final int   NET_SENSE_ALTERVOLT = 26;             // 交流电压传感器
        public static final int   NET_SENSE_DIRECTVOLT = 27;            // 直流电压传感器
        public static final int   NET_SENSE_ALTERCUR = 28;              // 交流电流传感器
        public static final int   NET_SENSE_DIRECTCUR = 29;             // 直流电流传感器
        public static final int   NET_SENSE_RSUGENERAL = 30;            // 高新兴通用模拟量	4~20mA或0~5V
        public static final int   NET_SENSE_RSUDOOR = 31;               // 高新兴门禁感应
        public static final int   NET_SENSE_RSUPOWEROFF = 32;           // 高新兴断电感应
        public static final int   NET_SENSE_TEMP1500 = 33;              // 1500温度传感器
        public static final int   NET_SENSE_TEMPDS18B20 = 34;           // DS18B20温度传感器
        public static final int   NET_SENSE_HUMIDITY1500 = 35;          // 1500湿度传感器
        public static final int   NET_SENSE_INFRARED = 36;              // 红外报警
        public static final int   NET_SENSE_FIREALARM = 37;             // 火警
        public static final int   NET_SENSE_CO2 = 38;                   // CO2浓度检测,典型值:0~5000ppm
        public static final int   NET_SNESE_SOUND = 39;                 // 噪音检测,典型值:30~130dB
        public static final int   NET_SENSE_PM25 = 40;                  // PM2.5检测,典型值:0~1000ug/m3
        public static final int   NET_SENSE_SF6 = 41;                   // SF6浓度检测,典型值:0~3000ppm
        public static final int   NET_SENSE_O3 = 42;                    // 臭氧浓度检测,典型值:0~100ppm
        public static final int   NET_SENSE_AMBIENTLIGHT = 43;          // 环境光照检测,典型值:0~20000Lux
        public static final int   NET_SENSE_SIGNINBUTTON = 44;          // 签入按钮
        public static final int   NET_SENSE_LIQUIDLEVEL = 45;           // 液位
        public static final int   NET_SENSE_DISTANCE = 46;              // 测距
        public static final int   NET_SENSE_WATERFLOW = 47;             // 水流量
        public static final int   NET_SENSE_NUM = 54;                   // 枚举类型总数
    }

    // 防区类型
    public static class NET_DEFENCEAREA_TYPE extends SdkStructure
    {
        public static final int   NET_DEFENCEAREA_TYPE_UNKNOWN = 0;     // 未知类型防区
        public static final int   NET_DEFENCEAREA_TYPE_ALARM = 1;       // 开关量防区
    }

    // 旁路状态类型
    public static class NET_BYPASS_MODE extends SdkStructure
    {
        public static final int   NET_BYPASS_MODE_UNKNOW = 0;           // 未知状态
        public static final int   NET_BYPASS_MODE_BYPASS = 1;           // 旁路
        public static final int   NET_BYPASS_MODE_NORMAL = 2;           // 正常
        public static final int   NET_BYPASS_MODE_ISOLATED = 3;         // 隔离
    }

    // 旁路状态变化事件的信息
    public static class ALARM_BYPASSMODE_CHANGE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              emDefenceType;                        // 防区类型，详见NET_DEFENCEAREA_TYPE
        public int              nIsExtend;                            // 是否为扩展(通道)防区, 1:扩展通道, 0: 非扩展通道
        public int              emMode;                               // 变化后的模式，详见NET_BYPASS_MODE
        public int              dwID;                                 // ID号, 遥控器编号或键盘地址, emTriggerMode为NET_EM_TRIGGER_MODE_NET类型时为0
        public int              emTriggerMode;                        // 触发方式，详见NET_EM_TRIGGER_MODE

        public ALARM_BYPASSMODE_CHANGE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 报警输入源事件详情(只要有输入就会产生改事件,不论防区当前的模式,无法屏蔽)
    public static class ALARM_INPUT_SOURCE_SIGNAL_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:开始 1:停止
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_INPUT_SOURCE_SIGNAL_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 门禁状态类型
    public static class NET_ACCESS_CTL_STATUS_TYPE extends SdkStructure
    {
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_UNKNOWN = 0;
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_OPEN = 1;  // 开门
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_CLOSE = 2; // 关门
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_ABNORMAL = 3; // 异常
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_FAKELOCKED = 4; // 假锁
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_CLOSEALWAYS = 5; // 常闭
        public static final int   NET_ACCESS_CTL_STATUS_TYPE_OPENALWAYS = 6; // 常开
    }

    // 多人组合开门事件(对应NET_ALARM_OPENDOORGROUP类型)
    public static class ALARM_OPEN_DOOR_GROUP_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 门通道号(从0开始)
        public NET_TIME         stuTime;                              // 事件时间

        public ALARM_OPEN_DOOR_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 获取信息事件(对应NET_ALARM_FINGER_PRINT类型)
    public static class ALARM_CAPTURE_FINGER_PRINT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 门通道号(从0开始)
        public NET_TIME         stuTime;                              // 事件时间
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 门读卡器ID
        public int              nPacketLen;                           // 单个信息数据包长度
        public int              nPacketNum;                           // 信息数据包个数
        public Pointer          szFingerPrintInfo;                    // 信息数据(数据总长度即nPacketLen*nPacketNum)，指向byte
        public int              bCollectResult;                       // 采集结果
        public byte[]           szCardNo = new byte[32];              // 信息所属人员卡号
        public byte[]           szUserID = new byte[32];              // 信息所属人员ID
        public int              nErrorCode;                           //指纹采集失败的错误码, -1 未知, 0, 通用成功 1, 通用失败 2, 采集失败 3, 合成失败 4, 插入失败 5, 超时 6, 采集暂停 7, 指纹重复 8,未知错误，9,指纹已满
        public int              nFingerImageDataLen;                  //指纹图像数据长度
        public byte[]           szFingerImageData = new byte[204800]; //指纹图像数据

        public ALARM_CAPTURE_FINGER_PRINT_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 子系统状态类型
    public static class EM_SUBSYSTEM_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_SUBSYSTEM_STATE_UNKNOWN = 0;       // 未知
        public static final int   EM_SUBSYSTEM_STATE_ACTIVE = 1;        // 已激活
        public static final int   EM_SUBSYSTEM_STATE_INACTIVE = 2;      // 未激活
    }

    // 子系统状态改变事件
    public static class ALARM_SUBSYSTEM_STATE_CHANGE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 子系统序号(从0开始)
        public NET_TIME         stuTime;                              // 事件发生的时间
        public int              emState;                              // 变化后的状态，详见EM_SUBSYSTEM_STATE_TYPE

        public ALARM_SUBSYSTEM_STATE_CHANGE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // PSTN掉线事件
    public static class ALARM_PSTN_BREAK_LINE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 电话线序号(从0开始)
        public int              nAction;                              // 0:开始 1:停止
        public NET_TIME         stuTime;                              // 事件发生的时间
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_PSTN_BREAK_LINE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 反复进入事件详细信息
    public static class ALARM_ACCESS_CTL_REPEAT_ENTER_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public byte[]           szDoorName = new byte[NET_MAX_DOORNAME_LEN]; // 门禁名称
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public int              nEventID;                             // 事件ID
        public byte[]           szUserID = new byte[64];              //用户ID，唯一标识一用户
        public byte[]           szReaderID = new byte[64];            //门读卡器ID,10进制

        public ALARM_ACCESS_CTL_REPEAT_ENTER_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 胁迫卡刷卡事件详细信息
    public static class ALARM_ACCESS_CTL_DURESS_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public byte[]           szDoorName = new byte[NET_MAX_DOORNAME_LEN]; // 门禁名称
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 胁迫卡号
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              nEventID;                             // 事件ID
        public byte[]           szSN = new byte[32];                  // 无线设备序列号
        public byte[]           szUserID = new byte[12];              // 用户ID
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           szUserIDEx = new byte[64];            // 用户ID扩展

        public ALARM_ACCESS_CTL_DURESS_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 区域检测事件动作
    public static class NET_CROSSREGION_ACTION_INFO extends SdkStructure
    {
        public static final int   EM_CROSSREGION_ACTION_UNKNOW = 0;
        public static final int   EM_CROSSREGION_ACTION_INSIDE = 1;     // 在区域内
        public static final int   EM_CROSSREGION_ACTION_CROSS = 2;      // 穿越区域
        public static final int   EM_CROSSREGION_ACTION_APPEAR = 3;     // 出现
        public static final int   EM_CROSSREGION_ACTION_DISAPPEAR = 4;  // 消失
    }

    // 警戒线入侵方向
    public static class NET_CROSSLINE_DIRECTION_INFO extends SdkStructure
    {
        public static final int   EM_CROSSLINE_DIRECTION_UNKNOW = 0;
        public static final int   EM_CROSSLINE_DIRECTION_LEFT2RIGHT = 1; // 左到右
        public static final int   EM_CROSSLINE_DIRECTION_RIGHT2LEFT = 2; // 右到左
        public static final int   EM_CROSSLINE_DIRECTION_ANY = 3;
    }

    // 警戒线事件(对应事件 NET_EVENT_CROSSLINE_DETECTION)
    public static class ALARM_EVENT_CROSSLINE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public int              emCrossDirection;                     // 入侵方向，详见NET_CROSSLINE_DIRECTION_INFO
        public int              nOccurrenceCount;                     // 规则被触发生次数
        public int              nLevel;                               // 事件级别,GB30147需求项
        public int              bIsObjectInfo;                        // 是否检测到物体信息
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体信息
        public int              nRetObjectNum;                        // 实际返回多个检测到的物体信息
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[MAX_TARGET_OBJECT_NUM]; // 多个检测到的物体信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           szPresetName = new byte[64];          //事件触发的预置点名称
        public int              nPresetID;                            //事件触发的预置点号

        public ALARM_EVENT_CROSSLINE_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuObjects.length; ++i) {
                stuObjects[i] = new NET_MSG_OBJECT();
            }
        }
    }

    //警戒区事件(对应事件 NET_EVENT_CROSSREGION_DETECTION)
    public static class ALARM_EVENT_CROSSREGION_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public int              emDirection;                          // 警戒区入侵方向，详见NET_CROSSREGION_DIRECTION_INFO
        public int              emActionType;                         // 警戒区检测动作类型，详见NET_CROSSREGION_ACTION_INFO
        public int              nOccurrenceCount;                     // 规则被触发生次数
        public int              nLevel;                               // 事件级别,GB30147需求项
        public byte[]           szName = new byte[NET_COMMON_STRING_128]; // 名称
        public int              bIsObjectInfo;                        // 是否检测到物体信息
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体信息
        public int              nRetObjectNum;                        // 实际返回多个检测到的物体信息
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[MAX_TARGET_OBJECT_NUM]; // 多个检测到的物体信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           szMac = new byte[32];                 // 事件触发源的Mac地址
        public byte[]           szReserved = new byte[1024];          // 预留字节

        public ALARM_EVENT_CROSSREGION_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuObjects.length; ++i) {
                stuObjects[i] = new NET_MSG_OBJECT();
            }
        }
    }

    // 探测器状态
    public static class EM_SENSOR_ABNORMAL_STATUS extends SdkStructure
    {
        public static final int   NET_SENSOR_ABNORMAL_STATUS_UNKNOWN = 0;
        public static final int   NET_SENSOR_ABNORMAL_STATUS_SHORT = 1; // 短路
        public static final int   NET_SENSOR_ABNORMAL_STATUS_BREAK = 2; // 断路
        public static final int   NET_SENSOR_ABNORMAL_STATUS_INTRIDED = 3; // 被拆开
    }

    //事件类型(NET_ALARM_SENSOR_ABNORMAL) 探测器状态异常报警
    public static class ALARM_SENSOR_ABNORMAL_INFO extends SdkStructure
    {
        public int              nAction;                              // 0:开始 1:停止
        public int              nChannelID;                           // 视频通道号
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              emStatus;                             // 探测器状态，详见EM_SENSOR_ABNORMAL_STATUS
        public int              emSenseMethod;                        // SenseMethod, 感应方式,参见具体枚举定义NET_SENSE_METHOD
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[124];           // 预留字段
    }

    // 防区布防撤防状态类型
    public static class EM_DEFENCEMODE extends SdkStructure
    {
        public static final int   EM_DEFENCEMODE_UNKNOWN = 0;           // "unknown"   未知
        public static final int   EM_DEFENCEMODE_ARMING = 1;            // "Arming"    布防
        public static final int   EM_DEFENCEMODE_DISARMING = 2;         // "Disarming" 撤防
    }

    //触发方式
    public static class EM_ARMMODECHANGE_TRIGGERMODE extends SdkStructure
    {
        public static final int   EM_ARMMODECHANGE_TRIGGERMODE_UNKNOWN = 0; // 未知
        public static final int   EM_ARMMODECHANGE_TRIGGERMODE_NET = 1; // 网络用户
        public static final int   EM_ARMMODECHANGE_TRIGGERMODE_KEYBOARD = 2; // 键盘
        public static final int   EM_ARMMODECHANGE_TRIGGERMODE_REMOTECONTROL = 3; // 遥控器
    }

    //防区类型
    public static class EM_ARMMODECHANGE_DEFENCEAREATYPE extends SdkStructure
    {
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_UNKNOWN = 0; // 未知
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_INTIME = 1; // 及时
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_DELAY = 2; // 延时
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_FULLDAY = 3; // 全天
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_FOLLOW = 4; // 跟随
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_MEDICAL = 5; // 医疗紧急
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_PANIC = 6; // 恐慌
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_FIRE = 7; // 火警
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_FULLDAYSOUND = 8; // 全天有声
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_FULLDAYSILENT = 9; // 全天无声
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_ENTRANCE1 = 10; // 出入防区1
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_ENTRANCE2 = 11; // 出入防区2
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_INSIDE = 12; // 内部防区
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_OUTSIDE = 13; // 外部防区
        public static final int   EM_ARMMODECHANGE_DEFENCEAREATYPE_PEOPLEDETECT = 14; // 人员检测
    }

    // 事件类型NET_ALARM_DEFENCE_ARMMODECHANGE (防区布撤防状态改变事件)
    public static class ALARM_DEFENCE_ARMMODECHANGE_INFO extends SdkStructure
    {
        public int              emDefenceStatus;                      // 布撤防状态，详见EM_DEFENCEMODE
        public int              nDefenceID;                           // 防区号
        public NET_TIME_EX      stuTime;                              // 时间
        public int              emTriggerMode;                        // 触发方式，详见EM_ARMMODECHANGE_TRIGGERMODE
        public int              emDefenceAreaType;                    // 防区类型，详见EM_ARMMODECHANGE_DEFENCEAREATYPE
        public int              nID;                                  // 遥控器编号或键盘地址
        public int              nAlarmSubSystem;                      // 子系统号
        public byte[]           szName = new byte[64];                // 防区名称
        public byte[]           szNetClientAddr = new byte[64];       // 用户IP或网络地址
        public byte[]           reserved = new byte[368];             // 预留
    }

    // 工作状态
    public static class EM_SUBSYSTEMMODE extends SdkStructure
    {
        public static final int   EM_SUBSYSTEMMODE_UNKNOWN = 0;         // "unknown"   未知
        public static final int   EM_SUBSYSTEMMODE_ACTIVE = 1;          // "active"    激活
        public static final int   EM_SUBSYSTEMMODE_INACTIVE = 2;        // "inactive"  未激活
        public static final int   EM_SUBSYSTEMMODE_UNDISTRIBUTED = 3;   // "undistributed" 未分配
        public static final int   EM_SUBSYSTEMMODE_ALLARMING = 4;       // "AllArming" 全部布防
        public static final int   EM_SUBSYSTEMMODE_ALLDISARMING = 5;    // "AllDisarming" 全部撤防
        public static final int   EM_SUBSYSTEMMODE_PARTARMING = 6;      // "PartArming" 部分布防
    }

    //触发方式
    public static class EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE extends SdkStructure
    {
        public static final int   EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE_UNKNOWN = 0; // 未知
        public static final int   EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE_NET = 1; // 网络用户
        public static final int   EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE_KEYBOARD = 2; // 键盘
        public static final int   EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE_REMOTECONTROL = 3; // 遥控器
    }

    // 事件类型 NET_ALARM_SUBSYSTEM_ARMMODECHANGE (子系统布撤防状态改变事件)
    public static class ALARM_SUBSYSTEM_ARMMODECHANGE_INFO extends SdkStructure
    {
        public int              emSubsystemMode;                      // 布撤防状态 (只支持AllArming，AllDisarming，PartArming三种状态)，详见EM_SUBSYSTEMMODE
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public byte[]           szSubSystemname = new byte[64];       // 子系统名称
        public int              nSubSystemID;                         // 子系统编号
        public int              emTriggerMode;                        // 触发方式，详见EM_SUBSYSTEM_ARMMODECHANGE_TRIGGERMODE
        public int              nID;                                  // 键盘或遥控器地址
        public byte[]           szNetClientAddr = new byte[64];       // 网络用户IP地址或网络地址
        public byte[]           reserved = new byte[440];             // 预留
    }

    // 立体视觉站立事件区域内人员列表
    public static class MAN_STAND_LIST_INFO extends SdkStructure
    {
        public NET_POINT        stuCenter;                            // 站立人员所在位置,8192坐标系
        public byte[]           szSerialUUID = new byte[22];          // 智能物体全局唯一物体标识
        // 有效数据位21位，包含’\0’
        // 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
        // 中间14位YYYYMMDDhhmmss:年月日时分秒
        // 后5位%u%u%u%u%u：物体ID，如00001
        public DH_RECT          stuBoundingBox;                       // 包围盒
        public byte[]           szReversed = new byte[90];            // 保留字节
    }

    // 事件类型EVENT_IVS_MAN_STAND_DETECTION(立体视觉站立事件)对应数据块描述信息
    public static class DEV_EVENT_MANSTAND_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐,非保留字节
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        ///////////////////////////////以上为公共字段，除nChannelID外的其他字段是为了预留公共字段空间//////////////////////////////
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public int              nManListCount;                        // 区域人员列表数量
        public MAN_STAND_LIST_INFO[] stuManList = new MAN_STAND_LIST_INFO[MAX_MAN_LIST_COUNT]; // 区域内人员列表
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szReversed = new byte[2048];          // 保留字节

        public DEV_EVENT_MANSTAND_DETECTION_INFO()
        {
            for (int i = 0; i < stuManList.length; ++i) {
                stuManList[i] = new MAN_STAND_LIST_INFO();
            }
        }
    }

    // 课堂行为动作类型
    public static class EM_CLASSROOM_ACTION extends SdkStructure
    {
        public static final int   EM_CLASSROOM_ACTION_UNKNOWN = 0;      // 未知
        public static final int   EM_CLASSROOM_ACTION_PLAY_PHONE = 1;   // 玩手机
        public static final int   EM_CLASSROOM_ACTION_HANDSUP = 2;      // 举手
        public static final int   EM_CLASSROOM_ACTION_LISTEN = 3;       // 听讲
        public static final int   EM_CLASSROOM_ACTION_READ_WRITE = 4;   // 读写
        public static final int   EM_CLASSROOM_ACTION_TABLE = 5;        // 趴桌子
    }

    // 事件类型 EVENT_IVS_CLASSROOM_BEHAVIOR (课堂行为分析事件) 对应的数据块描述信息
    public static class DEV_EVENT_CLASSROOM_BEHAVIOR_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              emClassType;                          // 智能事件所属大类，详见EM_SCENE_CLASS_TYPE
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public int              nObjectID;                            // 物体ID
        public int              nSequence;                            // 帧序号
        public int              emClassroomAction;                    // 课堂行为动作，详见EM_CLASSROOM_ACTION
        public NET_POINT[]      stuDetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public int              nPresetID;                            // 事件触发的预置点号
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置点名称
        public byte[]           szSerialUUID = new byte[22];          // 智能物体全局唯一物体标识
        // 格式如下：前2位%d%d:01-视频片段,02-图片,03-文件,99-其他;
        //中间14位YYYYMMDDhhmmss:年月日时分秒;后5位%u%u%u%u%u：物体ID，如00001
        public byte[]           byReserved1 = new byte[2];            // 用于字节对齐
        public NET_RECT         stuBoundingBox;                       // 包围盒
        public NET_INTELLIGENCE_IMAGE_INFO stuSceneImage;             // 人脸底图信息
        public NET_INTELLIGENCE_IMAGE_INFO stuFaceImage;              // 人脸小图信息
        public NET_FACE_ATTRIBUTE_EX stuFaceAttributes;               // 人脸属性
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        public byte             byReserved[] = new byte[1024];        //预留字节

        public DEV_EVENT_CLASSROOM_BEHAVIOR_INFO()
        {
            for (int i = 0; i < stuDetectRegion.length; ++i) {
                stuDetectRegion[i] = new NET_POINT();
            }
        }
    }

    // 抓拍类型
    public static class NET_EM_SNAP_SHOT_TYPE extends SdkStructure
    {
        public static final int   NET_EM_SNAP_SHOT_TYPE_UNKNOWN = 0;    // 未知
        public static final int   NET_EM_SNAP_SHOT_TYPE_NEAR = 1;       // 近景
        public static final int   NET_EM_SNAP_SHOT_TYPE_MEDIUM = 2;     // 中景
        public static final int   NET_EM_SNAP_SHOT_TYPE_FAR = 3;        // 远景
        public static final int   NET_EM_SNAP_SHOT_TYPE_FEATURE = 4;    // 车牌特写
    }

    // 抓拍间隔模式
    public static class NET_EM_SNAP_SHOT_INTERVAL_MODE extends SdkStructure
    {
        public static final int   NET_EM_SNAP_SHOT_INTERVAL_UNKNOWN = 0; // 未知
        public static final int   NET_EM_SNAP_SHOT_INTERVAL_TIME = 1;   // 按固定时间间隔，该模式下nSingleInterval有效
        public static final int   NET_EM_SNAP_SHOT_INTERVAL_FRAMEADAPTSPEED = 2; // 速度自适应帧间隔
        public static final int   NET_EM_SNAP_SHOT_INTERVAL_FRAME = 3;  // 固定帧间隔
    }

    // 规则集抓拍参数
    public static class NET_SNAP_SHOT_WITH_RULE_INFO extends SdkStructure
    {
        public int              nRuleId;
        public int              dwRuleType;                           // 规则类型，详见dhnetsdk.h中"智能分析事件类型"
        public int              nSnapShotNum;                         // 抓拍图片张数
        public int[]            emSnapShotType = new int[MAX_SNAP_SHOT_NUM]; // 抓拍图片类型数组，详见NET_EM_SNAP_SHOT_TYPE
        public int[]            nSingleInterval = new int[MAX_SNAP_SHOT_NUM]; // 抓图时间间隔数组,单位秒，数组第一个时间:5~180 默认10， 其余时间(N张抓拍有N-1个间隔时):1~3600 默认20
        public int              emIntervalMode;                       // 抓拍间隔模式，详见NET_EM_SNAP_SHOT_INTERVAL_MODE
        public byte[]           byReserved = new byte[1024];          // 预留
    }

    // 抓拍参数
    public static class NET_SCENE_SNAP_SHOT_WITH_RULE2_INFO extends SdkStructure
    {
        public int              nPresetID;                            // 场景预置点号
        public int              nRetSnapShotRuleNum;                  // stuSnapShotWithRule中有效数据个数
        public NET_SNAP_SHOT_WITH_RULE_INFO[] stuSnapShotWithRule = new NET_SNAP_SHOT_WITH_RULE_INFO[32]; // 规则集抓拍参数
        public byte[]           byReserved = new byte[1024];          // 预留

        public NET_SCENE_SNAP_SHOT_WITH_RULE2_INFO()
        {
            for (int i = 0; i < stuSnapShotWithRule.length; ++i) {
                stuSnapShotWithRule[i] = new NET_SNAP_SHOT_WITH_RULE_INFO();
            }
        }
    }

    // 场景抓拍设置 对应枚举 NET_EM_CFG_SCENE_SNAP_SHOT_WITH_RULE2
    public static class NET_CFG_SCENE_SNAP_SHOT_WITH_RULE2_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRuleNum;                          // pstuSnapShotWithRule中用户分配的内存个数
        public int              nRetRuleNum;                          // pstuSnapShotWithRule中实际有效的数据个数
        public Pointer          pstuSceneSnapShotWithRule;            // 抓拍参数,由用户分配和释放内存，大小为nMaxRuleNum * sizeof(NET_SCENE_SNAP_SHOT_WITH_RULE2_INFO)，指向NET_SCENE_SNAP_SHOT_WITH_RULE2_INFO

        public NET_CFG_SCENE_SNAP_SHOT_WITH_RULE2_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 车辆动作
    public static class EM_VEHICLE_ACTION extends Structure {
        public static final int   EM_VEHICLE_ACTION_UNKNOWN = 0;        // 未知
        public static final int   EM_VEHICLE_ACTION_APPEAR = 1;         // "Appear"在检测区域内
        public static final int   EM_VEHICLE_ACTION_DISAPPEAR = 2;      // "Disappear"离开检测区域
    }

    // 检测到的车辆信息
    public static class NET_DETECT_VEHICLE_INFO extends SdkStructure {
        public int              emAction;                             // 检测车辆动作
        public int /*UINT*/     nObjectID;                            // 物体ID
        public EVENT_PIC_INFO   stuVehicleImage;                      // 车辆抓图信息
        public NET_COLOR_RGBA   stuColor;                             // 车身主要颜色
        public int              emCategoryType;                       // 车辆类型,参考枚举EM_CATEGORY_TYPE
        public int /*UINT*/     nFrameSequence;                       // 帧序号
        public int /*UINT*/     nCarLogoIndex;                        // 车辆车标
        public int /*UINT*/     nSubBrand;                            // 车辆子品牌
        public int /*UINT*/     nBrandYear;                           // 车辆品牌年款
        public int /*UINT*/     nConfidence;                          // 置信度,值越大表示置信度越高, 范围 0~255
        public NET_RECT         stuBoundingBox;                       // 包围盒, 0-8191相对坐标
        public byte[]           szText = new byte[128];               // 车标
        public int /*UINT*/     nSpeed;                               // 车速,单位为km/h
        public int /*UINT*/     nDirection;                           // 车辆行驶方向, 0:未知, 1:上行方向, 2:下行方向
        public byte[]           bReserved = new byte[512];            // 保留字节
    }

    // 检测的车牌信息
    public static class NET_DETECT_PLATE_INFO extends SdkStructure {
        public int /*UINT*/     nObjectID;                            // 车牌ID
        public int /*UINT*/     nRelativeID;                          // 关联的车辆ID
        public EVENT_PIC_INFO   stuPlateImage;                        // 车牌图片信息
        public int              emPlateType;                          // 车牌类型，参考枚举EM_NET_PLATE_TYPE
        public int              emPlateColor;                         // 车牌颜色，参考枚举EM_NET_PLATE_COLOR_TYPE
        public int /*UINT*/     nConfidence;                          // 置信度,值越大表示置信度越高, 范围 0~255
        public byte[]           szCountry = new byte[3];              // 车牌国家
        public byte             bReserved1;                           // 字节对齐
        public byte[]           szPlateNumber = new byte[128];        // 车牌号码
        public byte[]           bReserved = new byte[512];            // 保留字节
    }

    // 加油站车辆检测事件 (对应 DEV_EVENT_GASSTATION_VEHICLE_DETECT_INFO)
    public static class DEV_EVENT_GASSTATION_VEHICLE_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲1:开始 2:停止
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public int              emClassType;                          // 智能事件所属大类
        public NET_DETECT_VEHICLE_INFO stuDetectVehicleInfo;          // 检测到的车辆信息
        public NET_DETECT_PLATE_INFO stuDetectPlateInfo;              // 检测到的车牌信息
        public int              bIsGlobalScene;                       // 是否有场景图
        public EVENT_PIC_INFO   stuSceneImage;                        // 场景图信息, bIsGlobalScene 为 TRUE 时有效
        public int              nCarCandidateNum;                     // 候选车辆数量
        public NET_CAR_CANDIDATE_INFO[] stuCarCandidate = (NET_CAR_CANDIDATE_INFO[])new NET_CAR_CANDIDATE_INFO().toArray(MAX_CAR_CANDIDATE_NUM); // 候选车辆数据
        /*public boolean                  bIsEmptyPlace;                              // 是否是空车位报警*/
        public NET_FUEL_DISPENSER_INFO stuFuelDispenser;              // 从加油机获取的信息，IVSS对接加油机及N8000
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public byte[]           bReserved = new byte[872];            // 保留字节
    }

    // 事件级别，GB30147需求
    public static class EM_EVENT_LEVEL extends Structure
    {
        public static final int   EM_EVENT_LEVEL_HINT = 0;              // 提示
        public static final int   EM_EVENT_LEVEL_GENERAL = 1;           // 普通
        public static final int   EM_EVENT_LEVEL_WARNING = 2;           // 警告
    }

    // 事件类型EVENT_IVS_SHOPPRESENCE(商铺占道经营事件)对应的数据块描述信息
    public static class DEV_EVENT_SHOPPRESENCE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体，推荐使用字段stuObjects获取物体信息
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置点名称
        public int              emEventLevel;                         // 事件级别，GB30147需求
        public byte[]           szShopAddress = new byte[256];        // 商铺地址
        public int              nViolationDuration;                   // 违法持续时长，单位：秒，缺省值0表示无意义
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjects = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(HDBJ_MAX_OBJECTS_NUM); // 检测到的物体
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public Pointer          pstuImageInfo;                        // 图片信息数组,指针对应NET_IMAGE_INFO_EX2数组
        public int              nImageInfoNum;                        // 图片信息个数
        public SCENE_IMAGE_INFO_EX stuSceneImage = new SCENE_IMAGE_INFO_EX(); // 全景图图片信息,事件前2~5s抓图
        public Pointer          pstuMosaicImage;                      // 合成图,指针对应SCENE_IMAGE_INFO_EX数组
        public int              nMosaicImageNum;                      // 合成图个数
        public Pointer          pstuAdvanceImage;                     // 事件发生前抓图，指针对应SCENE_IMAGE_INFO_EX数组
        public int              nAdvanceImageNum;                     // 事件发生前抓图个数
        public int              nVehicleSpeed;                        //车速, 单位km/h
        public double           dbHeadingAngle;                       //航向角, 以正北方向为基准输出车辆运动方向同正北方向的角度:范围 0~360,顺时针正,单位为度
        public double[]         dbLongitude = new double[3];          //经度,格式:度,分,秒(秒为浮点数)
        public double[]         dbLatitude = new double[3];           //纬度,格式:度,分,秒(秒为浮点数)
        public byte[]           byReserved2 = new byte[1188-POINTERSIZE*3]; // 保留字节
    }

    // 事件类型 EVENT_IVS_FLOWBUSINESS (流动摊贩事件) 对应的数据块描述信息
    public static class DEV_EVENT_FLOWBUSINESS_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nDetectRegionNum;                     // 检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 检测区域
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nViolationDuration;                   // 违法持续时长，单位：秒 缺省值0表示无意义
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjects = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(HDBJ_MAX_OBJECTS_NUM); // 检测到的物体
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public byte[]           byReserved = new byte[2044];          // 保留字节
    }

    // 立体视觉区域内人数统计事件区域人员列表
    public static class MAN_NUM_LIST_INFO extends SdkStructure
    {
        public DH_RECT          stuBoudingBox;                        // 人员包围盒,8192坐标系
        public int              nStature;                             // 人员身高，单位cm
        public byte[]           szReversed = new byte[128];           // 保留字节
    }

    /**
     * @author 260611
     * @description 事件类型EVENT_IVS_MAN_NUM_DETECTION(立体视觉区域内人数统计事件)对应数据块描述信息
     * @date 2023/01/10 19:44:49
     */
    public class DEV_EVENT_MANNUM_DETECTION_INFO extends SdkStructure {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐, 非保留字节
         */
        public byte[]           bReserved1 = new byte[4];
        /**
         * 时间戳(单位是毫秒)
         */
        public double           PTS;
        /**
         * 事件发生的时间
         */
        public org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX UTC = new org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 0:脉冲 1:开始 2:停止
         */
        public int              nAction;
        /**
         * 区域人员列表数量
         */
        public int              nManListCount;
        /**
         * 区域内人员列表
         */
        public MAN_NUM_LIST_INFO[] stuManList = new MAN_NUM_LIST_INFO[64];
        /**
         * 智能事件公共信息
         */
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo = new EVENT_INTELLI_COMM_INFO();
        /**
         * 区域ID(一个预置点可以对应多个区域ID)
         */
        public int              nAreaID;
        /**
         * 变化前人数
         */
        public int              nPrevNumber;
        /**
         * 当前人数
         */
        public int              nCurrentNumber;
        /**
         * 事件关联ID。应用场景是同一个物体或者同一张图片做不同分析，产生的多个事件的SourceID相同
         * 缺省时为空字符串，表示无此信息
         * 格式：类型+时间+序列号，其中类型2位，时间14位，序列号5位
         */
        public byte[]           szSourceID = new byte[32];
        /**
         * null
         */
        public byte[]           szRuleName = new byte[128];
        /**
         * 检测模式 {@link EM_EVENT_DETECT_TYPE}
         */
        public int              emDetectType;
        /**
         * 实际触发报警的人数
         */
        public int              nAlertNum;
        /**
         * 报警类型. 0:未知, 1:从人数正常到人数异常, 2:从人数异常到人数正常
         */
        public int              nAlarmType;
        /**
         * 图片信息数组
         */
        public Pointer          pstuImageInfo;
        /**
         * 图片信息个数
         */
        public int              nImageInfoNum;
        /**
         * 事件公共扩展字段结构体
         */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
        /**
         * 检测区个数
         */
        public int              nDetectRegionNum;
    	/**
    	 * 检测区
    	 */
        public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[20];
        /**
         * 保留字节
         */
        public byte[]           szReversed = new byte[700];

        public DEV_EVENT_MANNUM_DETECTION_INFO() {
            for (int i = 0; i < stuManList.length; i++) {
                stuManList[i] = new MAN_NUM_LIST_INFO();
            }
            for (int i = 0; i < stuDetectRegion.length; i++) {
            	stuDetectRegion[i] = new NET_POINT_EX();
    		}
        }
    }

    public static class EM_ALARM_TYPE extends SdkStructure
    {
        public static final int   EM_ALARM_TYPE_UNKNOWN = 0;            // 未知类型
        public static final int   EM_ALARM_TYPE_CROWD_DENSITY = 1;      // 拥挤人群密度报警
        public static final int   EM_ALARM_TYPE_NUMBER_EXCEED = 2;      // 人数超限报警
        public static final int   EM_ALARM_TYPE_CROWD_DENSITY_AND_NUMBER_EXCEED = 3; // 拥挤人群密度报警和人数超限报警
    }

    // 全局拥挤人群密度列表(圆形)信息
    public static class NET_CROWD_LIST_INFO extends SdkStructure
    {
        public NET_POINT        stuCenterPoint = new NET_POINT();     // 中心点坐标,8192坐标系
        public int              nRadiusNum;                           // 半径像素点个数
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 人数超限的报警区域ID列表信息
    public static class NET_REGION_LIST_INFO extends SdkStructure
    {
        public int              nRegionID;                            // 配置的区域下标
        public int              nPeopleNum;                           // 区域内人数统计值
        public byte[]           szName = new byte[32];                // 配置的名称
        public DH_POINT[]       stuDetectRegion = new DH_POINT[20];   // 配置的检测区域坐标
        public int              nDetectRegionNum;                     // 配置的检测区域坐标个数
        public byte[]           byReserved = new byte[908];           // 保留字节
    }

    // 全局拥挤人群密度列表(矩形)信息
    public static class NET_CROWD_RECT_LIST_INFO extends SdkStructure
    {
        public NET_POINT[]      stuRectPoint = (NET_POINT[])new NET_POINT().toArray(RECT_POINT); // 矩形的左上角点与右下角点,8192坐标系，表示矩形的人群密度矩形框
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    // 事件类型 EVENT_IVS_CROWDDETECTION(人群密度检测事件）对应的数据块描述信息
    public static class DEV_EVENT_CROWD_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventAction;                         // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public int              emAlarmType;                          // 报警业务类型
        public byte[]           szName = new byte[MAX_CROWD_DETECTION_NAME_LEN]; // 事件名称
        public int              nCrowdListNum;                        // 返回的全局拥挤人群密度列表个数 （圆形描述）
        public int              nRegionListNum;                       // 返回的人数超限的报警区域ID列表个数
        public NET_CROWD_LIST_INFO[] stuCrowdList = new NET_CROWD_LIST_INFO[MAX_CROWD_LIST_NUM]; // 全局拥挤人群密度列表信息（圆形描述）
        public NET_REGION_LIST_INFO[] stuRegionList = new NET_REGION_LIST_INFO[MAX_REGION_LIST_NUM]; // 人数超限的报警区域ID列表信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public int              nCrowdRectListNum;                    // 返回的全局拥挤人群密度列表个数 (矩形描述)
        public NET_CROWD_RECT_LIST_INFO[] stuCrowdRectList = new NET_CROWD_RECT_LIST_INFO[MAX_CROWD_RECT_LIST]; // 全局拥挤人群密度列表信息(矩形描述)
        public int              nGlobalPeopleNum;                     // 检测区全局总人数
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[680];           // 保留扩展字节
    }

    // 人群密度检测事件(对应事件NET_ALARM_CROWD_DETECTION)
    public static class ALARM_CROWD_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventAction;                         // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public int              emAlarmType;                          // 报警业务类型
        public byte[]           szName = new byte[MAX_CROWD_DETECTION_NAME_LEN]; // 事件名称
        public int              nCrowdListNum;                        // 返回的全局拥挤人群密度列表个数
        public int              nRegionListNum;                       // 返回的人数超限的报警区域ID列表个数
        public NET_CROWD_LIST_INFO[] stuCrowdList = new NET_CROWD_LIST_INFO[MAX_CROWD_LIST_NUM]; // 全局拥挤人群密度列表信息
        public NET_REGION_LIST_INFO[] stuRegionList = new NET_REGION_LIST_INFO[MAX_REGION_LIST_NUM]; // 人数超限的报警区域ID列表信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[896];           // 保留扩展字节
    }

    // 对象目标类型
    public static class EM_OBJECT_TYPE extends SdkStructure
    {
        public static final int   EM_OBJECT_TYPE_UNKNOWN = -1;          // 未知
        public static final int   EM_OBJECT_TYPE_FACE = 0;              // 目标
        public static final int   EM_OBJECT_TYPE_HUMAN = 1;             // 人体
        public static final int   EM_OBJECT_TYPE_VECHILE = 2;           // 机动车
        public static final int   EM_OBJECT_TYPE_NOMOTOR = 3;           // 非机动车
        public static final int   EM_OBJECT_TYPE_ALL = 4;               // 所有类型
    }

    // CLIENT_StartMultiFindFaceRecognition 接口输入参数
    public static class NET_IN_STARTMULTIFIND_FACERECONGNITION extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pChannelID;                           // 通道号
        public int              nChannelCount;                        //  通道申请个数
        public int              bPersonEnable;                        // 人员信息查询条件是否有效
        public FACERECOGNITION_PERSON_INFO stPerson;                  // 人员信息查询条件
        public NET_FACE_MATCH_OPTIONS stMatchOptions;                 // 人脸匹配选项
        public NET_FACE_FILTER_CONDTION stFilterInfo;                 // 查询过滤条件
        // 图片二进制数据
        public Pointer          pBuffer;                              // 缓冲地址
        public int              nBufferLen;                           // 缓冲数据长度
        public int              bPersonExEnable;                      // 人员信息查询条件是否有效, 并使用人员信息扩展结构体
        public FACERECOGNITION_PERSON_INFOEX stPersonInfoEx;          // 人员信息扩展
        public int              emObjectType;                         // 搜索的目标类型,参考EM_OBJECT_TYPE
        public int              nChannelNum;                          // 通道有效个数
        public byte[]           szChannelString = new byte[512*32];   // 通道号(使用)
        public int              nProcessType;                         // 以图搜图类型, -1: 未知, 0: 特征值搜索, 1: SMD属性特征搜索
        public int              bIsUsingTaskID;                       // 是否使能订阅的TaskID字段
        public int              nTaskIDNum;                           // 订阅的TaskID数组有效个数
        public int[]            nTaskID = new int[128];               // 订阅的TaskID, bIsUsingTaskID为TRUE,nTaskIDNum为0表示订阅所有任务结果

        public NET_IN_STARTMULTIFIND_FACERECONGNITION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartMultiFindFaceRecognition 接口输出参数
    public static class NET_OUT_STARTMULTIFIND_FACERECONGNITION extends SdkStructure
    {
        public int              dwSize;
        public int              nTotalCount;                          // 返回的符合查询条件的记录个数
        // -1表示总条数未生成,要推迟获取
        // 使用CLIENT_AttachFaceFindState接口状态
        public LLong            lFindHandle;                          // 查询句柄
        public int              nToken;                               // 获取到的查询令牌

        public NET_OUT_STARTMULTIFIND_FACERECONGNITION() {
            this.dwSize = this.size();
        }
    }

    // 开始目标检测/注册库的多通道查询
    public Boolean CLIENT_StartMultiFindFaceRecognition(LLong lLoginID,NET_IN_STARTMULTIFIND_FACERECONGNITION pstInParam,NET_OUT_STARTMULTIFIND_FACERECONGNITION pstOutParam,int nWaitTime);

    // 事件类型 EVENT_IVS_PEDESTRIAN_JUNCTION (行人卡口事件) 对应的数据块描述信息
    public static class DEV_EVENT_PEDESTRIAN_JUNCTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nGroupID;                             // 事件组ID, 同一个人抓拍过程内nGroupID相同
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号，从1开始
        public double           PTS;                                  // 事件戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              UTCMS;                                // UTC时间对应的毫秒数
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_MSG_OBJECT   stuObject;                            // 人脸信息
        public int              nLane;                                // 人行道号
        public int              nSequence;                            // 表示抓拍序号,如3/2/1,1表示抓拍结束,0表示异常结束
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车信息
        public int              bHasNonMotor;                         // stuNonMotor 字段是否有效
        public NET_MSG_OBJECT   stuVehicle;                           // 行人信息
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[344-2*POINTERSIZE]; // 保留字节
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    // 多人多开门方式组合(对应 CFG_CMD_OPEN_DOOR_GROUP 命令),表示每个通道的组合信息，
    // 第一个通道的组合的开门优先级最高，后面依次递减
    public static class CFG_OPEN_DOOR_GROUP_INFO extends SdkStructure {
        public int              nGroup;                               // 有效组合数
        public CFG_OPEN_DOOR_GROUP[] stuGroupInfo = new CFG_OPEN_DOOR_GROUP[CFG_MAX_OPEN_DOOR_GROUP_NUM]; // 多人开门组合信息
        public int              nGroupMaxNum;                         // 组合总数，指明pGroupInfoEx实际内存空间大小，获取和下发均需要用户赋值,非0时nGroup、stuGroupInfo字段不生效
        public int              nGroupRetNum;                         // 实际有效的组合数，获取时由动态库赋值，下发时由用户赋值
        public Pointer          pGroupInfoEx;                         //多人开门组合信息扩展，内存有用户申请,指针对应CFG_OPEN_DOOR_GROUP数组
        public byte[]           szReserved = new byte[2048];          // 保留字节
    }

    // 多人组合开门组信息
    public static class CFG_OPEN_DOOR_GROUP extends SdkStructure {
        public int              nUserCount;                           // 用户数目，表示需要组合才能开门的人数
        public int              nGroupNum;                            // 有效组数目
        public CFG_OPEN_DOOR_GROUP_DETAIL[] stuGroupDetail = new CFG_OPEN_DOOR_GROUP_DETAIL[CFG_MAX_OPEN_DOOR_GROUP_DETAIL_NUM]; // 多人组合开门组的详细信息
        public Boolean          bGroupDetailEx;                       // TRUE: stuGroupDetail
        // 字段无效、pstuGroupDetailEx字段有效, FALSE:
        // stuGroupDetail
        // 字段有效、pstuGroupDetailEx字段无效
        public int              nMaxGroupDetailNum;                   // 多人组合开门组的详细信息最大个数
        public Pointer          pstuGroupDetailEx;                    /*
         * 多人组合开门组的详细信息扩展, 由用户申请内存,
         * 大小为sizeof(CFG_OPEN_DOOR_GROUP_DETAIL
         * )*nMaxUserCount, 当多人组合开门组的详细信息个数大于
         * CFG_MAX_OPEN_DOOR_GROUP_DETAIL_NUM
         * 时使用此字段
         */
    }

    // 多人组合开门组详细信息
    public static class CFG_OPEN_DOOR_GROUP_DETAIL extends SdkStructure {
        public byte[]           szUserID = new byte[CFG_MAX_USER_ID_LEN]; // 用户ID
        public int              emMethod;                             // 开门方式
        public int              nMethodExNum;                         // 开门方式扩展个数
        public int[]            emMethodEx = new int[CFG_MAX_METHODEX_NUM]; // 开门方式扩展
        public int              emCombineMethod;                      //多人开门支持任意组合开门方式,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.CFG_DOOR_OPEN_METHOD}
        public byte[]           szReserved = new byte[1020];          //预留字段
    }

    public static class EM_CFG_OPEN_DOOR_GROUP_METHOD extends SdkStructure {
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_UNKNOWN = 0;
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_CARD = 1; // 刷卡
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_PWD = 2; // 密码
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_FINGERPRINT = 3; // 信息
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_ANY = 4; // 任意组合方式开门
        public static final int   EM_CFG_OPEN_DOOR_GROUP_METHOD_FACE = 5; // 人脸
    }

    // 开始查找X光机包裹信息
    public  LLong CLIENT_StartFindXRayPkg(LLong lLoginID,NET_IN_START_FIND_XRAY_PKG pInParam,NET_OUT_START_FIND_XRAY_PIC pOutParam,int nWaitTime);

    // 查询X光机包裹的信息
    public  Boolean CLIENT_DoFindXRayPkg(LLong lFindID,NET_IN_DO_FIND_XRAY_PKG pInParam,NET_OUT_DO_FIND_XRAY_PKG pOutParam,int nWaitTime);

    // 结束查询X光机包裹的信息
    public  Boolean CLIENT_StopFindXRayPkg(LLong lFindID);

    // 物品类型
    public static class EM_INSIDE_OBJECT_TYPE
    {
        public static final int   EM_INSIDE_OBJECT_UNKNOWN = 0;         // 算法未识别物品
        public static final int   EM_INSIDE_OBJECT_KNIFE = 1;           // 刀具
        public static final int   EM_INSIDE_OBJECT_BOTTLELIQUID = 2;    // 瓶装液体
        public static final int   EM_INSIDE_OBJECT_GUN = 3;             // 枪支
        public static final int   EM_INSIDE_OBJECT_UMBRELLA = 4;        // 雨伞
        public static final int   EM_INSIDE_OBJECT_PHONE = 5;           // 手机
        public static final int   EM_INSIDE_OBJECT_NOTEBOOK = 6;        // 笔记本
        public static final int   EM_INSIDE_OBJECT_POWERBANK = 7;       // 充电宝
        public static final int   EM_INSIDE_OBJECT_SHOES = 8;           // 鞋子
        public static final int   EM_INSIDE_OBJECT_ROD = 9;             // 杠子
        public static final int   EM_INSIDE_OBJECT_METAL = 10;          // 金属
        public static final int   EM_INSIDE_OBJECT_EXPLOSIVE = 11;      // 爆炸物
        public static final int   EM_INSIDE_OBJECT_CONTAINERSPRAY = 12; // 喷雾喷灌
        public static final int   EM_INSIDE_OBJECT_EXPLOSIVE_FIREWORKS = 13; // 烟花爆竹
        public static final int   EM_INSIDE_OBJECT_LIGHTER = 14;        // 打火机
        public static final int   EM_INSIDE_OBJECT_STICK = 15;          // 警棍
        public static final int   EM_INSIDE_OBJECT_BRASSKNUCKLE = 16;   // 指虎
        public static final int   EM_INSIDE_OBJECT_HANDCUFFS = 17;      // 手铐
        public static final int   EM_INSIDE_OBJECT_IVORY = 18;          // 象牙
        public static final int   EM_INSIDE_OBJECT_BOOK = 19;           // 书籍
        public static final int   EM_INSIDE_OBJECT_CD = 20;             // 光盘
        public static final int   EM_INSIDE_OBJECT_HAMMERS = 21;        // 锤子
        public static final int   EM_INSIDE_OBJECT_PLIERS = 22;         // 钳子
        public static final int   EM_INSIDE_OBJECT_AXE = 23;            // 斧头
        public static final int   EM_INSIDE_OBJECT_SCREW_DRIVER = 24;   // 螺丝刀
        public static final int   EM_INSIDE_OBJECT_WRENCH = 25;         // 扳手
        public static final int   EM_INSIDE_OBJECT_ELECTRIC_SHOCK_STICK = 26; // 电击棍
        public static final int   EM_INSIDE_OBJECT_THERMOS = 27;        // 保温杯
        public static final int   EM_INSIDE_OBJECT_GLASS_BOTTLES = 28;  // 玻璃杯
        public static final int   EM_INSIDE_OBJECT_PLASTIC_BOTTLE = 29; // 塑料瓶
        public static final int   EM_INSIDE_OBJECT_IGNITION_OIL = 30;   // 打火机油
        public static final int   EM_INSIDE_OBJECT_NAIL_POLISH = 31;    // 指甲油
        public static final int   EM_INSIDE_OBJECT_BLUNT_INSTRUMENT = 32; // 工具
        public static final int   EM_INSIDE_OBJECT_SCISSORS = 33;       // 剪刀
        public static final int   EM_INSIDE_OBJECT_ELECTRONIC = 34;     // 电子产品
        public static final int   EM_INSIDE_OBJECT_PISTOL = 35;         //	手枪
        public static final int   EM_INSIDE_OBJECT_FOLDINGKNIFE = 36;   //	折叠刀
        public static final int   EM_INSIDE_OBJECT_SHARPKNIFE = 37;     //	尖刀
        public static final int   EM_INSIDE_OBJECT_KITCHENKNIFE = 38;   //	菜刀
        public static final int   EM_INSIDE_OBJECT_UTILITYKNIFE = 39;   //	美工刀
        public static final int   EM_INSIDE_OBJECT_FIREWORKS = 40;      //	烟花
        public static final int   EM_INSIDE_OBJECT_FIRECRACKER = 41;    //	爆竹
        public static final int   EM_INSIDE_OBJECT_POWDER = 42;         //	粉末
        public static final int   EM_INSIDE_OBJECT_IMPENETERABLE_MATERALS = 43; //难穿透物品
        public static final int   EM_INSIDE_OBJECT_CIGARETTE = 44;      //香烟
        public static final int   EM_INSIDE_OBJECT_BATTERY = 45;        //电池
        public static final int   EM_INSIDE_OBJECT_GUNPARTS = 46;       //零部件
        public static final int   EM_INSIDE_OBJECT_MATCH = 47;          //火柴
        public static final int   EM_INSIDE_OBJECT_GUNGRIP = 48;        //握把
        public static final int   EM_INSIDE_OBJECT_GUNMAGAZINE = 49;    //弹夹
        public static final int   EM_INSIDE_OBJECT_GUNSLEEVE = 50;      //套筒
        public static final int   EM_INSIDE_OBJECT_GUNBARREL = 51;      //枪管
        public static final int   EM_INSIDE_OBJECT_BULLET = 52;         //子弹
        public static final int   EM_INSIDE_OBJECT_GRENADE = 53;        //手雷
        public static final int   EM_INSIDE_OBJECT_CERAMICSHEET = 54;   //陶瓷片
        public static final int   EM_INSIDE_OBJECT_GLASSSHEET = 55;     //玻璃片
        public static final int   EM_INSIDE_OBJECT_IPADBASE = 56;       //IPAD底壳
        public static final int   EM_INSIDE_OBJECT_SLINGSHOT = 57;      //弹弓
        public static final int   EM_INSIDE_OBJECT_DRUG = 58;           //毒品
        public static final int   EM_INSIDE_OBJECT_EXPLOSIVEPACKAGE = 59; //炸药包
        public static final int   EM_INSIDE_OBJECT_CELLBATTERY = 60;    //纽扣电池
        public static final int   EM_INSIDE_OBJECT_LEADBATTERY = 61;    //铅蓄电池
        public static final int   EM_INSIDE_OBJECT_METALLIGHTER = 62;   //金属打火机
        public static final int   EM_INSIDE_OBJECT_COSMETICBOTTLE = 63; //化妆瓶
        public static final int   EM_INSIDE_OBJECT_CONTAINERCAN = 64;   //易拉罐
        public static final int   EM_INSIDE_OBJECT_AIRBOTTLE = 65;      //气罐
        public static final int   EM_INSIDE_OBJECT_SQUAREKNIFE = 66;    //方刀
        public static final int   EM_INSIDE_OBJECT_WALKIETALKIE = 67;   ///// 对讲机
        public static final int   EM_INSIDE_OBJECT_ROUTER = 68;         ///// 路由器
        public static final int   EM_INSIDE_OBJECT_MICROPHONE = 69;     ///// 话筒
        public static final int   EM_INSIDE_OBJECT_UNMANED_AERIAL_VEHICLE = 70; ///// 无人机
        public static final int   EM_INSIDE_OBJECT_ELECTRICAL_RELAY = 71; ///// 继电器
        public static final int   EM_INSIDE_OBJECT_DETONATOR = 72;      ///// 雷管
        public static final int   EM_INSIDE_OBJECT_BLASTINGFUSE = 73;   ///// 导火索
        public static final int   EM_INSIDE_OBJECT_EXPLOSIVEFLUID = 74; ///// 流体爆炸物
        public static final int   EM_INSIDE_OBJECT_NAILGUN = 75;        ///// 炮钉枪
        public static final int   EM_INSIDE_OBJECT_NAIL = 76;           ///// 炮钉
        public static final int   EM_INSIDE_OBJECT_DRYBATTERY = 77;     ///// 干电池
        public static final int   EM_INSIDE_OBJECT_LITHIUMBATTERY = 78; ///// 锂电池
        public static final int   EM_INSIDE_OBJECT_SAW = 79;            ///// 锯子
        public static final int   EM_INSIDE_OBJECT_TABLEKNIFE = 80;     ///// 餐刀
        public static final int   EM_INSIDE_OBJECT_PLASTICUTTER = 81;   ///// 勾刀
        public static final int   EM_INSIDE_OBJECT_BOLT = 82;           ///// 弩箭
        public static final int   EM_INSIDE_OBJECT_CROSSBOW = 83;       ///// 弩
        public static final int   EM_INSIDE_OBJECT_SHAVER = 84;         ///// 剃须刀
        public static final int   EM_INSIDE_OBJECT_ELECTRIC_TOOTHBRUSH = 85; ///// 电动牙刷
        public static final int   EM_INSIDE_OBJECT_OILDRUM = 86;        ///// 油桶
        public static final int   EM_INSIDE_OBJECT_SAFELIQUID = 87;     // 安全液体
        public static final int   EM_INSIDE_OBJECT_SUSPECTEDLIQUID = 88; // 可疑液体
        public static final int   EM_INSIDE_OBJECT_DANGEROUSLIQUID = 89; // 危险液体
        public static final int   EM_INSIDE_OBJECT_BULLETPROOFVEST = 90; // 防弹衣
        public static final int   EM_INSIDE_OBJECT_CONTROLLEDKNIFE = 91; // 受控刀具
        public static final int   EM_INSIDE_OBJECT_BOXEDBEVERAGE = 92;  // 盒装液体
        public static final int   EM_INSIDE_OBJECT_BAGGEDBEVERAGE = 93; // 袋装液体
        public static final int   EM_INSIDE_OBJECT_GLASSGLUE = 94;      // 玻璃胶
        public static final int   EM_INSIDE_OBJECT_TOILETWATER = 95;    // 花露水
        public static final int   EM_INSIDE_OBJECT_WINEBOTTLE = 96;     // 酒瓶
    }

    // 危险等级
    public static class EM_DANGER_GRADE_TYPE extends SdkStructure
    {
        public static final int   EM_DANGER_GRADE_UNKNOWN = -1;         // 未知
        public static final int   EM_DANGER_GRADE_NORMAL = 0;           // 普通级别
        public static final int   EM_DANGER_GRADE_WARN = 1;             // 警示级别
        public static final int   EM_DANGER_GRADE_DANGER = 2;           // 危险级别
    }

    // CLIENT_StartFindXRayPkg 接口输入参数
    public static class NET_IN_START_FIND_XRAY_PKG extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emTimeOrder;                          // 查询结果按时间排序
        public NET_TIME         stuStartTime;                         // 查询的开始时间
        public NET_TIME         stuEndTime;                           // 查询的结束时间
        public int[]            nSimilarityRange = new int[2];        // 相似度范围,下标0:表示最小值, 下标1:表示最大值
        public int              nObjTypeNum;                          // 物体类型的数量
        public int[]            emObjType = new int[32];              // 物品类型,参考枚举EM_INSIDE_OBJECT_TYPE
        public int              nObjTypeCount;                        // 自定义物体类型的数量
        public NET_XRAY_INSIDE_ONJECT_TYPE[] stuObjType = (NET_XRAY_INSIDE_ONJECT_TYPE[]) new NET_XRAY_INSIDE_ONJECT_TYPE().toArray(32); // 自定义物品类型

        public NET_IN_START_FIND_XRAY_PKG()
        {
            this.dwSize = this.size();
        }
    }

    // X光机物体信息
    public static class NET_PKG_OBJECT_INFO extends SdkStructure
    {
        public int              emObjType;                            // 物品类型
        public int              emDangerGrade;                        // 物品危险等级
        public int              nSimilarity;                          // 相似度,0~100
        public byte[]           byReserved = new byte[132];           // 保留字节
    }

    // CLIENT_StartFindXRayPkg 接口输出参数
    public static class NET_OUT_START_FIND_XRAY_PIC extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTotal;                               // 包裹总数

        public NET_OUT_START_FIND_XRAY_PIC()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindXRayPkg 接口输入参数
    public static class NET_IN_DO_FIND_XRAY_PKG extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nOffset;                              // 查询偏移
        public int              nCount;                               // 需要查找的数目

        public NET_IN_DO_FIND_XRAY_PKG()
        {
            this.dwSize = this.size();
        }
    }

    // 视角信息数
    public static class NET_PKG_VIEW_INFO extends SdkStructure
    {
        public int              emViewType;                           // 视图类型
        public int              nEnergyImageLength;                   // 能量图大小 单位字节
        public byte[]           szEnergyImagePath = new byte[128];    // 能量图绝对路径
        public int              nColorImageLength;                    // 彩图大小单位字节
        public byte[]           szColorImagePath = new byte[128];     // 彩图绝对路径
        public int              nColorOverlayImageLength;             // 彩图叠加图大小单位字节
        public byte[]           szColorOverlayImagePath = new byte[128]; // 彩图叠加图绝对路径
        public NET_PKG_OBJECT_INFO[] stuObject = new NET_PKG_OBJECT_INFO[32]; // 物体数组
        public int              nObjectCount;                         // 物体数量
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // X光机的包裹信息
    public static class NET_XRAY_PKG_INFO extends SdkStructure
    {
        public NET_TIME         stuTime;                              // 包裹产生时间(含时区)
        public int              nChannelIn;                           // 关联的进口IPC通道号,从0开始,-1表示无效
        public int              nChannelOut;                          // 关联的出口IPC通道号,从0开始,-1表示无效
        public byte[]           szUser = new byte[128];               // 用户名
        public NET_PKG_VIEW_INFO[] stuViewInfo = new NET_PKG_VIEW_INFO[2]; // 视角信息数组
        public byte[]           byReserved = new byte[1024];          // 保留字节

        public NET_XRAY_PKG_INFO() {
            for (int i = 0; i < stuViewInfo.length; i ++) {
                stuViewInfo[i] = new NET_PKG_VIEW_INFO();
            }
        }
    }

    // CLIENT_DoFindXRayPkg 接口输出参数
    public static class NET_OUT_DO_FIND_XRAY_PKG extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxCount;                            // 用户指定分配结构体个数,需大于等于NET_IN_DO_FIND_XRAY_PKG的nCount
        public int              nRetCount;                            // 实际返回的查询数量
        public Pointer          pstuXRayPkgInfo;                      // X光机的包裹信息,缓存大小由用户指定

        public NET_OUT_DO_FIND_XRAY_PKG()
        {
            this.dwSize = this.size();
        }
    }

    //事件类型	EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING(车位有车事件)对应的规则配置
    public static class CFG_TRAFFIC_PARKINGSPACEPARKING_INFO extends SdkStructure
    {
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能
        public byte[]           bReserved = new byte[3];              // 保留字段
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        // public CFG_TIME_SECTION[]	stuTimeSection=new CFG_TIME_SECTION[WEEK_DAY_NUM*MAX_REC_TSECT_EX];			// 事件响应时间段
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public int              nLane;                                // 车位号
        public int              nDelayTime;                           // 检测到报警发生到开始上报的时间, 单位：秒，范围1~65535
        public int              nDetectRegionPoint;                   // 检测区域顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[]) new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区域
        public int              nPlateSensitivity;                    // 有牌检测灵敏度(控制抓拍)
        public int              nNoPlateSensitivity;                  // 无牌检测灵敏度（控制抓拍）
        public int              nLightPlateSensitivity;               // 有牌检测灵敏度（控制车位状态灯）
        public int              nLightNoPlateSensitivity;             // 无牌检测灵敏度（控制车位状态灯）
        public int              bForbidParkingEnable;                 // 禁止停车使能 TRUE:禁止 FALSE:未禁止
    }

    //事件类型	EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING(车位无车事件)对应的规则配置
    public static class CFG_TRAFFIC_PARKINGSPACENOPARKING_INFO extends SdkStructure
    {
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能
        public byte[]           bReserved = new byte[3];              // 保留字段
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public int              nLane;                                // 车位号
        public int              nDelayTime;                           // 检测到报警发生到开始上报的时间, 单位：秒，范围1~65535
        public int              nDetectRegionPoint;                   // 检测区域顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[]) new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区域
        public int              nPlateSensitivity;                    // 有牌检测灵敏度(控制抓拍)
        public int              nNoPlateSensitivity;                  // 无牌检测灵敏度（控制抓拍）
        public int              nLightPlateSensitivity;               // 有牌检测灵敏度（控制车位状态灯）
        public int              nLightNoPlateSensitivity;             // 无牌检测灵敏度（控制车位状态灯）
    }

    // 事件类型 EVENT_IVS_CITY_MOTORPARKING (城市机动车违停事件) 对应的数据块描述信息
    public static class DEV_EVENT_CITY_MOTORPARKING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC = new NET_TIME_EX();              // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO(); // 事件对应文件信息
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[NET_MAX_OBJECT_NUM]; // 检测到的物体
        public int              nDetectRegionNum;                     // 检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 检测区域
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo = new EVENT_INTELLI_COMM_INFO(); // 智能事件公共信息
        public int              nParkingDuration;                     // 违停持续时长，单位：秒 缺省值0表示无意义
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              bPtzPosition;                         // stuPtzPosition 是否有效
        public PTZ_NORMALIZED_POSITION_UNIT stuPtzPosition = new PTZ_NORMALIZED_POSITION_UNIT(); // 云台信息
        public int              emMotorStatus;                        // 车辆状态，{@link EM_CITYMOTOR_STATUS}
        public SCENE_IMAGE_INFO stuSceneImage = new SCENE_IMAGE_INFO(); // 全景广角图信息
        public int              emPreAlarm;                           // 是否为违规预警图片(预警触发后一定时间，违规物体还没有离开，才判定为违规)，参考EM_PREALARM
        public Pointer          pstuImageInfo;                        //图片信息数组,NET_IMAGE_INFO_EX2的数组
        public int              nImageInfoNum;                        //图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte             byVehicleHeadDirection;               // 车头朝向 0-未知,1-正面,2-侧面,3-背面
        public byte[]           szReversed = new byte[3];             //预留字节
        public int              nDetectRegionNumber;                  //检测区编号
        public byte[]           szDetectRegionName = new byte[128];   //检测区名称
        public byte[]           byReserved = new byte[876];           //预留字节

        public DEV_EVENT_CITY_MOTORPARKING_INFO() {
            for (int i = 0; i < stuObjects.length; i++) {
                stuObjects[i] = new NET_MSG_OBJECT();
            }
            for (int i = 0; i < DetectRegion.length; i++) {
                DetectRegion[i] = new NET_POINT();
            }
        }
    }

    // 事件类型 EVENT_IVS_CITY_NONMOTORPARKING (城市非机动车违停事件) 对应的数据块描述信息
    public static class DEV_EVENT_CITY_NONMOTORPARKING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nDetectRegionNum;                     // 检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 检测区域
        public int              nAlarmNum;                            // 报警阈值
        public int              nNoMotorNum;                          // 非机动车的个数
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nViolationDuration;                   // 违法持续时长，单位：秒 缺省值0表示无意义
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[HDBJ_MAX_OBJECTS_NUM]; // 检测到的物体
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景广角图
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[892];           // 保留字节
    }

    // 事件类型 EVENT_IVS_HOLD_UMBRELLA (违规撑伞检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_HOLD_UMBRELLA_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[HDBJ_MAX_OBJECTS_NUM]; // 检测到的物体
        public int              nObjectNum;                           // 检测到的物体个数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public int              nViolationDuration;                   // 违法持续时长，单位：秒 缺省值0表示无意义
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public byte[]           bReserved = new byte[4092];           // 保留字节,留待扩展.
    }

    // 事件类型 EVENT_IVS_GARBAGE_EXPOSURE (垃圾暴露检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_GARBAGE_EXPOSURE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[HDBJ_MAX_OBJECTS_NUM]; // 检测到的物体
        public int              nObjectNum;                           // 检测到的物体个数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public int              nViolationDuration;                   // 违法持续时长，单位：秒 缺省值0表示无意义
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public Pointer          pstuImageInfo;                        // 图片信息数组,指针对应NET_IMAGE_INFO_EX2的数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public SCENE_IMAGE_INFO_EX stuSceneImage = new SCENE_IMAGE_INFO_EX(); //全景图图片信息,事件前2~5s抓图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public Pointer          pstuMosaicImage;                      //合成图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public int              nMosaicImageNum;                      //合成图个数
        public Pointer          pstuAdvanceImage;                     //事件发生前抓图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public int              nAdvanceImageNum;                     //事件发生前抓图个数
        public int              nVehicleSpeed;                        //车速, 单位km/h
        public double           dbHeadingAngle;                       //航向角,以正北方向为基准输出车辆运动方向同正北方向的角度,范围 0~360,顺时针正,单位为度
        public double[]         dbLongitude = new double[3];          //经度,格式:度,分,秒(秒为浮点数)
        public double[]         dbLatitude = new double[3];           //纬度,格式:度,分,秒(秒为浮点数)
        public byte[]           bReserved = new byte[2160-POINTERSIZE*2]; // 保留字节,留待扩展.
    }

    // 事件类型 EVENT_IVS_DUSTBIN_OVER_FLOW (垃圾桶满溢检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_DUSTBIN_OVER_FLOW_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[HDBJ_MAX_OBJECTS_NUM]; // 检测到的物体
        public int              nObjectNum;                           // 检测到的物体个数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public int              nViolationDuration;                   // 违法持续时长，单位：秒 缺省值0表示无意义
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public Pointer          pstuImageInfo;                        // 图片信息数组,指针对应NET_IMAGE_INFO_EX2数组
        public int              nImageInfoNum;                        // 图片信息个数
        public int              nRuleId;                              // 规则编号
        public byte[]           szRuleName = new byte[128];           // 规则名称
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public SCENE_IMAGE_INFO_EX stuSceneImage = new SCENE_IMAGE_INFO_EX(); // 全景图图片信息,事件前2~5s抓图
        public Pointer          pstuMosaicImage;                      // 合成图,指针对应SCENE_IMAGE_INFO_EX数组
        public int              nMosaicImageNum;                      // 合成图个数
        public Pointer          pstuAdvanceImage;                     // 事件发生前抓图，指针对应SCENE_IMAGE_INFO_EX数组
        public int              nAdvanceImageNum;                     // 事件发生前抓图个数
        public byte[]           bReserved = new byte[2088-POINTERSIZE*2]; // 保留字节,留待扩展.
    }

    // 事件类型 EVENT_IVS_DOOR_FRONT_DIRTY (门前脏乱检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_DOOR_FRONT_DIRTY_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_MSG_OBJECT[] stuObjects = new NET_MSG_OBJECT[HDBJ_MAX_OBJECTS_NUM]; // 检测到的物体
        public int              nObjectNum;                           // 检测到的物体个数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public byte[]           szShopAddress = new byte[256];        // 商铺地址名称
        public int              nViolationDuration;                   // 违法持续时长，单位：秒，缺省值0表示无意义
        public byte[]           szSourceID = new byte[32];            // 事件关联ID,同一个物体或图片生成多个事件时SourceID相同
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public byte[]           bReserved = new byte[4092];           // 保留字节,留待扩展.
    }

    // CLIENT_StartRemoteUpgrade-输入参数
    public static class NET_IN_START_REMOTE_UPGRADE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nListNum;                             // 需要升级的远程通道个数
        public Pointer          pstuList;                             // 需要升级的远程通道信息
        public Pointer          pReserved;                            // 字节对齐
        public byte[]           szFileName = new byte[256];           // 升级文件名称
        public Callback         cbRemoteUpgrade;                      // 升级进度回调函数
        public Pointer          dwUser;                               // 用户数据
        public int              nPacketSize;                          // 每次分包发送大小，为0默认为16K

        public NET_IN_START_REMOTE_UPGRADE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartRemoteUpgrade-输出参数
    public static class NET_OUT_START_REMOTE_UPGRADE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_START_REMOTE_UPGRADE_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_AttachRemoteUpgradeState-输入参数
    public static class NET_IN_ATTACH_REMOTEUPGRADE_STATE extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public Callback         cbCallback;                           // 回调
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_REMOTEUPGRADE_STATE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_AttachRemoteUpgradeState-输出参数
    public static class NET_OUT_ATTACH_REMOTEUPGRADE_STATE extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小

        public NET_OUT_ATTACH_REMOTEUPGRADE_STATE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 远程通道信息
    public static class NET_REMOTE_UPGRADE_CHNL_INFO extends SdkStructure
    {
        public int              nChannel;                             // 远程通道号
        public byte[]           byReserved = new byte[512];           // 预留字段
    }

    // 升级远程设备程序回调函数
    public interface fRemoteUpgradeCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lUpgradeID,int emState,int nParam1,int nParam2,Pointer dwUser);
    }

    // 远程升级回调类型
    public static class EM_REMOTE_UPGRADE_CB_TYPE extends SdkStructure
    {
        public static final int   EM_REMOTE_UPGRADE_CB_TYPE_APPENDING = 0; // 推送回调	nParam1 文件总大小 nParam2 已发送大小
        public static final int   EM_REMOTE_UPGRADE_CB_TYPE_EXECUTE = 1; // 执行回调	nParam1 执行execute的结果
        public static final int   EM_REMOTE_UPGRADE_CB_TYPE_FAILED = 2; // 失败回调  nParam1 错误码
        public static final int   EM_REMOTE_UPGRADE_CB_TYPE_CANCEL = 3; // 取消回调
    }

    // 升级状态回调函数
    public interface fRemoteUpgraderStateCallback extends Callback {
        public void invoke(LLong lLoginId,LLong lAttachHandle,NET_REMOTE_UPGRADER_NOTIFY_INFO pBuf,int dwBufLen,Pointer pReserved,Pointer dwUser);
    }

    // 远程设备升级消息上报
    public static class NET_REMOTE_UPGRADER_NOTIFY_INFO extends SdkStructure
    {
        public int              nStateNum;                            // 状态数
        public Pointer          pstuStates;                           // 状态列表
        public byte[]           byReserved = new byte[1024];          // 预留
    }

    // 远程设备升级状态
    public static class NET_REMOTE_UPGRADER_STATE extends SdkStructure
    {
        public int              nChannel;                             // 通道号
        public int              emState;                              // 状态(对应的枚举值EM_REMOTE_UPGRADE_STATE)
        public int              nProgress;                            // 进度
        public byte[]           szDeviceID = new byte[128];           // 远程设备ID
    }

    // 远程设备升级状态
    public static class EM_REMOTE_UPGRADE_STATE extends SdkStructure
    {
        public static final int   EM_REMOTE_UPGRADE_STATE_UNKNOWN = 0;  // 未知
        public static final int   EM_REMOTE_UPGRADE_STATE_INIT = 1;     // 初始状态(未升级)
        public static final int   EM_REMOTE_UPGRADE_STATE_DOWNLOADING = 2; // 正在下载数据
        public static final int   EM_REMOTE_UPGRADE_STATE_UPGRADING = 3; // 正在升级
        public static final int   EM_REMOTE_UPGRADE_STATE_FAILED = 4;   // 升级失败
        public static final int   EM_REMOTE_UPGRADE_STATE_SUCCEEDED = 5; // 升级成功
        public static final int   EM_REMOTE_UPGRADE_STATE_CANCELLED = 6; // 取消升级
        public static final int   EM_REMOTE_UPGRADE_STATE_PREPARING = 7; // 准备升级中
    }

    //开始升级远程设备程序
    public LLong CLIENT_StartRemoteUpgrade(LLong lLoginID,NET_IN_START_REMOTE_UPGRADE_INFO pInParam,NET_OUT_START_REMOTE_UPGRADE_INFO pOutParam,int nWaitTime);

    //结束升级远程设备程序
    public Boolean CLIENT_StopRemoteUpgrade(LLong lUpgradeID);

    // 订阅ipc升级状态观察接口
    public LLong CLIENT_AttachRemoteUpgradeState(LLong lLoginID,NET_IN_ATTACH_REMOTEUPGRADE_STATE pInParam,NET_OUT_ATTACH_REMOTEUPGRADE_STATE pOutParam,int nWaitTime);

    // 取消订阅升级状态接口
    public Boolean CLIENT_DetachRemoteUpgradeState(LLong lAttachHandle);

    // 设置子连接网络参数, pSubConnectNetParam 资源由用户申请和释放
    public Boolean CLIENT_SetSubConnectNetworkParam(LLong lLoginID,NET_SUBCONNECT_NETPARAM pSubConnectNetParam);

    // 设置子链接网络参数
    public static class NET_SUBCONNECT_NETPARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nNetPort;                             // 网络映射端口号
        public byte[]           szNetIP = new byte[NET_MAX_IPADDR_EX_LEN]; // 网络映射IP地址

        public NET_SUBCONNECT_NETPARAM()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    public static class EM_ANALYSE_TASK_START_RULE extends SdkStructure
    {
        public static final int   EM_ANALYSE_TASK_START_NOW = 0;        // 立刻启动
        public static final int   EM_ANALYSE_TASK_START_LATER = 1;      // 稍候手动启动
    }

    // 视频分析支持的对象类型
    public static class EM_ANALYSE_OBJECT_TYPE extends SdkStructure
    {
        public static final int   EM_ANALYSE_OBJECT_TYPE_UNKNOWN = 0;   // 未知的
        public static final int   EM_ANALYSE_OBJECT_TYPE_HUMAN = 1;     // 人
        public static final int   EM_ANALYSE_OBJECT_TYPE_VEHICLE = 2;   // 车辆
        public static final int   EM_ANALYSE_OBJECT_TYPE_FIRE = 3;      // 火
        public static final int   EM_ANALYSE_OBJECT_TYPE_SMOKE = 4;     // 烟雾
        public static final int   EM_ANALYSE_OBJECT_TYPE_PLATE = 5;     // 片状物体
        public static final int   EM_ANALYSE_OBJECT_TYPE_HUMANFACE = 6; // 人脸
        public static final int   EM_ANALYSE_OBJECT_TYPE_CONTAINER = 7; // 货柜
        public static final int   EM_ANALYSE_OBJECT_TYPE_ANIMAL = 8;    // 动物
        public static final int   EM_ANALYSE_OBJECT_TYPE_TRAFFICLIGHT = 9; // 红绿灯
        public static final int   EM_ANALYSE_OBJECT_TYPE_PASTEPAPER = 10; // 贴纸 贴片
        public static final int   EM_ANALYSE_OBJECT_TYPE_HUMANHEAD = 11; // 人的头部
        public static final int   EM_ANALYSE_OBJECT_TYPE_ENTITY = 12;   // 普通物体
        public static final int   EM_ANALYSE_OBJECT_TYPE_PACKAGE = 13;  ///// 包裹
        public static final int   EM_ANALYSE_OBJECT_TYPE_SCRAPSTEEL_DANGER = 14; /////废钢危险品
    }

    // 事件类型 EVENT_IVS_SMART_KITCHEN_CLOTHES_DETECTION(智慧厨房穿着检测事件)对应的数据块描述信息
    public static class DEV_EVENT_SMART_KITCHEN_CLOTHES_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nRuleID;                              // 用于
        public int              emClassType;                          // 智能事件所属大类
        public byte[]           szClassAlias = new byte[16];          // 智能事件所属大类别名
        public HUMAN_IMAGE_INFO stuHumanImage;                        // 人体图片信息
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景广角图片信息
        public FACE_IMAGE_INFO  stuFaceImage;                         // 人脸图片信息
        public int              nObjectID;                            // 目标ID
        public int              emHasMask;                            // 检测是否有戴口罩（对应枚举值EM_NONMOTOR_OBJECT_STATUS）
        public int              emHasChefHat;                         // 检测是否有戴厨师帽（对应枚举值EM_NONMOTOR_OBJECT_STATUS）
        public int              emHasChefClothes;                     // 检测是否有穿厨师服（对应枚举值EM_NONMOTOR_OBJECT_STATUS）
        public int              emChefClothesColor;                   // 厨师服颜色（对应枚举值EM_OBJECT_COLOR_TYPE）
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        public byte             bReserved[] = new byte[1024];         //预留字节
    }

    // 事件类型EVENT_IVS_BANNER_DETECTION(拉横幅事件)对应数据块描述信息
    public static class DEV_EVENT_BANNER_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nAction;                              // 1:开始 2:停止
        public int              emClassType;                          // 智能事件所属大类(对应EM_CLASS_TYPE枚举)
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT [] stuObjects = new NET_MSG_OBJECT[32]; // 检测到的物体
        public int              nDetectRegionNum;                     // 检测区域顶点数
        public NET_POINT []     DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 检测区域
        public int              nCount;                               // 事件触发次数
        public int              nPresetID;                            // 预置点
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public Pointer          pstuImageInfo;                        //图片信息数组, refer to {@link NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public int              bSceneImage;                          //pstuSceneImage是否有效
        public Pointer          pstuSceneImage;                       //全景广角图, refer to {@link SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[1020-2*POINTERSIZE]; //保留字节,留待扩展.

        public DEV_EVENT_BANNER_DETECTION_INFO(){
    		for(int i=0;i<stuObjects.length;i++){
    			stuObjects[i]=new NET_MSG_OBJECT();
    		}
    		for(int i=0;i<DetectRegion.length;i++){
    			DetectRegion[i]=new NET_POINT();
    		}
        }
    }

    // 事件类型EVENT_IVS_BANNER_DETECTION(拉横幅检测事件)对应的规则配置
    public static class NET_BANNER_DETECTION_RULE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public POINTCOORDINATE	[] stuDetectRegion = new POINTCOORDINATE[20]; // 检测区域
        public int              nMinDuration;                         // 最短持续时间, 单位:秒，范围1-600, 默认30
        public int              nReportInterval;                      // 重复报警间隔,单位:秒,范围0-600,默认30,为0表示不重复
        public int              nSensitivity;                         // 检测灵敏度,范围1-10
        public int              nBannerPercent;                       // 近景抓拍时横幅在画面的百分比，范围1~100，默认80
        public byte[]           bReserved = new byte[520];            // 保留字节

        public NET_BANNER_DETECTION_RULE_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    public static class POINTCOORDINATE extends SdkStructure
    {
        public int              nX;                                   // 第一个元素表示景物点的x坐标(0~8191)
        public int              nY;                                   // 第二个元素表示景物点的y坐标(0~8191)
    }

    // 事件类型 EVENT_IVS_SMART_KITCHEN_CLOTHES_DETECTION(智慧类型衣着检测)对应的规则配置
    public static class NET_SMART_KITCHEN_CLOTHES_DETECTION_RULE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bMaskEnable;                          // 是否开启口罩检测  （TRUE:开启 FALSE:关闭）
        public int              bChefHatEnable;                       // 是否开启厨师帽检测（TRUE:开启 FALSE:关闭）
        public int              bChefClothesEnable;                   // 是否开启厨师服检测（TRUE:开启 FALSE:关闭）
        public int              nChefClothesColorNum;                 // 配置检查允许的厨师服颜色个数
        public int	[]           emChefClothesColors = new int[8];     // 厨师衣服颜色(对应的枚举值EM_CFG_CHEF_CLOTHES_COLORS)
        public int              nReportInterval;                      // 重复报警间隔,单位:秒,范围0-600,默认30,为0表示不重复
        public byte[]           byReserved = new byte[4096];          // 保留字节

        public NET_SMART_KITCHEN_CLOTHES_DETECTION_RULE_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 智能分析规则信息
    public static class NET_ANALYSE_RULE_INFO extends SdkStructure
    {
        public int              emClassType;                          // 分析大类类型(对应的枚举值EM_SCENE_CLASS_TYPE)
        public int              dwRuleType;                           // 规则类型, 详见dhnetsdk.h中"智能分析事件类型"
        // EVENT_IVS_FACEANALYSIS(人脸分析)对应结构体 NET_FACEANALYSIS_RULE_INFO
		// EVENT_IVS_NONMOTORDETECT(非机动车)对应结构体 NET_NONMOTORDETECT_RULE_INFO
		// EVENT_IVS_VEHICLEDETECT(机动车) 对应结构体 NET_VEHICLEDETECT_RULE_INFO
		// EVENT_IVS_HUMANTRAIT(人体) 对应结构体NET_HUMANTRAIT_RULE_INFO
		// EVENT_IVS_XRAY_DETECT_BYOBJECT(X光按物体检测) 对应结构体 NET_XRAY_DETECT_BYPBJECT_RULE_INFO
		// EVENT_IVS_WORKCLOTHES_DETECT(工装检测)对应结构体NET_WORKCLOTHDETECT_RULE_INFO
		// EVENT_IVS_WORKSTATDETECTION(作业统计)对应结构体NET_WORKSTATDETECTION_RULE_INFO
		// EVENT_IVS_CROSSLINEDETECTION(警戒线)对应结构体NET_CROSSLINE_RULE_INFO
		// EVENT_IVS_CROSSREGIONDETECTION(警戒区)对应结构体 NET_CROSSREGION_RULE_INFO
        // EVENT_IVS_FEATURE_ABSTRACT(特征提取)对应结构体 NET_FEATURE_ABSTRACT_RULE_INFO
        // EVENT_IVS_ELECTRIC_GLOVE_DETECT(电力检测手套检测事件)对应结构体NET_ELECTRIC_GLOVE_DETECT_RULE_INFO
        // EVENT_IVS_ELECTRIC_LADDER_DETECT(电力检测梯子检测事件)对应结构体NET_ELECTRIC_LADDER_DETECT_RULE_INFO
        // EVENT_IVS_ELECTRIC_CURTAIN_DETECT(电力检测布幔检测事件)对应结构体NET_ELECTRIC_CURTAIN_DETECT_RULE_INFO
        // EVENT_IVS_ELECTRIC_FENCE_DETECT(电力检测围栏检测事件)对应结构体NET_ELECTRIC_FENCE_DETECT_RULE_INFO
        // EVENT_IVS_ELECTRIC_SIGNBOARD_DETECT(电力检测标识牌检测事件)对应结构体NET_ELECTRIC_SIGNBOARD_DETECT_RULE_INFO
        // EVENT_IVS_ELECTRIC_BELT_DETECT(电力检测安全带检测事件)对应结构体NET_ELECTRIC_BELT_DETECT_RULE_INFO
		// EVENT_IVS_BANNER_DETECTION（拉横幅检测事件）对应结构体NET_BANNER_DETECTION_RULE_INFO
		// EVENT_IVS_SMART_KITCHEN_CLOTHES_DETECTION(智慧厨房衣着检测事件)对应结构体NET_SMART_KITCHEN_CLOTHES_DETECTION_RULE_INFO
		// EVENT_IVS_WATER_STAGE_MONITOR(水位检测事件)对应结构体NET_WATER_STAGE_MONITOR_RULE_INFO
		// EVENT_IVS_FLOATINGOBJECT_DETECTION(漂浮物检测事件)对应结构体 NET_FLOATINGOBJECT_DETECTION_RULE_INFO
        // EVENT_IVS_RIOTERDETECTION (人群聚集) 对应结构体 NET_RIOTERDETECTION_RULE_INFO
        // EVENT_IVS_LEFTDETECTION (物品遗留事件) 对应结构体 NET_LEFTDETECTION_RULE_INFO
        // EVENT_IVS_PARKINGDETECTION (非法停车事件) 对应结构体 NET_PARKINGDETECTION_RULE_INFO
        // EVENT_IVS_WANDERDETECTION( 徘徊事件)对应结构体 NET_WANDERDETECTION_RULE_INFO
        // EVENT_IVS_VIDEOABNORMALDETECTION (视频异常)对应结构体 NET_VIDEOABNORMALDETECTION_RULE_INFO
        // EVENT_IVSS_FACEATTRIBUTE (人脸属性检测) 对应结构体 NET_FACEATTRIBUTE_RULE_INFO
        // EVENT_IVS_MOVEDETECTION (移动检测) 对应结构体 NET_MOVEDETECTION_RULE_INFO
		// EVENT_IVSS_FACECOMPARE(IVSS目标识别事件) 对应结构体 NET_FACECOMPARE_INFO
		// EVENT_IVS_CONVEYER_BELT_DETECT(传送带检测) 对应结构体 NET_CONVEYER_BELT_DETECT_RULE_INFO
        // EVENT_IVS_NUMBERSTAT(数量统计事件) 对应结构体NET_NUMBERSTAT_RULE_INFO
        // EVENT_IVS_STEREO_FIGHTDETECTION(立体行为分析打架/剧烈运动检测) 对应结构体NET_STEREO_FIGHTDETECTION_RULE_INFO
        // EVENT_IVS_SMOKEDETECTION(烟雾报警检测) 对应结构体 NET_SMOKEDETECTION_RULE_INFO
        // EVENT_IVS_FIREDETECTION(火警检测)对应结构体 NET_FIREDETECTION_RULE_INFO
        // EVENT_IVS_PHONECALL_DETECT(打电话检测)对应结构体 NET_PHONECALL_DETECT_RULE_INFO
        // EVENT_IVS_SMOKING_DETECT(吸烟检测)对应结构体 NET_SMOKING_DETECT_RULE_INFO
        // EVENT_IVS_STEREO_STEREOFALLDETECTION(立体行为分析跌倒检测)对应结构体 NET_STEREO_STEREOFALLDETECTION_RULE_INFO
        // EVENT_IVS_WATER_LEVEL_DETECTION(水位尺检测)对应结构体 NET_WATER_LEVEL_DETECTION_RULE_INFO
        // EVENT_IVS_CLIMBDETECTION(攀高检测)对应结构体 NET_CLIMBDETECTION_RULE_INFO
        // EVENT_IVS_ARTICLE_DETECTION(物品检测)对应结构体NET_ARTICLE_DETECTION_RULE_INFO
        // EVENT_IVS_MAN_NUM_DETECTION(立体视觉区域内人数统计事件)对应结构体NET_IVS_MAN_NUM_DETECTION_RULE_INFO
		// EVENT_IVS_DIALRECOGNITION(仪表检测事件)对应结构体NET_IVS_DIALRECOGNITION_RULE_INFO
		// EVENT_IVS_ELECTRICFAULT_DETECT(仪表类缺陷检测事件)对应结构体NET_IVS_ELECTRICFAULT_DETECT_RULE_INFO
		// EVENT_IVS_TRAFFIC_ROAD_BLOCK(交通路障检测事件) 对应结构体 NET_TRAFFIC_ROAD_BLOCK_RULE_INFO
		// EVENT_IVS_TRAFFIC_ROAD_CONSTRUCTION(交通道路施工检测事件) 对应结构体 NET_TRAFFIC_ROAD_CONSTRUCTION_RULE_INFO
		// EVENT_IVS_TRAFFIC_FLOWSTATE(交通流量统计事件) 对应结构体 NET_TRAFFIC_FLOWSTAT_RULE_INFO
		// EVENT_IVS_TRAFFIC_OVERSPEED(超速事件) 对应结构体 NET_TRAFFIC_OVERSPEED_RULE_INFO
		// EVENT_IVS_TRAFFIC_UNDERSPEED(欠速事件) 对应结构体 NET_TRAFFIC_UNDERSPEED_RULE_INFO
		// EVENT_IVS_TRAFFIC_OVERYELLOWLINE(压黄线事件) 对应结构体 NET_TRAFFIC_OVERYELLOWLINE_RULE_INFO
		// EVENT_IVS_TRAFFIC_CROSSLANE(违章变道事件) 对应结构体 NET_TRAFFIC_CROSSLANE_RULE_INFO
		// EVENT_IVS_TRAFFICJAM(交通拥堵事件) 对应结构体 NET_TRAFFIC_JAM_RULE_INFO
		// EVENT_IVS_TRAFFIC_PEDESTRAIN(交通行人事件) 对应结构体 NET_TRAFFIC_PEDESTRAIN_RULE_INFO
		// EVENT_IVS_TRAFFIC_THROW(抛洒物事件) 对应结构体 NET_TRAFFIC_THROW_RULE_INFO
		// EVENT_IVS_TRAFFIC_RETROGRADE(逆行检测事件) 对应结构体 NET_TRAFFIC_RETROGRADE_RULE_INFO
		// EVENT_IVS_TRAFFICACCIDENT(交通事故事件) 对应结构体 NET_TRAFFIC_ACCIDENT_RULE_INFO
		// EVENT_IVS_TRAFFIC_BACKING(倒车事件) 对应结构体 NET_TRAFFIC_BACKING_RULE_INFO
		// EVENT_IVS_FOG_DETECTION(起雾检测事件) 对应结构体 NET_FOG_DETECTION_RULE_INFO
		// EVENT_IVS_CROSSREGIONDETECTION(警戒区事件) 对应结构体 NET_CROSSREGION_RULE_INFO
		// EVENT_IVS_TRAFFIC_PARKING(交通违章停车事件) 对应结构体 NET_TRAFFIC_PARKING_RULE_INFO
		// EVENT_IVS_FINANCE_CASH_TRANSACTION(智慧金融现金交易检测事件) 对应结构体 NET_FINANCE_CASH_TRANSACTION_RULE_INFO
		// EVENT_IVS_LEAVEDETECTION(离岗检测事件) 对应结构体 NET_LEAVEDETECTION_RULE_INFO
		// EVENT_IVS_LADLE_NO_DETECTION(钢包编号识别事件) 对应规则配置为空
		// EVENT_IVS_DIALRECOGNITION_EX(仪表检测事件)对应结构体NET_IVS_DIALRECOGNITION_RULE_INFO
        public Pointer          pReserved;                            // 规则配置, 具体结构体类型根据dwRuleType来确定, 具体信息见dwRuleType的注释
        public int              nObjectTypeNum;                       // 检测物体类型个数, 为0 表示不指定物体类型
        public int	[]           emObjectTypes = new int[16];          // 检测物体类型列表(对应的枚举值EM_ANALYSE_OBJECT_TYPE)
        public byte[]           szRuleName = new byte[128];           //规则名称，不带预置点的设备规则名称不能重名，带预置点的设备，同一预置点内规则名称不能重名，不同预置点之间规则名称可以重名
        public int              IsUsingEnable;                        //是否使用Enable字段
        public int              bEnable;                              //规则使能
        public Pointer          pstuEventHandler;                     //视频分析联动信息，内存由用户申请释放,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ANALYSE_RULE_EVENT_HANDLER_INFO}
        public byte[]           byReserved = new byte[820-POINTERSIZE]; // 保留字节
    }

    // 智能分析规则
    public static class NET_ANALYSE_RULE extends SdkStructure
    {
        public NET_ANALYSE_RULE_INFO [] stuRuleInfos = new NET_ANALYSE_RULE_INFO[MAX_ANALYSE_RULE_COUNT]; // 分析规则信息
        public int              nRuleCount;                           // 分析规则条数
        public byte[]           byReserved = new byte[1028];          // 保留字节

    public NET_ANALYSE_RULE(){

        for(int i=0;i<stuRuleInfos.length;i++){
            stuRuleInfos[i]=new NET_ANALYSE_RULE_INFO();
        }


    }
    }

    // 推送图片文件信息
    public static class NET_PUSH_PICFILE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emStartRule;                          // 智能任务启动规则(对应的枚举值EM_ANALYSE_TASK_START_RULE)
        public NET_ANALYSE_RULE stuRuleInfo = new NET_ANALYSE_RULE(); // 分析规则信息
        public byte[]           szTaskUserData = new byte[256];       // 任务数据
        public byte[]           szMQConfig = new byte[4096];          // MQ配置信息，参考Paas协议配置中心-算子配置，小型化方案使用。当远程访问类型为RabbitMq时,尝试从该字段获取MQ配置
        public int              nIsRepeat;                            // 是否许可重复,0默认是可以重复,1表示不能重复
        public NET_ANALYSE_TASK_GLOBAL stuGlobal = new NET_ANALYSE_TASK_GLOBAL(); // 全局配置
        public NET_ANALYSE_TASK_MODULE stuModule = new NET_ANALYSE_TASK_MODULE(); // 模块配置
        public int              bUseTransmit;                         //是否使用透传
        public Pointer          pszRules;                             //分析规则内容
        public Pointer          pszGlobal;                            //全局配置内容
        public Pointer          pszModule;                            //模块配置内容

        public NET_PUSH_PICFILE_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小

               @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }
    }

    // 智能分析数据源类型
    public static class EM_DATA_SOURCE_TYPE extends SdkStructure
    {
        public static final int   EM_DATA_SOURCE_REMOTE_REALTIME_STREAM = 1; // 远程实时流 , 对应 NET_REMOTE_REALTIME_STREAM_INFO
        public static final int   EM_DATA_SOURCE_PUSH_PICFILE = 2;      // 主动推送图片文件, 对应 NET_PUSH_PICFILE_INFO
    }

    // CLIENT_AddAnalyseTask 接口输出参数
    public static class NET_OUT_ADD_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID
        public int              nVirtualChannel;                      // 任务对应的虚拟通道号
        public byte[]           szUrl = new byte[256];                // 智能码流rtsp地址

        public NET_OUT_ADD_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 添加智能分析任务, 输入参数pInParam的结构体类型根据emDataSourceType的值来决定, pInParam 和 pOutParam 资源由用户申请和释放
    public Boolean CLIENT_AddAnalyseTask(LLong lLoginID,int emDataSourceType,Pointer pInParam,NET_OUT_ADD_ANALYSE_TASK pOutParam,int nWaitTime);

    // CLIENT_StartAnalyseTask 接口输入参数
    public static class NET_IN_START_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID

        public NET_IN_START_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_StartAnalyseTask 接口输出参数
    public static class NET_OUT_START_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_START_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 视频流协议类型
    public static class EM_STREAM_PROTOCOL_TYPE extends SdkStructure
    {
        public static final int   EM_STREAM_PROTOCOL_UNKNOWN = 0;       // 未知
        public static final int   EM_STREAM_PROTOCOL_PRIVATE_V2 = 1;    // 私有二代
        public static final int   EM_STREAM_PROTOCOL_PRIVATE_V3 = 2;    // 私有三代
        public static final int   EM_STREAM_PROTOCOL_RTSP = 3;          // rtsp
        public static final int   EM_STREAM_PROTOCOL_ONVIF = 4;         // Onvif
        public static final int   EM_STREAM_PROTOCOL_GB28181 = 5;       // GB28181
        public static final int   EM_STREAM_PROTOCOL_HIKVISION = 6;
        public static final int   EM_STREAM_PROTOCOL_BSCP = 7;          // 蓝星
    }

    // 远程实时视频源信息
    public static class NET_REMOTE_REALTIME_STREAM_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emStartRule;                          // 智能任务启动规则，Polling任务时无效(参考)EM_ANALYSE_TASK_START_RULE
        public NET_ANALYSE_RULE stuRuleInfo = new NET_ANALYSE_RULE(); // 分析规则信息
        public int              emStreamProtocolType;                 // 视频流协议类型(参考EM_STREAM_PROTOCOL_TYPE)
        public byte[]           szPath = new byte[NET_COMMON_STRING_256]; // 视频流地址
        public byte[]           szIp = new byte[NET_MAX_IPADDR_OR_DOMAIN_LEN]; // IP 地址
        public short            wPort;                                // 端口号
        public byte[]           byReserved = new byte[2];             // 用于字节对齐
        public byte[]           szUser = new byte[NET_COMMON_STRING_64]; // 用户名
        public byte[]           szPwd = new byte[NET_COMMON_STRING_64]; // 密码
        public int              nChannelID;                           // 通道号
        public int              nStreamType;                          // 码流类型, 0:主码流; 1:辅1码流; 2:辅2码流;
        public byte[]           szTaskUserData = new byte[256];       // 任务数据
        public byte[]           szMQConfig = new byte[4096];          // MQ配置信息，参考Paas协议配置中心-算子配置，小型化方案使用。当远程访问类型为RabbitMq时,尝试从该字段获取MQ配置
        public int              nIsRepeat;                            // 是否许可重复,0默认是可以重复,1表示不能重复
        public NET_ANALYSE_TASK_GLOBAL stuGlobal = new NET_ANALYSE_TASK_GLOBAL(); // 全局配置
        public NET_ANALYSE_TASK_MODULE stuModule = new NET_ANALYSE_TASK_MODULE(); // 模块配置
        public byte[]           szChannelId = new byte[32];           // 平台通道信息(专用)
        public int              bUseTransmit;                         //是否使用透传
        public Pointer          pszRules;                             //分析规则内容
        public Pointer          pszGlobal;                            //全局配置内容
        public Pointer          pszModule;                            //模块配置内容

        public NET_REMOTE_REALTIME_STREAM_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 启动智能分析任务, pInParam 和 pOutParam 资源由用户申请和释放
    public Boolean CLIENT_StartAnalyseTask(LLong lLoginID,NET_IN_START_ANALYSE_TASK pInParam,NET_OUT_START_ANALYSE_TASK pOutParam,int nWaitTime);

    // CLIENT_RemoveAnalyseTask 接口输入参数
    public static class NET_IN_REMOVE_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID

        public NET_IN_REMOVE_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_RemoveAnalyseTask 接口输出参数
    public static class NET_OUT_REMOVE_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_REMOVE_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 删除(停止)智能分析任务, pInParam 和 pOutParam 资源由用户申请和释放
    public Boolean CLIENT_RemoveAnalyseTask(LLong lLoginID,NET_IN_REMOVE_ANALYSE_TASK pInParam,NET_OUT_REMOVE_ANALYSE_TASK pOutParam,int nWaitTime);

    // CLIENT_FindAnalyseTask 接口输入参数
    public static class NET_IN_FIND_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_FIND_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_FindAnalyseTask 接口输出参数
    public static class NET_OUT_FIND_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskNum;                             // 智能分析任务个数
        public NET_ANALYSE_TASKS_INFO [] stuTaskInfos = new NET_ANALYSE_TASKS_INFO[256]; // 智能分析任务信息

        public NET_OUT_FIND_ANALYSE_TASK()
        {
            for(int i=0;i<stuTaskInfos.length;i++){
                stuTaskInfos[i]=new NET_ANALYSE_TASKS_INFO();
            }
            this.dwSize = this.size();

        }// 此结构体大小
    }

    // 智能分析任务信息
    public static class NET_ANALYSE_TASKS_INFO extends SdkStructure
    {
        /**
         任务ID
         */
        public			int            nTaskID;
        /**
         分析状态 {@link EM_ANALYSE_STATE}
         */
        public			int            emAnalyseState;
        /**
         错误码 {@link EM_ANALYSE_TASK_ERROR}
         */
        public			int            emErrorCode;
        /**
         字节对齐
         */
        public			byte[]         byReserved1 = new byte[4];
        /**
         任务数据
         */
        public			byte[]         szTaskUserData = new byte[256];
        /**
         录像分析进度，当任务添加接口CLIENT_AddAnalyseTask emDataSourceType参数为录像分析"EM_DATA_SOURCE_REMOTE_PICTURE_FILE"时有效 范围1~100，100表示分析完成
         */
        public			int            nVideoAnalysisProcess;
        /**
         智能流rtsp地址，实时流时才填写
         */
        public			byte[]         szUrl = new byte[256];
        /**
         智能大类类型 {@link EM_SCENE_CLASS_TYPE}
         */
        public			int            emClassType;
        /**
         数据源类型 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_DATA_SOURCE_TYPE}
         */
        public			int            emSourceType;
        /**
         任务使用的分析子卡ID.-1表示无效子卡，大于等于0的值表示子卡ID号
         emErrorCode为EM_ANALYSE_TASK_ERROR_ANALYZER_OFF_LINE或EM_ANALYSE_TASK_ERROR_ANALYZER_ON_LINE时此字段有效
         */
        public			int            nChipId;
        public int              nFilesNum;                            //上传的文件列表有效个数，最大值为32
        public Pointer          pstuFiles;                            //上传的文件列表, 内存由SDK申请释放,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EMERGENCY_FILE_INFO}
        /**
         保留字节
         */
        public			byte[]         byReserved = new byte[424-POINTERSIZE];

        public			NET_ANALYSE_TASKS_INFO(){
        }
    }

    // 分析状态
    public static class EM_ANALYSE_STATE extends SdkStructure
    {
        public static final int   EM_ANALYSE_STATE_UNKNOWN = 0;         // 未知
        public static final int   EM_ANALYSE_STATE_IDLE = 1;            // 已创建但未运行
        public static final int   EM_ANALYSE_STATE_ANALYSING = 2;       // 分析中
        public static final int   EM_ANALYSE_STATE_ANALYSING_WAITPUSH = 3; // 分析中并等待push数据
        public static final int   EM_ANALYSE_STATE_FINISH = 4;          // 正常完成
        public static final int   EM_ANALYSE_STATE_ERROR = 5;           // 执行异常
        public static final int   EM_ANALYSE_STATE_REMOVED = 6;         // 被删除
        public static final int   EM_ANALYSE_STATE_ROUNDFINISH = 7;     // 完成一轮视频源分析
        public static final int   EM_ANALYSE_STATE_STARTING = 8;        //任务开启状态
    }

    /**
     * 查找智能分析任务信息, pInParam 和 pOutParam 资源由用户申请和释放
     *  param[in] nWaitTime 接口超时时间, 单位毫秒
     *  return TRUE表示成功 FALSE表示失败
     */
    public Boolean CLIENT_FindAnalyseTask(LLong lLoginID, NET_IN_FIND_ANALYSE_TASK pInParam, NET_OUT_FIND_ANALYSE_TASK pOutParam, int nWaitTime);

    // CLIENT_PushAnalysePictureFile 接口输入参数
    public static class NET_IN_PUSH_ANALYSE_PICTURE_FILE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID
        public NET_PUSH_PICTURE_INFO [] stuPushPicInfos = (NET_PUSH_PICTURE_INFO[]) new NET_PUSH_PICTURE_INFO().toArray(MAX_ANALYSE_PICTURE_FILE_NUM); // 推送图片信息
        public int              nPicNum;                              // 推送图片数量
        public int              nBinBufLen;                           // 数据缓冲区长度, 单位:字节
        public Pointer          pBinBuf;                              // 数据缓冲区, 由用户申请和释放
        public NET_PUSH_PICTURE_INFO_EXTERN[] stuPushPicInfoExterns = new NET_PUSH_PICTURE_INFO_EXTERN[32]; //推图信息扩展字段，数量复用nPicNum,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_PUSH_PICTURE_INFO_EXTERN}

        public NET_IN_PUSH_ANALYSE_PICTURE_FILE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // X光机视角类型
    public static class EM_XRAY_VIEW_TYPE extends SdkStructure
    {
        public static final int   EM_XRAY_VIEW_TYPE_UNKNOWN = -1;       // 未知
        public static final int   EM_XRAY_VIEW_TYPE_MASTER = 0;         // 主视角
        public static final int   EM_XRAY_VIEW_TYPE_SLAVE = 1;          // 从视角
    }

    // 客户自定义信息, X光机专用
    public static class NET_XRAY_CUSTOM_INFO extends SdkStructure
    {
        public int              emViewType;                           // 视角类型
        public byte[]           szSerialNumber = new byte[128];       // 流水号
        public byte[]           byReserved = new byte[124];           // 保留字节

        @Override
        public String toString() {
            return "NET_XRAY_CUSTOM_INFO{" +
                    "emViewType=" + emViewType +
                    ", szSerialNumber=" + new String(szSerialNumber) +
                    '}';
        }
    }

    // 智能分析图片信息
    public static class NET_PUSH_PICTURE_INFO extends SdkStructure
    {
        public byte[]           szFileID = new byte[NET_COMMON_STRING_128]; // 文件ID
        public int              nOffset;                              // 文件数据在二进制数据中的偏移, 单位:字节
        public int              nLength;                              // 文件数据长度, 单位:字节
        public NET_XRAY_CUSTOM_INFO stuXRayCustomInfo;                // 客户自定义信息, X光机专用
        public byte[]           szUrl = new byte[512];                // 远程文件url地址  带访问所需必要信息 包含用户名 密码
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // CLIENT_PushAnalysePictureFile 接口输出参数
    public static class NET_OUT_PUSH_ANALYSE_PICTURE_FILE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_PUSH_ANALYSE_PICTURE_FILE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 推送智能分析图片文件，当CLIENT_AddAnalyseTask的数据源类型emDataSourceType为 EM_DATA_SOURCE_PUSH_PICFILE 时使用
    public Boolean CLIENT_PushAnalysePictureFile(LLong lLoginID,NET_IN_PUSH_ANALYSE_PICTURE_FILE pInParam,NET_OUT_PUSH_ANALYSE_PICTURE_FILE pOutParam,int nWaitTime);

    /*--------任务开始：  CLIENT_AttachAnalyseTaskState / CLIENT_DetachAnalyseTaskState --------*/
    // 智能分析任务状态回调信息
    public static class NET_CB_ANALYSE_TASK_STATE_INFO extends SdkStructure
    {
        public NET_ANALYSE_TASKS_INFO[] stuTaskInfos = (NET_ANALYSE_TASKS_INFO[])new NET_ANALYSE_TASKS_INFO().toArray(MAX_ANALYSE_TASK_NUM); // 智能分析任务信息
        public int              nTaskNum;                             // 任务个数
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 智能分析状态订阅函数原型, lAttachHandle 为 CLIENT_AttachAnalyseTaskState 函数的返回值
    public interface fAnalyseTaskStateCallBack extends Callback {
        public int invoke(LLong lAttachHandle,Pointer pstAnalyseTaskStateInfo,Pointer dwUser);
    }

    // CLIENT_AttachAnalyseTaskState 接口输入参数
    public static class NET_IN_ATTACH_ANALYSE_TASK_STATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int[]            nTaskIDs = new int[MAX_ANALYSE_TASK_NUM]; // 智能分析任务ID
        public int              nTaskIdNum;                           // 智能分析任务个数, 0表示订阅全部任务
        public fAnalyseTaskStateCallBack cbAnalyseTaskState;          // 智能分析任务状态订阅函数
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_ANALYSE_TASK_STATE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 订阅智能分析任务状态, pInParam 资源由用户申请和释放
    public LLong CLIENT_AttachAnalyseTaskState(LLong lLoginID,NET_IN_ATTACH_ANALYSE_TASK_STATE pInParam,int nWaitTime);

    // 取消订阅智能分析任务状态, lAttachHandle 为 CLIENT_AttachAnalyseTaskState接口的返回值
    public Boolean CLIENT_DetachAnalyseTaskState(LLong lAttachHandle);

    /*--------任务结束：  CLIENT_AttachAnalyseTaskState / CLIENT_DetachAnalyseTaskState --------*/
    /*--------任务开始：  CLIENT_AttachAnalyseTaskResult / CLIENT_DetachAnalyseTaskResult --------*/
    // 事件类型
    public static class EM_ANALYSE_EVENT_TYPE extends SdkStructure
    {
        public static final int   EM_ANALYSE_EVENT_UNKNOWN = 0;         // 未知
        public static final int   EM_ANALYSE_EVENT_ALL = 1;             // 所有事件
        public static final int   EM_ANALYSE_EVENT_FACE_DETECTION = 2;  // 目标检测事件, 对应结构体 DEV_EVENT_FACEDETECT_INFO
        public static final int   EM_ANALYSE_EVENT_FACE_RECOGNITION = 3; // 目标识别事件, 对应结构体 DEV_EVENT_FACERECOGNITION_INFO
        public static final int   EM_ANALYSE_EVENT_TRAFFICJUNCTION = 4; // 交通路口事件, 对应结构体 DEV_EVENT_TRAFFICJUNCTION_INFO
        public static final int   EM_ANALYSE_EVENT_HUMANTRAIT = 5;      // 人体特征事件, 对应结构体 DEV_EVENT_HUMANTRAIT_INFO
        public static final int   EM_ANALYSE_EVENT_XRAY_DETECTION = 6;  // X光机检测事件, 对应结构体 DEV_EVENT_XRAY_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_WORKCLOTHESDETECT = 7; // 工装(安全帽/工作服等)检测事件, 对应结构体 DEV_EVENT_WORKCLOTHES_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_WORKSTATDETECTION = 8; // 作业检测事件, 对应结构体 DEV_EVENT_WORKSTATDETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_CORSSLINEDETECTION = 9; // 警戒线事件, 对应结构体 DEV_EVENT_CROSSLINE_INFO
        public static final int   EM_ANALYSE_EVENT_CROSSREGIONDETECTION = 10; // 警戒区事件, 对应结构体 DEV_EVENT_CROSSREGION_INFO
        public static final int   EM_ANALYSE_EVENT_FEATURE_ABSTRACT = 11; // 特征提取事件 DEV_EVENT_FEATURE_ABSTRACT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_GLOVE_DETECT = 12; // 电力检测手套检测事件,  对应结构体 DEV_EVENT_ELECTRIC_GLOVE_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_LADDER_DETECT = 13; // 电力检测梯子检测事件,  对应结构体 DEV_EVENT_ELECTRIC_LADDER_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_CURTAIN_DETECT = 14; // 电力检测布幔检测事件,  对应结构体 DEV_EVENT_ELECTRIC_CURTAIN_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_FENCE_DETECT = 15; // 电力检测围栏检测事件,  对应结构体 DEV_EVENT_ELECTRIC_FENCE_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_SIGNBOARD_DETECT = 16; // 电力检测标识牌检测事件,  对应结构体 DEV_EVENT_ELECTRIC_SIGNBOARD_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRIC_BELT_DETECT = 17; // 电力检测安全带检测事件,  对应结构体 DEV_EVENT_ELECTRIC_BELT_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_BANNER_DETECTION = 18; // 拉横幅检测事件,	对应的结构体 DEV_EVENT_BANNER_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_SMART_KITCHEN_CLOTHES_DETECTION = 19; // 智慧厨房穿着检测事件, 对应结构体 DEV_EVENT_SMART_KITCHEN_CLOTHES_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_WATER_STAGE_MONITOR = 20; // 水位监测事件, 对应结构体DEV_EVENT_WATER_STAGE_MONITOR_INFO
        public static final int   EM_ANALYSE_EVENT_FLOATINGOBJECT_DETECTION = 21; // 漂浮物检测事件,  对应结构体 DEV_EVENT_FLOATINGOBJECT_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_RIOTERDETECTION = 22; // 人群聚集 对应结构体 DEV_EVENT_RIOTERL_INFO)
        public static final int   EM_ANALYSE_EVENT_IVS_LEFTDETECTION = 23; // 物品遗留事件 对应结构体 DEV_EVENT_LEFT_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_PARKINGDETECTION = 24; // 非法停车事件 对应结构体 DEV_EVENT_PARKINGDETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_WANDERDETECTION = 25; // 徘徊事件对应结构体 DEV_EVENT_WANDER_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_VIDEOABNORMALDETECTION = 26; // 视频异常对应结构体 DEV_EVENT_VIDEOABNORMALDETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_MOVEDETECTION = 27;  // 运动检测事件, 对应结构体 DEV_EVENT_MOVE_INFO
        public static final int   EM_ANALYSE_EVENT_VIDEO_NORMAL_DETECTION = 28; // 视频正常事件,在视频诊断检测周期结束时,将未报错的诊断项上报正常事件,对应结构体 DEV_EVENT_VIDEO_NORMAL_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_CONVEYER_BELT_BULK = 29; // 传送带大块异物检测事件, 对应结构体 DEV_EVENT_CONVEYER_BELT_BULK_INFO
        public static final int   EM_ANALYSE_EVENT_CONVEYER_BELT_NONLOAD = 30; // 传送带空载检测事件, 对应结构体 DEV_EVENT_CONVEYER_BELT_NONLOAD_INFO
        public static final int   EM_ANALYSE_EVENT_CONVEYER_BELT_RUNOFF = 31; // 传送带跑偏检测事件, 对应结构体 DEV_EVENT_CONVEYER_BELT_RUNOFF_INFO
        public static final int   EM_ANALYSE_EVENT_CONVEYER_BELT_BLOCK = 32; // 传送带阻塞检测事件, 对应结构体 DEV_EVENT_CONVEYORBLOCK_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_NUMBER_STAT = 33;    // 数量统计事件, 对应 结构体 DEV_EVENT_NUMBERSTAT_INFO
        public static final int   EM_ANALYSE_EVENT_FIGHTDETECTION = 34; // 斗殴事件, 对应结构体 DEV_EVENT_FIGHT_INFO
        public static final int   EM_ANALYSE_EVENT_SMOKEDETECTION = 35; // 烟雾报警检测事件, 对应结构体 DEV_EVENT_SMOKE_INFO
        public static final int   EM_ANALYSE_EVENT_FIREDETECTION = 36;  // 火警检测事件, 对应结构体 DEV_EVENT_FIRE_INFO
        public static final int   EM_ANALYSE_EVENT_PHONECALL_DETECT = 37; // 打电话检测事件, 对应结构体 DEV_EVENT_PHONECALL_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_SMOKING_DETECT = 38; // 吸烟检测事件, 对应结构体 DEV_EVENT_SMOKING_DETECT_INFO
        public static final int   EM_ANALYSE_EVENT_TUMBLE_DETECTION = 39; // 跌倒检测事件, 对应结构体 DEV_EVENT_TUMBLE_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_WATER_LEVEL_DETECTION = 40; // 水位尺检测事件, 对应结构体 DEV_EVENT_WATER_LEVEL_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_CLIMBDETECTION = 41; // 攀高检测事件, 对应结构体 DEV_EVENT_IVS_CLIMB_INFO
        public static final int   EM_ANALYSE_EVENT_MAN_NUM_DETECTION = 42; // 立体视觉区域内人数统计事件, 对应结构体DEV_EVENT_MANNUM_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_DIALRECOGNITION = 43; // 仪表检测事件, 对应结构体DEV_EVENT_DIALRECOGNITION_INFO
        public static final int   EM_ANALYSE_EVENT_ELECTRICFAULT_DETECT = 44; // 仪表类缺陷检测事件, 对应结构体DEV_EVENT_ELECTRICFAULTDETECT_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_ROAD_BLOCK = 45; // 交通路障检测事件,对应结构体 DEV_EVENT_TRAFFIC_ROAD_BLOCK_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_ROAD_CONSTRUCTION = 46; //交通道路施工检测事件,对应结构体 DEV_EVENT_TRAFFIC_ROAD_CONSTRUCTION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_FLOWSTATE = 47; // 交通流量统计事件,对应结构体 DEV_EVENT_TRAFFIC_FLOW_STATE
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_OVERSPEED = 48; // 超速事件,对应结构体 DEV_EVENT_TRAFFIC_OVERSPEED_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_UNDERSPEED = 49; // 欠速事件,对应结构体 DEV_EVENT_TRAFFIC_UNDERSPEED_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_OVERYELLOWLINE = 50; // 压黄线事件,对应结构体 DEV_EVENT_TRAFFIC_OVERYELLOWLINE_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_CROSSLANE = 51; // 违章变道事件, 对应结构体 DEV_EVENT_TRAFFIC_CROSSLANE_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFICJAM = 52; // 交通拥堵事件, 对应结构体 DEV_EVENT_TRAFFICJAM_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_PEDESTRAIN = 53; // 交通行人事件, 对应结构体 DEV_EVENT_TRAFFIC_PEDESTRAIN_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_THROW = 54; // 抛洒物事件, 对应结构体 DEV_EVENT_TRAFFIC_THROW_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_RETROGRADE = 55; // 交通逆行事件, 对应结构体 DEV_EVENT_TRAFFIC_RETROGRADE_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFICACCIDENT = 56; // 交通事故事件, 对应结构体 DEV_EVENT_TRAFFICACCIDENT_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_BACKING = 57; // 倒车事件, 对应结构体 DEV_EVENT_IVS_TRAFFIC_BACKING_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_FOG_DETECTION = 58; // 起雾检测事件, 对应结构体 DEV_EVENT_FOG_DETECTION
        public static final int   EM_ANALYSE_EVENT_IVS_CROSSREGIONDETECTION = 59; // 警戒区事件, 对应结构体 DEV_EVENT_CROSSREGION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_TRAFFIC_PARKING = 60; // 交通违章停车事件，对应结构体 DEV_EVENT_TRAFFIC_PARKING_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_FINANCE_CASH_TRANSACTION = 61; //智慧金融现金交易检测事件,对应结构体 DEV_EVENT_FINANCE_CASH_TRANSACTION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_LEAVEDETECTION = 62; // 离岗检测事件,对应结构体 DEV_EVENT_IVS_LEAVE_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_LADLE_NO_DETECTION = 63; // 钢包编号识别事件,对应结构体 DEV_EVENT_LADLE_NO_DETECTION_INFO
        public static final int   EM_ANALYSE_EVENT_IVS_OPEN_INTELLI = 78; ///// 开放智能事件(对应 DEV_EVENT_OPEN_INTELLI_INFO)
        public static final int   EM_ANALYSE_EVENT_IVS_GROUND_THING_DETECTION = 86; ///// 地物识别(对应 NET_DEV_EVENT_GROUND_THING_DETECTION_INFO)
        public static final int   EM_ANALYSE_EVENT_IVS_OCR_DETECTION = 87; ///// OCR检测事件(对应 NET_DEV_EVENT_OCR_DETECTION_INFO)
        public static final int   EM_ANALYSE_EVENT_CROSSLINEDETECTION_EX = 2000; // 警戒线事件(扩展), 对应结构体 DEV_EVENT_CROSSLINE_INFO_EX
    }

    // 文件分析状态
    public static class EM_FILE_ANALYSE_STATE extends SdkStructure
    {
        public static final int   EM_FILE_ANALYSE_UNKNOWN = -1;         // 未知
        public static final int   EM_FILE_ANALYSE_EXECUTING = 0;        // 分析中
        public static final int   EM_FILE_ANALYSE_FINISH = 1;           // 分析完成
        public static final int   EM_FILE_ANALYSE_FAILED = 2;           // 分析失败
    }

    // 二次录像分析事件信息
    public static class NET_SECONDARY_ANALYSE_EVENT_INFO extends SdkStructure
    {
        public int              emEventType;                          // 事件类型(对应的枚举值EM_ANALYSE_EVENT_TYPE)
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public Pointer          pstEventInfo;                         // 事件信息, 根据emEventType确定不同的结构体
        // EM_ANALYSE_EVENT_FACE_DETECTION (目标检测事件), 对应结构体 DEV_EVENT_FACEDETECT_INFO
        // EM_ANALYSE_EVENT_FACE_RECOGNITION (目标识别事件), 对应结构体 DEV_EVENT_FACERECOGNITION_INFO
        // EM_ANALYSE_EVENT_TRAFFICJUNCTION (交通路口事件), 对应结构体 DEV_EVENT_TRAFFICJUNCTION_INFO
        // EM_ANALYSE_EVENT_HUMANTRAIT (人体特征事件), 对应结构体 DEV_EVENT_HUMANTRAIT_INFO
        // EM_ANALYSE_EVENT_XRAY_DETECTION(X光机检测事件), 对应结构体 DEV_EVENT_XRAY_DETECTION_INFO
        // EM_ANALYSE_EVENT_WORKCLOTHESDETECT (工装(安全帽/工作服等)检测事件), 对应结构体 DEV_EVENT_WORKCLOTHES_DETECT_INFO
        // EM_ANALYSE_EVENT_WORKSTATDETECTION (作业检测事件), 对应结构体 DEV_EVENT_WORKSTATDETECTION_INFO
        // EM_ANALYSE_EVENT_CORSSLINEDETECTION (警戒线事件), 对应结构体 DEV_EVENT_CROSSLINE_INFO
        // EM_ANALYSE_EVENT_CROSSLINEDETECTION_EX (警戒线事件(扩展)), 对应结构体 DEV_EVENT_CROSSLINE_INFO_EX
        // EM_ANALYSE_EVENT_CROSSREGIONDETECTION (警戒区事件), 对应结构体 DEV_EVENT_CROSSREGION_INFO
        // EM_ANALYSE_EVENT_FEATURE_ABSTRACT(特征提取), 对应结构体 DEV_EVENT_FEATURE_ABSTRACT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_GLOVE_DETECT(电力检测手套检测事件),  对应结构体 DEV_EVENT_ELECTRIC_GLOVE_DETECT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_LADDER_DETECT(电力检测梯子检测事件),  对应结构体 DEV_EVENT_ELECTRIC_LADDER_DETECT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_CURTAIN_DETECT(电力检测布幔检测事件),  对应结构体 DEV_EVENT_ELECTRIC_CURTAIN_DETECT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_FENCE_DETECT(电力检测围栏检测事件),  对应结构体 DEV_EVENT_ELECTRIC_FENCE_DETECT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_SIGNBOARD_DETECT(电力检测标识牌检测事件),  对应结构体 DEV_EVENT_ELECTRIC_SIGNBOARD_DETECT_INFO
        // EM_ANALYSE_EVENT_ELECTRIC_BELT_DETECT(电力检测安全带检测事件),  对应结构体 DEV_EVENT_ELECTRIC_BELT_DETECT_INFO
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 任务自定义数据
    public static class NET_TASK_CUSTOM_DATA extends SdkStructure
    {
        public byte[]           szClientIP = new byte[128];           // 客户端IP
        public byte[]           szDeviceID = new byte[128];           // 设备ID
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 智能分析结果订阅的过滤条件
    public static class NET_ANALYSE_RESULT_FILTER extends SdkStructure
    {
        public int[]            dwAlarmTypes = new int[MAX_ANALYSE_FILTER_EVENT_NUM]; // 过滤事件, 详见dhnetsdk.h中"智能分析事件类型"
        public int              nEventNum;                            // 过滤事件数量
        public int              nImageDataFlag;                       // 是否包含图片, 0-包含,  1-不包含
        public byte[]           byReserved1 = new byte[4];            // 对齐
        public int              nImageTypeNum;                        // pImageType有效个数
        /**
         * 对应枚举类型为EM_FILTER_IMAGE_TYPE,int数组按位取值
         */
        public Pointer          pImageType;                           // 过滤上报的图片类型
        public byte[]           byReserved = new byte[1004];          // 保留字节
    }

    // CLIENT_AttachAnalyseTaskResult 接口输入参数
    public static class NET_IN_ATTACH_ANALYSE_RESULT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int[]            nTaskIDs = new int[MAX_ANALYSE_TASK_NUM]; // 智能分析任务ID
        public int              nTaskIdNum;                           // 智能分析任务个数, 0表示订阅全部任务
        public NET_ANALYSE_RESULT_FILTER stuFilter;                   // 过滤条件
        public byte[]           byReserved = new byte[4];             // 字节对齐
        public fAnalyseTaskResultCallBack cbAnalyseTaskResult;        // 智能分析任务结果订阅函数
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_ANALYSE_RESULT()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 智能分析任务结果信息
    public static class NET_ANALYSE_TASK_RESULT extends SdkStructure
    {
        public int              nTaskID;                              // 任务ID
        public byte[]           szFileID = new byte [NET_COMMON_STRING_128]; // 文件ID, 分析文件时有效
        public int              emFileAnalyseState;                   // 文件分析状态(对应的枚举值EM_FILE_ANALYSE_STATE)
        public byte[]           szFileAnalyseMsg = new byte[NET_COMMON_STRING_256]; // 文件分析额外信息, 一般都是分析失败的原因
        public NET_SECONDARY_ANALYSE_EVENT_INFO[] stuEventInfos = new NET_SECONDARY_ANALYSE_EVENT_INFO[MAX_SECONDARY_ANALYSE_EVENT_NUM]; // 事件信息
        public int              nEventCount;                          // 实际的事件个数
        public NET_TASK_CUSTOM_DATA stuCustomData = new NET_TASK_CUSTOM_DATA(); // 自定义数据
        public byte[]           szUserData = new byte[64];            // 频源数据，标示视频源信息，对应addPollingTask中UserData字段。
        public byte[]           szTaskUserData = new byte[256];       // 任务数据
        public Pointer          pstuEventInfosEx;                     // 扩展事件信息 NET_SECONDARY_ANALYSE_EVENT_INFO
        public int              nRetEventInfoExNum;                   // 返回扩展事件信息个数
        public byte[]           szUserDefineData = new byte[512];     // 用户定义数据，对应analyseTaskManager.analysePushPictureFileByRule中UserDefineData字段
        public byte[]           byReserved = new byte[184];           // 保留字节

        public NET_ANALYSE_TASK_RESULT() {
            for(int i = 0; i < stuEventInfos.length; i ++){
                this.stuEventInfos[i] = new NET_SECONDARY_ANALYSE_EVENT_INFO();
            }
        }
    }

    // 智能分析任务结果回调信息
    public static class NET_CB_ANALYSE_TASK_RESULT_INFO extends SdkStructure
    {
        public NET_ANALYSE_TASK_RESULT[] stuTaskResultInfos = new NET_ANALYSE_TASK_RESULT[MAX_ANALYSE_TASK_NUM]; // 智能分析任务结果信息
        public int              nTaskResultNum;                       // 任务个数
        public byte[]           byReserved = new byte[1028];          // 保留字节

        public NET_CB_ANALYSE_TASK_RESULT_INFO() {
            for(int i = 0; i < MAX_ANALYSE_TASK_NUM; i ++){
                this.stuTaskResultInfos[i] = new NET_ANALYSE_TASK_RESULT();
            }
        }
    }

    // 智能分析状态订阅函数原型, lAttachHandle 是 CLIENT_AttachAnalyseTaskResult接口的返回值,pstAnalyseTaskResult对应结构体NET_CB_ANALYSE_TASK_RESULT_INFO
    public interface fAnalyseTaskResultCallBack extends Callback {
        public int invoke(LLong lAttachHandle,Pointer pstAnalyseTaskResult,Pointer pBuf,int dwBufSize,Pointer dwUser);
    }

    // 取消订阅智能分析结果, lAttachHandle 为 CLIENT_AttachAnalyseTaskResult接口的返回值@@
    public Boolean CLIENT_DetachAnalyseTaskResult(LLong lAttachHandle);

    // 订阅智能分析结果, pInParam 资源由用户申请和释放
    public LLong CLIENT_AttachAnalyseTaskResult(LLong lLoginID,NET_IN_ATTACH_ANALYSE_RESULT pInParam,int nWaitTime);

    /*--------任务结束：  CLIENT_AttachAnalyseTaskResult / CLIENT_DetachAnalyseTaskResult --------*/
    /*--------任务开始：  T0058223ERR191213010-TASK1  停留事件：DEV_EVENT_STAY_INFO--------*/
    // 视频分析物体信息结构体
    public static class DH_MSG_OBJECT extends SdkStructure
    {
        public int              nObjectID;                            // 物体ID,每个ID表示一个唯一的物体
        public byte[]           szObjectType = new byte[128];         // 物体类型
        public int              nConfidence;                          // 置信度(0~255),值越大表示置信度越高
        public int              nAction;                              // 物体动作:1:Appear 2:Move 3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
        public DH_RECT          BoundingBox;                          // 包围盒
        public NET_POINT        Center;                               // 物体型心
        public int              nPolygonNum;                          // 多边形顶点个数
        public NET_POINT[]      Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM); // 较精确的轮廓多边形
        public int              rgbaMainColor;                        // 表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时, 其值为0x00ff0000.
        public byte[]           szText = new byte[128];               // 物体上相关的带0结束符文本,比如车牌,集装箱号等等
        public byte[]           szObjectSubType = new byte[62];       // 物体子类别,根据不同的物体类型,可以取以下子类型：
        // Vehicle Category:"Unknown"  未知,"Motor" 机动车,"Non-Motor":非机动车,"Bus": 公交车,"Bicycle" 自行车,"Motorcycle":摩托车,"PassengerCar":客车,
        // "LargeTruck":大货车,    "MidTruck":中货车,"SaloonCar":轿车,"Microbus":面包车,"MicroTruck":小货车,"Tricycle":三轮车,    "Passerby":行人
        // "DregsCar":渣土车, "Excavator":挖掘车, "Bulldozer":推土车, "Crane":吊车, "PumpTruck":泵车, "MachineshopTruck":工程车
        //  Plate Category："Unknown" 未知,"Normal" 蓝牌黑牌,"Yellow" 黄牌,"DoubleYellow" 双层黄尾牌,"Police" 警牌,
        // "SAR" 港澳特区号牌,"Trainning" 教练车号牌
        // "Personal" 个性号牌,"Agri" 农用牌,"Embassy" 使馆号牌,"Moto" 摩托车号牌,"Tractor" 拖拉机号牌,"Other" 其他号牌
        // "Civilaviation"民航号牌,"Black"黑牌
        // "PureNewEnergyMicroCar"纯电动新能源小车,"MixedNewEnergyMicroCar,"混合新能源小车,"PureNewEnergyLargeCar",纯电动新能源大车
        // "MixedNewEnergyLargeCar"混合新能源大车
        // HumanFace Category:"Normal" 普通人脸,"HideEye" 眼部遮挡,"HideNose" 鼻子遮挡,"HideMouth" 嘴部遮挡,"TankCar"槽罐车(装化学药品、危险品)
        public short            wColorLogoIndex;                      // 车标索引
        public short            wSubBrand;                            // 车辆子品牌 需要通过映射表得到真正的子品牌 映射表详见开发手册
        public byte             byReserved1;
        public byte             bPicEnble;                            // 是否有物体对应图片文件信息
        public NET_PIC_INFO     stPicInfo;                            // 物体对应图片信息
        public byte             bShotFrame;                           // 是否是抓拍张的识别结果
        public byte             bColor;                               // 物体颜色(rgbaMainColor)是否可用
        public byte             byReserved2;                          // 保留字节,留待扩展
        public byte             byTimeType;                           // 时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX      stuCurrentTime;                       // 针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX      stuStartTime;                         // 开始时间戳（物体开始出现时）
        public NET_TIME_EX      stuEndTime;                           // 结束时间戳（物体最后出现时）
        public DH_RECT          stuOriginalBoundingBox;               // 包围盒(绝对坐标)
        public DH_RECT          stuSignBoundingBox;                   // 车标坐标包围盒
        public int              dwCurrentSequence;                    // 当前帧序号（抓下这个物体时的帧）
        public int              dwBeginSequence;                      // 开始帧序号（物体开始出现时的帧序号）
        public int              dwEndSequence;                        // 结束帧序号（物体消逝时的帧序号）
        public long             nBeginFileOffset;                     // 开始时文件偏移, 单位: 字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long             nEndFileOffset;                       // 结束时文件偏移, 单位: 字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[]           byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; // 物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
        public byte[]           byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //上半身物体颜色相似度(物体类型为人时有效)
        public byte[]           byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //下半身物体颜色相似度(物体类型为人时有效)
        public int              nRelativeID;                          // 相关物体ID
        public byte[]           szSubText = new byte[20];             // "ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public short            wBrandYear;                           // 车辆品牌年款 需要通过映射表得到真正的年款 映射表详见开发手册

        protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
            int alignment = super.getNativeAlignment(type, value, isFirstElement);
            return Math.min(4, alignment);
        }

        @Override
        public String toString() {
            return "DH_MSG_OBJECT{" +
                    "nObjectID=" + nObjectID +
                    ", szObjectType=" + new String(szObjectType) +
                    ", nConfidence=" + nConfidence +
                    ", nAction=" + nAction +
                    ", BoundingBox=" + BoundingBox +
                    ", Center=" + Center +
                    ", nPolygonNum=" + nPolygonNum +
                    ", Contour=" + Arrays.toString(Contour) +
                    ", rgbaMainColor=" + rgbaMainColor +
                    ", szText=" + new String(szText) +
                    ", szObjectSubType=" + new String(szObjectSubType) +
                    ", wColorLogoIndex=" + wColorLogoIndex +
                    ", wSubBrand=" + wSubBrand +
                    ", byReserved1=" + byReserved1 +
                    ", bPicEnble=" + bPicEnble +
                    ", bShotFrame=" + bShotFrame +
                    ", bColor=" + bColor +
                    ", byReserved2=" + byReserved2 +
                    ", byTimeType=" + byTimeType +
                    ", stuCurrentTime=" + stuCurrentTime +
                    ", stuStartTime=" + stuStartTime +
                    ", stuEndTime=" + stuEndTime +
                    ", stuOriginalBoundingBox=" + stuOriginalBoundingBox +
                    ", stuSignBoundingBox=" + stuSignBoundingBox +
                    ", dwCurrentSequence=" + dwCurrentSequence +
                    ", dwBeginSequence=" + dwBeginSequence +
                    ", dwEndSequence=" + dwEndSequence +
                    ", nBeginFileOffset=" + nBeginFileOffset +
                    ", nEndFileOffset=" + nEndFileOffset +
                    ", byColorSimilar=" + new String(byColorSimilar) +
                    ", byUpperBodyColorSimilar=" + new String(byUpperBodyColorSimilar) +
                    ", byLowerBodyColorSimilar=" + new String(byLowerBodyColorSimilar) +
                    ", nRelativeID=" + nRelativeID +
                    ", szSubText=" + new String(szSubText) +
                    ", wBrandYear=" + wBrandYear +
                    '}';
        }
    }

    // 事件类型EVENT_IVS_STAYDETECTION(停留事件)对应的数据块描述信息
    public static class DEV_EVENT_STAY_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public DH_MSG_OBJECT    stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];             // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从 0 开始
        public int              nDetectRegionNum;                     //较精确的轮廓多边形                								// 规则检测区域顶点数
        public DH_POINT[]       DetectRegion = (DH_POINT[])new DH_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nObjectNum;                           // 检测到的物体个数
        public DH_MSG_OBJECT[]  stuObjectIDs = (DH_MSG_OBJECT[])new DH_MSG_OBJECT().toArray(DH_MAX_OBJECT_NUM); // 检测到的物体
        public int              nAreaID;                              // 区域ID(一个预置点可以对应多个区域ID)
        public int              bIsCompliant;                         // 该场景下是否合规
        public PTZ_PRESET_UNIT  stPosition;                           // 预置点的坐标和放大倍数
        public int              nCurChannelHFOV;                      // 当前报警通道的横向视场角,单位：度，实际角度乘以100
        public int              nCurChannelVFOV;                      // 当前报警通道的垂直视场角,单位：度，实际角度乘以100
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景广角图
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_LINK_INFO    stuLinkInfo;                          // 联动信息，保存其他设备传输的信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[624];            // 保留字节,留待扩展.
    }

    /*--------任务结束：  T0058223ERR191213010-TASK1  停留事件：DEV_EVENT_STAY_INFO--------*/
    /*--------任务开始：  ERR191213083-TASK1  发动机数据上报：DH_ALARM_ENGINE_FAILURE_STATUS --------*/
    // 发动机故障状态
    public static class EM_ENGINE_FAILURE_STATUS extends SdkStructure
    {
        public static final int   EM_ENGINE_FAILURE_UNKNOWN = 0;        // 未知
        public static final int   EM_ENGINE_FAILURE_NOTACTIVE = 1;      // "Not active"
        public static final int   EM_ENGINE_FAILURE_ACTIVE = 2;         // "Active"
        public static final int   EM_ENGINE_FAILURE_BLINK = 3;          // "Blink"
        public static final int   EM_ENGINE_FAILURE_NOTAVAILABLE = 4;   // "Not Available"
    }

    // 发动机故障状态上报事件( DH_ALARM_ENGINE_FAILURE_STATUS )
    public static class ALARM_ENGINE_FAILURE_STATUS_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              emStatus;                             // 发动机故障状态 详见 EM_ENGINE_FAILURE_STATUS
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息(车载需求)
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    /*--------任务开始：  ERR191213083-TASK1  发动机数据上报：DH_ALARM_ENGINE_FAILURE_STATUS--------*/
    /************************************************************************/
    /*视频上传交通运输部需求   从这往下                                 */
    /************************************************************************/
    // 获取转码虚拟通道号(虚拟通道号用于预览与回放), pInParam 和pOutParam 由用户申请和释放
    public boolean CLIENT_GetVirtualChannelOfTransCode(LLong lLoginID,NET_IN_GET_VIRTUALCHANNEL_OF_TRANSCODE pInParam,NET_OUT_GET_VIRTUALCHANNEL_OF_TRANSCODE pOutParam,int nWaitTime);

    // 虚拟通道转码策略
    public static class NET_VIRTUALCHANNEL_POLICY extends SdkStructure
    {
        public int              bDeleteByCaller;                      // 是否由用户管理虚拟通道, TRUE:由用户管理  FALSE:由设备管理
        public int              bContinuous;                          // 是否持续转码
        public int              nVirtualChannel;                      //虚拟通道号。当大于0时表示虚拟通道号由客户端指定和管理,范围处于 CLIENT_GetCapsOfTransCode返回的nMinVirtualChannel~nMaxVirtualChannel两值中间。否则由压缩设备管理
        public byte[]           byReserved = new byte[508];           // 保留字节
    }

    //CLIENT_GetVirtualChannelOfTransCode 接口输入参数
    public static class NET_IN_GET_VIRTUALCHANNEL_OF_TRANSCODE extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public NET_VIDEO_SOURCE_INFO stuVideoSourceInfo;              // 视频源信息
        public NET_TRANSCODE_VIDEO_FORMAT stuTransVideoFormat;        // 转码视频格式
        public NET_TRANSCODE_AUDIO_FORMAT stuTransAudioFormat;        // 转码音频格式
        public NET_VIRTUALCHANNEL_POLICY stuVirtualChnPolicy;         // 虚拟通道转码策略
        public NET_TRANSCODE_SNAP_FORMAT stuSnapFormat;               // 转码抓图格式参数
        public NET_TRANSCODE_WATER_MARK[] stuWaterMark = (NET_TRANSCODE_WATER_MARK[]) new NET_TRANSCODE_WATER_MARK().toArray(4); // 水印配置
        public NET_TRANSCODE_IMAGE_WATER_MARK[] stuImageWaterMark = new NET_TRANSCODE_IMAGE_WATER_MARK[4]; //图片水印配置,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_TRANSCODE_IMAGE_WATER_MARK}
        public int              nImageWaterMarkNum;                   //图片水印配置数量

        public NET_IN_GET_VIRTUALCHANNEL_OF_TRANSCODE(){
            this.dwSize = this.size();
        }
    }

   //转码抓图格式参数
    public static class NET_TRANSCODE_SNAP_FORMAT extends SdkStructure {
        public int              nWidth;                               // 抓图宽度
        public int              nHeight;                              // 抓图高度
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    //水印配置
    public static class NET_TRANSCODE_WATER_MARK extends SdkStructure {
        public byte[]           szText = new byte[256];               // 水印文本信息
        public int              bAngle;                               //nAngle字段是否生效
        public int              bOpacity;                             //nOpacity字段是否生效
        public int              bFontSize;                            //nFontSize字段是否生效
        public int              bRows;                                //nRows字段是否生效
        public int              bColumns;                             //nColumns字段是否生效
        public int              bColor;                               //nColor字段是否生效
        public int              nAngle;                               //水印倾斜角，范围-180~180，顺时针旋转为正值，逆时针旋转为负值，默认为0
        public int              nOpacity;                             //水印的不透明度，范围0-100，0表示全透明，默认100
        public int              nFontSize;                            //水印字体大小
        public int              nRows;                                //水印行数
        public int              nColumns;                             //水印列数
        public int              nColor;                               //水印字体颜色的RGB值，默认为0，表示黑色
        public int              bPosition;                            //Position字段是否生效
        public int              bType;                                //nType字段是否生效
        public int              nPosition;                            //水印位置0：左上 1：右上 2：左下 3：右下
        public int              nType;                                //区分水印和OSD，如未指定Type字段或Type为0时表示叠加为水印（效果为铺满全屏）；Type为1时表示OSD叠加（只展示在某个区域），Position字段有效。0：水印1：OSD
        public byte[]           byReserved = new byte[448];           // 保留字节
    }

    // 视频源信息
    public static class NET_VIDEO_SOURCE_INFO extends SdkStructure {
        public int              emProtocolType;                       // 设备协议类型,枚举值参考EM_DEV_PROTOCOL_TYPE
        public byte[]           szIp = new byte[64];                  // 前端设备IP地址
        public int              nPort;                                // 前端设备端口号
        public byte[]           szUser = new byte[128];               // 前端设备用户名
        public byte[]           szPwd = new byte[128];                // 前端设备密码
        public int              nChannelID;                           // 前端设备通道号
        public byte[]           szStreamUrl = new byte[512];          // 视频源url地址, emProtocolType为EM_DEV_PROTOCOL_GENERAL 时有效
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 设备协议类型
    public static class EM_DEV_PROTOCOL_TYPE extends SdkStructure
    {
        public static final int   EM_DEV_PROTOCOL_UNKNOWN = 0;          // 未知
        public static final int   EM_DEV_PROTOCOL_V2 = 1;               // 私有二代
        public static final int   EM_DEV_PROTOCOL_V3 = 2;               // 私有三代
        public static final int   EM_DEV_PROTOCOL_ONVIF = 3;            // onvif
        public static final int   EM_DEV_PROTOCOL_GENERAL = 4;          // general
        public static final int   EM_DEV_PROTOCOL_GB28181 = 5;          // 国标GB28181
        public static final int   EM_DEV_PROTOCOL_EHOME = 6;
        public static final int   EM_DEV_PROTOCOL_HIKVISION = 7;
        public static final int   EM_DEV_PROTOCOL_BSCP = 8;
        public static final int   EM_DEV_PROTOCOL_PRIVATE = 9;          // 私有
        public static final int   EM_DEV_PROTOCOL_RTSP = 10;            // RTSP
        public static final int   EM_DEV_PROTOCOL_HBGK = 11;
        public static final int   EM_DEV_PROTOCOL_LUAN = 12;
    }

    // 转码视频格式
    public static class NET_TRANSCODE_VIDEO_FORMAT extends SdkStructure {
        public int              emCompression;                        // 视频压缩格式,枚举值参考EM_TRANSCODE_VIDEO_COMPRESSION
        public int              nWidth;                               // 视频宽度
        public int              nHeight;                              // 视频高度
        public int              emBitRateControl;                     // 码流控制模式,枚举值参考NET_EM_BITRATE_CONTROL
        public int              nBitRate;                             // 视频码流(kbps)
        public float            fFrameRate;                           // 视频帧率
        public int              nIFrameInterval;                      // I帧间隔(1-100)，比如50表示每49个B帧或P帧，设置一个I帧。
        public int              emImageQuality;                       // 图像质量,枚举值参考EM_TRANSCODE_IMAGE_QUALITY
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 视频压缩格式
    public static class EM_TRANSCODE_VIDEO_COMPRESSION extends SdkStructure {
        public static final int   EM_TRANSCODE_VIDEO_AUTO = 0;          // auto
        public static final int   EM_TRANSCODE_VIDEO_MPEG4 = 1;         // MPEG4
        public static final int   EM_TRANSCODE_VIDEO_MPEG2 = 2;         // MPEG2
        public static final int   EM_TRANSCODE_VIDEO_MPEG1 = 3;         // MPEG1
        public static final int   EM_TRANSCODE_VIDEO_MJPG = 4;          // MJPG
        public static final int   EM_TRANSCODE_VIDEO_H263 = 5;          // H.263
        public static final int   EM_TRANSCODE_VIDEO_H264 = 6;          // H.264
        public static final int   EM_TRANSCODE_VIDEO_H265 = 7;          // H.265
    }

    // 码流控制模式
    public static class NET_EM_BITRATE_CONTROL extends SdkStructure {
        public static final int   EM_BITRATE_CBR = 0;                   // 固定码流
        public static final int   EM_BITRATE_VBR = 1;                   // 可变码流
    }

    // 图像质量
    public static class EM_TRANSCODE_IMAGE_QUALITY extends SdkStructure {
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_SELFADAPT = 0; // 自适应
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q10 = 1;   // 10%
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q30 = 2;   // 30%
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q50 = 3;   // 50%
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q60 = 4;   // 60%
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q80 = 5;   // 80%
        public static final int   EM_TRANSCODE_IMAGE_QUALITY_Q100 = 6;  // 100%
    }

    // 转码音频格式
    public static class NET_TRANSCODE_AUDIO_FORMAT extends SdkStructure {
        public int              emCompression;                        // 音频压缩模式,枚举值参考NET_EM_AUDIO_FORMAT
        public int              nFrequency;                           // 音频采样频率
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    public static class NET_EM_AUDIO_FORMAT extends SdkStructure {
        public static final int   EM_AUDIO_FORMAT_UNKNOWN = 0;          // unknown
        public static final int   EM_AUDIO_FORMAT_G711A = 1;            // G711a
        public static final int   EM_AUDIO_FORMAT_PCM = 2;              // PCM
        public static final int   EM_AUDIO_FORMAT_G711U = 3;            // G711u
        public static final int   EM_AUDIO_FORMAT_AMR = 4;              // AMR
        public static final int   EM_AUDIO_FORMAT_AAC = 5;              // AAC
        public static final int   EM_AUDIO_FORMAT_G726 = 6;             // G.726
        public static final int   EM_AUDIO_FORMAT_G729 = 7;             // G.729
        public static final int   EM_AUDIO_FORMAT_ADPCM = 8;            // ADPCM
        public static final int   EM_AUDIO_FORMAT_MPEG2 = 9;            // MPEG2
        public static final int   EM_AUDIO_FORMAT_MPEG2L2 = 10;         // MPEG2-Layer2
        public static final int   EM_AUDIO_FORMAT_OGG = 11;             // OGG
        public static final int   EM_AUDIO_FORMAT_MP3 = 12;             // MP3
        public static final int   EM_AUDIO_FORMAT_G7221 = 13;           // G.722.1
    }

    //CLIENT_GetVirtualChannelOfTransCode 接口输出参数
    public static class NET_OUT_GET_VIRTUALCHANNEL_OF_TRANSCODE extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              nVirtualChannel;                      // 虚拟通道号

        public NET_OUT_GET_VIRTUALCHANNEL_OF_TRANSCODE(){
            this.dwSize = this.size();
        }
    }

    // 获取转码能力集, pInParam 和pOutParam 由用户申请和释放
    public boolean CLIENT_GetCapsOfTransCode(LLong lLoginID,NET_IN_TRANDCODE_GET_CAPS pInParam,NET_OUT_TRANSCODE_GET_CAPS pOutParam,int nWaitTime);

    // 删除转码虚拟通道号
    public boolean CLIENT_DelVirtualChannelOfTransCode(LLong lLoginID,NET_IN_DEL_VIRTUALCHANNEL_OF_TRANSCODE pInParam,NET_OUT_DEL_VIRTUALCHANNEL_OF_TRANSCODE pOutParam,int nWaitTime);

    // CLIENT_DelVirtualChannelOfTransCode 接口输入参数
    public static class NET_IN_DEL_VIRTUALCHANNEL_OF_TRANSCODE extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              nVirtualChannel;                      // 虚拟通道号, -1 表示删除所有虚拟通道

        public NET_IN_DEL_VIRTUALCHANNEL_OF_TRANSCODE(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_DelVirtualChannelOfTransCode 接口输出参数
    public static class NET_OUT_DEL_VIRTUALCHANNEL_OF_TRANSCODE extends SdkStructure {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_DEL_VIRTUALCHANNEL_OF_TRANSCODE(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetCapsOfTransCode 接口输入参数
    public static class NET_IN_TRANDCODE_GET_CAPS extends SdkStructure {
        public int              dwSize;                               // 结构体大小

        public NET_IN_TRANDCODE_GET_CAPS(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetCapsOfTransCode 接口输出参数
    public static class NET_OUT_TRANSCODE_GET_CAPS extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              nMinVirtualChannel;                   // 最小虚拟通道号
        public int              nMaxVirtualChannel;                   // 最大虚拟通道号
        public int              bSupportErrorCode;                    // 是否支持压缩错误码实时上报
        public int              bSupportContinuous;                   // 是否支持持续转码
        public int              bSupportDelByCaller;                  // 是否支持由用户管理虚拟通道
        public int              bSupportSpecifyVirtualChannel;        // 是否支持由调用者指定虚拟通道号，
                                                            // 此项为true时，调用CLIENT_GetVirtualChannelOfTransCode时指定虚拟通道nVirtualChannel，虚拟通道号必须在nMinVirtualChannel~nMaxVirtualChannel的范围内；
                                                            // 当此项为false时，不支持客户端指定虚拟通道号。
        public float            fMaxDownLoadSpeed;                    // 支持最大的压缩下载倍速
        public int              nSupportCompressMaxChannel;           // 设备当前支持的最多压缩通道数
        public byte[]           szSupportCompressResolutionRangeMin = new byte[32]; // 支持压缩的分辨率 最小值
        public byte[]           szSupportCompressResolutionRangeMax = new byte[32]; // 支持压缩的分辨率 最大值
        public int              nSupportCompressFpsRangeMin;          // 支持压缩的帧率 最小值
        public int              nSupportCompressFpsRangeMax;          // 支持压缩的帧率 最大值
        public byte[]           szSupportCompressAudioTypes = new byte[64*32]; // 支持的音频格式
        public int              nSupportCompressAudioTypesNum;        // 支持的音频格式个数
        public int              nSupportCompressCompressionTypesNum;  // 支持的视频压缩格式个数
        public byte[]           szSupportCompressCompressionTypes = new byte[64*32]; // 支持的视频压缩格式
        public BYTE_ARRAY_64[]  szSupportImageOsdTypes = new BYTE_ARRAY_64[64]; //支持的图片OSD
        public int              nSupportImageOsdTypesNum;             //支持的图片OSD个数

        public NET_OUT_TRANSCODE_GET_CAPS(){
            this.dwSize = this.size();
        }
    }

    // 订阅虚拟转码通道状态, pInParam 由用户申请和释放
    public LLong CLIENT_AttachVirtualChannelStatus(LLong lLoginID,NET_IN_ATTACH_VIRTUALCHANNEL_STATUS pInParam,int nWaitTime);

    //CLIENT_AttachVirtualChannelStatus 接口输入参数
    public static class NET_IN_ATTACH_VIRTUALCHANNEL_STATUS extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public byte[]           byReserved = new byte[4];             // 用于字节对齐
        public fVirtualChannelStatusCallBack cbVirtualChannelStatus;  // 虚拟转码通道状态订阅函数
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_VIRTUALCHANNEL_STATUS(){
            this.dwSize = this.size();
        }
    }

    public interface fVirtualChannelStatusCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_CB_VIRTUALCHANNEL_STATUS_INFO pstVirChnStatusInfo,Pointer dwUser);
    }

    // 虚拟转码通道状态信息
    public class NET_CB_VIRTUALCHANNEL_STATUS_INFO extends SdkStructure {
        public int              nVirChannelID;                        // 虚拟转码通道号
        public int              emVirChannelStatus;                   // 虚拟转码通道状态,枚举值参考EM_VIRCHANNEL_STATUS
        public byte[]           byReserved = new byte[1024];          // 保留字节
// 		 public static class ByValue extends NET_CB_VIRTUALCHANNEL_STATUS_INFO implements SdkStructure.ByValue { }
    }

    public static class EM_VIRCHANNEL_STATUS extends SdkStructure {
        public static final int   EM_VIRCHANNEL_STATUS_UNKNOWN = -1;    // 未知
        public static final int   EM_VIRCHANNEL_STATUS_OVER_DECODE = 0; // 超出解码
        public static final int   EM_VIRCHANNEL_STATUS_OVER_COMPRESS = 1; // 超出压缩
        public static final int   EM_VIRCHANNEL_STATUS_NO_ORIGI_STREAM = 2; // 无原始码流
        public static final int   EM_VIRCHANNEL_STATUS_SLAVE_OFFLINE = 3; // 压缩通道所在的从片掉线
        public static final int   EM_VIRCHANNEL_STATUS_UNKNOWN_FAILURE = 255; // 未知的失败原因
    }

    // 取消订阅虚拟转码通道状态, lAttachHandle 为 CLIENT_AttachVirtualChannelStatus 函数的返回值
    public boolean CLIENT_DetachVirtualChannelStatus(LLong lAttachHandle);

    /************************************************************************/
    /* 视频上传交通运输部需求           从这往上                        */
    /************************************************************************/
    // 设置动态子连接断线回调函数,目前SVR设备的预览和回放是短连接的。
    public void CLIENT_SetSubconnCallBack(Callback cbSubDisConnect,Pointer dwUser);

    // 动态子连接断开回调函数原形
    public interface fSubDisConnect extends Callback {
        public void invoke(int emInterfaceType,Boolean bOnline,LLong lOperateHandle,LLong lLoginID,Pointer dwUser);
    }

    // 接口类型,对应CLIENT_SetSubconnCallBack接口
    public static class EM_INTERFACE_TYPE extends SdkStructure
    {
        public static final int   DH_INTERFACE_OTHER = 0;               // 未知接口
        public static final int   DH_INTERFACE_REALPLAY = 1;            // 实时预览接口
        public static final int   DH_INTERFACE_PREVIEW = 2;             // 多画面预览接口
        public static final int   DH_INTERFACE_PLAYBACK = 3;            // 回放接口
        public static final int   DH_INTERFACE_DOWNLOAD = 4;            // 下载接口
        public static final int   DH_INTERFACE_REALLOADPIC = 5;         // 下载智能图片接口
    }

    //人脸开门输入参数
    public static class NET_IN_FACE_OPEN_DOOR extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //门通道号
        public int              emCompareResult;                      //比对结果,EM_COMPARE_RESULT
        public NET_OPENDOOR_MATCHINFO stuMatchInfo;                   //匹配信息
        public NET_OPENDOOR_IMAGEINFO stuImageInfo;                   //图片信息

        public NET_IN_FACE_OPEN_DOOR(){
            this.dwSize = this.size();
        }
    }

    //匹配信息
    public static class NET_OPENDOOR_MATCHINFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[32];              //远程用户ID
        public byte[]           szUserName = new byte[32];            //用户名
        public int              emUserType;                           //用户类型
        public byte[]           szName = new byte[64];                //门禁名称
        public int              nMatchRate;                           //匹配度，范围为0-100
        public int              emOpenDoorType;                       //开门方式
        public NET_TIME         stuActivationTime;                    //(卡、头像)生效日期
        public NET_TIME         stuExpiryTime;                        //(卡、头像)截止日期
        public int              nScore;                               // 信用积分
        public byte[]           szCompanyName = new byte[MAX_COMPANY_NAME_LEN]; //单位名称
        public byte[]           szCompanionName = new byte[120];      //陪同人员姓名
        public byte[]           szCompanionCompany = new byte[MAX_COMPANY_NAME_LEN]; //陪同人员单位名称
        public byte[]           szPermissibleArea = new byte[MAX_COMMON_STRING_128]; //准许通行区域
        public byte[]           szSection = new byte[200];            //部门名称
        public Pointer          pstuCustomEducationInfo;              // 教育信息,参考NET_CUSTOM_EDUCATION_INFO
        public Pointer          pstuHealthCodeInfo;                   //健康码信息,NET_HEALTH_CODE_INFO
        public byte[]           szRoomNo = new byte[32];              //房间号
        public Pointer          pstuIDCardInfo;                       //证件信息,NET_IDCARD_INFO
        public Pointer          pstuBusStationInfo;                   //公交站信息,NET_BUS_STATION_INFO
        public Pointer          pstuCustomWorkerInfo;                 //工地工人信息,NET_CUSTOM_WORKER_INFO
        public int              bUseMatchInfoEx;                      //否使用匹配信息扩展字段
        public Pointer          pstuMatchInfoEx;                      //匹配信息扩展字段，NET_OPENDOOR_MATCHINFO_EX
        public Pointer          pstuHSJCInfo;                         // 核酸检测信息,NET_HSJC_INFO
        public Pointer          pstuVaccineInfo;                      // 新冠疫苗接种信息,NET_VACCINE_INFO
        public Pointer          pstuTravelInfo;                       // 行程码信息,NET_TRAVEL_INFO
        public Pointer          pstuCustomVisitorInfo;                //访客信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_CUSTOM_VISITOR_INFO}
        public int              nRemoteQRCodeType;                    //远程二维码开门时的二维码类型 0-默认值，1-访客二维码，2-一卡通二维码
        public byte[]           byReserved = new byte[4];
    }

    // 教育信息
    public static class NET_CUSTOM_EDUCATION_INFO extends SdkStructure
    {
        public int              emInfoType;                           // 信息类型
        public int              nStudentSeatNumber;                   // 座位号,最小值为1
        public byte[]           szInfoContent = new byte[128];        // 消息内容
        public int              emVoiceType;                          // 语音类型,EM_CUSTOM_EDUCATION_VOICE_TYPE
    }

    //图片信息
    public static class NET_OPENDOOR_IMAGEINFO extends SdkStructure
    {
        public int              nLibImageLen;                         //人脸库照片长度，限制为150k
        public int              nSnapImageLen;                        //抓拍照片长度，限制为150k
        public Pointer          pLibImage;                            //人脸库照片，内存由用户申请
        public Pointer          pSnapImage;                           //抓拍照片，内存由用户申请
        public byte[]           byReserved = new byte[1024];
    }

    //人脸开门输出参数
    public static class NET_OUT_FACE_OPEN_DOOR extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_FACE_OPEN_DOOR(){
            this.dwSize = this.size();
        }
    }

    //人脸开门
    public boolean CLIENT_FaceOpenDoor(LLong lLoginID,NET_IN_FACE_OPEN_DOOR pInParam,NET_OUT_FACE_OPEN_DOOR pOutParam,int nWaitTime);

    // 水位场景类型
    public static class EM_WATERSTAGE_SCENE_TYPE extends SdkStructure
    {
        public static final int   EM_WATERMONITOR_SCENE_UNKNOWN = 0;    // 未知
        public static final int   EM_WATERMONITOR_SCENE_WATERSTAGE_RULE = 1; // 水位检测, 有水位尺
        public static final int   EM_WATERMONITOR_SCENE_WATERSTAGE_NO_RULE = 2; // 水位检测, 无水位尺
        public static final int   EM_WATERMONITOR_SCENE_WATERLOGG_RULE = 3; // 内涝检测, 有水位尺
        public static final int   EM_WATERMONITOR_SCENE_WATERLOGG_NO_RULE = 4; // 内涝检测, 无水位尺
    }

    // 水面分割掩膜信息
    public static class NET_WATER_SURFACE_MASK_INFO extends SdkStructure
    {
        public int              nColNum;                              // 水面分割掩膜列数
        public int              nOffset;                              // 偏移
        public int              nLength;                              // 长度
        public byte[]           byReserved = new byte[1020];          // 预留字段
    }

    // 水位监测事件, 目前仅用于任务型智能分析
    public static class DEV_EVENT_WATER_STAGE_MONITOR_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public int              emClassType;                          // 智能事件所属大类
        public int              emSceneType;                          // 水位场景类型
        public double           dbMark;                               // 水尺读数
        public NET_POINT        stuCrossPoint;                        // 水尺与水面交点
        public NET_WATER_SURFACE_MASK_INFO stuWaterSurfaceMask;       // 水面分割掩膜信息, emSceneType 为EM_WATERMONITOR_SCENE_WATERSTAGE_NO_RULE 或者EM_WATERMONITOR_SCENE_WATERLOGG_NO_RULE有效
        public byte[]           byReserved = new byte[1020];          // 预留字段
    }

    // 标定线
    public static class NET_CALIBRATE_LINE_INFO extends SdkStructure
    {
        public NET_POINT        stuStartPoint;                        // 起点
        public NET_POINT        stuEndPoint;                          // 终点
    }

    // 事件类型EVENT_IVS_WATER_STAGE_MONITOR(水位检测事件)对应的规则配置
    public static class NET_WATER_STAGE_MONITOR_RULE_INFO extends SdkStructure
    {
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public POINTCOORDINATE[] stuDetectRegion = (POINTCOORDINATE[]) new POINTCOORDINATE().toArray(20); // 检测区域
        public int              dwSceneMask;                          // 使能检测的场景掩码	                                                                             // bit2：内涝检测，有水位尺, bit3：内涝检测，无水位尺
        public NET_CALIBRATE_LINE_INFO stuCalibrateLine;              // 标定线, 仅在人物分析模式下有效
        public byte[]           byReserved = new byte[4096];          //保留字节
    }

    // 事件类型EVENT_IVS_VIOLENT_THROW_DETECTION(暴力抛物检测)对应的数据块描述信息
    public static class DEV_EVENT_VIOLENT_THROW_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nFrameSequence;                       // 视频分析帧序号
        public byte[]           szRegionName = new byte[64];          // 暴力抛物检测区域名称
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 大图信息
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        public byte             byReserver[] = new byte[1024];        //预留字节
    }

    // CLIENT_GetHumanRadioCaps 接口输入参数
    public static class NET_IN_GET_HUMAN_RADIO_CAPS extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;

        public NET_IN_GET_HUMAN_RADIO_CAPS(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetHumanRadioCaps 接口输出参数
    public static class NET_OUT_GET_HUMAN_RADIO_CAPS extends SdkStructure
    {
        public int              dwSize;
        public int              bSupportRegulatorAlarm;

        public NET_OUT_GET_HUMAN_RADIO_CAPS(){
            this.dwSize = this.size();
        }
    }

    // 获取能力级
    public Boolean CLIENT_GetHumanRadioCaps(LLong lLoginID,NET_IN_GET_HUMAN_RADIO_CAPS pInParam,NET_OUT_GET_HUMAN_RADIO_CAPS pOutParam,int nWaitTime);

    // 区域内人员体温信息
    public static class NET_MAN_TEMP_INFO extends SdkStructure
    {
        public int              nObjectID;                            // 物体ID
        public NET_RECT         stRect;                               // 人员头肩信息, 8192坐标系
        public double           dbHighTemp;                           // 最高的温度
        public int              nTempUnit;                            // 温度单位(0摄氏度 1华氏度 2开尔文)
        public int              bIsOverTemp;                          // 是否温度异常
        public int              bIsUnderTemp;                         // 是否温度异常
        public int              nOffset;                              // 人脸小图特征值在二进制数据块中的偏移
        public int              nLength;                              // 人脸小图特征值长度, 单位:字节
        public int              emMaskDetectResult;                   // 口罩检测结果(参考EM_MASK_DETECT_RESULT_TYPE)
        public NET_RECT         stThermalRect;                        // 热成像检测人员头肩坐标信息(坐标系：8192)
        public int              nAge;                                 // 年龄
        public int              emSex;                                // 性别(参考EM_DEV_EVENT_FACEDETECT_SEX_TYPE)
        public byte[]           byReserved = new byte[36];            // 预留字段
    }

    // 全景图
    public static class NET_VIS_SCENE_IMAGE extends SdkStructure
    {
        public int              nOffset;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小, 单位字节
        public int              nWidth;                               // 图片宽度, 像素
        public int              nHeight;                              // 图片高度, 像素
        public byte[]           byReserved = new byte[64];            // 预留字段
    }

    // 热成像全景图
    public static class NET_THERMAL_SCENE_IMAGE extends SdkStructure
    {
        public int              nOffset;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小, 单位字节
        public int              nWidth;                               // 图片宽度, 像素
        public int              nHeight;                              // 图片高度, 像素
        public byte[]           byReserved = new byte[64];            // 预留字段
    }

    // 事件类型EVENT_IVS_ANATOMY_TEMP_DETECT(人体测温检测事件)对应的数据块描述信息
    public static class DEV_EVENT_ANATOMY_TEMP_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              emClassType;                          // 智能事件所属大类(对应枚举类型EM_CLASS_TYPE)
        public int              nPresetID;                            // 事件触发的预置点号, 从1开始, 没有该字段,表示预置点未知
        public NET_MAN_TEMP_INFO stManTempInfo;                       // 区域内人员体温信息
        public NET_VIS_SCENE_IMAGE stVisSceneImage;                   // 可见光全景图
        public NET_THERMAL_SCENE_IMAGE stThermalSceneImage;           // 热成像全景图
        public int              nSequence;                            // 帧序号
        public int              nEventRelevanceID;                    // 事件关联ID
        public int              bIsFaceRecognition;                   // 是否做过后智能的目标识别
        public Pointer          pstuImageInfo;                        // 图片信息数组，结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[1004];          // 预留字段
    }

    // 事件类型 ALARM_ANATOMY_TEMP_DETECT_INFO(人体温智能检测事件)对应的数据块描述信息
    public static class ALARM_ANATOMY_TEMP_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nPresetID;                            // 事件触发的预置点号, 从1开始, 没有该字段,表示预置点未知
        public NET_MAN_TEMP_INFO stManTempInfo;                       // 区域内人员体温信息
        public int              nSequence;                            // 帧序号
        public int              nEventRelevanceID;                    // 事件关联ID
        public int              bIsFaceRecognition;                   // 是否做过后智能的目标识别
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[1020];          // 预留字节
    }

    // 人体测温规则配置
    public static class CFG_ANATOMY_TEMP_DETECT_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public int              bRuleEnable;                          // 规则使能
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public byte             bTrackEnable;                         // 触发跟踪使能,仅对警戒线事件,警戒区规则有效
        public int              nDetectRegionPoint;                   // 多边形顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区域，多边形
        public int              bHighEnable;                          // 温度异常报警是否开启
        public int              bLowEnable;                           // 温度异常报警是否开启
        public int              fHighThresholdTemp;                   // 温度异常阈值，精度0.1，扩大10倍
        public int              fLowThresholdTemp;                    // 温度异常阈值，精度0.1，扩大10倍
        public int              bIsAutoStudy;                         // 是否自动学习
        public int              fHighAutoOffset;                      // 温度自动学习偏差值，精度0.1，扩大10倍
        public int              fLowAutoOffset;                       // 温度自动学习偏差值，精度0.1，扩大10倍
        public int              nSensitivity;                         // 灵敏度 范围[1, 10]
        public int              bSizeFileter;                         // 规则特定的尺寸过滤器是否有效
        public CFG_SIZEFILTER_INFO stuSizeFileter;                    // 规则特定的尺寸过滤器
        public int              bIsCaptureNormal;                     // 是否上报正常体温信息
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 事件类型 ALARM_REGULATOR_ABNORMAL_INFO(标准黑体源异常报警事件)对应的数据块描述信息
    public static class ALARM_REGULATOR_ABNORMAL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public byte[]           szTypes = new byte[MAX_COMMON_STRING_32]; // 异常类型
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    // 校准源信息
    public static class NET_REGULATOR_INFO extends SdkStructure
    {
        public int              nDistance;                            // 校准源距离, 单位cm
        public int              nTemperature;                         // 校准源温度, 精度0.1, 放大10倍
        public NET_RECT         stRect;                               // 校准源矩形位置取值0-8191
        public int              nHeight;                              // 校准源高度, 单位cm
        public int              nDiffTemperature;                     // 温度偏差值, 精度0.01, 放大100倍
        public byte[]           byReserve = new byte[32];             // 保留字节，用于字节对齐
    }

    // 人体测温标准黑体配置, 对应枚举 NET_EM_CFG_RADIO_REGULATOR
    public static class NET_CFG_RADIO_REGULATOR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bEnable;                              // 标准黑体配置使能
        public int              nPresetId;                            // 预置点编号, 对于无预置点设备为0
        public int              nCamerHeight;                         // 热成像相机安装高度, 单位cm
        public int              nCamerAngle;                          // 相机安装角度, 精度0.1, 放大10倍
        public NET_REGULATOR_INFO stRegulatorInfo;                    // 校准源信息

        public NET_CFG_RADIO_REGULATOR(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_BatchAppendFaceRecognition 接口输入参数
    public static class NET_IN_BATCH_APPEND_FACERECONGNITION extends SdkStructure
    {
        /**
         * 结构体大小
         */
        public int              dwSize;
        /**
         * 需要添加的人员数量
         */
        public int              nPersonNum;
        /**
         * 人员信息，内存由用户申请，大小为nPersonNum * sizeof(FACERECOGNITION_PERSON_INFOEX)
         */
        public Pointer          pstPersonInfo;
        // 图片二进制数据
        public Pointer          pBuffer;                              // 缓冲地址
        public int              nBufferLen;                           // 缓冲数据长度
        public byte[]           bReserved = new byte[4];              // 字节对齐
        public NET_MULTI_APPEND_EXTENDED_INFO stuInfo = new NET_MULTI_APPEND_EXTENDED_INFO(); //扩展信息

        public NET_IN_BATCH_APPEND_FACERECONGNITION(){
            this.dwSize = this.size();
        }
    }

    // 批量添加人员结果信息
    public static class NET_BATCH_APPEND_PERSON_RESULT extends SdkStructure
    {
        public int              nUID;                                 // 人员UID
        public int              dwErrorCode;                          // 错误码信息
        public byte[]           bReserved = new byte[512];            // 保留字段
    }

    // CLIENT_BatchAppendFaceRecognition 接口输出参数
    public static class NET_OUT_BATCH_APPEND_FACERECONGNITION extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nResultNum;                           // 批量添加结果个数，由用户指定，数值与NET_IN_BATCH_APPEND_FACERECONGNITION中的nPersonNum一致
        /**
         * 内存大小为结构体数组的大小,对应结构体为{@link NET_BATCH_APPEND_PERSON_RESULT}
         */
        public Pointer          pstResultInfo;                        // 批量添加结果信息
        public int              nUIDType;                             // 指定NET_BATCH_APPEND_PERSON_RESULT中的UID使用字段，不存在本字段或值为0则使用UID字段，若值为1则使用UID2字段

        public NET_OUT_BATCH_APPEND_FACERECONGNITION(){
            this.dwSize = this.size();
        }
    }

    // 添加多个人员信息和人脸样本
    public Boolean CLIENT_BatchAppendFaceRecognition(LLong lLoginID,NET_IN_BATCH_APPEND_FACERECONGNITION pstInParam,NET_OUT_BATCH_APPEND_FACERECONGNITION pstOutParam,int nWaitTime);

    // CLIENT_FindFileEx+DH_FILE_QUERY_SNAPSHOT_WITH_MARK  对应查询参数
    public static class MEDIAFILE_SNAPSHORT_WITH_MARK_PARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              bOnlySupportRealUTC;                  // 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stuStartTime, stuEndTime), 为FALSE表示仅下发stuStartTime, stuEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥

        public MEDIAFILE_SNAPSHORT_WITH_MARK_PARAM(){
            this.dwSize = this.size();
        }
    }

    // 抓图标记信息
    public static class NET_SNAPSHOT_MARK_INFO extends SdkStructure
    {
        public NET_POINT        stuPosition;                          // 标记的坐标位置, 绝对坐标系
        public byte[]           byReserved = new byte[1020];          // 预留字段
    }

    // DH_FILE_QUERY_SNAPSHOT_WITH_MARK 对应 FINDNEXT 查询返回结果
    public static class MEDIAFILE_SNAPSHORT_WITH_MARK_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道号从0开始,-1表示查询所有通道
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nFileSize;                            // 文件长度
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径
        public NET_SNAPSHOT_MARK_INFO stuMarkInfo;                    // 抓图标记信息
        public int              bRealUTC;                             // 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅stuStartTime和stuEndTime有效(仅使用stuStartTime和stuEndTime)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用

        public MEDIAFILE_SNAPSHORT_WITH_MARK_INFO(){
            this.dwSize = this.size();
        }
    }

    //////////////////////////////////////无人机航点功能开始///////////////////////////////////////////////////////////////////
    // 获取无人机航点入参
    public static class NET_IN_UAVMISSION_COUNT extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_UAVMISSION_COUNT(){
            this.dwSize = this.size();
        }
    }

    // 获取无人机航点出参
    public static class NET_OUT_UAVMISSION_COUNT extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // 航点总数

        public NET_OUT_UAVMISSION_COUNT(){
            this.dwSize = this.size();
        }
    }

    // 获取任务入参
    public static class NET_IN_READ_UAVMISSION extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_READ_UAVMISSION(){
            this.dwSize = this.size();
        }
    }

    // 航点任务
    public static class NET_UAVMISSION_ITEM extends SdkStructure
    {
        public int              nCurrentMode;                         // 使能状态 0-未使能; 1-使能;
        public int              bAutoContinue;                        // 自动执行下一个航点
        public int              nSequence;                            // 航点序号
        public int              emCommand;                            // 航点指令(参考ENUM_UAVCMD_TYPE)
        public NET_UAVCMD_PARAM_BUFFER stuCmdParam;                   // 指令参数
        public byte[]           byReserved = new byte[8];             // 保留字段
    }

    // 获取任务出参
    public static class NET_OUT_READ_UAVMISSION extends SdkStructure
    {
        public int              dwSize;
        public int              nItemCount;                           // 有效任务个数
        public Pointer          pstuItems;                            // 任务列表(参考NET_UAVMISSION_ITEM)

        public NET_OUT_READ_UAVMISSION(){
            this.dwSize = this.size();
        }
    }

    // 设置任务入参
    public static class NET_IN_WRITE_UAVMISSION extends SdkStructure
    {
        public int              dwSize;
        public int              nItemCount;                           // 有效任务个数
        public Pointer          pstuItems;                            // 任务列表(参考NET_UAVMISSION_ITEM)

        public NET_IN_WRITE_UAVMISSION(){
            this.dwSize = this.size();
        }
    }

    // 设置任务出参
    public static class NET_OUT_WRITE_UAVMISSION extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_WRITE_UAVMISSION(){
            this.dwSize = this.size();
        }
    }

    // 订阅任务消息入参
    public static class NET_IN_ATTACH_UAVMISSION_STATE extends SdkStructure
    {
        public int              dwSize;
        public fUAVMissionStateCallBack cbNotify;                     // 任务状态回调函数
        public Pointer          dwUser;                               // 用户信息

        public NET_IN_ATTACH_UAVMISSION_STATE(){
            this.dwSize = this.size();
        }
    }

    // 订阅任务消息出参
    public static class NET_OUT_ATTACH_UAVMISSION_STATE extends SdkStructure
    {
        public int              dwSize;                               // 赋值为结构体大小

        public NET_OUT_ATTACH_UAVMISSION_STATE(){
            this.dwSize = this.size();
        }
    }

    // 任务状态类型
    public static class ENUM_UAVMISSION_TYPE extends SdkStructure
    {
        public static final int   ENUM_UAVMISSION_TYPE_UNKNOWN = 0;     // 未知类型
        public static final int   ENUM_UAVMISSION_TYPE_WP_UPLOAD = 1;   // 航点上传
        public static final int   ENUM_UAVMISSION_TYPE_WP_DOWNLOAD = 2; // 航点下载
    }

    // 任务状态
    public static class ENUM_UAVMISSION_STATE extends SdkStructure
    {
        public static final int   ENUM_UAVMISSION_STATE_UNKNOWN = 0;    // 未知类型
        public static final int   ENUM_UAVMISSION_STATE_BEGIN = 1;      // 开始
        public static final int   ENUM_UAVMISSION_STATE_UNDERWAY = 2;   // 进行
        public static final int   ENUM_UAVMISSION_STATE_SUCCESS = 3;    // 成功
        public static final int   ENUM_UAVMISSION_STATE_FAIL = 4;       // 失败
    }

    // 任务状态信息
    public static class NET_UAVMISSION_STATE extends SdkStructure
    {
        public int              emType;                               // 类型(参见ENUM_UAVMISSION_TYPE枚举类型)
        public int              emState;                              // 状态(参见ENUM_UAVMISSION_STATE)
        public int              nTotalCount;                          // 总数
        public int              nSequence;                            // 当前航点编号
    }

    // 无人机任务状态回调
    public interface fUAVMissionStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_UAVMISSION_STATE pstuState,int dwStateInfoSize,Pointer dwUser);
    }

    // 无人机通用设置接口
    //emCmdType(参考ENUM_UAVCMD_TYPE枚举)
    //pParam对应ENUM_UAVCMD_TYPE所对应的结构体
    public boolean CLIENT_SendCommandToUAV(LLong lLoginID,int emCmdType,Pointer pParam,int nWaitTime);

    // 获取航点总数
    public boolean CLIENT_GetUAVMissonCount(LLong lLoginID,NET_IN_UAVMISSION_COUNT pstuInParam,NET_OUT_UAVMISSION_COUNT pstuOutParam,int nWaitTime);

    // 获取UAV航点信息
    public boolean CLIENT_ReadUAVMissions(LLong lLoginID,NET_IN_READ_UAVMISSION pstuInParam,NET_OUT_READ_UAVMISSION pstuOutParam,int nWaitTime);

    // 设置UAV航点信息
    public boolean CLIENT_WriteUAVMissions(LLong lLoginID,NET_IN_WRITE_UAVMISSION pstuInParam,NET_OUT_WRITE_UAVMISSION pstuOutParam,int nWaitTime);

    // 订阅UAV航点任务 pstuInParam 和 pstuOutParam 由设备申请释放
    public LLong CLIENT_AttachUAVMissonState(LLong lLoginID,NET_IN_ATTACH_UAVMISSION_STATE pstuInParam,NET_OUT_ATTACH_UAVMISSION_STATE pstuOutParam,int nWaitTime);

    // 退订UAV航点任务 lAttachHandle 是 CLIENT_AttachUAVMissonState 返回值
    public boolean CLIENT_DettachUAVMissonState(LLong lAttachHandle);

//////////////////////////////////////无人机航点功能结束///////////////////////////////////////////////////////////////////
    ////////////////////////////////////云上高速抓图起雾事件开始//////////////////////////////////////////
    // 起雾检测事件数据类型
    public static class EM_FOG_DETECTION_EVENT_TYPE
    {
        public static final int   EM_FOG_DETECTION_EVENT_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_FOG_DETECTION_EVENT_TYPE_REAL = 1; // 实时数据
        public static final int   EM_FOG_DETECTION_EVENT_TYPE_ALARM = 2; // 报警数据
    }

    // 雾值
    public static class EM_FOG_LEVEL extends SdkStructure
    {
        public static final int   EM_FOG_LEVEL_UNKNOWN = 0;             // 未知
        public static final int   EM_FOG_LEVEL_NO = 1;                  // 无
        public static final int   EM_FOG_LEVEL_BLUE = 2;                // 蓝色预警
        public static final int   EM_FOG_LEVEL_YELLOW = 3;              // 黄色预警
        public static final int   EM_FOG_LEVEL_ORANGE = 4;              // 橙色预警
        public static final int   EM_FOG_LEVEL_RED = 5;                 // 红色预警
    }

    // 起雾检测事件雾信息
    public static class FOG_DETECTION_FOG_INFO extends SdkStructure
    {
        public int              emFogLevel;                           // 雾等级，参考EM_FOG_LEVEL
        public byte[]           byReserved = new byte[508];           // 预留字段
    }

    // 事件类型EVENT_IVS_FOG_DETECTION(起雾检测事件)对应的数据块描述信息
    public static class DEV_EVENT_FOG_DETECTION extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              emClassType;                          // 智能事件所属大类，参考EM_CLASS_TYPE
        public int              nGroupID;                             // 事件组ID，同一辆车抓拍过程内GroupID相同
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号
        public int              nPresetID;                            // 预置点号，从1开始有效
        public byte[]           szPresetName = new byte[128];         // 阈值点名称
        public int              emEventType;                          // 事件数据类型，参考EM_FOG_DETECTION_EVENT_TYPE
        public FOG_DETECTION_FOG_INFO stuFogInfo;                     // 雾信息
        public NET_EVENT_FILE_INFO stFileInfo;                        // 事件对应文件信息
        public byte[]           byReserved = new byte[1024];          // 预留字段
    }

    ////////////////////////////////////云上高速抓图起雾事件开始//////////////////////////////////////////
    ////////////////////////////////////云上高速抓图开始//////////////////////////////////////////、
    // CLIENT_ManualSnap 接口输入参数
    public static class NET_IN_MANUAL_SNAP extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannel;                             // 抓图通道号
        public int              nCmdSerial;                           // 请求序列号
        public byte[]           szFilePath = new byte[260];           // 抓图保存路径

        public NET_IN_MANUAL_SNAP(){
            this.dwSize = this.size();
        }
    }

    // 抓图图片编码格式
    public static class EM_SNAP_ENCODE_TYPE extends SdkStructure
    {
        public static final int   EM_SNAP_ENCODE_TYPE_UNKNOWN = 0;      // 未知
        public static final int   EM_SNAP_ENCODE_TYPE_JPEG = 1;         // jpeg图片
        public static final int   EM_SNAP_ENCODE_TYPE_MPEG4_I = 2;      // mpeg4的i 帧
    }

    // CLIENT_ManualSnap 接口输出参数
    public static class NET_OUT_MANUAL_SNAP extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxBufLen;                           // pRcvBuf的长度,由用户指定
        public Pointer          pRcvBuf;                              // 接收图片缓冲, 用于存放抓图数据, 空间由用户申请和释放, 申请大小为nMaxBufLen
        public int              nRetBufLen;                           // 实际接收到的图片大小
        public int              emEncodeType;                         // 图片编码格式,参考EM_SNAP_ENCODE_TYPE
        public int              nCmdSerial;                           // 请求序列号
        public byte[]           bReserved = new byte[4];              // 字节对齐

        public NET_OUT_MANUAL_SNAP(){
            this.dwSize = this.size();
        }
    }

    // 手动抓图, 支持并发调用
    public boolean CLIENT_ManualSnap(LLong lLoginID,NET_IN_MANUAL_SNAP pInParam,NET_OUT_MANUAL_SNAP pOutParam,int nWaitTime);

    // 订阅抓图回调信息
    public static class NET_CB_ATTACH_SNAP_INFO extends SdkStructure
    {
        public Pointer          pRcvBuf;                              // 接收到的图片数据
        public int              nBufLen;                              // 图片数据长度
        public int              emEncodeType;                         // 图片编码格式,参考EM_SNAP_ENCODE_TYPE
        public int              nCmdSerial;                           // 抓图请求序列号
        public byte[]           byReserved = new byte[1028];          // 保留字节
    }

    // 订阅抓图回调函数原形
    public interface fAttachSnapRev extends Callback {
        public void invoke(LLong lAttachHandle,NET_CB_ATTACH_SNAP_INFO pstAttachCbInfo,Pointer dwUser);
    }

    // CLIENT_AttachSnap 接口输入参数
    public static class NET_IN_ATTACH_INTER_SNAP extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannel;                             // 抓图通道号
        public int              nCmdSerial;                           // 请求序列号
        public int              nIntervalSnap;                        // 定时抓图时间间隔
        public fAttachSnapRev   cbAttachSnapRev;                      // 回调函数
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_INTER_SNAP(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachSnap 接口输出参数
    public static class NET_OUT_ATTACH_INTER_SNAP extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_ATTACH_INTER_SNAP(){
            this.dwSize = this.size();
        }
    }

    // 订阅抓图
    public LLong CLIENT_AttachSnap(LLong lLoginID,NET_IN_ATTACH_INTER_SNAP pInParam,NET_OUT_ATTACH_INTER_SNAP pOutParam);

    // 取消订阅抓图
    public boolean CLIENT_DetachSnap(LLong lAttachHandle);

    ////////////////////////////////////云上高速抓图结束//////////////////////////////////////////
    // 二维码上报事件信息( DH_ALARM_QR_CODE_CHECK )
    public static class ALARM_QR_CODE_CHECK_INFO extends SdkStructure
    {
        public int              nEventID;                             // 事件ID
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public byte[]           szQRCode = new byte[256];             // 二维码字符串
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    /************************************************************************/
    /*                            录像备份回传                                */
    /************************************************************************/
    // 录像备份恢复任务信息
    public static class NET_REC_BAK_RST_TASK extends SdkStructure
    {
        public int              dwSize;
        public int              nTaskID;                              // 任务ID
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
        public int              nChannelID;                           // 通道号
        public NET_TIME         stuStartTime = new NET_TIME();        // 录像开始时间
        public NET_TIME         stuEndTime = new NET_TIME();          // 录像结束时间
        public int              nState;                               // 当前备份状态, 0-等待, 1-进行中, 2-完成, 3-失败, 4-暂停
        public NET_RECORD_BACKUP_PROGRESS stuProgress = new NET_RECORD_BACKUP_PROGRESS(); // 当前备份进度
        public int              emFailReason;                         // 失败的原因, 当nState字段为3的情况下有效,参考EM_RECORD_BACKUP_FAIL_REASON
        public NET_TIME         stuTaskStartTime = new NET_TIME();    // 任务开始时间, nState为"进行中"、"已完成"、"失败"的情况下该时间点有效;
        public NET_TIME         stuTaskEndTime = new NET_TIME();      // 任务结束时间, nState为"已完成"、"失败"的情况下该时间点有效;
        public  int             nRemoteChannel;                       // 备份源通道
        public double           dbLength;                             //该任务的总长度,单位字节, -1表示未知

        public NET_REC_BAK_RST_TASK(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_AddRecordBackupRestoreTask接口输入参数
    public static class NET_IN_ADD_REC_BAK_RST_TASK extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pszDeviceID;                          // 设备ID
        public Pointer          pnChannels;                           // 通道数组
        public int              nChannelCount;                        // 通道数组大小,由用户申请内存,大小为sizeof(int)*nChannelCount
        public NET_TIME         stuStartTime;                         // 起始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public byte[]           szUrl = new byte[256];                // RTSP URL
        public int              bIsOffline;                           //是否为前端断网续传任务
        public int              nStreamTypeNum;                       //码流类型数量
        public int[]            nStreamType = new int[16];            //码流类型数组 0:默认（主码流） 1：Jpg图片流；2：Main主码流 3：Extra1辅码流1 4：Extra2码流2
        public byte[]           szRecordSource = new byte[32];        //录像来源 "ChannelIDDirect"表示从ChannelID本身获取  "ChannelIDSubordinate"表示从ChannelID的从属获取
        public Pointer          pszChannelIDs;                        //通道ID列表,由用户申请内存,大小为64*nChannelIDNum,每个元素的长度都固定为：  64
        public int              nChannelIDNum;                        //通道ID列表数组大小

        public NET_IN_ADD_REC_BAK_RST_TASK(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_RemoveRecordBackupRestoreTask接口输入参数
    public static class NET_IN_REMOVE_REC_BAK_RST_TASK extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pnTaskIDs;                            // 任务ID数组,由用户申请内存，大小为sizeof(int)*nTaskCount
        public int              nTaskCount;                           // 任务数量

        public NET_IN_REMOVE_REC_BAK_RST_TASK(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryRecordBackupRestoreTask接口输入参数
    public static class NET_IN_QUERY_REC_BAK_RST_TASK extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_QUERY_REC_BAK_RST_TASK(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryRecordBackupRestoreTask接口输出参数
    public static class NET_OUT_QUERY_REC_BAK_RST_TASK extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pTasks;                               // 任务数组,由用户申请内存，大小为sizeof(NET_REC_BAK_RST_TASK)*nMaxCount
        public int              nMaxCount;                            // 数组大小
        public int              nReturnCount;                         // 返回的任务数量

        public NET_OUT_QUERY_REC_BAK_RST_TASK(){
            this.dwSize = this.size();
        }
    }

    // 开始录像备份恢复
    public LLong CLIENT_StartRecordBackupRestore(LLong lLoginID);

    // 停止录像备份恢复
    public void CLIENT_StopRecordBackupRestore(LLong lRestoreID);

    // 添加录像备份恢复任务,pInParam内存由用户申请释放
    public boolean CLIENT_AddRecordBackupRestoreTask(LLong lRestoreID,NET_IN_ADD_REC_BAK_RST_TASK pInParam,int nWaitTime);

    // 删除录像备份恢复任务,pInParam->NET_IN_REMOVE_REC_BAK_RST_TASK 内存由用户申请释放
    public boolean CLIENT_RemoveRecordBackupRestoreTask(LLong lRestoreID,Pointer pInParam,int nWaitTime);

    // 获取录像备份恢复任务信息,pInParam与pOutParam内存由用户申请释放
    // pInParam->NET_IN_QUERY_REC_BAK_RST_TASK pOutParam->NET_OUT_QUERY_REC_BAK_RST_TASK
    public boolean CLIENT_QueryRecordBackupRestoreTask(LLong lRestoreID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 导入配置文件(以JSON格式) pSendBuf: 待发送数据,用户分配内存, nSendBufLen: 待发送长度, reserved: 保留参数
    public boolean CLIENT_ImportConfigFileJson(LLong lLoginID,Pointer pSendBuf,int nSendBufLen,Pointer reserved,int nWaitTime);

    // 导出配置文件(以JSON格式) pOutBuffer: 接收缓冲,用户分配内存, maxlen: 接收缓冲长度, nRetlen: 实际导出长度, reserved: 保留参数
    public boolean CLIENT_ExportConfigFileJson(LLong lLoginID,Pointer pOutBuffer,int maxlen,IntByReference nRetlen,Pointer reserved,int nWaitTime);

    // web信息上传接口
    public boolean CLIENT_TransmitInfoForWeb(LLong lLoginID,Pointer szInBuffer,int dwInBufferSize,Pointer szOutBuffer,int dwOutBufferSize,Pointer pExtData,int waittime);

    //================================================GIP200413016开始============================================
    // 线圈信息（主要是里面的车辆信息）
    public static class COILS_INFO extends SdkStructure
    {
        public int              nCarId;                               //  车辆Id（不是车牌号，ID是设备检测到物体记录的编号)
        public byte[]           PlateNum = new byte[64];              //  车牌号
        public int              emCarType;                            //  车辆类型(參考EM_NET_CARTYPE)
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    // 车道信息
    public static class LANE_INFO extends SdkStructure
    {
        public int              nLane;                                // 物理车道号（范围0~4）
        public int              nLaneType;                            // 车道类型，虚线车道：0， 实线车道：1
        public double           dbLaneOcc;                            // 车道空间占有率,范围[0.0~1.0]
        public int              nRoadwayNumber;                       // 自定义车道号（范围0~128）
        public int              nCurrentLaneVehicleNum;               // 当前车道车的数量
        public int              nVehicleNum;                          // 从上次统计结束到现在，通过车的辆数(设备0.5秒下发一次)
        public int              nCarId;                               // 保留最近有效过车的ID（不是车牌号），CarId是设备检测到物体记录的编号
        public double           dbCarEnterTime;                       // 编号CarId车辆进入虚线车道的时间
        public double           dbCarLeaveTime;                       // 编号CarId车辆离开实线车道的时间
        public int              nCarDistance;                         // 编号CarId车辆行驶的距离，单位：米
        public int              nQueueLen;                            // 车辆等待时的排队长度，单位：米
        public double           dbCarSpeed;                           // 编号CarId车辆平均车速，单位：米/秒
        public int              nCoilsInfoNum;                        // 实际返回线圈信息个数
        public COILS_INFO[]     stuCoilsInfo = (COILS_INFO[])new COILS_INFO().toArray(70*2); // 线圈信息（主要是线圈内的车辆信息）
        public int              nRetSolidLanNum;                      // 实际返回虚线车道个数
        public int[]            nSolidLaneNum = new int[6];           // 虚线车道对应的实线车道自定义车道号
        public int              nVehicleNumByTypeNum;                 // 实际返回车辆类型统计个数
        public int[]            nVehicleNumByType = new int[64];      // 类型车辆统计,数组下标对应不同车型（车型参考 EM_NET_CARTYPE），下标值对应车辆类型统计的数量
        public int              nEndLen;                              // 车辆运行时，尾部车辆位置距离停车线的距离 ，单位：米
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    // 交通态势事件（NET_ALARM_TRAFFIC_XINKONG）
    public static class ALARM_TRAFFIC_XINKONG_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 只有一个事件动作0，表示脉冲事件
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public int              nLaneInfoNum;                         // 实际上报多少车道信息
        public LANE_INFO[]      stuLaneInfo = (LANE_INFO[])new LANE_INFO().toArray(6); // 车道信息
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    //================================================GIP200413016结束============================================
    //================================================ERR200412034开始============================================
    // 同轴IO控制类型
    public static class EM_COAXIAL_CONTROL_IO_TYPE extends SdkStructure
    {
        public static final int   EM_COAXIAL_CONTROL_IO_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_COAXIAL_CONTROL_IO_TYPE_LIGHT = 1; // 白光灯
        public static final int   EM_COAXIAL_CONTROL_IO_TYPE_SPEAKER = 2; // speak音频
    }

    // 同轴IO控制开关
    public static class EM_COAXIAL_CONTROL_IO_SWITCH extends SdkStructure
    {
        public static final int   EM_COAXIAL_CONTROL_IO_SWITCH_UNKNOWN = 0; // 未知
        public static final int   EM_COAXIAL_CONTROL_IO_SWITCH_OPEN = 1; // 开
        public static final int   EM_COAXIAL_CONTROL_IO_SWITCH_CLOSE = 2; // 关
    }

    // 同轴IO触发方式
    public static class EM_COAXIAL_CONTROL_IO_TRIGGER_MODE extends SdkStructure
    {
        public static final int   EM_COAXIAL_CONTROL_IO_TRIGGER_MODE_UNKNOWN = 0; // 未知
        public static final int   EM_COAXIAL_CONTROL_IO_TRIGGER_MODE_LINKAGE_TRIGGER = 1; // 联动触发
        public static final int   EM_COAXIAL_CONTROL_IO_TRIGGER_MODE_MANUAL_TRIGGER = 2; // 手动触发
    }

    // 同轴IO信息结构体
    public static class NET_COAXIAL_CONTROL_IO_INFO extends SdkStructure
    {
        public int              emType;                               // 同轴IO控制类型参考EM_COAXIAL_CONTROL_IO_TYPE
        public int              emSwicth;                             // 同轴IO控制开关参考EM_COAXIAL_CONTROL_IO_SWITCH
        public int              emMode;                               // 同轴IO触发方式参考EM_COAXIAL_CONTROL_IO_TRIGGER_MODE
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 发送同轴IO控制命令, CLIENT_ControlDeviceEx 入参 对应 CTRLTYPE_CTRL_COAXIAL_CONTROL_IO
    public static class NET_IN_CONTROL_COAXIAL_CONTROL_IO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannel;                             // 通道号
        public int              nInfoCount;                           // 同轴IO信息个数
        public NET_COAXIAL_CONTROL_IO_INFO[] stInfo = (NET_COAXIAL_CONTROL_IO_INFO[])new NET_COAXIAL_CONTROL_IO_INFO().toArray(MAX_COAXIAL_CONTROL_IO_COUNT); // 同轴IO信息

        public NET_IN_CONTROL_COAXIAL_CONTROL_IO(){
            this.dwSize = this.size();
        }
    }

    // 发送同轴IO控制命令, CLIENT_ControlDeviceEx 出参 对应 CTRLTYPE_CTRL_COAXIAL_CONTROL_IO
    public static class NET_OUT_CONTROL_COAXIAL_CONTROL_IO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_CONTROL_COAXIAL_CONTROL_IO(){
            this.dwSize = this.size();
        }
    }

    //================================================ERR200412034结束============================================
    //================================================ERR200410078 DH-TPC-BF2221开始============================================
    // 火灾配置类型
    public static class NET_EM_FIREWARNING_MODE_TYPE extends SdkStructure
    {
        public static final int   NET_EM_FIREWARNING_TYPE_PTZPRESET = 0; // 云台预置点模式（默认）
        public static final int   NET_EM_FIREWARNING_TYPE_SPACEEXCLUDE = 1; // 空间排除模式
    }

    // 火灾预警模式配置 NET_EM_CFG_FIRE_WARNINGMODE
    public static class NET_FIREWARNING_MODE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emFireWarningMode;                    // 火灾预警模式 参考NET_EM_FIREWARNING_MODE_TYPE

        public NET_FIREWARNING_MODE_INFO(){
            this.dwSize = this.size();
        }
    }

    // 时间表信息
    public static class NET_CFG_TIME_SCHEDULE extends SdkStructure
    {
        public int              bEnableHoliday;                       // 是否支持节假日配置，默认为不支持，除非获取配置后返回为TRUE，不要使能假日配置
        public NET_TSECT[]      stuTimeSection = (NET_TSECT[])new NET_TSECT().toArray(NET_N_SCHEDULE_TSECT*NET_N_REC_TSECT); // 第一维前7个元素对应每周7天，第8个元素对应节假日，每天最多6个时间段
    }

    // 火灾预警联动项
    public static class NET_FIREWARN_EVENTHANDLE_INFO extends SdkStructure
    {
        public NET_CFG_TIME_SCHEDULE stuTimeSection;                  // 报警时间段
        public int              bRecordEnable;                        // 录像使能，必须同时有RecordChannels。使能为TRUE，且事件action为start开始录像，stop停止录像。如果FALSE，则不做录像
        public int              nRecordChannelNum;                    // 录像通道个数
        public int[]            nRecordChannels = new int[32];        // 录像通道号列表
        public int              nRecordLatch;                         // 录像延时时间（单位：秒）范围[10,300]
        public int              bAlarmOutEnable;                      // 报警输出使能
        public int              nAlarmOutChannelNum;                  // 报警输出通道个数
        public int[]            nAlarmOutChannels = new int[32];      // 报警输出通道号列表
        public int              nAlarmOutLatch;                       // 报警输入停止后，输出延时时间（单位：秒）范围[10, 300]
        public int              nPtzLinkNum;                          // 云台配置数
        public SDK_PTZ_LINK[]   struPtzLink = (SDK_PTZ_LINK[])new SDK_PTZ_LINK().toArray(16); // 云台联动
        public int              bPtzLinkEnable;                       // 云台联动使能
        public int              bSnapshotEnable;                      // 快照使能
        public int              nSnapshotChannelNum;                  // 快照通道个数
        public int[]            nSnapshotChannels = new int[32];      // 快照通道号列表
        public int              bMailEnable;                          // 发送邮件，如果有图片，作为附件
        public NET_PTZ_LINK[]   stuPtzLinkEx = new NET_PTZ_LINK[16];  //云台联动项,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_PTZ_LINK}
        public NET_FIREWARN_PTZ_LINK_ENABLE[] stuPtzLinkEnable = new NET_FIREWARN_PTZ_LINK_ENABLE[16]; //云台联动项各参数使能，若要使用NET_PTZ_LINK中的参数，需将NET_FIREWARN_PTZ_LINK_ENABLE对应字段置为TRUE,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FIREWARN_PTZ_LINK_ENABLE}
        public byte[]           byReserved = new byte[448];           // 保留字节
    }

    public static class NET_POSTIONF extends SdkStructure
    {
        public float            fHorizontalAngle;                     // 水平角度 [-1,1]
        public float            fVerticalAngle;                       // 垂直角度 [-1,1]
        public float            fMagnification;                       // 放大倍数 [-1,1]
    }

    // 火警检测窗口
    public static class NET_FIREWARN_DETECTWND_INFO extends SdkStructure
    {
        public int              nRgnNum;                              // 检测区域的个数
        public byte[]           byReservedAlign = new byte[4];        // 保留字节
        public long[]           nRegions = new long[MAX_FIREWARNING_DETECTRGN_NUM]; // 检测区域
        public NET_POSTIONF     stuPostion;                           // 空间排除信息
        public int              nTargetSize;                          // 目标的尺寸(火警配置为:Normal有效,单位：像素)
        public int              nSensitivity;                         // 检测灵敏度（火警配置为:Normal有效）
        public int              nWindowsID;                           // 窗口ID
        public byte[]           szName = new byte[32];                // 窗口名称
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 火灾预警规则信息
    public static class NET_FIREWARN_RULE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 火灾预警功能是否开启
        public int              nPresetId;                            // 预置点编号,火灾预警模式为预置点模式生效
        public int              nRow;                                 // 火灾检测区域的行数
        public int              nCol;                                 // 火灾检测区域的列数
        public int              emFireWarningDetectMode;              // 火警检测模式 参考NET_EM_FIREWARNING_DETECTMODE_TYPE
        public int              emFireWarningDetectTragetType;        // 火警检测目标类型 参考NET_EM_FIREWARNING_DETECTTARGET_TYPE
        public int              bTimeDurationEnable;                  // 是否启用持续时间
        public int              nFireDuration;                        // 观察火情持续时间，单位秒。水平旋转组检测火点时，为避免同一点重复检测，
        // 设置超时时间，超过此时间，跳过此点
        public NET_FIREWARN_EVENTHANDLE_INFO stuEventHandler;         // 火警联动信息
        public int              nDetectWindowNum;                     // 窗口个数
        public NET_FIREWARN_DETECTWND_INFO[] stuDetectWnd = (NET_FIREWARN_DETECTWND_INFO[])new NET_FIREWARN_DETECTWND_INFO().toArray(MAX_FIREWARNING_DETECTWND_NUM); // 火警检测窗口
        public int              nGlobalSensitivity;                   //全局灵敏度，表示火情检测全局阈值，范围0-100，默认90
        public int              nShieldRegionNum;                     //屏蔽区域内存申请个数，最大1024
        public int              nShieldRegionRetNum;                  //屏蔽区域实际返回个数，获取配置时使用
        public Pointer          pstuShieldRegion;                     //火警屏蔽区域，需由用户申请内存，申请空间为sizeof(NET_FIREWARN_SHIELDREGION_INFO)*nShieldRegionNum,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FIREWARN_SHIELDREGION_INFO}
        public byte[]           byReserved = new byte[236];           // 保留字节
    }

    // 火灾预警配置(结构体较大，建议用New分配内存) NET_EM_CFG_FIRE_WARNING
    public static class NET_FIRE_WARNING_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFireWarnRuleNum;                     // 火灾预警配置个数
        public NET_FIREWARN_RULE_INFO[] stuFireWarnRule = (NET_FIREWARN_RULE_INFO[])new NET_FIREWARN_RULE_INFO().toArray(MAX_FIREWARNING_RULE_NUM); // 火灾预警配置规则

        public NET_FIRE_WARNING_INFO(){
            this.dwSize = this.size();
        }
    }

    //================================================ERR200410078 DH-TPC-BF2221结束============================================
    //================================================ERR200420018============================================
    // 事件类型 EVENT_IVS_VIDEOABNORMALDETECTION(视频异常事件)对应的数据块描述信息
    public static class DEV_EVENT_VIDEOABNORMALDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             bType;                                // 异常类型, 255-无意义（通常是设备了返回错误值）0-视频丢失, 1-视频冻结, 2-摄像头遮挡, 3-摄像头移动, 4-过暗, 5-过亮, 6-图像偏色, 7-噪声干扰, 8-条纹检测
        public byte[]           byReserved = new byte[1];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public SCENE_IMAGE_INFO_EX stuSceneImage = new SCENE_IMAGE_INFO_EX(); // 全景广角图信息
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte             byReserved2[] = new byte[1024];       //预留字节
    }

    // 人员信息
    public static class NET_HUMAN extends SdkStructure
    {
        public NET_RECT         stuBoundingBox;                       // 包围盒(8192坐标系)
        public int              nObjectID;                            // 物体ID
        public byte[]           szSerialUUID = new byte[22];          // 智能物体全局唯一物体标识
        // 有效数据位21位，包含’\0’
        // 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
        // 中间14位YYYYMMDDhhmmss:年月日时分秒
        // 后5位%u%u%u%u%u：物体ID，如00001
        public byte[]           bReserved = new byte[230];            // 保留字节
    }

    // 事件类型 EVENT_IVS_STAY_ALONE_DETECTION (单人独处事件) 对应的数据块描述信息
    public static class DEV_EVENT_STAY_ALONE_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public int              emClassType;                          // 智能事件所属大类参考EM_CLASS_TYPE
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              UTCMS;                                // UTC时间对应的毫秒数
        public int              nEventID;                             // 事件ID
        public NET_HUMAN        stuHuman;                             // 人员信息
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景图
        public int              nDetectRegionNum;                     // 检测区域顶点数
        public DH_POINT[]       stuDetectRegion = (DH_POINT[])new DH_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 检测区域
        public int              nImageInfoNum;                        // 图片信息个数
        public Pointer          pstuImageInfo;                        // 图片信息数组, refer to {@link NET_IMAGE_INFO_EX3}
        public byte[]           byReserved = new byte[1020 - POINTERSIZE]; // 保留字节
    }

    // 事件类型 EVENT_IVS_PSRISEDETECTION (囚犯起身事件) 对应的数据块描述信息
    public static class DEV_EVENT_PSRISEDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public DH_MSG_OBJECT    stuObject;                            // 检测到的物体
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public DH_POINT[]       DetectRegion = (DH_POINT[])new DH_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public double           dInitialUTC;                          // 事件初始UTC时间    UTC为事件的UTC (1970-1-1 00:00:00)秒数。
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szSerialUUID = new byte[22];          // 智能物体全局唯一物体标识
        // 有效数据位21位，包含’\0’
        // 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
        // 中间14位YYYYMMDDhhmmss:年月日时分秒
        // 后5位%u%u%u%u%u：物体ID，如00001
        public byte[]           bReserved = new byte[594];            // 保留字节,留待扩展.
    }

    //--------------------------------------------------------ERR200420018结束------------------------------------------------------------------------//
    //--------------------------------------------------------ERR200424047------------------------------------------------------------------------//
    // 逻辑屏显示内容
    public static class NET_TRAFFIC_LATTICE_SCREEN_SHOW_CONTENTS extends SdkStructure
    {
        public int              emContents;                           // 逻辑屏显示的内容：参考NET_EM_SCREEN_SHOW_CONTENTS
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public byte[]           szCustomStr = new byte[32];           // 自定义内容，emContents为	EM_TRAFFIC_LATTICE_SCREEN_CUSTOM 时有效
        public byte[]           byReserved = new byte[32];            // 预留
    }

    // 点阵屏显示信息
    public static class NET_TRAFFIC_LATTICE_SCREEN_SHOW_INFO extends SdkStructure
    {
        public NET_TRAFFIC_LATTICE_SCREEN_SHOW_CONTENTS[] stuContents = (NET_TRAFFIC_LATTICE_SCREEN_SHOW_CONTENTS[]) new NET_TRAFFIC_LATTICE_SCREEN_SHOW_CONTENTS().toArray(64); // 逻辑屏显示内容
        public int              nContentsNum;                         // 逻辑屏个数
        public byte[]           byReserved = new byte[1020];          // 预留
    }

    // 点阵屏显示信息配置, 对应枚举 NET_EM_CFG_TRAFFIC_LATTICE_SCREEN
    public static class NET_CFG_TRAFFIC_LATTICE_SCREEN_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nStatusChangeTime;                    // 状态切换间隔，单位：秒,取值10 ~ 60
        public NET_TRAFFIC_LATTICE_SCREEN_SHOW_INFO stuNormal = new NET_TRAFFIC_LATTICE_SCREEN_SHOW_INFO(); // 常态下
        public NET_TRAFFIC_LATTICE_SCREEN_SHOW_INFO stuCarPass = new NET_TRAFFIC_LATTICE_SCREEN_SHOW_INFO(); // 过车时
        public int              emShowType;                           /** 显示方式 {@link NET_EM_LATTICE_SCREEN_SHOW_TYPE}*/
        public int              emControlType;                        /** 控制方式 {@link NET_EM_LATTICE_SCREEN_CONTROL_TYPE}*/
        public int              emBackgroundMode;                     /** 逻辑屏背景风格模式 {@link NET_EM_LATTICE_SCREEN_BACKGROUND_MODE} */
        public byte[]           szPlayList = new byte[10*64];         // 资源文件播放列表,支持视频文件和图片文件播放,按照数组顺序循环播放
        public int              nPlayListNum;                         // 资料文件个数
        public NET_TRAFFIC_LATTICE_SCREEN_LOGO_INFO stuLogoInfo = new NET_TRAFFIC_LATTICE_SCREEN_LOGO_INFO(); // Logo信息
        public   NET_TRAFFIC_LATTICE_SCREEN_ALARM_NOTICE_INFO stuAlarmNoticeInfo = new NET_TRAFFIC_LATTICE_SCREEN_ALARM_NOTICE_INFO(); // 报警提示显示信息

        public NET_CFG_TRAFFIC_LATTICE_SCREEN_INFO(){
            this.dwSize = this.size();
        }
    }

    // 车位灯色
    public static class NET_PARKINGSPACELIGHT_INFO extends SdkStructure
    {
        public int              nRed;                                 // 红灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nYellow;                              // 黄灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nBlue;                                // 蓝灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nGreen;                               // 绿灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nPurple;                              // 紫灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nWhite;                               // 白灯: -1:无效, 0/灭, 1/亮, 2/闪烁
        public int              nPink;                                // 粉等: -1:无效, 0/灭, 1/亮, 2/闪烁
        public byte             nColorCount;                          //Color个数
        public byte[]           nColor = new byte[8];                 //颜色：0:未知, 1:灭或闪烁, 2:红灯, 3:黄灯, 4:蓝灯, 5:绿灯, 6:紫灯, 7:白灯, 8:粉灯, 9:青灯
        public byte             szReserved1;                          //字节对齐
        public int              nLightKeepTime;                       //车位状态亮灯时间 单位：秒，取值范围 -1 ~ 300，0表示不亮，-1表示常亮
        public byte[]           byReserved = new byte[16];            // 保留字节

        public void setInfo(int nRed, int nYellow, int nBlue, int nGreen, int nPurple, int nWhite,int nPink) {
            this.nRed = nRed;
            this.nYellow = nYellow;
            this.nBlue = nBlue;
            this.nGreen = nGreen;
            this.nPurple = nPurple;
            this.nWhite = nWhite;
            this.nPink = nPink;
        }
    }

    // 网络异常状态灯色
    public static class NET_NETWORK_EXCEPTION_INFO extends SdkStructure
    {
        public NET_PARKINGSPACELIGHT_INFO[] stNetPortAbortInfo = (NET_PARKINGSPACELIGHT_INFO[]) new NET_PARKINGSPACELIGHT_INFO().toArray(5); // 网口断开状态灯色
        public int              nRetNetPortAbortNum;                  // 实际返回的个数
        public NET_PARKINGSPACELIGHT_INFO stuSpaceSpecialInfo;        // 车位专用状态灯色
        public NET_PARKINGSPACELIGHT_INFO stuSpaceChargingInfo;       // 充电车位状态灯色
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 车位状态对应的车位指示灯色 对应 NET_EM_CFG_PARKINGSPACELIGHT_STATE
    public static class NET_PARKINGSPACELIGHT_STATE_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_PARKINGSPACELIGHT_INFO stuSpaceFreeInfo;           // 车位空闲状态灯色
        public NET_PARKINGSPACELIGHT_INFO stuSpaceFullInfo;           // 车位占满状态灯色
        public NET_PARKINGSPACELIGHT_INFO stuSpaceOverLineInfo;       // 车位压线状态灯色
        public NET_PARKINGSPACELIGHT_INFO stuSpaceOrderInfo;          // 车位预定状态灯色
        public NET_NETWORK_EXCEPTION_INFO stuNetWorkExceptionInfo;    // 网络异常状态灯色
        public NET_ABNORMAL_ALARM_INFO stuAbnormalAlarmInfo = new NET_ABNORMAL_ALARM_INFO(); //设备异常报警状态灯色控制,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ABNORMAL_ALARM_INFO}
        public NET_PARKINGSPACELIGHT_INFO stuSpaceAlarmInfo = new NET_PARKINGSPACELIGHT_INFO(); //车位报警状态灯色,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_PARKINGSPACELIGHT_INFO}

        public NET_PARKINGSPACELIGHT_STATE_INFO(){
            this.dwSize = this.size();
        }
    }

    // 车位监管状态
    public static class EM_CFG_LANE_STATUS extends SdkStructure
    {
        public static final int   EM_CFG_LANE_STATUS_UNKOWN = -1;       // 状态未知
        public static final int   EM_CFG_LANE_STATUS_UNSUPERVISE = 0;   // 不监管
        public static final int   EM_CFG_LANE_STATUS_SUPERVISE = 1;     // 监管
    }

    // 单个车位指示灯本机配置
    public static class CFG_PARKING_SPACE_LIGHT_GROUP_INFO extends SdkStructure
    {
        public int              bEnable;                              // 为TRUE时该配置生效，为FALSE时该配置无效
        public int[]            emLaneStatus = new int[MAX_LANES_NUM]; // 灯组监管的车位，下标表示车位号；参考EM_CFG_LANE_STATUS
        public int              nLanesNum;                            // 有效的车位数量（可以设为监管或不监管的车位数量）
        public int              bAcceptNetCtrl;                       // 是否接受远程控制
    }

    // 车位指示灯本机配置 CFG_CMD_PARKING_SPACE_LIGHT_GROUP
    public static class CFG_PARKING_SPACE_LIGHT_GROUP_INFO_ALL extends SdkStructure
    {
        public int              nCfgNum;                              // 获取到的配置个数
        public CFG_PARKING_SPACE_LIGHT_GROUP_INFO[] stuLightGroupInfo = (CFG_PARKING_SPACE_LIGHT_GROUP_INFO[]) new CFG_PARKING_SPACE_LIGHT_GROUP_INFO().toArray(MAX_LIGHT_GROUP_INFO_NUM); // 车位指示灯本机配置
        public CFG_PARKING_SPACE_LIGHT_GROUP_INFO[] stuLightGroupInfoEx = new CFG_PARKING_SPACE_LIGHT_GROUP_INFO[16]; //车位指示灯本机配置扩展,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.CFG_PARKING_SPACE_LIGHT_GROUP_INFO}
        public int              nCfgNumEx;                            //获取到的配置个数扩展
        public int              bIsUseLightGroupInfoEx;               //使用扩展配置下发配置
        public byte[]           szResvered = new byte[508];           //保留字节
    }

    //--------------------------------------------------------ERR200424047结束------------------------------------------------------------------------//
    //--------------------------------------------------------ERR200426006------------------------------------------------------------------------//
    // 灯光设备类型
    public static class EM_LIGHT_TYPE extends SdkStructure
    {
        public static final int   EM_LIGHT_TYPE_UNKNOWN = 0;            // 未知类型
        public static final int   EM_LIGHT_TYPE_COMMLIGHT = 1;          // 普通灯光
        public static final int   EM_LIGHT_TYPE_LEVELLIGHT = 2;         // 可调光
    }

    // 串口地址
    public static class CFG_COMMADDR_INFO extends SdkStructure
    {
        public int              nAddressNum;                          // 串口地址个数
        public int[]            nAddress = new int[MAX_ADDRESS_NUM];  // 地址描述,不同厂商地址位不同，用数组表示
    }

    // 灯光设备配置信息 (对应 CFG_CMD_LIGHT )
    public static class CFG_LIGHT_INFO extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[MAX_DEVICE_ID_LEN]; // 设备编码,惟一标识符
        public byte[]           szName = new byte[MAX_DEVICE_MARK_LEN]; // 设备描述
        public byte[]           szBrand = new byte[MAX_BRAND_NAME_LEN]; // 设备品牌
        public CFG_COMMADDR_INFO stuCommAddr;                         // 串口地址
        public int              nPositionID;                          // 设备在区域中编号
        public CFG_POLYGON      stuPosition;                          // 坐标
        public int              nState;                               // 设备状态: 1-打开,0-关闭
        public int              nRange;                               // 灯亮度幅度值 0-7 , emType 为 EM_LIGHT_TYPE_ADJUSTABLE 有意义
        public int              emType;                               // 灯光设备类型;参考EM_LIGHT_TYPE
    }

    // 近光灯信息
    public static class CFG_NEARLIGHT_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否使能，TRUE使能，FALSE不使能
        public int              dwLightPercent;                       // 灯光亮度百分比值(0~100)
        public int              dwAnglePercent;                       // 灯光角度百分比值(0~100)
    }

    // 远光灯信息
    public static class CFG_FARLIGHT_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否使能，TRUE使能，FALSE不使能
        public int              dwLightPercent;                       // 灯光亮度百分比值(0~100)
        public int              dwAnglePercent;                       // 灯光角度百分比值(0~100)
    }

    // 灯光设置详情
    public static class CFG_LIGHTING_DETAIL extends SdkStructure
    {
        public int              nCorrection;                          // 灯光补偿 (0~4) 倍率优先时有效
        public int              nSensitive;                           // 灯光灵敏度(0~5)倍率优先时有效，默认为3
        public int              emMode;                               // 灯光模式,参考EM_CFG_LIGHTING_MODE
        public int              nNearLight;                           // 近光灯有效个数
        public CFG_NEARLIGHT_INFO[] stuNearLights = (CFG_NEARLIGHT_INFO[]) new CFG_NEARLIGHT_INFO().toArray(MAX_LIGHTING_NUM); // 近光灯列表
        public int              nFarLight;                            // 远光灯有效个数
        public CFG_FARLIGHT_INFO[] stuFarLights = (CFG_FARLIGHT_INFO[]) new CFG_FARLIGHT_INFO().toArray(MAX_LIGHTING_NUM); // 远光灯列表
    }

    // 灯光模式
    public static class EM_CFG_LIGHTING_MODE extends SdkStructure
    {
        public static final int   EM_CFG_LIGHTING_MODE_UNKNOWN = 0;     // 未知
        public static final int   EM_CFG_LIGHTING_MODE_MANUAL = 1;      // 手动
        public static final int   EM_CFG_LIGHTING_MODE_ZOOMPRIO = 2;    // 倍率优先
        public static final int   EM_CFG_LIGHTING_MODE_TIMING = 3;      // 定时模式
        public static final int   EM_CFG_LIGHTING_MODE_AUTO = 4;        // 自动
        public static final int   EM_CFG_LIGHTING_MODE_OFF = 5;         // 关闭模式
    }

    // 灯光设置(对应 CFG_CMD_LIGHTING 命令)
    public static class CFG_LIGHTING_INFO extends SdkStructure
    {
        public int              nLightingDetailNum;                   // 灯光设置有效个数
        public CFG_LIGHTING_DETAIL[] stuLightingDetail = (CFG_LIGHTING_DETAIL[]) new CFG_LIGHTING_DETAIL().toArray(MAX_LIGHTING_DETAIL_NUM); // 灯光设置信息列表
    }

    //--------------------------------------------------------ERR200426006结束------------------------------------------------------------------------//
    //--------------------------------------------------------ERR200427081开始------------------------------------------------------------------------//
    // 事件类型EVENT_IVS_HIGH_TOSS_DETECT(高空抛物检测事件)对应的数据块描述信息
    public static class DEV_EVENT_HIGH_TOSS_DETECT_INFO extends SdkStructure
    {
        /**
         *通道号
         */
        public int              nChannelID;
        /**
         * 0:脉冲
         * 1:开始
         * 2:停止
         */
        public int              nAction;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 时间戳(单位是毫秒)
         */
        public double           PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX      UTC;
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 智能事件规则编号，用于标示哪个规则触发的事件
         */
        public int              nRuleID;
        /**
         * 智能事件所属大类,枚举值参考{@link EM_CLASS_TYPE}
         */
        public int              emClassType;
        /**
         * 物体信息
         */
        public NET_HIGHTOSS_OBJECT_INFO[] stuObjInfos = (NET_HIGHTOSS_OBJECT_INFO[]) new NET_HIGHTOSS_OBJECT_INFO().toArray(50);
        /**
         * 物体个数
         */
        public int              nObjNum;
        /**
         * 检测区域顶点数
         */
        public int              nDetectRegionNum;
        /**
         * 检测区域,[0,8192)
         */
        public NET_POINT[]      stuDetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);
        /**
         * 视频分析帧序号
         */
        public int              nFrameSequence;
        /**
         * 事件组ID, 同一物体抓拍过程内GroupID相同
         */
        public int              nGroupID;
        /**
         * 抓拍序号，从1开始
         */
        public int              nIndexInGroup;
        /**
         * 抓拍张数
         */
        public int              nCountInGroup;
        /**
         * 图片信息
         */
        public NET_EVENT_IMAGE_OFFSET_INFO stuImageInfo;
        /**
         * 是否上传大图, true表示第一个图片为全景大图，信息由ImageInfo来表示
         */
        public int              bIsGlobalScene;
        /**
         * 用于标记抓拍帧
         */
        public int              nMark;
        /**
         * 预留字节
         */
        public byte[]           byReserved = new byte[384];

        @Override
        public String toString() {
            return "DEV_EVENT_HIGH_TOSS_DETECT_INFO{" +
                    "通道号=" + nChannelID +
                    ", nAction=" + nAction +
                    ", 事件名称=" + new String(szName, Charset.forName("GBK")).trim() +
                    ", s事件戳=" + PTS +
                    ", 事件发生时间=" + UTC.toString() +
                    ", nEventID=" + nEventID +
                    ", 智能事件规则编号=" + nRuleID +
                    ", 智能事件所属大类=" + emClassType +
                    ", stuObjInfos=" + Arrays.toString(stuObjInfos) +
                    ", 物体个数=" + nObjNum +
                    ", 检测区域顶点数=" + nDetectRegionNum +
                    ", stuDetectRegion=" + Arrays.toString(stuDetectRegion) +
                    ", 帧序号=" + nFrameSequence +
                    ", 事件组ID=" + nGroupID +
                    ", 抓拍序号=" + nIndexInGroup +
                    ", 抓拍张数=" + nCountInGroup +
                    ", 图片信息=" + stuImageInfo +
                    ", 是否大图=" + bIsGlobalScene +
                    ", 标记抓拍帧=" + nMark +
                    '}';
        }
    }

    public static class NET_EVENT_IMAGE_OFFSET_INFO extends SdkStructure {
        /**
         * 偏移
         */
        public int              nOffSet;
        /**
         * 图片大小,单位字节
         */
        public int              nLength;
        /**
         * 图片宽度
         */
        public int              nWidth;
        /**
         * 图片高度
         */
        public int              nHeight;
        /**
         * 图片路径
         */
        public byte[]           szPath = new byte[260];
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        /**
         * 保留字节
         */
        public byte[]           byReserved = new byte[248];

        @Override
        public String toString() {
            return "NET_EVENT_IMAGE_OFFSET_INFO{" +
                    "nOffSet=" + nOffSet +
                    ", nLength=" + nLength +
}
