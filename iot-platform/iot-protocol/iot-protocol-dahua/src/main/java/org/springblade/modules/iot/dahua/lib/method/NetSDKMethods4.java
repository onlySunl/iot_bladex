package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 4 部分
 */
public interface NetSDKMethods4 {
    }

    public static class OSD_CUSTOM_GENERAL_INFO extends SdkStructure
    {
        public int              bEnable;                              //BOOL类型,是否叠加
    }

    public static class RADAR_INFO extends SdkStructure
    {
        public int              nAngle;                               //角度,用于修正雷达探头安装的角度造成的速度误差,范围[0,90]
        public int              nAntiJammingValue;                    //抗干扰门槛值
        public int              nComeInValue;                         //来向进入门槛值,取值范围[0,65535]
        public int              nComeOutValue;                        //来向离开门槛值
        public int              nDelayTime;                           //雷达延时,单位ms，范围[0,255]
        public int              nDetectBreaking;                      //违章类型掩码,从低位到高位依次是:
        //0-正常,1-闯红灯, 2-压线, 3-逆行,4-欠速
        //5-超速,6-有车占道,7-黄牌占道,8-闯黄灯,9-违章占公交车道
        public int              nDetectMode;                          //检测模式  0-车头检测 1-车尾检测 2-双向检测
        public int              nInstallMode;                         //雷达安装方式  0-侧面安装 1-上方安装
        public int              nLevel;                               //灵敏度,0级灵敏度最高,范围[0,5]
        public int              nMultiTargetFilter;                   //多目标过滤模式,0-正常 1-过滤
        public int              nWentEndValue;                        //去向信号结束门槛值
        public int              nWentInValue;                         //去向进入门槛值
        public int              nWentOutValue;                        //去向离开门槛值
    }

    // 串口状态
    public static class NET_COMM_STATE extends SdkStructure
    {
        public int              uBeOpened;                            // 串口是否打开,0:未打开 1:打开.
        public int              uBaudRate;                            // 波特率, 1~8分别表示 1200 2400  4800 9600 19200 38400 57600 115200
        public int              uDataBites;                           // 数据位，4~8表示4位~8位
        public int              uStopBits;                            // 停止位, 232串口 ： 数值0 代表停止位1; 数值1 代表停止位1.5; 数值2 代表停止位2.    485串口 ： 数值1 代表停止位1; 数值2 代表停止位2.
        public int              uParity;                              // 检验, 0：无校验，1：奇校验；2：偶校验;
        public byte[]           bReserved = new byte[32];
    }

    // 门禁卡记录查询条件
    public static class FIND_RECORD_ACCESSCTLCARD_CONDITION extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public int              bIsValid;                             // 是否有效, 1:有效, 0:无效 , boolean类型，为1或者0
        public int              abCardNo;                             // 卡号查询条件是否有效,针对成员 szCardNo,boolean类型，为1或者0
        public int              abUserID;                             // 用户ID查询条件是否有效,针对成员 szUserID, boolean类型，为1或者0
        public int              abIsValid;                            // IsValid查询条件是否有效,针对成员 bIsValid, boolean类型，为1或者0

        public FIND_RECORD_ACCESSCTLCARD_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 门禁卡记录集信息
    public static class NET_RECORDSET_ACCESS_CTL_CARD extends SdkStructure
    {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public NET_TIME         stuCreateTime = new NET_TIME();       // 创建时间
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID, 设备暂不支持
        public int              emStatus;                             // 卡状态   NET_ACCESSCTLCARD_STATE
        public int              emType;                               // 卡类型   NET_ACCESSCTLCARD_TYPE
        public byte[]           szPsw = new byte[NET_MAX_CARDPWD_LEN]; // 卡密码
        public int              nDoorNum;                             // 有效的门数目;
        public int[]            sznDoors = new int[NET_MAX_DOOR_NUM]; // 有权限的门序号,即CFG_CMD_ACCESS_EVENT配置的数组下标
        public int              nTimeSectionNum;                      // 有效的的开门时间段数目
        public int[]            sznTimeSectionNo = new int[NET_MAX_TIMESECTION_NUM]; // 开门时间段索引,即CFG_ACCESS_TIMESCHEDULE_INFO的数组下标
        public int              nUserTime;                            // 使用次数,仅当来宾卡时有效
        public NET_TIME         stuValidStartTime = new NET_TIME();   // 开始有效期, 设备暂不支持时分秒
        public NET_TIME         stuValidEndTime = new NET_TIME();     // 结束有效期, 设备暂不支持时分秒
        public int              bIsValid;                             // 是否有效,1有效; 0无效, boolean类型，为1或者0
        public NET_ACCESSCTLCARD_FINGERPRINT_PACKET stuFingerPrintInfo; // 下发信息数据信息，仅为兼容性保留，请使用 stuFingerPrintInfoEx, 如果使用，内部的 pPacketData，请初始化
        public int              bFirstEnter;                          // 是否拥有首卡权限, boolean类型，为1或者0
        public byte[]           szCardName = new byte[NET_MAX_CARDNAME_LEN]; // 卡命名
        public byte[]           szVTOPosition = new byte[NET_COMMON_STRING_64]; // 门口机关联位置
        public int              bHandicap;                            // 是否为残障人士卡, boolean类型，为1或者0
        public int              bEnableExtended;                      // 启用成员 stuFingerPrintInfoEx, boolean类型，为1或者0
        public NET_ACCESSCTLCARD_FINGERPRINT_PACKET_EX stuFingerPrintInfoEx; // 信息数据信息, 如果使用，内部的 pPacketData，请初始化
        public int              nFaceDataNum;                         // 人脸数据个数不超过20
        public NET_FACE_FACEDATA[] szFaceDataArr = (NET_FACE_FACEDATA[])new NET_FACE_FACEDATA().toArray(MAX_FACE_COUTN); // 人脸模板数据
        public byte[]           szDynamicCheckCode = new byte[MAX_COMMON_STRING_16]; // 动态校验码。
        // VTO等设备会保存此校验码，以后每次刷卡都以一定的算法生成新校验码并写入IC卡中，同时更新VTO设备的校验码，只有卡号和此校验码同时验证通过时才可开门。
        public int              nRepeatEnterRouteNum;                 // 反潜路径个数
        public int[]            arRepeatEnterRoute = new int[MAX_REPEATENTERROUTE_NUM]; // 反潜路径
        public int              nRepeatEnterRouteTimeout;             // 反潜超时时间
        public int              bNewDoor;                             // 是否启动新开门授权字段，TRUE表示使用nNewDoorNum和nNewDoors字段下发开门权限, BOOL类型
        public int              nNewDoorNum;                          // 有效的门数目;
        public int[]            nNewDoors = new int[MAX_ACCESSDOOR_NUM]; // 有权限的门序号,即CFG_CMD_ACCESS_EVENT配置的数组下标
        public int              nNewTimeSectionNum;                   // 有效的的开门时间段数目
        public int[]            nNewTimeSectionNo = new int[MAX_ACCESSDOOR_NUM]; // 开门时间段索引,即CFG_ACCESS_TIMESCHEDULE_INFO的数组下标
        public byte[]           szCitizenIDNo = new byte[MAX_COMMON_STRING_32]; // 证件号码
        public int              nSpecialDaysScheduleNum;              // 假日计划表示数量
        public int[]            arSpecialDaysSchedule = new int[MAX_ACCESSDOOR_NUM]; // 假日计划标识// 缺点：目前方案只支持一卡刷一个设备。
        public int              nUserType;                            // 用户类型, 0 普通用户, 1 禁止名单用户
        public int              nFloorNum;                            // 有效的楼层数量
        public FLOOR_NO[]       szFloorNoArr = (FLOOR_NO[])new FLOOR_NO().toArray(MAX_ACCESS_FLOOR_NUM); // 楼层号
        public byte[]           szSection = new byte[MAX_COMMON_STRING_64]; //部门名称
        public int              nScore;                               //信用积分
        public byte[]           szCompanyName = new byte[MAX_COMPANY_NAME_LEN]; //单位名称
        public int              nSectionID;                           //部门ID
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
        public int              emAuthority;                          // 用户权限，详见NET_ACCESSCTLCARD_AUTHORITY
        public byte[]           szCompanionName = new byte[120];      // 陪同人姓名
        public byte[]           szCompanionCompany = new byte[200];   // 陪同人单位
        public NET_TIME         stuTmpAuthBeginTime;                  // 临时授权开始时间,当该时间和其他时间同时生效时，以此时间为最高优先级
        public NET_TIME         stuTmpAuthEndTime;                    // 临时授权结束时间,当该时间和其他时间同时生效时，以此时间为最高优先级
        public int              bFloorNoExValid;                      // 楼层号扩展 szFloorNoEx 是否有效
        public int              nFloorNumEx;                          // 有效的楼层数量扩展
        public byte[]           szFloorNoEx = new byte[512*NET_COMMON_STRING_4]; // 楼层号扩展
        public byte[]           szSubUserID = new byte[32];           // 用户ID
        public byte[]           szPhoneNumber = new byte[32];         // 人员电话号码
        public byte[]           szPhotoPath = new byte[256];          // 人员照片对应在ftp上的路径
        public byte[]           szCause = new byte[64];               // 来访原因
        public byte[]           szCompanionCard = new byte[32];       // 陪同人员证件号
        public byte[]           szCitizenAddress = new byte[128];     // 证件地址
        public NET_TIME         stuBirthDay;                          // 出生日期（年月日有效）
        public int              bFloorNoEx2Valid;                     // stuFloors2 是否有效
        /**
         * 对应结构体{@link NET_FLOORS_INFO}
         */
        public Pointer          pstuFloorsEx2;                        // 楼层号（再次扩展）
        public byte[]           szDefaultFloor = new byte[8];         // 默认楼层号（梯控需求)
        public int              nUserTimeSectionNum;                  // 用户时间段有效个数
        public USER_TIME_SECTION[] szUserTimeSections = (USER_TIME_SECTION[])new USER_TIME_SECTION().toArray(6); //针对用户自身的开门时间段校验，最多支持6个时间段
        public byte[]           szWorkClass = new byte[256];          // 工作班别
        public NET_TIME         stuStartTimeInPeriodOfValidity = new NET_TIME(); //有效时间段内启动时间,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME}
        public int              emTestItems;                          //测试项目,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_TEST_ITEMS}
        public int              nAuthOverdueTime;                     //授权时间、过期时间，时间单位: 小时
        public int              emGreenCNHealthStatus;                //人员健康状态,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_GREENCNHEALTH_STATUS}
        public int              emAllowPermitFlag;                    //电子通行证状态,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_ALLOW_PERMIT_FLAG}
        public int              emRentState;                          //对接第三方平台数据,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_RENT_STATE}
        public int              nConsumptionTimeSectionsNum;          //用户消费时间段
        public BYTE_ARRAY_34[]  szConsumptionTimeSections = new BYTE_ARRAY_34[42]; //消费时间段.每天最多6个时间段，每6个元素对应一天。一共7天;, 每个时段格式为"星期 时:分:秒-时:分:秒 消费类型 可消费次数 可消费金额"
        public NET_ACCESS_CONTROL_CARD_MULTI_TIMESECTION_INFO[] stuMultiTimeSections = new NET_ACCESS_CONTROL_CARD_MULTI_TIMESECTION_INFO[128]; //一人对单个门支持多时段和假日计划方案需求,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_ACCESS_CONTROL_CARD_MULTI_TIMESECTION_INFO}
        public int              nMultiTimeSectionsNum;                //一人对单个门支持多时段和假日计划方案需求个数
        public byte[]           szResvered1 = new byte[4];            //字节对齐

        public NET_RECORDSET_ACCESS_CTL_CARD() {
            this.dwSize = this.size();
        }
    }

    // 用户权限
    public static class NET_ACCESSCTLCARD_AUTHORITY extends SdkStructure
    {
        public static final int   NET_ACCESSCTLCARD_AUTHORITY_UNKNOWN = 0; // 未知
        public static final int   NET_ACCESSCTLCARD_AUTHORITY_ADMINISTRATORS = 1; // 管理员
        public static final int   NET_ACCESSCTLCARD_AUTHORITY_CUSTOMER = 2; // 普通用户
    }

    public static class FLOOR_NO extends SdkStructure
    {
        public byte[]           szFloorNo = new byte[NET_COMMON_STRING_4]; // 楼层号
    }

    // 卡状态
    public static class NET_ACCESSCTLCARD_STATE extends SdkStructure
    {
        public static final int   NET_ACCESSCTLCARD_STATE_UNKNOWN = -1;
        public static final int   NET_ACCESSCTLCARD_STATE_NORMAL = 0;   // 正常
        public static final int   NET_ACCESSCTLCARD_STATE_LOSE = 0x01;  // 挂失
        public static final int   NET_ACCESSCTLCARD_STATE_LOGOFF = 0x02; // 注销
        public static final int   NET_ACCESSCTLCARD_STATE_FREEZE = 0x04; // 冻结
        public static final int   NET_ACCESSCTLCARD_STATE_ARREARAGE = 0x08; // 欠费
        public static final int   NET_ACCESSCTLCARD_STATE_OVERDUE = 0x10; // 逾期
        public static final int   NET_ACCESSCTLCARD_STATE_PREARREARAGE = 0x20; // 预欠费(还是可以开门,但有语音提示)
    }

    // 信息数据，只用于下发信息
    public static class NET_ACCESSCTLCARD_FINGERPRINT_PACKET extends SdkStructure
    {
        public int              dwSize;
        public int              nLength;                              // 单个数据包长度,单位字节
        public int              nCount;                               // 包个数
        public Pointer          pPacketData;                          // 所有信息数据包，用户申请内存并填充，长度为 nLength*nCount

        public NET_ACCESSCTLCARD_FINGERPRINT_PACKET() {
            this.dwSize = this.size();
        }
    }

    // 信息数据扩展，可用于下发和获取信息
    public static class NET_ACCESSCTLCARD_FINGERPRINT_PACKET_EX extends SdkStructure
    {
        public int              nLength;                              // 单个数据包长度,单位字节
        public int              nCount;                               // 包个数
        public Pointer          pPacketData;                          // 所有信息数据包, 用户申请内存,大小至少为nLength * nCount
        public int              nPacketLen;                           // pPacketData 指向内存区的大小，用户填写
        public int              nRealPacketLen;                       // 返回给用户实际信息总大小
        public int              nDuressIndex;                         // 胁迫信息序号，范围1~nCount
        public byte[]           byReverseed = new byte[1020];         //保留大小
    }

    // 查询记录能力集能力集
    public static class CFG_CAP_RECORDFINDER_INFO extends SdkStructure
    {
        public int              nMaxPageSize;                         //最大分页条数
        public int              bSupportRealUTC;                      // 查询表是否支持按真实UTC时间作为条件查询
    }

    // 时间同步服务器配置
    public static class CFG_NTP_INFO extends SdkStructure
    {
        public int              bEnable;                              // 使能开关,BOOL类型
        public byte[]           szAddress = new byte[MAX_ADDRESS_LEN]; // IP地址或网络名
        public int              nPort;                                // 端口号
        public int              nUpdatePeriod;                        // 更新周期，单位为分钟
        public int              emTimeZoneType;                       // 时区, 参考 EM_CFG_TIME_ZONE_TYPE
        public byte[]           szTimeZoneDesc = new byte[MAX_NAME_LEN]; // 时区描述
        public int              nSandbyServerNum;                     // 实际备用NTP服务器个数
        public CFG_NTP_SERVER[] stuStandbyServer = (CFG_NTP_SERVER[])new CFG_NTP_SERVER().toArray(MAX_NTP_SERVER); // 备选NTP服务器地址
        public int              nTolerance;                           // (机器人使用)表示设置的时间和当前时间的容差，单位为秒，如果设置的时间和当前的时间在容差范围内，则不更新当前时间。0 表示每次都修改。
    }

    // NTP服务器
    public static class CFG_NTP_SERVER extends SdkStructure
    {
        public int              bEnable;                              // BOOL类型
        public byte[]           szAddress = new byte[MAX_ADDRESS_LEN]; // IP地址或网络名
        public int              nPort;                                // 端口号
    }

    // 时区定义(NTP)
    public static class EM_CFG_TIME_ZONE_TYPE extends SdkStructure
    {
        public static final int   EM_CFG_TIME_ZONE_0 = 0;               // {0, 0*3600,"GMT+00:00"}
        public static final int   EM_CFG_TIME_ZONE_1 = 1;               // {1, 1*3600,"GMT+01:00"}
        public static final int   EM_CFG_TIME_ZONE_2 = 2;               // {2, 2*3600,"GMT+02:00"}
        public static final int   EM_CFG_TIME_ZONE_3 = 3;               // {3, 3*3600,"GMT+03:00"}
        public static final int   EM_CFG_TIME_ZONE_4 = 4;               // {4, 3*3600+1800,"GMT+03:30"}
        public static final int   EM_CFG_TIME_ZONE_5 = 5;               // {5, 4*3600,"GMT+04:00"}
        public static final int   EM_CFG_TIME_ZONE_6 = 6;               // {6, 4*3600+1800,"GMT+04:30"}
        public static final int   EM_CFG_TIME_ZONE_7 = 7;               // {7, 5*3600,"GMT+05:00"}
        public static final int   EM_CFG_TIME_ZONE_8 = 8;               // {8, 5*3600+1800,"GMT+05:30"}
        public static final int   EM_CFG_TIME_ZONE_9 = 9;               // {9, 5*3600+1800+900,"GMT+05:45"}
        public static final int   EM_CFG_TIME_ZONE_10 = 10;             // {10, 6*3600,"GMT+06:00"}
        public static final int   EM_CFG_TIME_ZONE_11 = 11;             // {11, 6*3600+1800,"GMT+06:30"}
        public static final int   EM_CFG_TIME_ZONE_12 = 12;             // {12, 7*3600,"GMT+07:00"}
        public static final int   EM_CFG_TIME_ZONE_13 = 13;             // {13, 8*3600,"GMT+08:00"}
        public static final int   EM_CFG_TIME_ZONE_14 = 14;             // {14, 9*3600,"GMT+09:00"}
        public static final int   EM_CFG_TIME_ZONE_15 = 15;             // {15, 9*3600+1800,"GMT+09:30"}
        public static final int   EM_CFG_TIME_ZONE_16 = 16;             // {16, 10*3600,"GMT+10:00"}
        public static final int   EM_CFG_TIME_ZONE_17 = 17;             // {17, 11*3600,"GMT+11:00"}
        public static final int   EM_CFG_TIME_ZONE_18 = 18;             // {18, 12*3600,"GMT+12:00"}
        public static final int   EM_CFG_TIME_ZONE_19 = 19;             // {19, 13*3600,"GMT+13:00"}
        public static final int   EM_CFG_TIME_ZONE_20 = 20;             // {20, -1*3600,"GMT-01:00"}
        public static final int   EM_CFG_TIME_ZONE_21 = 21;             // {21, -2*3600,"GMT-02:00"}
        public static final int   EM_CFG_TIME_ZONE_22 = 22;             // {22, -3*3600,"GMT-03:00"}
        public static final int   EM_CFG_TIME_ZONE_23 = 23;             // {23, -3*3600-1800,"GMT-03:30"}
        public static final int   EM_CFG_TIME_ZONE_24 = 24;             // {24, -4*3600,"GMT-04:00"}
        public static final int   EM_CFG_TIME_ZONE_25 = 25;             // {25, -5*3600,"GMT-05:00"}
        public static final int   EM_CFG_TIME_ZONE_26 = 26;             // {26, -6*3600,"GMT-06:00"}
        public static final int   EM_CFG_TIME_ZONE_27 = 27;             // {27, -7*3600,"GMT-07:00"}
        public static final int   EM_CFG_TIME_ZONE_28 = 28;             // {28, -8*3600,"GMT-08:00"}
        public static final int   EM_CFG_TIME_ZONE_29 = 29;             // {29, -9*3600,"GMT-09:00"}
        public static final int   EM_CFG_TIME_ZONE_30 = 30;             // {30, -10*3600,"GMT-10:00"}
        public static final int   EM_CFG_TIME_ZONE_31 = 31;             // {31, -11*3600,"GMT-11:00"}
        public static final int   EM_CFG_TIME_ZONE_32 = 32;             // {32, -12*3600,"GMT-12:00"}
    }

    // 事件信息
    public static class EVENT_INFO extends SdkStructure
    {
        public int              nEvent;                               // 事件类型,参见智能事件类型，如 EVENT_IVS_ALL
        public int	[]           arrayObejctType = new int[16];        // 支持的物体类型，当前支持 EM_OBJECT_TYPE_HUMAN, EM_OBJECT_TYPE_VECHILE, EM_OBJECT_TYPE_NOMOTOR, EM_OBJECT_TYPE_ALL
        public int              nObjectCount;                         // szObejctType 数量
        public byte[]           byReserved = new byte[512];           // 预留字段
    }

    // 录像信息对应 CLIENT_FindFileEx 接口的 NET_FILE_QUERY_FILE 命令 查询条件
    // 目前支持通过路径查询
    public static class NET_IN_MEDIA_QUERY_FILE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public String           szDirs;                               // 工作目录列表,一次可查询多个目录,为空表示查询所有目录。目录之间以分号分隔,如“/mnt/dvr/sda0;/mnt/dvr/sda1”,szDirs==null 或"" 表示查询所有
        public int              nMediaType;                           // 文件类型,0:查询任意类型,1:查询jpg图片,2:查询dav
        public int              nChannelID;                           // 通道号从0开始,-1表示查询所有通道
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int[]            nEventLists = new int[MAX_IVS_EVENT_NUM]; // 事件类型列表,参见智能分析事件类型
        public int              nEventCount;                          // 事件总数
        public byte             byVideoStream;                        // 视频码流 0-未知 1-主码流 2-辅码流1 3-辅码流2 4-辅码流3
        public byte[]           bReserved = new byte[3];              // 字节对齐
        public int[]            emFalgLists = new int[EM_RECORD_SNAP_FLAG_TYPE.FLAG_TYPE_MAX]; // 录像或抓图文件标志, 不设置标志表示查询所有文件, 参考 EM_RECORD_SNAP_FLAG_TYPE
        public int              nFalgCount;                           // 标志总数
        public NET_RECORD_CARD_INFO stuCardInfo;                      // 卡号录像信息, emFalgLists包含卡号录像时有效
        public int              nUserCount;                           // 用户名有效个数
        public byte[]           szUserName = new byte[MAX_QUERY_USER_NUM * NET_NEW_USER_NAME_LENGTH]; // 用户名
        public int              emResultOrder;                        // 查询结果排序方式, 参考 EM_RESULT_ORDER_TYPE
        public int              bTime;                                // 是否按时间查询
        public int              emCombination;                        // 查询结果是否合并录像文件
        public EVENT_INFO[]     stuEventInfo = (EVENT_INFO[])new EVENT_INFO().toArray(16); // 事件信息，当查询为 DH_FILE_QUERY_FILE_EX 类型时有效
        public int              nEventInfoCount;                      // stuEventInfo 个数
        public int              bOnlySupportRealUTC;                  // 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stuStartTime, stuEndTime), 为FALSE表示仅下发stuStartTime, stuEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥

        public NET_IN_MEDIA_QUERY_FILE() {
            this.dwSize = this.size();
        }
    }

    // 录像信息对应 CLIENT_FindFileEx 接口的 NET_FILE_QUERY_FILE 命令 查询结果
    public static class NET_OUT_MEDIA_QUERY_FILE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道号从0开始,-1表示查询所有通道
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nFileSize;                            // 文件长度
        public byte             byFileType;                           // 文件类型 1:jpg图片, 2: dav
        public byte             byDriveNo;                            // 该字段已废弃,后续开发使用 nDriveNo成员
        public byte             byPartition;                          // 分区号
        public byte             byVideoStream;                        // 视频码流 0-未知 1-主码流 2-辅码流1 3-辅码流 4-辅码流
        public int              nCluster;                             // 簇号
        public byte[]           szFilePath = new byte[MAX_PATH];      // 文件路径
        public int[]            nEventLists = new int[MAX_IVS_EVENT_NUM]; // 关联的事件列表,事件类型列表,参见智能分析事件类型
        public int              nEventCount;                          //事件总数
        public int[]            emFalgLists = new int[EM_RECORD_SNAP_FLAG_TYPE.FLAG_TYPE_MAX]; // 录像或抓图文件标志, 参考  EM_RECORD_SNAP_FLAG_TYPE
        public int              nFalgCount;                           //标志总数
        public int              nDriveNo;                             // 磁盘号
        //频浓缩文件相关信息
        public byte[]           szSynopsisPicPath = new byte[NET_COMMON_STRING_512]; // 预处理文件提取到的快照	文件路径
        // 支持HTTP URL表示:"http://www.te.com/1.jpg"
        // 支持FTP URL表示: "ftp://ftp.te.com/1.jpg"
        // 支持服务器本地路径
        // a)"C:/pic/1.jpg"
        // b)"/mnt//2010/8/11/dav/15:40:50.jpg"
        public int              nSynopsisMaxTime;                     // 支持浓缩视频最大时间长度,单位 秒
        public int              nSynopsisMinTime;                     // 支持浓缩视频最小时间长度,单位 秒
        //文件摘要信息
        public int              nFileSummaryNum;                      // 文件摘要信息数
        public NET_FILE_SUMMARY_INFO[] stFileSummaryInfo = (NET_FILE_SUMMARY_INFO[])new NET_FILE_SUMMARY_INFO().toArray(MAX_FILE_SUMMARY_NUM); // 文件摘要信息
        public long             nFileSizeEx;                          // 文件长度扩展,支持文件长度大于4G，单位字节
        public int              nTotalFrame;                          // 查询录像段内所有帧总和，不区分帧类型
        public int              emFileState;                          // 录像文件的状态,EM_VIDEO_FILE_STATE
        public byte[]           szWorkDir = new byte[256];            // 录像文件的存储目录
        public byte[]           szThumbnail = new byte[260];          // 缩略图路径，可根据该路径下载缩略图
        public int              bRealUTC;                             // 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅stuStartTime和stuEndTime有效(仅使用stuStartTime和stuEndTime)
        public NET_TIME         stuStartTimeRealUTC;                  // UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用
        public NET_TIME         stuEndTimeRealUTC;                    // UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用
        public int              nVideoKeyEncryptionKeyIdsNum;         //VKEKID数组数量
        public BYTE_ARRAY_64[]  szVideoKeyEncryptionKeyIds = new BYTE_ARRAY_64[8]; //VKEKID数组

        public NET_OUT_MEDIA_QUERY_FILE() {
            this.dwSize = this.size();
        }
    }

    // 卡号录像信息
    public static class NET_RECORD_CARD_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nType;                                // 类型, 0-Card, 1-Field
        public byte[]           szCardNo = new byte[NET_MAX_CARD_INFO_LEN]; // 卡号
        public int              emTradeType;                          // 交易类型, 参考 EM_ATM_TRADE_TYPE
        public byte[]           szAmount = new byte[NET_COMMON_STRING_64]; // 交易金额, 空字符串表示不限金额
        public int              nError;                               // 错误码, 0-所有错误, 1-吞钞, 2-吞卡
        public int              nFieldCount;                          // 域数量, 按域查询时有效
        public byte[]           szFields = new byte[MAX_CARD_RECORD_FIELD_NUM * NET_COMMON_STRING_256]; // 域信息, 按域查询时有效
        public byte[]           szChange = new byte[NET_COMMON_STRING_32]; // 零钱

        public NET_RECORD_CARD_INFO() {
            this.dwSize = this.size();
        }
    }

    // 文件摘要信息
    public static class NET_FILE_SUMMARY_INFO extends SdkStructure
    {
        public byte[]           szKey = new byte[NET_COMMON_STRING_64]; // 摘要名称
        public byte[]           szValue = new byte[NET_COMMON_STRING_512]; // 摘要内容
        public byte[]           bReserved = new byte[256];            // 保留字段
    }

    // 录像或抓图文件标志
    public static class EM_RECORD_SNAP_FLAG_TYPE extends SdkStructure
    {
        public static final int   FLAG_TYPE_TIMING = 0;                 //定时文件
        public static final int   FLAG_TYPE_MANUAL = 1;                 //手动文件
        public static final int   FLAG_TYPE_MARKED = 2;                 //重要文件
        public static final int   FLAG_TYPE_EVENT = 3;                  //事件文件
        public static final int   FLAG_TYPE_MOSAIC = 4;                 //合成图片
        public static final int   FLAG_TYPE_CUTOUT = 5;                 //抠图图片
        public static final int   FLAG_TYPE_LEAVE_WORD = 6;             //留言文件
        public static final int   FLAG_TYPE_TALKBACK_LOCAL_SIDE = 7;    //对讲本地方文件
        public static final int   FLAG_TYPE_TALKBACK_REMOTE_SIDE = 8;   //对讲远程方文件
        public static final int   FLAG_TYPE_SYNOPSIS_VIDEO = 9;         //浓缩视频
        public static final int   FLAG_TYPE_ORIGINAL_VIDEO = 10;        //原始视频
        public static final int   FLAG_TYPE_PRE_ORIGINAL_VIDEO = 11;    //已经预处理的原始视频
        public static final int   FLAG_TYPE_BLACK_PLATE = 12;           //禁止名单图片
        public static final int   FLAG_TYPE_ORIGINAL_PIC = 13;          //原始图片
        public static final int   FLAG_TYPE_CARD = 14;                  //卡号录像
        public static final int   FLAG_TYPE_MAX = 128;
    }

    // 交易类型
    public static class EM_ATM_TRADE_TYPE extends SdkStructure
    {
        public static final int   ATM_TRADE_ALL = 0;                    // 所有类型
        public static final int   ATM_TRADE_ENQUIRY = 1;                // 查询
        public static final int   ATM_TRADE_WITHDRAW = 2;               // 取款
        public static final int   ATM_TRADE_MODIFY_PASSWORD = 3;        // 修改密码
        public static final int   ATM_TRADE_TRANSFER = 4;               // 转账
        public static final int   ATM_TRADE_DEPOSIT = 5;                // 存款
        public static final int   ATM_TRADE_CARDLESS_ENQUIRY = 6;       // 无卡查询
        public static final int   ATM_TRADE_CARDLESS_DEPOSIT = 7;       // 无卡存款
        public static final int   ATM_TRADE_OTHER = 8;                  // 其他
    }

    // 查询结果排序方式
    public static class EM_RESULT_ORDER_TYPE extends SdkStructure
    {
        public static final int   EM_RESULT_ORDER_UNKNOWN = 0;          // 未知
        public static final int   EM_RESULT_ORDER_ASCENT_BYTIME = 1;    // 按时间升序排序
        public static final int   EM_RESULT_ORDER_DESCENT_BYTIME = 2;   // 按时间降序排序
    }

    // CLIENT_ControlDevice 接口的 CTRLTYPE_CTRL_START_VIDEO_ANALYSE 命令参数, 开始视频智能分析
    public static class NET_CTRL_START_VIDEO_ANALYSE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelId;                           // 通道号

        public NET_CTRL_START_VIDEO_ANALYSE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ControlDevice 接口的 CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE 命令参数, 停止视频智能分析
    public static class NET_CTRL_STOP_VIDEO_ANALYSE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelId;                           // 通道号

        public NET_CTRL_STOP_VIDEO_ANALYSE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachVideoAnalyseState 接口输入参数
    public static class NET_IN_ATTACH_VIDEOANALYSE_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              nChannleId;                           // 通道号
        public Callback         cbVideoAnalyseState;                  // 视频分析状态回调函数,fVideoAnalyseState 回调
        public Pointer          dwUser;                               // 用户信息

        public NET_IN_ATTACH_VIDEOANALYSE_STATE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachVideoAnalyseState 接口输出参数
    public static class NET_OUT_ATTACH_VIDEOANALYSE_STATE extends SdkStructure
    {
        public int              dwSize;
        public LLong            lAttachHandle;                        // 分析进度句柄,唯一标识某一通道的分析进度

        public NET_OUT_ATTACH_VIDEOANALYSE_STATE() {
            this.dwSize = this.size();
        }
    }

    public static class NET_VIDEOANALYSE_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              dwProgress;                           // 分析进度,0-100
        public byte[]           szState = new byte[NET_COMMON_STRING_64]; // 通道状态,Running"：运行,"Stop"：停止,"NoStart"：未启动,"Failed"：失败,"Successed"：成功
        public byte[]           szFailedCode = new byte[NET_COMMON_STRING_64]; // 错误码

        public NET_VIDEOANALYSE_STATE() {
            this.dwSize = this.size();
        }
    }

    // 热成像火情事件信息上报事件, 对应事件  NET_ALARM_FIREWARNING_INFO // 热成像火情事件信息上报事件 #define DH_ALARM_FIREWARNING_INFO 0x31da // 热成像火情事件信息上报(对应结构体 ALARM_FIREWARNING_INFO_DETAIL)
    public static class ALARM_FIREWARNING_INFO_DETAIL extends SdkStructure
    {
        public int              nChannel;                             // 对应视频通道号
        public int              nWarningInfoCount;                    // 报警信息个数
        public NET_FIREWARNING_INFO[] stuFireWarningInfo = new NET_FIREWARNING_INFO[MAX_FIREWARNING_INFO_NUM]; // 具体报警信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           reserved = new byte[256];

        public ALARM_FIREWARNING_INFO_DETAIL() {
            for(int i = 0; i < stuFireWarningInfo.length; i++) {
                stuFireWarningInfo[i] = new NET_FIREWARNING_INFO();
            }
        }
    }

    //热成像火情事件信息
    public static class NET_FIREWARNING_INFO extends SdkStructure
    {
        public int              nPresetId;                            // 预置点编号	从测温规则配置 CFG_RADIOMETRY_RULE_INFO 中选择
        public NET_RECT         stuBoundingBox;                       // 着火点矩形框
        public int              nTemperatureUnit;                     // 温度单位(当前配置的温度单位),见 NET_TEMPERATURE_UNIT
        public float            fTemperature;                         // 最高点温度值	同帧检测和差分检测提供
        public int              nDistance;                            // 着火点距离,单位米 0表示无效
        public GPS_POINT        stuGpsPoint;                          // 着火点经纬度
        public PTZ_POSITION_UNIT stuPTZPosition;                      // 云台运行信息
        public float            fAltitude;                            // 高度(单位：米)
        public int              nThermoHFOV;                          // Uint32 热成像横向视角
        public int              nThermoVFOV;                          // Uint32 热成像纵向视角
        public int              nFSID;                                // 火情编号ID
        public NET_FIRING_GPS_INFO stuFiringGPS;                      // 着火点的GPS坐标
        public byte[]           reserved = new byte[148];
    }

    // 着火点经纬度
    public static class GPS_POINT extends SdkStructure
    {
        public int              dwLongitude;                          // 经度(单位是百万分之度,范围0-360度)如东经120.178274度表示为300178274
        public int              dwLatidude;                           // 纬度(单位是百万分之度,范围0-180度)如北纬30.183382度表示为120183382
        // 经纬度的具体转换方式可以参考结构体  NET_WIFI_GPS_INFO 中的注释
    }

    //云台控制坐标单元
    public static class PTZ_POSITION_UNIT extends SdkStructure
    {
        public int              nPositionX;                           // 云台水平方向角度,归一化到-1~1
        public int              nPositionY;                           // 云台垂直方向角度,归一化到-1~1
        public int              nZoom;                                // 云台光圈放大倍率,归一化到 0~1
        public byte[]           szReserve = new byte[32];             // 预留32字节
    }

    // 搜索到的地点信息
    public static class NET_WIFI_GPS_INFO extends SdkStructure
    {
        public int              emPositioningResult;                  // 定位结果, 参考   NET_GPS_POSITION_RESULT
        public int              nLongitude;                           // 经度(单位是百万分之一度)
        // 西经：0 - 180000000				实际值应为: 180*1000000 – dwLongitude
        // 东经：180000000 - 360000000		实际值应为: dwLongitude – 180*1000000
        // 如: 300168866应为（300168866 - 180*1000000）/1000000 即东经120.168866度
        public int              nLatidude;                            // 纬度(单位是百万分之一度)
        // 南纬：0 - 90000000				实际值应为: 90*1000000 – dwLatidude
        // 北纬：90000000 – 180000000		实际值应为: dwLatidude – 90*1000000
        // 如: 120186268应为 (120186268 - 90*1000000)/1000000 即北纬30. 186268度
        public int              nSpeed;                               // 速度, 单位千分之一km/H
        public byte[]           reserved = new byte[112];             // 保留字段
    }

    // 定位结果
    public static class NET_GPS_POSITION_RESULT extends SdkStructure
    {
        public static final int   NET_GPS_POSITION_RESULT_UNKNOWN = 0;  // 未知
        public static final int   NET_GPS_POSITION_RESULT_FAILED = 1;   // 有GPS数据,但定位失败,此时定位数据无意义
        public static final int   NET_GPS_POSITION_RESULT_SUCCEED = 2;  // 有GPS数据,且定位成功,此时定位数据有意义
    }

    // 热成像增益模式
    public static class CFG_THERMO_GAIN_MODE extends SdkStructure
    {
        public static final int   CFG_THERMO_GAIN_MODE_UNKNOWN = 0;
        public static final int   CFG_THERMO_GAIN_MODE_HIGHTEMP = 1;    // 温度异常
        public static final int   CFG_THERMO_GAIN_MODE_LOWTEMP = 2;     // 温度异常
        public static final int   CFG_THERMO_GAIN_MODE_AUTO = 3;        // 自动
    }

    // 热成像自动增益设置
    public static class CFG_THERMO_AUTO_GAIN extends SdkStructure
    {
        public int              nLowToHigh;                           // 温度超过此设定值时，自动切换到高的温度模式
        public int              nLHROI;                               // 由低的温度切换到高的温度时的ROI 百分比0~100
        public int              nHighToLow;                           // 温度下降到此设定值时，自动切换到低的温度模式
        public int              nHLROI;                               // 由高的温度切换到低的温度时的ROI 百分比0~100
    }

    // 热成像配置，单个模式的配置
    public static class CFG_THERMOGRAPHY_OPTION extends SdkStructure
    {
        public int              nEZoom;                               // 倍数
        public int              nThermographyGamma;                   // 伽马值
        public int              nColorization;                        // 伪彩色，见 NET_THERMO_COLORIZATION
        public int              nSmartOptimizer;                      // 智能场景优化指数 0 ~100， 具体取值范围由能力决定
        public int              bOptimizedRegion;                     // 是否开启感兴趣区域，只有感兴趣区域内的信息会被纳入统计用来做自动亮度调整（AGC）
        public int              nOptimizedROIType;                    // 感兴趣区域类型，见 NET_THERMO_ROI
        public int              nCustomRegion;                        // 自定义区域个数
        public CFG_RECT[]       stCustomRegions = (CFG_RECT[])new CFG_RECT().toArray(64); // 自定义区域，仅在 nOptimizedROIType 为 NET_THERMO_ROI_CUSTOM 时有效
        public byte[]           Reserved = new byte[256];             // 此保留字段确保此结构布局与 NET_THERMO_GRAPHY_INFO 相同
        public int              nAgc;                                 // 自动增益控制 [0-255]具体取值范围由能力决定
        public int              nAgcMaxGain;                          // 最大自动增益 [0-255]具体取值范围由能力决定
        public int              nAgcPlateau;                          // 增益均衡 具体取值范围由能力决定
        public int              nGainMode;                            // 增益模式，参见 CFG_THERMO_GAIN_MODE
        public CFG_THERMO_AUTO_GAIN stAutoGain;                       // 自动增益设置，只在增益模式为 CFG_THERMO_GAIN_MODE_AUTO 有效
        public CFG_THERMO_GAIN  stuHighTempGain;                      // 高的温度下的增益设置
        public int              nBaseBrightness;                      // 基准亮度
        public int              nStretchIntensity;                    // 拉伸强度
        public CFG_RECT         stuContrastRect;                      // 区域增强位置,增加本区域与周边的对比度,8192坐标系
    }

    // 热成像配置
    public static class CFG_THERMOGRAPHY_INFO extends SdkStructure
    {
        public int              nModeCount;                           // 模式个数，目前只有一个
        public CFG_THERMOGRAPHY_OPTION[] stOptions = new CFG_THERMOGRAPHY_OPTION[16]; // 对应不同模式的配置

        public CFG_THERMOGRAPHY_INFO() {
            for(int i = 0; i < stOptions.length; i++) {
                stOptions[i] = new CFG_THERMOGRAPHY_OPTION();
            }
        }
    }

    // 温度单位
    public static class NET_TEMPERATURE_UNIT extends SdkStructure
    {
        public static final int   NET_TEMPERATURE_UNIT_UNKNOWN = 0;
        public static final int   NET_TEMPERATURE_UNIT_CENTIGRADE = 1;  // 摄氏度
        public static final int   NET_TEMPERATURE_UNIT_FAHRENHEIT = 2;  // 华氏度
    }

    // 测温规则配置结构, 对应命令  CFG_CMD_THERMOMETRY_RULE
    public static class CFG_RADIOMETRY_RULE_INFO extends SdkStructure
    {
        public int              nCount;                               // 规则个数
        public CFG_RADIOMETRY_RULE[] stRule = new CFG_RADIOMETRY_RULE[512]; // 测温规则
        public CFG_RADIOMETRY_RULE_EX[] stRuleEx = new CFG_RADIOMETRY_RULE_EX[512]; //测温规则-扩展新增的字段

        public CFG_RADIOMETRY_RULE_INFO() {
            for(int i = 0; i < stRule.length; i++) {
                stRule[i] = new CFG_RADIOMETRY_RULE();
            }
        }
    }

    // 区域测温的子类型
    public static class EM_CFG_AREA_SUBTYPE extends SdkStructure
    {
        public static final int   EM_CFG_AREA_SUBTYPE_UNKNOWN = 0;
        public static final int   EM_CFG_AREA_SUBTYPE_RECT = 1;         // 矩形
        public static final int   EM_CFG_AREA_SUBTYPE_ELLIPSE = 2;      // 椭圆
        public static final int   EM_CFG_AREA_SUBTYPE_POLYGON = 3;      // 多边形
    }

    // 测温规则
    public static class CFG_RADIOMETRY_RULE extends SdkStructure
    {
        public int              bEnable;                              // 测温使能, BOOL类型
        public int              nPresetId;                            // 预置点编号
        public int              nRuleId;                              // 规则编号
        public byte[]           szName = new byte[128];               // 自定义名称
        public int              nMeterType;                           // 测温模式的类型，见 NET_RADIOMETRY_METERTYPE
        public CFG_POLYGON[]    stCoordinates = new CFG_POLYGON[64];  // 测温点坐标	使用相对坐标体系，取值均为0~8191
        public int              nCoordinateCnt;                       // 测温点坐标实际个数
        public int              nSamplePeriod;                        // 温度采样周期	单位 : 秒
        public CFG_RADIOMETRY_ALARMSETTING[] stAlarmSetting = new CFG_RADIOMETRY_ALARMSETTING[64]; // 测温点报警设置
        public int              nAlarmSettingCnt;                     // 测温点报警设置实际个数
        public CFG_RADIOMETRY_LOCALPARAM stLocalParameters;           // 本地参数配置
        public int              emAreaSubType;                        // 区域测温的子类型, 见EM_CFG_AREA_SUBTYPE

        public CFG_RADIOMETRY_RULE() {
            for(int i = 0; i < stCoordinates.length; i++) {
                stCoordinates[i] = new CFG_POLYGON();
            }

            for(int i = 0; i < stAlarmSetting.length; i++) {
                stAlarmSetting[i] = new CFG_RADIOMETRY_ALARMSETTING();
            }
        }
    }

    // 温度统计
    public static class CFG_TEMP_STATISTICS extends SdkStructure
    {
        public int              bEnable;                              // 是否开启温度统计
        public byte[]           szName = new byte[128];               // 测温项的名字
        public int              nMeterType;                           // 测温模式的类型，见 NET_RADIOMETRY_METERTYPE
        public int              nPeriod;                              // 保存温度数据周期
    }

    // 温度统计配置结构
    public static class CFG_TEMP_STATISTICS_INFO extends SdkStructure
    {
        public int              nCount;                               // 个数
        public CFG_TEMP_STATISTICS[] stStatistics = new CFG_TEMP_STATISTICS[64]; // 温度统计

        public CFG_TEMP_STATISTICS_INFO() {
            for(int i = 0; i < stStatistics.length; i++) {
                stStatistics[i] = new CFG_TEMP_STATISTICS();
            }
        }
    }

    // 温度单位
    public static class CFG_TEMPERATURE_UNIT extends SdkStructure
    {
        public static final int   TEMPERATURE_UNIT_UNKNOWN = 0;
        public static final int   TEMPERATURE_UNIT_CENTIGRADE = 1;      // 摄氏度
        public static final int   TEMPERATURE_UNIT_FAHRENHEIT = 2;      // 华氏度
    }

    // 热成像测温全局配置
    public static class CFG_THERMOMETRY_INFO extends SdkStructure
    {
        public int              nRelativeHumidity;                    // 相对湿度
        public float            fAtmosphericTemperature;              // 大气温度
        public float            fObjectEmissivity;                    // 物体辐射系数
        public int              nObjectDistance;                      // 物体距离
        public float            fReflectedTemperature;                // 物体反射温度
        public int              nTemperatureUnit;                     // 温度单位，见 TEMPERATURE_UNIT
        public int              bIsothermEnable;                      // 色标功能使能
        public int              nMinLimitTemp;                        // 等温线下限温度值
        public int              nMediumTemp;                          // 等温线中位温度值
        public int              nMaxLimitTemp;                        // 等温线上限温度值
        public int              nSaturationTemp;                      // 等温线饱和温度值
        public CFG_RECT         stIsothermRect;                       // 色温条矩形区域（OSD 位置），使用相对坐标体系，取值均为0-8191
        public int              bColorBarDisplay;                     // 是否显示色标条（OSD 叠加）
        public int              bHotSpotFollow;                       // 是否开启热点探测追踪使能
        public int              bTemperEnable;                        // 测温开关
        public CFG_RGBA         stHighCTMakerColor;                   // 高色温标注颜色
        public CFG_RGBA         stLowCTMakerColor;                    // 低色温标注颜色
        public int              bColdSpotFollow;                      //是否开启冷点探测追踪使能
        public byte[]           szResvered = new byte[1020];          //保留字节
    }

    // 测温点报警设置
    public static class CFG_RADIOMETRY_ALARMSETTING extends SdkStructure
    {
        public int              nId;                                  // 报警唯一编号	报警编号统一编码
        public int              bEnable;                              // 是否开启该点报警, BOOL类型
        public int              nResultType;                          // 测温报警结果类型，见 CFG_STATISTIC_TYPE，可取值：
        // 点测温：具体值，
        // 线测温：最大, 最小, 平均
        // 区域测温：最大, 最小, 平均, 标准, 中间, ISO
        public int              nAlarmCondition;                      // 报警条件，见 CFG_COMPARE_RESULT
        public float            fThreshold;                           // 报警阈值温度	浮点数
        public float            fHysteresis;                          // 温度误差，浮点数，比如0.1 表示正负误差在0.1范围内
        public int              nDuration;                            // 阈值温度持续时间	单位：秒
    }

    // 测温规则本地参数配置
    public static class CFG_RADIOMETRY_LOCALPARAM extends SdkStructure
    {
        public int              bEnable;                              // 是否启用本地配置, BOOL类型
        public float            fObjectEmissivity;                    // 目标辐射系数	浮点数 0~1
        public int              nObjectDistance;                      // 目标距离
        public int              nRefalectedTemp;                      // 目标反射温度
    }

    // 通道录像组状态
    public static class CFG_DEVRECORD_INFO extends SdkStructure
    {
        public byte[]           szDevName = new byte[MAX_NAME_LEN];   // 设备名称
        public byte[]           szIP = new byte[MAX_ADDRESS_LEN];     // 设备IP
        public byte[]           szChannel = new byte[MAX_NAME_LEN];   // 通道号
        public byte[]           szChannelName = new byte[MAX_NAME_LEN]; // 通道名称
        public byte[]           szStoragePosition = new byte[MAX_NAME_LEN]; // 存储位置信息
        public byte             byStatus;                             // 状态 0:未知 1:录像 2:停止
        public byte[]           byReserved = new byte[3];             // 字节对齐
    }

    public static class CFG_DEVRECORDGROUP_INFO extends SdkStructure
    {
        public int              nChannelNum;                          // 通道个数
        public CFG_DEVRECORD_INFO[] stuDevRecordInfo = (CFG_DEVRECORD_INFO[])new CFG_DEVRECORD_INFO().toArray(MAX_CHAN_NUM); // 通道录像状态信息
    }

    // 存储组通道相关配置
    public static class AV_CFG_StorageGroupChannel extends SdkStructure
    {
        public int              nStructSize;
        public int              nMaxPictures;                         // 每个通道文件夹图片存储上限, 超过就覆盖
        public byte[]           szPath = new byte[AV_CFG_Max_ChannelRule]; // 通道在命名规则里的字符串表示, %c对应的内容

        public AV_CFG_StorageGroupChannel() {
            this.nStructSize = this.size();
        }
    }

    public static class DEVICE_NAME extends SdkStructure
    {
        public byte[]           szDeviceName = new byte[MAX_DEVICE_NAME_LEN]; // 历史SSID
    }

    // 存储组配置
    public static class AV_CFG_StorageGroup extends SdkStructure
    {
        public int              nStructSize;
        public byte[]           szName = new byte[AV_CFG_Group_Name_Len]; // 分组名称
        public byte[]           szMemo = new byte[AV_CFG_Group_Memo_Len]; // 分组说明
        public int              nFileHoldTime;                        // 文件保留时间
        public int              bOverWrite;                           // 存储空间满是否覆盖
        public byte[]           szRecordPathRule = new byte[AV_CFG_Max_Path]; // 录像文件路径命名规则
        public byte[]           szPicturePathRule = new byte[AV_CFG_Max_Path]; // 图片文件路径命名规则 %y年, %M月, %d日, %h时, %m分, %s秒, %c通道路径
        // 如果年月日时分秒出现两次, 第一次表示开始时间, 第二次表示结束时间
        public AV_CFG_StorageGroupChannel[] stuChannels = new AV_CFG_StorageGroupChannel[AV_CFG_Max_Channel_Num]; // 通道相关配置
        public int              nChannelCount;                        // 通道配置数
        public byte[]           szCustomName = new byte[AV_CFG_Group_Name_Len]; // 自定义名称，若为空使用szName
        public DEVICE_NAME[]    szSubDevices = new DEVICE_NAME[MAX_DEV_NUM]; // 子设备列表
        public int              nSubDevices;                          // 子设备数量

        public AV_CFG_StorageGroup() {
            this.nStructSize = this.size();

            for (int i = 0; i < stuChannels.length; ++i) {
                stuChannels[i] = new AV_CFG_StorageGroupChannel();
            }

            for (int i = 0; i < szSubDevices.length; ++i) {
                szSubDevices[i] = new DEVICE_NAME();
            }
        }
    }

    // 巡航路径中的预置点
    public static class CFG_PTZTOUR_PRESET extends SdkStructure
    {
        public int              nPresetID;                            // 预置点编号
        public int              nDuration;                            // 在改预置点的停留时间, 单位秒
        public int              nSpeed;                               // 到达该预置点的转动速度, 1~10
        public int              bEnable;                              // 预置点聚焦使能，0为不使能，1为使能
    }

    // 巡航路径
    public static class CFG_PTZTOUR_SINGLE extends SdkStructure
    {
        public int              bEnable;                              // 使能
        public byte[]           szName = new byte[CFG_COMMON_STRING_64]; // 名称
        public int              nPresetsNum;                          // 预置点数量
        public CFG_PTZTOUR_PRESET[] stPresets = new CFG_PTZTOUR_PRESET[CFG_MAX_PTZTOUR_PRESET_NUM]; // 该路径包含的预置点参数

        public CFG_PTZTOUR_SINGLE() {
            for (int i = 0; i < stPresets.length; ++i) {
                stPresets[i] = new CFG_PTZTOUR_PRESET();
            }
        }
    }

    // 云台巡航路径配置
    public static class CFG_PTZTOUR_INFO extends SdkStructure
    {
        public int              nCount;                               // 巡航路径数量
        public CFG_PTZTOUR_SINGLE[] stTours = new CFG_PTZTOUR_SINGLE[CFG_MAX_PTZTOUR_NUM]; // 巡航路径, 每个通道包含多条巡航路径

        public CFG_PTZTOUR_INFO() {
            for (int i = 0; i < stTours.length; ++i) {
                stTours[i] = new CFG_PTZTOUR_SINGLE();
            }
        }
    }

    // 云台控制坐标单元
    public static class CFG_PTZ_SPACE_UNIT extends SdkStructure
    {
        public int              nPositionX;                           //云台水平运动位置，有效范围：[0,3600]
        public int              nPositionY;                           //云台垂直运动位置，有效范围：[-1800,1800]
        public int              nZoom;                                //云台光圈变动位置，有效范围：[0,128]
    }

    // 云台控制预置点结构
    public static class PTZ_PRESET extends SdkStructure
    {
        public int              bEnable;                              // 该预置点是否生效
        public byte[]           szName = new byte[MAX_PTZ_PRESET_NAME_LEN]; // 预置点名称
        public CFG_PTZ_SPACE_UNIT stPosition;                         // 预置点的坐标和放大倍数
    }

    // 云台预置点配置对应结构
    public static class PTZ_PRESET_INFO extends SdkStructure
    {
        public int              dwMaxPtzPresetNum;                    // 最大预置点个数
        public int              dwRetPtzPresetNum;                    // 实际使用预置点个数
        public Pointer          pstPtzPreset;                         // 预置点信息(根据最大个数申请内存，大小sizeof(PTZ_PRESET)*dwMaxPtzPresetNum)
    }

    // 统计量类型
    public static class CFG_STATISTIC_TYPE extends SdkStructure
    {
        public static final int   CFG_STATISTIC_TYPE_UNKNOWN = 0;
        public static final int   CFG_STATISTIC_TYPE_VAL = 1;           // 具体值
        public static final int   CFG_STATISTIC_TYPE_MAX = 2;           // 最大
        public static final int   CFG_STATISTIC_TYPE_MIN = 3;           // 最小
        public static final int   CFG_STATISTIC_TYPE_AVR = 4;           // 平均
        public static final int   CFG_STATISTIC_TYPE_STD = 5;           // 标准
        public static final int   CFG_STATISTIC_TYPE_MID = 6;           // 中间
        public static final int   CFG_STATISTIC_TYPE_ISO = 7;           // ISO
    }

    // 比较运算结果
    public static class CFG_COMPARE_RESULT extends SdkStructure
    {
        public static final int   CFG_COMPARE_RESULT_UNKNOWN = 0;
        public static final int   CFG_COMPARE_RESULT_BELOW = 1;         // 低于
        public static final int   CFG_COMPARE_RESULT_MATCH = 2;         // 匹配
        public static final int   CFG_COMPARE_RESULT_ABOVE = 3;         // 高于
    }

    // 记录集新增操作(insert)参数
    public static class NET_CTRL_RECORDSET_INSERT_PARAM extends SdkStructure
    {
        public int              dwSize;
        public NET_CTRL_RECORDSET_INSERT_IN stuCtrlRecordSetInfo;     // 记录集信息(用户填写)
        public NET_CTRL_RECORDSET_INSERT_OUT stuCtrlRecordSetResult;  // 记录集信息(设备返回)

        public NET_CTRL_RECORDSET_INSERT_PARAM() {
            this.dwSize = this.size();
        }
    }

    // 记录集新增操作(insert)输入参数
    public static class NET_CTRL_RECORDSET_INSERT_IN extends SdkStructure
    {
        public int              dwSize;
        public int              emType;                               // 记录集信息类型, 取值参考  EM_NET_RECORD_TYPE
        public Pointer          pBuf;                                 // 记录集信息缓存,详见EM_NET_RECORD_TYPE注释，由用户申请内存.
        public int              nBufLen;                              // 记录集信息缓存大小,大小参照记录集信息类型对应的结构体

        public NET_CTRL_RECORDSET_INSERT_IN() {
            this.dwSize = this.size();
        }
    }

    // 记录集新增操作(insert)输出参数
    public static class NET_CTRL_RECORDSET_INSERT_OUT extends SdkStructure
    {
        public int              dwSize;
        public int              nRecNo;                               // 记录编号(新增insert时设备返回)

        public NET_CTRL_RECORDSET_INSERT_OUT() {
            this.dwSize = this.size();
        }
    }

    // 门禁密码记录查询条件
    public static class FIND_RECORD_ACCESSCTLPWD_CONDITION extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID

        public FIND_RECORD_ACCESSCTLPWD_CONDITION() {
            this.dwSize = this.size();
        }
    }

    // 门禁密码记录集信息
    public static class NET_RECORDSET_ACCESS_CTL_PWD extends SdkStructure
    {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public NET_TIME         stuCreateTime;                        // 创建时间
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID, 设备暂不支持
        public byte[]           szDoorOpenPwd = new byte[NET_MAX_CARDPWD_LEN]; // 开门密码
        public byte[]           szAlarmPwd = new byte[NET_MAX_CARDPWD_LEN]; // 报警密码
        public int              nDoorNum;                             // 有效的的门数目
        public int[]            sznDoors = new int[NET_MAX_DOOR_NUM]; // 有权限的门序号,即 CFG_CMD_ACCESS_EVENT 配置CFG_ACCESS_EVENT_INFO的数组下标
        public byte[]           szVTOPosition = new byte[NET_COMMON_STRING_64]; // 门口机关联位置
        public int              nTimeSectionNum;                      // 开门时间段个数
        public int[]            nTimeSectionIndex = new int[NET_MAX_TIMESECTION_NUM]; // 开门时间段索引,是个数组，每个元素与sznDoors中的门对应
        public int              bNewDoor;                             // BOOL类型, 是否启动新开门授权字段，TRUE表示使用 nNewDoorNum 和 nNewDoors 字段下发开门权限
        public int              nNewDoorNum;                          // 有效的门数目;
        public int[]            nNewDoors = new int[MAX_ACCESSDOOR_NUM]; // 有权限的门序号,即 CFG_CMD_ACCESS_EVENT 配置的数组下标
        public int              nNewTimeSectionNum;                   // 有效的的开门时间段数目
        public int[]            nNewTimeSectionNo = new int[MAX_ACCESSDOOR_NUM]; // 开门时间段索引,即 CFG_ACCESS_TIMESCHEDULE_INFO 的数组下标
        public NET_TIME         stuValidStartTime;                    // 开始有效期
        public NET_TIME         stuValidEndTime;                      // 结束有效期
        public int              nValidCounts;                         // 有效次数
        public byte[]           szCitizenIDNo = new byte[20];         // 证件号码

        public NET_RECORDSET_ACCESS_CTL_PWD() {
            this.dwSize = this.size();
        }
    }

    // 开门二维码记录集信息
    public static class NET_RECORD_ACCESSQRCODE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRecNo;                               // 记录集编号,只读
        public byte[]           szQRCode = new byte[NET_MAX_QRCODE_LEN]; // 二维码
        public int              nLeftTimes;                           // 剩余的有效次数
        public NET_TIME         stuStartTime;                         // 有效期开始时间
        public NET_TIME         stuEndTime;                           // 有效期截止时间
        public byte[]           szRoomNumber = new byte[16];          // 房间号
        public byte[]           szUserID = new byte[32];              // 用户ID

        public NET_RECORD_ACCESSQRCODE_INFO(){
            this.dwSize = this.size();
        }
    }

    // 查询盒子工作状态, 对应命令  NET_DEVSTATE_GET_WORK_STATE
    public static class NET_QUERY_WORK_STATE extends SdkStructure
    {
        public int              dwSize;                               // 保留字段
        public NET_WORKSTATE    stuWorkState;                         // 运行状态

        public NET_QUERY_WORK_STATE() {
            this.dwSize = this.size();
        }
    }

    // 设备工作状态
    public static class NET_WORKSTATE extends SdkStructure
    {
        public int              bOnline;                              // 设备是否在线,BOOL类型
        public byte[]           szFirmwareVersion = new byte[NET_COMMON_STRING_128]; // 固件版本号
        public float            fTemperature;                         // 温度值, 单位摄氏度
        public float            fPowerDissipation;                    // 功耗, 单位W
        public int              nUtilizationOfCPU;                    // CPU 使用率
        public int              nStorageNum;                          // 存储设备个数
        public NET_STORAGE_INFO[] stuStorages = new NET_STORAGE_INFO[MAX_STORAGE_NUM]; // 存储设备信息
        public int              nUpTimeLast;                          // 上次上电时间, 单位: 秒
        public int              nUpTimeTotal;                         // 总共上电时间, 单位: 秒
        public double           dbMemInfoTotal;                       // 总内存大小, 单位: 字节
        public double           dbMemInfoFree;                        // 剩余内存大小, 单位: 字节
        public byte[]           byReserved1 = new byte[4];            // 字节对齐，非保留字节
        public byte[]           szDevType = new byte[32];             // 设备型号
        public NET_RESOURCE_STATE stuResourceStat;                    // 网络资源
        public byte[]           byReserved = new byte[8];             // 保留字节

        public NET_WORKSTATE() {
            for(int i = 0; i < MAX_STORAGE_NUM; i++) {
                stuStorages[i] = new NET_STORAGE_INFO();
            }
        }
    }

    // 存储设备信息
    public static class NET_STORAGE_INFO extends SdkStructure
    {
        public int              emState;                              // 存储设备状态, ENUM_STORAGE_STATE
        public int              nPartitonNum;                         // 分区个数
        public NET_PARTITION_INFO[] stuPartions = new NET_PARTITION_INFO[MAX_PARTITION_NUM]; // 分区信息
        public byte[]           byReserved = new byte[128];           // 保留字段

        public NET_STORAGE_INFO() {
            for(int i = 0; i < MAX_PARTITION_NUM; i++) {
                stuPartions[i] = new NET_PARTITION_INFO();
            }
        }
    }

    // 网络资源
    public static class NET_RESOURCE_STATE extends SdkStructure
    {
        public int              nIPChanneIn;                          // IP通道接入速度, 单位: kbps
        public int              nNetRemain;                           // 网络接收剩余能力, 单位: kbps
        public int              nNetCapability;                       // 网络接收总能力, 单位: kbps
        public int              nRemotePreview;                       // 远程预览能力, 单位: kbps
        public int              nRmtPlayDownload;                     // 远程回放及下载能力, 单位: kbps
        public int              nRemoteSendRemain;                    // 远程发送剩余能力, 单位: kbps
        public int              nRemoteSendCapability;                // 远程发送总能力, 单位: kbps
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    // 存储设备分区信息
    public static class NET_PARTITION_INFO extends SdkStructure
    {
        public double           dbTotalBytes;                         // 分区总空间
        public double           dbUsedBytes;                          // 分区使用的空间
        public int              bError;                               // 是否异常, BOOL类型
        public byte[]           byReserved = new byte[64];            // 保留字段
    }

    // CLIENT_GetSelfCheckInfo 输入参数
    public static class NET_IN_GET_SELTCHECK_INFO extends SdkStructure
    {
        public int              dwSize;                               // 用户使用该结构体时,dwSize 需赋值为 sizeof (NET_IN_GET_SELTCHECK_INFO)

        public NET_IN_GET_SELTCHECK_INFO() {
            this.dwSize = this.size();
        }
    }

    // 设备自检信息
    public static class NET_SELFCHECK_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nAlarmIn;                             // 报警输入通道数
        public int              nAlarmOut;                            // 报警输出通道数
        public NET_TIME         stuTime;                              // 上报时间
        public byte[]           szPlateNo = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌
        public byte[]           szICCID = new byte[NET_MAX_SIM_LEN];  // SIM卡号,建议使用szICCIDExInfo字段
        public byte             byOrientation;                        // 定位状态,0-未定位,1-定位
        public byte             byACCState;                           // ACC 状态,0-关闭,1-打开
        public byte             byConstantElecState;                  // 常电状态,0-正常连接,1-断开,2-欠压,3-高压
        public byte             byAntennaState;                       // 通信信号状态,0-正常,1-未知故障,2-未接,3-短路
        // 外部设备状态
        public byte             byReportStation;                      // 报站器状态,0-未接,1-正常,2-异常
        public byte             byControlScreen;                      // 调度屏状态,0-未接,1-正常,2-异常
        public byte             byPOS;                                // POS机状态,0-未接,1-正常,2-异常
        public byte             byCoinBox;                            // 投币箱状态,0-未接,1-正常,2-异常
        // 能力集
        public int              bTimerSnap;                           // 定时抓图,TRUE-支持,FALSE-不支持, BOOL类型
        public int              bElectronEnclosure;                   // 电子围栏,TRUE-支持,FALSE-不支持, BOOL类型
        public int              bTeleUpgrade;                         // 远程升级,TRUE-支持,FALSE-不支持, BOOL类型
        public NET_HDD_STATE[]  stuHddStates = new NET_HDD_STATE[NET_MAX_DISKNUM]; // 硬盘状态
        public int              nHddNum;                              // 硬盘个数
        public Pointer          pChannleState;                        // 通道状态,是一个 NET_CHANNLE_STATE 数组,
        // CLIENT_AttachMission接口,NET_MISSION_TYPE_SELFCHECK类型,回调函数,内存由SDK申请,SDK释放
        // CLIENT_GetSelfCheckInfo接口,出参,内存由用户申请,用户释放,大小为sizeof(NET_CHANNLE_STATE)*nChannelMax
        public int              nChannleNum;                          // 实际上报的通道个数
        public int              nChannelMax;                          // CLIENT_GetSelfCheckInfo接口,pChannleState内存的最大NET_CHANNLE_STATE个数
        public int              emConnState;                          // PAD/DVR连接状态, 参考 NET_PAD_CONNECT_STATE
        public int              emHomeState;                          // Home键状态， 参考  NET_HOME_STATE
        public byte[]           szICCIDExInfo = new byte[NET_COMMON_STRING_256]; // SIM卡号扩展信息，用于字段扩展使用
        public byte             by3GState;                            // 3G/4G状态, 0-未连接，1-连接，2-模块未找到
        public byte             byWifiState;                          // Wifi状态, 0-未连接，1-连接，2-模块未找到
        public byte             byGpsState;                           // Gps状态, 0-未连接，1-连接，2-模块未找到
        public byte             byBlackBoxState;                      // BlackBox状态, 0-未接，1-正常
        public int              nCpuUsage;                            // CPU使用百分比, 单位%
        public int              nTemperature;                         // 设备内部温度, 摄氏度
        public byte[]           szVendor = new byte[32];              // 生产厂商
        public byte[]           szFirmwareVersion = new byte[128];    // 设备程序版本，是一个字符数组
        public byte[]           szSecurityBaseLineVersion = new byte[8]; // 安全基线版本
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS状态信息
        public byte[]           szNetworkOperName = new byte[32];     // 4G运营商网络信息，需要向sim卡提供方获取
        public int              emEmergencyStatus;                    // 紧急报警状态,EM_EMERGENCYSTATUS_TYPE
        public int              emTamperAletStatus;                   // 设备锁状态,EM_TAMPERALTERSTATUS_TYPE
        public byte[]           szImei = new byte[16];                // 国际移动设备辨识码15位数字标识
        public int              nGSMsignalStrength;                   // 3G信号强度0 - 31
        public int              nMcc;                                 // 移动信号所属国家码
        public int              nMnc;                                 // 移动网络号码，用于识别移动客户所属的移动网络，2~3位数字组成
        public int              nLAC;                                 // 位置区码 （移动通信系统中）,是为寻呼而设置的一个区域，覆盖一片地理区域，初期一般按行政区域划分（一个县或一个区）,现在很灵活了，按寻呼量划分
        public int              nCi;                                  // 小区识别码,三种主要的基于位置服务（LBS）技术之一。小区识别码通过识别网络中哪一个小区传输用户呼叫并将该信息翻译成纬度和经度来确定用户位置
        public int              nAlarmInStatusNum;                    // IO报警输入状态个数
        public int[]            nAlarmInStatus = new int[32];         // IO报警输入状态列表一维数组，每个成员表示对应的通道报警输入状态（0=Off; 1 =On）
        public int              nAlarmOutStatusNum;                   // IO报警输出状态个数
        public int[]            nAlarmOutStatus = new int[32];        // IO报警输出状态列表一维数组，每个成员表示对应的通道报警输出状态（0=Off; 1 =On）
        public byte[]           szMasterSvrAddr = new byte[128];      // 主服务器地址，IPv4格式为点分十进制，IPv6格式为x:x:x:x:x:x:x:x，其中x是16位的十六进制值
        public byte[]           szSlaveSvrAddr = new byte[128];       // 从服务器地址，IPv4格式为点分十进制，IPv6格式为x:x:x:x:x:x:x:x，其中x是16位的十六进制值
        public byte[]           szSerialNo = new byte[48];            // 设备序列号
        public int              emSignalStrength;                     // GPS 模块信号强度,参考枚举EM_GPS_SIGNAL_STRENGTH_TYPE
        public int              emDataType;                           // 事件数据类型,参考枚举EM_SELFCHECK_DATA_TYPE

        public NET_SELFCHECK_INFO() {
            this.dwSize = this.size();

            for(int i = 0; i < NET_MAX_DISKNUM; i++) {
                stuHddStates[i] = new NET_HDD_STATE();
            }
        }
    }

    // 硬盘状态
    public static class NET_HDD_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              nState;                               // 硬盘状态,0-正常,1-错误
        public double           dbTotalSize;                          // 硬盘总容量,字节为单位
        public NET_PARTITION_STATE[] stuPartitions = new NET_PARTITION_STATE[NET_MAX_STORAGE_PARTITION_NUM]; // 分区状态
        public int              nPartitionNum;                        // 分区数

        public NET_HDD_STATE() {
            this.dwSize = this.size();

            for(int i = 0; i < NET_MAX_STORAGE_PARTITION_NUM; i++) {
                stuPartitions[i] = new NET_PARTITION_STATE();
            }
        }
    }

    // 分区状态
    public static class NET_PARTITION_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              nStatus;                              // 分区状态,0-正常,1-错误
        public double           dbTotalSize;                          // 分区总容量,字节为单位
        public double           dbRemainSize;                         // 剩余容量,字节为单位

        public NET_PARTITION_STATE() {
            this.dwSize = this.size();
        }
    }

    // 录像状态详细信息
    public static class NET_RECORD_STATE_DETAIL extends SdkStructure
    {
        public int              dwSize;
        public int              bMainStream;                          // 主码流, TRUE-正在录像, FALSE-没在录像
        public int              bExtraStream1;                        // 辅码流1, TRUE-正在录像, FALSE-没在录像
        public int              bExtraStream2;                        // 辅码流2, TRUE-正在录像, FALSE-没在录像
        public int              bExtraStream3;                        // 辅码流3, TRUE-正在录像, FALSE-没在录像

        public NET_RECORD_STATE_DETAIL() {
            this.dwSize = this.size();
        }
    }

    // 硬盘信息
    public static class NET_DEV_DISKSTATE extends SdkStructure
    {
        public int              dwVolume;                             // 硬盘的容量, 单位MB(B表示字节)
        public int              dwFreeSpace;                          // 硬盘的剩余空间, 单位MB(B表示字节)
        public byte             dwStatus;                             // 高四位的值表示硬盘类型,具体见枚举类型EM_DISK_TYPE；低四位的值表示硬盘的状态,0-休眠,1-活动,2-故障等；将DWORD拆成四个BYTE
        public byte             bDiskNum;                             // 硬盘号
        public byte             bSubareaNum;                          // 分区号
        public byte             bSignal;                              // 标识,0为本地 1为远程
    }

    // 设备硬盘信息
    public static class NET_DEV_HARDDISK_STATE extends SdkStructure
    {
        public int              dwDiskNum;                            // 个数
        public NET_DEV_DISKSTATE[] stDisks = new NET_DEV_DISKSTATE[NET_MAX_DISKNUM]; // 硬盘或分区信息

        public NET_DEV_HARDDISK_STATE() {
            for(int i = 0; i < NET_MAX_DISKNUM; i++) {
                stDisks[i] = new NET_DEV_DISKSTATE();
            }
        }
    }

    public static class EM_DISK_TYPE extends SdkStructure
    {
        public static final int   SDK_DISK_READ_WRITE = 0;              // 读写驱动器
        public static final int   SDK_DISK_READ_ONLY = 1;               // 只读驱动器
        public static final int   SDK_DISK_BACKUP = 2;                  // 备份驱动器或媒体驱动器
        public static final int   SDK_DISK_REDUNDANT = 3;               // 冗余驱动器
        public static final int   SDK_DISK_SNAPSHOT = 4;                // 快照驱动器
    }

    //表示硬盘的基本信息
    public static class NETDEV_DEVICE_INFO extends SdkStructure
    {
        public byte[]           byModle = new byte[32];               // 型号
        public byte[]           bySerialNumber = new byte[32];        // 序列号
        public byte[]           byFirmWare = new byte[32];            // 固件号
        public int              nAtaVersion;                          // ATA协议版本号
        public int              nSmartNum;                            // smart 信息数
        public long             Sectors;
        public int              nStatus;                              // 磁盘状态 0-正常 1-异常
        public int[]            nReserved = new int[33];              // 保留字节
    }

    //硬盘的smart信息,可能会有很多条,最多不超过30个　　
    public static class NETDEV_SMART_VALUE extends SdkStructure
    {
        public byte             byId;                                 // ID
        public byte             byCurrent;                            // 属性值
        public byte             byWorst;                              // 最大出错值
        public byte             byThreshold;                          // 阈值
        public byte[]           szName = new byte[64];                // 属性名
        public byte[]           szRaw = new byte[8];                  // 实际值
        public int              nPredict;                             // 状态
        public byte[]           reserved = new byte[128];
    }

    //硬盘smart信息查询
    public static class NETDEV_SMART_HARDDISK extends SdkStructure
    {
        public byte             nDiskNum;                             // 硬盘号
        public byte             byRaidNO;                             // Raid子盘,0表示单盘
        public byte[]           byReserved = new byte[2];             // 保留字节
        public NETDEV_DEVICE_INFO deviceInfo;
        public NETDEV_SMART_VALUE[] smartValue = new NETDEV_SMART_VALUE[MAX_SMART_VALUE_NUM];

        public NETDEV_SMART_HARDDISK() {
            for(int i = 0; i < MAX_SMART_VALUE_NUM; i++) {
                smartValue[i] = new NETDEV_SMART_VALUE();
            }
        }
    }

    // 扩展网络配置结构体
    public static class NETDEV_NET_CFG_EX extends SdkStructure
    {
        public int              dwSize;
        public byte[]           sDevName = new byte[NET_MAX_NAME_LEN]; // 设备主机名
        public short            wTcpMaxConnectNum;                    // TCP最大连接数
        public short            wTcpPort;                             // TCP帧听端口
        public short            wUdpPort;                             // UDP侦听端口
        public short            wHttpPort;                            // HTTP端口号
        public short            wHttpsPort;                           // HTTPS端口号
        public short            wSslPort;                             // SSL端口号
        public int              nEtherNetNum;                         // 以太网口数
        public NET_ETHERNET_EX[] stEtherNet = new NET_ETHERNET_EX[NET_MAX_ETHERNET_NUM_EX]; // 以太网口
        public NET_REMOTE_HOST  struAlarmHost;                        // 报警服务器
        public NET_REMOTE_HOST  struLogHost;                          // 日志服务器
        public NET_REMOTE_HOST  struSmtpHost;                         // SMTP服务器
        public NET_REMOTE_HOST  struMultiCast;                        // 多播组
        public NET_REMOTE_HOST  struNfs;                              // NFS服务器
        public NET_REMOTE_HOST  struPppoe;                            // PPPoE服务器
        public byte[]           sPppoeIP = new byte[NET_MAX_IPADDR_LEN]; // PPPoE注册返回的IP
        public NET_REMOTE_HOST  struDdns;                             // DDNS服务器
        public byte[]           sDdnsHostName = new byte[NET_MAX_HOST_NAMELEN]; // DDNS主机名
        public NET_REMOTE_HOST  struDns;                              // DNS服务器
        public NET_MAIL_CFG     struMail;                             // 邮件配置
        public byte[]           bReserved = new byte[128];            // 保留字节

        public NETDEV_NET_CFG_EX() {
            this.dwSize = this.size();

            for(int i = 0; i < NET_MAX_ETHERNET_NUM_EX; i++) {
                stEtherNet[i] = new NET_ETHERNET_EX();
            }
        }
    }

    // 以太网扩展配置
    public static class NET_ETHERNET_EX extends SdkStructure
    {
        public byte[]           sDevIPAddr = new byte[NET_MAX_IPADDR_LEN]; // DVR IP 地址
        public byte[]           sDevIPMask = new byte[NET_MAX_IPADDR_LEN]; // DVR IP 地址掩码
        public byte[]           sGatewayIP = new byte[NET_MAX_IPADDR_LEN]; // 网关地址
        /*
         * 1：10Mbps 全双工
         * 2：10Mbps 自适应
         * 3：10Mbps 半双工
         * 4：100Mbps 全双工
         * 5：100Mbps 自适应
         * 6：100Mbps 半双工
         * 7：自适应
         */
        // 为了扩展将DWORD拆成四个
        public byte             dwNetInterface;                       // NSP
        public byte             bTranMedia;                           // 0：有线,1：无线
        public byte             bValid;                               // 按位表示,第一位：1：有效 0：无效；第二位：0：DHCP关闭 1：DHCP使能；第三位：0：不支持DHCP 1：支持DHCP
        public byte             bDefaultEth;                          // 是否作为默认的网卡 1：默认 0：非默认
        public byte[]           byMACAddr = new byte[NET_MACADDR_LEN]; // MAC地址,只读
        public byte             bMode;                                // 网卡所处模式, 0:绑定模式, 1:负载均衡模式, 2:多址模式, 3:容错模式
        public byte[]           bReserved1 = new byte[3];             // 字节对齐
        public byte[]           szEthernetName = new byte[NET_MAX_NAME_LEN]; // 网卡名,只读
        public byte[]           bReserved = new byte[12];             // 保留字节
    }

    // 远程主机配置
    public static class NET_REMOTE_HOST extends SdkStructure
    {
        public byte             byEnable;                             // 连接使能
        public byte             byAssistant;                          // 目前只对于PPPoE服务器有用,0：在有线网卡拨号；1：在无线网卡上拨号
        public short            wHostPort;                            // 远程主机 端口
        public byte[]           sHostIPAddr = new byte[NET_MAX_IPADDR_LEN]; // 远程主机 IP 地址
        public byte[]           sHostUser = new byte[NET_MAX_HOST_NAMELEN]; // 远程主机 用户名
        public byte[]           sHostPassword = new byte[NET_MAX_HOST_PSWLEN]; // 远程主机 密码
    }

    // 邮件配置
    public static class NET_MAIL_CFG extends SdkStructure
    {
        public byte[]           sMailIPAddr = new byte[NET_MAX_IPADDR_LEN]; // 邮件服务器IP地址
        public short            wMailPort;                            // 邮件服务器端口
        public short            wReserved;                            // 保留
        public byte[]           sSenderAddr = new byte[NET_MAX_MAIL_ADDR_LEN]; // 发送地址
        public byte[]           sUserName = new byte[NET_MAX_NAME_LEN]; // 用户名
        public byte[]           sUserPsw = new byte[NET_MAX_NAME_LEN]; // 用户密码
        public byte[]           sDestAddr = new byte[NET_MAX_MAIL_ADDR_LEN]; // 目的地址
        public byte[]           sCcAddr = new byte[NET_MAX_MAIL_ADDR_LEN]; // 抄送地址
        public byte[]           sBccAddr = new byte[NET_MAX_MAIL_ADDR_LEN]; // 暗抄地址
        public byte[]           sSubject = new byte[NET_MAX_MAIL_SUBJECT_LEN]; // 标题
    }

    // 自动维护配置
    public static class NETDEV_AUTOMT_CFG extends SdkStructure
    {
        public int              dwSize;
        public byte             byAutoRebootDay;                      // 自动重启；0：从不, 1：每天,2：每星期日,3：每星期一,......
        public byte             byAutoRebootTime;                     // 0：0:00,1：1:00,......23：23:00
        public byte             byAutoDeleteFilesTime;                // 自动删除文件；0：从不,1：24H,2：48H,3：72H,4：96H,......
        public byte[]           reserved = new byte[13];              // 保留位

        public NETDEV_AUTOMT_CFG() {
            this.dwSize = this.size();
        }
    }

    // 电子围栏类型枚举
    public static class ENCLOSURE_TYPE
    {
        public static final int   ENCLOSURE_UNKNOWN = 0x00;             // 未知
        public static final int   ENCLOSURE_LIMITSPEED = 0x01;          // 限速区
        public static final int   ENCLOSURE_DRIVEALLOW = 0x02;          // 驾驶区
        public static final int   ENCLOSURE_FORBIDDRIVE = 0x04;         // 禁止区
        public static final int   ENCLOSURE_LOADGOODS = 0x08;           // 装货区
        public static final int   ENCLOSURE_UPLOADGOODS = 0x10;         // 卸货区
        public static final int   ENCLOSURE_FLYALLOW = 0x20;            // 飞行区
        public static final int   ENCLOSURE_MANUALFORBIDFLY = 0x40;     // 禁飞区(手动设置)
        public static final int   ENCLOSURE_FIXEDFORBIDFLY = 0x80;      // 禁飞区(机场) - 不可配置
        public static final int   ENCLOSURE_FiXEDLIMITFLY = 0x81;       // 限制飞行 - 不可配置
    }

    // 电子围栏配置
    public static class NETDEV_ENCLOSURE_CFG extends SdkStructure
    {
        public int              unType;                               // 电子围栏类型掩码,见 ENCLOSURE_TYPE
        public byte[]           bRegion = new byte[8];                // 前四位分别代表国家、省、市或区、县(0-255),后4bytes保留
        public int              unId;                                 // 一个区域以一个ID标识
        public int              unSpeedLimit;                         // 限速,单位km/h
        public int              unPointNum;                           // 电子围栏区域顶点数
        public GPS_POINT[]      stPoints = (GPS_POINT[]) new GPS_POINT().toArray(128); // 电子围栏区域信息
        public byte[]           szStationName = new byte[32];         // 电子围栏包围的车站站点名称
        public byte             bDisenable;                           // 去使能。 0 启用 ;1 不启用
        public byte             bShape;                               // 围栏形状 0 多边形 1 圆形
        public byte             bLimitType;                           // 围栏限制类型 0 无; 1 限高; 2 限半径; 3 限高限半径
        public byte             bAction;                              // 触发围栏后，设备的动作；0 无,兼容以前; 1 只报告; 2 悬停; 3 返航;
        public int              nLimitAltitude;                       // 高度 单位：厘米。 0 无效
        public int              nAlarmAltitude;                       // 预警高度单位：厘米。 0 无效
        public int              unLimitRadius;                        // 半径单位：厘米。0 无效
        public int              unAlarmRadius;                        // 预警半径：厘米。0 无效
        public byte[]           reserved = new byte[12];              // 保留
    }

    // 电子围栏版本号配置
    public static class NETDEV_ENCLOSURE_VERSION_CFG extends SdkStructure
    {
        public int              unType;                               // 围栏类型掩码,如LIMITSPEED | DRIVEALLOW
        public int[]            unVersion = new int[32];              // 每个类型一个版本号,用于统一平台与设备上的围栏配置
        public int              nReserved;                            // 保留
    }

    // 向视频输出口投放视频和图片文件, CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_DELIVERY_FILE 命令参数
    public static class NET_CTRL_DELIVERY_FILE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nPort;                                // 视频输出口
        public int              emPlayMode;                           // 播放类型, 参考  EM_VIDEO_PLAY_MODE_TYPE
        public NET_TIME         stuStartPlayTime;                     // 开始播放的时间
        public NET_TIME         stuStopPlayTime;                      // 结束播放的时间，emPlayMode为 EM_VIDEO_PLAY_MODE_TYPE_REPEAT 时，此值有效
        public int              nFileCount;                           // 投放的文件个数
        public NET_DELIVERY_FILE_INFO[] stuFileInfo = new NET_DELIVERY_FILE_INFO[MAX_DELIVERY_FILE_NUM]; // 投放的文件信息(推荐使用stuFileInfoEx，二者互斥)
        																								// 如果nFileCountEx不为0，视为使用stuFileInfoEx，stuFileInfo值无效
        public int              emOperateType;                        // 操作类型。如无该字段，默认为EM_VIDEO_PLAY_OPERATE_TYPE_REPLACE
        public int              nFileCountEx;                         // 投放的文件个数
        public NET_DELIVERY_FILE_INFOEX[] stuFileInfoEx = new NET_DELIVERY_FILE_INFOEX[MAX_DELIVERY_FILE_NUM]; // 投放的文件信息
        public int              nNumber;                              // 当前广告计划编号,调用者可以通过此编号来设置不同广告计划
        public NET_CFG_TIME_SCHEDULE stuTimeSection;                  // 播放时间段
        public int              bEnable;                              // 播放使能
        public byte[]           szName = new byte[128];               // 广告名称

        public NET_CTRL_DELIVERY_FILE() {
            this.dwSize = this.size();

            for(int i = 0; i < MAX_DELIVERY_FILE_NUM; i++) {
                stuFileInfo[i] = new NET_DELIVERY_FILE_INFO();
            }
            for(int i = 0; i < MAX_DELIVERY_FILE_NUM; i++) {
                stuFileInfoEx[i] = new NET_DELIVERY_FILE_INFOEX();
            }
        }
    }

    public static class NET_DELIVERY_FILE_INFOEX extends SdkStructure
    {
        public int              emFileType;                           // 文件类型, 参考 EM_DELIVERY_FILE_TYPE
        public byte[]           szFileURL = new byte[DELIVERY_FILE_URL_LEN]; // 文件的资源地址
        public int              nImageSustain;                        // 每张图片停留多长时间，单位秒 (emFileType为 EM_DELIVERY_FILE_TYPE_IMAGE 时此字段有效)
        public int              emPlayWithMode;                       // 文件所属的模式, 参考 EM_PLAY_WITH_MODE
        public byte[]           szFileURLEx = new byte[512];          // NET_OUT_GET_ADVERTIMENT_CAPS中bSupport为true时填写此字段，并且szFileURL填空
        public NET_CFG_TIME_SCHEDULE stuTimeSection = new NET_CFG_TIME_SCHEDULE(); //播放时间段
        public int              nSize;                                // 文件大小
        public int              nID;                                  // 文件编号
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 投放文件信息
    public static class NET_DELIVERY_FILE_INFO extends SdkStructure
    {
        public int              emFileType;                           // 文件类型, 参考 EM_DELIVERY_FILE_TYPE
        public byte[]           szFileURL = new byte[DELIVERY_FILE_URL_LEN]; // 文件的资源地址
        public int              nImageSustain;                        // 每张图片停留多长时间，单位秒 (emFileType为 EM_DELIVERY_FILE_TYPE_IMAGE 时此字段有效)
        public int              emPlayWithMode;                       // 文件所属的模式, 参考 EM_PLAY_WITH_MODE
        public byte[]           byReserved = new byte[1020];          // 保留字节
    }

    // 视频播放模式
    public static class EM_VIDEO_PLAY_MODE_TYPE extends SdkStructure
    {
        public static final int   EM_VIDEO_PLAY_MODE_TYPE_UNKNOWN = 0;  // 未知
        public static final int   EM_VIDEO_PLAY_MODE_TYPE_ONCE = 1;     // 播放一次
        public static final int   EM_VIDEO_PLAY_MODE_TYPE_REPEAT = 2;   // 循环播放
    }

    // 投放的文件类型
    public static class EM_DELIVERY_FILE_TYPE extends SdkStructure
    {
        public static final int   EM_DELIVERY_FILE_TYPE_UNKNOWN = 0;    // 未知
        public static final int   EM_DELIVERY_FILE_TYPE_VIDEO = 1;      // 视频
        public static final int   EM_DELIVERY_FILE_TYPE_IMAGE = 2;      // 图片
        public static final int   EM_DELIVERY_FILE_TYPE_AUDIO = 3;      // 音频
    }

    // CLIENT_ControlDevice接口的 CTRLTYPE_CTRL_START_PLAYAUDIO 命令参数
    public static class NET_CTRL_START_PLAYAUDIO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szAudioPath = new byte[NET_MAX_AUDIO_PATH];

        public NET_CTRL_START_PLAYAUDIO() {
            this.dwSize = this.size();
        }
    }

    // 公告记录信息查询条件
    public static class FIND_RECORD_ANNOUNCEMENT_CONDITION extends SdkStructure
    {
        public int              dwSize;
        public int              bTimeEnable;                          // 启用时间段查询, BOOL类型
        public NET_TIME         stStartTime;                          // 起始时间
        public NET_TIME         stEndTime;                            // 结束时间

        public FIND_RECORD_ANNOUNCEMENT_CONDITION() {
            this.dwSize = this.size();
        }
    }

    //公告记录信息
    public static class NET_RECORD_ANNOUNCEMENT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nRecNo;                               // 记录集编号,只读
        public NET_TIME         stuCreateTime;                        // 创建时间
        public NET_TIME         stuIssueTime;                         // 公告发布时间
        public byte[]           szAnnounceTitle = new byte[NET_COMMON_STRING_64]; // 公告标题
        public byte[]           szAnnounceContent = new byte[NET_COMMON_STRING_256]; //公告内容
        public byte[]           szAnnounceDoor = new byte[NET_COMMON_STRING_16]; //公告要发送的房间号
        public NET_TIME         stuExpireTime;                        //公告过期的时间
        public int              emAnnounceState;                      //公告的状态 , 参考 NET_ANNOUNCE_STATE
        public int              emAnnounceReadFlag;                   //公告是否已经浏览, 参考 NET_ANNOUNCE_READFLAG
        public int              nBackgroundPicture;                   // 门禁公告可以选择背景图片(具体图片由色设备绑定)： 0 : 图片1 , 1：图片2 , 2 ： 图片3
        public int              bUseEx;                               // 是否使用szAnnounceTitleEx/szAnnounceContentEx字段
        public byte[]           szAnnounceTitleEx = new byte[256];    // 公告标题
        public byte[]           szAnnounceContentEx = new byte[1024]; // 公告内容
        public NET_TIME_EX      stuCreateTimeRealUTC = new NET_TIME_EX(); // 真UTC创建时间
        public NET_TIME         stuIssueTimeRealUTC = new NET_TIME(); // 真UTC公告发布时间
        public NET_TIME         stuExpirTimeRealUTC = new NET_TIME(); // 真UTC公告过期的时间
        public byte[]           szType = new byte[16];                //公告类型

        public NET_RECORD_ANNOUNCEMENT_INFO() {
            this.dwSize = this.size();
        }
    }

    //公告的状态
    public static class NET_ANNOUNCE_STATE extends SdkStructure
    {
        public static final int   NET_ANNOUNCE_STATE_UNSENDED = 0;      //初始状态(未发送)
        public static final int   NET_ANNOUNCE_STATE_SENDED = 1;        //已经发送
        public static final int   NET_ANNOUNCE_STATE_EXPIRED = 2;       //已经过期
        public static final int   NET_ANNOUNCE_STATE_UNKNOWN = 3;       //未知
    }

    //公告是否已经浏览
    public static class NET_ANNOUNCE_READFLAG extends SdkStructure
    {
        public static final int   NET_ANNOUNCE_READFLAG_UNREADED = 0;   //未读
        public static final int   NET_ANNOUNCE_READFLAG_READED = 1;     //已读
        public static final int   NET_ANNOUNCE_READFLAG_UNKNOWN = 2;    //未知
    }

    // 开始实时预览并指定回调数据格式入参
    public static class NET_IN_REALPLAY_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道编号
        public Pointer          hWnd;                                 // 窗口句柄, HWND类型
        public int              rType;                                // 码流类型 ，参考  NET_RealPlayType
        public fRealDataCallBackEx cbRealData;                        // 数据回调函数
        public int              emDataType;                           // 回调的数据类型，参考 EM_REAL_DATA_TYPE
        public Pointer          dwUser;                               // 用户数据
        public String           szSaveFileName;                       // 转换后的文件名
        public fRealDataCallBackEx2 cbRealDataEx;                     // 数据回调函数-扩展
        public int              emAudioType;                          // 音频格式,对应枚举EM_AUDIO_DATA_TYPE
        public fDataCallBackEx  cbRealDataEx2;                        // 数据回调（扩展带时间戳，帧类型）,使用fDataCallBackEx
        public int              nMP4Type;                             //0表示默认，使用写文件方式获取mp4流；1表示使用回调获取mp4流

        public NET_IN_REALPLAY_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 开始实时预览并指定回调数据格式出参
    public static class NET_OUT_REALPLAY_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_REALPLAY_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 实时预览回调数据类型
    public static class EM_REAL_DATA_TYPE extends SdkStructure
    {
        public static final int   EM_REAL_DATA_TYPE_PRIVATE = 0;        // 私有码流
        public static final int   EM_REAL_DATA_TYPE_GBPS = 1;           // 国标PS码流
        public static final int   EM_REAL_DATA_TYPE_TS = 2;             // TS码流
        public static final int   EM_REAL_DATA_TYPE_MP4 = 3;            // MP4文件(从回调函数出来的是私有码流数据,参数dwDataType值为0)
        public static final int   EM_REAL_DATA_TYPE_H264 = 4;           // 裸H264码流
        public static final int   EM_REAL_DATA_TYPE_FLV_STREAM = 5;     // 流式FLV
    }

    // 开始回放并指定回调数据格式 入参
    public static class NET_IN_PLAYBACK_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道编号
        public NET_TIME         stStartTime;                          // 开始时间
        public NET_TIME         stStopTime;                           // 结束时间
        public Pointer          hWnd;                                 // 播放窗格, 可为NULL
        public Callback         cbDownLoadPos;                        // 进度回调
        public Pointer          dwPosUser;                            // 进度回调用户信息
        public Callback         fDownLoadDataCallBack;                // 数据回调
        public int              emDataType;                           // 回调的数据类型
        public Pointer          dwDataUser;                           // 数据回调用户信息
        public int              nPlayDirection;                       // 播放方向, 0:正放; 1:倒放;
        public int              emAudioType;                          // 音频类型,EM_AUDIO_DATA_TYPE
        public Callback         fDownLoadDataCallBackEx;              // 数据回调（扩展带时间戳，帧类型）,使用fDataCallBackEx

        public NET_IN_PLAYBACK_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 开始回放并指定回调数据格式 出参
    public static class NET_OUT_PLAYBACK_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_PLAYBACK_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 开始下载并指定回调数据格式 入参
    public static class NET_IN_DOWNLOAD_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道编号
        public int              emRecordType;                         // 录像类型, 详见EM_QUERY_RECORD_TYPE
        public String           szSavedFileName;                      // 下载的文件路径
        public NET_TIME         stStartTime;                          // 开始时间
        public NET_TIME         stStopTime;                           // 结束时间
        public Callback         cbDownLoadPos;                        // 进度回调
        public Pointer          dwPosUser;                            // 进度回调用户信息
        public Callback         fDownLoadDataCallBack;                // 数据回调
        public int              emDataType;                           // 回调的数据类型,详见 EM_REAL_DATA_TYPE
        public Pointer          dwDataUser;                           // 数据回调用户信息
        public int              emAudioType;                          // 音频类型,对应枚举EM_AUDIO_DATA_TYPE

        public NET_IN_DOWNLOAD_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 开始下载并指定回调数据格式 出参
    public static class NET_OUT_DOWNLOAD_BY_DATA_TYPE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_DOWNLOAD_BY_DATA_TYPE() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 NET_ALARM_HIGH_SPEED (车辆超速报警事件)对应的数据块描述信息
    public static class ALARM_HIGH_SPEED_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_GPS_STATUS_INFO stGPSStatusInfo;                   // GPS信息
        public int              nSpeedLimit;                          // 车连限速值km/h
        public int              nCurSpeed;                            // 当前车辆速度km/h
        public int              nMaxSpeed;                            // 最高速度Km/h
        public byte[]           byReserved = new byte[508];           // 保留字节
    }

    // GPS状态信息
    public static class NET_GPS_STATUS_INFO extends SdkStructure
    {
        public NET_TIME         revTime = new NET_TIME();             // 定位时间
        public byte[]           DvrSerial = new byte[50];             // 设备序列号
        public byte[]           byRserved1 = new byte[6];             // 对齐字节
        public double           longitude;                            // 经度(单位是百万分之度,范围0-360度)
        public double           latidude;                             // 纬度(单位是百万分之度,范围0-180度)
        public double           height;                               // 高度(米)
        public double           angle;                                // 方向角(正北方向为原点,顺时针为正)
        public double           speed;                                // 速度(单位km/H)
        public short            starCount;                            // 定位星数, emDateSource为 EM_DATE_SOURCE_GPS时有效
        public byte[]           byRserved2 = new byte[2];             // 对齐字节
        public int              antennaState;                         // 天线状态, 参考  NET_THREE_STATUS_BOOL, emDateSource为 EM_DATE_SOURCE_GPS时有效
        public int              orientationState;                     // 定位状态, 参考  NET_THREE_STATUS_BOOL
        public int              workStae;                             // 工作状态(0=未定位,1=非差分定位,2=差分定位,3=无效PPS,6=正在估算
        // emDateSource为 EM_DATE_SOURCE_GPS时有效
        public int              nAlarmCount;                          // 发生的报警位置个数
        public int[]            nAlarmState = new int[128];           // 发生的报警位置,值可能多个, emDateSource为 EM_DATE_SOURCE_GPS时有效
        public byte             bOffline;                             // 0-实时 1-补传
        public byte             bSNR;                                 // GPS信噪比,表示GPS信号强度,值越大,信号越强 范围：0~100,0表示不可用
        public byte[]           byRserved3 = new byte[2];             // 对齐字节
        public int              emDateSource;                         // 数据来源, 参考 EM_DATE_SOURCE
        public int              nSignalStrength;                      // 在当前工作模式下（GPS或北斗等系统）的信号强度
        public float            fHdop;                                // 水平精度因子惯性导航时无效
        public float            fPdop;                                // 位置精度因子,惯性导航时无效
        public int              nMileage;                             //总里程， 单位 米
        public byte[]           byRserved = new byte[96];             // 保留字节
    }

    //三态布尔类型
    public static class NET_THREE_STATUS_BOOL extends SdkStructure
    {
        public static final int   BOOL_STATUS_FALSE = 0;
        public static final int   BOOL_STATUS_TRUE = 1;
        public static final int   BOOL_STATUS_UNKNOWN = 2;              //未知
    }

    // 数据来源
    public static class EM_DATE_SOURCE extends SdkStructure
    {
        public static final int   EM_DATE_SOURCE_GPS = 0;               // GPS
        public static final int   EM_DATE_SOURCE_INERTIALNAVIGATION = 1; // 惯性导航数据
    }

    // Gps定位信息
    public static class NET_GPS_LOCATION_INFO extends SdkStructure
    {
        public GPS_Info         stuGpsInfo;                           // GPS信息
        public ALARM_STATE_INFO stuAlarmStateInfo;                    // 报警状态信息
        public int              nTemperature;                         // 温度(单位:0.1摄氏度)
        public int              nHumidity;                            // 湿度(单位:0.1%)
        public int              nIdleTime;                            // 怠速时长(单位:秒)
        public int              nMileage;                             // 里程(单位:0.1km)
        public int              nVoltage;                             // 设置电压值(单位:0.1伏)
        public byte             bOffline;                             // 0-实时 1-补传
        public byte[]           byReserved = new byte[1023];
    }

    // 事件类型 NET_ALARM_VIDEO_LOSS (视频丢失事件)对应的数据块描述信息
    public static class ALARM_VIDEO_LOSS_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public int              nChannelID;                           // 通道号
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public NET_TIME_EX      stuStartTime;                         // 开始时间,nAction为2时上报此字段
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息
    	/**
    	 * 采集源分辨率 {@link EM_NET_VIFORMAT_TYPE}
    	 */
        public int              emViFormat;
    	/**
    	 * 事件公共扩展字段结构体
    	 */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
        public byte[]           byReserved = new byte[1020];          // 保留字节
    }

    //报警事件类型 NET_ALARM_BUS_SHARP_ACCELERATE(车辆急加速事件)对应的数据描述信息
    public static class ALARM_BUS_SHARP_ACCELERATE_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              nAction;                              //事件动作, 1:开始 2:停止
        public int              bRealUTC;                             //stuRealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则用 stuTime 字段
        public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();       //事件发生的时间(标准UTC时间),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}

        public ALARM_BUS_SHARP_ACCELERATE_INFO() {
            this.dwSize = this.size();
        }
    }

    //报警事件类型 NET_ALARM_BUS_SHARP_DECELERATE(车辆急减速事件)对应的数据描述信息
    public static class ALARM_BUS_SHARP_DECELERATE_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              nAction;                              //事件动作, 1:开始 2:停止
        public int              bRealUTC;                             //stuRealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则用 stuTime 字段
        public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();       //事件发生的时间(标准UTC时间),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}

        public ALARM_BUS_SHARP_DECELERATE_INFO() {
            this.dwSize = this.size();
        }
    }

    // GPS未定位报警(NET_ALARM_GPS_NOT_ALIGNED)
    public static class ALARM_GPS_NOT_ALIGNED_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,0表示脉冲事件,1表示报警开始,2表示报警结束;
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 前端断网报警信息, 对应  NET_ALARM_FRONTDISCONNECT
    public static class ALARM_FRONTDISCONNET_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:开始 1:停止
        public NET_TIME         stuTime;                              // 事件发生时间
        public byte[]           szIpAddress = new byte[MAX_PATH];     // 前端IPC的IP地址
        public NET_GPS_STATUS_INFO stGPSStatus;                       // GPS信息 功能，仅部分设备有效。报警类型为DH_ALARM_FRONTDISCONNECT时,此字段无效。
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_FRONTDISCONNET_INFO() {
            this.dwSize = this.size();
        }
    }

    // 存储错误报警, 对应  NET_ALARM_STORAGE_FAILURE_EX
    public static class ALARM_STORAGE_FAILURE_EX extends SdkStructure
    {
        public int              dwSize;
        public int              nAction;                              // 0:开始 1:停止
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           szDevice = new byte[NET_STORAGE_NAME_LEN]; // 存储设备名称
        public byte[]           szGroup = new byte[NET_STORAGE_NAME_LEN]; // 存储组名称
        public byte[]           szPath = new byte[MAX_PATH];          // 路径
        public int              emError;                              // 错误类型, 参考   EM_STORAGE_ERROR
        public int              nPhysicNo;                            // 硬盘所在槽编码, 从1开始
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public NET_GPS_STATUS_INFO stGPSStatus;                       // GPS信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public ALARM_STORAGE_FAILURE_EX() {
            this.dwSize = this.size();
        }
    }

    // 存储组不存在事件信息, 对应  NET_ALARM_STORAGE_NOT_EXIST
    public static class ALARM_STORAGE_NOT_EXIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nAction;                              // 0:开始 1:停止
        public byte[]           szGroup = new byte[NET_STORAGE_NAME_LEN]; // 在录像或抓图存储点中设置但不存在的组
        public NET_TIME         stuTime;                              // 事件触发时间
        /**
         * gps信息
         */
        public NET_GPS_STATUS_INFO stGPSStatus;
        public int              bRealUTC;                             //stuRealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则用 stuTime 字段
        public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();       //事件发生的时间(标准UTC时间),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}

        public ALARM_STORAGE_NOT_EXIST_INFO() {
            this.dwSize = this.size();
        }
    }

    // 车辆ACC报警事件, 对应事件类型  NET_ALARM_VEHICLE_ACC
    public static class ALARM_VEHICLE_ACC_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nACCStatus;                           // ACC状态, 0:无效, 1:开启, 2:关闭
        public int              nAction;                              // 事件动作, 0:Start, 1:Stop
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public int              nConstantElectricStatus;              // 常电状态, 0:未知, 1:连接, 2:断开
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              nTotalMileage;                        // 总的里程数,单位:米,当nACCStatus为2时,将ntotalMileage进行上报
        public NET_TIME_EX      stuStartTime;                         // nACCStatus为1时刻的时间
        public NET_GPS_STATUS_INFO stuStartGPS;                       // nACCStatus为1时刻的GPS信息
        public int              bRealUTC;                             //stuRealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则用 stuTime 字段
        public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();       //事件发生的时间(标准UTC时间),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}

        public ALARM_VEHICLE_ACC_INFO() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 NET_ALARM_VIDEOBLIND(视频遮挡事件)对应的数据块描述信息
    public static class ALARM_VIDEO_BLIND_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public int              nChannelID;                           // 通道号
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_TIME_EX      stuStartTime;                         // 开始时间,nAction为2时上报此字段
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 紧急事件(对应 NET_URGENCY_ALARM_EX2, 对原有的 NET_URGENCY_ALARM_EX 类型的升级, 指人为触发的紧急事件, 一般处理是联动外部通讯功能请求帮助)
    public static class ALARM_URGENCY_ALARM_EX2 extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stuTime;                              // 事件产生的时间
        public int              nID;                                  // 用于标识不同的紧急事件
        /** 0:脉冲*/
        public			int            nAction;
        /** GPS信息*/
        public NET_GPS_STATUS_INFO stuGPSStatusInfo = new NET_GPS_STATUS_INFO();
        /** 国际移动设备辨识码15位数字标识*/
        public			byte[]         szImei = new byte[16];
        /** 距离上次上报的GPS移动距离, 单位0.1Km*/
        public			int            nDistance;
        /** 车牌号码*/
        public			byte[]         szPlateNumber = new byte[64];
        /** 应答号码*/
        public			byte[]         szReplyNumber = new byte[16];
        /** 线路信息*/
        public			byte[]         szLine = new byte[64];

        public ALARM_URGENCY_ALARM_EX2() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 NET_ALARM_DRIVER_NOTCONFIRM (司机未按确认按钮报警事件)对应的数据块描述信息
    public static class ALARM_DRIVER_NOTCONFIRM_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 交通拥塞报警
    public static class ALARM_TRAF_CONGESTION_INFO extends SdkStructure
    {
        public int              nDriveWayID;                          // 车道号
        public int              nCongestionLevel;                     // 交通拥塞等级:1,2,3,4,5,6;1级最严重
        public NET_TIME         stuTime;                              // 报警发生时间
        public int              nState;                               // 设备状态,0表示故障恢复,1表示发生故障
        public int              dwChannel;                            // 报警的通道号
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           reserve = new byte[28];
    }

    // RAID异常信息
    public static class ALARM_RAID_INFO extends SdkStructure
    {
        public int              nRaidNumber;                          // 上报的RAID个数
        public RAID_STATE_INFO[] stuRaidInfo = new RAID_STATE_INFO[NET_MAX_RAID_NUM]; // 异常的RAID信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           reserved = new byte[128];

        public ALARM_RAID_INFO() {
            for (int i = 0; i < stuRaidInfo.length; ++i) {
                stuRaidInfo[i] = new RAID_STATE_INFO();
            }
        }
    }

    public static class RAID_STATE_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[16];                // Raid名称
        public byte             byType;                               // 类型 1:Jbod     2:Raid0      3:Raid1     4:Raid5
        public byte             byStatus;                             // 状态 0:Error ，1:Active，2:Degraded，3:Inactive，4:Resyncing
        public byte[]           byReserved = new byte[2];
        public int              nCntMem;                              // nMember数组的有效数据个数
        public int[]            nMember = new int[32];                // 1,2,3,... 组成磁盘通道,是个数组
        public int              nCapacity;                            // 容量,单位G
        public int              nRemainSpace;                         // 剩余容量,单位M
        public int              nTank;                                // 扩展柜 0:主机,1:扩展柜1,2:扩展柜2,……
        public byte[]           reserved = new byte[32];
    }

    // 流量统计报警通道信息
    public static class ALARM_TRAFFIC_FLUX_LANE_INFO extends SdkStructure
    {
        public NET_TIME         stuCurTime;                           // 当前时间
        public int              nLane;                                // 车道号
        public int              nState;                               // 状态值：1-表示拥堵, 2-表示拥堵恢复, 3-表示正常, 4-表示中断, 5-表示中断恢复
        public int              nFlow;                                // 流量值,单位：辆/分
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[124];           // 保留
    }

    // CLIENT_AttachBusState, 订阅Bus状态输入参结构
    public static class NET_IN_BUS_ATTACH extends SdkStructure
    {
        public int              dwSize;
        public fBusStateCallBack cbBusState;                          // 状态回调函数
        public Pointer          dwUser;                               // 用户数据

        public NET_IN_BUS_ATTACH() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_AttachBusState, 订阅Bus状态输出参结构
    public static class NET_OUT_BUS_ATTACH extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_BUS_ATTACH() {
            this.dwSize = this.size();
        }
    }

    // 事件类型 NET_ALARM_BUS_PASSENGER_CARD_CHECK (乘客刷卡事件)对应的数据描述信息
    public static class ALARM_PASSENGER_CARD_CHECK extends SdkStructure
    {
        public int              bEventConfirm;                        // 是否需要回复, BOOL类型
        public byte[]           szCardNum = new byte[NET_MAX_BUSCARD_NUM]; // 公交卡号
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息
        public NET_TIME_EX      UTC;                                  // 刷卡时间
        public int              nTime;                                // UTC整型
        public int              emType;                               // 刷卡类型, 参考  EM_PASSENGER_CARD_CHECK_TYPE
        public byte[]           szMac = new byte[NET_MAX_POS_MAC_NUM]; // 刷卡机Mac码 (默认"0000",兼容老设备)
        public byte[]           reserved = new byte[1012];            // 预留
    }

    public static class EM_PASSENGER_CARD_CHECK_TYPE extends SdkStructure
    {
        public static final int   EM_PASSENGER_CARD_CHECK_TYPE_UNKOWN = 0; // 未知
        public static final int   EM_PASSENGER_CARD_CHECK_TYPE_SIGNIN = 1; // 签到/上车
        public static final int   EM_PASSENGER_CARD_CHECK_TYPE_SIGNOUT = 2; // 签出/下车
        public static final int   EM_PASSENGER_CARD_CHECK_TYPE_NORMAL = 3; // 正常刷卡，不区分上下车
    }

    // CLIENT_AttachEventRestore 接口输入参数
    public static class NET_IN_ATTACH_EVENT_RESTORE extends SdkStructure
    {
        public int              dwSize;                               //结构体大小
        public byte[]           szUuid = new byte[MAX_EVENT_RESTORE_UUID]; //客户端惟一标识
        public BYTE_ARRAY_32[]  szCodes = new BYTE_ARRAY_32[64];      //需要订阅的事件码列表，一次可订阅多个事件
        public int              nCodesNum;                            //需要订阅的事件码数量

        public NET_IN_ATTACH_EVENT_RESTORE() {
            this.dwSize = this.size();
        }
    }

    public static class GPS_TEMP_HUMIDITY_INFO extends SdkStructure
    {
        public double           dTemperature;                         // 温度值(摄氏度),实际值的1000倍,如30.0摄氏度表示为30000
        public double           dHumidity;                            // 湿度值(%),实际值的1000倍,如30.0%表示为30000
        public byte[]           bReserved = new byte[128];            // 保留字节

        public static class ByValue extends GPS_Info implements SdkStructure.ByValue { }
    }

    // 事件类型 NET_ALARM_FACEINFO_COLLECT (人脸信息录入事件)对应的数据块描述信息
    public static class ALARM_FACEINFO_COLLECT_INFO extends SdkStructure
    {
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 人脸信息记录操作类型, 接口  CLIENT_FaceInfoOpreate
    public static class EM_FACEINFO_OPREATE_TYPE extends SdkStructure
    {
        public static final int   EM_FACEINFO_OPREATE_ADD = 0;          // 添加, pInbuf = NET_IN_ADD_FACE_INFO , pOutBuf = NET_OUT_ADD_FACE_INFO
        public static final int   EM_FACEINFO_OPREATE_GET = 1;          // 获取, pInBuf = NET_IN_GET_FACE_INFO , pOutBuf = NET_OUT_GET_FACE_INFO
        public static final int   EM_FACEINFO_OPREATE_UPDATE = 2;       // 更新, pInbuf = NET_IN_UPDATE_FACE_INFO , pOutBuf = NET_OUT_UPDATE_FACE_INFO
        public static final int   EM_FACEINFO_OPREATE_REMOVE = 3;       // 删除, pInbuf = NET_IN_REMOVE_FACE_INFO , pOutBuf = NET_OUT_REMOVE_FACE_INFO
        public static final int   EM_FACEINFO_OPREATE_CLEAR = 4;        // 清除, pInbuf = NET_IN_CLEAR_FACE_INFO, pOutBuf = NET_OUT_CLEAR_FACE_INFO
        public static final int   EM_FACEINFO_OPREATE_GETFACEEIGEN = 5; // 获取人脸特征值, pInbuf = NET_IN_GETFACEEIGEN_INFO, pOutBuf = NET_OUT_GETFACEEIGEN_INFO
    }

    // 添加人脸记录信息输入参数
    public static class NET_IN_ADD_FACE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public NET_FACE_RECORD_INFO stuFaceInfo;                      // 人脸数据

        public NET_IN_ADD_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 人脸信息
    public static class NET_FACE_RECORD_INFO extends SdkStructure
    {
        public byte[]           szUserName = new byte[MAX_USER_NAME_LEN]; // 用户名
        public int              nRoom;                                // 房间个数
        public NET_FACE_ROOMNO[] szRoomNoArr = (NET_FACE_ROOMNO[])new NET_FACE_ROOMNO().toArray(MAX_ROOMNUM_COUNT); // 房间号列表
        public int              nFaceData;                            // 人脸模板数据个数
        public NET_FACE_FACEDATA[] szFaceDataArr = (NET_FACE_FACEDATA[])new NET_FACE_FACEDATA().toArray(MAX_FACE_COUTN); // 人脸模板数据
        public int[]            nFaceDataLen = new int[MAX_FACE_COUTN]; // 人脸模版数据大小
        public int              nFacePhoto;                           // 人脸照片个数
        public int[]            nFacePhotoLen = new int[MAX_PHOTO_COUNT]; // 每张图片的大小
        public FACE_PHOTO[]     pszFacePhotoArr = (FACE_PHOTO[])new FACE_PHOTO().toArray(MAX_PHOTO_COUNT); // 人脸照片数据,大小不超过120K
        public int              bValidDate;                           //是否设置人脸有效时间
        public NET_TIME         stuValidDateStart;                    //人脸有效开始时间
        public NET_TIME         stuValidDateEnd;                      //人脸有效结束时间
        public int              nValidCounts;                         // 刷脸有效次数：小于0表示不限次数， 等于0刷脸次数已用完
        public int              bValidCountsEnable;                   // 次数字段使能
        public int              bFaceDataExEnable;                    // 人脸模板数据扩展使能
        public Pointer[]        pszFaceDataEx = new Pointer[MAX_FACE_COUTN]; // 人脸模板数据扩展, 由用户申请释放, 每张照片最大为8K
        //public FACE_PHOTO_EX[]      pszFaceDataExArr = (FACE_PHOTO_EX[])new FACE_PHOTO_EX().toArray(MAX_FACE_COUTN);  // 人脸模板数据扩展, 由用户申请释放, 每张照片最大为8K
        public byte[]           byReserved = new byte[240];           // 保留字节
    }

    public static class NET_FACE_ROOMNO extends SdkStructure {
        public byte[]           szRoomNo = new byte[NET_COMMON_STRING_16]; // 房间号
    }

    public static class NET_FACE_FACEDATA extends SdkStructure {
        public byte[]           szFaceData = new byte[MAX_FACE_DATA_LEN]; // 人脸数据
    }

    public static class FACE_PHOTO extends SdkStructure {
        public Pointer          pszFacePhoto;
    }

	/*public static class FACE_PHOTO_EX extends SdkStructure {
		public Pointer pszFaceDataEx;
	}*/
    // 添加人脸记录信息输出参数
    public static class NET_OUT_ADD_FACE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_ADD_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取人脸记录信息输入参数
    public static class NET_IN_GET_FACE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID

        public NET_IN_GET_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 获取人脸记录信息输出参数
    public static class NET_OUT_GET_FACE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nFaceData;                            // 人脸模板数据个数
        public NET_FACE_FACEDATA[] szFaceDataArr = (NET_FACE_FACEDATA[])new NET_FACE_FACEDATA().toArray(MAX_FACE_COUTN); // 人脸模板数据
        public int              nPhotoData;                           // 白光人脸照片数据个数, 最大个数：5
        public int[]            nInPhotoDataLen = new int[MAX_PHOTO_COUNT]; // 用户申请的每张白光人脸照片大小
        public int[]            nOutPhotoDataLen = new int[MAX_PHOTO_COUNT]; // 每张白光人脸照片实际的大小
        public Pointer[]        pPhotoData = new Pointer[MAX_PHOTO_COUNT]; // 白光人脸照片数据, 由用户申请释放, 每张照片最大为200K

        public NET_OUT_GET_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 更新人脸记录信息输入参数
    public static class NET_IN_UPDATE_FACE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public NET_FACE_RECORD_INFO stuFaceInfo;                      // 人脸数据

        public NET_IN_UPDATE_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 更新人脸记录信息输出参数
    public static class NET_OUT_UPDATE_FACE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_UPDATE_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 删除人脸记录信息输入参数
    public static class NET_IN_REMOVE_FACE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID

        public NET_IN_REMOVE_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 删除人脸记录信息输出参数
    public static class NET_OUT_REMOVE_FACE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_REMOVE_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 清除人脸记录信息输入参数
    public static class NET_IN_CLEAR_FACE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_CLEAR_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 清除人脸记录信息输出参数
    public static class NET_OUT_CLEAR_FACE_INFO extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_CLEAR_FACE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 添加节目信息接口输入参数
    public static class NET_IN_ADD_ONE_PROGRAMME extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAMME_INFO stuProgrammeInfo;                   // 节目信息

        public NET_IN_ADD_ONE_PROGRAMME() {
            this.dwSize = this.size();
        }
    }

    // 添加节目信息接口输出参数
    public static class NET_OUT_ADD_ONE_PROGRAMME extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID

        public NET_OUT_ADD_ONE_PROGRAMME() {
            this.dwSize = this.size();
        }
    }

    // 诱导屏节目配置信息
    public static class NET_PROGRAMME_INFO extends SdkStructure
    {
        public byte[]           szProgrammeName = new byte[MAX_COMMON_STRING_64]; // 节目名称
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID，添加时无效，用于修改、删除
        public int              bEnable;                              // 节目是否启用,BOOL类型
        public NET_ORDINARY_INFO stuOrdinaryInfo = new NET_ORDINARY_INFO(); // 普通节目信息，此参数需要在库里new对象
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 诱导屏普通节目信息
    public static class NET_ORDINARY_INFO extends SdkStructure
    {
        public int              bTempletState;                        // 节目是否保存为模板, BOOL类型
        public byte[]           szDescription = new byte[MAX_COMMON_STRING_128]; // 节目描述信息
        public int              nWhnCount;                            // 诱导屏窗口个数
        public NET_GUIDESCREEN_WINDOW_INFO[] stuWindowsInfo = (NET_GUIDESCREEN_WINDOW_INFO[])new NET_GUIDESCREEN_WINDOW_INFO().toArray(MAX_WINDOWS_COUNT); // 诱导屏窗口信息
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 诱导屏窗口信息
    public static class NET_GUIDESCREEN_WINDOW_INFO extends SdkStructure
    {
        public byte[]           szWindowID = new byte[MAX_COMMON_STRING_64]; // 窗口ID
        public int              nVolume;                              // 窗口音量，相对整屏音量的百分比
        public NET_COLOR_RGBA   stuColor;                             // 窗口背景颜色
        public int              nDiaphaneity;                         // 窗口背景透明度0-100
        public int              emTourPeriodType;                     // 窗口轮训类型, 对应  EM_TOURPERIOD_TYPE
        public int              nTourPeriodTime;                      // 自定义轮训时间，单位秒, 轮训类型为自定义轮训时有效
        public int              bAutoPlay;                            // 预览自动播放,Video元素有效, BOOL类型
        public int              bLoopPlay;                            // 预览循环播放,Video元素有效, BOOL类型
        public int              nElementsCount;                       // 诱导屏窗口元素个数
        public Pointer          pstElementsBuf;                       // 诱导屏窗口元素信息缓存区, 根据类型对应不同的结构体
        // 填充多个元素信息, 每个元素信息内容为 NET_ELEMENT_COMMON_INFO + 元素类型对应的结构体
        public int              nBufLen;                              // 诱导屏窗口元素信息缓存区大小
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 窗口元素通有信息
    public static class NET_ELEMENT_COMMON_INFO extends SdkStructure
    {
        public int              emElementsType;                       // 窗口元素类型,对应枚举  EM_ELEMENTS_TYPE
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    //窗口轮训周期类型
    public static class EM_TOURPERIOD_TYPE extends SdkStructure
    {
        public static final int   EM_TOURPERIOD_UNKNOWN = 0;            // 未知
        public static final int   EM_TOURPERIOD_PROGRAMME = 1;          // 节目周期
        public static final int   EM_TOURPERIOD_PLAN = 2;               // 计划周期
        public static final int   EM_TOURPERIOD_CUSTOM = 3;             // 自定义周期
    }

    // 诱导屏窗口元素类型
    public static class EM_ELEMENTS_TYPE extends SdkStructure
    {
        public static final int   EM_ELEMENTS_UNKNOWN = 0;              // 未知
        public static final int   EM_ELEMENTS_VIDEO = 1;                // 视频元素, 对应 NET_VIDEO_ELEMENT_INFO
        public static final int   EM_ELEMENTS_PICTURE = 2;              // 图片元素, 对应 NET_PICTURE_ELEMENT_INFO
        public static final int   EM_ELEMENTS_TEXT = 3;                 // 文本元素, 对应 NET_TEXT_ELEMENT_INFO
        public static final int   EM_ELEMENTS_PLACEHOLDER = 4;          // 占位符元素, 对应 NET_PLACEHOLDER_ELEMENT_INFO
        public static final int   EM_ELEMENTS_CAPTURE = 5;              // 抓拍元素, 对应 NET_CAPTURE_ELEMENT_INFO
    }

    // 视频元素信息
    public static class NET_VIDEO_ELEMENT_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_COMMON_STRING_64]; // 素材自定义名称
        public int              bFillerState;                         // 是否垫片, BOOL类型
        public byte[]           szPath = new byte[MAX_COMMON_STRING_128]; // 文件地址
        public int              nPlayCount;                           // 播放次数
        public int              nNote;                                // 注释信息个数
        public NET_GUIDESCREEN_NOTE_INFO[] stuNoteInfo = (NET_GUIDESCREEN_NOTE_INFO[])new NET_GUIDESCREEN_NOTE_INFO().toArray(MAX_NOTE_COUNT); // 注释信息
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 诱导屏窗口元素注释信息
    public static class NET_GUIDESCREEN_NOTE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否有效, BOOL类型
        public NET_GUIDESCREEN_TEXT_INFO stuTextInfo;                 // 文字注释信息
        public NET_RECT         stuRect;                              // 文字注释的坐标
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 文本信息
    public static class NET_GUIDESCREEN_TEXT_INFO extends SdkStructure
    {
        public byte[]           szContent = new byte[MAX_ELEMENTTEXT_LENGTH]; // 文本内容
        public int              nFontSize;                            // 字体大小
        public NET_COLOR_RGBA   stuFontColor;                         // 字体颜色
        public byte[]           szFontStyle = new byte[MAX_COMMON_STRING_32]; // 字体类型
        public double           dbLineHeight;                         // 行高
        public int              emHoriAlign;                          // 水平对齐方向, 对应 EM_HORI_ALIGN_TYPE
        public int              emVertAlign;                          // 垂直对齐方向, 对应 EM_VERT_ALIGN_TYPE
        public int              nPlayTime;                            // 播放时间, 单位秒
        public int              nPlayCount;                           // 播放次数
        public int              nStayTime;                            // 停留间隔（切入切出的间隔时间）单位：s
        public int              emEnterStyle;                         // 切入风格, 对应  EM_PIC_STYLE_TYPE
        public int              emExitStyle;                          // 切出风格, 对应  EM_PIC_STYLE_TYPE
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 水平对齐类型
    public static class EM_HORI_ALIGN_TYPE extends SdkStructure
    {
        public static final int   EM_HORI_ALIGN_UNKNOWN = 0;            // 未知
        public static final int   EM_HORI_ALIGN_LEFT = 1;               // 左对齐
        public static final int   EM_HORI_ALIGN_CENTER = 2;             // 居中
        public static final int   EM_HORI_ALIGN_RIGHT = 3;              // 右对齐
    }

    // 垂直对齐类型
    public static class EM_VERT_ALIGN_TYPE extends SdkStructure
    {
        public static final int   EM_VERT_ALIGN_UNKNOWN = 0;            // 未知
        public static final int   EM_VERT_ALIGN_UP = 1;                 // 上对齐
        public static final int   EM_VERT_ALIGN_CENTER = 2;             // 居中
        public static final int   EM_VERT_ALIGN_DOWN = 3;               // 下对齐
    }

    // 切入(切出) 风格
    public static class EM_PIC_STYLE_TYPE extends SdkStructure
    {
        public static final int   EM_PIC_STYLE_UNKNOWN = 0;             // 未知
        public static final int   EM_PIC_STYLE_DEFAULT = 1;             // 默认
        public static final int   EM_PIC_STYLE_UP = 2;                  // 上移
        public static final int   EM_PIC_STYLE_DOWN = 3;                // 下移
        public static final int   EM_PIC_STYLE_LEFT = 4;                // 左移
        public static final int   EM_PIC_STYLE_RIGHT = 5;               // 右移
    }

    // 图片元素信息
    public static class NET_PICTURE_ELEMENT_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_COMMON_STRING_64]; // 素材自定义名称
        public int              bFillerState;                         // 是否垫片, BOOL类型
        public byte[]           szPath = new byte[MAX_COMMON_STRING_128]; // 图片文件地址
        public int              nPlayTime;                            // 播放时间, 单位秒
        public int              nPlayCount;                           // 播放次数
        public int              nDiaphaneity;                         // 透明度, 0-100
        public int              nStayTime;                            // 停留时间, 单位秒
        public int              emEnterStyle;                         // 切入风格, 对应  EM_PIC_STYLE_TYPE
        public int              emExitStyle;                          // 切出风格, 对应  EM_PIC_STYLE_TYPE
        public int              nNote;                                // 注释信息个数
        public NET_GUIDESCREEN_NOTE_INFO[] stuNoteInfo = (NET_GUIDESCREEN_NOTE_INFO[])new NET_GUIDESCREEN_NOTE_INFO().toArray(MAX_NOTE_COUNT); // 注释信息
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 诱导屏窗口文本元素信息
    public static class NET_TEXT_ELEMENT_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_COMMON_STRING_64]; // 素材自定义名称
        public int              bFillerState;                         // 是否垫片, 对应 BOOL类型
        public NET_GUIDESCREEN_TEXT_INFO stuElementsText;             // 文本元素信息
        public int              nNote;                                // 注释信息个数
        public NET_GUIDESCREEN_NOTE_INFO[] stuNoteInfo = (NET_GUIDESCREEN_NOTE_INFO[])new NET_GUIDESCREEN_NOTE_INFO().toArray(MAX_NOTE_COUNT); // 注释信息
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 诱导屏窗口占位符元素信息
    public static class NET_PLACEHOLDER_ELEMENT_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_COMMON_STRING_64]; // 素材自定义名称
        public int              bFillerState;                         // 是否垫片, BOOL类型
        public int              nNote;                                // 注释信息个数
        public NET_GUIDESCREEN_NOTE_INFO[] stuNoteInfo = (NET_GUIDESCREEN_NOTE_INFO[])new NET_GUIDESCREEN_NOTE_INFO().toArray(MAX_NOTE_COUNT); // 注释信息
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    public static class NET_CAPTURE_OSD_INFO extends SdkStructure
    {
        public  int             bEnable;                              // 叠加使能
        public  NET_COLOR_RGBA  stuFontColor;                         // 文字颜色
        public  NET_COLOR_RGBA  stuBackGroundColor;                   // 背景颜色
        public  int             nFontSize;                            // 字体大小
        public  byte[]          szContent = new byte[MAX_COMMON_STRING_512]; // OSD内容
        public  byte[]          byReserved = new byte[1024];          // 保留字节
    }

    // 抓拍元素信息
    public static class NET_CAPTURE_ELEMENT_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_COMMON_STRING_64]; // 素材自定义名称
        public int              bFillerState;                         // 是否垫片, BOOL类型
        public byte[]           szUserName = new byte[NET_USER_NAME_LEN_EX]; // 用户名
        public byte[]           szPassWord = new byte[NET_USER_PSW_LEN_EX]; // 密码
        public byte[]           szIP = new byte[NET_MAX_IPADDR_LEN_EX]; // IP地址
        public int              nPort;                                // 端口号
        public int              nChannel;                             // 通道号
        public int              emCaptureType;                        // 抓拍类型, 对应  EM_CAPTURE_TYPE
        public int              nPlayTime;                            // 播放时间, 单位秒
        public int              nNote;                                // 注释信息个数
        public NET_GUIDESCREEN_NOTE_INFO[] stuNoteInfo = (NET_GUIDESCREEN_NOTE_INFO[])new NET_GUIDESCREEN_NOTE_INFO().toArray(MAX_NOTE_COUNT); // 注释信息
        public Pointer          pstOsdInfo;                           // OSD叠加信息, 由用户申请和释放内存，若为NULL，则不下发(获取)OSD信息,对应结构体NET_CAPTURE_OSD_INFO
        public byte[]           byReserved = new byte[124];           // 保留字节
    }

    // 抓拍类型
    public static class EM_CAPTURE_TYPE extends SdkStructure
    {
        public static final int   EM_CAPTURE_UNKNOWN = 0;               // 未知
        public static final int   EM_CAPTURE_VIDEO = 1;                 // 视频
        public static final int   EM_CAPTURE_PICTURE = 2;               // 图片
    }

    // 设置诱导屏屏幕配置信息输入参数
    public static class NET_IN_SET_GUIDESCREEN_CFG extends SdkStructure
    {
        public int              dwSize;
        public int              nScreenCount;                         // 诱导屏属性配置信息个数, 值由用户指定
        // 诱导屏属性配置信息, 内存由用户维护, NET_GUIDESCREEN_ATTRIBUTE_INFO
        // 大小为nScreenCount 个 NET_GUIDESCREEN_ATTRIBUTE_INFO
        public Pointer          pstGuideScreenCfg;

        public NET_IN_SET_GUIDESCREEN_CFG() {
            this.dwSize = this.size();
        }
    }

    // 设置诱导屏屏幕配置信息输出参数
    public static class NET_OUT_SET_GUIDESCREEN_CFG extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_GUIDESCREEN_CFG() {
            this.dwSize = this.size();
        }
    }

    // 诱导屏属性配置信息
    public static class NET_GUIDESCREEN_ATTRIBUTE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szScreenID = new byte[MAX_COMMON_STRING_64]; // 屏幕ID
        public int              emStatus;                             // 显示屏开关状态    对应 EM_SCREEN_STATUS_TYPE
        public int              bIsForeverOpen;                       // 是否永久开屏, 0：开屏     1：关屏
        public int              nScreenTime;                          // 开关屏时间个数
        public NET_SCREEN_TIME_INFO[] stuScreenTime = (NET_SCREEN_TIME_INFO[])new NET_SCREEN_TIME_INFO().toArray(MAX_SCREENTIME_COUNT); // 开关屏时间数组
        public int              nBright;                              // 显示屏亮度, 1-100
        public int              nContrast;                            // 显示屏对比度, 1-100
        public int              nSaturation;                          // 显示屏饱和度, 1-100
        public int              nVolume;                              // 屏幕整体音量
        public int              nWidth;                               // 宽度
        public int              nHeight;                              // 高度
        public int              nWindowsCount;                        // 窗口个数
        public NET_GUIDESCREEN_WINDOW_RECT_INFO[] stuWindows = (NET_GUIDESCREEN_WINDOW_RECT_INFO[])new NET_GUIDESCREEN_WINDOW_RECT_INFO().toArray(MAX_WINDOWS_COUNT); // 窗口信息
        public NET_GUIDESCREEN_AUTO_BRIGHT stuAutoBright;             // 诱导屏自动调节屏幕亮度信息
        public byte[]           byReserved = new byte[512];           // 保留字节

        public NET_GUIDESCREEN_ATTRIBUTE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 诱导屏自动调节屏幕亮度信息
    public static class NET_GUIDESCREEN_AUTO_BRIGHT extends SdkStructure
    {
        public int              bEnable;                              // 是否使能自动亮度配置
        public int              nLightBright;                         // 白天的亮度
        public int              nDarkBright;                          // 夜间的亮度
        public NET_SUN_RISE_SET_TIME[] stuSunTime = (NET_SUN_RISE_SET_TIME[])new NET_SUN_RISE_SET_TIME().toArray(MAX_SUNTIME_COUNT); // 日出日落时间
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 诱导屏配置日出日落时间
    public static class NET_SUN_RISE_SET_TIME extends SdkStructure
    {
        public int              nSunrise;                             // 日出时间, 默认上午6 时
        public int              nSunset;                              // 日落时间, 默认下午18 时
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    //开关屏时间信息
    public static class NET_SCREEN_TIME_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否启用, BOOL类型
        public int              emDateType;                           // 开关屏日期类型  对应 EM_SCREEN_DATE_TYPE
        public int              nDateCount;                           // 开关屏日期个数'
        public int[]            nPlayDates = new int[MAX_PLAYDATES_COUNT]; // 开关屏日期
        public NET_PROGRAMME_TIME_INFO stuOpenTime;                   // 开屏时间
        public NET_PROGRAMME_TIME_INFO stuCloseTime;                  // 关屏时间
        public byte[]           byReserved = new byte[128];           // 保留
    }

    // 诱导屏窗口坐标信息
    public static class NET_GUIDESCREEN_WINDOW_RECT_INFO extends SdkStructure
    {
        public byte[]           szWindowID = new byte[MAX_COMMON_STRING_64]; // 窗口ID
        public NET_RECT         stuRect;                              // 窗口坐标
        public int              nWindowBright;                        // 诱导屏窗口亮度，单独配置某个子屏的亮度，填0时以整屏亮度为准
        public byte[]           byReserved = new byte[132];           // 保留
    }

    // 节目时间信息
    public static class NET_PROGRAMME_TIME_INFO extends SdkStructure
    {
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒

        public String toString() {
            return dwHour + ":" + dwMinute + "：" + dwSecond;
        }
    }

    // 显示屏开关状态（枚举转结构体）
    public static class EM_SCREEN_STATUS_TYPE extends SdkStructure
    {
        public static final int   EM_SCREEN_STATUS_UNKNOWN = 0;         // 未知
        public static final int   EM_SCREEN_STATUS_ON = 1;              // 开
        public static final int   EM_SCREEN_STATUS_OFF = 2;             // 关
    }

    // 日期类型枚（枚举转结构体）
    public static class EM_SCREEN_DATE_TYPE extends SdkStructure
    {
        public static final int   EM_SCREEN_DATE_UNKNOWN = 0;           // 未知
        public static final int   EM_SCREEN_DATE_MONTH = 1;             // 每月
        public static final int   EM_SCREEN_DATE_WEEK = 2;              // 每周
        public static final int   EM_SCREEN_DATE_DAY = 3;               // 每日
    }

    // 增加即时节目计划输入参数
    public static class NET_IN_ADD_IMME_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;
        public NET_IMMEDIATELY_PLAN_INFO stuImmePlan;                 // 即时节目计划信息

        public NET_IN_ADD_IMME_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 增加节目计划输出参数
    public static class NET_OUT_ADD_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szPlanID = new byte[MAX_COMMON_STRING_64]; // 节目计划ID char[]

        public NET_OUT_ADD_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 即时节目计划配置信息
    public static class NET_IMMEDIATELY_PLAN_INFO extends SdkStructure
    {
        public byte[]           szPlanName = new byte[MAX_COMMON_STRING_64]; // 节目计划名称
        public byte[]           szPlanID = new byte[MAX_COMMON_STRING_64]; // 节目计划ID ，添加时无效，用于修改、删除
        public byte[]           szSplitScreenID = new byte[MAX_COMMON_STRING_64]; // 分屏ID
        public int              bEnable;                              // 计划是否启用 , BOOL类型
        public int              nPlayTime;                            // 播放时长, 单位 : 分钟
        public byte[]           szProgrammeName = new byte[MAX_COMMON_STRING_64]; // 即时发布的节目名称
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 即时发布的节目ID
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 修改节目信息接口输入参数
    public static class NET_IN_MODIFY_ONE_PROGRAMME extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAMME_INFO stuProgrammeInfo;                   // 节目信息

        public NET_IN_MODIFY_ONE_PROGRAMME() {
            this.dwSize = this.size();
        }
    }

    // 修改节目信息接口输出参数
    public static class NET_OUT_MODIFY_ONE_PROGRAMME extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MODIFY_ONE_PROGRAMME() {
            this.dwSize = this.size();
        }
    }

    // 批量删除节目信息接口输入参数
    public static class NET_IN_DEL_PROGRAMMES extends SdkStructure
    {
        public int              dwSize;
        public int              nProgrammeID;                         // 节目ID个数
        public PRO_GRAMME_ID[]  szProGrammeIdListArr = (PRO_GRAMME_ID[])new PRO_GRAMME_ID().toArray(MAX_PROGRAMMES_COUNT); // 需要删除的节目ID列表

        public NET_IN_DEL_PROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 需要删除的节目ID
    public static class PRO_GRAMME_ID extends SdkStructure
    {
        public byte[]           szProGrammeIdList = new byte[MAX_COMMON_STRING_64]; // 需要删除的节目ID
    }

    // 批量删除节目信息接口输出参数
    public static class NET_OUT_DEL_PROGRAMMES extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_DEL_PROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 修改即时节目计划输入参数
    public static class NET_IN_MODIFY_IMME_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;
        public NET_IMMEDIATELY_PLAN_INFO stuImmePlan;                 // 即时节目计划信息

        public NET_IN_MODIFY_IMME_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 修改即时节目计划输出参数
    public static class NET_OUT_MODIFY_IMME_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MODIFY_IMME_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 增加定时节目计划输入参数
    public static class NET_IN_ADD_TIMER_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;
        public NET_TIMER_PLAN_INFO stuTimerPlan;                      // 定时节目计划信息

        public NET_IN_ADD_TIMER_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 定时节目计划配置信息
    public static class NET_TIMER_PLAN_INFO extends SdkStructure
    {
        public byte[]           szPlanName = new byte[MAX_COMMON_STRING_64]; // 节目计划名称
        public byte[]           szPlanID = new byte[MAX_COMMON_STRING_64]; // 节目计划ID，添加时无效，用于修改、删除
        public byte[]           szSplitScreenID = new byte[MAX_COMMON_STRING_64]; // 分屏ID
        public int              emDataType;                           // 节目计划日期类型, 对应 EM_TIMERPLAN_DATE_TYPE
        public int              nDataCount;                           // 节目计划日期个数
        public int[]            nPlayDates = new int[MAX_PLAYDATES_COUNT]; // 节目播放日期列表
        public NET_PROGRAMME_DATA stuSatrtDate = new NET_PROGRAMME_DATA(); // 节目开始日期
        public NET_PROGRAMME_DATA stuEndDate = new NET_PROGRAMME_DATA(); // 节目结束日期
        public int              emReviewState;                        // 审核状态, 对应  EM_REVIES_STATE
        public byte[]           szReviewOpinion = new byte[MAX_COMMON_STRING_64]; // 审核意见
        public int              bOverdue;                             // 计划是否过期, BOOL类型
        public int              nProgrammes;                          // 节目个数
        public NET_PROGRAMME_OF_PLAN[] stuProgrammes = (NET_PROGRAMME_OF_PLAN[])new NET_PROGRAMME_OF_PLAN().toArray(MAX_PROGRAMMES_COUNT); // 节目组信息
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 定时计划日期类型
    public static class EM_TIMERPLAN_DATE_TYPE extends SdkStructure
    {
        public static final int   EM_TIMERPLAN_DATE_UNKNOWN = 0;        // 未知
        public static final int   EM_TIMERPLAN_DATE_MONTH = 1;          // 每月
        public static final int   EM_TIMERPLAN_DATE_WEEK = 2;           // 每周
        public static final int   EM_TIMERPLAN_DATE_DAY = 3;            // 每日
        public static final int   EM_TIMERPLAN_DATE_CUSTOM = 4;         // 自定义
    }

    // 节目日期格式
    public static class NET_PROGRAMME_DATA extends SdkStructure
    {
        public int              dwYear;                               // 年
        public int              dwMonth;                              // 月
        public int              dwDay;                                // 日

        public String toString() {
            return dwYear + "-" + dwMonth + "-" + dwDay;
        }
    }

    // 节目计划中的节目信息
    public static class NET_PROGRAMME_OF_PLAN extends SdkStructure
    {
        public byte[]           szProgrammeName = new byte[MAX_COMMON_STRING_64]; // 节目名称
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID
        public int              bIsBgProgramme;                       // 是否背景节目, BOOL类型
        public NET_PROGRAMME_TIME_INFO stuSatrtTime;                  // 节目开始时间
        public NET_PROGRAMME_TIME_INFO stuEndTime;                    // 节目结束时间
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 审核状态
    public static class EM_REVIES_STATE extends SdkStructure
    {
        public static final int   EM_REVIES_UNKNOWN = 0;                // 未知
        public static final int   EM_REVIES_PASS = 1;                   // 通过
        public static final int   EM_REVIES_NOTPASS = 2;                // 不通过
    }

    // 修改定时节目计划输入参数
    public static class NET_IN_MODIFY_TIMER_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;
        public NET_TIMER_PLAN_INFO stuTimerPlan;                      // 定时节目计划信息

        public NET_IN_MODIFY_TIMER_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 修改定时节目计划输出参数
    public static class NET_OUT_MODIFY_TIMER_PROGRAMMEPLAN extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MODIFY_TIMER_PROGRAMMEPLAN() {
            this.dwSize = this.size();
        }
    }

    // 删除多个节目计划输入参数
    public static class NET_IN_DEL_PROGRAMMEPLANS extends SdkStructure
    {
        public int              dwSize;
        public int              nPlanID;                              // 节目计划ID个数
        public PLAN_ID[]        szPlanIDArr = (PLAN_ID[])new PLAN_ID().toArray(MAX_PROGRAMMES_COUNT); // 节目计划ID

        public NET_IN_DEL_PROGRAMMEPLANS() {
            this.dwSize = this.size();
        }
    }

    // 节目计划ID
    public static class PLAN_ID extends SdkStructure
    {
        public byte[]           szPlanID = new byte[MAX_COMMON_STRING_64]; // 节目计划ID
    }

    // 删除多个节目计划输出参数
    public static class NET_OUT_DEL_PROGRAMMEPLANS extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_DEL_PROGRAMMEPLANS() {
            this.dwSize = this.size();
        }
    }

    // 通过诱导屏ID 获取诱导屏配置信息输入参数
    public static class NET_IN_GET_GUIDESCREEN_CFG_BYID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szScreenID = new byte[MAX_COMMON_STRING_64]; // 屏ID

        public NET_IN_GET_GUIDESCREEN_CFG_BYID() {
            this.dwSize = this.size();
        }
    }

    // 通过诱导屏ID 获取诱导屏配置信息输出参数
    public static class NET_OUT_GET_GUIDESCREEN_CFG_BYID extends SdkStructure
    {
        public int              dwSize;
        public NET_GUIDESCREEN_ATTRIBUTE_INFO stuGuideScreenCfg;      // 诱导屏属性信息

        public NET_OUT_GET_GUIDESCREEN_CFG_BYID() {
            this.dwSize = this.size();
        }
    }

    // 获取所有诱导屏配置信息输入参数
    public static class NET_IN_GET_ALL_GUIDESCREEN_CFG extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_GET_ALL_GUIDESCREEN_CFG() {
            this.dwSize = this.size();
        }
    }

    // 获取所有诱导屏配置信息输出参数
    public static class NET_OUT_GET_ALL_GUIDESCREEN_CFG extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxScreen;                           // 最大诱导屏个数, 值由用户指定
        public int              nRetScreen;                           // 实际返回的诱导屏个数
        public Pointer          pstGuideScreenCfg;                    // 用于存放获取到的诱导屏属性信息, 内存由用户维护
        // 大小为nMaxScreen 个 NET_GUIDESCREEN_ATTRIBUTE_INFO
        // 对应 NET_GUIDESCREEN_ATTRIBUTE_INFO[]

        public NET_OUT_GET_ALL_GUIDESCREEN_CFG() {
            this.dwSize = this.size();
        }
    }

    // 通过节目ID 获取节目信息输入参数
    public static class NET_IN_GET_PROGRAMME_BYID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID

        public NET_IN_GET_PROGRAMME_BYID() {
            this.dwSize = this.size();
        }
    }

    // 通过节目ID 获取节目信息输出参数
    public static class NET_OUT_GET_PROGRAMME_BYID extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAMME_INFO stuProgrammeInfo;                   // 节目配置信息

        public NET_OUT_GET_PROGRAMME_BYID() {
            this.dwSize = this.size();
        }
    }

    // 获取所有节目信息输入参数
    public static class NET_IN_GET_ALL_PROGRAMMES extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_GET_ALL_PROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 获取所有节目信息输出参数
    public static class NET_OUT_GET_ALL_PROGRAMMES extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxCnt;                              // pstProgrammeInfo最大NET_PROGRAMME_INFO 个数
        public int              nRetCnt;                              // pstProgrammeInfo实际返回的 NET_PROGRAMME_INFO 个数
        public Pointer          pstProgrammeInfo;                     // 节目配置信息, 内存由用户维护,对应 NET_PROGRAMME_INFO[]
        // 大小为 nMaxCnt 个  NET_PROGRAMME_INFO

        public NET_OUT_GET_ALL_PROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 获取所有节目的简要信息输入参数
    public static class NET_IN_GET_ALL_BRIEFLYPROGRAMMES extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_GET_ALL_BRIEFLYPROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 获取所有节目的简要信息输出参数
    public static class NET_OUT_GET_ALL_BRIEFLYPROGRAMMES extends SdkStructure
    {
        public int              dwSize;
        public int              nRetCnt;                              // 实际返回的个数
        public NET_BRIEFLY_PROGRAMME_INFO[] stuBriProgrammes = (NET_BRIEFLY_PROGRAMME_INFO[])new NET_BRIEFLY_PROGRAMME_INFO().toArray(MAX_PROGRAMMES_COUNT); // 节目简要信息

        public NET_OUT_GET_ALL_BRIEFLYPROGRAMMES() {
            this.dwSize = this.size();
        }
    }

    // 节目简要信息
    public static class NET_BRIEFLY_PROGRAMME_INFO extends SdkStructure
    {
        public byte[]           szProgrammeName = new byte[MAX_COMMON_STRING_64]; // 节目名称
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID
        public int              emProgrammeType;                      // 简要节目信息类型,对应枚举  EM_BRIEFLYPROGRAM_TYPE
        public int              bEnable;                              // 节目是否启用, BOOL类型
        public int              bTempletState;                        // 节目是否保存为模板, BOOL类型
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 简要节目信息类型
    public static class EM_BRIEFLYPROGRAM_TYPE extends SdkStructure
    {
        public static final int   EM_BRIEFLYPROGRAM_UNKNOWN = 0;        // 未知
        public static final int   EM_BRIEFLYPROGRAM_BAR = 1;            // 广告节目
        public static final int   EM_BRIEFLYPROGRAM_ORDINARY = 2;       // 普通节目
    }

    // 获取所有节目计划输入参数
    public static class NET_IN_GET_ALL_PROGRAMMEPLANS extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_GET_ALL_PROGRAMMEPLANS() {
            this.dwSize = this.size();
        }
    }

    // 获取所有节目计划输出参数
    public static class NET_OUT_GET_ALL_PROGRAMMEPLANS extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxPlanCnt;                          // 即时节目和定时节目计划最大个数, 由用户指定
        public int              nRetImmCnt;                           // 实际返回的即时节目计划个数
        public Pointer          pstImmePlan;                          // 即时节目计划信息, 对应  NET_IMMEDIATELY_PLAN_INFO[]，
        // 大小 nMaxPlanCnt 个 NET_IMMEDIATELY_PLAN_INFO
        public int              nRetTimerCnt;                         // 实际返回的定时节目计划个数
        public Pointer          pstTimerPlan;                         // 定时节目计划信息,对应  NET_TIMER_PLAN_INFO[]，
        // 大小 nMaxPlanCnt 个 NET_TIMER_PLAN_INFO

        public NET_OUT_GET_ALL_PROGRAMMEPLANS() {
            this.dwSize = this.size();
        }
    }

    // 即时计划与定时计划信息数组
    public static class NET_PROGRAMME_PLANS_INFO extends SdkStructure {
        public NET_IMMEDIATELY_PLAN_INFO[] szImmePlan;                // 即时节目计划信息数组
        public NET_TIMER_PLAN_INFO[] szTimerPlan;                     // 定时节目计划信息数组

        public NET_PROGRAMME_PLANS_INFO() {}

        public NET_PROGRAMME_PLANS_INFO(int maxPlanCount) {
            szImmePlan = new NET_IMMEDIATELY_PLAN_INFO[maxPlanCount];
            szTimerPlan = new NET_TIMER_PLAN_INFO[maxPlanCount];
            for(int i = 0; i < maxPlanCount; i++) {
                szImmePlan[i] = new NET_IMMEDIATELY_PLAN_INFO();
                szTimerPlan[i] = new NET_TIMER_PLAN_INFO();
            }
        }
    }

    // 通过节目计划ID 获取节目计划输入参数
    public static class NET_IN_GET_PROGRAMMEPLAN_BYID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szPlanID = new byte[MAX_COMMON_STRING_64]; // 节目计划ID

        public NET_IN_GET_PROGRAMMEPLAN_BYID() {
            this.dwSize = this.size();
        }
    }

    // 通过节目计划ID 获取节目计划输出参数
    public static class NET_OUT_GET_PROGRAMMEPLAN_BYID extends SdkStructure
    {
        public int              dwSize;
        public int              emPlanType;                           // 节目计划类型, 对应  EM_PROGRAMMEPLAN_TYPE
        public NET_IMMEDIATELY_PLAN_INFO stuImmePlan;                 // 即时节目计划信息, emPlanType 为 EM_PROGRAMMEPLAN_IMME 时有效
        public NET_TIMER_PLAN_INFO stuTimerPlan;                      // 定时节目计划信息, emPlanType 为 EM_PROGRAMMEPLAN_TIMER 时有效

        public NET_OUT_GET_PROGRAMMEPLAN_BYID() {
            this.dwSize = this.size();
        }
    }

    // 节目计划类型
    public static class EM_PROGRAMMEPLAN_TYPE extends SdkStructure
    {
        public static final int   EM_PROGRAMMEPLAN_UNKNOWN = 0;         // 未知
        public static final int   EM_PROGRAMMEPLAN_IMME = 1;            // 即时计划
        public static final int   EM_PROGRAMMEPLAN_TIMER = 2;           // 定时计划
    }

    //设置光带状态信息输入参数
    public static class NET_IN_SET_GD_STATUS extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szScreenID = new byte[MAX_COMMON_STRING_64]; // 分屏ID, 即窗口ID
        public int              nGDNum;                               // 光带总数
        public int[]            emStatus = new int[MAX_GD_COUNT];     // 光带信息, 对应  EM_GD_COLOR_TYPE

        public NET_IN_SET_GD_STATUS() {
            this.dwSize = this.size();
        }
    }

    //设置光带状态信息输出参数
    public static class NET_OUT_SET_GD_STATUS extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_GD_STATUS() {
            this.dwSize = this.size();
        }
    }

    // 光带颜色类型
    public static class EM_GD_COLOR_TYPE extends SdkStructure
    {
        public static final int   EM_GD_COLOR_RED = 0;                  // 红色
        public static final int   EM_GD_COLOR_GREEN = 1;                // 绿色
        public static final int   EM_GD_COLOR_YELLOW = 2;               // 黄色
    }

    // 用户信息表
    public static class USER_MANAGE_INFO_EX extends SdkStructure
    {
        public int              dwRightNum;                           // 权限信息
        public OPR_RIGHT_EX[]   rightList = (OPR_RIGHT_EX[])new OPR_RIGHT_EX().toArray(NET_MAX_RIGHT_NUM);
        public int              dwGroupNum;                           // 用户组信息
        public USER_GROUP_INFO_EX[] groupList = (USER_GROUP_INFO_EX[])new USER_GROUP_INFO_EX().toArray(NET_MAX_GROUP_NUM);
        public int              dwUserNum;                            // 用户信息
        public USER_INFO_EX[]   userList = (USER_INFO_EX[])new USER_INFO_EX().toArray(NET_MAX_USER_NUM);
        public int              dwFouctionMask;                       // 掩码；0x00000001 - 支持用户复用,0x00000002 - 密码修改需要校验
        public byte             byNameMaxLength;                      // 支持的用户名最大长度
        public byte             byPSWMaxLength;                       // 支持的密码最大长度
        public byte[]           byReserve = new byte[254];
    }

    // 权限信息
    public static class OPR_RIGHT_EX extends SdkStructure
    {
        public int              dwID;
        public byte[]           name = new byte[NET_RIGHT_NAME_LENGTH];
        public byte[]           memo = new byte[NET_MEMO_LENGTH];
    }

    // 用户组信息
    public static class USER_GROUP_INFO_EX extends SdkStructure
    {
        public int              dwID;
        public byte[]           name = new byte[NET_USER_NAME_LENGTH_EX];
        public int              dwRightNum;
        public int[]            rights = new int[NET_MAX_RIGHT_NUM];
        public byte[]           memo = new byte[NET_MEMO_LENGTH];
    }

    // 用户信息
    public static class USER_INFO_EX extends SdkStructure
    {
        public int              dwID;
        public int              dwGroupID;
        public byte[]           name = new byte[NET_USER_NAME_LENGTH_EX];
        public byte[]           passWord = new byte[NET_USER_PSW_LENGTH_EX];
        public int              dwRightNum;
        public int[]            rights = new int[NET_MAX_RIGHT_NUM];
        public byte[]           memo = new byte[NET_MEMO_LENGTH];
        public int              dwFouctionMask;                       // 掩码,0x00000001 - 支持用户复用
        public byte[]           byReserve = new byte[32];
    }

    // CLIENT_DownloadRemoteFile 接口输入参数(文件下载)
    public static class NET_IN_DOWNLOAD_REMOTE_FILE extends SdkStructure
    {
        public int              dwSize;
        public Pointer          pszFileName;                          // 需要下载的文件名
        public Pointer          pszFileDst;                           // 存放文件路径

        public NET_IN_DOWNLOAD_REMOTE_FILE() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DownloadRemoteFile 接口输出参数(文件下载)
    public static class NET_OUT_DOWNLOAD_REMOTE_FILE extends SdkStructure
    {
        public int              dwSize;
        public int              dwMaxFileBufLen;                      // 文件缓存区pstFileBuf的大小, 由用户指定
        public Pointer          pstFileBuf;                           // 文件缓存区, 由用户申请和释放
        public int              dwRetFileBufLen;                      // 缓存区中返回的实际文件数据大小
        public byte[]           byReserved = new byte[4];             // 字节对齐

        public NET_OUT_DOWNLOAD_REMOTE_FILE() {
            this.dwSize = this.size();
        }
    }

    // 车牌对比, 对应事件 EVENT_IVS_VEHICLE_RECOGNITION
    public static class DEV_EVENT_VEHICLE_RECOGNITION_INFO extends SdkStructure
    {
        public byte[]           szName = new byte[128];               // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nVehicleAction;                       // 车辆动作 0-未知,1-在检测区域内,2-离开检测区域
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public NET_SEAT_INFO    stuMainSeatInfo;                      // 主驾驶位信息
        public NET_SEAT_INFO    stuSlaveSeatInfo;                     // 副驾驶位信息
        public int              nVehicleAttachNum;                    // 车上附件数量
        public NET_VEHICLE_ATTACH[] stuVehicleAttach = (NET_VEHICLE_ATTACH[])new NET_VEHICLE_ATTACH().toArray(8); // 车上附件数据
        public byte[]           szCountry = new byte[32];             // 国家,2字节,符合ISO3166规范
        public int              nCarCandidateNum;                     // 候选车辆数量
        public NET_CAR_CANDIDATE_INFO[] stuCarCandidate = (NET_CAR_CANDIDATE_INFO[])new NET_CAR_CANDIDATE_INFO().toArray(MAX_CAR_CANDIDATE_NUM); // 候选车辆数据
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public int              nChannel;                             // 通道号
        public byte[]           bReserved = new byte[1024];
    }

    // 事件类型EVENT_IVS_ELEVATOR_ABNORMAL(电动扶梯运行异常事件)对应的数据块描述信息
    public static class DEV_EVENT_ELEVATOR_ABNORMAL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public byte[]           szName = new byte[SDK_EVENT_NAME_LEN]; // 事件名称
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nAction;                              // 1:开始 2:停止
        public int              emClassType;                          // 智能事件所属大类，参考EM_CLASS_TYPE
        public int              nDetectRegionPointNum;                // 扶梯检测区顶点数
        public NET_POINT[]      stuDetectRegion = new NET_POINT[20];  // 扶梯检测区多边形类型，多边形中每个顶点的坐标归一化到[0,8191]区间。
        public NET_POINT[]      stuDirection = new NET_POINT[2];      // 扶梯的运动方向，第一个点是起点，第二个点是终点。坐标归一化到[0,8191]区间。
        public Pointer          pstuImageInfo;                        // 图片信息数组,指针对应NET_IMAGE_INFO_EX2结构体数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public byte[]           byReserved1 = new byte[908];          // 保留字节

        public DEV_EVENT_ELEVATOR_ABNORMAL_INFO() {
    		for(int i=0;i<stuDetectRegion.length;i++){
    			stuDetectRegion[i]=new NET_POINT();
    		}
    		for(int i=0;i<stuDirection.length;i++){
    			stuDirection[i]=new NET_POINT();
    		}
        }
    }

    public static class NET_CAR_CANDIDATE_INFO extends SdkStructure
    {
        public NET_VEHICLE_INFO stuVehicleInfo = new NET_VEHICLE_INFO(); // 车辆信息
        public int              nDifferentAttributresNum;             // 和数据库不相符的属性数目
        public int[]            nDifferentAttributres = new int[16];  // 和数据库不相符的属性集合,元素值取值意义:0-未知 1-车牌属地 2-车标 3-车型 4-车色 5-车牌颜色
        public byte[]           bReserved = new byte[512];
    }

    public static class NET_VEHICLE_INFO extends SdkStructure
    {
        public int              nUID;                                 // 车辆唯一标识符,由服务端生成用于程序中表示惟一
        public byte[]           szGroupID = new byte[64];             // 车辆所属组ID
        public byte[]           szGroupName = new byte[128];          // 车辆所属组名
        public byte[]           szPlateNumber = new byte[64];         // 车牌号码
        public byte[]           szPlateCountry = new byte[4];         // 车辆所在国家,2字节，符合ISO3166规范
        public int              nPlateType;                           // 车牌类型
        // 01  大型汽车号牌 黄底黑字
        // 02  小型汽车号牌 蓝底白字
        // 03  使馆汽车号牌 黑底白字、红“使”字
        // 04  领馆汽车号牌 黑底白字、红“领”字
        // 05  境外汽车号牌 黑底白、红字
        // 06  外籍汽车号牌 黑底白字
        // 13  农用运输车号牌 黄底黑字黑框线
        // 15  挂车号牌 黄底黑字黑框线
        // 16  教练汽车号牌 黄底黑字黑框线
        // 18  试验汽车号牌
        // 20  临时入境汽车号牌 白底红字黑“临时入境”
        // 22  临时行驶车号牌 白底黑字黑线框
        // 23  公安警用汽车号牌
        public int              nVehicleType;                         // 车型(轿车、卡车等)
        // 001  巡逻车
        // 002  交警车辆
        // 003  消防车
        // 004  单兵
        // 005  其他警车
        // 006  其他设备
        // 020  政府车辆
        // 031  校车
        // 032  运钞车
        // 033  客运车辆
        // 034  公交车
        // 035  出租车
        // 036  危险品车辆
        public int              nBrand;                               // 车辆车标,需要通过映射表得到真正的车标.同卡口事件的CarLogoIndex
        public int              nCarSeries;                           // 车辆子品牌，需要通过映射表得到真正的子品牌,同卡口事件的SubBrand
        public int              nCarSeriesModelYearIndex;             // 车辆品牌年款，需要通过映射表得到真正的年款，同卡口事件的BrandYear 车头年款序号范围1~999；车尾年款序号范围1001~1999；0表示未知；1000预留。
        public NET_COLOR_RGBA   stuVehicleColor = new NET_COLOR_RGBA(); // 车色 第一个元素表示红色分量值； 第二个元素表示绿色分量值； 第三个元素表示蓝色分量值； 第四个元素表示透明度分量(无意义)
        public NET_COLOR_RGBA   stuPlateColor = new NET_COLOR_RGBA(); // 车牌颜色,规则同车色
        public byte[]           szOwnerName = new byte[64];           // 车主名称
        public int              nSex;                                 // 车主性别
        public int              nCertificateType;                     // 车主证件类型 0-未知 1-证件 2-护照 3-军官证
        public byte[]           szPersonID = new byte[32];            // 人员证件号码,工号,或其他编号
        public byte[]           szOwnerCountry = new byte[4];         // 车主国籍,2字节,符合ISO3166规范
        public byte[]           szProvince = new byte[64];            // 省份
        public byte[]           szCity = new byte[64];                // 城市
        public byte[]           szHomeAddress = new byte[128];        // 注册人员家庭地址(IVSS需求)
        public byte[]           szEmail = new byte[32];               // 车主电子邮箱
        public byte[]           szPhoneNo = new byte[128];            // 注册车主电话号码
        public	int              nVehicleColorState;                   //车色状态:0-未知，1-已知
        public	int              nPlateColorState;                     //车牌颜色状态:0-未知，1-已知
        public byte[]           bReserved = new byte[504];

        @Override
        public String toString() {
            return "NET_VEHICLE_INFO{" +
                    "nUID=" + nUID +
                    ", szGroupID=" + new String(szGroupID) +
                    ", szGroupName=" + new String(szGroupName) +
                    ", szPlateCountry=" + new String(szPlateCountry) +
                    ", nPlateType=" + nPlateType +
                    ", nVehicleType=" + nVehicleType +
                    ", nBrand=" + nBrand +
                    ", nCarSeries=" + nCarSeries +
                    ", nCarSeriesModelYearIndex=" + nCarSeriesModelYearIndex +
                    ", stuVehicleColor=" + stuVehicleColor +
                    ", stuPlateColor=" + stuPlateColor +
                    ", szOwnerName=" + new String(szOwnerName) +
                    ", nSex=" + nSex +
                    ", nCertificateType=" + nCertificateType +
                    ", szPersonID=" + new String(szPersonID) +
                    ", szOwnerCountry=" + new String(szOwnerCountry) +
                    ", szProvince=" + new String(szProvince) +
                    ", szCity=" + new String(szCity) +
                    ", szHomeAddress=" + new String(szHomeAddress) +
                    ", szEmail=" + new String(szEmail) +
                    ", szPhoneNo=" + new String(szPhoneNo) +
                    ", nVehicleColorState=" + nVehicleColorState +
                    ", nPlateColorState=" + nPlateColorState +
                    ", bReserved=" + new String(bReserved) +
                    '}';
        }
    }

    //获取播放盒上全部节目信息接口输入参数
    public static class NET_IN_GET_ALL_PLAYBOX_PROGRAM extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_GET_ALL_PLAYBOX_PROGRAM() {
            this.dwSize = this.size();
        }
    }

    //获取播放盒上全部节目信息接口输出参数
    public static class NET_OUT_GET_ALL_PLAYBOX_PROGRAM extends SdkStructure
    {
        public int              dwSize;
        public int              nMaxProgramCount;                     // 节目信息最大个数，由用户指定
        public int              nRetProgramCount;                     // 实际返回的节目信息个数
        public Pointer          pstProgramInfo;                       // 播放盒上的节目信息, 内存资源由用户维护,对应 NET_PROGRAM_ON_PLAYBOX[]

        public NET_OUT_GET_ALL_PLAYBOX_PROGRAM() {
            this.dwSize = this.size();
        }
    }

    // 播放盒节目信息
    public static class NET_PROGRAM_ON_PLAYBOX extends SdkStructure
    {
        public byte[]           szProgrammeName = new byte[MAX_COMMON_STRING_64]; // 节目名称
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID, 添加节目时不需要指定
        public int              bEnable;                              // 节目是否启用
        public int              emProgramType;                        // 节目类型, 参考  EM_PLAYBOXPROGRAM_TYPE
        public NET_PROGRAM_LOGO_INFO stuLogoInfo;                     // LOGO节目信息, emProgramType为EM_PROGRAM_ON_PLAYBOX_LOGO时有效
        public NET_PROGRAM_BAR_INFO stuBarInfo;                       // 广告条节目信息, emProgramType为EM_PROGRAM_ON_PLAYBOX_BAR时有效
        public NET_PROGRAM_ORDINARY_INFO stuOrdinaryInfo = new NET_PROGRAM_ORDINARY_INFO(); // 普通节目信息, emProgramType为EM_PROGRAM_ON_PLAYBOX_ORDINARY时有效, 此参数需要在库里new对象
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 播放盒节目类型
    public static class EM_PLAYBOXPROGRAM_TYPE extends SdkStructure
    {
        public static final int   EM_PROGRAM_ON_PLAYBOX_LOGO = 0;       // LOGO, 对应结构体 NET_PROGRAM_LOGO_INFO
        public static final int   EM_PROGRAM_ON_PLAYBOX_BAR = 1;        // 广告条, 对应结构体 NET_PROGRAM_BAR_INFO
        public static final int   EM_PROGRAM_ON_PLAYBOX_ORDINARY = 2;   // 普通节目, 对应结构体 NET_PROGRAM_ORDINARY_INFO
    }

    // LOGO节目信息
    public static class NET_PROGRAM_LOGO_INFO extends SdkStructure
    {
        public byte[]           szLogoPath = new byte[MAX_COMMON_STRING_128]; // Logo路径
        public NET_RECT         stuBackgroundRect;                    // Logo位置
        public int              nDiaphaneity;                         // 透明度, 0-100
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 广告条节目信息
    public static class NET_PROGRAM_BAR_INFO extends SdkStructure
    {
        public byte[]           szContent = new byte[MAX_COMMON_STRING_512]; // 广告内容
        public NET_COLOR_RGBA   stuFontColor;                         // 字体颜色
        public int              nFontSize;                            // 字体大小
        public byte[]           szFontStyle = new byte[MAX_COMMON_STRING_32]; // 字体类型
        public int              nPlaySpeed;                           // 播放速度
        public NET_RECT         stuBackgroundRect;                    // 广告条位置
        public NET_COLOR_RGBA   stuBackColor;                         // 广告条背景颜色
        public int              nDiaphaneity;                         // 透明度, 0-100
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 普通广告节目信息
    public static class NET_PROGRAM_ORDINARY_INFO extends SdkStructure
    {
        public int              bTempletState;                        // 节目是否保存为模板
        public byte[]           szDescription = new byte[MAX_COMMON_STRING_128]; // 节目描述信息
        public int              nWidth;                               // 画布宽度
        public int              nHeight;                              // 画布高度
        public int              nWinCount;                            // 窗口数量
        public NET_PLAYBOX_WINDOWS_INFO[] stuWindowsInfo = (NET_PLAYBOX_WINDOWS_INFO[])new NET_PLAYBOX_WINDOWS_INFO().toArray(MAX_WINDOWS_COUNT); // 窗口信息
        public byte[]           byReserved = new  byte[128];          // 保留字节
    }

    // 播放盒上窗口信息
    public static class NET_PLAYBOX_WINDOWS_INFO extends SdkStructure
    {
        public NET_RECT         stuRect;                              // 窗口位置
        public int              nZorder;                              // 窗口Z轴序
        public int              nVolume;                              // 窗口音量，相对整屏音量的百分比
        public NET_COLOR_RGBA   stuBackColor;                         // 窗口背景颜色
        public int              nDiaphaneity;                         // 窗口背景透明度0-100
        public int              emTourPeriodType;                     // 窗口轮训类型 EM_TOURPERIOD_TYPE
        public int              nTourPeriodTime;                      // 自定义轮训时间，单位秒, 轮训类型为自定义轮训时有效
        public int              bAutoPlay;                            // 预览自动播放,Video元素有效
        public int              bLoopPlay;                            // 预览循环播放,Video元素有效
        public int              nElementsCount;                       // 诱导屏窗口元素个数
        public Pointer          pstElementsBuf;                       // 播放盒窗口元素信息缓存区, 根据类型对应不同的结构体
        // 填充多个元素信息, 每个元素信息内容为 NET_ELEMENT_COMMON_INFO + 元素类型对应的结构体
        public int              nBufLen;                              // 诱导屏窗口元素信息缓存区大小
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // 通过programme ID 获取播放盒上对应的节目信息输入参数
    public static class NET_IN_GET_PLAYBOX_PROGRAM_BYID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID

        public NET_IN_GET_PLAYBOX_PROGRAM_BYID() {
            this.dwSize = this.size();
        }
    }

    // 通过programme ID 获取播放盒上对应的节目信息输出参数
    public static class NET_OUT_GET_PLAYBOX_PROGRAM_BYID extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAM_ON_PLAYBOX stuPlayBoxProgram;              // 播放盒节目信息

        public NET_OUT_GET_PLAYBOX_PROGRAM_BYID() {
            this.dwSize = this.size();
        }
    }

    // 在播放盒上添加一个节目信息输入参数
    public static class NET_IN_ADD_ONE_PLAYBOX_PRAGROM extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAM_ON_PLAYBOX stuPlayBoxProgram;              // 播放盒节目信息

        public NET_IN_ADD_ONE_PLAYBOX_PRAGROM() {
            this.dwSize = this.size();
        }
    }

    // 在播放盒上添加一个节目信息输出参数
    public static class NET_OUT_ADD_ONE_PLAYBOX_PRAGROM extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szProgrammeID = new byte[MAX_COMMON_STRING_64]; // 节目ID

        public NET_OUT_ADD_ONE_PLAYBOX_PRAGROM() {
            this.dwSize = this.size();
        }
    }

    // 在播放盒上修改指定ID的节目信息输入参数
    public static class NET_IN_MODIFY_PLAYBOX_PROGRAM_BYID extends SdkStructure
    {
        public int              dwSize;
        public NET_PROGRAM_ON_PLAYBOX stuPlayBoxProgram;              // 播放盒节目信息

        public NET_IN_MODIFY_PLAYBOX_PROGRAM_BYID() {
            this.dwSize = this.size();
        }
    }

    // 在播放盒上修改指定ID的节目信息输出参数
    public static class NET_OUT_MODIFY_PLAYBOX_PROGRAM_BYID extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_MODIFY_PLAYBOX_PROGRAM_BYID() {
            this.dwSize = this.size();
        }
    }

    // 云台定位信息报警
    public static class NET_PTZ_LOCATION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nPTZPan;                              // 云台水平运动位置,有效范围：[0,3600]
        public int              nPTZTilt;                             // 云台垂直运动位置,有效范围：[-1800,1800]
        public int              nPTZZoom;                             // 云台光圈变动位置,有效范围：[0,128]
        public byte             bState;                               // 云台运动状态, 0-未知 1-运动 2-空闲
        public byte             bAction;                              // 云台动作,
                                                                    // 255-未知,0-预置点,1-线扫,2-巡航,3-巡迹,4-水平旋转,5-普通移动,6-巡迹录制,
                                                                    // 7-全景云台扫描,8-热度图,9-精确定位,10-设备校正,11-智能配置，12-云台重启
        public byte             bFocusState;                          // 云台聚焦状态, 0-未知, 1-运动状态, 2-空闲
        public byte             bEffectiveInTimeSection;              // 在时间段内预置点状态是否有效
                                                                    // 如果当前上报的预置点是时间段内的预置点,则为1,其他情况为0
        public int              nPtzActionID;                         // 巡航ID号
        public int              dwPresetID;                           // 云台所在预置点编号
        public float            fFocusPosition;                       // 聚焦位置
        public byte             bZoomState;                           // 云台ZOOM状态,0-未知,1-ZOOM,2-空闲
        public byte[]           bReserved = new byte[3];              // 对齐
        public int              dwSequence;                           // 包序号,用于校验是否丢包
        public int              dwUTC;                                // 对应的UTC(1970-1-1 00:00:00)秒数。
        public int              emPresetStatus;                       // 预置点位置,参考 EM_DH_PTZ_PRESET_STATUS
        public int              nZoomValue;                           // 真实变倍值 当前倍率（扩大100倍表示）
        public NET_PTZSPACE_UNNORMALIZED stuAbsPosition;              // 云台方向与放大倍数（扩大100倍表示）
                                                                    // 第一个元素为水平角度，0-36000；
                                                                    // 第二个元素为垂直角度，（-18000）-（18000）；
                                                                    // 第三个元素为显示放大倍数，0-MaxZoom*100
        public int              nFocusMapValue;                       // 聚焦映射值
        public int              nZoomMapValue;                        // 变倍映射值
        public int              emPanTiltStatus;                      //云台P/T运动状态
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public byte[]           reserved = new byte[696];             // 保留字段
    }

    // 云台定位中非归一化坐标和变倍
    public static class NET_PTZSPACE_UNNORMALIZED extends SdkStructure
    {
        public int              nPosX;                                // x坐标
        public int              nPosY;                                // y坐标
        public int              nZoom;                                // 放大倍率
        public byte[]           byReserved = new byte[52];            // 预留字节
    }

    // 预置点状态枚举
    public static class EM_DH_PTZ_PRESET_STATUS extends SdkStructure
    {
        public static final int   EM_DH_PTZ_PRESET_STATUS_UNKNOWN = 0;  // 未知
        public static final int   EM_DH_PTZ_PRESET_STATUS_REACH = 1;    // 预置点到达
        public static final int   EM_DH_PTZ_PRESET_STATUS_UNREACH = 2;  // 预置点未到达
    }

    //通用曝光属性配置
    public static class NET_VIDEOIN_EXPOSURE_NORMAL_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCfgType;                            // 配置类型，获取和设置时都要指定,对应枚举  NET_EM_CONFIG_TYPE
        public int              emExposureMode;                       // 曝光模式, 对应枚举  NET_EM_EXPOSURE_MODE
        public int              nAntiFlicker;                         // 防闪烁0-Outdoor  1-50Hz防闪烁 2-60Hz防闪烁
        public int              nCompensation;                        // 曝光补偿0-100
        public int              nGain;                                // 增益值
        public int              nGainMin;                             // 增益下限0-100
        public int              nGainMax;                             // 增益上限0-100
        public int              nExposureIris;                        // 光圈值，模式为光圈优先时有效，0-100
        public double           dbExposureValue1;                     // 自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public double           dbExposureValue2;                     // 自动曝光时间上限,毫秒为单位，取值0.1ms~80ms，且必须不小于"ExposureValue1"取值
        public Boolean          bIrisAuto;                            // 自动光圈使能
        public int              emDoubleExposure;                     // 双快门的支持类型

        public NET_VIDEOIN_EXPOSURE_NORMAL_INFO() {
            this.dwSize = this.size();
        }
    }

    // 每个通道对应的配置类型
    public static class NET_EM_CONFIG_TYPE extends SdkStructure
    {
        public static final int   NET_EM_CONFIG_DAYTIME = 0;            // 白天
        public static final int   NET_EM_CONFIG_NIGHT = 1;              // 夜晚
        public static final int   NET_EM_CONFIG_NORMAL = 2;             // 普通
    }

    // 透雾模式
    public static class NET_EM_DEFOG_MODE extends SdkStructure {
        public static final int   NET_EM_DEFOG_UNKNOW = 0;              // 未知模式
        public static final int   NET_EM_DEFOG_OFF = 1;                 // 关闭
        public static final int   NET_EM_DEFOG_AUTO = 2;                // 自动
        public static final int   NET_EM_DEFOG_MANAUL = 3;              // 手动
    }

    // 大气光模式枚举
    public static class NET_EM_INTENSITY_MODE extends SdkStructure {
        public static final int   NET_EM_INTENSITY_MODE_UNKNOW = 0;     // 未知方式
        public static final int   NET_EM_INTENSITY_MODE_AUTO = 1;       // 自动
        public static final int   NET_EM_INTENSITY_MODE_MANUAL = 2;     // 手动
    }

    // 曝光模式
    public static class NET_EM_EXPOSURE_MODE extends SdkStructure
    {
        public static final int   NET_EM_EXPOSURE_AUTO = 0;             // 默认自动
        public static final int   NET_EM_EXPOSURE_LOWNICE = 1;          // 低噪声
        public static final int   NET_EM_EXPOSURE_ANTISHADOW = 2;       // 防拖影
        public static final int   NET_EM_EXPOSURE_MANUALRANGE = 4;      // 手动区间
        public static final int   NET_EM_EXPOSURE_APERTUREFIRST = 5;    // 光圈优先
        public static final int   NET_EM_EXPOSURE_MANUALFIXATION = 6;   // 手动固定
        public static final int   NET_EM_EXPOSURE_GIANFIRST = 7;        // 增益优先
        public static final int   NET_EM_EXPOSURE_SHUTTERFIRST = 8;     // 快门优先
        public static final int   NET_EM_EXPOSURE_FLASHMATCH = 9;       // 闪光灯匹配模式
    }

    // 背光模式
    public static class NET_EM_BACK_MODE extends SdkStructure
    {
        public static final int   NET_EM_BACKLIGHT_MODE_UNKNOW = 0;     // 未知模式
        public static final int   NET_EM_BACKLIGHT_MODE_OFF = 1;        // 关闭
        public static final int   NET_EM_BACKLIGHT_MODE_BACKLIGHT = 2;  // 背光补偿
        public static final int   NET_EM_BACKLIGHT_MODE_WIDEDYNAMIC = 3; // 宽动态
        public static final int   NET_EM_BACKLIGHT_MODE_GLAREINHIBITION = 4; // 强光抑制
        public static final int   NET_EM_BACKLIGHT_MODE_SSA = 5;        // 场景自适应
    }

    // 背光补偿模式
    public static class NET_EM_BLACKLIGHT_MODE extends SdkStructure
    {
        public static final int   NET_EM_BLACKLIGHT_UNKNOW = 0;         // 未知模式
        public static final int   NET_EM_BLACKLIGHT_DEFAULT = 1;        // 默认模式
        public static final int   NET_EM_BLACKLIGHT_REGION = 2;         // 自定义区域模式
    }

    // 背光配置
    public static class NET_VIDEOIN_BACKLIGHT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCfgType;                            // 配置类型，获取和设置时都要指定, 对应枚举  NET_EM_CONFIG_TYPE
        public int              emBlackMode;                          // 背光模式, 对应枚举  NET_EM_BACK_MODE
        public int              emBlackLightMode;                     // 背光补偿模式, 对应枚举  NET_EM_BLACKLIGHT_MODE
        public NET_RECT         stuBacklightRegion;                   // 背光补偿区域
        public int              nWideDynamicRange;                    // 宽动态值，emBlackMode为NET_EM_BACKLIGHT_MODE_WIDEDYNAMIC时生效
        public int              nGlareInhibition;                     // 强光抑制0-100，emBlackMode为NET_EM_BACKLIGHT_MODE_GLAREINHIBITION时生效

        public NET_VIDEOIN_BACKLIGHT_INFO() {
            this.dwSize = this.size();
        }
    }

    // 聚焦模式配置
    public static class NET_VIDEOIN_FOCUSMODE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCfgType;                            // 配置类型，获取和设置时都要指定,对应枚举 NET_EM_CONFIG_TYPE
        public int              emFocusMode;                          // 聚焦模式, 对应枚举  NET_EM_FOCUS_MODE

        public NET_VIDEOIN_FOCUSMODE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 聚焦模式
    public static class NET_EM_FOCUS_MODE extends SdkStructure
    {
        public static final int   NET_EM_FOCUS_OFF = 0;                 // 关闭
        public static final int   NET_EM_FOCUS_ASSIST = 1;              // 辅助聚焦
        public static final int   NET_EM_FOCUS_AUTO = 2;                // 自动聚焦
        public static final int   NET_EM_FOCUS_SEMI_AUTO = 3;           // 半自动聚焦
        public static final int   NET_EM_FOCUS_MANUAL = 4;              // 手动聚焦
    }

    // 图像属性配置
    public static class NET_VIDEOIN_IMAGE_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emCfgType;                            // 配置类型，获取和设置时都要制定,对应枚举 NET_EM_CONFIG_TYPE
        public int              bMirror;                              // 是否开启画面镜像功能
        public int              bFlip;                                // 是否开启画面翻转功能
        public int              nRotate90;                            // 0-不旋转，1-顺时针90°，2-逆时针90°

        public NET_VIDEOIN_IMAGE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 网络协议配置
    public static class CFG_DVRIP_INFO extends SdkStructure
    {
        public int              nTcpPort;                             // TCP服务端口,1025~65535
        public int              nSSLPort;                             // SSL服务端口,1025~65535
        public int              nUDPPort;                             // UDP服务端口,1025~65535
        public int              nMaxConnections;                      // 最大连接数
        public int              bMCASTEnable;                         // 组播使能
        public int              nMCASTPort;                           // 组播端口号
        public byte[]           szMCASTAddress = new byte[MAX_ADDRESS_LEN]; // 组播地址
        public int              nRegistersNum;                        // 主动注册配置个数
        public CFG_REGISTER_SERVER_INFO[] stuRegisters = (CFG_REGISTER_SERVER_INFO[])new CFG_REGISTER_SERVER_INFO().toArray(MAX_REGISTER_NUM); // 主动注册配置
        public int              emStreamPolicy;                       // 带宽不足时码流策略,对应枚举  EM_STREAM_POLICY
        public CFG_REGISTERSERVER_VEHICLE stuRegisterServerVehicle;   // 车载专用主动注册配置
    }

    // 带宽不足时码流策略
    public static class EM_STREAM_POLICY extends SdkStructure
    {
        public static final int   STREAM_POLICY_UNKNOWN = 0;
        public static final int   STREAM_POLICY_NONE = 1;               // 无策略,不开启使能"None"
        public static final int   STREAM_POLICY_QUALITY = 2;            // 画质优先"Quality"
        public static final int   STREAM_POLICY_FLUENCY = 3;            // 流畅度优先"Fluency"
        public static final int   STREAM_POLICY_AUTOADAPT = 4;          // 自动"AutoAdapt"
    }

    // 主动注册配置
    public static class CFG_REGISTER_SERVER_INFO extends SdkStructure
    {
        public int              bEnable;                              // 主动注册使能
        public byte[]           szDeviceID = new byte[MAX_ADDRESS_LEN]; // 设备ID
        public int              nServersNum;                          // 服务器个数
        public CFG_SERVER_INFO[] stuServers = (CFG_SERVER_INFO[])new CFG_SERVER_INFO().toArray(MAX_SERVER_NUM); // 服务器数组
    }

    // 服务器
    public static class CFG_SERVER_INFO extends SdkStructure
    {
        public int              nPort;                                // 服务器端口号
        public byte[]           szAddress = new byte[MAX_ADDRESS_LEN]; // IP地址或网络名
        public int              abEnable;                             //bEnable 是否有效
        public int              bEnable;                              //主动注册使能
        public byte[]           szReserved = new byte[64];            //预留字节
    }

    // 车载专用主动注册配置
    public static class CFG_REGISTERSERVER_VEHICLE extends SdkStructure
    {
        public int              bEnable;                              // 主动注册使能
        public int              bRepeatEnable;                        // 是否发送相同坐标数据
        public byte[]           szDeviceID = new byte[MAX_ADDRESS_LEN]; // 子设备ID
        public int              nSendInterval;                        // 发送间隔, 单位：秒
        public byte[]           szAddress = new byte[MAX_ADDRESS_LEN]; // IP地址或网络名
        public int              nPort;                                // 端口号
        public int              emSendPolicy;                         // 上传策略,对应枚举  EM_CFG_SENDPOLICY
        public byte[]           szTestAddress = new byte[MAX_ADDRESS_LEN]; // 测试IP地址或网络名
        public int              nTestPort;                            // 测试端口号
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // 上传策略
    public static class EM_CFG_SENDPOLICY extends SdkStructure
    {
        public static final int   EM_SNEDPOLICY_UNKNOWN = -1;
        public static final int   EM_SENDPOLICY_TIMING = 0;             // 定时上报
        public static final int   EM_SENDPOLICY_EVENT = 1;              // 事件触发上报
    }

    // 网络接口配置
    public static class CFG_NETWORK_INFO extends SdkStructure {
		/** 主机名称 */
        public byte[]           szHostName = new byte[128];
		/** 所属域 */
        public byte[]           szDomain = new byte[128];
		/** 默认使用的网卡 */
        public byte[]           szDefInterface = new byte[128];
		/** 网卡数量 */
        public int              nInterfaceNum;
		/** 网卡列表 */
        public CFG_NETWORK_INTERFACE[] stuInterfaces = (CFG_NETWORK_INTERFACE[]) new CFG_NETWORK_INTERFACE().toArray(32);
		/** 虚拟绑定网口数量 */
        public int              nBondInterfaceNum;
		/** 虚拟绑定网口列表 */
        public CFG_NETWORK_BOND_INTERFACE[] stuBondInterfaces = (CFG_NETWORK_BOND_INTERFACE[]) new CFG_NETWORK_BOND_INTERFACE().toArray(32);
		/** 网桥数量 */
        public int              nBrInterfaceNum;
		/** 网桥列表 */
        public CFG_NETWORK_BR_INTERFACE[] stuBrInterfaces = (CFG_NETWORK_BR_INTERFACE[]) new CFG_NETWORK_BR_INTERFACE().toArray(32);
    }

    // 网络接口
    public static class CFG_NETWORK_INTERFACE extends SdkStructure
    {
        public byte[]           szName = new byte[MAX_NAME_LEN];      // 网络接口名称
        public byte[]           szIP = new byte[MAX_ADDRESS_LEN];     // ip地址
        public byte[]           szSubnetMask = new byte[MAX_ADDRESS_LEN]; // 子网掩码
        public byte[]           szDefGateway = new byte[MAX_ADDRESS_LEN]; // 默认网关
        public int              bDhcpEnable;                          // 是否开启DHCP
        public int              bDnsAutoGet;                          // DNS获取方式，dhcp使能时可以设置为true，支持通过dhcp获取
        public DNS_SERVERS[]    szDnsServersArr = (DNS_SERVERS[])new DNS_SERVERS().toArray(MAX_DNS_SERVER_NUM); // DNS服务器地址
        public int              nMTU;                                 // 网络最大传输单元
        public byte[]           szMacAddress = new byte[MAX_ADDRESS_LEN]; // mac地址
        public int              bInterfaceEnable;                     // 网络接口使能开关，表示该网口配置是否生效。不生效时，IP地址不设置到网卡上。
        public int              bReservedIPEnable;                    // DHCP失败时是否使用保留IP，使用保留IP时还继续发DHCP请求
        public int              emNetTranmissionMode;                 // 网络传输模式，默认adapt自适应模式, 对应枚举  CFG_ENUM_NET_TRANSMISSION_MODE
        public int              emInterfaceType;                      // 网口类型, 对应枚举  CFG_ENUM_NET_INTERFACE_TYPE
        public int              bBond;                                // 是否绑定虚拟网口,对应枚举  CFG_THREE_STATUS_BOOL
    }

    public static class DNS_SERVERS extends SdkStructure
    {
        public byte[]           szDnsServers = new byte[MAX_ADDRESS_LEN]; // DNS服务器地址
    }

    // 网络传输模式
    public static class CFG_ENUM_NET_TRANSMISSION_MODE extends SdkStructure
    {
        public static final int   CFG_ENUM_NET_MODE_ADAPT = 0;          // 自适应
        public static final int   CFG_ENUM_NET_MODE_HALF10M = 1;        // 10M半双工
        public static final int   CFG_ENUM_NET_MODE_FULL10M = 2;        // 10M全双工
        public static final int   CFG_ENUM_NET_MODE_HALF100M = 3;       // 100M半双工
        public static final int   CFG_ENUM_NET_MODE_FULL100M = 4;       // 100M全双工
    }

    // 网口类型
    public static class CFG_ENUM_NET_INTERFACE_TYPE extends SdkStructure
    {
        public static final int   CFG_ENUM_NET_INTERFACE_TYPE_UNKNOWN = 0; // 未知
        public static final int   CFG_ENUM_NET_INTERFACE_TYPE_STANDARD = 1; // 标准网口
        public static final int   CFG_ENUM_NET_INTERFACE_TYPE_MANAGER = 2; // 管理网口
        public static final int   CFG_ENUM_NET_INTERFACE_TYPE_EXTEND = 3; // 扩展网口
    }

    //三态布尔类型
    public static class CFG_THREE_STATUS_BOOL extends SdkStructure
    {
        public static final int   CFG_BOOL_STATUS_UNKNOWN = -1;         //未知
        public static final int   CFG_BOOL_STATUS_FALSE = 0;
        public static final int   CFG_BOOL_STATUS_TRUE = 1;
    }

    // RTMP配置
    public static class CFG_RTMP_INFO extends SdkStructure
    {
        public int              bEnable;                              // RTMP配置是否开启
        public byte[]           szAddr = new byte[MAX_ADDRESS_LEN];   // RTMP服务器地址
        public int              nPort;                                // RTMP服务器端口
        public int              nMainChnNum;                          // 主码流通道个数
        public int[]            szMainChannel = new int[AV_CFG_Max_Channel_Num]; // 启用主码流通道号列表:每个成员表示对应的通道需要上传到RTMP服务器,通道号从0开始
        public int              nExtraChnNum;                         // 辅码流通道个数
        public int[]            szExtraChannel = new int[AV_CFG_Max_Channel_Num]; // 启用辅码流通道号列表:每个成员表示对应的通道需要上传到RTMP服务器,通道号从0开始
        public byte[]           szCustomPath = new byte[MAX_ADDRESS_LEN]; // 路径名
        public byte[]           szStreamPath = new byte[MAX_ADDRESS_LEN]; // 码流路径前缀:不同通道以后缀数字区分
    }

    // 下载远程文件事件,对应  NET_ALARM_DOWNLOAD_REMOTE_FILE
    public static class ALARM_DOWNLOAD_REMOTE_FILE_INFO extends SdkStructure
    {
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX      stuTime;                              // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public byte[]           szURL = new byte[NET_COMMON_STRING_256]; // 下载文件对应的URL地址
        public int              nProgress;                            // 下载进度[0,100]
        public byte[]           byReserved = new byte[1020];          // 保留字节
    }

    // CLIENT_GetSplitWindowsInfo接口输入参数
    public static class NET_IN_SPLIT_GET_WINDOWS extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号

        public NET_IN_SPLIT_GET_WINDOWS() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetSplitWindowsInfo接口输出参数
    public static class NET_OUT_SPLIT_GET_WINDOWS extends SdkStructure
    {
        public int              dwSize;
        public NET_BLOCK_COLLECTION stuWindows;                       // 窗口信息

        public NET_OUT_SPLIT_GET_WINDOWS() {
            this.dwSize = this.size();
        }
    }

    // 融合屏通道信息
    public static class NET_COMPOSITE_CHANNEL extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szMonitorWallName = new byte[NET_DEVICE_NAME_LEN]; // 电视墙名称
        public byte[]           szCompositeID = new byte[NET_DEV_ID_LEN_EX]; // 融合屏ID
        public int              nVirtualChannel;                      // 虚拟通道号

        public NET_COMPOSITE_CHANNEL() {
            this.dwSize = this.size();
        }
    }

    // 电视墙
    public static class AV_CFG_MonitorWall extends SdkStructure
    {
        public int              nStructSize;
        public byte[]           szName = new byte[AV_CFG_Monitor_Name_Len]; // 名称
        public int              nLine;                                // 网络行数
        public int              nColumn;                              // 网格列数
        public int              nBlockCount;                          // 区块数量
        public AV_CFG_MonitorWallBlock[] stuBlocks = new AV_CFG_MonitorWallBlock[AV_CFG_Max_Block_In_Wall]; // 区块数组
        public int              bDisable;                             // 是否禁用, 0-该电视墙有效, 1-该电视墙无效
        public byte[]           szDesc = new byte[CFG_COMMON_STRING_256]; // 电视墙描述信息
        public int              nCoordinateType;                      //电视墙坐标系类型, 0:未知, 1:8192相对坐标系, 2:物理坐标系

        public AV_CFG_MonitorWall() {
            this.nStructSize = this.size();
            for(int i = 0; i < stuBlocks.length; i++){
                stuBlocks[i] = new AV_CFG_MonitorWallBlock();
            }
        }

        @Override
        public int fieldOffset(String name){
            return super.fieldOffset(name);
        }
    }

    // 电视墙区块
    public static class AV_CFG_MonitorWallBlock extends SdkStructure
    {
        public int              nStructSize;
        public int              nLine;                                // 单个TV占的网格行数
        public int              nColumn;                              // 单个TV占的网格列数
        public AV_CFG_Rect      stuRect = new AV_CFG_Rect();          // 区块的区域坐标
        public int              nTVCount;                             // TV数量
        public AV_CFG_MonitorWallTVOut[] stuTVs = new AV_CFG_MonitorWallTVOut[AV_CFG_Max_TV_In_Block]; // TV数组
        public TIME_SECTION_WEEK_DAY_6[] stuTimeSectionWeekDay = new TIME_SECTION_WEEK_DAY_6[WEEK_DAY_NUM]; // 开关机时间
        public byte[]           szName = new byte[AV_CFG_Channel_Name_Len]; // 区块名称
        public byte[]           szCompositeID = new byte[AV_CFG_Device_ID_Len]; // 融合屏ID
        public byte[]           szBlockType = new byte[NET_COMMON_STRING_32]; // 显示单元组类型,为支持由接收卡组成单元的小间距LED区块而增加该字段,其他类型的区块填写为"LCD",如不存在该字段,默认采用LCD
        public int              nOutputDelay;                         // 输出延迟,单位：毫秒

        public AV_CFG_MonitorWallBlock() {
            this.nStructSize = this.size();
            for(int i = 0; i < stuTVs.length; i++){
                stuTVs[i] = new AV_CFG_MonitorWallTVOut();
            }
            for(int i = 0; i < stuTimeSectionWeekDay.length; i++){
                stuTimeSectionWeekDay[i] = new TIME_SECTION_WEEK_DAY_6();
            }
        }

        @Override
        public int fieldOffset(String name){
            return super.fieldOffset(name);
        }
    }

    // 区域
    public static class AV_CFG_Rect extends SdkStructure
    {
        public int              nStructSize;
        public int              nLeft;
        public int              nTop;
        public int              nRight;
        public int              nBottom;

        public AV_CFG_Rect() {
            this.nStructSize = this.size();
        }
    }

    // 电视墙输出通道信息
    public static class AV_CFG_MonitorWallTVOut extends SdkStructure
    {
        public int              nStructSize;
        public byte[]           szDeviceID = new byte[AV_CFG_Device_ID_Len]; // 设备ID, 为空或"Local"表示本地设备
        public int              nChannelID;                           // 通道ID
        public byte[]           szName = new byte[AV_CFG_Channel_Name_Len]; // 屏幕名称
        public int              bIsVirtual;                           // 是否是虚拟屏（虚拟屏表示在本设备上不存在的屏）TRUE:虚拟屏 FALSE:非虚拟屏
        public byte[]           szAddress = new byte[40];             // 归属设备地址IP
        public AV_CFG_MONITOR_WALL_OUT_MODE_INFO stuOutMode = new AV_CFG_MONITOR_WALL_OUT_MODE_INFO(); // 输出模式信息
        public int              nPosX;                                //相对物理坐标系下水平方向起始位置
        public int              nPosY;                                //相对物理坐标系下垂直方向起始位置

        public AV_CFG_MonitorWallTVOut() {
            this.nStructSize = this.size();
        }
    }

    // CLIENT_OpenSplitWindow接口输入参数(开窗)
    public static class NET_IN_SPLIT_OPEN_WINDOW extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 通道号(屏号)
        public DH_RECT          stuRect;                              // 窗口位置, 0~8192
        public int              bDirectable;                          // 坐标是否满足直通条件, 直通是指拼接屏方式下,此窗口区域正好为物理屏区域

        public NET_IN_SPLIT_OPEN_WINDOW() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_OpenSplitWindow接口输出参数(开窗)
    public static class NET_OUT_SPLIT_OPEN_WINDOW extends SdkStructure
    {
        public int              dwSize;
        public int              nWindowID;                            // 窗口序号
        public int              nZOrder;                              // 窗口次序

        public NET_OUT_SPLIT_OPEN_WINDOW() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_CloseSplitWindow接口输入参数(关窗)
    public static class NET_IN_SPLIT_CLOSE_WINDOW extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 输出通道号或融合屏虚拟通道号, pszCompositeID为NULL时有效
        public int              nWindowID;                            // 窗口序号
        public String           pszCompositeID;                       // 融合屏ID

        public NET_IN_SPLIT_CLOSE_WINDOW() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_CloseSplitWindow接口输出参数(关窗)
    public static class NET_OUT_SPLIT_CLOSE_WINDOW extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SPLIT_CLOSE_WINDOW() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetGroupInfoForChannel接口输入参数
    public static class NET_IN_GET_GROUPINFO_FOR_CHANNEL extends SdkStructure
    {
        public int              dwSize;
        public int              nChannelID;                           // 通道号

        public NET_IN_GET_GROUPINFO_FOR_CHANNEL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_GetGroupInfoForChannel接口输出参数
    public static class NET_OUT_GET_GROUPINFO_FOR_CHANNEL extends SdkStructure
    {
        public int              dwSize;
        public int              nGroupIdNum;                          // 人员组数
        public GROUP_ID[]       szGroupIdArr = (GROUP_ID[])new GROUP_ID().toArray(MAX_GOURP_NUM); // 人员组ID
        public int              nSimilaryNum;                         // 相似度阈值个数, 与人员组数相同
        public int[]            nSimilary = new int[MAX_GOURP_NUM];   // 每个人脸组的相似度阈值, 0-100

        public NET_OUT_GET_GROUPINFO_FOR_CHANNEL() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FaceRecognitionPutDisposition 接口输入参数
    public static class NET_IN_FACE_RECOGNITION_PUT_DISPOSITION_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; // 人员组ID
        public int              nDispositionChnNum;                   // 布控视频通道个数
        public NET_DISPOSITION_CHANNEL_INFO[] stuDispositionChnInfo = (NET_DISPOSITION_CHANNEL_INFO[])new NET_DISPOSITION_CHANNEL_INFO().toArray(NET_MAX_CAMERA_CHANNEL_NUM); // 布控视频通道信息

        public NET_IN_FACE_RECOGNITION_PUT_DISPOSITION_INFO() {
            this.dwSize = this.size();
        }
    }

    // 布控的视频通道信息
    public static class NET_DISPOSITION_CHANNEL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 视频通道号
        public int              nSimilary;                            // 相似度阈值, 0-100
        public byte[]           bReserved = new byte[256];            // 保留
    }

    // CLIENT_FaceRecognitionPutDisposition 接口输出参数
    public static class NET_OUT_FACE_RECOGNITION_PUT_DISPOSITION_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nReportCnt;                           // 通道布控结果个数
        public int[]            bReport = new int[NET_MAX_CAMERA_CHANNEL_NUM]; // 通道布控结果, TRUE追加成功, FALSE追加失败

        public NET_OUT_FACE_RECOGNITION_PUT_DISPOSITION_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FaceRecognitionDelDisposition 接口输入参数
    public static class NET_IN_FACE_RECOGNITION_DEL_DISPOSITION_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szGroupId = new byte[NET_COMMON_STRING_64]; // 人员组ID
        public int              nDispositionChnNum;                   // 撤控视频通道个数
        public int[]            nDispositionChn = new int[NET_MAX_CAMERA_CHANNEL_NUM]; // 撤控视频通道列表

        public NET_IN_FACE_RECOGNITION_DEL_DISPOSITION_INFO() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_FaceRecognitionDelDisposition 接口输出参数
    public static class NET_OUT_FACE_RECOGNITION_DEL_DISPOSITION_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nReportCnt;                           // 通道布控结果个数
        public int[]            bReport = new int[NET_MAX_CAMERA_CHANNEL_NUM]; // 通道布控结果, TRUE删除成功, FALSE删除失败

        public NET_OUT_FACE_RECOGNITION_DEL_DISPOSITION_INFO() {
            this.dwSize = this.size();
        }
    }

    // 人证比对事件，用实时拍摄的人脸照片，和该人持有的证件照片进行比对，并上报检测结果
    // 对应事件类型为 EVENT_IVS_CITIZEN_PICTURE_COMPARE
    public static class DEV_EVENT_CITIZEN_PICTURE_COMPARE_INFO extends SdkStructure
    {
        //公共字段
        public int              nChannelID;                           // 通道号,从0开始
        public int              nEventAction;                         // 事件动作, 0表示脉冲, -1表示未知
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public NET_TIME_EX      stuUTC;                               // 事件发生的时间
        public int              nEventID;                             // 事件ID
        //事件对应字段
        public byte             bCompareResult;                       // 人证比对结果,相似度大于等于阈值认为比对成功,1-表示成功,0-表示失败
        public byte             nSimilarity;                          // 两张图片的相似度,单位百分比,范围[1,100]
        public byte             nThreshold;                           // 检测阈值,范围[1,100]
        public int              emSex;                                // 性别, 参考  EM_CITIZENIDCARD_SEX_TYPE
        public int              nECType;                              // 民族(参照DEV_EVENT_ALARM_CITIZENIDCARD_INFO的nECType定义)
        public byte[]           szCitizen = new byte[NET_COMMON_STRING_64]; // 居民姓名
        public byte[]           szAddress = new byte[NET_COMMON_STRING_256]; // 住址
        public byte[]           szNumber = new byte[NET_COMMON_STRING_64]; // 证件号
        public byte[]           szAuthority = new byte[NET_COMMON_STRING_256]; // 签发机关
        public NET_TIME         stuBirth;                             // 出生日期(年月日)
        public NET_TIME         stuValidityStart;                     // 有效期限起始日期(年月日)
        public int              bLongTimeValidFlag;                   // 该值为 TRUE, 截止日期 表示长期有效,此时 stuValidityEnd 值无意义
        // 该值为 FALSE, 此时 截止日期 查看 stuValidityEnd 值
        public NET_TIME         stuValidityEnd;                       // 有效期限结束日期(年月日)
        public CITIZEN_PICTURE_COMPARE_IMAGE_INFO[] stuImageInfo = (CITIZEN_PICTURE_COMPARE_IMAGE_INFO[])new CITIZEN_PICTURE_COMPARE_IMAGE_INFO().toArray(2); // 图片信息，第一张为拍摄照片，第二张为证件照片
        public byte[]           szCardNo = new byte[NET_COMMON_STRING_32]; // IC卡号
        public byte[]           szCellPhone = new byte[NET_COMMON_STRING_20]; // 手机号（比对时先输入手机号）
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public CITIZEN_PICTURE_COMPARE_IMAGE_INFO_EX[] stuImageInfoEx = (CITIZEN_PICTURE_COMPARE_IMAGE_INFO_EX[])new CITIZEN_PICTURE_COMPARE_IMAGE_INFO_EX().toArray(6); // 图片扩展信息
        public byte[]           szCallNumber = new byte[20];          // 呼叫号码
        public int              emDoorOpenMethod;                     // 开门方式（人证照片或者人证信息） 参考 NET_ACCESS_DOOROPEN_METHOD
        public int              nEventGroupID;                        // 事件ID,用于不同事件进行关联
        public int              nEventType;                           // 事件类型：0：人证比对结果	1：人证人脸采集 2：访客登记 3：人脸权限下发 4：人证人脸底库查询
        public byte[]           szUserID = new byte[32];              // 人证人脸采集时人员ID
        public byte[]           szBuildingNo = new byte[16];          // 楼号
        public byte[]           szBuildingUnitNo = new byte[16];      // 单元号
        public byte[]           szBuildingRoomNo = new byte[16];      // 房间号
        public int              nFaceIndex;                           // 人脸序号
        public int              emMask;                               // 口罩状态 EM_MASK_STATE_TYPE
        public int              bManTemperature;                      // 人员温度信息是否有效
        public NET_MAN_TEMPERATURE_INFO stuManTemperatureInfo;        // 人员温度信息, bManTemperature 为TRUE 时有效
        public double           dbBulkOilQuantity;                    // 散装油量
        public int              nScore;                               // 人脸质量评分
        public Pointer          pstuCardNoArray;                      // 卡号数组信息,对应NET_CARDNOARRAY_INFO
        public Pointer          pstuFingerPrint;                      // 信息数组信息,对应NET_FINGERPRINT_INFO
        public byte[]           szIDPhysicalNumber = new byte[20];    // 物理证件号（证件序列号）
        //public byte[]	 	byReserved = new byte[24];	// 预留字节
        public int              emCardType;                           //卡类型,参考EM_CARD_TYPE
        public int              nCardTypeNum;                         // 卡类型数组个数
        public int[]            arrCardTypeArray = new int[5];        // 卡类型数组
        public int              nVisitorNumber;                       // 访客人数
        public byte[]           szTrafficPlate = new byte[32];        // 访客车牌
        public byte[]           szRespondentsName = new byte[32];     // 被访者者姓名，人证登记场景使用
        public byte[]           szStudentNum = new byte[32];          // 用于记录学生学号默认”FFFFFF”,若为学工号上报，则填对应学工号信息
    }

    // 人证对比图片信息
    public static class CITIZEN_PICTURE_COMPARE_IMAGE_INFO extends SdkStructure
    {
        public int              dwOffSet;                             // 文件在二进制数据块中的偏移位置, 单位:字节
        public int              dwFileLenth;                          // 文件大小, 单位:字节
        public short            wWidth;                               // 图片宽度, 单位:像素
        public short            wHeight;                              // 图片高度, 单位:像素
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 人证对比扩展图片信息
    public static class CITIZEN_PICTURE_COMPARE_IMAGE_INFO_EX extends SdkStructure
    {
        public int              emType;                               // 图片类型, 参考枚举  CITIZEN_PICTURE_COMPARE_TYPE
        public int              dwOffSet;                             // 文件在二进制数据块中的偏移位置, 单位:字节
        public int              dwFileLenth;                          // 文件大小, 单位:字节
        public short            wWidth;                               // 图片宽度, 单位:像素
        public short            wHeight;                              // 图片高度, 单位:像素
        public byte[]           byReserved = new byte[64];            // 保留字节
    }

    // 图片类型
    public static class CITIZEN_PICTURE_COMPARE_TYPE extends SdkStructure
    {
        public static final int   CITIZEN_PICTURE_COMPARE_TYPE_UNKNOWN = -1;
        public static final int   CITIZEN_PICTURE_COMPARE_TYPE_LOCAL = 0; // 本地人脸库图
        public static final int   CITIZEN_PICTURE_COMPARE_TYPE_FACEMAP = 1; // 拍摄场景图
    }

    public static class EM_CITIZENIDCARD_SEX_TYPE extends SdkStructure
    {
        public static final int   EM_CITIZENIDCARD_SEX_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_CITIZENIDCARD_SEX_TYPE_MALE = 1;   // 男
        public static final int   EM_CITIZENIDCARD_SEX_TYPE_FEMALE = 2; // 女
        public static final int   EM_CITIZENIDCARD_SEX_TYPE_UNTOLD = 3; // 未说明
    }

    /**
     * 事件类型 EVENT_IVS_HUMANTRAIT(人体特征事件)对应的数据块描述信息
     */
    public static class DEV_EVENT_HUMANTRAIT_INFO extends SdkStructure {
        /**
         *  通道号
         */
        public int              nChannelID;
        /**
         *  事件名称
         */
        public byte             szName[] = new byte[SDK_EVENT_NAME_LEN];
        /**
         *  事件ID
         */
        public int              nEventID;
        /**
         *  时间戳(单位是毫秒)
         */
        public double           PTS;
        /**
         *  事件发生的时间
         */
        public NET_TIME_EX      UTC = new NET_TIME_EX();
        /**
         *  1:开始 2:停止
         */
        public int              nAction;
        /**
         *  智能事件所属大类
         */
        public int              emClassType;
        /**
         *  事件组ID，一次检测的多个人体特征nGroupID相同
         */
        public int              nGroupID;
        /**
         *  一个事件组内的抓拍张数(人体个数),一次检测的多个人体特征nCountInGroup相同
         */
        public int              nCountInGroup;
        /**
         *  一个事件组内的抓拍序号，从1开始
         */
        public int              nIndexInGroup;
        /**
         *  人体图片信息
         */
        public HUMAN_IMAGE_INFO stuHumanImage = new HUMAN_IMAGE_INFO();
        /**
         *  人脸图片信息
         */
        public FACE_IMAGE_INFO  stuFaceImage = new FACE_IMAGE_INFO();
        /**
         *  检测到的人的信息
         */
        public int              emDetectObject;
        /**
         *  人体属性
         */
        public HUMAN_ATTRIBUTES_INFO stuHumanAttributes = new HUMAN_ATTRIBUTES_INFO();
        /**
         *  全景大图信息
         */
        public SCENE_IMAGE_INFO stuSceneImage = new SCENE_IMAGE_INFO();
        /**
         *  人脸属性
         */
        public NET_FACE_ATTRIBUTE stuFaceAttributes = new NET_FACE_ATTRIBUTE();
        /**
         *  人脸全景图
         */
        public FACE_SCENE_IMAGE stuFaceSceneImage = new FACE_SCENE_IMAGE();
        /**
         *  扩展信息
         */
        public NET_EXTENSION_INFO stuExtensionInfo = new NET_EXTENSION_INFO();
        /**
         *  补充事件，表示当前人体特征是由该事件产生的
         */
        public NET_HUMANTRAIT_EXTENSION_INFO stuHumanTrait = new NET_HUMANTRAIT_EXTENSION_INFO();
        /**
         *  人体特征值数据在二进制数据中的位置信息
         */
        public NET_HUMAN_FEATURE_VECTOR_INFO stuHumanFeatureVectorInfo = new NET_HUMAN_FEATURE_VECTOR_INFO();
        /**
         *  人体特征值版本号
         */
        public int              emHumanFeatureVersion;
        /**
         *  人脸特征值数据在二进制数据中的位置信息
         */
        public NET_FACE_FEATURE_VECTOR_INFO stuFaceFeatureVectorInfo = new NET_FACE_FEATURE_VECTOR_INFO();
        /**
         *  人脸特征值版本号
         */
        public int              emFaceFeatureVersion;
        /**
         *  合规标记0：不合规，1：合规,没有开启合规检测，无此字段。     此字段已废弃
         */
        public int              nCompliantMark;
        /**
         *  判断是否合规的属性列表个数
         */
        public int              nCompliantDetailsNum;
        /**
         *  判断是否合规的属性列表，不合规检测报不合规的属性字段，合规检测报合规的属性字段，没有开启合规检测，无此字段。
         */
        public int[]            emCompliantDetailType = new int[32];
        /**
         *  关联的人体消息类型列表个数
         */
        public int              nHumanPostureTypeNum;
        /**
         * 关联的人体消息类型列表
         */
        public int              emHumanPostureType[] = new int[32];
        /**
         * 与最优人脸同画面的人体信息，emHumanPostureType包含EM_HUMAN_POSTURE_ALONG_WITH_FACE时有效
         */
        public HUMAN_IMAGE_INFO stuAlongWithFaceHumanImage = new HUMAN_IMAGE_INFO();
        /**
         * 与最优人脸同画面人体的全景图，emHumanPostureType包含EM_HUMAN_POSTURE_ALONG_WITH_FACE时有效
         */
        public SCENE_IMAGE_INFO stuAlongWithFaceHumanSceneImage = new SCENE_IMAGE_INFO();
        /**
         * 与最优人脸同画面人体的属性，emHumanPostureType包含EM_HUMAN_POSTURE_ALONG_WITH_FACE时有效
         */
        public HUMAN_ATTRIBUTES_INFO stuAlongWithFaceHumanAttributes = new HUMAN_ATTRIBUTES_INFO();
        /**
         *  是否开启合规检测
         */
        public int              bCompliantMarkEnable;
        /**
         *  人体特征值数据在二进制数据中的位置信息
         */
        public NET_HUMAN_FEATURE_VECTOR_INFO stuAlongWithFaceHumanVectorInfo = new NET_HUMAN_FEATURE_VECTOR_INFO();
        /**
         *  人体特征值版本号
         */
        public int              emAlongWithFaceHumanVersion;
        /**
         *  合规模式: 0-不合规，1-合规，没有开启合规检测，无此字段
         */
        public int              nCompliantMode;
        /**
         *  1:不合规 2:合规
         */
        public int              nAlarmCompliance;
        /**
         *  目标在视频中出现时的视频帧编号。视频帧编号只能在一个通道内表示唯一，且不连续。视频流时该值有效
         */
        public int              nStartSequence;
        /**
         *  目标在视频中消失时的视频帧编号。视频帧编号只能在一个通道内表示唯一，且不连续。视频流时该值有效。
         */
        public int              nEndSequence;
        /**
         *  图像成像光源类型
         */
        public int              emImageLightType;
        /**
         *  stuHumanAttributes 的扩展
         */
        public HUMAN_ATTRIBUTES_INFO_EX stuHumanAttributesEx = new HUMAN_ATTRIBUTES_INFO_EX();
        /**
         *  stuAlongWithFaceHumanAttributes 的扩展
         */
        public HUMAN_ATTRIBUTES_INFO_EX stuAlongWithFaceHumanAttributesEx = new HUMAN_ATTRIBUTES_INFO_EX();
        /**
         *  图片信息数组
         */
        public NET_IMAGE_INFO_EX2[] stuImageInfo = new NET_IMAGE_INFO_EX2[32];
        /**
         *  图片信息个数
         */
        public int              nImageInfoNum;
        /**
         *  智能物体全局唯一物体标识
         */
        public  byte[]          szObjectUUID = new byte[48];
        /**
         *  人体特征值版本号-字符串
         */
        public  byte[]          szHumanFeatureVersion = new byte[32];
        /**
         *  目标特征值版本号-字符串
         */
        public  byte[]          szFaceFeatureVersion = new byte[32];
        /**
         *  人体特征值版本号-字符串
         */
        public  byte[]          szAlongWithFaceHumanVersion = new byte[32];
        /**
         *  事件公共扩展字段结构体
         */
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
        /**
         *  有效数据位21位，包含’\0’
         *  前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
         *  中间14位YYYYMMDDhhmmss:年月日时分秒
         *  后5位%u%u%u%u%u：物体ID，如00001
         */
        public byte[]           szSerialUUID = new byte[22];
        /**
         *  对齐
         */
        public byte[]           szReserved = new byte[2];
        /**
         *  图片信息数组  {@link NET_IMAGE_INFO_EX3}
         */
        public  Pointer         pstuImageInfo;
        /**
         *  检测物体具体包含的信息类型: 0: 只包含属性 1: 只包含特征值 2: 属性、特征值都包含 3:属性和特征值都不包含
         */
        public  int             nDetectMode;
        /**
         *  保留字节
         */
        public byte             byReserved[] = new byte[852-POINTERSIZE];

        public DEV_EVENT_HUMANTRAIT_INFO(){

            for(int i=0;i<stuImageInfo.length;i++){
                stuImageInfo[i]=new NET_IMAGE_INFO_EX2();
            }

        }
    }

    // 人体图片信息
    public static class HUMAN_IMAGE_INFO extends SdkStructure
    {
        public int              nOffSet;                              // 偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public int              nIndexInData;                         // 在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[52];            // 预留字节
    }

    // 人脸图片信息
    public static class FACE_IMAGE_INFO extends SdkStructure
    {
        public int              nOffSet;                              // 偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[52];            // 预留字节
    }

    // 检测到的人的信息
    public static class EM_DETECT_OBJECT extends SdkStructure
    {
        public static final int   EM_DETECT_OBJECT_UNKNOWN = 0;         // 未知信息
        public static final int   EM_DETECT_OBJECT_HUMAN_BODY_AND_FACE = 1; // 人体和人脸都有
        public static final int   EM_DETECT_OBJECT_HUMAN_BODY = 2;      // 仅有人体
        public static final int   EM_DETECT_OBJECT_HUMAN_FACE = 3;      // 仅有人脸
    }

    // 角度
    public static class EM_ANGLE_TYPE extends SdkStructure
    {
        public static final int   EM_ANGLE_UNKNOWN = 0;                 // 未知
        public static final int   EM_ANGLE_FRONT = 1;                   // 正面
        public static final int   EM_ANGLE_SIDE = 2;                    // 侧面
        public static final int   EM_ANGLE_BACK = 3;                    // 背面
    }

    // 是否打伞
    public static class EM_HAS_UMBRELLA extends SdkStructure
    {
        public static final int   EM_HAS_UMBRELLA_UNKNOWN = 0;          // 未知
        public static final int   EM_HAS_UMBRELLA_NO = 1;               // 未打伞
        public static final int   EM_HAS_UMBRELLA_YES = 2;              // 打伞
    }

    // 包类型
    public static class EM_BAG_TYPE extends SdkStructure
    {
        public static final int   EM_BAG_UNKNOWN = 0;                   // 未知
        public static final int   EM_BAG_HANDBAG = 1;                   // 手提包
        public static final int   EM_BAG_SHOULDERBAG = 2;               // 肩包
        public static final int   EM_BAG_KNAPSACK = 3;                  // 背包
        public static final int   EM_BAG_DRAWBARBOX = 4;                // 拉杆箱
    }

    // 上半身衣服图案
    public static class EM_CLOTHES_PATTERN extends SdkStructure
    {
        public static final int   EM_CLOTHES_PATTERN_UNKNOWN = 0;       // 未知
        public static final int   EM_CLOTHES_PATTERN_PURE = 1;          // 纯色
        public static final int   EM_CLOTHES_PATTERN_STRIPE = 2;        // 条纹
        public static final int   EM_CLOTHES_PATTERN_PATTERN = 3;       // 图案
        public static final int   EM_CLOTHES_PATTERN_GAP = 4;           // 缝隙
        public static final int   EM_CLOTHES_PATTERN_LATTICE = 5;       // 格子
    }

    // 头发样式
    public static class EM_HAIR_STYLE extends SdkStructure
    {
        public static final int   EM_HAIR_UNKNOWN = 0;                  // 未知
        public static final int   EM_HAIR_LONG_HAIR = 1;                // 长发
        public static final int   EM_HAIR_SHORT_HAIR = 2;               // 短发
        public static final int   EM_HAIR_PONYTAIL = 3;                 // 马尾
        public static final int   EM_HAIR_UPDO = 4;                     // 盘发
        public static final int   EM_HAIR_HEAD_BLOCKED = 5;             // 头部被遮挡
    }

    // 帽类型
    public static class EM_CAP_TYPE extends SdkStructure
    {
        public static final int   EM_CAP_UNKNOWN = 0;                   // 未知
        public static final int   EM_CAP_ORDINARY = 1;                  // 普通帽子
        public static final int   EM_CAP_HELMET = 2;                    // 头盔
        public static final int   EM_CAP_SAFE = 3;                      // 安全帽
    }

    // 人体属性信息
    public static class HUMAN_ATTRIBUTES_INFO extends SdkStructure
    {
        public int              emCoatColor;                          // 上衣颜色, 详见 EM_CLOTHES_COLOR
        public int              emCoatType;                           // 上衣类型, 详见 EM_COAT_TYPE
        public int              emTrousersColor;                      // 裤子颜色, 详见 EM_CLOTHES_COLOR
        public int              emTrousersType;                       // 裤子类型, 详见 EM_TROUSERS_TYPE
        public int              emHasHat;                             // 是否戴帽子, 详见 EM_HAS_HAT
        public int              emHasBag;                             // 是否带包, 详见 EM_HAS_BAG
        public NET_RECT         stuBoundingBox;                       // 包围盒(8192坐标系)
        public int              nAge;                                 // 年龄
        public int              emSex;                                // 性别, 详见 EM_SEX_TYPE
        public int              emAngle;                              // 角度, 详见 EM_ANGLE_TYPE
        public int              emHasUmbrella;                        // 是否打伞, 详见 EM_HAS_UMBRELLA
        public int              emBag;                                // 包类型 , 详见EM_BAG_TYPE
        public int              emUpperPattern;                       // 上半身衣服图案, 详见EM_CLOTHES_PATTERN
        public int              emHairStyle;                          // 头发样式, 详见EM_HAIR_STYLE
        public int              emCap;                                // 帽类型, 详见EM_CAP_TYPE
        public NET_POINT        stuHumanCenter = new NET_POINT();     //人体型心(不是包围盒中心), 0-8191相对坐标, 相对于大图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_POINT}
        public int              emHasVest;                            //是否有反光背心;,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HAS_VEST}
        public int              emHasBadge;                           //是否佩戴工牌,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HAS_BADGE}
        public int              emHasBabyCarriage;                    //是否推婴儿车,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HAS_BABYCARRIAGE}
        public int              emIsErrorDetect;                      //是否虚检（背景误检，仅头，仅下半身都会判定为虚检）,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_IS_ERRORDETECT}
        public int              emHasHead;                            //人体部位是否有头,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HAS_HEAD}
        public int              emHasDownBody;                        //人体部位是否有下半身,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HAS_DOWNBODY}
        public int              nAngleConf;                           //姿态置信度，质量评估总分0到100
        public int              nUpColorConf;                         //上衣颜色置信度，质量评估总分0到100
        public int              nDownColorConf;                       //下衣颜色置信度，质量评估总分0到100
        public int              nGenderConf;                          //性别置信度，质量评估总分0到100
        public int              nAgeConf;                             //年龄段置信度，质量评估总分0到100
        public int              nHatTypeConf;                         //帽子类型置信度，质量评估总分0到100
        public int              nUpTypeConf;                          //上衣种类置信度，质量评估总分0到100
        public int              nDownTypeConf;                        //下衣种类置信度，质量评估总分0到100
        public int              nHairTypeConf;                        //发型种类置信度，质量评估总分0到100
        public int              nHasHeadConf;                         //人体部位是否有头的置信度，质量评估总分0到100
        public int              nHasDownBodyConf;                     //人体部位是否有下半身置信度，质量评估总分0到100
        public int              nUniformStyleConf;                    //制服类型置信度，质量评估总分0到100
        public byte             nCoatType;                            //上衣类型，emCoatType实现和协议不一致，兼容处理，, 0:未知 1:长袖 2:短袖 3:长款大衣 4:夹克及牛仔服 5:T恤；, 6:运动装 7:羽绒服 8:衬衫 9:连衣裙 10:西装 11:毛衣 12:无袖 13:背心
        public byte[]           byReserved = new byte[3];             // 预留字节
    }

    // 衣服颜色
    public static class EM_CLOTHES_COLOR extends SdkStructure
    {
        public static final int   EM_CLOTHES_COLOR_UNKNOWN = 0;         // 未知
        public static final int   EM_CLOTHES_COLOR_WHITE = 1;           // 白色
        public static final int   EM_CLOTHES_COLOR_ORANGE = 2;          // 橙色
        public static final int   EM_CLOTHES_COLOR_PINK = 3;            // 粉色
        public static final int   EM_CLOTHES_COLOR_BLACK = 4;           // 黑色
        public static final int   EM_CLOTHES_COLOR_RED = 5;             // 红色
        public static final int   EM_CLOTHES_COLOR_YELLOW = 6;          // 黄色
        public static final int   EM_CLOTHES_COLOR_GRAY = 7;            // 灰色
        public static final int   EM_CLOTHES_COLOR_BLUE = 8;            // 蓝色
        public static final int   EM_CLOTHES_COLOR_GREEN = 9;           // 绿色
        public static final int   EM_CLOTHES_COLOR_PURPLE = 10;         // 紫色
        public static final int   EM_CLOTHES_COLOR_BROWN = 11;          // 棕色
        public static final int   EM_CLOTHES_COLOR_OTHER = 13;          // 其他颜色
    }

    //上衣类型
    public static class EM_COAT_TYPE extends SdkStructure
    {
        public static final int   EM_COAT_TYPE_UNKNOWN = 0;             // 未知
        public static final int   EM_COAT_TYPE_LONG_SLEEVE = 1;         // 长袖
        public static final int   EM_COAT_TYPE_COTTA = 2;               // 短袖
    }

    // 裤子类型
    public static class EM_TROUSERS_TYPE extends SdkStructure
    {
        public static final int   EM_TROUSERS_TYPE_UNKNOWN = 0;         // 未知
        public static final int   EM_TROUSERS_TYPE_TROUSERS = 1;        // 长裤
        public static final int   EM_TROUSERS_TYPE_SHORTS = 2;          // 短裤
        public static final int   EM_TROUSERS_TYPE_SKIRT = 3;           // 裙子
    }

    // 是否戴帽子
    public static class EM_HAS_HAT extends SdkStructure
    {
        public static final int   EM_HAS_HAT_UNKNOWN = 0;               // 未知
        public static final int   EM_HAS_HAT_NO = 1;                    // 不戴帽子
        public static final int   EM_HAS_HAT_YES = 2;                   // 戴帽子
    }

    // 是否戴包(包括背包或拎包)
    public static class EM_HAS_BAG extends SdkStructure
    {
        public static final int   EM_HAS_BAG_UNKNOWN = 0;               // 未知
        public static final int   EM_HAS_BAG_NO = 1;                    // 不带包
        public static final int   EM_HAS_BAG_YES = 2;                   // 带包
    }

    // 全景广角图
    public static class SCENE_IMAGE_INFO extends SdkStructure
    {
        public int              nOffSet;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度(像素)
        public int              nHeight;                              // 图片高度(像素)
        public int              nIndexInData;                         // 在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[52];            // 预留字节
    }

    // 人脸属性
    public static class NET_FACE_ATTRIBUTE extends SdkStructure
    {
        public int              emSex;                                // 性别, 详见 EM_DEV_EVENT_FACEDETECT_SEX_TYPE
        public int              nAge;                                 // 年龄,-1表示该字段数据无效
        public int              nFeatureValidNum;                     // 人脸特征数组有效个数,与 emFeature 结合使用
        public int[]            emFeatures = new int[NET_MAX_FACEDETECT_FEATURE_NUM]; // 人脸特征数组,与 nFeatureValidNum 结合使用 , 详见 EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE
        public byte[]           szReserved = new byte[4];             //
        public int              emEye;                                // 眼睛状态, 详见 EM_EYE_STATE_TYPE
        public int              emMouth;                              // 嘴巴状态, 详见 EM_MOUTH_STATE_TYPE
        public int              emMask;                               // 口罩状态, 详见 EM_MASK_STATE_TYPE
        public int              emBeard;                              // 胡子状态, 详见 EM_BEARD_STATE_TYPE
        public int              nAttractive;                          // 魅力值, 0未识别，识别时范围1-100,得分高魅力高
        public NET_RECT         stuBoundingBox;                       // 包围盒(8192坐标系)
        public NET_EULER_ANGLE  stuFaceCaptureAngle = new NET_EULER_ANGLE(); //目标在抓拍图片中的角度信息, nPitch:抬头低头的俯仰角, nYaw左右转头的偏航角, nRoll头在平面内左偏右偏的翻滚角, 角度值取值范围[-90,90], 三个角度值都为999表示此角度信息无效,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_EULER_ANGLE}
        public int              nFaceQuality;                         //目标抓拍质量分数,范围 0~10000
        public int              nFaceAlignScore;                      //目标对齐得分分数,范围 0~10000,-1为无效值
        public int              nFaceClarity;                         //目标清晰度分数,范围 0~10000,-1为无效值
        public NET_POINT        stuFaceCenter = new NET_POINT();      //目标型心(不是包围盒中心), 0-8191相对坐标, 相对于大图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_POINT}
        public int              emGlass;                              //是否戴眼镜,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.EM_FACEDETECT_GLASSES_TYPE}
        public int              nFaceDetectConf;                      //目标检测置信度，取值0~100
        public NET_FACE_ORIGINAL_SIZE stuOriginalSize = new NET_FACE_ORIGINAL_SIZE(); //算法目标分析时的实际目标图片尺寸, 宽高为0时无效,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_FACE_ORIGINAL_SIZE}
        public int[]            arrAngleStatus = new int[3];          //目标抓拍角度(Angle)状态
        public int              nIlluminationScore;                   //目标光照值,范围[0,255]
        public byte             nLeftEyeCoverConf;                    //目标左眼遮挡置信度,范围[0,100]
        public byte             nLeftCheekCoverConf;                  //目标左脸颊遮挡置信度,范围[0,100]
        public byte             nMouthCoverConf;                      //目标嘴巴遮挡置信度,范围[0,100]
        public byte             nRightEyeCoverConf;                   //目标右眼遮挡置信度,范围[0,100]
        public byte             nRightCheekCoverConf;                 //目标右脸颊遮挡置信度,范围[0,100]
        public byte             nChinCoverConf;                       //目标下巴遮挡置信度,范围[0,100]
        public byte             nIsCompleteFace;                      //目标完整度评价 目标完整度评价，取值0和1 0为不完整（轮廓超出图像边界） 1为完整
        public byte             nSaturationScore;                     //目标图片饱和度评分,范围[0,100]
        public byte             nBrowCoverConf;                       //目标额头遮挡置信度,范围[0,100]
        public byte             nNoseCoverConf;                       //目标鼻子遮挡置信度,范围[0,100]
        public byte[]           bReserved0 = new byte[2];             //保留字节,留待扩展
        public int              emAgeSeg;                             //年龄段,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_AGE_SEG}
        public byte[]           bReserved = new byte[36];             // 保留字节,留待扩展.
    }

    // 人脸全景图
    public static class FACE_SCENE_IMAGE extends SdkStructure
    {
        public int              nOffSet;                              // 在二进制数据块中的偏移
        public int              nLength;                              // 图片大小,单位字节
        public int              nWidth;                               // 图片宽度(像素)
        public int              nHeight;                              // 图片高度(像素)
        public int              nIndexInData;                         //在上传图片数据中的图片序号
        public byte[]           byReserved = new byte[52];            // 预留字节
    }

    // 事件扩展信息
    public static class NET_EXTENSION_INFO extends SdkStructure
    {
        public byte[]           szEventID = new byte[MAX_EVENT_ID_LEN]; // 国标事件ID
        public byte[]           byReserved = new byte[80];            // 保留字节
    }

    // 当前人体特征是由什么事件产生的
    public static class NET_HUMANTRAIT_EXTENSION_INFO extends SdkStructure
    {
        public byte[]           szAdditionalCode = new byte[MAX_HUMANTRAIT_EVENT_LEN]; // 当前人体特征是由什么事件产生的,设备刚好返回32个字节数据，多加4个字节用于字节对齐和添加字符结束符
        public byte[]           byReserved = new byte[32];            // 保留字节
    }

    // 事件类型 NET_ALARM_WIFI_SEARCH (搜索WIFI设备)对应的数据描述信息
    public static class ALARM_WIFI_SEARCH_INFO extends SdkStructure
    {
        public int              nWifiNum;                             // WIFI设备数量, 指示stuWifi的有效数量
        public NET_WIFI_DEV_INFO[] stuWifi = (NET_WIFI_DEV_INFO[])new NET_WIFI_DEV_INFO().toArray(1024); // 周围Wifi设备的信息
        public int              nChannel;                             // 通道号
        public NET_WIFI_BASIC_INFO stuWifiBasiInfo;                   // Wifi事件上报基础信息
        public int              bGPSinfo;                             // 是否包含GPS信息
        public NET_WIFI_GPS_INFO stuWifiGPSInfo;                      // GPS信息
        public byte[]           reserved = new byte[376];             // 预留
    }

    public static class ALARM_WIFI_SEARCH_INFO_EX extends SdkStructure
    {
        public Pointer          pstuWifi;                             // 周围Wifi设备的信息
        public int              nWifiNum;                             // Wifi设备数量，pstuWifi 的个数
        public int              nChannel;                             // 通道号
        public NET_WIFI_BASIC_INFO stuWifiBasiInfo;                   // Wifi事件上报基础信息
        public int              bGPSinfo;                             // 是否包含GPS信息
        public NET_WIFI_GPS_INFO stuWifiGPSInfo;                      // GPS信息
        public byte[]           reserved = new byte[512];             // 预留
    }

    // 搜索到的WIFI设备信息
    public static class NET_WIFI_DEV_INFO extends SdkStructure
    {
        public byte[]           szMac = new byte[NET_MACADDR_LEN];    // Wifi设备的Mac地址
        public int              nLinkQuality;                         // 链接质量百分比, 0~100
        public NET_TIME_EX      stuEnterTime;                         // 第一被搜索到的时间
        public NET_TIME_EX      stuLeaveTime;                         // 消失的时间
        public int              nSearchedCount;                       // 被搜索到的次数
        public byte[]           szSSID = new byte[24];                // 网络名称
        public NET_TIME_EX      UTC;                                  // 事件发生时间
        public int              emDevType;                            // WIFI设备类型, 参考 EM_WIRELESS_DEV_TYPE
        public int              nChannel;                             // Wifi设备当前所在的信道
        public int              emAuth;                               // 认证方式, 参考 EM_WIRELESS_AUTHENTICATION
        public int              emEncrypt;                            // 数据加密方式, 参考 EM_WIRELESS_DATA_ENCRYPT
        public byte[]           szAPMac = new byte[NET_MACADDR_LEN];  // 接入热点Mac
        public int              nAPChannel;                           // 接入热点频道
        public byte[]           szAPSSID = new byte[24];              // 接入热点SSID
        public int              emAPEncrypt;                          // 接入热点加密类型, 参考 EM_WIRELESS_DATA_ENCRYPT
        public int              nRssiQuality;                         // 信号强度
        public byte[]           szManufacturer = new byte[MAX_MANUFACTURER_LEN]; // Mac地址所属制造商
        public MACHISTORY_SSID[] szMacHistorySSIDList = (MACHISTORY_SSID[])new MACHISTORY_SSID().toArray(MAX_MACHISTORY_SSID_NUM); // 此设备曾经连接过的历史SSID列表
        public int              nRetMacHistorySSIDNum;                // 此设备实际连接过的SSID个数
        public byte[]           reserved = new byte[264];             // 预留
    }

    // 历史SSID
    public static class MACHISTORY_SSID extends SdkStructure
    {
        public byte[]           szMacHistorySSID = new byte[MAX_MACHISTORY_SSID_LEN]; // 历史SSID
    }

    // 搜索到的WIFI基本信息
    public static class NET_WIFI_BASIC_INFO extends SdkStructure
    {
        public int              nPeriodUTC;                           // 本周期上报的起始时间，为同一上报周期的标识；当同一上报周期内设备总数超过单次上报数量上限时需要多次上报事件，该值作为同一上报周期内多次上报事件的标识码；
        public int              nDeviceSum;                           // 本周期上报的wifi总数，同一上报周期内的事件中的该值均应为本周期上报的wifi总数
        public int              nCurDeviceCount;                      // 本次事件上报的Wifi设备数量，应与ALARM_WIFI_SEARCH_INFO结构体中的nWifiNum值一致；同一上报周期内该值的累积总数与nDeviceSum一致。
        public byte[]           reserved = new byte[500];             // 预留字节
    }

    // 事件类型  NET_ALARM_WIFI_VIRTUALINFO_SEARCH (获取周围wifi设备虚拟信息事件)对应的数据描述信息
    public static class ALARM_WIFI_VIRTUALINFO_SEARCH_INFO extends SdkStructure
    {
        public int              nVirtualInfoNum;                      // WIFI设备虚拟身份数量, 指示stuVirtualInfo的有效数量
        public NET_WIFI_VIRTUALINFO[] stuVirtualInfo = (NET_WIFI_VIRTUALINFO[])new NET_WIFI_VIRTUALINFO().toArray(MAX_VIRTUALINFO_NUM); // 周围Wifi虚拟身份信息
        public int              nChannel;                             // 通道号
        public byte[]           reserved = new byte[512];             // 预留
    }

    // 搜索到的WIFI设备虚拟身份信息
    public static class NET_WIFI_VIRTUALINFO extends SdkStructure
    {
        public NET_TIME_EX      stuAccessTime;                        // 访问时间，时间不精确仅供参考
        public byte[]           szSrcMac = new byte[NET_MACADDR_LEN]; // 虚拟信息的来源MAC,字母大写，用"-"分隔
        public byte[]           szDstMac = new byte[NET_MACADDR_LEN]; // 虚拟信息的目标MAC,字母大写，用"-"分隔
        public int              nProtocal;                            // 协议代号，上网应用对应的标识码
        public byte[]           szUrl = new byte[NET_MAX_URL_LEN];    // 上网url
        public byte[]           szDomain = new byte[MAX_VIRTUALINFO_DOMAIN_LEN]; // 上网域
        public byte[]           szTitle = new byte[MAX_VIRTUALINFO_TITLE_LEN]; // 上网标题
        public byte[]           szUsrName = new byte[MAX_VIRTUALINFO_USERNAME_LEN]; // 用户名
        public byte[]           szPassWord = new byte[MAX_VIRTUALINFO_PASSWORD_LEN]; // 密码
        public byte[]           szPhoneNum = new byte[MAX_VIRTUALINFO_PHONENUM_LEN]; // 手机号
        public byte[]           szImei = new byte[MAX_VIRTUALINFO_IMEI_LEN]; // 国际移动设备标识
        public byte[]           szImsi = new byte[MAX_VIRTUALINFO_IMSI_LEN]; // 国际移动用户识别码
        public byte[]           szLatitude = new byte[MAX_VIRTUALINFO_LATITUDE_LEN]; // 经度
        public byte[]           szLongitude = new byte[MAX_VIRTUALINFO_LONGITUDE_LEN]; // 纬度
        public byte[]           szSrcIP = new byte[NET_MAX_IPADDR_LEN_EX]; // 源IP
        public byte[]           szDstIP = new byte[NET_MAX_IPADDR_LEN_EX]; // 目的IP
        public int              nSrcPort;                             // 源端口
        public int              nDstPort;                             // 目的端口
        public byte[]           szSiteNum = new byte[MAX_COMMON_STRING_16]; // 场所编号
        public byte[]           szDevNum = new byte[MAX_COMMON_STRING_32]; // 采集设备编号
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; // 虚拟用户ID
        public byte[]           szIDFA = new byte[MAX_COMMON_STRING_64]; // 苹果手机的IDFA
        public byte[]           reserved = new byte[368];             // 预留
    }

    // 事件类型 EVENT_IVS_ACCESS_CTL (门禁事件)对应数据块描述信息
    public static class DEV_EVENT_ACCESS_CTL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 门通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              emEventType;                          // 门禁事件类型, 参考 NET_ACCESS_CTL_EVENT_TYPE
        public int              bStatus;                              // 刷卡结果, 1表示成功, 0表示失败
        public int              emCardType;                           // 卡类型, 参考 NET_ACCESSCTLCARD_TYPE
        public int              emOpenMethod;                         // 开门方式, 参考 NET_ACCESS_DOOROPEN_METHOD
        public byte[]           szCardNo = new byte[NET_MAX_CARDNO_LEN]; // 卡号
        public byte[]           szPwd = new byte[NET_MAX_CARDPWD_LEN]; // 密码
        public byte[]           szReaderID = new byte[NET_COMMON_STRING_32]; // 门读卡器ID
        public byte[]           szUserID = new byte[NET_COMMON_STRING_64]; // 开门用户
        public byte[]           szSnapURL = new byte[NET_COMMON_STRING_128]; // 抓拍照片存储地址
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
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved = new byte[3];             // 字节对齐
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见 NET_RESERVED_COMMON
        public int              emAttendanceState;                    // 考勤状态, 参考 NET_ATTENDANCESTATE
        public byte[]           szClassNumber = new byte[MAX_CLASS_NUMBER_LEN]; // 班级（考勤）
        public byte[]           szPhoneNumber = new byte[MAX_PHONENUMBER_LEN]; // 电话（考勤）
        public byte[]           szCardName = new byte[NET_MAX_CARDNAME_LEN]; // 卡命名
        public int              uSimilarity;                          // 目标识别相似度,范围为0~100
        public DEV_ACCESS_CTL_IMAGE_INFO[] stuImageInfo = (DEV_ACCESS_CTL_IMAGE_INFO[])new DEV_ACCESS_CTL_IMAGE_INFO().toArray(6); // 图片信息
        public int              nImageInfoCount;                      // 图片信息数量
        public byte[]           szCitizenIDNo = new byte[20];         // 证件号
        public int              nGroupID;                             // 事件组ID
        public int              nCompanionCardCount;                  // 陪同者卡号个数
        public COMPANION_CARD[] szCompanionCards = (COMPANION_CARD[])new COMPANION_CARD().toArray(MAX_COMPANION_CARD_NUM); // 陪同者卡号信息
        public DEV_ACCESS_CTL_CUSTOM_WORKER_INFO stuCustomWorkerInfo; // 人员信息
        public int              emCardState;                          // 当前事件是否为采集卡片,参考EM_CARD_STATE
        public byte[]           szSN = new byte[32];                  // 设备序列号
        public int              emHatStyle;                           // 帽子类型(参考EM_HAT_STYLE)
        public int              emHatColor;                           // 帽子颜色(参考EM_UNIFIED_COLOR_TYPE)
        public int              emLiftCallerType;                     // 梯控方式触发者(参考EM_LIFT_CALLER_TYPE)
        public int              bManTemperature;                      // 人员温度信息是否有效
        public NET_MAN_TEMPERATURE_INFO stuManTemperatureInfo;        // 人员温度信息, bManTemperature 为TRUE时有效
        public byte[]           szCitizenName = new byte[256];        // 证件姓名
        public int              nCompanionInfo;                       // 陪同人员 stuCompanionInfo 个数
        public NET_COMPANION_INFO[] stuCompanionInfo = (NET_COMPANION_INFO[])new NET_COMPANION_INFO().toArray(12); // 陪同人员信息
        public int              emMask;                               // 口罩状态（EM_MASK_STATE_UNKNOWN、EM_MASK_STATE_NOMASK、EM_MASK_STATE_WEAR 有效）
        public int              nFaceIndex;                           // 一人多脸的人脸序号
        public int              bClassNumberEx;                       // szClassNumberEx 是否有效，为TRUE时，szClassNumberEx 有效
        public byte[]           szClassNumberEx = new byte[512];      // 班级
        public byte[]           szDormitoryNo = new byte[64];         // 宿舍号
        public byte[]           szStudentNo = new byte[64];           // 学号
        public int              emUserType;                           // 用户类型( EM_USER_TYPE_ORDINARY 至 EM_USER_TYPE_DISABLED 有效 ) EM_USER_TYPE
        public int              bRealUTC;                             // RealUTC 是否有效，bRealUTC 为 TRUE 时，用 RealUTC，否则用 UTC 字段
        public NET_TIME_EX      RealUTC;                              // 事件发生的时间（标准UTC）
        public byte[]           szQRCode = new byte[512];             // 二维码信息
        public byte[]           szCompanyName = new byte[200];        // 公司名称
        public int              nScore;                               // 人脸质量评分
           /** {@link EM_FACE_CHECK} */
        public int              emFaceCheck;                          // 刷卡开门时，门禁后台校验人脸是否是同一个人
        /**  {@link EM_QRCODE_IS_EXPIRED}  */
        public int              emQRCodeIsExpired;                    // 二维码是否过期。默认值0
        /**  {@link EM_QRCODE_STATE}  */
        public int              emQRCodeState;                        // 二维码状态
        public  NET_TIME        stuQRCodeValidTo;                     // 二维码截止日期
        public int              nBlockId;                             // 上报事件数据序列号从1开始自增
        public  byte[]          szSection = new byte[64];             // 部门名称
        public  byte[]          szWorkClass = new byte[256];          // 工作班级
        /** {@link EM_TEST_ITEMS }  */
        public int              emTestItems;                          // 测试项目
        public  NET_TEST_RESULT stuTestResult;                        // ESD阻值测试结果
        public byte[]           szDeviceID = new byte[128];           // 门禁设备编号
        public byte[]           szUserUniqueID = new byte[128];       // 用户唯一表示ID
        public int              bUseCardNameEx;                       // 是否使用卡命名扩展
        public byte[]           szCardNameEx = new byte[128];         // 卡命名扩展
        public  int             nHSJCResult;                          //核酸检测报告结果   -1: 未知  2: 未检测 3: 过期
        public  NET_VACCINE_INFO stuVaccineInfo = new NET_VACCINE_INFO(); // 新冠疫苗接种信息
        public  NET_TRAVEL_INFO stuTravelInfo = new  NET_TRAVEL_INFO(); // 行程码信息
        public  byte[]          szTrafficPlate = new byte[32];        // 车牌
        public byte[]           szQRCodeEx = new byte[2048];          //大二维码内容
        public byte[]           szReversed = new byte[4424];          // 预留字节
    }

    //用户类型
    public static class EM_USER_TYPE
    {
        public static final int   EM_USER_TYPE_UNKNOWN = -1;            // 未知
        public static final int   EM_USER_TYPE_ORDINARY = 0;            // 普通用户
        public static final int   EM_USER_TYPE_BLACKLIST = 1;           //
        public static final int   EM_USER_TYPE_VIP = 2;                 //
        public static final int   EM_USER_TYPE_GUEST = 3;               //
        public static final int   EM_USER_TYPE_PATROL = 4;              //
        public static final int   EM_USER_TYPE_DISABLED = 5;            //
        public static final int   EM_USER_TYPE_FROZEN = 6;              // 冻结用户
        public static final int   EM_USER_TYPE_LOGOUT = 7;              // 注销用户
        public static final int   EM_USER_TYPE_LOSSCARD = 8;            // 挂失卡
    }

    // 人员温度信息
    public static class NET_MAN_TEMPERATURE_INFO extends SdkStructure
    {
        public float            fCurrentTemperature;                  // 人员体温
        public int              emTemperatureUnit;                    // 温度单位(参考EM_HUMAN_TEMPERATURE_UNIT)
        public int              bIsOverTemperature;                   // 是否超温
        public int              emTemperatureStatus;                  //人体测温状态,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_HUMAN_TEMPERATURE_STATUS}
        public byte[]           byReserved = new byte[256];           // 预留字节
    }

    // 陪同人员信息
    public static class NET_COMPANION_INFO extends SdkStructure
    {
        public byte[]           szCompanionCard = new byte[32];       // 陪同者卡号
        public byte[]           szCompanionUserID = new byte[32];     // 陪同者ID
        public byte[]           szCompanionName = new byte[120];      // 陪同者姓名
        public byte[]           szCompanionCompany = new byte[200];   //陪同者单位
        public byte[]           byReserved = new byte[56];            // 预留字段
    }

    // 人员信息
    public static class DEV_ACCESS_CTL_CUSTOM_WORKER_INFO extends SdkStructure
    {
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
        public byte[]           bReserved = new byte[256];            // 保留字节,留待扩展.
    }

    //图片信息
    public static class DEV_ACCESS_CTL_IMAGE_INFO extends SdkStructure
    {
        public int              emType;                               // 图片类型, 参考 EM_ACCESS_CTL_IMAGE_TYPE
        public int              nOffSet;                              // 二进制块偏移字节
        public int              nLength;                              // 图片大小
        public int              nWidth;                               // 图片宽度(单位:像素)
        public int              nHeight;                              // 图片高度(单位:像素)
        public byte[]           byReserved = new byte[64];            // 保留字节
    }

    //图片类型
    public static class EM_ACCESS_CTL_IMAGE_TYPE extends SdkStructure
    {
        public static final int   EM_ACCESS_CTL_IMAGE_UNKNOWN = -1;     // 未知
        public static final int   EM_ACCESS_CTL_IMAGE_LOCAL = 0;        // 本地人脸图库
        public static final int   EM_ACCESS_CTL_IMAGE_SCENE = 1;        // 拍摄场景抠图
        public static final int   EM_ACCESS_CTL_IMAGE_FACE = 2;         // 人脸抠图
    }

    public static class COMPANION_CARD extends SdkStructure
    {
        public byte[]           szCompanionCard = new byte[NET_MAX_CARDINFO_LEN]; // 陪同者卡号信息
    }

    // 门禁事件类型
    public static class NET_ACCESS_CTL_EVENT_TYPE extends SdkStructure
    {
        public static final int   NET_ACCESS_CTL_EVENT_UNKNOWN = 0;
        public static final int   NET_ACCESS_CTL_EVENT_ENTRY = 1;       // 进门
        public static final int   NET_ACCESS_CTL_EVENT_EXIT = 2;        // 出门
    }

    // 当前门采集状态
    public static class EM_CARD_STATE extends SdkStructure
    {
        public static final int   EM_CARD_STATE_UNKNOWN = -1;           // 未知
        public static final int   EM_CARD_STATE_SWIPE = 0;              // 门禁刷卡
        public static final int   EM_CARD_STATE_COLLECTION = 1;         // 门禁采集卡
    }

    // 获取热度统计信息, 对应命令 NET_DEVSTATE_GET_HEAT_MAP
    public static class NET_QUERY_HEAT_MAP extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public NET_IN_QUERY_HEAT_MAP stuIn;                           // 热度统计信息查询条件
        public NET_OUT_QUERY_HEAT_MAP stuOut;                         // 热度统计信息查询结果

        public NET_QUERY_HEAT_MAP() {
            this.dwSize = this.size();
        }
    }

    // 获取热度统计信息入参
    public static class NET_IN_QUERY_HEAT_MAP extends SdkStructure
    {
        public int              nChannel;                             // 通道号
        public NET_TIME_EX      stuBegin;                             // 开始时间
        public NET_TIME_EX      stuEnd;                               // 结束时间
        public int              nPlanID;                              // 计划ID,仅球机有效,从1开始
        public int              emDataType;                           // 希望获取的数据类型, 参考  EM_HEAT_PIC_DATA_TYPE
        public byte[]           reserved = new byte[1016];            // 预留
    }

    // 获取热度统计信息出参
    public static class NET_OUT_QUERY_HEAT_MAP extends SdkStructure
    {
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public Pointer          pBufData;                             // 热度数据灰阶位图, 用户申请内存,大小为nBufLen
        // 若emDataType为EM_HEAT_PIC_DATA_TYPE_GRAYDATA,则一个字节表示一个点
        // 若emDataType为EM_HEAT_PIC_DATA_TYPE_SOURCEDATA,则四个字节表示一个点
        public int              nBufLen;                              // pBufData最大长度
        public int              nBufRet;                              // 实际返回的长度
        public int              nAverage;                             // 均值信息
        public int              nPlanID;                              // 计划ID,与请求NET_IN_QUERY_HEAT_MAP的nPlanID对应
        public int              emDataType;                           // 获取到的数据类型, 参考 EM_HEAT_PIC_DATA_TYPE
        public int              nPixelMax;                            // 实际像素点的最大值
        public int              nPixelMin;                            // 实际像素点的最小值
        public byte[]           reserved = new byte[1004];            // 预留
    }

    // 热度图数据类型
    public static class EM_HEAT_PIC_DATA_TYPE extends SdkStructure
    {
        public static final int   EM_HEAT_PIC_DATA_TYPE_UNKNOWN = 0;    // 未知类型
        public static final int   EM_HEAT_PIC_DATA_TYPE_GRAYDATA = 1;   // 灰度数据
        public static final int   EM_HEAT_PIC_DATA_TYPE_SOURCEDATA = 2; // 原始数据
    }

    // 通道名称配置
    public static class NET_ENCODE_CHANNELTITLE_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szChannelName = new byte[MAX_CHANNEL_NAME_LEN]; // 通道名称

        public NET_ENCODE_CHANNELTITLE_INFO() {
            this.dwSize = this.size();
        }
    }

    // 视频分析全局配置
    public static class CFG_ANALYSEGLOBAL_INFO extends SdkStructure
    {
        // 信息
        public byte[]           szSceneType = new byte[MAX_NAME_LEN]; // 应用场景,详见"支持的场景列表", 参考  EM_SCENE_TYPE 里的场景
        //交通场景信息
        public double           CameraHeight;                         // 摄像头离地高度	单位：米
        public double           CameraDistance;                       // 摄像头离地面检测区域中心的水平距离	单位：米
        public CFG_POLYGON      stuNearDetectPoint;                   // 近景检测点
        public CFG_POLYGON      stuFarDectectPoint;                   // 远景检测点
        public int              nNearDistance;                        // NearDetectPoint,转换到实际场景中时,离摄像头垂直线的水平距离
        public int              nFarDistance;                         // FarDectectPoint,转换到实际场景中时,离摄像头垂直线的水平距离
        public byte[]           szSubType = new byte[MAX_NAME_LEN];   // 交通场景的子类型,"Gate",卡口类型,"Junction" 路口类型,"ParkingSpace" 车位检测类型
        public int              nLaneNum;                             // 车道数
        public CFG_LANE[]       stuLanes = (CFG_LANE[])new CFG_LANE().toArray(MAX_LANE_NUM); // 车道信息
        public int              nPlateHintNum;                        // 车牌字符暗示个数
        public PLATE_HINT[]     szPlateHintsArr = (PLATE_HINT[])new PLATE_HINT().toArray(MAX_PLATEHINT_NUM); // 车牌字符暗示数组，在拍摄图片质量较差车牌识别不确定时，根据此数组中的字符进行匹配，数组下标越小，匹配优先级越高
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
        // 一般场景信息
        public int              nStaffNum;                            // 标尺数
        public CFG_STAFF[]      stuStaffs = (CFG_STAFF[])new CFG_STAFF().toArray(MAX_STAFF_NUM); // 标尺
        public int              nCalibrateAreaNum;                    // 标定区域数
        public CFG_CALIBRATEAREA_INFO[] stuCalibrateArea = (CFG_CALIBRATEAREA_INFO[])new CFG_CALIBRATEAREA_INFO().toArray(MAX_CALIBRATEBOX_NUM); // 标定区域(若该字段不存在，则以整幅场景为标定区域)
        public int              bFaceRecognition;                     // 目标识别场景是否有效
        public CFG_FACERECOGNITION_SCENCE_INFO stuFaceRecognitionScene; // 目标识别场景
        public byte             abJitter;
        public byte             abDejitter;
        public byte[]           bReserved = new byte[2];              // 保留字段
        public int              nJitter;                              // 摄像机抖动率 : 摄像机抖动率，取值0-100，反应静止摄像机抖动程度，抖动越厉害，值越大。
        public int              bDejitter;                            // 是否开启去抖动模块 目前不实现
        public int              abCompatibleMode;
        public int              nCompatibleMode;                      // 0:"OldTrafficRule" : 交通老规则兼容模式;1:"NewTrafficRule" :  交通新规则兼容模式;-1:字符串错误
        public int              nCustomDataLen;                       // 实际数据长度，不能大于1024
        public byte[]           byCustomData = new byte[1024];        // 第三方自定义配置数据
        public double           CameraAngle;                          // 摄像头与垂方向的夹角
        public CFG_POLYGON      stuLandLineStart;                     // 地平线线段起始点(点的坐标坐标归一化到[0,8192)区间。)
        public CFG_POLYGON      stuLandLineEnd;                       // 地平线线段终止点(点的坐标坐标归一化到[0,8192)区间。)
        public int              bFaceDetection;                       // 目标检测场景是否有效
        public CFG_FACEDETECTION_SCENCE_INFO stuFaceDetectionScene = new CFG_FACEDETECTION_SCENCE_INFO(); // 目标检测场景
        public CFG_TIME_PERIOD  stuDayTimePeriod = new CFG_TIME_PERIOD(); // 标定白天的时间段.(8,20),表示从8点到晚上20点为白天
        public CFG_TIME_PERIOD  stuNightTimePeriod = new CFG_TIME_PERIOD(); // 标定黑夜的时间段.(20,7)，表示从晚8点到凌晨7点为黑夜
        public CFG_TIME_PERIOD_SCENE_INFO stuTimePeriodSceneInfo;     // 多场景标定白天和黑夜时间段
        public CFG_CALIBRATEAREA_SCENE_INFO stuCalibrateAreaSceneInfo; // 多场景标定区域配置信息
        public int              emSwitchMode;                         // 昼夜算法切换模式,详见 CFG_TIMEPERIOD_SWITCH_MODE
        public int              nSceneNum;                            // 场景数, >0时表示支持多场景, stuMultiScene有效
        public CFG_ANALYSEGLOBAL_SCENE[]	    stuMultiScene = (CFG_ANALYSEGLOBAL_SCENE[])new CFG_ANALYSEGLOBAL_SCENE().toArray(MAX_ANALYSE_SCENE_NUM);	// 多场景配置
//        public byte[]           stuMultiScene = new byte[189872 * MAX_ANALYSE_SCENE_NUM];
}
