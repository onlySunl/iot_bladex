package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 3 部分
 */
public interface NetSDKMethods3 {
    // 云台控制-扇扫对应结构
    public static class PTZ_CONTROL_SECTORSCAN extends SdkStructure
    {
        public int              nBeginAngle;                          //起始角度,范围:-180,180]
        public int              nEndAngle;                            //结束角度,范围:-180,180]
        public int              nSpeed;                               //速度,范围:0,255]
        public byte[]           szReserve = new byte[64];             //预留64字节
    }

    // 控制鱼眼电子云台信息
    public static class PTZ_CONTROL_SET_FISHEYE_EPTZ extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public int              dwWindowID;                           //进行EPtz控制的窗口编号
        public int              dwCommand;                            //电子云台命令
        public int              dwParam1;                             //命令对应参数1
        public int              dwParam2;                             //命令对应参数2
        public int              dwParam3;                             //命令对应参数3
        public int              dwParam4;                             //命令对应参数4
    }

    // 光照场景类型
    public static class EM_LIGHT_SCENE extends SdkStructure
    {
        public static final int   EM_LIGHT_SCENE_UNKNOWN = 0;           // 未知
        public static final int   EM_LIGHT_SCENE_DAY = 1;               // 白天
        public static final int   EM_LIGHT_SCENE_NIGHT = 2;             // 夜晚
        public static final int   EM_LIGHT_SCENE_NORMAL = 3;            // 普通
        public static final int   EM_LIGHT_SCENE_FRONT_LIGHT = 4;       // 顺光
        public static final int   EM_LIGHT_SCENE_BACK_LIGHT = 5;        // 一般逆光
        public static final int   EM_LIGHT_SCENE_STRONG_LIGHT = 6;      // 强逆光
        public static final int   EM_LIGHT_SCENE_LOW_LIGHT = 7;         // 低照度
        public static final int   EM_LIGHT_SCENE_CUSTOM = 8;            // 自定义
    }

    // 变倍设置基本信息单元
    public static class CFG_VIDEO_IN_ZOOM_UNIT extends SdkStructure
    {
        public int              nSpeed;                               //变倍速率(0~7)
        public int              bDigitalZoom;                         //是否数字变倍, 类型为BOOL, 取值0或者1
        public int              nZoomLimit;                           //当前速率下最大变倍上限(0~13)。
        public int              emLightScene;                         //光照场景名称 EM_LIGHT_SCENE
    }

    // 单通道变倍设置基本信息
    public static class CFG_VIDEO_IN_ZOOM extends SdkStructure
    {
        public int              nChannelIndex;                        //通道号
        public int              nVideoInZoomRealNum;                  //配置使用个数
        public CFG_VIDEO_IN_ZOOM_UNIT[] stVideoInZoomUnit = new CFG_VIDEO_IN_ZOOM_UNIT[MAX_VIDEO_IN_ZOOM]; //通道变速配置单元信息

        public CFG_VIDEO_IN_ZOOM(){
            for(int i=0;i<stVideoInZoomUnit.length;i++){
                stVideoInZoomUnit[i]=new CFG_VIDEO_IN_ZOOM_UNIT();
            }
        }
    }

    // 设备状态
    public static class CFG_TRAFFIC_DEVICE_STATUS extends SdkStructure
    {
        public byte[]           szType = new byte[MAX_PATH];          // 设备类型 支持："Radar","Detector","SigDetector","StroboscopicLamp"," FlashLamp"
        public byte[]           szSerialNo = new byte[MAX_PATH];      // 设备编号
        public byte[]           szVendor = new byte[MAX_PATH];        // 生产厂商
        public int              nWokingState;                         // 工作状态 0-故障,1-正常工作
        public byte             byLightState;                         // RS485灯的亮灭状态，Type 为"DhrsStroboscopicLamp"或者"DhrsSteadyLamp"时有效
        // 0-未知, 1-灯亮, 2-灯灭
        public byte[]           byReserved = new byte[3];             // 预留字节
    }

    // 获取设备工作状态是否正常 (对应命令 CFG_CAP_CMD_DEVICE_STATE )
    public static class CFG_CAP_TRAFFIC_DEVICE_STATUS extends SdkStructure
    {
        public int              nStatus;                              // stuStatus 实际个数
        public CFG_TRAFFIC_DEVICE_STATUS[] stuStatus = (CFG_TRAFFIC_DEVICE_STATUS[]) new CFG_TRAFFIC_DEVICE_STATUS().toArray(MAX_STATUS_NUM);
    }

    // 视频输入通道
    public static class CFG_RemoteDeviceVideoInput extends SdkStructure
    {
        public int              bEnable;
        public byte[]           szName = new byte[MAX_DEVICE_NAME_LEN];
        public byte[]           szControlID = new byte[MAX_DEV_ID_LEN_EX];
        public byte[]           szMainUrl = new byte[MAX_PATH];       // 主码流url地址
        public byte[]           szExtraUrl = new byte[MAX_PATH];      // 辅码流url地址
        public int              nServiceType;                         // 服务类型, 0-TCP, 1-UDP, 2-MCAST, -1-AUTO
    }

    // 远程设备
    public static class AV_CFG_RemoteDevice extends SdkStructure
    {
        public int              nStructSize;
        public int              bEnable;                              // 使能
        public byte[]           szID = new byte[AV_CFG_Device_ID_Len]; // 设备ID
        public byte[]           szIP = new byte[AV_CFG_IP_Address_Len]; // 设备IP
        public int              nPort;                                // 端口
        public byte[]           szProtocol = new byte[AV_CFG_Protocol_Len]; // 协议类型
        public byte[]           szUser = new byte[AV_CFG_User_Name_Len]; // 用户名
        public byte[]           szPassword = new byte[AV_CFG_Password_Len]; // 密码
        public byte[]           szSerial = new byte[AV_CFG_Serial_Len]; // 设备序列号
        public byte[]           szDevClass = new byte[AV_CFG_Device_Class_Len]; // 设备类型
        public byte[]           szDevType = new byte[AV_CFG_Device_Type_Len]; // 设备型号
        public byte[]           szName = new byte[AV_CFG_Device_Name_Len]; // 机器名称
        public byte[]           szAddress = new byte[AV_CFG_Address_Len]; // 机器部署地点
        public byte[]           szGroup = new byte[AV_CFG_Group_Name_Len]; // 机器分组
        public int              nDefinition;                          // 清晰度, 0-标清, 1-高清
        public int              nVideoChannel;                        // 视频输入通道数
        public int              nAudioChannel;                        // 音频输入通道数
        public int              nRtspPort;                            // Rtsp端口号
        public byte[]           szVendor = new byte[MAX_PATH];        // 设备接入类型
        public Pointer          pVideoInput;                          // 视频输入通道，用户申请nMaxVideoInputs个CFG_RemoteDeviceVideoInput空间
        public int              nMaxVideoInputs;
        public int              nRetVideoInputs;
        public int              nHttpPort;                            // http端口号
        /* 以下3项为国际接入方式相关  */
        public int              bGB28181;                             // 是否有国际接入方式
        public int              nDevLocalPort;                        // 设备本地端口
        public byte[]           szDeviceNo = new byte[AV_CFG_DeviceNo_Len]; // 设备编号
        public int              nLoginType;                           // 登录方式 0 : TCP直连(默认方式)  6 : 主动注册  7 : P2P方式，此方式时通过SerialNo与设备通过P2P连接
        public byte[]           szVersion = new byte[32];             // 设备软件版本
        public int              bPoE;                                 // 是否由PoE端口连接, 该选项为只读, 只能由设备修改
        public int              nPoEPort;                             // PoE物理端口号, 该选项为只读, 只能由设备修改
        public byte[]           szMac = new byte[18];                 // 设备mac地址，冒号+大写

        public AV_CFG_RemoteDevice() {
            this.nStructSize = this.size();
        }
    }

    // 录像模式
    public static class AV_CFG_RecordMode extends SdkStructure
    {
        public int              nStructSize;
        public int              nMode;                                // 录像模式, 0-自动录像，1-手动录像，2-关闭录像
        public int              nModeExtra1;                          // 辅码流录像模式, 0-自动录像，1-手动录像，2-关闭录像
        public int              nModeExtra2;                          // 辅码流2录像模式, 0-自动录像，1-手动录像，2-关闭录像

        public AV_CFG_RecordMode() {
            this.nStructSize = this.size();
        }
    }

    // 视频分析资源类型
    public static class CFG_VIDEO_SOURCE_TYPE extends SdkStructure {
        public static final int   CFG_VIDEO_SOURCE_REALSTREAM = 0;      // 实时流
        public static final int   CFG_VIDEO_SOURCE_FILESTREAM = 1;      // 文件流
    }

    // 分析源文件类型
    public static class CFG_SOURCE_FILE_TYPE extends SdkStructure {
        public static final int   CFG_SOURCE_FILE_UNKNOWN = 0;          // 未知类型
        public static final int   CFG_SOURCE_FILE_RECORD = 1;           // 录像文件
        public static final int   CFG_SOURCE_FILE_PICTURE = 2;          // 图片文件
    }

    // 视频分析源文件信息
    public static class CFG_SOURCE_FILE_INFO extends SdkStructure {
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径
        public int              emFileType;                           // 文件类型，详见 CFG_SOURCE_FILE_TYPE
    }

    // 每个视频输入通道对应的视频分析资源配置信息
    public static class CFG_ANALYSESOURCE_INFO extends SdkStructure {
        public byte             bEnable;                              // 视频分析使能   1-使能， 0-禁用
        public byte[]           bReserved = new byte[3];              // 保留对齐
        public int              nChannelID;                           // 智能分析的前端视频通道号
        public int              nStreamType;                          // 智能分析的前端视频码流类型，0:抓图码流; 1:主码流; 2:子码流1; 3:子码流2; 4:子码流3; 5:物体流
        public byte[]           szRemoteDevice = new byte[MAX_NAME_LEN]; // 设备名
        public int              abDeviceInfo;                         // 设备信息是否有效 ; 1-有效，0-无效
        public AV_CFG_RemoteDevice stuDeviceInfo;                     // 设备信息
        public int              emSourceType;                         // 视频分析源类型，详见  CFG_VIDEO_SOURCE_TYPE
        public CFG_SOURCE_FILE_INFO stuSourceFile;                    // 当视频分析源类型为 CFG_VIDEO_SOURCE_FILESTREAM 时，有效
    }

    public static class CFG_OVERSPEED_INFO extends SdkStructure {
        public int[]            nSpeedingPercentage = new int[2];     // 超速百分比区间要求区间不能重叠。有效值为0,正数,-1，-1表示无穷大值
        // 如果是欠速：要求区间不能重叠。有效值为0,正数,-1，-1表示无穷大值，欠速百分比的计算方式：限低速-实际车速/限低速
        public byte[]           szCode = new byte[MAX_VIOLATIONCODE]; // 违章代码
        public byte[]           szDescription = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述
    }

    // 违章代码配置表
    public static class VIOLATIONCODE_INFO extends SdkStructure {
        public byte[]           szRetrograde = new byte[MAX_VIOLATIONCODE]; // 逆行
        public byte[]           szRetrogradeDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szRetrogradeShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 显示名称
        public byte[]           szRetrogradeHighway = new byte[MAX_VIOLATIONCODE]; // 逆行-高速公路
        public byte[]           szRetrogradeHighwayDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szRunRedLight = new byte[MAX_VIOLATIONCODE]; // 闯红灯
        public byte[]           szRunRedLightDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szCrossLane = new byte[MAX_VIOLATIONCODE]; // 违章变道
        public byte[]           szCrossLaneDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szCrossLaneShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章变道显示名称
        public byte[]           szTurnLeft = new byte[MAX_VIOLATIONCODE]; // 违章左转
        public byte[]           szTurnLeftDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szTurnRight = new byte[MAX_VIOLATIONCODE]; // 违章右转
        public byte[]           szTurnRightDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szU_Turn = new byte[MAX_VIOLATIONCODE]; // 违章掉头
        public byte[]           szU_TurnDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szU_TurnShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 显示信息
        public byte[]           szJam = new byte[MAX_VIOLATIONCODE];  // 交通拥堵
        public byte[]           szJamDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szParking = new byte[MAX_VIOLATIONCODE]; // 违章停车
        public byte[]           szParkingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szParkingShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章停车显示名称
        // 超速 和 超速比例 只需且必须有一个配置
        public byte[]           szOverSpeed = new byte[MAX_VIOLATIONCODE]; // 超速
        public byte[]           szOverSpeedDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public CFG_OVERSPEED_INFO[] stOverSpeedConfig = (CFG_OVERSPEED_INFO[])new CFG_OVERSPEED_INFO().toArray(5); // 超速比例代码
        // 超速(高速公路) 和 超速比例(高速公路) 只需且必须有一个配置
        public byte[]           szOverSpeedHighway = new byte[MAX_VIOLATIONCODE]; // 超速-高速公路
        public byte[]           szOverSpeedHighwayDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 超速-违章描述信息
        public CFG_OVERSPEED_INFO[] stOverSpeedHighwayConfig = (CFG_OVERSPEED_INFO[])new CFG_OVERSPEED_INFO().toArray(5); // 超速比例代码
        // 欠速 和 欠速比例 只需且必须有一个配置
        public byte[]           szUnderSpeed = new byte[MAX_VIOLATIONCODE]; // 欠速
        public byte[]           szUnderSpeedDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public CFG_OVERSPEED_INFO[] stUnderSpeedConfig = (CFG_OVERSPEED_INFO[]) new CFG_OVERSPEED_INFO().toArray(5); // 欠速配置信息是一个数组，不同的欠速比违章代码不同，为空表示违章代码不区分超速比
        public byte[]           szOverLine = new byte[MAX_VIOLATIONCODE]; // 压线
        public byte[]           szOverLineDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szOverLineShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 压线显示名称
        public byte[]           szOverYellowLine = new byte[MAX_VIOLATIONCODE]; // 压黄线
        public byte[]           szOverYellowLineDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章描述信息
        public byte[]           szYellowInRoute = new byte[MAX_VIOLATIONCODE]; // 黄牌占道
        public byte[]           szYellowInRouteDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 黄牌占道违章描述信息
        public byte[]           szWrongRoute = new byte[MAX_VIOLATIONCODE]; // 不按车道行驶
        public byte[]           szWrongRouteDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 不按车道行驶违章描述信息
        public byte[]           szDrivingOnShoulder = new byte[MAX_VIOLATIONCODE]; // 路肩行驶
        public byte[]           szDrivingOnShoulderDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 路肩行驶违章描述信息
        public byte[]           szPassing = new byte[MAX_VIOLATIONCODE]; // 正常行驶
        public byte[]           szPassingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 正常行驶违章描述信息
        public byte[]           szNoPassing = new byte[MAX_VIOLATIONCODE]; // 禁止行驶
        public byte[]           szNoPassingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 禁止行驶违章描述信息
        public byte[]           szFakePlate = new byte[MAX_VIOLATIONCODE]; // 套牌
        public byte[]           szFakePlateDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 套牌违章描述信息
        public byte[]           szParkingSpaceParking = new byte[MAX_VIOLATIONCODE]; // 车位有车
        public byte[]           szParkingSpaceParkingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 车位有车违章描述信息、
        public byte[]           szParkingSpaceNoParking = new byte[MAX_VIOLATIONCODE]; // 车位无车
        public byte[]           szParkingSpaceNoParkingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 车位无车违章描述信息
        public byte[]           szWithoutSafeBelt = new byte[MAX_VIOLATIONCODE]; // 不系安全带
        public byte[]           szWithoutSafeBeltShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 不系安全带显示名称
        public byte[]           szWithoutSafeBeltDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 不系安全带违章描述信息
        public byte[]           szDriverSmoking = new byte[MAX_VIOLATIONCODE]; // 驾驶员抽烟
        public byte[]           szDriverSmokingShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 驾驶员抽烟显示名称
        public byte[]           szDriverSmokingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 驾驶员抽烟带违章描述信息
        public byte[]           szDriverCalling = new byte[MAX_VIOLATIONCODE]; // 驾驶员打电话
        public byte[]           szDriverCallingShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 驾驶员打电话显示名称
        public byte[]           szDriverCallingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 驾驶员打电话违章描述信息
        public byte[]           szBacking = new byte[MAX_VIOLATIONCODE]; // 违章倒车
        public byte[]           szBackingShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章倒车显示名称
        public byte[]           szBackingDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章倒车描述信息
        public byte[]           szVehicleInBusRoute = new byte[MAX_VIOLATIONCODE]; // 违章占道
        public byte[]           szVehicleInBusRouteShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章占道显示名称
        public byte[]           szVehicleInBusRouteDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 违章占道描述信息
        public byte[]           szPedestrianRunRedLight = new byte[MAX_VIOLATIONCODE]; // 行人闯红灯
        public byte[]           szPedestrianRunRedLightShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 行人闯红灯显示名称
        public byte[]           szPedestrianRunRedLightDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 行人闯红灯描述信息
        public byte[]           szPassNotInOrder = new byte[MAX_VIOLATIONCODE]; // 未按规定依次通行
        public byte[]           szPassNotInOrderShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 未按规定依次通行显示名称
        public byte[]           szPassNotInOrderDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 未按规定依次通行描述信息
        public byte[]           szTrafficBan = new byte[MAX_VIOLATIONCODE]; // 机动车违法禁令标识
        public byte[]           szTrafficBanShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 机动车违法禁令标识显示名称
        public byte[]           szTrafficBanDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 描述信息
        public byte[]           szParkingB = new byte[MAX_VIOLATIONCODE]; // B类违章停车
        public byte[]           szParkingBDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // B类违章描述信息
        public byte[]           szParkingBShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // B类违章停车显示名称
        public byte[]           szParkingC = new byte[MAX_VIOLATIONCODE]; // C类违章停车
        public byte[]           szParkingCDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // C类违章描述信息
        public byte[]           szParkingCShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // C类违章停车显示名称
        public byte[]           szParkingD = new byte[MAX_VIOLATIONCODE]; // D类违章停车
        public byte[]           szParkingDDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // D类违章描述信息
        public byte[]           szParkingDShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // D类违章停车显示名称
        public byte[]           szNonMotorHoldUmbrella = new byte[MAX_VIOLATIONCODE]; // 非机动车装载伞具代码
        public byte[]           szNonMotorHoldUmbrellaDesc = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 非机动车装载伞具描述信息
        public byte[]           szNonMotorHoldUmbrellaShowName = new byte[MAX_VIOLATIONCODE_DESCRIPT]; // 非机动车装载伞具显示名称
        public int              nBigCarOverSpeedConfigNum;            // 大车超速配置信息数量
        public CFG_OVERSPEED_INFO[] stBigCarOverSpeedConfig = (CFG_OVERSPEED_INFO[])new CFG_OVERSPEED_INFO().toArray(5); // 大车超速配置信息
    }

    // 违章抓拍时间配置表
    public static class TIME_SCHEDULE_INFO extends SdkStructure {
        public int              bEnable;                              // 是否启用时间表
        public TIME_SECTION_WEEK_DAY_6[] stuTimeScheduleWeekDay = (TIME_SECTION_WEEK_DAY_6[])new TIME_SECTION_WEEK_DAY_6().toArray(WEEK_DAY_NUM); // 时间表
    }

    // 违章抓拍自定义时间配置
    public static class VIOLATION_TIME_SCHEDULE extends SdkStructure
    {
        public int              abTrafficGate;                        // 是否携带交通卡口信息
        public TIME_SCHEDULE_INFO stTrafficGate;                      // 交通卡口时间配置
        public int              abTrafficJunction;                    // 是否携带交通路口信息
        public TIME_SCHEDULE_INFO stTrafficJunction;                  // 交通路口时间配置
        public int              abTrafficTollGate;                    // 是否携带新交通卡口信息
        public TIME_SCHEDULE_INFO stTrafficTollGate;                  // 新交通卡口时间配置
        public int              abTrafficRunRedLight;                 // 是否携带交通闯红灯信息
        public TIME_SCHEDULE_INFO stTrafficRunRedLight;               // 交通闯红灯时间配置
        public int              abTrafficRunYellowLight;              // 是否携带交通闯黄灯信息
        public TIME_SCHEDULE_INFO stTrafficRunYellowLight;            // 交通闯黄灯时间配置
        public int              abTrafficOverLine;                    // 是否携带交通压线信息
        public TIME_SCHEDULE_INFO stTrafficOverLine;                  // 交通压线时间配置
        public int              abTrafficOverYellowLine;              // 是否携带交通压黄线信息
        public TIME_SCHEDULE_INFO stTrafficOverYellowLine;            // 交通压黄线时间配置
        public int              abTrafficRetrograde;                  // 是否携带交通逆行信息
        public TIME_SCHEDULE_INFO stTrafficRetrograde;                // 交通逆行时间配置
        public int              abTrafficTurnLeft;                    // 是否携带交通违章左转信息
        public TIME_SCHEDULE_INFO stTrafficTurnLeft;                  // 交通违章左转时间配置
        public int              abTrafficTurnRight;                   // 是否携带交通违章右转信息
        public TIME_SCHEDULE_INFO stTrafficTurnRight;                 // 交通路口违章右转类型
        public int              abTrafficU_Turn;                      // 是否携带交通违章掉头信息
        public TIME_SCHEDULE_INFO stTrafficU_Turn;                    // 交通违章掉头时间配置
        public int              abTrafficCrossLane;                   // 是否携带交通违章变道信息
        public TIME_SCHEDULE_INFO stTrafficCrossLane;                 // 交通违章变道时间配置
        public int              abTrafficParking;                     // 是否携带交通违章停车信息
        public TIME_SCHEDULE_INFO stTrafficParking;                   // 交通违章停车时间配置
        public int              abTrafficJam;                         // 是否携带交通拥堵信息
        public TIME_SCHEDULE_INFO stTrafficJam;                       // 交通拥堵时间配置
        public int              abTrafficIdle;                        // 是否携带交通交通空闲信息
        public TIME_SCHEDULE_INFO stTrafficIdle;                      // 交通交通空闲时间配置
        public int              abTrafficWaitingArea;                 // 是否携带交通违章驶入待行区信息
        public TIME_SCHEDULE_INFO stTrafficWaitingArea;               // 交通违章驶入待行区时间配置
        public int              abTrafficUnderSpeed;                  // 是否携带交通欠速信息
        public TIME_SCHEDULE_INFO stTrafficUnderSpeed;                // 交通欠速时间配置
        public int              abTrafficOverSpeed;                   // 是否携带交通超速信息
        public TIME_SCHEDULE_INFO stTrafficOverSpeed;                 // 交通超速时间配置
        public int              abTrafficWrongRoute;                  // 是否携带交通不按车道行驶信息
        public TIME_SCHEDULE_INFO stTrafficWrongRoute;                // 交通不按车道行驶时间配置
        public int              abTrafficYellowInRoute;               // 是否携带交通黄牌占道信息
        public TIME_SCHEDULE_INFO stTrafficYellowInRoute;             // 交通黄牌占道时间配置
        public int              abTrafficVehicleInRoute;              // 是否携带交通有车占道信息
        public TIME_SCHEDULE_INFO stTrafficVehicleInRoute;            // 交通有车占道时间配置
        public int              abTrafficControl;                     // 是否携带交通交通管理信息
        public TIME_SCHEDULE_INFO stTrafficControl;                   // 交通交通管理时间配置
        public int              abTrafficObjectAlarm;                 // 是否携带交通指定类型抓拍信息
        public TIME_SCHEDULE_INFO stTrafficObjectAlarm;               // 交通指定类型抓拍时间配置
        public int              abTrafficAccident;                    // 是否携带交通交通事故信息
        public TIME_SCHEDULE_INFO stTrafficAccident;                  // 交通交通事故时间配置
        public int              abTrafficStay;                        // 是否携带交通交通停留/滞留信息
        public TIME_SCHEDULE_INFO stTrafficStay;                      // 交通交通停留/滞留时间配置
        public int              abTrafficPedestrainPriority;          // 是否携带交通斑马线行人优先信息
        public TIME_SCHEDULE_INFO stTrafficPedestrainPriority;        // 交通斑马线行人优先时间配置
        public int              abTrafficPedestrain;                  // 是否携带交通交通行人事件信息
        public TIME_SCHEDULE_INFO stTrafficPedestrain;                // 交通交通行人事件时间配置
        public int              abTrafficThrow;                       // 是否携带交通交通抛洒物品事件信息
        public TIME_SCHEDULE_INFO stTrafficThrow;                     // 交通交通抛洒物品事件时间配置
        public int              abTrafficVehicleInBusRoute;           // 是否携带交通违章占道信息
        public TIME_SCHEDULE_INFO stTrafficVehicleInBusRoute;         // 交通违章占道时间配置
        public int              abTrafficBacking;                     // 是否携带交通违章倒车信息
        public TIME_SCHEDULE_INFO stTrafficBacking;                   // 交通违章倒车时间配置
        public int              abTrafficOverStopLine;                // 是否携带交通压停止线信息
        public TIME_SCHEDULE_INFO stTrafficOverStopLine;              // 交通压停止线时间配置
        public int              abTrafficParkingOnYellowBox;          // 是否携带交通黄网格线抓拍信息
        public TIME_SCHEDULE_INFO stTrafficParkingOnYellowBox;        // 交通黄网格线抓拍时间配置
        public int              abTrafficParkingSpaceParking;         // 是否携带交通车位有车信息
        public TIME_SCHEDULE_INFO stTrafficParkingSpaceParking;       // 交通车位有车时间配置
        public int              abTrafficParkingSpaceNoParking;       // 是否携带交通车位无车信息
        public TIME_SCHEDULE_INFO stTrafficParkingSpaceNoParking;     // 交通车位无车时间配置
        public int              abTrafficParkingSpaceOverLine;        // 是否携带交通车位有车压线信息
        public TIME_SCHEDULE_INFO stTrafficParkingSpaceOverLine;      // 交通车位有车压线时间配置
        public int              abParkingSpaceDetection;              // 是否携带交通多停车位状态检测信息
        public TIME_SCHEDULE_INFO stParkingSpaceDetection;            // 交通多停车位状态检测时间配置
        public int              abTrafficRestrictedPlate;             // 是否携带交通受限车牌信息
        public TIME_SCHEDULE_INFO stTrafficRestrictedPlate;           // 交通受限车牌时间配置
        public int              abTrafficWithoutSafeBelt;             // 是否携带交通不系安全带信息
        public TIME_SCHEDULE_INFO stTrafficWithoutSafeBelt;           // 交通不系安全带时间配置
        public int              abTrafficNoPassing;                   // 是否携带交通禁行信息
        public TIME_SCHEDULE_INFO stTrafficNoPassing;                 // 交通禁行时间配置
        public int              abVehicleAnalyse;                     // 是否携带交通车辆特征检测分析信息
        public TIME_SCHEDULE_INFO stVehicleAnalyse;                   // 交通车辆特征时间配置
        public int              abCrossLineDetection;                 // 是否携带交通警戒线信息
        public TIME_SCHEDULE_INFO stCrossLineDetection;               // 交通警戒线时间配置
        public int              abCrossFenceDetection;                // 是否携带交通穿越围栏信息
        public TIME_SCHEDULE_INFO stCrossFenceDetection;              // 交通穿越围栏时间配置
        public int              abCrossRegionDetection;               // 是否携带交通警戒区信息
        public TIME_SCHEDULE_INFO stCrossRegionDetection;             // 交通警戒区时间配置
        public int              abPasteDetection;                     // 是否携带交通ATM贴条信息
        public TIME_SCHEDULE_INFO stPasteDetection;                   // 交通ATM贴条时间配置
        public int              abLeftDetection;                      // 是否携带交通物品遗留信息
        public TIME_SCHEDULE_INFO stLeftDetection;                    // 交通物品遗留时间配置
        public int              abPreservation;                       // 是否携带交通物品保全信息
        public TIME_SCHEDULE_INFO stPreservation;                     // 交通物品保全时间配置
        public int              abTakenAwayDetection;                 // 是否携带交通物品搬移信息
        public TIME_SCHEDULE_INFO stTakenAwayDetection;               // 交通物品搬移时间配置
        public int              abStayDetection;                      // 是否携带交通停留/滞留信息
        public TIME_SCHEDULE_INFO stStayDetection;                    // 交通停留/滞留时间配置
        public int              abParkingDetection;                   // 是否携带交通非法停车信息
        public TIME_SCHEDULE_INFO stParkingDetection;                 // 交通非法停车时间配置
        public int              abWanderDetection;                    // 是否携带交通徘徊信息
        public TIME_SCHEDULE_INFO stWanderDetection;                  // 交通徘徊时间配置
        public int              abMoveDetection;                      // 是否携带交通运动信息
        public TIME_SCHEDULE_INFO stMoveDetection;                    // 交通运动时间配置
        public int              abTailDetection;                      // 是否携带交通尾随信息
        public TIME_SCHEDULE_INFO stTailDetection;                    // 交通尾随时间配置
        public int              abRioterDetection;                    // 是否携带交通聚集信息
        public TIME_SCHEDULE_INFO stRioterDetection;                  // 交通聚集时间配置
        public int              abFightDetection;                     // 是否携带交通打架信息
        public TIME_SCHEDULE_INFO stFightDetection;                   // 交通打架时间配置
        public int              abRetrogradeDetection;                // 是否携带交通逆行信息
        public TIME_SCHEDULE_INFO stRetrogradeDetection;              // 交通逆行时间配置
        public int              abFireDetection;                      // 是否携带交通火焰信息
        public TIME_SCHEDULE_INFO stFireDetection;                    // 交通火焰时间配置
        public int              abSmokeDetection;                     // 是否携带交通烟雾信息
        public TIME_SCHEDULE_INFO stSmokeDetection;                   // 交通烟雾时间配置
        public int              abNumberStat;                         // 是否携带交通数量统计信息
        public TIME_SCHEDULE_INFO stNumberStat;                       // 交通数量统计时间配置
        public int              abVideoAbnormalDetection;             // 是否携带交通视频异常信息
        public TIME_SCHEDULE_INFO stVideoAbnormalDetection;           // 交通视频异常时间配置
        public int              abPSRiseDetection;                    // 是否携带囚犯起身检测信息
        public TIME_SCHEDULE_INFO stPSRiseDetection;                  // 囚犯起身检测时间配置
        public int              abFaceDetection;                      // 是否携带目标检测信息
        public TIME_SCHEDULE_INFO stFaceDetection;                    // 目标检测时间配置
        public int              abFaceRecognition;                    // 是否携带目标识别信息
        public TIME_SCHEDULE_INFO stFaceRecognition;                  // 目标识别时间配置
        public int              abDensityDetection;                   // 是否携带密集度检测信息
        public TIME_SCHEDULE_INFO stDensityDetection;                 // 密集度检测时间配置
        public int              abQueueDetection;                     // 是否携带排队检测信息
        public TIME_SCHEDULE_INFO stQueueDetection;                   // 排队检测时间配置
        public int              abClimbDetection;                     // 是否携带攀高检测信息
        public TIME_SCHEDULE_INFO stClimbDetection;                   // 攀高时间配置
        public int              abLeaveDetection;                     // 是否携带离岗检测信息
        public TIME_SCHEDULE_INFO stLeaveDetection;                   // 离岗时间配置
        public int              abVehicleOnPoliceCar;                 // 是否携带车载警车信息
        public TIME_SCHEDULE_INFO stVehicleOnPoliceCar;               // 车载警车时间配置
        public int              abVehicleOnBus;                       // 是否携带车载公交信息
        public TIME_SCHEDULE_INFO stVehicleOnBus;                     // 车载公交时间配置
        public int              abVehicleOnSchoolBus;                 // 是否携带车载校车信息
        public TIME_SCHEDULE_INFO stVehicleOnSchoolBus;               // 车载校车时间配置
        public Boolean          abTrafficNonMotorHoldUmbrella;        // 是否携带非机动车装载伞具时间配置
        public TIME_SCHEDULE_INFO stTrafficNonMotorHoldUmbrella;      // 非机动车装载伞具时间配置
    }

    // 交通全局配置对应图片命名格式参数配置
    public static class TRAFFIC_NAMING_FORMAT extends SdkStructure {
        public byte[]           szFormat = new byte[CFG_COMMON_STRING_256]; // 图片格式
    }

    // CFG_NET_TIME 时间
    public static class CFG_NET_TIME extends SdkStructure {
        public int              nStructSize;
        public int              dwYear;                               // 年
        public int              dwMonth;                              // 月
        public int              dwDay;                                // 日
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒

        public CFG_NET_TIME() {
            this.nStructSize = this.size();
        }
    }

    // PERIOD_OF_VALIDITY
    public static class PERIOD_OF_VALIDITY extends SdkStructure {
        public CFG_NET_TIME     stBeginTime;                          // 标定开始时间
        public CFG_NET_TIME     stEndTime;                            // 标定到期时间
    }

    // 交通全局配置对应标定相关配置
    public static class TRAFFIC_CALIBRATION_INFO extends SdkStructure {
        public byte[]           szUnit = new byte[CFG_COMMON_STRING_256]; // 标定单位
        public byte[]           szCertificate = new byte[CFG_COMMON_STRING_256]; // 标定证书
        public PERIOD_OF_VALIDITY stPeriodOfValidity;                 // 标定有效期
    }

    // TRAFFIC_EVENT_CHECK_MASK
    public static class TRAFFIC_EVENT_CHECK_MASK extends SdkStructure {
        public int              abTrafficGate;                        // 是否携带交通卡口信息
        public int              nTrafficGate;                         // 交通卡口检测模式掩码
        public int              abTrafficJunction;                    // 是否携带交通路口信息
        public int              nTrafficJunction;                     // 交通路口检测模式掩码
        public int              abTrafficTollGate;                    // 是否携带新交通卡口信息
        public int              nTrafficTollGate;                     // 新交通卡口检测模式掩码
        public int              abTrafficRunRedLight;                 // 是否携带交通闯红灯信息
        public int              nTrafficRunRedLight;                  // 交通闯红灯检测模式掩码
        public int              abTrafficRunYellowLight;              // 是否携带交通闯黄灯信息
        public int              nTrafficRunYellowLight;               // 交通闯黄灯检测模式掩码
        public int              abTrafficOverLine;                    // 是否携带交通压线信息
        public int              nTrafficOverLine;                     // 交通压线检测模式掩码
        public int              abTrafficOverYellowLine;              // 是否携带交通压黄线信息
        public int              nTrafficOverYellowLine;               // 交通压黄线检测模式掩码
        public int              abTrafficRetrograde;                  // 是否携带交通逆行信息
        public int              nTrafficRetrograde;                   // 交通逆行检测模式掩码
        public int              abTrafficTurnLeft;                    // 是否携带交通违章左转信息
        public int              nTrafficTurnLeft;                     // 交通违章左转检测模式掩码
        public int              abTrafficTurnRight;                   // 是否携带交通违章右转信息
        public int              nTrafficTurnRight;                    // 交通路口违章右转类型
        public int              abTrafficU_Turn;                      // 是否携带交通违章掉头信息
        public int              nTrafficU_Turn;                       // 交通违章掉头检测模式掩码
        public int              abTrafficCrossLane;                   // 是否携带交通违章变道信息
        public int              nTrafficCrossLane;                    // 交通违章变道检测模式掩码
        public int              abTrafficParking;                     // 是否携带交通违章停车信息
        public int              nTrafficParking;                      // 交通违章停车检测模式掩码
        public int              abTrafficJam;                         // 是否携带交通拥堵信息
        public int              nTrafficJam;                          // 交通拥堵检测模式掩码
        public int              abTrafficIdle;                        // 是否携带交通交通空闲信息
        public int              nTrafficIdle;                         // 交通交通空闲检测模式掩码
        public int              abTrafficWaitingArea;                 // 是否携带交通违章驶入待行区信息
        public int              nTrafficWaitingArea;                  // 交通违章驶入待行区检测模式掩码
        public int              abTrafficUnderSpeed;                  // 是否携带交通欠速信息
        public int              nTrafficUnderSpeed;                   // 交通欠速检测模式掩码
        public int              abTrafficOverSpeed;                   // 是否携带交通超速信息
        public int              nTrafficOverSpeed;                    // 交通超速检测模式掩码
        public int              abTrafficWrongRoute;                  // 是否携带交通不按车道行驶信息
        public int              nTrafficWrongRoute;                   // 交通不按车道行驶检测模式掩码
        public int              abTrafficYellowInRoute;               // 是否携带交通黄牌占道信息
        public int              nTrafficYellowInRoute;                // 交通黄牌占道检测模式掩码
        public int              abTrafficVehicleInRoute;              // 是否携带交通有车占道信息
        public int              nTrafficVehicleInRoute;               // 交通有车占道检测模式掩码
        public int              abTrafficControl;                     // 是否携带交通交通管理信息
        public int              nTrafficControl;                      // 交通交通管理检测模式掩码
        public int              abTrafficObjectAlarm;                 // 是否携带交通指定类型抓拍信息
        public int              nTrafficObjectAlarm;                  // 交通指定类型抓拍检测模式掩码
        public int              abTrafficAccident;                    // 是否携带交通交通事故信息
        public int              nTrafficAccident;                     // 交通交通事故检测模式掩码
        public int              abTrafficStay;                        // 是否携带交通交通停留/滞留信息
        public int              nTrafficStay;                         // 交通交通停留/滞留检测模式掩码
        public int              abTrafficPedestrainPriority;          // 是否携带交通斑马线行人优先信息
        public int              nTrafficPedestrainPriority;           // 交通斑马线行人优先检测模式掩码
        public int              abTrafficPedestrain;                  // 是否携带交通交通行人事件信息
        public int              nTrafficPedestrain;                   // 交通交通行人事件检测模式掩码
        public int              abTrafficThrow;                       // 是否携带交通交通抛洒物品事件信息
        public int              nTrafficThrow;                        // 交通交通抛洒物品事件检测模式掩码
        public int              abTrafficVehicleInBusRoute;           // 是否携带交通违章占道信息
        public int              nTrafficVehicleInBusRoute;            // 交通违章占道检测模式掩码
        public int              abTrafficBacking;                     // 是否携带交通违章倒车信息
        public int              nTrafficBacking;                      // 交通违章倒车检测模式掩码
        public int              abTrafficOverStopLine;                // 是否携带交通压停止线信息
        public int              nTrafficOverStopLine;                 // 交通压停止线检测模式掩码
        public int              abTrafficParkingOnYellowBox;          // 是否携带交通黄网格线抓拍信息
        public int              nTrafficParkingOnYellowBox;           // 交通黄网格线抓拍检测模式掩码
        public int              abTrafficParkingSpaceParking;         // 是否携带交通车位有车信息
        public int              nTrafficParkingSpaceParking;          // 交通车位有车检测模式掩码
        public int              abTrafficParkingSpaceNoParking;       // 是否携带交通车位无车信息
        public int              nTrafficParkingSpaceNoParking;        // 交通车位无车检测模式掩码
        public int              abTrafficParkingSpaceOverLine;        // 是否携带交通车位有车压线信息
        public int              nTrafficParkingSpaceOverLine;         // 交通车位有车压线检测模式掩码
        public int              abParkingSpaceDetection;              // 是否携带交通多停车位状态检测信息
        public int              nParkingSpaceDetection;               // 交通多停车位状态检测检测模式掩码
        public int              abTrafficRestrictedPlate;             // 是否携带交通受限车牌信息
        public int              nTrafficRestrictedPlate;              // 交通受限车牌检测模式掩码
        public int              abTrafficWithoutSafeBelt;             // 是否携带交通不系安全带信息
        public int              nTrafficWithoutSafeBelt;              // 交通不系安全带检测模式掩码
        public int              abTrafficNoPassing;                   // 是否携带交通禁行信息
        public int              nTrafficNoPassing;                    // 交通禁行检测模式掩码
        public int              abVehicleAnalyse;                     // 是否携带交通车辆特征检测分析信息
        public int              nVehicleAnalyse;                      // 交通车辆特征检测模式掩码
        public int              abCrossLineDetection;                 // 是否携带交通警戒线信息
        public int              nCrossLineDetection;                  // 交通警戒线检测模式掩码
        public int              abCrossFenceDetection;                // 是否携带交通穿越围栏信息
        public int              nCrossFenceDetection;                 // 交通穿越围栏检测模式掩码
        public int              abCrossRegionDetection;               // 是否携带交通警戒区信息
        public int              nCrossRegionDetection;                // 交通警戒区检测模式掩码
        public int              abPasteDetection;                     // 是否携带交通ATM贴条信息
        public int              nPasteDetection;                      // 交通ATM贴条检测模式掩码
        public int              abLeftDetection;                      // 是否携带交通物品遗留信息
        public int              nLeftDetection;                       // 交通物品遗留检测模式掩码
        public int              abPreservation;                       // 是否携带交通物品保全信息
        public int              nPreservation;                        // 交通物品保全检测模式掩码
        public int              abTakenAwayDetection;                 // 是否携带交通物品搬移信息
        public int              nTakenAwayDetection;                  // 交通物品搬移检测模式掩码
        public int              abStayDetection;                      // 是否携带交通停留/滞留信息
        public int              nStayDetection;                       // 交通停留/滞留检测模式掩码
        public int              abParkingDetection;                   // 是否携带交通非法停车信息
        public int              nParkingDetection;                    // 交通非法停车检测模式掩码
        public int              abWanderDetection;                    // 是否携带交通徘徊信息
        public int              nWanderDetection;                     // 交通徘徊检测模式掩码
        public int              abMoveDetection;                      // 是否携带交通运动信息
        public int              nMoveDetection;                       // 交通运动检测模式掩码
        public int              abTailDetection;                      // 是否携带交通尾随信息
        public int              nTailDetection;                       // 交通尾随检测模式掩码
        public int              abRioterDetection;                    // 是否携带交通聚集信息
        public int              nRioterDetection;                     // 交通聚集检测模式掩码
        public int              abFightDetection;                     // 是否携带交通打架信息
        public int              nFightDetection;                      // 交通打架检测模式掩码
        public int              abRetrogradeDetection;                // 是否携带交通逆行信息
        public int              nRetrogradeDetection;                 // 交通逆行检测模式掩码
        public int              abFireDetection;                      // 是否携带交通火焰信息
        public int              nFireDetection;                       // 交通火焰检测模式掩码
        public int              abSmokeDetection;                     // 是否携带交通烟雾信息
        public int              nSmokeDetection;                      // 交通烟雾检测模式掩码
        public int              abNumberStat;                         // 是否携带交通数量统计信息
        public int              nNumberStat;                          // 交通数量统计检测模式掩码
        public int              abVideoAbnormalDetection;             // 是否携带交通视频异常信息
        public int              nVideoAbnormalDetection;              // 交通视频异常检测模式掩码
        public int              abPSRiseDetection;                    // 是否携带囚犯起身检测信息
        public int              nPSRiseDetection;                     // 囚犯起身检测检测模式掩码
        public int              abFaceDetection;                      // 是否携带目标检测信息
        public int              nFaceDetection;                       // 目标检测检测模式掩码
        public int              abFaceRecognition;                    // 是否携带目标识别信息
        public int              nFaceRecognition;                     // 目标识别检测模式掩码
        public int              abDensityDetection;                   // 是否携带密集度检测信息
        public int              nDensityDetection;                    // 密集度检测检测模式掩码
        public int              abQueueDetection;                     // 是否携带排队检测信息
        public int              nQueueDetection;                      // 排队检测检测模式掩码
        public int              abClimbDetection;                     // 是否携带攀高检测信息
        public int              nClimbDetection;                      // 攀高检测模式掩码
        public int              abLeaveDetection;                     // 是否携带离岗检测信息
        public int              nLeaveDetection;                      // 离岗检测模式掩码
        public int              abVehicleOnPoliceCar;                 // 是否携带车载警车信息
        public int              nVehicleOnPoliceCar;                  // 车载警车检测模式掩码
        public int              abVehicleOnBus;                       // 是否携带车载公交信息
        public int              nVehicleOnBus;                        // 车载公交检测模式掩码
        public int              abVehicleOnSchoolBus;                 // 是否携带车载校车信息
        public int              nVehicleOnSchoolBus;                  // 车载校车检测模式掩码
    }

    // 交通全局配置对应灯组状态配置
    public static class ENABLE_LIGHT_STATE_INFO extends SdkStructure {
        public int              bEnable;                              // 是否启动应用层收到的灯组状态给底层
    }

    // 车道检测类型
    public static class EM_CHECK_TYPE extends SdkStructure {
        public static final int   EM_CHECK_TYPE_UNKNOWN = 0;            // 不识别的检测类型
        public static final int   EM_CHECK_TYPE_PHYSICAL = 1;           // 物理检测
        public static final int   EM_CHECK_TYPE_VIDEO = 2;              // 视频检测
    }

    // TRAFFIC_EVENT_CHECK_INFO
    public static class TRAFFIC_EVENT_CHECK_INFO extends SdkStructure {
        public int              abTrafficGate;                        // 是否携带交通卡口信息
        public int              emTrafficGate;                        // 交通卡口检测类型 EM_CHECK_TYPE
        public int              abTrafficJunction;                    // 是否携带交通路口信息
        public int              emTrafficJunction;                    // 交通路口检测类型
        public int              abTrafficTollGate;                    // 是否携带新交通卡口信息
        public int              emTrafficTollGate;                    // 新交通卡口检测类型
        public int              abTrafficRunRedLight;                 // 是否携带交通闯红灯信息
        public int              emTrafficRunRedLight;                 // 交通闯红灯检测类型
        public int              abTrafficRunYellowLight;              // 是否携带交通闯黄灯信息
        public int              emTrafficRunYellowLight;              // 交通闯黄灯检测类型
        public int              abTrafficOverLine;                    // 是否携带交通压线信息
        public int              emTrafficOverLine;                    // 交通压线检测类型
        public int              abTrafficOverYellowLine;              // 是否携带交通压黄线信息
        public int              emTrafficOverYellowLine;              // 交通压黄线检测类型
        public int              abTrafficRetrograde;                  // 是否携带交通逆行信息
        public int              emTrafficRetrograde;                  // 交通逆行检测类型
        public int              abTrafficTurnLeft;                    // 是否携带交通违章左转信息
        public int              emTrafficTurnLeft;                    // 交通违章左转检测类型
        public int              abTrafficTurnRight;                   // 是否携带交通违章右转信息
        public int              emTrafficTurnRight;                   // 交通路口违章右转类型
        public int              abTrafficU_Turn;                      // 是否携带交通违章掉头信息
        public int              emTrafficU_Turn;                      // 交通违章掉头检测类型
        public int              abTrafficCrossLane;                   // 是否携带交通违章变道信息
        public int              emTrafficCrossLane;                   // 交通违章变道检测类型
        public int              abTrafficParking;                     // 是否携带交通违章停车信息
        public int              emTrafficParking;                     // 交通违章停车检测类型
        public int              abTrafficJam;                         // 是否携带交通拥堵信息
        public int              emTrafficJam;                         // 交通拥堵检测类型
        public int              abTrafficIdle;                        // 是否携带交通交通空闲信息
        public int              emTrafficIdle;                        // 交通交通空闲检测类型
        public int              abTrafficWaitingArea;                 // 是否携带交通违章驶入待行区信息
        public int              emTrafficWaitingArea;                 // 交通违章驶入待行区检测类型
        public int              abTrafficUnderSpeed;                  // 是否携带交通欠速信息
        public int              emTrafficUnderSpeed;                  // 交通欠速检测类型
        public int              abTrafficOverSpeed;                   // 是否携带交通超速信息
        public int              emTrafficOverSpeed;                   // 交通超速检测类型
        public int              abTrafficWrongRoute;                  // 是否携带交通不按车道行驶信息
        public int              emTrafficWrongRoute;                  // 交通不按车道行驶检测类型
        public int              abTrafficYellowInRoute;               // 是否携带交通黄牌占道信息
        public int              emTrafficYellowInRoute;               // 交通黄牌占道检测类型
        public int              abTrafficVehicleInRoute;              // 是否携带交通有车占道信息
        public int              emTrafficVehicleInRoute;              // 交通有车占道检测类型
        public int              abTrafficControl;                     // 是否携带交通交通管理信息
        public int              emTrafficControl;                     // 交通交通管理检测类型
        public int              abTrafficObjectAlarm;                 // 是否携带交通指定类型抓拍信息
        public int              emTrafficObjectAlarm;                 // 交通指定类型抓拍检测类型
        public int              abTrafficAccident;                    // 是否携带交通交通事故信息
        public int              emTrafficAccident;                    // 交通交通事故检测类型
        public int              abTrafficStay;                        // 是否携带交通交通停留/滞留信息
        public int              emTrafficStay;                        // 交通交通停留/滞留检测类型
        public int              abTrafficPedestrainPriority;          // 是否携带交通斑马线行人优先信息
        public int              emTrafficPedestrainPriority;          // 交通斑马线行人优先检测类型
        public int              abTrafficPedestrain;                  // 是否携带交通交通行人事件信息
        public int              emTrafficPedestrain;                  // 交通交通行人事件检测类型
        public int              abTrafficThrow;                       // 是否携带交通交通抛洒物品事件信息
        public int              emTrafficThrow;                       // 交通交通抛洒物品事件检测类型
        public int              abTrafficVehicleInBusRoute;           // 是否携带交通违章占道信息
        public int              emTrafficVehicleInBusRoute;           // 交通违章占道检测类型
        public int              abTrafficBacking;                     // 是否携带交通违章倒车信息
        public int              emTrafficBacking;                     // 交通违章倒车检测类型
        public int              abTrafficOverStopLine;                // 是否携带交通压停止线信息
        public int              emTrafficOverStopLine;                // 交通压停止线检测类型
        public int              abTrafficParkingOnYellowBox;          // 是否携带交通黄网格线抓拍信息
        public int              emTrafficParkingOnYellowBox;          // 交通黄网格线抓拍检测类型
        public int              abTrafficParkingSpaceParking;         // 是否携带交通车位有车信息
        public int              emTrafficParkingSpaceParking;         // 交通车位有车检测类型
        public int              abTrafficParkingSpaceNoParking;       // 是否携带交通车位无车信息
        public int              emTrafficParkingSpaceNoParking;       // 交通车位无车检测类型
        public int              abTrafficParkingSpaceOverLine;        // 是否携带交通车位有车压线信息
        public int              emTrafficParkingSpaceOverLine;        // 交通车位有车压线检测类型
        public int              abParkingSpaceDetection;              // 是否携带交通多停车位状态检测信息
        public int              emParkingSpaceDetection;              // 交通多停车位状态检测检测类型
        public int              abTrafficRestrictedPlate;             // 是否携带交通受限车牌信息
        public int              emTrafficRestrictedPlate;             // 交通受限车牌检测类型
        public int              abTrafficWithoutSafeBelt;             // 是否携带交通不系安全带信息
        public int              emTrafficWithoutSafeBelt;             // 交通不系安全带检测类型
        public int              abTrafficNoPassing;                   // 是否携带交通禁行信息
        public int              emTrafficNoPassing;                   // 交通禁行检测类型
        public int              abVehicleAnalyse;                     // 是否携带交通车辆特征检测分析信息
        public int              emVehicleAnalyse;                     // 交通车辆特征检测类型
        public int              abCrossLineDetection;                 // 是否携带交通警戒线信息
        public int              emCrossLineDetection;                 // 交通警戒线检测类型
        public int              abCrossFenceDetection;                // 是否携带交通穿越围栏信息
        public int              emCrossFenceDetection;                // 交通穿越围栏检测类型
        public int              abCrossRegionDetection;               // 是否携带交通警戒区信息
        public int              emCrossRegionDetection;               // 交通警戒区检测类型
        public int              abPasteDetection;                     // 是否携带交通ATM贴条信息
        public int              emPasteDetection;                     // 交通ATM贴条检测类型
        public int              abLeftDetection;                      // 是否携带交通物品遗留信息
        public int              emLeftDetection;                      // 交通物品遗留检测类型
        public int              abPreservation;                       // 是否携带交通物品保全信息
        public int              emPreservation;                       // 交通物品保全检测类型
        public int              abTakenAwayDetection;                 // 是否携带交通物品搬移信息
        public int              emTakenAwayDetection;                 // 交通物品搬移检测类型
        public int              abStayDetection;                      // 是否携带交通停留/滞留信息
        public int              emStayDetection;                      // 交通停留/滞留检测类型
        public int              abParkingDetection;                   // 是否携带交通非法停车信息
        public int              emParkingDetection;                   // 交通非法停车检测类型
        public int              abWanderDetection;                    // 是否携带交通徘徊信息
        public int              emWanderDetection;                    // 交通徘徊检测类型
        public int              abMoveDetection;                      // 是否携带交通运动信息
        public int              emMoveDetection;                      // 交通运动检测类型
        public int              abTailDetection;                      // 是否携带交通尾随信息
        public int              emTailDetection;                      // 交通尾随检测类型
        public int              abRioterDetection;                    // 是否携带交通聚集信息
        public int              emRioterDetection;                    // 交通聚集检测类型
        public int              abFightDetection;                     // 是否携带交通打架信息
        public int              emFightDetection;                     // 交通打架检测类型
        public int              abRetrogradeDetection;                // 是否携带交通逆行信息
        public int              emRetrogradeDetection;                // 交通逆行检测类型
        public int              abFireDetection;                      // 是否携带交通火焰信息
        public int              emFireDetection;                      // 交通火焰检测类型
        public int              abSmokeDetection;                     // 是否携带交通烟雾信息
        public int              emSmokeDetection;                     // 交通烟雾检测类型
        public int              abNumberStat;                         // 是否携带交通数量统计信息
        public int              emNumberStat;                         // 交通数量统计检测类型
        public int              abVideoAbnormalDetection;             // 是否携带交通视频异常信息
        public int              emVideoAbnormalDetection;             // 交通视频异常检测类型
        public int              abPSRiseDetection;                    // 是否携带囚犯起身检测信息
        public int              emPSRiseDetection;                    // 囚犯起身检测检测类型
        public int              abFaceDetection;                      // 是否携带目标检测信息
        public int              emFaceDetection;                      // 目标检测检测类型
        public int              abFaceRecognition;                    // 是否携带目标识别信息
        public int              emFaceRecognition;                    // 目标识别检测类型
        public int              abDensityDetection;                   // 是否携带密集度检测信息
        public int              emDensityDetection;                   // 密集度检测检测类型
        public int              abQueueDetection;                     // 是否携带排队检测信息
        public int              emQueueDetection;                     // 排队检测检测类型
        public int              abClimbDetection;                     // 是否携带攀高检测信息
        public int              emClimbDetection;                     // 攀高检测类型
        public int              abLeaveDetection;                     // 是否携带离岗检测信息
        public int              emLeaveDetection;                     // 离岗检测类型
        public int              abVehicleOnPoliceCar;                 // 是否携带车载警车信息
        public int              emVehicleOnPoliceCar;                 // 车载警车检测类型
        public int              abVehicleOnBus;                       // 是否携带车载公交信息
        public int              emVehicleOnBus;                       // 车载公交检测类型
        public int              abVehicleOnSchoolBus;                 // 是否携带车载校车信息
        public int              emVehicleOnSchoolBus;                 // 车载校车检测类型
        public int              abStandUpDetection;                   // 是否携带学生起立信息
        public int              emStandUpDetection;                   // 学生起立检测类型
    }

    // MixModeConfig中关于车道配置信息
    public static class MIX_MODE_LANE_INFO extends SdkStructure {
        public  int             nLaneNum;                             // 车道配置个数
        public  TRAFFIC_EVENT_CHECK_INFO[] stCheckInfo = (TRAFFIC_EVENT_CHECK_INFO[]) new TRAFFIC_EVENT_CHECK_INFO().toArray(MAX_LANE_CONFIG_NUMBER); // 车道配置对应事件检测信息
    }

    // MixModeConfig 混合模式违章配置
    public static class MIX_MODE_CONFIG extends SdkStructure {
        public int              bLaneDiffEnable;                      // 是否按车道区分
        public MIX_MODE_LANE_INFO stLaneInfo;
        public TRAFFIC_EVENT_CHECK_INFO stCheckInfo;
    }

    // CFG_CMD_TRAFFICGLOBAL 交通全局配置配置表
    public static class CFG_TRAFFICGLOBAL_INFO extends SdkStructure
    {
        public VIOLATIONCODE_INFO stViolationCode;                    // 违章代码配置表
        public int              bEnableRedList;                       // 使能红名单检测，使能后，名单内车辆违章不上报
        public int              abViolationTimeSchedule;              // 是否携带违章抓拍自定义时间配置
        public VIOLATION_TIME_SCHEDULE stViolationTimeSchedule;       // 违章抓拍自定义时间配置
        public int              abEnableBlackList;                    // 是否携带使能禁止名单检测信息
        public int              bEnableBlackList;                     // 使能禁止名单检测
        public int              abPriority;                           // 是否携带违章优先级参数
        public int              nPriority;                            // 违章优先级个数
        public byte[]           szPriority = new byte[MAX_PRIORITY_NUMBER*CFG_COMMON_STRING_256]; // 违章优先级, 0为最高优先级
        public int              abNamingFormat;                       // 是否携带图片命名格式参数
        public TRAFFIC_NAMING_FORMAT stNamingFormat;                  // 图片命名格式参数配置
        public int              abVideoNamingFormat;                  // 是否携带录像命名格式参数
        public TRAFFIC_NAMING_FORMAT stVideoNamingFormat;             // 录像命名格式参数配置
        public int              abCalibration;                        // 是否携带标定信息
        public TRAFFIC_CALIBRATION_INFO stCalibration;                // 标定信息
        public int              abAddress;                            // 是否携带查询地址参数
        public byte[]           szAddress = new byte[CFG_COMMON_STRING_256]; // 查询地址，UTF-8编码
        public int              abTransferPolicy;                     // 是否携带传输策略参数
        public int              emTransferPolicy;                     // 传输策略, EM_TRANSFER_POLICY
        public int              abSupportModeMaskConfig;              // 是否携带违章掩码
        public TRAFFIC_EVENT_CHECK_MASK stSupportModeMaskConfig;      // 违章类型支持的检测模式掩码配置
        public int              abIsEnableLightState;                 // 是否携带灯组状态
        public ENABLE_LIGHT_STATE_INFO stIsEnableLightState;          // 交通全局配置对应图片命名格式参数配置
        public int              abMixModeInfo;                        // 是否含有混合模式配置
        public MIX_MODE_CONFIG  stMixModeInfo;                        // 混合模式配置
    }

    // 手动抓拍参数
    public static class MANUAL_SNAP_PARAMETER extends SdkStructure
    {
        public int              nChannel;                             // 抓图通道,从0开始
        public byte[]           bySequence = new byte[64];            // 抓图序列号字符串
        public byte[]           byReserved = new byte[60];            // 保留字段
    }

    // 视频统计小计信息
    public static class NET_VIDEOSTAT_SUBTOTAL extends SdkStructure
    {
        public int              nTotal;                               // 设备运行后人数统计总数
        public int              nHour;                                // 小时内的总人数
        public int              nToday;                               // 当天的总人数
        public int              nOSD;                                 // 统计人数,用于OSD显示, 可手动清除
        public int              nTotalNonMotor;                       // 设备运行后非机动车统计总数,重启后从上次总数开始继续累加
        public int              nHourNonMotor;                        // 小时内的总非机动车数量
        public int              nTodayNonMotor;                       // 当天的总非机动车数(自然天)
        public int              nTotalPig;                            // 设备运行后猪只统计总数,重启后从上次总数开始继续累加
        public int              nHourPig;                             // 小时内的总猪只数量
        public int              nTodayPig;                            // 当天的总猪只数(自然天)
        public int              nTotalPigInTimeSection;               // IPC专用，如果不执行clearSectionStat操作，等同于TodayPig猪只数。
        public byte[]           reserved = new byte[224];
    }

    // 视频统计摘要信息
    public static class NET_VIDEOSTAT_SUMMARY extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szRuleName = new byte[32];            // 规则名称
        public NET_TIME_EX      stuTime;                              // 统计时间
        public NET_VIDEOSTAT_SUBTOTAL stuEnteredSubtotal;             // 进入小计
        public NET_VIDEOSTAT_SUBTOTAL stuExitedSubtotal;              // 出去小计
        public int              nInsidePeopleNum;                     // 区域内人数
        public int              emRuleType;                           // 规则类型, 参考枚举 EM_RULE_TYPE
        public int              nRetExitManNum;                       // 离开的人数个数
        public NET_EXITMAN_STAY_STAT[] stuExitManStayInfo = (NET_EXITMAN_STAY_STAT[])new NET_EXITMAN_STAY_STAT().toArray(MAX_EXIT_MAN_NUM); // 离开人员的滞留时间信息
        public int              nPlanID;                              // 计划ID,仅球机有效,从1开始
        public int              nAreaID;                              // 区域ID(一个预置点可以对应多个区域ID)
        public int              nCurrentDayInsidePeopleNum;           // 当天区域内总人数
        public int              nInsideTotalNonMotor;                 // 区域内非机动车总数
        public int              nInsideTodayNonMotor;                 // 当天的非机动车数
        public int              nRetNonMotorNum;                      // 非机动车的滞留个数
        public NET_NONMOTOR_STAY_STAT[] stuNonMotorStayStat = (NET_NONMOTOR_STAY_STAT[])new NET_NONMOTOR_STAY_STAT().toArray(MAX_EXIT_MAN_NUM); // 非机动车的滞留时间信息
        public int              nInsideTotalPig;                      // 区域内猪只数
        public int              nPigStayStatCount;                    // 猪只离开滞留时间信息个数
        public NET_PIG_STAY_STAT[] stuPigStayStatInfo = new NET_PIG_STAY_STAT[32]; //猪只离开滞留时间信息
        public int              nInsideTodayPig;                      // 当天的猪只数
        public byte[]           szReserved = new byte[4];             //字节对齐
        public NET_PASSED_SUBTOTAL_INFO stuPassedSubtotal = new NET_PASSED_SUBTOTAL_INFO(); // 经过小计
        public NET_ENTERED_NO_DUP_SUBTOTAL_INFO stuEnteredNoDupSubtotal = new NET_ENTERED_NO_DUP_SUBTOTAL_INFO(); //去重后的进入人数小计,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ENTERED_NO_DUP_SUBTOTAL_INFO}
        public NET_EXITED_NO_DUP_SUBTOTAL_INFO stuExitedNoDupSubtotal = new NET_EXITED_NO_DUP_SUBTOTAL_INFO(); //去重后的出去人数小计,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EXITED_NO_DUP_SUBTOTAL_INFO}
        public NET_ENTERED_DUP_SUBTOTAL_INFO stuEnteredDupSubtotal = new NET_ENTERED_DUP_SUBTOTAL_INFO(); //进入重复小计,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ENTERED_DUP_SUBTOTAL_INFO}
        public NET_EXITED_DUP_SUBTOTAL_INFO stuExitedDupSubtotal = new NET_EXITED_DUP_SUBTOTAL_INFO(); //出去重复小计,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EXITED_DUP_SUBTOTAL_INFO}
        public byte[]           szRuleClass = new byte[32];           //业务大类 ,"CustomerFlow" ：精准客流
        public byte[]           reserved = new byte[500];             //保留字节

    	public NET_VIDEOSTAT_SUMMARY() {
    		for (int i = 0; i < stuExitManStayInfo.length; i++) {
    			stuExitManStayInfo[i] = new NET_EXITMAN_STAY_STAT();
    		}
    		for (int i = 0; i < stuNonMotorStayStat.length; i++) {
    			stuNonMotorStayStat[i] = new NET_NONMOTOR_STAY_STAT();
    		}
    		for (int i = 0; i < stuPigStayStatInfo.length; i++) {
    			stuPigStayStatInfo[i] = new NET_PIG_STAY_STAT();
    		}
    	}
    }

    // 离开人员的滞留时间信息
    public static class NET_EXITMAN_STAY_STAT extends SdkStructure
    {
        public NET_TIME         stuEnterTime;                         // 人员进入区域时间
        public NET_TIME         stuExitTime;                          // 人员离开区域时间
        public int              nManID;                               //离开区域人员ID
        public byte[]           reserved = new byte[124];             // 保留字节
    }

    // CLIENT_AttachVideoStatSummary 入参
    public static class NET_IN_ATTACH_VIDEOSTAT_SUM extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 视频通道号
        public Callback         cbVideoStatSum;                       // 视频统计摘要信息回调, fVideoStatSumCallBack 回调
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_ATTACH_VIDEOSTAT_SUM()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachVideoStatSummary 出参
    public static class NET_OUT_ATTACH_VIDEOSTAT_SUM extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTACH_VIDEOSTAT_SUM()
        {
            this.dwSize = this.size();
        }
    }

    public static class EM_OTHER_RULE_TYPE extends SdkStructure
    {
        public static final int   EM_OTHER_RULE_TYPE_UNKOWN = 0;        // 未知
        public static final int   EM_OTHER_RULE_TYPE_AVERAGE_STAYTIME = 1; // 平均滞留时间
    }

    // 接口(CLIENT_StartFindNumberStat)输入参数
    public static class NET_IN_FINDNUMBERSTAT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nChannelID;                           // 要进行查询的通道号
        public NET_TIME         stStartTime;                          // 开始时间 暂时精确到小时
        public NET_TIME         stEndTime;                            // 结束时间 暂时精确到小时
        public int              nGranularityType;                     // 查询粒度0:分钟,1:小时,2:日,3:周,4:月,5:季,6:年
        // 7:即时, 8:人员 (7和8只在emRuleType为EM_RULE_MAN_NUM_DETECTION时有效)
        public int              nWaittime;                            // 等待接收数据的超时时间
        public int              nPlanID;                              // 计划ID,仅球机有效,从1开始
        public int              emRuleType;                           // 规则类型
        public int              nMinStayTime;                         // 参考枚举 EM_RULE_TYPE, 区域人数查询最小滞留时间，不填默认为0，返回滞留时长大于等于该时间的人数信息
        // NumberStat时不需要此参数
        public int              nAreaIDNum;                           // 区域ID个数
        public int[]            nAreaID = new int[20];                // 区域ID(一个预置点可以对应多个区域ID)
        public int              emOtherRule;                          // 其他规则, 参考 EM_OTHER_RULE_TYPE
        public int              nGranularityExt;                      // 当查询粒度为分钟时，用以细化具体粒度 不填默认5分钟粒度
        public int[]            nClusterAreaID = new int[1024];       //多通道融合区域ID
        public int              nClusterAreaIDNum;                    //多通道融合区域ID有效个数
        public byte[]           szRuleClass = new byte[32];           //规则大类

        public NET_IN_FINDNUMBERSTAT() {
            this.dwSize = this.size();
        }
    }

    // 规则类型
    public static class EM_RULE_TYPE extends SdkStructure
    {
        public static final int   EM_RULE_UNKNOWN = 0;                  // 未知
        public static final int   EM_RULE_NUMBER_STAT = 1;              // 人数统计
        public static final int   EM_RULE_MAN_NUM_DETECTION = 2;        // 区域内人数统计
    }

    // 接口(CLIENT_StartFindNumberStat)输出参数
    public static class NET_OUT_FINDNUMBERSTAT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              dwTotalCount;                         // 符合此次查询条件的结果总条数

        public NET_OUT_FINDNUMBERSTAT() {
            this.dwSize = this.size();
        }
    }

    // 接口(CLIENT_DoFindNumberStat)输入参数
    public static class NET_IN_DOFINDNUMBERSTAT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nBeginNumber;                         // [0, totalCount-1], 查询起始序号,表示从beginNumber条记录开始,取count条记录返回;
        public int              nCount;                               // 每次查询的流量统计条数
        public int              nWaittime;                            // 等待接收数据的超时时间

        public NET_IN_DOFINDNUMBERSTAT() {
            this.dwSize = this.size();
        }
    }

    public static class NET_NUMBERSTAT extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 统计通道号
        public byte[]           szRuleName = new byte[NET_CHAN_NAME_LEN]; // 规则名称
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nEnteredSubTotal;                     // 进入人数小计
        public int              nExitedSubtotal;                      // 出去人数小计
        public int              nAvgInside;                           // 平均保有人数(除去零值)
        public int              nMaxInside;                           // 最大保有人数
        public int              nEnteredWithHelmet;                   // 戴安全帽进入人数小计
        public int              nEnteredWithoutHelmet;                // 不戴安全帽进入人数小计
        public int              nExitedWithHelmet;                    // 戴安全帽出去人数小计
        public int              nExitedWithoutHelmet;                 // 不戴安全帽出去人数小计
        public int              nInsideSubtotal;                      // 在区域内人数小计
        public int              nPlanID;                              // 计划ID,仅球机有效,从1开始
        public int              nAreaID;                              // 区域ID(一个预置点可以对应多个区域ID)
        public int              nAverageStayTime;                     // 区域内平均滞留时间
        public NET_TEMPERATURE_STATISTICS_INFO stuTempInfo;           // 温度统计信息(NET_IN_FINDNUMBERSTAT 字段 emRuleType 取值为 EM_RULE_ANATOMYTEMP_DETECT 时有效)
        public int              nPassedSubtotal;                      // 经过人数小计
        public int              nEnteredDupSubtotal;                  // 进入重复人数小计
        public int              nExitedDupSubtotal;                   // 出去重复人数小计
        public int              nEnteredNoDupSubtotal;                // 去重后的进入人数小计
        public int              nExitedNoDupSubtotal;                 // 去重后的出去人数小计
        public int              nClusterAreaID;                       // 多通道融合区域ID
        public int              nBatchTotal;                          // 批次数小计
        public int              nRealNumber;                          //精准客流使用，表示校正后的人数
        public int              nEnteredSpecSubtotal;                 //特定人员进入人数小计
        public int              nExitedSpecSubtotal;                  //特定人员出去人数小计
        public int              nEnteredUmbrellaSubtotal;             //进入雨伞小计
        public int              nExitedUmbrellaSubtotal;              //离开雨伞小计
        public int              nPowerOff;                            //是否是断电期间的数据。0：未知， 1：正常运行时统计的数据，2：断电期间数据

        public NET_NUMBERSTAT() {
            this.dwSize = this.size();
        }
    }

    // 接口(CLIENT_DoFindNumberStat)输出参数
    public static class NET_OUT_DOFINDNUMBERSTAT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nCount;                               // 查询返人数统计信息个数
        public Pointer          pstuNumberStat;                       // 返人数统计信息数组, NET_NUMBERSTAT类型 ,由用户申请内存，大小为nBufferLen
        public int              nBufferLen;                           // 用户申请的内存大小,以NET_NUMBERSTAT中的dwsize大小为单位
        public int              nMinStayTime;                         // 区域人数查询时指定的最小滞留时间

        public NET_OUT_DOFINDNUMBERSTAT() {
            this.dwSize = this.size();
        }
    }

    public static class CONNECT_STATE extends SdkStructure
    {
        public static final int   CONNECT_STATE_UNCONNECT = 0;
        public static final int   CONNECT_STATE_CONNECTING = 1;
        public static final int   CONNECT_STATE_CONNECTED = 2;
        public static final int   CONNECT_STATE_ERROR = 255;
    }

    // 虚拟摄像头状态查询
    public static class NET_DEV_VIRTUALCAMERA_STATE_INFO extends SdkStructure
    {
        public int              nStructSize;                          //结构体大小
        public int              nChannelID;                           //通道号
        public int              emConnectState;                       //连接状态, 取值范围为CONNECT_STATE中的值
        public int              uiPOEPort;                            //此虚拟摄像头所连接的POE端口号,0表示不是POE连接, 类型为unsigned int
        public byte[]           szDeviceName = new byte[64];          //设备名称
        public byte[]           szDeviceType = new byte[128];         //设备类型
        public byte[]           szSystemType = new byte[128];         //系统版本
        public byte[]           szSerialNo = new byte[NET_SERIALNO_LEN]; //序列号
        public int              nVideoInput;                          //视频输入
        public int              nAudioInput;                          //音频输入
        public int              nAlarmOutput;                         //外部报警

        public NET_DEV_VIRTUALCAMERA_STATE_INFO()
        {
            this.nStructSize = this.size();
        }
    }

    // 录像文件类型
    public static class NET_RECORD_TYPE extends SdkStructure
    {
        public final static int   NET_RECORD_TYPE_ALL = 0;              // 所有录像
        public final static int   NET_RECORD_TYPE_NORMAL = 1;           // 普通录像
        public final static int   NET_RECORD_TYPE_ALARM = 2;            // 外部报警录像
        public final static int   NET_RECORD_TYPE_MOTION = 3;           // 动检报警录像
    }

    // 对讲方式
    public static class EM_USEDEV_MODE extends SdkStructure
    {
        public static final int   NET_TALK_CLIENT_MODE = 0;             // 设置客户端方式进行语音对讲
        public static final int   NET_TALK_SERVER_MODE = 1;             // 设置服务器方式进行语音对讲
        public static final int   NET_TALK_ENCODE_TYPE = 2;             // 设置语音对讲编码格式(对应 NETDEV_TALKDECODE_INFO)
        public static final int   NET_ALARM_LISTEN_MODE = 3;            // 设置报警订阅方式
        public static final int   NET_CONFIG_AUTHORITY_MODE = 4;        // 设置通过权限进行配置管理
        public static final int   NET_TALK_TALK_CHANNEL = 5;            // 设置对讲通道(0~MaxChannel-1)
        public static final int   NET_RECORD_STREAM_TYPE = 6;           // 设置待查询及按时间回放的录像码流类型(0-主辅码流,1-主码流,2-辅码流)
        public static final int   NET_TALK_SPEAK_PARAM = 7;             // 设置语音对讲喊话参数,对应结构体 NET_SPEAK_PARAM
        public static final int   NET_RECORD_TYPE = 8;                  // 设置按时间录像回放及下载的录像文件类型(详见  NET_RECORD_TYPE)
        public static final int   NET_TALK_MODE3 = 9;                   // 设置三代设备的语音对讲参数, 对应结构体 NET_TALK_EX
        public static final int   NET_PLAYBACK_REALTIME_MODE = 10;      // 设置实时回放功能(0-关闭,1开启)
        public static final int   NET_TALK_TRANSFER_MODE = 11;          // 设置语音对讲是否为转发模式, 对应结构体 NET_TALK_TRANSFER_PARAM
        public static final int   NET_TALK_VT_PARAM = 12;               // 设置VT对讲参数, 对应结构体 NET_VT_TALK_PARAM
        public static final int   NET_TARGET_DEV_ID = 13;               // 设置目标设备标示符, 用以查询新系统能力(非0-转发系统能力消息)
    }

    // 语音编码类型
    public static class NET_TALK_CODING_TYPE extends SdkStructure
    {
        public static final int   NET_TALK_DEFAULT = 0;                 // 无头PCM
        public static final int   NET_TALK_PCM = 1;                     // 带头PCM
        public static final int   NET_TALK_G711a = 2;                   // G711a
        public static final int   NET_TALK_AMR = 3;                     // AMR
        public static final int   NET_TALK_G711u = 4;                   // G711u
        public static final int   NET_TALK_G726 = 5;                    // G726
        public static final int   NET_TALK_G723_53 = 6;                 // G723_53
        public static final int   NET_TALK_G723_63 = 7;                 // G723_63
        public static final int   NET_TALK_AAC = 8;                     // AAC
        public static final int   NET_TALK_OGG = 9;                     // OGG
        public static final int   NET_TALK_G729 = 10;                   // G729
        public static final int   NET_TALK_MPEG2 = 11;                  // MPEG2
        public static final int   NET_TALK_MPEG2_Layer2 = 12;           // MPEG2-Layer2
        public static final int   NET_TALK_G722_1 = 13;                 // G.722.1
        public static final int   NET_TALK_ADPCM = 21;                  // ADPCM
        public static final int   NET_TALK_MP3 = 22;                    // MP3
    }

    // 设备支持的语音对讲类型
    public static class NETDEV_TALKFORMAT_LIST extends SdkStructure
    {
        public int              nSupportNum;                          // 个数
        public NETDEV_TALKDECODE_INFO[] type = (NETDEV_TALKDECODE_INFO[])new NETDEV_TALKDECODE_INFO().toArray(64); // 编码类型
        public byte[]           reserved = new byte[64];
    }

    // 语音编码信息
    public static class NETDEV_TALKDECODE_INFO extends SdkStructure
    {
        public int              encodeType;                           // 编码类型, encodeType对应NET_TALK_CODING_TYPE
        public int              nAudioBit;                            // 位数,如8或16等
        public int              dwSampleRate;                         // 采样率,如8000或16000等
        public int              nPacketPeriod;                        // 打包周期, 单位ms, 目前只能是25
        public byte[]           reserved = new byte[60];
    }

    // 语音对讲喊话参数
    public static class NET_SPEAK_PARAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMode;                                // 0：对讲（默认模式）,1：喊话；从喊话切换到对讲要重新设置
        public int              nSpeakerChannel;                      // 扬声器通道号,喊话时有效
        public int              bEnableWait;                          // 开启对讲时是否等待设备的响应,默认不等待.TRUE:等待;FALSE:不等待
        // 超时时间由CLIENT_SetNetworkParam设置,对应NET_PARAM的nWaittime字段
        public int              nTalkDeviceMode;                      //对讲设备类型 0: 默认 ,1: IP speaker
        public int              nIPSpeakerChannelCount;               //IPSpeaker喊话通道数量
        public Pointer          nIPSpeakerChannel;                    //IPSpeaker喊话通道
        public int              bEnableRender;                        //0:默认， 1：非windows的场景下，支持本地录音对讲

        public NET_SPEAK_PARAM()
        {
            this.dwSize = this.size();
        }
    }

    // 是否开启语音对讲的转发模式
    public static class NET_TALK_TRANSFER_PARAM extends SdkStructure
    {
        public int              dwSize;
        public int              bTransfer;                            // 是否开启语音对讲转发模式, TRUE: 开启转发

        public NET_TALK_TRANSFER_PARAM()
        {
            this.dwSize = this.size();
        }
    }

    // 预览类型,对应CLIENT_RealPlayEx接口
    public static class NET_RealPlayType extends SdkStructure
    {
        public static final int   NET_RType_Realplay = 0;               // 实时预览
        public static final int   NET_RType_Multiplay = 1;              // 多画面预览
        public static final int   NET_RType_Realplay_0 = 2;             // 实时预览-主码流 ,等同于NET_RType_Realplay
        public static final int   NET_RType_Realplay_1 = 3;             // 实时预览-从码流1
        public static final int   NET_RType_Realplay_2 = 4;             // 实时预览-从码流2
        public static final int   NET_RType_Realplay_3 = 5;             // 实时预览-从码流3
        public static final int   NET_RType_Multiplay_1 = 6;            // 多画面预览－1画面
        public static final int   NET_RType_Multiplay_4 = 7;            // 多画面预览－4画面
        public static final int   NET_RType_Multiplay_8 = 8;            // 多画面预览－8画面
        public static final int   NET_RType_Multiplay_9 = 9;            // 多画面预览－9画面
        public static final int   NET_RType_Multiplay_16 = 10;          // 多画面预览－16画面
        public static final int   NET_RType_Multiplay_6 = 11;           // 多画面预览－6画面
        public static final int   NET_RType_Multiplay_12 = 12;          // 多画面预览－12画面
        public static final int   NET_RType_Multiplay_25 = 13;          // 多画面预览－25画面
        public static final int   NET_RType_Multiplay_36 = 14;          // 多画面预览－36画面
        public static final int   NET_RType_Multiplay_64 = 15;          // 多画面预览－64画面
        public static final int   NET_RType_Multiplay_255 = 16;         // 不修改当前预览通道数
        public static final int   NET_RType_Realplay_Audio = 17;        // 只拉音频, 非通用
        public static final int   NET_RType_Realplay_Test = 255;        // 带宽测试码流
    }

    // 实时预览的实时数据标志, 对应 CLIENT_SetRealDataCallBackEx(Ex2) 中的 dwFlag 参数
    // 支持 '|' 运算符, 如 dwFlag = REALDATA_FLAG_RAW_DATA | REALDATA_FLAG_YUV_DATA
    public static class EM_REALDATA_FLAG extends SdkStructure
    {
        public static final int   REALDATA_FLAG_RAW_DATA = 0x01;        // 原始数据标志,			对应fRealDataCallBack(Ex/Ex2)回调函数中 dwDataType 为0, 0x01 = 0x01 << 0
        public static final int   REALDATA_FLAG_DATA_WITH_FRAME_INFO = 0x02; // 带有帧信息的数据标志,		对应fRealDataCallBack(Ex/Ex2)回调函数中 dwDataType 为1, 0x02 = 0x01 << 1
        public static final int   REALDATA_FLAG_YUV_DATA = 0x04;        // YUV 数据标志,			对应fRealDataCallBack(Ex/Ex2)回调函数中 dwDataType 为2, 0x04 = 0x01 << 2
        public static final int   REALDATA_FLAG_PCM_AUDIO_DATA = 0x08;  // PCM 音频数据标志,		对应fRealDataCallBack(Ex/Ex2)回调函数中 dwDataType 为3, 0x08 = 0x01 << 3
    }

    // 回调视频数据帧的帧参数结构体
    public static class tagVideoFrameParam extends SdkStructure
    {
        public byte             encode;                               // 编码类型
        public byte             frametype;                            // I = 0, P = 1, B = 2...
        public byte             format;                               // PAL - 0, NTSC - 1
        public byte             size;                                 // CIF - 0, HD1 - 1, 2CIF - 2, D1 - 3, VGA - 4, QCIF - 5, QVGA - 6 ,
        // SVCD - 7,QQVGA - 8, SVGA - 9, XVGA - 10,WXGA - 11,SXGA - 12,WSXGA - 13,UXGA - 14,WUXGA - 15, LFT - 16, 720 - 17, 1080 - 18 ,1_3M-19
        // 2M-20, 5M-21;当size=255时，成员变量width,height 有效
        public int              fourcc;                               // 如果是H264编码则总为0，否则值为*( DWORD*)"DIVX"，即0x58564944
        public short            width;                                // 宽，单位是像素，当size=255时有效
        public short            height;                               // 高，单位是像素，当size=255时有效
        public NET_TIME         struTime;                             // 时间信息
        public int              sequence;                             //帧序号
        public int              millisecond;                          //绝对时间毫秒
    }

    // 回调音频数据帧的帧参数结构体
    public static class tagCBPCMDataParam extends SdkStructure
    {
        public byte             channels;                             // 声道数
        public byte             samples;                              // 采样 0 - 8000, 1 - 11025, 2 - 16000, 3 - 22050, 4 - 32000, 5 - 44100, 6 - 48000
        public byte             depth;                                // 采样深度 取值8或者16等。直接表示
        public byte             param1;                               // 0 - 指示无符号,1-指示有符号
        public int              reserved;                             // 保留
    }

    // 视频预览断开事件类型
    public static class EM_REALPLAY_DISCONNECT_EVENT_TYPE extends SdkStructure
    {
        public static final int   DISCONNECT_EVENT_REAVE = 0;           // 表示高级用户抢占低级用户资源
        public static final int   DISCONNECT_EVENT_NETFORBID = 1;       // 禁止入网
        public static final int   DISCONNECT_EVENT_SUBCONNECT = 2;      // 动态子链接断开
    }

    // 电池电压过低报警
    public static class ALARM_BATTERYLOWPOWER_INFO extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public int              nAction;                              //0:开始1:停止
        public int              nBatteryLeft;                         //剩余电量百分比,单位%
        public NET_TIME         stTime;                               //事件发生时间
        public int              nChannelID;                           //通道号,标识子设备电池,从0开始
        public NET_GPS_STATUS_INFO stGPSStatus;                       // GPS信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_BATTERYLOWPOWER_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 温度过高报警
    public static class ALARM_TEMPERATURE_INFO extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public byte[]           szSensorName = new byte[NET_MACHINE_NAME_NUM]; //温度传感器名称
        public int              nChannelID;                           //通道号
        public int              nAction;                              //0:开始1:停止
        public float            fTemperature;                         //当前温度值,单位摄氏度
        public NET_TIME         stTime = new NET_TIME();              //事件发生时间
        public NET_GPS_STATUS_INFO stuGPSStatus = new NET_GPS_STATUS_INFO(); // GPS信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public NET_TIME_EX      stuUTC = new NET_TIME_EX();           //事件发生的时间,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}
        public int              nTimeZone;                            //时区索引
        public int              nDSTTune;                             //夏令时标志,1:当前时间属于夏令时时间,0:当前时间不属于夏令时时间,默认值为0

        public ALARM_TEMPERATURE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 普通报警信息
    public static class NET_CLIENT_STATE_EX extends SdkStructure
    {
        public int              channelcount;
        public int              alarminputcount;
        public byte[]           alarm = new byte[32];                 // 外部报警
        public byte[]           motiondection = new byte[32];         // 动态检测
        public byte[]           videolost = new byte[32];             // 视频丢失
        public byte[]           bReserved = new byte[32];
    }

    // 视频遮挡报警状态信息对应结构体
    public static class NET_CLIENT_VIDEOBLIND_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              channelcount;
        public int[]            dwAlarmState = new int[NET_MAX_CHANMASK]; //每一个int按位表示32通道的报警状态,0-表示无报警,1-表示有报警

        public NET_CLIENT_VIDEOBLIND_STATE()
        {
            this.dwSize = this.size();
        }
    }

    // 视频丢失报警状态信息对应结构体
    public static class NET_CLIENT_VIDEOLOST_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              channelcount;
        public int[]            dwAlarmState = new int[NET_MAX_CHANMASK]; //每一个int按位表示32通道的报警状态（只有dwAlarmState[0]有效）,0-表示无报警,1-表示有报警

        public NET_CLIENT_VIDEOLOST_STATE()
        {
            this.dwSize = this.size();
        }
    }

    // 门禁开门 CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_ACCESS_OPEN 命令参数
    public static class NET_CTRL_ACCESS_OPEN extends SdkStructure {
        public int              dwSize;
        public int              nChannelID;                           // 通道号(0开始)
        public Pointer          szTargetID;                           // 转发目标设备ID,为NULL表示不转发
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; //远程用户ID
        public int              emOpenDoorType;                       // 开门方式, 参考 EM_OPEN_DOOR_TYPE
        /**
         * EM_OPEN_DOOR_DIRECTION
         */
        public int              emOpenDoorDirection;                  // 开门方向
        /**
         * EM_REMOTE_CHECK_CODE
         */
        public int              emRemoteCheckCode;                    // 远程权限验证结果
        public byte[]           szShortNumber = new byte[16];         // 兼容字段
        public byte[]           szOperatorID = new byte[36];          //平台下发操作员ID, 仅用于远程开门
        public byte[]           szAlign = new byte[4];                //字节对齐

        public NET_CTRL_ACCESS_OPEN() {
            this.dwSize = this.size();
        }
    }

    // 门禁控制--开门方式
    public static class EM_OPEN_DOOR_TYPE extends SdkStructure
    {
        public static final int   EM_OPEN_DOOR_TYPE_UNKNOWN = 0;
        public static final int   EM_OPEN_DOOR_TYPE_REMOTE = 1;         // 远程开门
        public static final int   EM_OPEN_DOOR_TYPE_LOCAL_PASSWORD = 2; // 本地密码开门
        public static final int   EM_OPEN_DOOR_TYPE_LOCAL_CARD = 3;     // 本地刷卡开门
        public static final int   EM_OPEN_DOOR_TYPE_LOCAL_BUTTON = 4;   // 本地按钮开门
    }

    // 门禁关门CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_ACCESS_CLOSE 命令参数
    public static class NET_CTRL_ACCESS_CLOSE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           //通道号(0开始)

        public NET_CTRL_ACCESS_CLOSE() {
            this.dwSize = this.size();
        }
    }

    // 门禁状态类型
    public static class EM_NET_DOOR_STATUS_TYPE extends SdkStructure
    {
        public static final int   EM_NET_DOOR_STATUS_UNKNOWN = 0;
        public static final int   EM_NET_DOOR_STATUS_OPEN = EM_NET_DOOR_STATUS_UNKNOWN+1; //门打开
        public static final int   EM_NET_DOOR_STATUS_CLOSE = EM_NET_DOOR_STATUS_OPEN+1; //门关闭
        public static final int   EM_NET_DOOR_STATUS_BREAK = EM_NET_DOOR_STATUS_CLOSE+1; //门异常打开
    }

    // 门禁状态信息(CLIENT_QueryDevState 接口输入参数)
    public static class NET_DOOR_STATUS_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //门禁通道号
        public int              emStateType;                          //门禁状态信息, 对应枚举EM_NET_DOOR_STATUS_TYPE

        public NET_DOOR_STATUS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 开启道闸参数(对应 CTRLTYPE_CTRL_OPEN_STROBE 命令)
    public static class NET_CTRL_OPEN_STROBE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelId;                           // 通道号
        public byte[]           szPlateNumber = new byte[64];         // 车牌号码
        public int              emOpenType;                           // 开闸类型,EM_OPEN_STROBE_TYPE
        public int              nLocation;                            // 开闸车道号
        public byte[]           szCustomParkNo = new byte[32];        // 自定义车道号，优先使用自定义车道号，自定义车道号为空，则使用location
        public int              bIsAutoOpen;                          //是否自动放行
        public int              bIsTrustCar;                          //是否（平台）允许车辆
        public int              nEntryOrExitType;                     //进出场类型, 0.未知 1.进场 2.出场
        public byte[]           szAlign = new byte[4];                //字节对齐
        public byte[]           szKey = new byte[32];                 //外设唯一标识

        public NET_CTRL_OPEN_STROBE() {
            this.dwSize = this.size();
        }
    }

    // 刻录控制 (对应 CTRLTYPE_BURNING_START 命令)
    public static class BURNNG_PARM extends SdkStructure
    {
        public int              channelMask;                          // 通道掩码,按位表示要刻录的通道
        public byte             devMask;                              // 刻录机掩码,根据查询到的刻录机列表,按位表示
        public byte             bySpicalChannel;                      // 画中画通道(通道数+31,例如对于4通道设备,该值应为35)
        public byte[]           byReserved = new byte[2];             // 保留字段
    }

    // 关闭道闸参数(对应  CTRLTYPE_CTRL_CLOSE_STROBE 命令)
    public static class NET_CTRL_CLOSE_STROBE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelId;                           // 通道号
        public int              nLocation;                            // 关闸车道号
        public byte[]           szCustomParkNo = new byte[32];        //自定义车道号
        public byte[]           szKey = new byte[32];                 //外设唯一标识

        public NET_CTRL_CLOSE_STROBE() {
            this.dwSize = this.size();
        }
    }

    // 道闸控制方式枚举
    public static class NET_EM_CFG_TRAFFICSTROBE_CTRTYPE extends SdkStructure
    {
        public static final int   NET_EM_CFG_CTRTYPE_UNKNOWN = 0;       // 未定义
        public static final int   NET_EM_CFG_CTRTYPE_TRAFFICTRUSTLIST = 1; // 通过允许名单控制是否开闸；只有允许名单内车辆才开闸
        public static final int   NET_EM_CFG_CTRTYPE_ALLSNAPCAR = 2;    // 针对所有抓拍车辆都开闸
        public static final int   NET_EM_CFG_CTRTYPE_ORDER = 3;         // 通过上层下发的命令开闸
    }

    // 所有车开闸种类
    public static class NET_EM_CFG_ALL_SNAP_CAR extends SdkStructure
    {
        public static final int   NET_EM_CFG_ALL_SNAP_CAR_UNKNOWN = 0;  // 未知开闸种类
        public static final int   NET_EM_CFG_ALL_SNAP_CAR_PLATE = 1;    // 所有有牌车车辆
        public static final int   NET_EM_CFG_ALL_SNAP_CAR_NOPLATE = 2;  // 所有无牌车车辆
    }

    // 道闸常开配置
    public static class NET_CFG_STATIONARY_OPEN extends SdkStructure
    {
        public int              bEnable;                              // 使能
        public CFG_TIME_SCHEDULE stTimeShecule = new CFG_TIME_SCHEDULE(); // 常开模式执行时间段
        public byte[]           szReserved = new byte[512];           //预留字段
    }

    // 道闸配置信息(对应 NET_EM_CFG_TRAFFICSTROBE 命令)
    public static class NET_CFG_TRAFFICSTROBE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bEnable;                              // 使能
        public int              nCtrlTypeCount;                       // 道闸控制方式个数
        public int[]            emCtrlType = new int[NET_CFG_MAX_CTRLTYPE_NUM]; // 道闸控制方式, 详见NET_EM_CFG_TRAFFICSTROBE_CTRTYPE
        public int              nAllSnapCarCount;                     // 所有车开闸种类个数
        public int[]            emAllSnapCar = new int[NET_MAX_ALL_SNAP_CAR_COUNT]; // 所有车开闸种类, 详见NET_EM_CFG_ALL_SNAP_CAR
        public NET_ALARM_MSG_HANDLE stuEventHandler;                  // 开启道闸联动参数
        public NET_ALARM_MSG_HANDLE stuEventHandlerClose;             // 关闭道闸联动参数
        public byte[]           szOrderIP = new byte[NET_MAX_IPADDR_EX_LEN]; // 负责命令开闸的平台IP
        public int              emCtrlTypeOnDisconnect;               // 平台IP与设备断开连接后，设备采用的开闸方式, 详见NET_EM_CFG_TRAFFICSTROBE_CTRTYPE
        public NET_CFG_STATIONARY_OPEN stuStationaryOpen;             // 道闸常开配置
        public NET_CFG_FORBID_OPEN stuForbidOpen = new NET_CFG_FORBID_OPEN(); //道闸禁开配置,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_CFG_FORBID_OPEN}

        public NET_CFG_TRAFFICSTROBE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 报警状态 (对应   CTRLTYPE_TRIGGER_ALARM_OUT 命令)
    public static class ALARMCTRL_PARAM extends SdkStructure
    {
        public int              dwSize;
        public int              nAlarmNo;                             // 报警通道号,从0开始
        public int              nAction;                              // 1：触发报警,0：停止报警

        public ALARMCTRL_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 查询 IVS 前端设备入参
    public static class NET_IN_IVS_REMOTE_DEV_INFO extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public int              nChannel;                             // 通道号

        public NET_IN_IVS_REMOTE_DEV_INFO() {
            this.dwSize = this.size();
        }
    }

    // 查询 IVS 前端设备出参
    public static class NET_OUT_IVS_REMOTE_DEV_INFO extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public int              nPort;                                // 端口
        public byte[]           szIP = new byte[64];                  // 设备IP
        public byte[]           szUser = new byte[64];                // 用户名
        public byte[]           szPassword = new byte[64];            // 密码
        public byte[]           szAddress = new byte[128];            // 机器部署地点

        public NET_OUT_IVS_REMOTE_DEV_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取视频通道属性命令的子类型
    public static class NET_VIDEO_CHANNEL_TYPE extends SdkStructure
    {
        public static final int   NET_VIDEO_CHANNEL_TYPE_ALL = 0;       // 全部
        public static final int   NET_VIDEO_CHANNEL_TYPE_INPUT = 1;     // 输入
        public static final int   NET_VIDEO_CHANNEL_TYPE_OUTPUT = 2;    // 输出
    }

    // CLIENT_QueryDevInfo , NET_QUERY_VIDEOCHANNELSINFO 命令输入参数
    public static class NET_IN_GET_VIDEOCHANNELSINFO extends SdkStructure
    {
        public int              dwSize;
        public int              emType;                               // 需要获取的通道类型,详见NET_VIDEO_CHANNEL_TYPE

        public NET_IN_GET_VIDEOCHANNELSINFO() {
            this.dwSize = this.size();
        }
    }

    public static class NET_VIDEOCHANNELS_INPUT extends SdkStructure
    {
        public int              nThermographyCount;                   // 热成像通道个数
        public int[]            nThermography = new int[64];          // 热成像通道的通道号
        public int              nMultiPreviewCount;                   // 多画面预览通道个数
        public int[]            nMultiPreview = new int[4];           // 多画面预览通道号
        public int              nPIPCount;                            // 画中画通道个数
        public int[]            nPIP = new int[4];                    // 画中画通道号
        public int              nCompressPlayCount;                   // 二次压缩回放通道个数
        public int[]            nCompressPlay = new int[4];           // 二次压缩回放通道号
        public int              nSDCount;                             // 球机通道个数
        public int[]            nSD = new int[64];                    // 球机通道号
        public int              nPTZCount;                            // 支持云台程序的通道数量
        public short[]          nPTZ = new short[64];                 // 支持云台程序的通道号
        public int              nFuseRadarCount;                      // 支持融合雷达流，可见光叠加雷达流的通道数量
        public int[]            nFuseRadar = new int[64];             // 支持融合雷达流，可见光叠加雷达流的通道号
        public int              nPureRadarCount;                      // 支持纯雷达流，无可见光的通道数量
        public int[]            nPureRadar = new int[64];             // 支持纯雷达流，无可见光的通道号
        public byte[]           reserved = new byte[4096];
    }

    public static class NET_VIDEOCHANNELS_OUTPUT extends SdkStructure
    {
        public int              nVGACount;                            // VGA输出个数
        public int[]            nVGA = new int[128];                  // VGA输出
        public int              nTVCount;                             // TV输出个数
        public int[]            nTV = new int[128];                   // TV输出
        public byte[]           reserved = new byte[512];
    }

    //CLIENT_QueryDevInfo , NET_QUERY_VIDEOCHANNELSINFO 命令输出参数
    public static class NET_OUT_GET_VIDEOCHANNELSINFO extends SdkStructure
    {
        public int              dwSize;
        public NET_VIDEOCHANNELS_INPUT stInputChannels;               // 输入通道信息,获取类型为NET_VIDEO_CHANNEL_TYPE_ALL/INPUT时有效
        public NET_VIDEOCHANNELS_OUTPUT stOutputChannels;             // 输出通道信息,获取类型为NET_VIDEO_CHANNEL_TYPE_ALL/OUTPUT时有效
        public NET_VIDEOCHANNELS_INPUT_EX stInputChannelsEx;          // 输入通道扩展信息,获取类型为NET_VIDEO_CHANNEL_TYPE_ALL/INPUT时有效

        public NET_OUT_GET_VIDEOCHANNELSINFO() {
            this.dwSize = this.size();
        }
    }

    // 热成像色彩
    public static class NET_THERMO_COLORIZATION extends SdkStructure
    {
        public static final int   NET_THERMO_COLORIZATION_UNKNOWN = 0;  // 未知
        public static final int   NET_THERMO_COLORIZATION_WHITE_HOT = 1; // 白热
        public static final int   NET_THERMO_COLORIZATION_BLACK_HOT = 2; // 黑热
        public static final int   NET_THERMO_COLORIZATION_IRONBOW2 = 3; // 铁虹2
        public static final int   NET_THERMO_COLORIZATION_ICEFIRE = 4;  // 冰火
        public static final int   NET_THERMO_COLORIZATION_FUSION = 5;   // 融合
        public static final int   NET_THERMO_COLORIZATION_RAINBOW = 6;  // 彩虹
        public static final int   NET_THERMO_COLORIZATION_GLOBOW = 7;   // ..
        public static final int   NET_THERMO_COLORIZATION_IRONBOW1 = 8; // 铁虹1
        public static final int   NET_THERMO_COLORIZATION_SEPIA = 9;    // 深褐
        public static final int   NET_THERMO_COLORIZATION_COLOR1 = 10;  // 彩色1
        public static final int   NET_THERMO_COLORIZATION_COLOR2 = 11;  // 彩色2
        public static final int   NET_THERMO_COLORIZATION_RAIN = 12;    // 雨天
        public static final int   NET_THERMO_COLORIZATION_RED_HOT = 13; // 红热
        public static final int   NET_THERMO_COLORIZATION_GREEN_HOT = 14; // 绿热
    }

    // 热成像感兴趣区域模式
    public static class NET_THERMO_ROI extends SdkStructure
    {
        public static final int   NET_THERMO_ROI_UNKNOWN = 0;           // 未知
        public static final int   NET_THERMO_ROI_FULL_SCREEN = 1;       // 全屏
        public static final int   NET_THERMO_ROI_SKY = 2;               // 顶部
        public static final int   NET_THERMO_ROI_GROUND = 3;            // 中部
        public static final int   NET_THERMO_ROI_HORIZONTAL = 4;        // 底部
        public static final int   NET_THERMO_ROI_CENTER_75 = 5;         // 中心点 75%
        public static final int   NET_THERMO_ROI_CENTER_50 = 6;         // 中心点 50%
        public static final int   NET_THERMO_ROI_CENTER_25 = 7;         // 中心点 25%
        public static final int   NET_THERMO_ROI_CUSTOM = 8;            // 自定义
    }

    // 热成像模式
    public static class NET_THERMO_MODE extends SdkStructure
    {
        public static final int   NET_THERMO_MODE_UNKNOWN = 0;          // 未知
        public static final int   NET_THERMO_MODE_DEFAULT = 1;          // 默认
        public static final int   NET_THERMO_MODE_INDOOR = 2;           // 室内
        public static final int   NET_THERMO_MODE_OUTDOOR = 3;          // 室外
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_PRESET 命令入参
    public static class NET_IN_THERMO_GET_PRESETINFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号
        public int              emMode;                               // 模式, 参考NET_THERMO_MODE

        public NET_IN_THERMO_GET_PRESETINFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_PRESET 命令出参
    public static class NET_OUT_THERMO_GET_PRESETINFO extends SdkStructure
    {
        public int              dwSize;
        public NET_THERMO_GRAPHY_INFO stInfo;                         // 热成像信息

        public NET_OUT_THERMO_GET_PRESETINFO() {
            this.dwSize = this.size();
        }
    }

    // 热成像优化区域
    public static class NET_THERMO_GRAPHY_OPTREGION extends SdkStructure
    {
        public int              bOptimizedRegion;                     // 是否开启优化区域
        public int              nOptimizedROIType;                    // 优化区域类型,见NET_THERMO_ROI
        public int              nCustomRegion;                        // 自定义区域个数
        public NET_RECT[]       stCustomRegions = (NET_RECT[])new NET_RECT().toArray(64); // 自定义区域,仅在 nOptimizedROIType 为 NET_THERMO_ROI_CUSTOM 时有效
        public byte[]           Reserved = new byte[256];
    }

    // 热成像信息
    public static class NET_THERMO_GRAPHY_INFO extends SdkStructure
    {
        public int              nBrightness;                          // 亮度
        public int              nSharpness;                           // 锐度
        public int              nEZoom;                               // 倍数
        public int              nThermographyGamma;                   // 伽马值
        public int              nColorization;                        // 色彩,见NET_THERMO_COLORIZATION
        public int              nSmartOptimizer;                      // 优化指数
        public NET_THERMO_GRAPHY_OPTREGION stOptRegion;               // 优化区域
        public int              nAgc;                                 // 自动增益控制
        public int              nAgcMaxGain;                          // 最大自动增益
        public int              nAgcPlateau;                          // 增益均衡
        public byte[]           reserved = new byte[244];             // 保留字段
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_OPTREGION 命令入参
    public static class NET_IN_THERMO_GET_OPTREGION extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号

        public NET_IN_THERMO_GET_OPTREGION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_OPTREGION 命令出参
    public static class NET_OUT_THERMO_GET_OPTREGION extends SdkStructure
    {
        public int              dwSize;
        public NET_THERMO_GRAPHY_OPTREGION stInfo;                    // 优化区域信息

        public NET_OUT_THERMO_GET_OPTREGION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_EXTSYSINFO 命令入参
    public static class NET_IN_THERMO_GET_EXTSYSINFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号

        public NET_IN_THERMO_GET_EXTSYSINFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_THERMO_GRAPHY_EXTSYSINFO 命令出参
    public static class NET_OUT_THERMO_GET_EXTSYSINFO extends SdkStructure
    {
        public int              dwSize;
        public NET_THERMO_SYSINFO stInfo;                             // 通道号

        public NET_OUT_THERMO_GET_EXTSYSINFO() {
            this.dwSize = this.size();
        }
    }

    // 外部系统信息
    public static class NET_THERMO_SYSINFO extends SdkStructure
    {
        public byte[]           szSerialNumber = new byte[64];        // 序列号
        public byte[]           szSoftwareVersion = new byte[64];     // 软件版本
        public byte[]           szFirmwareVersion = new byte[64];     // 固件版本
        public byte[]           szLibVersion = new byte[64];          // 库版本
        public byte[]           reserved = new byte[256];
    }

    // CLIENT_GetDevCaps 接口 NET_THERMO_GRAPHY_CAPS 命令入参
    public static class NET_IN_THERMO_GETCAPS extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号

        public NET_IN_THERMO_GETCAPS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetDevCaps 接口 NET_THERMO_GRAPHY_CAPS 命令出参
    public static class NET_OUT_THERMO_GETCAPS extends SdkStructure
    {
        public int              dwSize;
        public int              dwModes;                              // 支持的预置模式掩码
        public int              dwColorization;                       // 预置着色模式掩码
        public int              dwROIModes;                           // 预置感兴趣区域模式掩码
        public RANGE            stBrightness;                         // 亮度相关能力
        public RANGE            stSharpness;                          // 锐度相关能力
        public RANGE            stEZoom;                              // 倍数相关能力
        public RANGE            stThermographyGamma;                  // 伽马相关能力
        public RANGE            stSmartOptimizer;                     // 优化参数相关能力
        public NET_FFCPERIOD_RANGE stFFCPeriod;                       // 平场聚焦校准相关能力

        public NET_OUT_THERMO_GETCAPS() {
            this.dwSize = this.size();
        }
    }

    // range
    public static class RANGE extends SdkStructure
    {
        public float            fMax;                                 // 最大值
        public float            fMin;                                 // 最小值
        public int              abStep;                               // 是否启用步长
        public float            fStep;                                // 步长
        public int              abDefault;                            // 是否启用默认值
        public float            fDefault;                             // 默认值
        public byte[]           reserved = new byte[16];
    }

    // CLIENT_GetDevCaps 接口 NET_RADIOMETRY_CAPS 命令入参
    public static class NET_IN_RADIOMETRY_GETCAPS extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号

        public NET_IN_RADIOMETRY_GETCAPS() {
            this.dwSize = this.size();
        }
    }

    // 测温模式的类型
    public static class NET_RADIOMETRY_METERTYPE extends SdkStructure
    {
        public static final int   NET_RADIOMETRY_METERTYPE_UNKNOWN = 0;
        public static final int   NET_RADIOMETRY_METERTYPE_SPOT = 1;    // 点
        public static final int   NET_RADIOMETRY_METERTYPE_LINE = 2;    // 线
        public static final int   NET_RADIOMETRY_METERTYPE_AREA = 3;    // 区域
    }

    // 点,线,区域总个数能力
    public static class NET_RADIOMETRY_TOTALNUM extends SdkStructure
    {
        public int              dwMaxNum;                             // 最多支持个数
        public int              dwMaxSpots;                           // 最多点的个数
        public int              dwMaxLines;                           // 最多划线的条数
        public int              dwMaxAreas;                           // 最多区域的个数
        public byte[]           reserved = new byte[32];              // 保留字节
    }

    // CLIENT_GetDevCaps 接口 NET_RADIOMETRY_CAPS 命令出参
    public static class NET_OUT_RADIOMETRY_GETCAPS extends SdkStructure
    {
        public int              dwSize;
        public NET_RADIOMETRY_TOTALNUM stTotalNum;                    // 点,线,区域总个数能力
        public int              dwMaxPresets;                         // 最多测温预置点的个数
        public int              dwMeterType;                          // 测温模式的类型掩码,见NET_RADIOMETRY_METERTYPE
        public RANGE            stObjectEmissivity;                   // 辐射系数相关能力
        public RANGE            stObjectDistance;                     // 距离相关能力
        public RANGE            stReflectedTemperature;               // 反射温度相关能力
        public RANGE            stRelativeHumidity;                   // 相对湿度相关能力
        public RANGE            stAtmosphericTemperature;             // 大气温度相关能力
        public int              nStatisticsMinPeriod;                 // 测温点统计功能最小存储数据间隔  单位为秒
        public float            fIsothermMaxTemp;                     // 色标条最高的温度值
        public float            fIsothermMinTemp;                     // 色标条最低的温度值

        public NET_OUT_RADIOMETRY_GETCAPS() {
            this.dwSize = this.size();
        }
    }

    // 测温信息
    public static class NET_RADIOMETRYINFO extends SdkStructure
    {
        public int              nMeterType;                           // 返回测温类型,见NET_RADIOMETRY_METERTYPE
        public int              nTemperUnit;                          // 温度单位(当前配置的温度单位),见 NET_TEMPERATURE_UNIT
        public float            fTemperAver;                          // 点的温度或者平均温度   点的时候 只返回此字段
        public float            fTemperMax;                           // 最高的温度
        public float            fTemperMin;                           // 最低的温度
        public float            fTemperMid;                           // 中间温度值
        public float            fTemperStd;                           // 标准方差值
        public byte[]           reserved = new byte[64];
    }

    // 获取测温项温度的条件
    public static class NET_RADIOMETRY_CONDITION extends SdkStructure
    {
        public int              nPresetId;                            // 预置点编号
        public int              nRuleId;                              // 规则编号
        public int              nMeterType;                           // 测温项类别,见NET_RADIOMETRY_METERTYPE
        public byte[]           szName = new byte[64];                // 测温项的名称,从测温配置规则名字中选取
        public int              nChannel;                             // 通道号
        public byte[]           reserved = new byte[256];
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_RADIOMETRY_POINT_TEMPER 命令入参
    public static class NET_IN_RADIOMETRY_GETPOINTTEMPER extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号
        public NET_POINT        stCoordinate;                         // 测温点的坐标,坐标值 0~8192

        public NET_IN_RADIOMETRY_GETPOINTTEMPER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_RADIOMETRY_POINT_TEMPER 命令出参
    public static class NET_OUT_RADIOMETRY_GETPOINTTEMPER extends SdkStructure
    {
        public int              dwSize;
        public NET_RADIOMETRYINFO stPointTempInfo;                    // 获取测温点的参数值

        public NET_OUT_RADIOMETRY_GETPOINTTEMPER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_RADIOMETRY_TEMPER 命令入参
    public static class NET_IN_RADIOMETRY_GETTEMPER extends SdkStructure
    {
        public int              dwSize;
        public NET_RADIOMETRY_CONDITION stCondition;                  // 获取测温项温度的条件

        public NET_IN_RADIOMETRY_GETTEMPER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryDevInfo 接口 NET_QUERY_DEV_RADIOMETRY_TEMPER 命令出参
    public static class NET_OUT_RADIOMETRY_GETTEMPER extends SdkStructure
    {
        public int              dwSize;
        public NET_RADIOMETRYINFO stTempInfo;                         // 获取测温参数值

        public NET_OUT_RADIOMETRY_GETTEMPER() {
            this.dwSize = this.size();
        }
    }

    // 云台预置点
    public static class NET_PTZ_PRESET extends SdkStructure
    {
        public int              nIndex;                               // 编号
        public byte[]           szName = new byte[PTZ_PRESET_NAME_LEN]; // 名称
        public int[]            nPosition = new int[3];               // 预置点的坐标和放大倍数
															                      // 第一个参数是水平坐标,范围[0,3599]，表示0度到359.9度,度数扩大10倍表示
                                                                                  // 第二个参数是垂直坐标,范围[-1800,1800],表示-180.0度到+180.0度,度数扩大10倍表示
		                                                                          // 第三个参数是放大参数,范围[0,128],表示最小倍到最大倍的变倍位置
        public int              bSetNameEx;                           // 设置预置点名称时需置TRUE
        public byte[]           szNameEx = new byte[256];             // 名称，支持256字节长度
        public byte[]           szReserve = new byte[52];             // 预留字节
    }

    // 云台预置点列表
    public static class NET_PTZ_PRESET_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              dwMaxPresetNum;                       // 预置点最大个数
        public int              dwRetPresetNum;                       // 返回预置点个数
        public Pointer          pstuPtzPorsetList;                    // 预置点列表(入参需要根据最大个数申请内存),内存大小为sizeof(NET_PTZ_PRESET)*dwMaxPresetNum

        public NET_PTZ_PRESET_LIST() {
            this.dwSize = this.size();
        }
    }

    //-------------------------------报警属性---------------------------------
    // 云台联动
    public static class SDK_PTZ_LINK extends SdkStructure
    {
        public int              iType;                                //0-None,1-Preset,2-Tour,3-Pattern
        public int              iValue;
    }

    ////////////////////////////////HDVR专用//////////////////////////////////
    // 报警联动扩展结构体
    public static class NET_MSG_HANDLE_EX extends SdkStructure
    {
        /* 消息处理方式,可以同时多种处理方式,包括
         * 0x00000001 - 报警上传
         * 0x00000002 - 联动录象
         * 0x00000004 - 云台联动
         * 0x00000008 - 发送邮件
         * 0x00000010 - 本地轮巡
         * 0x00000020 - 本地提示
         * 0x00000040 - 报警输出
         * 0x00000080 - Ftp上传
         * 0x00000100 - 蜂鸣
         * 0x00000200 - 语音提示
         * 0x00000400 - 抓图
         */
        /*当前报警所支持的处理方式,按位掩码表示*/ public int dwActionMask;
        /*触发动作,按位掩码表示,具体动作所需要的参数在各自的配置中体现*/
        public int              dwActionFlag;
        /*报警触发的输出通道,报警触发的输出,为1表示触发该输出*/
        public byte[]           byRelAlarmOut = new byte[NET_MAX_ALARMOUT_NUM_EX];
        public int              dwDuration;                           /*报警持续时间*/
        /*联动录象*/
        public byte[]           byRecordChannel = new byte[NET_MAX_VIDEO_IN_NUM_EX]; /*报警触发的录象通道,为1表示触发该通道*/
        public int              dwRecLatch;                           /*录象持续时间*/
        /*抓图通道*/
        public byte[]           bySnap = new byte[NET_MAX_VIDEO_IN_NUM_EX];
        /*轮巡通道*/
        public byte[]           byTour = new byte[NET_MAX_VIDEO_IN_NUM_EX]; /*轮巡通道0-31路*/
        /*云台联动*/
        public SDK_PTZ_LINK[]   struPtzLink = (SDK_PTZ_LINK[])new SDK_PTZ_LINK().toArray(NET_MAX_VIDEO_IN_NUM_EX);
        public int              dwEventLatch;                         /*联动开始延时时间,s为单位,范围是0~15,默认值是0*/
        /*报警触发的无线输出通道,报警触发的输出,为1表示触发该输出*/
        public byte[]           byRelWIAlarmOut = new byte[NET_MAX_ALARMOUT_NUM_EX];
        public byte             bMessageToNet;
        public byte             bMMSEn;                               /*短信报警使能*/
        public byte             bySnapshotTimes;                      /*短信发送抓图张数*/
        public byte             bMatrixEn;                            /*!<矩阵使能*/
        public int              dwMatrix;                             /*!<矩阵掩码*/
        public byte             bLog;                                 /*!<日志使能,目前只有在WTN动态检测中使用*/
        public byte             bSnapshotPeriod;                      /*!<抓图帧间隔,每隔多少帧抓一张图片,一定时间内抓拍的张数还与抓图帧率有关。0表示不隔帧,连续抓拍。*/
        public byte[]           byTour2 = new byte[NET_MAX_VIDEO_IN_NUM_EX]; /*轮巡通道32-63路*/
        public byte             byEmailType;                          /*<0,图片附件,1,录像附件>*/
        public byte             byEmailMaxLength;                     /*<附件录像时的最大长度,单位MB>*/
        public byte             byEmailMaxTime;                       /*<附件是录像时最大时间长度,单位秒>*/
        public byte[]           byReserved = new byte[475];
    }

    public static class EM_NET_DEFENCE_AREA_TYPE extends SdkStructure
    {
        public static final int   EM_NET_DEFENCE_AREA_TYPE_UNKNOW = 0;  //未知
        public static final int   EM_NET_DEFENCE_AREA_TYPE_INTIME = 1;  //即时防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_DELAY = 2;   //延时防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_FULLDAY = 3; //24小时防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_Follow = 4;  //跟随防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_MEDICAL = 5; //医疗紧急防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_PANIC = 6;   //恐慌防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_FIRE = 7;    //火警防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_FULLDAYSOUND = 8; //24小时有声防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_FULLDATSLIENT = 9; //24小时无声防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_ENTRANCE1 = 10; //出入防区1
        public static final int   EM_NET_DEFENCE_AREA_TYPE_ENTRANCE2 = 11; //出入防区2
        public static final int   EM_NET_DEFENCE_AREA_TYPE_INSIDE = 12; //内部防区
        public static final int   EM_NET_DEFENCE_AREA_TYPE_OUTSIDE = 13; //外部防区
        public static final int   EN_NET_DEFENCE_AREA_TYPE_PEOPLEDETECT = 14; //人员检测防区
    }

    // 本地报警事件(对NET_ALARM_ALARM_EX升级)
    public static class ALARM_ALARM_INFO_EX2 extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           //通道号
        public int              nAction;                              //0:开始, 1:停止
        public NET_TIME         stuTime;                              //报警事件发生的时间
        public int              emSenseType;                          //传感器类型, 取值范围为  NET_SENSE_METHOD 中的值
        public NET_MSG_HANDLE_EX stuEventHandler;                     //联动信息
        public int              emDefenceAreaType;                    //防区类型, 取值类型为EM_NET_DEFENCE_AREA_TYPE中的值
        public int              nEventID;                             //事件ID
        public byte[]           szName = new byte[NET_COMMON_STRING_32]; // 通道名称
        public int              nCount;                               // 事件发生次数
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息
    	/**
    	 * 本地报警时登陆的用户ID
    	 */
        public byte[]           szUserID = new byte[32];
    	/**
    	 * 本地报警时登陆的用户名
    	 */
        public byte[]           szUserName = new byte[128];
    	/**
    	 * 设备序列号
    	 */
        public byte[]           szSN = new byte[32];
    	/**
    	 * 外部输入报警
    	 */
        public int              bExAlarmIn;
    	/**
    	 * 报警通道所属区域的个数
    	 */
        public int              nAreaNums;
    	/**
    	 * 报警通道所属的区域
    	 */
        public int[]            nAreas = new int[64];
    	/**
    	 * 事件公共扩展字段结构体
    	 */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
        public byte[]           szDelayUploadSeq = new byte[128];     //延时上传序号
        public int              nVideoCount;                          //待上传的视频的数量
        public int              nPictureCount;                        //待上传的图片的数量
        public int              nAreaInfoNum;                         //所属区域信息个数
        public Pointer          pstuAreaInfo;                         //所属区域信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_AREAR_INFO}
    	/**
    	 * 保留字节
    	 */
        public byte[]           byReserved = new byte[428-POINTERSIZE];

        public ALARM_ALARM_INFO_EX2() {
            this.dwSize = this.size();
        }
    }

    // 布撤防状态变化事件的信息
    public static class ALARM_ARMMODE_CHANGE_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              bArm;                                 // 变化后的状态,对应  NET_ALARM_MODE
        public int              emSceneMode;                          // 情景模式，对应  NET_SCENE_MODE
        public int              dwID;                                 // ID号, 遥控器编号或键盘地址, emTriggerMode为NET_EM_TRIGGER_MODE_NET类型时为0
        public int              emTriggerMode;                        // 触发方式,对应  NET_EM_TRIGGER_MODE
        public byte[]           szNetClientAddr = new byte[64];       //网络用户IP地址或网络地址
        public int              nUserCode;                            //用户ID，0:管理员，1~20：普通用户，21：安装员，22：挟持用户

        public ALARM_ARMMODE_CHANGE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 布撤防模式
    public static class NET_ALARM_MODE extends SdkStructure
    {
        public static final int   NET_ALARM_MODE_UNKNOWN = -1;          // 未知
        public static final int   NET_ALARM_MODE_DISARMING = 0;         // 撤防
        public static final int   NET_ALARM_MODE_ARMING = 1;            // 布防
        public static final int   NET_ALARM_MODE_FORCEON = 2;           // 强制布防
        public static final int   NET_ALARM_MODE_PARTARMING = 3;        // 部分布防
    }

    // 布撤防场景模式
    public static class NET_SCENE_MODE extends SdkStructure
    {
        public static final int   NET_SCENE_MODE_UNKNOWN = 0;           // 未知场景
        public static final int   NET_SCENE_MODE_OUTDOOR = 1;           // 外出模式
        public static final int   NET_SCENE_MODE_INDOOR = 2;            // 室内模式
        public static final int   NET_SCENE_MODE_WHOLE = 3;             // 全局模式
        public static final int   NET_SCENE_MODE_RIGHTNOW = 4;          // 立即模式
        public static final int   NET_SCENE_MODE_SLEEPING = 5;          // 就寝模式
        public static final int   NET_SCENE_MODE_CUSTOM = 6;            // 自定义模式
    }

    // 触发方式
    public static class NET_EM_TRIGGER_MODE extends SdkStructure
    {
        public static final int   NET_EM_TRIGGER_MODE_UNKNOWN = 0;
        public static final int   NET_EM_TRIGGER_MODE_NET = 1;          // 网络用户(平台或Web)
        public static final int   NET_EM_TRIGGER_MODE_KEYBOARD = 2;     // 键盘
        public static final int   NET_EM_TRIGGER_MODE_REMOTECONTROL = 3; // 遥控器
    }

    // 紧急救助事件详情
    public static class ALARM_RCEMERGENCY_CALL_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nAction;                              // -1:未知 0:开始 1:停止
        public int              emType;                               // 紧急类型,对应 EM_RCEMERGENCY_CALL_TYPE
        public NET_TIME         stuTime;                              // 事件发生时间
        public int              emMode;                               // 报警方式，对应 EM_RCEMERGENCY_MODE_TYPE
        public int              dwID;                                 // 用于标示不同的紧急事件(只有emMode是遥控器类型时有效, 表示遥控器的编号, 0表示无效ID)
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           szDelayUploadSeq = new byte[128];     // 延时上传序号
        public byte[]           szResvered = new byte[1020];          // 保留字节

        public ALARM_RCEMERGENCY_CALL_INFO() {
            this.dwSize = this.size();
        }
    }

    // 紧急救助事件类型
    public static class EM_RCEMERGENCY_CALL_TYPE extends SdkStructure
    {
        public static final int   EM_RCEMERGENCY_CALL_UNKNOWN = 0;
        public static final int   EM_RCEMERGENCY_CALL_FIRE = 1;         // 火警
        public static final int   EM_RCEMERGENCY_CALL_DURESS = 2;       // 胁迫
        public static final int   EM_RCEMERGENCY_CALL_ROBBER = 3;       // 匪警
        public static final int   EM_RCEMERGENCY_CALL_MEDICAL = 4;      // 医疗
        public static final int   EM_RCEMERGENCY_CALL_EMERGENCY = 5;    // 紧急
    }

    // 报警方式
    public static class EM_RCEMERGENCY_MODE_TYPE extends SdkStructure
    {
        public static final int   EM_RCEMERGENCY_MODE_UNKNOWN = 0;
        public static final int   EM_RCEMERGENCY_MODE_KEYBOARD = 1;     // 键盘
        public static final int   EM_RCEMERGENCY_MODE_WIRELESS_CONTROL = 2; // 遥控器
    }

    /////////////////////////////////////////////////////
    ////////用户信息管理对应接口CLIENT_QueryUserInfoNew/////////
    // 用户信息表
    public static class USER_MANAGE_INFO_NEW extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              dwRightNum;                           // 权限信息有效个数
        public OPR_RIGHT_NEW[]  rightList = new OPR_RIGHT_NEW[NET_NEW_MAX_RIGHT_NUM]; // 权限信息，有效个数由 dwRightNum 成员决定, 用户权限个数上限NET_NEW_MAX_RIGHT_NUM = 1024
        public int              dwGroupNum;                           // 用户组信息有效个数
        public USER_GROUP_INFO_NEW[] groupList = new USER_GROUP_INFO_NEW[NET_MAX_GROUP_NUM]; // 用户组信息，此参数废弃，请使用groupListEx
        public int              dwUserNum;                            // 用户数
        public USER_INFO_NEW[]  userList = new USER_INFO_NEW[NET_MAX_USER_NUM]; // 用户列表， 用户个数上限NET_MAX_USER_NUM=200
        public int              dwFouctionMask;                       // 掩码： 0x00000001 - 支持用户复用， 0x00000002 - 密码修改需要校验
        public byte             byNameMaxLength;                      // 支持的用户名最大长度
        public byte             byPSWMaxLength;                       // 支持的密码最大长度
        public byte[]           byReserve = new byte[254];
        public USER_GROUP_INFO_EX2[] groupListEx = new USER_GROUP_INFO_EX2[NET_MAX_GROUP_NUM]; // 用户组信息扩展, 用户组个数上限NET_MAX_GROUP_NUM=20

        public USER_MANAGE_INFO_NEW() {
            this.dwSize = this.size();

            for(int i = 0; i < NET_NEW_MAX_RIGHT_NUM; i++) {
                rightList[i] = new OPR_RIGHT_NEW();
            }

            for(int i = 0; i < NET_MAX_USER_NUM; i++) {
                userList[i] = new USER_INFO_NEW();
            }

            for(int i = 0; i < NET_MAX_GROUP_NUM; i++) {
                groupList[i] = new USER_GROUP_INFO_NEW();
                groupListEx[i] = new USER_GROUP_INFO_EX2();
            }
        }
    }

    // 权限信息
    public static class OPR_RIGHT_NEW extends SdkStructure {
        public int              dwSize;                               //结构体大小
        public int              dwID;                                 //权限ID，每个 权限都有各自的ID
        public byte[]           name = new byte[NET_RIGHT_NAME_LENGTH]; //名称 权限名长度 NET_RIGHT_NAME_LENGTH=32
        public byte[]           memo = new byte[NET_MEMO_LENGTH];     //说明备注长度NET_MEMO_LENGTH=32

        public OPR_RIGHT_NEW() {
            this.dwSize = this.size();
        }
    }

    // 用户组信息
    public static class USER_GROUP_INFO_NEW extends SdkStructure {
        public int              dwSize;
        public int              dwID;                                 // 用户组ID， 每个用户组都有各自的ID
        public byte[]           name = new byte[NET_USER_NAME_LENGTH_EX]; // 用户组名称/NET_USER_NAME_LENGTH_EX=16
        public int              dwRightNum;                           // 用户组权限有效个数
        public int[]            rights = new int[NET_NEW_MAX_RIGHT_NUM]; // 用户组支持权限数组
        public byte[]           memo = new byte[NET_MEMO_LENGTH];     // 用户组备注说明

        public USER_GROUP_INFO_NEW() {
            this.dwSize = this.size();
        }
    }

    // 用户组信息扩展，用户组名加长
    public static class USER_GROUP_INFO_EX2 extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              dwID;                                 // ID
        public byte[]           name = new byte[NET_NEW_USER_NAME_LENGTH]; // 用户名 长度NET_NEW_USER_NAME_LENGTH=128
        public int              dwRightNum;                           // 权限数量
        public int[]            rights = new int[NET_NEW_MAX_RIGHT_NUM]; // 用户权限 个数上限 NET_NEW_MAX_RIGHT_NUM = 1024
        public byte[]           memo = new byte[NET_MEMO_LENGTH];     // 说明， 备注长度NET_MEMO_LENGTH=32

        public USER_GROUP_INFO_EX2() {
            this.dwSize = this.size();
        }
    }

    // 用户信息结构体
    public static class USER_INFO_NEW extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              dwID;                                 // 用户ID
        public int              dwGroupID;                            // 用户组组ID
        public byte[]           name = new byte[NET_NEW_USER_NAME_LENGTH]; // 用户名称，长度NET_NEW_USER_NAME_LENGTH=128
        public byte[]           passWord = new byte[NET_NEW_USER_PSW_LENGTH]; // 用户密码，NET_NEW_USER_PSW_LENGTH=128
        public int              dwRightNum;                           // 用户权限有效个数
        public int[]            rights = new int[NET_NEW_MAX_RIGHT_NUM]; // 用户支持权限数组，个数上限 NET_NEW_MAX_RIGHT_NUM = 1024
        public byte[]           memo = new byte[NET_MEMO_LENGTH];     // 用户备注说明， 备注长度NET_MEMO_LENGTH=32
        public int              dwFouctionMask;                       // 掩码,0x00000001 - 支持用户复用
        public NET_TIME         stuTime;                              // 最后修改时间
        public byte             byIsAnonymous;                        // 是否可以匿名登录, 0:不可匿名登录, 1: 可以匿名登录
        public byte[]           byReserve = new byte[7];              // 保留字节

        public USER_INFO_NEW() {
            this.dwSize = this.size();
        }
    }

    //------------------------允许名单相关结构体-------------------------
    // CLIENT_FindRecord接口输入参数
    public static class NET_IN_FIND_RECORD_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              emType;                               // 待查询记录类型,emType对应  EM_NET_RECORD_TYPE
        public Pointer          pQueryCondition;                      // 查询类型对应的查询条件

        public NET_IN_FIND_RECORD_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 交通禁止/允许名单账户记录查询条件
    public static class FIND_RECORD_TRAFFICREDLIST_CONDITION extends SdkStructure {
        public int              dwSize;
        public byte[]           szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌号
        public byte[]           szPlateNumberVague = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌号码模糊查询
        public int              nQueryResultBegin;                    // 第一个条返回结果在查询结果中的偏移量
        public int              bRapidQuery;                          // 是否快速查询, TRUE:为快速,快速查询时不等待所有增、删、改操作完成。默认为非快速查询
        public byte[]           szProvince = new byte[64];            // 省份类型
        public int              emPlateType;                          // 车牌类型 ,参考枚举EM_NET_PLATE_TYPE

        public FIND_RECORD_TRAFFICREDLIST_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 交通流量记录查询条件
    public static class FIND_RECORD_TRAFFICFLOW_CONDITION extends SdkStructure {
        public int              dwSize;
        public int              abChannelId;                          // 通道号查询条件是否有效
        public int              nChannelId;                           // 通道号
        public int              abLane;                               // 车道号查询条件是否有效
        public int              nLane;                                // 车道号
        public int              bStartTime;                           // 开始时间查询条件是否有效
        public NET_TIME         stStartTime;                          // 开始时间
        public int              bEndTime;                             // 结束时间查询条件是否有效
        public NET_TIME         stEndTime;                            // 结束时间
        public int              bStatisticsTime;                      // 查询是否为统计时间,为BOOL类型，bStartTime及bEndTime均为1

        public FIND_RECORD_TRAFFICFLOW_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 门禁出入记录查询条件
    public static class FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX extends SdkStructure {
        public int              dwSize;
        public int              bCardNoEnable;                        // 启用卡号查询, 为BOOL类型
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public int              bTimeEnable;                          // 启用时间段查询, 为BOOL类型
        public NET_TIME         stStartTime;                          // 起始时间
        public NET_TIME         stEndTime;                            // 结束时间
        public int              nOrderNum;                            // 规则数
        public FIND_RECORD_ACCESSCTLCARDREC_ORDER[] stuOrders = (FIND_RECORD_ACCESSCTLCARDREC_ORDER[])new FIND_RECORD_ACCESSCTLCARDREC_ORDER().toArray(MAX_ORDER_NUMBER); // 规则数组
        public int              bRealUTCTimeEnable;                   // 启动RealUTC时间查询, bRealUTCTimeEnable为TRUE时bTimeEnable无效
        public int              nStartRealUTCTime;                    // 真实UTC时间戳，起始时间
        public int              nEndRealUTCTime;                      // 真实UTC时间戳，结束时间
        public byte[]           szReserved = new byte[40];            // 字节对齐

        public FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX() {
            this.dwSize = this.size();
        }
    }

    // 门禁出入记录排序规则详情
    public static class FIND_RECORD_ACCESSCTLCARDREC_ORDER extends SdkStructure
    {
        public int              emField;                              // 排序字段, 对应枚举 EM_RECORD_ACCESSCTLCARDREC_ORDER_FIELD
        public int              emOrderType;                          // 排序类型, 对应枚举 EM_RECORD_ORDER_TYPE
        public byte[]           byReverse = new byte[64];             // 保留字节
    }

    // 门禁出入记录排序字段
    public static class EM_RECORD_ACCESSCTLCARDREC_ORDER_FIELD extends SdkStructure
    {
        public static final int   EM_RECORD_ACCESSCTLCARDREC_ORDER_FIELD_UNKNOWN = 0; // 未知
        public static final int   EM_RECORD_ACCESSCTLCARDREC_ORDER_FIELD_RECNO = 1; // 记录集编号
        public static final int   EM_RECORD_ACCESSCTLCARDREC_ORDER_FIELD_CREATETIME = 2; // 创建时间
    }

    // 排序类型
    public static class EM_RECORD_ORDER_TYPE extends SdkStructure
    {
        public static final int   EM_RECORD_ORDER_TYPE_UNKNOWN = 0;     // 未知
        public static final int   EM_RECORD_ORDER_TYPE_ASCENT = 1;      // 升序
        public static final int   EM_RECORD_ORDER_TYPE_DESCENT = 2;     // 降序
    }

    // 开门方式(门禁事件,门禁出入记录,实际的开门方式)
    public static class NET_ACCESS_DOOROPEN_METHOD extends SdkStructure {
        public static final int   NET_ACCESS_DOOROPEN_METHOD_UNKNOWN = 0;
        public static final int   NET_ACCESS_DOOROPEN_METHOD_PWD_ONLY = 1; // 密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD = 2;  // 刷卡开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_FIRST = 3; // 先刷卡后密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_PWD_FIRST = 4; // 先密码后刷卡开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_REMOTE = 5; // 远程开锁,如通过室内机或者平台对门口机开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_BUTTON = 6; // 开锁按钮进行开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT = 7; // 信息开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_PWD_CARD_FINGERPRINT = 8; // 密码+刷卡+信息组合开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_PWD_FINGERPRINT = 10; // 密码+信息组合开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_FINGERPRINT = 11; // 刷卡+信息组合开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_PERSONS = 12; // 多人开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_KEY = 13;  // 钥匙开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_COERCE_PWD = 14; // 胁迫密码开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_QRCODE = 15; // 二维码开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACE_RECOGNITION = 16; // 目标识别开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACEIDCARD = 18; // 人证对比
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACEIDCARD_AND_IDCARD = 19; // 证件+ 人证比对
        public static final int   NET_ACCESS_DOOROPEN_METHOD_BLUETOOTH = 20; // 蓝牙开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CUSTOM_PASSWORD = 21; // 个性化密码开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_USERID_AND_PWD = 22; // UserID+密码
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACE_AND_PWD = 23; // 人脸+密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_AND_PWD = 24; // 信息+密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_AND_FACE = 25; // 信息+人脸开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_AND_FACE = 26; // 刷卡+人脸开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACE_OR_PWD = 27; // 人脸或密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_OR_PWD = 28; // 信息或密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_OR_FACE = 29; // 信息或人脸开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_OR_FACE = 30; // 刷卡或人脸开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_OR_FINGERPRINT = 31; // 刷卡或信息开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_AND_FACE_AND_PWD = 32; // 信息+人脸+密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_AND_FACE_AND_PWD = 33; // 刷卡+人脸+密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_AND_FINGERPRINT_AND_PWD = 34; // 刷卡+信息+密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_AND_PWD_AND_FACE = 35; // 卡+信息+人脸组合开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FINGERPRINT_OR_FACE_OR_PWD = 36; // 信息或人脸或密码
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_OR_FACE_OR_PWD = 37; // 卡或人脸或密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_OR_FINGERPRINT_OR_FACE = 38; // 卡或信息或人脸开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_AND_FINGERPRINT_AND_FACE_AND_PWD = 39; // 卡+信息+人脸+密码组合开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CARD_OR_FINGERPRINT_OR_FACE_OR_PWD = 40; // 卡或信息或人脸或密码开锁
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACEIPCARDANDIDCARD_OR_CARD_OR_FACE = 41; // (证件+人证比对)或 刷卡 或 人脸
        public static final int   NET_ACCESS_DOOROPEN_METHOD_FACEIDCARD_OR_CARD_OR_FACE = 42; // 人证比对 或 刷卡(二维码) 或 人脸
        public static final int   NET_ACCESS_DOOROPEN_METHOD_DTMF = 43; // DTMF开锁(包括SIPINFO,RFC2833,INBAND)
        public static final int   NET_ACCESS_DOOROPEN_METHOD_REMOTE_QRCODE = 44; // 远程二维码开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_REMOTE_FACE = 45; // 远程人脸开门
        public static final int   NET_ACCESS_DOOROPEN_METHOD_CITIZEN_FINGERPRINT = 46; // 人证比对开门(信息)
    }

    // 卡类型
    public static class NET_ACCESSCTLCARD_TYPE  {
        public static final int   NET_ACCESSCTLCARD_TYPE_UNKNOWN = -1;
        public static final int   NET_ACCESSCTLCARD_TYPE_GENERAL = 0;   // 一般卡
        public static final int   NET_ACCESSCTLCARD_TYPE_VIP = 1;       // 卡
        public static final int   NET_ACCESSCTLCARD_TYPE_GUEST = 2;     // 来宾卡
        public static final int   NET_ACCESSCTLCARD_TYPE_PATROL = 3;    // 巡逻卡
        public static final int   NET_ACCESSCTLCARD_TYPE_BLACKLIST = 4; // 禁止名单卡
        public static final int   NET_ACCESSCTLCARD_TYPE_CORCE = 5;     // 胁迫卡
        public static final int   NET_ACCESSCTLCARD_TYPE_POLLING = 6;   // 巡检卡
        public static final int   NET_ACCESSCTLCARD_TYPE_MOTHERCARD = 0xff; // 母卡
    }

    // 门禁刷卡记录记录集信息
    public static class NET_RECORDSET_ACCESS_CTL_CARDREC extends SdkStructure {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public byte[]           szPwd = new byte[NET_MAX_CARDPWD_LEN]; // 密码
        public NET_TIME         stuTime;                              // 刷卡时间
        public int              bStatus;                              // 刷卡结果,TRUE表示成功,FALSE表示失败
        public int              emMethod;                             // 开门方式 NET_ACCESS_DOOROPEN_METHOD
        public int              nDoor;                                // 门号,即CFG_CMD_ACCESS_EVENT配置CFG_ACCESS_EVENT_INFO的数组下标
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public int              nReaderID;                            // 读卡器ID (废弃,不再使用)
        public byte[]           szSnapFtpUrl = new byte[MAX_PATH];    // 开锁抓拍上传的FTP地址
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 读卡器ID													// 开门并上传抓拍照片,在记录集记录存储地址,成功才有
        public int              emCardType;                           // 卡类型 NET_ACCESSCTLCARD_TYPE
        public int              nErrorCode;                           // 开门失败的原因,仅在bStatus为FALSE时有效
        // 0x00 没有错误
        // 0x10 未授权
        // 0x11 卡挂失或注销
        // 0x12 没有该门权限
        // 0x13 开门模式错误
        // 0x14 有效期错误
        // 0x15 防反潜模式
        // 0x16 胁迫报警未打开
        // 0x17 门常闭状态
        // 0x18 AB互锁状态
        // 0x19 巡逻卡
        // 0x1A 设备处于闯入报警状态
        // 0x20 时间段错误
        // 0x21 假期内开门时间段错误
        // 0x30 需要先验证有首卡权限的卡片
        // 0x40 卡片正确,输入密码错误
        // 0x41 卡片正确,输入密码超时
        // 0x42 卡片正确,输入信息错误
        // 0x43 卡片正确,输入信息超时
        // 0x44 信息正确,输入密码错误
        // 0x45 信息正确,输入密码超时
        // 0x50 组合开门顺序错误
        // 0x51 组合开门需要继续验证
        // 0x60 验证通过,控制台未授权
        // 0x61 卡片正确,人脸错误
        // 0x62 卡片正确,人脸超时
        // 0x63 重复进入
        // 0x64 未授权,需要后端平台识别
        // 0x65 体温过高
        // 0x66	未戴口罩
        // 0x67 健康码获取失败
        // 0x68 黄码禁止通行
        // 0x69 红码禁止通行
        // 0x6a 健康码无效
        // 0x6b 绿码验证通过
        // 0x70 获取健康码信息
        public byte[]           szRecordURL = new byte[NET_COMMON_STRING_128]; // 刷卡录像的地址
        public int              nNumbers;                             // 抓图的张数
        public int              emAttendanceState;                    // 考勤状态 ,参考  NET_ATTENDANCESTATE
        public int              emDirection;                          // 开门方向, 参考  NET_ENUM_DIRECTION_ACCESS_CTL
        public byte[]           szClassNumber = new byte[MAX_CLASS_NUMBER_LEN]; // 班级（考勤）
        public byte[]           szPhoneNumber = new byte[MAX_PHONENUMBER_LEN]; // 电话（考勤）
        public byte[]           szCardName = new byte[NET_MAX_CARDNAME_LEN]; // 卡命名
        public byte[]           szSN = new byte[NET_COMMON_STRING_32]; // 智能锁序列号,无线配件需要该字段
        public int              bCitizenIDResult;                     // 人证比对结果
        public byte[]           szCitizenIDName = new byte[30];       // 名字
        public byte             nRedScarfResult;                      //红领巾识别结果 0:未知 1:未佩戴 2:已佩戴；255:未使能算法识别
        public byte[]           byReserved1 = new byte[1];            // 字节对齐
        public int              emCitizenIDSex;                       // 性别, 详见EM_CITIZENIDCARD_SEX_TYPE
        public int              emCitizenIDEC;                        // 民族, 详见EM_CITIZENIDCARD_EC_TYPE
        public NET_TIME         stuCitizenIDBirth;                    // 出生日期(时分秒无效)
        public byte[]           szCitizenIDAddress = new byte[108];   // 住址
        public byte[]           szCitizenIDAuthority = new byte[48];  // 签发机关
        public NET_TIME         stuCitizenIDStart;                    // 有效起始日期(时分秒无效)
        public NET_TIME         stuCitizenIDEnd;                      // 有效截止日期(时分秒无效, 年为负数时表示长期有效)
        public int              bIsEndless;                           // 是否长期有效
        public byte[]           szSnapFaceURL = new byte[NET_COMMON_STRING_128]; // 人脸图片保存地址
        public byte[]           szCitizenPictureURL = new byte[NET_COMMON_STRING_128]; // 证件图片保存地址
        public byte[]           szCitizenIDNo = new byte[20];         // 证件号码
        public int              emSex;                                // 性别, 详见NET_ACCESSCTLCARD_SEX
        public byte[]           szRole = new byte[MAX_COMMON_STRING_32]; // 角色
        public byte[]           szProjectNo = new byte[MAX_COMMON_STRING_32]; // 项目ID
        public byte[]           szProjectName = new byte[MAX_COMMON_STRING_64]; // 项目名称
        public byte[]           szBuilderName = new byte[MAX_COMMON_STRING_64]; // 施工单位全称
        public byte[]           szBuilderID = new byte[MAX_COMMON_STRING_32]; // 施工单位ID
        public byte[]           szBuilderType = new byte[MAX_COMMON_STRING_32]; // 施工单位类型
        public byte[]           szBuilderTypeID = new byte[MAX_COMMON_STRING_8]; // 施工单位类别ID
        public byte[]           szPictureID = new byte[MAX_COMMON_STRING_64]; // 人员照片ID
        public byte[]           szContractID = new byte[MAX_COMMON_STRING_16]; // 原合同系统合同编号
        public byte[]           szWorkerTypeID = new byte[MAX_COMMON_STRING_8]; // 工种ID
        public byte[]           szWorkerTypeName = new byte[MAX_COMMON_STRING_32]; // 工种名称
        public int              bPersonStatus;                        // 人员状态, TRUE:启用, FALSE:禁用
        public int              emHatStyle;                           // 帽子类型
        public int              emHatColor;                           // 帽子颜色
        public NET_MAN_TEMPERATURE_INFO stuManTemperatureInfo;        // 人员温度信息
        public int              nCompanionInfo;                       // 陪同人员 stuCompanionInfo 个数
        public NET_COMPANION_INFO[] stuCompanionInfo = new NET_COMPANION_INFO[12]; // 陪同人员信息：姓名、卡号字段有效
        public int              emMask;                               // 口罩状态（EM_MASK_STATE_UNKNOWN、EM_MASK_STATE_NOMASK、EM_MASK_STATE_WEAR 有效） EM_MASK_STATE_TYPE
        public int              nFaceIndex;                           // 一人多脸的人脸序号
        public int              nScore;                               // 人脸质量评分
        public int              nLiftNo;                              // 电梯编号
        public byte[]           szQRCode = new byte[512];             // 二维码
        /**
         * EM_FACE_CHECK
         */
        public int              emFaceCheck;                          // 功能，刷卡开门时，门禁后台校验人脸是否是同一个人
        /**
         * EM_QRCODE_IS_EXPIRED
         */
        public int              emQRCodeIsExpired;                    // 二维码是否过期。默认值0
        /**
         * EM_QRCODE_STATE
         */
        public int              emQRCodeState;                        // 二维码状态
        public NET_TIME         stuQRCodeValidTo;                     // 二维码截止日期
        /**
         * EM_LIFT_CALLER_TYPE
         */
        public int              emLiftCallerType;                     // 梯控方式触发者
        public int              nBlockId;                             // 上报事件数据序列号从1开始自增
        public byte[]           szSection = new byte[64];             // 部门名称
        public byte[]           szWorkClass = new byte[256];          // 工作班级
         /**
        * EM_TEST_ITEMS
        * */
        public int              emTestItems;                          // 测试项目
        /**
         * NET_TEST_RESULT
         */
        public NET_TEST_RESULT  stuTestResult;                        // ESD阻值测试结果
        public int              bUseCardNameEx;                       // 是否使用卡命名扩展
        public byte[]           szCardNameEx = new byte[128];         // 卡命名扩展
        public  int             nHSJCResult;                          //核酸检测报告结果  -1: 未知 2: 未检测 3: 过期
        public int              nVaccinateFlag;                       //是否已接种新冠疫苗（0:否，1:是）
        public byte[]           szVaccineName = new byte[128];        //新冠疫苗名称
        public  int             nDateCount;                           //历史接种日期有效数
       // public byte[]                    szVaccinateDate=new byte[8*32];           //历史接种日期 	历史接种日期 (yyyy-MM-dd)。	”0000-00-00”，表示已接种，但无具体日期。
        /**
         *  历史接种日期 (yyyy-MM-dd). 如提供不了时间, 则填"0000-00-00", 表示已接种
         */
        public VaccinateDateByteArr[] szVaccinateDate = (VaccinateDateByteArr[])new VaccinateDateByteArr().toArray(8);
        /**
         * EM_TRAVELCODE_COLOR_STATE
         */
        public int              emTravelCodeColor;                    //返回行程码状态信息
        public int              nCityCount;                           //最近14天经过的城市名有效数
        /**
         *  最近14天经过的城市名. 按时间顺序排列, 最早经过的城市放第一个
         */
        public PassingCityByteArr[] szPassingCity = (PassingCityByteArr[])new PassingCityByteArr().toArray(16);
        public byte[]           szTrafficPlate = new byte[32];        //车牌
        public byte[]           szRecordLocalUrl = new byte[128];     // 刷卡录像的地址
        public byte[]           szHSJCReportDate = new byte[32];      // 核酸检测报告日期(格式: yyyy-MM-dd)
        public int              nHSJCExpiresIn;                       // 核酸检测报告有效期(单位:天)
        public byte[]           szAntigenReportDate = new byte[32];   // 抗原检测报告日期(格式: yyyy-MM-dd)
        public int              nAntigenStatus;                       // 抗原检测报告结果: -1:未知2: 未检测 3: 过期
        public int              nAntigenExpiresIn;                    // 抗原检测报告有效期(单位:天)
        public byte[]           szCheckOutType = new byte[32];        // 签出类型
        public byte[]           szCheckOutCause = new byte[512];      // 签出原因
        public int              nCreateTimeRealUTC;                   // 刷卡时间，真实UTC时间戳
        public byte[]           szReserved = new byte[20];            // 字节对齐
        public byte[]           szLocationName = new byte[256];       // 场所码名称
        public byte[]           szLocationAddress = new byte[256];    // 场所码详细地址
        public byte[]           szLocationType = new byte[256];       // 场所码类型
        public int              nPassResult;                          //通行结果，人员是否有同行 0-未知 1-人员进入 2-人员退出 3-人员未通行
        public int              nCustomerPWDType;                     //设备在不同的密码模式下上报的密码类型，0:未知 1,唤醒密码模式  2,陪同密码模式  3胁迫密码模式
        public byte[]           szUserIDEx = new byte[128];           //用户ID扩展, 当前只支持32位有效值解析获取, 并未实现下发

        public NET_RECORDSET_ACCESS_CTL_CARDREC() {
            this.dwSize = this.size();
            for(int i = 0; i<stuCompanionInfo.length;i++){
                stuCompanionInfo[i] = new NET_COMPANION_INFO();
            }
        }
    }

    // 性别
    public static class NET_ACCESSCTLCARD_SEX extends SdkStructure
    {
        public static final int   NET_ACCESSCTLCARD_SEX_UNKNOWN = 0;    // 未知
        public static final int   NET_ACCESSCTLCARD_SEX_MALE = 1;       // 男
        public static final int   NET_ACCESSCTLCARD_SEX_FEMALE = 2;     // 女
    }

    // 民族
    public static class EM_CITIZENIDCARD_EC_TYPE extends SdkStructure
    {
        public static final int   EM_CITIZENIDCARD_EC_Unknown = 0;      // 未知
        public static final int   EM_CITIZENIDCARD_EC_Han = 1;          // 汉族
        public static final int   EM_CITIZENIDCARD_EC_Mongolian = 2;    // 蒙古族
        public static final int   EM_CITIZENIDCARD_EC_Hui = 3;          // 回族
        public static final int   EM_CITIZENIDCARD_EC_Tibetan = 4;      // 藏族
        public static final int   EM_CITIZENIDCARD_EC_Uygur = 5;        // 维吾尔族
        public static final int   EM_CITIZENIDCARD_EC_Miao = 6;         // 苗族
        public static final int   EM_CITIZENIDCARD_EC_Yi = 7;           // 彝族
        public static final int   EM_CITIZENIDCARD_EC_Zhuang = 8;       // 壮族
        public static final int   EM_CITIZENIDCARD_EC_Bouyei = 9;       // 布依族
        public static final int   EM_CITIZENIDCARD_EC_Korean = 10;      // 朝鲜族
        public static final int   EM_CITIZENIDCARD_EC_Manchu = 11;      // 满族
        public static final int   EM_CITIZENIDCARD_EC_Dong = 12;        // 侗族
        public static final int   EM_CITIZENIDCARD_EC_Yao = 13;         // 瑶族
        public static final int   EM_CITIZENIDCARD_EC_Bai = 14;         // 白族
        public static final int   EM_CITIZENIDCARD_EC_Tujia = 15;       // 土家族
        public static final int   EM_CITIZENIDCARD_EC_Hani = 16;        // 哈尼族
        public static final int   EM_CITIZENIDCARD_EC_Kazak = 17;       // 哈萨克族
        public static final int   EM_CITIZENIDCARD_EC_Dai = 18;         // 傣族
        public static final int   EM_CITIZENIDCARD_EC_Li = 19;          // 黎族
        public static final int   EM_CITIZENIDCARD_EC_Lisu = 20;        // 傈僳族
        public static final int   EM_CITIZENIDCARD_EC_Va = 21;          // 佤族
        public static final int   EM_CITIZENIDCARD_EC_She = 22;         // 畲族
        public static final int   EM_CITIZENIDCARD_EC_Gaoshan = 23;     // 高山族
        public static final int   EM_CITIZENIDCARD_EC_Lahu = 24;        // 拉祜族
        public static final int   EM_CITIZENIDCARD_EC_Shui = 25;        // 水族
        public static final int   EM_CITIZENIDCARD_EC_Dongxiang = 26;   // 东乡族
        public static final int   EM_CITIZENIDCARD_EC_Naxi = 27;        // 纳西族
        public static final int   EM_CITIZENIDCARD_EC_Jingpo = 28;      // 景颇族
        public static final int   EM_CITIZENIDCARD_EC_Kirgiz = 29;      // 柯尔克孜族
        public static final int   EM_CITIZENIDCARD_EC_Tu = 30;          // 土族
        public static final int   EM_CITIZENIDCARD_EC_Daur = 31;        // 达斡尔族
        public static final int   EM_CITIZENIDCARD_EC_Mulam = 32;       // 仫佬族
        public static final int   EM_CITIZENIDCARD_EC_Qoiang = 33;      // 羌族
        public static final int   EM_CITIZENIDCARD_EC_Blang = 34;       // 布朗族
        public static final int   EM_CITIZENIDCARD_EC_Salar = 35;       // 撒拉族
        public static final int   EM_CITIZENIDCARD_EC_Maonan = 36;      // 毛南族
        public static final int   EM_CITIZENIDCARD_EC_Gelo = 37;        // 仡佬族
        public static final int   EM_CITIZENIDCARD_EC_Xibe = 38;        // 锡伯族
        public static final int   EM_CITIZENIDCARD_EC_Achang = 39;      // 阿昌族
        public static final int   EM_CITIZENIDCARD_EC_Pumi = 40;        // 普米族
        public static final int   EM_CITIZENIDCARD_EC_Tajik = 41;       // 塔吉克族
        public static final int   EM_CITIZENIDCARD_EC_Nu = 42;          // 怒族
        public static final int   EM_CITIZENIDCARD_EC_Ozbek = 43;       // 乌孜别克族
        public static final int   EM_CITIZENIDCARD_EC_Russian = 44;     // 俄罗斯族
        public static final int   EM_CITIZENIDCARD_EC_Ewenkl = 45;      // 鄂温克族
        public static final int   EM_CITIZENIDCARD_EC_Deang = 46;       // 德昂族
        public static final int   EM_CITIZENIDCARD_EC_Bonan = 47;       // 保安族
        public static final int   EM_CITIZENIDCARD_EC_Yugur = 48;       // 裕固族
        public static final int   EM_CITIZENIDCARD_EC_Jing = 49;        // 京族
        public static final int   EM_CITIZENIDCARD_EC_Tatar = 50;       // 塔塔尔族
        public static final int   EM_CITIZENIDCARD_EC_Drung = 51;       // 独龙族
        public static final int   EM_CITIZENIDCARD_EC_Oroqen = 52;      // 鄂伦春族
        public static final int   EM_CITIZENIDCARD_EC_Hezhen = 53;      // 赫哲族
        public static final int   EM_CITIZENIDCARD_EC_Moinba = 54;      // 门巴族
        public static final int   EM_CITIZENIDCARD_EC_Lhoba = 55;       // 珞巴族
        public static final int   EM_CITIZENIDCARD_EC_Jino = 56;        // 基诺族
    }

    //考勤状态
    public static class NET_ATTENDANCESTATE extends SdkStructure {
        public static final int   NET_ATTENDANCESTATE_UNKNOWN = 0;
        public static final int   NET_ATTENDANCESTATE_SIGNIN = 1;       // 签入
        public static final int   NET_ATTENDANCESTATE_GOOUT = 2;        // 外出
        public static final int   NET_ATTENDANCESTATE_GOOUT_AND_RETRUN = 3; // 外出归来
        public static final int   NET_ATTENDANCESTATE_SIGNOUT = 4;      // 签出
        public static final int   NET_ATTENDANCESTATE_WORK_OVERTIME_SIGNIN = 5; // 加班签到
        public static final int   NET_ATTENDANCESTATE_WORK_OVERTIME_SIGNOUT = 6; // 加班签出
    }

    // 开门方向
    public static class NET_ENUM_DIRECTION_ACCESS_CTL extends SdkStructure {
        public static final int   NET_ENUM_DIRECTION_UNKNOWN = 0;
        public static final int   NET_ENUM_DIRECTION_ENTRY = 1;         // 进门
        public static final int   NET_ENUM_DIRECTION_EXIT = 2;          // 出门
    }

    // 记录集类型
    public static class EM_NET_RECORD_TYPE extends SdkStructure {
        public static final int   NET_RECORD_UNKNOWN = 0;
        public static final int   NET_RECORD_TRAFFICREDLIST = 1;        // 交通允许名单账户记录, 查询条件对应 FIND_RECORD_TRAFFICREDLIST_CONDITION 结构体,记录信息对应 NET_TRAFFIC_LIST_RECORD 结构体
        public static final int   NET_RECORD_TRAFFICBLACKLIST = 2;      // 交通禁止名单账号记录,查询条件对应 FIND_RECORD_TRAFFICREDLIST_CONDITION 结构体,记录信息对应 NET_TRAFFIC_LIST_RECORD 结构体
        public static final int   NET_RECORD_BURN_CASE = 3;             // 刻录案件记录,查询条件对应 FIND_RECORD_BURN_CASE_CONDITION 结构体,记录信息对应 NET_BURN_CASE_INFO 结构体
        public static final int   NET_RECORD_ACCESSCTLCARD = 4;         // 门禁卡,查询条件对应 FIND_RECORD_ACCESSCTLCARD_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARD 结构体
        public static final int   NET_RECORD_ACCESSCTLPWD = 5;          // 门禁密码,查询条件对应 FIND_RECORD_ACCESSCTLPWD_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_PWD
        public static final int   NET_RECORD_ACCESSCTLCARDREC = 6;      // 门禁出入记录（必须同时按卡号和时间段查询,建议用 NET_RECORD_ACCESSCTLCARDREC_EX 查询）,查询条件对应 FIND_RECORD_ACCESSCTLCARDREC_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARDREC 结构体
        public static final int   NET_RECORD_ACCESSCTLHOLIDAY = 7;      // 假日记录集,查询条件对应 FIND_RECORD_ACCESSCTLHOLIDAY_CONDITION 结构体,记录信息对应 NET_RECORDSET_HOLIDAY 结构体
        public static final int   NET_RECORD_TRAFFICFLOW_STATE = 8;     // 查询交通流量记录,查询条件对应 FIND_RECORD_TRAFFICFLOW_CONDITION 结构体,记录信息对应 NET_RECORD_TRAFFIC_FLOW_STATE 结构体
        public static final int   NET_RECORD_VIDEOTALKLOG = 9;          // 通话记录,查询条件对应 FIND_RECORD_VIDEO_TALK_LOG_CONDITION 结构体,记录信息对应 NET_RECORD_VIDEO_TALK_LOG 结构体
        public static final int   NET_RECORD_REGISTERUSERSTATE = 10;    // 状态记录,查询条件对应 FIND_RECORD_REGISTER_USER_STATE_CONDITION 结构体,记录信息对应 NET_RECORD_REGISTER_USER_STATE 结构体
        public static final int   NET_RECORD_VIDEOTALKCONTACT = 11;     // 联系人记录,查询条件对应 FIND_RECORD_VIDEO_TALK_CONTACT_CONDITION 结构体,记录信息对应 NET_RECORD_VIDEO_TALK_CONTACT 结构体
        public static final int   NET_RECORD_ANNOUNCEMENT = 12;         // 公告记录,查询条件对应 FIND_RECORD_ANNOUNCEMENT_CONDITION 结构体,记录信息对应 NET_RECORD_ANNOUNCEMENT_INFO 结构体
        public static final int   NET_RECORD_ALARMRECORD = 13;          // 报警记录,查询条件对应 FIND_RECORD_ALARMRECORD_CONDITION 结构体,记录信息对应 NET_RECORD_ALARMRECORD_INFO 结构体
        public static final int   NET_RECORD_COMMODITYNOTICE = 14;      // 下发商品记录,查询条件对应 FIND_RECORD_COMMODITY_NOTICE_CONDITION 结构体,记录信息对应 NET_RECORD_COMMODITY_NOTICE 结构体
        public static final int   NET_RECORD_HEALTHCARENOTICE = 15;     // 就诊信息记录,查询条件对应 FIND_RECORD_HEALTH_CARE_NOTICE_CONDITION 结构体,记录信息对应 NET_RECORD_HEALTH_CARE_NOTICE 结构体
        public static final int   NET_RECORD_ACCESSCTLCARDREC_EX = 16;  // 门禁出入记录(可选择部分条件查询,建议替代NET_RECORD_ACCESSCTLCARDREC),查询条件对应 FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARDREC 结构体
        public static final int   NET_RECORD_GPS_LOCATION = 17;         // GPS位置信息记录, 只实现import和clear,记录信息对应 NET_RECORD_GPS_LOCATION_INFO 结构体
        public static final int   NET_RECORD_RESIDENT = 18;             // 公租房租户信息,查询条件对应 FIND_RECORD_RESIDENT_CONDTION结构体, 记录信息对应 NET_RECORD_RESIDENT_INFO 结构体
        public static final int   NET_RECORD_SENSORRECORD = 19;         // 监测量数据记录,查询条件对应 FIND_RECORD_SENSORRECORD_CONDITION 结构体,记录信息对应 NET_RECORD_SENSOR_RECORD 结构体
        public static final int   NET_RECORD_ACCESSQRCODE = 20;         // 开门二维码记录集,记录信息对应 NET_RECORD_ACCESSQRCODE_INFO结构体
        public static final int   NET_RECORD_ACCESS_BLUETOOTH = 22;     // 蓝牙开门记录集, 查询条件对应 FIND_RECORD_ACCESS_BLUETOOTH_INFO_CONDITION 结构体, 记录信息对应 NET_RECORD_ACCESS_BLUETOOTH_INFO 结构体
        public static final int   NET_RECORD_ACCESS_CONSUMPTION = 29;   // 消费记录 查询条件对应 FIND_RECORD_ACCESS_CTL_CONSUMPTION_INFO_CONDITION 结构体, 记录信息对应 NET_RECORD_ACCESS_CTL_CONSUMPTION_INFO 结构体
    }

    //交通禁止/允许名单记录信息
    public static class NET_TRAFFIC_LIST_RECORD extends SdkStructure {
        public int              dwSize;
        public int              nRecordNo;                            // 之前查询到的记录号
        public byte[]           szMasterOfCar = new byte[NET_MAX_NAME_LEN]; // 车主姓名
        public byte[]           szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌号码
        public int              emPlateType;                          // 车牌类型,对应EM_NET_PLATE_TYPE
        public int              emPlateColor;                         // 车牌颜色 ，对应EM_NET_PLATE_COLOR_TYPE
        public int              emVehicleType;                        // 车辆类型 ，对应EM_NET_VEHICLE_TYPE
        public int              emVehicleColor;                       // 车身颜色，对应EM_NET_VEHICLE_COLOR_TYPE
        public NET_TIME         stBeginTime;                          // 开始时间
        public NET_TIME         stCancelTime;                         // 撤销时间
        public int              nAuthrityNum;                         // 权限个数
        public NET_AUTHORITY_TYPE[] stAuthrityTypes = new NET_AUTHORITY_TYPE[NET_MAX_AUTHORITY_LIST_NUM]; // 权限列表 , 允许名单仅有
        public int              emControlType;                        // 布控类型 ,禁止名单仅有，对应EM_NET_TRAFFIC_CAR_CONTROL_TYPE
        /**
         布控路线ID
         */
        public	int              nControlledRouteID;
        public	int              nLocation;                            // 车辆所处位置
        public  int             bLocation;                            // 车辆所处位置是否下发，TRUE:下发 -1，FALSE:不下发 -0
        public  byte[]          szCustomParkNo = new byte[32];        // 自定义车位号，适用于车检器场景
        public  byte[]          szProvince = new byte[64];            // 省份类型
        public  byte[]          szMasterOfCarEx = new byte[64];       // 车主姓名(拓展)
        public  int             bIsMasterOfCarExValid;                // 是否使用szMasterOfCarEx下发
        public byte[]           szOwnerRemark = new byte[128];        //车主备注信息
        public byte[]           szDepartMent = new byte[128];         //车主所在部门
        public byte[]           szCardID = new byte[32];              //刷卡开闸的卡号。只有在开闸权限OpenGate为true的情况下，刷卡开闸才有效
        public byte[]           szTelephoneNumber = new byte[20];     //车主联系方式

         public static class ByReference extends NET_TRAFFIC_LIST_RECORD implements Structure.ByReference {}

        public NET_TRAFFIC_LIST_RECORD() {
            for(int i=0;i<NET_MAX_AUTHORITY_LIST_NUM;i++){
                stAuthrityTypes[i]=new NET_AUTHORITY_TYPE();
            }
            this.dwSize = this.size();
        }
    }

    //车牌类型
    public static class EM_NET_PLATE_TYPE extends SdkStructure
    {
        public static final int   NET_PLATE_TYPE_UNKNOWN = 0;
        public static final int   NET_PLATE_TYPE_NORMAL = 1;            // "Normal" 蓝牌黑牌
        public static final int   NET_PLATE_TYPE_YELLOW = 2;            // "Yellow" 黄牌
        public static final int   NET_PLATE_TYPE_DOUBLEYELLOW = 3;      // "DoubleYellow" 双层黄尾牌
        public static final int   NET_PLATE_TYPE_POLICE = 4;            // "Police" 警牌
        public static final int   NET_PLATE_TYPE_WJ = 5;                //
        public static final int   NET_PLATE_TYPE_OUTERGUARD = 6;        //
        public static final int   NET_PLATE_TYPE_DOUBLEOUTERGUARD = 7;  //
        public static final int   NET_PLATE_TYPE_SAR = 8;               // "SAR" 港澳特区号牌
        public static final int   NET_PLATE_TYPE_TRAINNING = 9;         // "Trainning" 教练车号牌
        public static final int   NET_PLATE_TYPE_PERSONAL = 10;         // "Personal" 个性号牌
        public static final int   NET_PLATE_TYPE_AGRI = 11;             // "Agri" 农用牌
        public static final int   NET_PLATE_TYPE_EMBASSY = 12;          // "Embassy" 使馆号牌
        public static final int   NET_PLATE_TYPE_MOTO = 13;             // "Moto" 摩托车号牌
        public static final int   NET_PLATE_TYPE_TRACTOR = 14;          // "Tractor" 拖拉机号牌
        public static final int   NET_PLATE_TYPE_OFFICIALCAR = 15;      // "OfficialCar " 公务车
        public static final int   NET_PLATE_TYPE_PERSONALCAR = 16;      // "PersonalCar" 私家车
        public static final int   NET_PLATE_TYPE_WARCAR = 17;           // "WarCar"  军用
        public static final int   NET_PLATE_TYPE_OTHER = 18;            // "Other" 其他号牌
        public static final int   NET_PLATE_TYPE_CIVILAVIATION = 19;    // "Civilaviation" 民航号牌
        public static final int   NET_PLATE_TYPE_BLACK = 20;            // "Black" 黑牌
        public static final int   NET_PLATE_TYPE_PURENEWENERGYMICROCAR = 21; // "PureNewEnergyMicroCar" 纯电动新能源小车
        public static final int   NET_PLATE_TYPE_MIXEDNEWENERGYMICROCAR = 22; // "MixedNewEnergyMicroCar" 混合新能源小车
        public static final int   NET_PLATE_TYPE_PURENEWENERGYLARGECAR = 23; // "PureNewEnergyLargeCar" 纯电动新能源大车
        public static final int   NET_PLATE_TYPE_MIXEDNEWENERGYLARGECAR = 24; // "MixedNewEnergyLargeCar" 混合新能源大车
    }

    //车牌颜色
    public static class EM_NET_PLATE_COLOR_TYPE extends SdkStructure
    {
        public static final int   NET_PLATE_COLOR_OTHER = 0;            // 其他颜色
        public static final int   NET_PLATE_COLOR_BLUE = 1;             // 蓝色 "Blue"
        public static final int   NET_PLATE_COLOR_YELLOW = 2;           // 黄色 "Yellow"
        public static final int   NET_PLATE_COLOR_WHITE = 3;            // 白色 "White"
        public static final int   NET_PLATE_COLOR_BLACK = 4;            // 黑色 "Black"
        public static final int   NET_PLATE_COLOR_YELLOW_BOTTOM_BLACK_TEXT = 5; // 黄底黑字 "YellowbottomBlackText"
        public static final int   NET_PLATE_COLOR_BLUE_BOTTOM_WHITE_TEXT = 6; // 蓝底白字 "BluebottomWhiteText"
        public static final int   NET_PLATE_COLOR_BLACK_BOTTOM_WHITE_TEXT = 7; // 黑底白字 "BlackBottomWhiteText"
        public static final int   NET_PLATE_COLOR_SHADOW_GREEN = 8;     // 渐变绿 "ShadowGreen"
        public static final int   NET_PLATE_COLOR_YELLOW_GREEN = 9;     // 黄绿双拼 "YellowGreen"
    }

    //车辆类型
    public static class EM_NET_VEHICLE_TYPE extends SdkStructure
    {
        public static final int   NET_VEHICLE_TYPE_UNKNOW = 0;          // 未知类型
        public static final int   NET_VEHICLE_TYPE_MOTOR = 1;           // "Motor" 机动车
        public static final int   NET_VEHICLE_TYPE_NON_MOTOR = 2;       // "Non-Motor"非机动车
        public static final int   NET_VEHICLE_TYPE_BUS = 3;             // "Bus"公交车
        public static final int   NET_VEHICLE_TYPE_BICYCLE = 4;         // "Bicycle" 自行车
        public static final int   NET_VEHICLE_TYPE_MOTORCYCLE = 5;      // "Motorcycle"摩托车
        public static final int   NET_VEHICLE_TYPE_UNLICENSEDMOTOR = 6; // "UnlicensedMotor": 无牌机动车
        public static final int   NET_VEHICLE_TYPE_LARGECAR = 7;        // "LargeCar"  大型汽车
        public static final int   NET_VEHICLE_TYPE_MICROCAR = 8;        // "MicroCar" 小型汽车
        public static final int   NET_VEHICLE_TYPE_EMBASSYCAR = 9;      // "EmbassyCar" 使馆汽车
        public static final int   NET_VEHICLE_TYPE_MARGINALCAR = 10;    // "MarginalCar" 领馆汽车
        public static final int   NET_VEHICLE_TYPE_AREAOUTCAR = 11;     // "AreaoutCar" 境外汽车
        public static final int   NET_VEHICLE_TYPE_FOREIGNCAR = 12;     // "ForeignCar" 外籍汽车
        public static final int   NET_VEHICLE_TYPE_DUALTRIWHEELMOTORCYCLE = 13; // "DualTriWheelMotorcycle"两、三轮摩托车
        public static final int   NET_VEHICLE_TYPE_LIGHTMOTORCYCLE = 14; // "LightMotorcycle" 轻便摩托车
        public static final int   NET_VEHICLE_TYPE_EMBASSYMOTORCYCLE = 15; // "EmbassyMotorcycle "使馆摩托车
        public static final int   NET_VEHICLE_TYPE_MARGINALMOTORCYCLE = 16; // "MarginalMotorcycle "领馆摩托车
        public static final int   NET_VEHICLE_TYPE_AREAOUTMOTORCYCLE = 17; // "AreaoutMotorcycle "境外摩托车
        public static final int   NET_VEHICLE_TYPE_FOREIGNMOTORCYCLE = 18; // "ForeignMotorcycle "外籍摩托车
        public static final int   NET_VEHICLE_TYPE_FARMTRANSMITCAR = 19; // "FarmTransmitCar" 农用运输车
        public static final int   NET_VEHICLE_TYPE_TRACTOR = 20;        // "Tractor" 拖拉机
        public static final int   NET_VEHICLE_TYPE_TRAILER = 21;        // "Trailer"  挂车
        public static final int   NET_VEHICLE_TYPE_COACHCAR = 22;       // "CoachCar"教练汽车
        public static final int   NET_VEHICLE_TYPE_COACHMOTORCYCLE = 23; // "CoachMotorcycle "教练摩托车
        public static final int   NET_VEHICLE_TYPE_TRIALCAR = 24;       // "TrialCar" 试验汽车
        public static final int   NET_VEHICLE_TYPE_TRIALMOTORCYCLE = 25; // "TrialMotorcycle "试验摩托车
        public static final int   NET_VEHICLE_TYPE_TEMPORARYENTRYCAR = 26; // "TemporaryEntryCar"临时入境汽车
        public static final int   NET_VEHICLE_TYPE_TEMPORARYENTRYMOTORCYCLE = 27; // "TemporaryEntryMotorcycle"临时入境摩托车
        public static final int   NET_VEHICLE_TYPE_TEMPORARYSTEERCAR = 28; // "TemporarySteerCar"临时行驶车
        public static final int   NET_VEHICLE_TYPE_PASSENGERCAR = 29;   // "PassengerCar" 客车
        public static final int   NET_VEHICLE_TYPE_LARGETRUCK = 30;     // "LargeTruck" 大货车
        public static final int   NET_VEHICLE_TYPE_MIDTRUCK = 31;       // "MidTruck" 中货车
        public static final int   NET_VEHICLE_TYPE_SALOONCAR = 32;      // "SaloonCar" 轿车
        public static final int   NET_VEHICLE_TYPE_MICROBUS = 33;       // "Microbus"面包车
        public static final int   NET_VEHICLE_TYPE_MICROTRUCK = 34;     // "MicroTruck"小货车
        public static final int   NET_VEHICLE_TYPE_TRICYCLE = 35;       // "Tricycle"三轮车
        public static final int   NET_VEHICLE_TYPE_PASSERBY = 36;       // "Passerby" 行人
    }

    //车身颜色
    public static class EM_NET_VEHICLE_COLOR_TYPE extends SdkStructure
    {
        public static final int   NET_VEHICLE_COLOR_OTHER = 0;          // 其他颜色
        public static final int   NET_VEHICLE_COLOR_WHITE = 1;          // 白色	"White"
        public static final int   NET_VEHICLE_COLOR_BLACK = 2;          // 黑色	"Black"
        public static final int   NET_VEHICLE_COLOR_RED = 3;            // 红色	"Red"
        public static final int   NET_VEHICLE_COLOR_YELLOW = 4;         // 黄色	"Yellow"
        public static final int   NET_VEHICLE_COLOR_GRAY = 5;           // 灰色	"Gray"
        public static final int   NET_VEHICLE_COLOR_BLUE = 6;           // 蓝色	"Blue"
        public static final int   NET_VEHICLE_COLOR_GREEN = 7;          // 绿色	"Green"
        public static final int   NET_VEHICLE_COLOR_PINK = 8;           // 粉红色 "Pink"
        public static final int   NET_VEHICLE_COLOR_PURPLE = 9;         // 紫色	"Purple"
        public static final int   NET_VEHICLE_COLOR_BROWN = 10;         // 棕色	"Brown"
    }

    // 交通流量记录
    public static class NET_RECORD_TRAFFIC_FLOW_STATE extends SdkStructure {
        public int              dwSize;
        public int              nRecordNum;                           // 记录编号
        public int              nChannel;                             // 通道号
        public int              nLane;                                // 车道号
        public int              nVehicles;                            // 通过车辆总数
        public float            fAverageSpeed;                        // 平均车速,单位km/h
        public float            fTimeOccupyRatio;                     // 时间占有率,即单位时间内通过断面的车辆所用时间的总和占单位时间的比例
        public float            fSpaceOccupyRatio;                    // 空间占有率,即按百分率计量的车辆长度总和除以时间间隔内车辆平均行驶距离
        public float            fSpaceHeadway;                        // 车头间距,相邻车辆之间的距离,单位米/辆
        public float            fTimeHeadway;                         // 车头时距,单位秒/辆
        public int              nLargeVehicles;                       // 大车交通量(9米<车长<12米),辆/单位时间
        public int              nMediumVehicles;                      // 中型车交通量(6米<车长<9米),辆/单位时间
        public int              nSmallVehicles;                       // 小车交通量(4米<车长<6米),辆/单位时间,
        public float            fBackOfQueue;                         // 排队长度,单位：米, 从信号交叉口停车线到上游排队车辆末端之间的距离
        public int              nPasserby;                            // 通过行人数

        public NET_RECORD_TRAFFIC_FLOW_STATE() {
            this.dwSize = this.size();
        }
    }

    //权限列表 , 允许名单仅有
    public static class NET_AUTHORITY_TYPE extends SdkStructure {
        public int              dwSize;
        public int              emAuthorityType;                      //权限类型，对应EM_NET_AUTHORITY_TYPE
        public int              bAuthorityEnable;                     //权限使能

        public NET_AUTHORITY_TYPE() {
            this.dwSize = this.size();
        }
    }

    //权限类型
    public static class EM_NET_AUTHORITY_TYPE extends SdkStructure {
        public static final int   NET_AUTHORITY_UNKNOW = 0;
        public static final int   NET_AUTHORITY_OPEN_GATE = 1;          //开闸权限
    }

    //布控类型
    public static class EM_NET_TRAFFIC_CAR_CONTROL_TYPE extends SdkStructure
    {
        public static final int   NET_CAR_CONTROL_OTHER = 0;
        public static final int   NET_CAR_CONTROL_OVERDUE_NO_CHECK = 1; // 过期未检	"OverdueNoCheck"
        public static final int   NET_CAR_CONTROL_BRIGANDAGE_CAR = 2;   // 	"BrigandageCar"
        public static final int   NET_CAR_CONTROL_BREAKING = 3;         // 违章		"Breaking"
        public static final int   NET_CAR_CONTROL_CAUSETROUBLE_ESCAPE = 4; // "CausetroubleEscape"
        public static final int   NET_CAR_CONTROL_CAUSETROUBLE_OVERDUEPARKING = 5; // 停车欠费  "OverdueParking"
        public static final int   NET_CAR_CONTROL_COUNTERFEI_PLATE_CAR = 6; // 假牌车              "CounterfeitPlateCar"
        public static final int   NET_CAR_CONTROL_FAKE_PLATE_CAR = 7;   // 套牌车              "FakePlateCar"
        public static final int   NET_CAR_CONTROL_FOCAL_CAR = 8;        // 重点车辆          "FocalCar"
        public static final int   NET_CAR_CONTROL_GUARANTEE_CAR = 9;    // 保障车辆          "GuaranteeCar"
        public static final int   NET_CAR_CONTROL_FOLLOW_CAR = 10;      // 关注车辆          "FollowCar"
    }

    // 呼叫类型
    public static class EM_VIDEO_TALK_LOG_CALLTYPE extends SdkStructure {
        public static final int   EM_VIDEO_TALK_LOG_CALLTYPE_UNKNOWN = 0; // 未知
        public static final int   EM_VIDEO_TALK_LOG_CALLTYPE_INCOMING = 1; // 呼入
        public static final int   EM_VIDEO_TALK_LOG_CALLTYPE_OUTGOING = 2; // 呼出
        public static final int   EM_VIDEO_TALK_LOG_CALLTYPE_MAX = 3;
    }

    // 最终状态
    public static class EM_VIDEO_TALK_LOG_ENDSTATE extends SdkStructure {
        public static final int   EM_VIDEO_TALK_LOG_ENDSTATE_UNKNOWN = 0; // 未知
        public static final int   EM_VIDEO_TALK_LOG_ENDSTATE_MISSED = 1; // 未接
        public static final int   EM_VIDEO_TALK_LOG_ENDSTATE_RECEIVED = 2; // 已接
        public static final int   EM_VIDEO_TALK_LOG_ENDSTATE_MAX = 3;
    }

    // 对方类型
    public static class EM_VIDEO_TALK_LOG_PEERTYPE extends SdkStructure {
        public static final int   EM_VIDEO_TALK_LOG_PEERTYPE_UNKNOWN = 0; // 未知
        public static final int   EM_VIDEO_TALK_LOG_PEERTYPE_VTO = 1;   // 门口机
        public static final int   EM_VIDEO_TALK_LOG_PEERTYPE_VTH = 2;   // 室内机
        public static final int   EM_VIDEO_TALK_LOG_PEERTYPE_VTS = 3;   // 管理中心
    }

    // 通话记录查询条件
    public static class FIND_RECORD_VIDEO_TALK_LOG_CONDITION extends SdkStructure {
        public int              dwSize;
        public int              bCallTypeEnable;                      // 呼叫类型查询条件是否有效
        public int              nCallTypeListNum;                     // 对应 emCallTypeList 中有效枚举个数
        public int[]            emCallTypeList = new int[NET_MAX_CALLTYPE_LIST_NUM]; // 呼叫类型使能列表, 详见EM_VIDEO_TALK_LOG_CALLTYPE
        public int              bEndStateEnable;                      // 最终状态查询条件是否有效
        public int              nEndStateListNum;                     // 对应 emEndStateList 中有效枚举个数
        public int[]            emEndStateList = new int[NET_MAX_ENDSTATE_LIST_NUM]; // 最终状态使能列表, 详见EM_VIDEO_TALK_LOG_ENDSTATE
        public int              bTimeEnable;                          // 启用时间段查询
        public NET_TIME         stStartTime;                          // 起始时间
        public NET_TIME         stEndTime;                            // 结束时间

        public FIND_RECORD_VIDEO_TALK_LOG_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 通话记录记录集信息
    public static class NET_RECORD_VIDEO_TALK_LOG extends SdkStructure {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public NET_TIME         stuCreateTime;                        // 开始时间
        public int              emCallType;                           // 呼叫类型,详见EM_VIDEO_TALK_LOG_CALLTYPE
        public int              emEndState;                           // 最终状态,详见EM_VIDEO_TALK_LOG_ENDSTATE
        public byte[]           szPeerNumber = new byte[NET_COMMON_STRING_64]; // 对方号码 可以是短号,中号或长号
        public int              emPeerType;                           // 对方类型,详见EM_VIDEO_TALK_LOG_PEERTYPE
        public byte[]           szLocalNumber = new byte[NET_COMMON_STRING_64]; // 本机号码 可以是短号,中号或长号
        public int              nTalkTime;                            // 通话时间 单位秒
        public int              nMessageTime;                         // 留言时间 单位秒
        public byte[]           szPicturePath = new byte[NET_COMMON_STRING_128]; // 图片路径
        public int              emOfflineCall;                        // 平台断线是否呼出,NET_EM_OFFLINE_CALL_TYPE
        public NET_TIME_EX      stuCreateTimeRealUTC = new NET_TIME_EX(); // 真实UTC开始时间

        public NET_RECORD_VIDEO_TALK_LOG() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FindRecord接口输出参数
    public static class NET_OUT_FIND_RECORD_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public LLong            lFindeHandle;                         // 查询记录句柄,唯一标识某次查询

        public NET_OUT_FIND_RECORD_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FindNextRecord接口输入参数
    public static class NET_IN_FIND_NEXT_RECORD_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public LLong            lFindeHandle;                         // 查询句柄
        public int              nFileCount;                           // 当前想查询的记录条数

        public NET_IN_FIND_NEXT_RECORD_PARAM() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_FindNextRecord接口输出参数
    public static class NET_OUT_FIND_NEXT_RECORD_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public Pointer          pRecordList;                          // 记录列表,用户分配内存，对应 交通禁止/允许名单记录信息 NET_TRAFFIC_LIST_RECORD
        public int              nMaxRecordNum;                        // 列表记录数
        public int              nRetRecordNum;                        // 查询到的记录条数,当查询到的条数小于想查询的条数时,查询结束

        public NET_OUT_FIND_NEXT_RECORD_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryRecordCount接口输入参数
    public static class NET_IN_QUEYT_RECORD_COUNT_PARAM extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public LLong            lFindeHandle;                         //查询句柄

        public NET_IN_QUEYT_RECORD_COUNT_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_QueryRecordCount接口输出参数
    public static class NET_OUT_QUEYT_RECORD_COUNT_PARAM extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public int              nRecordCount;                         //设备返回的记录条数

        public NET_OUT_QUEYT_RECORD_COUNT_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_OperateTrafficList接口输入参数,
    public static class NET_IN_OPERATE_TRAFFIC_LIST_RECORD extends SdkStructure {
        public int              dwSize;
        public int              emOperateType;                        // emOperateType对应EM_RECORD_OPERATE_TYPE
        public int              emRecordType;                         // 要操作记录信息类型,emRecordType对应EM_NET_RECORD_TYPE
        public Pointer          pstOpreateInfo;                       // 对应 添加NET_INSERT_RECORD_INFO/ 删除NET_REMOVE_RECORD_INFO / 修改NET_UPDATE_RECORD_INFO

        public NET_IN_OPERATE_TRAFFIC_LIST_RECORD() {
            this.dwSize = this.size();
        }
    }

    // 添加
    public static class NET_INSERT_RECORD_INFO extends SdkStructure {
        public int              dwSize;
        public NET_TRAFFIC_LIST_RECORD.ByReference pRecordInfo = new NET_TRAFFIC_LIST_RECORD.ByReference(); // 记录内容信息

        public NET_INSERT_RECORD_INFO () {
            this.dwSize = this.size();
        }
    }

    // 删除
    public static class NET_REMOVE_RECORD_INFO extends SdkStructure {
        public int              dwSize;
        public int              nRecordNo;                            // 之前查询到的记录号，对应NET_TRAFFIC_LIST_RECORD里的nRecordNo

        public NET_REMOVE_RECORD_INFO() {
            this.dwSize = this.size();
        }
    }

    // 修改
    public static class NET_UPDATE_RECORD_INFO extends SdkStructure {
        public int              dwSize;
        public NET_TRAFFIC_LIST_RECORD.ByReference pRecordInfo = new NET_TRAFFIC_LIST_RECORD.ByReference(); // 记录内容信息 ，对应  NET_TRAFFIC_LIST_RECORD

        public NET_UPDATE_RECORD_INFO() {
            this.dwSize = this.size();
        }
    }

    // 禁止/允许名单操作类型
    public static class EM_RECORD_OPERATE_TYPE extends SdkStructure {
        public static final int   NET_TRAFFIC_LIST_INSERT = 0;          // 增加记录操作
        public static final int   NET_TRAFFIC_LIST_UPDATE = 1;          // 更新记录操作
        public static final int   NET_TRAFFIC_LIST_REMOVE = 2;          // 删除记录操作
        public static final int   NET_TRAFFIC_LIST_MAX = 3;
    }

    // CLIENT_OperateTrafficList接口输出参数,现阶段实现的操作接口中,只有返回nRecordNo的操作,stRetRecord暂时不可用,是null
    public static class NET_OUT_OPERATE_TRAFFIC_LIST_RECORD extends SdkStructure {
        public int              dwSize;
        public int              nRecordNo;                            //记录号

        public NET_OUT_OPERATE_TRAFFIC_LIST_RECORD() {
            this.dwSize = this.size();
        }
    }

    // 记录集操作参数
    public static class NET_CTRL_RECORDSET_PARAM extends SdkStructure {
        public int              dwSize;
        public int              emType;                               // 记录集信息类型,对应EM_NET_RECORD_TYPE
        public Pointer          pBuf;                                 // 新增\更新\查询\导入时,为记录集信息缓存,详见EM_NET_RECORD_TYPE注释
                                                                 // 删除时,为存放记录集编号的内存地址(类型为int*), 批量删除时，为NET_CTRL_RECORDSET_REMOVEEX_PARAM, 由用户申请内存, 长度为nBufLen
        public int              nBufLen;                              // 记录集信息缓存大小

        public NET_CTRL_RECORDSET_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 禁止/允许名单上传
    public static class NETDEV_BLACKWHITE_LIST_INFO extends SdkStructure {
        public byte[]           szFile = new byte[MAX_PATH_STOR];     // 禁止/允许名单文件路径
        public int              nFileSize;                            // 升级文件大小
        public byte             byFileType;                           // 当前文件类型,0-禁止名单,1-允许名单
        public byte             byAction;                             // 动作,0-覆盖,1-追加
        public byte[]           byReserved = new byte[126];           // 保留
    }

    // GPS信息(车载设备)
    public static class GPS_Info extends SdkStructure {
        public NET_TIME         revTime;                              // 定位时间
        public byte[]           DvrSerial = new byte[50];             // 设备序列号
        public double           longitude;                            // 经度(单位是百万分之度,范围0-360度)
        public double           latidude;                             // 纬度(单位是百万分之度,范围0-180度)
        public double           height;                               // 高度(米)
        public double           angle;                                // 方向角(正北方向为原点,顺时针为正)
        public double           speed;                                // 速度(单位是海里,speed/1000*1.852公里/小时)
        public short            starCount;                            // 定位星数,无符号
        public int              antennaState;                         // 天线状态(true 好,false 坏)
        public int              orientationState;                     // 定位状态(true 定位,false 不定位)

        public static class ByValue extends GPS_Info implements SdkStructure.ByValue { }
    }

    // 报警状态信息
    public static class ALARM_STATE_INFO extends SdkStructure {
        public int              nAlarmCount;                          // 发生的报警事件个数
        public int[]            nAlarmState = new int[128];           // 发生的报警事件类型
        public byte[]           byRserved = new byte[128];            // 保留字节

        public static class ByValue extends ALARM_STATE_INFO implements SdkStructure.ByValue { }
    }

    // 对应CLIENT_SearchDevicesByIPs接口
    public static class DEVICE_IP_SEARCH_INFO extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              nIpNum;                               // 当前搜索的IP个数
        public DEVICE_IP[]      szIPArr = (DEVICE_IP[])new DEVICE_IP().toArray(NET_MAX_SAERCH_IP_NUM); // 具体待搜索的IP信息数组
        public fSearchDevicesCBEx cbSearchDevicesEx;                  //设备信息回调函数,参见回调函数定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.fSearchDevicesCBEx}

        public DEVICE_IP_SEARCH_INFO() {
            this.dwSize = this.size();
        }
    }

    // 具体待搜索的IP信息
    public static class DEVICE_IP extends SdkStructure {
        public byte[]           szIP = new byte[64];                  // 具体待搜索的IP信息
    }

    // CLIENT_UploadRemoteFile 接口输入参数(上传文件到设备)
    public static class NET_IN_UPLOAD_REMOTE_FILE extends SdkStructure {
        public int              dwSize;
        public Pointer          pszFileSrc;                           // 源文件路径
        public Pointer          pszFileDst;                           // 目标文件路径
        public Pointer          pszFolderDst;                         // 目标文件夹路径：可为NULL, NULL时设备使用默认路径
        public int              nPacketLen;                           // 文件分包大小(字节): 0表示不分包

        public NET_IN_UPLOAD_REMOTE_FILE(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_UploadRemoteFile 接口输出参数(上传文件到设备)
    public static class NET_OUT_UPLOAD_REMOTE_FILE extends SdkStructure {
        public int              dwSize;

        public NET_OUT_UPLOAD_REMOTE_FILE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ListRemoteFile 接口输入参数
    public static class NET_IN_LIST_REMOTE_FILE extends SdkStructure
    {
        public int              dwSize;
        public String           pszPath;                              // 路径
        public int              bFileNameOnly;                        // 只获取文件名称, 不返回文件夹信息, 文件信息中只有文件名有效, BOOL类型
        public int              emCondition;                          // 指定获取文件的条件, 对应  NET_REMOTE_FILE_COND

        public NET_IN_LIST_REMOTE_FILE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ListRemoteFile 接口输出参数
    public static class NET_OUT_LIST_REMOTE_FILE extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pstuFiles;                            // 文件信息数组, 用户分配内存, 对应 NET_REMOTE_FILE_INFO[],大小为sizeof(NET_REMOTE_FILE_INFO)*nMaxFileCount
        public int              nMaxFileCount;                        // 文件信息数组大小, 用户填写
        public int              nRetFileCount;                        // 返回的文件数量

        public NET_OUT_LIST_REMOTE_FILE() {
            this.dwSize = this.size();
        }
    }

    // 文件/目录信息
    public static class SDK_REMOTE_FILE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              bDirectory;                           // 是否文件夹, BOOL类型
        public byte[]           szPath = new byte[MAX_PATH];          // 路径
        public NET_TIME         stuCreateTime;                        // 创建时间
        public NET_TIME         stuModifyTime;                        // 修改时间
        public long             nFileSize;                            // 文件大小
        public byte[]           szFileType = new byte[NET_FILE_TYPE_LEN]; // 文件类型

        public SDK_REMOTE_FILE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取文件的条件
    public static class NET_REMOTE_FILE_COND extends SdkStructure
    {
        public static final int   NET_REMOTE_FILE_COND_NONE = 0;        // 无条件
        public static final int   NET_REMOTE_FILE_COND_VOICE = 1;       // 语音联动的文件,*无法*按路径获取,*只能*获取获取文件名称
    }

    // CLIENT_RemoveRemoteFiles 接口输入参数
    public static class NET_IN_REMOVE_REMOTE_FILES extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pszPathPointer;                       // 文件路径数组指针,对应 FILE_PATH[]
        public int              nFileCount;                           // 文件路径数量

        public NET_IN_REMOVE_REMOTE_FILES() {
            this.dwSize = this.size();
        }
    }

    public static class FILE_PATH extends SdkStructure {
        public String           pszPath;
    }

    // CLIENT_RemoveRemoteFiles 接口输出参数
    public static class NET_OUT_REMOVE_REMOTE_FILES extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_REMOVE_REMOTE_FILES() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlAttachRecord()接口输入参数
    public static class NET_IN_PARKING_CONTROL_PARAM extends SdkStructure {
        public int              dwSize;
        public Callback         cbCallBack;                           // 数据回调函数,fParkingControlRecordCallBack 回调
        public Pointer          dwUser;                               // 用户定义参数

        public NET_IN_PARKING_CONTROL_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlDetachRecord()接口输出参数
    public static class NET_OUT_PARKING_CONTROL_PARAM extends SdkStructure {
        public int              dwSize;

        public NET_OUT_PARKING_CONTROL_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 过车记录信息
    public static class NET_CAR_PASS_ITEM extends SdkStructure {
        public int              dwSize;
        public NET_TIME         stuTime;                              // 过车时间
        public int              dwCardNo;                             // 卡号
        public int              emCardType;                           // 智能停车系统出入口机IC卡用户类型,对应 NET_ECK_IC_CARD_USER_TYPE
        public int              emFlag;                               // 过车记录类型，对应 NET_ECK_CAR_PASS_FLAG

        public NET_CAR_PASS_ITEM(){
            this.dwSize = this.size();
        }
    }

    // 智能停车系统出入口机IC卡用户类型
    public static class NET_ECK_IC_CARD_USER_TYPE extends SdkStructure {
        public static final int   NET_ECK_IC_CARD_USER_UNKNOWN = 0;
        public static final int   NET_ECK_IC_CARD_USER_ALL = 1;         // 全部类型
        public static final int   NET_ECK_IC_CARD_USER_TEMP = 2;        // 临时用户
        public static final int   NET_ECK_IC_CARD_USER_LONG = 3;        // 长期用户
        public static final int   NET_ECK_IC_CARD_USER_ADMIN = 4;       // 管理员
        public static final int   NET_ECK_IC_CARD_USER_BLACK_LIST = 5;  // 禁止名单
    }

    // 智能停车系统出入口机异常过车记录类型
    public static class NET_ECK_CAR_PASS_FLAG extends SdkStructure {
        public static final int   NET_ECK_CAR_PASS_FLAG_NORMAL = 0;     // 正常
        public static final int   NET_ECK_CAR_PASS_FLAG_ABNORMAL = 1;   // 异常
        public static final int   NET_ECK_CAR_PASS_FLAG_ALL = 2;        // 全部
    }

    // CLIENT_ParkingControlStartFind接口输入参数******************
    public static class NET_IN_PARKING_CONTROL_START_FIND_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              bSearchCount;                         // 查询记录调试是否有效
        public int              dwSearchCount;                        // 查询记录条数, 数值范围1~100
        public int              bBegin;                               // 查询开始时间是否有效
        public NET_TIME         stuBegin;                             // 查询开始时间
        public int              bEnd;                                 // 查询结束时间是否有效
        public NET_TIME         stuEnd;                               // 查询结束时间
        public int              bCardType;                            // 卡类型是否有效
        public int              emCardType;                           // 卡类型,对应 NET_ECK_IC_CARD_USER_TYPE
        public int              bFlag;                                // 过车标记是否有效
        public int              emFlag;                               // 过车标记，对应 NET_ECK_CAR_PASS_FLAG

        public NET_IN_PARKING_CONTROL_START_FIND_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlStartFind接口输出参数
    public static class NET_OUT_PARKING_CONTROL_START_FIND_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              dwTotalCount;                         // 符合此次查询条件的结果总条数

        public NET_OUT_PARKING_CONTROL_START_FIND_PARAM(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlDoFind接口输入参数*******************
    public static class NET_IN_PARKING_CONTROL_DO_FIND_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              dwFileCount;                          // 当前想查询的记录条数

        public NET_IN_PARKING_CONTROL_DO_FIND_PARAM(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlDoFind接口输出参数
    public static class NET_OUT_PARKING_CONTROL_DO_FIND_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public Pointer          pstuRecordList;                       // 记录列表,用户分配内存,对应NET_CAR_PASS_ITEM[],大小nMaxRecordNum个NET_CAR_PASS_ITEM
        public int              nMaxRecordNum;                        // 列表记录数
        public int              nRetRecordNum;                        // 查询到的记录条数,当查询到的条数小于想查询的条数时,查询结束

        public NET_OUT_PARKING_CONTROL_DO_FIND_PARAM(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlAttachParkInfo()接口输入参数
    public static class NET_IN_PARK_INFO_PARAM extends SdkStructure
    {
        public int              dwSize;
        public NET_PARK_INFO_FILTER stuFilter;
        public Callback         cbCallBack;                           // 数据回调函数,fParkInfoCallBack 回调
        public Pointer          dwUser;                               // 用户定义参数

        public NET_IN_PARK_INFO_PARAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ParkingControlAttachParkInfo()接口输出参数
    public static class NET_OUT_PARK_INFO_PARAM extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_PARK_INFO_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 车位检测器信息查询条件
    public static class NET_PARK_INFO_FILTER extends SdkStructure
    {
        public int              dwSize;
        public int              dwNum;                                // 车位检测器类型数量
        public int[]            emType = new int[NET_ECK_PARK_DETECTOR_TYPE.NET_ECK_PARK_DETECTOR_TYPE_ALL]; // 车位检测器类型

        public NET_PARK_INFO_FILTER() {
            this.dwSize = this.size();
        }
    }

    // 车位检测器类型
    public static class NET_ECK_PARK_DETECTOR_TYPE extends SdkStructure
    {
        public static final int   NET_ECK_PARK_DETECTOR_TYPE_SONIC = 0; // 超声波探测器
        public static final int   NET_ECK_PARK_DETECTOR_TYPE_CAMERA = 1; // 相机检测器
        public static final int   NET_ECK_PARK_DETECTOR_TYPE_ALL = 2;
    }

    // 车位信息
    public static class NET_PARK_INFO_ITEM extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szParkNo = new byte[NET_COMMON_STRING_32]; // 车位号
        public int              emState;                              // 车位状态,对应  NET_ECK_PARK_STATE
        public int              dwScreenIndex;                        // 车位号显示对应的诱导屏分屏号
        public int              dwFreeParkNum;                        // 屏号显示的当前空余车位数目

        public NET_PARK_INFO_ITEM(){
            this.dwSize = this.size();
        }
    }

    // 智能停车系统车位状态
    public static class NET_ECK_PARK_STATE extends SdkStructure
    {
        public static final int   NET_ECK_PARK_STATE_UNKOWN = 0;
        public static final int   NET_ECK_PARK_STATE_PARK = 1;          // 车位有车
        public static final int   NET_ECK_PARK_STATE_NOPARK = 2;        // 车位无车
    }

    // 智能停车系统出入口机设置车位信息 参数 NET_CTRL_ECK_SET_PARK_INFO
    public static class NET_CTRL_ECK_SET_PARK_INFO_PARAM extends SdkStructure
    {
        public int              dwSize;
        public int              nScreenNum;                           // 屏数量, 不超过 ECK_SCREEN_NUM_MAX
        public int[]            nScreenIndex = new int[ECK_SCREEN_NUM_MAX]; // 屏号, 每个元素表示屏序号
        public int[]            nFreeParkNum = new int[ECK_SCREEN_NUM_MAX]; // 对应屏管理下的空余车位数
        // 长度和下标与nScreenIndex一致,每个元素表示对应屏号下的空余车位

        public NET_CTRL_ECK_SET_PARK_INFO_PARAM(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_PowerControl接口输入参数(电视墙电源控制)
    public static class NET_IN_WM_POWER_CTRL extends SdkStructure
    {
        public int              dwSize;
        public int              nMonitorWallID;                       // 电视墙序号
        public String           pszBlockID;                           // 区块ID, NULL/""-所有区块
        public int              nTVID;                                // 显示单元序号, -1表示区块中所有显示单元
        public int              bPowerOn;                             // 是否打开电源

        public NET_IN_WM_POWER_CTRL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_PowerControl接口输出参数(电视墙电源控制)
    public static class NET_OUT_WM_POWER_CTRL extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_WM_POWER_CTRL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_LoadMonitorWallCollection接口输入参数(载入电视墙预案)
    public static class NET_IN_WM_LOAD_COLLECTION extends SdkStructure
    {
        public int              dwSize;
        public int              nMonitorWallID;                       // 电视墙序号
        public Pointer          pszName;                              // 预案名称

        public NET_IN_WM_LOAD_COLLECTION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_LoadMonitorWallCollection接口输出参数(载入电视墙预案)
    public static class NET_OUT_WM_LOAD_COLLECTION extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_WM_LOAD_COLLECTION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SaveMonitorWallCollection接口输入参数(保存电视墙预案)
    public static class NET_IN_WM_SAVE_COLLECTION extends SdkStructure
    {
        public int              dwSize;
        public int              nMonitorWallID;                       // 电视墙序号
        public Pointer          pszName;                              // 预案名称
        public Pointer          pszControlID;                         // 控制id
        public byte[]           bReserverd = new byte[4];             // 保留字节，用于字节对齐
        public int              emType;                               // 预案类型 EM_SAVE_COLLECTION_TYPE

        public NET_IN_WM_SAVE_COLLECTION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SaveMonitorWallCollection接口输出参数(保存电视墙预案)
    public static class NET_OUT_WM_SAVE_COLLECTION extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_WM_SAVE_COLLECTION() {
            this.dwSize = this.size();
        }
    }

    // 分割模式
    public static class NET_SPLIT_MODE extends SdkStructure
    {
        public static final int   NET_SPLIT_1 = 1;                      // 1画面
        public static final int   NET_SPLIT_2 = 2;                      // 2画面
        public static final int   NET_SPLIT_4 = 4;                      // 4画面
        public static final int   NET_SPLIT_5 = 5;                      // 5画面
        public static final int   NET_SPLIT_6 = 6;                      // 6画面
        public static final int   NET_SPLIT_8 = 8;                      // 8画面
        public static final int   NET_SPLIT_9 = 9;                      // 9画面
        public static final int   NET_SPLIT_12 = 12;                    // 12画面
        public static final int   NET_SPLIT_16 = 16;                    // 16画面
        public static final int   NET_SPLIT_20 = 20;                    // 20画面
        public static final int   NET_SPLIT_25 = 25;                    // 25画面
        public static final int   NET_SPLIT_36 = 36;                    // 36画面
        public static final int   NET_SPLIT_64 = 64;                    // 64画面
        public static final int   NET_SPLIT_144 = 144;                  // 144画面
        public static final int   NET_PIP_1 = NET_SPLIT_PIP_BASE + 1;   // 画中画模式, 1个全屏大画面+1个小画面窗口
        public static final int   NET_PIP_3 = NET_SPLIT_PIP_BASE + 3;   // 画中画模式, 1个全屏大画面+3个小画面窗口
        public static final int   NET_SPLIT_FREE = NET_SPLIT_PIP_BASE * 2; // 自由开窗模式,可以自由创建、关闭窗口,自由设置窗口位置和Z轴次序
        public static final int   NET_COMPOSITE_SPLIT_1 = NET_SPLIT_PIP_BASE * 3 + 1; // 融合屏成员1分割
        public static final int   NET_COMPOSITE_SPLIT_4 = NET_SPLIT_PIP_BASE * 3 + 4; // 融合屏成员4分割
        public static final int   NET_SPLIT_3 = 10;                     // 3画面
        public static final int   NET_SPLIT_3B = 11;                    // 3画面倒品
        public static final int   NET_SPLIT_4A = NET_SPLIT_PIP_BASE * 4 + 1; // 4个画面, 一个大画面在左边，3个小画面在右边排成一列
    }

    // 区块窗口信息
    public static class NET_WINDOW_COLLECTION extends SdkStructure
    {
        public int              dwSize;
        public int              nWindowID;                            // 窗口ID
        public int              bWndEnable;                           // 窗口是否有效
        public DH_RECT          stuRect;                              // 窗口区域, 自由分割模式下有效
        public int              bDirectable;                          // 坐标是否满足直通条件
        public int              nZOrder;                              // 窗口Z次序
        public int              bSrcEnable;                           // 显示源是否有效
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
        public int              nVideoChannel;                        // 视频通道号
        public int              nVideoStream;                         // 视频码流类型
        public int              nAudioChannel;                        // 音频通道
        public int              nAudioStream;                         // 音频码流类型
        public int              nUniqueChannel;                       // 设备内统一编号的唯一通道号
        public NET_MONITOR_WALL_DEVICE_INFO stuDeviceInfo;            // 设备详细信息
        public int              nInterval;                            // 轮巡时间间隔,单位秒 (窗口轮巡有效，否则忽略)
        public NET_REMOTE_DEVICE stuDeviceInfoEx = new NET_REMOTE_DEVICE(); // 设备详细信息扩展

        public NET_WINDOW_COLLECTION() {
            this.dwSize = this.size();
        }
    }

    // 分割窗口OSD信息
    public static class NET_SPLIT_OSD extends SdkStructure
    {
        public int              dwSize;
        public Boolean          bEnable;                              // 使能
        public NET_COLOR_RGBA   stuFrontColor;                        // 前景颜色
        public NET_COLOR_RGBA   stuBackColor;                         // 背景颜色
        public DH_RECT          stuFrontRect;                         // 前景区域
        public DH_RECT          stuBackRect;                          // 背景区域
        public Boolean          bRoll;                                // 是否滚动显示, 只对文本有效
        public  byte            byRollMode;                           // 滚动模式, 只对文本有效, 0-从左往右, 1-从右往左, 2-从上往下滚动, 3-从下往上滚动
        public  byte            byRoolSpeed;                          // 滚动速率, 只对文本有效, 0~4, 数值越大滚动越快
        public  byte            byFontSize;                           // 字体大小, 只对文本有效
        public byte             byTextAlign;                          // 对齐方式, 0-靠左, 1-居中, 2-靠右
        public byte             byType;                               // OSD类型, 0-文本, 1-图标, 2-时间
        public byte[]           Reserved = new byte[3];               // 保留字节
        public byte[]           szContent = new byte[MAX_PATH];       // OSD内容
        // 若类型为图标, 内容为图标名称
        // 若类型为Time，内容为”Date”(日期),”Week”(星期),”Time”(时间),”\n”(换行)的自由组合。
        // 例如：不同行显示，需要将此字段填为"Date\nTime",在屏幕上就会显示
        // 2018年4月23日
        // 16:49:15
        // 同行显示，需要将此字段填为"DateTime",在屏幕上就会显示
        // 2018年4月23日16:49:15
        public float            fPitch;                               // 字符间距, 0.0 ~ 5.0
        public byte[]           szFontType = new byte[NET_COMMON_STRING_64]; // 字体类型
        public byte[]           szPattern = new byte[8];              // 文本显示模式 Row:横排(默认) Column:竖排
        public byte[]           szContentEx = new byte[1024];         // OSD内容
    }

    // 拼接区底图信息
    public static class NET_SCREEEN_BACKGROUD extends SdkStructure
    {
        public Boolean          bEnable;                              // 底图是否开启
        public byte[]           szName = new byte[130];               // 底图名称,底图是已经上传的文件，不带路径名称
        public byte[]           byReserved = new byte[130];           // 保留字节用
    }

    // 区块收藏
    public static class NET_BLOCK_COLLECTION extends SdkStructure
    {
        public int              dwSize;
        public int              emSplitMode;                          // 分割模式，对应  NET_SPLIT_MODE
        public NET_WINDOW_COLLECTION[] stuWnds = new NET_WINDOW_COLLECTION[NET_MAX_SPLIT_WINDOW]; // 窗口信息数组
        public int              nWndsCount;                           // 窗口数量
        public byte[]           szName = new byte[NET_DEVICE_NAME_LEN]; // 收藏夹名称
        public int              nScreen;                              // 输出通道号, 包括拼接屏
        public byte[]           szCompositeID = new byte[NET_DEV_ID_LEN_EX]; // 拼接屏ID
        public Pointer          pstuWndsEx;                           // 窗口信息数组指针 NET_WINDOW_COLLECTION[] , 由用户分配内存. 当stuWnds数组大小不够用时可以使用
        public int              nMaxWndsCountEx;                      // 最大窗口数量, 用户填写. pstuWndsEx数组的元素个数
        public int              nRetWndsCountEx;                      // 返回窗口数量
        public int              nSplitOsdCount;                       // OSD的个数
        public NET_SPLIT_OSD[]  stuSplitOsd = new NET_SPLIT_OSD[20];  // 拼接区OSD叠加信息，
        public NET_SCREEEN_BACKGROUD stuScreenBackground;             // 拼接区底图信息，目前仅预案获取时使用

        public NET_BLOCK_COLLECTION() {
            this.dwSize = this.size();
            for (int i = 0; i < stuWnds.length; ++i) {
                stuWnds[i] = new NET_WINDOW_COLLECTION();
            }
        }
    }

    // 电视墙显示单元
    public static class NET_MONITORWALL_OUTPUT extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN]; // 设备ID, 本机时为""
        public int              nChannel;                             // 通道号
        public byte[]           szName = new byte[NET_DEV_NAME_LEN];  // 屏幕名称
        public int              bIsVirtual;                           // 是否是虚拟屏（虚拟屏表示在本设备上不存在的屏）TRUE:虚拟屏 FALSE:非虚拟屏
        public byte[]           szAddress = new byte[40];             // 归属设备地址IP
        public NET_MONITOR_WALL_OUT_MODE_INFO stuOutMode;             // 输出模式信息
        public int              nPosX;                                //相对物理坐标系下水平方向起始位置
        public int              nPosY;                                //相对物理坐标系下垂直方向起始位置

        public NET_MONITORWALL_OUTPUT() {
            this.dwSize = this.size();
        }
    }

    // 电视墙显示区块
    public static class NET_MONITORWALL_BLOCK extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szName = new byte[NET_DEV_NAME_LEN];  // 区块名称
        public byte[]           szCompositeID = new byte[NET_DEV_ID_LEN]; // 拼接屏ID
        public byte[]           szControlID = new byte[NET_DEV_ID_LEN]; // 控制ID
        public int              nSingleOutputWidth;                   // 单个显示单元所占的网格列数
        public int              nSingleOutputHeight;                  // 单个显示单元所占的网格行数
        public DH_RECT          stuRect;                              // 区域坐标
        public NET_TSECT_WEEK_DAY[] stuPowerSchedule = (NET_TSECT_WEEK_DAY[])new NET_TSECT_WEEK_DAY().toArray(NET_TSCHE_DAY_NUM); // 开机时间表, 第一维各元素表示周日~周六和节假日
        public Pointer          pstuOutputs;                          // 显示单元数组 NET_MONITORWALL_OUTPUT[] , 用户分配内存
        public int              nMaxOutputCount;                      // 显示单元数组大小, 用户填写
        public int              nRetOutputCount;                      // 返回的显示单元数量
        public byte[]           szBlockType = new byte[NET_COMMON_STRING_32]; // 显示单元组类型,为支持由接收卡组成单元的小间距LED区块而增加该字段,其他类型
        public int              nOutputDelay;                         // 输出延迟,单位：毫秒

        public NET_MONITORWALL_BLOCK() {
            this.dwSize = this.size();
        }
    }

    public static class NET_TSECT_WEEK_DAY extends SdkStructure
    {
        public NET_TSECT[]      stuPowerSchedule = (NET_TSECT[])new NET_TSECT().toArray(NET_TSCHE_SEC_NUM);
    }

    // 电视墙配置
    public static class NET_MONITORWALL extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szName = new byte[NET_DEV_NAME_LEN];  // 名称
        public int              nGridLine;                            // 网格行数
        public int              nGridColume;                          // 网格列数
        public Pointer          pstuBlocks;                           // 显示区块数组 NET_MONITORWALL_BLOCK[] , 用户分配内存
        public int              nMaxBlockCount;                       // 显示区块数组大小, 用户填写
        public int              nRetBlockCount;                       // 返回的显示区块数量
        public int              bDisable;                             // 是否禁用, 0-该电视墙有效, 1-该电视墙无效
        public byte[]           szDesc = new byte[NET_COMMON_STRING_256]; // 电视墙描述信息

        public NET_MONITORWALL() {
            this.dwSize = this.size();
        }
    }

    // 电视墙预案
    public static class NET_MONITORWALL_COLLECTION extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szName = new byte[NET_DEVICE_NAME_LEN]; // 电视墙预案名称
        public NET_BLOCK_COLLECTION[] stuBlocks = new NET_BLOCK_COLLECTION[NET_MAX_BLOCK_NUM]; // 区块数组
        public int              nBlocksCount;                         // 区块数量
        public byte[]           szControlID = new byte[NET_DEV_ID_LEN_EX]; // 控制ID
        public NET_MONITORWALL  stuMonitorWall;                       // 电视墙配置
        public int              emType;                               // 预案类型
        public byte[]           byReserved = new byte[4];             // 保留字节用，于字节对齐

        public NET_MONITORWALL_COLLECTION() {
            this.dwSize = this.size();

            for(int i = 0; i < NET_MAX_BLOCK_NUM; i++) {
                stuBlocks[i] = new NET_BLOCK_COLLECTION();
            }
        }
    }

    // CLIENT_GetMonitorWallCollections接口输入参数(获取电视墙预案信息)
    public static class NET_IN_WM_GET_COLLECTIONS extends SdkStructure
    {
        public int              dwSize;
        public int              nMonitorWallID;                       // 电视墙ID

        public NET_IN_WM_GET_COLLECTIONS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetMonitorWallCollections接口输出参数(获取电视墙预案信息)
    public static class NET_OUT_WM_GET_COLLECTIONS extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pCollections;                         // 电视墙预案数组, 对应   NET_MONITORWALL_COLLECTION 指针
        public int              nMaxCollectionsCount;                 // 电视墙预案数组大小
        public int              nCollectionsCount;                    // 电视墙预案数量

        public NET_OUT_WM_GET_COLLECTIONS() {
            this.dwSize = this.size();
        }
    }

    // 级联权限验证信息
    public static class SDK_CASCADE_AUTHENTICATOR extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUser = new byte[NET_NEW_USER_NAME_LENGTH]; // 用户名
        public byte[]           szPwd = new byte[NET_NEW_USER_PSW_LENGTH]; // 密码
        public byte[]           szSerialNo = new byte[NET_SERIALNO_LEN]; // 设备序列号

        public SDK_CASCADE_AUTHENTICATOR() {
            this.dwSize = this.size();
        }
    }

    public static class EM_SRC_PUSHSTREAM_TYPE extends SdkStructure
    {
        public static final int   EM_SRC_PUSHSTREAM_AUTO = 0;           // 设备端根据码流头自动识别，默认值
        public static final int   EM_SRC_PUSHSTREAM_HIKVISION = 1;      // 私有码流
        public static final int   EM_SRC_PUSHSTREAM_PS = 2;             // PS流
        public static final int   EM_SRC_PUSHSTREAM_TS = 3;             // TS流
        public static final int   EM_SRC_PUSHSTREAM_SVAC = 4;           // SVAC码流
    }

    // 显示源
    public static class NET_SPLIT_SOURCE extends SdkStructure
    {
        public int              dwSize;
        public int              bEnable;                              // 使能
        public byte[]           szIp = new byte[NET_MAX_IPADDR_LEN];  // IP, 空表示没有设置
        public byte[]           szUser = new byte[NET_USER_NAME_LENGTH]; // 用户名, 建议使用szUserEx
        public byte[]           szPwd = new byte[NET_USER_PSW_LENGTH]; // 密码, 建议使用szPwdEx
        public int              nPort;                                // 端口
        public int              nChannelID;                           // 通道号
        public int              nStreamType;                          // 视频码流, -1-自动, 0-主码流, 1-辅码流1, 2-辅码流2, 3-辅码流3, 4-snap, 5-预览
        public int              nDefinition;                          // 清晰度, 0-标清, 1-高清
        public int              emProtocol;                           // 协议类型,对应   NET_DEVICE_PROTOCOL
        public byte[]           szDevName = new byte[NET_DEVICE_NAME_LEN]; // 设备名称
        public int              nVideoChannel;                        // 视频输入通道数
        public int              nAudioChannel;                        // 音频输入通道数
        //--------------------------------------------------------------------------------------
        // 以下只对解码器有效
        public int              bDecoder;                             // 是否是解码器
        public byte             byConnType;                           // -1: auto, 0：TCP；1：UDP；2：组播
        public byte             byWorkMode;                           // 0：直连；1：转发
        public short            wListenPort;                          // 指示侦听服务的端口,转发时有效; byConnType为组播时,则作为多播端口
        public byte[]           szDevIpEx = new byte[NET_MAX_IPADDR_OR_DOMAIN_LEN]; // szDevIp扩展,前端DVR的IP地址(可以输入域名)
        public byte             bySnapMode;                           // 抓图模式(nStreamType==4时有效) 0：表示请求一帧,1：表示定时发送请求
        public byte             byManuFactory;                        // 目标设备的生产厂商, 具体参考EM_IPC_TYPE类
        public byte             byDeviceType;                         // 目标设备的设备类型, 0:IPC
        public byte             byDecodePolicy;                       // 目标设备的解码策略, 0:兼容以前
        // 1:实时等级高 2:实时等级中
        // 3:实时等级低 4:默认等级
        // 5:流畅等级高 6:流畅等级中
        // 7:流畅等级低
        //--------------------------------------------------------------------------------------
        public int              dwHttpPort;                           // Http端口号, 0-65535
        public int              dwRtspPort;                           // Rtsp端口号, 0-65535
        public byte[]           szChnName = new byte[NET_DEVICE_NAME_LEN]; // 远程通道名称, 只有读取到的名称不为空时才可以修改该通道的名称
        public byte[]           szMcastIP = new byte[NET_MAX_IPADDR_LEN]; // 多播IP地址, byConnType为组播时有效
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID, ""-null, "Local"-本地通道, "Remote"-远程通道, 或者填入具体的RemoteDevice中的设备ID
        public int              bRemoteChannel;                       // 是否远程通道(只读)
        public int              nRemoteChannelID;                     // 远程通道ID(只读), bRemoteChannel=TRUE时有效
        public byte[]           szDevClass = new byte[NET_DEV_TYPE_LEN]; // 设备类型, 如IPC, DVR, NVR等
        public byte[]           szDevType = new byte[NET_DEV_TYPE_LEN]; // 设备具体型号, 如IPC-HF3300
        public byte[]           szMainStreamUrl = new byte[MAX_PATH]; // 主码流url地址, byManuFactory为NET_IPC_OTHER时有效
        public byte[]           szExtraStreamUrl = new byte[MAX_PATH]; // 辅码流url地址, byManuFactory为NET_IPC_OTHER时有效
        public int              nUniqueChannel;                       // 设备内统一编号的唯一通道号, 只读
        public SDK_CASCADE_AUTHENTICATOR stuCascadeAuth;              // 级联认证信息, 设备ID为"Local/Cascade/SerialNo"时有效, 其中SerialNo是设备序列号
        public int              nHint;                                // 0-普通视频源, 1-报警视频源
        public int              nOptionalMainUrlCount;                // 备用主码流地址数量
        public byte[]           szOptionalMainUrls = new byte[NET_MAX_OPTIONAL_URL_NUM * MAX_PATH]; // 备用主码流地址列表
        public int              nOptionalExtraUrlCount;               // 备用辅码流地址数量
        public byte[]           szOptionalExtraUrls = new byte[NET_MAX_OPTIONAL_URL_NUM * MAX_PATH]; // 备用辅码流地址列表
        //--------------------------------------------------------------------------------------
        //协议后续添加字段
        public int              nInterval;                            // 轮巡时间间隔   单位：秒
        public byte[]           szUserEx = new byte[NET_NEW_USER_NAME_LENGTH]; // 用户名
        public byte[]           szPwdEx = new byte[NET_NEW_USER_PSW_LENGTH]; // 密码
        public int              emPushStream;                         // 推流方式的码流类型,只有byConnType为TCP-Push或UDP-Push才有该字段,对应  EM_SRC_PUSHSTREAM_TYPE
        public NET_RECT         stuSRect;                             // 视频源区域,当szDeviceID不为空时有效
        public NET_SOURCE_STREAM_ENCRYPT stuSourceStreamEncrypt;      // 码流加密方式
        public byte[]           szSerialNo = new byte[NET_SERIALNO_LEN]; // 设备序列号,当连接设备的协议类型为云睿接入，该字段必填
        public byte[]           szCaption = new byte[128];            // 通道备注，可用于在OSD显示
        public int              bUserStreamUrlEx;                     // 是否使用szMainStreamUrlEx/szExtraStreamUrlEx字段
        public byte[]           szMainStreamUrlEx = new byte[1024];   // 主码流url地址, byManuFactory为DH_IPC_OTHER时有效
        public byte[]           szExtraStreamUrlEx = new byte[1024];  // 辅码流url地址, byManuFactory为DH_IPC_OTHER时有效

        public NET_SPLIT_SOURCE() {
            this.dwSize = this.size();
        }
    }

    // 显示源码流加密方式
    public static class NET_SOURCE_STREAM_ENCRYPT extends SdkStructure
    {
        public int              emEncryptLevel;                       // 加密等级
        public int              emAlgorithm;                          // 加密算法
        public int              emExchange;                           // 密钥交换方式
        public Boolean          bUnvarnished;                         // MTS使用场景,true为交互MIKEY后让数据不进行加/解密
        public byte[]           szPSK = new byte[1032];               // 密钥
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 矩阵子卡信息
    public static class NET_MATRIX_CARD extends SdkStructure
    {
        public int              dwSize;
        public int              bEnable;                              // 是否有效
        public int              dwCardType;                           // 子卡类型
        public byte[]           szInterface = new byte[NET_MATRIX_INTERFACE_LEN]; // 信号接口类型, "CVBS", "VGA", "DVI"...
        public byte[]           szAddress = new byte[NET_MAX_IPADDR_OR_DOMAIN_LEN]; // 设备ip或域名, 无网络接口的子卡可以为空
        public int              nPort;                                // 端口号, 无网络接口的子卡可以为0
        public int              nDefinition;                          // 清晰度, 0=标清, 1=高清
        public int              nVideoInChn;                          // 视频输入通道数
        public int              nAudioInChn;                          // 音频输入通道数
        public int              nVideoOutChn;                         // 视频输出通道数
        public int              nAudioOutChn;                         // 音频输出通道数
        public int              nVideoEncChn;                         // 视频编码通道数
        public int              nAudioEncChn;                         // 音频编码通道数
        public int              nVideoDecChn;                         // 视频解码通道数
        public int              nAudioDecChn;                         // 音频解码通道数
        public int              nStauts;                              // 状态: -1-未知, 0-正常, 1-无响应, 2-网络掉线, 3-冲突, 4-正在升级, 5-链路状态异常, 6-子板背板未插好, 7-程序版本出错
        public int              nCommPorts;                           // 串口数
        public int              nVideoInChnMin;                       // 视频输入通道号最小值
        public int              nVideoInChnMax;                       // 视频输入通道号最大值
        public int              nAudioInChnMin;                       // 音频输入通道号最小值
        public int              nAudioInChnMax;                       // 音频输入通道号最大值
        public int              nVideoOutChnMin;                      // 视频输出通道号最小值
        public int              nVideoOutChnMax;                      // 视频输出通道号最大值
        public int              nAudioOutChnMin;                      // 音频输出通道号最小值
        public int              nAudioOutChnMax;                      // 音频输出通道号最大值
        public int              nVideoEncChnMin;                      // 视频编码通道号最小值
        public int              nVideoEncChnMax;                      // 视频编码通道号最大值
        public int              nAudioEncChnMin;                      // 音频编码通道号最小值
        public int              nAudioEncChnMax;                      // 音频编码通道号最大值
        public int              nVideoDecChnMin;                      // 视频解码通道号最小值
        public int              nVideoDecChnMax;                      // 视频解码通道号最大值
        public int              nAudioDecChnMin;                      // 音频解码通道号最小值
        public int              nAudioDecChnMax;                      // 音频解码通道号最大值
        public int              nCascadeChannels;                     // 级联通道数
        public int              nCascadeChannelBitrate;               // 级联通道带宽, 单位Mbps
        public int              nAlarmInChnCount;                     // 报警输入通道数
        public int              nAlarmInChnMin;                       // 报警输入通道号最小值
        public int              nAlarmInChnMax;                       // 报警输入通道号最大值
        public int              nAlarmOutChnCount;                    // 报警输出通道数
        public int              nAlarmOutChnMin;                      // 报警输入通道号最小值
        public int              nAlarmOutChnMax;                      // 报警输入通道号最大值
        public int              nVideoAnalyseChnCount;                // 智能分析通道数
        public int              nVideoAnalyseChnMin;                  // 智能分析通道号最小值
        public int              nVideoAnalyseChnMax;                  // 智能分析通道号最大值
        public int              nCommPortMin;                         // 串口号最小值
        public int              nCommPortMax;                         // 串口号最大值
        public byte[]           szVersion = new byte[NET_COMMON_STRING_32]; // 版本信息
        public NET_TIME         stuBuildTime;                         // 编译时间
        public byte[]           szBIOSVersion = new byte[NET_COMMON_STRING_64]; // BIOS版本号
        public byte[]           szMAC = new byte[NET_MACADDR_LEN];    // MAC地址
        public int              nMonitorWallEchoDisplayChannels;      //电视墙回显通道数目
        public int              nMonitorWallEchoDisplayChannelsEnumNum; //电视墙回显逻辑通道号枚举有效个数
        public int[]            nMonitorWallEchoDisplayChannelsEnum = new int[128]; //电视墙回显逻辑通道号枚举
        public int              nVideoOutputChannelsEnumCount;        //视频输出逻辑通道号枚举有效个数
        public int[]            nVideoOutputChannelsEnum = new int[128]; //视频输出逻辑通道号枚举

        public NET_MATRIX_CARD() {
            this.dwSize = this.size();
        }
    }

    // 矩阵子卡列表
    public static class NET_MATRIX_CARD_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // 子卡数量
        public NET_MATRIX_CARD[] stuCards = new NET_MATRIX_CARD[NET_MATRIX_MAX_CARDS]; // 子卡列表

        public NET_MATRIX_CARD_LIST() {
            this.dwSize = this.size();
            for(int i = 0; i < NET_MATRIX_MAX_CARDS; i++) {
                stuCards[i] = new NET_MATRIX_CARD();
            }
        }
    }

    // CLIENT_FindFramInfo 接口输入参数
    public static class NET_IN_FIND_FRAMEINFO_PRAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              abFileName;                           // 文件名是否作为有效的查询条件,若文件名有效,则不用填充文件信息（stRecordInfo）
        public byte[]           szFileName = new byte[MAX_PATH];      // 文件名
        public NET_RECORDFILE_INFO stuRecordInfo;                     // 文件信息
        public int              dwFramTypeMask;                       // 帧类型掩码,详见“帧类型掩码定义”,FRAME_TYPE_MOTION 动检帧;  FRAME_TYPE_HUMAN动检帧(人); FRAME_TYPE_VEHICLE动检帧(车)
        public int              bSendByUTCTime;                       // 是否使用UTC时间
        public NET_TIME         stuStartTimeRealUTC = new NET_TIME(); // UTC开始时间
        public NET_TIME         stuEndTimeRealUTC = new NET_TIME();   // UTC结束时间

        public NET_IN_FIND_FRAMEINFO_PRAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FindFramInfo 接口输出参数
    public static class NET_OUT_FIND_FRAMEINFO_PRAM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public LLong            lFindHandle;                          // 文件查找句柄

        public NET_OUT_FIND_FRAMEINFO_PRAM() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FileStreamClearTags / CLIENT_FileStreamSetTags 接口输入参数
    public static class NET_IN_FILE_STREAM_TAGS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nArrayCount;                          // 标签数组个数
        public Pointer          pstuTagInfo;                          // 标签数组，各项内容关系为"且", 用户分配内存,大小为sizeof( NET_FILE_STREAM_TAG_INFO )*nArrayCount

        public NET_IN_FILE_STREAM_TAGS_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FileStreamClearTags / CLIENT_FileStreamSetTags 接口输出参数
    public static class NET_OUT_FILE_STREAM_TAGS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_FILE_STREAM_TAGS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 标签数组
    public static class NET_FILE_STREAM_TAG_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_TIME         stuTime;                              // 标签时间
        public byte[]           szContext = new byte[NET_COMMON_STRING_64]; // 标签内容，中文必须使用utf8编码
        public byte[]           szUserName = new byte[NET_COMMON_STRING_32]; // 用户名，中文必须使用utf8编码，EVS增加
        public byte[]           szChannelName = new byte[NET_COMMON_STRING_64]; // 通道名称，中文必须使用utf8编码，EVS增加
        public int              nDuration;                            // 打标的录像持续时间，单位秒

        public NET_FILE_STREAM_TAG_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FileStreamGetTags 接口输入参数
    public static class NET_IN_FILE_STREAM_GET_TAGS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_FILE_STREAM_GET_TAGS_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FileStreamGetTags 接口输出参数
    public static class NET_OUT_FILE_STREAM_GET_TAGS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxNumber;                           // 标签数组最大个数
        public int              nRetTagsCount;                        // 实际返回的标签信息个数
        public Pointer          pstuTagInfo;                          // 标签数组  NET_FILE_STREAM_TAG_INFO_EX

        public NET_OUT_FILE_STREAM_GET_TAGS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 查询到的标签信息
    public static class NET_FILE_STREAM_TAG_INFO_EX extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_TIME         stuTime;                              // 标签所对于视频的时间，精确到秒
        public int              nMillisecond;                         // 毫秒
        public int              nSequence;                            // 视频序列号
        public byte[]           szContext = new byte[NET_COMMON_STRING_64]; // 标签内容，中文必须使用utf8编码
        public NET_TIME         stuStartTime;                         // 录像文件开始时间
        public NET_TIME         stuEndTime;                           // 录像文件结束时间
        public int              emType;                               // 文件类型,对应   NET_FILE_STREAM_TYPE
        public byte[]           szUserName = new byte[NET_COMMON_STRING_32]; // 用户名，中文必须使用utf8编码，EVS增加
        public byte[]           szChannelName = new byte[NET_COMMON_STRING_64]; // 通道名称，中文必须使用utf8编码，EVS增加
        public int              nDuration;                            // 打标的录像持续时间，单位秒

        public NET_FILE_STREAM_TAG_INFO_EX() {
            this.dwSize = this.size();
        }
    }

    // 文件类型
    public static class NET_FILE_STREAM_TYPE extends SdkStructure
    {
        public static final int   NET_FILE_STREAM_TYPE_UNKNOWN = 0;     // 未知
        public static final int   NET_FILE_STREAM_TYPE_NORMAL = 1;      // 普通
        public static final int   NET_FILE_STREAM_TYPE_ALARM = 2;       // 报警
        public static final int   NET_FILE_STREAM_TYPE_DETECTION = 3;   // 动检
    }

    // 一屏幕的分割模式信息， CLIENT_GetSplitMode/CLIENT_SetSplitMode参数
    public static class NET_SPLIT_MODE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emSplitMode;                          // 分割模式, NET_SPLIT_MODE
        public int              nGroupID;                             // 分组序号
        public int              dwDisplayType;                        // 显示类型；具体见NET_SPLIT_DISPLAY_TYPE（注释各模式下显示内容由"PicInPic"决定, 各模式下显示内容按NVD旧有规则决定（即DisChn字段决定）。兼容,没有这一个项时,默认为普通显示类型,即"General"）

        public NET_SPLIT_MODE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 分割能力， CLIENT_GetSplitCaps 参数
    public static class NET_SPLIT_CAPS extends SdkStructure
    {
        public int              dwSize;
        public int              nModeCount;                           // 支持的分割模式数量
        public int[]            emSplitMode = new int[NET_MAX_SPLIT_MODE_NUM]; // 支持的分割模式, 见 NET_SPLIT_MODE
        public int              nMaxSourceCount;                      // 最大显示源配置数
        public int              nFreeWindowCount;                     // 支持的最大自由开窗数目
        public int              bCollectionSupported;                 // 是否支持区块收藏, BOOL类型，0或1
        public int              dwDisplayType;                        // 掩码表示多个显示类型,具体见NET_SPLIT_DISPLAY_TYPE（注释各模式下显示内容由"PicInPic"决定, 各模式下显示内容按NVD旧有规则决定（即DisChn字段决定）。兼容,没有这一个项时,默认为普通显示类型,即"General"）
        public int              nPIPModeCount;                        // 画中画支持的分割模式数量
        public int[]            emPIPSplitMode = new int[NET_MAX_SPLIT_MODE_NUM]; // 画中画支持的分割模式, 见 NET_SPLIT_MODE
        public int[]            nInputChannels = new int[NET_SPLIT_INPUT_NUM]; // 支持的输入通道
        public int              nInputChannelCount;                   // 支持的输入通道个数, 0表示没有输入通道限制
        public int              nBootModeCount;                       // 启动分割模式数量
        public int[]            emBootMode = new int[NET_MAX_SPLIT_MODE_NUM]; // 支持的启动默认画面分割模式, 见 NET_SPLIT_MODE

        public NET_SPLIT_CAPS() {
            this.dwSize = this.size();
        }
    }

    // (设置显示源, 支持同时设置多个窗口)CLIENT_SplitSetMultiSource 接口的输入参数
    public static class NET_IN_SPLIT_SET_MULTI_SOURCE extends SdkStructure
    {
        public int              dwSize;
        public int              emCtrlType;                           // 视频输出控制方式,见 EM_VIDEO_OUT_CTRL_TYPE
        public int              nChannel;                             // 视频输出逻辑通道号, emCtrlType为EM_VIDEO_OUT_CTRL_CHANNEL时有效
        public String           pszCompositeID;                       // 拼接屏ID, emCtrlType为EM_VIDEO_OUT_CTRL_COMPOSITE_ID时有效
        public int              bSplitModeEnable;                     // 是否改变分割模式, BOOL类型，0或1
        public int              emSplitMode;                          // 分割模式, bSplitModeEnable=TRUE时有效,见 NET_SPLIT_MODE
        public int              nGroupID;                             // 分割分组号, bSplitModeEnable=TRUE时有效
        public Pointer          pnWindows;                            // 窗口号数组 int[],由用户申请内存，大小为sizeof(int)*nWindowCount
        public int              nWindowCount;                         // 窗口数量
        public Pointer          pstuSources;                          // 视频源信息, 分别对应每个窗口, 数量同窗口数  NET_SPLIT_SOURCE[] ,由用户申请内存，大小为sizeof(NET_SPLIT_SOURCE)*nWindowCount

        public NET_IN_SPLIT_SET_MULTI_SOURCE() {
            this.dwSize = this.size();
        }
    }

    // (设置显示源, 支持同时设置多个窗口) CLIENT_SplitSetMultiSource 接口的输出参数
    public static class NET_OUT_SPLIT_SET_MULTI_SOURCE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SPLIT_SET_MULTI_SOURCE() {
            this.dwSize = this.size();
        }
    }

    // (下位矩阵切换) CLIENT_MatrixSwitch 输入参数
    public static class NET_IN_MATRIX_SWITCH extends SdkStructure
    {
        public int              dwSize;
        public int              emSplitMode;                          // 分割模式,见 NET_SPLIT_MODE
        public Pointer          pnOutputChannels;                     // 输出通道, 可同时指定多个输出通道一起切换, 内容一致
        // 由用户申请内存 int[] ，大小为sizeof(int)*nOutputChannelCount
        public int              nOutputChannelCount;                  // 输出通道数
        public Pointer          pnInputChannels;                      // 输入通道, 每个分割窗口一个对应一个输入通道
        // 由用户申请内存 int[] ，大小为sizeof(int)*nInputChannelCount
        public int              nInputChannelCount;                   // 输入通道数

        public NET_IN_MATRIX_SWITCH() {
            this.dwSize = this.size();
        }
    }

    // (下位矩阵切换) CLIENT_MatrixSwitch 输出参数
    public static class NET_OUT_MATRIX_SWITCH extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MATRIX_SWITCH() {
            this.dwSize = this.size();
        }
    }

    // 刻录模式
    public static class NET_BURN_MODE extends SdkStructure
    {
        public static final int   BURN_MODE_SYNC = 0;                   // 同步
        public static final int   BURN_MODE_TURN = 1;                   // 轮流
        public static final int   BURN_MODE_CYCLE = 2;                  // 循环
    }

    // 刻录流格式
    public static class NET_BURN_RECORD_PACK extends SdkStructure
    {
        public static final int   BURN_PACK_DHAV = 0;                   // DHAV
        public static final int   BURN_PACK_PS = 1;                     // PS
        public static final int   BURN_PACK_ASF = 2;                    // ASF
        public static final int   BURN_PACK_MP4 = 3;                    // MP4
        public static final int   BURN_PACK_TS = 4;                     // TS
    }

    // 刻录扩展模式
    public static class NET_BURN_EXTMODE extends SdkStructure
    {
        public static final int   BURN_EXTMODE_UNKNOWN = 0;             // 未知
        public static final int   BURN_EXTMODE_NORMAL = 1;              // 正常刻录
        public static final int   BURN_EXTMODE_NODISK = 2;              // 无盘刻录
    }

    // (开始刻录) CLIENT_StartBurn 接口输入参数
    public static class NET_IN_START_BURN extends SdkStructure
    {
        public int              dwSize;
        public int              dwDevMask;                            // 刻录设备掩码, 按位表示多个刻录设备组合
        public int[]            nChannels = new int[NET_MAX_BURN_CHANNEL_NUM]; // 刻录通道数组
        public int              nChannelCount;                        // 刻录通道数
        public int              emMode;                               // 刻录模式,见  NET_BURN_MODE
        public int              emPack;                               // 刻录流格式,见  NET_BURN_RECORD_PACK
        public int              emExtMode;                            // 刻录扩展模式, 见  NET_BURN_EXTMODE

        public NET_IN_START_BURN() {
            this.dwSize = this.size();
        }
    }

    // (开始刻录)CLIENT_StartBurn 接口输出参数
    public static class NET_OUT_START_BURN extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_START_BURN() {
            this.dwSize = this.size();
        }
    }

    // (打开会话)CLIENT_StartBurnSession 接口输入参数
    public static class NET_IN_START_BURN_SESSION extends SdkStructure
    {
        public int              dwSize;
        public int              nSessionID;                           // 会话ID

        public NET_IN_START_BURN_SESSION() {
            this.dwSize = this.size();
        }
    }

    // (打开会话)CLIENT_StartBurnSession 接口输出参数
    public static class NET_OUT_START_BURN_SESSION extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_START_BURN_SESSION() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_EJECT_BURNER、CTRLTYPE_CTRL_CLOSE_BURNER 等 命令参数
    public static class NET_CTRL_BURNERDOOR extends SdkStructure
    {
        public int              dwSize;
        public Pointer          szBurnerName;                         // 光盘名称,如“/dev/sda”, 用户申请内存
        public int              bResult;                              // 操作结果
        public int              bSafeEject;                           // 是否安全弹出光驱, 1-弹出前做数据保存, 0-直接弹出

        public NET_CTRL_BURNERDOOR() {
            this.dwSize = this.size();
        }
    }

    // 光驱托盘状态
    public static class EM_NET_BURN_DEV_TRAY_TYPE extends SdkStructure
    {
        public static final int   EM_NET_BURN_DEV_TRAY_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_NET_BURN_DEV_TRAY_TYPE_READY = 1;  // 光盘就绪
        public static final int   EM_NET_BURN_DEV_TRAY_TYPE_OPEN = 2;   // 托盘弹出
        public static final int   EM_NET_BURN_DEV_TRAY_TYPE_NODISK = 3; // 无盘
        public static final int   EM_NET_BURN_DEV_TRAY_TYPE_NOT_READY = 4; // 光盘未就绪
    }

    // 光驱使用状态
    public static class EM_NET_BURN_DEV_OPERATE_TYPE extends SdkStructure
    {
        public static final int   EM_NET_BURN_DEV_OPERATE_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_NET_BURN_DEV_OPERATE_TYPE_WRITE = 1; // 执行写
        public static final int   EM_NET_BURN_DEV_OPERATE_TYPE_READ = 2; // 执行读
        public static final int   EM_NET_BURN_DEV_OPERATE_TYPE_IDLE = 3; // 空闲
    }

    // 刻录机信息
    public static class NET_DEV_BURNING extends SdkStructure
    {
        public int              dwDriverType;                         // 刻录驱动器类型；0：DHFS,1：DISK,2：CDRW
        public int              dwBusType;                            // 总线类型；0：USB,1：1394,2：IDE, 3: SATA, 4: ESATA
        public int              dwTotalSpace;                         // 总容量(KB)
        public int              dwRemainSpace;                        // 剩余容量(KB)
        public byte[]           dwDriverName = new byte[NET_BURNING_DEV_NAMELEN]; // 刻录驱动器名称
        public int              emTrayType;                           // 光驱托盘状态, 详见EM_NET_BURN_DEV_TRAY_TYPE
        public int              emOperateType;                        // 光盘使用状态, 详见EM_NET_BURN_DEV_OPERATE_TYPE
    }

    // 设备刻录机信息
    public static class NET_BURNING_DEVINFO extends SdkStructure
    {
        public int              dwDevNum;                             // 刻录设备个数
        public NET_DEV_BURNING[] stuList = (NET_DEV_BURNING[])new NET_DEV_BURNING().toArray(NET_MAX_BURNING_DEV_NUM); // 各刻录设备信息
    }

    // CLIENT_AttachBurnState()输入参数
    public static class NET_IN_ATTACH_STATE extends SdkStructure
    {
        public int              dwSize;
        public Pointer          szDeviceName;                         // 光盘名称,如"/dev/sda"
        public fAttachBurnStateCB cbAttachState;                      // 刻录监听回调
        public Pointer          dwUser;                               // 用户数据
        public LLong            lBurnSession;                         // 刻录会话句柄, CLIENT_StartBurnSession的返回值. 该值为0时, szDeviceName有效, 此时按刻录设备订阅刻录状态
        public fAttachBurnStateCBEx cbAttachStateEx;                  // 扩展刻录监听回调
        public Pointer          dwUserEx;                             // 扩展刻录监听回调用户数据

        public NET_IN_ATTACH_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachBurnState 输出参数
    public static class NET_OUT_ATTACH_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTACH_STATE() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_StartUploadFileBurned 输入参数
    public static class NET_IN_FILEBURNED_START extends SdkStructure {
        public int              dwSize;
        public Pointer          szMode;                               // 文件上传方式
        // "append", 追加模式,此时刻录文件名固定为" FILE.zip ",filename被忽略
        // "evidence", 证据等大附件, 要求单独刻录的光盘内
        public Pointer          szDeviceName;                         // 光盘名称,如"/dev/sda"
        public Pointer          szFilename;                           // 本地文件名称
        public fBurnFileCallBack cbBurnPos;                           // 刻录进度回调
        public Pointer          dwUser;                               // 用户数据
        public LLong            lBurnSession;                         // 刻录会话句柄, CLIENT_StartBurnSession的返回值. 该值为0时, szDeviceName有效, 此时按刻录设备订阅刻录状态

        public NET_IN_FILEBURNED_START() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_StartUploadFileBurned 输出参数
    public static class NET_OUT_FILEBURNED_START extends SdkStructure {
        public int              dwSize;
        public byte[]           szState = new byte[MAX_COMMON_STRING_16]; //char[]
        // "start"：系统准备就绪,可以开始上传; "busy"：系统忙,稍后在试。"error"：系统未在刻录中,返回出错,请求失败

        public NET_OUT_FILEBURNED_START() {
            this.dwSize = this.size();
        }
    }

    // fAttachBurnStateCB 参数
    public static class NET_CB_BURNSTATE extends SdkStructure
    {
        public int              dwSize;
        public Pointer          szState;                              // 消息类型
        //"UploadFileStart"：可以开始附件上传
        //"InitBurnDevice":初始化刻录设备
        //"Burning":刻录中
        //"BurnExtraFileStop"：刻录停止
        //"BurnFilePause":刻录暂停
        //"SpaceFull":刻录空间满
        //"BurnFileError":刻录出错
        public Pointer          szFileName;                           // 当前刻录附件文件名,用于"UploadFileStart"开始附件上传消息
        public int              dwTotalSpace;                         // 总容量,单位KB,用于"Burning"刻录中,显示容量或计算进度
        public int              dwRemainSpace;                        // 剩余容量,单位KB,用于"Burning"刻录中
        public Pointer          szDeviceName;                         // 刻录设备名称,用于区分不同的刻录设备
        public int              nRemainTime;                          // 刻录剩余时间, 单位秒, -1代表无效
    }

    // 刻录状态
    public static class NET_BURN_STATE extends SdkStructure
    {
        public static final int   BURN_STATE_STOP = 0;                  // 停止
        public static final int   BURN_STATE_STOPING = 1;               // 停止中
        public static final int   BURN_STATE_INIT = 2;                  // 初始化
        public static final int   BURN_STATE_BURNING = 3;               // 刻录中
        public static final int   BURN_STATE_PAUSE = 4;                 // 暂停
        public static final int   BURN_STATE_CHANGE_DISK = 5;           // 换盘中
        public static final int   BURN_STATE_PREPARE_EXTRA_FILE = 6;    // 附件初始化
        public static final int   BURN_STATE_WAIT_EXTRA_FILE = 7;       // 等待附件刻录
        public static final int   BURN_STATE_UPLOAD_FILE_START = 8;     // 附件刻录中
        public static final int   BURN_STATE_CHECKING_DISK = 9;         // 检测光盘中
        public static final int   BURN_STATE_DISK_READY = 10;           // 光盘准备就绪
    }

    // 刻录错误码
    public static class NET_BURN_ERROR_CODE extends SdkStructure
    {
        public static final int   BURN_CODE_NORMAL = 0;                 // 正常
        public static final int   BURN_CODE_UNKNOWN_ERROR = 1;          // 未知错误
        public static final int   BURN_CODE_SPACE_FULL = 2;             // 刻录满
        public static final int   BURN_CODE_START_ERROR = 3;            // 开始刻录出错
        public static final int   BURN_CODE_STOP_ERROR = 4;             // 停止刻录出错
        public static final int   BURN_CODE_WRITE_ERROR = 5;            // 刻录出错
        public static final int   BURN_CODE_UNKNOWN = 6;                // 未知
    }

    // CLIENT_BurnGetState 接口输入参数
    public static class NET_IN_BURN_GET_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_BURN_GET_STATE() {
            this.dwSize = this.size();
        }
    }

    // 光驱使用状态
    public static class EM_NET_BURN_DEV_USED_STATE extends SdkStructure
    {
        public static final int   EM_NET_BURN_DEV_USED_STATE_UNKNOWN = 0; //未知
        public static final int   EM_NET_BURN_DEV_USED_STATE_STOP = 1;  //停止
        public static final int   EM_NET_BURN_DEV_USED_STATE_BURNING = 2; //刻录中
    }

    // 刻录设备状态
    public static class NET_BURN_DEV_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              nDeviceID;                            // 光驱设备ID
        public byte[]           szDevName = new byte[NET_BURNING_DEV_NAMELEN]; // 光驱设备名称
        public int              dwTotalSpace;                         // 光驱总容量, 单位KB
        public int              dwRemainSpace;                        // 光驱剩余容量, 单位KB
        public int              emUsedType;                           // 光驱使用状态, 详见EM_NET_BURN_DEV_USED_STATE
        public int              emError;                              // 单个光驱出错状态, 详见NET_BURN_ERROR_CODE
        public int              emDiskState;                          // 光盘状态,EM_DISK_STATE

        public NET_BURN_DEV_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_BurnGetState 接口输出参数
    public static class NET_OUT_BURN_GET_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              emState;                              // 刻录状态, 详见NET_BURN_STATE
        public int              emErrorCode;                          // 错误码, 详见NET_BURN_ERROR_CODE
        public int              dwDevMask;                            // 刻录设备掩码, 按位表示多个刻录设备组合
        public int[]            nChannels = new int[NET_MAX_BURN_CHANNEL_NUM]; // 刻录通道数组
        public int              nChannelCount;                        // 刻录通道数
        public int              emMode;                               // 刻录模式, 详见NET_BURN_MODE
        public int              emPack;                               // 刻录流格式, 详见NET_BURN_RECORD_PACK
        public int              nFileIndex;                           // 当前刻录文件编号
        public NET_TIME         stuStartTime;                         // 刻录开始时间
        public NET_BURN_DEV_STATE[] stuDevState = (NET_BURN_DEV_STATE[])new NET_BURN_DEV_STATE().toArray(NET_MAX_BURNING_DEV_NUM); // 刻录设备状态
        public int              nRemainTime;                          // 刻录剩余时间, 单位秒, -1代表无效
        public int              emExtMode;                            // 扩展模式,当为无盘刻录时，stuDevState可能无效, 详见NET_BURN_EXTMODE

        public NET_OUT_BURN_GET_STATE() {
            this.dwSize = this.size();
        }
    }

    // 雷达监测超速报警事件 智能楼宇专用 ( NET_ALARM_RADAR_HIGH_SPEED )
    public static class ALARM_RADAR_HIGH_SPEED_INFO extends SdkStructure
    {
        public NET_TIME_EX      stuTime;                              // 事件发生时间
        public float            fSpeed;                               // 速度(单位:km/h)
        public byte[]           szPlateNumber = new byte[16];         // 车牌
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           byReserved = new byte[958];           // 预留字段
    }

    // 设备巡检报警事件 智网专用 ( NET_ALARM_POLLING_ALARM )
    public static class ALARM_POLLING_ALARM_INFO extends SdkStructure
    {
        public NET_TIME_EX      stuTime;                              // 事件发生时间
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           byReserved = new byte[974];           // 预留字段
    }

    // 门禁事件 ALARM_ACCESS_CTL_EVENT
    public static class ALARM_ACCESS_CTL_EVENT_INFO extends SdkStructure {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public byte[]           szDoorName = new byte[NET_MAX_DOORNAME_LEN]; // 门禁名称
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              emEventType;                          // 门禁事件类型 参考 NET_ACCESS_CTL_EVENT_TYPE
        public int              bStatus;                              // 刷卡结果,TRUE表示成功,FALSE表示失败
        public int              emCardType;                           // 卡类型, 参考 NET_ACCESSCTLCARD_TYPE
        public int              emOpenMethod;                         // 开门方式, 参考 NET_ACCESS_DOOROPEN_METHOD
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public byte[]           szPwd = new byte[NET_MAX_CARDPWD_LEN]; // 密码
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 门读卡器ID
        public byte[]           szUserID = new byte[NET_COMMON_STRING_64]; // 开门用户
        public byte[]           szSnapURL = new byte[NET_COMMON_STRING_256]; // 抓拍照片存储地址
        public int              nErrorCode;                           // 开门失败的原因,仅在bStatus为FALSE时有效
        // 0x00 没有错误
        // 0x10 未授权
        // 0x11 卡挂失或注销
        // 0x12 没有该门权限
        // 0x13 开门模式错误
        // 0x14 有效期错误
        // 0x15 防反潜模式
        // 0x16 胁迫报警未打开
        // 0x17 门常闭状态
        // 0x18 AB互锁状态
        // 0x19 巡逻卡
        // 0x1A 设备处于闯入报警状态
        // 0x20 时间段错误
        // 0x21 假期内开门时间段错误
        // 0x30 需要先验证有首卡权限的卡片
        // 0x40 卡片正确,输入密码错误
        // 0x41 卡片正确,输入密码超时
        // 0x42 卡片正确,输入信息错误
        // 0x43 卡片正确,输入信息超时
        // 0x44 信息正确,输入密码错误
        // 0x45 信息正确,输入密码超时
        // 0x50 组合开门顺序错误
        // 0x51 组合开门需要继续验证
        // 0x60 验证通过,控制台未授权
        public int              nPunchingRecNo;                       // 刷卡记录集中的记录编号
        public int              nNumbers;                             // 抓图张数
        public int              emStatus;                             // 卡状态     NET_ACCESSCTLCARD_STATE
        public byte[]           szSN = new byte[32];                  // 智能锁序列号
        public int              emAttendanceState;                    // 考勤状态, 参考  NET_ATTENDANCESTATE
        public byte[]           szQRCode = new byte[512];             // 二维码
        public byte[]           szCallLiftFloor = new byte[16];       // 呼梯楼层号
        public int              emCardState;                          // 是否为采集卡片,参考  EM_CARD_STATE
        public byte[]           szCitizenIDNo = new byte[20];         // 证件号
        public COMPANION_CARD[] szCompanionCards = new COMPANION_CARD[MAX_COMPANION_CARD_NUM]; // 陪同者卡号信息
        public int              nCompanionCardCount;                  // 陪同者卡号个数
        public int              emHatStyle;                           // 帽子类型
        public int              emHatColor;                           // 帽子颜色
        public int              emLiftCallerType;                     // 梯控方式触发者
        public int              bManTemperature;                      // 人员温度信息是否有效
        public NET_MAN_TEMPERATURE_INFO stuManTemperatureInfo;        // 人员温度信息, bManTemperature 为TRUE 时有效
        public byte[]           szCitizenName = new byte[256];        // 证件姓名
        public int              emMask;                               // 口罩状态（EM_MASK_STATE_UNKNOWN、EM_MASK_STATE_NOMASK、EM_MASK_STATE_WEAR 有效）参考EM_MASK_STATE_TYPE
        public byte[]           szCardName = new byte[NET_MAX_CARDNAME_LEN]; // 卡命名
        public int              nFaceIndex;                           // 一人脸时的人脸序号
        public int              emUserType;                           // 用户类型( EM_USER_TYPE_ORDINARY 至 EM_USER_TYPE_DISABLED 有效 ) 参考EM_USER_TYPE
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           szCompanyName = new byte[200];        // 公司名称
        public int              nScore;                               // 人脸质量评分
        public int              nLiftNo;                              // 电梯编号
        public int              emQRCodeIsExpired;                    // 二维码是否过期。默认值0 ,EM_QRCODE_IS_EXPIRED
        public int              emQRCodeState;                        // 二维码状态,EM_QRCODE_STATE
        public NET_TIME         stuQRCSodeValidTo;                    // 二维码截止日期
        public byte[]           szDynPWD = new byte[32];              // 平台通过密码校验权限。用于动态密码校验，动态密码由手机APP生成，设备仅透传给平台
     //   public byte[]                    byReserved=new byte[1436];            // 预留字段
        public  int             nBlockId;                             // 上报事件数据序列号从1开始自增
        public byte[]           szSection = new byte[64];             // 部门名称
        public byte[]           szWorkClass = new byte[256];          // 工作班级
        /**
         * EM_TEST_ITEMS
         * */
        public  int             emTestItems;                          // 测试项目
        /**
         * NET_TEST_RESULT
         */
        public NET_TEST_RESULT  stuTestResult;                        // ESD阻值测试结果
        public byte[]           szDeviceID = new byte[128];           // 门禁设备编号
        public byte[]           szUserUniqueID = new byte[128];       // 用户唯一表示ID
        public int              bUseCardNameEx;                       // 是否使用卡命名扩展
        public byte[]           szCardNameEx = new byte[128];         // 卡命名扩展
        public byte[]           szTempPassword = new byte[64];        // 临时密码
        public byte[]           szNote = new byte[512];               // 摘要信息
        public int              nHSJCResult;                          //核酸检测报告结果   -1: 未知  2: 未检测 3: 过期
        public NET_VACCINE_INFO stuVaccineInfo = new NET_VACCINE_INFO(); // 新冠疫苗接种信息
        public NET_TRAVEL_INFO  stuTravelInfo = new NET_TRAVEL_INFO(); // 行程码信息
        public byte[]           szQRCodeEx = new byte[2048];          //大二维码内容
        public NET_HSJC_INFO    stuHSJCInfo;                          // 核酸信息
        public NET_ANTIGEN_INFO stuAntigenInfo;                       // 抗原检测信息
        public byte[]           szHealthGreenStatus = new byte[20];   // 个人健康状态 绿码:"Green" 红码:"Red" 黄码:"Yellow" 橙:"Orange" 未知:"None"
        public int              nAge;                                 //年龄
        public byte[]           szCheckOutType = new byte[32];        //签出类型
        public byte[]           szCheckOutCause = new byte[512];      //签出原因
        public int              nTargetCheck;                         //刷卡开门时，门禁后台目标校验是否为同一人，, 0：该人员无目标数据, 1：刷卡和目标人员一致, 2：刷卡和目标人员不一致
        public NET_ACCESS_CTL_OBJECT_PROPERTIES stuObjectProperties = new NET_ACCESS_CTL_OBJECT_PROPERTIES(); //动态识别的结构化信息对象,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ACCESS_CTL_OBJECT_PROPERTIES}
        public NET_BUTTON_CONTROL_INFO stuButtonControlInfo = new NET_BUTTON_CONTROL_INFO(); //按钮控制鉴权信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_BUTTON_CONTROL_INFO}
        public int              nSimilarity;                          //人脸目标识别相似度, 人脸开门时有效, 范围: 0~100
        public int              nPassResult;                          //通行结果，人员是否有同行 0-未知 1-人员进入 2-人员退出 3-人员未通行
        public int              nCustomerPWDType;                     //设备在不同的密码模式下上报的密码类型，0:未知 1,唤醒密码模式  2,陪同密码模式  3胁迫密码模式
        public byte[]           szOperatorID = new byte[36];          //平台下发操作员ID, 仅用于远程开门
        public byte[]           szTransmissionUuid = new byte[64];    //设备开门信息透传唯一标识
        public byte[]           szReserved = new byte[1256];          // 预留字节

        public ALARM_ACCESS_CTL_EVENT_INFO() {
            super();
            this.dwSize = this.size();
            for (int i = 0; i < szCompanionCards.length; ++i) {
                szCompanionCards[i] = new COMPANION_CARD();
            }

        }
    }

    // 消警事件
    public static class ALARM_ALARMCLEAR_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              bEventAction;                         // 事件动作，0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[1292];          // 补齐字节

        public ALARM_ALARMCLEAR_INFO() {
            this.dwSize = this.size();
        }

        @Override
        public String toString() {
            return "ALARM_ALARMCLEAR_INFO [dwSize=" + dwSize + ", nChannelID="
                    + nChannelID + ", stuTime=" + stuTime + ", bEventAction="
                    + bEventAction + "]";
        }
    }

    public static class NET_ALARM_TYPE
    {
        public static final int   NET_ALARM_LOCAL = 0;                  //开关量防区的报警事件(对应 NET_ALARM_ALARM_EX2 事件)
        public static final int   NET_ALARM_ALARMEXTENDED = 1;          //扩展模块报警事件(对应 NET_ALARM_ALARMEXTENDED 事件)
        public static final int   NET_ALARM_TEMP = 2;                   //温度报警事件(对应 NET_ALARM_TEMPERATURE 事件)
        public static final int   NET_ALARM_URGENCY = 3;                //紧急报警事件(对应 NET_URGENCY_ALARM_EX 事件)
        public static final int   NET_ALARM_RCEMERGENCYCALL = 4;        //紧急呼叫报警事件(对应 NET_ALARM_RCEMERGENCY_CALL 事件)
        public static final int   NET_ALARM_ALL = 5;                    //所有报警事件
    }

    // CLIENT_ControlDevice 接口的 NET_CTRL_CLEAR_ALARM 命令参数
    public static class NET_CTRL_CLEAR_ALARM extends SdkStructure {
        public int              dwSize;
        public int              nChannelID;                           // 防区通道号, -1 表示所有通道
        public int              emAlarmType;                          // 事件类型(支持的类型较少,建议用nEventType字段) NET_ALARM_TYPE
        public String           szDevPwd;                             // 登陆设备的密码,如不使用加密消警,直接赋值为NULL
        public int              bEventType;                           // 表示是否启用nEventType字段, TRUE:nEventType代替emAlarmType字段, FALSE:沿用emAlarmType字段,忽略nEventType字段
        public int              nEventType;                           // 事件类型, 对应 fMessCallBack 回调来上的lCommand字段, 即CLIENT_StartListenEx接口获得事件类型
        												    // 比如NET_ALARM_ALARM_EX2表示本地报警事件
        public byte[]           szName = new byte[128];               // 事件名称
        public int              bIsUsingName;                         // 事件名称字段是否有效

        public NET_CTRL_CLEAR_ALARM() {
            this.dwSize = this.size();
        }

        @Override
        public String toString() {
            return "NET_CTRL_CLEAR_ALARM [dwSize=" + dwSize + ", nChannelID="
                    + nChannelID + ", emAlarmType=" + emAlarmType + ", szDevPwd="
                    + szDevPwd + ", bEventType=" + bEventType + ", nEventType="
                    + nEventType + "]";
        }
    }

    // CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_START_ALARMBELL / CTRLTYPE_CTRL_STOP_ALARMBELL命令参数
    public static class NET_CTRL_ALARMBELL extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号(0开始)

        public NET_CTRL_ALARMBELL(){
            this.dwSize = this.size();
        }
    }

    // 警灯配置(对应 CFG_CMD_ALARMLAMP)
    public static class CFG_ALARMLAMP_INFO extends SdkStructure
    {
        public int              emAlarmLamp;                          // 警灯状态,参考  EM_ALARMLAMP_MODE
    }

    // 警灯状态
    public static class EM_ALARMLAMP_MODE extends SdkStructure
    {
        public static final int   EM_ALARMLAMP_MODE_UNKNOWN = -1;       // 未知
        public static final int   EM_ALARMLAMP_MODE_OFF = 0;            // 灭
        public static final int   EM_ALARMLAMP_MODE_ON = 1;             // 亮
        public static final int   EM_ALARMLAMP_MODE_BLINK = 2;          // 闪烁
    }

    // 发送的通知类型,对应CLIENT_SendNotifyToDev接口
    public static class NET_EM_NOTIFY_TYPE extends SdkStructure
    {
        public static final int   NET_EM_NOTIFY_PATROL_STATUS = 1;      // 发送巡更通知 (对应结构体 NET_IN_PATROL_STATUS_INFO, NET_OUT_PATROL_STATUS_INFO )
    }

    // 巡更状态
    public static class NET_EM_PATROL_STATUS extends SdkStructure
    {
        public static final int   NET_EM_PATROL_STATUS_UNKNOWN = 0;     // 未知状态
        public static final int   NET_EM_PATROL_STATUS_BEGIN = 1;       // 巡更开始
        public static final int   NET_EM_PATROL_STATUS_END = 2;         // 巡更结束
        public static final int   NET_EM_PATROL_STATUS_FAIL = 3;        // 巡更失败
    }

    // CLIENT_SendNotifyToDev 入参 (对应枚举 NET_EM_NOTIFY_PATROL_STATUS)
    public static class NET_IN_PATROL_STATUS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emPatrolStatus;                       // 巡更状态,参考  NET_EM_PATROL_STATUS

        public NET_IN_PATROL_STATUS_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SendNotifyToDev 出参 (对应枚举 NET_EM_NOTIFY_PATROL_STATUS)
    public static class NET_OUT_PATROL_STATUS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_PATROL_STATUS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 报警事件类型  NET_ALARM_TALKING_INVITE (设备请求对方发起对讲事件)对应的数据描述信息
    public static class ALARM_TALKING_INVITE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCaller;                             // 设备希望的对讲发起方,取值参考   EM_TALKING_CALLER
        public NET_TIME         stuTime;                              // 事件触发时间
        public byte[]           szCallID = new byte[NET_COMMON_STRING_64]; // 呼叫惟一标识符
        public int              nLevel;                               // 表示所呼叫设备所处层级
        public TALKINGINVITE_REMOTEDEVICEINFO stuRemoteDeviceInfo;    // 远端设备信息
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）

        public ALARM_TALKING_INVITE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 对讲发起方
    public static class EM_TALKING_CALLER extends SdkStructure
    {
        public static final int   EM_TALKING_CALLER_UNKNOWN = 0;        // 未知发起方
        public static final int   EM_TALKING_CALLER_PLATFORM = 1;       // 对讲发起方为平台
    }

    // Invite事件远程设备协议
    public static class TALKINGINVITE_REMOTEDEVICE_PROTOCOL extends SdkStructure
    {
        public static final int   EM_TALKINGINVITE_REMOTEDEVICE_PROTOCOL_UNKNOWN = 0; // 未知
        public static final int   EM_TALKINGINVITE_REMOTEDEVICE_PROTOCOL_HIKVISION = 1; //
    }

    // Invite事件远端设备信息
    public static class TALKINGINVITE_REMOTEDEVICEINFO extends SdkStructure
    {
        public byte[]           szIP = new byte[MAX_REMOTEDEVICEINFO_IPADDR_LEN]; // 设备IP
        public int              nPort;                                // 端口
        public int              emProtocol;                           // 协议类型,取值参考 EM_TALKINGINVITE_REMOTEDEVICE_PROTOCOL
        public byte[]           szUser = new byte[MAX_REMOTEDEVICEINFO_USERNAME_LEN]; // 用户名
        public byte[]           szPassword = new byte[MAX_REMOTEDEVICEINFO_USERPSW_LENGTH]; // 密码
        public byte[]           szReverse = new byte[1024];           // 保留字段
    }

    // IO控制命令,对应 CLIENT_QueryIOControlState 接口  和  CLIENT_IOControl 接口
    public static class NET_IOTYPE extends SdkStructure
    {
        public static final int   NET_ALARMINPUT = 1;                   // 控制报警输入,对应结构体为  ALARM_CONTROL
        public static final int   NET_ALARMOUTPUT = 2;                  // 控制报警输出，对应结构体为  ALARM_CONTROL
        public static final int   NET_DECODER_ALARMOUT = 3;             // 控制报警解码器输出，对应结构体为  DECODER_ALARM_CONTROL
        public static final int   NET_WIRELESS_ALARMOUT = 5;            // 控制无线报警输出，对应结构体为  ALARM_CONTROL
        public static final int   NET_ALARM_TRIGGER_MODE = 7;           // 报警触发方式（手动,自动,关闭）,使用  TRIGGER_MODE_CONTROL 结构体
    }

    // 报警IO控制
    public static class ALARM_CONTROL extends SdkStructure
    {
        public short            index;                                // 端口序号
        public short            state;                                // 端口状态，0 - 关闭，1 - 打开
    }

    // 报警解码器控制
    public static class DECODER_ALARM_CONTROL extends SdkStructure
    {
        public int              decoderNo;                            // 报警解码器号,从0开始
        public short            alarmChn;                             // 报警输出口,从0开始
        public short            alarmState;                           // 报警输出状态；1：打开,0：关闭
    }

    // 触发方式
    public static class TRIGGER_MODE_CONTROL extends SdkStructure
    {
        public short            index;                                // 端口序号
        public short            mode;                                 // 触发方式(0关闭1手动2自动);不设置的通道,sdk默认将保持原来的设置。
        public byte[]           bReserved = new byte[28];
    }

    // 报警输出通道的状态的配置, 对应 命令  CFG_CMD_ALARMOUT
    public static class CFG_ALARMOUT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 报警通道号(0开始)
        public byte[]           szChnName = new byte[MAX_CHANNELNAME_LEN]; // 报警通道名称
        public byte[]           szOutputType = new byte[MAX_NAME_LEN]; // 输出类型, 用户自定义
        public int              nOutputMode;                          // 输出模式, 0-自动报警, 1-强制报警, 2-关闭报警
        public int              nPulseDelay;                          // 脉冲模式输出时间, 单位为秒(0-255秒)
        public int              nSlot;                                // 根地址, 0表示本地通道, 1表示连接在第一个串口上的扩展通道, 2、3...以此类推, -1表示无效
        public int              nLevel1;                              // 第一级级联地址, 表示连接在第nSlot串口上的第nLevel1个探测器或仪表, 从0开始, -1表示无效
        public byte             abLevel2;                             // 类型为bool, 表示nLevel2字段是否存在
        public int              nLevel2;                              // 第二级级联地址, 表示连接在第nLevel1个的仪表上的探测器序号, 从0开始
        public int              emPole;                               // 输出有效模式,EM_ALARMOUT_POLE
        public NET_ALARMOUT_TIME_SCHEDULE_INFO stuTimeSchedule = new NET_ALARMOUT_TIME_SCHEDULE_INFO(); //报警输出时间段控制信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ALARMOUT_TIME_SCHEDULE_INFO}
        public byte[]           szReserved = new byte[1024];          //预留字节
    }

    // 检测采集设备报警事件, 对应事件类型 NET_ALARM_SCADA_DEV_ALARM
    public static class ALARM_SCADA_DEV_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号
        public NET_TIME         stuTime;                              // 事件发生的时间
        public byte[]           szDevName = new byte[NET_COMMON_STRING_64]; // 故障设备名称
        public byte[]           szDesc = new byte[NET_COMMON_STRING_256]; // 报警描述
        public int              nAction;                              // -1:未知 0:脉冲 1:开始 2:停止
        public byte[]           szID = new byte[NET_COMMON_STRING_32]; // 点位ID, 目前使用16字节
        public byte[]           szSensorID = new byte[NET_COMMON_STRING_32]; // 探测器ID, 目前使用16字节
        public byte[]           szDevID = new byte[NET_COMMON_STRING_32]; // 设备ID, 目前使用16字节
        public byte[]           szPointName = new byte[NET_COMMON_STRING_64]; // 点位名,与点表匹配
        public int              nAlarmFlag;                           // 0:开始, 1:结束
        public int              emDevType;                            // 设备类型，详见EM_ALARM_SCADA_DEV_TYPE
        public int              emDevStatus;                          // 设备状态，详见EM_SCADA_DEVICE_STATUS

        public ALARM_SCADA_DEV_INFO() {
            this.dwSize = this.size();
        }
    }

    //SCADA类型
    public static class EM_ALARM_SCADA_DEV_TYPE extends SdkStructure
    {
        public static final int   EM_ALARM_SCADA_DEV_UNKNOWN = 0;       // 未知
        public static final int   EM_ALARM_SCADA_DEV_LEAKAGE = 1;       // 漏水检测
        public static final int   EM_ALARM_SCADA_DEV_THCONTROLLER = 2;  // 湿温度
        public static final int   EM_ALARM_SCADA_DEV_UPS = 3;           // UPS
        public static final int   EM_ALARM_SCADA_DEV_SWITCH = 4;        // 开关电源
        public static final int   EM_ALARM_SCADA_DEV_ELECTRICMETER = 5; // 智能电表
        public static final int   EM_ALARM_SCADA_DEV_COMMERCIALPOWER = 6; // 市电检测
        public static final int   EM_ALARM_SCADA_DEV_BATTERY = 7;       // 蓄电池
        public static final int   EM_ALARM_SCADA_DEV_AIRCONDITION = 8;  // 空调
        public static final int   EM_ALARM_SCADA_DEV_ACCESS = 9;        // 门禁
        public static final int   EM_ALARM_SCADA_DEV_SMOKINGSENSOR = 10; // 烟感
        public static final int   EM_ALARM_SCADA_DEV_INFRARED = 11;     // 红外
        public static final int   EM_ALARM_SCADA_DEV_CHEMICAL = 12;     // 化工
        public static final int   EM_ALARM_SCADA_DEV_PERIMETER = 13;    // 周界
        public static final int   EM_ALARM_SCADA_DEV_DOORMAGNETISM = 14; //
        public static final int   EM_ALARM_SCADA_DEV_DISTANCE = 15;     // 测距
        public static final int   EM_ALARM_SCADA_DEV_WINDSENSOR = 16;   // 风速
        public static final int   EM_ALARM_SCADA_DEV_LOCATION = 17;     // 位置
        public static final int   EM_ALARM_SCADA_DEV_ATMOSPHERE = 18;   // 大气
        public static final int   EM_ALARM_SCADA_DEV_SOLARPOWER = 19;   // 太阳能
    }

    //设备状态
    public static class EM_SCADA_DEVICE_STATUS extends SdkStructure
    {
        public static final int   EM_SCADA_DEVICE_STATUS_KNOWN = -1;    // 未知
        public static final int   EM_SCADA_DEVICE_STATUS_NORMAL = 0;    // 正常
        public static final int   EM_SCADA_DEVICE_STATUS_ALARM = 1;     // 报警
        public static final int   EM_SCADA_DEVICE_STATUS_OFFLINE = 2;   // 离线
    }

    // 点位类型
    public static class EM_NET_SCADA_POINT_TYPE extends SdkStructure
    {
        public static final int   EM_NET_SCADA_POINT_TYPE_UNKNOWN = 0;  // 未知
        public static final int   EM_NET_SCADA_POINT_TYPE_ALL = 1;      // 所有类型
        public static final int   EM_NET_SCADA_POINT_TYPE_YC = 2;       // 遥测 模拟量输入
        public static final int   EM_NET_SCADA_POINT_TYPE_YX = 3;       // 遥信 开关量输入
        public static final int   EM_NET_SCADA_POINT_TYPE_YT = 4;       // 遥调 模拟量输出
        public static final int   EM_NET_SCADA_POINT_TYPE_YK = 5;       // 遥控 开关量输出
    }

    // CLIENT_SCADAAttachInfo()接口输入参数
    public static class NET_IN_SCADA_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbCallBack;                           // 数据回调函数, fSCADAAttachInfoCallBack 回调
        public int              emPointType;                          // 点位类型,取值参考  EM_NET_SCADA_POINT_TYPE
        public Pointer          dwUser;                               // 用户定义参数

        public NET_IN_SCADA_ATTACH_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SCADAAttachInfo()接口输出参数
    public static class NET_OUT_SCADA_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SCADA_ATTACH_INFO() {
            this.dwSize = this.size();
        }
    }

    // 监测点位信息列表
    public static class NET_SCADA_NOTIFY_POINT_INFO_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nList;                                // 监测点位信息个数
        public NET_SCADA_NOTIFY_POINT_INFO[] stuList = (NET_SCADA_NOTIFY_POINT_INFO[])new NET_SCADA_NOTIFY_POINT_INFO().toArray(MAX_SCADA_POINT_LIST_INFO_NUM); // 监测点位信息

        public NET_SCADA_NOTIFY_POINT_INFO_LIST() {
            this.dwSize = this.size();
        }
    }

    // 监测点位信息
    public static class NET_SCADA_NOTIFY_POINT_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevName = new byte[NET_COMMON_STRING_64]; // 设备名称,与getInfo获取的名称一致
        public int              emPointType;                          // 点位类型,取值参考 EM_NET_SCADA_POINT_TYPE
        public byte[]           szPointName = new byte[NET_COMMON_STRING_64]; // 点位名,与点位表的取值一致
        public float            fValue;                               // Type为YC时为浮点数
        public int              nValue;                               // Type为YX时为整数
        public byte[]           szFSUID = new byte[NET_COMMON_STRING_64]; // 现场监控单元ID(Field Unit), 即设备本身
        public byte[]           szID = new byte[NET_COMMON_STRING_64]; // 点位ID
        public byte[]           szSensorID = new byte[NET_COMMON_STRING_64]; // 探测器ID
        public NET_TIME_EX      stuCollectTime;                       // 采集时间

        public NET_SCADA_NOTIFY_POINT_INFO() {
            this.dwSize = this.size();
        }
    }

    public static class CFG_TRAFFICSNAPSHOT_NEW_INFO extends SdkStructure
    {
        public int              nCount;                               // 有效成员个数
        public CFG_TRAFFICSNAPSHOT_INFO[] stInfo = (CFG_TRAFFICSNAPSHOT_INFO[])new CFG_TRAFFICSNAPSHOT_INFO().toArray(8); // 交通抓拍表数组
    }

    // CFG_CMD_INTELLECTIVETRAFFIC
    public static class CFG_TRAFFICSNAPSHOT_INFO extends SdkStructure
    {
        public byte[]           szDeviceAddress = new byte[MAX_DEVICE_ADDRESS]; // 设备地址	UTF-8编码，256字节
        public int              nVideoTitleMask;                      // OSD叠加类型掩码	从低位到高位分别表示：0-时间 1-地点 2-车牌3-车长 4-车速 5-限速6-大车限速 7-小车限速8-超速 9-违法代码10-车道号 11-车身颜色 12-车牌类型 13-车牌颜色14-红灯点亮时间 15-违章类型 16-雷达方向 17-设备编号 18-标定到期时间 19-车型 20-行驶方向
        public int              nRedLightMargin;                      // 红灯冗余间隔时间	红灯开始的一段时间内，车辆通行不算闯红灯，单位：秒
        public float            fLongVehicleLengthLevel;              // 超长车长度最小阈值	单位：米，包含
        public float[]          arfLargeVehicleLengthLevel = new float[2]; // 大车长度阈值	单位：米，包含小值
        public float[]          arfMediumVehicleLengthLevel = new float[2]; // 中型车长度阈值	单位：米，包含小值
        public float[]          arfSmallVehicleLengthLevel = new float[2]; // 小车长度阈值	单位：米，包含小值
        public float            fMotoVehicleLengthLevel;              // 摩托车长度最大阈值	单位：米，不包含
        public BREAKINGSNAPTIMES_INFO stBreakingSnapTimes;            // 违章抓拍张数
        public DETECTOR_INFO[]  arstDetector = (DETECTOR_INFO[])new DETECTOR_INFO().toArray(MAX_DETECTOR); // 车检器配置，下标是车道号
        public int              nCarType;                             // 抓拍车辆类型	0-大小车都抓拍1-抓拍小车2-抓拍大车3-大小车都不抓拍
        public int              nMaxSpeed;                            // 当测得的速度超过最大速度时，则以最大速度计	0~255km/h
        public int              nFrameMode;                           // 帧间隔模式	1-速度自适应（超过速度上限取0间隔，低于速度下限取2间隔，中间取1间隔）2-由联动参数决定
        public int[]            arnAdaptiveSpeed = new int[2];        // 速度自适应下限和上限
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 交通抓拍联动参数
        public int              abSchemeRange;                        // BOOL类型,TRUE:方案针对相机,以车到0的值为准;FALSE:方案针对车道。不可以修改此字段数据, 只内部使用
        public int              nVideoTitleMask1;                     // 从低位到高位分别表示：0-车标 1-红灯结束时间 2-设备制造厂商 3-小车低限速 4-大车低限速 5-小车高限速 6-大车高限速 7-设备工作模式 8-通用自定义 9-车道自定义 10-抓拍触发源 11-停车场区域12-车辆类型(面包车、轿车等等) 13-中车低限速 14-中车高限速 15-道路方向 16-GPS信息
        public int              nMergeVideoTitleMask;                 // 合成图片OSD叠加类型掩码	参照nVideoTitleMask字段
        public int              nMergeVideoTitleMask1;                // 合成图片OSD叠加类型掩码	参照nVideoTitleMask1字段
        public int              nTriggerSource;                       // 触发源掩码 0-RS232 1-RS485 2-IO 3-Video 4-Net
        public int              nSnapMode;                            // 抓拍模式 0-全部抓拍 1-超速抓拍 2-逆向抓拍 3-PK模式
        public int              nWorkMode;                            // 工作模式 0-自动模式，1-线圈抓拍模式，2-线圈抓拍识别，3-视频抓拍，4-视频识别, 5-混合抓拍（带识别）
        public int              nCarThreShold;                        // 车长阈值  区分大小车长的阈值，单位: cm
        public int              nSnapType;                            // 抓拍或抓录选择 0-正常抓拍模式 1-视频抓拍模式 2-黑屏快抓模式
        public int[]            nCustomFrameInterval = new int[3];    // 自定义抓拍帧间隔 第一个元素指车速小于速度自适应下限时的抓拍帧间隔，依次类推
        public int              nKeepAlive;                           // 与雷达、车检器的默认保活周期 单位秒
        public OSD_INFO         stOSD;                                // 原始图片OSD参数配置
        public OSD_INFO         stMergeOSD;                           // 合成图片OSD参数配置
        public CFG_NET_TIME     stValidUntilTime;                     // 标定到期时间，指该时间点之前抓拍照片有效
        public RADAR_INFO       stRadar;
        public byte[]           szRoadwayCode = new byte[MAX_ROADWAYNO]; // 道路代码
        public int              nVideoTitleMask2;                     // 原始图片OSD叠加类型掩码2 从低位到高位分别表示：0-国别 1-尾气数据
        public int              nMergeVideoTitleMask2;                // 合成图片OSD叠加类型掩码2 参照nVideoTitleMask2字段
        public int              nParkType;                            // 出入口类型，0-默认( 兼容以前，不区分出口/入口 )，1-入口相机， 2-出口相机
        public int              nCoilSpeedAdjustDelayFrameTime;       // 线圈速度校正等待时间，范围【500, 4000】，单位：毫秒
        public int              bCoilSpeedAdjustEnable;               // 线圈速度校正使能，TRUE：校正 FALSE：不校正
        public int              nSnapSigMinConfidence;                // 触发雷达信号抓拍值，范围【0~100】
        public int              emMixSnapSpeedSource;                 // 视频抓拍速度来源,EM_MIX_SNAP_SPEED_SOURCE
        public CFG_ALARM_MSG_HANDLE_EX stuEventHandlerEx = new CFG_ALARM_MSG_HANDLE_EX(); //交通抓拍联动参数扩展,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_ALARM_MSG_HANDLE_EX}
    }

    public static class COIL_MAP_INFO extends SdkStructure
    {
        public int              nPhyCoilID;                           // 物理线圈号
        public int              nLogicCoilID;                         // 逻辑线圈号
    }

    // 车检器配置
    public static class DETECTOR_INFO extends SdkStructure
    {
        public int              nDetectBreaking;                      // 违章类型掩码	从低位到高位依次是：0-正常1-闯红灯2-压线3-逆行4-欠速5-超速6-有车占道
        // 7-黄牌占道 8-闯黄灯 9-违章占公交车道 10-不系安全带 11-驾驶员抽烟 12-驾驶员打电话
        public COILCONFIG_INFO[] arstCoilCfg = (COILCONFIG_INFO[])new COILCONFIG_INFO().toArray(MAX_COILCONFIG); // 线圈配置数组
        public int              nRoadwayNumber;                       // 车道号	1-16 ; 0表示不启用
        public int              nRoadwayDirection;                    // 车道方向（车开往的方向）	0-南向北 1-西南向东北 2-东 3-西北向东南 4-北向南 5-东北向西南 6-东向西 7-东南向西北 8-忽略
        public int              nRedLightCardNum;                     // 卡口图片序号	表示将电警中的某一张图片作为卡口图片（共三张），0表示不采用，1~3,表示采用对应序号的图片
        public int              nCoilsNumber;                         // 线圈个数	1-3
        public int              nOperationType;                       // 业务模式	0-卡口电警1-电警2-卡口
        public int[]            arnCoilsDistance = new int[3];        // 两两线圈的间隔	范围0-1000，单位为厘米
        public int              nCoilsWidth;                          // 每个线圈的宽度	0~200cm
        public int[]            arnSmallCarSpeedLimit = new int[2];   // 小型车辆速度下限和上限	0~255km/h，不启用大小车限速时作为普通车辆限速
        public int[]            arnBigCarSpeedLimit = new int[2];     // 大型车辆速度下限和上限	0~255km/h，启用大小车限速时有效
        public int              nOverSpeedMargin;                     // 限高速宽限值	单位：km/h
        public int              nBigCarOverSpeedMargin;               // 大车限高速宽限值	单位：km/h，启用大小车限速时有效
        public int              nUnderSpeedMargin;                    // 限低速宽限值	单位：km/h
        public int              nBigCarUnderSpeedMargin;              // 大车限低速宽限值	单位：km/h，启用大小车限速时有效
        public byte             bSpeedLimitForSize;                   // bool类型,是否启用大小车限速
        public byte             bMaskRetrograde;                      // bool类型,逆行是否视为违章行为
        public byte[]           byReserved = new byte[2];             // 保留对齐
        public byte[]           szDrivingDirection = new byte[3*MAX_DRIVINGDIRECTION]; // "DrivingDirection" : ["Approach", "", ""],行驶方向
        // "Approach"-上行，即车辆离设备部署点越来越近；"Leave"-下行，
        // 即车辆离设备部署点越来越远，第二和第三个参数分别代表上行和
        // 下行的两个地点，UTF-8编码
        public int              nOverPercentage;                      // 超速百分比，超过限速百分比后抓拍
        public int              nCarScheme;                           // 具体的方案Index,具体方案含义参参考打包环境local.png;根据CFG_TRAFFICSNAPSHOT_INFO.abSchemeRange字段区分作用范围
        public int              nSigScheme;                           // 同上，非卡口使用
        public int              bEnable;                              // BOOL类型,车道是否有效，只有有效时才抓拍
        public int[]            nYellowSpeedLimit = new int[2];       //黄牌车限速上限和下限 范围0~255km/h
        public int              nRoadType;                            //工作路段 0 普通公路 1 高速公路
        public int              nSnapMode;                            //抓拍模式 0-全部抓拍 1-超速抓拍 2-逆向抓拍
        public int              nDelayMode;                           //延时抓拍方案 0-使DelaySnapshotDistance，1-使用DelayTime
        public int              nDelayTime;                           //延时抓拍时间 闯红灯第三张抓拍位置距离最后一个线圈的时间，单位毫秒
        public int              nTriggerMode;                         //触发模式 0-入线圈触发 1-出线圈触发 2-出入都抓拍 3-关闭
        public int              nErrorRange;                          //速度误差值，进线圈2与进线圈3之间的速度误差值，若实际误差大于或等于该值，视速度无效，否则取平均速度 0-20
        public double           dSpeedCorrection;                     //速度校正系数，即速度值为测出的值乘以该系数
        public int[]            nDirection = new int[2];              //相对车道方向需要上报车辆行驶方向,nDirection[0] 0--空 1--正向 ; nDirection[1] 0--空 1--反向
        public byte[]           szCustomParkNo = new byte[CFG_COMMON_STRING_32 + 1]; // 自定义车位号（停车场用）
        public byte[]           btReserved = new byte[3];
        public int              nCoilMap;                             // 有多少对线圈映射关系
        public COIL_MAP_INFO[]  stuCoilMap = (COIL_MAP_INFO[])new COIL_MAP_INFO().toArray(16); ;                  // 线圈号映射关系
    }

    // 线圈配置
    public static class COILCONFIG_INFO extends SdkStructure
    {
        public int              nDelayFlashID;                        // 延时闪光灯序号	每个线圈对应的延时闪关灯序号，范围0~5，0表示不延时任何闪光灯
        public int              nFlashSerialNum;                      // 闪光灯序号	范围0~5，0表示不打开闪光灯
        public int              nRedDirection;                        // 红灯方向	每个线圈对应的红灯方向：0-不关联,1-左转红灯,2-直行红灯,3-右转红灯,4-待左,5-待直,6-待右, 只在电警中有效
        public int              nTriggerMode;                         // 线圈触发模式	触发模式：0-入线圈触发1-出线圈触发
        public int              nFlashSerialNum2;                     //多抓第二张对应闪光灯序号 范围0~5，0表示不打开闪光灯
        public int              nFlashSerialNum3;                     //多抓第三张对应闪光灯序号 范围0~5，0表示不打开闪光灯
    }

    // 违章抓拍张数
    public static class BREAKINGSNAPTIMES_INFO extends SdkStructure
    {
        public int              nNormal;                              // 正常
        public int              nRunRedLight;                         // 闯红灯
        public int              nOverLine;                            // 压线
        public int              nOverYellowLine;                      // 压黄线
        public int              nRetrograde;                          // 逆向
        public int              nUnderSpeed;                          // 欠速
        public int              nOverSpeed;                           // 超速
        public int              nWrongRunningRoute;                   // 有车占道
        public int              nYellowInRoute;                       // 黄牌占道
        public int              nSpecialRetrograde;                   // 特殊逆行
        public int              nTurnLeft;                            // 违章左转
        public int              nTurnRight;                           // 违章右转
        public int              nCrossLane;                           // 违章变道
        public int              nU_Turn;                              // 违章调头
        public int              nParking;                             // 违章停车
        public int              nWaitingArea;                         // 违章进入待行区
        public int              nWrongRoute;                          // 不按车道行驶
        public int              nParkingSpaceParking;                 // 车位有车
        public int              nParkingSpaceNoParking;               // 车位无车
        public int              nRunYellowLight;                      // 闯黄灯
        public int              nStay;                                // 违章停留
        public int              nPedestrainPriority;                  // 斑马线行人优先违章
        public int              nVehicleInBusRoute;                   // 违章占道
        public int              nBacking;                             // 违章倒车
        public int              nOverStopLine;                        // 压停止线
        public int              nParkingOnYellowBox;                  // 黄网格线停车
        public int              nRestrictedPlate;                     // 受限车牌
        public int              nNoPassing;                           // 禁行
        public int              nWithoutSafeBelt;                     // 不系安全带
        public int              nDriverSmoking;                       // 驾驶员抽烟
        public int              nDriverCalling;                       // 驾驶员打电话
        public int              nPedestrianRunRedLight;               // 行人闯红灯
        public int              nPassNotInOrder;                      // 未按规定依次通行
    }

    // OSD属性
    public static class OSD_INFO extends SdkStructure
    {
        public BLACK_REGION_INFO stBackRegionInfo;                    //OSD黑边属性
        public int              nOSDAttrScheme;                       //OSD属性配置方案 0=未知 , 1=全体OSD项共用属性 , 2=每个OSD项一个属性
        public OSD_ATTR_SCHEME  stOSDAttrScheme;                      //OSD属性配置方案内容
        public OSD_CUSTOM_SORT[] stOSDCustomSorts = (OSD_CUSTOM_SORT[])new OSD_CUSTOM_SORT().toArray(MAX_OSD_CUSTOM_SORT_NUM); //OSD叠加内容自定义排序
        public int              nOSDCustomSortNum;
        public int              nRedLightTimeDisplay;                 //OSD红灯时间配置 0=未知,1=违法最后一张,2=所有张
        public byte             cSeperater;                           //OSD不同项之间的分隔符
        public byte[]           bReserved = new byte[3];              //字节对齐
        public byte[]           szOSDOrder = new byte[MAX_CONF_CHAR];
        public int              nOSDContentScheme;                    //0=未知, 1=Mask , 2=CustomizeSort
        public OSD_CUSTOM_INFO  stOSDCustomInfo;                      //OSD自定义项
    }

    // OSD黑边
    public static class BLACK_REGION_INFO extends SdkStructure
    {
        public int              nHeight;                              //黑边高度 取值范围：0 ~ ( 8192-原图片高度)
        public int              nOSDPosition;                         //黑边位置 0=未知 , 1=顶部 , 2=底部
    }

    // OSD属性配置方案内容
    public static class OSD_ATTR_SCHEME extends SdkStructure
    {
        public OSD_WHOLE_ATTR   stWholeAttr;                          //全体OSD项共用属性
    }

    // 全体OSD项共用属性
    public static class OSD_WHOLE_ATTR extends SdkStructure
    {
        public int              bPositionAsBlackRegion;               //BOOL类型,位置是否同黑边相同,true时，下面的Position无效,BOOL类型
        public CFG_RECT         stPostion;                            //位置,不能超过图片范围
        public int              bNewLine;                             //BOOL类型,超出矩形范围是否换行,bPositionAsBlackRegion为true时有效,BOOL类型
        public int              bLoneVehicle;                         //BOOL类型,车辆信息独立显示,true 一行显示一辆车信息,false 允许多辆车信息显示在一行,BOOL类型
    }

    // OSD叠加内容自定义排序
    public static class OSD_CUSTOM_SORT extends SdkStructure
    {
        public OSD_CUSTOM_ELEMENT[] stElements = (OSD_CUSTOM_ELEMENT[])new OSD_CUSTOM_ELEMENT().toArray(MAX_OSD_CUSTOM_SORT_ELEM_NUM); //具体叠加元素
        public int              nElementNum;
    }

    // OSD具体叠加元素
    public static class OSD_CUSTOM_ELEMENT extends SdkStructure
    {
        public int              nNameType;                            //名称类型,	0:szName字段含义参照szOSDOrder字段定义的项
        //          1:"Name"字段表示自定义项，无需解析
        public byte[]           szName = new byte[MAX_OSD_CUSTOM_VALUE_LEN]; // 该项名称
        public byte[]           szPrefix = new byte[MAX_PRE_POX_STR_LEN]; // 叠加前缀字符串
        public byte[]           szPostfix = new byte[MAX_PRE_POX_STR_LEN]; // 叠加后缀字符串
        public int              nSeperaterCount;                      // 后面添加分隔符个数
    }

    // OSD自定义项
    public static class OSD_CUSTOM_INFO extends SdkStructure
    {
        public OSD_CUSTOM_GENERAL_INFO[] stGeneralInfos = (OSD_CUSTOM_GENERAL_INFO[])new OSD_CUSTOM_GENERAL_INFO().toArray(MAX_OSD_CUSTOM_GENERAL_NUM); //具体叠加元素
        public int              nGeneralInfoNum;
}
