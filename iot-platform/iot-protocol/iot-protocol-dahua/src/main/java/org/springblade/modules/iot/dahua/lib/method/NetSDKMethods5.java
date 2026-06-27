package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 5 部分
 */
public interface NetSDKMethods5 {
        public int              nSceneCount;                          // 实际场景个数
        public SCENE_TYPE_LIST[] szSceneTypeListArr = (SCENE_TYPE_LIST[])new SCENE_TYPE_LIST().toArray(MAX_SCENE_TYPE_LIST_SIZE); // 场景列别，同一视频通道下启用多个场景时，表示第2个之后的方案(可选)
        public int              emDepthType;                          // 画面景深, 参考 EM_DEPTH_TYPE
        public int              nPtzPresetId;                         // 云台预置点编号，0~255
        public int              unLongitude;                          // 经度 单位百万分之一度
        public int              unLatitude;                           // 纬度 单位百万分之一度
        public int              bSceneTypeListEx;                     // szSceneTypeListEx 是否有效，当为TRUE时，使用 szSceneTypeListEx；否则使用 szSceneTypeList
        public int              nSceneCountEx;                        // 实际场景个数扩展，szSceneTypeListEx 的有效个数
        public byte[]           szSceneTypeListEx = new byte[32*128]; // 场景列表扩展，szSceneTypeList 扩展字段
        public CFG_ANATOMYTEMP_SCENCE_INFO stuAnatomyTempScene;       // 人体测温场景配置
        public Pointer          pstuDetectRegionsInfo = new Pointer(new CFG_DETECT_REGIONS_INFO().size()); // 规则相关检测区域信息,CFG_DETECT_REGIONS_INFO
        public int              nMaxDetectRegions;                    // 最大规则相关检测区域个数,内存由客户申请
        public int              nDetectRegionsNum;                    // 规则相关检测区域信息个数
        public CFG_DETAIL_DRIVEASSISTANT_INFO stuDriveAssistant = new CFG_DETAIL_DRIVEASSISTANT_INFO(); // 驾驶辅助场景配置
        public int              bParkingSpaceChangeEnable;            // 车位变更使能 FALSE：不使能 TRUE:使能
        public int              emSceneType;                          // szSceneType的枚举形式
        public int              nSceneCountEm;                        // SceneTypeList数量
        public int[]            emSceneTypeList = new int[32];        // szSceneTypeList的枚举形式
        public CFG_PARKING_STATISTICS_INFO stuParkingStatistics;      // 车位统计场景配置信息, szSubType为ParkingStatistics时有效

        @Override
        public int fieldOffset(String name) {
            return super.fieldOffset(name);
        }

