package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 2 部分
 */
public interface NetSDKMethods2 {
        public int              nFaceQuality;                         // 人脸抓拍质量分数
        public int              nFaceAlignScore;                      // 人脸对齐得分分数,范围 0~10000,-1为无效值
        public int              nFaceClarity;                         // 人脸清晰度分数,范围 0~10000,-1为无效值
        public double           dbTemperature;                        // 温度, bAnatomyTempDetect 为TRUE时有效
        public int              bAnatomyTempDetect;                   // 是否人体测温
        public int              emTemperatureUnit;                    // 温度单位, bAnatomyTempDetect 为TRUE时有效
        public int              bIsOverTemp;                          // 是否温度异常, bAnatomyTempDetect 为TRUE时有效
        public int              bIsUnderTemp;                         // 是否温度异常, bAnatomyTempDetect 为TRUE时有效
        public NET_FACE_ORIGINAL_SIZE stuOriginalSize;                // 算法人脸分析时的实际人脸图片尺寸, 宽高为0时无效
        public int              emGlass;                              /** 戴眼镜状态  {@link  EM_GLASS_STATE_TYPE} */
        public byte[]           bReserved = new byte[64];             // 保留字节,留待扩展.
    }

    //目标检测对应性别类型
    public static class EM_DEV_EVENT_FACEDETECT_SEX_TYPE extends SdkStructure
    {
        public static final int   EM_DEV_EVENT_FACEDETECT_SEX_TYPE_UNKNOWN = 0; //未知
        public static final int   EM_DEV_EVENT_FACEDETECT_SEX_TYPE_MAN = 1; //男性
        public static final int   EM_DEV_EVENT_FACEDETECT_SEX_TYPE_WOMAN = 2; //女性
    }

    //目标检测对应人脸特征类型
    public static class EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE extends SdkStructure
    {
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_UNKNOWN = 0; //未知
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_WEAR_GLASSES = 1; //戴眼镜
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SMILE = 2; //微笑
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_ANGER = 3; //愤怒
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SADNESS = 4; //悲伤
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_DISGUST = 5; //厌恶
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_FEAR = 6; //害怕
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SURPRISE = 7; //惊讶
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_NEUTRAL = 8; //正常
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_LAUGH = 9; //大笑
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_NOGLASSES = 10; // 没戴眼镜
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_HAPPY = 11; // 高兴
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_CONFUSED = 12; // 困惑
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SCREAM = 13; // 尖叫
        public static final int   EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_WEAR_SUNGLASSES = 14; // 戴太阳眼镜
    }

    // 眼睛状态
    public static class EM_EYE_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_EYE_STATE_UNKNOWN = 0;             // 未知
        public static final int   EM_EYE_STATE_NODISTI = 1;             // 未识别
        public static final int   EM_EYE_STATE_CLOSE = 2;               // 闭眼
        public static final int   EM_EYE_STATE_OPEN = 3;                // 睁眼
    }

    // 嘴巴状态
    public static class EM_MOUTH_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_MOUTH_STATE_UNKNOWN = 0;           // 未知
        public static final int   EM_MOUTH_STATE_NODISTI = 1;           // 未识别
        public static final int   EM_MOUTH_STATE_CLOSE = 2;             // 闭嘴
        public static final int   EM_MOUTH_STATE_OPEN = 3;              // 张嘴
    }

    // 口罩状态
    public static class EM_MASK_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_MASK_STATE_UNKNOWN = 0;            // 未知
        public static final int   EM_MASK_STATE_NODISTI = 1;            // 未识别
        public static final int   EM_MASK_STATE_NOMASK = 2;             // 没戴口罩
        public static final int   EM_MASK_STATE_WEAR = 3;               // 戴口罩
    }

    // 胡子状态
    public static class EM_BEARD_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_BEARD_STATE_UNKNOWN = 0;           // 未知
        public static final int   EM_BEARD_STATE__NODISTI = 1;          // 未识别
        public static final int   EM_BEARD_STATE_NOBEARD = 2;           // 没胡子
        public static final int   EM_BEARD_STATE_HAVEBEARD = 3;         // 有胡子
    }

    // 人员建模状态
    public static class EM_PERSON_FEATURE_STATE extends SdkStructure
    {
        public static final int   EM_PERSON_FEATURE_UNKNOWN = 0;        // 未知
        public static final int   EM_PERSON_FEATURE_FAIL = 1;           // 建模失败,可能是图片不符合要求,需要换图片
        public static final int   EM_PERSON_FEATURE_USEFUL = 2;         // 有可用的特征值
        public static final int   EM_PERSON_FEATURE_CALCULATING = 3;    // 正在计算特征值
        public static final int   EM_PERSON_FEATURE_UNUSEFUL = 4;       // 已建模，但算法升级导致数据不可用，需要重新建模
    }

    // 事件文件的文件标签类型
    public static class EM_EVENT_FILETAG extends SdkStructure
    {
        public static final int   NET_ATMBEFOREPASTE = 1;               //ATM贴条前
        public static final int   NET_ATMAFTERPASTE = 2;                //ATM贴条后
    }

    // 事件对应文件信息
    public static class NET_EVENT_FILE_INFO extends SdkStructure
    {
        public byte             bCount;                               // 当前文件所在文件组中的文件总数
        public byte             bIndex;                               // 当前文件在文件组中的文件编号(编号1开始)
        public byte             bFileTag;                             // 文件标签,具体说明见枚举类型 EM_EVENT_FILETAG
        public byte             bFileType;                            // 文件类型,0-普通1-合成2-抠图
        public NET_TIME_EX      stuFileTime;                          // 文件时间
        public int              nGroupId;                             // 同一组抓拍文件的唯一标识

        @Override
        public String toString() {
            return "事件对应文件信息,NET_EVENT_FILE_INFO{" +
                    "bCount=" + bCount +
                    ", bIndex=" + bIndex +
                    ", bFileTag=" + bFileTag +
                    ", bFileType=" + bFileType +
                    ", stuFileTime=" + stuFileTime.toStringTime() +
                    ", nGroupId=" + nGroupId +
                    '}';
        }
    }

    // 多目标检测信息
    public static class NET_FACE_INFO extends SdkStructure
    {
        public int              nObjectID;                            // 物体ID,每个ID表示一个唯一的物体
        public byte[]           szObjectType = new byte[128];         // 物体类型
        public int              nRelativeID;                          // Relative与另一张图片ID相同,表示这张人脸抠图是从大图中取出的
        public DH_RECT          BoundingBox;                          // 包围盒
        public NET_POINT        Center;                               // 物体型心
    }

    public static class EM_FACE_DETECT_STATUS extends SdkStructure
    {
        public static final int   EM_FACE_DETECT_UNKNOWN = 0;           // 未知
        public static final int   EM_FACE_DETECT_APPEAR = 1;            //出现
        public static final int   EM_FACE_DETECT_INPICTURE = 2;         //在画面中
        public static final int   EM_FACE_DETECT_EXIT = 3;              //离开
    }

    // 姿态角数据
    public static class NET_EULER_ANGLE extends SdkStructure
    {
        public int              nPitch;                               // 仰俯角
        public int              nYaw;                                 // 偏航角
        public int              nRoll;                                // 翻滚角
    }

    //事件类型EVENT_IVS_FACEDETECT(目标检测事件)对应的数据块描述信息
    public static class DEV_EVENT_FACEDETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           reserved = new byte[2];               // 保留字节
        public byte             byImageIndex;                         // 图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public byte[]           szSnapDevAddress = new byte[MAX_PATH]; // 抓拍当前人脸的设备地址,如：滨康路37号
        public int              nOccurrenceCount;                     // 事件触发累计次数, 类型为unsigned int
        public int              emSex;                                // 性别, 取值为EM_DEV_EVENT_FACEDETECT_SEX_TYPE中的值
        public int              nAge;                                 // 年龄,-1表示该字段数据无效
        public int              nFeatureValidNum;                     //人脸特征数组有效个数,与emFeature结合使用, 类型为unsigned int
        public int[]            emFeature = new int[NET_MAX_FACEDETECT_FEATURE_NUM]; // 人脸特征数组,与nFeatureValidNum, 取值为EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE中的值
        public int              nFacesNum;                            // 指示stuFaces有效数量
        public NET_FACE_INFO[]  stuFaces = (NET_FACE_INFO[])new NET_FACE_INFO().toArray(10); // 多张人脸时使用,此时没有Object
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte[]           szReserved1 = new byte[4];
        /**眼睛状态 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_EYE_STATE_TYPE}
        * */
        public int              emEye;
        /**嘴巴状态 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_MOUTH_STATE_TYPE}
         * */
        public int              emMouth;
        /**口罩状态 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_MASK_STATE_TYPE}
         * */
        public int              emMask;
        /**胡子状态 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_BEARD_STATE_TYPE}
         * */
        public int              emBeard;
        public int              nAttractive;                          // 魅力值, -1表示无效, 0未识别，识别时范围1-100,得分高魅力高
        public byte[]           szUID = new byte[NET_COMMON_STRING_32]; // 抓拍人员写入数据库的唯一标识符
        public byte[]           bReserved2 = new byte[4];             // 保留字节
        public NET_FEATURE_VECTOR stuFeatureVector;                   // 特征值信息
        public byte[]           szFeatureVersion = new byte[32];      // 特征值算法版本
        public int              emFaceDetectStatus;                   // 人脸在摄像机画面中的状态 ，参考EM_FACE_DETECT_STATUS
        public NET_EULER_ANGLE  stuFaceCaptureAngle;                  // 人脸在抓拍图片中的角度信息, nPitch:抬头低头的俯仰角, nYaw左右转头的偏航角, nRoll头在平面内左偏右偏的翻滚角
        // 角度值取值范围[-90,90], 三个角度值都为999表示此角度信息无效
        public int              nFaceQuality;                         // 人脸抓拍质量分数,范围 0~10000
        public double           dHumanSpeed;                          // 人的运动速度, km/h
        public int              nFaceAlignScore;                      // 人脸对齐得分分数,范围 0~10000,-1为无效值
        public int              nFaceClarity;                         // 人脸清晰度分数,范围 0~10000,-1为无效值
        public int              bHumanTemperature;                    // 人体温信息是否有效
        public NET_HUMAN_TEMPERATURE_INFO stuHumanTemperature = new NET_HUMAN_TEMPERATURE_INFO(); // 人体温信息, bHumanTemperature为TURE时有效
        public byte[]           szCameraID = new byte[64];            // 国标编码
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO(); // 对应图片的分辨率
        public NET_FACE_ORIGINAL_SIZE stuOriginalSize = new NET_FACE_ORIGINAL_SIZE(); // 算法人脸分析时的实际人脸尺寸. 宽高为0是无效
        public int              emGlass;                              // 戴眼镜状态,参考枚举EM_GLASS_STATE_TYPE
        public int              emHat;                                // 帽子状态，参考枚举EM_HAT_STYLE
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,指正对应结构体   NET_IMAGE_INFO_EX3的数组
        public int              nImageInfoNum;                        // 图片信息个数
        public Pointer          pstFeatureAlarm;                      //属性报警,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FEATURE_ALARM_INFO}
        public int              nFeatureAlarmNum;                     //属性报警个数
        public byte[]           szGMIDNumber = new byte[64];          //国芯安全模块ID
        public byte[]           szFaceKeyVersion = new byte[8];       //人像认证密钥版本
        public byte[]           szFaceDataHmac = new byte[256];       //人像数据认证码
        public Pointer          pstuFaceDetectInfo;                   //目标检测事件扩展信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FACE_DETECT_INFO_EX}
        public byte[]           bReserved = new byte[60-POINTERSIZE*3]; // 保留字节,留待扩展
    }

    // 事件类型EVENT_IVS_TRAFFICJAM(交通拥堵事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFICJAM_INFO extends SdkStructure {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐
         */
        public byte[]           bReserved1 = new byte[4];
        /**
         * 时间戳(单位是毫秒)
         */
        public double           PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX      UTC = new NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 对应车道号
         */
        public int              nLane;
        /**
         * 事件对应文件信息
         */
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO();
        /**
         * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
         */
        public byte             bEventAction;
        /**
         * 表示拥堵长度(总车道长度百分比）0-100
         */
        public byte             bJamLenght;
        /**
         * 保留字节
         */
        public byte             reserved;
        /**
         * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
         */
        public byte             byImageIndex;
        /**
         * 开始停车时间
         */
        public NET_TIME_EX      stuStartJamTime = new NET_TIME_EX();
        /**
         * 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束(bEventAction=2时此参数有效)
         */
        public int              nSequence;
        /**
         * 报警时间间隔,单位:秒。(此事件为连续性事件,在收到第一个此事件之后,若在超过间隔时间后未收到此事件的后续事件,则认为此事件异常结束了)
         */
        public int              nAlarmIntervalTime;
        /**
         * 抓图标志(按位),具体见NET_RESERVED_COMMON
         */
        public int              dwSnapFlagMask;
        /**
         * 对应图片的分辨率
         */
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO();
        /**
         * 表实际的拥堵长度,单位米
         */
        public int              nJamRealLength;
        /**
         * 扩展信息
         */
        public NET_EXTENSION_INFO stuExtensionInfo = new NET_EXTENSION_INFO();
        /**
         * 区域拥堵标志是否有效
         */
        public int              bJamRegionFlagValid;
        /**
         * 区域拥堵标志 0-车道拥堵 1-区域拥堵
         */
        public int              nJamRegionFlag;
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        /**
         * 保留字节,留待扩展.
         */
        public byte[]           bReserved = new byte[864-2*POINTERSIZE];
        /**
         * 交通车辆信息
         */
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
        /**
         * 公共信息
         */
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}

        public DEV_EVENT_TRAFFICJAM_INFO() {
        }                           // 公共信息
    }

    // 车辆行驶方向
    public static class NET_FLOWSTAT_DIRECTION extends SdkStructure
    {
        public static final int   DRIVING_DIR_UNKNOW = 0;               //兼容之前
        public static final int   DRIVING_DIR_APPROACH = 1;             //上行,即车辆离设备部署点越来越近
        public static final int   DRIVING_DIR_LEAVE = 2;                //下行,即车辆离设备部署点越来越远
    }

    //车辆流量统计车辆行驶方向信息
    public static class NET_TRAFFIC_FLOWSTAT_INFO_DIR extends SdkStructure
    {
        public int              emDrivingDir;                         //行驶方向 (参见NET_FLOWSTAT_DIRECTION)
        public byte[]           szUpGoing = new byte[FLOWSTAT_ADDR_NAME]; //上行地点
        public byte[]           szDownGoing = new byte[FLOWSTAT_ADDR_NAME]; //下行地点
        public byte[]           reserved = new byte[32];              //保留字节
    }

    public static class NET_TRAFFIC_JAM_STATUS extends SdkStructure
    {
        public static final int   JAM_STATUS_UNKNOW = 0;                //未知
        public static final int   JAM_STATUS_CLEAR = 1;                 //通畅
        public static final int   JAM_STATUS_JAMMED = 2;                //拥堵
    }

    public static class NET_TRAFFIC_FLOW_STATE extends SdkStructure
    {
        public int              nLane;                                // 车道号
        public int              dwState;                              // 状态值
        // 1- 流量过大
        // 2- 流量过大恢复
        // 3- 正常
        // 4- 流量过小
        // 5- 流量过小恢复
        public int              dwFlow;                               // 流量值, 单位: 辆
        public int              dwPeriod;                             // 流量值对应的统计时间
        public NET_TRAFFIC_FLOWSTAT_INFO_DIR stTrafficFlowDir;        // 车道方向信息
        public int              nVehicles;                            // 通过车辆总数
        public float            fAverageSpeed;                        // 平均车速,单位km/h
        public float            fAverageLength;                       // 平均车长,单位米
        public float            fTimeOccupyRatio;                     // 时间占有率,即单位时间内通过断面的车辆所用时间的总和占单位时间的比例
        public float            fSpaceOccupyRatio;                    // 空间占有率,即按百分率计量的车辆长度总和除以时间间隔内车辆平均行驶距离
        public float            fSpaceHeadway;                        // 车头间距,相邻车辆之间的距离,单位米/辆
        public float            fTimeHeadway;                         // 车头时距,单位秒/辆
        public float            fDensity;                             // 车辆密度,每公里的车辆数,单位辆/km
        public int              nOverSpeedVehicles;                   // 超速车辆数
        public int              nUnderSpeedVehicles;                  // 低速车辆数
        public int              nLargeVehicles;                       // 大车交通量(9米<车长<12米),辆/单位时间
        public int              nMediumVehicles;                      // 中型车交通量(6米<车长<9米),辆/单位时间
        public int              nSmallVehicles;                       // 小车交通量(4米<车长<6米),辆/单位时间,
        public int              nMotoVehicles;                        // 摩托交通量(微型车,车长<4米),辆/单位时间,
        public int              nLongVehicles;                        // 超长交通量(车长>=12米),辆/单位时间,
        public int              nVolume;                              // 交通量, 辆/单位时间, 某时间间隔通过车道、道路或其他通道上一点的车辆数,常以1小时计,
        public int              nFlowRate;                            // 流率小车当量,辆/小时, 车辆通过车道、道路某一断面或某一路段的当量小时流量
        public int              nBackOfQueue;                         // 排队长度,单位：米, 从信号交叉口停车线到上游排队车辆末端之间的距离
        public int              nTravelTime;                          // 旅行时间,单位：秒, 车辆通过某一条道路所用时间。包括所有停车延误
        public int              nDelay;                               // 延误,单位：秒,驾驶员、乘客或行人花费的额外的行程时间
        public byte[]           byDirection = new byte[MAX_DRIVING_DIR_NUM]; // 车道方向,详见NET_ROAD_DIRECTION
        public byte             byDirectionNum;                       // 车道行驶方向个数
        public byte[]           reserved1 = new byte[3];              // 字节对齐
        public int              emJamState;                           // 道路拥挤状况 (参见 NET_TRAFFIC_JAM_STATUS )
        //  按车辆类型统计交通量
        public int              nPassengerCarVehicles;                // 客车交通量(辆/单位时间)
        public int              nLargeTruckVehicles;                  // 大货车交通量(辆/单位时间)
        public int              nMidTruckVehicles;                    // 中货车交通量(辆/单位时间)
        public int              nSaloonCarVehicles;                   // 轿车交通量(辆/单位时间)
        public int              nMicrobusVehicles;                    // 面包车交通量(辆/单位时间)
        public int              nMicroTruckVehicles;                  // 小货车交通量(辆/单位时间)
        public int              nTricycleVehicles;                    // 三轮车交通量(辆/单位时间)
        public int              nMotorcycleVehicles;                  // 摩托车交通量(辆/单位时间)
        public int              nPasserbyVehicles;                    // 行人交通量(辆/单位时间)
        public int              emRank;                               // 道路等级, 参考  NET_TRAFFIC_ROAD_RANK
        public int              nState;                               // 流量状态
        // 1- 流量过大(拥堵)
        // 2- 流量过大恢复(略堵)
        // 3- 正常
        // 4- 流量过小(通畅)
        // 5- 流量过小恢复(良好)
        public int              bOccupyHeadCoil;                      // 车头虚拟线圈是否被占用 TURE表示占用，FALSE表示未占用
        public int              bOccupyTailCoil;                      // 车尾虚拟线圈是否被占用 TURE表示占用，FALSE表示未占用
        public int              bStatistics;                          // 流量数据是否有效 TURE表示有效，FALSE表示无效
        public int              nLeftVehicles;                        // 左转车辆总数,单位:分钟
        public int              nRightVehicles;                       // 右转车辆总数,单位:分钟
        public int              nStraightVehicles;                    // 直行车辆总数,单位:分钟
        public int              nUTurnVehicles;                       // 掉头车辆总数,单位:分钟
        public NET_POINT        stQueueEnd;                           // 每个车道的最后一辆车坐标,采用8192坐标系
        public double           dBackOfQueue;                         // 排队长度,单位：米, 从信号交叉口停车线到上游排队车辆末端之间的距离
        public int              dwPeriodByMili;                       // 流量值的毫秒时间,值不超过60000,和dwPeriod一起使用,流量值总时间:dwPeriod*60*1000+dwPeriodByMili(单位：毫秒)
        public int              nBusVehicles;                         // 公交车交通量(辆/单位时间)
        public int              nMPVVehicles;                         // MPV交通量(辆/单位时间)
        public int              nMidPassengerCarVehicles;             // 中客车交通量(辆/单位时间)
        public int              nMiniCarriageVehicles;                // 微型轿车交通量(辆/单位时间)
        public int              nOilTankTruckVehicles;                // 油罐车交通量(辆/单位时间)
        public int              nPickupVehicles;                      // 皮卡车交通量(辆/单位时间)
        public int              nSUVVehicles;                         // SUV交通量(辆/单位时间)
        public int              nSUVorMPVVehicles;                    // SUV或者MPV交通量(辆/单位时间)
        public int              nTankCarVehicles;                     // 槽罐车交通量(辆/单位时间)
        public int              nUnknownVehicles;                     // 未知车辆交通量(辆/单位时间)
        public int              emCustomFlowAttribute;                // 车道流量信息属性
        public int              nRoadFreeLength;                      // 道路空闲长度，例：如设定路段长度为100米，实际检测到排队长度为30米，那么道路空闲长度就为70米，单位：米
        public int              emOverflowState;                      // 溢出状态。例：如给当前路段设定允许排队长度阀值，实际排队长度超过阀值后就判定当前时刻该路段有溢出。
        public int              nQueueVehicleNum;                     // 排队车辆数，单位：辆
        public int              nSpaceOccupyRatioMultiCount;          // 空间占有率统计个数
        public NET_SPACE_OCCUPY_RATIO_MULTI[] stuSpaceOccupyRatioMulti = new NET_SPACE_OCCUPY_RATIO_MULTI[32]; // 空间占有率统计信息
        public int              nTotalStopNum;                        // 停车次数，周期内该车道内所有目标的总停车次数，单位:次
        public int              nTotalDelayTime;                      // 延误时间，周期内该车道内所有目标的总延误时间，单位：毫秒
        public int              nTotalNum;                            // 目标总数量，周期内进入该车道内所有目标总数量，单位 辆
        public float            fAverageStopNum;                      // 平均停车次数，周期内该车道所有目标的平均停车次数。单位：次
        public float            fAverageDelayTime;                    // 平均延误时间，周期内该车道所有目标的平均延误时间。单位：毫秒
        public float            fFlowSaturationRatio;                 // 流量饱和度，该车道内的流量饱和度
        public float            fMaxQueueLen;                         // 最大排队长度，周期内最大排队长度，单位:米
        public float            fQueueStartingPoint;                  // 排队开始位置，排队时队首距设备的位置，以设备方向为正，负数表示队首在设备后方，单位：米
        public float            fQueueFinishingPoint;                 // 排队结束位置，排队时队尾距设备的位置，以设备方向为正，负数表示队尾在设备后方，单位：米
        public byte[]           szSpaceOccupyNum = new byte[64];      // 空间占有目标个数，即区域车辆数，该车道内指定区域的车辆数目，支持车道内多区域统计，不同区域车辆数使用|符号分隔。
        public int              nPresetID;                            // 云台预置点,球机预置点必大于0
        public byte[]           reserved = new byte[88];              // 保留字节

        @Override
        public String toString() {
            return "NET_TRAFFIC_FLOW_STATE{" +
                    "nLane=" + nLane +
                    ", dwState=" + dwState +
                    ", dwFlow=" + dwFlow +
                    ", dwPeriod=" + dwPeriod +
                    ", stTrafficFlowDir=" + stTrafficFlowDir +
                    ", nVehicles=" + nVehicles +
                    ", fAverageSpeed=" + fAverageSpeed +
                    ", fAverageLength=" + fAverageLength +
                    ", fTimeOccupyRatio=" + fTimeOccupyRatio +
                    ", fSpaceOccupyRatio=" + fSpaceOccupyRatio +
                    ", fSpaceHeadway=" + fSpaceHeadway +
                    ", fTimeHeadway=" + fTimeHeadway +
                    ", fDensity=" + fDensity +
                    ", nOverSpeedVehicles=" + nOverSpeedVehicles +
                    ", nUnderSpeedVehicles=" + nUnderSpeedVehicles +
                    ", nLargeVehicles=" + nLargeVehicles +
                    ", nMediumVehicles=" + nMediumVehicles +
                    ", nSmallVehicles=" + nSmallVehicles +
                    ", nMotoVehicles=" + nMotoVehicles +
                    ", nLongVehicles=" + nLongVehicles +
                    ", nVolume=" + nVolume +
                    ", nFlowRate=" + nFlowRate +
                    ", nBackOfQueue=" + nBackOfQueue +
                    ", nTravelTime=" + nTravelTime +
                    ", nDelay=" + nDelay +
                    ", byDirection=" + Arrays.toString(byDirection) +
                    ", byDirectionNum=" + byDirectionNum +
                    ", reserved1=" + Arrays.toString(reserved1) +
                    ", emJamState=" + emJamState +
                    ", nPassengerCarVehicles=" + nPassengerCarVehicles +
                    ", nLargeTruckVehicles=" + nLargeTruckVehicles +
                    ", nMidTruckVehicles=" + nMidTruckVehicles +
                    ", nSaloonCarVehicles=" + nSaloonCarVehicles +
                    ", nMicrobusVehicles=" + nMicrobusVehicles +
                    ", nMicroTruckVehicles=" + nMicroTruckVehicles +
                    ", nTricycleVehicles=" + nTricycleVehicles +
                    ", nMotorcycleVehicles=" + nMotorcycleVehicles +
                    ", nPasserbyVehicles=" + nPasserbyVehicles +
                    ", emRank=" + emRank +
                    ", nState=" + nState +
                    ", bOccupyHeadCoil=" + bOccupyHeadCoil +
                    ", bOccupyTailCoil=" + bOccupyTailCoil +
                    ", bStatistics=" + bStatistics +
                    ", nLeftVehicles=" + nLeftVehicles +
                    ", nRightVehicles=" + nRightVehicles +
                    ", nStraightVehicles=" + nStraightVehicles +
                    ", nUTurnVehicles=" + nUTurnVehicles +
                    ", stQueueEnd=" + stQueueEnd +
                    ", dBackOfQueue=" + dBackOfQueue +
                    ", dwPeriodByMili=" + dwPeriodByMili +
                    ", nBusVehicles=" + nBusVehicles +
                    ", nMPVVehicles=" + nMPVVehicles +
                    ", nMidPassengerCarVehicles=" + nMidPassengerCarVehicles +
                    ", nMiniCarriageVehicles=" + nMiniCarriageVehicles +
                    ", nOilTankTruckVehicles=" + nOilTankTruckVehicles +
                    ", nPickupVehicles=" + nPickupVehicles +
                    ", nSUVVehicles=" + nSUVVehicles +
                    ", nSUVorMPVVehicles=" + nSUVorMPVVehicles +
                    ", nTankCarVehicles=" + nTankCarVehicles +
                    ", nUnknownVehicles=" + nUnknownVehicles +
                    ", emCustomFlowAttribute=" + emCustomFlowAttribute +
                    ", nRoadFreeLength=" + nRoadFreeLength +
                    ", emOverflowState=" + emOverflowState +
                    '}';
        }
    }

    //事件类型 EVENT_IVS_TRAFFIC_FLOWSTATE(交通流量事件)对应数据块描述信息
    public static class DEV_EVENT_TRAFFIC_FLOW_STATE extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nRuleID;                              // 规则编号,用于标示哪个规则触发的事件，缺省时默认为0
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nSequence;                            // 序号
        public int              nStateNum;                            // 流量状态数量
        public NET_TRAFFIC_FLOW_STATE[] stuStates = (NET_TRAFFIC_FLOW_STATE[])new NET_TRAFFIC_FLOW_STATE().toArray(NET_MAX_LANE_NUM); // 流量状态, 每个车道对应数组中一个元素
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nStopVehiclenum;                      // 静止车辆数，当前时刻检测范围内车速小于某个阀值的车辆数，单位：辆
        public int              nDetectionAreaVehicleNum;             // 车辆总数，当前时刻检测范围内检测到的所有车道内的车辆总数，单位：辆
        public byte[]           bReserved = new byte[884];            // 保留字节
    }

    // 图片分辨率
    public static class NET_RESOLUTION_INFO extends SdkStructure
    {
        public short            snWidth;                              //宽
        public short            snHight;                              //高

        @Override
        public String toString() {
            return "NET_RESOLUTION_INFO{" +
                    "宽=" + snWidth +
                    ",高=" + snHight +
                    '}';
        }
    }

    public static class EM_COMMON_SEAT_TYPE extends SdkStructure
    {
        public static final int   COMMON_SEAT_TYPE_UNKNOWN = 0;         //未识别
        public static final int   COMMON_SEAT_TYPE_MAIN = 1;            //主驾驶
        public static final int   COMMON_SEAT_TYPE_SLAVE = 2;           //副驾驶
    }

    // 违规状态
    public static class EVENT_COMM_STATUS extends SdkStructure
    {
        public byte             bySmoking;                            //是否抽烟
        public byte             byCalling;                            //是否打电话
        public byte[]           szReserved = new byte[14];            //预留字段
    }

    public static class NET_SAFEBELT_STATE extends SdkStructure
    {
        public static final int   SS_NUKNOW = 0;                        //未知
        public static final int   SS_WITH_SAFE_BELT = 1;                //已系安全带
        public static final int   SS_WITHOUT_SAFE_BELT = 2;             //未系安全带
    }

    //遮阳板状态
    public static class NET_SUNSHADE_STATE extends SdkStructure
    {
        public static final int   SS_NUKNOW_SUN_SHADE = 0;              //未知
        public static final int   SS_WITH_SUN_SHADE = 1;                //有遮阳板
        public static final int   SS_WITHOUT_SUN_SHADE = 2;             //无遮阳板
    }

    // 驾驶位违规信息
    public static class EVENT_COMM_SEAT extends SdkStructure
    {
        public int              bEnable;                              //是否检测到座驾信息, 类型BOOL, 取值0或者1
        public int              emSeatType;                           //座驾类型,0:未识别;1:主驾驶; 取值为EM_COMMON_SEAT_TYPE中的值
        public EVENT_COMM_STATUS stStatus;                            //违规状态
        public int              emSafeBeltStatus;                     //安全带状态, 取值为NET_SAFEBELT_STATE中的值
        public int              emSunShadeStatus;                     //遮阳板状态, 取值为NET_SUNSHADE_STATE中的值
        public int              emCallAction;                         //打电话动作,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CALL_ACTION_TYPE}
        public int              nSafeBeltConf;                        //安全带确信度
        public int              nPhoneConf;                           //打电话置信度
        public int              nSmokeConf;                           //抽烟置信度
        public byte[]           szReserved = new byte[8];             //预留字节
    }

    // 车辆物件
    public static class EVENT_COMM_ATTACHMENT extends SdkStructure
    {
        public int              emAttachmentType;                     //物件类型, 取值为EM_COMM_ATTACHMENT_TYPE中的值
        public NET_RECT         stuRect;                              //坐标
        public int              nConf;                                //置信度
        public byte[]           bReserved = new byte[16];             //预留字节
    }

    //NTP校时状态
    public static class EM_NTP_STATUS extends SdkStructure
    {
        public static final int   NET_NTP_STATUS_UNKNOWN = 0;
        public static final int   NET_NTP_STATUS_DISABLE = 1;
        public static final int   NET_NTP_STATUS_SUCCESSFUL = 2;
        public static final int   NET_NTP_STATUS_FAILED = 3;
    }

    // 交通抓图图片信息
    public static class EVENT_PIC_INFO extends SdkStructure
    {
        public int              nOffset;                              // 原始图片偏移，单位字节
        public int              nLength;                              // 原始图片长度，单位字节
        public int              nIndexInData;                         // 在上传图片数据中的图片序号
    }

    public static class EVENT_COMM_INFO extends SdkStructure
    {
        public int              emNTPStatus;                          // NTP校时状态, 取值为EM_NTP_STATUS中的值
        public int              nDriversNum;                          // 驾驶员信息数
        public Pointer          pstDriversInfo;                       // 驾驶员信息数据，类型为 NET_MSG_OBJECT_EX*
        public Pointer          pszFilePath;                          // 本地硬盘或者sd卡成功写入路径,为NULL时,路径不存在， 类型为char *
        public Pointer          pszFTPPath;                           // 设备成功写到ftp服务器的路径， 类型为char *
        public Pointer          pszVideoPath;                         // 当前接入需要获取当前违章的关联视频的FTP上传路径， 类型为char *
        public EVENT_COMM_SEAT[] stCommSeat = new EVENT_COMM_SEAT[COMMON_SEAT_MAX_NUMBER]; // 驾驶位信息
        public int              nAttachmentNum;                       // 车辆物件个数
        public EVENT_COMM_ATTACHMENT[] stuAttachment = new EVENT_COMM_ATTACHMENT[NET_MAX_ATTACHMENT_NUM]; //车辆物件信息
        public int              nAnnualInspectionNum;                 // 年检标志个数
        public NET_RECT[]       stuAnnualInspection = new NET_RECT[NET_MAX_ANNUUALINSPECTION_NUM]; //年检标志
        public float            fHCRatio;                             // HC所占比例，单位：%
        public float            fNORatio;                             // NO所占比例，单位：%
        public float            fCOPercent;                           // CO所占百分比，单位：% 取值0~100
        public float            fCO2Percent;                          // CO2所占百分比，单位：% 取值0~100
        public float            fLightObscuration;                    // 不透光度，单位：% 取值0~100
        public int              nPictureNum;                          // 原始图片张数
        public EVENT_PIC_INFO[] stuPicInfos = new EVENT_PIC_INFO[NET_MAX_EVENT_PIC_NUM]; // 原始图片信息
        public float            fTemperature;                         // 温度值,单位摄氏度
        public int              nHumidity;                            // 相对湿度百分比值
        public float            fPressure;                            // 气压值,单位Kpa
        public float            fWindForce;                           // 风力值,单位m/s
        public int              nWindDirection;                       // 风向,单位度,范围:[0,360]
        public float            fRoadGradient;                        // 道路坡度值,单位度
        public float            fAcceleration;                        // 加速度值,单位:m/s2
        public NET_RFIDELETAG_INFO stuRFIDEleTagInfo = new NET_RFIDELETAG_INFO(); // RFID 电子车牌标签信息
        public EVENT_PIC_INFO   stuBinarizedPlateInfo = new EVENT_PIC_INFO(); // 二值化车牌抠图
        public EVENT_PIC_INFO   stuVehicleBodyInfo = new EVENT_PIC_INFO(); // 车身特写抠图
        public int              emVehicleTypeInTollStation;           // 收费站车型分类,详见EM_VEHICLE_TYPE
        public int              emSnapCategory;                       // 抓拍的类型，默认为机动车,详见EM_SNAPCATEGORY
        public int              nRegionCode;                          // 车牌所属地区代码,默认-1表示未识别
        public int              emVehicleTypeByFunc;                  // 按功能划分的车辆类型，详见EM_VEHICLE_TYPE_BY_FUNC
        public int              emStandardVehicleType;                // 标准车辆类型，详见EM_STANDARD_VEHICLE_TYPE
        public int              nExtraPlateCount;                     // 额外车牌数量
        public byte[]           szExtraPlateNumer = new byte[3*32];   // 额外车牌信息
        /**
         海外车辆类型中的子类别 {@link EM_OVERSEA_VEHICLE_CATEGORY_TYPE}
         */
        public			int            emOverseaVehicleCategory;
        /**
         车牌所属国家的省、州等地区名
         */
        public			byte[]         szProvince = new byte[64];
        /**
         物体在雷达坐标系中的信息,单位：米，设备视角：右手方向为X轴正向，正前方为Y轴正向
         */
        public			NET_EVENT_RADAR_INFO stuRadarInfo = new NET_EVENT_RADAR_INFO();
        /**
         触发事件时物体的GPS信息
         */
        public			NET_EVENT_GPS_INFO stuGPSInfo = new NET_EVENT_GPS_INFO();
        /**
         辅车牌信息，某些国家或地区一车多牌，比如港澳三地车，一车会有3个车牌，其中一个主车牌，一般是内地发给香港或澳门的能以此在内地行驶的"港澳牌"；另外两个分别是香港牌或澳门牌，是得以在香港或澳门行驶的牌照，而这两个则称为辅牌，有辅牌的车的车牌相关信息则填在此字段，目前最多2个辅车牌
         */
        public			NET_EXTRA_PLATES[] stuExtraPlates = new NET_EXTRA_PLATES[2];
        /**
         辅车牌有效个数
         */
        public			int            nExtraPlatesCount;
        /**
         车牌识别置信度
         */
        public			int            nPlateRecogniseConf;
        /**
         车辆姿态置信度
         */
        public			int            nVecPostureConf;
        /**
         车身颜色置信度
         */
        public			int            nVecColorConf;
        /**
         特殊车辆识别结果置信度
         */
        public			int            nSpecialVehConf;
        /**
         机动车是否为大角度
         */
        public			int            nIsLargeAngle;
        /**
         当前机动车车身是否曾经关联车牌
         */
        public			int            nIsRelatedPlate;
        /**
         机动车检测置信度
         */
        public			int            nDetectConf;
        /**
         机动车清晰度分值
         */
        public			int            nClarity;
        /**
         机动车完整度评分
         */
        public			int            nCompleteScore;
        /**
         机动车优选分数
         */
        public			int            nQeScore;
        public float            fSpeedFloat;                          //浮点型速度值，单位km/h
        public double           dbHeadingAngle;                       //航向角, 以正北方向为基准输出车辆运动方向同正北方向的角度; 范围 0~360，顺时针正,单位为度；
        public int              nDriverNum;                           //车辆前排驾驶室人员数量
        /**
         预留字节
         */
        public			byte[]         bReserved = new byte[112];
        public byte[]           szCountry = new byte[20];             // 国家

        public			EVENT_COMM_INFO(){
            for(int i=0;i<stCommSeat.length;i++){
                stCommSeat[i]=new EVENT_COMM_SEAT();
            }
            for(int i=0;i<stuAttachment.length;i++){
                stuAttachment[i]=new EVENT_COMM_ATTACHMENT();
            }
            for(int i=0;i<stuAnnualInspection.length;i++){
                stuAnnualInspection[i]=new NET_RECT();
            }
            for(int i=0;i<stuPicInfos.length;i++){
                stuPicInfos[i]=new EVENT_PIC_INFO();
            }
            for(int i=0;i<stuExtraPlates.length;i++){
                stuExtraPlates[i]=new NET_EXTRA_PLATES();
            }
        }
    }

    //收费站车型分类
    public static class EM_VEHICLE_TYPE extends SdkStructure
    {
        public static final int   EM_VEHICLE_TYPE_UNKNOWN = 0;          // 未知
        public static final int   EM_VEHICLE_TYPE_PASSENGERCAR1 = 1;    // 客1
        public static final int   EM_VEHICLE_TYPE_TRUCK1 = 2;           // 货1
        public static final int   EM_VEHICLE_TYPE_PASSENGERCAR2 = 3;    // 客2
        public static final int   EM_VEHICLE_TYPE_TRUCK2 = 4;           // 货2
        public static final int   EM_VEHICLE_TYPE_PASSENGERCAR3 = 5;    // 客3
        public static final int   EM_VEHICLE_TYPE_TRUCK3 = 6;           // 货3
        public static final int   EM_VEHICLE_TYPE_PASSENGERCAR4 = 7;    // 客4
        public static final int   EM_VEHICLE_TYPE_TRUCK4 = 8;           // 货4
        public static final int   EM_VEHICLE_TYPE_PASSENGERCAR5 = 9;    // 客5
        public static final int   EM_VEHICLE_TYPE_TRUCK5 = 10;          // 货5
    }

    //抓拍的类型
    public static class EM_SNAPCATEGORY extends SdkStructure
    {
        public static final int   EM_SNAPCATEGORY_MOTOR = 0;            // 机动车
        public static final int   EM_SNAPCATEGORY_NONMOTOR = 1;         // 非机动车
    }

    // RFID 电子车牌标签信息
    public static class NET_RFIDELETAG_INFO extends SdkStructure
    {
        public byte[]           szCardID = new byte[MAX_RFIDELETAG_CARDID_LEN]; // 卡号
        public int              nCardType;                            // 卡号类型, 0:交通管理机关发行卡, 1:新车出厂预装卡
        public int              emCardPrivince;                       // 卡号省份, 对应   EM_CARD_PROVINCE
        public byte[]           szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌号码
        public byte[]           szProductionDate = new byte[MAX_RFIDELETAG_DATE_LEN]; // 出厂日期
        public int              emCarType;                            // 车辆类型, 对应  EM_CAR_TYPE
        public int              nPower;                               // 功率,单位：千瓦时，功率值范围0~254；255表示该车功率大于可存储的最大功率值
        public int              nDisplacement;                        // 排量,单位：百毫升，排量值范围0~254；255表示该车排量大于可存储的最大排量值
        public int              nAntennaID;                           // 天线ID，取值范围:1~4
        public int              emPlateType;                          // 号牌种类, 对应  EM_PLATE_TYPE
        public byte[]           szInspectionValidity = new byte[MAX_RFIDELETAG_DATE_LEN]; // 检验有效期，年-月
        public int              nInspectionFlag;                      // 逾期未年检标志, 0:已年检, 1:逾期未年检
        public int              nMandatoryRetirement;                 // 强制报废期，从检验有效期开始，距离强制报废期的年数
        public int              emCarColor;                           // 车身颜色, 对应  EM_CAR_COLOR_TYPE
        public int              nApprovedCapacity;                    // 核定载客量，该值<0时：无效；此值表示核定载客，单位为人
        public int              nApprovedTotalQuality;                // 此值表示总质量，单位为百千克；该值<0时：无效；该值的有效范围为0~0x3FF，0x3FF（1023）表示数据值超过了可存储的最大值
        public NET_TIME_EX      stuThroughTime;                       // 过车时间
        public int              emUseProperty;                        // 使用性质, 对应  EM_USE_PROPERTY_TYPE
        public byte[]           szPlateCode = new byte[MAX_COMMON_STRING_8]; // 发牌代号，UTF-8编码
        public byte[]           szPlateSN = new byte[MAX_COMMON_STRING_16]; // 号牌号码序号，UTF-8编码
        public byte[]           szTID = new byte[MAX_COMMON_STRING_64]; // 标签(唯一标识), UTF-8编码
        public byte[]           bReserved = new byte[40];             // 保留字节,留待扩展.
    }

    // 车检器冗余信息
    public static class NET_SIG_CARWAY_INFO_EX extends SdkStructure
    {
        public byte[]           byRedundance = new byte[8];           //由车检器产生抓拍信号冗余信息
        public byte[]           bReserved = new byte[120];            //保留字段
    }

    // 颜色RGBA
    public static class NET_COLOR_RGBA extends SdkStructure
    {
        public int              nRed;                                 //红
        public int              nGreen;                               //绿
        public int              nBlue;                                //蓝
        public int              nAlpha;                               //透明

        public String toString() {
            return "[" + nRed + " " + nGreen + " " + nBlue + " " + nAlpha + "]";
        }
    }

    // TrafficCar 交通车辆信息
    public static class DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO extends SdkStructure
    {
        public byte[]           szPlateNumber = new byte[32];         //车牌号码
        public byte[]           szPlateType = new byte[32];           //号牌类型参见VideoAnalyseRule中车牌类型定义
        public byte[]           szPlateColor = new byte[32];          //车牌颜色"Blue","Yellow",
        public byte[]           szVehicleColor = new byte[32];        //车身颜色"White",
        public int              nSpeed;                               //速度单位Km/H
        public byte[]           szEvent = new byte[64];               //触发的相关事件参见事件列表Event
        public byte[]           szViolationCode = new byte[32];       //违章代码详见TrafficGlobal.ViolationCode
        public byte[]           szViolationDesc = new byte[64];       //违章描述
        public int              nLowerSpeedLimit;                     //速度下限
        public int              nUpperSpeedLimit;                     //速度上限
        public int              nOverSpeedMargin;                     //限高速宽限值单位：km/h
        public int              nUnderSpeedMargin;                    //限低速宽限值单位：km/h
        public int              nLane;                                //车道参见事件列表EventList中卡口和路口事件。
        public int              nVehicleSize;                         //车辆大小,-1表示未知,否则按位
        // 第0位:"Light-duty", 小型车
        // 第1位:"Medium", 中型车
        // 第2位:"Oversize", 大型车
        // 第3位:"Minisize", 微型车
        // 第4位:"Largesize", 长车
        public float            fVehicleLength;                       // 车辆长度单位米
        public int              nSnapshotMode;                        // 抓拍方式0-未分类,1-全景,2-近景,4-同向抓拍,8-反向抓拍,16-号牌图像
        public byte[]           szChannelName = new byte[32];         // 本地或远程的通道名称,可以是地点信息来源于通道标题配置ChannelTitle.Name
        public byte[]           szMachineName = new byte[256];        // 本地或远程设备名称来源于普通配置General.MachineName
        public byte[]           szMachineGroup = new byte[256];       // 机器分组或叫设备所属单位默认为空,用户可以将不同的设备编为一组,便于管理,可重复。
        public byte[]           szRoadwayNo = new byte[64];           // 道路编号
        public byte[]           szDrivingDirection = new byte[3*NET_MAX_DRIVINGDIRECTION]; //
        // 行驶方向 , "DrivingDirection" : ["Approach", "", ""],
        // "Approach"-上行,即车辆离设备部署点越来越近；"Leave"-下行,
        // 即车辆离设备部署点越来越远,第二和第三个参数分别代表上行和
        // 下行的两个地点
        public Pointer          szDeviceAddress;                      // 设备地址,OSD叠加到图片上的,来源于配 置TrafficSnapshot.DeviceAddress,'\0'结束
        public byte[]           szVehicleSign = new byte[32];         // 车辆标识,例如
        public NET_SIG_CARWAY_INFO_EX stuSigInfo;                     // 由车检器产生抓拍信号冗余信息
        public Pointer          szMachineAddr;                        // 设备部署地点
        public float            fActualShutter;                       // 当前图片曝光时间,单位为毫秒
        public byte             byActualGain;                         // 当前图片增益,范围为0~100
        public byte             byDirection;                          // 车道方向,0-南向北1-西南向东北2-西向东
        public byte[]           byReserved = new byte[2];
        public Pointer          szDetailedAddress;                    // 详细地址,作为szDeviceAddress的补充
        public byte[]           szDefendCode = new byte[NET_COMMON_STRING_64]; //图片防伪码
        public int              nTrafficBlackListID;                  // 关联禁止名单数据库记录默认主键ID,0,无效；>0,禁止名单数据记录
        public NET_COLOR_RGBA   stuRGBA;                              // 车身颜色RGBA
        public NET_TIME         stSnapTime;                           // 抓拍时间
        public int              nRecNo;                               // 记录编号
        public byte[]           szCustomParkNo = new byte[NET_COMMON_STRING_32+1]; // 自定义车位号（停车场用）
        public byte[]           byReserved1 = new byte[3];
        public int              nDeckNo;                              // 车板位号
        public int              nFreeDeckCount;                       // 空闲车板数量
        public int              nFullDeckCount;                       // 占用车板数量
        public int              nTotalDeckCount;                      // 总共车板数量
        public byte[]           szViolationName = new byte[64];       // 违章名称
        public int              nWeight;                              // 车重(单位Kg), 类型为unsigned int
        public byte[]           szCustomRoadwayDirection = new byte[32]; // 自定义车道方向,byDirection为9时有效
        public byte             byPhysicalLane;                       // 物理车道号,取值0到5
        public byte[]           byReserved2 = new byte[3];
        public int              emMovingDirection;                    // 车辆行驶方向 EM_TRAFFICCAR_MOVE_DIRECTION
        public NET_TIME         stuEleTagInfoUTC;                     // 对应电子车牌标签信息中的过车时间(ThroughTime)
        public NET_RECT         stuCarWindowBoundingBox;              // 车窗包围盒，0~8191
        public NET_TRAFFICCAR_WHITE_LIST stuWhiteList;                // 允许名单信息
        public int              emCarType;                            // 车辆类型, 详见 EM_TRAFFICCAR_CAR_TYPE
        public int              emLaneType;                           // 车道类型, 详见EM_TRAFFICCAR_LANE_TYPE
        public byte[]           szVehicleBrandYearText = new byte[64]; // 车系年款翻译后文本内容
        public byte[]           szCategory = new byte[32];            // 车辆子类型
        public NET_TRAFFICCAR_BLACK_LIST stuBlackList;                // 禁止名单信息
        public int              emFlowDirection;                      // 车流量方向   EM_VEHICLE_DIRECTION
        /**
         * 收费公路车辆通行费车型分类 {@link EM_TOLLS_VEHICLE_TYPE}
         */
        public int              emTollsVehicleType;
        /**
         * 轴型代码,参考轴型国标 0代表其他
         */
        public int              nAxleType;
        /**
         * 车轴数量
         */
        public int              nAxleCount;
        /**
         * 车轮数量
         */
        public int              nWheelNum;
        /**
         * 车身抠图
         */
        public NET_TRAFFICCAR_ORIGINAL_VEHICLE stuOriginalVehicle = new NET_TRAFFICCAR_ORIGINAL_VEHICLE();
        /**
         * 按功能划分的车辆类型 {@link EM_VEHICLE_TYPE_BY_FUNC}
         */
        public int              emVehicleTypeByFunc;
        /**
         * 车辆子品牌
         */
        public short            nSunBrand;
        /**
         * 车辆年款
         */
        public short            nBrandYear;
        /**
         * 交通灯类型,仅在EVENT_IVS_TRAFFIC_RUNREDLIGHT中有效, 0;未知, 1:箭头灯, 2:圆形灯
         */
        public int              nTrafficLightType;
        /**
         * 车牌属性 {@link EM_PLATE_ATTRIBUTE}
         */
        public int              emPlateAttribute;
        /**
         * 交通车辆信息扩展, refer to {@link NET_TRAFFICCAR_INFO_EXTERN}
         */
        public Pointer          pTrafficInfoExtern;
        public byte[]           szCarNoGroupType = new byte[64];      //分组类别,出租车、网约车、私家车等等
        public int              nOverShoot;                           //车辆是否冲出检测区域(0-未知 1-冲出 2-未冲出)
        /**
         * 保留字节,留待扩展
         */
        public byte[]           bReserved = new byte[64-POINTERSIZE];
    }

    // 禁止名单信息
    public static class NET_TRAFFICCAR_BLACK_LIST extends SdkStructure
    {
        /**
         是否已启用禁止名单
         */
        public			int            bEnable;
        /**
         车牌是否属于禁止名单
         */
        public			int            bIsBlackCar;
        /**
         禁止名单起始时间
         */
        public NET_TIME         stuBeginTime = new NET_TIME();
        /**
         禁止名单过期时间
         */
        public NET_TIME         stuCancelTime = new NET_TIME();
        /**
         布控类型 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_NET_TRAFFIC_CAR_CONTROL_TYPE}
         */
        public			int            emControlType;
        /**
         布控路线ID
         */
        public			int            nControlledRouteID;
        /**
         保留字节
         */
        public			byte[]         bReserved = new byte[24];

        public			NET_TRAFFICCAR_BLACK_LIST(){
        }
    }

    // 车辆类型
    public static class EM_TRAFFICCAR_CAR_TYPE extends SdkStructure
    {
        public static final int   EM_TRAFFICCAR_CAR_TYPE_UNKNOWN = 0;   // 未知
        public static final int   EM_TRAFFICCAR_CAR_TYPE_TRUST_CAR = 1; // 允许名单车辆
        public static final int   EM_TRAFFICCAR_CAR_TYPE_SUSPICIOUS_CAR = 2; // 禁止名单车辆
        public static final int   EM_TRAFFICCAR_CAR_TYPE_NORMAL_CAR = 3; // 非允许名单且非禁止名单车辆
    }

    // 车道类型
    public static class EM_TRAFFICCAR_LANE_TYPE extends SdkStructure
    {
        public static final int   EM_TRAFFICCAR_LANE_TYPE_UNKNOWN = 0;  // 未知
        public static final int   EM_TRAFFICCAR_LANE_TYPE_NORMAL = 1;   // 普通车道
        public static final int   EM_TRAFFICCAR_LANE_TYPE_NONMOTOR = 2; // 非机动车车道
        public static final int   EM_TRAFFICCAR_LANE_TYPE_LIGHT_DUTY = 3; // 小型车车道
        public static final int   EM_TRAFFICCAR_LANE_TYPE_BUS = 4;      // 公交车车道
        public static final int   EM_TRAFFICCAR_LANE_TYPE_EMERGENCY = 5; // 应急车道
        public static final int   EM_TRAFFICCAR_LANE_TYPE_DANGEROUS = 6; // 危险品车道
    }

    // 允许名单信息
    public static class NET_TRAFFICCAR_WHITE_LIST extends SdkStructure
    {
        public int              bTrustCar;                            // 车牌是否属于允许名单
        public NET_TIME         stuBeginTime;                         // 允许名单起始时间
        public NET_TIME         stuCancelTime;                        // 允许名单过期时间
        public NET_WHITE_LIST_AUTHORITY_LIST stuAuthorityList;        // 允许名单权限列表
        public byte[]           bReserved = new byte[32];             // 保留字节
    }

    // 允许名单权限列表
    public static class NET_WHITE_LIST_AUTHORITY_LIST extends SdkStructure
    {
        public int              bOpenGate;                            // 是否有开闸权限
        public byte[]           bReserved = new byte[16];             // 保留字节
    }

    // 事件类型EVENT_IVS_TRAFFIC_PARKING(交通违章停车事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PARKING_INFO extends SdkStructure {
        public int              nChannelID;                           //通道号
        public byte[]           szName = new byte[128];               //事件名称
        public byte[]           bReserved1 = new byte[4];             //字节对齐
        public double           PTS;                                  //时间戳(单位是毫秒)
        public NET_TIME_EX      UTC = new NET_TIME_EX();              //事件发生的时间
        public int              nEventID;                             //事件ID
        public NET_MSG_OBJECT   stuObject = new NET_MSG_OBJECT();     //检测到的物体
        public NET_MSG_OBJECT   stuVehicle = new NET_MSG_OBJECT();    //车身信息
        public int              nLane;                                //对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO(); //事件对应文件信息
        public byte             bEventAction;                         //事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           reserved = new byte[2];               //保留字节
        public byte             byImageIndex;                         //图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public NET_TIME_EX      stuStartParkingTime = new NET_TIME_EX(); //开始停车时间
        public int              nSequence;                            //表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束(bEventAction=2时此参数有效)
        public int              nAlarmIntervalTime;                   //报警时间间隔,单位:秒。(此事件为连续性事件,在收到第一个此事件之后,若在超过间隔时间后未收到此事件的后续事件,则认为此事件异常结束了)
        public int              nParkingAllowedTime;                  //允许停车时长,单位：秒。
        public int              nDetectRegionNum;                     //规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[]) new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); //规则检测区域
        public int              dwSnapFlagMask;                       //抓图标志(按位),具体见NET_RESERVED_COMMON
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO(); //对应图片的分辨率
        public int              bIsExistAlarmRecord;                  //true:有对应的报警录像;false:无对应的报警录像, 类型为BOOL, 取值为0或1
        public int              dwAlarmRecordSize;                    //录像大小
        public byte[]           szAlarmRecordPath = new byte[NET_COMMON_STRING_256]; //录像路径
        public byte[]           szFTPPath = new byte[NET_COMMON_STRING_256]; //FTP路径
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo = new EVENT_INTELLI_COMM_INFO(); //智能事件公共信息
        public byte             byPreAlarm;                           // 是否为违章预警图片,0 违章停车事件1 预警事件(预警触发后一定时间，车辆还没有离开，才判定为违章)由于此字段会导致事件含义改变，必须和在平台识别预警事件后，才能有此字段,
        public byte[]           bReserved2 = new byte[3];             // 保留字节,留待扩展.
        public NET_GPS_INFO     stuGPSInfo = new NET_GPS_INFO();      // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,{@link NET_IMAGE_INFO_EX2}
        public int              nImageInfoNum;                        //图片信息个数
        public int              nPresetID;                            //预置点编号,从1开始
        public byte[]           szSN = new byte[32];                  //设备SN号
        public int              emViolationSnapSource;                //抓拍触发源,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_VIOLATION_SNAP_SOURCE}
        public int              bIsStrictArea;                        //是否为严抓路段 TRUE:是 FALSE:不是
        public Pointer          pstuSceneImage;                       //全景图图片信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           szPreParkingPlace = new byte[64];     //违法地点
        public byte[]           szRecordEndTime = new byte[64];       //最后一张图片的时间, 格式为：yyyy-MM-ddTHH:mm:ss.SSSXXX, 其中T为不需要解析的无意义字符，XXX为时区
        public byte[]           bReserved = new byte[48-POINTERSIZE]; //保留字节,留待扩展.
        public org.springblade.modules.iot.dahua.lib.structure.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar = new org.springblade.modules.iot.dahua.lib.structure.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO(); //交通车辆信息
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();   //公共信息
        public VA_OBJECT_NONMOTOR stuNonMotor = new VA_OBJECT_NONMOTOR(); //非机动车对象
        public int              bHasNonMotor;                         //是否有非机动车对象
        public int              nParkingDuration;                     //违停持续时间，单位：秒, 0表示无意义
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public DEV_EVENT_TRAFFIC_PARKING_INFO() {
            for (int i = 0; i < DetectRegion.length; i++) {
                DetectRegion[i] = new NET_POINT();
            }
        }
    }

    //停车场信息
    public static class DEV_TRAFFIC_PARKING_INFO extends SdkStructure
    {
        public int              nFeaturePicAreaPointNum;              // 特征图片区点个数
        public NET_POINT[]      stFeaturePicArea = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM); // 特征图片区信息
        public byte[]           bReserved = new byte[572];            // 保留字节
    }

    //事件类型 EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING(车位有车事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PARKINGSPACEPARKING_INFO extends SdkStructure
    {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐
         */
        public byte[]           bReserved1 = new byte[8];
        /**
         * 时间戳(单位是毫秒)
         */
        public int              PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX      UTC = new NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 对应车道号
         */
        public int              nLane;
        /**
         * 检测到的物体
         */
        public NET_MSG_OBJECT stuObject = new NET_MSG_OBJECT();
        /**
         * 车身信息
         */
        public NET_MSG_OBJECT stuVehicle = new NET_MSG_OBJECT();
        /**
         * 事件对应文件信息
         */
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO();
        /**
         * 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
         */
        public int              nSequence;
        /**
         * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
         */
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];
        /**
         * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
         */
        public byte             byImageIndex;
        /**
         * 抓图标志(按位),具体见NET_RESERVED_COMMON
         */
        public int              dwSnapFlagMask;
        /**
         * 对应图片的分辨率
         */
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO();
        /**
         * 交通车辆信息
         */
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
        /**
         * 车位综合的状态,0-占用,1-空闲,2-压线
         */
        public int              nParkingSpaceStatus;
        /**
         * 停车场信息
         */
        public DEV_TRAFFIC_PARKING_INFO stTrafficParingInfo = new DEV_TRAFFIC_PARKING_INFO();
        /**
         * 车牌识别来源, 0:本地算法识别,1:后端服务器算法识别
         */
        public byte             byPlateTextSource;
        /**
         * 字节对齐
         */
        public byte[]           byReserved2 = new byte[3];
        /**
         * 车位(地磁)编号
         */
        public byte[]           szParkingNum = new byte[32];
        /**
         * 球机预置位编号
         */
        public int              dwPresetNum;
        /**
         * 车位是否有故障
         */
        public int              bParkingFault;
        public Pointer          pstuSceneImage;                       //全景广角图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public int              bSceneImage;                          //pstuSceneImage是否有效
        /**
         * 保留字节
         */
        public byte[]           bReserved = new byte[360-POINTERSIZE];
        /**
         * 公共信息
         */
        public EVENT_COMM_INFO stCommInfo = new EVENT_COMM_INFO();
        /**
         * 车位图片信息
         */
        public NET_INTELLIGENCE_IMAGE_INFO stuParkingImage = new NET_INTELLIGENCE_IMAGE_INFO();
        /**
         * 车位置信度（0-100）
         */
        public int              nConfidence;
        /**
         * 是否跨位 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_ACROSS_PARKING}
         */
        public int              emAcrossParking;
        /**
         * 停车方向 {@link EM_PARKINGSPACE_DIRECTION}
         */
        public int              emParkingDirection;
        /**
         * 禁停状态 {@link EM_FORBID_PARKING_STATUS}
         */
        public int              emForbidParkingStatus;
        /**
         * 是否小车占大车位 {@link EM_SMALL_OCCUPY_LARGE}
         */
        public int              emSmallOccupyLarge;
        /**
         * 是否为非允许名单车辆 {@link EM_NON_ALLOW_LIST_CAR}
         */
        public int              emNonAllowListCar;
        /**
         * 是否为非新能源车辆 {@link EM_NON_NEW_ENERGY_CAR}
         */
        public int              emNonNewEnergyCar;
        /**
         * 为图片信息做预留字节，新增的字段请在该保留字节下面添加
         */
        public byte[]           byReserved1 = new byte[992];
        /**
         * 触发类型 {@link EM_PARKING_TRIGGER_TYPE}
         */
        public int              emTriggerType;
        /**
         * 一位多车信息, 如果车位此前没有车占用, 不会带有本字段信息
         */
        public DEV_OCCUPIED_WARNING_INFO stuOccupiedWarningInfo = new DEV_OCCUPIED_WARNING_INFO();
        /**
         * 所有使能的车位号
         */
        public ParkingNoWithSize32[] szAllParkingNo = new ParkingNoWithSize32[12];
        /**
         * 使能车位号的个数
         */
        public int              nParkingNoNum;
        /**
         * 车位有车的事件类型 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_PARKING_SPACE_TYPE}
         */
        public int              emParkingSpaceType;
        /**
         * 停车变更信息
         */
        public NET_PARKING_CHANGE_INFO stuParkingChangeInfo = new NET_PARKING_CHANGE_INFO();
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
         * 字节对齐
         */
        public byte[]           szReserved = new byte[4];

        public DEV_EVENT_TRAFFIC_PARKINGSPACEPARKING_INFO() {
            for(int i = 0;i<szAllParkingNo.length;i++){
                szAllParkingNo[i] = new ParkingNoWithSize32();
            }
        }
    }

    // 停车变更信息
    public static class NET_PARKING_CHANGE_INFO extends SdkStructure
    {
        public byte[]           szPreParkingNo = new byte[32];        // 变更前的车位
        public byte[]           szAfterParkingNo = new byte[32];      // 变更后的车位
        public int              nStrandTime;                          // 变更车位停车时长，单位：秒
        public byte[]           byReserved = new byte[252];           // 保留字节
    }

    // 车位有车事件类型
    public static class EM_PARKING_SPACE_TYPE
    {
        public static int         EM_PARKING_SPACE_TYPE_UNKNOWN = -1;   // 未知
        public static int         EM_PARKING_SPACE_TYPE_NORMAL = 0;     // 正常驶入
        public static int         EM_PARKING_SPACE_TYPE_CHANGE = 1;     // 泊车变更事件
    }

    // 是否跨位
    public static class EM_ACROSS_PARKING
    {
        public static int         EM_ACROSS_PARKING_UNKNOWN = 0;        // 未知
        public static int         EM_ACROSS_PARKING_NO = 1;             // 未跨位
        public static int         EM_ACROSS_PARKING_YES = 2;            // 跨位
    }

    // 一位多车信息
    public class DEV_OCCUPIED_WARNING_INFO extends SdkStructure
    {
        public byte[]           szParkingNo = new byte[32];           // 车位号
        public DEV_OCCUPIED_WARNING_PLATE_NUMBER[] szPlateNumber = new DEV_OCCUPIED_WARNING_PLATE_NUMBER[5]; // 车牌号码
        public int              nPlateNumber;                         // 车牌数量
        public byte[]           bReserved = new byte[508];            // 预留字节

        public DEV_OCCUPIED_WARNING_INFO(){
            for(int i = 0;i<szPlateNumber.length;i++){
                szPlateNumber[i] = new DEV_OCCUPIED_WARNING_PLATE_NUMBER();
                ;            }
        }
    }

    public class DEV_OCCUPIED_WARNING_PLATE_NUMBER extends SdkStructure
    {
        public byte[]           plateNumber = new byte[64];
    }

    // 事件类型 EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING(车位无车事件)对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO和EVENT_IVS_TRAFFICGATE要一起处理,以防止有视频电警和线圈电警同时接入平台的情况发生
    // 另外EVENT_IVS_TRAFFIC_TOLLGATE只支持新卡口事件的配置
    /**
     * @author 260611
     * @description 事件类型 EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING(车位无车事件)对应的数据块描述信息
     * @origin autoTool
     * @date 2023/06/20 10:13:06
     */
    public static class DEV_EVENT_TRAFFIC_PARKINGSPACENOPARKING_INFO extends SdkStructure
    {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐
         */
        public byte[]           bReserved1 = new byte[8];
        /**
         * 时间戳(单位是毫秒)
         */
        public int              PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX      UTC = new NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 对应车道号
         */
        public int              nLane;
        /**
         * 检测到的物体
         */
        public NET_MSG_OBJECT   stuObject = new NET_MSG_OBJECT();
        /**
         * 车身信息
         */
        public NET_MSG_OBJECT   stuVehicle = new NET_MSG_OBJECT();
        /**
         * 事件对应文件信息
         */
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO();
        /**
         * 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
         */
        public int              nSequence;
        /**
         * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
         */
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];
        /**
         * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
         */
        public byte             byImageIndex;
        /**
         * 抓图标志(按位),具体见NET_RESERVED_COMMON
         */
        public int              dwSnapFlagMask;
        /**
         * 对应图片的分辨率
         */
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO();
        /**
         * 交通车辆信息
         */
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
        /**
         * 停车场信息
         */
        public DEV_TRAFFIC_PARKING_INFO stTrafficParingInfo = new DEV_TRAFFIC_PARKING_INFO();
        /**
         * 车牌识别来源, 0:本地算法识别,1:后端服务器算法识别
         */
        public byte             byPlateTextSource;
        /**
         * 字节对齐
         */
        public byte[]           byReserved2 = new byte[3];
        /**
         * 车位(地磁)编号
         */
        public byte[]           szParkingNum = new byte[32];
        /**
         * 球机预置位编号
         */
        public int              dwPresetNum;
        /**
         * 车位是否有故障
         */
        public int              bParkingFault;
        /**
         * 图片信息数组
         */
        public Pointer          pstuImageInfo;
        /**
         * 图片信息个数
         */
        public int              nImageInfoNum;
        public int              bSceneImage;                          //pstuSceneImage是否有效
        public Pointer          pstuSceneImage;                       //全景广角图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        /**
         * 保留字节
         */
        public byte[]           bReserved = new byte[360-2*POINTERSIZE];
        /**
         * 公共信息
         */
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();
        /**
         * 全景大图信息
         */
        public NET_INTELLIGENCE_IMAGE_INFO stuGlobalImage = new NET_INTELLIGENCE_IMAGE_INFO();
        /**
         * 车位图片信息
         */
        public NET_INTELLIGENCE_IMAGE_INFO stuParkingImage = new NET_INTELLIGENCE_IMAGE_INFO();
        /**
         * 车位置信度（0-100）
         */
        public int              nConfidence;
        /**
         * 为图片信息做预留字节，新增的字段请在该保留字节下面添加
         */
        public byte[]           byReserved1 = new byte[1016];
        /**
         * 触发方式 {@link EM_PARKING_TRIGGER_TYPE}
         */
        public int              emTriggerType;
        /**
         * 匹配到的车辆驶入信息
         */
        public DEV_MATCH_PARKING_INFO[] stuMatchParkingInfo = new DEV_MATCH_PARKING_INFO[5];
        /**
         * 匹配到的车辆驶入信息个数
         */
        public int              nMatchParkingNum;
        /**
         * 所有使能的车位号
         */
        public ParkingNoWithSize32[] szAllParkingNo = new ParkingNoWithSize32[12];
        /**
         * 使能车位号的个数
         */
        public int              nParkingNoNum;
        /**
         * 事件公共扩展字段结构体
         */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();

        public DEV_EVENT_TRAFFIC_PARKINGSPACENOPARKING_INFO() {
            for (int i = 0; i < stuMatchParkingInfo.length; i++) {
                stuMatchParkingInfo[i] = new DEV_MATCH_PARKING_INFO();
            }
            for(int i = 0;i<szAllParkingNo.length;i++){
                szAllParkingNo[i] = new ParkingNoWithSize32();
            }
        }
    }

    public class ParkingNoWithSize32 extends SdkStructure
    {
        public byte[]           parkingNo = new byte[32];
    }

    // 匹配到的车辆驶入信息
    public class DEV_MATCH_PARKING_INFO extends SdkStructure
    {
        public byte[]           szParkingNo = new byte[32];           // 驶入的车位号
        public byte[]           szPlateNum = new byte[64];            // 驶入的车牌号信息
        public int              nSimilarity;                          // 驶出车牌号码与匹配到的驶入车牌号码的相似度, 全匹配成功为100, 失败为0。
        // 如果开启模糊匹配, 全匹配不为100则进行模糊匹配, 如下
        // 1. 除汉字外，所有的数字和字母一样 为95
        // 2. 有一位数字或字母不一样，则为90
        // 3. 有两位数字或字母不一样，则为80
        // 其他的情况则认为没有匹配到，则为0
        public byte[]           bReserved = new byte[508];            // 保留字节
    }

    /**
     * @author 260611
     * @description 事件类型 EVENT_IVS_TRAFFIC_PEDESTRAIN(交通行人事件)对应数据块描述信息
     * @origin autoTool
     * @date 2023/08/02 10:47:11
     */
    public class DEV_EVENT_TRAFFIC_PEDESTRAIN_INFO extends SdkStructure {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐
         */
        public byte[]           bReserved1 = new byte[8];
        /**
         * 时间戳(单位是毫秒)
         */
        public int              PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX      UTC = new NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 事件对应文件信息
         */
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO();
        /**
         * 对应图片的分辨率
         */
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO();
        /**
         * 抓图标志(按位),0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
         */
        public int              dwSnapFlagMask;
        /**
         * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
         */
        public byte             bEventAction;
        public byte[]           bReserved2 = new byte[2];
        /**
         * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
         */
        public byte             byImageIndex;
        /**
         * 对应车道号
         */
        public int              nLane;
        /**
         * 检测到的物体
         */
        public NET_MSG_OBJECT   stuObject = new NET_MSG_OBJECT();
        /**
         * 智能事件公共信息
         */
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo = new EVENT_INTELLI_COMM_INFO();
        /**
         * 交通车辆部分信息
         */
        public EVENT_TRAFFIC_CAR_PART_INFO stuTrafficCarPartInfo = new EVENT_TRAFFIC_CAR_PART_INFO();
        /**
         * 行人信息, 废弃
         */
        public EVENT_VEHICLE_INFO stuVehicle = new EVENT_VEHICLE_INFO();
        /**
         * 行人信息, refer to {@link NET_MSG_OBJECT}
         */
        public Pointer          pstuVehicle;
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public Pointer          pstuObjects;                          //上报一个报警中行人多目标的信息，内存由NetSDK申请释放,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_MSG_OBJECT}
        public int              nObjectsNum;                          //行人多目标信息有效个数, 最大值为100
        public Pointer          pstuHumanList;                        //上报一个报警中行人多目标的信息，内存由NetSDK申请释放,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_VAOBJECT_ROADCONE_INFO}
        public int              nHumanListNum;                        //行人多目标信息有效个数, 最大值为100
        /**
         * 保留字节
         */
        public byte[]           bReserved = new byte[240-5*POINTERSIZE];
        /**
         * 公共信息
         */
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}

        public DEV_EVENT_TRAFFIC_PEDESTRAIN_INFO() {
        }
    }

    //事件类型 EVENT_IVS_TRAFFIC_THROW(交通抛洒物品事件)对应数据块描述信息
    public static class DEV_EVENT_TRAFFIC_THROW_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[8];             // 字节对齐
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              dwSnapFlagMask;                       // 抓图标志(按位),0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           bReserved2 = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public EVENT_TRAFFIC_CAR_PART_INFO stuTrafficCarPartInfo;     // 交通车辆部分信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public SCENE_IMAGE_INFO stuSceneImage = new SCENE_IMAGE_INFO(); //全景广角图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO}
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public byte[]           bReserved = new byte[264-POINTERSIZE]; // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
    }

    // 交通车辆部分信息
    public class EVENT_TRAFFIC_CAR_PART_INFO extends SdkStructure
    {
        public byte[]           szMachineName = new byte[128];        // 本地或远程设备名称    来源于普通配置General.MachineName
        public byte[]           szRoadwayNo = new byte[32];           // 道路编号
        public byte[]           szPlateNumber = new byte[32];         //车牌号码
        public byte[]           szCategory = new byte[32];            //车辆子类型
        public byte[]           bReserved = new byte[288];            // 保留字节
    }

    // 事件上报携带卡片信息
    public static class EVENT_CARD_INFO extends SdkStructure
    {
        public byte[]           szCardNumber = new byte[NET_EVENT_CARD_LEN]; // 卡片序号字符串
        public byte[]           bReserved = new byte[32];             // 保留字节,留待扩展.
    }

    // 车辆方向信息
    public static class EM_VEHICLE_DIRECTION extends SdkStructure
    {
        public static final int   NET_VEHICLE_DIRECTION_UNKOWN = 0;     // 未知
        public static final int   NET_VEHICLE_DIRECTION_HEAD = 1;       // 车头
        public static final int   NET_VEHICLE_DIRECTION_TAIL = 2;       // 车尾
        public static final int   NET_VEHICLE_DIRECTION_VEHBODYSIDE = 3; // 车身(侧面)
    }

    // 开闸状态
    public static class EM_OPEN_STROBE_STATE extends SdkStructure
    {
        public static final int   NET_OPEN_STROBE_STATE_UNKOWN = 0;     // 未知状态
        public static final int   NET_OPEN_STROBE_STATE_CLOSE = 1;      // 关闸
        public static final int   NET_OPEN_STROBE_STATE_AUTO = 2;       // 自动开闸
        public static final int   NET_OPEN_STROBE_STATE_MANUAL = 3;     // 手动开闸
    }

    public static class RESERVED_PARA extends SdkStructure
    {
        public int              dwType;                               //pData的数据类型
        //当[dwType]为 RESERVED_TYPE_FOR_INTEL_BOX 时,pData 对应为结构体 RESERVED_DATA_INTEL_BOX 的地址
        //当[dwType]为 RESERVED_TYPE_FOR_COMMON 时,[pData]对应为结构体 NET_RESERVED_COMMON 的结构体地址
        //当[dwType]为 RESERVED_TYPE_FOR_PATH 时,[pData]对应结构体NET_RESERVED_PATH的结构体地址
        public Pointer          pData;                                //数据,由用户申请内存，大小参考对应的结构体
    }

    // 事件类型 EVENT_IVS_TRAFFICJUNCTION 交通路口老规则事件/视频电警上的交通卡口老规则事件对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO 和 EVENT_IVS_TRAFFICGATE要一起处理
    // 以防止有视频电警和线圈电警同时接入平台的情况发生, 另外EVENT_IVS_TRAFFIC_TOLLGATE只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFICJUNCTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte             byMainSeatBelt;                       // 主驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte             bySlaveSeatBelt;                      // 副驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte             byVehicleDirection;                   // 当前被抓拍到的车辆是车头还是车尾,具体请见 EM_VEHICLE_DIRECTION
        public byte             byOpenStrobeState;                    // 开闸状态,具体请见 EM_OPEN_STROBE_STATE
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
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byDirection;                          // 路口方向,1-表示正向,2-表示反向
        public byte             byLightState;                         // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte             byReserved;                           // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见 NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte[]           szRecordFile = new byte[NET_COMMON_STRING_128]; // 报警对应的原始录像文件信息
        public EVENT_JUNCTION_CUSTOM_INFO stuCustomInfo;              // 自定义信息
        public byte             byPlateTextSource;                    // 车牌识别来源, 0:本地算法识别,1:后端服务器算法识别
        public byte[]           bReserved1 = new byte[3];             // 保留字节,留待扩展.
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
        public byte[]           bReserved = new byte[22];             // 保留字节,留待扩展.
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public int              dwRetCardNumber;                      // 卡片个数
        public EVENT_CARD_INFO[] stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM); // 卡片信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public int              bNonMotorInfoEx;                      // 是否有非机动车信息, 1-true; 0-false
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车信息
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public EVENT_PLATE_INFO stuPlateInfo = new EVENT_PLATE_INFO(); // 车辆信息，记录了车头、车尾车牌号和车牌颜色
        public int              bSceneImage;                          // 全景图是否有效
        public SCENE_IMAGE_INFO_EX stuSceneImage = new SCENE_IMAGE_INFO_EX(); // 全景图
        public Pointer          pstObjects;                           // 检测到的多个车牌信息
        public int              nObjectNum;                           // 检测到的多个车牌个数
        public int              emVehiclePosture;                     // 车辆姿势
        public int              nVehicleSignConfidence;               // 车标置信度（范围：0~100）
        public int              nVehicleCategoryConfidence;           // 车型置信度（范围：0~100）
        public int              emCarDrivingDirection;                // 规则区内车辆行驶方向
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = new NET_IMAGE_INFO_EX2[32]; // 图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public byte[]           szSerialNo = new byte[128];           // 和客户端请求的抓图序列号对应
        public int              nAlarmCompliance;                     // 报警合规, 0:未知, 1:不合规, 2:合规
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public NET_MSG_OBJECT_SUPPLEMENT stObjectInfoEx;              // 视频分析物体信息补充字段，与 DH_MSG_OBJECT 的合集表示视频分析物体信息
        public Pointer          pstuObjectEx2;                        // 检测到的物体扩展,指针对应结构体NET_MSG_OBJECT_EX2
        public Pointer          pstuVehicleEx2;                       // 车身信息扩展，指针对应结构体NET_MSG_OBJECT_EX2
        public Pointer          pstuObjectsEx2;                       // 检测到的多个车牌信息扩展，数量为nObjectNum,指针对应结构体NET_MSG_OBJECT_EX2
        public int              nPresetID;                            // 事件触发的预置点号，从1开始, 0表示未知
        public byte[]           byReserved2 = new byte[584-3*POINTERSIZE]; // 保留字节

    	public DEV_EVENT_TRAFFICJUNCTION_INFO(){
            for(int i = 0; i < 32; i ++){
                stuImageInfo[i] = new NET_IMAGE_INFO_EX2();
            }
        }
    }

    // 非机动车对象
    public static class VA_OBJECT_NONMOTOR extends SdkStructure
    {
        /**
         *  物体ID,每个ID表示一个唯一的物体
         */
        public int              nObjectID;
        /**
         *  非机动车子类型
         */
        public int              emCategory;
        /**
         *  包围盒， 非机动车矩形框，0~8191相对坐标
         */
        public DH_RECT          stuBoundingBox = new DH_RECT();
        /**
         *  包围盒， 非机动车矩形框，绝对坐标
         */
        public DH_RECT          stuOriginalBoundingBox = new DH_RECT();
        /**
         *  非机动车颜色, RGBA
         */
        public NET_COLOR_RGBA   stuMainColor = new NET_COLOR_RGBA();
        /**
         *  非机动车颜色, 枚举
         */
        public int              emColor;
        /**
         *  是否有抠图
         */
        public int              bHasImage;
        /**
         *  物体截图
         */
        public NET_NONMOTOR_PIC_INFO stuImage = new NET_NONMOTOR_PIC_INFO();
        /**
         *  骑车人数量
         */
        public int              nNumOfCycling;
        /**
         *  骑车人特征,个数和nNumOfCycling关联
         */
        public NET_RIDER_INFO[] stuRiderList = new NET_RIDER_INFO[NetSDKLib.MAX_RIDER_NUM];
        /**
         *  全景广角图
         */
        public SCENE_IMAGE_INFO stuSceneImage = new SCENE_IMAGE_INFO();
        /**
         *  人脸全景广角图
         */
        public FACE_SCENE_IMAGE stuFaceSceneImage = new FACE_SCENE_IMAGE();
        /**
         *  检测到的人脸数量
         */
        public int              nNumOfFace;
        /**
         *  物体速度，单位为km/h
         */
        public float            fSpeed;
        /**
         *  非机动车特征值数据在二进制数据中的位置信息
         */
        public NET_NONMOTOR_FEATURE_VECTOR_INFO stuNonMotorFeatureVectorInfo = new NET_NONMOTOR_FEATURE_VECTOR_INFO();
        /**
         *  非机动车特征值版本号
         */
        public int              emNonMotorFeatureVersion;
        /**
         *  非机动车牌信息
         */
        public NET_NONMOTOR_PLATE_INFO stuNomotorPlateInfo = new NET_NONMOTOR_PLATE_INFO();
        /**
         *  物体型心(不是包围盒中心), 0-8191相对坐标, 相对于大图
         */
        public NET_POINT        stuObjCenter = new NET_POINT();
        /**
         *  人脸特征值数据在二进制数据中的位置信息, 废弃
         */
        public NET_FACE_FEATURE_VECTOR_INFO stuFaceFeatureVectorInfo = new NET_FACE_FEATURE_VECTOR_INFO();
        /**
         *  人脸特征值版本号, 废弃
         */
        public int              emFaceFeatureVersion;
        /**
         非机动车类型置信度
         */
        public			int            nCategoryConf;
        /**
         非机动车特征值版本号-字符串
         */
        public			byte[]         szNonMotorFeatureVersion = new byte[32];
        /**
         非机动车的角度 {@link EM_OBJECT_NONMOTORANGLE_TYPE}
         */
        public			int            emNonMotorAngle;
        /**
         非机动车车篮 {@link EM_OBJECT_BASKET_TYPE}
         */
        public			int            emBasket;
        /**
         非机动车后备箱 {@link EM_OBJECT_STORAGEBOX_TYPE}
         */
        public			int            emStorageBox;
        /**
         非机动车完整度评分，范围[0,100]，越大越完整
         */
        public			int            nCompleteScore;
        /**
         非机动车清晰度分值 取值范围为[1,100], 越大越清晰, 0为无效值
         */
        public			int            nClarityScore;
        /**
         目标出现的帧号
         */
        public			int            nStartSequence;
        /**
         目标消失的帧号
         */
        public			int            nEndSequence;
        /**
         非机动车车身及骑手整体，是否虚检，0: 否，1: 是
         */
        public			int            bIsErrorDetect;
        /**
         图像成像光源类型, 0:未知, 1:可见光成像, 2:近红外成像(灰度图), 3:热红外成像(伪彩色)
         */
        public			int            nImageLightType;
        /**
         非机动车综合质量评分，范围[0,100]，越大质量越好
         */
        public			int            nAbsScore;
        /**
         雨棚（伞）类型 {@link EM_RAIN_SHED_TYPE}
         */
        public			int            emRainShedType;
        /**
         智能物体全局唯一物体标识
         有效数据位21位，包含’\0’
         前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
         中间14位YYYYMMDDhhmmss:年月日时分秒
         后5位%u%u%u%u%u：物体ID，如00001
         */
        public			byte[]         szSerialUUID = new byte[22];
        /**
         对齐
         */
        public			byte[]         szReserved = new byte[2];
        /**
         非机动车的骑手和车身是否单独提取, 0:否, 1:是
         */
        public          int     nHumanFeatureExtractSingle;
        /**
         结构化非机动车支持人体图
         */
        public          SCENE_IMAGE_INFO stuHumanImage = new SCENE_IMAGE_INFO();
        /**
         保留
         */
        public			byte[]         byReserved = new byte[2848];

        public VA_OBJECT_NONMOTOR(){
            for(int i=0;i<stuRiderList.length;i++){
                stuRiderList[i]=new NET_RIDER_INFO();
            }

        }
    }

    // 非机动车配牌信息
    public static class NET_NONMOTOR_PLATE_INFO extends SdkStructure
    {
        public byte[]           szPlateNumber = new byte[128];        // 非机动车车牌号
        public NET_RECT         stuBoundingBox;                       // 包围盒， 非机动车矩形框，0~8191相对坐标
        public NET_RECT         stuOriginalBoundingBox;               // 包围盒， 非机动车矩形框，绝对坐标
        public NET_NONMOTOR_PLATE_IMAGE stuPlateImage;                // 非机动车车牌抠图
        public int              emPlateColor;                         // 车牌颜色
        public byte[]           byReserved = new byte[132];           // 保留
    }

    // 非机动车车牌图片信息
    public static class NET_NONMOTOR_PLATE_IMAGE extends SdkStructure
    {
        public int              nOffset;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[508];           // 保留字节
    }

    public static class NET_NONMOTOR_FEATURE_VECTOR_INFO extends SdkStructure {
        public int              nOffset;                              // 非机动车特征值在二进制数据中的偏移, 单位:字节
        public int              nLength;                              // 非机动车特征值数据长度, 单位:字节
        public int              bFeatureEnc;                          //用于标识特征值是否加密
        public byte[]           byReserved = new byte[28];            // 保留
    }

    public static class EM_FEATURE_VERSION extends SdkStructure {
        public static final int   EM_FEATURE_VERSION_UNKNOWN = 0;       // 未知
        public static final int   EM_FEATURE_VERSION_FACE_LARGE_1_01_001 = 1; // 人脸，大模型，1.01.001
        public static final int   EM_FEATURE_VERSION_FACE_LARGE_1_02_001 = 2; // 人脸，大模型，1.02.001
        public static final int   EM_FEATURE_VERSION_FACE_LARGE_1_03_001 = 3; // 人脸，大模型，1.03.001
        public static final int   EM_FEATURE_VERSION_FACE_LARGE_1_04_001 = 4; // 人脸，大模型，1.04.001
        public static final int   EM_FEATURE_VERSION_FACE_MIDDLE_1_01_002 = 31; // 人脸，中模型，1.01.002
        public static final int   EM_FEATURE_VERSION_FACE_MIDDLE_1_02_002 = 32; // 人脸，中模型，1.02.002
        public static final int   EM_FEATURE_VERSION_FACE_MIDDLE_1_03_002 = 33; // 人脸，中模型，1.03.002
        public static final int   EM_FEATURE_VERSION_FACE_MIDDLE_1_04_002 = 34; // 人脸，中模型，1.04.002
        public static final int   EM_FEATURE_VERSION_FACE_SMALL_1_01_003 = 61; // 人脸，小模型，1.01.003
        public static final int   EM_FEATURE_VERSION_FACE_SMALL_1_02_003 = 62; // 人脸，小模型，1.02.003
        public static final int   EM_FEATURE_VERSION_HUMAN_NONMOTOR = 91; // 人和非机动车，全局无版本号
        public static final int   EM_FEATURE_VERSION_HUMAN_NONMOTOR_FLOAT_1_00_01 = 92; // 人和非机动车，全局浮点，1.00.01
        public static final int   EM_FEATURE_VERSION_HUMAN_NONMOTOR_HASH_1_00_01 = 93; // 人和非机动车，全局哈希，1.00.01
        public static final int   EM_FEATURE_VERSION_HUMAN_NONMOTOR_FLOAT_1_01_00 = 94; // 人和非机动车，全局浮点，1.01.00
        public static final int   EM_FEATURE_VERSION_HUMAN_NONMOTOR_HASH_1_01_00 = 95; // 人和非机动车，全局哈希，1.01.00
        public static final int   EM_FEATURE_VERSION_TRAFFIC = 121;     // 机动车，全局无版本号
        public static final int   EM_FEATURE_VERSION_TRAFFIC_FLOAT = 122; // 机动车，全局浮点版本号0
        public static final int   EM_FEATURE_VERSION_TRAFFIC_FLOAT_1_00_01 = 123; // 机动车，全局浮点版本号1.00.01
        public static final int   EM_FEATURE_VERSION_TRAFFIC_HASH_1_00_01 = 124; // 机动车，全局哈希版本号1.00.01
        public static final int   EM_FEATURE_VERSION_TRAFFIC_FLOAT_1_00_02 = 125; // 机动车，全局浮点版本号1.00.02
        public static final int   EM_FEATURE_VERSION_TRAFFIC_HASH_1_00_02 = 126; // 机动车，全局哈希版本号1.00.02
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_1_5_0 = 151; // 商汤，人脸，1.5.0
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_1_8_1 = 152; // 商汤，人脸，1.8.1
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_2_1_3 = 153; // 商汤，人脸，2.1.3
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_2_39_6 = 154; // 商汤，人脸，2.39.6
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_2_39_7 = 155; // 商汤，人脸，2.39.7
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_2_39_8 = 156; // 商汤，人脸，2.39.8
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_239 = 157; // 商汤，人脸，239
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_242 = 158; // 商汤，人脸，242
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_244 = 159; // 商汤，人脸，244
        public static final int   EM_FEATURE_VERSION_SHANGTANG_FACE_245 = 160; // 商汤，人脸，245
        public static final int   EM_FEATURE_VERSION_SHENMO_HUMAN_TRAFFIC_NON_2_4_2 = 181; // 深瞐，人脸/机动车/非机动车，2.4.2
        public static final int   EM_FEATURE_VERSION_SHENMO_HUMAN_TRAFFIC_NON_2_5_7 = 182; // 深瞐，人脸/机动车/非机动车，2.5.7
    }

    // 非机动车抠图信息
    public static class NET_NONMOTOR_PIC_INFO extends SdkStructure
    {
        public int              uOffset;                              // 在二进制数据块中的偏移
        public int              uLength;                              // 图片大小,单位：字节
        public int              uWidth;                               // 图片宽度
        public int              uHeight;                              // 图片高度
        public byte[]           szFilePath = new byte[MAX_PATH_LEN];  // 文件路径
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[508];           // 保留
    }

    // 骑车人脸图片信息
    public static class RIDER_FACE_IMAGE_INFO extends SdkStructure
    {
        public int              uOffset;                              // 在二进制数据块中的偏移
        public int              uLength;                              // 图片大小,单位：字节
        public int              uWidth;                               // 图片宽度
        public int              uHeight;                              // 图片高度
        public int              nIndexInData;                         //图片的序号
        public byte[]           byReserved = new byte[44];            // 保留
    }

    // 斜视状态
    public static class EM_STRABISMUS_TYPE extends SdkStructure
    {
        public static final int   EM_STRABISMUS_UNKNOWN = 0;            // 未知
        public static final int   EM_STRABISMUS_NORMAL = 1;             // 正常
        public static final int   EM_STRABISMUS_YES = 2;                // 斜视
    }

    // 是否带眼镜
    public static class EM_HAS_GLASS extends SdkStructure
    {
        public static final int   EM_HAS_GLASS_UNKNOWN = 0;             // 未知
        public static final int   EM_HAS_GLASS_NO = 1;                  // 未戴
        public static final int   EM_HAS_GLASS_NORMAL = 2;              // 戴普通眼镜
        public static final int   EM_HAS_GLASS_SUN = 3;                 // 戴太阳眼镜
        public static final int   EM_HAS_GLASS_BLACK = 4;               // 戴黑框眼镜
    }

    // 人脸属性
    public static class NET_FACE_ATTRIBUTE_EX extends SdkStructure
    {
        public int              emSex;                                // 性别,参考EM_SEX_TYPE
        public int              nAge;                                 // 年龄,-1表示该字段数据无效
        public byte[]           szReserved = new byte[4];             //
        public int              emEye;                                // 眼睛状态，参考EM_EYE_STATE_TYPE
        public int              emMouth;                              // 嘴巴状态，参考EM_MOUTH_STATE_TYPE
        public int              emMask;                               // 口罩状态， 参考EM_MASK_STATE_TYPE
        public int              emBeard;                              // 胡子状态， EM_BEARD_STATE_TYPE
        public int              nAttractive;                          // 魅力值, 0未识别，识别时范围1-100,得分高魅力高
        public int              emGlass;                              // 眼镜，参考EM_HAS_GLASS
        public int              emEmotion;                            // 表情，参考EM_EMOTION_TYPE
        public DH_RECT          stuBoundingBox;                       // 包围盒(8192坐标系)
        public byte[]           byReserved1 = new byte[4];            // 保留
        public int              emStrabismus;                         // 斜视状态，EM_STRABISMUS_TYPE
        public int[]            nAngle = new int[3];                  //目标, 三个角度依次分别是, Pitch（仰俯角）, 指抬头低头的角度, 范围是-70~60; , yaw（偏航角）, 指左右转头的角度, 范围是-90~90; , Roll（翻滚角）, 指左右倾斜的角度, 范围是-90~90;, [180,180,180]表示未识别到角度
        public NET_POINT        stuObjCenter = new NET_POINT();       //物体型心(不是包围盒中心), 0-8191相对坐标, 相对于大图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_POINT}
        public byte[]           byReserved = new byte[48];            // 保留
    }

    // 骑车人信息
    public static class NET_RIDER_INFO extends SdkStructure
    {
        public int              bFeatureValid;                        // 是否识别到特征信息, TRUE时下面数据才有效, 1-true; 0-false
        public int              emSex;                                // 性别, 对应枚举  EM_SEX_TYPE
        public int              nAge;                                 // 年龄
        public int              emHelmet;                             // 头盔状态, 对应枚举  EM_NONMOTOR_OBJECT_STATUS
        public int              emCall;                               // 是否在打电话, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emBag;                                // 是否有背包, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emCarrierBag;                         // 有没有手提包, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emUmbrella;                           // 是否打伞, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emGlasses;                            // 是否有带眼镜, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emMask;                               // 是否带口罩, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int              emEmotion;                            // 表情, 对应枚举 EM_EMOTION_TYPE
        public int              emUpClothes;                          // 上衣类型, 对应枚举 EM_CLOTHES_TYPE
        public int              emDownClothes;                        // 下衣类型, 对应枚举 EM_CLOTHES_TYPE
        public int              emUpperBodyColor;                     // 上衣颜色, 对应枚举 EM_OBJECT_COLOR_TYPE
        public int              emLowerBodyColor;                     // 下衣颜色, 对应枚举 EM_OBJECT_COLOR_TYPE
        public int              bHasFaceImage;                        // 是否有骑车人人脸抠图信息
        public RIDER_FACE_IMAGE_INFO stuFaceImage = new RIDER_FACE_IMAGE_INFO(); // 骑车人人脸特写描述
        public int              bHasFaceAttributes;                   // 是否有人脸属性
        public NET_FACE_ATTRIBUTE_EX stuFaceAttributes = new NET_FACE_ATTRIBUTE_EX(); // 人脸属性
        public int              emHasHat;                             //是否戴帽子,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_HAS_HAT}
        public int              emCap;                                //帽类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_CAP_TYPE}
        public int              emHairStyle;                          //头发样式,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_HAIR_STYLE}
        public NET_FACE_FEATURE_VECTOR_INFO stuFaceFeatureVectorInfo = new NET_FACE_FEATURE_VECTOR_INFO(); //目标特征值数据在二进制数据中的位置信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FACE_FEATURE_VECTOR_INFO}
        public int              emFaceFeatureVersion;                 //目标特征值版本号,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_FEATURE_VERSION}
        public NET_HUMAN_FEATURE_VECTOR_INFO stuHumanFeatureVectorInfo = new NET_HUMAN_FEATURE_VECTOR_INFO(); //人体特征值数据在二进制数据中的位置信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_HUMAN_FEATURE_VECTOR_INFO}
        public int              emHumanFeatureVersion;                //人体特征值版本号,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_FEATURE_VERSION}
        public int              nAgeConf;                             //年龄段置信度
        public int              nUpColorConf;                         //上衣颜色置信度
        public int              nDownColorConf;                       //下衣颜色置信度
        public int              nUpTypeConf;                          //上衣种类置信度
        public int              nDownTypeConf;                        //下衣种类置信度
        public int              nHatTypeConf;                         //帽子类型置信度
        public int              nHairTypeConf;                        //发型种类置信度
        public int              emUpperPattern;                       //上半身衣服图案,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_CLOTHES_PATTERN}
        public int              nUpClothes;                           //上衣类型 0:未知 1:长袖 2:短袖 3:长款大衣 4:夹克及牛仔服 5:T恤,6:运动装 7:羽绒服 8:衬衫 9:连衣裙 10:西装 11:毛衣 12:无袖 13:背心
        public int              emUniformStyle;                       //制服类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_UNIFORM_STYLE}
        public int              nRainCoat;                            //是否有雨披 0:未识别 1:无 2:有
        public int              emCoatStyle;                          //上衣款式,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_COAT_TYPE}
        public int              emAgeSeg;                             //年龄段,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_AGE_SEG}
        public int              nShoulderBag;                         //是否有肩包 0-未识别 1-无 2-有
        public int              nMessengerBag;                        //是否有斜挎包 0-未识别 1-无 2-有
        public int              bNewUpClothes;                        //是否支持新上衣类型
        public int              emNewUpClothes;                       //新上衣类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_NEWUPCLOTHES_TYPE}
        public int              bNewDownClothes;                      //是否支持新下衣类型
        public int              emNewDownClothes;                     //新下衣类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_NEWDOWNCLOTHES_TYPE}
        public byte[]           byReserved = new byte[140];           // 保留
    }

    // 性别
    public static class EM_SEX_TYPE extends SdkStructure
    {
        public static final int   EM_SEX_TYPE_UNKNOWN = 0;              //未知
        public static final int   EM_SEX_TYPE_MALE = 1;                 //男性
        public static final int   EM_SEX_TYPE_FEMALE = 2;               //女性
    }

    // 事件/物体状态
    public static class EM_NONMOTOR_OBJECT_STATUS extends SdkStructure
    {
        public static final int   EM_NONMOTOR_OBJECT_STATUS_UNKNOWN = 0; // 未识别
        public static final int   EM_NONMOTOR_OBJECT_STATUS_NO = 1;     // 否
        public static final int   EM_NONMOTOR_OBJECT_STATUS_YES = 2;    // 是
    }

    // 表情
    public static class EM_EMOTION_TYPE extends SdkStructure
    {
        public static final int   EM_EMOTION_TYPE_UNKNOWN = 0;          // 未知
        public static final int   EM_EMOTION_TYPE_NORMAL = 1;           // 普通/正常
        public static final int   EM_EMOTION_TYPE_SMILE = 2;            // 微笑
        public static final int   EM_EMOTION_TYPE_ANGER = 3;            // 愤怒
        public static final int   EM_EMOTION_TYPE_SADNESS = 4;          // 悲伤
        public static final int   EM_EMOTION_TYPE_DISGUST = 5;          // 厌恶
        public static final int   EM_EMOTION_TYPE_FEAR = 6;             // 害怕
        public static final int   EM_EMOTION_TYPE_SURPRISE = 7;         // 惊讶
        public static final int   EM_EMOTION_TYPE_NEUTRAL = 8;          // 正常
        public static final int   EM_EMOTION_TYPE_LAUGH = 9;            // 大笑
        public static final int   EM_EMOTION_TYPE_HAPPY = 10;           // 高兴
        public static final int   EM_EMOTION_TYPE_CONFUSED = 11;        // 困惑
        public static final int   EM_EMOTION_TYPE_SCREAM = 12;          // 尖叫
        public static final int   EM_EMOTION_TYPE_CALMNESS = 13;        // 平静
    }

    public static class EM_CLOTHES_TYPE extends SdkStructure
    {
        public static final int   EM_CLOTHES_TYPE_UNKNOWN = 0;          //未知
        public static final int   EM_CLOTHES_TYPE_LONG_SLEEVE = 1;      //长袖
        public static final int   EM_CLOTHES_TYPE_SHORT_SLEEVE = 2;     //短袖
        public static final int   EM_CLOTHES_TYPE_TROUSERS = 3;         //长裤
        public static final int   EM_CLOTHES_TYPE_SHORTS = 4;           //短裤
        public static final int   EM_CLOTHES_TYPE_SKIRT = 5;            //裙子
        public static final int   EM_CLOTHES_TYPE_WAISTCOAT = 6;        //背心
        public static final int   EM_CLOTHES_TYPE_MINIPANTS = 7;        //超短裤
        public static final int   EM_CLOTHES_TYPE_MINISKIRT = 8;        //超短裙
    }

    // 非机动车子类型
    public static class EM_CATEGORY_NONMOTOR_TYPE extends SdkStructure
    {
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_TRICYCLE = 1; // "Tricycle" 三轮车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_MOTORCYCLE = 2; // "Motorcycle" 摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_NON_MOTOR = 3; // "Non-Motor"非机动车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_BICYCLE = 4; // "Bicycle" 自行车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_DUALTRIWHEELMOTORCYCLE = 5; // "DualTriWheelMotorcycle"两、三轮摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_LIGHTMOTORCYCLE = 6; // "LightMotorcycle" 轻便摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_EMBASSYMOTORCYCLE = 7; // "EmbassyMotorcycle "使馆摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_MARGINALMOTORCYCLE = 8; // "MarginalMotorcycle "领馆摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_AREAOUTMOTORCYCLE = 9; // "AreaoutMotorcycle "境外摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_FOREIGNMOTORCYCLE = 10; // "ForeignMotorcycle "外籍摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_TRIALMOTORCYCLE = 11; // "TrialMotorcycle "试验摩托车
        public static final int   EM_CATEGORY_NONMOTOR_TYPE_COACHMOTORCYCLE = 12; // "CoachMotorcycle "教练摩托车
    }

    // 颜色类型
    public static class EM_OBJECT_COLOR_TYPE extends SdkStructure
    {
        public static final int   EM_OBJECT_COLOR_TYPE_UNKNOWN = 0;     // 未知
        public static final int   EM_OBJECT_COLOR_TYPE_WHITE = 1;       // 白色
        public static final int   EM_OBJECT_COLOR_TYPE_ORANGE = 2;      // 橙色
        public static final int   EM_OBJECT_COLOR_TYPE_PINK = 3;        // 粉色
        public static final int   EM_OBJECT_COLOR_TYPE_BLACK = 4;       // 黑色
        public static final int   EM_OBJECT_COLOR_TYPE_RED = 5;         // 红色
        public static final int   EM_OBJECT_COLOR_TYPE_YELLOW = 6;      // 黄色
        public static final int   EM_OBJECT_COLOR_TYPE_GRAY = 7;        // 灰色
        public static final int   EM_OBJECT_COLOR_TYPE_BLUE = 8;        // 蓝色
        public static final int   EM_OBJECT_COLOR_TYPE_GREEN = 9;       // 绿色
        public static final int   EM_OBJECT_COLOR_TYPE_PURPLE = 10;     // 紫色
        public static final int   EM_OBJECT_COLOR_TYPE_BROWN = 11;      // 棕色
        public static final int   EM_OBJECT_COLOR_TYPE_SLIVER = 12;     // 银色
        public static final int   EM_OBJECT_COLOR_TYPE_DARKVIOLET = 13; // 暗紫罗兰色
        public static final int   EM_OBJECT_COLOR_TYPE_MAROON = 14;     // 栗色
        public static final int   EM_OBJECT_COLOR_TYPE_DIMGRAY = 15;    // 暗灰色
        public static final int   EM_OBJECT_COLOR_TYPE_WHITESMOKE = 16; // 白烟色
        public static final int   EM_OBJECT_COLOR_TYPE_DARKORANGE = 17; // 深橙色
        public static final int   EM_OBJECT_COLOR_TYPE_MISTYROSE = 18;  // 浅玫瑰色
        public static final int   EM_OBJECT_COLOR_TYPE_TOMATO = 19;     // 番茄红色
        public static final int   EM_OBJECT_COLOR_TYPE_OLIVE = 20;      // 橄榄色
        public static final int   EM_OBJECT_COLOR_TYPE_GOLD = 21;       // 金色
        public static final int   EM_OBJECT_COLOR_TYPE_DARKOLIVEGREEN = 22; // 暗橄榄绿色
        public static final int   EM_OBJECT_COLOR_TYPE_CHARTREUSE = 23; // 黄绿色
        public static final int   EM_OBJECT_COLOR_TYPE_GREENYELLOW = 24; // 绿黄色
        public static final int   EM_OBJECT_COLOR_TYPE_FORESTGREEN = 25; // 森林绿色
        public static final int   EM_OBJECT_COLOR_TYPE_SEAGREEN = 26;   // 海洋绿色
        public static final int   EM_OBJECT_COLOR_TYPE_DEEPSKYBLUE = 27; // 深天蓝色
        public static final int   EM_OBJECT_COLOR_TYPE_CYAN = 28;       // 青色
        public static final int   EM_OBJECT_COLOR_TYPE_OTHER = 29;      // 无法识别
    }

    public static class NET_SEAT_INFO extends SdkStructure
    {
        public NET_RECT         stuFaceRect;                          // 人脸矩形框信息(8192坐标系)
        public byte             bySunShade;                           // 遮阳板状态 0: 未知 1：无遮阳板 2：有遮阳板
        public byte             byDriverCalling;                      // 打电话状态 0: 未知 1：未打电话 2：打电话
        public byte             byDriverSmoking;                      // 抽烟状态	0: 未知 1：未吸烟 2：吸烟
        public byte             bySafeBelt;                           // 安全带状态	0: 未知 1：未系安全带 2：系安全带
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    public static class NET_VEHICLE_ATTACH extends SdkStructure
    {
        public int              nType;                                // 附件类型	0-未知  1-年检标志  2-挂件  3-纸巾盒  4-香水盒
        public NET_RECT         stuBoundingBox;                       // 包围盒信息(8192坐标系)
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    //卡口事件专用上报内容，需求增加到Custom下
    public static class EVENT_JUNCTION_CUSTOM_INFO extends SdkStructure
    {
        public EVENT_CUSTOM_WEIGHT_INFO stuWeightInfo;                // 原始图片信息
        public int              nCbirFeatureOffset;                   // 数据偏移，单位字节 （由于结构体保留字节有限的限制,添加在此处， 下同）
        public int              nCbirFeatureLength;                   // 数据大小，单位字节
        public int              dwVehicleHeadDirection;               // 车头朝向 0:未知 1:左 2:中 3:右
        public int              nAvailableSpaceNum;                   // 停车场车位余位数量
        public NET_RADAR_FREE_STREAM stuRadarFreeStream;              // 雷达自由流信息
        public NET_CUSTOM_MEASURE_TEMPER stuMeasureTemper;            // 测温信息
        public byte[]           bReserved = new byte[12];             // 预留字节
    }

    // 雷达自由流信息
    public static class NET_RADAR_FREE_STREAM extends SdkStructure
    {
        public long             nABSTime;                             // 1年1月1日0时起至今的毫秒数
        public int              nVehicleID;                           // 车辆ID
        public int              unOBUMAC;                             // OBU的MAC地址
    }

    //称重信息
    public static class EVENT_CUSTOM_WEIGHT_INFO extends SdkStructure
    {
        public int              dwRoughWeight;                        // 毛重,车辆满载货物重量。单位KG
        public int              dwTareWeight;                         // 皮重,空车重量。单位KG
        public int              dwNetWeight;                          // 净重,载货重量。单位KG
        public byte[]           bReserved = new byte[28];             // 预留字节
    }

    // 事件类型 EVENT_IVS_TRAFFICGATE(交通卡口老规则事件/线圈电警上的交通卡口老规则事件)对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO和EVENT_IVS_TRAFFICGATE要一起处理,以防止有视频电警和线圈电警同时接入平台的情况发生
    // 另外 EVENT_IVS_TRAFFIC_TOLLGATE 只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFICGATE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte             byOpenStrobeState;                    // 开闸状态,具体请见EM_OPEN_STROBE_STATE
        public byte             bReserved1[] = new byte[3];           // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nLane;                                // 对应车道号
        public int              nSpeed;                               // 车辆实际速度Km/h
        public int              nSpeedUpperLimit;                     // 速度上限 单位：km/h
        public int              nSpeedLowerLimit;                     // 速度下限 单位：km/h
        public int              dwBreakingRule;                       // 违反规则掩码,第一位:逆行;
        // 第二位:压线行驶; 第三位:超速行驶;
        // 第四位：欠速行驶; 第五位:闯红灯;第六位:穿过路口(卡口事件)
        // 第七位: 压黄线; 第八位: 有车占道; 第九位: 黄牌占道;否则默认为:交通卡口事件
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public byte             szManualSnapNo[] = new byte[64];      // 手动抓拍序号
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[3];             // 保留字节
        public byte[]           szSnapFlag = new byte[16];            // 设备产生的抓拍标识
        public byte             bySnapMode;                           // 抓拍方式,0-未分类 1-全景 2-近景 4-同向抓拍 8-反向抓拍 16-号牌图像
        public byte             byOverSpeedPercentage;                // 超速百分比
        public byte             byUnderSpeedingPercentage;            // 欠速百分比
        public byte             byRedLightMargin;                     // 红灯容许间隔时间,单位：秒
        public byte             byDriveDirection;                     // 行驶方向,0-上行(即车辆离设备部署点越来越近),1-下行(即车辆离设备部署点越来越远)
        public byte[]           szRoadwayNo = new byte[32];           // 道路编号
        public byte[]           szViolationCode = new byte[16];       // 违章代码
        public byte[]           szViolationDesc = new byte[128];      // 违章描述
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte[]           szVehicleType = new byte[32];         // 车辆大小类型 Minisize"微型车,"Light-duty"小型车,"Medium"中型车,
        // "Oversize"大型车,"Huge"超大车,"Largesize"长车 "Unknown"未知
        public byte             byVehicleLenth;                       // 车辆长度, 单位米
        public byte             byLightState;                         // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte             byReserved1;                          // 保留字节,留待扩展
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nOverSpeedMargin;                     // 限高速宽限值    单位：km/h
        public int              nUnderSpeedMargin;                    // 限低速宽限值    单位：km/h
        public byte[]           szDrivingDirection = new byte[3*NET_MAX_DRIVINGDIRECTION]; //
        // "DrivingDirection" : ["Approach", "", ""],行驶方向
        // "Approach"-上行,即车辆离设备部署点越来越近；"Leave"-下行,
        // 即车辆离设备部署点越来越远,第二和第三个参数分别代表上行和
        // 下行的两个地点,UTF-8编码
        public byte[]           szMachineName = new byte[256];        // 本地或远程设备名称
        public byte[]           szMachineAddress = new byte[256];     // 机器部署地点、道路编码
        public byte[]           szMachineGroup = new byte[256];       // 机器分组、设备所属单位
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_SIG_CARWAY_INFO_EX stuSigInfo;                     // 由车检器产生抓拍信号冗余信息
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径
        public NET_TIME_EX      RedLightUTC;                          // 红灯开始UTC时间
        public Pointer          szDeviceAddress;                      // 设备地址,OSD叠加到图片上的,来源于配置TrafficSnapshot.DeviceAddress,'\0'结束
        public float            fActualShutter;                       // 当前图片曝光时间,单位为毫秒
        public byte             byActualGain;                         // 当前图片增益,范围为0~100
        public byte             byDirection;                          // 0-南向北 1-西南向东北 2-西向东 3-西北向东南 4-北向南 5-东北向西南 6-东向西 7-东南向西北 8-未知
        public byte             bReserve;                             // 保留字节, 字节对齐
        public byte             bRetCardNumber;                       // 卡片个数
        public EVENT_CARD_INFO[] stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM); // 卡片信息
        public byte[]           szDefendCode = new byte[NET_COMMON_STRING_64]; // 图片防伪码
        public int              nTrafficBlackListID;                  // 关联禁止名单数据库记录默认主键ID, 0,无效；> 0,禁止名单数据记录
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public byte[]           bReserved = new byte[452];            // 保留字节,留待扩展.
    }

    //事件类型EVENT_IVS_TRAFFIC_RUNREDLIGHT(交通-闯红灯事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_RUNREDLIGHT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLightState;                          // 红绿灯状态 0:未知 1：绿灯 2:红灯 3:黄灯
        public int              nSpeed;                               // 车速,km/h
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_TIME_EX      stRedLightUTC;                        // 红灯开始时间
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte             byRedLightMargin;                     // 红灯容许间隔时间,单位：秒
        public byte[]           byAlignment = new byte[3];            // 字节对齐
        public int              nRedLightPeriod;                      // 表示红灯周期时间,单位毫秒
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[928];            // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public int              bHasNonMotor;                         // 是否有非机动车对象
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型EVENT_IVS_TRAFFIC_OVERLINE(交通-压线事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERLINE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[964-2*POINTERSIZE]; // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    // 事件类型EVENT_IVS_TRAFFIC_RETROGRADE(交通-逆行事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_RETROGRADE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              bIsExistAlarmRecord;                  // rue:有对应的报警录像; false:无对应的报警录像
        public int              dwAlarmRecordSize;                    // 录像大小
        public byte[]           szAlarmRecordPath = new byte[NET_COMMON_STRING_256]; // 录像路径
        public EVENT_INTELLI_COMM_INFO intelliCommInfo;               // 智能事件公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[484];            // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public int              nDetectNum;                           // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public int              bHasNonMotor;                         // 是否有非机动车对象
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型EVENT_IVS_TRAFFIC_OVERSPEED(交通超速事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERSPEED_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSpeed;                               // 车辆实际速度Km/h
        public int              nSpeedUpperLimit;                     // 速度上限 单位：km/h
        public int              nSpeedLowerLimit;                     // 速度下限 单位：km/h
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public int              nSpeedingPercentage;                  // 超速百分比
        public byte[]           bReserved = new byte[572];            // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_REGION_INFO  stRegionInfo;                         // 区间测速信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx;                  // 事件公共扩展字段结构体
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车信息
        public int              bHasNonMotor;                         // 是否有非机动车对象
    }

    //事件类型EVENT_IVS_TRAFFIC_UNDERSPEED(交通欠速事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_UNDERSPEED_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved2 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSpeed;                               // 车辆实际速度Km/h
        public int              nSpeedUpperLimit;                     // 速度上限 单位：km/h
        public int              nSpeedLowerLimit;                     // 速度下限 单位：km/h
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           bReserved1 = new byte[2];             // 对齐
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nUnderSpeedingPercentage;             // 欠速百分比
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[828-2*POINTERSIZE]; // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型EVENT_IVS_TRAFFIC_WRONGROUTE(交通违章-不按车道行驶)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_WRONGROUTE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nLane;                                // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nSpeed;                               // 车辆实际速度,km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[972];            // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    // 事件类型 EVENT_IVS_TRAFFIC_TURNLEFT(交通-违章左转)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_TURNLEFT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见  NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[964-2*POINTERSIZE]; // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public int              bHasNonMotor;                         // 是否有非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    // 事件类型 EVENT_IVS_TRAFFIC_TURNRIGHT (交通-违章右转)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_TURNRIGHT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见  NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[964-2*POINTERSIZE]; // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    // 事件类型EVENT_IVS_TRAFFIC_UTURN(违章调头事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_UTURN_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见  NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[964-2*POINTERSIZE]; // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型 EVENT_IVS_TRAFFIC_RUNYELLOWLIGHT(交通违章-闯黄灯事件)对应数据块描述信息
    public static class DEV_EVENT_TRAFFIC_RUNYELLOWLIGHT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLightState;                          // 红绿灯状态 0:未知 1：绿灯 2:红灯 3:黄灯
        public int              nSpeed;                               // 车速,km/h
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见  NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_TIME_EX      stYellowLightUTC;                     // 黄灯开始时间
        public int              nYellowLightPeriod;                   // 黄灯周期间隔时间,单位秒
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte             byRedLightMargin;                     // 黄灯容许间隔时间,单位：秒
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public byte[]           bReserved = new byte[1024];           // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
    }

    //事件类型EVENT_IVS_TRAFFIC_OVERYELLOWLINE(交通违章-压黄线)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERYELLOWLINE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nLane;                                // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nSpeed;                               // 车辆实际速度,km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              bIsExistAlarmRecord;                  // bool 类型： 1:有对应的报警录像; 0:无对应的报警录像
        public int              dwAlarmRecordSize;                    // 录像大小
        public byte[]           szAlarmRecordPath = new byte[NET_COMMON_STRING_256]; // 录像路径
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,指正对应结构体   NET_IMAGE_INFO_EX3的数组
        public int              nImageInfoNum;                        // 图片信息个数
        public byte[]           bReserved = new byte[528 - POINTERSIZE]; // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public int              nDetectNum;                           // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE(交通违章-黄牌车占道事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_YELLOWPLATEINLANE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nLane;                                // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nSpeed;                               // 车辆实际速度,km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[1016-POINTERSIZE]; // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    //事件类型 EVENT_IVS_TRAFFIC_VEHICLEINROUTE(有车占道事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_VEHICLEINROUTE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nLane;                                // 对应车道号
        public int              nSequence;                            // 抓拍序号,如3-2-1/0,1表示抓拍正常结束,0表示抓拍异常结束
        public int              nSpeed;                               // 车速
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 表示交通车辆的数据库记录
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved0 = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[884-POINTERSIZE];
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    //事件类型EVENT_IVS_TRAFFIC_CROSSLANE(交通违章-违章变道)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_CROSSLANE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nLane;                                // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nSpeed;                               // 车辆实际速度,km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuSceneImage;                       //全景图图片信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[836-POINTERSIZE]; // 保留字节,留待扩展.留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO(); //交通车辆信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO}
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();   //公共信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EVENT_COMM_INFO}
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    // 事件类型EVENT_IVS_TRAFFIC_NOPASSING(交通违章-禁止通行事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_NOPASSING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              UTCMS;                                //
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved1 = new byte[3];
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              nSource;                              // 视频分析的数据源地址
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           byReserved = new byte[984];           // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
    }

    //事件类型 EVENT_IVS_TRAFFIC_PEDESTRAINPRIORITY(斑马线行人优先事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PEDESTRAINPRIORITY_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public double           dInitialUTC;                          // 事件初始UTC时间    UTC为事件的UTC (1970-1-1 00:00:00)秒数。
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 表示交通车辆的数据库记录
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[984-POINTERSIZE]; // 保留字节,留待扩展.
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    //事件类型 EVENT_IVS_TRAFFIC_VEHICLEINBUSROUTE(占用公交车道事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_VEHICLEINBUSROUTE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public int              nSequence;                            // 抓拍序号,如3-2-1/0,1表示抓拍正常结束,0表示抓拍异常结束
        public int              nSpeed;                               // 车速,km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 表示交通车辆的数据库记录
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[980-POINTERSIZE]; // 保留字节,留待扩展.
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    //事件类型 EVENT_IVS_TRAFFIC_BACKING(违章倒车事件)对应的数据块描述信息
    public static class DEV_EVENT_IVS_TRAFFIC_BACKING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public int              nSequence;                            // 抓拍序号,如3-2-1/0,1表示抓拍正常结束,0表示抓拍异常结束
        public int              nSpeed;                               // 车速,km/h
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 表示交通车辆的数据库记录
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[848];            // 保留字节,留待扩展.
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
    }

    // GPS信息
    public static class NET_GPS_INFO extends SdkStructure
    {
        public int              nLongitude;                           // 经度(单位是百万分之一度)
        // 西经：0 - 180000000				实际值应为: 180*1000000 – dwLongitude
        // 东经：180000000 - 360000000		实际值应为: dwLongitude – 180*1000000
        // 如: 300168866应为（300168866 - 180*1000000）/1000000 即东经120.168866度
        public int              nLatidude;                            // 纬度(单位是百万分之一度)
        // 南纬：0 - 90000000				实际值应为: 90*1000000 – dwLatidude
        // 北纬：90000000 – 180000000		实际值应为: dwLatidude – 90*1000000
        // 如: 120186268应为 (120186268 - 90*1000000)/1000000 即北纬30. 186268度
        public double           dbAltitude;                           // 高度,单位为米
        public double           dbSpeed;                              // 速度,单位km/H
        public double           dbBearing;                            // 方向角,单位°
        public byte[]           bReserved = new byte[8];              // 保留字段

        protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
            int alignment = super.getNativeAlignment(type, value, isFirstElement);
            return Math.min(4, alignment);
        }

        @Override
        public String toString() {
            return "NET_GPS_INFO{" +
                    "nLongitude=" + nLongitude +
                    ", nLatidude=" + nLatidude +
                    ", dbAltitude=" + dbAltitude +
                    ", dbSpeed=" + dbSpeed +
                    ", dbBearing=" + dbBearing +
                    '}';
        }
    }

    // 事件类型 EVENT_IVS_TRAFFIC_OVERSTOPLINE (压停车线事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERSTOPLINE extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             byEventAction;                        // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved1 = new byte[2];
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              nSource;                              // 视频分析的数据源地址
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           byReserved = new byte[984];           // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public boolean          bHasNonMotor;                         // 	stuNonMotor 字段是否有效
        public VA_OBJECT_NONMOTOR stuNonMotor;                        // 非机动车对象
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型 EVENT_IVS_TRAFFIC_PARKINGONYELLOWBOX(黄网格线抓拍事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PARKINGONYELLOWBOX_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[8];             // 字节对齐
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nInterval1;                           // 第二张和第一张的延时时间,单位秒
        public int              nInterval2;                           // 第三张和第二张的延时时间,单位秒
        public int              nFollowTime;                          // 跟随时间,如果一辆车与前一辆车进入黄网格的时间差小于此值,就认为是跟车进入,跟车进入情况下如果停车则不算违章
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[984-POINTERSIZE]; // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //扩展协议字段,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
    }

    // 事件类型EVENT_IVS_TRAFFIC_WITHOUT_SAFEBELT(交通未系安全带事件事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_WITHOUT_SAFEBELT extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             byEventAction;                        // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;    public byte                    byReserved1[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved1 = new byte[2];
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              nSource;                              // 视频分析的数据源地址
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public int              emMainSeat;                           // 主驾驶座位安全带状态   参考 NET_SAFEBELT_STATE
        public int              emSlaveSeat;                          // 副驾驶座位安全带状态 参考 NET_SAFEBELT_STATE
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON , 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图图片信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[724-POINTERSIZE*2]; // 保留字节
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
    }

    //事件类型EVENT_IVS_TRAFFIC_JAM_FORBID_INTO(交通拥堵禁入事件)对应的数据块描述信息
    public static class DEV_EVENT_ALARM_JAMFORBIDINTO_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEveID;                               // 事件ID
        ///////////////////////////////以上为公共字段//////////////////////////////
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nSource;                              // 视频分析的数据源地址
        public int              nSequence;                            // 表示抓拍序号,如3-2-1/0,1表示抓拍正常结束,0表示抓拍异常结束
        public int              nFrameSequence;                       // 帧序号
        public int              nLane;                                // 车道号
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[984];            // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
    }

    //事件类型EVENT_IVS_TRAFFIC_PASSNOTINORDER(交通-未按规定依次通过)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PASSNOTINORDER_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nLane;                                // 对应车道号
        public NET_MSG_OBJECT   stuObject;                            // 车牌信息
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON , 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;        // 交通车辆信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           bReserved = new byte[984];            // 保留字节
    }

    /**
     * @author 260611
     * @description 事件类型EVENT_IVS_TRAFFIC_MANUALSNAP(交通手动抓拍事件)对应的数据块描述信息
     * @date 2023/01/11 15:02:23
     */
    public class DEV_EVENT_TRAFFIC_MANUALSNAP_INFO extends SdkStructure {
        /**
         * 通道号
         */
        public int              nChannelID;
        /**
         * 事件名称
         */
        public byte[]           szName = new byte[128];
        /**
         * 字节对齐
         */
        public byte[]           bReserved1 = new byte[4];
        /**
         * 时间戳(单位是毫秒)
         */
        public double           PTS;
        /**
         * 事件发生的时间
         */
        public NET_TIME_EX UTC = new NET_TIME_EX();
        /**
         * 事件ID
         */
        public int              nEventID;
        /**
         * 对应车道号
         */
        public int              nLane;
        /**
         * 手动抓拍序号
         */
        public byte[]           szManualSnapNo = new byte[64];
        /**
         * 检测到的物体
         */
        public NET_MSG_OBJECT   stuObject = new NET_MSG_OBJECT();
        /**
         * 检测到的车身信息
         */
        public NET_MSG_OBJECT   stuVehicle = new NET_MSG_OBJECT();
        /**
         * 表示交通车辆的数据库记录
         */
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar = new DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO();
        /**
         * 事件对应文件信息
         */
        public NET_EVENT_FILE_INFO stuFileInfo = new NET_EVENT_FILE_INFO();
        /**
         * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
         */
        public byte             bEventAction;
        /**
         * 开闸状态, 具体请见 EM_OPEN_STROBE_STATE
         */
        public byte             byOpenStrobeState;
        public byte[]           byReserved = new byte[1];
        /**
         * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
         */
        public byte             byImageIndex;
        /**
         * 抓图标志(按位),具体见NET_RESERVED_COMMON
         */
        public int              dwSnapFlagMask;
        /**
         * 对应图片的分辨率
         */
        public NET_RESOLUTION_INFO stuResolution = new NET_RESOLUTION_INFO();
        /**
         * 停车位数据信息数组实际有效大小
         */
        public int              nParkingInfoNum;
        /**
         * 保留字节,留待扩展.
         */
        public byte[]           bReserved = new byte[500];
        /**
         * 手动抓拍专用上报内容
         */
        public EVENT_MANUALSNAP_CUSTOM_DATA stuCustom = new EVENT_MANUALSNAP_CUSTOM_DATA();
        /**
         * 公共信息
         */
        public EVENT_COMM_INFO  stCommInfo = new EVENT_COMM_INFO();
        /**
         * 停车位数据信息
         */
        public EVENT_MANUALSNAP_PARKING_INFO[] stuParkingInfo = new EVENT_MANUALSNAP_PARKING_INFO[32];
        /**
         * 事件公共扩展字段结构体
         */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();

        public DEV_EVENT_TRAFFIC_MANUALSNAP_INFO() {
            for (int i = 0; i < stuParkingInfo.length; i++) {
                stuParkingInfo[i] = new EVENT_MANUALSNAP_PARKING_INFO();
            }
        }
    }

    // 手动抓拍专用上报内容
    public static class EVENT_MANUALSNAP_CUSTOM_DATA extends SdkStructure
    {
        public EVENT_CUSTOM_WEIGHT_INFO stuWeighInfo;                 // 称重信息
        public byte[]           byReserved = new byte[472];           // 保留字节
    }

    // 事件类型 EVENT_IVS_CROSSLINEDETECTION(警戒线事件)对应的数据块描述信息
    public static class DEV_EVENT_CROSSLINE_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_LINE_NUM); // 规则检测线
        public int              nDetectLineNum;                       // 规则检测线顶点数
        public NET_POINT[]      TrackLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_TRACK_LINE_NUM); // 物体运动轨迹
        public int              nTrackLineNum;                        // 物体运动轨迹顶点数
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             bDirection;                           // 表示入侵方向, 0-由左至右, 1-由右至左
        public byte[]           byReserved = new byte[1];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见  NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数, 类型为unsigned int
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public int              nObjetcHumansNum;                     // 检测到人的数量
        public NET_VAOBJECT_NUMMAN []stuObjetcHumans = (NET_VAOBJECT_NUMMAN [])new NET_VAOBJECT_NUMMAN().toArray(100); // 检测的到人
        public int              nRuleID;                              // 规则编号,用于标示哪个规则触发的事件，缺省时默认为0
        public int              emEventType;                          // 事件级别,参考EM_EVENT_LEVEL
        public NET_PRESET_POSITION stPosition;                        // 预置点的坐标和放大倍数
        public int              nVisibleHFOV;                         // 可见光横向视场角,单位度 实际角度乘以100
        public int              nVisibleVFOV;                         // 可见光纵向视场角,单位度 实际角度乘以100
        public int              nCurChannelHFOV;                      // 当前报警通道的横向视场角，单位度，实际角度乘以100
        public int              nCurChannelVFOV;                      // 当前报警通道的纵向视场角，单位度，实际角度乘以100
        public int              nImageNum;                            // 图片信息个数
        public Pointer          pImageArray;                          // 图片信息数组，结构体NET_IMAGE_INFO_EX2数组指针
        public int              nCarMirrorStatus;                     // 车的后视镜状态，-1: 未知, 0: 正常, 1: 不正常(如数量缺失等)
        public int              nCarLightStatus;                      // 车的车灯状态,-1: 未知, 0: 正常, 1:不正常(如灯未亮等)
        public int              nObjectBoatsNum;                      // 船只物体个数
        public NET_BOAT_OBJECT[] stuBoatObjects = new NET_BOAT_OBJECT[100]; // 船只物品信息
        public int              nUpDownGoing;                         // 车道/航道方向, 0:未知, 1:上行, 2:下行
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved1 = new byte[452];          // 预留字节
    }

    /**
     * 检测到的人信息
     * @author 29779
     */
    public static class NET_VAOBJECT_NUMMAN extends SdkStructure {
        public int              nObjectID;                            // 物体ID，每个ID表示一个唯一的物体
        /**
         * @link EM_UNIFORM_STYLE 制服样式
         */
        public int              emUniformStyle;
        public NET_RECT         stuBoundingBox;                       // 包围盒,手套对象在全景图中的框坐标,为0~8191相对坐标
        public NET_RECT         stuOriginalBoundingBox;               // 包围盒,绝对坐标
        public long             nQueueDuration;                       //排队时长
        public Pointer          pstuHumanAttributes;                  //人体属性信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.HUMAN_ATTRIBUTES_INFO}
        public Pointer          pstuHumanAttributesEx;                //人体属性信息扩展,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.HUMAN_ATTRIBUTES_INFO_EX}
        public Pointer          pstuFaceAttributes;                   //人脸属性,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_FACE_ATTRIBUTE}
        public byte[]           byReserved = new byte[120-3*POINTERSIZE]; // 预留字节
    }

    // 事件类型 EVENT_IVS_CROSSREGIONDETECTION(警戒区事件)对应的数据块描述信息
    public static class DEV_EVENT_CROSSREGION_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      TrackLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_TRACK_LINE_NUM); // 物体运动轨迹
        public int              nTrackLineNum;                        // 物体运动轨迹顶点数
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             bDirection;                           // 表示入侵方向, 0-进入, 1-离开,2-出现,3-消失
        public byte             bActionType;                          // 表示检测动作类型,0-出现 1-消失 2-在区域内 3-穿越区域
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON , 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数, unsigned int 类型
        public NET_CUSTOM_INFO  stuCustom;                            // 货物通道信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public  NET_PRESET_POSITION stPosition;                       // 预置点的坐标和放大倍数
        public int              nVisibleHFOV;                         // 可见光横向视场角,单位度 实际角度乘以100
        public int              nVisibleVFOV;                         // 可见光纵向视场角,单位度 实际角度乘以100
        public int              nCurChannelHFOV;                      // 当前报警通道的横向视场角，单位度，实际角度乘以100
        public int              nCurChannelVFOV;                      // 当前报警通道的纵向视场角，单位度，实际角度乘以100
        public byte[]           szRealEventType = new byte[32];       // 采用该字段区分是区域入侵还是事件，该字段不携带则是通用的区域入侵事件，携带则根据内容区分实际的事件类型，目前只有IllegalDumping（垃圾违规投放）
        public Pointer          pstuBoatObjectEx;                     //船只物品信息扩展，数量为nObjectBoatNum,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_BOAT_OBJECT_EXTERN}
        public int              nEventType;                           //0:未知 1：船舶事件 2：船名事件
        public byte[]           szLinkTargetUUID = new byte[128];     //雷达前端事件中的LinkTargetUUID字段信息
        public int              nReviewMode;                          //0:未知 1：TrackingMode跟踪复核模式 2：VideoMode视频模式
        public int              nVehicleCount;                        //车位检测区域内车辆数量
        public int              nIndexInDataInHumanImage;             //人体图片序号
        public int              nIndexInDataInFaceImage;              //人脸图片序号
        public int              nIndexInDataInFaceSceneImage;         //人脸全景图片序号
        public byte[]           szReserved3 = new byte[4];            //字节对齐
        public int              nPresetID;                            //事件触发的预置点号
        public byte[]           szPresetName = new byte[64];          //事件触发的预置点名称
        public int              bMisReport;                           //疑似误报标记，当存在这个字段且值为true时，表示此事件经过误报库检测后判断为误报事件
        public int              nSecondEvaluation;                    //二次研判状态,0:未知,1:待研判,2:正报,3:疑似误报,4:漏报
        public byte[]           bReserved = new byte[32-POINTERSIZE]; // 保留字节,留待扩展.
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjectIDs = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST); // 检测到的物体
        public int              nTrackNum;                            // 轨迹数(与检测到的物体个数  nObjectNum 对应)
        public NET_POLY_POINTS[] stuTrackInfo = (NET_POLY_POINTS[]) new NET_POLY_POINTS().toArray(NET_MAX_OBJECT_LIST); // 轨迹信息(与检测到的物体对应)
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public int              nObjetcHumansNum;                     // 检测到人的数量
        public NET_VAOBJECT_NUMMAN[] stuObjetcHumans = (NET_VAOBJECT_NUMMAN[])new NET_VAOBJECT_NUMMAN().toArray(100); // 检测的到人
        public NET_MSG_OBJECT   stuVehicle;                           //车身信息
        public int              emTriggerType;                        //触发类型,参考EM_TRIGGER_TYPE
        public int              nMark;                                // 标记抓拍帧
        public int              nSource;                              // 视频分析的数据源地址
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              emCaptureProcess;                     // 抓拍过程,参考EM_CAPTURE_PROCESS_END_TYPE
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar; //交通车辆信息
        public EVENT_COMM_INFO stuCommInfo;                 //公共信息
        public NET_PTZSPACE_UNNORMALIZED stuAbsPosition;              // 云台方向与放大倍数（扩大100倍表示）
		                                                                // 第一个元素为水平角度，0-36000；
		                                                                // 第二个元素为垂直角度，（-18000）-（18000）；
		                                                                // 第三个元素为显示放大倍数，0-MaxZoom*100
        public int              nHFovValue;                           // 对应倍率水平视场角, 	单位0.01度, 扩大100倍表示
        public double           dbFocusPosition;                      // 聚焦位置
        public int              nObjectBoatNum;                       //船只物体个数
        public NET_BOAT_OBJECT[] stuBoatObject = new NET_BOAT_OBJECT[100]; //船只物品信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_BOAT_OBJECT}
        public int              nImageNum;                            //图片信息个数
        public Pointer          pImageArray;                          //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX2}
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_EVENT_INFO_EXTEND}
        public Pointer          pstuObjectEx2;                        //检测到的物体扩展,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
        public Pointer          pstuObjectIDsEx2;                     //检测到的物体扩展,数量为nObjectNum,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
        public Pointer          pstuVehicleEx2;                       //车身信息扩展,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
        public NET_GPS_INFO     stuGPSInfo = new NET_GPS_INFO();      //GPS信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_GPS_INFO}
        public byte[]           szReserved = new byte[1024];          //保留字节,留待扩展.
    }

    // 事件类型 EVENT_IVS_WANDERDETECTION(徘徊事件)对应的数据块描述信息
    public static class DEV_EVENT_WANDER_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];             // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjectIDs = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST); // 检测到的物体
        public int              nTrackNum;                            // 轨迹数(与检测到的物体个数对应)
        public NET_POLY_POINTS[] stuTrackInfo = (NET_POLY_POINTS[]) new NET_POLY_POINTS().toArray(NET_MAX_OBJECT_LIST); // 轨迹信息(与检测到的物体对应)
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON , 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数, unsigned int 类型
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public short            nPreserID;                            // 事件触发的预置点号，从1开始（没有表示未知）
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public NET_POSTION      stuPostion;                           // 坐标与放大倍数
        public byte[]           byReserved2 = new byte[4];            // 字节对齐
        public int              nCurChannelHFOV;                      // 当前报警通道的横向视场角,单位：度，实际角度乘以100
        public int              nCurChannelVFOV;                      // 当前报警通道的垂直视场角,单位：度，实际角度乘以100
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[402];            // 保留字节,留待扩展.
    }

    //事件类型 EVENT_IVS_LEAVEDETECTION(离岗检测事件)对应数据块描述信息
    public static class DEV_EVENT_IVS_LEAVE_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              emTriggerMode;                        // 离岗的触发模式,参考EM_LEAVEDETECTION_TRIGGER_MODE
        public int              emState;                              // 检测状态,参考EM_LEAVEDETECTION_STATE
        public int              bSceneImage;                          // stuSceneImage 是否有效
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public byte[]           szUserName = new byte[32];            // 用户名称
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[50];             // 保留字节
    }

    //事件类型 EVENT_IVS_AUDIO_ABNORMALDETECTION(声音异常检测)对应数据块描述信息
    public static class DEV_EVENT_IVS_AUDIO_ABNORMALDETECTION_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nDecibel;                             // 声音强度
        public int              nFrequency;                           // 声音频率
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public byte[]           bReserved = new byte[1024];           // 保留字节,留待扩展.
    }

    //事件类型 EVENT_IVS_CLIMBDETECTION(攀高检测事件)对应数据块描述信息
    public static class DEV_EVENT_IVS_CLIMB_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public int              nDetectLineNum;                       // 规则检测线顶点数
        public NET_POINT[]      DetectLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_LINE_NUM); // 规则检测线
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nOccurrenceCount;                     // 事件触发累计次数, unsigned int
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public Pointer          pstuImageInfo;                        // 图片信息数组, refer to {@link NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        // 图片信息个数
        public byte[]           bReserved = new byte[886 - POINTERSIZE]; // 保留字节
    }

    // 事件类型 EVENT_IVS_FIGHTDETECTION(斗殴事件)对应的数据块描述信息
    public static class DEV_EVENT_FIGHT_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjectIDs = (NET_MSG_OBJECT[])new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST); // 检测到的物体列表
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];             // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = (NET_POINT[]) new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数, unsigned int 类型
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public byte[]           szSourceID = new byte[32];            // 事件关联ID。应用场景是同一个物体或者同一张图片做不同分析，产生的多个事件的SourceID相同
    															       // 缺省时为空字符串，表示无此信息
    															       // 格式：类型+时间+序列号，其中类型2位，时间14位，序列号5位
        public int              emActionType;                         // 动作类型   ,参考枚举EM_STEREO_ACTION_TYPE
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景广角图
        public Pointer          pstuImageInfo;                        // 图片信息数组,对应结构体NET_IMAGE_INFO_EX2的数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[240];            // 保留字节,留待扩展.
    }

    // 加油类型
    public static class EM_REFUEL_TYPE extends SdkStructure {
        public static final int   EM_REFUEL_TYPE_UNKNOWN = 0;           // unknown
        public static final int   EM_REFUEL_TYPE_NINETY_EIGHT = 1;      // "98#"
        public static final int   EM_REFUEL_TYPE_NINETY_SEVEN = 2;      // "97#"
        public static final int   EM_REFUEL_TYPE_NINETY_FIVE = 3;       // "95#"
        public static final int   EM_REFUEL_TYPE_NINETY_THREE = 4;      // "93#"
        public static final int   EM_REFUEL_TYPE_NINETY = 5;            // "90#"
        public static final int   EM_REFUEL_TYPE_TEN = 6;               // "10#"
        public static final int   EM_REFUEL_TYPE_FIVE = 7;              // "5#"
        public static final int   EM_REFUEL_TYPE_ZERO = 8;              // "0#"
        public static final int   EM_REFUEL_TYPE_NEGATIVE_TEN = 9;      // "-10#"
        public static final int   EM_REFUEL_TYPE_NEGATIVE_TWENTY = 10;  // "-20#"
        public static final int   EM_REFUEL_TYPE_NEGATIVE_THIRTY_FIVE = 11; // "-35#"
        public static final int   EM_REFUEL_TYPE_NEGATIVE_FIFTY = 12;   // "-50#"
    }

    // 车辆抓拍图片信息
    public static class DEV_EVENT_TRAFFIC_FCC_IMAGE extends SdkStructure {
        public int              dwOffSet;                             // 图片文件在二进制数据块中的偏移位置, 单位:字节
        public int              dwLength;                             // 图片大小, 单位:字节
        public short            wWidth;                               // 图片宽度, 单位:像素
        public short            wHeight;                              // 图片高度, 单位:像素
    }

    // 车辆抓图信息
    public static class DEV_EVENT_TRAFFIC_FCC_OBJECT extends SdkStructure {
        public DEV_EVENT_TRAFFIC_FCC_IMAGE stuImage;                  // 车辆抓拍图片信息
    }

    // 事件类型  EVENT_IVS_TRAFFIC_FCC 加油站提枪、挂枪事件
    public static class DEV_EVENT_TRAFFIC_FCC_INFO extends SdkStructure {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nTriggerID;                           // 触发类型: 1表示提枪, 2表示挂枪
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        ///////////////////////////////以上为公共字段//////////////////////////////
        public int              nNum;                                 // 加油枪号
        public int              nLitre;                               // 加油升数,单位 0.01升
        public int              emType;                               // 加油类型: 取值范围{"90#","93#","10#","-20#"}, 具体参考 EM_REFUEL_TYPE
        public int              dwMoney;                              // 加油金额,单位 0.01元
        public byte[]           szText = new byte[NET_COMMON_STRING_16]; // 车牌号
        public byte[]           szTime = new byte[NET_COMMON_STRING_32]; // 事件发生时间: "2016-05-23 10:31:17"
        public DEV_EVENT_TRAFFIC_FCC_OBJECT stuObject;                // 车辆抓图信息
        public byte[]           bReserved = new byte[1024];           // 保留字节,留待扩展
    }

    // 区域或曲线顶点信息
    public static class NET_POLY_POINTS extends SdkStructure
    {
        public int              nPointNum;                            // 顶点数
        public NET_POINT[]      stuPoints = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 顶点信息

        public NET_POLY_POINTS(){
            for(int i=0;i<stuPoints.length;i++){
                stuPoints[i]=new NET_POINT();
            }

        }
    }

    // 抓图类型
    public static class NET_CAPTURE_FORMATS extends SdkStructure
    {
        public static final int   NET_CAPTURE_BMP = 0;
        public static final int   NET_CAPTURE_JPEG = 1;                 // 100%质量的JPEG
        public static final int   NET_CAPTURE_JPEG_70 = 2;              // 70%质量的JPEG
        public static final int   NET_CAPTURE_JPEG_50 = 3;
        public static final int   NET_CAPTURE_JPEG_30 = 4;
    }

    // 抓图参数结构体
    public static class SNAP_PARAMS extends SdkStructure
    {
        public int              Channel;                              // 抓图的通道
        public int              Quality;                              // 画质；1~6
        public int              ImageSize;                            // 画面大小；0：QCIF,1：CIF,2：D1
        public int              mode;                                 // 抓图模式；-1:表示停止抓图, 0：表示请求一帧, 1：表示定时发送请求, 2：表示连续请求
        public int              InterSnap;                            // 时间单位秒；若mode=1表示定时发送请求时
        // 只有部分特殊设备(如：车载设备)支持通过该字段实现定时抓图时间间隔的配置
        // 建议通过 CFG_CMD_ENCODE 配置的stuSnapFormat[nSnapMode].stuVideoFormat.nFrameRate字段实现相关功能
        public int              CmdSerial;                            // 请求序列号，有效值范围 0~65535，超过范围会被截断为 unsigned short
        public int[]            Reserved = new int[4];
    }

    // 对应CLIENT_StartSearchDevices接口
    public static class DEVICE_NET_INFO_EX extends SdkStructure
    {
        public int              iIPVersion;                           // 4 for IPV4, 6 for IPV6
        public byte[]           szIP = new byte[64];                  // IPIPV4形如"192.168.0.1"
        public int              nPort;                                // tcp端口
        public byte[]           szSubmask = new byte[64];             // 子网掩码IPV6无子网掩码
        public byte[]           szGateway = new byte[64];             // 网关
        public byte[]           szMac = new byte[NET_MACADDR_LEN];    // MAC地址
        public byte[]           szDeviceType = new byte[NET_DEV_TYPE_LEN]; // 设备类型
        public byte             byManuFactory;                        // 目标设备的生产厂商,具体参考EM_IPC_TYPE类
        public byte             byDefinition;                         // 1-标清2-高清
        public byte             bDhcpEn;                              // Dhcp使能状态,true-开,false-关, 类型为bool, 取值0或者1
        public byte             byReserved1;                          // 字节对齐
        public byte[]           verifyData = new byte[88];            // 校验数据通过异步搜索回调获取(在修改设备IP时会用此信息进行校验)
        public byte[]           szSerialNo = new byte[NET_DEV_SERIALNO_LEN]; // 序列号
        public byte[]           szDevSoftVersion = new byte[NET_MAX_URL_LEN]; // 设备软件版本号
        public byte[]           szDetailType = new byte[NET_DEV_TYPE_LEN]; // 设备型号
        public byte[]           szVendor = new byte[NET_MAX_STRING_LEN]; // OEM客户类型
        public byte[]           szDevName = new byte[NET_MACHINE_NAME_NUM]; // 设备名称
        public byte[]           szUserName = new byte[NET_USER_NAME_LENGTH_EX]; // 登陆设备用户名（在修改设备IP时需要填写）
        public byte[]           szPassWord = new byte[NET_USER_NAME_LENGTH_EX]; // 登陆设备密码（在修改设备IP时需要填写）
        public short            nHttpPort;                            // HTTP服务端口号, unsigned short类型
        public short            wVideoInputCh;                        // 视频输入通道数
        public short            wRemoteVideoInputCh;                  // 远程视频输入通道数
        public short            wVideoOutputCh;                       // 视频输出通道数
        public short            wAlarmInputCh;                        // 报警输入通道数
        public short            wAlarmOutputCh;                       // 报警输出通道数
        public int              bNewWordLen;                          // TRUE使用新密码字段szNewPassWord, BOOL类型
        public byte[]           szNewPassWord = new byte[NET_COMMON_STRING_64]; // 登陆设备密码（在修改设备IP时需要填写）
        public byte             byInitStatus;                         // 设备初始化状态，按位确定初始化状态
        // bit0~1：0-老设备，没有初始化功能 1-未初始化账号 2-已初始化账户
        // bit2~3：0-老设备，保留 1-公网接入未使能 2-公网接入已使能
        // bit4~5：0-老设备，保留 1-手机直连未使能 2-手机直连使能
        public byte             byPwdResetWay;                        // 支持密码重置方式：按位确定密码重置方式，只在设备有初始化账号时有意义
        // bit0-支持预置手机号 bit1-支持预置邮箱 bit2-支持文件导出
        public byte             bySpecialAbility;                     // 设备初始化能力，按位确定初始化能力
        public byte[]           szNewDetailType = new byte[NET_COMMON_STRING_64]; // 设备型号
        public int              bNewUserName;                         // TRUE表示使用新用户名(szNewUserName)字段. BOOL类型
        public byte[]           szNewUserName = new byte[NET_COMMON_STRING_64]; // 登陆设备用户名（在修改设备IP时需要填写）
        public byte             byPwdFindVersion;                     // 密码找回的版本号,设备支持密码重置时有效;
        // 0-设备使用的是老方案的密码重置版本;1-支持预留联系方式进行密码重置操作;
        // 2-支持更换联系方式进行密码重置操作;
        public byte[]           szDeviceID = new byte[24];            // 字段, 不作为通用协议，不对接通用客户端
        public int              dwUnLoginFuncMask;                    // 未登陆功能掩码
        // Bit0-Wifi列表扫描及WLan设置
        // Bit1-支持会话外修改过期密码
        // Bit2-设备是否支持串口日志重定向（提前引导功能）
        public byte[]           szMachineGroup = new byte[64];        // 设备分组
        public byte[]           cReserved = new byte[12];             // 扩展字段
    }

    // 视频输入通道信息
    public static class NET_VIDEO_INPUTS extends SdkStructure {
        public int              dwSize;
        public byte[]           szChnName = new byte[64];             // 通道名称
        public int              bEnable;                              // 使能
        public byte[]           szControlID = new byte[128];          // 控制ID
        public byte[]           szMainStreamUrl = new byte[MAX_PATH]; // 主码流url地址
        public byte[]           szExtraStreamUrl = new byte[MAX_PATH]; // 辅码流url地址
        public int              nOptionalMainUrlCount;                // 备用主码流地址数量
        public byte[]           szOptionalMainUrls = new byte[8*MAX_PATH]; // 备用主码流地址列表
        public int              nOptionalExtraUrlCount;               // 备用辅码流地址数量
        public byte[]           szOptionalExtraUrls = new byte[8*MAX_PATH]; // 备用辅码流地址列表
        public byte[]           szCaption = new byte[32];             // 通道备注
        public int              emServiceType;                        // 指码流传输的服务类型，参考EM_STREAM_TRANSMISSION_SERVICE_TYPE
        public NET_SOURCE_STREAM_ENCRYPT stuSourceStreamEncrypt;      // 码流加密方式

        public NET_VIDEO_INPUTS()
        {
            this.dwSize = this.size();
        }
    }

    // 远程设备信息
    public static class NET_REMOTE_DEVICE extends SdkStructure {
        public int              dwSize;
        public int              bEnable;                              // 使能,1-true   0-false
        public byte[]           szIp = new byte[16];                  // IP
        public byte[]           szUser = new byte[8];                 // 用户名, 建议使用szUserEx
        public byte[]           szPwd = new byte[8];                  // 密码, 建议使用szPwdEx
        public int              nPort;                                // 端口
        public int              nDefinition;                          // 清晰度, 0-标清, 1-高清
        public int              emProtocol;                           // 协议类型  NET_DEVICE_PROTOCOL
        public byte[]           szDevName = new byte[64];             // 设备名称
        public int              nVideoInputChannels;                  // 视频输入通道数
        public int              nAudioInputChannels;                  // 音频输入通道数
        public byte[]           szDevClass = new byte[32];            // 设备类型, 如IPC, DVR, NVR等
        public byte[]           szDevType = new byte[32];             // 设备具体型号, 如IPC-HF3300
        public int              nHttpPort;                            // Http端口
        public int              nMaxVideoInputCount;                  // 视频输入通道最大数
        public int              nRetVideoInputCount;                  // 返回实际通道个数
        public Pointer          pstuVideoInputs;                      // 视频输入通道信息 NET_VIDEO_INPUTS*
        public byte[]           szMachineAddress = new byte[256];     // 设备部署地
        public byte[]           szSerialNo = new byte[48];            // 设备序列号
        public int              nRtspPort;                            // Rtsp端口
        /*以下用于新平台扩展*/
        public byte[]           szUserEx = new byte[32];              // 用户名
        public byte[]           szPwdEx = new byte[32];               // 密码
        public byte[]           szVendorAbbr = new byte[NET_COMMON_STRING_32]; // 厂商缩写
        public byte[]           szSoftwareVersion = new byte[NET_COMMON_STRING_64]; // 设备软件版本
        public NET_TIME         stuActivationTime;                    // 启动时间
        public byte[]           szMac = new byte[20];                 // MAC地址
        public int              nHttpsPort;                           //HttpsPort
        public byte[]           byReserved = new byte[4];             //保留字段
        public Pointer          pstuRemoteDevEx;                      //用于DH_REMOTE_DEVICE新增字段扩展,由用户申请内存，大小为sizeof(NET_REMOTE_DEVICE_EX),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_REMOTE_DEVICE_EX}

        public NET_REMOTE_DEVICE() {
            this.dwSize = this.size();
        }
    }

    // 可用的显示源信息
    public static class NET_MATRIX_CAMERA_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szName = new byte[128];               // 名称
        public byte[]           szDevID = new byte[128];              // 设备ID
        public byte[]           szszControlID = new byte[128];        // 控制ID
        public int              nChannelID;                           // 通道号, DeviceID设备内唯一
        public int              nUniqueChannel;                       // 设备内统一编号的唯一通道号
        public int              bRemoteDevice;                        // 是否远程设备
        public NET_REMOTE_DEVICE stuRemoteDevice;                     // 远程设备信息
        public int              emStreamType;                         // 视频码流类型  NET_STREAM_TYPE
        public int              emChannelType;                        // 通道类型应 NET_LOGIC_CHN_TYPE
        public int              bEnable;                              // 仅在使用DeviceID添加/删除设备时的使能，通过DeviceInfo操作不要使用
        public int              emVideoStream;                        // 视频码流,参考EM_VIDEO_STREAM

        public NET_MATRIX_CAMERA_INFO() {
            this.dwSize = this.size();
            stuRemoteDevice = new NET_REMOTE_DEVICE();
        }
    }

    // CLIENT_MatrixGetCameras接口的输入参数
    public static class NET_IN_MATRIX_GET_CAMERAS extends SdkStructure {
        public int              dwSize;

        public NET_IN_MATRIX_GET_CAMERAS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_MatrixGetCameras接口的输出参数
    public static class NET_OUT_MATRIX_GET_CAMERAS extends SdkStructure {
        public int              dwSize;
        public Pointer          pstuCameras;                          // 显示源信息数组, 用户分配内存  NET_MATRIX_CAMERA_INFO
        public int              nMaxCameraCount;                      // 显示源数组大小
        public int              nRetCameraCount;                      // 返回的显示源数量

        public NET_OUT_MATRIX_GET_CAMERAS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SnapPictureToFile 接口输入参数
    public static class NET_IN_SNAP_PIC_TO_FILE_PARAM extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public SNAP_PARAMS      stuParam;                             // 抓图参数, 其中mode字段仅一次性抓图, 不支持定时或持续抓图; 除了车载DVR, 其他设备仅支持每秒一张的抓图频率
        public byte[]           szFilePath = new byte[MAX_PATH];      // 写入文件的地址

        public NET_IN_SNAP_PIC_TO_FILE_PARAM() {
            this.dwSize = this.size();
            this.stuParam = new SNAP_PARAMS();
        }
    }

    //  CLIENT_SnapPictureToFile 接口输出参数
    public static class NET_OUT_SNAP_PIC_TO_FILE_PARAM extends SdkStructure {
        public int              dwSize;
        public Pointer          szPicBuf;                             // 图片内容,用户分配内存
        public int              dwPicBufLen;                          // 图片内容内存大小, 单位:字节
        public int              dwPicBufRetLen;                       // 返回的图片大小, 单位:字节

        public NET_OUT_SNAP_PIC_TO_FILE_PARAM() {
            this.dwSize = this.size();
        }

        public NET_OUT_SNAP_PIC_TO_FILE_PARAM(int nMaxBuf) {
            this.dwSize = this.size();
            this.dwPicBufLen = nMaxBuf;
            Memory mem = new Memory(nMaxBuf);
            mem.clear();
            this.szPicBuf = mem;
        }
    }

    // 录像文件信息
    public static class NET_RECORDFILE_INFO extends SdkStructure {
        public int              ch;                                   // 通道号
        public byte[]           filename = new byte[124];             // 文件名
        public int              framenum;                             // 文件总帧数
        public int              size;                                 // 文件长度
        public NET_TIME         starttime = new NET_TIME();           // 开始时间
        public NET_TIME         endtime = new NET_TIME();             // 结束时间
        public int              driveno;                              // 磁盘号(区分网络录像和本地录像的类型,0－127表示本地录像,其中64表示光盘1,128表示网络录像)
        public int              startcluster;                         // 起始簇号
        public byte             nRecordFileType;                      // 录象文件类型  0：普通录象；1：报警录象；2：移动检测；3：卡号录象；4：图片, 5: 智能录像,255:所有录像
        public byte             bImportantRecID;                      // 0:普通录像 1:重要录像
        public byte             bHint;                                // 文件定位索引(nRecordFileType==4<图片>时,bImportantRecID<<8 +bHint ,组成图片定位索引 )
        public byte             bRecType;                             // 0-主码流录像 1-辅码1流录像 2-辅码流2 3-辅码流3录像

        public static class ByValue extends NET_RECORDFILE_INFO implements SdkStructure.ByValue { }
    }

    // 录像查询类型
    public static class EM_QUERY_RECORD_TYPE extends SdkStructure {
        public static final int   EM_RECORD_TYPE_ALL = 0;               // 所有录像
        public static final int   EM_RECORD_TYPE_ALARM = 1;             // 外部报警录像
        public static final int   EM_RECORD_TYPE_MOTION_DETECT = 2;     // 动态检测报警录像
        public static final int   EM_RECORD_TYPE_ALARM_ALL = 3;         // 所有报警录像
        public static final int   EM_RECORD_TYPE_CARD = 4;              // 卡号查询
        public static final int   EM_RECORD_TYPE_CONDITION = 5;         // 按条件查询
        public static final int   EM_RECORD_TYPE_JOIN = 6;              // 组合查询
        public static final int   EM_RECORD_TYPE_CARD_PICTURE = 8;      // 按卡号查询图片,HB-U、NVS等使用
        public static final int   EM_RECORD_TYPE_PICTURE = 9;           // 查询图片,HB-U、NVS等使用
        public static final int   EM_RECORD_TYPE_FIELD = 10;            // 按字段查询
        public static final int   EM_RECORD_TYPE_INTELLI_VIDEO = 11;    // 智能录像查询
        public static final int   EM_RECORD_TYPE_NET_DATA = 15;         // 查询网络数据,金桥网吧等使用
        public static final int   EM_RECORD_TYPE_TRANS_DATA = 16;       // 查询透明串口数据录像
        public static final int   EM_RECORD_TYPE_IMPORTANT = 17;        // 查询重要录像
        public static final int   EM_RECORD_TYPE_TALK_DATA = 18;        // 查询录音文件
        public static final int   EM_RECORD_TYPE_INVALID = 256;         // 无效的查询类型
    }

    // 语言种类
    public static class NET_LANGUAGE_TYPE extends SdkStructure
    {
        public static final int   NET_LANGUAGE_ENGLISH = 0;             //英文
        public static final int   NET_LANGUAGE_CHINESE_SIMPLIFIED = NET_LANGUAGE_ENGLISH+1; //简体中文
        public static final int   NET_LANGUAGE_CHINESE_TRADITIONAL = NET_LANGUAGE_CHINESE_SIMPLIFIED+1; //繁体中文
        public static final int   NET_LANGUAGE_ITALIAN = NET_LANGUAGE_CHINESE_TRADITIONAL+1; //意大利文
        public static final int   NET_LANGUAGE_SPANISH = NET_LANGUAGE_ITALIAN+1; //西班牙文
        public static final int   NET_LANGUAGE_JAPANESE = NET_LANGUAGE_SPANISH+1; //日文版
        public static final int   NET_LANGUAGE_RUSSIAN = NET_LANGUAGE_JAPANESE+1; //俄文版
        public static final int   NET_LANGUAGE_FRENCH = NET_LANGUAGE_RUSSIAN+1; //法文版
        public static final int   NET_LANGUAGE_GERMAN = NET_LANGUAGE_FRENCH+1; //德文版
        public static final int   NET_LANGUAGE_PORTUGUESE = NET_LANGUAGE_GERMAN+1; //葡萄牙语
        public static final int   NET_LANGUAGE_TURKEY = NET_LANGUAGE_PORTUGUESE+1; //土尔其语
        public static final int   NET_LANGUAGE_POLISH = NET_LANGUAGE_TURKEY+1; //波兰语
        public static final int   NET_LANGUAGE_ROMANIAN = NET_LANGUAGE_POLISH+1; //罗马尼亚
        public static final int   NET_LANGUAGE_HUNGARIAN = NET_LANGUAGE_ROMANIAN+1; //匈牙利语
        public static final int   NET_LANGUAGE_FINNISH = NET_LANGUAGE_HUNGARIAN+1; //芬兰语
        public static final int   NET_LANGUAGE_ESTONIAN = NET_LANGUAGE_FINNISH+1; //爱沙尼亚语
        public static final int   NET_LANGUAGE_KOREAN = NET_LANGUAGE_ESTONIAN+1; //韩语
        public static final int   NET_LANGUAGE_FARSI = NET_LANGUAGE_KOREAN+1; //波斯语
        public static final int   NET_LANGUAGE_DANSK = NET_LANGUAGE_FARSI+1; //丹麦语
        public static final int   NET_LANGUAGE_CZECHISH = NET_LANGUAGE_DANSK+1; //捷克文
        public static final int   NET_LANGUAGE_BULGARIA = NET_LANGUAGE_CZECHISH+1; //保加利亚文
        public static final int   NET_LANGUAGE_SLOVAKIAN = NET_LANGUAGE_BULGARIA+1; //斯洛伐克语
        public static final int   NET_LANGUAGE_SLOVENIA = NET_LANGUAGE_SLOVAKIAN+1; //斯洛文尼亚文
        public static final int   NET_LANGUAGE_CROATIAN = NET_LANGUAGE_SLOVENIA+1; //克罗地亚语
        public static final int   NET_LANGUAGE_DUTCH = NET_LANGUAGE_CROATIAN+1; //荷兰语
        public static final int   NET_LANGUAGE_GREEK = NET_LANGUAGE_DUTCH+1; //希腊语
        public static final int   NET_LANGUAGE_UKRAINIAN = NET_LANGUAGE_GREEK+1; //乌克兰语
        public static final int   NET_LANGUAGE_SWEDISH = NET_LANGUAGE_UKRAINIAN+1; //瑞典语
        public static final int   NET_LANGUAGE_SERBIAN = NET_LANGUAGE_SWEDISH+1; //塞尔维亚语
        public static final int   NET_LANGUAGE_VIETNAMESE = NET_LANGUAGE_SERBIAN+1; //越南语
        public static final int   NET_LANGUAGE_LITHUANIAN = NET_LANGUAGE_VIETNAMESE+1; //立陶宛语
        public static final int   NET_LANGUAGE_FILIPINO = NET_LANGUAGE_LITHUANIAN+1; //菲律宾语
        public static final int   NET_LANGUAGE_ARABIC = NET_LANGUAGE_FILIPINO+1; //阿拉伯语
        public static final int   NET_LANGUAGE_CATALAN = NET_LANGUAGE_ARABIC+1; //加泰罗尼亚语
        public static final int   NET_LANGUAGE_LATVIAN = NET_LANGUAGE_CATALAN+1; //拉脱维亚语
        public static final int   NET_LANGUAGE_THAI = NET_LANGUAGE_LATVIAN+1; //泰语
        public static final int   NET_LANGUAGE_HEBREW = NET_LANGUAGE_THAI+1; //希伯来语
        public static final int   NET_LANGUAGE_Bosnian = NET_LANGUAGE_HEBREW+1; //波斯尼亚文
    }

    // 区域信息
    public static class CFG_RECT extends SdkStructure
    {
        public int              nLeft;
        public int              nTop;
        public int              nRight;
        public int              nBottom;
    }

    // 视频输入夜晚特殊配置选项，在晚上光线较暗时自动切换到夜晚的配置参数
    public static class CFG_VIDEO_IN_NIGHT_OPTIONS extends SdkStructure
    {
        public byte             bySwitchMode;                         //已废弃,使用CFG_VIDEO_IN_OPTIONS里面的bySwitchMode
        //0-不切换，总是使用白天配置；1-根据亮度切换；2-根据时间切换；3-不切换，总是使用夜晚配置；4-使用普通配置
        public byte             byProfile;                            //当前使用的配置文件.
        // 0-白天
        // 1-晚上
        // 2-Normal
        // 0，1,2都为临时配置，使图像生效，便于查看图像调试效果，不点击确定，离开页面不保存至设备。
        ///3-非临时配置，点击确定后保存至设备，与SwitchMode结合使用，根据SwitchMode决定最终生效的配置。
        // SwitchMode=0，Profile=3，设置白天配置到设备；
        // SwitchMode=1，Profile=3，则设置夜晚配置到设备
        // SwitchMode=2，Profile=3，根据日出日落时间段切换，白天时间段使用白天配置，夜晚时间段使用夜晚配置，保存至设备；
        // SwitchMode=4，Profile=3；使用普通配置，保存至设备
        public byte             byBrightnessThreshold;                //亮度阈值0~100
        public byte             bySunriseHour;                        //大致日出和日落时间，日落之后日出之前，将采用夜晚特殊的配置。
        public byte             bySunriseMinute;                      //00:00:00 ~ 23:59:59
        public byte             bySunriseSecond;
        public byte             bySunsetHour;
        public byte             bySunsetMinute;
        public byte             bySunsetSecond;
        public byte             byGainRed;                            //红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainBlue;                           //绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainGreen;                          //蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byExposure;                           //曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float            fExposureValue1;                      //自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float            fExposureValue2;                      //自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte             byWhiteBalance;                       //白平衡,0-"unAble", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte             byGain;                               //0~100,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte             bGainAuto;                            //自动增益, 类型为bool, 取值0或1
        public byte             bIrisAuto;                            //自动光圈, 类型为bool, 取值0或1
        public float            fExternalSyncPhase;                   //外同步的相位设置0~360
        public byte             byGainMin;                            //增益下限
        public byte             byGainMax;                            //增益上限
        public byte             byBacklight;                          //背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte             byAntiFlicker;                        //防闪烁模式0-Outdoor1-50Hz防闪烁 2-60Hz防闪烁
        public byte             byDayNightColor;                      //日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte             byExposureMode;                       //曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先
        public byte             byRotate90;                           //0-不旋转，1-顺时针90°，2-逆时针90°
        public byte             bMirror;                              //镜像, 类型为bool, 取值0或1
        public byte             byWideDynamicRange;                   //宽动态值0-关闭，1~100-为真实范围值
        public byte             byGlareInhibition;                    //强光抑制0-关闭，1~100为范围值
        public CFG_RECT         stuBacklightRegion = new CFG_RECT();  //背光补偿区域
        public byte             byFocusMode;                          //0-关闭，1-辅助聚焦，2-自动聚焦
        public byte             bFlip;                                //翻转, 类型为bool, 取值0或1
        public byte[]           reserved = new byte[74];              //保留
    }

    // 闪光灯配置
    public static class CFG_FLASH_CONTROL extends SdkStructure
    {
        public byte             byMode;                               //工作模式，0-禁止闪光，1-始终闪光，2-自动闪光
        public byte             byValue;                              //工作值,0-0us,1-64us, 2-128us, 3-192...15-960us
        public byte             byPole;                               //触发模式,0-低电平1-高电平 2-上升沿 3-下降沿
        public byte             byPreValue;                           //亮度预设值区间0~100
        public byte             byDutyCycle;                          //占空比,0~100
        public byte             byFreqMultiple;                       //倍频,0~10
        public byte[]           reserved = new byte[122];             //保留
    }

    // 抓拍参数特殊配置
    public static class CFG_VIDEO_IN_SNAPSHOT_OPTIONS extends SdkStructure
    {
        public byte             byGainRed;                            //红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainBlue;                           //绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainGreen;                          //蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byExposure;                           //曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float            fExposureValue1;                      //自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float            fExposureValue2;                      //自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte             byWhiteBalance;                       //白平衡,0-"unAble", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte             byColorTemperature;                   //色温等级,白平衡为"CustomColorTemperature"模式下有效
        public byte             bGainAuto;                            //自动增益, 类型为bool, 取值0或1
        public byte             byGain;                               //增益调节,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte[]           reversed = new byte[112];             //保留
    }

    // 鱼眼镜头配置
    public static class CFG_FISH_EYE extends SdkStructure
    {
        public CFG_POLYGON      stuCenterPoint;                       //鱼眼圆心坐标,范围[0,8192]
        public int              nRadius;                              //鱼眼半径大小,范围[0,8192], 类型为unsigned int
        public float            fDirection;                           //镜头旋转方向,旋转角度[0,360.0]
        public byte             byPlaceHolder;                        //镜头安装方式1顶装，2壁装；3地装,默认1
        public byte             byCalibrateMode;                      //鱼眼矫正模式,详见CFG_CALIBRATE_MODE枚举值
        public byte[]           reversed = new byte[31];              //保留
    }

    public static class CFG_VIDEO_IN_NORMAL_OPTIONS extends SdkStructure
    {
        public byte             byGainRed;                            //红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainBlue;                           //绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainGreen;                          //蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byExposure;                           //曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float            fExposureValue1;                      //自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float            fExposureValue2;                      //自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte             byWhiteBalance;                       //白平衡,0-"unAble", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte             byGain;                               //0~100,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte             bGainAuto;                            //自动增益, 类型为bool, 取值0或1
        public byte             bIrisAuto;                            //自动光圈, 类型为bool, 取值0或1
        public float            fExternalSyncPhase;                   //外同步的相位设置0~360
        public byte             byGainMin;                            //增益下限
        public byte             byGainMax;                            //增益上限
        public byte             byBacklight;                          //背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte             byAntiFlicker;                        //防闪烁模式0-Outdoor1-50Hz防闪烁 2-60Hz防闪烁
        public byte             byDayNightColor;                      //日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte             byExposureMode;                       //曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先
        public byte             byRotate90;                           //0-不旋转，1-顺时针90°，2-逆时针90°
        public byte             bMirror;                              //镜像, 类型为bool, 取值0或1
        public byte             byWideDynamicRange;                   //宽动态值0-关闭，1~100-为真实范围值
        public byte             byGlareInhibition;                    //强光抑制0-关闭，1~100为范围值
        public CFG_RECT         stuBacklightRegion;                   //背光补偿区域
        public byte             byFocusMode;                          //0-关闭，1-辅助聚焦，2-自动聚焦
        public byte             bFlip;                                //翻转, 类型为bool, 取值0或1
        public byte[]           reserved = new byte[74];              //保留
    }

    // 视频输入前端选项
    public static class CFG_VIDEO_IN_OPTIONS extends SdkStructure
    {
        public byte             byBacklight;                          //背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte             byDayNightColor;                      //日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte             byWhiteBalance;                       //白平衡,0-"unAble", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte             byColorTemperature;                   //色温等级,白平衡为"CustomColorTemperature"模式下有效
        public byte             bMirror;                              //镜像, 类型为bool, 取值0或1
        public byte             bFlip;                                //翻转, 类型为bool, 取值0或1
        public byte             bIrisAuto;                            //自动光圈, 类型为bool, 取值0或1
        public byte             bInfraRed;                            //根据环境光自动开启红外补偿灯, 类型为bool, 取值0或1
        public byte             byGainRed;                            //红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainBlue;                           //绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byGainGreen;                          //蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte             byExposure;                           //曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float            fExposureValue1;                      //自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float            fExposureValue2;                      //自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte             bGainAuto;                            //自动增益, 类型为bool, 取值0或1
        public byte             byGain;                               //增益调节,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte             bySignalFormat;                       //信号格式,0-Inside(内部输入)1-BT656 2-720p 3-1080p  4-1080i  5-1080sF
        public byte             byRotate90;                           //0-不旋转，1-顺时针90°，2-逆时针90°
        public float            fExternalSyncPhase;                   //外同步的相位设置 0~360
        public byte             byExternalSync;                       //外部同步信号输入,0-内部同步 1-外部同步
        public byte             bySwitchMode;                         //0-不切换，总是使用白天配置；1-根据亮度切换；2-根据时间切换；3-不切换，总是使用夜晚配置；4-使用普通配置
        public byte             byDoubleExposure;                     //双快门,0-不启用，1-双快门全帧率，即图像和视频只有快门参数不同，2-双快门半帧率，即图像和视频快门及白平衡参数均不同
        public byte             byWideDynamicRange;                   //宽动态值
        public CFG_VIDEO_IN_NIGHT_OPTIONS stuNightOptions;            //夜晚参数
        public CFG_FLASH_CONTROL stuFlash;                            //闪光灯配置
        public CFG_VIDEO_IN_SNAPSHOT_OPTIONS stuSnapshot;             //抓拍参数,双快门时有效
        public CFG_FISH_EYE     stuFishEye;                           //鱼眼镜头
        public byte             byFocusMode;                          //0-关闭，1-辅助聚焦，2-自动聚焦
        public byte[]           reserved = new byte[28];              //保留
        public byte             byGainMin;                            //增益下限
        public byte             byGainMax;                            //增益上限
        public byte             byAntiFlicker;                        //防闪烁模式 0-Outdoor 1-50Hz防闪烁 2-60Hz防闪烁
        public byte             byExposureMode;                       //曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先,4-手动
        public byte             byGlareInhibition;                    //强光抑制0-关闭，1~100为范围值
        public CFG_RECT         stuBacklightRegion;                   //背光补偿区域
        public CFG_VIDEO_IN_NORMAL_OPTIONS stuNormalOptions;          //普通参数
    }

    // 通用云台控制命令
    public static class NET_PTZ_ControlType extends SdkStructure
    {
        public static final int   NET_PTZ_UP_CONTROL = 0;               //上
        public static final int   NET_PTZ_DOWN_CONTROL = NET_PTZ_UP_CONTROL+1; //下
        public static final int   NET_PTZ_LEFT_CONTROL = NET_PTZ_DOWN_CONTROL+1; //左
        public static final int   NET_PTZ_RIGHT_CONTROL = NET_PTZ_LEFT_CONTROL+1; //右
        public static final int   NET_PTZ_ZOOM_ADD_CONTROL = NET_PTZ_RIGHT_CONTROL+1; //变倍+
        public static final int   NET_PTZ_ZOOM_DEC_CONTROL = NET_PTZ_ZOOM_ADD_CONTROL+1; //变倍-
        public static final int   NET_PTZ_FOCUS_ADD_CONTROL = NET_PTZ_ZOOM_DEC_CONTROL+1; //调焦+
        public static final int   NET_PTZ_FOCUS_DEC_CONTROL = NET_PTZ_FOCUS_ADD_CONTROL+1; //调焦-
        public static final int   NET_PTZ_APERTURE_ADD_CONTROL = NET_PTZ_FOCUS_DEC_CONTROL+1; //光圈+
        public static final int   NET_PTZ_APERTURE_DEC_CONTROL = NET_PTZ_APERTURE_ADD_CONTROL+1; //光圈-
        public static final int   NET_PTZ_POINT_MOVE_CONTROL = NET_PTZ_APERTURE_DEC_CONTROL+1; //转至预置点
        public static final int   NET_PTZ_POINT_SET_CONTROL = NET_PTZ_POINT_MOVE_CONTROL+1; //设置
        public static final int   NET_PTZ_POINT_DEL_CONTROL = NET_PTZ_POINT_SET_CONTROL+1; //删除
        public static final int   NET_PTZ_POINT_LOOP_CONTROL = NET_PTZ_POINT_DEL_CONTROL+1; //点间巡航
        public static final int   NET_PTZ_LAMP_CONTROL = NET_PTZ_POINT_LOOP_CONTROL+1; //灯光雨刷
    }

    // 云台控制扩展命令
    public static class NET_EXTPTZ_ControlType extends SdkStructure
    {
        public static final int   NET_EXTPTZ_LEFTTOP = 0x20;            //左上
        public static final int   NET_EXTPTZ_RIGHTTOP = NET_EXTPTZ_LEFTTOP+1; //右上
        public static final int   NET_EXTPTZ_LEFTDOWN = NET_EXTPTZ_RIGHTTOP+1; //左下
        public static final int   NET_EXTPTZ_RIGHTDOWN = NET_EXTPTZ_LEFTDOWN+1; //右下
        public static final int   NET_EXTPTZ_ADDTOLOOP = NET_EXTPTZ_RIGHTDOWN+1; //加入预置点到巡航巡航线路预置点值
        public static final int   NET_EXTPTZ_DELFROMLOOP = NET_EXTPTZ_ADDTOLOOP+1; //删除巡航中预置点巡航线路预置点值
        public static final int   NET_EXTPTZ_CLOSELOOP = NET_EXTPTZ_DELFROMLOOP+1; //清除巡航巡航线路
        public static final int   NET_EXTPTZ_STARTPANCRUISE = NET_EXTPTZ_CLOSELOOP+1; //开始水平旋转
        public static final int   NET_EXTPTZ_STOPPANCRUISE = NET_EXTPTZ_STARTPANCRUISE+1; //停止水平旋转
        public static final int   NET_EXTPTZ_SETLEFTBORDER = NET_EXTPTZ_STOPPANCRUISE+1; //设置左边界
        public static final int   NET_EXTPTZ_SETRIGHTBORDER = NET_EXTPTZ_SETLEFTBORDER+1; //设置右边界
        public static final int   NET_EXTPTZ_STARTLINESCAN = NET_EXTPTZ_SETRIGHTBORDER+1; //开始线扫
        public static final int   NET_EXTPTZ_CLOSELINESCAN = NET_EXTPTZ_STARTLINESCAN+1; //停止线扫
        public static final int   NET_EXTPTZ_SETMODESTART = NET_EXTPTZ_CLOSELINESCAN+1; //设置模式开始模式线路
        public static final int   NET_EXTPTZ_SETMODESTOP = NET_EXTPTZ_SETMODESTART+1; //设置模式结束模式线路
        public static final int   NET_EXTPTZ_RUNMODE = NET_EXTPTZ_SETMODESTOP+1; //运行模式模式线路
        public static final int   NET_EXTPTZ_STOPMODE = NET_EXTPTZ_RUNMODE+1; //停止模式模式线路
        public static final int   NET_EXTPTZ_DELETEMODE = NET_EXTPTZ_STOPMODE+1; //清除模式模式线路
        public static final int   NET_EXTPTZ_REVERSECOMM = NET_EXTPTZ_DELETEMODE+1; //翻转命令
        public static final int   NET_EXTPTZ_FASTGOTO = NET_EXTPTZ_REVERSECOMM+1; //快速定位水平坐标(8192)垂直坐标(8192)变倍(4)
        public static final int   NET_EXTPTZ_AUXIOPEN = NET_EXTPTZ_FASTGOTO+1; //辅助开关开辅助点
        public static final int   NET_EXTPTZ_AUXICLOSE = NET_EXTPTZ_AUXIOPEN+1; //辅助开关关辅助点
        public static final int   NET_EXTPTZ_OPENMENU = 0x36;           //打开球机菜单
        public static final int   NET_EXTPTZ_CLOSEMENU = NET_EXTPTZ_OPENMENU+1; //关闭菜单
        public static final int   NET_EXTPTZ_MENUOK = NET_EXTPTZ_CLOSEMENU+1; //菜单确定
        public static final int   NET_EXTPTZ_MENUCANCEL = NET_EXTPTZ_MENUOK+1; //菜单取消
        public static final int   NET_EXTPTZ_MENUUP = NET_EXTPTZ_MENUCANCEL+1; //菜单上
        public static final int   NET_EXTPTZ_MENUDOWN = NET_EXTPTZ_MENUUP+1; //菜单下
        public static final int   NET_EXTPTZ_MENULEFT = NET_EXTPTZ_MENUDOWN+1; //菜单左
        public static final int   NET_EXTPTZ_MENURIGHT = NET_EXTPTZ_MENULEFT+1; //菜单右
        public static final int   NET_EXTPTZ_ALARMHANDLE = 0x40;        //报警联动云台parm1：报警输入通道；parm2：报警联动类型1-预置点2-线扫3-巡航；parm3：联动值，如预置点号
        public static final int   NET_EXTPTZ_MATRIXSWITCH = 0x41;       //矩阵切换parm1：预览器号(视频输出号)；parm2：视频输入号；parm3：矩阵号
        public static final int   NET_EXTPTZ_LIGHTCONTROL = NET_EXTPTZ_MATRIXSWITCH+1; //灯光控制器
        public static final int   NET_EXTPTZ_EXACTGOTO = NET_EXTPTZ_LIGHTCONTROL+1; //三维精确定位parm1：水平角度(0~3600)；parm2：垂直坐标(0~900)；parm3：变倍(1~128)
        public static final int   NET_EXTPTZ_RESETZERO = NET_EXTPTZ_EXACTGOTO+1; //三维定位重设零位
        public static final int   NET_EXTPTZ_MOVE_ABSOLUTELY = NET_EXTPTZ_RESETZERO+1; //绝对移动控制命令，param4对应结构PTZ_CONTROL_ABSOLUTELY
        public static final int   NET_EXTPTZ_MOVE_CONTINUOUSLY = NET_EXTPTZ_MOVE_ABSOLUTELY+1; //持续移动控制命令，param4对应结构PTZ_CONTROL_CONTINUOUSLY
        public static final int   NET_EXTPTZ_GOTOPRESET = NET_EXTPTZ_MOVE_CONTINUOUSLY+1; //云台控制命令，以一定速度转到预置位点，parm4对应结构PTZ_CONTROL_GOTOPRESET
        public static final int   NET_EXTPTZ_SET_VIEW_RANGE = 0x49;     //设置可视域(param4对应结构PTZ_VIEW_RANGE_INFO)
        public static final int   NET_EXTPTZ_FOCUS_ABSOLUTELY = 0x4A;   //绝对聚焦(param4对应结构PTZ_FOCUS_ABSOLUTELY)
        public static final int   NET_EXTPTZ_HORSECTORSCAN = 0x4B;      //水平扇扫(param4对应PTZ_CONTROL_SECTORSCAN,param1、param2、param3无效)
        public static final int   NET_EXTPTZ_VERSECTORSCAN = 0x4C;      //垂直扇扫(param4对应PTZ_CONTROL_SECTORSCAN,param1、param2、param3无效)
        public static final int   NET_EXTPTZ_SET_ABS_ZOOMFOCUS = 0x4D;  //设定绝对焦距、聚焦值,param1为焦距,范围:0,255],param2为聚焦,范围:[0,255],param3、param4无效
        public static final int   NET_EXTPTZ_SET_FISHEYE_EPTZ = 0x4E;   //控制鱼眼电子云台，param4对应结构PTZ_CONTROL_SET_FISHEYE_EPTZ
        public static final int   NET_EXTPTZ_UP_TELE = 0x70;            //上 + TELE param1=速度(1-8)，下同
        public static final int   NET_EXTPTZ_DOWN_TELE = NET_EXTPTZ_UP_TELE+1; //下 + TELE
        public static final int   NET_EXTPTZ_LEFT_TELE = NET_EXTPTZ_DOWN_TELE+1; //左 + TELE
        public static final int   NET_EXTPTZ_RIGHT_TELE = NET_EXTPTZ_LEFT_TELE+1; //右 + TELE
        public static final int   NET_EXTPTZ_LEFTUP_TELE = NET_EXTPTZ_RIGHT_TELE+1; //左上 + TELE
        public static final int   NET_EXTPTZ_LEFTDOWN_TELE = NET_EXTPTZ_LEFTUP_TELE+1; //左下 + TELE
        public static final int   NET_EXTPTZ_TIGHTUP_TELE = NET_EXTPTZ_LEFTDOWN_TELE+1; //右上 + TELE
        public static final int   NET_EXTPTZ_RIGHTDOWN_TELE = NET_EXTPTZ_TIGHTUP_TELE+1; //右下 + TELE
        public static final int   NET_EXTPTZ_UP_WIDE = NET_EXTPTZ_RIGHTDOWN_TELE+1; // 上 + WIDEparam1=速度(1-8)，下同
        public static final int   NET_EXTPTZ_DOWN_WIDE = NET_EXTPTZ_UP_WIDE+1; //下 + WIDE
        public static final int   NET_EXTPTZ_LEFT_WIDE = NET_EXTPTZ_DOWN_WIDE+1; //左 + WIDE
        public static final int   NET_EXTPTZ_RIGHT_WIDE = NET_EXTPTZ_LEFT_WIDE+1; //右 + WIDE
        public static final int   NET_EXTPTZ_LEFTUP_WIDE = NET_EXTPTZ_RIGHT_WIDE+1; //左上 + WIDENET_IN_PTZBASE_SET_FOCUS_MAP_VALUE_INFO
        public static final int   NET_EXTPTZ_LEFTDOWN_WIDE = NET_EXTPTZ_LEFTUP_WIDE+1; //左下 + WIDE
        public static final int   NET_EXTPTZ_TIGHTUP_WIDE = NET_EXTPTZ_LEFTDOWN_WIDE+1; //右上 + WIDE
        public static final int   NET_EXTPTZ_RIGHTDOWN_WIDE = NET_EXTPTZ_TIGHTUP_WIDE+1; //右下 + WIDE
        public static final int   NET_EXTPTZ_GOTOPRESETSNAP = 0x80;     // 转至预置点并抓图
        public static final int   NET_EXTPTZ_DIRECTIONCALIBRATION = 0x82; // 校准云台方向（双方向校准）
        public static final int   NET_EXTPTZ_SINGLEDIRECTIONCALIBRATION = 0x83; // 校准云台方向（单防线校准）,param4对应结构 NET_IN_CALIBRATE_SINGLEDIRECTION
        public static final int   NET_EXTPTZ_MOVE_RELATIVELY = 0x84;    // 云台相对定位,param4对应结构 NET_IN_MOVERELATIVELY_INFO
        public static final int   NET_EXTPTZ_SET_DIRECTION = 0x85;      // 设置云台方向, param4对应结构 NET_IN_SET_DIRECTION_INFO
        public static final int   NET_EXTPTZ_BASE_MOVE_ABSOLUTELY = 0x86; // 精准绝对移动控制命令, param4对应结构 NET_IN_PTZBASE_MOVEABSOLUTELY_INFO（通过 CFG_CAP_CMD_PTZ 命令获取云台能力集( CFG_PTZ_PROTOCOL_CAPS_INFO )，若bSupportReal为TRUE则设备支持该操作）
        public static final int   NET_EXTPTZ_BASE_MOVE_CONTINUOUSLY = NET_EXTPTZ_BASE_MOVE_ABSOLUTELY+1; // 云台连续移动控制命令, param4 对应结构 NET_IN_PTZBASE_MOVE_CONTINUOUSLY_INFO.  通过 CFG_CAP_CMD_PTZ 命令获取云台能力集
                                                                                                        // 若 CFG_PTZ_PROTOCOL_CAPS_INFO 中 stuMoveContinuously 字段的 stuType.bSupportExtra 为 TRUE, 表示设备支持该操作
        public static final int   NET_EXTPTZ_BASE_SET_FOCUS_MAP_VALUE = NET_EXTPTZ_BASE_MOVE_CONTINUOUSLY+1; // 设置当前位置聚焦值, param4对应结构体 NET_IN_PTZBASE_SET_FOCUS_MAP_VALUE_INFO
        public static final int   NET_EXTPTZ_BASE_MOVE_ABSOLUTELY_ONLYPT = NET_EXTPTZ_BASE_SET_FOCUS_MAP_VALUE+1; // 绝对定位独立控制PT并能以度/秒为单位的速度控制, param4对应结构 NET_IN_PTZBASE_MOVEABSOLUTELY_ONLYPT_INFO
        public static final int   NET_EXTPTZ_BASE_MOVE_ABSOLUTELY_ONLYZOOM = NET_EXTPTZ_BASE_MOVE_ABSOLUTELY_ONLYPT+1; // 绝对定位独立控制zoom，并能控制变倍速度, param4对应结构 NET_IN_PTZBASE_MOVEABSOLUTELY_ONLYZOOM_INFO
        public static final int   NET_EXTPTZ_STOP_MOVE = NET_EXTPTZ_BASE_MOVE_ABSOLUTELY_ONLYZOOM+1; // 云台移动停止,同时也停止巡航模式,param4对应结构体 NET_IN_STOP_MOVE_INFO
        public static final int   NET_EXTPTZ_START = NET_EXTPTZ_STOP_MOVE+1; // 开始云台控制,param4对应结构体 NET_IN_PTZ_START_INFO
        public static final int   NET_EXTPTZ_STOP = NET_EXTPTZ_START+1; // 结束云台控制,param4对应结构体 NET_IN_PTZ_STOP_INFO
        public static final int   NET_EXTPTZ_START_PATTERN_RECORD = NET_EXTPTZ_STOP+1; // 开始模式记录,param4对应结构体 NET_IN_START_PATTERN_RECORD_INFO
        public static final int   NET_EXTPTZ_STOP_PATTERN_RECORD = NET_EXTPTZ_START_PATTERN_RECORD+1; // 停止模式记录,param4对应结构体 NET_IN_STOP_PATTERN_RECORD_INFO
        public static final int   NET_EXTPTZ_START_PATTERN_REPLAY = NET_EXTPTZ_STOP_PATTERN_RECORD+1; // 开始模式回放,param4对应结构体 NET_IN_START_PATTERN_REPLAY_INFO
        public static final int   NET_EXTPTZ_STOP_PATTERN_REPLAY = NET_EXTPTZ_START_PATTERN_REPLAY+1; // 停止模式回放,param4对应结构体 NET_IN_STOP_PATTERN_REPLAY_INFO
        public static final int   NET_EXTPTZ_MOVE_DIRECTLY = NET_EXTPTZ_STOP_PATTERN_REPLAY+1; // 云台三维定位, param4对应结构体 NET_IN_MOVE_DIRECTLY_INFO
        public static final int   NET_EXTPTZ_TOTAL = NET_EXTPTZ_MOVE_DIRECTLY+1; //最大命令值
    }

    // 雨刷工作模式
    public static class EM_CFG_RAINBRUSHMODE_MODE extends SdkStructure
    {
        public static final int   EM_CFG_RAINBRUSHMODE_MODE_UNKNOWN = 0; //未知
        public static final int   EM_CFG_RAINBRUSHMODE_MODE_MANUAL = EM_CFG_RAINBRUSHMODE_MODE_UNKNOWN+1; //手动模式
        public static final int   EM_CFG_RAINBRUSHMODE_MODE_TIMING = EM_CFG_RAINBRUSHMODE_MODE_MANUAL+1; //定时模式
    }

    // 雨刷使能电平模式
    public static class EM_CFG_RAINBRUSHMODE_ENABLEMODE extends SdkStructure
    {
        public static final int   EM_CFG_RAINBRUSHMODE_ENABLEMODE_UNKNOWN = 0; //未知
        public static final int   EM_CFG_RAINBRUSHMODE_ENABLEMODE_LOW = EM_CFG_RAINBRUSHMODE_ENABLEMODE_UNKNOWN+1; //低电平有效（常闭）
        public static final int   EM_CFG_RAINBRUSHMODE_ENABLEMODE_HIGH = EM_CFG_RAINBRUSHMODE_ENABLEMODE_LOW+1; //高电平有效（常开）
    }

    // 雨刷模式相关配置(对应 CFG_RAINBRUSHMODE_INFO 命令)
    public static class CFG_RAINBRUSHMODE_INFO extends SdkStructure
    {
        public int              emMode;                               //雨刷工作模式, 取值为EM_CFG_RAINBRUSHMODE_MODE中的值
        public int              emEnableMode;                         //雨刷使能电平模式, 取值为EM_CFG_RAINBRUSHMODE_ENABLEMODE中的值
        public int              nPort;                                //雨刷使用的IO端口,-1表示未接入设备,-2表示该字段无效（设备未传送该字段）
        public int              nSensitivity;                         // 雨刷灵敏度, 只有当mode为Auto时有效, 范围[0, 10]
    }

    public static class CFG_RAINBRUSH_INFO extends SdkStructure
    {
        public byte             bEnable;                              // 雨刷使能, 类型为bool, 取值0或1
        public byte             bSpeedRate;                           // 雨刷速度,1:快速;2:中速;3:慢速
        public byte[]           bReserved = new byte[2];              // 保留对齐
        public TIME_SECTION_WEEK_DAY_6[] stuTimeSectionWeekDay = (TIME_SECTION_WEEK_DAY_6[])new TIME_SECTION_WEEK_DAY_6().toArray(WEEK_DAY_NUM); // 事件响应时间段
        public int              nInterval;                            // 雨刷运动间隔事件, 单位: 秒
        public int              bProtectEnable;                       // 雨刷保护使能: true 保护开启, false 保护关闭
        public int              nProtectTime;                         // 保护时间, 单位: 秒
        public CFG_RAINBRUSH_TOUR_INFO stuRainBrushTour = new CFG_RAINBRUSH_TOUR_INFO(); // 雨刷巡航模式配置，当CFG_RAINBRUSHMODE_INFO中EM_CFG_RAINBRUSHMODE_MODE为EM_CFG_RAINBRUSHMODE_MODE_TOUR时有效
        public CFG_RAINBRUSH_CYCLE stuRainBrushCycle = new CFG_RAINBRUSH_CYCLE(); //周期模式配置，当CFG_RAINBRUSHMODE_INFO中EM_CFG_RAINBRUSHMODE_MODE为EM_CFG_RAINBRUSHMODE_MODE_CYCLE时有效,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.CFG_RAINBRUSH_CYCLE}
        public int              nSelfCleanRunTime;                    //手动模式下，一次自清洗操作雨刷运行的时长，单位：秒
        public byte[]           szReserved = new byte[1024];          //保留字节
    }

    // 控制类型，对应CLIENT_ControlDevice接口
    public static class CtrlType extends SdkStructure
    {
        public static final int   CTRLTYPE_CTRL_REBOOT = 0;             //重启设备
        public static final int   CTRLTYPE_CTRL_SHUTDOWN = CTRLTYPE_CTRL_REBOOT+1; //关闭设备
        public static final int   CTRLTYPE_CTRL_DISK = CTRLTYPE_CTRL_SHUTDOWN+1; //硬盘管理
        public static final int   CTRLTYPE_KEYBOARD_POWER = 3;          //网络键盘
        public static final int   CTRLTYPE_KEYBOARD_ENTER = CTRLTYPE_KEYBOARD_POWER+1;
        public static final int   CTRLTYPE_KEYBOARD_ESC = CTRLTYPE_KEYBOARD_ENTER+1;
        public static final int   CTRLTYPE_KEYBOARD_UP = CTRLTYPE_KEYBOARD_ESC+1;
        public static final int   CTRLTYPE_KEYBOARD_DOWN = CTRLTYPE_KEYBOARD_UP+1;
        public static final int   CTRLTYPE_KEYBOARD_LEFT = CTRLTYPE_KEYBOARD_DOWN+1;
        public static final int   CTRLTYPE_KEYBOARD_RIGHT = CTRLTYPE_KEYBOARD_LEFT+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN0 = CTRLTYPE_KEYBOARD_RIGHT+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN1 = CTRLTYPE_KEYBOARD_BTN0+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN2 = CTRLTYPE_KEYBOARD_BTN1+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN3 = CTRLTYPE_KEYBOARD_BTN2+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN4 = CTRLTYPE_KEYBOARD_BTN3+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN5 = CTRLTYPE_KEYBOARD_BTN4+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN6 = CTRLTYPE_KEYBOARD_BTN5+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN7 = CTRLTYPE_KEYBOARD_BTN6+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN8 = CTRLTYPE_KEYBOARD_BTN7+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN9 = CTRLTYPE_KEYBOARD_BTN8+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN10 = CTRLTYPE_KEYBOARD_BTN9+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN11 = CTRLTYPE_KEYBOARD_BTN10+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN12 = CTRLTYPE_KEYBOARD_BTN11+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN13 = CTRLTYPE_KEYBOARD_BTN12+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN14 = CTRLTYPE_KEYBOARD_BTN13+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN15 = CTRLTYPE_KEYBOARD_BTN14+1;
        public static final int   CTRLTYPE_KEYBOARD_BTN16 = CTRLTYPE_KEYBOARD_BTN15+1;
        public static final int   CTRLTYPE_KEYBOARD_SPLIT = CTRLTYPE_KEYBOARD_BTN16+1;
        public static final int   CTRLTYPE_KEYBOARD_ONE = CTRLTYPE_KEYBOARD_SPLIT+1;
        public static final int   CTRLTYPE_KEYBOARD_NINE = CTRLTYPE_KEYBOARD_ONE+1;
        public static final int   CTRLTYPE_KEYBOARD_ADDR = CTRLTYPE_KEYBOARD_NINE+1;
        public static final int   CTRLTYPE_KEYBOARD_INFO = CTRLTYPE_KEYBOARD_ADDR+1;
        public static final int   CTRLTYPE_KEYBOARD_REC = CTRLTYPE_KEYBOARD_INFO+1;
        public static final int   CTRLTYPE_KEYBOARD_FN1 = CTRLTYPE_KEYBOARD_REC+1;
        public static final int   CTRLTYPE_KEYBOARD_FN2 = CTRLTYPE_KEYBOARD_FN1+1;
        public static final int   CTRLTYPE_KEYBOARD_PLAY = CTRLTYPE_KEYBOARD_FN2+1;
        public static final int   CTRLTYPE_KEYBOARD_STOP = CTRLTYPE_KEYBOARD_PLAY+1;
        public static final int   CTRLTYPE_KEYBOARD_SLOW = CTRLTYPE_KEYBOARD_STOP+1;
        public static final int   CTRLTYPE_KEYBOARD_FAST = CTRLTYPE_KEYBOARD_SLOW+1;
        public static final int   CTRLTYPE_KEYBOARD_PREW = CTRLTYPE_KEYBOARD_FAST+1;
        public static final int   CTRLTYPE_KEYBOARD_NEXT = CTRLTYPE_KEYBOARD_PREW+1;
        public static final int   CTRLTYPE_KEYBOARD_JMPDOWN = CTRLTYPE_KEYBOARD_NEXT+1;
        public static final int   CTRLTYPE_KEYBOARD_JMPUP = CTRLTYPE_KEYBOARD_JMPDOWN+1;
        public static final int   CTRLTYPE_KEYBOARD_10PLUS = CTRLTYPE_KEYBOARD_JMPUP+1;
        public static final int   CTRLTYPE_KEYBOARD_SHIFT = CTRLTYPE_KEYBOARD_10PLUS+1;
        public static final int   CTRLTYPE_KEYBOARD_BACK = CTRLTYPE_KEYBOARD_SHIFT+1;
        public static final int   CTRLTYPE_KEYBOARD_LOGIN = CTRLTYPE_KEYBOARD_BACK+1; // 新网络键盘功能
        public static final int   CTRLTYPE_KEYBOARD_CHNNEL = CTRLTYPE_KEYBOARD_LOGIN+1; // 切换视频通道
        public static final int   CTRLTYPE_TRIGGER_ALARM_IN = 100;      // 触发报警输入
        public static final int   CTRLTYPE_TRIGGER_ALARM_OUT = CTRLTYPE_TRIGGER_ALARM_IN+1; // 触发报警输出
        public static final int   CTRLTYPE_CTRL_MATRIX = CTRLTYPE_TRIGGER_ALARM_OUT+1; // 矩阵控制
        public static final int   CTRLTYPE_CTRL_SDCARD = CTRLTYPE_CTRL_MATRIX+1; // SD卡控制(IPC产品)参数同硬盘控制
        public static final int   CTRLTYPE_BURNING_START = CTRLTYPE_CTRL_SDCARD+1; // 刻录机控制，开始刻录
        public static final int   CTRLTYPE_BURNING_STOP = CTRLTYPE_BURNING_START+1; // 刻录机控制，结束刻录
        public static final int   CTRLTYPE_BURNING_ADDPWD = CTRLTYPE_BURNING_STOP+1; // 刻录机控制，叠加密码(以'\0'为结尾的字符串，最大长度8位)
        public static final int   CTRLTYPE_BURNING_ADDHEAD = CTRLTYPE_BURNING_ADDPWD+1; // 刻录机控制，叠加片头(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int   CTRLTYPE_BURNING_ADDSIGN = CTRLTYPE_BURNING_ADDHEAD+1; // 刻录机控制，叠加打点到刻录信息(参数无)
        public static final int   CTRLTYPE_BURNING_ADDCURSTOMINFO = CTRLTYPE_BURNING_ADDSIGN+1; // 刻录机控制，自定义叠加(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int   CTRLTYPE_CTRL_RESTOREDEFAULT = CTRLTYPE_BURNING_ADDCURSTOMINFO+1; // 恢复设备的默认设置
        public static final int   CTRLTYPE_CTRL_CAPTURE_START = CTRLTYPE_CTRL_RESTOREDEFAULT+1; // 触发设备抓图
        public static final int   CTRLTYPE_CTRL_CLEARLOG = CTRLTYPE_CTRL_CAPTURE_START+1; // 清除日志
        public static final int   CTRLTYPE_TRIGGER_ALARM_WIRELESS = 200; // 触发无线报警(IPC产品)
        public static final int   CTRLTYPE_MARK_IMPORTANT_RECORD = CTRLTYPE_TRIGGER_ALARM_WIRELESS+1; // 标识重要录像文件
        public static final int   CTRLTYPE_CTRL_DISK_SUBAREA = CTRLTYPE_MARK_IMPORTANT_RECORD+1; // 网络硬盘分区
        public static final int   CTRLTYPE_BURNING_ATTACH = CTRLTYPE_CTRL_DISK_SUBAREA+1; // 刻录机控制，附件刻录.
        public static final int   CTRLTYPE_BURNING_PAUSE = CTRLTYPE_BURNING_ATTACH+1; // 刻录暂停
        public static final int   CTRLTYPE_BURNING_CONTINUE = CTRLTYPE_BURNING_PAUSE+1; // 刻录继续
        public static final int   CTRLTYPE_BURNING_POSTPONE = CTRLTYPE_BURNING_CONTINUE+1; // 刻录顺延
        public static final int   CTRLTYPE_CTRL_OEMCTRL = CTRLTYPE_BURNING_POSTPONE+1; // 报停控制
        public static final int   CTRLTYPE_BACKUP_START = CTRLTYPE_CTRL_OEMCTRL+1; // 设备备份开始
        public static final int   CTRLTYPE_BACKUP_STOP = CTRLTYPE_BACKUP_START+1; // 设备备份停止
        public static final int   CTRLTYPE_VIHICLE_WIFI_ADD = CTRLTYPE_BACKUP_STOP+1; // 车载手动增加WIFI配置
        public static final int   CTRLTYPE_VIHICLE_WIFI_DEC = CTRLTYPE_VIHICLE_WIFI_ADD+1; // 车载手动删除WIFI配置
        public static final int   CTRLTYPE_BUZZER_START = CTRLTYPE_VIHICLE_WIFI_DEC+1; // 蜂鸣器控制开始
        public static final int   CTRLTYPE_BUZZER_STOP = CTRLTYPE_BUZZER_START+1; // 蜂鸣器控制结束
        public static final int   CTRLTYPE_REJECT_USER = CTRLTYPE_BUZZER_STOP+1; // 剔除用户
        public static final int   CTRLTYPE_SHIELD_USER = CTRLTYPE_REJECT_USER+1; // 屏蔽用户
        public static final int   CTRLTYPE_RAINBRUSH = CTRLTYPE_SHIELD_USER+1; // 智能交通,雨刷控制
        public static final int   CTRLTYPE_MANUAL_SNAP = CTRLTYPE_RAINBRUSH+1; // 智能交通,手动抓拍(对应结构体MANUAL_SNAP_PARAMETER)
        public static final int   CTRLTYPE_MANUAL_NTP_TIMEADJUST = CTRLTYPE_MANUAL_SNAP+1; // 手动NTP校时
        public static final int   CTRLTYPE_NAVIGATION_SMS = CTRLTYPE_MANUAL_NTP_TIMEADJUST+1; // 导航信息和短消息
        public static final int   CTRLTYPE_CTRL_ROUTE_CROSSING = CTRLTYPE_NAVIGATION_SMS+1; // 路线点位信息
        public static final int   CTRLTYPE_BACKUP_FORMAT = CTRLTYPE_CTRL_ROUTE_CROSSING+1; // 格式化备份设备
        public static final int   CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT = CTRLTYPE_BACKUP_FORMAT+1; // 控制设备端本地预览分割(对应结构体DEVICE_LOCALPREVIEW_SLIPT_PARAMETER)
        public static final int   CTRLTYPE_CTRL_INIT_RAID = CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT+1; // RAID初始化
        public static final int   CTRLTYPE_CTRL_RAID = CTRLTYPE_CTRL_INIT_RAID+1; // RAID操作
        public static final int   CTRLTYPE_CTRL_SAPREDISK = CTRLTYPE_CTRL_RAID+1; // 热备盘操作
        public static final int   CTRLTYPE_WIFI_CONNECT = CTRLTYPE_CTRL_SAPREDISK+1; // 手动发起WIFI连接(对应结构体WIFI_CONNECT)
        public static final int   CTRLTYPE_WIFI_DISCONNECT = CTRLTYPE_WIFI_CONNECT+1; // 手动断开WIFI连接(对应结构体WIFI_CONNECT)
        public static final int   CTRLTYPE_CTRL_ARMED = CTRLTYPE_WIFI_DISCONNECT+1; // 布撤防操作
        public static final int   CTRLTYPE_CTRL_IP_MODIFY = CTRLTYPE_CTRL_ARMED+1; // 修改前端IP(对应结构体 NET_CTRL_IPMODIFY_PARAM)
        public static final int   CTRLTYPE_CTRL_WIFI_BY_WPS = CTRLTYPE_CTRL_IP_MODIFY+1; // wps连接wifi(对应结构体NET_CTRL_CONNECT_WIFI_BYWPS)
        public static final int   CTRLTYPE_CTRL_FORMAT_PATITION = CTRLTYPE_CTRL_WIFI_BY_WPS+1; // 格式化分区(对应结构体NET_FORMAT_PATITION)
        public static final int   CTRLTYPE_CTRL_EJECT_STORAGE = CTRLTYPE_CTRL_FORMAT_PATITION+1; // 手动卸载设备(对应结构体NET_EJECT_STORAGE_DEVICE)
        public static final int   CTRLTYPE_CTRL_LOAD_STORAGE = CTRLTYPE_CTRL_EJECT_STORAGE+1; // 手动装载设备(对应结构体NET_LOAD_STORAGE_DEVICE)
        public static final int   CTRLTYPE_CTRL_CLOSE_BURNER = CTRLTYPE_CTRL_LOAD_STORAGE+1; // 关闭刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等6
        public static final int   CTRLTYPE_CTRL_EJECT_BURNER = CTRLTYPE_CTRL_CLOSE_BURNER+1; // 弹出刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等4秒
        public static final int   CTRLTYPE_CTRL_CLEAR_ALARM = CTRLTYPE_CTRL_EJECT_BURNER+1; // 消警(对应结构体NET_CTRL_CLEAR_ALARM)
        public static final int   CTRLTYPE_CTRL_MONITORWALL_TVINFO = CTRLTYPE_CTRL_CLEAR_ALARM+1; // 电视墙信息显示(对应结构体NET_CTRL_MONITORWALL_TVINFO)
        public static final int   CTRLTYPE_CTRL_START_VIDEO_ANALYSE = CTRLTYPE_CTRL_MONITORWALL_TVINFO+1; // 开始视频智能分析(对应结构体NET_CTRL_START_VIDEO_ANALYSE)
        public static final int   CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE = CTRLTYPE_CTRL_START_VIDEO_ANALYSE+1; // 停止视频智能分析(对应结构体NET_CTRL_STOP_VIDEO_ANALYSE)
        public static final int   CTRLTYPE_CTRL_UPGRADE_DEVICE = CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE+1; // 控制启动设备升级,由设备独立完成升级过程,不需要传输升级文件
        public static final int   CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES = CTRLTYPE_CTRL_UPGRADE_DEVICE+1; // 切换多通道预览回放的通道(对应结构体NET_CTRL_MULTIPLAYBACK_CHANNALES)
        public static final int   CTRLTYPE_CTRL_SEQPOWER_OPEN = CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES+1; // 电源时序器打开开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int   CTRLTYPE_CTRL_SEQPOWER_CLOSE = CTRLTYPE_CTRL_SEQPOWER_OPEN+1; // 电源时序器关闭开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int   CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL = CTRLTYPE_CTRL_SEQPOWER_CLOSE+1; // 电源时序器打开开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int   CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL = CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL+1; // 电源时序器关闭开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int   CTRLTYPE_CTRL_PROJECTOR_RISE = CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL+1; // 投影仪上升(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int   CTRLTYPE_CTRL_PROJECTOR_FALL = CTRLTYPE_CTRL_PROJECTOR_RISE+1; // 投影仪下降(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int   CTRLTYPE_CTRL_PROJECTOR_STOP = CTRLTYPE_CTRL_PROJECTOR_FALL+1; // 投影仪停止(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int   CTRLTYPE_CTRL_INFRARED_KEY = CTRLTYPE_CTRL_PROJECTOR_STOP+1; // 红外按键(对应NET_CTRL_INFRARED_KEY_PARAM)
        public static final int   CTRLTYPE_CTRL_START_PLAYAUDIO = CTRLTYPE_CTRL_INFRARED_KEY+1; // 设备开始播放音频文件(对应结构体NET_CTRL_START_PLAYAUDIO)
        public static final int   CTRLTYPE_CTRL_STOP_PLAYAUDIO = CTRLTYPE_CTRL_START_PLAYAUDIO+1; // 设备停止播放音频文件
        public static final int   CTRLTYPE_CTRL_START_ALARMBELL = CTRLTYPE_CTRL_STOP_PLAYAUDIO+1; // 开启警号(对应结构体 NET_CTRL_ALARMBELL )
        public static final int   CTRLTYPE_CTRL_STOP_ALARMBELL = CTRLTYPE_CTRL_START_ALARMBELL+1; // 关闭警号(对应结构体 NET_CTRL_ALARMBELL )
        public static final int   CTRLTYPE_CTRL_ACCESS_OPEN = CTRLTYPE_CTRL_STOP_ALARMBELL+1; // 门禁控制-开门(对应结构体 NET_CTRL_ACCESS_OPEN)
        public static final int   CTRLTYPE_CTRL_SET_BYPASS = CTRLTYPE_CTRL_ACCESS_OPEN+1; // 设置旁路功能(对应结构体NET_CTRL_SET_BYPASS)
        public static final int   CTRLTYPE_CTRL_RECORDSET_INSERT = CTRLTYPE_CTRL_SET_BYPASS+1; // 添加记录，获得记录集编号(对应NET_CTRL_RECORDSET_INSERT_PARAM)
        public static final int   CTRLTYPE_CTRL_RECORDSET_UPDATE = CTRLTYPE_CTRL_RECORDSET_INSERT+1; // 更新某记录集编号的记录(对应 NET_CTRL_RECORDSET_PARAM)
        public static final int   CTRLTYPE_CTRL_RECORDSET_REMOVE = CTRLTYPE_CTRL_RECORDSET_UPDATE+1; // 根据记录集编号删除某记录(对应 NET_CTRL_RECORDSET_PARAM)
        public static final int   CTRLTYPE_CTRL_RECORDSET_CLEAR = CTRLTYPE_CTRL_RECORDSET_REMOVE+1; // 清除所有记录集信息(对应NET_CTRL_RECORDSET_PARAM)
        public static final int   CTRLTYPE_CTRL_ACCESS_CLOSE = CTRLTYPE_CTRL_RECORDSET_CLEAR+1; // 门禁控制-关门(对应结构体NET_CTRL_ACCESS_CLOSE)
        public static final int   CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET = CTRLTYPE_CTRL_ACCESS_CLOSE+1; // 报警子系统激活设置(对应结构体NET_CTRL_ALARM_SUBSYSTEM_SETACTIVE)
        public static final int   CTRLTYPE_CTRL_FORBID_OPEN_STROBE = CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET+1; // 禁止设备端开闸(对应结构体NET_CTRL_FORBID_OPEN_STROBE)
        public static final int   CTRLTYPE_CTRL_OPEN_STROBE = CTRLTYPE_CTRL_FORBID_OPEN_STROBE+1; // 开启道闸(对应结构体 NET_CTRL_OPEN_STROBE)
        public static final int   CTRLTYPE_CTRL_TALKING_REFUSE = CTRLTYPE_CTRL_OPEN_STROBE+1; // 对讲拒绝接听(对应结构体NET_CTRL_TALKING_REFUSE)
        public static final int   CTRLTYPE_CTRL_ARMED_EX = CTRLTYPE_CTRL_TALKING_REFUSE+1; // 布撤防操作(对应结构体CTRL_ARM_DISARM_PARAM_EX),对CTRL_ARM_DISARM_PARAM升级，建议用这个
        public static final int   CTRLTYPE_CTRL_NET_KEYBOARD = 400;     // 网络键盘控制(对应结构体NET_CTRL_NET_KEYBOARD)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_OPEN = CTRLTYPE_CTRL_NET_KEYBOARD+1; // 打开空调(对应结构体NET_CTRL_OPEN_AIRCONDITION)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_CLOSE = CTRLTYPE_CTRL_AIRCONDITION_OPEN+1; // 关闭空调(对应结构体NET_CTRL_CLOSE_AIRCONDITION)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_CLOSE+1; // 设定空调温度(对应结构体NET_CTRL_SET_TEMPERATURE)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE+1; // 调节空调温度(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_SETMODE = CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE+1; // 设置空调工作模式(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int   CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE = CTRLTYPE_CTRL_AIRCONDITION_SETMODE+1; // 设置空调送风模式(对应结构体NET_CTRL_AIRCONDITION_SETMODE)
        public static final int   CTRLTYPE_CTRL_RESTOREDEFAULT_EX = CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE+1; // 恢复设备的默认设置新协议(对应结构体 NET_CTRL_RESTORE_DEFAULT )
        // 恢复配置优先使用该枚举，如果接口失败，
        // 且CLIENT_GetLastError返回NET_UNSUPPORTED,再尝试使用NET_CTRL_RESTOREDEFAULT恢复配置
        public static final int   CTRLTYPE_CTRL_NOTIFY_EVENT = CTRLTYPE_CTRL_RESTOREDEFAULT_EX+1; // 向设备发送事件(对应结构体NET_NOTIFY_EVENT_DATA)
        public static final int   CTRLTYPE_CTRL_SILENT_ALARM_SET = CTRLTYPE_CTRL_NOTIFY_EVENT+1; // 无声报警设置
        public static final int   CTRLTYPE_CTRL_START_PLAYAUDIOEX = CTRLTYPE_CTRL_SILENT_ALARM_SET+1; // 设备开始语音播报(对应结构体NET_CTRL_START_PLAYAUDIOEX)
        public static final int   CTRLTYPE_CTRL_STOP_PLAYAUDIOEX = CTRLTYPE_CTRL_START_PLAYAUDIOEX+1; // 设备停止语音播报
        public static final int   CTRLTYPE_CTRL_CLOSE_STROBE = CTRLTYPE_CTRL_STOP_PLAYAUDIOEX+1; // 关闭道闸(对应结构体 NET_CTRL_CLOSE_STROBE)
        public static final int   CTRLTYPE_CTRL_SET_ORDER_STATE = CTRLTYPE_CTRL_CLOSE_STROBE+1; // 设置车位预定状态(对应结构体NET_CTRL_SET_ORDER_STATE)
        public static final int   CTRLTYPE_CTRL_RECORDSET_INSERTEX = CTRLTYPE_CTRL_SET_ORDER_STATE+1; // 添加记录，获得记录集编号(对应 NET_CTRL_RECORDSET_INSERT_PARAM )
        public static final int   CTRLTYPE_CTRL_RECORDSET_UPDATEEX = CTRLTYPE_CTRL_RECORDSET_INSERTEX+1; // 更新某记录集编号的记录(对应NET_CTRL_RECORDSET_PARAM)
        public static final int   CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT = CTRLTYPE_CTRL_RECORDSET_UPDATEEX+1; // 信息采集(对应结构体NET_CTRL_CAPTURE_FINGER_PRINT)
        public static final int   CTRLTYPE_CTRL_ECK_LED_SET = CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT+1; // 停车场出入口控制器LED设置(对应结构体NET_CTRL_ECK_LED_SET_PARAM)
        public static final int   CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT = CTRLTYPE_CTRL_ECK_LED_SET+1; // 智能停车系统出入口机IC卡信息导入(对应结构体NET_CTRL_ECK_IC_CARD_IMPORT_PARAM)
        public static final int   CTRLTYPE_CTRL_ECK_SYNC_IC_CARD = CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT+1; // 智能停车系统出入口机IC卡信息同步指令，收到此指令后，设备删除原有IC卡信息(对应结构体NET_CTRL_ECK_SYNC_IC_CARD_PARAM)
        public static final int   CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE = CTRLTYPE_CTRL_ECK_SYNC_IC_CARD+1; // 删除指定无线设备(对应结构体NET_CTRL_LOWRATEWPAN_REMOVE)
        public static final int   CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY = CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE+1; // 修改无线设备信息(对应结构体NET_CTRL_LOWRATEWPAN_MODIFY)
        public static final int   CTRLTYPE_CTRL_ECK_SET_PARK_INFO = CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY+1; // 智能停车系统出入口机设置车位信息(对应结构体NET_CTRL_ECK_SET_PARK_INFO_PARAM)
        public static final int   CTRLTYPE_CTRL_VTP_DISCONNECT = CTRLTYPE_CTRL_ECK_SET_PARK_INFO+1; // 挂断视频电话(对应结构体NET_CTRL_VTP_DISCONNECT)
        public static final int   CTRLTYPE_CTRL_UPDATE_FILES = CTRLTYPE_CTRL_VTP_DISCONNECT+1; // 远程投放多媒体文件更新(对应结构体NET_CTRL_UPDATE_FILES)
        public static final int   CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH = CTRLTYPE_CTRL_UPDATE_FILES+1; // 保存上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_SAVE_SWITCH)
        public static final int   CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH = CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH+1; // 恢复上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_RESTORE_SWITCH)
        public static final int   CTRLTYPE_CTRL_VTP_DIVERTACK = CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH+1; // 呼叫转发响应(对应结构体NET_CTRL_VTP_DIVERTACK)
        public static final int   CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE = CTRLTYPE_CTRL_VTP_DIVERTACK+1; // 雨刷来回刷一次，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVEONCE)
        public static final int   CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY = CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE+1; // 雨刷来回循环刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY)
        public static final int   CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE = CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY+1; // 雨刷停止刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_STOPMOVE)
        public static final int   CTRLTYPE_CTRL_ALARM_ACK = CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE+1; // 报警事件确认(对应结构体NET_CTRL_ALARM_ACK)
        // NET_CTRL_ALARM_ACK 该操作切勿在报警回调接口中调用
        public static final int   CTRLTYPE_CTRL_RECORDSET_IMPORT = CTRLTYPE_CTRL_ALARM_ACK + 1; // 批量导入记录集信息(对应 NET_CTRL_RECORDSET_PARAM )
        public static final int   CTRLTYPE_CTRL_DELIVERY_FILE = CTRLTYPE_CTRL_RECORDSET_IMPORT + 1; // 向视频输出口投放视频和图片文件, 楼宇对讲使用，同一时间投放(对应 NET_CTRL_DELIVERY_FILE )
        public static final int   CTRLTYPE_CTRL_FORCE_BREAKING = CTRLTYPE_CTRL_DELIVERY_FILE + 1; // 强制产生违章类型(对应 NET_CTRL_FORCE_BREAKING)
        public static final int   CTRLTYPE_CTRL_RESTORE_EXCEPT = CTRLTYPE_CTRL_FORCE_BREAKING + 1; // 恢复除指定配置外的其他配置为默认。
        public static final int   CTRLTYPE_CTRL_SET_PARK_INFO = CTRLTYPE_CTRL_RESTORE_EXCEPT + 1; // 设置停车信息，平台设置给相机，内容用于点阵屏显示(对应结构体 NET_CTRL_SET_PARK_INFO)
        public static final int   CTRLTYPE_CTRL_CLEAR_SECTION_STAT = CTRLTYPE_CTRL_SET_PARK_INFO + 1; // 清除当前时间段内人数统计信息, 重新从0开始计算(对应结构体NET_CTRL_CLEAR_SECTION_STAT_INFO)
        public static final int   CTRLTYPE_CTRL_DELIVERY_FILE_BYCAR = CTRLTYPE_CTRL_CLEAR_SECTION_STAT + 1; // 向视频输出口投放视频和图片文件, 车载使用，广告单独时间投放(对应NET_CTRL_DELIVERY_FILE_BYCAR)
        public static final int   CTRLTYPE_CTRL_ECK_GUIDINGPANEL_CONTENT = CTRLTYPE_CTRL_DELIVERY_FILE_BYCAR + 1; // 设置诱导屏显示内容(对应结构体 NET_CTRL_ECK_GUIDINGPANEL_CONTENT)
        public static final int   CTRLTYPE_CTRL_SET_SAFE_LEVEL = CTRLTYPE_CTRL_ECK_GUIDINGPANEL_CONTENT + 1; // 设置门禁安全等级(对应结构体，pInBuf= NET_IN_SET_SAFE_LEVEL*, pOutBuf= NET_OUT_SET_SAFE_LEVEL * )
        public static final int   CTRLTYPE_CTRL_VTP_INVITEACK = CTRLTYPE_CTRL_SET_SAFE_LEVEL + 1; // 对讲请求回复(对应结构体 NET_CTRL_VTP_INVITEACK)
        public static final int   CTRLTYPE_CTRL_ACCESS_RESET_PASSWORD = CTRLTYPE_CTRL_VTP_INVITEACK + 1; // 门禁控制-重置密码(对应结构体 NET_CTRL_ACCESS_RESET_PASSWORD)
        public static final int   CTRLTYPE_CTRL_ACCESS_CALL_LIFT = CTRLTYPE_CTRL_ACCESS_RESET_PASSWORD+1; // 门禁控制-呼梯(对应结构体 NET_CTRL_ACCESS_CALL_LIFT)
        /**
         *  以下命令只在 CLIENT_ControlDeviceEx 上有效
         */
        public static final int   CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER = 0x10000; // 设置热成像快门启用/禁用,pInBuf= NET_IN_THERMO_EN_SHUTTER*, pOutBuf= NET_OUT_THERMO_EN_SHUTTER *
        public static final int   CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK = CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER+1; // 设置测温项的osd为高亮,pInBuf=NET_IN_RADIOMETRY_SETOSDMARK*,pOutBuf= NET_OUT_RADIOMETRY_SETOSDMARK *
        public static final int   CTRLTYPE_CTRL_AUDIO_REC_START_NAME = CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK+1; // 开启音频录音并得到录音名,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int   CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME = CTRLTYPE_CTRL_AUDIO_REC_START_NAME+1; // 关闭音频录音并返回文件名称,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int   CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT = CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME+1; // 即时抓图(又名手动抓图),pInBuf  =NET_IN_SNAP_MNG_SHOT *, pOutBuf = NET_OUT_SNAP_MNG_SHOT *
        public static final int   CTRLTYPE_CTRL_LOG_STOP = CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT+1; // 强制同步缓存数据到数据库并关闭数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
        public static final int   CTRLTYPE_CTRL_LOG_RESUME = CTRLTYPE_CTRL_LOG_STOP+1; // 恢复数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
        public static final int   CTRLTYPE_CTRL_POS_ADD = CTRLTYPE_CTRL_LOG_RESUME + 1; // 增加一个Pos设备, pInBuf = NET_IN_POS_ADD *, pOutBuf = NET_OUT_POS_ADD *
        public static final int   CTRLTYPE_CTRL_POS_REMOVE = CTRLTYPE_CTRL_POS_ADD + 1; // 删除一个Pos设备, pInBuf = NET_IN_POS_REMOVE *, pOutBuf = NET_OUT_POS_REMOVE *
        public static final int   CTRLTYPE_CTRL_POS_REMOVE_MULTI = CTRLTYPE_CTRL_POS_REMOVE + 1; // 批量删除Pos设备, pInBuf = NET_IN_POS_REMOVE_MULTI *, pOutBuf = NET_OUT_POS_REMOVE_MULTI *
        public static final int   CTRLTYPE_CTRL_POS_MODIFY = CTRLTYPE_CTRL_POS_REMOVE_MULTI + 1; // 修改一个Pos设备, pInBuf = NET_IN_POS_ADD *, pOutBuf = NET_OUT_POS_ADD *
        public static final int   CTRLTYPE_CTRL_SET_SOUND_ALARM = CTRLTYPE_CTRL_POS_MODIFY + 1; // 触发有声报警, pInBuf = NET_IN_SOUND_ALARM *, pOutBuf = NET_OUT_SOUND_ALARM *
        public static final int   CTRLTYPE_CTRL_AUDIO_MATRIX_SILENCE = CTRLTYPE_CTRL_SET_SOUND_ALARM + 1; // 音频举证一键静音控制(对应pInBuf = NET_IN_AUDIO_MATRIX_SILENCE, pOutBuf =  NET_OUT_AUDIO_MATRIX_SILENCE)
        public static final int   CTRLTYPE_CTRL_MANUAL_UPLOAD_PICTURE = CTRLTYPE_CTRL_AUDIO_MATRIX_SILENCE + 1; // 设置手动上传, pInBuf = NET_IN_MANUAL_UPLOAD_PICTURE *, pOutBUf = NET_OUT_MANUAL_UPLOAD_PICTURE *
        public static final int   CTRLTYPE_CTRL_REBOOT_NET_DECODING_DEV = CTRLTYPE_CTRL_MANUAL_UPLOAD_PICTURE + 1; // 重启网络解码设备, pInBuf = NET_IN_REBOOT_NET_DECODING_DEV *, pOutBuf = NET_OUT_REBOOT_NET_DECODING_DEV *
        public static final int   CTRLTYPE_CTRL_SET_IC_SENDER = CTRLTYPE_CTRL_REBOOT_NET_DECODING_DEV + 1; // ParkingControl 设置发卡设备, pInBuf = NET_IN_SET_IC_SENDER *, pOutBuf = NET_OUT_SET_IC_SENDER *
        public static final int   CTRLTYPE_CTRL_SET_MEDIAKIND = CTRLTYPE_CTRL_SET_IC_SENDER + 1; // 设置预览码流组成,如仅音频,仅视频,音视频 pInBuf = NET_IN_SET_MEDIAKIND *, pOutBuf = NET_OUT_SET_MEDIAKIND *// 配合功能列表能力集使用, EN_ENCODE_CHN,2-预览支持音视频分开获取
        public static final int   CTRLTYPE_CTRL_LOWRATEWPAN_ADD = CTRLTYPE_CTRL_SET_MEDIAKIND + 1; // 增加无线设备信息(对应结构体 pInBuf = NET_CTRL_LOWRATEWPAN_ADD *, pOutBUf = NULL)
        public static final int   CTRLTYPE_CTRL_LOWRATEWPAN_REMOVEALL = CTRLTYPE_CTRL_LOWRATEWPAN_ADD + 1; // 删除所有的无线设备信息(对应结构体 pInBuf = NET_CTRL_LOWRATEWPAN_REMOVEALL *, pOutBUf = NULL)
        public static final int   CTRLTYPE_CTRL_SET_DOOR_WORK_MODE = CTRLTYPE_CTRL_LOWRATEWPAN_REMOVEALL + 1; // 设置门锁工作模式(对应结构体 pInBuf = NET_IN_CTRL_ACCESS_SET_DOOR_WORK_MODE *, pOutBUf = NULL)
        public static final int   CTRLTYPE_CTRL_TEST_MAIL = CTRLTYPE_CTRL_SET_DOOR_WORK_MODE + 1; // 测试邮件 pInBuf = NET_IN_TEST_MAIL *, pOutBUf = NET_OUT_TEST_MAIL *
        public static final int   CTRLTYPE_CTRL_CONTROL_SMART_SWITCH = CTRLTYPE_CTRL_TEST_MAIL + 1; // 控制智能开关 pInBuf = NET_IN_CONTROL_SMART_SWITCH *, pOutBUf = NET_OUT_CONTROL_SMART_SWITCH *
        public static final int   CTRLTYPE_CTRL_LOWRATEWPAN_SETWORKMODE = CTRLTYPE_CTRL_CONTROL_SMART_SWITCH + 1; // 设置探测器的工作模式(对应结构体pInBuf = NET_IN_CTRL_LOWRATEWPAN_SETWORKMODE *, pOutBUf = NULL)
        public static final int   CTRLTYPE_CTRL_COAXIAL_CONTROL_IO = CTRLTYPE_CTRL_LOWRATEWPAN_SETWORKMODE + 1; // 发送同轴IO控制命令(对应结构体pInBuf = NET_IN_CONTROL_COAXIAL_CONTROL_IO*, pOutBUf = NET_OUT_CONTROL_COAXIAL_CONTROL_IO*)
        public static final int   CTRLTYPE_CTRL_START_REMOTELOWRATEWPAN_ALARMBELL = CTRLTYPE_CTRL_COAXIAL_CONTROL_IO + 1; // 开启无线警号 (对应结构体pInBuf = NET_IN_START_REMOTELOWRATEWPAN_ALARMBELL*, pOutBUf = NET_OUT_START_REMOTELOWRATEWPAN_ALARMBELL*)
        public static final int   CTRLTYPE_CTRL_STOP_REMOTELOWRATEWPAN_ALARMBELL = CTRLTYPE_CTRL_START_REMOTELOWRATEWPAN_ALARMBELL + 1; // 关闭无线警号 (对应结构体pInBuf = NET_IN_STOP_REMOTELOWRATEWPAN_ALARMBELL*, pOutBUf = NET_OUT_STOP_REMOTELOWRATEWPAN_ALARMBELL*)
        public static final int   CTRLTYPE_CTRL_THERMO_DO_FFC = CTRLTYPE_CTRL_STOP_REMOTELOWRATEWPAN_ALARMBELL + 1; // 热成像FFC校准(对应结构体 pInBuf = NET_IN_THERMO_DO_FFC *,pOutBuf = NET_OUT_THERMO_DO_FFC *)
        public static final int   CTRLTYPE_CTRL_THERMO_FIX_FOCUS = CTRLTYPE_CTRL_THERMO_DO_FFC + 1; // 热成像双目定焦调(对应结构体 pInBuf = NET_IN_THERMO_FIX_FOCUS *,pOutBuf = NET_OUT_THERMO_FIX_FOCUS *)
        public static final int   CTRLTYPE_CTRL_SET_THIRD_CALLSTATUS = CTRLTYPE_CTRL_THERMO_FIX_FOCUS + 1; // 设置对讲状态(对应结构体pInBuf = NET_IN_VTP_THIRDCALL_STATUS*, pOutBuf = NET_OUT_VTP_THIRDCALL_STATUS*)
        public static final int   CTRL_ACCESS_CLEAR_STATUS = CTRLTYPE_CTRL_SET_THIRD_CALLSTATUS + 1; // 门禁-清除用户进出门状态 (对应结构体pInBuf = NET_IN_ACCESS_CLEAR_STATUS*, pOutBuf = NET_OUT_ACCESS_CLEAR_STATUS *)
        public static final int   CTRL_ACCESS_DEAL_RECORD = CTRL_ACCESS_CLEAR_STATUS + 1; // 门禁-查询/设置用户进出门记录 (对应结构体pInBuf = NET_IN_ACCESS_DEAL_RECORD*, pOutBuf = NET_OUT_ACCESS_DEAL_RECORD*)
        public static final int   CTRL_QUERY_DELIVERED_FILE = CTRL_ACCESS_DEAL_RECORD + 1; // 向视频输出口查询广告信息,楼宇对讲使用,(对应NET_CTRL_QUERY_DELIVERY_FILE)
        public static final int   CTRL_SET_PARK_CONTROL_INFO = CTRL_QUERY_DELIVERED_FILE + 1; // 设置停车控制信息(点阵屏和语音播报的控制)(对应结构体pInBuf = NET_IN_SET_PARK_CONTROL_INFO*, pOutBuf = NET_OUT_SET_PARK_CONTROL_INFO*)
        /**********LowRateWPAN控制(0x10100-0x10150)**********************************************************************************/
        public static final int   CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL = 0x10100; // 获取无线设备信号强度(对应结构体 pInBuf = NET_IN_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL *,pOutBuf = NET_OUT_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL *)
        public static final int   CTRL_LOWRATEWPAN_SET_ACCESSORY_PARAM = CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL+1; // 设置配件信息(对应结构体pInBuf = NET_IN_CTRL_LOWRATEWPAN_ACCESSORY_PARAM *,pOutBuf = NULL)
    }

    // 视频压缩格式
    public static class CFG_VIDEO_COMPRESSION extends SdkStructure
    {
        public static final int   VIDEO_FORMAT_MPEG4 = 0;               //MPEG4
        public static final int   VIDEO_FORMAT_MS_MPEG4 = VIDEO_FORMAT_MPEG4+1; //MS-MPEG4
        public static final int   VIDEO_FORMAT_MPEG2 = VIDEO_FORMAT_MS_MPEG4+1; //MPEG2
        public static final int   VIDEO_FORMAT_MPEG1 = VIDEO_FORMAT_MPEG2+1; //MPEG1
        public static final int   VIDEO_FORMAT_H263 = VIDEO_FORMAT_MPEG1+1; //H.263
        public static final int   VIDEO_FORMAT_MJPG = VIDEO_FORMAT_H263+1; //MJPG
        public static final int   VIDEO_FORMAT_FCC_MPEG4 = VIDEO_FORMAT_MJPG+1; //FCC-MPEG4
        public static final int   VIDEO_FORMAT_H264 = VIDEO_FORMAT_FCC_MPEG4+1; //H.264
        public static final int   VIDEO_FORMAT_H265 = VIDEO_FORMAT_H264+1; //H.265
    }

    // 码流控制模式
    public static class CFG_BITRATE_CONTROL extends SdkStructure
    {
        public static final int   BITRATE_CBR = 0;                      //固定码流
        public static final int   BITRATE_VBR = BITRATE_CBR+1;          //可变码流
    }

    // H264 编码级别
    public static class CFG_H264_PROFILE_RANK extends SdkStructure
    {
        public static final int   PROFILE_BASELINE = 1;                 //提供I/P帧，仅支持progressive(逐行扫描)和CAVLC
        public static final int   PROFILE_MAIN = PROFILE_BASELINE+1;    //提供I/P/B帧，支持progressiv和interlaced，提供CAVLC或CABAC
        public static final int   PROFILE_EXTENDED = PROFILE_MAIN+1;    //提供I/P/B/SP/SI帧，仅支持progressive(逐行扫描)和CAVLC
        public static final int   PROFILE_HIGH = PROFILE_EXTENDED+1;    //即FRExt，Main_Profile基础上新增：8x8intraprediction(8x8帧内预测), custom
        // quant(自定义量化), lossless video coding(无损视频编码), 更多的yuv格式
    }

    // 画质
    public static class CFG_IMAGE_QUALITY extends SdkStructure
    {
        public static final int   IMAGE_QUALITY_Q10 = 1;                //图像质量10%
        public static final int   IMAGE_QUALITY_Q30 = IMAGE_QUALITY_Q10+1; //图像质量30%
        public static final int   IMAGE_QUALITY_Q50 = IMAGE_QUALITY_Q30+1; //图像质量50%
        public static final int   IMAGE_QUALITY_Q60 = IMAGE_QUALITY_Q50+1; //图像质量60%
        public static final int   IMAGE_QUALITY_Q80 = IMAGE_QUALITY_Q60+1; //图像质量80%
        public static final int   IMAGE_QUALITY_Q100 = IMAGE_QUALITY_Q80+1; //图像质量100%
    }

    // 视频格式
    public static class CFG_VIDEO_FORMAT extends SdkStructure
    {
        // 能力
        public byte             abCompression;                        // 类型为bool, 取值0或1
        public byte             abWidth;                              // 类型为bool, 取值0或1
        public byte             abHeight;                             // 类型为bool, 取值0或1
        public byte             abBitRateControl;                     // 类型为bool, 取值0或1
        public byte             abBitRate;                            // 类型为bool, 取值0或1
        public byte             abFrameRate;                          // 类型为bool, 取值0或1
        public byte             abIFrameInterval;                     // 类型为bool, 取值0或1
        public byte             abImageQuality;                       // 类型为bool, 取值0或1
        public byte             abFrameType;                          // 类型为bool, 取值0或1
        public byte             abProfile;                            // 类型为bool, 取值0或1
        // 信息
        public int              emCompression;                        //视频压缩格式, 取值为CFG_VIDEO_COMPRESSION中的值
        public int              nWidth;                               //视频宽度
        public int              nHeight;                              //视频高度
        public int              emBitRateControl;                     //码流控制模式, 取值为CFG_BITRATE_CONTROL中的值
        public int              nBitRate;                             //视频码流(kbps)
        public float            nFrameRate;                           //视频帧率
        public int              nIFrameInterval;                      //I帧间隔(1-100)，比如50表示每49个B帧或P帧，设置一个I帧。
        public int              emImageQuality;                       //图像质量, 取值为CFG_IMAGE_QUALITY中的值
        public int              nFrameType;                           //打包模式，0－DHAV，1－"PS"
        public int              emProfile;                            //H.264编码级别, 取值为CFG_H264_PROFILE_RANK中的值
        public int              nMaxBitrate;                          // 最大码流单位是kbps（博世专用）
    }

    // 音频编码模式
    public static class CFG_AUDIO_FORMAT extends SdkStructure
    {
        public static final int   AUDIO_FORMAT_G711A = 0;               //G711a
        public static final int   AUDIO_FORMAT_PCM = AUDIO_FORMAT_G711A+1; //PCM
        public static final int   AUDIO_FORMAT_G711U = AUDIO_FORMAT_PCM+1; //G711u
        public static final int   AUDIO_FORMAT_AMR = AUDIO_FORMAT_G711U+1; //AMR
        public static final int   AUDIO_FORMAT_AAC = AUDIO_FORMAT_AMR+1; //AAC
    }

    // 音频格式
    public static class CFG_AUDIO_ENCODE_FORMAT extends SdkStructure
    {
        // 能力
        public byte             abCompression;                        // 类型为bool, 取值0或1
        public byte             abDepth;                              // 类型为bool, 取值0或1
        public byte             abFrequency;                          // 类型为bool, 取值0或1
        public byte             abMode;                               // 类型为bool, 取值0或1
        public byte             abFrameType;                          // 类型为bool, 取值0或1
        public byte             abPacketPeriod;                       // 类型为bool, 取值0或1
        public byte             abChannels;                           // 类型为bool, 取值0或1
        public byte             abMix;                                // 类型为bool, 取值0或1
        // 信息
        public int              emCompression;                        //音频压缩模式，取值为CFG_AUDIO_FORMAT中的值
        public int              nDepth;                               //音频采样深度
        public int              nFrequency;                           //音频采样频率
        public int              nMode;                                //音频编码模式
        public int              nFrameType;                           //音频打包模式,0-DHAV,1-PS
        public int              nPacketPeriod;                        //音频打包周期,ms
        public int              nChannelsNum;                         // 视频通道的伴音通道号列表个数
        public int              arrChannels[] = new int[8];           // 视频通道的伴音通道号列表
        public int              bMix;                                 // 是否同源
    }

    // 视频编码参数
    public static class CFG_VIDEOENC_OPT extends SdkStructure
    {
        // 能力
        public byte             abVideoEnable;                        // 类型为bool, 取值0或1
        public byte             abAudioEnable;                        // 类型为bool, 取值0或1
        public byte             abSnapEnable;                         // 类型为bool, 取值0或1
        public byte             abAudioAdd;                           //音频叠加能力, 类型为bool, 取值0或1
        public byte             abAudioFormat;                        // 类型为bool, 取值0或1
        // 信息
        public int              bVideoEnable;                         //视频使能, 类型为BOOL, 取值0或者1
        public CFG_VIDEO_FORMAT stuVideoFormat;                       //视频格式
        public int              bAudioEnable;                         //音频使能, 类型为BOOL, 取值0或者1
        public int              bSnapEnable;                          //定时抓图使能, 类型为BOOL, 取值0或者1
        public int              bAudioAddEnable;                      //音频叠加使能, 类型为BOOL, 取值0或者1
        public CFG_AUDIO_ENCODE_FORMAT stuAudioFormat;                //音频格式
    }

    // 遮挡信息
    public static class CFG_COVER_INFO extends SdkStructure
    {
        // 能力
        public byte             abBlockType;                          // 类型为bool, 取值0或1
        public byte             abEncodeBlend;                        // 类型为bool, 取值0或1
        public byte             abPreviewBlend;                       // 类型为bool, 取值0或1
        // 信息
        public CFG_RECT         stuRect = new CFG_RECT();             //覆盖的区域坐标
        public CFG_RGBA         stuColor = new CFG_RGBA();            //覆盖的颜色
        public int              nBlockType;                           //覆盖方式；0－黑块，1－马赛克
        public int              nEncodeBlend;                         //编码级遮挡；1－生效，0－不生效
        public int              nPreviewBlend;                        //预览遮挡；1－生效，0－不生效
    }

    // 多区域遮挡配置
    public static class CFG_VIDEO_COVER extends SdkStructure
    {
        public int              nTotalBlocks;                         //支持的遮挡块数
        public int              nCurBlocks;                           //已设置的块数
        public CFG_COVER_INFO[] stuCoverBlock = (CFG_COVER_INFO[])new CFG_COVER_INFO().toArray(MAX_VIDEO_COVER_NUM); // 覆盖的区域
    }

    // OSD信息
    public static class CFG_OSD_INFO extends SdkStructure
    {
        // 能力
        public byte             abShowEnable;                         // 类型为bool, 取值0或1
        // 信息
        public CFG_RGBA         stuFrontColor = new CFG_RGBA();       //前景颜色
        public CFG_RGBA         stuBackColor = new CFG_RGBA();        //背景颜色
        public CFG_RECT         stuRect = new CFG_RECT();             //矩形区域
        public int              bShowEnable;                          //显示使能, 类型为BOOL, 取值0或者1
    }

    // 画面颜色属性
    public static class CFG_COLOR_INFO extends SdkStructure
    {
        public int              nBrightness;                          //亮度(0-100)
        public int              nContrast;                            //对比度(0-100)
        public int              nSaturation;                          //饱和度(0-100)
        public int              nHue;                                 //色度(0-100)
        public int              nGain;                                //增益(0-100)
        public int              bGainEn;                              //增益使能, 类型为BOOL, 取值0或者1
    }

    // 图像通道属性信息
    public static class CFG_ENCODE_INFO extends SdkStructure
    {
        public int              nChannelID;                           //通道号(0开始),获取时，该字段有效；设置时，该字段无效
        public byte[]           szChnName = new byte[MAX_CHANNELNAME_LEN]; //无效字段
        public CFG_VIDEOENC_OPT[] stuMainStream = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM); // 主码流，0－普通录像，1-动检录像，2－报警录像
        public int              nValidCountMainStream;                // 主码流数组中有效的个数
        public CFG_VIDEOENC_OPT[] stuExtraStream = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM); // 辅码流，0－辅码流1，1－辅码流2，2－辅码流3
        public int              nValidCountExtraStream;               // 辅码流数组中有效的个数
        public CFG_VIDEOENC_OPT[] stuSnapFormat = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM); // 抓图，0－普通抓图，1－动检抓图，2－报警抓图
        public int              nValidCountSnapFormat;                // 抓图数组中有效的个数
        public int              dwCoverAbilityMask;                   //无效字段
        public int              dwCoverEnableMask;                    //无效字段
        public CFG_VIDEO_COVER  stuVideoCover;                        //无效字段
        public CFG_OSD_INFO     stuChnTitle;                          //无效字段
        public CFG_OSD_INFO     stuTimeTitle;                         //无效字段
        public CFG_COLOR_INFO   stuVideoColor;                        //无效字段
        public int              emAudioFormat;                        //无效字段, 取值为CFG_AUDIO_FORMAT中的值
        public int              nProtocolVer;                         //协议版本号,只读,获取时，该字段有效；设置时，该字段无效
        public int              bIsUseExtraStreamEx;                  //是否使用辅码流扩展
        public CFG_VIDEOENC_OPT[] stuExtraStreamEx = new CFG_VIDEOENC_OPT[12]; //辅码流扩展，0－辅码流1，1－辅码流2，2－辅码流3，3－辅码流4，4－辅码流5，5－辅码流6，6－辅码流7，7－辅码流8，8－辅码流9，9－辅码流10，10－辅码流11，11－辅码流12,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.CFG_VIDEOENC_OPT}
        public int              nValidCountExtraStreamEx;             //辅码流扩展数组中有效的个数
        public byte[]           szReserved = new byte[120];           //保留字节
    }

    // 设备软件版本信息,高16位表示主版本号,低16位表示次版本号
    public static class NET_VERSION_INFO extends SdkStructure
    {
        public int              dwSoftwareVersion;
        public int              dwSoftwareBuildDate;
        public int              dwDspSoftwareVersion;
        public int              dwDspSoftwareBuildDate;
        public int              dwPanelVersion;
        public int              dwPanelSoftwareBuildDate;
        public int              dwHardwareVersion;
        public int              dwHardwareDate;
        public int              dwWebVersion;
        public int              dwWebBuildDate;
    }

    // 设备软件版本信息,对应CLIENT_QueryDevState接口
    public static class NETDEV_VERSION_INFO extends SdkStructure
    {
        public byte[]           szDevSerialNo = new byte[NET_DEV_SERIALNO_LEN]; // 序列号
        public int              byDevType;                            // 设备类型,见枚举  NET_DEVICE_TYPE
        public byte[]           szDevType = new byte[NET_DEV_TYPE_LEN]; // 设备详细型号,字符串格式,可能为空
        public int              nProtocalVer;                         // 协议版本号
        public byte[]           szSoftWareVersion = new byte[NET_MAX_URL_LEN];
        public int              dwSoftwareBuildDate;
        public byte[]           szPeripheralSoftwareVersion = new byte[NET_MAX_URL_LEN]; // 从片版本信息,字符串格式,可能为空
        public int              dwPeripheralSoftwareBuildDate;
        public byte[]           szGeographySoftwareVersion = new byte[NET_MAX_URL_LEN]; // 地理信息定位芯片版本信息,字符串格式,可能为空
        public int              dwGeographySoftwareBuildDate;
        public byte[]           szHardwareVersion = new byte[NET_MAX_URL_LEN];
        public int              dwHardwareDate;
        public byte[]           szWebVersion = new byte[NET_MAX_URL_LEN];
        public int              dwWebBuildDate;
        public byte[]           reserved = new byte[256];
    }

    // 设备类型
    public static class NET_DEVICE_TYPE extends SdkStructure
    {
        public static final int   NET_PRODUCT_NONE = 0;
        public static final int   NET_DVR_NONREALTIME_MACE = 1;         // 非实时MACE
        public static final int   NET_DVR_NONREALTIME = 2;              // 非实时
        public static final int   NET_NVS_MPEG1 = 3;                    // 网络视频服务器
        public static final int   NET_DVR_MPEG1_2 = 4;                  // MPEG1二路录像机
        public static final int   NET_DVR_MPEG1_8 = 5;                  // MPEG1八路录像机
        public static final int   NET_DVR_MPEG4_8 = 6;                  // MPEG4八路录像机
        public static final int   NET_DVR_MPEG4_16 = 7;                 // MPEG4十六路录像机
        public static final int   NET_DVR_MPEG4_SX2 = 8;                // LB系列录像机
        public static final int   NET_DVR_MEPG4_ST2 = 9;                // GB系列录像机
        public static final int   NET_DVR_MEPG4_SH2 = 10;               // HB系列录像机               10
        public static final int   NET_DVR_MPEG4_GBE = 11;               // GBE系列录像机
        public static final int   NET_DVR_MPEG4_NVSII = 12;             // II代网络视频服务器
        public static final int   NET_DVR_STD_NEW = 13;                 // 新标准配置协议
        public static final int   NET_DVR_DDNS = 14;                    // DDNS服务器
        public static final int   NET_DVR_ATM = 15;                     // ATM机
        public static final int   NET_NB_SERIAL = 16;                   // 二代非实时NB系列机器
        public static final int   NET_LN_SERIAL = 17;                   // LN系列产品
        public static final int   NET_BAV_SERIAL = 18;                  // BAV系列产品
        public static final int   NET_SDIP_SERIAL = 19;                 // SDIP系列产品
        public static final int   NET_IPC_SERIAL = 20;                  // IPC系列产品                20
        public static final int   NET_NVS_B = 21;                       // NVS B系列
        public static final int   NET_NVS_C = 22;                       // NVS H系列
        public static final int   NET_NVS_S = 23;                       // NVS S系列
        public static final int   NET_NVS_E = 24;                       // NVS E系列
        public static final int   NET_DVR_NEW_PROTOCOL = 25;            // 从QueryDevState中查询设备类型,以字符串格式
        public static final int   NET_NVD_SERIAL = 26;                  // 解码器
        public static final int   NET_DVR_N5 = 27;                      // N5
        public static final int   NET_DVR_MIX_DVR = 28;                 // 混合DVR
        public static final int   NET_SVR_SERIAL = 29;                  // SVR系列
        public static final int   NET_SVR_BS = 30;                      // SVR-BS                     30
        public static final int   NET_NVR_SERIAL = 31;                  // NVR系列
        public static final int   NET_DVR_N51 = 32;                     // N51
        public static final int   NET_ITSE_SERIAL = 33;                 // ITSE 智能分析盒
        public static final int   NET_ITC_SERIAL = 34;                  // 智能交通像机设备
        public static final int   NET_HWS_SERIAL = 35;                  // 雷达测速仪HWS
        public static final int   NET_PVR_SERIAL = 36;                  // 便携式音视频录像机
        public static final int   NET_IVS_SERIAL = 37;                  // IVS（智能视频服务器系列）
        public static final int   NET_IVS_B = 38;                       // 通用智能视频侦测服务器
        public static final int   NET_IVS_F = 39;                       // 目标识别服务器
        public static final int   NET_IVS_V = 40;                       // 视频质量诊断服务器         40
        public static final int   NET_MATRIX_SERIAL = 41;               // 矩阵
        public static final int   NET_DVR_N52 = 42;                     // N52
        public static final int   NET_DVR_N56 = 43;                     // N56
        public static final int   NET_ESS_SERIAL = 44;                  // ESS
        public static final int   NET_IVS_PC = 45;                      // 人数统计服务器
        public static final int   NET_PC_NVR = 46;                      // pc-nvr
        public static final int   NET_DSCON = 47;                       // 大屏控制器
        public static final int   NET_EVS = 48;                         // 网络视频存储服务器
        public static final int   NET_EIVS = 49;                        // 嵌入式智能分析视频系统
        public static final int   NET_DVR_N6 = 50;                      // DVR-N6       50
        public static final int   NET_UDS = 51;                         // 万能解码器
        public static final int   NET_AF6016 = 52;                      // 银行报警主机
        public static final int   NET_AS5008 = 53;                      // 视频网络报警主机
        public static final int   NET_AH2008 = 54;                      // 网络报警主机
        public static final int   NET_A_SERIAL = 55;                    // 报警主机系列
        public static final int   NET_BSC_SERIAL = 56;                  // 门禁系列产品
        public static final int   NET_NVS_SERIAL = 57;                  // NVS系列产品
        public static final int   NET_VTO_SERIAL = 58;                  // VTO系列产品
        public static final int   NET_VTNC_SERIAL = 59;                 // VTNC系列产品
        public static final int   NET_TPC_SERIAL = 60;                  // TPC系列产品, 即热成像设备  60
        public static final int   NET_ASM_SERIAL = 61;                  // 无线中继设备
        public static final int   NET_VTS_SERIAL = 62;                  // 管理机
    }

    // DSP能力描述,对应CLIENT_GetDevConfig接口
    public static class NET_DSP_ENCODECAP extends SdkStructure
    {
        public int              dwVideoStandardMask;                  //视频制式掩码,按位表示设备能够支持的视频制式
        public int              dwImageSizeMask;                      //分辨率掩码,按位表示设备能够支持的分辨率设置
        public int              dwEncodeModeMask;                     //编码模式掩码,按位表示设备能够支持的编码模式设置
        public int              dwStreamCap;                          // 按位表示设备支持的多媒体功能,
        // 第一位表示支持主码流
        // 第二位表示支持辅码流1
        // 第三位表示支持辅码流2
        // 第五位表示支持jpg抓图
        public int[]            dwImageSizeMask_Assi = new int[8];    //表示主码流为各分辨率时,支持的辅码流分辨率掩码。
        public int              dwMaxEncodePower;                     //DSP支持的最高编码能力
        public short            wMaxSupportChannel;                   //每块DSP支持最多输入视频通道数
        public short            wChannelMaxSetSync;                   //DSP每通道的最大编码设置是否同步；0：不同步,1：同步
    }

    // 系统信息
    public static class NET_DEV_SYSTEM_ATTR_CFG extends SdkStructure
    {
        public int              dwSize;
        /* 下面是设备的只读部分 */
        public NET_VERSION_INFO stVersion;
        public NET_DSP_ENCODECAP stDspEncodeCap;                      //DSP能力描述
        public byte[]           szDevSerialNo = new byte[NET_DEV_SERIALNO_LEN]; //序列号
        public byte             byDevType;                            //设备类型,见枚举NET_DEVICE_TYPE
        public byte[]           szDevType = new byte[NET_DEV_TYPE_LEN]; //设备详细型号,字符串格式,可能为空
        public byte             byVideoCaptureNum;                    //视频口数量
        public byte             byAudioCaptureNum;                    //音频口数量
        public byte             byTalkInChanNum;                      //对讲输入接口数量
        public byte             byTalkOutChanNum;                     //对讲输出接口数量
        public byte             byDecodeChanNum;                      //NSP
        public byte             byAlarmInNum;                         //报警输入口数
        public byte             byAlarmOutNum;                        //报警输出口数
        public byte             byNetIONum;                           //网络口数
        public byte             byUsbIONum;                           //USB口数量
        public byte             byIdeIONum;                           //IDE数量
        public byte             byComIONum;                           //串口数量
        public byte             byLPTIONum;                           //并口数量
        public byte             byVgaIONum;                           //NSP
        public byte             byIdeControlNum;                      //NSP
        public byte             byIdeControlType;                     //NSP
        public byte             byCapability;                         //NSP,扩展描述
        public byte             byMatrixOutNum;                       //视频矩阵输出口数
        /* 下面是设备的可写部分 */
        public byte             byOverWrite;                          //硬盘满处理方式(覆盖、停止)
        public byte             byRecordLen;                          //录象打包长度
        public byte             byDSTEnable;                          //是否实行夏令时1-实行0-不实行
        public short            wDevNo;                               //设备编号,用于遥控
        public byte             byVideoStandard;                      //视频制式:0-PAL,1-NTSC
        public byte             byDateFormat;                         //日期格式
        public byte             byDateSprtr;                          //日期分割符(0：".",1："-",2："/")
        public byte             byTimeFmt;                            //时间格式(0-24小时,1－12小时)
        public byte             byLanguage;                           //枚举值详见NET_LANGUAGE_TYPE

        public NET_DEV_SYSTEM_ATTR_CFG()
        {
            this.dwSize = this.size();
        }
    }

    // 入侵方向
    public static class EM_MSG_OBJ_PERSON_DIRECTION extends SdkStructure
    {
        public static final int   EM_MSG_OBJ_PERSON_DIRECTION_UNKOWN = 0; //未知方向
        public static final int   EM_MSG_OBJ_PERSON_DIRECTION_LEFT_TO_RIGHT = EM_MSG_OBJ_PERSON_DIRECTION_UNKOWN+1; //从左向右
        public static final int   EM_MSG_OBJ_PERSON_DIRECTION_RIGHT_TO_LEFT = EM_MSG_OBJ_PERSON_DIRECTION_LEFT_TO_RIGHT+1; //从右向左
    }

    // 视频分析物体信息扩展结构体
    public static class NET_MSG_OBJECT_EX extends SdkStructure
    {
        public int              dwSize;
        public int              nObjectID;                            //物体ID,每个ID表示一个唯一的物体
        public byte[]           szObjectType = new byte[128];         //物体类型
        public int              nConfidence;                          //置信度(0~255),值越大表示置信度越高
        public int              nAction;                              //物体动作:1:Appear2:Move3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
        public DH_RECT          BoundingBox;                          //包围盒
        public NET_POINT        Center;                               //物体型心
        public int              nPolygonNum;                          //多边形顶点个数
        public NET_POINT[]      Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM); // 较精确的轮廓多边形
        public int              rgbaMainColor;                        //表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[]           szText = new byte[128];               //同NET_MSG_OBJECT相应字段
        public byte[]           szObjectSubType = new byte[64];       //物体子类别,根据不同的物体类型,可以取以下子类型：
        // 同NET_MSG_OBJECT相应字段
        public byte[]           byReserved1 = new byte[3];
        public byte             bPicEnble;                            //是否有物体对应图片文件信息, 类型为bool, 取值0或1
        public NET_PIC_INFO     stPicInfo;                            //物体对应图片信息
        public byte             bShotFrame;                           //是否是抓拍张的识别结果, 类型为bool, 取值0或1
        public byte             bColor;                               //物体颜色(rgbaMainColor)是否可用, 类型为bool, 取值0或1
        public byte             bLowerBodyColor;                      //下半身颜色(rgbaLowerBodyColor)是否可用
        public byte             byTimeType;                           //时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX      stuCurrentTime;                       //针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX      stuStartTime;                         //开始时间戳（物体开始出现时）
        public NET_TIME_EX      stuEndTime;                           //结束时间戳（物体最后出现时）
        public DH_RECT          stuOriginalBoundingBox;               //包围盒(绝对坐标)
        public DH_RECT          stuSignBoundingBox;                   //车标坐标包围盒
        public int              dwCurrentSequence;                    //当前帧序号（抓下这个物体时的帧）
        public int              dwBeginSequence;                      //开始帧序号（物体开始出现时的帧序号）
        public int              dwEndSequence;                        //结束帧序号（物体消逝时的帧序号）
        public long             nBeginFileOffset;                     //开始时文件偏移,单位:字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long             nEndFileOffset;                       //结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[]           byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
        public byte[]           byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //上半身物体颜色相似度(物体类型为人时有效)
        public byte[]           byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //下半身物体颜色相似度(物体类型为人时有效)
        public int              nRelativeID;                          //相关物体ID
        public byte[]           szSubText = new byte[20];             //"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public int              nPersonStature;                       //入侵人员身高,单位cm
        public int              emPersonDirection;                    //人员入侵方向, 取值为EM_MSG_OBJ_PERSON_DIRECTION中的值
        public int              rgbaLowerBodyColor;                   //使用方法同rgbaMainColor,物体类型为人时有效
        public int              nTextRegionID;                        //Text文本内容对应的区域编号

        public NET_MSG_OBJECT_EX()
        {
            this.dwSize = this.size();
        }

        protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
            int alignment = super.getNativeAlignment(type, value, isFirstElement);
            return Math.min(4, alignment);
        }
    }

    // 视频分析物体信息扩展结构体,扩展版本2
    public static class NET_MSG_OBJECT_EX2 extends SdkStructure
    {
        public int              dwSize;
        public int              nObjectID;                            //物体ID,每个ID表示一个唯一的物体
        public byte[]           szObjectType = new byte[128];         //物体类型
        public int              nConfidence;                          //置信度(0~255),值越大表示置信度越高
        public int              nAction;                              //物体动作:1:Appear2:Move3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
        public DH_RECT          BoundingBox;                          //包围盒
        public NET_POINT        Center;                               //物体型心
        public int              nPolygonNum;                          //多边形顶点个数
        public NET_POINT[]      Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM); //较精确的轮廓多边形
        public int              rgbaMainColor;                        //表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[]           szText = new byte[128];               //同NET_MSG_OBJECT相应字段
        public byte[]           szObjectSubType = new byte[64];       //物体子类别,根据不同的物体类型,可以取以下子类型：
        // 同NET_MSG_OBJECT相应字段
        public byte[]           byReserved1 = new byte[3];
        public byte             bPicEnble;                            //是否有物体对应图片文件信息, 类型为bool, 取值0或者1
        public NET_PIC_INFO     stPicInfo;                            //物体对应图片信息
        public byte             bShotFrame;                           //是否是抓拍张的识别结果, 类型为bool, 取值0或者1
        public byte             bColor;                               //物体颜色(rgbaMainColor)是否可用, 类型为bool, 取值0或者1
        public byte             bLowerBodyColor;                      //下半身颜色(rgbaLowerBodyColor)是否可用
        public byte             byTimeType;                           //时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX      stuCurrentTime;                       //针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX      stuStartTime;                         //开始时间戳（物体开始出现时）
        public NET_TIME_EX      stuEndTime;                           //结束时间戳（物体最后出现时）
        public DH_RECT          stuOriginalBoundingBox;               //包围盒(绝对坐标)
        public DH_RECT          stuSignBoundingBox;                   //车标坐标包围盒
        public int              dwCurrentSequence;                    //当前帧序号（抓下这个物体时的帧）
        public int              dwBeginSequence;                      //开始帧序号（物体开始出现时的帧序号）
        public int              dwEndSequence;                        //结束帧序号（物体消逝时的帧序号）
        public long             nBeginFileOffset;                     //开始时文件偏移,单位:字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long             nEndFileOffset;                       //结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[]           byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
        public byte[]           byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //上半身物体颜色相似度(物体类型为人时有效)
        public byte[]           byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX]; //下半身物体颜色相似度(物体类型为人时有效)
        public int              nRelativeID;                          //相关物体ID
        public byte[]           szSubText = new byte[20];             //"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public int              nPersonStature;                       //入侵人员身高,单位cm
        public int              emPersonDirection;                    //人员入侵方向, 取值为EM_MSG_OBJ_PERSON_DIRECTION中的值
        public int              rgbaLowerBodyColor;                   //使用方法同rgbaMainColor,物体类型为人时有效
        //视频浓缩额外信息
        public int              nSynopsisSpeed;                       //浓缩速度域值,共分1~10共十个档位,5表示浓缩后只保留5以上速度的物体。是个相对单位
        // 为0时,该字段无效
        public int              nSynopsisSize;                        //浓缩尺寸域值,共分1~10共十个档位,3表示浓缩后只保留3以上大小的物体。是个相对单位
        // 为0时,该字段无效
        public int              bEnableDirection;                     //为True时,对物体运动方向做过滤, 类型为BOOL, 取值0或者1
        // 为False时,不对物体运动方向做过滤,
        public NET_POINT        stuSynopsisStartLocation;             //浓缩运动方向,起始坐标点,点的坐标归一化到[0,8192)区间,bEnableDirection为True时有效
        public NET_POINT        stuSynopsisEndLocation;               //浓缩运动方向,终止坐标点,点的坐标归一化到[0,8192)区间,bEnableDirection为True时有效
        public byte[]           szSerialUUID = new byte[22];          //智能物体全局唯一物体标识, 有效数据位21位，包含’\0’, 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他, 中间14位YYYYMMDDhhmmss:年月日时分秒, 后5位%u%u%u%u%u：物体ID，如00001
        public byte[]           szReserved = new byte[2];             //对齐
        public int              nCategoryType;                        //物体类别的类型, 0:未知, 1:Category, 2:InductiveVehicleType
        public byte[]           szInductiveVehicleType = new byte[32]; //在Category类型基础上对车辆类型归纳后的类型
        public byte[]           szTextDirection = new byte[32];       //移动方向(比如钢包运动检测方向)LeftToRight,从左到右,RightToLeft,从右到左,TopToBottom，从上到下,BottomToTop，从下到上
        public byte[]           szQRCode = new byte[64];              //车辆上的二维码识别结果
        public NET_MSG_OBJECT_OBJECTUUID_INFO stuObjectUUID = new NET_MSG_OBJECT_OBJECTUUID_INFO(); //智能物体全局唯一物体标识,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_MSG_OBJECT_OBJECTUUID_INFO}
        public int              nTextRegionID;                        //Text文本内容对应的区域编号
        public int              nCloseDistance;                       //和相邻检测目标的距离，目前指的是工程车，单位：cm
        public int              nSpeed;                               //物体速度,单位为km/h
        public byte[]           szVehicleIDNumber = new byte[128];    //车架号
        public int              nCarTripLineDirection;                //车辆绊线方向 0:未知，1:A到B，2:B到A
        public NET_VAO_EXTRA_VEHICLE_INFO stuVehicleExtra = new NET_VAO_EXTRA_VEHICLE_INFO(); //车辆扩展信息，ObjectType为Vehicle时有效,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_VAO_EXTRA_VEHICLE_INFO}
        public byte[]           szTrafficUUID = new byte[33];         //交通违章UUID
        public byte[]           szReserved2 = new byte[3];            //字节对齐
        public double           dbLongitude;                          //经度，单位：度,正为东经，负为西经，取值范围[-180,180]
        public double           dbLatitude;                           //纬度，单位：度,正为北纬，负为南纬，取值范围[-90,90]
        public int              nElevation;                           //高程信息，单位：米
        public byte[]           byReserved = new byte[412];           //扩展字节

        public NET_MSG_OBJECT_EX2()
        {
            this.dwSize = this.size();
        }

        protected int getNativeAlignment(Class<?> type, Object value, boolean isFirstElement) {
            int alignment = super.getNativeAlignment(type, value, isFirstElement);
            return Math.min(4, alignment);
        }
    }

    // 设备协议类型
    public static class NET_DEVICE_PROTOCOL extends SdkStructure
    {
        public static final int   NET_PROTOCOL_PRIVATE2 = 0;            //私有2代协议
        public static final int   NET_PROTOCOL_PRIVATE3 = NET_PROTOCOL_PRIVATE2+1; //私有3代协议
        public static final int   NET_PROTOCOL_ONVIF = NET_PROTOCOL_PRIVATE3+1; //Onvif
        public static final int   NET_PROTOCOL_VNC = NET_PROTOCOL_ONVIF+1; //虚拟网络计算机
        public static final int   NET_PROTOCOL_TS = NET_PROTOCOL_VNC+1; //标准TS
        public static final int   NET_PROTOCOL_PRIVATE = 100;           //私有协议
        public static final int   NET_PROTOCOL_AEBELL = NET_PROTOCOL_PRIVATE+1; //美电贝尔
        public static final int   NET_PROTOCOL_PANASONIC = NET_PROTOCOL_AEBELL+1; //松下
        public static final int   NET_PROTOCOL_SONY = NET_PROTOCOL_PANASONIC+1; //索尼
        public static final int   NET_PROTOCOL_DYNACOLOR = NET_PROTOCOL_SONY+1; //Dynacolor
        public static final int   NET_PROTOCOL_TCWS = NET_PROTOCOL_DYNACOLOR+1; //天城威视
        public static final int   NET_PROTOCOL_SAMSUNG = NET_PROTOCOL_TCWS+1; //三星
        public static final int   NET_PROTOCOL_YOKO = NET_PROTOCOL_SAMSUNG+1; //YOKO
        public static final int   NET_PROTOCOL_AXIS = NET_PROTOCOL_YOKO+1; //安讯视
        public static final int   NET_PROTOCOL_SANYO = NET_PROTOCOL_AXIS+1; //三洋
        public static final int   NET_PROTOCOL_BOSH = NET_PROTOCOL_SANYO+1; //Bosch
        public static final int   NET_PROTOCOL_PECLO = NET_PROTOCOL_BOSH+1; //Peclo
        public static final int   NET_PROTOCOL_PROVIDEO = NET_PROTOCOL_PECLO+1; //Provideo
        public static final int   NET_PROTOCOL_ACTI = NET_PROTOCOL_PROVIDEO+1; //ACTi
        public static final int   NET_PROTOCOL_VIVOTEK = NET_PROTOCOL_ACTI+1; //Vivotek
        public static final int   NET_PROTOCOL_ARECONT = NET_PROTOCOL_VIVOTEK+1; //Arecont
        public static final int   NET_PROTOCOL_PRIVATEEH = NET_PROTOCOL_ARECONT+1; //PrivateEH
        public static final int   NET_PROTOCOL_IMATEK = NET_PROTOCOL_PRIVATEEH+1; //IMatek
        public static final int   NET_PROTOCOL_SHANY = NET_PROTOCOL_IMATEK+1; //Shany
        public static final int   NET_PROTOCOL_VIDEOTREC = NET_PROTOCOL_SHANY+1; //动力盈科
        public static final int   NET_PROTOCOL_URA = NET_PROTOCOL_VIDEOTREC+1; //Ura
        public static final int   NET_PROTOCOL_BITICINO = NET_PROTOCOL_URA+1; //Bticino
        public static final int   NET_PROTOCOL_ONVIF2 = NET_PROTOCOL_BITICINO+1; //Onvif协议类型,同NET_PROTOCOL_ONVIF
        public static final int   NET_PROTOCOL_SHEPHERD = NET_PROTOCOL_ONVIF2+1; //视霸
        public static final int   NET_PROTOCOL_YAAN = NET_PROTOCOL_SHEPHERD+1; //亚安
        public static final int   NET_PROTOCOL_AIRPOINT = NET_PROTOCOL_YAAN+1; //Airpop
        public static final int   NET_PROTOCOL_TYCO = NET_PROTOCOL_AIRPOINT+1; //TYCO
        public static final int   NET_PROTOCOL_XUNMEI = NET_PROTOCOL_TYCO+1; //讯美
        public static final int   NET_PROTOCOL_HIKVISION = NET_PROTOCOL_XUNMEI+1; //
        public static final int   NET_PROTOCOL_LG = NET_PROTOCOL_HIKVISION+1; //LG
        public static final int   NET_PROTOCOL_AOQIMAN = NET_PROTOCOL_LG+1; //奥奇曼
        public static final int   NET_PROTOCOL_BAOKANG = NET_PROTOCOL_AOQIMAN+1; //宝康
        public static final int   NET_PROTOCOL_WATCHNET = NET_PROTOCOL_BAOKANG+1; //Watchnet
        public static final int   NET_PROTOCOL_XVISION = NET_PROTOCOL_WATCHNET+1; //Xvision
        public static final int   NET_PROTOCOL_FUSITSU = NET_PROTOCOL_XVISION+1; //富士通
        public static final int   NET_PROTOCOL_CANON = NET_PROTOCOL_FUSITSU+1; //Canon
        public static final int   NET_PROTOCOL_GE = NET_PROTOCOL_CANON+1; //GE
        public static final int   NET_PROTOCOL_Basler = NET_PROTOCOL_GE+1; //巴斯勒
        public static final int   NET_PROTOCOL_Patro = NET_PROTOCOL_Basler+1; //帕特罗
        public static final int   NET_PROTOCOL_CPKNC = NET_PROTOCOL_Patro+1; //CPPLUSK系列
        public static final int   NET_PROTOCOL_CPRNC = NET_PROTOCOL_CPKNC+1; //CPPLUSR系列
        public static final int   NET_PROTOCOL_CPUNC = NET_PROTOCOL_CPRNC+1; //CPPLUSU系列
        public static final int   NET_PROTOCOL_CPPLUS = NET_PROTOCOL_CPUNC+1; //CPPLUSIPC
        public static final int   NET_PROTOCOL_XunmeiS = NET_PROTOCOL_CPPLUS+1; //讯美s,实际协议为Onvif
        public static final int   NET_PROTOCOL_GDDW = NET_PROTOCOL_XunmeiS+1; //广东电网
        public static final int   NET_PROTOCOL_PSIA = NET_PROTOCOL_GDDW+1; //PSIA
        public static final int   NET_PROTOCOL_GB2818 = NET_PROTOCOL_PSIA+1; //GB2818
        public static final int   NET_PROTOCOL_GDYX = NET_PROTOCOL_GB2818+1; //GDYX
        public static final int   NET_PROTOCOL_OTHER = NET_PROTOCOL_GDYX+1; //由用户自定义
    }

    // 雨刷来回循环刷,雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY)
    public static class NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //表示雨刷的索引
        public int              nInterval;                            //雨刷间隔

        public NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY()
        {
            this.dwSize = this.size();
        }
    }

    // 雨刷停止刷,雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE)
    public static class NET_CTRL_RAINBRUSH_STOPMOVE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //表示雨刷的索引

        public NET_CTRL_RAINBRUSH_STOPMOVE()
        {
            this.dwSize = this.size();
        }
    }

    // 雨刷来回刷一次，雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE)
    public static class NET_CTRL_RAINBRUSH_MOVEONCE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //表示雨刷的索引

        public NET_CTRL_RAINBRUSH_MOVEONCE()
        {
            this.dwSize = this.size();
        }
    }

    // DSP能力描述，扩展类型，对应CLIENT_QueryDevState接口
    public static class NET_DEV_DSP_ENCODECAP extends SdkStructure
    {
        public int              dwVideoStandardMask;                  //视频制式掩码，按位表示设备能够支持的视频制式
        public int              dwImageSizeMask;                      //分辨率掩码，按位表示设备能够支持的分辨率
        public int              dwEncodeModeMask;                     //编码模式掩码，按位表示设备能够支持的编码模式
        public int              dwStreamCap;                          //按位表示设备支持的多媒体功能，
        // 第一位表示支持主码流
        // 第二位表示支持辅码流1
        // 第三位表示支持辅码流2
        // 第五位表示支持jpg抓图
        public int[]            dwImageSizeMask_Assi = new int[32];   //表示主码流为各分辨率时，支持的辅码流分辨率掩码。
        public int              dwMaxEncodePower;                     //DSP支持的最高编码能力
        public short            wMaxSupportChannel;                   //每块DSP支持最多输入视频通道数
        public short            wChannelMaxSetSync;                   //DSP每通道的最大编码设置是否同步；0：不同步，1：同步
        public byte[]           bMaxFrameOfImageSize = new byte[32];  //不同分辨率下的最大采集帧率，与dwVideoStandardMask按位对应
        public byte             bEncodeCap;                           //标志，配置时要求符合下面条件，否则配置不能生效；
        // 0：主码流的编码能力+辅码流的编码能力 <= 设备的编码能力，
        // 1：主码流的编码能力+辅码流的编码能力 <= 设备的编码能力，
        // 辅码流的编码能力 <= 主码流的编码能力，
        // 辅码流的分辨率 <= 主码流的分辨率，
        // 主码流和辅码流的帧率 <= 前端视频采集帧率
        // 2：N5的计算方法
        // 辅码流的分辨率 <= 主码流的分辨率
        // 查询支持的分辨率和相应最大帧率
        public byte[]           reserved = new byte[95];
    }

    //云台控制坐标单元
    public static class PTZ_SPACE_UNIT extends SdkStructure
    {
        public int              nPositionX;                           // 云台水平运动位置,有效范围：0,3600]
        public int              nPositionY;                           // 云台垂直运动位置,有效范围：-1800,1800]
        public int              nZoom;                                // 云台光圈变动位置,有效范围：0,128]
        public byte[]           szReserve = new byte[32];             //预留32字节
    }

    //云台控制速度单元
    public static class PTZ_SPEED_UNIT extends SdkStructure
    {
        public float            fPositionX;                           //云台水平方向速率,归一化到-1~1
        public float            fPositionY;                           //云台垂直方向速率,归一化到-1~1
        public float            fZoom;                                //云台光圈放大倍率,归一化到0~1
        public byte[]           szReserve = new byte[32];             //预留32字节
    }

    //持续控制云台对应结构
    public static class PTZ_CONTROL_CONTINUOUSLY extends SdkStructure
    {
        public PTZ_SPEED_UNIT   stuSpeed;                             //云台运行速度
        public int              nTimeOut;                             //连续移动超时时间,单位为秒
        public byte[]           szReserve = new byte[64];             //预留64字节
    }

    //绝对控制云台对应结构
    public static class PTZ_CONTROL_ABSOLUTELY extends SdkStructure
    {
        public PTZ_SPACE_UNIT   stuPosition;                          //云台绝对移动位置
        public PTZ_SPEED_UNIT   stuSpeed;                             //云台运行速度
        public byte[]           szReserve = new byte[64];             //预留64字节
    }

    //带速度转动到预置位点云台控制对应结构
    public static class PTZ_CONTROL_GOTOPRESET extends SdkStructure
    {
        public int              nPresetIndex;                         //预置位索引
        public PTZ_SPEED_UNIT   stuSpeed;                             //云台运行速度
        public byte[]           szReserve = new byte[64];             //预留64字节
    }

    //设置云台可视域信息
    public static class PTZ_VIEW_RANGE_INFO extends SdkStructure
    {
        public int              nStructSize;
        public int              nAzimuthH;                            //水平方位角度,0~3600,单位:度

        public PTZ_VIEW_RANGE_INFO()
        {
            this.nStructSize = this.size();
        }
    }

    //云台绝对聚焦对应结构
    public static class PTZ_FOCUS_ABSOLUTELY extends SdkStructure
    {
        public int              dwValue;                              //云台聚焦位置,取值范围(0~8191)
        public int              dwSpeed;                              //云台聚焦速度,取值范围(0~7)
        public byte[]           szReserve = new byte[64];             //预留64字节
    }

}