        public CFG_ANALYSEGLOBAL_INFO(){

            this.setAutoSynch(false);


        }
    }

    // 人体测温场景配置
    public static class CFG_ANATOMYTEMP_SCENCE_INFO extends SdkStructure
    {
        public int              emFaceDetectType;                     // 目标检测智能类型（参考CFG_EM_FACEDETECT_TYPE）
        public CFG_FACEDETECT_VISUAL_INFO stuVisual;                  // 可见光配置，emFaceDetectType为CFG_EM_FACEDETECT_TYPE_VISUAL或CFG_EM_FACEDETECT_TYPE_TIMESECTION时有效
        public CFG_TIME_SECTION stuTimeSection;                       // 可见光时间段，emFaceDetectType为CFG_EM_FACEDETECT_TYPE_TIMESECTION时有效
        public byte[]           byReserved = new byte[1024];          // 预留字段
    }

    // 可见光配置
    public static class CFG_FACEDETECT_VISUAL_INFO extends SdkStructure
    {
        public int              nFaceAngleUp;                         // 需要检测的人脸向上(向下)最大偏角,超过此角度不上报,单位度,0-90。
        public int              nFaceAngleRight;                      // 需要检测的人脸向右(向左)最大偏角,超过此角度不上报,单位度,0-90。
        public int              nFaceRollRight;                       // 需要检测的人脸向右（向左）歪头最大偏角,超过此角度不上报,单位度,0-90。
        public int              bTempOptimization;                    //是否开启智能优选
        public int              bEyesWidthDetection;                  //是否开启检测，换算出目标距离
        public byte[]           byReserved = new byte[1016];          // 预留字段
    }

    public static class PLATE_HINT extends SdkStructure
    {
        public byte[]           szPlateHints = new byte[MAX_NAME_LEN]; // 车牌字符暗示数组，在拍摄图片质量较差车牌识别不确定时，根据此数组中的字符进行匹配，数组下标越小，匹配优先级越高
    }

    public static class SCENE_TYPE_LIST extends SdkStructure
    {
        public byte[]           szSceneTypeList = new byte[CFG_COMMON_STRING_16]; // 场景列别，同一视频通道下启用多个场景时，表示第2个之后的方案(可选)
    }

    // 车道信息
    public static class CFG_LANE extends SdkStructure
    {
        public int              nLaneId;                              // 车道编号
        public int              nDirection;                           // 车道方向(车开往的方向),0-北 1-东北 2-东 3-东南 4-南 5-西南 6-西 7-西北
        public CFG_POLYLINE[]   stuLeftLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 左车道线，车道线的方向表示车道方向，沿车道方向左边的称为左车道线
        public int              nLeftLineNum;                         // 左车道线顶点数
        public CFG_POLYLINE[]   stuRightLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 右车道线，车道线的方向表示车道方向，沿车道方向右边的称为右车道线
        public int              nRightLineNum;                        // 右车道线顶点数
        public int              nLeftLineType;                        // 左车道线属性，1-表示白实线，2- 白虚线，3- 黄线
        public int              nRightLineType;                       // 右车道线属性，1-表示白实线，2- 白虚线，3- 黄线
        public int              bDriveDirectionEnable;                // 车道行驶方向使能, 1-true  0-false
        public int              nDriveDirectionNum;                   // 车道行驶方向数
        public DRIVE_DIRECTION[] szDriveDirectionArr = (DRIVE_DIRECTION[])new DRIVE_DIRECTION().toArray(MAX_LIGHT_DIRECTION); // 车道行驶方向，"Straight" 直行，"TurnLeft" 左转，"TurnRight" 右转,"U-Turn":掉头
        public int              nStopLineNum;                         // 车道对应停止线顶点数
        public CFG_POLYLINE[]   stuStopLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 车道对应停止线
        public int              nTrafficLightNumber;                  // 车道对应的红绿灯组编号
        public byte             abDetectLine;                         // 对应能力集
        public byte             abPreLine;
        public byte             abPostLine;
        public byte[]           byReserved = new byte[1];
        public int              nDetectLine;
        public CFG_POLYLINE[]   stuDetectLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 车道对应的检测线
        public int              nPreLine;
        public CFG_POLYLINE[]   stuPreLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 车道对应的前置线
        public int              nPostLine;
        public CFG_POLYLINE[]   stuPostLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 车道对应的后置线
        public CFG_TRAFFIC_FLOWSTAT_DIR_INFO stuTrafficFlowDir;       // 车道流量信息
        public int              emRankType;                           // 道路等级，用于车流量统计上报交通状态, 参考 EM_LANE_RANK_TYPE
        public int              nRoadwayNumber;                       // 用户自定义车道号, 1-16
    }

    public static class DRIVE_DIRECTION extends SdkStructure
    {
        public byte[]           szDriveDirection = new byte[MAX_NAME_LEN]; // 车道行驶方向，"Straight" 直行，"TurnLeft" 左转，"TurnRight" 右转,"U-Turn":掉头
    }

    // 折线的端点信息
    public static class CFG_POLYLINE extends SdkStructure
    {
        public int              nX;                                   //0~8191
        public int              nY;
    }

    // 车辆流量统计车道方向信息
    public static class CFG_TRAFFIC_FLOWSTAT_DIR_INFO extends SdkStructure
    {
        public int              emDrivingDir;                         //行驶方向, 参考 CFG_FLOWSTAT_DIRECTION
        public byte[]           szUpGoing = new byte[CFG_FLOWSTAT_ADDR_NAME]; //上行地点
        public byte[]           szDownGoing = new byte[CFG_FLOWSTAT_ADDR_NAME]; //下行地点
    }

    // 交通灯组配置信息
    public static class CFG_LIGHTGROUPS extends SdkStructure
    {
        public int              nLightGroupId;                        // 灯组编号
        public CFG_RECT         stuLightLocation;                     // 灯组坐标
        public int              nDirection;                           // 灯组的方向,1- 灯组水平向,2- 灯组垂直向
        public int              bExternalDetection;                   // 是否为外接红绿灯信号,当外接红绿灯时，以外界信号为判断依据。外界信号每次跳变时通知
        public int              bSwingDetection;                      // 是否支持自适应灯组摇摆检测,在风吹或者容易震动的场景下，位置会进行一定的浮动偏差。如果由算法自行检测，会增加检测时间
        public int              nLightNum;                            // 灯组中交通灯的数量
        public CFG_LIGHTATTRIBUTE[] stuLightAtrributes = (CFG_LIGHTATTRIBUTE[])new CFG_LIGHTATTRIBUTE().toArray(MAX_LIGHT_NUM); // 灯组中各交通灯的属性
    }

    // 交通灯属性
    public static class CFG_LIGHTATTRIBUTE extends SdkStructure
    {
        public int              bEnable;                              // 当前交通灯是否有效，与车辆通行无关的交通需要设置无效
        public int              nTypeNum;
        public LIGHT_TYPE[]     szLightTypeArr = (LIGHT_TYPE[])new LIGHT_TYPE().toArray(MAX_LIGHT_TYPE); // 当前交通灯显现内容（包括:红-Red,黄-Yellow,绿-Green,倒计时-Countdown），如某交通灯可以显示红黄绿三种颜色，某交通灯只显示倒计时
        public int              nDirectionNum;
        public DIRECTION[]      szDirectionArr = (DIRECTION[])new DIRECTION().toArray(MAX_LIGHT_DIRECTION); // 交通灯指示的行车方向,"Straight": 直行，"TurnLeft":左转，"TurnRight":右转，"U-Turn": 掉头
        public int              nYellowTime;                          // 黄灯亮时间
    }

    public static class LIGHT_TYPE extends SdkStructure
    {
        public byte[]           szLightType = new byte[MAX_NAME_LEN]; // 当前交通灯显现内容（包括:红-Red,黄-Yellow,绿-Green,倒计时-Countdown），如某交通灯可以显示红黄绿三种颜色，某交通灯只显示倒计时
    }

    public static class DIRECTION extends SdkStructure
    {
        public byte[]           szDirection = new byte[MAX_NAME_LEN]; // 交通灯指示的行车方向,"Straight": 直行，"TurnLeft":左转，"TurnRight":右转，"U-Turn": 掉头
    }

    // 交通灯颜色校正配置
    public static class CFG_ADJUST_LIGHT_COLOR extends SdkStructure
    {
        public int              nMode;                                // 红灯颜色校正模式 0:未定义 1:红绿灯才校正 2:一直校正
        public int              bEnable;                              // 是否允许图片红绿灯颜色校正
        public int              nLevel;                               // 校正等级 范围0~100，数值越大矫正越明显
        public int              bVideoEnable;                         // 是否启用视频涂红功能，存在此项时，Level值使用LevelSeparate下的Level值
        public ADJUST_LEVEL_SEP[] stLevelSep = (ADJUST_LEVEL_SEP[])new ADJUST_LEVEL_SEP().toArray(4); // 分立等级，目前为4个
    }

    // 交通灯颜色校正配置，分立项
    public static class ADJUST_LEVEL_SEP extends SdkStructure
    {
        public int              nType;                                // 0：未定义，1：视频，2：图片
        public int              nTime;                                // 0：未定义，1：白天，2：夜晚
        public int              nLevel;                               // 范围0~100，数值越大矫正越明显
    }

    public static class CFG_PARKING_SPACE extends SdkStructure
    {
        public int              nNumber;                              //车位编号
        public CFG_REGION       stArea;                               //检测区域
        public int              nShieldAreaNum;                       //有效屏蔽区个数
        public CFG_REGION[]     stShieldArea = (CFG_REGION[])new CFG_REGION().toArray(MAX_SHIELD_AREA_NUM); //屏蔽区域
        public byte[]           szCustomParkNo = new byte[CFG_COMMON_STRING_32]; // 自定义车位名称
        //public int             nPtzPresetId;							     // 云台预置点编号，0~255
        public BYTE_ARRAY_32[]  szAssociateDevice = new BYTE_ARRAY_32[16]; //关联到的外设唯一标识
        public int              nAssociateDeviceNum;                  //关联到的外设唯一标识个数
        public byte[]           szReserved = new byte[1020];          //预留字节
    }

    public static class CFG_STAFF extends SdkStructure
    {
        public CFG_POLYLINE     stuStartLocation;                     // 起始坐标点
        public CFG_POLYLINE     stuEndLocation;                       // 终止坐标点
        public float            nLenth;                               // 实际长度,单位米
        public int              emType;                               // 标尺类型, 参考 EM_STAFF_TYPE
    }

    // 标定区域,普通场景使用
    public static class CFG_CALIBRATEAREA_INFO extends SdkStructure
    {
        public int              nLinePoint;                           // 水平方向标尺线顶点数
        public CFG_POLYGON[]    stuLine = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYLINE_NUM); // 水平方向标尺线
        public float            fLenth;                               // 实际长度
        public CFG_REGION       stuArea;                              // 区域
        public int              nStaffNum;                            // 垂直标尺数
        public CFG_STAFF[]      stuStaffs = (CFG_STAFF[])new CFG_STAFF().toArray(MAX_STAFF_NUM); // 垂直标尺
        public int              emType;                               // 区域类型, 参考 EM_CALIBRATEAREA_TYPE
        public int              emMethodType;                         // 标定方式, 参考  EM_METHOD_TYPE
    }

    // 目标识别场景
    public static class CFG_FACERECOGNITION_SCENCE_INFO extends SdkStructure
    {
        public double           dbCameraHeight;                       // 摄像头离地高度 单位：米
        public double           dbCameraDistance;                     // 摄像头离地面检测区域中心的水平距离 单位：米
        public int              nMainDirection;                       // 人流主要方向顶点数
        public CFG_POLYGON[]    stuMainDirection = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYLINE_NUM); // 人流主要方向，第一个点是起始点，第二个点是终止点
        public byte             byFaceAngleDown;                      // 需要检测的人脸向下最大偏角, 单位度，-45~45，负数表示人脸向画面上边，正数表示人脸向画面下边，0表示人脸垂直方向上正对着摄像头。
        public byte             byFaceAngleUp;                        // 需要检测的人脸向上最大偏角,单位度，-45~45，负数表示人脸向画面上边，正数表示人脸向画面下边，0表示人脸垂直方向上正对着摄像头。
        public byte             byFaceAngleLeft;                      // 需要检测的人脸向左最大偏角,单位度，-45~45，负数表示人脸向画面左边，正数表示人脸向画面右边，0表示人脸水平方向上正对着摄像头
        public byte             byFaceAngleRight;                     // 需要检测的人脸向右最大偏角,单位度，-45~45，负数表示人脸向画面左边，正数表示人脸向画面右边，0表示人脸水平方向上正对着摄像头
        public int              emDetectType;                         // 目标检测类型, 参考 EM_FACEDETECTION_TYPE
    }

    // 目标检测场景
    public static class CFG_FACEDETECTION_SCENCE_INFO extends SdkStructure
    {
        public double           dbCameraHeight;                       // 摄像头离地高度 单位：米
        public double           dbCameraDistance;                     // 摄像头离地面检测区域中心的水平距离 单位：米
        public int              nMainDirection;                       // 人流主要方向顶点数
        public CFG_POLYGON[]    stuMainDirection = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYLINE_NUM); // 人流主要方向，第一个点是起始点，第二个点是终止点
        public byte             byFaceAngleDown;                      // 需要检测的人脸向下最大偏角, 单位度，-45~45，负数表示人脸向画面上边，正数表示人脸向画面下边，0表示人脸垂直方向上正对着摄像头。
        public byte             byFaceAngleUp;                        // 需要检测的人脸向上最大偏角,单位度，-45~45，负数表示人脸向画面上边，正数表示人脸向画面下边，0表示人脸垂直方向上正对着摄像头。
        public byte             byFaceAngleLeft;                      // 需要检测的人脸向左最大偏角,单位度，-45~45，负数表示人脸向画面左边，正数表示人脸向画面右边，0表示人脸水平方向上正对着摄像头
        public byte             byFaceAngleRight;                     // 需要检测的人脸向右最大偏角,单位度，-45~45，负数表示人脸向画面左边，正数表示人脸向画面右边，0表示人脸水平方向上正对着摄像头
        public int              emDetectType;                         // 目标检测类型, 参考 EM_FACEDETECTION_TYPE
    }

    public static class CFG_TIME_PERIOD extends SdkStructure
    {
        public CFG_TIME         stuStartTime;
        public CFG_TIME         stuEndTime;
    }

    public static class CFG_TIME extends SdkStructure
    {
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒
    }

    // 多场景标定白天和黑夜配置
    public static class CFG_TIME_PERIOD_SCENE_INFO extends SdkStructure
    {
        public int              dwMaxTimePeriodSceneNum;              // 多场景标定白天和黑夜配置最大个数(需要申请此大小内存)
        public int              dwRetTimePeriodSceneNum;              // 实际包含多场景标定白天和黑夜配置个数
        public Pointer          pstuTimePeriodScene;                  // 多场景标白天和黑夜配置域单元,由用户申请内存，大小为sizeof(CFG_TIME_PERIOD_SCENE_UNIT)*dwMaxTimePeriodSceneNum
        // 指向  CFG_TIME_PERIOD_SCENE_UNIT[]
    }

    // 多场景标定区域配置
    public static class CFG_CALIBRATEAREA_SCENE_INFO extends SdkStructure
    {
        public int              dwMaxSceneCalibrateAreaNum;           // 多场景标定区域最大个数(需要申请此大小内存)
        public int              dwRetSceneCalibrateAreaNum;           // 实际包含多场景标定区域个数
        public Pointer          pstuCalibrateArea;                    // 多场景标定区域单元, 由用户申请内存, 指向 CFG_CALIBRATEAREA_SCENE_UNIT[]。
        // 大小为  sizeof(CFG_CALIBRATEAREA_SCENE_UNIT)*dwMaxSceneCalibrateAreaNum
    }

    // 昼夜算法切换模式
    public static class CFG_TIMEPERIOD_SWITCH_MODE extends SdkStructure
    {
        public static final int   CFG_TIMEPERIOD_SWITCH_MODE_UNKNOWN = 0; // 未知
        public static final int   CFG_TIMEPERIOD_SWITCH_MODE_BYCOLOR = 1; // 通过色彩切换
        public static final int   CFG_TIMEPERIOD_SWITCH_MODE_BYBRIGHTNESS = 2; // 通过亮度切换
        public static final int   CFG_TIMEPERIOD_SWITCH_MODE_BYPOS = 3; // 通过经纬度计算日出日落时间切换
    }

    // 视频分析全局配置场景
    public static class CFG_ANALYSEGLOBAL_SCENE extends SdkStructure
    {
        public byte[]           szSceneType = new byte[MAX_NAME_LEN]; // 应用场景,详见"支持的场景列表"
        public union            union = new union();
        // 以下为场景具体信息, 根据szSceneType决定哪个场景有效
        public static class union extends Union {
            public CFG_FACEDETECTION_SCENCE_INFO	stuFaceDetectionScene;				// 目标检测场景/目标识别检查
            public CFG_TRAFFIC_SCENE_INFO			stuTrafficScene;					// 交通场景
            public CFG_NORMAL_SCENE_INFO			stuNormalScene;						// 普通场景/远景场景/中景场景/近景场景/室内场景/人数统计场景
            public CFG_TRAFFIC_TOUR_SCENE_INFO		stuTrafficTourScene;				// 交通巡视场景
            public CFG_CROWD_SCENE_INFO			    stuCrowdScene;			// 人群态势和人群密度场景
            public CFG_ANATOMYTEMP_SCENCE_INFO		stuAnatomyTempScene;	// 人体测温场景
        }
        public int              emDepthType;                          // 画面景深, 参考 EM_DEPTH_TYPE
        public int              nPtzPresetId;                         // 云台预置点编号，0~255
        // 以下是有多个大类业务的情况
        public int              nSceneListCount;                      // 实际场景个数
        public SCENE_TYPE_LIST[] szSceneTypeListArr = (SCENE_TYPE_LIST[])new SCENE_TYPE_LIST().toArray(MAX_SCENE_TYPE_LIST_SIZE); // 场景列别，同一视频通道下启用多个场景时，表示第2个之后的方案
        // 多个大类业务时有效
        public CFG_INTELLI_UNIFORM_SCENE stuUniformScene;             // 统一场景配置
        public int              bSceneTypeListEx;                     // szSceneTypeListEx 是否有效，当为TRUE时，使用szSceneTypeListEx
        public int              nSceneListCountEx;                    // 实际场景个数扩展，szSceneTypeListEx 的有效个数
        public byte[]           szSceneTypeListEx = new byte[32*128]; // 场景列表扩展，szSceneTypeList 的扩展字段
        public Pointer          pstuDetectRegionsInfo;                // 规则相关检测区域信息,CFG_DETECT_REGIONS_INFO
        public int              nMaxDetectRegions;                    // 最大规则相关检测区域个数,内存由客户申请
        public int              nDetectRegionsNum;                    // 规则相关检测区域信息个数
    }

    public static class CFG_CROWD_SCENE_INFO extends SdkStructure {
        public float            fCameraHeight;                        // 摄像头离地高度	单位：米
    }

    // 交通场景
    public static class CFG_TRAFFIC_SCENE_INFO extends SdkStructure
    {
        public int              abCompatibleMode;
        public int              nCompatibleMode;                      // 0:"OldTrafficRule" : 交通老规则兼容模式;1:"NewTrafficRule" :  交通新规则兼容模式;-1:字符串错误
        public float            fCameraHeight;                        // 摄像头离地高度	单位：米
        public float            fCameraDistance;                      // 摄像头离地面检测区域中心的水平距离	单位：米
        public byte[]           szSubType = new byte[MAX_NAME_LEN];   // 交通场景的子类型,"Gate" 卡口类型,"Junction" 路口类型
        // "Tunnel"隧道类型 , "ParkingSpace"车位检测类型
        // "Bridge"桥梁类型
        // "Freeway"高速公路类型
        public CFG_POLYGON      stuNearDetectPoint;                   // 近景检测点
        public CFG_POLYGON      stuFarDectectPoint;                   // 远景检测点
        public int              nNearDistance;                        // NearDetectPoint,转换到实际场景中时,离摄像头垂直线的水平距离
        public int              nFarDistance;                         // FarDectectPoint,转换到实际场景中时,离摄像头垂直线的水平距离
        public int              nPlateHintNum;                        // 车牌字符暗示个数
        public PLATE_HINT[]     szPlateHintsArr = (PLATE_HINT[])new PLATE_HINT().toArray(MAX_PLATEHINT_NUM); // 车牌字符暗示数组，在拍摄图片质量较差车牌识别不确定时，根据此数组中的字符进行匹配，数组下标越小，匹配优先级越高
        public int              nLaneNum;                             // 车道数
        public CFG_LANE[]       stuLanes = (CFG_LANE[])new CFG_LANE().toArray(MAX_LANE_NUM); // 车道信息
        public int              nLightGroupNum;                       // 灯组数
        public CFG_LIGHTGROUPS[] stLightGroups = (CFG_LIGHTGROUPS[])new CFG_LIGHTGROUPS().toArray(MAX_LIGHTGROUP_NUM); // 灯组配置信息
        public int              bHangingWordPlate;                    // 是否识别挂字车牌
        public int              bNonStdPolicePlate;                   // 是否识别非标准警牌
        public int              bYellowPlateLetter;                   // 是否识别黄牌字母
        public int              nReportMode;                          // 上报模式，0：未定义，1：上报所有违章车辆，2：上报车牌识别成功的违章车辆
        public int              nPlateMatch;                          // 车牌匹配率，0~100，多次识别车牌使用,表示重复识别车牌和前一次识别车牌的一致性要求
        public int              nJudgment;                            // 违章判定依据，0：未定义，1：按车道，2：按行驶方向，3：按车道和行驶方向
        public int              nLeftDivisionPtCount;                 // 左转弯分界线点数
        public CFG_POLYLINE[]   stLeftDivisionLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 左转弯分界线
        public int              nRightDivisionPtCount;                // 右转弯分界线点数
        public CFG_POLYLINE[]   stRightDivisionLine = (CFG_POLYLINE[])new CFG_POLYLINE().toArray(MAX_POLYLINE_NUM); // 右转弯分界线
        public CFG_ADJUST_LIGHT_COLOR stAdjustLightColor;             // 交通灯颜色校正配置
        public int              nParkingSpaceNum;                     // 车位数
        public CFG_PARKING_SPACE[] stParkingSpaces = (CFG_PARKING_SPACE[])new CFG_PARKING_SPACE().toArray(MAX_PARKING_SPACE_NUM); // 车位配置信息,每个元素代表一个车位
    }

    // 普遍场景
    public static class CFG_NORMAL_SCENE_INFO extends SdkStructure
    {
        public float            fCameraHeight;                        // 摄像头离地高度	单位：米
        public float            fCameraAngle;                         // 摄像头与垂方向的夹角	单位度，0~90，
        public float            fCameraDistance;                      // 摄像头离地面检测区域中心的水平距离	单位：米
        public CFG_POLYGON      stuLandLineStart;                     // 地平线线段起始点(点的坐标坐标归一化到[0,8192)区间。)
        public CFG_POLYGON      stuLandLineEnd;                       // 地平线线段终止点(点的坐标坐标归一化到[0,8192)区间。)
    }

    // 交通巡视场景
    public static class CFG_TRAFFIC_TOUR_SCENE_INFO extends SdkStructure
    {
        public int              nPlateHintNum;                        // 车牌字符暗示个数
        public PLATE_HINT[]     szPlateHintsArr = (PLATE_HINT[])new PLATE_HINT().toArray(MAX_PLATEHINT_NUM); // 车牌字符暗示数组，在拍摄图片质量较差车牌识别不确定时，根据此数组中的字符进行匹配，数组下标越小，匹配优先级越高
    }

    // 统一场景配置,TypeList存在时配置此场景
    public static class CFG_INTELLI_UNIFORM_SCENE extends SdkStructure
    {
        public byte[]           szSubType = new byte[MAX_NAME_LEN];   // 交通场景的子类型,"Gate" 卡口类型,"Junction" 路口类型
        // "Tunnel"隧道类型 , "ParkingSpace"车位检测类型
        // "Bridge"桥梁类型
        // "Freeway"高速公路类型
        public int              nPlateHintNum;                        // 车牌字符暗示个数
        public PLATE_HINT[]     szPlateHints = (PLATE_HINT[])new PLATE_HINT[MAX_PLATEHINT_NUM]; // 车牌字符暗示数组，在拍摄图片质量较差车牌识别不确定时，根据此数组中的字符进行匹配，数组下标越小，匹配优先级越高
        public int              nLaneNum;                             // 车道数
        public CFG_LANE[]       stuLanes = (CFG_LANE[])new CFG_LANE().toArray(MAX_LANE_NUM); // 车道信息
    }

    // CLIENT_MatrixAddCamerasByDevice 接口输入参数
    public static class NET_IN_ADD_LOGIC_BYDEVICE_CAMERA extends SdkStructure
    {
        public int              dwSize;
        public byte[]           pszDeviceID = new byte[NET_DEV_ID_LEN]; // 设备ID
        public NET_REMOTE_DEVICE stuRemoteDevice;                     // 远程设备信息
        public int              nCameraCount;                         // 视频源信息数量
        public Pointer          pCameras;                             // 视频源信息数组,用户分配内存,大小为sizeof(NET_LOGIC_BYDEVICE_ADD_CAMERA_PARAM)*nCameraCount
        // 对应 NET_LOGIC_BYDEVICE_ADD_CAMERA_PARAM[]

        public NET_IN_ADD_LOGIC_BYDEVICE_CAMERA() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_MatrixAddCamerasByDevice 接口输出参数
    public static class NET_OUT_ADD_LOGIC_BYDEVICE_CAMERA extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN]; // 设备ID
        public int              nMaxResultCount;                      // nMaxResultCount, 用户填写
        public int              nRetResultCount;                      // 实际结果数量
        public Pointer          pResults;                             // 添加视频源结果数组,用户分配内存,大小为sizeof(NET_LOGIC_BYDEVICE_ADD_CAMERA_RESULT)*nMaxResultCount
        // 对应  NET_LOGIC_BYDEVICE_ADD_CAMERA_RESULT[]

        public NET_OUT_ADD_LOGIC_BYDEVICE_CAMERA() {
            this.dwSize = this.size();
        }
    }

    // 视频源信息
    public static class NET_LOGIC_BYDEVICE_ADD_CAMERA_PARAM extends SdkStructure
    {
        public int              dwSize;
        public int              nUniqueChannel;                       // 统一编号
        public int              nChannel;                             // 通道号

        public NET_LOGIC_BYDEVICE_ADD_CAMERA_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 添加视频源结果信息
    public static class NET_LOGIC_BYDEVICE_ADD_CAMERA_RESULT extends SdkStructure
    {
        public int              dwSize;
        public int              nUniqueChannel;                       // 统一编号
        public int              nFailedCode;                          // 失败码, 0-成功,1-通道不支持设置

        public NET_LOGIC_BYDEVICE_ADD_CAMERA_RESULT() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 EVENT_IVS_FACEDETECT (目标检测事件)对应的规则配置
    public static class CFG_FACEDETECT_INFO extends SdkStructure
    {
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public byte             bRuleEnable;                          // 规则使能, 1-true  0-false
        public byte[]           bReserved = new byte[3];              // 保留字段
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区
        public int              nHumanFaceTypeCount;                  // 触发事件的人脸类型个数
        public byte[]           szHumanFaceType = new byte[MAX_HUMANFACE_LIST_SIZE*MAX_NAME_LEN]; // 触发事件的人脸类型
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public int              nMinDuration;                         // 最短触发时间,单位：秒
        public int              nSensitivity;                         // 灵敏度,范围[1,10],灵敏度越高越容易检测
        public int              nReportInterval;                      // 重复报警间隔,单位:秒,[0,600](等于0表示不重复报警)
        public int              bSizeFileter;                         // 规则特定的尺寸过滤器是否有效
        public CFG_SIZEFILTER_INFO stuSizeFileter;                    // 规则特定的尺寸过滤器, 1-true  0-false
        public int              bFeatureEnable;                       // 是否开启人脸属性识别, IPC增加, 1-true 0-false
        // (通过FaceDetection能力中的FeatureSupport来确定该配置是否可以设置)
        public int              nFaceFeatureNum;                      // 需要检测的人脸属性个数
        public int[]            emFaceFeatureType = new int[MAX_FEATURE_LIST_SIZE]; // 需检测的人脸属性, 通过FaceDetection能力来获取支持哪些人脸属性, 参考  EM_FACEFEATURE_TYPE
        public CFG_FACE_BEAUTIFICATION stuFaceBeautification = new CFG_FACE_BEAUTIFICATION(); //人Lian美化,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_FACE_BEAUTIFICATION}
        public byte[]           szReserved = new byte[1024];          //保留字段
    }

    public static class MAX_OBJECT_LIST extends SdkStructure
    {
        public byte[]           szObjectTypes = new byte[MAX_NAME_LEN]; // 相应物体类型列表
    }

    public static class MAX_HUMANFACE_LIST extends SdkStructure
    {
        public byte[]           szHumanFaceType = new byte[MAX_NAME_LEN]; // 触发事件的人脸类型
    }

    public static class TIME_SECTION_WEEK_DAY_10 extends SdkStructure
    {
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(MAX_REC_TSECT_EX); // 事件响应时间段

             public TIME_SECTION_WEEK_DAY_10(){
            for(int i=0;i<stuTimeSection.length;i++){
                stuTimeSection[i]=new CFG_TIME_SECTION();
            }
        }
    }

    public static class TIME_SECTION_WEEK_DAY_6 extends SdkStructure
    {
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(MAX_REC_TSECT); // 事件响应时间段, 每天最多6个时间段

        public TIME_SECTION_WEEK_DAY_6(){
            for(int i=0;i<stuTimeSection.length;i++){
                stuTimeSection[i]=new CFG_TIME_SECTION();
            }

        }
    }

    public static class TIME_SECTION_WEEK_DAY_4 extends SdkStructure
    {
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(MAX_DOOR_TIME_SECTION); // 事件响应时间段, 每天最多4个时间段

        public TIME_SECTION_WEEK_DAY_4(){
            for(int i=0;i<stuTimeSection.length;i++){
                stuTimeSection[i]=new CFG_TIME_SECTION();
            }
        }
    }

    public static class TIME_SECTION_WEEK_DAY_2 extends SdkStructure
    {
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(MAX_NAS_TIME_SECTION); // 事件响应时间段, 每天最多4个时间段

                public TIME_SECTION_WEEK_DAY_2(){
                    for(int i=0;i<stuTimeSection.length;i++){
                        stuTimeSection[i]=new CFG_TIME_SECTION();
                    }
                }
    }

    // 设备ID
    public static class DEVICE_ID extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
    }

    // 设备通知类型
    public static class EM_DEVICE_NOTIFY_TYPE extends SdkStructure
    {
        public static final int   EM_DEVICE_NOTIFY_TYPE_UNKNOWN = 0;    // 未知
        public static final int   EM_DEVICE_NOTIFY_TYPE_NEW = 1;        // 新设备添加
        public static final int   EM_DEVICE_NOTIFY_TYPE_UPDATE = 2;     // 设备信息更新
        public static final int   EM_DEVICE_NOTIFY_TYPE_REMOVE = 3;     // 设备删除
        public static final int   EM_DEVICE_NOTIFY_TYPE_CONNECT = 4;    // 设备有通道上下线
    }

    // 向设备注册的回调返回信息
    public static class NET_CB_ATTACH_DEVICE_STATE extends SdkStructure
    {
        public int              emNotifyType;                         // 通知类型, 详见 EM_DEVICE_NOTIFY_TYPE
        public int              nRetCount;                            // 设备个数
        public DEVICE_ID[]      szDeviceIDsArr = (DEVICE_ID[])new DEVICE_ID().toArray(MAX_LINK_DEVICE_NUM); // 设备列表
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // CLIENT_AttachDeviceState 注册设备状态回调入参结构
    public static class NET_IN_ATTACH_DEVICE_STATE extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbDeviceState;                        //回调函数
        public Pointer          dwUser;                               //用户数据

        public NET_IN_ATTACH_DEVICE_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachDeviceState 注册设备状态回调出参结构
    public static class NET_OUT_ATTACH_DEVICE_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTACH_DEVICE_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AsyncAddDevice 添加设备入参结构
    public static class NET_IN_ASYNC_ADD_DEVICE extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // URL个数
        public DEVICE_URL[]     szUrlsArr = (DEVICE_URL[])new DEVICE_URL().toArray(MAX_ADD_DEVICE_NUM); // URL组

        public NET_IN_ASYNC_ADD_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // 设备URL
    public static class DEVICE_URL extends SdkStructure {
        public byte[]           szUrl = new byte[MAX_COMMON_STRING_512]; // URL
    }

    // CLIENT_AsyncAddDevice 添加设备出参结构
    public static class NET_OUT_ASYNC_ADD_DEVICE extends SdkStructure
    {
        public int              dwSize;
        public int              nTaskID;                              //任务ID

        public NET_OUT_ASYNC_ADD_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // 设备的添加状态
    public static class EM_DEVICE_ADD_STATE extends SdkStructure
    {
        public static final int   EM_DEVICE_ADD_STATE_UNKNOWN = 0;      // 未知
        public static final int   EM_DEVICE_ADD_STATE_WAIT = 1;         // 等待添加
        public static final int   EM_DEVICE_ADD_STATE_CONNECT = 2;      // 连接中
        public static final int   EM_DEVICE_ADD_STATE_FAILURE = 3;      // 添加失败
        public static final int   EM_DEVICE_ADD_STATE_SUCCESS = 4;      // 添加成功
        public static final int   EM_DEVICE_ADD_STATE_STOP = 5;         // 停止添加
    }

    // 注册添加设备的回调返回信息
    public static class NET_CB_ATTACH_ADD_DEVICE extends SdkStructure
    {
        public int              nTaskID;                              // 任务ID
        public int              emAddState;                           // 添加设备结果
        public int              nIndex;                               // 任务中设备序号
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // CLIENT_AttachAddDevice 注册添加设备回调入参结构
    public static class NET_IN_ATTACH_ADD_DEVICE extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbAddDevice;                          //回调函数
        public Pointer          dwUser;                               //用户数据

        public NET_IN_ATTACH_ADD_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachAddDevice 注册添加设备回调出参结构
    public static class NET_OUT_ATTACH_ADD_DEVICE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTACH_ADD_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetAddDeviceInfo 获取添加中的设备状态入参结构
    public static class NET_IN_GET_ADD_DEVICE_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nTaskID;                              // 任务ID
        public int              nCount;                               // 设备个数
        public int[]            nIndex = new int[MAX_ADD_DEVICE_NUM]; // 设备序号列表(NET_IN_ADD_DEVICE中szUrls的序号，从0开始)

        public NET_IN_GET_ADD_DEVICE_LIST_INFO() {
            this.dwSize = this.size();
        }
    }

    // 添加中设备的结果信息
    public static class NET_GET_ADD_DEVICE_INFO extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
        public byte[]           szUrl = new byte[NET_COMMON_STRING_512]; // url
        public int              emAddState;                           // 当前添加状态，详见 EM_DEVICE_ADD_STATE
        public int              nErrorCode;                           // 错误码
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // CLIENT_GetAddDeviceInfo 获取添加中的设备状态出参结构
    public static class NET_OUT_GET_ADD_DEVICE_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nRetCount;                            // 设备个数
        public NET_GET_ADD_DEVICE_INFO[] stuDeviceInfo = (NET_GET_ADD_DEVICE_INFO[])new NET_GET_ADD_DEVICE_INFO().toArray(MAX_ADD_DEVICE_NUM); // 设备信息列表

        public NET_OUT_GET_ADD_DEVICE_LIST_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetDeviceInfo 获取已添加的设备状态入参结构
    public static class NET_IN_GET_DEVICE_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // 设备个数
        public DEVICE_ID[]      szDeviceIDsArr = (DEVICE_ID[])new DEVICE_ID().toArray(MAX_LINK_DEVICE_NUM); // 设备列表

        public NET_IN_GET_DEVICE_LIST_INFO() {
            this.dwSize = this.size();
        }
    }

    // 已添加设备的结果信息
    public static class NET_GET_DEVICE_INFO extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
        public byte[]           szUrl = new byte[NET_COMMON_STRING_512]; // url
        public byte[]           szSerialNo = new byte[NET_COMMON_STRING_32]; // 设备序列号
        public byte[]           szDeviceType = new byte[NET_COMMON_STRING_64]; // 设备类型
        public byte[]           szDeviceClass = new byte[NET_DEV_CLASS_LEN]; // 设备大类
        public int              nMacCount;                            // 设备mac个数
        public DEVICE_MAC[]     szMacsArr = (DEVICE_MAC[])new DEVICE_MAC().toArray(MAX_MACADDR_NUM); // 设备mac地址组
        public byte[]           szDevSoftVersion = new byte[NET_COMMON_STRING_128]; // 设备软件版本号
        public byte[]           szDeviceName = new byte[NET_DEV_NAME_LEN]; // 设备名称
        public byte[]           szDetail = new byte[NET_COMMON_STRING_512]; // 设备详细信息
        public int              nVideoInputCh;                        // 视频输入通道数
        public int              nVideoOutputCh;                       // 视频输出通道数
        public int              nAudioInputCh;                        // 音频输入通道数
        public int              nAudioOutputCh;                       // 音频输出通道数
        public int              nAlarmInputCh;                        // 报警输入通道数
        public int              nAlarmOutputCh;                       // 报警输出通道数
        public int              nErrorCode;                           // 设备离线错误码
        public int              nVtoDoors;                            // 门禁设备可控制的门的总数
        public byte             byOnline;                             // 设备是否在线 0:离线 1：在线
        public byte[]           byReserved = new byte[511];           // 保留字节
    }

    // 设备mac地址
    public static class DEVICE_MAC extends SdkStructure {
        public byte[]           szMac = new byte[NET_MACADDR_LEN];    // 设备mac地址
    }

    // CLIENT_GetDeviceInfo 获取已添加的设备状态出参结构
    public static class NET_OUT_GET_DEVICE_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxCount;                            // 用户申请的设备个数
        public int              nRetCount;                            // 实际返回的设备个数
        public Pointer          pstuDeviceInfo;                       // 设备信息列表 用户分配内存,大小为sizeof(NET_GET_DEVICE_INFO)*nMaxCount, 对应 NET_GET_DEVICE_INFO[]

        public NET_OUT_GET_DEVICE_LIST_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SetConnectChannel 设置连接通道入参结构
    public static class NET_IN_SET_CONNECT_CHANNEL extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID
        public int              nCount;                               // 通道个数
        public int[]            nChannels = new int[MAX_DEVICE_CHANNEL_NUM]; // 通道列表

        public NET_IN_SET_CONNECT_CHANNEL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SetConnectChannel 设置连接通道出参结构
    public static class NET_OUT_SET_CONNECT_CHANNEL extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_CONNECT_CHANNEL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetChannelInfo 获取设备通道信息入参结构
    public static class NET_IN_GET_CHANNEL_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_DEV_ID_LEN_EX]; // 设备ID

        public NET_IN_GET_CHANNEL_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取设备通道信息结果信息
    public static class NET_GET_CHANNEL_INFO extends SdkStructure
    {
        public int              nRemoteChannel;                       // 远程通道号
        public int              nLogicChannel;                        // 分配的逻辑通道
        public byte[]           szName = new byte[NET_DEV_NAME_LEN];  // 通道名称
        public byte[]           szDetail = new byte[NET_COMMON_STRING_512]; // 设备详细信息
        public byte[]           szDeviceType = new byte[NET_COMMON_STRING_64]; // 设备类型
        public byte[]           szDeviceClass = new byte[NET_DEV_CLASS_LEN]; // 设备大类
        public byte[]           szIP = new byte[NET_MAX_IPADDR_LEN];  // ip地址
        public byte[]           szMac = new byte[NET_MACADDR_LEN];    // 设备mac地址
        public byte[]           szSerialNo = new byte[NET_DEV_SERIALNO_LEN]; // 设备序列号
        public byte[]           szDevSoftVersion = new byte[NET_COMMON_STRING_128]; // 设备软件版本号
        public int              nVideoInputCh;                        // 视频输入通道数
        public int              nVideoOutputCh;                       // 视频输出通道数
        public int              nAudioInputCh;                        // 音频输入通道数
        public int              nAudioOutputCh;                       // 音频输出通道数
        public int              nAlarmInputCh;                        // 报警输入通道数
        public int              nAlarmOutputCh;                       // 报警输出通道数
        public byte             byOnline;                             // 通道是否在线0:离线 1：在线
        public byte             byUsed;                               // 该通道是否被本地设备使用 0：未使用 1：使用
        public byte[]           byReserved = new byte[510];           // 保留字节
    }

    // CLIENT_GetChannelInfo 获取设备通道信息出参结构
    public static class NET_OUT_GET_CHANNEL_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxCount;                            // 用户申请的通道个数
        public int              nRetCount;                            // 实际返回的通道个数
        public Pointer          pstuChannelInfo;                      // 通道信息列表 用户分配内存,大小为sizeof(NET_GET_CHANNEL_INFO)*nMaxCount, 对应 NET_GET_CHANNEL_INFO[]

        public NET_OUT_GET_CHANNEL_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_RemoveDevice 删除设备入参结构
    public static class NET_IN_REMOVE_DEVICE extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // 设备个数
        public DEVICE_ID[]      szDeviceIDsArr = (DEVICE_ID[])new DEVICE_ID().toArray(MAX_LINK_DEVICE_NUM); // 设备列表

        public NET_IN_REMOVE_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_RemoveDevice 删除设备出参结构
    public static class NET_OUT_REMOVE_DEVICE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_REMOVE_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_CancelAddDeviceTask 接口输入参数
    public static class NET_IN_CANCEL_ADD_TASK extends SdkStructure
    {
        public int              dwSize;
        public int              nTaskID;                              // 任务ID

        public NET_IN_CANCEL_ADD_TASK() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_CancelAddDeviceTask 接口输出参数
    public static class NET_OUT_CANCEL_ADD_TASK extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_CANCEL_ADD_TASK() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ConfirmAddDeviceTask 接口输入参数
    public static class NET_IN_CONFIRM_ADD_TASK extends SdkStructure
    {
        public int              dwSize;
        public int              nTaskID;                              // 任务ID

        public NET_IN_CONFIRM_ADD_TASK() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ConfirmAddDeviceTask 接口输出参数
    public static class NET_OUT_CONFIRM_ADD_TASK extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_CONFIRM_ADD_TASK() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SCADAAlarmAttachInfo()接口输入参数
    public static class NET_IN_SCADA_ALARM_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbCallBack;                           // 数据回调函数,对应回调 fSCADAAlarmAttachInfoCallBack
        public Pointer          dwUser;                               // 用户定义参数

        public NET_IN_SCADA_ALARM_ATTACH_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SCADAAlarmAttachInfo()接口输出参数
    public static class NET_OUT_SCADA_ALARM_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SCADA_ALARM_ATTACH_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SyncParkingInfo 接口输入参数
    public static class NET_IN_SYNC_PARKING_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号
        public byte[]           szParkingNum = new byte[32];          // 车位编号
        public int              dwPresetNum;                          // 预置点编号
        public int              bHaveCar;                             // 车位是否有车
        public int              bParkingFault;                        // 车位是否有故障
        public int              nSnapTimes;                           // 补拍次数（取值范围：0-5）
        public int              nSnapIntervel;                        // 补拍间隔（取值范围：3-10）

        public NET_IN_SYNC_PARKING_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SyncParkingInfo 接口输出参数
    public static class NET_OUT_SYNC_PARKING_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SYNC_PARKING_INFO() {
            this.dwSize = this.size();
        }
    }

    // 监测点位报警信息列表
    public static class NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nList;                                // 监测点位报警信息个数
        public NET_SCADA_NOTIFY_POINT_ALARM_INFO[] stuList = (NET_SCADA_NOTIFY_POINT_ALARM_INFO[])new NET_SCADA_NOTIFY_POINT_ALARM_INFO().toArray(MAX_SCADA_POINT_LIST_ALARM_INFO_NUM); // 监测点位报警信息
        public NET_SCADA_NOTIFY_POINT_ALARM_INFO_EX[] stuListEx = (NET_SCADA_NOTIFY_POINT_ALARM_INFO_EX[])new NET_SCADA_NOTIFY_POINT_ALARM_INFO_EX().toArray(256); // 监测点位报警信息

        public NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST() {
            this.dwSize = this.size();
        }
    }

    // 监测点位报警信息
    public static class NET_SCADA_NOTIFY_POINT_ALARM_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevID = new byte[NET_COMMON_STRING_16]; // 设备ID
        public byte[]           szPointID = new byte[NET_COMMON_STRING_128]; // 点位ID
        public int              bAlarmFlag;                           // 报警标志, 1-true  0-false
        public NET_TIME         stuAlarmTime;                         // 报警时间
        public int              nAlarmLevel;                          // 报警级别（0~6）
        public int              nSerialNo;                            // 报警编号,同一个告警的开始和结束的编号是相同的。
        public byte[]           szAlarmDesc = new byte[NET_COMMON_STRING_128]; // 报警描述
        public byte[]           szSignalName = new byte[64];          // 点位名称

        public NET_SCADA_NOTIFY_POINT_ALARM_INFO() {
            this.dwSize = this.size();
        }
    }

    // 信息采集(对应 CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT 命令)
    public static class NET_CTRL_CAPTURE_FINGER_PRINT extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 门禁序号(从开始)
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 读卡器ID
        public byte[]           szUserID = new byte[12];              // 用户ID（智能楼宇需求，可选）

        public NET_CTRL_CAPTURE_FINGER_PRINT() {
            this.dwSize = this.size();
        }
    }

    // 门禁状态事件
    public static class ALARM_ACCESS_CTL_STATUS_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public NET_TIME         stuTime;                              // 事件发生的时间
        public int              emStatus;                             // 门禁状态, 对应   NET_ACCESS_CTL_STATUS_TYPE
        public byte[]           szSerialNumber = new byte[256];       //无线设备序列号(智能锁)
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用
        // stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）

        public ALARM_ACCESS_CTL_STATUS_INFO() {
            this.dwSize = this.size();
        }
    }

    //事件类型 EVENT_IVS_SNAPMANUAL(SnapManual事件)对应数据块描述信息
    public static class DEV_EVENT_SNAPMANUAL extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见 NET_RESERVED_COMMON
        public byte[]           bReserved = new byte[1024];           // 保留字节,留待扩展.
    }

    // 即时抓图(又名手动抓图)入参, 对应命令 CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT
    public static class NET_IN_SNAP_MNG_SHOT extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public int              nChannel;                             // 通道号
        public int              nTime;                                // 连拍次数, 0表示停止抓拍,正数表示连续抓拍的张数

        public NET_IN_SNAP_MNG_SHOT() {
            this.dwSize = this.size();
        }
    }

    // 即时抓图(又名手动抓图)出参, 对应命令 CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT
    public static class NET_OUT_SNAP_MNG_SHOT extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小

        public NET_OUT_SNAP_MNG_SHOT() {
            this.dwSize = this.size();
        }
    }

    // 获取摄像机状态, CLIENT_QueryDevInfo 接口 NET_QUERY_GET_CAMERA_STATE 命令入参
    public static class NET_IN_GET_CAMERA_STATEINFO extends SdkStructure
    {
        public int              dwSize;
        public int              bGetAllFlag;                          // 是否查询所有摄像机状态,若该成员为 TRUE,则 nChannels 成员无需设置, 1-true; 0-false
        public int              nValidNum;                            // 该成员,bGetAllFlag 为 FALSE时有效,表示 nChannels 成员有效个数
        public int[]            nChannels = new int[NET_MAX_CAMERA_CHANNEL_NUM]; // 该成员,bGetAllFlag 为 FALSE时有效,将需要查询的通道号依次填入

        public NET_IN_GET_CAMERA_STATEINFO() {
            this.dwSize = this.size();
        }
    }

    // 获取摄像机状态, CLIENT_QueryDevInfo 接口 NET_QUERY_GET_CAMERA_STATE 命令出参
    public static class NET_OUT_GET_CAMERA_STATEINFO extends SdkStructure
    {
        public int              dwSize;
        public int              nValidNum;                            // 查询到的摄像机通道状态有效个数,由sdk返回
        public int              nMaxNum;                              // pCameraStateInfo 数组最大个数,由用户填写
        public Pointer          pCameraStateInfo;                     // 摄像机通道信息数组,由用户分配, 对应  NET_CAMERA_STATE_INFO[]

        public NET_OUT_GET_CAMERA_STATEINFO() {
            this.dwSize = this.size();
        }
    }

    public static class NET_CAMERA_STATE_INFO extends SdkStructure
    {
        public int              nChannel;                             // 摄像机通道号, -1表示通道号无效
        public int              emConnectionState;                    // 连接状态, 参考  EM_CAMERA_STATE_TYPE
        public byte[]           szReserved = new byte[1024];          // 保留字节
    }

    public static class EM_CAMERA_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_CAMERA_STATE_TYPE_UNKNOWN = 0;     // 未知
        public static final int   EM_CAMERA_STATE_TYPE_CONNECTING = 1;  // 正在连接
        public static final int   EM_CAMERA_STATE_TYPE_CONNECTED = 2;   // 已连接
        public static final int   EM_CAMERA_STATE_TYPE_UNCONNECT = 3;   // 未连接
        public static final int   EM_CAMERA_STATE_TYPE_EMPTY = 4;       // 通道未配置,无信息
        public static final int   EM_CAMERA_STATE_TYPE_DISABLE = 5;     // 通道有配置,但被禁用
    }

    // CLIENT_StartFindFaceInfo 输入参数
    public static class NET_IN_FACEINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID

        public NET_IN_FACEINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFindFaceInfo 输出参数
    public static class NET_OUT_FACEINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              nTotalCount;                          // 符合查询条件的总数

        public NET_OUT_FACEINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindFaceInfo 输入参数
    public static class NET_IN_FACEINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              nStartNo;                             // 起始序号
        public int              nCount;                               // 本次查询的条数

        public NET_IN_FACEINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindFaceInfo 输出参数
    public static class NET_OUT_FACEINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              nRetNum;                              // 本次查询到的个数
        public Pointer          pstuInfo;                             // 查询结果, 用户分配内存,大小为sizeof(NET_FACEINFO)*nMaxNum, 对应 NET_FACEINFO[]
        public int              nMaxNum;                              // 用户分配内存的个数
        public byte[]           byReserved = new byte[4];

        public NET_OUT_FACEINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // 人脸信息
    public static class NET_FACEINFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public int              nMD5;                                 // 有效的MD5编码数量
        public MD5[]            szMD5Arr = (MD5[])new MD5().toArray(5); // 图片对应的32字节MD5编码加密
        public byte[]           byReserved = new byte[512];
    }

    // 图片对应的32字节MD5编码加密
    public static class MD5 extends SdkStructure
    {
        public byte[]           szMD5 = new byte[NET_COMMON_STRING_64];
    }

    // 初始化设备账户输入结构体
    public static class NET_IN_INIT_DEVICE_ACCOUNT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小:初始化结构体时赋值
        public byte[]           szMac = new byte[NET_MACADDR_LEN];    // 设备mac地址
        public byte[]           szUserName = new byte[MAX_USER_NAME_LEN]; // 用户名
        public byte[]           szPwd = new byte[MAX_PWD_LEN];        // 设备密码
        public byte[]           szCellPhone = new byte[MAX_CELL_PHONE_NUMBER_LEN]; // 预留手机号
        public byte[]           szMail = new byte[MAX_MAIL_LEN];      // 预留邮箱
        public byte             byInitStatus;                         // 此字段已经废弃
        public byte             byPwdResetWay;                        // 设备支持的密码重置方式：搜索设备接口(CLIENT_SearchDevices、CLIENT_StartSearchDevices的回调函数、CLIENT_SearchDevicesByIPs)返回字段byPwdResetWay的值
        // 该值的具体含义见 DEVICE_NET_INFO_EX 结构体，需要与设备搜索接口返回的 byPwdResetWay 值保持一致
        // bit0 : 1-支持预留手机号，此时需要在szCellPhone数组中填入预留手机号(如果需要设置预留手机) ;
        // bit1 : 1-支持预留邮箱，此时需要在szMail数组中填入预留邮箱(如果需要设置预留邮箱)
        public byte[]           byReserved = new byte[2];             // 保留字段
        public int              emAccountProtocol;                    //初始化协议类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_ACCOUNT_PROTOCOL}

        public NET_IN_INIT_DEVICE_ACCOUNT() {
            this.dwSize = this.size();
        }
    }

    // 初始化设备账户输出结构体
    public static class NET_OUT_INIT_DEVICE_ACCOUNT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小:初始化结构体时赋值

        public NET_OUT_INIT_DEVICE_ACCOUNT() {
            this.dwSize = this.size();
        }
    }

    //用户权限
    public static class NET_ATTENDANCE_AUTHORITY extends SdkStructure
    {
        public static final int   NET_ATTENDANCE_AUTHORITY_UNKNOWN = -1;
        public static final int   NET_ATTENDANCE_AUTHORITY_CUSTOMER = 0; //普通用户
        public static final int   NET_ATTENDANCE_AUTHORITY_ADMINISTRATORS = 1; //管理员
    }

    //考勤用户信息
    public static class NET_ATTENDANCE_USERINFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; //用户编号ID
        public byte[]           szUserName = new byte[MAX_ATTENDANCE_USERNAME_LEN]; //人员姓名
        public byte[]           szCardNo = new byte[MAX_COMMON_STRING_32]; // 卡号
        public int              emAuthority;                          // 用户权限
        public byte[]           szPassword = new byte[MAX_COMMON_STRING_32]; // 密码
        public int              nPhotoLength;                         // 照片数据长度
        public byte[]           szClassNumber = new byte[MAX_CLASS_NUMBER_LEN]; // 班级
        public byte[]           szPhoneNumber = new byte[MAX_PHONENUMBER_LEN]; // 电话
        public byte[]           byReserved = new byte[208];           // 保留字节
    }

    // CLIENT_Attendance_AddUser 入参
    public static class NET_IN_ATTENDANCE_ADDUSER extends SdkStructure
    {
        public int              dwSize;
        public NET_ATTENDANCE_USERINFO stuUserInfo = new NET_ATTENDANCE_USERINFO(); // 用户信息
        public Pointer          pbyPhotoData;                         // 照片数据

        public NET_IN_ATTENDANCE_ADDUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_AddUser 出参
    public static class NET_OUT_ATTENDANCE_ADDUSER extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTENDANCE_ADDUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_DelUser 入参
    public static class NET_IN_ATTENDANCE_DELUSER extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; // 用户ID

        public NET_IN_ATTENDANCE_DELUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_DelUser 出参
    public static class NET_OUT_ATTENDANCE_DELUSER extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTENDANCE_DELUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_ModifyUser 入参
    public static class NET_IN_ATTENDANCE_ModifyUSER extends SdkStructure
    {
        public int              dwSize;
        public NET_ATTENDANCE_USERINFO stuUserInfo = new NET_ATTENDANCE_USERINFO(); // 用户信息
        public Pointer          pbyPhotoData;                         // 照片数据

        public NET_IN_ATTENDANCE_ModifyUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_ModifyUser 出参
    public static class NET_OUT_ATTENDANCE_ModifyUSER extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ATTENDANCE_ModifyUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_GetUser 入参
    public static class NET_IN_ATTENDANCE_GetUSER extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; // 用户ID

        public NET_IN_ATTENDANCE_GetUSER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_Attendance_GetUser 出参
    public static class NET_OUT_ATTENDANCE_GetUSER extends SdkStructure
    {
        public int              dwSize;
        public NET_ATTENDANCE_USERINFO stuUserInfo = new NET_ATTENDANCE_USERINFO(); // 用户信息
        public int              nMaxLength;                           // 最大存放照片数据的长度
        public Pointer          pbyPhotoData;                         // 照片数据

        public NET_OUT_ATTENDANCE_GetUSER() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_InsertFingerByUserID 入参
    public static class NET_IN_FINGERPRINT_INSERT_BY_USERID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_COMMON_STRING_32]; // 用户ID
        public int              nSinglePacketLen;                     // 单个信息数据包长度
        public int              nPacketCount;                         // 信息数据包的个数
        public Pointer          szFingerPrintInfo;                    // 信息数据(数据总长度即nSinglePacketLen*nPacketCount)

        public NET_IN_FINGERPRINT_INSERT_BY_USERID() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_InsertFingerByUserID 出参
    public static class NET_OUT_FINGERPRINT_INSERT_BY_USERID extends SdkStructure
    {
        public int              dwSize;
        public int[]            nFingerPrintID = new int[NET_MAX_FINGER_PRINT]; //信息ID数组
        public int              nReturnedCount;                       //数组中实际返回的个数
        public int              nFailedCode;                          //错误码  0：成功;   1：其他错误;  2：超过本用户下信息能力的限制.

        public NET_OUT_FINGERPRINT_INSERT_BY_USERID() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_RemoveFingerByUserID 入参(removeByUserID)
    public static class NET_CTRL_IN_FINGERPRINT_REMOVE_BY_USERID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_COMMON_STRING_32]; // 用户ID

        public NET_CTRL_IN_FINGERPRINT_REMOVE_BY_USERID() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_RemoveFingerByUserID 出参
    public static class NET_CTRL_OUT_FINGERPRINT_REMOVE_BY_USERID extends SdkStructure
    {
        public int              dwSize;

        public NET_CTRL_OUT_FINGERPRINT_REMOVE_BY_USERID() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_RemoveFingerRecord 入参(remove)
    public static class NET_CTRL_IN_FINGERPRINT_REMOVE extends SdkStructure
    {
        public int              dwSize;
        public int              nFingerPrintID;                       // 信息编号

        public NET_CTRL_IN_FINGERPRINT_REMOVE() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_RemoveFingerRecord 出参
    public static class NET_CTRL_OUT_FINGERPRINT_REMOVE extends SdkStructure
    {
        public int              dwSize;

        public NET_CTRL_OUT_FINGERPRINT_REMOVE() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_GetFingerRecord 入参
    public static class NET_CTRL_IN_FINGERPRINT_GET extends SdkStructure
    {
        public int              dwSize;
        public int              nFingerPrintID;                       // 信息编号

        public NET_CTRL_IN_FINGERPRINT_GET() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_GetFingerRecord 出参
    public static class NET_CTRL_OUT_FINGERPRINT_GET extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_COMMON_STRING_32]; // 所属用户的用户ID
        public byte[]           szFingerPrintName = new byte[NET_COMMON_STRING_32]; // 信息名称
        public int              nFingerPrintID;                       // 信息ID
        public int              nRetLength;                           // 实际返回的二进制信息数据长度
        public int              nMaxFingerDataLength;                 // 二进制信息数据的最大长度
        public Pointer          szFingerPrintInfo;                    // 信息数据

        public NET_CTRL_OUT_FINGERPRINT_GET() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_FindUser 入参
    public static class NET_IN_ATTENDANCE_FINDUSER extends SdkStructure
    {
        public int              dwSize;
        public int              nOffset;                              // 查询偏移
        public int              nPagedQueryCount;                     // 查询个数，分页查询，最多不超过100

        public NET_IN_ATTENDANCE_FINDUSER() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_FindUser 出参
    public static class NET_OUT_ATTENDANCE_FINDUSER extends SdkStructure
    {
        public int              dwSize;
        public int              nTotalUser;                           // 总的用户数
        public int              nMaxUserCount;                        // 用户信息最大缓存数
        public Pointer          stuUserInfo;                          // 用户信息，内存由用户申请，大小为(sizeof(NET_ATTENDANCE_USERINFO)*nMaxUserCount)
        public int              nRetUserCount;                        // 实际返回的用户个数
        public int              nMaxPhotoDataLength;                  // 照片数据最大长度
        public int              nRetPhoteLength;                      // 实际返回的照片数据长度
        public Pointer          pbyPhotoData;                         // 照片数据

        public NET_OUT_ATTENDANCE_FINDUSER() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_GetFingerByUserID 入参
    public static class NET_IN_FINGERPRINT_GETBYUSER extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_COMMON_STRING_32]; // 用户ID

        public NET_IN_FINGERPRINT_GETBYUSER() {
            this.dwSize = this.size();
        }
    }

    //CLIENT_Attendance_GetFingerByUserID 出参
    public static class NET_OUT_FINGERPRINT_GETBYUSER extends SdkStructure
    {
        public int              dwSize;
        public int[]            nFingerPrintIDs = new int[NET_MAX_FINGER_PRINT]; // 信息ID数组
        public int              nRetFingerPrintCount;                 // 实际返回的信息ID个数，即nFingerPrintIDs数组中实际有效个数
        public int              nSinglePacketLength;                  // 单个信息数据包长度
        public int              nMaxFingerDataLength;                 // 接受信息数据的缓存的最大长度
        public int              nRetFingerDataLength;                 // 实际返回的总的信息数据包的长度
        public Pointer          pbyFingerData;                        // 信息数据

        public NET_OUT_FINGERPRINT_GETBYUSER() {
            this.dwSize = this.size();
        }
    }

    // 获取考勤机在线状态入参
    public static class NET_IN_ATTENDANCE_GETDEVSTATE extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_ATTENDANCE_GETDEVSTATE() {
            this.dwSize = this.size();
        }
    }

    // 获取考勤机在线状态出参
    public static class NET_OUT_ATTENDANCE_GETDEVSTATE extends SdkStructure
    {
        public int              dwSize;
        public int              nState;                               // 0:离线;1:在线;其他:未知;考勤机状态

        public NET_OUT_ATTENDANCE_GETDEVSTATE() {
            this.dwSize = this.size();
        }
    }

    // 输入通道基本配置
    public static class CFG_VIDEO_IN_INFO extends SdkStructure
    {
        public byte[]           szDevType = new byte[MAX_NAME_LEN];   // 通道类型（指通道连接的设备类型）
        public byte[]           szDevID = new byte[MAX_NAME_LEN];     // 摄像头唯一编号
        public byte[]           szChnName = new byte[MAX_NAME_LEN];   // 通道名称
        public byte[]           szManufacturer = new byte[MAX_NAME_LEN]; // 厂商
        public byte[]           szModel = new byte[MAX_NAME_LEN];     // 设备型号
        public byte[]           szAddress = new byte[MAX_ADDRESS_LEN]; // 安装地址
        public byte[]           szCivilCode = new byte[MAX_NAME_LEN]; // 行政区域
        public byte[]           szOwner = new byte[MAX_NAME_LEN];     // 设备归属
        public int              bParental;                            // 是否有子设备
        public int              bEnable;                              // 通道使能
        public int              nRegisterWay;                         // 注册方式
        // 0-符合sip3261标准的认证注册模式
        // 1-基于口令的双向认证注册模式
        // 2-基于数字证书的双向认证注册模式
        public int              bSecrecy;                             // 属性, FALSE不涉密, TRUE涉密
        public byte[]           szUpperDevID = new byte[MAX_NAME_LEN]; // 上级连接设备设备ID
        public int              nUpperDevOutChn;                      // 上级连接设备输出通道号
        public byte[]           szRemoteName = new byte[MAX_NAME_LEN]; // 远程通道名称
        public int              emSignalType;                         // 输入信号类型 , 详见EM_CFG_VIDEO_SIGNAL_TYPE
        public int              emLineType;                           // 通道接入线缆的类型, 详见EM_CFG_VIDEO_LINE_TYPE
    }

    // 输入信号类型
    public static class EM_CFG_VIDEO_SIGNAL_TYPE extends SdkStructure
    {
        public static final int   EM_CFG_VIDEO_SIGNAL_UNKNOWN = 0;
        public static final int   EM_CFG_VIDEO_SIGNAL_CVBS = 1;
        public static final int   EM_CFG_VIDEO_SIGNAL_SDI = 2;
        public static final int   EM_CFG_VIDEO_SIGNAL_VGA = 3;
        public static final int   EM_CFG_VIDEO_SIGNAL_DVI = 4;
        public static final int   EM_CFG_VIDEO_SIGNAL_HDMI = 5;
        public static final int   EM_CFG_VIDEO_SIGNAL_YPBPR = 6;
        public static final int   EM_CFG_VIDEO_SIGNAL_SFP = 7;
        public static final int   EM_CFG_VIDEO_SIGNAL_HDCVI = 8;
        public static final int   EM_CFG_VIDEO_SIGNAL_DUALLINK = 9;
        public static final int   EM_CFG_VIDEO_SIGNAL_AHD = 10;
        public static final int   EM_CFG_VIDEO_SIGNAL_AUTO = 11;
        public static final int   EM_CFG_VIDEO_SIGNAL_TVI = 12;
    }

    // 通道接入线缆的类型
    public static class EM_CFG_VIDEO_LINE_TYPE extends SdkStructure
    {
        public static final int   EM_CFG_VIDEO_LINE_TYPE_UNKNOWN = 0;   // 未知
        public static final int   EM_CFG_VIDEO_LINE_TYPE_COAXIAL = 1;   // 同轴线
        public static final int   EM_CFG_VIDEO_LINE_TYPE_TP10 = 2;      // 10欧姆阻抗双绞线
        public static final int   EM_CFG_VIDEO_LINE_TYPE_TP17 = 3;      // 17欧姆阻抗双绞线
        public static final int   EM_CFG_VIDEO_LINE_TYPE_TP25 = 4;      // 25欧姆阻抗双绞线
        public static final int   EM_CFG_VIDEO_LINE_TYPE_TP35 = 5;      // 35欧姆阻抗双绞线
    }

    // 通道名称配置
    public static class AV_CFG_ChannelName extends SdkStructure
    {
        public int              nStructSize;
        public int              nSerial;                              // 摄像头唯一编号
        public byte[]           szName = new byte[CFG_MAX_CHANNEL_NAME_LEN]; // 通道名

        public AV_CFG_ChannelName() {
            this.nStructSize = this.size();
        }
    }

    // 设备通过Wifi模块扫描周围无线设备配置
    public static class CFG_WIFI_SEARCH_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否生效, boolean类型,为1或者0
        public int              nPeriod;                              // 事件上报周期,单位秒,该值需要大于等于6
        public int              bOptimizNotification;                 // boolean类型,为1或者0,默认值是0，置0时，搜索到的设备状态每次均全部上报,置1时，搜索到的设备状态在未超过PD中OptimizationPeriod取值周期时，只上报新增/离开的设备状态；超过PD中OptimizationPeriod取值周期时，当次需上报搜索到的全部设备状态
    }

    public static class EM_CFG_CARD_STATE extends SdkStructure
    {
        public static final int   EM_CFG_CARD_STATE_UNKNOWN = -1;       // 未知
        public static final int   EM_CFG_CARD_STATE_SWIPE = 0;          // 门禁刷卡
        public static final int   EM_CFG_CARD_STATE_COLLECTION = 1;     // 门禁采集卡
    }

    // 门禁事件配置
    public static class CFG_ACCESS_EVENT_INFO extends SdkStructure
    {
        public byte[]           szChannelName = new byte[MAX_NAME_LEN]; // 门禁通道名称
        public int              emState;                              // 门禁状态, 参考 CFG_ACCESS_STATE
        public int              emMode;                               // 门禁模式, 参考  CFG_ACCESS_MODE
        public int              nEnableMode;                          // 门禁使能电平值, 0:低电平有效(断电启动); 1:高电平有效(通电启动);
        public int              bSnapshotEnable;                      // 事件联动抓图使能, 1-true, 0-false
        // 能力
        public byte             abDoorOpenMethod;
        public byte             abUnlockHoldInterval;
        public byte             abCloseTimeout;
        public byte             abOpenAlwaysTimeIndex;
        public byte             abCloseAlwaysTimeIndex;
        public byte             abHolidayTimeIndex;
        public byte             abBreakInAlarmEnable;
        public byte             abRepeatEnterAlarmEnable;
        public byte             abDoorNotClosedAlarmEnable;
        public byte             abDuressAlarmEnable;
        public byte             abDoorTimeSection;
        public byte             abSensorEnable;
        public byte             abFirstEnterEnable;
        public byte             abRemoteCheck;
        public byte             abRemoteDetail;
        public byte             abHandicapTimeOut;
        public byte             abCheckCloseSensor;
        public byte             abAutoRemoteCheck;
        public byte             abSpecialDaysOpenAlwaysTime;          //是否支持SpecialDaysOpenAlwaysTime字段
        public byte             abSpecialDaysCloseAlwaysTime;         //是否支持SpecialDaysCloseAlwaysTime字段
        public int              emDoorOpenMethod;                     // 开门方式, 参考  CFG_DOOR_OPEN_METHOD
        public int              nUnlockHoldInterval;                  // 门锁保持时间(自动关门时间),单位毫秒,[250, 20000]
        public int              nCloseTimeout;                        // 关门超时时间, 超过阈值未关会触发报警，单位秒,[0,9999];0表示不检测超时
        public int              nOpenAlwaysTimeIndex;                 // 常开时间段, 值为CFG_ACCESS_TIMESCHEDULE_INFO配置的数组下标
        public int              nCloseAlwaysTimeIndex;                // 常关时间段, 值为CFG_ACCESS_TIMESCHEDULE_INFO配置的数组下标
        public int              nHolidayTimeRecoNo;                   // 假期内时间段, 值为假日记录集的记录编号，对应NET_RECORDSET_HOLIDAY的nRecNo
        public int              bBreakInAlarmEnable;                  // 闯入报警使能, 1-true, 0-false
        public int              bRepeatEnterAlarm;                    // 反潜报警使能, 1-true, 0-false
        public int              bDoorNotClosedAlarmEnable;            // 门未关报警使能, 1-true, 0-false
        public int              bDuressAlarmEnable;                   // 胁迫报警使能, 1-true, 0-false
        public CFG_DOOROPEN_TIMESECTION_WEEK_DAY[] stuDoorTimeSectionArr = (CFG_DOOROPEN_TIMESECTION_WEEK_DAY[])new CFG_DOOROPEN_TIMESECTION_WEEK_DAY().toArray(WEEK_DAY_NUM); // 分时段开门信息
        public int              bSensorEnable;                        // 门磁使能, 1-true, 0-false
        public CFG_ACCESS_FIRSTENTER_INFO stuFirstEnterInfo;          // 首卡开门信息
        public int              bRemoteCheck;                         // 1-true, 0-false, 是否需要平台验证, TRUE表示权限通过后必须要平台验证后才能开门, FALSE表示权限验证通过后可立即开门
        public CFG_REMOTE_DETAIL_INFO stuRemoteDetail;                // 与bRemoteCheck配合使用, 如果远端验证未应答, 设定的设备超时时间到后, 是正常开门还是不开门
        public CFG_HANDICAP_TIMEOUT_INFO stuHandicapTimeOut;          // 针对残障人士的开门参数
        public int              bCloseCheckSensor;                    // 闭锁前是否检测门磁, 1-true, 0-false
        // true:则当开锁保持时间计时结束后，只有监测到有效门磁信号时，才可以恢复关闭锁的动作。
        // 反之，如果开锁保持时间已到，但未检测到有效门磁信号，则一直保持开锁状态；
        // false(默认):则直接按照设定的开锁保持时间进行开锁保持和恢复关闭的动作。
        public CFG_AUTO_REMOTE_CHECK_INFO stuAutoRemoteCheck;         // 开门远程验证, 如果开启, 在该时间段内, 设备通过多人组合开门事件通知到平台确认是否可以开门
        public int              bLocalControlEnable;                  // 本地控制启用, 1-TRUE 启用,   0-false 停用
        public int              bRemoteControlEnable;                 // 远程控制启用, 1-true  启用,   0-false 停用
        public int              nSensorDelay;                         // 传感器输出延时，超过此时间判断有人, 单位：秒。 0~10
        public int              nHumanStatusSensitivity;              // 人状态变化检测灵敏度,在此时间内，判断有人 单位： 秒。 0~300
        public int              nDetectSensitivity;                   // 传感器本身的检测灵敏度  单位：%， 0~100
        public int              bLockTongueEnable;                    // 锁舌使能, 1-true, 0-false
        public int              nABLockRoute;                         // AB互锁路线与AB互锁的index对应；-1代表无效
        public int              nDoorNotClosedReaderAlarmTime;        // 门未关超时读卡器报警, 单位：秒
        public int              bEnable;                              // 使能项,此通道配置是否启用, 1-true为使能, 0-false为关闭
        public byte[]           szSN = new byte[CFG_MAX_SN_LEN];      // 无线设备序列号,只获取，不能设置
        public int              nCloseDuration;                       // 门闭合时间,单位：秒
        public int              nUnlockReloadInterval;                // 开锁命令响应间隔时间,单位:毫秒
        public int              emAccessProtocol;                     // 门禁协议, 参考  CFG_EM_ACCESS_PROTOCOL
        public int              emProtocolType;                       // 参考 CFG_EM_SERIAL_PROTOCOL_TYPE, 串口协议下的具体协议功能,当emAccessProtocol为CFG_EM_ACCESS_PROTOCOL_SERIAL时有效
        public CFG_ACCESS_CONTROL_UDP_INFO stuAccessControlUdpInfo;   // 门禁udp开锁信息,当emAccessProtocol为CFG_EM_ACCESS_PROTOCOL_REMOTE时有效
        public int              nEntranceLockChannel;                 // 门禁控制器下的子通道,当emAccessProtocol为CFG_EM_ACCESS_PROTOCOL_REMOTE时有效
        public int              bSnapshotUpload;                      // 使能项,抓图是否上传, 1-true为抓图上传, 0-false为抓图不上传
        public int              nSnapUploadPos;                       // 抓图上传地址,对应的是NAS配置项的下标，NAS配置为CFG_CMD_NASEX
        public int              bCustomPasswordEnable;                // 是否启用个性化密码, 1-true, 0-false
        public int              nRepeatEnterTime;                     // 重复进入时间,0~180秒，0表示不启用
        public int              nCardNoConvert;                       // 卡号转换，0:不需要转换,1:字节取反,2:按HIDpro转换
        public int              bUnAuthorizedMaliciousSwipEnable;     // 未授权恶意刷卡事件使能, 1-true, 0-false
        public int              bFakeLockedAlarmEnable;               // 假锁报警使能, 1-true, 0-false
        public int              emReadCardState;                      // 当前门采集状态,参考EM_CFG_CARD_STATE
        public int              bHelmetEnable;                        // 是否检测安全帽
        public int              nSpecialDaysOpenAlwaysTime;           // 门禁假日常开时间段,值为SpecialDaysSchedule索引
        public int              nSpecialDaysCloseAlwaysTime;          // 门禁假日常闭时间段,值为SpecialDaysSchedule索引
        public int              nSensorType;                          // 门磁类型, 0: 未知, 1: 常开型门磁, 2: 常闭型门磁
        public CFG_AUTO_REMOTE_CHECK_INFO_EX stuAutoRemoteCheckEx;    // 自动远程开门扩展信息
        public int              bIsSpecialDaysAuthenticationTimeValid; //门禁假日正常鉴权时间段是否有效
        public int              nSpecialDaysAuthenticationTime;       //门禁假日正常鉴权时间段,值为SpecialDaysSchedule索引.假日常开大于假日常闭大于假日鉴权大于普通常开大于普通常闭
        public int              bIsOpenDoorGroupTimeOutValid;         //多人组合验证超时时间是否有效
        public int              nOpenDoorGroupTimeOut;                //多人组合验证超时时间，配套多人组合开门使用，单位：秒[10-60]，默认20
        public CFG_ACCESS_FIRSTENTER_INFO_EX stuFirstEnterInfoEx = new CFG_ACCESS_FIRSTENTER_INFO_EX(); //首卡开门信息(拓展)，与stuFirstEnterInfo搭配使用,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_ACCESS_FIRSTENTER_INFO_EX}
        public int              bIsDoorNotClosedReaderAlarmTypeValid; //门未关超时报警联动读卡器蜂鸣类型是否有效
        public int              nDoorNotClosedReaderAlarmType;        //门未关超时报警联动读卡器蜂鸣类型 ,0 : 报警后读卡器按照设定时长蜂鸣，时间到停止蜂鸣,1 : 报警后读卡器一直蜂鸣，直到门关上为止
        public int              bAutoRemoteHolidayScheduleIDUnenable; //不下发远程验证假日计划字段，(仅下发有效)
        public int              bFirstEnterHolidayScheduleIDUnenable; //不下发首卡验证假日计划字段，(仅下发有效)
        public int              abOutDoorMethodEnable;                //是否支持使能出门开门方式
        public int              bOutDoorMethodEnable;                 //是否使能出门开门方式（若使能，出门和进门模式可以分别配置）
        public int              nOutDoorMethod;                       //时间段内出门开门方式(bOutDoorMethodEnable为TRUE时有效)
        public byte[]           szResvered = new byte[852];           // 保留字节
    }

    // 分时段开门信息
    public static class CFG_DOOROPEN_TIMESECTION_WEEK_DAY extends SdkStructure
    {
        public CFG_DOOROPEN_TIMESECTION_INFO[] stuDoorTimeSection = (CFG_DOOROPEN_TIMESECTION_INFO[])new CFG_DOOROPEN_TIMESECTION_INFO().toArray(MAX_DOOR_TIME_SECTION);
    }

    // 分时段开门
    public static class CFG_DOOROPEN_TIMESECTION_INFO extends SdkStructure
    {
        public CFG_TIME_PERIOD  stuTime;                              // 时间段
        public int              emDoorOpenMethod;                     // 开门模式, 参考 CFG_DOOR_OPEN_METHOD
        public int              abOutDoorMethodEnable;                //是否支持使能出门开门方式
        public int              bOutDoorMethodEnable;                 //是否使能出门开门方式（若使能，出门和进门模式可以分别配置）
        public int              nOutDoorMethod;                       //时间段内出门开门方式(bOutDoorMethodEnable为TRUE时有效)
        public byte[]           szReserved = new byte[36];            //保留字段
    }

    // 首卡开门信息
    public static class CFG_ACCESS_FIRSTENTER_INFO extends SdkStructure
    {
        public int              bEnable;                              // 在指定的时间,只有拥有首卡权限的用户验证通过后，其他的用户才能刷卡(信息等)进入，1-true 使能, 0-false 关闭
        public int              emStatus;                             // 首卡权限验证通过后的门禁状态, 参考 CFG_ACCESS_FIRSTENTER_STATUS
        public int              nTimeIndex;                           // 需要首卡验证的时间段, 值为  配置 "AccessTimeSchedule"的门禁刷卡时间段的下标
    }

    // 远程开门验证
    public static class CFG_REMOTE_DETAIL_INFO extends SdkStructure
    {
        public int              nTimeOut;                             // 超时时间, 0表示永久等待, 其他值表示超时时间(单位为秒)
        public int              bTimeOutDoorStatus;                   // 超时后的门状态, 1-true:打开, 0-false:关闭
    }

    // 针对残障人士的开门信息
    public static class CFG_HANDICAP_TIMEOUT_INFO extends SdkStructure
    {
        public int              nUnlockHoldInterval;                  // 门锁保持时间(自动关门时间),单位毫秒,[250, 60000]
        public int              nCloseTimeout;                        // 关门超时时间, 超过阈值未关会触发报警，单位秒,[0,9999];0表示不检测超时
    }

    // 开门远程验证
    public static class CFG_AUTO_REMOTE_CHECK_INFO extends SdkStructure
    {
        public int              bEnable;                              // 使能项, 1-true: 开启, 0-false: 关闭
        public int              nTimeSechdule;                        // 对应CFG_CMD_ACCESSTIMESCHEDULE配置的索引
    }

    // DH门禁udp开锁信息
    public static class CFG_ACCESS_CONTROL_UDP_INFO extends SdkStructure
    {
        public byte[]           szAddress = new byte[CFG_MAX_ACCESS_CONTROL_ADDRESS_LEN]; // 地址
        public int              nPort;                                // 端口
    }

    // 门禁状态
    public static class CFG_ACCESS_STATE extends SdkStructure
    {
        public static final int   ACCESS_STATE_NORMAL = 0;              // 普通
        public static final int   ACCESS_STATE_CLOSEALWAYS = 1;         // 常关
        public static final int   ACCESS_STATE_OPENALWAYS = 2;          // 常开
        // 常开常闭状态下,Opendoor开门无效.
        public static final int   ACCESS_STATE_NOPERSONNC = 3;          // 无人状态常闭
        public static final int   ACCESS_STATE_NOPERSONNO = 4;          // 无人状态常开
    }

    // 门禁模式
    public static class CFG_ACCESS_MODE extends SdkStructure
    {
        public static final int   ACCESS_MODE_HANDPROTECTED = 0;        // 防夹模式
        public static final int   ACCESS_MODE_SAFEROOM = 1;             // 防护房间模式
        public static final int   ACCESS_MODE_OTHER = 2;                // 其它
    }

    // 自定义开门方式
    public static class CFG_DOOR_OPEN_METHOD extends SdkStructure
    {
        public static final int   CFG_DOOR_OPEN_METHOD_UNKNOWN = 0;
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_ONLY = 1;    // 只允许密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD = 2;        // 只允许刷卡开锁
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_OR_CARD = 3; // 密码或刷卡开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_FIRST = 4;  // 先刷卡后密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_FIRST = 5;   // 先密码后刷卡开锁
        public static final int   CFG_DOOR_OPEN_METHOD_SECTION = 6;     // 分时段开门
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINTONLY = 7; // 仅信息开锁
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_OR_CARD_OR_FINGERPRINT = 8; // 密码或刷卡或信息开锁
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_AND_CARD_AND_FINGERPINT = 9; // 密码+刷卡+信息组合开锁
        public static final int   CFG_DOOR_OPEN_METHOD_PWD_AND_FINGERPRINT = 10; // 密码+信息组合开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_FINGERPRINT = 11; // 刷卡+信息开锁
        public static final int   CFG_DOOR_OPEN_METHOD_MULTI_PERSON = 12; // 多人开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIDCARD = 13; // 人证对比
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIDCARD_AND_IDCARD = 14; // 证件+ 人证比对
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIDCARD_OR_CARD_OR_FINGER = 15; // 人证比对或刷卡或信息
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIPCARDANDIDCARD_OR_CARD_OR_FINGER = 16; // (证件+认证比对)或刷卡或信息
        public static final int   CFG_DOOR_OPEN_METHOD_USERID_AND_PWD = 17; // UserID+密码
        public static final int   CFG_DOOR_OPEN_METHOD_FACE_ONLY = 18;  // 只允许人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FACE_AND_PWD = 19; // 人脸+密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_AND_PWD = 20; // 信息+密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_AND_FACE = 21; // 信息+人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_FACE = 22; // 刷卡+人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FACE_OR_PWD = 23; // 人脸或密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_OR_PWD = 24; // 信息或密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_OR_FACE = 25; // 信息或人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_OR_FACE = 26; // 刷卡或人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_OR_FINGERPRINT = 27; // 刷卡或信息开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_AND_FACE_AND_PWD = 28; // 信息+人脸+密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_FACE_AND_PWD = 29; // 刷卡+人脸+密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_FINGERPRINT_AND_PWD = 30; // 刷卡+信息+密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_PWD_AND_FACE = 31; // 卡+信息+人脸组合开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FINGERPRINT_OR_FACE_OR_PWD = 32; // 信息或人脸或密码
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_OR_FACE_OR_PWD = 33; // 卡或人脸或密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_OR_FINGERPRINT_OR_FACE = 34; // 卡或信息或人脸开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_AND_FINGERPRINT_AND_FACE_AND_PWD = 35; // 卡+信息+人脸+密码组合开锁
        public static final int   CFG_DOOR_OPEN_METHOD_CARD_OR_FINGERPRINT_OR_FACE_OR_PWD = 36; // 卡或信息或人脸或密码开锁
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIPCARDANDIDCARD_OR_CARD_OR_FACE = 37; //(证件+人证比对)或 刷卡 或 人脸
        public static final int   CFG_DOOR_OPEN_METHOD_FACEIDCARD_OR_CARD_OR_FACE = 38; // 人证比对 或 刷卡(二维码) 或 人脸
    }

    // 首卡权限验证通过后的门禁状态
    public static class CFG_ACCESS_FIRSTENTER_STATUS extends SdkStructure
    {
        public static final int   ACCESS_FIRSTENTER_STATUS_UNKNOWN = 0; // 未知状态
        public static final int   ACCESS_FIRSTENTER_STATUS_KEEPOPEN = 1; // KeepOpen-首卡权限验证通过后，门保持常开
        public static final int   ACCESS_FIRSTENTER_STATUS_NORMAL = 2;  // Normal-首卡权限验证通过后，其他用户才能刷卡(信息等)验证通过
    }

    // 门禁协议
    public static class CFG_EM_ACCESS_PROTOCOL extends SdkStructure
    {
        public static final int   CFG_EM_ACCESS_PROTOCOL_UNKNOWN = 0;   // 未知
        public static final int   CFG_EM_ACCESS_PROTOCOL_LOCAL = 1;     // 本机开关量控制
        public static final int   CFG_EM_ACCESS_PROTOCOL_SERIAL = 2;    // 串口协议
        public static final int   CFG_EM_ACCESS_PROTOCOL_REMOTE = 3;    // 门禁udp开锁
    }

    // 串口协议下的具体协议功能
    public static class CFG_EM_SERIAL_PROTOCOL_TYPE extends SdkStructure
    {
        public static final int   CFG_EM_SERIAL_PROTOCOL_TYPE_UNKNOWN = -1; // 未知
        public static final int   CFG_EM_SERIAL_PROTOCOL_TYPE_UNUSED = 0; // 未使用
        public static final int   CFG_EM_SERIAL_PROTOCOL_TYPE_DAHUA_ACCESS_485 = 1; // 门禁485
        public static final int   CFG_EM_SERIAL_PROTOCOL_TYPE_LADDER_CONTROL = 2; // 梯控
        public static final int   CFG_EM_SERIAL_PROTOCOL_TYPE_REMOTE_READ_HEAD = 3; // 远距离读头
    }

    // CLIENT_MatrixSetCameras接口的输入参数
    public static class NET_IN_MATRIX_SET_CAMERAS extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pstuCameras;                          // 显示源信息 NET_MATRIX_CAMERA_INFO 数组, 用户分配内存，大小为sizeof(NET_MATRIX_CAMERA_INFO)*nCameraCount
        public int              nCameraCount;                         // 显示源数组大小

        public NET_IN_MATRIX_SET_CAMERAS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_MatrixSetCameras接口的输出参数
    public static class NET_OUT_MATRIX_SET_CAMERAS extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MATRIX_SET_CAMERAS() {
            this.dwSize = this.size();
        }
    }

    // 各种违章事件联动报警输出事件 (NET_ALARM_TRAFFIC_LINKAGEALARM)
    public static class ALARM_TRAFFIC_LINKAGEALARM_INFO extends SdkStructure
    {
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public byte[]           szCode = new byte[NET_COMMON_STRING_32]; // 违章联动报警事件
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // IPC新增(2017.4),RFID标签信息采集事件
    public static class ALARM_LABELINFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szIndexIs = new byte[NET_COMMON_STRING_8]; // 事件Index代表的含义，如无该字段，无法级联
        public int              nVideoIndex;                          // 视频通道号
        public int              nACK;                                 // 确认ID
        public byte[]           szReceiverID = new byte[NET_COMMON_STRING_16]; // 接收器ID
        public byte[]           szLabelID = new byte[NET_COMMON_STRING_16]; // RFID标签
        public NET_TIME_EX      stuDateTime;                          // 采集时间UTC
        public int              emLabelDataState;                     // 标签的数据状态（进入0、离开1）
        public byte[]           byReserve = new byte[1024];           // 保留字节
    }

    // 事件数据类型
    public static class NET_EM_EVENT_DATA_TYPE extends SdkStructure
    {
        public static final int   NET_EN_EVENT_DATA_TYPE_UNKNOWN = 0;   // 未知
        public static final int   NET_EN_EVENT_DATA_TYPE_REAL = 1;      // 实时数据
        public static final int   NET_EN_EVENT_DATA_TYPE_ALARM = 2;     // 报警数据
    }

    // 事件类型 NET_ALARM_FLOATINGOBJECT_DETECTION (漂浮物检测事件)
    public static class ALARM_FLOATINGOBJECT_DETECTION_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           reserved1 = new byte[4];              // 预留字段
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nChannel;                             // 通道号
        public int              nEventID;                             // 事件ID
        public int              nPresetID;                            // 事件触发的预置点ID
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public int              bExistFloatingObject;                 // 是否存在漂浮物
        public int              emEventType;                          // 事件数据类型,详见NET_EM_EVENT_DATA_TYPE
        public float            fCurrentRatio;                        // 漂浮物当前占比（相对于检测区域）单位:%, 取值范围[0, 100]
        public float            fAlarmThreshold;                      // 报警阈值。漂浮物相对于检测区域的占比, 取值范围[0, 100]
        public NET_POINT[]      stuDetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public byte[]           byReserved = new byte[1020];          // 预留字段
    }

    // 水位状态
    public static class NET_EM_WATER_LEVEL_STATUS extends SdkStructure
    {
        public static final int   NET_EM_WATER_LEVEL_STATUS_UNKNOWN = 0; // 未知
        public static final int   NET_EM_WATER_LEVEL_STATUS_NORMAL = 1; // 水位正常
        public static final int   NET_EM_WATER_LEVEL_STATUS_HIGH = 2;   // 水位高于上限阈值
        public static final int   NET_EM_WATER_LEVEL_STATUS_LOW = 3;    // 水位低于上限阈值
    }

    // 水位尺颜色
    public static class NET_EM_WATER_RULER_COLOR extends SdkStructure
    {
        public static final int   NET_EM_WATER_LEVEL_STATUS_UNKNOWN = 0; // 未知
        public static final int   NET_EM_WATER_RULER_COLOR_BLUE = 1;    // 蓝色
        public static final int   NET_EM_WATER_RULER_COLOR_RED = 2;     // 红色
    }

    // 水位尺
    public static class NET_WATER_RULER extends SdkStructure
    {
        public int              emRulerColor;                         // 水位尺颜色, 详见 NET_EM_WATER_RULER_COLOR
        public byte[]           szRulerNum = new byte[128];           // 水位尺编号
        public float            fWaterLevel;                          // 水位值（对于有拼接的情况，该值为标定的基准值加上当前刻度值，单位：米）
        public float            fHighLevel;                           //水位上限报警阈值(单位：米)
        public float            fLowLevel;                            //水位下限报警阈值(单位：米)
        public byte[]           byReserved = new byte[504];           // 保留字节
    }

    // 事件类型 NET_ALARM_WATER_LEVEL_DETECTION (水位检测事件)
    public static class ALARM_WATER_LEVEL_DETECTION_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           reserved1 = new byte[4];              // 预留字段
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nChannel;                             // 通道号
        public int              nEventID;                             // 事件ID
        public int              nPresetID;                            // 预置点ID
        public byte[]           szPresetName = new byte[64];          // 预置点名称
        public byte[]           szObjectUUID = new byte[48];          // 智能物体全局唯一物体标识
        public int              emEventType;                          // 事件数据类型, 详见 NET_EM_EVENT_DATA_TYPE
        public int              emStatus;                             // 水位状态, 详见 NET_EM_WATER_LEVEL_STATUS
        public NET_WATER_RULER  stuWaterRuler;                        // 水位尺
        public int              bManual;                              //主动查询水位功能，用于区分是否为手动触发的上报事件
        public byte[]           szReversed2 = new byte[4];            //预留对齐字节
        public byte[]           szChannelName = new byte[256];        //通道名称
        public int              nDetectMethod;                        //水位检测方式, 0:未知/不关心,默认值;1:雷达检测上报;2:智能检测
        public byte[]           byReserved = new byte[756];           // 预留字段
    }

    // 事件类型 EVENT_IVS_FLOATINGOBJECT_DETECTION (漂浮物检测)对应的规则配置
    public static class CFG_FLOATINGOBJECT_DETECTION_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public int              bRuleEnable;                          // 规则使能
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public CFG_POLYGON[]    stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM); // 检测区
        public float            fAlarmThreshold;                      // 报警阈值。当检测区域内的漂浮物占比超过阈值时则产生报警;单位:%，取值范围(0, 100]
        public int              nAlarmInterval;                       // 报警时间间隔。（单位：秒）。取值范围[60, 86400]
        public int              bDataUpload;                          // 是否上报实时数据。
        public int              nUpdateInterval;                      // 实时数据上报间隔。(单位：秒)。取值范围[60, 86400]
        public byte[]           byReserved = new byte[4096];          // 保留字节
    }

    // 事件类型 EVENT_IVS_WATER_LEVEL_DETECTION (水位检测事件)对应的规则配置
    public static class CFG_WATER_LEVEL_DETECTION_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szRuleName = new byte[MAX_NAME_LEN];  // 规则名称,不同规则不能重名
        public int              bRuleEnable;                          // 规则使能
        public int              nObjectTypeNum;                       // 相应物体类型个数
        public byte[]           szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN]; // 相应物体类型列表
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX); // 事件响应时间段
        public int              nBaseLinePointNum;                    // 拼接的水位尺坐标点数
        public CFG_POLYGON[]    stuBaseLine = (CFG_POLYGON[])new CFG_POLYGON().toArray(16); // 对于拼接的水位尺，需页面上标定出拼接处，若无拼接无需填充																					// 一般4个点，最大不超过16个点,每个点坐标归一化到[0,8192]区间
        public float            fBaseLevel;                           // 对应BaseLine标定的实际水位值。（单位：米）
        public float            fHighLevel;                           // 水位上限报警阈值(单位：米)
        public float            fLowLevel;                            // 水位下限报警阈值(单位：米)
        public int              nAlarmInterval;                       // 报警时间间隔。（单位：秒）。取值范围[60, 86400]
        public int              bDataUpload;                          // 是否上报实时数据。
        public int              nUpdateInterval;                      // 实时数据上报间隔。(单位：秒)。取值范围[60, 86400]
        public byte[]           byReserved = new byte[4096];          // 保留字节
    }

    // 事件类型NET_ALARM_TRAFFIC_JUNCTION
    public static class ALARM_TAFFIC_JUNCTION_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte             byMainSeatBelt;                       // 主驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte             bySlaveSeatBelt;                      // 副驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte             byVehicleDirection;                   // 当前被抓拍到的车辆是车头还是车尾,具体请见 EM_VEHICLE_DIRECTION
        public byte             byOpenStrobeState;                    // 开闸状态,具体请见 EM_OPEN_STROBE_STATE
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nLane;                                // 对应车道号
        public int              dwBreakingRule;                       // 违反规则掩码,第一位:闯红灯;
        // 第二位:不按规定车道行驶;
        // 第三位:逆行; 第四位：违章掉头;
        // 第五位:交通堵塞; 第六位:交通异常空闲
        // 第七位:压线行驶; 否则默认为:交通路口事件
        public NET_TIME_EX      RedLightUTC;                          // 红灯开始UTC时间
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度Km/h
        /*public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;*/
        public byte             byDirection;                          // 路口方向,1-表示正向,2-表示反向
        public byte             byLightState;                         // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte             byReserved2;                          // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见 NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte[]           szRecordFile = new byte[NET_COMMON_STRING_128]; // 报警对应的原始录像文件信息
        public EVENT_JUNCTION_CUSTOM_INFO stuCustomInfo;              // 自定义信息
        public byte             byPlateTextSource;                    // 车牌识别来源, 0:本地算法识别,1:后端服务器算法识别
        public byte[]           byReserved3 = new byte[3];            // 保留字节,留待扩展.
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte             byNoneMotorInfo;                      // 0-无非机动车人员信息信息,1-有非机动车人员信息信息  此字段为1时下面11个字段生效
        public byte             byBag;                                // 是否背包, 0-未知 1-不背包   2-背包
        public byte             byUmbrella;                           // 是否打伞, 0-未知 1-不打伞   2-打伞
        public byte             byCarrierBag;                         // 手提包状态,0-未知 1-没有 2-有
        public byte             byHat;                                // 是否戴帽子, 0-未知 1-不戴帽子 2-戴帽子
        public byte             byHelmet;                             // 头盔状态,0-未知 1-没有 2-有
        public byte             bySex;                                // 性别,0-未知 1-男性 2-女性
        public byte             byAge;                                // 年龄
        public NET_COLOR_RGBA   stuUpperBodyColor;                    // 上身颜色
        public NET_COLOR_RGBA   stuLowerBodyColor;                    // 下身颜色
        public byte             byUpClothes;                          // 上身衣服类型 0:未知 1:长袖 2:短袖 3:长裤 4:短裤 5:裙子 6:背心 7:超短裤 8:超短裙
        public byte             byDownClothes;                        // 下身衣服类型 0:未知 1:长袖 2:短袖 3:长裤 4:短裤 5:裙子 6:背心 7:超短裤 8:超短裙
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public byte[]           byReserved4 = new byte[22];           // 保留字节,留待扩展.
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public int              dwRetCardNumber;                      // 卡片个数
        public EVENT_CARD_INFO[] stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM); // 卡片信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public int              bNonMotorInfoEx;                      // 是否有非机动车信息, 1-true; 0-false
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车信息
        public byte[]           byReserved = new byte[2048];          // 保留字节,留待扩展
    }

    // 门禁控制器操作类型
    public static class NET_EM_ACCESS_CTL_MANAGER extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_CTL_MANAGER_ADDDEVICE = 0; // 添加单个分控器, 对应结构体 pstInParam = NET_IN_ACCESS_CTL_MANAGER_ADDDEVICE, pstOutParam = NET_OUT_ACCESS_CTL_MANAGER_ADDDEVICE
        public static final int   NET_EM_ACCESS_CTL_MANAGER_MODIFYDEVICE = 1; // 修改分控器名称, 对应结构体 pstInParam = NET_IN_ACCESS_CTL_MANAGER_MODIFYDEVICE, pstOutParam = NET_OUT_ACCESS_CTL_MANAGER_MODIFYDEVICE
        public static final int   NET_EM_ACCESS_CTL_MANAGER_REMOVEDEVICE = 2; // 删除分控器, 对应结构体 pstInParam = NET_IN_ACCESS_CTL_MANAGER_REMOVEDEVICE, pstOutParam = NET_OUT_ACCESS_CTL_MANAGER_REMOVEDEVICE
        public static final int   NET_EM_ACCESS_CTL_GETSUBCONTROLLER_INFO = 3; // 获取分控器信息, 对应结构体 pstInParam = NET_IN_GET_SUB_CONTROLLER_INFO, pstOutParam = NET_OUT_GET_SUB_CONTROLLER_INFO
        public static final int   NET_EM_ACCESS_CTL_GETSUBCONTROLLER_STATE = 4; // 获取分控器状态, 对应结构体 pstInParam = NET_IN_GET_SUB_CONTROLLER_STATE, pstOutParam = NET_OUT_GET_SUB_CONTROLLER_STATE
        public static final int   NET_EM_ACCESS_CTL_SET_REPEAT_ENTERROUTE = 5; // 设置反潜路径信息, 对应结构体 pstInparam = NET_IN_SET_REPEAT_ENTERROUTE, pstOutParam = NET_OUT_SET_REPEAT_ENTERROUTE
        public static final int   NET_EM_ACCESS_CTL_GET_REPEAT_ENTERROUTE = 6; // 获取反潜路径信息, 对应结构体 pstInparam = NET_IN_GET_REPEAT_ENTERROUTE, pstOutParam = NET_OUT_GET_REPEAT_ENTERROUTE
        public static final int   NET_EM_ACCESS_CTL_SET_ABLOCK_ROUTE = 7; // 设置AB互锁路径信息, 对应结构体 pstInparam = NET_IN_SET_ABLOCK_ROUTE, pstOutParam = NET_OUT_SET_ABLOCK_ROUTE
        public static final int   NET_EM_ACCESS_CTL_GET_ABLOCK_ROUTE = 8; // 获取AB互锁路径信息, 对应结构体 pstInparam = NET_IN_GET_ABLOCK_ROUTE, pstOutParam = NET_OUT_GET_ABLOCK_ROUTE
        public static final int   NET_EM_ACCESS_CTL_GET_LOGSTATUS = 9;  // 获取日志同步状态,对应结构体 pstInparam = NET_IN_GET_LOGSTATUS, pstOutParam = NET_OUT_GET_LOGSTATUS
        public static final int   NET_EM_ACCESS_CTL_SYNCHRO_OFFLINE_LOG = 10; // 同步离线日志, 对应结构体 pstInparam = NET_IN_SYNCHRO_OFFLINE_LOG, pstOutParam = NET_OUT_SYNCHRO_OFFLINE_LOG
        public static final int   NET_EM_ACCESS_CTL_SYNCHRO_TIME = 11;  // 同步分控器时间,对应结构体 pstInparam = NET_IN_SYNCHRO_CONTROLLER_TIME, pstOutParam = NET_OUT_SYNCHRO_CONTROLLER_TIME
        public static final int   NET_EM_ACCESS_CTL_SET_QRCODEDECODE_INFO = 12; // 设置二维码的解码信息, 对应结构体 pstInparam = NET_IN_SET_QRCODE_DECODE_INFO, pstOutParam = NET_OUT_SET_QRCODE_DECODE_INFO
    }

    // 获取分控器信息入参
    public static class NET_IN_GET_SUB_CONTROLLER_INFO extends SdkStructure
    {
        public int              dwSize;
        public int[]            nSubControllerID = new int[MAX_ACCESSSUBCONTROLLER_NUM]; // 分控器ID, 取值范围 -1~255, -1:获取所有,0:获取本地,1~255:外置分控
        public int              nSubControllerNum;                    // 需要查询的分控器数量

        public NET_IN_GET_SUB_CONTROLLER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取分控器信息出参
    public static class NET_OUT_GET_SUB_CONTROLLER_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_SUB_CONTROLLER_INFO[] stuSubControllerInfo = (NET_SUB_CONTROLLER_INFO[])new NET_SUB_CONTROLLER_INFO().toArray(MAX_ACCESSSUBCONTROLLER_NUM); // 分控器信息
        public int              nRetNum;                              // 查询到的分控器数量

        public NET_OUT_GET_SUB_CONTROLLER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 分控器信息
    public static class NET_SUB_CONTROLLER_INFO extends SdkStructure
    {
        public int              nSubControllerID;                     // 分控器ID，取值范围 0~255,0为本地
        public byte[]           szSubControllerName = new byte[MAX_COMMON_STRING_128]; // 分控器名称
        public int              emProperty;                           // 门禁单双向, 对应枚举 NET_ACCESS_PROPERTY
        public byte[]           szDeviceType = new byte[MAX_COMMON_STRING_128]; // 分控器型号
        public byte[]           szVesion = new byte[MAX_COMMON_STRING_128]; // 分控器版本号
        public int              nDoorNum;                             // 门数量
        public NET_CARDREAD_INFO[] stuReaderInfo = (NET_CARDREAD_INFO[])new NET_CARDREAD_INFO().toArray(MAX_ACCESSDOOR_NUM); // 门对应的读卡器号
        public byte[]           byReserved = new byte[128];
    }

    // 读卡器信息
    public static class NET_CARDREAD_INFO extends SdkStructure
    {
        public int              nDoor;                                // 门序号
        public int              nReadNum;                             // 读卡器数量
        public READ_ID[]        szReadIDArr = (READ_ID[])new READ_ID().toArray(MAX_ACCESS_READER_NUM); // 读卡器ID
        public byte[]           byReserved = new byte[64];
    }

    // 读卡器ID
    public static class READ_ID extends SdkStructure
    {
        public byte[]           szReadID = new byte[NET_COMMON_STRING_32]; // 读卡器ID
    }

    // 单双向
    public static class NET_ACCESS_PROPERTY extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_PROPERTY_UNKNOWN = 0;   // 未知
        public static final int   NET_EM_ACCESS_PROPERTY_BIDIRECT = 1;  // 双向门禁
        public static final int   NET_EM_ACCESS_PROPERTY_UNIDIRECT = 2; // 单向门径
    }

    // 门禁刷卡时间段，对此配置，通道号实际表示配置索引, 对应命令  CFG_CMD_ACCESSTIMESCHEDULE
    public static class CFG_ACCESS_TIMESCHEDULE_INFO extends SdkStructure
    {
        public TIME_SECTION_WEEK_DAY_4[] stuTimeWeekDay = (TIME_SECTION_WEEK_DAY_4[])new TIME_SECTION_WEEK_DAY_4().toArray(WEEK_DAY_NUM); // 刷卡时间段
        public int              bEnable;                              // 时间段使能开关， 1-true; 0-false
        public byte[]           szName = new byte[CFG_COMMON_STRING_128]; // 自定义名称
        public   int[]          nTimeScheduleConsumptionTimes = new int[7*4]; // 每个时间段可消费的次数
        // 第一维：前7个元素代表每周7天，第8个元素对应节假日
        // 7个元素中第一个是星期日，第二个是星期一，以此类推
        // 第二维：每天最多6个时间段
        // （设备只支持7*4，数组大小和stuTime保持一致）
        public int              nConsumptionStrategyNums;             // 消费策略的个数
        public DayTimeStrategy[] szConsumptionStrategy = new DayTimeStrategy[42]; // 消费策略,每天最多6个时间段，每6个元素对应一天, 一共7天。
        // 每个时段格式为"星期 时:分:秒-时:分:秒 消费类型 可消费次数 可消费金额"
        // 星期从0开始，表示周日，前6个时段前面都是0，表示周日的6个时段，剩下依次周一，周二... 一共42个时段。
        // 消费类型包括：0为定额消费，1为非定额消费；可消费次数最大上限200次；可消费金额最高999900，也就是9999元
        public int              bIsTimeExValid;                       //是否使用stuTimeEx,为TRUE时stuTime字段无效,仅下发时使用,获取时stuTime与stuTimeEx均有效
        public CFG_TIME_SECTION_ARRAY_6[] stuTimeEx = new CFG_TIME_SECTION_ARRAY_6[7]; //刷卡时间段（拓展）,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_TIME_SECTION}
        public byte[]           szReserved = new byte[1024];          //预留字节
    }

    public static class DayTimeStrategy extends SdkStructure {
        public byte[]           dayTime = new byte[34];
    }

    // 普通配置 (CFG_CMD_DEV_GENERRAL) General
    public static class CFG_DEV_DISPOSITION_INFO extends SdkStructure
    {
        public int              nLocalNo;                             // 本机编号，主要用于遥控器区分不同设备	0~998
        public byte[]           szMachineName = new byte[256];        // 机器名称或编号
        public byte[]           szMachineAddress = new byte[256];     // 机器部署地点即道路编码
        public byte[]           szMachineGroup = new byte[256];       // 机器分组或叫设备所属单位	默认为空，用户可以将不同的设备编为一组，便于管理，可重复。
        public byte[]           szMachineID = new byte[64];           // 机器编号, 联网平台内唯一
        public int              nLockLoginTimes;                      // 登陆失败可尝试次数
        public int              nLoginFailLockTime;                   // 登陆失败锁定时间
        public int              bLockLoginEnable;                     // 登陆失败可尝试次数使能, 1-true; 0-false
        public CFG_DATA_TIME    stuActivationTime = new CFG_DATA_TIME(); //启动时间,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_DATA_TIME}
        public int              nCheckDuration;                       //清零周期，如果规定时间内登陆次数未超过可尝试次数，尝试次数清零
        public int              bUseLocalPolicy;                      //是否使用本地GUI锁定策略
        public CFG_LOCAL_POLICY_INFO stuLocalPolicy = new CFG_LOCAL_POLICY_INFO(); //本地GUI锁定策略,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_LOCAL_POLICY_INFO}
        public byte[]           szMachineName1 = new byte[256];       //机器名称或编号
        public byte[]           bReserved = new byte[508];            // 保留字节
    }

    // 接口 CLIENT_StartVideoDiagnosis 的输入参数
    public static class NET_IN_VIDEODIAGNOSIS extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nDiagnosisID;                         // 视频诊断ID,从0开始
        public int              dwWaitTime;                           // 接口超时等待时间
        public Callback         cbVideoDiagnosis;                     // 视频诊断结果回调函数, 对应回调函数  fRealVideoDiagnosis
        public Pointer          dwUser;                               // 用户自定义参数

        public NET_IN_VIDEODIAGNOSIS() {
            this.dwSize = this.size();
        }
    }

    // 接口 CLIENT_StartVideoDiagnosis 的输出参数
    public static class NET_OUT_VIDEODIAGNOSIS extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public LLong            lDiagnosisHandle;                     // 订阅句柄

        public NET_OUT_VIDEODIAGNOSIS() {
            this.dwSize = this.size();
        }
    }

    // cbVideoDiagnosis 回调参数类型
    public static class NET_REAL_DIAGNOSIS_RESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public Pointer          pstDiagnosisCommonInfo;               // 视频诊断通用信息, 对应 NET_VIDEODIAGNOSIS_COMMON_INFO
        public int              nTypeCount;                           // 诊断结果数据诊断类型个数
        public Pointer          pDiagnosisResult;                     // 一次诊断结果数据, 大小为 dwBufSize, 格式如 NET_DIAGNOSIS_RESULT_HEADER+诊断类型1+NET_DIAGNOSIS_RESULT_HEADER+诊断类型2+...
        public int              dwBufSize;                            // 缓冲长度

        public NET_REAL_DIAGNOSIS_RESULT() {
            this.dwSize = this.size();
        }
    }

    public static class NET_DIAGNOSIS_RESULT_HEADER extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public byte[]           szDiagnosisType = new byte[MAX_PATH]; // 诊断类型,详见"视频诊断上报结果检测类型定义" 如 NET_DIAGNOSIS_DITHER
        public int              nDiagnosisTypeLen;                    // 该诊断类型结构体大小

        public NET_DIAGNOSIS_RESULT_HEADER() {
            this.dwSize = this.size();
        }
    }

    // 视频诊断结果上报通用数据
    public class NET_VIDEODIAGNOSIS_COMMON_INFO extends SdkStructure {
            /**
             此结构体大小
             */
        public			int            dwSize;
            /**
             检测通道
             */
        public			int            nDiagnosisID;
            /**
             计划名称
             */
        public			NET_ARRAY      stProject = new NET_ARRAY();
            /**
             任务名称
             */
        public			NET_ARRAY      stTask = new NET_ARRAY();
            /**
             参数表名称
             */
        public			NET_ARRAY      stProfile = new NET_ARRAY();
            /**
             设备唯一标志
             */
        public			NET_ARRAY      stDeviceID = new NET_ARRAY();
            /**
             开始时间
             */
        public NET_TIME         stStartTime = new NET_TIME();
            /**
             结束时间
             */
        public NET_TIME         stEndTime = new NET_TIME();
            /**
             视频通道号 前端设备比如DVR,IPC的通道
             */
        public			int            nVideoChannelID;
            /**
             视频码流 {@link NET_STREAM_TYPE}
             */
        public			int            emVideoStream;
            /**
             诊断结果类型 {@link NET_VIDEODIAGNOSIS_RESULT_TYPE}
             */
        public			int            emResultType;
            /**
             诊断结果
             */
        public			int            bCollectivityState;
            /**
             失败原因 {@link NET_VIDEODIAGNOSIS_FAIL_TYPE}
             */
        public			int            emFailedCause;
            /**
             失败原因描述
             */
        public			byte[]         szFailedCode = new byte[64];
            /**
             诊断结果存放地址,建议使用szResultAddressEx字段
             */
        public			byte[]         szResultAddress = new byte[128];
            /**
             码率	单位 kb/s, 每天上报一次
             */
        public			int            nFrameRate;
            /**
             宽	每天上报一次
             */
        public			int            nFrameWidth;
            /**
             高	每天上报一次
             */
        public			int            nFrameHeight;
            /**
             背景图片个数
             */
        public			int            nBackPic;
            /**
             背景图片路径
             */
        public BACK_PICTURE_ADDRESS[] szBackPicAddressArr = new BACK_PICTURE_ADDRESS[MAX_BACKPIC_COUNT]; // 背景图片路径列表
            /**
             诊断结果存放地址扩展
             */
        public			byte[]         szResultAddressEx = new byte[256];
            /**
             流开始时间
             */
        public NET_TIME         stStreamStartTime = new NET_TIME();
            /**
             流结束时间
             */
        public NET_TIME         stStreamEndTime = new NET_TIME();

            public	NET_VIDEODIAGNOSIS_COMMON_INFO(){
                for(int i=0;i<szBackPicAddressArr.length;i++){
                    szBackPicAddressArr[i]=new BACK_PICTURE_ADDRESS();
                }

                this.dwSize=this.size();

            }
    }

    // 背景图片路径
    public static class BACK_PICTURE_ADDRESS extends SdkStructure
    {
        public byte[]           szBackPicAddress = new byte[NET_COMMON_STRING_128]; // 背景图片路径
    }

    // 通用变长字符串以‘\0’结束
    public static class NET_ARRAY extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public Pointer          pArray;                               // 缓冲区 目前最小260字节,调用者申请内存 填充数据保证是'\0'结束
        public int              dwArrayLen;                           // 缓冲空间长度

        public NET_ARRAY() {
            this.dwSize = this.size();
        }
    }

    // 视频码流类型
    public static class NET_STREAM_TYPE extends SdkStructure
    {
        public static final int   NET_EM_STREAM_ERR = 0;                // 其它
        public static final int   NET_EM_STREAM_MAIN = 1;               // "Main"-主码流
        public static final int   NET_EM_STREAM_EXTRA_1 = 2;            // "Extra1"-辅码流1
        public static final int   NET_EM_STREAM_EXTRA_2 = 3;            // "Extra2"-辅码流2
        public static final int   NET_EM_STREAM_EXTRA_3 = 4;            // "Extra3"-辅码流3
        public static final int   NET_EM_STREAM_SNAPSHOT = 5;           // "Snapshot"-抓图码流
        public static final int   NET_EM_STREAM_OBJECT = 6;             // "Object"-物体流
        public static final int   NET_EM_STREAM_AUTO = 7;               // "Auto"-自动选择合适码流
        public static final int   NET_EM_STREAM_PREVIEW = 8;            // "Preview"-预览裸数据码流
        public static final int   NET_EM_STREAM_NONE = 9;               // 无视频码流(纯音频)
    }

    // 视频诊断结果类型
    public static class NET_VIDEODIAGNOSIS_RESULT_TYPE extends SdkStructure
    {
        public static final int   NET_EM_ROTATION = 0;                  // "Rotation" -视频轮巡分析结果
        public static final int   NET_EM_REAL = 1;                      // "Real" -实时视频分析结果
        public static final int   NET_EM_NR_UNKNOW = 2;                 // 未定义
    }

    //视频诊断错误原因
    public static class NET_VIDEODIAGNOSIS_FAIL_TYPE extends SdkStructure
    {
        public static final int   NET_EM_NO_ERROR = 0;                  // 诊断成功
        public static final int   NET_EM_DISCONNECT = 1;                // "Disconnect" - 末能连接设备
        public static final int   NET_EM_CH_NOT_EXIST = 2;              // "ChannelNotExist" - 通道不存在
        public static final int   NET_EM_LOGIN_OVER_TIME = 3;           // "LoginOverTime" - 登录超时
        public static final int   NET_EM_NO_VIDEO = 4;                  // "NoVideo" - 登录成功无视频
        public static final int   NET_EM_NO_RIGHT = 5;                  // "NoRight" - 无操作权限
        public static final int   NET_EM_PLATFROM_LOGIN_FAILED = 6;     // "PlatformLoginFailed" - 平台登入失败
        public static final int   NET_EM_PLATFROM_DISCONNECT = 7;       // "PlatformDisconnect" - 平台断开连接
        public static final int   NET_EM_GET_STREAM_OVER_TIME = 8;      // "GetStreamOverTime" - 获取码流超时
        public static final int   NET_EM_GET_NO_ENOUGH_STREAM = 9;      // "NoEnoughStream" - 码流不足
        public static final int   NET_EM_DECODE_STREAM_FAILED = 10;     // "DecodeStreamFailed" - 解码失败
        public static final int   NET_EM_GET_OFF_LINE = 11;             // "OffLine" - 前端设备离线
        public static final int   NET_EM_NF_UNKNOW = 12;                // 其他原因,详见结构体里的失败原因描述
        public static final int   NET_EM_NOT_SD = 13;                   // "NotSD" - 设备非球机，云台类检测无效
    }

    // 接口 CLIENT_StartFindDiagnosisResult 的输入参数
    public static class NET_IN_FIND_DIAGNOSIS extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nDiagnosisID;                         // 视频诊断ID,从0开始
        public int              dwWaitTime;                           // 接口超时等待时间
        public NET_ARRAY        stuDeviceID;                          // 设备唯一标志,pArray : null表示不使用设备ID查询
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nVideoChannel;                        // 视频通道号,-1:所有通道号
        public int              nTypeCount;                           // 诊断类型个数,0:不使用诊断类型查询
        public Pointer          pstDiagnosisTypes;                    // 诊断类型数组,表示需要查询的诊断类型, 对应 NET_ARRAY[]
        // 由用户申请内存，大小为sizeof(NET_ARRAY)*nTypeCount
        public byte[]           szProjectName = new byte[MAX_PATH];   // 计划名称
        public int              nCollectivityStateNum;                // 需要查询的诊断结果状态个数, 为0时表示需要查询的诊断结果类型为成功
        public int[]            emCollectivityState = new int[2];     // 需要查询的诊断结果状态数组, 表示需要查询的诊断结果类型,对应枚举EM_COLLECTIVITY_STATE

        public NET_IN_FIND_DIAGNOSIS() {
            this.dwSize = this.size();
        }
    }

    // 接口 CLIENT_StartFindDiagnosisResult 的输出参数
    public static class NET_OUT_FIND_DIAGNOSIS extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public LLong            lFindHandle;                          // 查询句柄
        public int              dwTotalCount;                         // 符合条件的总个数

        public NET_OUT_FIND_DIAGNOSIS() {
            this.dwSize = this.size();
        }
    }

    // 接口 CLIENT_DoFindDiagnosisResult 的输入参数
    public static class NET_IN_DIAGNOSIS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nDiagnosisID;                         // 视频诊断ID,从0开始
        public int              dwWaitTime;                           // 接口超时等待时间
        public int              nFindCount;                           // 每次查询的视频诊断结果条数
        public int              nBeginNumber;                         // 查询起始序号 0<=beginNumber<= totalCount-1

        public NET_IN_DIAGNOSIS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 接口 CLIENT_DoFindDiagnosisResult 的输出参数
    public static class NET_OUT_DIAGNOSIS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nInputNum;                            // 用户分配的 NET_VIDEODIAGNOSIS_RESULT_INFO 个数
        public int              nReturnNum;                           // 返回结果个数
        public Pointer          pstDiagnosisResult;                   // 结果数据  结构体指针需要调用者分配, 对应 NET_VIDEODIAGNOSIS_RESULT_INFO[]
        // 申请内存大小为sizeof(NET_VIDEODIAGNOSIS_RESULT_INFO)*nInputNum

        public NET_OUT_DIAGNOSIS_INFO() {
            this.dwSize = this.size();
        }
    }

    public static class NET_VIDEODIAGNOSIS_RESULT_INFO extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public Pointer          pstDiagnosisCommonInfo;               // 视频诊断通用信息  以下结构体指针需要调用者分配, 对应 NET_VIDEODIAGNOSIS_COMMON_INFO
        public int              abDither;                             // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstDither;                            // 视频抖动检测, 对应  NET_VIDEO_DITHER_DETECTIONRESULT
        public int              abStration;                           // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstStration;                          // 视频条纹检测, 对应  NET_VIDEO_STRIATION_DETECTIONRESULT
        public int              abLoss;                               // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstLoss;                              // 视频丢失检测, 对应  NET_VIDEO_LOSS_DETECTIONRESULT
        public int              abCover;                              // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstCover;                             // 视频遮挡检测, 对应 NET_VIDEO_COVER_DETECTIONRESULT
        public int              abFrozen;                             // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstFrozen;                            // 视频冻结检测, 对应 NET_VIDEO_FROZEN_DETECTIONRESULT
        public int              abBrightness;                         // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstBrightness;                        // 视频亮度异常检测, 对应  NET_VIDEO_BRIGHTNESS_DETECTIONRESULT
        public int              abContrast;                           // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstContrast;                          // 视频对比度异常检测, 对应  NET_VIDEO_CONTRAST_DETECTIONRESULT
        public int              abUnbalance;                          // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstUnbalance;                         // 视频偏色检测, 对应 NET_VIDEO_UNBALANCE_DETECTIONRESULT
        public int              abNoise;                              // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstNoise;                             // 视频噪声检测, 对应 NET_VIDEO_NOISE_DETECTIONRESULT
        public int              abBlur;                               // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstBlur;                              // 视频模糊检测, 对应  NET_VIDEO_BLUR_DETECTIONRESULT
        public int              abSceneChange;                        // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstSceneChange;                       // 视频场景变化检测, 对应 NET_VIDEO_SCENECHANGE_DETECTIONRESULT
        public int              abVideoDelay;                         // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstVideoDelay;                        // 视频延迟检测, 对应 NET_VIDEO_DELAY_DETECTIONRESUL
        public int              abPTZMoving;                          // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstPTZMoving;                         // 云台操作检测, 对应 NET_PTZ_MOVING_DETECTIONRESULT
        public int              abBlackAndWhite;                      // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstBlackAndWhite;                     // 黑白图像检测, 对应  NET_BLACK_WHITE_DETECTIONRESULT
        public int              abDramaticChange;                     // 用于表示此次结果中这个诊断项是否有效, 1-true, 0-false
        public Pointer          pstDramaticChange;                    // 场景剧变检测, 对应  NET_DIAGNOSIS_DRAMATIC_DETECTIONRESULT
        public int              abVideoAvailability;                  // 是否支持视频完好率监测
        public Pointer          pstVideoAvailability;                 // 视频完好率监测结果,对应NET_VIDEO_AVAILABILITY_DETECTIONRESULT
        public int              abSnowflake;                          // 是否支持雪花屏检测
        public Pointer          pstSnowflake;                         // 雪花屏检测,对应NET_VIDEO_SNOWFLAKE_DETECTIONRESULT
        public int              abAlgorithmType;                      //是否支持视频算法类型检测结果
        public Pointer          pstAlgorithmType;                     //视频算法类型检测结果,对应NET_VIDEO_ALGORITHMTYPE_DETECTIONRESULT
        public int              abVideoFilckering;                    //是否支持视频闪频检测
        public Pointer          pstVideoFilckering;                   //视频闪频检测,对应NET_VIDEO_FILCKERING_DETECTION_RESULT
        public int              abVideoLossFrame;                     //是否支持视频丢帧检测
        public Pointer          pstVideoLossFrame;                    //视频丢帧检测,对应NET_VIDEO_LOSS_FRAME_DETECTION_RESULT

        public NET_VIDEODIAGNOSIS_RESULT_INFO() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_DITHER) 视频抖动检测结果 -- 画面变化 风吹,晃动,转动包括云台转动
    public static class NET_VIDEO_DITHER_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态  一般小于是正常,大于是异常,中间是警告, 参考  NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间  检测项持续检测时间 暂时无用
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_DITHER_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    public static class NET_STATE_TYPE extends SdkStructure
    {
        public static final int   NET_EM_STATE_ERR = 0;                 // 其它
        public static final int   NET_EM_STATE_NORMAL = 1;              // "Normal" 正常
        public static final int   NET_EM_STATE_WARNING = 2;             // "Warning" 警告
        public static final int   NET_EM_STATE_ABNORMAL = 3;            // "Abnormal" 异常
    }

    // 对应检测类型(NET_DIAGNOSIS_STRIATION)视频条纹检测结果 -- 相机受到干扰出现异常条纹
    public static class NET_VIDEO_STRIATION_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考  NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_STRIATION_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_LOSS)视频丢失检测结果 -- 断电 断线等造成的
    public static class NET_VIDEO_LOSS_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_LOSS_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_COVER)视频遮挡检测结果 -- 相机被遮挡了
    public static class NET_VIDEO_COVER_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_COVER_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_FROZEN)视频冻结检测结果 -- 画面不动多久为冻结
    public static class NET_VIDEO_FROZEN_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_FROZEN_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_BRIGHTNESS)视频亮度异常检测结果 --以下是相机配置不正确的一些现象检测
    public static class NET_VIDEO_BRIGHTNESS_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_BRIGHTNESS_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_CONTRAST)视频对比度异常检测结果
    public static class NET_VIDEO_CONTRAST_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_CONTRAST_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_UNBALANCE)视频偏色异常检测结果
    public static class NET_VIDEO_UNBALANCE_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_UNBALANCE_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_NOISE)视频噪声异常检测结果
    public static class NET_VIDEO_NOISE_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_NOISE_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_BLUR)视频模糊异常检测结果
    public static class NET_VIDEO_BLUR_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_BLUR_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // 对应检测类型(NET_DIAGNOSIS_SCENECHANGE)视频场景变化检测结果
    public static class NET_VIDEO_SCENECHANGE_DETECTIONRESULT extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public int              nValue;                               // 检测结果量化值
        public int              emState;                              // 检测结果状态, 参考 NET_STATE_TYPE
        public int              nDuration;                            // 状态持续时间
        public byte[]           szPicUrl = new byte[256];             // 异常检测结果图片地址

        public NET_VIDEO_SCENECHANGE_DETECTIONRESULT() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DeleteDevConfig 输入参数
    public static class NET_IN_DELETECFG extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public String           szCommand;                            // 配置命令

        public NET_IN_DELETECFG() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DeleteDevConfig 输出参数
    public static class NET_OUT_DELETECFG extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nError;                               // 设备返回的错误码
        public int              nRestart;                             // 设备是否重启
        public int              dwOptionMask;                         // 选项 具体见枚举 NET_EM_CONFIGOPTION

        public NET_OUT_DELETECFG() {
            this.dwSize = this.size();
        }
    }

    public static class NET_EM_CONFIGOPTION extends SdkStructure
    {
        public static final int   NET_EM_CONFIGOPTION_OK = 0;
        public static final int   NET_EM_CONFIGOPTION_NEEDRESTART = 1;  // 需要重启应用程序
        public static final int   NET_EM_CONFIGOPTION_NEEDREBOOT = 2;   // 需要重启系统
        public static final int   NET_EM_CONFIGOPTION_WRITEFILEERROR = 4; // 写文件出错
        public static final int   NET_EM_CONFIGOPTION_CAPSNOTSUPPORT = 8; // 设备特性不支持
        public static final int   NET_EM_CONFIGOPTION_VALIDATEFAILED = 16; // 配置校验失败
    }

    // CLIENT_GetMemberNames 输入参数
    public static class NET_IN_MEMBERNAME extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public String           szCommand;                            // 配置命令

        public NET_IN_MEMBERNAME() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetMemberNames 输出参数
    public static class NET_OUT_MEMBERNAME extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nError;                               // 设备返回的错误码
        public int              nRestart;                             // 设备是否重启
        public int              nTotalNameCount;                      // 名称个数 通过能力集得到,调用者填写
        public int              nRetNameCount;                        // 返回的实际名称个数
        public Pointer          pstNames;                             // 名称数组 调用者申请内存,个数是 nTotalNameCount 个 NET_ARRAY
        // 由用户申请内存，大小为sizeof(NET_ARRAY)*nTotalNameCount

        public NET_OUT_MEMBERNAME() {
            this.dwSize = this.size();
        }
    }

    // 视频诊断参数表(CFG_CMD_VIDEODIAGNOSIS_PROFILE)，支持多种参数表，用表名称来索引   调用者申请内存并初始化
    public static class CFG_VIDEODIAGNOSIS_PROFILE extends SdkStructure
    {
        public int              nTotalProfileNum;                     // 调用者分配参数表数 根据能力集获取
        public int              nReturnProfileNum;                    // 返回的实际参数表数
        public Pointer          pstProfiles;                          // 调用者分配 nTotalProfileNum 个 CFG_VIDEO_DIAGNOSIS_PROFILE
    }

    public static class CFG_VIDEO_DIAGNOSIS_PROFILE extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_PATH];          // 名称Ansi编码
        public Pointer          pstDither;                            // 视频抖动检测, 对应 CFG_VIDEO_DITHER_DETECTION
        public Pointer          pstStriation;                         // 视频条纹检测, 对应  CFG_VIDEO_STRIATION_DETECTION
        public Pointer          pstLoss;                              // 视频丢失检测, 对应  CFG_VIDEO_LOSS_DETECTION
        public Pointer          pstCover;                             // 视频遮挡检测, 对应  CFG_VIDEO_COVER_DETECTION
        public Pointer          pstFrozen;                            // 视频冻结检测, 对应  CFG_VIDEO_FROZEN_DETECTION
        public Pointer          pstBrightness;                        // 视频亮度异常检测, 对应  CFG_VIDEO_BRIGHTNESS_DETECTION
        public Pointer          pstContrast;                          // 对比度异常检测, 对应  CFG_VIDEO_CONTRAST_DETECTION
        public Pointer          pstUnbalance;                         // 偏色异常检测, 对应  CFG_VIDEO_UNBALANCE_DETECTION
        public Pointer          pstNoise;                             // 噪声检测, 对应  CFG_VIDEO_NOISE_DETECTION
        public Pointer          pstBlur;                              // 模糊检测, 对应  CFG_VIDEO_BLUR_DETECTION
        public Pointer          pstSceneChange;                       // 场景变化检测, 对应  CFG_VIDEO_SCENECHANGE_DETECTION
        public Pointer          pstVideoDelay;                        // 视频延时检测, 对应  CFG_VIDEO_DELAY_DETECTION
        public Pointer          pstPTZMoving;                         // 云台移动检测, 对应  CFG_PTZ_MOVING_DETECTION
        public Pointer          pstBlackAndWhite;                     // 黑白图像检测, 对应  CFG_VIDEO_BLACKWHITE_DETECTION
        public Pointer          pstDramaticChange;                    // 场景剧变检测, 对应  CFG_VIDEO_DRAMATICCHANGE_DETECTION
        public Pointer          pstVideoAvailability;                 // 视频完好率监测,对应 CFG_VIDEO_AVAILABILITY_DETECTION
        public Pointer          pstSnowflake;                         // 雪花屏检测,对应 CFG_VIDEO_SNOWFLAKE_DETECTION
        public Pointer          pstVideoAlgorithmType;                // 视频算法类型检测,对应CFG_VIDEO_ALGORITHMTYPE_DETECTION
        public Pointer          pstuVideoFilckeringDetection;         // 闪频检测,对应 CFG_VIDEO_FILCKERING_DETECTION
        public Pointer          pstuVideoLossFrameDetection;          // 丢帧检测,对应 CFG_VIDEO_LOSS_FRAME_DETECTION
        public int              bIsCompareRecord;                     // 是否比较录像
    }

    ///////////////////////////////////视频诊断参数配置///////////////////////////////////////
    // 视频抖动检测
    public static class CFG_VIDEO_DITHER_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 条纹检测
    public static class CFG_VIDEO_STRIATION_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
        public byte[]           byReserved1 = new byte[2];            // 字节对齐
        public int              bUVDetection;                         // UV分量是否检测, 1-true, 0-false
    }

    // 视频丢失检测
    public static class CFG_VIDEO_LOSS_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
    }

    // 视频遮挡检测
    public static class CFG_VIDEO_COVER_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 画面冻结检测
    public static class CFG_VIDEO_FROZEN_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
    }

    // 亮度异常检测
    public static class CFG_VIDEO_BRIGHTNESS_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             bylowerThrehold1;                     // 预警阀值 取值1-100
        public byte             bylowerThrehold2;                     // 报警阀值 取值1-100
        public byte             byUpperThrehold1;                     // 预警阀值 取值1-100
        public byte             byUpperThrehold2;                     // 报警阀值 取值1-100
    }

    // 对比度异常检测
    public static class CFG_VIDEO_CONTRAST_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             bylowerThrehold1;                     // 预警阀值 取值1-100
        public byte             bylowerThrehold2;                     // 报警阀值 取值1-100
        public byte             byUpperThrehold1;                     // 预警阀值 取值1-100
        public byte             byUpperThrehold2;                     // 报警阀值 取值1-100
    }

    // 偏色检测
    public static class CFG_VIDEO_UNBALANCE_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 噪声检测
    public static class CFG_VIDEO_NOISE_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 模糊检测
    public static class CFG_VIDEO_BLUR_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 场景变化检测
    public static class CFG_VIDEO_SCENECHANGE_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nMinDuration;                         // 最短持续时间 单位：秒 0~65535
        public byte             byThrehold1;                          // 预警阀值 取值1-100
        public byte             byThrehold2;                          // 报警阀值 取值1-100
    }

    // 视频延时检测
    public static class CFG_VIDEO_DELAY_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
    }

    // 云台移动检测
    public static class CFG_PTZ_MOVING_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
    }

    // 黑白图像检测
    public static class CFG_VIDEO_BLACKWHITE_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nEarlyWarning;                        // 预警阈值
        public int              nAlarm;                               // 报警阈值
        public int              nMinDuration;                         // 最短持续时间
    }

    // 场景剧变检测
    public static class CFG_VIDEO_DRAMATICCHANGE_DETECTION extends SdkStructure
    {
        public int              bEnable;                              // 使能配置, 1-true, 0-false
        public int              nEarlyWarning;                        // 预警阈值
        public int              nAlarm;                               // 报警阈值
        public int              nMinDuration;                         // 最短持续时间
    }

    // 视频诊断任务表(CFG_CMD_VIDEODIAGNOSIS_TASK),不同的任务通过名子索引  调用者申请内存并初始化
    public static class CFG_VIDEODIAGNOSIS_TASK extends SdkStructure
    {
        public int              nTotalTaskNum;                        // 调用者分配任务个数  根据能力集获取
        public int              nReturnTaskNum;                       // 返回实际任务个数
        public Pointer          pstTasks;                             // 任务配置 调用者分配内存 nTotalTaskNum 个 CFG_DIAGNOSIS_TASK
    }

    public static class CFG_DIAGNOSIS_TASK extends SdkStructure
    {
        public byte[]           szTaskName = new byte[MAX_PATH];      // 任务名称Ansi编码
        public byte[]           szProfileName = new byte[MAX_PATH];   // 本任务使用的诊断参数表名Ansi编码
        public int              nTotalSourceNum;                      // 调用者分配任务数据源的个数  根据能力集获取
        public int              nReturnSourceNum;                     // 返回实际任务数据源的个数
        public Pointer          pstSources;                           // 任务数据源 调用者分配内存 nTotalSourceNum 个 CFG_TAST_SOURCES
    }

    public static class CFG_TAST_SOURCES extends SdkStructure
    {
        // 能力
        public byte             abDeviceID;                           // abDeviceID(使用szDeviceID) 和 abRemoteDevice(使用stRemoteDevice) 必须有一个为true，否则Sources是null
        public byte             abRemoteDevice;
        public byte[]           szDeviceID = new byte[MAX_PATH];      // 设备ID
        public CFG_TASK_REMOTEDEVICE stRemoteDevice = new CFG_TASK_REMOTEDEVICE(); // 设备详细信息
        public int              nVideoChannel;                        // 视频通道号
        public int              emVideoStream;                        // 视频码流类型, 参考 CFG_EM_STREAM_TYPE
        public int              nDuration;                            // 持续诊断时间
        public int              abStartTime;                          // 0 表示源为实时码流, stuStartTime字段无效; 1表示源为录像文件, stuStartTime字段有效
        public CFG_NET_TIME     stuStartTime;                         // 当abStartTime为TRUE时有效，表示源不是实时预览码流, 而是录像文件，该时间表示要分析的录像的开始时间
        // stuStartTime字段作废，后续都使用stuDiagnosisRecordInfo，保留此字段只为兼容老设备
        public int              abDiagnosisRecordInfo;                // 表示stuDiagnosisRecordInfo字段是否有效; 若源为录像文件则设置为TRUE, 否则设置成FALSE.
        public NET_VIDEO_DIAGNOSIS_RECORD_INFO stuDiagnosisRecordInfo; // 视频诊断录像信息, 当 abDiagnosisRecordInfo 为TRUE有效
        public int              emSourceInputType;                    // 视频源输入方式,EM_VIDEO_CHANNEL_SOURCE_INPUT_TYPE
        public byte[]           szPath = new byte[MAX_PATH];          // 视频流地址，字段不为空优先使用
        public int              abCompareRecordInfo;                  // 表示stuCompareRecordInfo字段是否有效;
        public NET_VIDEO_DIAGNOSIS_COMPARE_RECORD_INFO stuCompareRecordInfo; // 比较录像详细信息
    }

    // 设备详细信息
    public static class CFG_TASK_REMOTEDEVICE extends SdkStructure
    {
        public byte[]           szAddress = new byte[MAX_PATH];       // 设备地址或域名
        public int              dwPort;                               // 端口号
        public byte[]           szUserName = new byte[MAX_PATH];      // 用户名
        public byte[]           szPassword = new byte[MAX_PATH];      // 密码明文
        public byte[]           szProtocolType = new byte[MAX_PATH];  // 连接设备的协议类型
        public Pointer          pVideoInput;                          // 视频输入通道，用户申请nMaxVideoInputs 个 CFG_RemoteDeviceVideoInput
        public int              nMaxVideoInputs;                      // 视频输入通道最大数
        public int              nRetVideoInputs;                      // 返回的视频输入通道数
    }

    public static class CFG_EM_STREAM_TYPE extends SdkStructure
    {
        public static final int   CFG_EM_STREAM_ERR = 0;                // 其它
        public static final int   CFG_EM_STREAM_MAIN = 1;               // "Main"-主码流
        public static final int   CFG_EM_STREAM_EXTRA_1 = 2;            // "Extra1"-辅码流1
        public static final int   CFG_EM_STREAM_EXTRA_2 = 3;            // "Extra2"-辅码流2
        public static final int   CFG_EM_STREAM_EXTRA_3 = 4;            // "Extra3"-辅码流3
        public static final int   CFG_EM_STREAM_SNAPSHOT = 5;           // "Snapshot"-抓图码流
        public static final int   CFG_EM_STREAM_OBJECT = 6;             // "Object"-物体流
    }

    // 频诊断计划表(CFG_CMD_VIDEODIAGNOSIS_PROJECT),不同的计划通过名字索引 调用者申请内存并初始化
    public static class CFG_VIDEODIAGNOSIS_PROJECT extends SdkStructure
    {
        public int              nTotalProjectNum;                     // 调用者分配计划个数  根据能力集获取
        public int              nReturnProjectNum;                    // 返回实际计划个数
        public Pointer          pstProjects;                          // 计划配置 调用者分配内存 nTotalProjectNum 个 CFG_DIAGNOSIS_PROJECT
    }

    public static class CFG_DIAGNOSIS_PROJECT extends SdkStructure
    {
        public byte[]           szProjectName = new byte[MAX_PATH];   // 计划名称Ansi编码
        public int              nTotalTaskNum;                        // 调用者分配任务列表个数  根据能力集获取
        public int              nReturnTaskNum;                       // 返回实际任务列表个数
        public Pointer          pstProjectTasks;                      // 任务列表 调用者分配内存 nTotalTaskNum 个 CFG_PROJECT_TASK
    }

    // 视频诊断计划
    public static class CFG_PROJECT_TASK extends SdkStructure
    {
        public int              bEnable;                              // 任务是否使能, 1-true, 0-false
        public byte[]           szTaskName = new byte[MAX_PATH];      // 任务名称Ansi编码
        public TIME_SECTION_WEEK_DAY_6[] stTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_6[])new TIME_SECTION_WEEK_DAY_6().toArray(WEEK_DAY_NUM); // 任务时间段
        public int              bIsCycle;                             // 任务是否循环, 1表示循环, 0表示不循环
        //public int                       emTaskType;                        // 任务类型 EM_VIDEODIAGNOSIS_PROJECT_TASK_TYPE
        public int              bIsRepeat;                            // 任务是否重复执行，（跨天/周是否重复执行）
        public int              nCycleInterval;                       // 任务循环间隔，单位秒
    }

    // 任务类型
    public static class EM_VIDEODIAGNOSIS_PROJECT_TASK_TYPE
    {
        public static final int   EM_VIDEODIAGNOSIS_PROJECT_TASK_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_VIDEODIAGNOSIS_PROJECT_TASK_TYPE_BY_TIMESECTION = 1; // 基于TimeSection进行分析，分析时间根据TimeSection控制
        public static final int   EM_VIDEODIAGNOSIS_PROJECT_TASK_TYPE_BY_CHANNELNUM = 2; // 基于通道数量进行分析，任务下发立即执行，单次任务直至所有通道分析完毕才停止，IsCycle为true则开启下次分析,若无此字段默认基于TimeSection
    }

    // 获取视频诊断进行状态( CFG_CMD_VIDEODIAGNOSIS_GETSTATE )对应结构体
    public static class CFG_VIDEODIAGNOSIS_STATE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否使能, 1-true, 0-false
        public int              bRunning;                             // 是否正在运行, 1-true, 0-false, 使能开启，并且当前有工作任务正在进行，没有错误发生。
        public byte[]           szCurrentProject = new byte[MAX_PATH]; // 当前计划名称
        public byte[]           szCurrentTask = new byte[MAX_PATH];   // 当前任务名称
        public byte[]           szCurrentProfile = new byte[MAX_PATH]; // 当前任务配置参数表名称
        public int              nCurrentSourceCount;                  // 当前任务轮训视频源个数
        public int              nCurrentSourceIndex;                  // 当前任务当前视频源序号	从0开始
        public CFG_TIME_SECTION stCurrentTimeSection;                 // 当前计划时间段
        public int              nTaskCountOfProject;                  // 当前计划总任务数
        public int              nIndexOfCurrentTask;                  // 当前任务序号 从0开始
        public int              emTaskState;                          // 任务运行状态,对应枚举EM_VIDEODIAGNOSIS_TASK_STATE
    }

    // 云台支持能力信息( CFG_CAP_CMD_PTZ_ENABLE )对应结构体
    public static class CFG_CAP_PTZ_ENABLEINFO extends SdkStructure
    {
        public int              bEnable;                              //该通道是否支持云台
    }

    // CLIENT_GetVideoDiagnosisState 入参
    public static class NET_IN_GET_VIDEODIAGNOSIS_STATE extends SdkStructure
    {
        public int              dwSize;                               //  结构体大小
        public byte[]           szProject = new byte[128];            //  计划名，为空表示所有执行的计划

        public NET_IN_GET_VIDEODIAGNOSIS_STATE() {
            this.dwSize = this.size();
        }
    }

    // 诊断状态
    public static class VIDEODIAGNOSIS_STATE extends SdkStructure
    {
        public int              bEnable;                              // 是否使能
        public int              bRunning;                             // 是否正在运行	使能开启，并且当前有工作任务正在进行，没有错误发生。
        public byte[]           szCurrentProject = new byte[MAX_PATH]; // 当前计划名称
        public byte[]           szCurrentTask = new byte[MAX_PATH];   // 当前任务名称
        public byte[]           szCurrentProfile = new byte[MAX_PATH]; // 当前任务配置参数表名称
        public int              nCurrentSourceCount;                  // 当前任务轮训视频源个数
        public int              nCurrentSourceIndex;                  // 当前任务当前视频源序号	从0开始
        public NET_TSECT        stCurrentTimeSection;                 // 当前计划时间段
        public int              nTaskCountOfProject;                  // 当前计划总任务数
        public int              nIndexOfCurrentTask;                  // 当前任务序号 从0开始
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // CLIENT_GetVideoDiagnosisState 出参
    public static class NET_OUT_GET_VIDEODIAGNOSIS_STATE extends SdkStructure
    {
        public int              dwSize;                               //  结构体大小
        public VIDEODIAGNOSIS_STATE[] stuState = (VIDEODIAGNOSIS_STATE[])new VIDEODIAGNOSIS_STATE().toArray(2); //  工作状态,数组下标0:Tour,1:RealTime

        public NET_OUT_GET_VIDEODIAGNOSIS_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_RadiometryAttach 入参
    public static class NET_IN_RADIOMETRY_ATTACH extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 视频通道号	-1 表示全部
        public Callback         cbNotify;                             // 状态回调函数指针, 对应回调函数  fRadiometryAttachCB
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_RADIOMETRY_ATTACH() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_RadiometryAttach 出参
    public static class NET_OUT_RADIOMETRY_ATTACH extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_RADIOMETRY_ATTACH() {
            this.dwSize = this.size();
        }
    }

    // 热图数据
    public static class NET_RADIOMETRY_DATA extends SdkStructure
    {
        public NET_RADIOMETRY_METADATA stMetaData;                    // 元数据
        public Pointer          pbDataBuf;                            // 热图数据缓冲区（压缩过的数据,里面是每个像素点的温度数据,可以使用元数据信息解压）
        public int              dwBufSize;                            // 热图数据缓冲区大小
        public byte[]           reserved = new byte[512];
    }

    // 热图元数据信息
    public static class NET_RADIOMETRY_METADATA extends SdkStructure
    {
        public int              nHeight;                              // 高
        public int              nWidth;                               // 宽
        public int              nChannel;                             // 通道
        public NET_TIME         stTime;                               // 获取数据时间
        public int              nLength;                              // 数据大小
        public byte[]           szSensorType = new byte[64];          // 机芯类型
        public int              nUnzipParamR;                         // 解压缩参数R
        public int              nUnzipParamB;                         // 解压缩参数B
        public int              nUnzipParamF;                         // 解压缩参数F
        public int              nUnzipParamO;                         // 解压缩参数O
        public byte[]           Reserved = new byte[256];
    }

    // CLIENT_RadiometryFetch 入参
    public static class NET_IN_RADIOMETRY_FETCH extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号, 通道号要与订阅时一致, -1除外

        public NET_IN_RADIOMETRY_FETCH() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_RadiometryFetch 出参
    public static class NET_OUT_RADIOMETRY_FETCH extends SdkStructure
    {
        public int              dwSize;
        public int              nStatus;                              // 0: 未知, 1: 空闲, 2: 获取热图中

        public NET_OUT_RADIOMETRY_FETCH() {
            this.dwSize = this.size();
        }
    }

    // 设备信息类型,对应 CLIENT_StartFind CLIENT_DoFind CLIENT_StopFind 接口
    public static class NET_FIND extends SdkStructure
    {
        public static final int   NET_FIND_RADIOMETRY = 0;              // 热成像温度查询, pInBuf= NET_IN_RADIOMETRY_*FIND*, pOutBuf= NET_OUT_RADIOMETRY_*FIND*
        public static final int   NET_FIND_POS_EXCHANGE = 1;            // POS交易信息查询,pInBuf = NET_IN_POSEXCHANGE_*FIND*,pOutBuf= NET_OUT_POSEXCHANGE_*FIND*
    }

    //热成像查询保存周期
    public static class EM_RADIOMETRY_PERIOD extends SdkStructure
    {
        public static final int   EM_RADIOMETRY_PERIOD_UNKNOWN = 0;     // 未知
        public static final int   EM_RADIOMETRY_PERIOD_5 = 5;           // 5分钟记录表，默认
        public static final int   EM_RADIOMETRY_PERIOD_10 = 10;         // 10分钟记录表
        public static final int   EM_RADIOMETRY_PERIOD_15 = 15;         // 15分钟记录表
        public static final int   EM_RADIOMETRY_PERIOD_30 = 30;         // 30分钟记录表
    }

    // CLIENT_StartFind 接口 NET_FIND_RADIOMETRY 命令入参
    public static class NET_IN_RADIOMETRY_STARTFIND extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stStartTime;                          // 查询开始时间
        public NET_TIME         stEndTime;                            // 查询结束时间
        public int              nMeterType;                           // 查询类别,见NET_RADIOMETRY_METERTYPE
        public int              nChannel;                             // 通道号
        public int              emPeriod;                             // 所查询表的保存周期,详见EM_RADIOMETRY_PERIOD

        public NET_IN_RADIOMETRY_STARTFIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFind 接口 NET_FIND_RADIOMETRY 命令出参
    public static class NET_OUT_RADIOMETRY_STARTFIND extends SdkStructure
    {
        public int              dwSize;
        public int              nFinderHanle;                         // 取到的查询句柄
        public int              nTotalCount;                          // 符合此次查询条件的结果总条数

        public NET_OUT_RADIOMETRY_STARTFIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFind 接口 NET_FIND_RADIOMETRY 命令入参
    public static class NET_IN_RADIOMETRY_DOFIND extends SdkStructure
    {
        public int              dwSize;
        public int              nFinderHanle;                         // 查询句柄
        public int              nBeginNumber;                         // 本次查询开始的索引号
        public int              nCount;                               // 本次查询条数,最大为NET_IN_RADIOMETRY_DOFIND_MAX

        public NET_IN_RADIOMETRY_DOFIND() {
            this.dwSize = this.size();
        }
    }

	// 热成像查询返回的查询结果
    public static class NET_RADIOMETRY_QUERY extends SdkStructure
    {
        public NET_TIME         stTime;                               // 记录时间
        public int              nPresetId;                            // 预置点编号
        public int              nRuleId;                              // 规则编号
        public byte[]           szName = new byte[64];                // 查询项名称
        public NET_POINT        stCoordinate;                         // 查询测温点坐标
        public int              nChannel;                             // 通道号
        public NET_RADIOMETRYINFO stTemperInfo;                       // 测温信息,目前nTemperMid, nTemperStd 成员无效
		//public NET_POINT            stCoordinates[8];				   // 查询测温点坐标
        public NET_POINT[]      stCoordinates = (NET_POINT[])new NET_POINT().toArray(8);
        public int              nCoordinateNum;                       // 查询温度点坐标返回的个数
        public byte[]           reserved = new byte[220];
    }

    // CLIENT_DoFind 接口 NET_FIND_RADIOMETRY 命令出参
    public static class NET_OUT_RADIOMETRY_DOFIND extends SdkStructure
    {
        public int              dwSize;
        public int              nFound;                               // 实际查询到的点数
        public NET_RADIOMETRY_QUERY[] stInfo = (NET_RADIOMETRY_QUERY[])new NET_RADIOMETRY_QUERY().toArray(NET_RADIOMETRY_DOFIND_MAX); // 温度统计信息

        public NET_OUT_RADIOMETRY_DOFIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StopFind 接口 NET_FIND_RADIOMETRY 命令入参
    public static class NET_IN_RADIOMETRY_STOPFIND extends SdkStructure
    {
        public int              dwSize;
        public int              nFinderHanle;                         // 查询句柄

        public NET_IN_RADIOMETRY_STOPFIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StopFind 接口 NET_FIND_RADIOMETRY 命令出参
    public static class NET_OUT_RADIOMETRY_STOPFIND extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_RADIOMETRY_STOPFIND() {
            this.dwSize = this.size();
        }
    }

    // IPC报警,IPC通过DVR或NVR上报的本地报警(对应事件 NET_ALARM_IPC)
    public static class ALARM_IPC_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号
        public int              nEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public byte[]           szName = new byte[MAX_ALARM_CHANNEL_NAME_LEN]; // 报警通道名称
        public int              nAlarmChannel;                        // 报警输入通道号，从0开始。没有该字段表示无法区分报警通道号。

        public ALARM_IPC_INFO() {
            this.dwSize = this.size();
        }
    }

    // 蓝牙开门记录集信息查询条件
    public static class FIND_RECORD_ACCESS_BLUETOOTH_INFO_CONDITION extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserName = new byte[NET_COMMON_STRING_128]; // 用户名

        public FIND_RECORD_ACCESS_BLUETOOTH_INFO_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 蓝牙开门记录集信息
    public static class NET_RECORD_ACCESS_BLUETOOTH_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRecNo;                               // 记录集编号,只读
        public byte[]           szUserName = new byte[NET_COMMON_STRING_128]; // 用户名
        public byte[]           szPassword = new byte[NET_COMMON_STRING_128]; // 密码
        public byte[]           szMac = new byte[NET_COMMON_STRING_32]; // mac地址
        public byte[]           szNote = new byte[NET_COMMON_STRING_128]; // 用户备注信息

        public NET_RECORD_ACCESS_BLUETOOTH_INFO() {
            this.dwSize = this.size();
        }
    }

    // 智能锁添加更新用户信息接口 CLIENT_UpdateSmartLockUser 入参
    public static class NET_IN_SMARTLOCK_UPDATE_USER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szSerialNumber = new byte[32];        // 设备序列号
        public byte[]           szCredentialHolder = new byte[16];    // 身份拥有者(与AccessControlCard记录集中的UserID概念一致)
        public byte[]           szUserName = new byte[32];            // 用户名称
        public NET_TIME         stuStartTime;                         // 起始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public byte[]           szReserve = new byte[512];            // 保留字段
        public int              nCardInfoNum;                         // 卡信息数量
        public NET_SMARTLOCK_CARDINFO[] stuCardInfo = (NET_SMARTLOCK_CARDINFO[])new NET_SMARTLOCK_CARDINFO().toArray(4); // 卡的信息
        public int              nPwdInfoNum;                          // 密码信息数量
        public NET_SMARTLOCK_PWDINFO[] stuPwdInfo = (NET_SMARTLOCK_PWDINFO[])new NET_SMARTLOCK_PWDINFO().toArray(4); // 密码信息
        public int              nFingerPrintInfoNum;                  // 密码信息数量
        public NET_SMARTLOCK_FPINFO[] stuFingerPrintInfo = (NET_SMARTLOCK_FPINFO[])new NET_SMARTLOCK_FPINFO().toArray(4); // 信息信息
        public int              nTaskID;                              // 任务ID

        public NET_IN_SMARTLOCK_UPDATE_USER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 智能锁添加更新用户信息接口 CLIENT_UpdateSmartLockUser 出参
    public static class NET_OUT_SMARTLOCK_UPDATE_USER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SMARTLOCK_UPDATE_USER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 卡片信息
    public static class NET_SMARTLOCK_CARDINFO extends SdkStructure
    {
        public int              emType;                               // 开门类型, 参考 NET_ACCESS_METHOD
        public int              nIndex;                               // 用户信息序号
        public byte[]           szCardNo = new byte[32];              // 卡号
        public int              emCardType;                           // 卡类型, 参考 NET_ACCESSCTLCARD_TYPE
        public byte[]           szReserve = new byte[512];            // 保留字段
    }

    // 密码信息
    public static class NET_SMARTLOCK_PWDINFO extends SdkStructure
    {
        public int              emType;                               // 开门类型, 参考 NET_ACCESS_METHOD
        public int              nIndex;                               // 用户信息序号
        public byte[]           szPassword = new byte[32];            // 密码
        public int              dwUseTime;                            // 使用次数
        public byte[]           szReserve = new byte[512];            // 保留字段
    }

    // 信息信息
    public static class NET_SMARTLOCK_FPINFO extends SdkStructure
    {
        public int              emType;                               // 开门类型, 参考 NET_ACCESS_METHOD
        public int              nIndex;                               // 用户信息序号
        public int              nFingerprintLen;                      // 信息数据长度,不超过1.5K
        public Pointer          pFingerprintData;                     // 信息数据
        public byte[]           szReserve = new byte[512];            // 保留字段
    }

    // 控制方式
    public static class NET_ACCESS_METHOD extends SdkStructure
    {
        public static final int   NET_ACCESS_METHOD_UNKNOWN = 0;        // 未知
        public static final int   NET_ACCESS_METHOD_CARD = 1;           // 卡
        public static final int   NET_ACCESS_METHOD_PASSWORD = 2;       // 密码
        public static final int   NET_ACCESS_METHOD_FINGERPRINT = 3;    // 信息
    }

    // 获取当前智能锁的注册用户信息 CLIENT_GetSmartLockRegisterInfo 入参 (每次获取最多获取32条信息)
    public static class NET_IN_GET_SMART_LOCK_REGISTER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szSerialNumber = new byte[MAX_COMMON_STRING_32]; // 设备序列号
        public int              nOffset;                              // 用户列表的偏移量

        public NET_IN_GET_SMART_LOCK_REGISTER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 注册用户的信息
    public static class NET_SMART_LOCK_REGISTER_INFO extends SdkStructure
    {
        public int              emType;                               // 开锁方式类型, 参考 NET_ACCESS_METHOD
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_16]; // 用户ID(非AccessControlCard记录集中的UserID概念)
        public byte[]           szName = new byte[MAX_COMMON_STRING_32]; // 用户名称
        public byte[]           byReserved = new byte[512];           // 保留字段
    }

    // 获取当前智能锁的注册用户信息 CLIENT_GetSmartLockRegisterInfo 出参
    public static class NET_OUT_GET_SMART_LOCK_REGISTER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTotalCount;                          // 总的用户数量
        public int              nReturnCount;                         // 实际返回的用户数量
        public NET_SMART_LOCK_REGISTER_INFO[] stuRegisterInfo = (NET_SMART_LOCK_REGISTER_INFO[])new NET_SMART_LOCK_REGISTER_INFO().toArray(MAX_NUMBER_REGISTER_INFO); // 注册用户的信息

        public NET_OUT_GET_SMART_LOCK_REGISTER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 智能锁修改用户信息 CLIENT_SetSmartLockUserName 入参
    public static class NET_IN_SET_SMART_LOCK_USERNAME extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emType;                               // 开锁方式类型, 参考 NET_ACCESS_METHOD
        public byte[]           szSerialNumber = new byte[MAX_COMMON_STRING_32]; // 智能锁序列号
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_16]; // 用户ID(非AccessControlCard记录集中的UserID概念)
        public byte[]           szName = new byte[MAX_COMMON_STRING_32]; // 需要修改成的名称

        public NET_IN_SET_SMART_LOCK_USERNAME() {
            this.dwSize = this.size();
        }
    }

    // 智能锁修改用户信息 CLIENT_SetSmartLockUserName 出参
    public static class NET_OUT_SET_SMART_LOCK_USERNAME extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SET_SMART_LOCK_USERNAME() {
            this.dwSize = this.size();
        }
    }

    // 智能锁删除用户接口 CLIENT_RemoveSmartLockUser 入参
    public static class NET_IN_SMARTLOCK_REMOVE_USER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szSerialNumber = new byte[32];        // 设备序列号
        public byte[]           szCredentialHolder = new byte[16];    // 身份拥有者(与AccessControlCard记录集中的UserID概念一致)
        public int              emType;                               // 开门类型,unknown 表示全部, 参考 NET_ACCESS_METHOD
        public int              nIndex;                               // 某种开门方式的索引号，-1表示全部
        public int              nTaskID;                              // 任务ID

        public NET_IN_SMARTLOCK_REMOVE_USER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 智能锁删除用户接口 CLIENT_RemoveSmartLockUser 出参
    public static class NET_OUT_SMARTLOCK_REMOVE_USER_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SMARTLOCK_REMOVE_USER_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取对码信息, 对应命令  NET_DEVSTATE_GET_CODEID_LIST
    public static class NET_GET_CODEID_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nStartIndex;                          // 开始的索引号,开始第一次查询可设为0
        public int              nQueryNum;                            // 本次获取的对码条数,此值小于等于能力集中nMaxPageSize字段的值
        public int              nRetCodeIDNum;                        // 实际返回的对码条数
        public Pointer          pstuCodeIDInfo;                       // 获取对码的内容, 对应 NET_CODEID_INFO[],内存由用户分配,不能小于nQueryNum*sizeof(NET_CODEID_INFO)

        public NET_GET_CODEID_LIST() {
            this.dwSize = this.size();
        }
    }

    // 对码信息
    public static class NET_CODEID_INFO extends SdkStructure
    {
        public int              dwSize;
        public long             nWirelessId;                          // 无线ID号
        public int              emType;                               // 无线设备类型, 对应枚举 NET_WIRELESS_DEVICE_TYPE
        public byte[]           szName = new byte[NET_USER_NAME_LENGTH]; // 用户名
        public int              bEnable;                              // 是否启用了此设备, 1-true; 0-false
        public byte[]           szCustomName = new byte[NET_COMMON_STRING_64]; // 自定义名称
        public int              nChannel;                             // 无线防区的alarm通道号,Alarm配置的下标,只有Type为Defence时此字段才有效。
        public int              emMode;                               // 无线设备工作模式, 对应枚举  EM_WIRELESS_DEVICE_MODE
        public int              emSenseMethod;                        // 传感器方式, 对应枚举  EM_CODEID_SENSE_METHOD_TYPE
        public byte[]           szSerialNumber = new byte[NET_WIRELESS_DEVICE_SERIAL_NUMBER_MAX_LEN]; // 无线设备序列号
        public int              nTaskID;                              // 任务ID
        public byte[]           szRoomNo = new byte[64];              // 智能锁房间号
        public int              nMaxFingerprints;                     // 信息数量:为0时表示不支持信息
        public int              nMaxCards;                            // 卡片数量:为0时表示不支持卡片
        public int              nMaxPwd;                              // 密码数量:为0时表示不支持密码
        public byte[]           szRandSalt = new byte[128];           // 智能门锁复杂盐值

        public NET_CODEID_INFO() {
            this.dwSize = this.size();
        }
    }

    // 无线设备类型
    public static class NET_WIRELESS_DEVICE_TYPE extends SdkStructure
    {
        public static final int   NET_WIRELESS_DEVICE_TYPE_UNKNOWN = 0;
        public static final int   NET_WIRELESS_DEVICE_TYPE_KEYBOARD = 1; // 无线键盘
        public static final int   NET_WIRELESS_DEVICE_TYPE_DEFENCE = 2; // 无线防区
        public static final int   NET_WIRELESS_DEVICE_TYPE_REMOTECONTROL = 3; // 无线遥控
        public static final int   NET_WIRELESS_DEVICE_TYPE_MAGNETOMER = 4; // 无线门磁
        public static final int   NET_WIRELESS_DEVICE_TYPE_ALARMBELL = 5; // 无线警号
        public static final int   NET_WIRELESS_DEVICE_TYPE_SWITCHER = 6; // 无线插座
        public static final int   NET_WIRELESS_DEVICE_TYPE_SMARTLOCK = 7; // 无线智能锁
        public static final int   NET_WIRELESS_DEVICE_TYPE_REPEATER = 8; // 无线中继器
    }

    // 无线设备工作模式
    public static class EM_WIRELESS_DEVICE_MODE extends SdkStructure
    {
        public static final int   EM_WIRELESS_DEVICE_MODE_UNKNOWN = 0;  // 模式未识别
        public static final int   EM_WIRELESS_DEVICE_MODE_NORMAL = 1;   // Normal 普通模式
        public static final int   EM_WIRELESS_DEVICE_MODE_POLLING = 2;  // Polling 巡检模式 只有Type为RemoteControl时才能处于巡检模式
    }

    // 传感器方式
    public static class EM_CODEID_SENSE_METHOD_TYPE extends SdkStructure
    {
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_UNKOWN = 0; // 未知的
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_DOOR_MAGNETISM = 1; // 门磁
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_GAS_SENSOR = 2; // 燃气传感
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_CURTAIN_SENSOR = 3; // 幕帘传感器
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_MOBILE_SENSOR = 4; // 移动传感器
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_PASSIVEINFRA = 5; // 被动红外传感器
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_URGENCY_BUTTON = 6; // 紧急按钮
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_SMOKING_SENSOR = 7; // 烟雾传感器
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_DOUBLEMETHOD = 8; // 双鉴传感器(红外+微波)
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_WATER_SENSOR = 9; // 水浸传感器
        public static final int   EM_CODEID_SENSE_METHOD_TYPE_THREEMETHOD = 10; // 三技术
    }

    // CLIENT_EncryptString 接口入参
    public static class NET_IN_ENCRYPT_STRING extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szCard = new byte[33];                // 需要加密的字符串
        public byte[]           byReserved1 = new byte[3];            // 字节对齐
        public byte[]           szKey = new byte[33];                 // 秘钥
        public byte[]           byReserved2 = new byte[3];            // 字节对齐

        public NET_IN_ENCRYPT_STRING() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_EncryptString 接口出参
    public static class NET_OUT_ENCRYPT_STRING extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szEncryptString = new byte[1024];     // 加密后字符串

        public NET_OUT_ENCRYPT_STRING() {
            this.dwSize = this.size();
        }
    }

    // 设置二维码的解码信息入参
    public static class NET_IN_SET_QRCODE_DECODE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCipher;                             // 加密方式, 参考枚举 NET_ENUM_QRCODE_CIPHER
        public byte[]           szKey = new byte[33];                 // 秘钥
        public byte[]           byReserved = new byte[3];             // 字节对齐
        public int              bUseKeyEx;                            //是否使用扩展密钥
        public byte[]           szKeyEx = new byte[128];              //密钥扩展

        public NET_IN_SET_QRCODE_DECODE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 设置二维码的解码信息出参
    public static class NET_OUT_SET_QRCODE_DECODE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_QRCODE_DECODE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 使用的加密算法
    public static class NET_ENUM_QRCODE_CIPHER extends SdkStructure
    {
        public static final int   NET_ENUM_QRCODE_CIPHER_UNKNOWN = 0;
        public static final int   NET_ENUM_QRCODE_CIPHER_AES256 = 1;    // AES-256
    }

    //门禁卡数据操作事件
    public static class ALARM_ACCESS_CARD_OPERATE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emActionType;                         // 门禁卡数据操作类型, 参考  NET_ACCESS_ACTION_TYPE
        public byte[]           szCardNo = new byte[NET_MAX_CARDINFO_LEN]; // 门禁卡卡号
        public int              emResult;                             // 操作结果,-1为未知,0为失败,1为成功,参考 NET_THREE_STATUS_BOOL
        public int              nChannelID;                           // 门通道号(或者门锁,门和门锁一一对应), 从0开始
        public byte[]           szCredentialHolder = new byte[16];    // 身份拥有者
        public int              emType;                               // 用户类型， 参考 NET_ACCESS_METHOD
        public byte[]           szSerialNum = new byte[32];           // 序列号
        public int              nIndex;                               // 某开门方式下的索引号
        public int              nTaskID;                              // 任务ID
        public int              emErrorde;                            // 操作错误码 (只有当操作结果emResult=0时才有效)

        public ALARM_ACCESS_CARD_OPERATE_INFO() {
            this.dwSize = this.size();
        }
    }

    //门禁卡数据操作类型
    public static class NET_ACCESS_ACTION_TYPE extends SdkStructure
    {
        public static final int   NET_ACCESS_ACTION_TYPE_UNKNOWN = 0;   // 未知
        public static final int   NET_ACCESS_ACTION_TYPE_INSERT = 1;    // 插入
        public static final int   NET_ACCESS_ACTION_TYPE_UPDATE = 2;    // 更新
        public static final int   NET_ACCESS_ACTION_TYPE_REMOVE = 3;    // 删除
    }

    // 门禁未关事件详细信息
    public static class ALARM_ACCESS_CTL_NOT_CLOSE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public byte[]           szDoorName = new byte[NET_MAX_DOORNAME_LEN]; // 门禁名称
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              nAction;                              // 0:开始 1:停止
        public int              nEventID;                             // 事件ID
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）

        public ALARM_ACCESS_CTL_NOT_CLOSE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 闯入事件详细信息
    public static class ALARM_ACCESS_CTL_BREAK_IN_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nDoor;                                // 门通道号
        public byte[]           szDoorName = new byte[NET_MAX_DOORNAME_LEN]; // 门禁名称
        public NET_TIME         stuTime;                              // 报警事件发生的时间
        public int              nEventID;                             //事件ID
        public int              emMethod;                             // 闯入方式
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用RealUTC，否则用 stuTime 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           reserved = new byte[4];               // 字节对齐

        public ALARM_ACCESS_CTL_BREAK_IN_INFO() {
            this.dwSize = this.size();
        }
    }

    // 门禁控制-重置密码(对应CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_ACCESS_RESET_PASSWORD 命令)
    public static class NET_CTRL_ACCESS_RESET_PASSWORD extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 门禁序号(从0开始)
        public int              emType;                               // 密码类型, 参考 EM_ACCESS_PASSWORD_TYPE
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; // 用户ID
        public byte[]           szNewPassword = new byte[MAX_COMMON_STRING_32]; // 新密码

        public NET_CTRL_ACCESS_RESET_PASSWORD() {
            this.dwSize = this.size();
        }
    }

    // 门禁控制密码类型
    public static class EM_ACCESS_PASSWORD_TYPE extends SdkStructure
    {
        public static final int   EM_ACCESS_PASSWORD_OPENDOOR = 1;      // 开门密码
        public static final int   EM_ACCESS_PASSWORD_ALARM = 2;         // 防劫持报警密码
    }

    //设置对讲状态
    public static class EM_CALL_STATUS extends SdkStructure
    {
        public static final int   EM_CALL_STATUS_UNKNOWN = 0;
        public static final int   EM_CALL_STATUS_TRYING = 1;            // 通话请求正在处理事件
        public static final int   EM_CALL_STATUS_RINGING = 2;           // 主叫时，被叫回铃事件
        public static final int   EM_CALL_STATUS_PREPARECONNECTED = 3;  // 收到对端通话接通
        public static final int   EM_CALL_STATUS_CONNECTED = 4;         // 双方通话已经正式建立
        public static final int   EM_CALL_STATUS_CALLED = 5;            // 被叫来电事件
        public static final int   EM_CALL_STATUS_PREPARELEAVINGMESSAGE = 6; // 收到留言请求事件
        public static final int   EM_CALL_STATUS_LEAVINGMESSAGECONNECTED = 7; // 留言通话接通事件
        public static final int   EM_CALL_STATUS_CALLEND = 8;           // 呼叫结束事件
        public static final int   EM_CALL_STATUS_CALLTRANSFER = 9;      // 转移呼叫到其他设备
        public static final int   EM_CALL_STATUS_CALLTRANSFERCONNECTED = 10; // 呼叫转移接通
        public static final int   EM_CALL_STATUS_HELD = 11;             // 呼叫保持
        public static final int   EM_CALL_STATUS_RESUME = 12;           // 呼叫召回
        public static final int   EM_CALL_STATUS_DND = 13;              // 免打扰
        public static final int   EM_CALL_STATUS_REMOTESDPCHANGE = 14;  // 远端SDP改变
        public static final int   EM_CALL_STATUS_REFUSE = 15;           // 拒接
    }

    // CLIENT_ControlDeviceEx接口的  DH_CTRL_SET_THIRD_CALLSTATUS 命令参数入参
    public static class NET_IN_VTP_THIRDCALL_STATUS extends SdkStructure
    {
        public int              dwSize;
        public int              emCallStatus;                         // 门禁对讲状态,参考EM_CALL_STATUS

        public NET_IN_VTP_THIRDCALL_STATUS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ControlDeviceEx接口的  DH_CTRL_SET_THIRD_CALLSTATUS 命令参数出参
    public static class NET_OUT_VTP_THIRDCALL_STATUS extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szCallID = new byte[64];              // 对讲id

        public NET_OUT_VTP_THIRDCALL_STATUS() {
            this.dwSize = this.size();
        }
    }

    // 录像回放入参信息
    public static class NET_IN_PLAY_BACK_BY_TIME_INFO extends SdkStructure
    {
        public NET_TIME         stStartTime;                          // 开始时间
        public NET_TIME         stStopTime;                           // 结束时间
        public Pointer          hWnd;                                 // 播放窗格, 可为NULL
        public fDownLoadPosCallBack cbDownLoadPos;                    // 进度回调
        public Pointer          dwPosUser;                            // 进度回调用户信息
        public fDataCallBack    fDownLoadDataCallBack;                // 数据回调
        public Pointer          dwDataUser;                           // 数据回调用户信息
        public int              nPlayDirection;                       // 播放方向, 0:正放; 1:倒放;
        public int              nWaittime;                            // 接口超时时间, 目前倒放使用
        public byte[]           bReserved = new byte[1024];           // 预留字节
    }

    // 录像回放出参信息
    public static class NET_OUT_PLAY_BACK_BY_TIME_INFO extends SdkStructure
    {
        public byte[]           bReserved = new byte[1024];           // 预留字节
    }

    // 扩展模块掉线事件 对应事件类型 NET_ALARM_MODULE_LOST
    public static class ALARM_MODULE_LOST_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stuTime;                              // 事件上报时间
        public int              nSequence;                            // 扩展模块接的总线的序号(从0开始)
        public int              emBusType;                            // 总线类型, 参考枚举  NET_BUS_TYPE
        public int              nAddr;                                // 掉线的扩展模块数目
        public int[]            anAddr = new int[MAX_ALARMEXT_MODULE_NUM]; // 掉线的扩展模块的序号(从0开始)
        public byte[]           szDevType = new byte[NET_COMMON_STRING_64]; // 设备类型 "SmartLock",是级联设备,当设备类型"AlarmDefence"接口序号为报警序号
        public int              bOnline;                              // 在线情况   默认0。   0-不在线;  1-在线
        public byte[]           szSN = new byte[32];                  // 无线配件序列号
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_MODULE_LOST_INFO() {
            this.dwSize = this.size();
        }
    }

    // 总线类型
    public static class NET_BUS_TYPE extends SdkStructure
    {
        public static final int   NET_BUS_TYPE_UNKNOWN = 0;
        public static final int   NET_BUS_TYPE_MBUS = 1;                // M-BUS总线
        public static final int   NET_BUS_TYPE_RS485 = 2;               // RS-485总线
        public static final int   NET_BUS_TYPE_CAN = 3;                 // CAN总线
    }

    //节假日记录信息
    public static class NET_RECORDSET_HOLIDAY extends SdkStructure {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public int              nDoorNum;                             // 有效的的门数目
        public int[]            sznDoors = new int[NET_MAX_DOOR_NUM]; // 有权限的门序号,即CFG_CMD_ACCESS_EVENT配置CFG_ACCESS_EVENT_INFO的数组下标
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              bEnable;                              // 假期使能
        public byte[]           szHolidayNo = new byte[NET_COMMON_STRING_32]; // 假期编号
        public byte[]           szHolidayName = new byte [NET_COMMON_STRING_128]; // 假期名称

        public NET_RECORDSET_HOLIDAY() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 EVENT_IVS_FLOATINGOBJECT_DETECTION (漂浮物检测)对应的规则配置
    public static class NET_FLOATINGOBJECT_DETECTION_RULE_INFO extends SdkStructure
    {
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public POINTCOORDINATE[] stuDetectRegion = (POINTCOORDINATE[]) new POINTCOORDINATE().toArray(20); // 检测区
        public float            fAlarmThreshold;                      // 报警阈值。当检测区域内的漂浮物占比超过阈值时则产生报警;单位:%，取值范围(0, 100]
        public int              nAlarmInterval;                       // 报警时间间隔。（单位：秒）。取值范围[60, 86400]
        public int              bDataUpload;                          // 是否上报实时数据。
        public int              nUpdateInterval;                      // 实时数据上报间隔。(单位：秒)。取值范围[60, 86400]
        public byte[]           byReserved = new byte[4096];          // 保留字节
    }

    // 堆积物点阵图信息
    public static class NET_FLOATINGOBJECT_MASK_INFO extends SdkStructure
    {
        public int              nColNum;                              // 点阵列数
        public int              nOffset;                              // 偏移
        public int              nLength;                              // 长度
        public byte[]           byReserved = new byte[508];           // 预留字段
    }

    // 事件类型 EVENT_IVS_FLOATINGOBJECT_DETECTION (漂浮物检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_FLOATINGOBJECT_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      stuDetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public int              nImageIndex;                          // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjects = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(HDBJ_MAX_OBJECTS_NUM); // 检测到的物体
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public int              bExistFloatingObject;                 // 是否存在漂浮物
        public int              emEventType;                          // 事件数据类型,详见NET_EM_EVENT_DATA_TYPE
        public float            fCurrentRatio;                        // 漂浮物当前占比（相对于检测区域）单位:%, 取值范围[0, 100]
        public float            fAlarmThreshold;                      // 报警阈值。漂浮物相对于检测区域的占比, 取值范围[0, 100]
        public NET_INTELLIGENCE_IMAGE_INFO stuOriginalImage;          // 原始图
        public NET_INTELLIGENCE_IMAGE_INFO stuSceneImage;             // 球机变到最小倍下的抓图
        public NET_FLOATINGOBJECT_MASK_INFO stuObjectMaskInfo;        // 堆积物点阵图信息
        /**
         	用来区分是普通漂浮物场景还是泡沫检测场景 {@link EM_FLOATINGOBJECT_DETECTION_SENCE_TYPE}
         */
        public	int              emDetectSenceType;
        public	int              nImageInfoNum;                        // 图片信息个数
        public	Pointer          pstuImageInfo;                        //图片信息数组, refer to {@link NET_IMAGE_INFO_EX3}
        public	byte[]           byReserved = new byte[3428 - POINTERSIZE]; //保留字节,留待扩展.
    }

    // 事件类型 EVENT_IVS_WATER_LEVEL_DETECTION(水位检测事件)对应的数据块描述信息
    public static class DEV_EVENT_WATER_LEVEL_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[MAX_EVENT_NAME];    // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szPresetName = new byte[64];          // 预置点名称
        public byte[]           szObjectUUID = new byte[48];          // 智能物体全局唯一物体标识
        public int              emEventType;                          // 事件数据类型,详见NET_EM_EVENT_DATA_TYPE
        public int              emStatus;                             // 水位状态,详见NET_EM_WATER_LEVEL_STATUS
        public NET_WATER_RULER  stuWaterRuler;                        // 水位尺
        public NET_INTELLIGENCE_IMAGE_INFO stuOriginalImage;          // 原始图
        public NET_INTELLIGENCE_IMAGE_INFO stuSceneImage;             // 球机变到最小倍下的抓图
        public int              bManual;                              //主动查询水位功能，用于区分是否为手动触发的上报事件
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public byte[]           szChannelName = new byte[256];        //通道名称
        public int              nDetectMethod;                        //水位检测方式, 0:未知/不关心,默认值;1:雷达检测上报;2:智能检测
        public byte[]           byReserved = new byte[756-POINTERSIZE]; // 保留字节
    }

    // 全景广角图
    public static class SCENE_IMAGE_INFO_EX extends SdkStructure
    {
        public int              nOffSet;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度(像素)
        public int              nHeight;                              // 图片高度(像素)
        public byte[]           szFilePath = new byte[260];           // 全景图片路径
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           szImageID = new byte[42];             //图片ID
        public byte[]           szReserved = new byte[6];             //预留字节
        public NET_TIME_EX      SnapTime = new NET_TIME_EX();         //抓拍时间，标准UTC时间（不带时区夏令时偏差），单位秒,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}
        public byte[]           byReserved = new byte[424];           // 预留字节
    }

    // 水果类型
    public static class EM_FRUIT_TYPE extends SdkStructure
    {
        public static final int   EM_FRUIT_TYPE_UNKNOWN = 0;            // 未知
        public static final int   EM_FRUIT_TYPE_GREEN_TANGERINE = 1;    // 青橘
        public static final int   EM_FRUIT_TYPE_YELLOW_TANGERINE_ORANGE = 2; // 黄橘，橙类
        public static final int   EM_FRUIT_TYPE_GRAPEFRUIT = 3;         // 柚子
        public static final int   EM_FRUIT_TYPE_LEMON = 4;              // 柠檬
        public static final int   EM_FRUIT_TYPE_WATERMELON = 5;         // 西瓜
        public static final int   EM_FRUIT_TYPE_BANANA = 6;             // 香蕉
        public static final int   EM_FRUIT_TYPE_RED_APPLE = 7;          // 红苹果
        public static final int   EM_FRUIT_TYPE_GREEN_APPLE = 8;        // 青苹果
        public static final int   EM_FRUIT_TYPE_MUSKMELON = 9;          // 香瓜
        public static final int   EM_FRUIT_TYPE_HAMIMELON = 10;         // 哈密瓜
        public static final int   EM_FRUIT_TYPE_PEAR = 11;              // 梨
        public static final int   EM_FRUIT_TYPE_KIWI = 12;              // 奇异果
        public static final int   EM_FRUIT_TYPE_PAPAYA = 13;            // 木瓜
        public static final int   EM_FRUIT_TYPE_PINEAPPLE = 14;         // 菠萝
        public static final int   EM_FRUIT_TYPE_MANGO = 15;             // 芒果
        public static final int   EM_FRUIT_TYPE_LONGAN = 16;            // 龙眼
        public static final int   EM_FRUIT_TYPE_GINSENG_FRUIT = 17;     // 人参果
        public static final int   EM_FRUIT_TYPE_POMEGRABATE = 18;       // 石榴
        public static final int   EM_FRUIT_TYPE_COCONUT = 19;           // 椰子
        public static final int   EM_FRUIT_TYPE_CREAM_JUJUBE = 20;      // 奶油枣
        public static final int   EM_FRUIT_TYPE_WINTER_JUJUBE = 21;     // 冬枣
        public static final int   EM_FRUIT_TYPE_AVOCADO = 22;           // 牛油果
        public static final int   EM_FRUIT_TYPE_RED_PLUM = 23;          // 红布林
        public static final int   EM_FRUIT_TYPE_PITAYA = 24;            // 火龙果
        public static final int   EM_FRUIT_TYPE_GUAVA = 25;             // 芭乐
        public static final int   EM_FRUIT_TYPE_PERSIMMON = 26;         // 柿子
        public static final int   EM_FRUIT_TYPE_YACON = 27;             // 雪莲果
    }

    // 检测到的水果信息
    public static class NET_CANDIDATE_FRUIT_INFO extends SdkStructure
    {
        public int              nSimilarity;                          // 相似度
        public int              emFruitType;                          // 检测到的水果类型,参考EM_FRUIT_TYPE
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    // 事件类型 EVENT_IVS_WEIGHING_PLATFORM_DETECTION(称重平台检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_WEIGHING_PLATFORM_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[MAX_EVENT_NAME];    // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nCandidateFruitNum;                   // 检测到的水果信息个数
        public NET_CANDIDATE_FRUIT_INFO[] stuFruitInfos = (NET_CANDIDATE_FRUIT_INFO[])new NET_CANDIDATE_FRUIT_INFO().toArray(100); // 检测到的水果信息
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景图
        public SCENE_IMAGE_INFO_EX stuFruitImage;                     // 水果抠图
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 安全帽佩戴状态
    public static class EM_WORK_HELMET_STATE extends SdkStructure
    {
        public static final int   EM_HELMET_STATE_UNKNOWN = 0;          // 未知
        public static final int   EM_HELMET_STATE_NOTWEAR = 1;          // 不带安全帽
        public static final int   EM_HELMET_STATE_WEAR = 2;             // 有带安全帽
    }

    // 安全帽属性
    public static class NET_HELMET_ATTRIBUTE extends SdkStructure
    {
        public int              emHelmetState;                        // 安全帽佩戴状态，参考EM_WORK_HELMET_STATE
        public int              emHelmetColor;                        // 安全帽颜色，参考EM_CLOTHES_COLOR
        public int              nHelmetFlag;                          //报警类型: 0:未知, 1:达到触发速度的报警, 2:达到上报速度的报警, 3:两者同时达到
        public int              nReportFlag;                          //报警上传标识 -1: 未知, 0: 未报警, 1: 报警,
        public int              nHasLegalHat;                         //安全帽检测结果, 0-合规 1-不合规 2-未知
        public byte[]           byReserved = new byte[1012];          // 保留字节
    }

    // 工作服穿戴状态
    public static class EM_WORKCLOTHES_STATE extends SdkStructure
    {
        public static final int   EM_WORKCLOTHES_STATE_UNKNOWN = 0;     // 未知
        public static final int   EM_WORKCLOTHES_STATE_NOTWEAR = 1;     // 无工作服
        public static final int   EM_WORKCLOTHES_STATE_WEAR = 2;        // 有工作服
    }

    //工作服合法状态
    public static class EM_CLOTHES_LEGAL_STATE extends SdkStructure {
        public static final int   EM_CLOTHES_LEGAL_STATE_UNKNOWN = 0;   // 未知
        public static final int   EM_CLOTHES_LEGAL_STATE_WRONGFUL = 1;  // 不合法
        public static final int   EM_CLOTHES_LEGAL_STATE_LEGAL = 2;     // 合法
    }

    // 工作服属性
    public static class NET_WORKCLOTHES_ATTRIBUTE extends SdkStructure
    {
        public int              emWorkClothesState;                   // 工作服穿戴状态，参考EM_WORKCLOTHES_STATE
        public int              emWorkClothColor;                     // 工作服颜色,参考EM_CLOTHES_COLOR
        public int              emWorkClothesLegalState;              // 工作服合法状态,参考EM_CLOTHES_LEGAL_STATE
        public int              nLinkGroupInfoNum;                    //联动报警的工装库信息个数
        public Pointer          pstuLinkGroupInfo;                    //联动报警的工装库信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_LINK_GROUP_INFO}
        public int              emTrousersColor;                      //工作服裤子颜色,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_CLOTHES_COLOR}
        public byte[]           szReserved1 = new byte[4];            //字节对齐
        public int              nCutoutPolicy;                        //优选方案, 0:未知,1:全身,2:上半身
        public byte[]           szLinkGroupName = new byte[128];      //触发报警的工装库名称
        public byte[]           szLinkGroupID = new byte[128];        //触发报警的工装库ID
        public byte[]           byReserved = new byte[748-POINTERSIZE]; // 保留字节
    }

   // 工作裤穿戴状态
    public static class EM_WORKPANTS_STATE extends SdkStructure {
        public static final int   EM_WORKPANTS_STATE_UNKNOWN = 0;       // 未知
        public static final int   EM_WORKPANTS_STATE_NOTWEAR = 1;       // 没有
        public static final int   EM_WORKPANTS_STATE_WEAR = 2;          // 有
    }

    //工作裤属性
    public static class NET_WORKPANTS_ATTRIBUTE extends SdkStructure {
        public int              emWorkPantsState;                     // 工作裤穿戴状态,参考EM_WORKPANTS_STATE
        public int              emWorkPantsColor;                     // 工作裤颜色,参考EM_CLOTHES_COLOR
        public byte[]           byReserved = new byte[1024];          // 预留字节
    }

    // 事件类型 EVENT_IVS_WORKCLOTHES_DETECT(工装(安全帽/工作服等)) 对应的数据块描述信息
    public static class DEV_EVENT_WORKCLOTHES_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              emClassType;                          // 智能事件所属大类,详见EM_SCENE_CLASS_TYPE
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public int              nObjectID;                            // 物体ID
        public int              nGroupID;                             // 事件组ID，一次检测的多个nGroupID相同
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数,一次检测的多个nCountInGroup相同
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号，从1开始
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景大图信息
        public HUMAN_IMAGE_INFO stuHumanImage;                        // 人体小图信息
        public NET_HELMET_ATTRIBUTE stuHelmetAttribute;               // 安全帽属性
        public NET_WORKCLOTHES_ATTRIBUTE stuWorkClothesAttribute;     // 工作服属性
        public NET_WORKPANTS_ATTRIBUTE stuWorkPantsAttribute;         // 工作裤属性
        public int              nAlarmType;                           // 不规范报警类型 0-未知 1-防护服不规范 2: 工作服不规范3:安全帽不规范4:安全帽和工作服不规范
        public byte             szSourceID[] = new byte[32];          // 事件关联ID。应用场景是同一个物体或者同一张图片做不同分析，产生的多个事件的SourceID相同
        public int              emRuleType;                           // 报警规则类型
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        /**
         * 普通帽子相关属性状态信息
         */
        public			NET_NORMALHAT_ATTRIBUTE stuNormalHat = new NET_NORMALHAT_ATTRIBUTE();
        /**
         * 口罩相关属性状态信息
         */
        public			NET_MASK_ATTRIBUTE stuMask = new NET_MASK_ATTRIBUTE();
        /**
         * 围裙相关属性状态信息
         */
        public			NET_APRON_ATTRIBUTE stuApron = new NET_APRON_ATTRIBUTE();
        /**
         * 手套相关属性状态信息
         */
        public			NET_GLOVE_ATTRIBUTE stuGlove = new NET_GLOVE_ATTRIBUTE();
        /**
         * 靴子相关属性状态信息
         */
        public			NET_BOOT_ATTRIBUTE stuBoot = new NET_BOOT_ATTRIBUTE();
        /**
         * 鞋套相关属性状态信息
         */
        public			NET_SHOESCOVER_ATTRIBUTE stuShoesCover = new NET_SHOESCOVER_ATTRIBUTE();
        /**
         * 无帽子相关属性状态信息
         */
        public			NET_NOHAT_ATTRIBUTE stuNoHat = new NET_NOHAT_ATTRIBUTE();
        /**
         * 防护面罩相关属性状态信息
         */
        public       NET_PROHELMET_ATTRIBUTE stuProhelmet = new NET_PROHELMET_ATTRIBUTE();
        /**
         * 事件公共扩展字段结构体
         */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
        public NET_FIREPROOF_CLOTHES stuFireProofClothes = new NET_FIREPROOF_CLOTHES(); // 防火衣相关属性状态信息
        public Pointer          pstObjectInfo;                        // 物体信息数据  ,对应NET_MSG_OBJECT_EX2数组
        public int              nObjectNum;                           // 物体信息数
        public NET_GLASSES_RELATED_INFO stuGlassesInfo = new NET_GLASSES_RELATED_INFO(); // 眼镜相关属性状态信息
        public NET_BREATHING_MASK_INFO stuBreathingMaskInfo = new NET_BREATHING_MASK_INFO(); //呼吸面罩相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_BREATHING_MASK_INFO}
        public NET_PROTECTIVE_SUIT_INFO stuProtectiveSuitInfo = new NET_PROTECTIVE_SUIT_INFO(); //防护服相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_PROTECTIVE_SUIT_INFO}
        public NET_UNIFORM_INFO stuUniformInfo = new NET_UNIFORM_INFO(); //制服相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_UNIFORM_INFO}
        public NET_SAFETY_ROPE_INFO stuSafetyRopeInfo = new NET_SAFETY_ROPE_INFO(); //安全绳相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_SAFETY_ROPE_INFO}
        public NET_SAFE_BELT_INFO stuSafeBeltInfo = new NET_SAFE_BELT_INFO(); //安全带相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_SAFE_BELT_INFO}
        public NET_VEST_INFO    stuVestInfo = new NET_VEST_INFO();    //反光背心相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_VEST_INFO}
        public NET_SAFETY_SHOES_INFO stuSafetyShoesInfo = new NET_SAFETY_SHOES_INFO(); //劳保鞋相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_SAFETY_SHOES_INFO}
        public NET_WRIST_GUARD_INFO stuWristGuardInfo = new NET_WRIST_GUARD_INFO(); //防割护腕相关属性状态信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_WRIST_GUARD_INFO}
        public int              nLegalAlarmType;                      //报警方式, 0:未知, 1:有不合规项即报警 2:所有合规才报警
        public byte             bHelmet;                              //是否上报了Helmet属性
        public byte             bClothes;                             //是否上报了Clothes属性
        public byte             bWorkPants;                           //是否上报了WorkPants属性
        public byte             bNormalHat;                           //是否上报了NormalHat属性
        public byte             bMask;                                //是否上报了Mask属性
        public byte             bApron;                               //是否上报了Apron属性
        public byte             bGlove;                               //是否上报了Glove属性
        public byte             bBoot;                                //是否上报了Boot属性
        public byte             bShoesCover;                          //是否上报了ShoesCover属性
        public byte             bNoHat;                               //是否上报了NoHat属性
        public byte             bProhelmet;                           //是否上报了Prohelmet属性
        public byte             bGlasses;                             //是否上报了Glasses属性
        public byte             bFireProofClothes;                    //是否上报了FireProofClothes属性
        public byte             bProtectiveSuit;                      //是否上报了ProtectiveSuit属性
        public byte             bUniform;                             //是否上报了Uniform属性
        public byte             bBreathingMask;                       //是否上报了BreathingMask属性
        public byte             bSafeBelt;                            //是否上报了SafeBelt属性
        public byte             bVest;                                //是否上报了Vest属性
        public byte             bSafetyShoes;                         //是否上报了SafetyShoes属性
        public byte             bWristGuard;                          //是否上报了WristGuard属性
        public byte             bSafetyRope;                          //是否上报了SafetyRope属性
        public byte[]           szResvered1 = new byte[3];            //字节对齐
        public int              nPresetID;                            //预置点ID,如果普通IPC则为0
        public byte[]           szPresetName = new byte[64];          //事件触发的预置点名称
        public Pointer          pstuEmployeeClothes;                  //职员工装,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EMPLOYEE_CLOTHES_INFO}
        /**
         * 预留字节
         */
        public byte[]           byReserved = new byte[236-2*POINTERSIZE];
    }

    // 智能事件抓图信息
    public static class NET_INTELLIGENCE_IMAGE_INFO extends SdkStructure
    {
        public int              nOffSet;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度(像素)
        public int              nHeight;                              // 图片高度(像素)
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[44];            // 预留字节
    }

    // 事件类型 EVENT_IVS_RADAR_SPEED_LIMIT_ALARM(雷达限速报警事件)对应的数据块描述信息
    public static class DEV_EVENT_RADAR_SPEED_LIMIT_ALARM_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szAddress = new byte[32];             // 设备IP地址
        public int              nSpeed;                               // 时速, 单位km/h
        public NET_TIME_EX      UTC;                                  // 事件发生时间
        public int              nGroupID;                             // 事件组ID
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号，测速过程第n张图片,从1开始
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    /*************************************************************************************************
     * 							门禁用户信息操作：增、删、改、查、清空
     ************************************************************************************************/
    // 人员信息操作类型
    public static class NET_EM_ACCESS_CTL_USER_SERVICE extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_CTL_USER_SERVICE_INSERT = 0; // 添加/更新人员信息, pstInParam = NET_IN_ACCESS_USER_SERVICE_INSERT , pstOutParam = NET_OUT_ACCESS_USER_SERVICE_INSERT
        public static final int   NET_EM_ACCESS_CTL_USER_SERVICE_GET = 1; // 获取人员信息, pstInParam = NET_IN_ACCESS_USER_SERVICE_GET , pstOutParam = NET_OUT_ACCESS_USER_SERVICE_GET
        public static final int   NET_EM_ACCESS_CTL_USER_SERVICE_REMOVE = 2; // 删除人员信息,包含所有授权信息 pstInParam = NET_IN_ACCESS_USER_SERVICE_REMOVE , pstOutParam = NET_OUT_ACCESS_USER_SERVICE_REMOVE
        public static final int   NET_EM_ACCESS_CTL_USER_SERVICE_CLEAR = 3; // 清空所有人员信息, pstInParam = NET_IN_ACCESS_USER_SERVICE_CLEAR , pstOutParam = NET_OUT_ACCESS_USER_SERVICE_CLEAR
    }

    // 新增或更新用户信息入参
    public static class NET_IN_ACCESS_USER_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nInfoNum;                             // 用户信息数量
        public Pointer          pUserInfo;                            // 用户信息,对应数组 NET_ACCESS_USER_INFO,内存由用户申请释放，申请大小不小于nInfoNum*sizeof(NET_ACCESS_USER_INFO);

        public NET_IN_ACCESS_USER_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 新增或更新用户信息出参
    public static class NET_OUT_ACCESS_USER_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 申请的最大返回的错误信息数量,不小于NET_IN_ACCESS_USER_SERVICE_INSERT中nInfoNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配释放内存,插入失败时，对应插入的每一项的结果,返回个数同NET_IN_ACCESS_USER_SERVICE_INSERT中nInfoNum

        public NET_OUT_ACCESS_USER_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 插入失败时，对应插入的每一项的结果
    public static class FAIL_CODE extends SdkStructure
    {
        public int              nFailCode;                            // 对应 NET_EM_FAILCODE
    }

    // 获取用户信息入参
    public static class NET_IN_ACCESS_USER_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nUserNum;                             // 查询的数量
        public USERID[]         szUserIDs = new USERID[100];          // 用户ID列表
        public USERIDEX[]       szUserIDEx = new USERIDEX[100];       // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_USER_SERVICE_GET() {
            for(int i=0;i<szUserIDs.length;i++){
                szUserIDs[i]=new USERID();
            }
            for (int i = 0; i < szUserIDEx.length; i++) {
            	szUserIDEx[i]=new USERIDEX();
			}
            this.dwSize = this.size();

        }
    }

    public static class USERID extends SdkStructure
    {
        public byte[]           szUserID = new byte[32];              // 用户ID
    }

    public static class USERIDEX extends SdkStructure
    {
        public byte[]           szUserIDEx = new byte[128];           // 用户ID扩展，当前只支持32位有效值下发
    }

    // 获取用户信息出参
    public static class NET_OUT_ACCESS_USER_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 查询返回的最大数量
        public Pointer          pUserInfo;                            // 用户信息，对应数组 NET_ACCESS_USER_INFO,内存由用户申请释放，申请大小不小于 nUserNum*sizeof(NET_ACCESS_USER_INFO)                                                                           // 返回个数同NET_IN_ACCESS_USER_SERVICE_GET中nUserNum
        public Pointer          pFailCode;                            // 对应FAIL_CODE, 查询失败时，内存由用户申请释放,对应查询的每一项的结果，返回个数同NET_IN_ACCESS_USER_SERVICE_GET中nUserNum

        public NET_OUT_ACCESS_USER_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 删除指定ID人员信息入参
    public static class NET_IN_ACCESS_USER_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nUserNum;                             // 删除的数量
        public USERID[]         szUserIDs = (USERID[])new USERID().toArray(100); // 用户ID列表
        public USERIDEX[]       szUserIDEx = (USERIDEX[])new USERIDEX().toArray(100); // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_USER_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除指定ID人员信息出参
    public static class NET_OUT_ACCESS_USER_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 返回的最大数量,不小于 NET_IN_ACCESS_USER_SERVICE_REMOVE中nUserNum
        public Pointer          pFailCode;                            // 对应FAIL_CODE, 插入失败时，内存由用户申请释放,对应插入的每一项的结果,返回个数同NET_IN_ACCESS_USER_SERVICE_REMOVE中nUserNum

        public NET_OUT_ACCESS_USER_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除所有人员信息入参
    public static class NET_IN_ACCESS_USER_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_ACCESS_USER_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 删除所有人员信息出参
    public static class NET_OUT_ACCESS_USER_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_ACCESS_USER_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 用户类型
    public static class NET_ENUM_USER_TYPE extends SdkStructure
    {
        public static final int   NET_ENUM_USER_TYPE_UNKNOWN = -1;      // 未知用户
        public static final int   NET_ENUM_USER_TYPE_NORMAL = 0;        //
        public static final int   NET_ENUM_USER_TYPE_BLACKLIST = 1;     //
        public static final int   NET_ENUM_USER_TYPE_GUEST = 2;         //
        public static final int   NET_ENUM_USER_TYPE_PATROL = 3;        //
        public static final int   NET_ENUM_USER_TYPE_VIP = 4;           //
        public static final int   NET_ENUM_USER_TYPE_HANDICAP = 5;      //
    }

    // 用户信息
    public static class NET_ACCESS_USER_INFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public byte[]           szName = new byte[MAX_COMMON_STRING_32]; // 人员名称
        public int              emUserType;                           // 用户类型, 对应枚举  NET_ENUM_USER_TYPE
        public int              nUserStatus;                          // 用户状态, 0 正常, 1 冻结
        public int              nUserTime;                            // 来宾卡的通行次数
        public byte[]           szCitizenIDNo = new byte[MAX_COMMON_STRING_32]; // 证件号码
        public byte[]           szPsw = new byte[NET_MAX_CARDPWD_LEN]; // UserID+密码开门时密码
        public int              nDoorNum;                             // 有效的门数目;
        public int[]            nDoors = new int[NET_MAX_DOOR_NUM];   // 有权限的门序号,即 CFG_CMD_ACCESS_EVENT 配置的数组下标
        public int              nTimeSectionNum;                      // 有效的的开门时间段数目
        public int[]            nTimeSectionNo = new int[NET_MAX_TIMESECTION_NUM]; // 开门时间段索引,即 CFG_ACCESS_TIMESCHEDULE_INFO 的数组下标
        public int              nSpecialDaysScheduleNum;              // 假日计划表示数量
        public int[]            nSpecialDaysSchedule = new int[MAX_ACCESSDOOR_NUM]; // 假日计划标识, 即 NET_EM_CFG_ACCESSCTL_SPECIALDAYS_SCHEDULE 配置的下标
        public NET_TIME         stuValidBeginTime = new NET_TIME();   // 开始有效期
        public NET_TIME         stuValidEndTime = new NET_TIME();     // 结束有效期
        public int              bFirstEnter;                          // 是否拥有首卡权限, 1-true, 0-false
        public int              nFirstEnterDoorsNum;                  // 拥有首用户权限的门数量
        public int[]            nFirstEnterDoors = new int[NET_MAX_DOOR_NUM]; // 拥有首用户权限的门序号，bFirstEnter为TRUE时有效,-1表示全通道
        public int              emAuthority;                          // 用户权限，可选, 对应枚举   NET_ATTENDANCE_AUTHORITY
        public int              nRepeatEnterRouteTimeout;             // 反潜超时时间
        public int              nFloorNum;                            // 有效的楼层数量
        public ACCESS_FLOOR_INFO[] szFloorNos = (ACCESS_FLOOR_INFO[])new ACCESS_FLOOR_INFO().toArray(MAX_ACCESS_FLOOR_NUM); // 楼层号列表
        public int              nRoom;                                // 房间个数
        public ROOM_INFO[]      szRoomNos = (ROOM_INFO[])new ROOM_INFO().toArray(MAX_ROOMNUM_COUNT); // 房间号列表
        public int              bFloorNoExValid;                      // szFloorNoEx 是否有效
        public int              nFloorNumEx;                          // 有效的楼层数量扩展
        public FloorNoEx_INFO[] szFloorNoEx = (FloorNoEx_INFO[])new FloorNoEx_INFO().toArray(256); // 楼层号扩展
        public byte[]           szClassInfo = new byte[256];          // 班级信息
        public byte[]           szStudentNo = new byte[64];           // 学号
        public byte[]           szCitizenAddress = new byte[128];     // 证件地址
        public NET_TIME         stuBirthDay;                          // 出生日期（年月日有效）
        public int              emSex;                                // 性别,枚举值参考NET_ACCESSCTLCARD_SEX
        public byte[]           szDepartment = new byte[128];         // 部门
        public byte[]           szSiteCode = new byte[32];            // 站点码
        public byte[]           szPhoneNumber = new byte[32];         // 手机号码
        public byte[]           szDefaultFloor = new byte[8];         // 默认楼层号
        public int              bFloorNoEx2Valid;                     // 是否使用扩展结构体
        /**
         * 对应结构体,{@link NET_FLOORS_INFO}
         */
        public Pointer          pstuFloorsEx2;                        // 楼层号（再次扩展）
        public int              bHealthStatus;                        // 人员健康状态
        public int              nUserTimeSectionsNum;                 // 用户自身的开门时间段校验有效个数
        // 针对用户自身的开门时间段校验
        public USER_TIME_SECTION[] szUserTimeSections = (USER_TIME_SECTION[]) new USER_TIME_SECTION().toArray(6);
        public byte[]           szECType = new byte[64];              // 保留字段
        /**
         * EM_TYPE_OF_CERTIFICATE
         */
        public int              emTypeOfCertificate;                  // 证件类型
        public byte[]           szCountryOrAreaCode = new byte[8];    // 国籍或所在地区代码，符合GB/T2659-2000的规范
        public byte[]           szCountryOrAreaName = new byte[64];   // 国籍或所在地区名称，符合GB/T2659-2000的规范
        public byte[]           szCertificateVersionNumber = new byte[64]; // 永久居住证的证件版本号
        public byte[]           szApplicationAgencyCode = new byte[64]; // 申请受理机关代码
        public byte[]           szIssuingAuthority = new byte[64];    // 签发机关
        public byte[]           szStartTimeOfCertificateValidity = new byte[64]; // 证件有效开始时间
        public byte[]           szEndTimeOfCertificateValidity = new byte[64]; // 证件有效结束时间
        public int              nSignNum;                             // 证件签发次数
        public byte[]           szActualResidentialAddr = new byte[108]; // 实际家庭住址
        /**
         工作班别
         */
        public			byte[]         szWorkClass = new byte[256];
        /**
         有效时间段内启动时间
         */
        public NET_TIME         stuStartTimeInPeriodOfValidity = new NET_TIME();
        /**
         测试项目    {@link EM_TEST_ITEMS }
         */
        public			int            emTestItems;
        /**
         szNameEx 是否有效，为TRUE时，使用szNameEx字段
         */
        public			int            bUseNameEx;
        /**
         人员名称扩展
         */
        public			byte[]         szNameEx = new byte[128];
        /**
         是否使用用户信息结构体
         */
        public			int            bUserInfoExValid;
        /**
         扩展用户信息  NET_ACCESS_USER_INFO_EX
         */
        public			Pointer        pstuUserInfoEx;
        /**
         授权时间、过期时间，时间单位: 小时
         */
        public			int            nAuthOverdueTime;
        /**
         人员健康状态（添加）      {@link EM_GREENCNHEALTH_STATUS}
         */
        public			int            emGreenCNHealthStatus;
        /**
         电子通行证状态（添加）     {@link EM_ALLOW_PERMIT_FLAG}
         */
        public			int            emAllowPermitFlag;
        /**
        假日组HolidayGroup索引值
         */
        public			int            nHolidayGroupIndex;
        /**
         信息更新时间,UTC时间
         */
        public org.springblade.modules.iot.dahua.lib.structure.NET_TIME stuUpdateTime = new org.springblade.modules.iot.dahua.lib.structure.NET_TIME();
        /**
         * 用户的门通道起始有效期,每个通道设置一个有效期,数组元素与门通道一一对应
         */
        public byte[]           szValidFroms = new byte[8 * 24];
        /**
         * 用户的门通道起始有效期有效个数, 最大值为8
         */
        public int              nValidFromsNum;
        /**
         * 用户的门通道截止有效期有效个数, 最大值为8
         */
        public int              nValidTosNum;
        /**
         * 用户的门通道截止有效期,每个通道设置一个有效期,数组元素与门通道一一对应
         */
        public byte[]           szValidTos = new byte[8 * 24];
        /**
         * 用户ID扩展，当前只支持32位有效值下发
         */
        public byte[]           szUserIDEx = new byte[128];
        /**
         * szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段
         */
        public int              bUserIDEx;
        /**
         * 金融业务库的人员权限，-1:表示未知, 0:存取人, 1:复核人, 2:管理员
         */
        public int              nFinancialUserType;
        /**
         * 自定义人员类型枚举 0 : 未知，1：轻工类型
         */
        public int              nCustomUserType;
        /**
         * 自定义类型的枚举 0默认值 1学生 2教师 3保安 4保障人员 5其他 6家属 7受限用户 8临时 9临时人员 10维修工 11博士 12硕士 13本科
         * 14特定学生 15在编 16校聘 17特殊岗位 19一般 20特殊 21蒋钱湾居民 22立信公寓长城别苑居民 23社区居民 24常住户 25租户
         * 26教职工家属 27预留1 28预留2 29预留3 30预留4 31预留5 32预留6 33预留7 34预留8 35预留9 36预留10 37预留11
         * 38预留12 39预留13 40预留14
         */
        public int              nCustomUserTypeValue;
        /**
         * 允许签入的时间点
         */
        public org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX stuAllowCheckInTime = new org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX();
        /**
         * 允许签出的时间点
         */
        public org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX stuAllowCheckOutTime = new org.springblade.modules.iot.dahua.lib.structure.NET_TIME_EX();
        /**
         * 扩展用户信息V2, refer to {@link NET_ACCESS_USER_INFO_EX2}
         */
        public Pointer          pstuUserInfoEx2;
        /**
         * 是否使用用户信息结构体V2
         */
        public int              bUserInfoEx2Valid;
        /**
         * 保留字段
         */
        public byte[]           byReserved = new byte[660 - 2 * POINTERSIZE];

        public NET_ACCESS_USER_INFO() {
        }
    }

    public static class FloorNoEx_INFO extends SdkStructure {
        public byte[]           szFloorNoEx = new byte[4];
    }

    public static class ACCESS_FLOOR_INFO extends SdkStructure
    {
        public byte[]           szFloorNo = new byte[NET_COMMON_STRING_16]; // 楼层号
    }

    public static class ROOM_INFO extends SdkStructure
    {
        public byte[]           szRoomNo = new byte[NET_COMMON_STRING_16]; // 房间号
    }

    // 操作错误码
    public static class NET_EM_FAILCODE extends SdkStructure
    {
        public static final int   NET_EM_FAILCODE_NOERROR = 0;          // 没有错误
        public static final int   NET_EM_FAILCODE_UNKNOWN = 1;          // 未知错误
        public static final int   NET_EM_FAILCODE_INVALID_PARAM = 2;    // 参数错误
        public static final int   NET_EM_FAILCODE_INVALID_PASSWORD = 3; // 无效密码
        public static final int   NET_EM_FAILCODE_INVALID_FP = 4;       // 无效信息数据
        public static final int   NET_EM_FAILCODE_INVALID_FACE = 5;     // 无效人脸数据
        public static final int   NET_EM_FAILCODE_INVALID_CARD = 6;     // 无效卡数据
        public static final int   NET_EM_FAILCODE_INVALID_USER = 7;     // 无效人数据
        public static final int   NET_EM_FAILCODE_FAILED_GET_SUBSERVICE = 8; // 能力集子服务获取失败
        public static final int   NET_EM_FAILCODE_FAILED_GET_METHOD = 9; // 获取组件的方法集失败
        public static final int   NET_EM_FAILCODE_FAILED_GET_SUBCAPS = 10; // 获取资源实体能力集失败
        public static final int   NET_EM_FAILCODE_ERROR_INSERT_LIMIT = 11; // 已达插入上限
        public static final int   NET_EM_FAILCODE_ERROR_MAX_INSERT_RATE = 12; // 已达最大插入速度
        public static final int   NET_EM_FAILCODE_FAILED_ERASE_FP = 13; // 清除信息数据失败
        public static final int   NET_EM_FAILCODE_FAILED_ERASE_FACE = 14; // 清除人脸数据失败
        public static final int   NET_EM_FAILCODE_FAILED_ERASE_CARD = 15; // 清除卡数据失败
        public static final int   NET_EM_FAILCODE_NO_RECORD = 16;       // 没有记录
        public static final int   NET_EM_FAILCODE_NOMORE_RECORD = 17;   // 查找到最后，没有更多记录
        public static final int   NET_EM_FAILCODE_RECORD_ALREADY_EXISTS = 18; // 下发卡或信息时，数据重复
        public static final int   NET_EM_FAILCODE_MAX_FP_PERUSER = 19;  // 超过个人最大信息记录数
        public static final int   NET_EM_FAILCODE_MAX_CARD_PERUSER = 20; // 超过个人最大卡片记录数
        public static final int   NET_EM_FAILCODE_EXCEED_MAX_PHOTOSIZE = 21; // 超出最大照片大小
        public static final int   NET_EM_FAILCODE_INVALID_USERID = 22;  // 用户ID无效（未找到客户）
        public static final int   NET_EM_FAILCODE_EXTRACTFEATURE_FAIL = 23; // 提取人脸特征失败
        public static final int   NET_EM_FAILCODE_PHOTO_EXIST = 24;     // 人脸照片已存在
        public static final int   NET_EM_FAILCODE_PHOTO_OVERFLOW = 25;  // 超出最大人脸照片数
        public static final int   NET_EM_FAILCODE_INVALID_PHOTO_FORMAT = 26; // 照片格式无效
        public static final int   NET_EM_FAILCODE_EXCEED_ADMINISTRATOR_LIMIT = 27; // 超出管理员人数限制
        public static final int   NET_EM_FAILECODE_FACE_FEATURE_ALREADY_EXIST = 28; // 人脸特征已存在
        public static final int   NET_EM_FAILECODE_FINGERPRINT_EXIST = 29; // 信息已存在
        public static final int   NET_EM_FAILECODE_CITIZENID_EXIST = 30; // 证件号已存在
        public static final int   NET_EM_FAILECODE_NORMAL_USER_NOTSUPPORT = 31; // 不支持普通用户下发
        public static final int   NET_EM_FAILCODE_NO_FACE_DETECTED = 32; // 图片中检测到0个人脸目标
        public static final int   NET_EM_FAILCODE_MULTI_FACE_DETECTED = 33; // 图片中检测到多个人脸，无法返回特征
        public static final int   NET_EM_FAILCODE_PICTURE_DECODING_ERROR = 34; // 图片解码错误
        public static final int   NET_EM_FAILCODE_LOW_PICTURE_QUALITY = 35; // 图片质量太低
        public static final int   NET_EM_FAILCODE_NOT_RECOMMENDED = 36; // 结果不推荐使用，比如：对外国人，特征提取成功，但算法支持不好，容易造成误识别
        public static final int   NET_EM_FAILCODE_FACE_ANGLE_OVER_THRESHOLDS = 37; // 人脸角度超过配置阈值
        public static final int   NET_EM_FAILCODE_FACE_RADIO_EXCEEDS_RANGE = 38; // 人脸占比超出范围，算法建议占比:不要超过2/3;不要小于1/3
        public static final int   NET_EM_FAILCODE_FACE_OVER_EXPOSED = 39; // 人脸过爆
        public static final int   NET_EM_FAILCODE_FACE_UNDER_EXPOSED = 40; // 人脸欠爆
        public static final int   NET_EM_FAILCODE_BRIGHTNESS_IMBALANCE = 41; // 人脸亮度不均衡 ,用于判断阴阳脸
        public static final int   NET_EM_FAILCODE_FACE_LOWER_CONFIDENCE = 42; // 人脸的置信度低
        public static final int   NET_EM_FAILCODE_FACE_LOW_ALIGN = 43;  // 人脸对齐分数低
        public static final int   NET_EM_FAILCODE_FRAGMENTARY_FACE_DETECTED = 44; // 人脸存在遮挡、残缺不全
        public static final int   NET_EM_FAILCODE_PUPIL_DISTANCE_NOT_ENOUGH = 45; // 人脸瞳距小于阈值
        public static final int   NET_EM_FAILCODE_FACE_DATA_DOWNLOAD_FAILED = 46; // 人脸数据下载失败
        public static final int   NET_EM_FAILCODE_FACE_FFE_FAILED = 47; // 人脸可检测，但特征值提取失败（算法场景）
        public static final int   NET_EM_FAILCODE_PHOTO_FEATURE_FAILED_FOR_FA = 48; // 人脸照片因口罩，帽子，墨镜等人脸属性不符合而提取特征值错误
        public static final int   NET_EM_FAILCODE_FACE_DATA_PHOTO_INCOMPLETE = 49; // 人脸照片不完整
        public static final int   NET_EM_FAILCODE_DATABASE_ERROR_INSERT_OVERFLOW = 50; ///// 	数据库插入越上限
        public static final int   NET_EM_CARD_NOT_EXIST = 51;           ///// 卡号不存在
        public static final int   NET_EM_FAILCODE_USER_EXIST = 52;      ///// User已存在
        public static final int   NET_EM_FAILCODE_CARD_NUM_EXIST = 53;  ///// 卡号已存在
        public static final int   NET_EM_FAILCODE_FINGERPRINT_DOWNLOAD_FAIL = 54; ///// 信息通过URL下载方式下载失败
        public static final int   NET_EM_FAILCODE_ACCOUNT_IN_USE = 55;  ///// 账户登录中
        public static final int   NET_EM_FAILCODE_IRIS_INFO_NOT_EXISTED = 56; ///// 更新用户眼睛信息时,用户不存在眼睛信息
        public static final int   NET_EM_FAILCODE_INVALID_IRIS_DATA = 57; ///// 下发的眼睛信息数据格式、特征值大小错误
        public static final int   NET_EM_FAILCODE_IRIS_ALREADY_EXIST = 58; ///// 眼睛信息已存在
        public static final int   NET_EM_FAILCODE_ERASE_IRIS_FAILED = 59; ///// 眼睛信息信息删除失败
        public static final int   NET_EM_FAILCODE_EXCEED_MAX_IRIS_GROUP_COUNT_PER_USER = 60; ///// 超出个人所支持的眼睛信息组数量，两个眼睛(左右眼)为一组
        public static final int   NET_EM_FAILCODE_EXCEED_MAX_IRIS_COUNT_PER_GROUP = 61; ///// 超出个人单组所能记录的最大数量
    }

    /*************************************************************************************************
     * 							门禁卡信息操作：增、删、改、查、清空
     ************************************************************************************************/
    // 卡片信息操作类型
    public static class NET_EM_ACCESS_CTL_CARD_SERVICE extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_CTL_CARD_SERVICE_INSERT = 0; // 添加, pstInParam = NET_IN_ACCESS_CARD_SERVICE_INSERT , pstOutParam = NET_OUT_ACCESS_CARD_SERVICE_INSERT
        public static final int   NET_EM_ACCESS_CTL_CARD_SERVICE_GET = 1; // 获取, pstInParam = NET_IN_ACCESS_CARD_SERVICE_GET , pstOutParam = NET_OUT_ACCESS_CARD_SERVICE_GET
        public static final int   NET_EM_ACCESS_CTL_CARD_SERVICE_UPDATE = 2; // 更新, pstInParam = NET_IN_ACCESS_CARD_SERVICE_UPDATE , pstOutParam = NET_OUT_ACCESS_CARD_SERVICE_UPDATE
        public static final int   NET_EM_ACCESS_CTL_CARD_SERVICE_REMOVE = 3; // 删除, pstInParam = NET_IN_ACCESS_CARD_SERVICE_REMOVE , pstOutParam = NET_OUT_ACCESS_CARD_SERVICE_REMOVE
        public static final int   NET_EM_ACCESS_CTL_CARD_SERVICE_CLEAR = 4; // 清空, pstInParam = NET_IN_ACCESS_CARD_SERVICE_CLEAR , pstOutParam = NET_OUT_ACCESS_CARD_SERVICE_CLEAR
    }

    /**
     * @author 260611
     * @description 卡片信息
     * @origin autoTool
     * @date 2023/09/18 10:23:47
     */
    public class NET_ACCESS_CARD_INFO extends SdkStructure {
        /**
         * / 卡号
         */
        public byte[]           szCardNo = new byte[32];
        /**
         * / 用户ID
         */
        public byte[]           szUserID = new byte[32];
        /**
         * / 卡类型,只支持一般卡、胁迫卡和母卡 {@link NET_ACCESSCTLCARD_TYPE}
         */
        public int              emType;
        /**
         * / 动态校验码
         */
        public byte[]           szDynamicCheckCode = new byte[16];
        /**
         * / 信息更新时间,UTC时间
         */
        public org.springblade.modules.iot.dahua.lib.structure.NET_TIME stuUpdateTime = new org.springblade.modules.iot.dahua.lib.structure.NET_TIME();
        /**
         * / 用户ID扩展，当前只支持32位有效值下发
         */
        public byte[]           szUserIDEx = new byte[128];
        /**
         * / szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段
         */
        public int              bUserIDEx;
        /**
         * / 保留字节
         */
        public byte[]           byReserved = new byte[3940];

        public NET_ACCESS_CARD_INFO() {
        }
    }

    // 新增卡片信息入参
    public static class NET_IN_ACCESS_CARD_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nInfoNum;                             // 用户信息数量
        public Pointer          pCardInfo;                            // 卡片信息,对应数组NET_ACCESS_CARD_INFO,用户分配释放内存,大小为sizeof(NET_ACCESS_CARD_INFO)*nInfoNum

        public NET_IN_ACCESS_CARD_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 新增卡片信息出参
    public static class NET_OUT_ACCESS_CARD_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回的用户信息数量,不小于NET_IN_ACCESS_CARD_SERVICE_INSERT中nInfoNum
        public Pointer          pFailCode;                            // 对应  FAIL_CODE, 用户分配释放内存,插入失败时,对应插入的每一项的结果,返回个数同NET_IN_ACCESS_CARD_SERVICE_INSERT中nInfoNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_CARD_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 获取卡片信息入参
    public static class NET_IN_ACCESS_CARD_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nCardNum;                             // 查询的数量
        public CARDNO[]         szCardNos = (CARDNO[])new CARDNO().toArray(100); // 卡号列表

        public NET_IN_ACCESS_CARD_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    public static class CARDNO extends SdkStructure
    {
        public byte[]           szCardNo = new byte[32];              // 卡号
    }

    // 获取卡片信息出参
    public static class NET_OUT_ACCESS_CARD_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 查询返回的最大数量
        public Pointer          pCardInfo;                            // 卡片信息,对应数组NET_ACCESS_CARD_INFO,内存由用户申请释放，申请大小不小于nCardNum*sizeof(NET_ACCESS_CARD_INFO);                                                                           // 返回个数同NET_IN_ACCESS_CARD_SERVICE_GET中nCardNum
        public Pointer          pFailCode;                            // 对应FAIL_CODE, 查询失败时，对应查询的每一项的结果,返回个数同NET_IN_ACCESS_CARD_SERVICE_GET中nCardNum

        public NET_OUT_ACCESS_CARD_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 更新卡片信息入参
    public static class NET_IN_ACCESS_CARD_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nInfoNum;                             // 用户信息数量
        public Pointer          pCardInfo;                            // 卡片信息, 对应数组NET_ACCESS_CARD_INFO,用户分配释放内存,大小为sizeof(NET_ACCESS_CARD_INFO)*nInfoNum

        public NET_IN_ACCESS_CARD_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 更新卡片信息出参
    public static class NET_OUT_ACCESS_CARD_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回的用户信息数量,不小于NET_IN_ACCESS_CARD_SERVICE_UPDATE中nInfoNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配释放内存,插入失败时，对应插入的每一项的结果,返回个数同NET_IN_ACCESS_CARD_SERVICE_UPDATE中nInfoNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_CARD_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 删除指定卡号信息入参
    public static class NET_IN_ACCESS_CARD_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nCardNum;                             // 删除的数量
        public CARDNO[]         szCardNos = (CARDNO[])new CARDNO().toArray(100); // 卡号列表

        public NET_IN_ACCESS_CARD_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除指定卡号信息出参
    public static class NET_OUT_ACCESS_CARD_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回信息数量,不小于 NET_IN_ACCESS_CARD_SERVICE_REMOVE中nCardNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配释放内存,插入失败时,对应删除的每一项的结果,返回个数同NET_IN_ACCESS_CARD_SERVICE_REMOVE中nCardNum
        public byte[]           byReserved = new byte[4];

        public NET_OUT_ACCESS_CARD_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除所有卡片信息入参
    public static class NET_IN_ACCESS_CARD_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_ACCESS_CARD_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 删除所有卡片信息出参
    public static class NET_OUT_ACCESS_CARD_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_ACCESS_CARD_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 产品定义
    public static class NET_PRODUCTION_DEFNITION extends SdkStructure
    {
        public int              dwSize;
        public int              nVideoInChannel;                      // 视频输入通道数
        public int              nVideoOutChannel;                     // 视频输出通道数
        public int              nRemoteDecChannel;                    // 远程解码通道数
        public byte[]           szDevType = new byte[NET_DEV_TYPE_LEN]; // 设备类型
        public byte[]           szVendor = new byte[NET_MAX_NAME_LEN]; // OEM客户
        public int              nOEMVersion;                          // OEM版本
        public int              nMajorVerion;                         // 主版本号
        public int              nMinorVersion;                        // 次版本号
        public int              nRevision;                            // 修订版本
        public byte[]           szWebVerion = new byte[NET_MAX_NAME_LEN]; // Web版本
        public byte[]           szDefLanguage = new byte[NET_MAX_NAME_LEN]; // 默认语言
        public NET_TIME         stuBuildDateTime;                     // 发布时间, 精确到秒
        public int              nAudioInChannel;                      // 音频输入通道数
        public int              nAudioOutChannel;                     // 音频输出通道数
        public int              bGeneralRecord;                       // 是否支持定时存储
        public int              bLocalStore;                          // 是否支持本地存储
        public int              bRemoteStore;                         // 是否支持网络存储
        public int              bLocalurgentStore;                    // 是否支持紧急存储到本地
        public int              bRealtimeCompress;                    // 是否支持实时压缩存储
        public int              dwVideoStandards;                     // 支持的视频制式列表, bit0-PAL, bit1-NTSC
        public int              nDefVideoStandard;                    // 默认视频制式, 0-PAL, 1-NTSC
        public int              nMaxExtraStream;                      // 最大辅码流路数
        public int              nRemoteRecordChannel;                 // 远程录像通道数
        public int              nRemoteSnapChannel;                   // 远程抓图通道数
        public int              nRemoteVideoAnalyseChannel;           // 远程视频分析通道数
        public int              nRemoteTransmitChannel;               // 远程实时流转发最大通道数
        public int              nRemoteTransmitFileChannel;           // 远程文件流竹筏通道通道数
        public int              nStreamTransmitChannel;               // 最大网络传输通道总数
        public int              nStreamReadChannel;                   // 最大读文件流通道总数
        public int              nMaxStreamSendBitrate;                // 最大码流网络发送能力, kbps
        public int              nMaxStreamRecvBitrate;                // 最大码流网络接口能力, kbps
        public int              bCompressOldFile;                     // 是否压缩旧文件, 去除P帧, 保留I帧
        public int              bRaid;                                // 是否支持RAID
        public int              nMaxPreRecordTime;                    // 最大预录时间, s
        public int              bPtzAlarm;                            // 是否支持云台报警
        public int              bPtz;                                 // 是否支持云台
        public int              bATM;                                 // 是否显示ATM相关功能
        public int              b3G;                                  // 是否支持3G模块
        public int              bNumericKey;                          // 是否带数字键
        public int              bShiftKey;                            // 是否带Shift键
        public int              bCorrectKeyMap;                       // 数字字母映射表是否正确
        public int              bNewATM;                              // 新的二代ATM前面板
        public int              bDecoder;                             // 是否是解码器
        public DEV_DECODER_INFO stuDecoderInfo;                       // 解码器信息, bDecoder=true时有效
        public int              nVideoOutputCompositeChannels;        // 融合屏输出通道上限
        public int              bSupportedWPS;                        // 是否支持WPS功能
        public int              nVGAVideoOutputChannels;              // VGA视频输出通道数
        public int              nTVVideoOutputChannels;               // TV视频输出通道数
        public int              nMaxRemoteInputChannels;              // 最大远程通道数
        public int              nMaxMatrixInputChannels;              // 最大矩阵通道数
        public int              nMaxRoadWays;                         // 智能交通最大车道数 1~6
        public int              nMaxParkingSpaceScreen;               // 和相机对接最多支持的区域屏个数 0~20
        public int              nPtzHorizontalAngleMin;               // 云台水平最小角度, [0-360]
        public int              nPtzHorizontalAngleMax;               // 云台水平最大角度, [0-360]
        public int              nPtzVerticalAngleMin;                 // 云台垂直最小角度, [-90,90]
        public int              nPtzVerticalAngleMax;                 // 云台垂直最大角度, [-90,90]
        public int              bPtzFunctionMenu;                     // 是否支持云台功能菜单
        public int              bLightingControl;                     // 是否支持灯光控制
        public int              dwLightingControlMode;                // 手动灯光控制模式, 按位, 见NET_LIGHTING_CTRL_ON_OFF
        public int              nNearLightNumber;                     // 近光灯组数量, 0表示不支持
        public int              nFarLightNumber;                      // 远光灯组数量, 0表示不支持
        public int              bFocus;                               // 是否支持控制聚焦
        public int              bIris;                                // 是否支持控制光圈
        public byte[]           szPtzProtocolList = new byte[NET_COMMON_STRING_1024]; // 云台支持的协议列表, 可以是多个, 每个用';'分隔
        public int              bRainBrushControl;                    // 是否支持雨刷控制
        public int              nBrushNumber;                         // 雨刷数量, 为0时表示不支持
        public int[]            nLowerMatrixInputChannels = new int[NET_MAX_LOWER_MITRIX_NUM]; // 下位矩阵视频输入通道, 下标对应矩阵编号
        public int[]            nLowerMatrixOutputChannels = new int[NET_MAX_LOWER_MITRIX_NUM]; // 下位矩阵视频输出通道, 下标对应矩阵编号
        public int              bSupportVideoAnalyse;                 // 是否支持智能分析
        public int              bSupportIntelliTracker;               // 是否支持智能跟踪
        public int              nSupportBreaking;                     //设备支持的违章类型掩码(按位获取)
        //0-闯红灯 1-压线 2-逆行 3-欠速 4-超速 5-有车占道 6-黄牌占道 7-违章行驶（左转、右转、调头）
        //8-违章进入待行区 9-违章停车 10-交通拥堵 11-不按车道行驶 12-违章变道 13-压黄线 14-路肩行驶
        //15-手动抓拍 16-违章滞留 17-斑马线行人优先 18-流量过大 19-流量过小 20-违章占道 21-违章倒车
        //22-压停止线 23-闯黄灯 24-黄网格线停车 25-车位有车 26-车位无车 27-车位有车压线 28-受限车牌
        //29-禁行 30-不系安全带 31-驾驶员抽烟
        public int              nSupportBreaking1;                    //0-驾驶员打电话 1-行人闯红灯 2-车辆拥堵禁入 3-未按规定依次交替通行
        public NET_PD_VIDEOANALYSE stuVideoAnalyse;                   //智能分析
        public int              bTalkTransfer;                        //是否支持转发对讲功能
        public int              bCameraAttribute;                     // 是否支持球机摄像头属性页面
        public int              bPTZFunctionViaApp;                   // 是否支持由应用主控的云台功能逻辑
        public int              bAudioProperties;                     // 喇叭是否支持语音播报
        public int              bIsCameraIDOsd;                       // 是否支持摄像机编号叠加
        public int              bIsPlaceOsd;                          // 是否支持地点信息叠加
        public int              nMaxGeographyTitleLine;               // 地理位置叠加最大支持行数
        public int              emAudioChannel;                       // 设备声道支持类型,详见EM_AUDIO_CHANNEL_TYPE
        public byte[]           szVendorAbbr = new byte[NET_COMMON_STRING_32]; // 厂商缩写
        public byte[]           szTypeVersion = new byte[NET_COMMON_STRING_32]; // 软件发布类型
        public int              bIsVideoNexus;                        // 是否是视讯互联大基线
        public int              emWlanScanAndConfig;                  // WIFI扫描及配置支持的版本类型,EM_WLAN_SCAN_AND_CONFIG_TYPE
        public int              bSupportLensMasking;                  // IPC是否支持镜头调节到负角度，进行结构遮挡
        public int              bIoTGate;                             //数视融合一体机设备是否具备网关能力，无该字段或该字段值为false时表示不具备网关能力；true表示设备具备网关能力
        public byte[]           szSupportBoardType = new byte[64];    //支持的控制板类型
        public byte[]           szVersion = new byte[16];             //版本：用于判断使用多通道配置(MultiFovCalibration)还是老配置(FovCalibration)，V2代表使用MultiFovCalibration多通道配置。如果没有该能力，默认使用FovCalibration配置,V2代表使用MultiFovCalibration多通道配置。如果没有该能力，默认使用FovCalibration配置
        public int              bSupportRemoteVideoAnalyse;           //是否支持前智能接入能力
        public int              nVideoAnalyseSupportedSceneNum;       //支持的智能大类列表数量
        public NET_PD_REMOTE_VIDEO_ANALYSE stuRemoteVideoAnalyse = new NET_PD_REMOTE_VIDEO_ANALYSE(); //前智能接入能力信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_PD_REMOTE_VIDEO_ANALYSE}
        public BYTE_ARRAY_16[]  szVideoAnalyseSupportedScene = new BYTE_ARRAY_16[32]; //智能分析支持的智能大类列表
        public NET_PD_SUPPORTED_RULES_INFO stuVideoAnalyseSupportedRules = new NET_PD_SUPPORTED_RULES_INFO(); //智能分析支持大类业务内容,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_PD_SUPPORTED_RULES_INFO}
        public int              bSupportSyncRemoteTrafficList;        //是否支持同步远程禁止/允许名单

        public NET_PRODUCTION_DEFNITION(){
            this.dwSize =  this.size();
        }
    }

    // 解码器信息
    public static class DEV_DECODER_INFO extends SdkStructure
    {
        public byte[]           szDecType = new byte[64];             // 类型
        public int              nMonitorNum;                          // TV个数
        public int              nEncoderNum;                          // 解码通道个数
        public byte[]           szSplitMode = new byte[16];           // 支持的TV画面分割数,以数组形式表示,0为结尾
        public byte[]           bMonitorEnable = new byte[16];        // 各TV使能
        public byte             bTVTipDisplay;                        // 指示是否支持TV提示信息叠加设置, 0:不支持 1:支持.
        public byte[]           reserved1 = new byte[3];
        public byte[]           byLayoutEnable = new byte[48];        // 各解码通道显示叠加信息使能
        public int[]            dwLayoutEnMask = new int[2];          // 各解码通道显示叠加信息使能掩码,从低位到高位支持64个通道,其中dwLayoutEnMask[0]是低32位
        public byte[]           reserved = new byte[4];
    }

    // 智能分析
    public static class NET_PD_VIDEOANALYSE extends SdkStructure
    {
        public int              bSupport;                             // 是否支持智能分析
        public NET_COMMON_STRING_64[] szSupportScenes = (NET_COMMON_STRING_64[])new NET_COMMON_STRING_64().toArray(NET_VIDEOANALYSE_SCENES); // 支持的场景
        public NET_COMMON_STRING_64[] SupportRules = (NET_COMMON_STRING_64[])new NET_COMMON_STRING_64().toArray(NET_VIDEOANALYSE_RULES); ;       // 支持的规则
    }

    public static class NET_COMMON_STRING_64 extends SdkStructure
    {
        public byte[]           szCommon = new byte[NET_COMMON_STRING_64]; // 通用64位字符串
    }

    /*************************************************************************************************
     * 							门禁人脸信息操作：增、删、改、查、清空
     ************************************************************************************************/
    // 人脸信息操作类型
    public static class NET_EM_ACCESS_CTL_FACE_SERVICE extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_CTL_FACE_SERVICE_INSERT = 0; // 添加, pInbuf = NET_IN_ACCESS_FACE_SERVICE_INSERT , pOutBuf = NET_OUT_ACCESS_FACE_SERVICE_INSERT
        public static final int   NET_EM_ACCESS_CTL_FACE_SERVICE_GET = 1; // 获取, pInbuf = NET_IN_ACCESS_FACE_SERVICE_GET , pOutBuf = NET_OUT_ACCESS_FACE_SERVICE_GET
        public static final int   NET_EM_ACCESS_CTL_FACE_SERVICE_UPDATE = 2; // 更新, pInbuf = NET_IN_ACCESS_FACE_SERVICE_UPDATE , pOutBuf = NET_OUT_ACCESS_FACE_SERVICE_UPDATE
        public static final int   NET_EM_ACCESS_CTL_FACE_SERVICE_REMOVE = 3; // 删除, pInbuf = NET_IN_ACCESS_FACE_SERVICE_REMOVE , pOutBuf = NET_OUT_ACCESS_FACE_SERVICE_REMOVE
        public static final int   NET_EM_ACCESS_CTL_FACE_SERVICE_CLEAR = 4; // 清空, pInbuf = NET_IN_ACCESS_FACE_SERVICE_CLEAR , pOutBuf = NET_OUT_ACCESS_FACE_SERVICE_CLEAR
    }

    // 人脸信息
    public static class NET_ACCESS_FACE_INFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[32];              // 用户ID
        public int              nFaceData;                            // 人脸模板数据个数,最大20
        public FACEDATA[]       szFaceDatas = new FACEDATA[20];       // 人脸模板数据
        public int[]            nFaceDataLen = new int[20];           // 人脸模版数据大小
        public int              nFacePhoto;                           // 人脸照片个数,不超过5个
        public int[]            nInFacePhotoLen = new int[5];         // 用户申请的每张图片的大小
        public int[]            nOutFacePhotoLen = new int[5];        // 每张图片实际的大小
        public FACEPHOTO[]      pFacePhotos = new FACEPHOTO[5];       // 人脸照片数据数组 人脸照片数据,大小不超过200K
        public int              bFaceDataExEnable;                    // 是否使用扩展人脸模板数据
        public int[]            nMaxFaceDataLen = new int[20];        // 用户申请的扩展人脸模板数据大小
        public int[]            nRetFaceDataLen = new int[20];        // 实际人脸模板数据大小
        public  FACEDATAEX[]    pFaceDataEx = new FACEDATAEX[20];     // 人脸模板数据扩展字段 当bFaceDataExEnable有效时，建议使用扩展字段pFaceDataEx
        public    NET_TIME      stuUpdateTime = new NET_TIME();       // 人脸信息更新时间,UTC时间
        public byte[]           szUserIDEx = new byte[128];           // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段
        public  int             nEigenData;                           // 人脸特征值个数
        public int[]            nInEigenDataLen = new int[5];         // 用户申请的每个人脸特征值大小
        public int[]            nOutEigenDataLen = new int[5];        // 每个人脸特征值实际的大小
        public EIGENDATA[]      pEigenDatas = new EIGENDATA[5];       // 人脸特征值, 大小不超过2048
        public byte[]           byReserved = new byte[1600-POINTERSIZE*5]; // 保留字节

       public NET_ACCESS_FACE_INFO(){

           for(int i=0;i<szFaceDatas.length;i++){
               szFaceDatas[i]=new FACEDATA();
           }

           for(int i=0;i<pFacePhotos.length;i++){
               pFacePhotos[i]=new FACEPHOTO();
           }

           for(int i=0;i<pFaceDataEx.length;i++){
               pFaceDataEx[i]=new FACEDATAEX();
           }

           for(int i=0;i<pEigenDatas.length;i++){
               pEigenDatas[i]=new  EIGENDATA();
           }
       }
    }

    public static class EIGENDATA extends SdkStructure {
        public Pointer          pEigenData;                           // 人脸特征值, 大小不超过2048
    }

    public static class FACEDATA extends SdkStructure
    {
        public byte[]           szFaceData = new byte[2048];          // 人脸模板数据
    }

    public static class FACEDATAEX extends SdkStructure {
        public Pointer          pFaceDataEx;                          //人脸模板数据拓展,当bFaceDataExEnable为true有效
    }

    public static class FACEPHOTO extends SdkStructure
    {
        public Pointer          pFacePhoto;                           // 人脸照片数据,大小不超过200K
    }

    // 添加人脸记录信息输入参数(NET_EM_ACCESS_CTL_FACE_SERVICE_INSERT)
    public static class NET_IN_ACCESS_FACE_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFaceInfoNum;                         // 人脸信息数量
        public Pointer          pFaceInfo;                            // 人脸数据,用户自行分配数据, 对应数组NET_ACCESS_FACE_INFO

        public NET_IN_ACCESS_FACE_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 添加人脸记录信息输出参数(NET_EM_ACCESS_CTL_FACE_SERVICE_INSERT)
    public static class NET_OUT_ACCESS_FACE_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回数量,不小于 NET_IN_ACCESS_FACE_SERVICE_INSERT 中的nFaceInfoNum
        public Pointer          pFailCode;                            // 对应FAIL_CODE, 用户分配内存,添加失败时,对应插入的每一项的结果,返回个数同NET_IN_ACCESS_FACE_SERVICE_INSERT中的nFaceInfoNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_FACE_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 批量获取多用户多个人脸输入参数(NET_EM_ACCESS_CTL_FACE_SERVICE_GET)
    public static class NET_IN_ACCESS_FACE_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nUserNum;                             // 用户ID数量,最大100
        public USERID[]         szUserIDs = (USERID[])new USERID().toArray(100); // 用户ID
        public USERIDEX[]       szUserIDEx = (USERIDEX[])new USERIDEX().toArray(100); // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_FACE_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 批量获取多用户多个人脸输出参数(NET_EM_ACCESS_CTL_FACE_SERVICE_GET)
    public static class NET_OUT_ACCESS_FACE_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回数量,不小于 NET_IN_ACCESS_FACE_SERVICE_GET 中的 nUserNum
        public Pointer          pFaceInfo;                            // 人脸数据, 对应数组NET_ACCESS_FACE_INFO,用户分配内存,返回个数同NET_IN_ACCESS_FACE_SERVICE_GET中的nUserNum,只返人脸模版数据
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配内存,获取失败时,对应获取的每一项的结果,返回个数同NET_IN_ACCESS_FACE_SERVICE_GET中的nUserNum

        public NET_OUT_ACCESS_FACE_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 更新多用户多个人脸记录信息输入参数(NET_EM_ACCESS_CTL_FACE_SERVICE_UPDATE)
    public static class NET_IN_ACCESS_FACE_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFaceInfoNum;                         // 人脸信息数量
        public Pointer          pFaceInfo;                            // 人脸数据,用户分配内存, 对应数组 NET_ACCESS_FACE_INFO

        public NET_IN_ACCESS_FACE_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 更新多用户多个人脸记录信息输出参数(NET_EM_ACCESS_CTL_FACE_SERVICE_UPDATE)
    public static class NET_OUT_ACCESS_FACE_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回数量,不小于 NET_IN_ACCESS_FACE_SERVICE_UPDATE中的nFaceInfoNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配内存.更新失败时,对应更新的每一项的结果,返回个数同NET_IN_ACCESS_FACE_SERVICE_UPDATE中的nFaceInfoNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_FACE_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 删除多用户的多个人脸信息输入参数(NET_EM_ACCESS_CTL_FACE_SERVICE_REMOVE)
    public static class NET_IN_ACCESS_FACE_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nUserNum;                             // 用户ID数量,最大100
        public USERID[]         szUserIDs = (USERID[])new USERID().toArray(100); // 用户ID
        public USERIDEX[]       szUserIDEx = (USERIDEX[])new USERIDEX().toArray(100); // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_FACE_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除多用户的多个人脸信息输出参数(NET_EM_ACCESS_CTL_FACE_SERVICE_REMOVE)
    public static class NET_OUT_ACCESS_FACE_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回数量,不小于 NET_IN_ACCESS_FACE_SERVICE_REMOVE中的nUserNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配内存.删除失败时,对应删除的每一项的结果,返回个数同NET_IN_ACCESS_FACE_SERVICE_REMOVE中的nUserNum

        public NET_OUT_ACCESS_FACE_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 清空所有人脸记录信息输入参数(NET_EM_ACCESS_CTL_FACE_SERVICE_CLEAR)
    public static class NET_IN_ACCESS_FACE_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_ACCESS_FACE_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 清空所有人脸记录信息输出参数(NET_EM_ACCESS_CTL_FACE_SERVICE_CLEAR)
    public static class NET_OUT_ACCESS_FACE_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_ACCESS_FACE_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    /*************************************************************************************************
     * 							门禁信息信息操作：增、删、改、查、清空
     ************************************************************************************************/
    // 信息信息操作类型
    public static class NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE extends SdkStructure
    {
        public static final int   NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE_INSERT = 0; // 添加, pInbuf = NET_IN_ACCESS_FINGERPRINT_SERVICE_INSERT , pOutBuf = NET_OUT_ACCESS_FINGERPRINT_SERVICE_INSERT
        public static final int   NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE_GET = 1; // 获取, pInbuf = NET_IN_ACCESS_FINGERPRINT_SERVICE_GET , pOutBuf = NET_OUT_ACCESS_FINGERPRINT_SERVICE_GET
        public static final int   NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE_UPDATE = 2; // 更新, pInbuf = NET_IN_ACCESS_FINGERPRINT_SERVICE_UPDATE , pOutBuf = NET_OUT_ACCESS_FINGERPRINT_SERVICE_UPDATE
        public static final int   NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE_REMOVE = 3; // 删除, pInbuf = NET_IN_ACCESS_FINGERPRINT_SERVICE_REMOVE , pOutBuf = NET_OUT_ACCESS_FINGERPRINT_SERVICE_REMOVE
        public static final int   NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE_CLEAR = 4; // 清空, pInbuf = NET_IN_ACCESS_FINGERPRINT_SERVICE_CLEAR , pOutBuf = NET_OUT_ACCESS_FINGERPRINT_SERVICE_CLEAR
    }

    // 信息信息
    public static class NET_ACCESS_FINGERPRINT_INFO extends SdkStructure
    {
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public int              nPacketLen;                           // 单个信息数据包长度
        public int              nPacketNum;                           // 信息数据包个数
        public Pointer          szFingerPrintInfo;                    // 信息数据(数据总长度即nPacketLen*nPacketNum),用户分配释放内存
        public int              nDuressIndex;                         // 胁迫信息序号,取值范围[1,nPacketNum] 非法取值的话，该字段无效
        public byte[]           byReserved = new byte[4096];          // 保留字节
    }

    // 插入信息信息入参
    public static class NET_IN_ACCESS_FINGERPRINT_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFpNum;                               // 信息信息的数量
        public Pointer          pFingerPrintInfo;                     // 信息信息,用户分配释放内存, 对应 NET_ACCESS_FINGERPRINT_INFO

        public NET_IN_ACCESS_FINGERPRINT_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 插入信息信息出参
    public static class NET_OUT_ACCESS_FINGERPRINT_SERVICE_INSERT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 返回信息数量,不小于NET_IN_ACCESS_FINGERPRINT_SERVICE_INSERT 中 nFpNum
        public Pointer          pFailCode;                            // 对应FAIL_CODE, 用户分配释放内存,插入失败时，对应插入的每一项的结果,返回个数同NET_IN_ACCESS_FINGERPRINT_SERVICE_INSERT 中nFpNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_FINGERPRINT_SERVICE_INSERT() {
            this.dwSize = this.size();
        }
    }

    // 更新信息信息入参
    public static class NET_IN_ACCESS_FINGERPRINT_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFpNum;                               // 信息信息的数量
        public Pointer          pFingerPrintInfo;                     // 信息信息,用户分配释放内存, 对应数组 NET_ACCESS_FINGERPRINT_INFO

        public NET_IN_ACCESS_FINGERPRINT_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 更新信息信息出参
    public static class NET_OUT_ACCESS_FINGERPRINT_SERVICE_UPDATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回信息数量,不小于 NET_IN_ACCESS_FINGERPRINT_SERVICE_UPDATE中nFpNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配释放内存,插入失败时，对应插入的每一项的结果,返回个数同NET_IN_ACCESS_FINGERPRINT_SERVICE_UPDATE中nFpNum
        public NET_ERROR_DETAIL stuDetail = new NET_ERROR_DETAIL();   //错误详情,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ERROR_DETAIL}

        public NET_OUT_ACCESS_FINGERPRINT_SERVICE_UPDATE() {
            this.dwSize = this.size();
        }
    }

    // 获取信息信息入参
    public static class NET_IN_ACCESS_FINGERPRINT_SERVICE_GET extends SdkStructure
}
