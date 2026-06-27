package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 8 部分
 */
public interface NetSDKMethods8 {
                    ", nWidth=" + nWidth +
                    ", nHeight=" + nHeight +
                    ", szPath=" + new String(szPath,Charset.forName("GBK")) +
                    '}';
        }
    }

    // 高空抛物物体信息
    public static class NET_HIGHTOSS_OBJECT_INFO extends SdkStructure
    {
        public int              nObjectID;                            // 物体ID
        public int              emObjAction;                          // 物体动作类型;参考EM_HIGHTOSS_ACTION_TYPE
        public NET_RECT         stuBoundingBox;                       // 包围盒
        public int              nConfidence;                          // 置信度
        public int              emObjectType;                         // 物体类型;参考EM_ANALYSE_OBJECT_TYPE
        public NET_POINT        stuCenter;                            // 物体型心
        public NET_EVENT_IMAGE_OFFSET_INFO stuImageInfo;              // 抓拍小图
        public byte[]           byReserved = new byte[1516];          // 预留字节 		// 预留字节

        @Override
        public String toString() {
            return "NET_HIGHTOSS_OBJECT_INFO{" +
                    "nObjectID=" + nObjectID +
                    ", 动作类型=" + emObjAction +
                    ", stuBoundingBox=" + stuBoundingBox +
                    ", 置信度=" + nConfidence +
                    ", 物体类型=" + emObjectType +
                    ",物体型心="+stuCenter +
                    ",抓拍小图="+stuImageInfo.toString()+
                    '}';
        }
    }

    // 登陆时TLS加密模式
    public static class EM_LOGIN_TLS_TYPE extends SdkStructure {
        public static final int   EM_LOGIN_TLS_TYPE_NO_TLS = 0;         // 不走tls加密, 默认方式
        public static final int   EM_LOGIN_TLS_TYPE_TLS_ADAPTER = 1;    // 自适应tls加密
        public static final int   EM_LOGIN_TLS_TYPE_TLS_COMPEL = 2;     // 强制tls加密
        public static final int   EM_LOGIN_TLS_TYPE_TLS_MAIN_ONLY = 3;  // 部分tls加密
    }

    // CLIENT_LoginWithHighLevelSecurity 输入参数
    public static class NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szIP = new byte[64];                  // IP
        public int              nPort;                                // 端口
        public byte[]           szUserName = new byte[64];            // 用户名
        public byte[]           szPassword = new byte[64];            // 密码
        public int              emSpecCap;                            // 登录模式
        public byte[]           byReserved = new byte[4];             // 字节对齐
        public Pointer          pCapParam;                            // 见 CLIENT_LoginEx 接口 pCapParam 与 nSpecCap 关系
        public int              emTLSCap;                             //登录的TLS模式，参考EM_LOGIN_TLS_TYPE，目前仅支持EM_LOGIN_SPEC_CAP_TCP，EM_LOGIN_SPEC_CAP_SERVER_CONN 模式下的 tls登陆
        public byte[]           szLocalIP = new byte[64];             //本地ip

        public NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_LoginWithHighLevelSecurity 输出参数
    public static class NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_DEVICEINFO_Ex stuDeviceInfo;                       // 设备信息
        public int              nError;                               // 错误码，见 CLIENT_Login 接口错误码
        public byte[]           byReserved = new byte[132];           // 预留字段

        public NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 高安全级别登陆
    public LLong CLIENT_LoginWithHighLevelSecurity(NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam,NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam);

    // GDPR使能全局开关
    public void CLIENT_SetGDPREnable(boolean bEnable);

    // 合成通道配置(对应CFG_CMD_COMPOSE_CHANNEL)
    public static class CFG_COMPOSE_CHANNEL extends SdkStructure
    {
        public int              emSplitMode;                          // 分割模式,写入枚举值 枚举值在 CFG_SPLITMODE 类中定义，不要自己写
        public int[]            nChannelCombination = new int[MAX_VIDEO_CHANNEL_NUM]; // 割模式下的各子窗口显示内容  最大 MAX_VIDEO_CHANNEL_NUM
        public int              nChannelCount;                        // 分割窗口数量
    }

    // 画中画方案
    public static class CFG_PICINPIC_INFO extends SdkStructure
    {
        public      int         nMaxSplit;                            // 内存申请的CFG_SPLIT_INFO个数,最大值通过CLIENT_GetSplitCaps接口获取，见nModeCount
        public      int         nReturnSplit;                         // 解析得到实际使用的或封装发送的CFG_SPLIT_INFO个数
        public      Pointer     pSplits;                              // 分割方案，指向 CFG_SPLIT_INFO
    }

    // 分割方案
    public static class CFG_SPLIT_INFO extends SdkStructure
    {
        public int              emSplitMode;                          // CFG_SPLITMODE分割模式，通过CLIENT_GetSplitCaps接口获取，见emSplitMode
        public int              nMaxChannels;                         // 申请内存CFG_SPLIT_CHANNEL_INFO个数, 比如有16个通道，nMaxChannels就是16，SPLITMODE_4模式，则按顺序依次分为4组
        public int              nReturnChannels;                      // 解析返回通道个数,要封装发送的通道个数
        public Pointer          pSplitChannels;                       // 分割通道信息,指向 CFG_SPLIT_CHANNEL_INFO
    }

    // 分割通道
    public static class CFG_SPLIT_CHANNEL_INFO extends SdkStructure
    {
        public int              bEnable;                              // 使能
        public byte[]           szDeviceID = new byte[AV_CFG_Device_ID_Len]; // 设备ID
        public int              nChannelID;                           // 通道号(0开始)
        public int              nMaxSmallChannels;                    // 小画面通道个数，每个通道一个CFG_SMALLPIC_INFO,这里最大应该是设备通道数减一
        public int              nReturnSmallChannels;                 // 解析返回的或封装发送的小画面通道个数
        public Pointer          pPicInfo;                             // 小画面信息 CFG_SMALLPIC_INFO
    }

    // 审讯画中画需求
    // 小画面窗口信息
    public static class CFG_SMALLPIC_INFO extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[AV_CFG_Device_ID_Len]; // 设备ID
        public int              nChannelID;                           // 通道号(0开始)
        public int              bAudio;                               // 大画面是否混合小画面音频
        public CFG_RECT         stuPosition;                          // 使用相对坐标体系，取值均为0-8192,在整个屏幕上的位置
    }

    //--------------------------------------------------------ERR200507125开始------------------------------------------------------------------------//
    // 空闲动作配置信息
    public static class CFG_IDLE_MOTION_INFO extends SdkStructure
    {
        public int              bEnable;                              // 使能
        public int              nTime;                                // 启动空闲动作的时间1~60分钟
        public int              emFunction;                           // 空闲动作功能,见枚举 EM_CFG_IDLEMOTION_FUNCTION
        public int              nPresetId;                            // 预置点编号,   范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wPresetMin和wPresetMax
        public int              nScanId;                              // 自动线扫编号, 范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wAutoScanMin和wAutoScanMax
        public int              nTourId;                              // 巡航编号,     范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wTourMin和wTourMax
        public int              nPatternId;                           // 自动巡迹编号, 范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wPatternMin和wPatternMax
        public int              nSecond;                              // 启动空闲动作的时长（秒数）范围0-59秒,总时长为nTime * 60 + nSecond
    }

    //--------------------------------------------------------ERR200507125结束------------------------------------------------------------------------//
    //--------------------------------------------------------ERR200513038-TASK1开始------------------------------------------------------------------------//
    // 每个通道的RTMP信息
    public static class NET_CHANNEL_RTMP_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否使能
        public int              nChannel;                             // 通道号（URL中的Channel）
        public byte[]           szUrl = new byte[512];                // RTMP连接URL
        public byte[]           byReserved = new byte[1024];          // 预留字段
    }

    // RTMP 配置
    public static class NET_CFG_RTMP_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小，赋值为sizeof(NET_CFG_RTMP_INFO)
        public int              bEnable;                              // RTMP配置是否开启
        public byte[]           szAddr = new byte[256];               // RTMP服务器地址
        public int              nPort;                                // RTMP服务器端口
        public byte[]           byReserved = new byte[4];             // 字节对齐
        public byte[]           szCustomPath = new byte[256];         // 路径名
        public byte[]           szStreamPath = new byte[256];         // 码流路径前缀:不同通道以后缀数字区分
        public byte[]           szKey = new byte[128];                // 获取RTMP地址时的Key
        public Pointer          pstuMainStream;                       // 主码流信息，用户分配内存，内存大小为 sizeof(NET_CHANNEL_RTMP_INFO) * nMainStream
        public int              nMainStream;                          // pstuMainStream 个数
        public int              nMainStreamRet;                       // 返回的 pstuMainStream 个数（获取配置时有效）
        public Pointer          pstuExtra1Stream;                     // 辅码流1信息，用户分配内存，内存大小为 sizeof(NET_CHANNEL_RTMP_INFO) * nExtra1Stream
        public int              nExtra1Stream;                        // pstuExtra1Stream 个数
        public int              nExtra1StreamRet;                     // 返回的 nExtra1StreamRet 个数（获取配置时有效）
        public Pointer          pstuExtra2Stream;                     // 辅码流2信息，用户分配内存，内存大小为 sizeof(NET_CHANNEL_RTMP_INFO) * nExtra2Stream
        public int              nExtra2Stream;                        // pstuExtra2Stream 个数
        public int              nExtra2StreamRet;                     // 返回的 nExtra2StreamRet 个数（获取配置时有效）

        public NET_CFG_RTMP_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //--------------------------------------------------------ERR200513038开始------------------------------------------------------------------------//
    //--------------------------------------------------------GIP200520016实现------------------------------------------------------------------------//
    // 轮询任务对象
    public static class NET_POLLING_INFO extends SdkStructure
    {
        public int              emSourceType;                         // 数据源类型 参考EM_DATA_SOURCE_TYPE
        public Pointer          pSourceData;                          // 数据源信息, 根据emSouceType对应不一样的结构体
        public byte[]           szUserData = new byte[64];            // 视频源数据，标示视频源信息。在返回结果时，原封不动的带上。当任务的包含多个视频源时，attachResult每个视频源单独上报结果
        public byte[]           byReserved = new byte[256];           // 保留字节
    }

    // 接口输出参数
    public static class NET_IN_ADD_POLLING_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nIntervalTime;                        // 每个视频源的检测执行时间，单位为秒，1~65535
        public int              nLoopCount;                           // 诊断轮询次数, 0代表永久轮询
        public int              nInfoCount;                           // 任务对象个数
        public Pointer          pInfoList;                            // 任务对象列表(参考NET_POLLING_INFO)

        public NET_IN_ADD_POLLING_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 接口输出参数
    public static class NET_OUT_ADD_POLLING_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID

        public NET_OUT_ADD_POLLING_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    public static class NET_IN_UPDATE_POLLING_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID
        public int              nIntervalTime;                        // 每个视频源的检测执行时间，单位为秒，1~65535
        public int              nLoopCount;                           // 诊断轮询次数, 0代表永久轮询
        public int              nInfoCount;                           // 任务对象个数
        public int              nReserved;                            // 字节对齐
        public Pointer          pInfoList;                            // 任务对象列表(参考NET_POLLING_INFO)

        public NET_IN_UPDATE_POLLING_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 接口输出参数
    public static class NET_OUT_UPDATE_POLLING_ANALYSE_TASK extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_UPDATE_POLLING_ANALYSE_TASK()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //获取剩余智能分析资源入参
    public static class NET_IN_REMAIN_ANAYLSE_RESOURCE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_REMAIN_ANAYLSE_RESOURCE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //智能分析剩余能力具体信息
    public static class NET_REMAIN_ANALYSE_CAPACITY extends SdkStructure
    {
        public int              nMaxStreamNum;                        // 剩余能分析的视频流数目
        public int              emClassType;                          // 大类业务方案(参考EM_SCENE_CLASS_TYPE)
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    //获取剩余智能分析资源出参
    public static class NET_OUT_REMAIN_ANAYLSE_RESOURCE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRetRemainCapNum;                     // 返回的能力格式
        public NET_REMAIN_ANALYSE_CAPACITY[] stuRemainCapacities = (NET_REMAIN_ANALYSE_CAPACITY[])new NET_REMAIN_ANALYSE_CAPACITY().toArray(32); // 智能分析剩余能力
        public NET_REMAIN_ANALYSE_TOTAL_CAPACITY[] stuTotalCapacity = (NET_REMAIN_ANALYSE_TOTAL_CAPACITY[])new NET_REMAIN_ANALYSE_TOTAL_CAPACITY().toArray(32); //
        // 可供任务调度的总的智能能力
        public int              nTotalCapacityNum;
        public byte[]           byReserved = new byte[60684];         // 保留字节

        public NET_OUT_REMAIN_ANAYLSE_RESOURCE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    public static class NET_IN_REMOTEDEVICE_CAPS extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        //public byte[]                   szSubClassID=new byte[32];                                      // 空表示管理远程通道的设备列表                                                                                                // "EmbeddedPlatform": 表示管理嵌入式管理平台的设备管理器

        public NET_IN_REMOTEDEVICE_CAPS()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    public static class NET_OUT_REMOTEDEVICE_CAP extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRetCount;                            // 返回的pnProtocal 有效个数
        public int[]            snProtocal = new int[512];            // 协议类型 值同 EM_STREAM_PROTOCOL_TYPE

        public NET_OUT_REMOTEDEVICE_CAP()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //算法独立升级能力
    public static class NET_ANALYSE_CAPS_SUPPORT_ALGORITHM_UPGRADE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bSupportOnlyAlgorithmUpgrade;         // 是否支持算法独立升级能力
        public int              nMaxUpgradeAINum;                     // AI 方案最大个数, 由用户指定,  最大支持128
        public int              nRetUpgradeAINum;                     // 实际返回的AI 方案个数, 即pstUpgradeAIInfo 数组的有效元素个数
        /**
         * 指针内传入结构体数组,结构体为{@link NET_ALGORITHM_UPGRADE_AI_INFO}
         */
        public Pointer          pstUpgradeAIInfo;                     // 独立算法升级支持的AI方案信息, 内存由用户申请和释放, 申请大小sizeof(NET_ALGORITHM_UPGRADE_AI_INFO)*nMaxUpgradeAINum
        public int              nRetStorageNum;                       // 实际返回的设备分区个数, 即stuStorageInfo 数组的有效元素个数
        public NET_ALGORITHM_DEV_STORAGE_INFO[] stuStorageInfos = (NET_ALGORITHM_DEV_STORAGE_INFO[])new NET_ALGORITHM_DEV_STORAGE_INFO().toArray(16); // 设备的分区信息
        public NET_ALGORITHM_BUILD_INFO stuBuildInfo;                 // 算法构建信息

        public NET_ANALYSE_CAPS_SUPPORT_ALGORITHM_UPGRADE()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //智能分析总能力
    public static class NET_TOTAL_CAP extends SdkStructure
    {
        public int              emClassType;                          // 业务大类(参考EM_SCENE_CLASS_TYPE)
        public int[]            dwRuleTypes = new int[MAX_ANALYSE_RULE_COUNT]; // 规则类型, 详见dhnetsdk.h中"智能分析事件类型"
        public int              nRuleNum;                             // 规则数量
        public int              nMaxStreamNum;                        // 最多支持同时分析的视频流数目
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    //智能分析的总能力
    public static class NET_ANALYSE_CAPS_TOTAL extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_TOTAL_CAP[]  stuTotalCaps = (NET_TOTAL_CAP[])new NET_TOTAL_CAP().toArray(MAX_ANALYSE_TOTALCAPS_NUM); // 智能分析总能力
        public int              nTotalCapsNum;                        // 智能分析总能力个数
        public int              nTotalDecodeCaps;                     // 总解码能力，即总解码资源个数
        public int              nTotalComputingCaps;                  // 总算力
        public int              nSingleTaskComputingCaps;             // 单任务最大算力

        public NET_ANALYSE_CAPS_TOTAL()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 算法版本信息
    public static class NET_ALGORITHM_INFO extends SdkStructure
    {
        public int              emClassType;                          // 业务大类(参考EM_SCENE_CLASS_TYPE)
        public byte[]           szVersion = new byte[NET_COMMON_STRING_32]; // 算法版本
        public int              emAlgorithmVendor;                    // 算法厂商(参考EM_ALGORITHM_VENDOR)
        public byte[]           szAlgorithmLibVersion = new byte[NET_COMMON_STRING_32]; // 算法库文件版本
        public byte[]           byReserved = new byte[992];           // 保留字节
    }

    //智能分析的算法版本信息
    public static class NET_ANALYSE_CAPS_ALGORITHM extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public NET_ALGORITHM_INFO[] stuAlgorithmInfos = (NET_ALGORITHM_INFO[])new NET_ALGORITHM_INFO().toArray(MAX_ANALYSE_ALGORITHM_NUM); // 算法版本信息
        public int              nAlgorithmNum;                        // 算法个数

        public NET_ANALYSE_CAPS_ALGORITHM()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 智能分析服务能力类型
    public static class EM_ANALYSE_CAPS_TYPE extends SdkStructure
    {
        public static final int   EM_ANALYSE_CAPS_ALGORITHM = 1;        // 算法版本, 对应输出结构体 NET_ANALYSE_CAPS_ALGORITHM
        public static final int   EM_ANALYSE_CAPS_TOTALCAPS = 2;        // 智能分析总能力, 对应输出结构体 NET_ANALYSE_CAPS_TOTAL
        public static final int   EM_ANALYSE_CAPS_SUPPORT_ALGORITHM_UPGRADE = 3; // 算法独立升级能力, 对应输出结构体 NET_ANALYSE_CAPS_SUPPORT_ALGORITHM_UPGRADE
    }

    //CLIENT_SetAnalyseTaskCustomData 接口输入参数
    public static class NET_IN_SET_ANALYSE_TASK_CUSTOM_DATA extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTaskID;                              // 任务ID
        public NET_TASK_CUSTOM_DATA stuTaskCustomData;                // 自定义数据

        public NET_IN_SET_ANALYSE_TASK_CUSTOM_DATA()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    //CLIENT_SetAnalyseTaskCustomData 接口输出参数
    public static class NET_OUT_SET_ANALYSE_TASK_CUSTOM_DATA extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SET_ANALYSE_TASK_CUSTOM_DATA()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 获取视频分析服务智能能力集, pstOutParam根据emCapsType的值取对应的结构体(参考EM_ANALYSE_CAPS_TYPE), pstOutParam 资源由用户申请和释放
    public boolean CLIENT_GetAnalyseCaps(LLong lLoginID,int emCapsType,Pointer pOutParam,int nWaitTime);

    // 添加轮询检测任务 (入参NET_IN_ADD_POLLING_ANALYSE_TASK，出参NET_OUT_ADD_POLLING_ANALYSE_TASK)
    public boolean CLIENT_AddPollingAnalyseTask(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 更新轮询检测任务规则(入参NET_IN_UPDATE_POLLING_ANALYSE_TASK，出参NET_OUT_UPDATE_POLLING_ANALYSE_TASK)
    public boolean CLIENT_UpdatePollingAnalyseTask(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 获取剩余智能分析资源(入参NET_IN_REMAIN_ANAYLSE_RESOURCE，出参NET_OUT_REMAIN_ANAYLSE_RESOURCE)
    public boolean CLIENT_GetRemainAnalyseResource(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 设置任务的自定义数据(入参NET_IN_SET_ANALYSE_TASK_CUSTOM_DATA,出参NET_OUT_SET_ANALYSE_TASK_CUSTOM_DATA)
    public boolean CLIENT_SetAnalyseTaskCustomData(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    public static class NET_VIDEOABNORMALDETECTION_RULE_INFO extends SdkStructure {
        /**
         * 最短持续时间	单位：秒，0~65535
         */
        public int              nMinDuration;
        /**
         * 灵敏度, 取值1-10，值越小灵敏度越低(只对检测类型视频遮挡，过亮，过暗，场景变化有效)
         */
        public int              nSensitivity;
        /**
         * 检测类型数
         */
        public int              nDetectType;
        public int              nReserved;
        /**
         * 异常检测阈值,范围1~100
         */
        public int[]            nThreshold = new int[32];
        /**
         * 检测类型,0-视频丢失, 1-视频遮挡, 2-画面冻结, 3-过亮, 4-过暗, 5-场景变化
         * 6-条纹检测 , 7-噪声检测 , 8-偏色检测 , 9-视频模糊检测 , 10-对比度异常检测
         * 11-视频运动 , 12-视频闪烁 , 13-视频颜色 , 14-虚焦检测 , 15-过曝检测, 16-场景巨变
         */
        public byte[]           bDetectType = new byte[32];
        /**
         * 保留字节
         */
        public byte[]           byReserved = new byte[4096];
    }

    //--------------------------------------------------------ERR200529144------------------------------------------------------------------------//
    // 热度图灰度数据
    public static class NET_CB_HEATMAP_GRAY_INFO extends SdkStructure
    {
        public int              nWidth;                               // 图片宽度
        public int              nHeight;                              // 图片高度
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nMax;                                 // 最大值
        public int              nMin;                                 // 最小值
        public int              nAverage;                             // 平均值
        public int              nLength;                              // 灰度图数据长度
        public Pointer          pGrayInfo;                            // 灰度图数据
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 热度图灰度数据回调函数, lAttachHandle 为 CLIENT_AttachHeatMapGrayInfo 返回的结果(pstGrayInfo参考NET_CB_HEATMAP_GRAY_INFO)
    public interface fHeatMapGrayCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstGrayInfo,Pointer dwUser);
    }

    // CLIENT_AttachHeatMapGrayInfo 接口输入参数
    public static class NET_IN_GRAY_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道号
        public fHeatMapGrayCallBack cbHeatMapGray;                    // 热度图灰度数据回调函数
        public Pointer          dwUser;                               // 用户信息

        public NET_IN_GRAY_ATTACH_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // CLIENT_AttachHeatMapGrayInfo接口输出参数
    public static class NET_OUT_GRAY_ATTACH_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_GRAY_ATTACH_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 订阅热度图灰度数据接口,pInParam与pOutParam内存由用户申请释放(pInParam参考NET_IN_GRAY_ATTACH_INFO,pOutParam参考NET_OUT_GRAY_ATTACH_INFO)
    public LLong CLIENT_AttachHeatMapGrayInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 退订热度图灰度数据
    public boolean CLIENT_DetachHeatMapGrayInfo(LLong lAttachHandle);

    // 事件类型EVENT_IVS_RETROGRADEDETECTION(人员逆行事件)对应的数据块描述信息
    public static class DEV_EVENT_RETROGRADEDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public DH_MSG_OBJECT    stuObject;                            // 检测到的物体
        public int              nTrackLineNum;                        // 物体运动轨迹顶点数
        public DH_POINT[]       TrackLine = (DH_POINT[])new DH_POINT().toArray(NET_MAX_TRACK_LINE_NUM); // 物体运动轨迹
        public int              nDirectionPointNum;                   // 规则里规定的方向顶点数
        public DH_POINT[]       stuDirections = (DH_POINT[])new DH_POINT().toArray(NET_MAX_DETECT_LINE_NUM); // 规则里规定的方向
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public DH_POINT[]       DetectRegion = (DH_POINT[])new DH_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[604];            // 保留字节,留待扩展.
    }

    // 智能交通语音播报配置 对应枚举 NET_EM_CFG_TRAFFIC_VOICE_BROADCAST
    public static class NET_CFG_TRAFFIC_VOICE_BROADCAST_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nEnableCount;                         // 使能播报个数
        public int[]            emEnable = new int[NET_MAX_PLATEENABLE_NUM]; // 使能过车车牌播报,见枚举 NET_EM_PLATEENABLE_TYPE
        public byte[]           szNormalCar = new byte[MAX_PATH];     // 普通车辆过车播报内容,例如:播报语音文件"欢迎光临.wav"
        public byte[]           szTrustCar = new byte[MAX_PATH];      // 信任车辆过车播报内容,例如:播报语音文件"欢迎光临.wav"
        public byte[]           szSuspiciousCar = new byte[MAX_PATH]; // 嫌疑车辆过车播报内容,例如:播报语音文件"非注册车辆.wav"
        public NET_TRAFFIC_VOICE_BROADCAST_ELEMENT[] stuElement = (NET_TRAFFIC_VOICE_BROADCAST_ELEMENT[])new  NET_TRAFFIC_VOICE_BROADCAST_ELEMENT().toArray(NET_MAX_BROADCAST_ELEMENT_NUM); // 播报元素
        public int              nElementNum;                          // stuElement中有效数据个数

        public NET_CFG_TRAFFIC_VOICE_BROADCAST_INFO()
        {
            this.dwSize = this.size();
        }// 此结构体大小
    }

    // 播报元素
    public static class NET_TRAFFIC_VOICE_BROADCAST_ELEMENT extends SdkStructure
    {
        public int              emType;                               // 类型(参考NET_EM_VOICE_BROADCAST_ELEMENT_TYPE)
        public byte[]           byReserved1 = new byte[4];            // 字节对齐
        public byte[]           szPrefix = new byte[512];             // 前缀字符串
        public byte[]           szPostfix = new byte[512];            // 后缀字符串
        public byte[]           byReserved = new byte[1024];          // 预留
    }

    // 485串口协议设备配置信息
    public static class NET_CFG_DHRS_INFO extends SdkStructure
    {
        public int              nDeviceNum;                           // 串口设备个数
        public NET_CFG_DHRS_DEVICE_INFO[] stuDHRSDeviceInfo = (NET_CFG_DHRS_DEVICE_INFO[])new NET_CFG_DHRS_DEVICE_INFO().toArray(32); // 串口设备信息
    }

    public static class NET_CFG_DHRS_DEVICE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 串口设备是否启用
        public int              emType;                               // 串口设备类型(参考EM_DHRS_DEVICE_TYPE)
        public NET_CFG_LATTICE_SCREEN_CONFIG stuLatticeScreenConfig;  // 485串口点阵屏配置
        public byte[]           byReserved = new byte[4096];          //预留字节
    }

    // 485串口点阵屏配置
    public static class NET_CFG_LATTICE_SCREEN_CONFIG extends SdkStructure
    {
        public int              nAddress;                             // 配置对应设备的地址, 范围[1,31]
        public int              emRollSpeedLevel;                     // 点阵屏滚动速度级别(参考EM_ROLL_SPEED_LEVEL)
        public int              nLogicScreenNum;                      // 逻辑屏个数
        public NET_LOGIC_SCREEN[] stuLogicScreens = (NET_LOGIC_SCREEN[])new NET_LOGIC_SCREEN().toArray(8); // 逻辑屏信息, 划分物理屏的某一区域为逻辑屏
        public int              nOutPutVoiceVolume;                   // 语音播报音量大小, 范围：[0 - 100]
        public int              nOutPutVoiceSpeed;                    // 语音播报速度， 范围：[0-100]
        public byte[]           byReserved = new byte[1024];
    }

    // 串口设备类型
    public static class EM_DHRS_DEVICE_TYPE extends SdkStructure
    {
        public static final int   EM_DHRS_DEVICE_TYPE_UNKNOWN = 0;      // 未知
        public static final int   EM_DHRS_DEVICE_TYPE_STEADYLIGHT = 1;  // 常亮灯
        public static final int   EM_DHRS_DEVICE_TYPE_STROBELIGHT = 2;  // 可以通过485控制的频闪灯
        public static final int   EM_DHRS_DEVICE_TYPE_POWERMODULE = 3;  // 电源模块
        public static final int   EM_DHRS_DEVICE_TYPE_LATTICESCREEN = 4; // 点阵屏
        public static final int   EM_DHRS_DEVICE_TYPE_INDICATORLIGHT = 5; // 指示灯
        public static final int   EM_DHRS_DEVICE_TYPE_RAINBRUSH = 6;    // 雨刷洗涤模块
        public static final int   EM_DHRS_DEVICE_TYPE_FLASHLAMP = 7;    // 爆闪灯
        public static final int   EM_DHRS_DEVICE_TYPE_RFID = 8;         // 射频识别
        public static final int   EM_DHRS_DEVICE_TYPE_COMMON = 9;       // 通用485
    }

    // 逻辑屏信息
    public static class NET_LOGIC_SCREEN extends SdkStructure
    {
        public NET_RECT         stuRegion;                            // 逻辑屏区域, 实际点阵屏坐标
        public int              emDisplayMode;                        // 显示动作(参考EM_DISPLAY_MODE)
        public int              emDisplayColor;                       // 显示颜色(参考EM_DISPLAY_COLOR)
        public byte[]           byReserved = new byte[512];           // 预留字节
    }

    // 点阵屏滚动速度级别
    public static class EM_ROLL_SPEED_LEVEL extends SdkStructure
    {
        public static final int   EM_ROLL_SPEED_LEVEL_UNKNOWN = 0;      // 未知
        public static final int   EM_ROLL_SPEED_LEVEL_SLOW = 1;         // 慢
        public static final int   EM_ROLL_SPEED_LEVEL_SLOWER = 2;       // 较慢
        public static final int   EM_ROLL_SPEED_LEVEL_MEDIUM = 3;       // 中等
        public static final int   EM_ROLL_SPEED_LEVEL_FASTER = 4;       // 较快
        public static final int   EM_ROLL_SPEED_LEVEL_FAST = 5;         // 快
    }

    // 显示动作
    public static class EM_DISPLAY_MODE extends SdkStructure
    {
        public static final int   EM_DISPLAY_MODE_UNKNOWN = 0;          // 未知
        public static final int   EM_DISPLAY_MODE_ROLL = 1;             // 滚动
        public static final int   EM_DISPLAY_MODE_INTERCEPT = 2;        // 截取
    }

    // 显示颜色
    public static class EM_DISPLAY_COLOR extends SdkStructure
    {
        public static final int   EM_DISPLAY_COLOR_UNKNOWN = 0;         // 未知
        public static final int   EM_DISPLAY_COLOR_RED = 1;             // 红
        public static final int   EM_DISPLAY_COLOR_GREEN = 2;           // 绿
        public static final int   EM_DISPLAY_COLOR_YELLOW = 3;          // 黄
    }

    public static class NET_FACEANALYSIS_RULE_INFO extends SdkStructure {
        public int              dwSize;                               // 结构体大小
        public int              nDetectRegionPoint;                   // 检测区顶点数
        public POINTCOORDINATE[] stuDetectRegion = new POINTCOORDINATE[20]; // 检测区
        public int              nSensitivity;                         // 灵敏度,范围[1,10],灵敏度越高越容易检测
        public int              nLinkGroupNum;                        // 联动布控个数
        public NET_CFG_LINKGROUP_INFO[] stuLinkGroup = new NET_CFG_LINKGROUP_INFO[20]; // 联动的布控组
        public NET_CFG_STRANGERMODE_INFO stuStrangerMode;             // 陌生人布防模式
        public int              bSizeFileter;                         // 规则特定的尺寸过滤器是否有效
        public NET_CFG_SIZEFILTER_INFO stuSizeFileter;                // 规则特定的尺寸过滤器
        public int              bFeatureEnable;                       // 是否开启人脸属性识别, IPC增加
        public int              nFaceFeatureNum;                      // 需要检测的人脸属性个数
        public int[]            emFaceFeatureType = new int[32];      // 需检测的人脸属性 NET_EM_FACEFEATURE_TYPE
        public int              bFeatureFilter;                       // 在人脸属性开启前提下，如果人脸图像质量太差，是否不上报属性
        // true-图像太差不上报属性 false-图像很差也上报属性(可能会非常不准，影响用户体验)
        public int              nMinQuality;                          // 人脸图片质量阈值,和bFeatureFilter一起使用 范围[1,100]
        public NET_CFG_FACE_BEAUTIFICATION stuFaceBeautification = new NET_CFG_FACE_BEAUTIFICATION(); //人Lian美化,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_CFG_FACE_BEAUTIFICATION}

        public NET_FACEANALYSIS_RULE_INFO(){
            for(int i = 0; i < stuDetectRegion.length; i++ ){
                stuDetectRegion[i] = new POINTCOORDINATE();
            }
            for(int i = 0; i < stuLinkGroup.length; i++){
                stuLinkGroup[i] = new NET_CFG_LINKGROUP_INFO();
            }
            dwSize  =   this.size();
        }
    }

    // 联动的布控组
    public static class NET_CFG_LINKGROUP_INFO extends SdkStructure {
        public int              bEnable;                              // 布控组是否启用
        public byte[]           szGroupID = new byte[64];             // 布控组ID
        public byte             bySimilarity;                         // 相似度阈值 1-100
        public byte[]           bReserved1 = new byte[3];             // 字节对齐
        public byte[]           szColorName = new byte[32];           // 事件触发时绘制人脸框的颜色
        public int              bShowTitle;                           // 事件触发时规则框上是否显示报警标题
        public int              bShowPlate;                           // 事件触发时是否显示比对面板
        public NET_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public byte[]           bReserved = new byte[512];            // 保留字段
    }

    // 陌生人布防模式
    public static class NET_CFG_STRANGERMODE_INFO extends SdkStructure {
        public int              bEnable;                              // 模式是否启用
        public byte[]           szColorHex = new byte[8];             // 事件触发时绘制人脸框的颜色
        public int              bShowTitle;                           // 事件触发时规则框上是否显示报警标题
        public int              bShowPlate;                           // 事件触发时是否显示比对面板
        public NET_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
        public byte[]           bReserved = new byte[512];            // 保留字段
    }

    // 人脸属性类型
    public static class NET_EM_FACEFEATURE_TYPE
    {
        public final static int   NET_EM_FACEFEATURE_UNKNOWN = 0;       // 未知
        public final static int   NET_EM_FACEFEATURE_SEX = 1;           // 性别
        public final static int   NET_EM_FACEFEATURE_AGE = 2;           // 年龄
        public final static int   NET_EM_FACEFEATURE_EMOTION = 3;       // 表情
        public final static int   NET_EM_FACEFEATURE_GLASSES = 4;       // 眼镜状态
        public final static int   NET_EM_FACEFEATURE_EYE = 5;           // 眼睛状态
        public final static int   NET_EM_FACEFEATURE_MOUTH = 6;         // 嘴巴状态
        public final static int   NET_EM_FACEFEATURE_MASK = 7;          // 口罩状态
        public final static int   NET_EM_FACEFEATURE_BEARD = 8;         // 胡子状态
        public final static int   NET_EM_FACEFEATURE_ATTRACTIVE = 9;    // 魅力值
    }

    // 事件类型EM_ANALYSE_EVENT_FEATURE_ABSTRACT(特征提取)对应的数据块描述信息
    public static class DEV_EVENT_FEATURE_ABSTRACT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public int              emClassType;                          // 智能事件所属大类 EM_CLASS_TYPE
        public int              nFeatureNum;                          // 特征值数量
        public NET_FEATURE_VECTOR_INFO[] stuFeatureVectorList = new NET_FEATURE_VECTOR_INFO[10]; // 特征值数组，同一个图片需要进行多个版本的特征向量提取，在一个事件中返回
        public byte[]           byReserved = new byte[1024];          // 预留字节

        public DEV_EVENT_FEATURE_ABSTRACT_INFO(){
            for(int i=0;i<stuFeatureVectorList.length;i++){
                stuFeatureVectorList[i] = new NET_FEATURE_VECTOR_INFO();
            }
        }
    }

    // 特征值信息
    public static class NET_FEATURE_VECTOR_INFO extends SdkStructure
    {
        public byte[]           szFeatureVersion = new byte[32];      // 特征版本版本号
        public int              emFeatureErrCode;                     // 特征建模失败错误码 EM_FEATURE_ERROR_CODE
        public NET_FEATURE_VECTOR stuFeatureVector;                   // 特征值的偏移和大小信息
        public NET_FACE_ATTRIBUTES stuFaceAttribute;                  // 人脸属性 ,当提取人脸特征向量成功时上报
        public byte[]           byReserved = new byte[968];           // 预留字节
    }

    // 特征建模失败错误码
    public static class EM_FEATURE_ERROR_CODE
    {
        public final static int   EM_FEATURE_ERROR_SUCCESS = 0;         // 成功
        public final static int   EM_FEATURE_ERROR_UNKNOWN = 1;         // 未知
        public final static int   EM_FEATURE_ERROR_IMAGE_FORMAT_ERROR = 2; // 图片格式问题
        public final static int   EM_FEATURE_ERROR_NOFACE_OR_NOTCLEAR = 3; // 无人脸或不够清晰
        public final static int   EM_FEATURE_ERROR_MULT_FACES = 4;      // 多个人脸
        public final static int   EM_FEATURE_ERROR_IMAGH_DECODE_FAILED = 5; // 图片解码失败
        public final static int   EM_FEATURE_ERROR_NOT_SUGGEST_STORAGE = 6; // 不推荐入库
        public final static int   EM_FEATURE_ERROR_DATABASE_OPERATE_FAILED = 7; // 数据库操作失败
        public final static int   EM_FEATURE_ERROR_GET_IMAGE_FAILED = 8; // 获取图片失败
        public final static int   EM_FEATURE_ERROR_SYSTEM_EXCEPTION = 9; // 系统异常（如Licence失效、建模分析器未启动导致的失败）
    }

    // 人脸属性
    public static class NET_FACE_ATTRIBUTES extends SdkStructure
    {
        public int[]            nAngle = new int[3];                  // 人脸抓拍角度,三个角度分别是：仰俯角,偏航角,翻滚角；默认值[999,999,999]表示无此数据
        public int              nFaceQuality;                         // 人脸抓拍质量分数,取值范围 0~10000
        public int              nFaceAlignScore;                      // 人脸对齐得分分数,取值范围 0~10000，-1为无效值
        public byte[]           byReserved = new byte[36];            // 预留字节
    }

    // 事件类型EVENT_IVS_FEATURE_ABSTRACT(提取特征)对应的规则配置
    public static class NET_FEATURE_ABSTRACT_RULE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nFeature;                             // 特征的数量
        public NET_FEATURE_ABSTRACT_VERSION[] szFeatureVersions = new NET_FEATURE_ABSTRACT_VERSION[10]; // 对图片进行特征向量提取时使用，需要对图片进行同一种特征向量多个版本进行提取,最大是10个版本
        public int              emAbstractType;                       // 进行特征提取的类型 EM_FEATURE_ABSTRACT_TYPE

        public NET_FEATURE_ABSTRACT_RULE_INFO(){
            for(int i = 0;i<szFeatureVersions.length;i++){
                szFeatureVersions[i] = new NET_FEATURE_ABSTRACT_VERSION();
            }
            this.dwSize = this.size();
        }
    }

    public static class NET_FEATURE_ABSTRACT_VERSION extends SdkStructure
    {
        public byte[]           szFeatureVersion = new byte[32];
    }

    // 进行特征提取的类型
    public static class EM_FEATURE_ABSTRACT_TYPE
    {
        public final static int   EM_FEATURE_ABSTRACT_UNKNOWN = -1;     // 未知
        public final static int   EM_FEATURE_ABSTRACT_FACE = 0;         // 人脸
        public final static int   EM_FEATURE_ABSTRACT_HUMAN_TRAIT = 1;  // 人体
        public final static int   EM_FEATURE_ABSTRACT_VEHICLE = 2;      // 机动车
        public final static int   EM_FEATURE_ABSTRACT_NON_MOTOR_VEHICLE = 3; // 非机动车
    }

    public static class ALARM_TRAFFIC_PARKING_TIMEOUT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public NET_TIME         stuInParkTime;                        // 进场时间
        public NET_TIME         stuOutParkTime;                       // 出场时间
        public int              nParkingTime;                         // 停车时长，单位秒
        public byte[]           byReserved = new byte[1024];          // 预留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆的数据库记录
    }

    // 嫌疑车辆上报事件, 对应事件类型 DH_ALARM_TRAFFIC_SUSPICIOUSCAR
    public static class ALARM_TRAFFIC_SUSPICIOUSCAR_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nAction;                              // 事件动作, -1:未知,0:Start, 1:Stop, 2:Pulse
        public DH_MSG_OBJECT    stuVehicle;                           // 车身信息
        public NET_TRAFFIC_LIST_RECORD stuCarInfo;                    // 车辆的禁止名单信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息

        public ALARM_TRAFFIC_SUSPICIOUSCAR_INFO(){
            this.dwSize = this.size();
        }
    }

    // 事件类型 DH_ALARM_PARKING_LOT_STATUS_DETECTION (室外停车位状态检测事件) 对应的数据块描述信息
    public static class ALARM_PARKING_LOT_STATUS_DETECTION extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 1:开始 2:停止
        public byte[]           szName = new byte[128];               // 事件名称
        public int              emClassType;                          // 智能事件所属大类 EM_CLASS_TYPE
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public int              nEventID;                             // 事件ID
        public int              nRuleID;                              // 智能事件规则编号，用于标示哪个规则触发的事件
        public int              nSequence;                            // 帧序号
        public int              nParkingStatusNum;                    // 室外停车位个数
        public NET_PARKING_STATUS[] stuParkingStatus = new NET_PARKING_STATUS[100]; // 室外停车位状态
        public byte[]           byReserved = new byte[1020];          // 预留字节

        public ALARM_PARKING_LOT_STATUS_DETECTION(){
            for(int i = 0;i<stuParkingStatus.length;i++){
                stuParkingStatus[i] = new NET_PARKING_STATUS();
            }
        }
    }

    // 室外停车位状态
    public static class NET_PARKING_STATUS extends SdkStructure
    {
        public byte[]           szName = new byte[32];                // 车位名称
        public int              nID;                                  // 车位ID，范围:[0,99]
        public int              nParkedNumber;                        // 车位内已停车位数量，范围:[0,255]
        public int              emChangeStatus;                       // 车位内已停车位数量相对上次上报的变化状态 EM_PARKING_NUMBER_CHANGE_STATUS
        public byte[]           reserved = new byte[252];             // 预留字节
    }

    // 车位内已停车位数量相对上次上报的变化状态
    public static class EM_PARKING_NUMBER_CHANGE_STATUS
    {
        public final static int   EM_PARKING_NUMBER_CHANGE_STATUS_UNKNOWN = -1; // 未知
        public final static int   EM_PARKING_NUMBER_CHANGE_STATUS_NO_CHANGE = 0; // 无变化
        public final static int   EM_PARKING_NUMBER_CHANGE_STATUS_INCREASE = 1; // 数量增加
        public final static int   EM_PARKING_NUMBER_CHANGE_STATUS_DECREASE = 2; // 数量减少
    }

    // 停车超时检测配置
    public static class NET_CFG_PARKING_TIMEOUT_DETECT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              bEnable;                              // 是否使能停车超时检测
        public int              nParkingTime;                         // 可停车时长, 单位为秒, 默认值为604800. 范围:3600-604800, 超过指定时长则判断为超时停车
        public int              nDetectInterval;                      // 上报超时停车间隔, 单位为秒, 默认值为86400(24小时). 最小值为600, 最大值为86400

        public NET_CFG_PARKING_TIMEOUT_DETECT(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_RemoveParkingCarInfo 接口输入参数
    public static class NET_IN_REMOVE_PARKING_CAR_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public DEV_OCCUPIED_WARNING_INFO stuParkingCarInfo;           // 车位信息

        public NET_IN_REMOVE_PARKING_CAR_INFO(){
            this.dwSize = this.size();
        }
    }

    // CLIENT_RemoveParkingCarInfo 接口输出参数
    public static class NET_OUT_REMOVE_PARKING_CAR_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_REMOVE_PARKING_CAR_INFO(){
            this.dwSize = this.size();
        }
    }

  //对应CLIENT_StartSearchDevicesEx接口
    public static class DEVICE_NET_INFO_EX2 extends SdkStructure
    {
        public DEVICE_NET_INFO_EX stuDevInfo;                         // 设备信息结构体
        public byte[]           szLocalIP = new byte[64];             // 搜索到设备的本地IP地址
        public byte[]           cReserved = new byte[2048];           // 扩展字段
    }

    // 异步搜索设备回调(pDevNetInfo内存由SDK内部申请释放, 参考DEVICE_NET_INFO_EX2)
    public interface fSearchDevicesCBEx extends Callback {
        public void invoke(LLong lSearchHandle,Pointer pDevNetInfo,Pointer pUserData);
    }

    // 异步搜索设备(参考NET_IN_STARTSERACH_DEVICE,NET_OUT_STARTSERACH_DEVICE)
    public LLong CLIENT_StartSearchDevicesEx(Pointer pInBuf,Pointer pOutBuf);

    // 同步跨网段搜索设备IP (pIpSearchInfo内存由用户申请释放)
    // szLocalIp为本地IP，可不做输入, fSearchDevicesCB回调
    // 接口调用1次只发送搜索信令1次
    public boolean CLIENT_SearchDevicesByIPs(DEVICE_IP_SEARCH_INFO pIpSearchInfo,Callback cbSearchDevices,Pointer dwUserData,String szLocalIp,int dwWaitTime);

    /**
     *
     * @param lLoginID
     * @param pInParam
     * @param pOutParam
     * @param nWaitTime
     * @return boolean
     */
    public boolean CLIENT_AsyncAddCustomDevice(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /***
     *
     * @param lLoginID
     * @param nChannelID
     * @param dwFocusCommand
     * @param nFocus
     * @param nZoom
     * @param reserved
     * @param waittime
     * @return
     * 镜头聚焦控制  dwFocusCommand = 0
     * 为聚焦调节dwFocusCommand = 1
     * 为连续聚焦调节dwFocusCommand = 2,为自动聚焦调节,调节焦点至最佳位置。nFocus和nZoom无效。
     */
    public boolean CLIENT_FocusControl(LLong lLoginID,int nChannelID,int dwFocusCommand,double nFocus,double nZoom,Pointer reserved,int waittime);

    //事件类型 EVENT_IVS_TUMBLE_DETECTION(倒地报警事件)对应数据块描述信息
    public static class DEV_EVENT_TUMBLE_DETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              UTCMS;                                // UTC时间对应的毫秒数
        public int              emClassType;                          // 智能事件所属大类 EM_CLASS_TYPE
        public int              nObjectID;                            // 目标ID
        public byte[]           szObjectType = new byte[NET_COMMON_STRING_16]; // 物体类型,支持以下:
        //"Unknown", "Human", "Vehicle", "Fire", "Smoke", "Plate", "HumanFace",
        // "Container", "Animal", "TrafficLight", "PastePaper", "HumanHead", "BulletHole", "Entity"
        public NET_RECT         stuBoundingBox;                       // 物体包围盒
        public byte[]           szSerialUUID = new byte[22];          // 智能物体全局唯一物体标识
        // 有效数据位21位，包含’\0’
        // 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
        // 中间14位YYYYMMDDhhmmss:年月日时分秒
        // 后5位%u%u%u%u%u：物体ID，如00001
        public SCENE_IMAGE_INFO stuSceneImage;                        // 全景广角图
        public Pointer          pstuImageInfo;                        // 图片信息数组,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); // 事件公共扩展字段结构体
        public int              nDetectRegionNum;                     //检测区个数
        public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[20]; //检测区
        public byte[]           bReserved = new byte[834];            // 保留字节

    	public DEV_EVENT_TUMBLE_DETECTION_INFO() {
    		for (int i = 0; i < stuDetectRegion.length; i++) {
    			stuDetectRegion[i] = new NET_POINT_EX();
    		}
    	}
    }

    /**
     * 设置二维码信息
     * @param lLoginID 登录句柄
     * @param pInParam 入参,对应结构体{@link NET_IN_SET_2DCODE}
     * @param pOutParam 出参,对应结构体{@link NET_OUT_SET_2DCODE}
     * @param nWaitTime 超时时间
     * @return
     */
    public boolean CLIENT_Set2DCode(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 订阅热度图数据,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参,对应结构体 {@link NET_IN_ATTACH_VIDEOSTAT_HEATMAP}
     * @Param pOutParam 出参,对应结构体{@link NET_OUT_ATTACH_VIDEOSTAT_HEATMAP}
     * 也可使用{@link EmptyStructure}
     * @param nWaitTime 超时时间
     * @return
     */
    public  LLong CLIENT_AttachVideoStatHeatMap(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取热图数据,pInParam与pOutParam内存由用户申请释放
     * @param lAttachHandle 热度图订阅句柄
     * @param pInParam 入参,对应结构体 {@link NET_IN_GET_VIDEOSTAT_HEATMAP}
     * @param pOutParam 出参,对应结构体{@link NET_OUT_GET_VIDEOSTAT_HEATMAP}
     * @param nWaitTime 超时时间
     * @return
     */
    public boolean CLIENT_GetVideoStatHeatMap(LLong lAttachHandle,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 取消订阅热度图数据
     * @param lAttachHandle 订阅句柄
     * @return
     */
    public boolean CLIENT_DetachVideoStatHeatMap(LLong lAttachHandle);

    //
    /**
     *
     * @param lLoginID
     * @param pstInParam
     *
     * @param nWaitTime = NET_INTERFACE_DEFAULT_TIMEOUT
     * @return
     */
    /**
     * 计算两张人脸图片的相似度faceRecognitionServer.matchTwoFace,pstInParam与pstOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pstInParam 入参 {@link NET_MATCH_TWO_FACE_IN}
     * @param pstOutParam 出参{@link NET_MATCH_TWO_FACE_OUT}
     * @param nWaitTime 接口超时时间,默认超时时间为3000
     * @return
     */
    public boolean CLIENT_MatchTwoFaceImage(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 设置相机参数, pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_SET_CAMERA_CFG}
     * @param pOutParam 出参{@link NET_OUT_SET_CAMERA_CFG}
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_SetCameraCfg(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取相机参数, pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_GET_CAMERA_CFG}
     * @param pOutParam 出参{@link NET_OUT_GET_CAMERA_CFG}
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_GetCameraCfg(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置通道参数, pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_SET_CHANNEL_CFG}
     * @param pOutParam 出参{@link NET_OUT_SET_CHANNEL_CFG}
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_SetChannelCfg(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取通道参数, pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_GET_CHANNEL_CFG}
     * @param pOutParam 出参{@link NET_OUT_GET_CHANNEL_CFG}
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_GetChannelCfg(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 订阅安全门报警统计信息
     *  CLIENT_NET_API LLONG CALL_METHOD CLIENT_SecurityGateAttachAlarmStatistics(LLONG lLoginID, const NET_IN_SECURITYGATE_ATTACH_ALARM_STATISTICS* pInParam, NET_OUT_SECURITYGATE_ATTACH_ALARM_STATISTICS* pOutParam, int nWaitTime);
     *
     */
    public LLong CLIENT_SecurityGateAttachAlarmStatistics(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

/**
 *  订阅X光机包裹数量统计信息
    CLIENT_NET_API LLONG CALL_METHOD CLIENT_XRayAttachPackageStatistics(LLONG lLoginID, const NET_IN_XRAY_ATTACH_PACKAGE_STATISTICS* pInParam, NET_OUT_XRAY_ATTACH_PACKAGE_STATISTICS* pOutParam, int nWaitTime);
*/
    public LLong CLIENT_XRayAttachPackageStatistics(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 取消订阅X光机包裹数量统计信息
     * CLIENT_NET_API BOOL CALL_METHOD CLIENT_XRayDetachPackageStatistics(LLONG lAttachHandle);
      */
    public boolean CLIENT_XRayDetachPackageStatistics(LLong lAttachHandle);

    /**
     * 交通灯信号检测-获取相机信息, pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_GET_CAMERA_INFO}
     * @param pOutParam 出参{@link NET_OUT_GET_CAMERA_INFO}
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_GetCameraInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 主动获取每个热成像点的像素温度
     * @param lLoginID 登录句柄
     * @param pstInParam 入参 {@link NET_IN_GET_HEATMAPS_INFO}
     * @param pstOutParam 出参{@link NET_OUT_GET_HEATMAPS_INFO}
     * @param nWaitTime 接口超时时间,默认超时时间为 3000
     * @return
     */
    public boolean CLIENT_GetHeatMapsDirectly(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /******************************************************************************
    功能描述	:	透传扩展接口,按透传类型走对应透传方式接口，目前支持F6纯透传, 同时兼容CLIENT_TransmitInfoForWeb接口
    参数定义	:
        lLoginID:       登录接口返回的句柄
        pInParam:       透传扩展接口输入参数
        pOutParam       透传扩展接口输出参数
        nWaittime       接口超时时间
    返 回 值	：	BOOL  TRUE :成功; FALSE :失败
    ******************************************************************************/
    /**
     *
     * @param lLoginID
     * @param pInParam NET_IN_TRANSMIT_INFO
     * @param pOutParam NET_OUT_TRANSMIT_INFO
     * @return
     */
    public boolean CLIENT_TransmitInfoForWebEx(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaittime);

    // CLIENT_TransmitInfoForWebEx输入参数
    public static class NET_IN_TRANSMIT_INFO extends SdkStructure
    {
        public int              dwSize;                               // 用户使用该结构体，dwSize需赋值为sizeof(NET_IN_TRANSMIT_INFO)
        public int              emType;                               // 透传类型,参考NET_TRANSMIT_INFO_TYPE
        public String           szInJsonBuffer;                       // Json请求数据,用户申请空间
        public int              dwInJsonBufferSize;                   // Json请求数据长度
        public Pointer          szInBinBuffer;                        // 二进制请求数据，用户申请空间
        public int              dwInBinBufferSize;                    // 二进制请求数据长度
        public int              emEncryptType;                        // 加密类型(参考EM_TRANSMIT_ENCRYPT_TYPE)

   	 public NET_IN_TRANSMIT_INFO()
	 {
	     this.dwSize = this.size();
	 }// 此结构体大小
    }

    // CLIENT_TransmitInfoForWebEx输出参数
    public static class NET_OUT_TRANSMIT_INFO extends SdkStructure
    {
        public int              dwSize;                               // 用户使用该结构体时，dwSize需赋值为sizeof(NET_OUT_TRANSMIT_INFO)
        public Pointer          szOutBuffer;                          // 应答数据缓冲空间, 用户申请空间
        public int              dwOutBufferSize;                      // 应答数据缓冲空间长度
        public int              dwOutJsonLen;                         // Json应答数据长度
        public int              dwOutBinLen;                          // 二进制应答数据长度

       public NET_OUT_TRANSMIT_INFO()
  	 {
  	     this.dwSize = this.size();
  	 }// 此结构体大小
    }

    // 透传类型
    public static class NET_TRANSMIT_INFO_TYPE
    {
        public static final int   NET_TRANSMIT_INFO_TYPE_DEFAULT = 0;   // 默认类型，即CLIENT_TransmitInfoForWeb接口的兼容透传方式
        public static final int   NET_TRANSMIT_INFO_TYPE_F6 = 1;        // F6纯透传
    }

    // 透传加密类型
    public static class EM_TRANSMIT_ENCRYPT_TYPE
    {
        public static final int   EM_TRANSMIT_ENCRYPT_TYPE_UNKNOWN = -1; // 未知
        public static final int   EM_TRANSMIT_ENCRYPT_TYPE_NORMAL = 0;  // SDK内部自行确定是否加密，默认
        public static final int   EM_TRANSMIT_ENCRYPT_TYPE_MULTISEC = 1; // 设备支持加密的场景下，走multiSec加密
        public static final int   EM_TRANSMIT_ENCRYPT_TYPE_BINARYSEC = 2; // 设备支持加密的场景下，走binarySec加密，二进制部分不加密
    }

    /**
     * 批量下载文件,pstInParam与pstOutParam内存由用户申请释放
     * 入参 NET_IN_DOWNLOAD_MULTI_FILE 出参 NET_OUT_DOWNLOAD_MULTI_FILE
     */
    public boolean CLIENT_DownLoadMultiFile(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int waittime);

    //JNA Callback方法定义,断线回调
    public interface fMultiFileDownLoadPosCB extends Callback {
        public void invoke(LLong lDownLoadHandle,int dwID,int dwFileTotalSize,int dwDownLoadSize,int nError,Pointer dwUser,Pointer pReserved);
    }

    /**
     * 订阅摄像头状态,pstInParam与pstOutParam内存由用户申请释放
     * pstInParam->NET_IN_CAMERASTATE ; pstOutParam->NET_OUT_CAMERASTATE
     */
    public LLong CLIENT_AttachCameraState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 停止订阅摄像头状态,lAttachHandle是CLIENT_AttachCameraState返回值
     */
    public boolean CLIENT_DetachCameraState(LLong lAttachHandle);

    // CLIENT_AttachCameraState()回调函数原形, 每次1条,pBuf->NET_CB_CAMERASTATE dwSize == nBufLen
    public interface fCameraStateCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

     /**
      * 获取IPC设备的存储信息
      * @param lLoginID 登录句柄
      * @param pstInParam 入参 {@link NET_IN_GET_DEVICE_AII_INFO}
      * @param pstOutParam 出参{@link NET_OUT_GET_DEVICE_AII_INFO}
      * @param nWaitTime
      * @return
      */
    public boolean CLIENT_GetDeviceAllInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    // 交通信号灯回调函数 lLoginID - 登录句柄 lAttchHandle - 订阅句柄
    public interface fTrafficLightState extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer pBuf,long dwUser);
    }

    /**
     * 订阅交通信号灯状态 , pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pInParam 入参 {@link NET_IN_ATTACH_TRAFFICLIGHT_INFO}
     * @param pOutParam 出参{@link NET_OUT_ATTACH_TRAFFICLIGHT_INFO}
     * @param nWaitTime
     * @return
     */
    public LLong CLIENT_AttachTrafficLightState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 退订交通信号灯状态
     * @param lAttchHandle 订阅句柄
     * @return
     */
    public boolean CLIENT_DetachTrafficLightState(LLong lAttchHandle);

    /**
     * 订阅雷达的报警点信息 , pInParam 和pOutParam 资源由用户申请和释放
     * @param lLoginID 登录句柄
     * @param pstInParam 入参 {@link NET_IN_RADAR_ALARMPOINTINFO}
     * @param pstOutParam 出参 {@link NET_OUT_RADAR_ALARMPOINTINFO}
     * @param nWaitTime
     * @return
     */
    public LLong CLIENT_AttachRadarAlarmPointInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 取消订阅雷达的报警点信息
     * @param lAttachHandle 订阅句柄
     * @return
     */
    public boolean CLIENT_DetachRadarAlarmPointInfo(LLong lAttachHandle);

    /**
     * 雷达报警点信息回调函数
     */
    public interface fRadarAlarmPointInfoCallBack extends Callback {
/**
         *
         * @param lLoginId 登录句柄
         * @param lAttachHandle 订阅句柄
         * @param pBuf {@link NET_RADAR_NOTIFY_ALARMPOINTINFO}
         * @param dwBufLen pBuf中结构体的长度
         * @param pReserved 保留数据
         * @param dwUser 用户自定义数据
         */
        public void invoke(LLong lLoginId,LLong lAttachHandle,Pointer pBuf,int dwBufLen,Pointer pReserved,Pointer dwUser);
    }

    /**
     * 查询系统状态(pstuStatus内存由用户申请释放)
     * @param lLoginID
     * @param pstInParam NET_SYSTEM_STATUS
     * @param nWaitTime
     * @return
     */
    public boolean CLIENT_QuerySystemStatus(LLong lLoginID,Pointer pstInParam,int nWaitTime);

    /**
     * 订阅云台元数据接口,pstuInPtzStatusProc与pstuOutPtzStatusProc内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pstInParam 入参 {@link NET_IN_PTZ_STATUS_PROC}
     * @param pstOutParam 出参{@link NET_OUT_PTZ_STATUS_PROC}
     * @param nWaitTime
     * @return
     */
    public LLong CLIENT_AttachPTZStatusProc(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 停止订阅云台元数据接口,lAttachHandle是CLIENT_AttachPTZStatusProc返回值
     * @param lAttachHandle 订阅句柄
     * @return
     */
    public boolean CLIENT_DetachPTZStatusProc(LLong lAttachHandle);

    /**
     * 订阅云台元数据接口回调函数原型
     * pBuf 现阶段主要为 NET_PTZ_LOCATION_INFO 类型 {@link NET_PTZ_LOCATION_INFO}
     */
    public interface fPTZStatusProcCallBack extends Callback {
        public void invoke(LLong lLoginId,LLong lAttachHandle,Pointer pBuf,int dwBufLen,long dwUser);
    }

    /**
     * 查询某月的各天是否存在录像文件,
     *
     * @param lLoginID
     * @param nChannelId
     * @param nRecordFileType EM_QUERY_RECORD_TYPE 的枚举值
     *        nRecordFileType == EM_RECORD_TYPE_CARD，pchCardid输入卡号，限制字符长度 59 字节
     *        nRecordFileType == EM_RECORD_TYPE_FIELD，pchCardid输入自定义字段，限制字符长度 256 字节
     * @param tmMonth Pointer -> NET_TIME
     * @param pchCardid Pointer -> byte[]
     * @param pRecordStatus Poiter -> NET_RECORD_STATUS
     * @param waittime
     * @return boolean
     */
    public boolean CLIENT_QueryRecordStatus(LLong lLoginID,int nChannelId,int nRecordFileType,Pointer tmMonth,Pointer pchCardid,Pointer pRecordStatus,int waittime);

   //设置SDK本地参数,在CLIENT_Init之前调用，szInBuffer内存由用户申请释放，里面存放被设置的信息，具体见NET_EM_SDK_LOCAL_CFG_TYPE类型对应结构体
    boolean CLIENT_SetSDKLocalCfg(int emCfgType,Pointer szInBuffer);

    /**
     * 开启重定向服务扩展接口
     * @param pInParam {@link NET_IN_START_REDIRECT_SERVICE}
     * @param pOutParam NET_OUT_START_REDIRECT_SERVICE,空结构体,可使用{@link EmptyStructure}
     * @return
     */
    LLong CLIENT_StartRedirectServiceEx(Pointer pInParam,Pointer pOutParam);

    /**
     * 停止重定向服务
     * @param lServerHandle 服务句柄
     * @return
     */
    boolean CLIENT_StopRedirectService(LLong lServerHandle);

    /**
    * 设置重定向服务器的IP和Port
    * @param lDevHandle 重定向设备句柄
    * @param ARSIP 重定向设备IP
    * @param ARSPort 重定向设备端口
    * @param nRetry 设备主动注册尝试次数
    * @return
    */
    boolean CLIENT_SetAutoRegisterServerInfo(LLong lDevHandle,String ARSIP,short ARSPort,short nRetry);

    /**
    * 强制I帧,用于拉流优化
    * @param lLoginID 登录句柄
    * @param nChannelID 通道号
    * @param nSubChannel 码流类型:0:主码流,1:子码流1
    * @return
    */
    boolean CLIENT_MakeKeyFrame(LLong lLoginID,int nChannelID,int nSubChannel);

    /**
     * 关闭设备
     */
    boolean CLIENT_ShutDownDev(LLong lLoginID);

    /**
     * 设置通道录像状态(pRsBuffer内存由用户申请释放)
     */
    boolean CLIENT_SetupRecordState(LLong lLoginID,Pointer pRSBuffer,int nRSBufferlen);

    /**
     * 设置通道辅码流录像状态(pRsBuffer内存由用户申请释放)
     */
    boolean CLIENT_SetupExtraRecordState(LLong lLoginID,Pointer pRSBuffer,int nRSBufferlen,Pointer pReserved);

    /**
     * 增加远程录像备份任务, pInParam pOutParam 内存有用户申请释放
     * @param lRestoreID restoreId
     * @param pInParam -> NET_IN_ADD_REC_BAK_RST_REMOTE_TASK
     * @param pOutParam -> NET_OUT_ADD_REC_BAK_RST_REMOTE_TASK
     * @param nWaitTime 超时时间
     * @return 添加是否成功
     */
    boolean CLIENT_AddRecordBackupRestoreRemoteTask(LLong lRestoreID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置停车规则
     */
    boolean CLIENT_SetParkingRule(LLong lLoginID,NET_IN_SET_PARKINGRULE_INFO pstInParam,NET_OUT_SET_PARKINGRULE_INFO pstOutParam,int nWaitTime);

  // 设置运行模式参数,在CLIENT_Init之后调用 pstuRunParams->NET_RUNMODE_PARAMS
    boolean CLIENT_SetRunModeParams(Pointer pstuRunParams);

    public boolean CLIENT_DownloadPieceFile(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

  // 清除当前时间段内人数统计信息, 重新从0开始计算
  //CLIENT_ControlDevice接口的 DH_CTRL_CLEAR_SECTION_STAT命令参数
    public static class NET_CTRL_CLEAR_SECTION_STAT_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             // 视频通道号

      public NET_CTRL_CLEAR_SECTION_STAT_INFO(){
          this.dwSize = this.size();
      }
    }

//刻录设备回调函数原形,lAttachHandle是CLIENT_AttachBurnState返回值, 每次1条,pBuf->dwSize == nBufLen (pBuf->NET_CB_BACKUPTASK_STATE)
    public interface fAttachBackupTaskStateCB extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pBuf,Pointer dwUser);
    }

//开始备份任务,pstInParam(NET_IN_START_BACKUP_TASK_INFO)与pstOutParam(NET_OUT_START_BACKUP_TASK_INFO)内存由用户申请释放
    public boolean CLIENT_StartBackupTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

//停止备份任务public boolean CLIENT_StopBackupTask(LLong lBackupSession);
//订阅备份状态,pstInParam(NET_IN_ATTACH_BACKUP_STATE)与pstOutParam(NET_OUT_ATTACH_BACKUP_STATE)内存由用户申请释放
    public LLong CLIENT_AttachBackupTaskState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

//取消订阅备份状态,lAttachHandle是CLIENT_AttachBackupTaskState返回值
    public boolean CLIENT_DetachBackupTaskState(LLong lAttachHandle);

   // 获取安检门人数统计信息
  //CLIENT_NET_API BOOL CALL_METHOD CLIENT_GetPopulationStatistics(LLONG lLoginID, const NET_IN_GET_POPULATION_STATISTICS *pInParam, NET_OUT_GET_POPULATION_STATISTICS *pOutParam, int nWaitTime);
    public boolean CLIENT_GetPopulationStatistics(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

  // 订阅安检门人数变化信息,pstInParam与pstOutParam内存由用户申请释放
  //CLIENT_NET_API LLONG CALL_METHOD CLIENT_AttachPopulationStatistics(LLONG lLoginID, NET_IN_ATTACH_GATE_POPULATION_STATISTICS_INFO* pstInParam, NET_OUT_ATTACH_GATE_POPULATION_STATISTICS_INFO* pstOutParam , int nWaitTime);
    public LLong CLIENT_AttachPopulationStatistics(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

  // 取消订阅安检门人数变化信息 lPopulationStatisticsHandle 为 CLIENT_AttachPopulationStatistics 返回的句柄
  //CLIENT_NET_API BOOL CALL_METHOD CLIENT_DetachPopulationStatistics(LLONG lPopulationStatisticsHandle);
    public boolean CLIENT_DetachPopulationStatistics(LLong lPopulationStatisticsHandle);

    //创建车辆组
   //   CLIENT_NET_API BOOL CALL_METHOD CLIENT_CreateGroupForVehicleRegisterDB(LLONG lLoginID, const NET_IN_CREATE_GROUP_FOR_VEHICLE_REG_DB *pInParam, NET_OUT_CREATE_GROUP_FOR_VEHICLE_REG_DB *pOutParam, int nWaitTime);
    public  boolean CLIENT_CreateGroupForVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    //删除车辆组
   //  CLIENT_NET_API BOOL CALL_METHOD CLIENT_DeleteGroupFromVehicleRegisterDB(LLONG lLoginID, const NET_IN_DELETE_GROUP_FROM_VEHICLE_REG_DB *pInParam, NET_OUT_DELETE_GROUP_FROM_VEHICLE_REG_DB *pOutParam, int nWaitTime);
    public boolean CLIENT_DeleteGroupFromVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    //向车牌库添加车辆信息
   //  CLIENT_NET_API BOOL CALL_METHOD CLIENT_MultiAppendToVehicleRegisterDB(LLONG lLoginID, const NET_IN_MULTI_APPEND_TO_VEHICLE_REG_DB *pInParam, NET_OUT_MULTI_APPEND_TO_VEHICLE_REG_DB *pOutParam, int nWaitTime);
    public boolean CLIENT_MultiAppendToVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 开包工作台上报开包检查信息
    //  CLIENT_NET_API BOOL CALL_METHOD CLIENT_UploadUnpackingCheckInfo(LLONG lLoginID, const NET_IN_UPLOAD_UPPACKING_CHECK_INFO* pInParam, NET_OUT_UPLOAD_UPPACKING_CHECK_INFO* pOutParam, int nWaitTime);
    public  boolean CLIENT_UploadUnpackingCheckInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 网络应用组件,公司内部接口
     * 可用于获取前端设备的网络资源数据,如网络收发数据的速率等,pstuIn与pstuOut内存由用户申请释放，大小参照emType对应的结构体
     * @param lLoginID 登录句柄
     * @param emType 网络应用组件 操作类型 EM_RPC_NETAPP_TYPE
     * @param pstuIn 对应操作入参
     * @param pstuOut 对应操作出参
     * @param nWaitTime 超时时间
     * @return 添加是否成功
     */
    public boolean CLIENT_RPC_NetApp(LLong lLoginID,int emType,Pointer pstuIn,Pointer pstuOut,int nWaitTime);

    /**
     * 雷达操作
     * @param lLoginID 登录句柄
     * @param emType 网络应用组件 操作类型 EM_RADAR_OPERATE_TYPE
     * @param pInBuf 对应操作入参
     * @param pOutBuf 对应操作出参
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_RadarOperate(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 设置信号机备份模式,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_SET_BACKUP_MODE
     * @param pOutParam -> NET_OUT_SET_BACKUP_MODE
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_SetRtscBackupMode(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置信号机运行模式,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_SET_RUNNING_MODE
     * @param pOutParam -> NET_OUT_SET_RUNNING_MODE
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_SetRtscRunningMode(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取信号机运行模式,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_GET_RUNNING_MODE
     * @param pOutParam -> NET_OUT_GET_RUNNING_MODE
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_GetRtscRunningMode(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取信号机全局配置,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_GET_GLOBAL_PARAMETER
     * @param pOutParam -> NET_OUT_GET_GLOBAL_PARAMETER
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_GetRtscGlobalParam(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置信号机全局配置,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_SET_GLOBAL_PARAMETER
     * @param pOutParam -> NET_OUT_SET_GLOBAL_PARAMETER
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_SetRtscGlobalParam(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取信号机运行信息,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pInParam -> NET_IN_GET_RUNNING_INFO
     * @param pOutParam -> NET_OUT_GET_RUNNING_INFO
     * @param nWaitTime 超时时间
     * @return 是否成功
     */
    public boolean CLIENT_GetRtscRunningInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 手动选择球机要跟踪的轨迹目标
   // CLIENT_NET_API BOOL CALL_METHOD CLIENT_RadarManualTrack(LLONG lLoginID, const NET_IN_RADAR_MANUAL_TRACK* pstInParam, NET_OUT_RADAR_MANUAL_TRACK* pstOutParam, int nWaitTime);
    public boolean CLIENT_RadarManualTrack(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 西欧报警主机获取操作
     * @param lLoginID
     * @param emType NET_EM_GET_ALARMREGION_INFO 的枚举值
     * @param nWaitTime
     * @return boolean
     */
    public boolean CLIENT_GetAlarmRegionInfo(LLong lLoginID,int emType,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 标定球机和蓄水池污点位置
     * @param lLoginID
     * @param pstInParam -> NET_IN_SET_PTZ_WASH_POSISTION_INFO
     * @param pstOutParam -> NET_OUT_SET_PTZ_WASH_POSISTION_INFO
     * @param dwWaitTime
     * @return boolean
     */
    public boolean CLIENT_PtzSetWashPosistion(LLong lLoginID,NET_IN_SET_PTZ_WASH_POSISTION_INFO pstInParam,NET_OUT_SET_PTZ_WASH_POSISTION_INFO pstOutParam,int dwWaitTime);

    /**
     * 获取标定后的冲洗信息
     * @param lLoginID
     * @param pstInParam -> NET_IN_GET_PTZ_WASH_INFO
     * @param pstOutParam -> NET_OUT_GET_PTZ_WASH_INFO
     * @param dwWaitTime
     * @return boolean
     */
    public boolean CLIENT_PtzGetWashInfo(LLong lLoginID,NET_IN_GET_PTZ_WASH_INFO pstInParam,NET_OUT_GET_PTZ_WASH_INFO pstOutParam,int dwWaitTime);

    /**
     *按文件方式回放(lpRecordFile内存由用户申请释放)
     * @param lLoginID
     * @param lpRecordFile -> LPNET_RECORDFILE_INFO
     * @param hWnd -> Pointer
     * @param cbDownLoadPos -> fDownLoadPosCallBack
     * @param dwUserData
     * @return boolean
     */
    public LLong CLIENT_PlayBackByRecordFile(LLong lLoginID,NET_RECORDFILE_INFO lpRecordFile,Pointer hWnd,fDownLoadPosCallBack cbDownLoadPos,Pointer dwUserData);

    // 开始查找录像文件
    public LLong CLIENT_FindFile(LLong lLoginID,int nChannelId,int nRecordFileType,Pointer cardid,NET_TIME time_start,NET_TIME time_end,boolean bTime,int waittime);

    /**
     * 订阅人群分布图实时统计信息
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_ATTACH_CROWDDISTRI_MAP_INFO  输入参数, 由用户申请资源
     * @param pstOutParam -> NET_OUT_ATTACH_CROWDDISTRI_MAP_INFO  输出参数, 由用户申请资源
     * @param nWaitTime  等待超时时间
     * @return LLong  订阅句柄
     */
    public LLong CLIENT_AttachCrowdDistriMap(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 订阅人群分布图实时统计信息回调函数原型,
     * lAttachHandle为CLIENT_AttachCrowdDistriMap接口的返回值,
     * pstResult 解析结构体为 NET_CB_CROWD_DISTRI_STREAM_INFO
     */
    public interface fCrowdDistriStream extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstResult,Pointer dwUser);
    }

    /**
     * 取消订阅人群分布图实时统计信息
     * @param lAttachHandle  订阅句柄，为接口CLIENT_AttachCrowdDistriMap的返回值
     * @return boolean
     */
    public boolean CLIENT_DetachCrowdDistriMap(LLong lAttachHandle);

    /**
     * 获取人群分布图全局和区域实时人数统计值
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_GETSUMMARY_CROWDDISTRI_MAP_INFO  接口输入参数
     * @param pstOutParam -> NET_OUT_GETSUMMARY_CROWDDISTRI_MAP_INFO 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_GetSummaryCrowdDistriMap(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 下发人员信息录入结果
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_PERSON_INFO_INPUT_RESULT  接口输入参数
     * @param pstOutParam -> NET_OUT_PERSON_INFO_INPUT_RESULT 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_SetPersonInfoInputResult(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 根据查询条件返回录像备份任务的信息表,pInParam与pOutParam内存由用户申请释放
     * @param lLoginID  登陆句柄
     * @param pInParam -> NET_IN_FIND_REC_BAK_RST_TASK  接口输入参数
     * @param pOutParam -> NET_OUT_FIND_REC_BAK_RST_TASK 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_FindRecordBackupRestoreTaskInfos(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 功能描述:异步纯透传订阅接口
     * @param lLoginID  登录接口返回的句柄
     * @param pInParam -> NET_IN_ATTACH_TRANSMIT_INFO  异步纯透传接口输入参数
     * @param pOutParam -> NET_OUT_ATTACH_TRANSMIT_INFO 异步纯透传接口输出参数
     * @param nWaittime  接口超时时间
     * @return LLong 异步纯透传句柄
     */
    public LLong CLIENT_AttachTransmitInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * CLIENT_AttachTransmitInfo()回调函数原型，第一个参数lAttachHandle是CLIENT_AttachTransmitInfo返回值,
     */
    public interface AsyncTransmitInfoCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_CB_TRANSMIT_INFO pTransmitInfo,Pointer dwUser);
    }

    /**
     * 功能描述:异步纯透传取消订阅接口
     * @param lAttachHandle  异步纯透传句柄，即CLIENT_AttachTransmitInfo接口的返回值
     * @param pInParam -> NET_IN_DETACH_TRANSMIT_INFO  异步纯透传取消订阅接口输入参数
     * @param pOutParam -> NET_OUT_DETACH_TRANSMIT_INFO 异步纯透传取消订阅接口输出参数
     * @param nWaittime  接口超时时间
     * @return boolean TRUE :成功; FALSE :失败
     */
    public boolean CLIENT_DetachTransmitInfo(LLong lAttachHandle,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 雷达订阅RFID卡片信息
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_ATTACH_RADAR_RFIDCARD_INFO  接口输入参数
     * @param pstOutParam -> NET_OUT_ATTACH_RADAR_RFIDCARD_INFO 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return LLong
     */
    public LLong CLIENT_AttachRadarRFIDCardInfo(LLong lLoginID,NET_IN_ATTACH_RADAR_RFIDCARD_INFO pstInParam,NET_OUT_ATTACH_RADAR_RFIDCARD_INFO pstOutParam,int nWaitTime);

    /**
     * 雷达取消订阅RFID卡片信息
     * @param lAttachHandle  订阅句柄
     * @return boolean
     */
    public boolean CLIENT_DetachRadarRFIDCardInfo(LLong lAttachHandle);

    /**
     * 查询RFID的工作模式
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_RADAR_GET_RFID_MODE  接口输入参数
     * @param pstOutParam -> NET_OUT_RADAR_GET_RFID_MODE 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_GetRadarRFIDMode(LLong lLoginID,NET_IN_RADAR_GET_RFID_MODE pstInParam,NET_OUT_RADAR_GET_RFID_MODE pstOutParam,int nWaitTime);

    /**
     * 设置RFID的工作模式
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_RADAR_SET_RFID_MODE  接口输入参数
     * @param pstOutParam -> NET_OUT_RADAR_SET_RFID_MODE 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_SetRadarRFIDMode(LLong lLoginID,NET_IN_RADAR_SET_RFID_MODE pstInParam,NET_OUT_RADAR_SET_RFID_MODE pstOutParam,int nWaitTime);

    /**
     *	按条件删除车牌库中的车牌
     * @param lLoginID  登陆句柄
     * @param pstInParam -> NET_IN_DEL_BY_CONDITION_FROM_VEHICLE_REG_DB  接口输入参数
     * @param pstOutParam -> NET_OUT_DEL_BY_CONDITION_FROM_VEHICLE_REG_DB 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_DeleteByConditionFromVehicleRegisterDB(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 设置消费结果
     * @param lLoginID  登陆句柄
     * @param pInParam -> NET_IN_SET_CONSUME_RESULT  接口输入参数
     * @param pOutParam -> NET_OUT_SET_CONSUME_RESULT 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_SetConsumeResult(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 远程休眠模式
     * @param lLoginID  登录句柄
     * @param pInParam -> NET_IN_REMOTE_SLEEP  接口输入参数
     * @param pOutParam -> NET_OUT_REMOTE_SLEEP 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return boolean
     */
    public boolean CLIENT_RemoteSleep(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 自定义定时抓图订阅接口(目前智慧养殖猪温检测在用)
     * @param lLoginID  登录句柄
     * @param pInParam -> NET_IN_ATTACH_CUSTOM_SNAP_INFO  接口输入参数
     * @param pOutParam -> NET_OUT_ATTACH_CUSTOM_SNAP_INFO 接口输出参数
     * @param nWaitTime  等待超时时间
     * @return LLong
     */
    public LLong CLIENT_AttachCustomSnapInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 自定义定时抓图订阅回调函数原型,
     * @param lAttachHandle  为 CLIENT_AttachCustomSnapInfo 接口的返回值
     * @param pstResult      参考结构体 NET_CB_CUSTOM_SNAP_INFO
     */
    public interface fAttachCustomSnapInfo extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstResult,Pointer pBuf,int dwBufSize,Pointer dwUser);
    }

    /**
     * 取消自定义定时抓图订阅接口(目前智慧养殖猪温检测在用)
     * @param lAttachHandle 订阅句柄
     * @return boolean
     */
    public boolean CLIENT_DetachCustomSnapInfo(LLong lAttachHandle);

	/**
     * 物模型属性订阅回调函数原型, lAttachHandle为CLIENT_AttachThingsInfo接口的返回值
	 * @param lAttachHandle 订阅句柄
     * @param pstResult 物模型属性订阅回调信息, 参考{@link NET_CB_THINGS_INFO}
	 * @return void
	 */
    public interface fThingsCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstResult,Pointer dwUserData);
    }

    /**
     * 停止获取设备历史数据
     * @param lFindHandle 查找句柄
	 * @return TRUE表示成功  FALSE表示失败
	 */
    public boolean CLIENT_StopThingsHistoryData(LLong lFindHandle);

    /**
     * 获取设备历史数据结果接口
     * @param lFindHandle 查找句柄
     * @param pstInParam 接口输入参数，参考{@link NET_IN_THINGS_DOFIND_HISTORYDATA}
     * @param pstOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_DOFIND_HISTORYDATA}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_DoFindThingsHistoryData(LLong lFindHandle,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 开始获取设备历史数据接口
     * @param lLoginID 登录句柄
     * @param pstInParam 接口输入参数，参考{@link NET_IN_THINGS_START_HISTORYDATA}
     * @param pstOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_START_HISTORYDATA}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public LLong CLIENT_StartThingsHistoryData(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 智慧用电Things物模型服务调用接口
     * @param lLoginID 登录句柄
     * @param emType 物模型服务类型,参考{@link EM_THINGS_SERVICE_TYPE}
     * @param pInBuf 接口输入参数，参考emType类型，内存资源由用户申请和释放
     * @param pOutBuf 接口输出参数，参考emType类型，内存资源由用户申请和释放
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_ThingsServiceOperate(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 智慧用电Things获取设备连接状态信息接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_GET_NETSTATE}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_GET_NETSTATE}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_GetThingsNetState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 智慧用电Things获取设备列表接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_GET_DEVLIST}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_GET_DEVLIST}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_GetThingsDevList(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 智慧用电Things获取设备能力集接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_GET_CAPS}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_GET_CAPS}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_GetThingsCaps(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 智慧用电Things取消物模型属性订阅接口
     * @param lAttachHandle 订阅句柄
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_DetachThingsInfo(LLong lAttachHandle);

    /**
     * 智慧用电Things物模型属性订阅接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_ATTACH}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_ATTACH}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return 订阅句柄
	 */
    public LLong CLIENT_AttachThingsInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 智慧用电Things配置设置接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_SET}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_SET}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_SetThingsConfig(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 智慧用电Things配置获取接口
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_THINGS_GET}
     * @param pOutParam 接口输出参数, 参考{@link NET_OUT_THINGS_GET}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_GetThingsConfig(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取流量统计信息,pstInParam与pstOutParam内存由用户申请释放
     * @param lLoginID 登录句柄
     * @param pstInParam 接口输入参数，参考{@link NET_IN_TRAFFICSTARTFINDSTAT}
     * @param pstOutParam 接口输出参数, 参考{@link NET_OUT_TRAFFICSTARTFINDSTAT}
     * @return 查询句柄
     */
    public LLong CLIENT_StartFindFluxStat(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam);

    /**
     *@brief 继续查询流量统计,pstInParam与pstOutParam内存由用户申请释放
     * @param lFindHandle 查询句柄
     * @param pstInParam 接口输入参数，参考{@link NET_IN_TRAFFICDOFINDSTAT}
     * @param pstOutParam 接口输出参数, 参考{@link NET_OUT_TRAFFICDOFINDSTAT}
     * @return
     */
    public int CLIENT_DoFindFluxStat(LLong lFindHandle,Pointer pstInParam,Pointer pstOutParam);

    /**
     * 结束查询流量统计
     */
    public boolean CLIENT_StopFindFluxStat(LLong lFindHandle);

    /**
     * 获取智能订阅参数CustomInfo的格式化字符串能力
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_GET_CUSTOMINFO_CAPS}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_GET_CUSTOMINFO_CAPS}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetCustomInfoCaps(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取温度值
     * @param lLoginID 登录句柄
     * @param pInParam 接口输入参数，参考{@link NET_IN_GET_TEMPERATUREEX}
     * @param pstOutParam 接口输出参数, 参考{@link NET_OUT_GET_TEMPERATUREEX}
     * @return 查询句柄
     */
    public boolean CLIENT_FaceBoard_GetTemperatureEx(LLong lLoginID,Pointer pInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 考试计划
     * CLIENT_SetExamRecordingPlans 接口入参
     * CLIENT_SetExamRecordingPlans 接口出参
     * 添加考试录像计划
     *param[in]		lLoginID:		登录句柄
     *param[in]		pstuInParam:	接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SET_EXAM_RECORDING_PLANS}
     *param[out]		pstuOutParam:	接口输出参数, 内存资源由用户申请和释放  {@link NET_OUT_SET_EXAM_RECORDING_PLANS}
     *param[in]		nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetExamRecordingPlans(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取SMART扫描信息
     * @param lLoginID 登录句柄
     * @param pstuInParam 接口输入参数，参考{@link NET_IN_GET_DEV_STORAGE_SMART_VALUE}
     * @param pstuOutParam 接口输出参数, 参考{@link NET_OUT_GET_DEV_STORAGE_SMART_VALUE}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return
     */
    public boolean CLIENT_GetDevStorageSmartValue(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取设备各网卡的上传与发送速率
     * @param lLoginID 登录句柄
     * @param pstuInParam 接口输入参数，参考{@link NET_IN_GET_DEVICE_ETH_BAND_INFO}
     * @param pstuOutParam 接口输出参数, 参考{@link NET_OUT_GET_DEVICE_ETH_BAND_INFO}
     * @param nWaitTime 接口超时时间, 单位毫秒
     * @return
     */
    public boolean CLIENT_GetDeviceEthBandInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     *@ingroup       functions
     *@brief         销毁业务sdk模块
     *@param[in]     lSubBizHandle  业务sdk句柄，由CLIENT_CreateSubBusinessModule接口返回
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_DestroySubBusinessModule(LLong lSubBizHandle);

    /**
     *@ingroup       functions
     *@brief         创建业务sdk模块
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 NET_IN_CREAT_SUB_BUSINESS_MDL_INFO
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 NET_OUT_CREAT_SUB_BUSINESS_MDL_INFO
     *@retval LLONG
     *@return	业务sdk句柄，非0表示成功  0表示失败
     */
    public LLong CLIENT_CreateSubBusinessModule(Pointer pInParam,Pointer pOutParam);

    /**
     *@ingroup       functions
     *@brief         启动子连接监听服务
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 NET_IN_START_SUBLINK_LISTEN_SERVER
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 NET_OUT_START_SUBLINK_LISTEN_SERVER
     *@retval LLONG
     *@return	子链接监听服务句柄, 非0表示成功  0表示失败
     */
    public LLong CLIENT_StartSubLinkListenServer(Pointer pInParam,Pointer pOutParam);

    /**
     *@ingroup       functions
     *@brief         停止子连接监听服务
     *@param[in]     lListenServer  监听服务句柄，由CLIENT_StartSubLinkListenServer接口返回
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_StopSubLinkListenServer(LLong lListenServer);

    /**
     *@ingroup       functions
     *@brief         发送创建子连接所需的信息给设备, 由主业务模块调用
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 NET_IN_TRANSFER_SUBLINK_INFO
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 NET_OUT_TRANSFER_SUBLINK_INFO
     *@param[in]     nWaitTime 接口超时时间, 单位毫秒
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_TransferSubLinkInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     *@ingroup       functions
     *@brief         设置私有透传通道参数
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 NET_IN_SET_TRANSMITTUNNEL_PARAM
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 NET_OUT_SET_TRANSMITTUNNEL_PARAM
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_SetTransmitTunnelParam(Pointer pInParam,Pointer pOutParam);

    /**
     *@ingroup       functions
     *@brief         创建隧道
     *@param[in]     lSubBizHandle    业务sdk句柄，由CLIENT_CreateSubBusinessModule接口返回
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 NET_IN_CREATE_TRANSMIT_TUNNEL
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 NET_OUT_CREATE_TRANSMIT_TUNNEL
     *@retval LLONG
     *@return	透传隧道业务句柄，非0表示成功  0表示失败
     */
    public LLong CLIENT_CreateTransmitTunnel(LLong lSubBizHandle,Pointer pInParam,Pointer pOutParam);

    /**
     *@ingroup       functions
     *@brief         销毁隧道
     *@param[in]     lTransmitHandle  透传隧道业务句柄，由CLIENT_CreateTransmitTunnel接口返回
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_DestroyTransmitTunnel(LLong lTransmitHandle);

    /**
     *@ingroup       callback
     *@brief         子连接监听服务回调函数原型
     *@param[out]    lListenServer 子链接监听服务句柄, 由CLIENT_StartSubLinkListenServer接口返回
     *@param[out]    lSubBizHandle 分压业务sdk句柄, 由CLIENT_CreateSubBusinessModule接口返回
     *@param[out]    pstSubLinkCallBack 子链接监听服务回调信息 NET_SUBLINK_SERVER_CALLBACK
     *@retval void
     */
    public interface fSubLinkServiceCallBack extends Callback {
        public void invoke(LLong lListenServer,LLong lSubBizHandle,Pointer pstSubLinkCallBack);
    }

    /**
     *@ingroup       callback
     *@brief         隧道业务连接断开回调
     *@param[out]    lSubBizHandle 下载句柄, 由CLIENT_CreateSubBusinessModule接口返回
     *@param[out]    lOperateHandle 业务句柄
     *@param[out]    pstDisConnectInfo 断线回调数据 NET_TRANSMIT_DISCONNECT_CALLBACK
     *@retval void
     */
    public interface fTransmitDisConnectCallBack extends Callback {
        public void invoke(LLong lSubBizHandle,LLong lOperateHandle,Pointer pstDisConnectInfo);
    }

    /**
     *@ingroup       functions
     *@brief         获取/设置解码窗口输出OSD信息扩展接口(pInparam, pOutParam内存由用户申请释放)
     *@param[in]     lLoginHandle    登录句柄
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SPLIT_GET_OSD_EX}
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SPLIT_GET_OSD_EX}
     *@param[in]     nWaitTime 接口超时时间, 单位毫秒
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_GetSplitOSDEx(LLong lLoginHandle,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     *@ingroup       functions
     *@brief         获取/设置解码窗口输出OSD信息扩展接口(pInparam, pOutParam内存由用户申请释放)
     *@param[in]     lLoginHandle    登录句柄
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SPLIT_SET_OSD_EX}
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SPLIT_SET_OSD_EX}
     *@param[in]     nWaitTime 接口超时时间, 单位毫秒
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_SetSplitOSDEx(LLong lLoginHandle,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     *@ingroup       functions
     *@brief         销毁隧道
     *@param[in]     lLoginHandle    登录句柄
     *@param[in]     emType    入参类型枚举，{@link NET_SPLIT_OPERATE_TYPE}
     *@param[in]     pInParam  接口输入参数, 内存资源由用户申请和释放，类型参考枚举注释{@link NET_SPLIT_OPERATE_TYPE}
     *@param[out]    pOutParam 接口输出参数, 内存资源由用户申请和释放，类型参考枚举注释{@link NET_SPLIT_OPERATE_TYPE}
     *@param[in]     nWaitTime 接口超时时间, 单位毫秒
     *@retval BOOL
     *@return	TRUE表示成功  FALSE表示失败
     */
    public boolean CLIENT_OperateSplit(LLong lLoginHandle,int emType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 根据中心公钥获取锁具随机公钥
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放  {@link NET_IN_GET_DYNAMIC_LOCK_RANDOM_PUBLICKEY_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_GET_DYNAMIC_LOCK_RANDOM_PUBLICKEY_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetDynamicLockRandomPublicKey(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置通讯秘钥
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SET_DYNAMIC_LOCK_COMMUNICATIONKEY_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SET_DYNAMIC_LOCK_COMMUNICATIONKEY_INFO}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetDynamicLockCommunicationKey(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置开锁密钥
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link  NET_IN_SET_DYNAMIC_LOCK_OPENKEY_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SET_DYNAMIC_LOCK_OPENKEY_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetDynamicLockOpenKey(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置临时身份码
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放  {@link NET_IN_SET_DYNAMIC_LOCK_TEMP_USERID_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SET_DYNAMIC_LOCK_TEMP_USERID_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetDynamicLockTempUserID(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置开锁码
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SET_DYNAMIC_LOCK_OPEN_CODE_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SET_DYNAMIC_LOCK_OPEN_CODE_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetDynamicLockOpenCode(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 推送智能分析图片文件和规则信息，当CLIENT_AddAnalyseTask的数据源类型emDataSourceType为 EM_DATA_SOURCE_PUSH_PICFILE_BYRULE 时使用
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_PUSH_ANALYSE_PICTURE_FILE_BYRULE}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_PUSH_ANALYSE_PICTURE_FILE_BYRULE}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_PushAnalysePictureFileByRule(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 指定智能事件解析所用的结构体 用于解决java大结构体new对象慢导致的问题.该接口全局有效,建议在SDK初始化前调用
     * @param pInParam 接口输入参数, 内存资源由用户申请和释放，参考{@link NET_IN_SET_IVSEVENT_PARSE_INFO}
     * @return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetIVSEventParseType(NET_IN_SET_IVSEVENT_PARSE_INFO pInParam);

    /**
     * 平台下发轮询配置
     * param[in] lLoginID 登录句柄
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link NET_IN_SET_POLLING_CONFIG}
     * param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_SET_POLLING_CONFIG}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetPollingConfig(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 按通道获取设备智能业务的运行状态
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数, 内存资源由用户申请和释放   {@link NET_IN_GET_CHANNEL_STATE}
     *param[out] pstuOutParam 接口输出参数, 内存资源由用户申请和释放  {@link NET_OUT_GET_CHANNEL_STATE}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetChannelState(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取隐私遮挡列表
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_GET_PRIVACY_MASKING}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_GET_PRIVACY_MASKING}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetPrivacyMasking(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 设置隐私遮挡列表
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_SET_PRIVACY_MASKING}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_SET_PRIVACY_MASKING}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetPrivacyMasking(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 使能或关闭所有隐私遮挡块
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_SET_PRIVACY_MASKING_ENABLE}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_SET_PRIVACY_MASKING_ENABLE}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetPrivacyMaskingEnable(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取隐私遮挡总开关使能状态
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_GET_PRIVACY_MASKING_ENABLE}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_GET_PRIVACY_MASKING_ENABLE}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetPrivacyMaskingEnable(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 跳转到隐私遮档块
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_GOTO_PRIVACY_MASKING}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_GOTO_PRIVACY_MASKING}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GotoPrivacyMasking(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 删除隐私遮档块
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_DELETE_PRIVACY_MASKING}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_DELETE_PRIVACY_MASKING}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DeletePrivacyMasking(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 清除遮挡
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数 ,{@link NET_IN_CLEAR_PRIVACY_MASKING}
     * param[out]pstuOutParam 接口输出参数,,{@link NET_OUT_CLEAR_PRIVACY_MASKING}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_ClearPrivacyMasking(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 远程复位（消防）
     * param[in] lLoginID 登录句柄
     * param[in] emType 操作类型枚举，{@link EM_RADAR_OPERATE_TYPE}
     * param[in] pInParam 接口输入参数 ,参考emType
     * param[out]pOutParam 接口输出参数 ,参考emType
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_RadarOperate(LLong lLoginID,EM_RADAR_OPERATE_TYPE emType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 订阅mini雷达的报警点信息
     * param[in] lLoginID 登录句柄
     * param[in] pInParam 接口输入参数 ,{@link NET_IN_MINI_RADAR_ALARMPOINTINFO}
     * param[out]pOutParam 接口输出参数 ,{@link NET_OUT_MINI_RADAR_ALARMPOINTINFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_AttachMiniRadarAlarmPointInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 远程复位（消防）
     * param[in] lLoginID 登录句柄
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachMiniRadarAlarmPointInfo(LLong lAttachHandle);

    /**
     * 远程复位（消防）
     * param[in] lLoginID 登录句柄
     * param[in] pInParam 接口输入参数 ,{@link NET_IN_SMOKE_REMOTE_REBOOT_INFO}
     * param[out]pOutParam 接口输出参数 ,{@link NET_OUT_SMOKE_REMOTE_REBOOT_INFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SmokeRemoteReboot(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * mini雷达报警点信息
     * 上报的mini雷达报警点信息
     * mini雷达报警点信息回调函数指针
     */
    public interface fMiniRadarAlarmPointInfoCallBack extends Callback {
/**
         *
         * @param lLoginId 登录句柄
         * @param lAttachHandle 订阅句柄
         * @param pBuf {@link NET_MINI_RADAR_NOTIFY_ALARMPOINTINFO}
         * @param dwBufLen pBuf中结构体的长度
         * @param pReserved 保留数据
         * @param dwUser 用户自定义数据
         */
        public void invoke(LLong lLoginId,LLong lAttachHandle,Pointer pBuf,int dwBufLen,Pointer pReserved,Pointer dwUser);
    }

    /**
     * 获取业务库管理的舱位信息
     *param[in] lLoginID 登录句柄
     *param[in] pInParam 接口输入参数, 内存资源由用户申请和释放 {@link  NET_IN_GET_FINANCIAL_CABIN_INFO}
     *param[out] pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link  NET_OUT_GET_FINANCIAL_CABIN_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetFinancialCabinInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取金库门状态
     *param[in]		lLoginID:		登录句柄
     *param[in]		pstuInParam:	接口输入参数, 内存资源由用户申请和释放   {@link  NET_IN_GET_VAULTDOOR_STATE_INFO}
     *param[out]		pstuOutParam:	接口输出参数, 内存资源由用户申请和释放 {@link  NET_OUT_GET_VAULTDOOR_STATE_INFO}
     *param[in]		nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功FALSE表示失败
     */
    public boolean CLIENT_GetVaultDoorState(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取金融柜体设备状态
     *param[in]		lLoginID:		登录句柄
     *param[in]		pstuInParam:	接口输入参数, 内存资源由用户申请和释放  {@link  NET_IN_GET_CABINET_STATE_INFO}
     *param[out]		pstuOutParam:	接口输出参数, 内存资源由用户申请和释放  {@link  NET_OUT_GET_CABINET_STATE_INFO}
     *param[in]		nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功FALSE表示失败
     */
    public boolean CLIENT_GetFinancialCabinetState(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取当前电梯运行信息
     *param[in]		lLoginID:		登录句柄
     *param[in]		pstuInParam:	接口输入参数, 内存资源由用户申请和释放  {@link  NET_IN_GET_ELEVATOR_WORK_INFO}
     *param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放  {@link  NET_OUT_GET_ELEVATOR_WORK_INFO}
     *param[in]		nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功FALSE表示失败
     */
    public boolean CLIENT_GetElevatorWorkInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取水质检测能力
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_WATERDATA_STAT_SERVER_GETCAPS_INFO}
     *param[out]pstuOutParam 接口输出参数  {@link  NET_OUT_WATERDATA_STAT_SERVER_GETCAPS_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_GetWaterDataStatServerCaps(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 水质检测实时数据获取
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_WATERDATA_STAT_SERVER_GETDATA_INFO}
     *param[out]pstuOutParam 接口输出参数  {@link  NET_OUT_WATERDATA_STAT_SERVER_GETDATA_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_GetWaterDataStatServerWaterData(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 开始水质检测报表数据查询
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_START_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[out]pstuOutParam 接口输出参数 {@link  NET_OUT_START_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_StartFindWaterDataStatServer(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 水质检测报表数据查询
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_DO_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[out]pstuOutParam 接口输出参数  {@link  NET_OUT_DO_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_DoFindWaterDataStatServer(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 停止水质检测报表数据查询
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_STOP_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[out]pstuOutParam 接口输出参数 {@link  NET_OUT_STOP_FIND_WATERDATA_STAT_SERVER_INFO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_StopFindWaterDataStatServer(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取设备状态, DMSS专用接口, pInParam与pOutParam内存由用户申请释放
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_UNIFIEDINFOCOLLECT_GET_DEVSTATUS}
     *param[out]pstuOutParam 接口输出参数 {@link  NET_OUT_UNIFIEDINFOCOLLECT_GET_DEVSTATUS}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetUnifiedStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 主从联动组, 操作接口,pInParam与pOutParam内存由用户申请释放,大小参照emOperateType对应的结构体
     * param[in] lLoginID 登录句柄
     * param[in] emOperateType 接口输入参数 ,参考枚举 {@link  EM_MSGROUP_OPERATE_TYPE}
     * param[in] pInParam 接口输入参数 ,参考枚举对应的入参
     * param[out] pOutParam 接口输出参数 ,参考枚举对应的出参
     * param[in] nWaitTime 接口超时时间，默认3000, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_OperateMasterSlaveGroup(LLong lLoginID,int emOperateType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取token, pstuInParam与pstuOutParam内存由用户申请释放
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数   {@link  NET_IN_MAKE_TOKEN}
     *param[out]pstuOutParam 接口输出参数   {@link  NET_OUT_MAKE_TOKEN}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_MakeToken(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取共享文件夹工作目录信息
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数  {@link  NET_IN_NAS_DIRECTORY_GET_INFO}
     *param[out]  pstOutParam 接口输出参数  {@link  NET_OUT_NAS_DIRECTORY_GET_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetNASDirectoryInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 根据文件路径获取外部导入文件信息
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数  {@link  NET_IN_GET_FILE_INFO_BY_PATH_INFO}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_GET_FILE_INFO_BY_PATH_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetFileManagerExFileInfoByPath(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /***************************工装合规接口Start***************************************************/
    /**
     * 创建工装合规组
     *param[in]   lLoginID 登录句柄
     *param[in]   pInParam 接口输入参数 {@link  NET_IN_CREATE_WORKSUIT_COMPARE_GROUP}
     *param[out]  pOutParam 接口输出参数 {@link  NET_OUT_CREATE_WORKSUIT_COMPARE_GROUP}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_CreateWorkSuitCompareGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 删除工装合规组
     *param[in]   lLoginID 登录句柄
     *param[in]   pInParam 接口输入参数 {@link  NET_IN_DELETE_WORKSUIT_COMPARE_GROUP}
     *param[out]  pOutParam 接口输出参数 {@link  NET_OUT_DELETE_WORKSUIT_COMPARE_GROUP}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DeleteWorkSuitCompareGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 查找工装合规组信息
     *param[in]   lLoginID 登录句柄
     *param[in]   pInParam 接口输入参数 {@link  NET_IN_FIND_WORKSUIT_COMPARE_GROUP}
     *param[out]  pOutParam 接口输出参数 {@link  NET_OUT_FIND_WORKSUIT_COMPARE_GROUP}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_FindWorkSuitCompareGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 批量添加工装合规样本
     *param[in]   lLoginID 登录句柄
     *param[in]   pInParam 接口输入参数 {@link  NET_IN_MULTI_APPEND_TO_WORKSUIT_GROUP}
     *param[out]  pOutParam 接口输出参数 {@link  NET_OUT_MULTI_APPEND_TO_WORKSUIT_GROUP}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_MultiAppendToWorkSuitCompareGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 通过全景图唯一标识符删除工装合规样本
     *param[in]   lLoginID 登录句柄
     *param[in]   pInParam 接口输入参数 {@link  NET_IN_DELETE_WORKSUIT_BY_SOURCEUID}
     *param[out]  pOutParam 接口输出参数 {@link  NET_OUT_DELETE_WORKSUIT_BY_SOURCEUID}
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DeleteWorkSuitBySourceUID(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /***************************工装合规接口End***************************************************/
    /**
     * 区分报表查询, 单独实现一套全量查询数据接口
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数 {@link  NET_IN_START_FIND_DETAIL_CLUSTER}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_START_FIND_DETAIL_CLUSTER}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_StartFindDetailNumberStatCluster(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 分批查询全量记录
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数 {@link  NET_IN_DO_FIND_DETAIL_CLUSTER}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_DO_FIND_DETAIL_CLUSTER}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DoFindDetailNumberStatCluster(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 停止查询
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数{@link  NET_IN_STOP_FIND_DETAIL_CLUSTER_INFO}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_STOP_FIND_DETAIL_CLUSTER_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_StopFindDetailNumberStatCluster(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 平台主动获取设备聚档状态
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数{@link  NET_IN_GET_CLUSTER_STATE_INFO}
     *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_GET_CLUSTER_STATE_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetClusterState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 获取烟感数据
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数{@link  NET_IN_GET_SMOKE_DATA}
     *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_GET_SMOKE_DATA}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetSmokeData(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 修改车辆组
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数{@link  NET_IN_MODIFY_GROUP_FOR_VEHICLE_REG_DB}
     *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_MODIFY_GROUP_FOR_VEHICLE_REG_DB}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_ModifyGroupForVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

     /**
       * 查找车辆组信息
       *param[in]   lLoginID 登录句柄
       *param[in]   pstInParam 接口输入参数{@link  NET_IN_FIND_GROUP_FROM_VEHICLE_REG_DB}
       *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_FIND_GROUP_FROM_VEHICLE_REG_DB}
       *param[in]   nWaitTime 接口超时时间, 单位毫秒
       *return TRUE表示成功 FALSE表示失败
      */
    public boolean CLIENT_FindGroupFormVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

      /**
         * 修改车辆信息
         *param[in]   lLoginID 登录句柄
         *param[in]   pstInParam 接口输入参数{@link  NET_IN_MODIFY_VEHICLE_FOR_VEHICLE_REG_DB}
         *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_MODIFY_VEHICLE_FOR_VEHICLE_REG_DB}
         *param[in]   nWaitTime 接口超时时间, 单位毫秒
         *return TRUE表示成功 FALSE表示失败
         */
    public boolean CLIENT_ModifyVehicleForVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

        /**
         * 删除车辆信息
         *param[in]   lLoginID 登录句柄
         *param[in]   pstInParam 接口输入参数{@link  NET_IN_DELETE_VEHICLE_FROM_VEHICLE_REG_DB}
         *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_DELETE_VEHICLE_FROM_VEHICLE_REG_DB}
         *param[in]   nWaitTime 接口超时时间, 单位毫秒
         *return TRUE表示成功 FALSE表示失败
         */
    public boolean CLIENT_DeleteVehicleFromVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

        /**
         * 向指定注册库查询车辆
         *param[in]   lLoginID 登录句柄
         *param[in]   pstInParam 接口输入参数{@link  NET_IN_START_FIND_VEHICLE_REG_DB}
         *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_START_FIND_VEHICLE_REG_DB}
         *param[in]   nWaitTime 接口超时时间, 单位毫秒
         *return TRUE表示成功 FALSE表示失败
         */
    public boolean CLIENT_StartFindVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

        /**
         * 获取车辆查询结果信息
         *param[in]   lLoginID 登录句柄
         *param[in]   pstInParam 接口输入参数{@link  NET_IN_DO_FIND_VEHICLE_REG_DB}
         *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_DO_FIND_VEHICLE_REG_DB}
         *param[in]   nWaitTime 接口超时时间, 单位毫秒
         *return TRUE表示成功 FALSE表示失败
         */
    public boolean CLIENT_DoFindVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

       /**
        * 结束车辆查询
        *param[in]   lLoginID 登录句柄
        *param[in]   pstInParam 接口输入参数{@link  NET_IN_STOP_FIND_VEHICLE_REG_DB}
        *param[out]  pstOutParam 接口输出参数{@link  NET_OUT_STOP_FIND_VEHICLE_REG_DB}
        *param[in]   nWaitTime 接口超时时间, 单位毫秒
        *return TRUE表示成功 FALSE表示失败
        */
    public boolean CLIENT_StopFindVehicleRegisterDB(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     *订阅陀螺仪数据接口回调函数原型, lAttachGyroHandle为CLIENT_AttachGyro接口的返回值
     *param[out] lAttachGyroHandle 订阅句柄
     *param[out] pstuGyroDataInfo 订阅的陀螺仪数据回调信息   {@link NET_NOTIFY_GYRO_DATA_INFO}
     *param[out] dwUser 用户信息
     *return void
     */
    public interface fNotifyGyroData extends Callback {
        public void invoke(LLong lAttachGyroHandle,Pointer pstuGyroDataInfo,Pointer dwUser);
    }

    /**
     * 订阅陀螺仪数据接口
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_ATTACH_GYRO}
     *param[out] pstuOutParam 接口输出参数  {@link  NET_OUT_ATTACH_GYRO}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return 返回订阅句柄
     */
    public LLong CLIENT_AttachGyro(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 取消陀螺仪数据订阅接口
     *param[in] lAttachHandle 订阅句柄
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachGyro(LLong lAttachHandle);

   /**
    * 立体行为-视频统计信息回调函数原形，
    * param[out] lAttachHandle 是 CLIENT_AttachVideoStatistics返回值
    * param[out] emType 是枚举{@link NET_EM_VS_TYPE}的值
    * param[out] pBuf  是对应结构体数据指针，参考枚举值描述
    */
    public interface fVideoStatisticsInfoCallBack extends Callback {
        public void invoke(LLong lAttachHandle,int emType,Pointer pBuf,int dwBufLen,Pointer dwUser);
    }

    /**
     * 订阅客流统计服务实时数据
     *param[in] lLoginID 登录句柄
     *param[in] pstuInParam 接口输入参数  {@link  NET_IN_ATTACH_VIDEO_STATISTICS}
     *param[out] pstuOutParam 接口输出参数  {@link  NET_OUT_ATTACH_VIDEO_STATISTICS}
     *param[in] nWaitTime 接口超时时间, 单位毫秒
     *return 返回订阅句柄
     */
    public LLong CLIENT_AttachVideoStatistics(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 取消订阅客流统计服务实时数据
     *param[in] lAttachHandle 订阅句柄
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachVideoStatistics(LLong lAttachHandle);

    /**
     * 智能事件开始查询
     *param[in]   lLoginID 登录句柄
     *param[in]   pstInParam 接口输入参数 {@link  NET_IN_IVSEVENT_FIND_INFO}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_IVSEVENT_FIND_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return 查询句柄 非0表示成功,0表示失败
     */
    public LLong CLIENT_IVSEventFind(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 智能事件信息查询
     *param[in]   lFindHandle 查询句柄
     *param[in]   pstInParam 接口输入参数 {@link  NET_IN_IVSEVENT_NEXTFIND_INFO}
     *param[out]  pstOutParam 接口输出参数 {@link  NET_OUT_IVSEVENT_NEXTFIND_INFO}
     *param[in]   nWaitTime 接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_IVSEventNextFind(LLong lFindHandle,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 智能事件结束查询
     *param[in]   lFindHandle 查询句柄
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_IVSEventFindClose(LLong lFindHandle);

    /**
     * 按时间回放进度回调函数原形的扩展
     * 参数recordfileinfoEx 指针对应结构体NET_RECORDFILE_INFOEX
     */
    public interface fTimeDownLoadPosCallBackEx extends Callback {
        public void invoke(LLong lPlayHandle,int dwTotalSize,int dwDownLoadSize,int index,Pointer recordfileinfoEx,Pointer dwUser);
    }

    /**
     * VK信息回调(pBuffer内存由SDK内部申请释放),dwError值可以dhnetsdk.h中找到相应的解释,比如NET_NOERROR,NET_ERROR_VK_INFO_DECRYPT_FAILED等
     * 参数pBuffer指针对应结构体NET_VKINFO
     */
    public interface fVKInfoCallBack extends Callback {
        public void invoke(LLong lRealHandle,Pointer pBuffer,int dwError,Pointer dwUser,Pointer pReserved);
    }

    /**
     * 分压业务连接断线回调
     *param[out]    lSubBizHandle 分压业务sdk句柄, 由CLIENT_CreateSubBusinessModule接口返回
     *param[out]    lOperateHandle 业务句柄
     *param[out]    pstDisConnectInfo 断线回调数据  对应结构体NET_SUBBIZ_DISCONNECT_CALLBACK
     *return void
     */
    public interface fSubBizDisConnectCallBack extends Callback {
        public void invoke(LLong lSubBizHandle,LLong lOperateHandle,Pointer pstDisConnectInfo);
    }

    /**
     * 订阅大图检测小图进度,配合CLIENT_FaceRecognitionDetectMultiFace使用, pstInParam与pstOutParam内存由用户申请释放
     * param[in] pstuInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_MULTIFACE_DETECT_STATE}
     * param[out]pstuOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_MULTIFACE_DETECT_STATE}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public LLong CLIENT_AttachDetectMultiFaceState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 订阅大图检测小图进度回调函数原型,
     * pstStates指针对应结构体NET_CB_MULTIFACE_DETECT_STATE
     */
    public interface fMultiFaceDetectState extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstStates,Pointer dwUser);
    }

    /**
     * 订阅大图检测小图进度回调函数原型
     * pstStates指针对应结构体NET_CB_MULTIFACE_DETECT_STATE_EX
     */
    public interface fMultiFaceDetectStateEx extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstStates,Pointer dwUser);
    }

    /**
     * 开始目标检测/注册库的多通道查询
     * param[in] lLoginID 登录句柄
     * param[in] pstuInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_STARTMULTIFIND_FACERECONGNITION_EX}
     * param[out]pstuOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_STARTMULTIFIND_FACERECONGNITION_EX}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_StartMultiFindFaceRecognitionEx(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取人脸查询结果信息
     * param[in] pstuInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_DOFIND_FACERECONGNITION_EX}
     * param[out]pstuOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_DOFIND_FACERECONGNITION_EX}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DoFindFaceRecognitionEx(Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 向服务器提交多张大图，从中检测人脸图片, pstInParam与pstOutParam内存由用户申请释放
     * param[in] pstuInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_FACE_RECOGNITION_DETECT_MULTI_FACE_INFO}
     * param[out]pstuOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_FACE_RECOGNITION_DETECT_MULTI_FACE_INFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_FaceRecognitionDetectMultiFace(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 取消订阅大图检测小图进度, lAttachHandle为CLIENT_AttachDetectMultiFaceState 返回的句柄
     */
    public boolean CLIENT_DetachDetectMultiFaceState(LLong lAttachHandle);

    /**
     * 获取安检机安全等级信息,pstInParam与pstOutParam内存由用户申请释放
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_GET_XRAY_MULTILEVEL_DETECT_INFO}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_GET_XRAY_MULTILEVEL_DETECT_INFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     */
    public LLong CLIENT_GetXRayMultiLevelDetectCFG(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置安检机安全等级信息,pstInParam与pstOutParam内存由用户申请释放
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_SET_XRAY_MULTILEVEL_DETECT_INFO}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_SET_XRAY_MULTILEVEL_DETECT_INFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     */
    public LLong CLIENT_SetXRayMultiLevelDetectCFG(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 动环遥测数据订阅
     * param[in] lLoginID 登录句柄
     * param[in] pstInParam 接口输入参数,{@link NET_IN_ATTACH_SCADA_DATA_INFO}
     * param[out] pstOutParam 接口输出参数,{@link NET_OUT_ATTACH_SCADA_DATA_INFO}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     * return 返回订阅句柄
     */
    public LLong CLIENT_AttachSCADAData(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 动环遥测数据退订
     * param[in] lSCADADataHandle 订阅句柄
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachSCADAData(LLong lSCADADataHandle);

    /**
     * 动环遥测数据订阅回调函数原型, lSCADADataHandle 为 CLIENT_AttachSCADAData 接口的返回值
     * param[out] lSCADADataHandle 订阅句柄
     * param[out] pstuSCADADataNotifyInfo 订阅的遥测数据,{@link NET_NOTIFY_SCADA_DATA_INFO}
     * param[out] dwUser 用户信息
     * return void
     */
    public interface fNotifySCADAData extends Callback {
        public void invoke(LLong lSCADADataHandle,Pointer pstuSCADADataNotifyInfo,Pointer dwUser);
    }

    /**
     * 订阅统计通道数据,pInParam与pOutParam内存由用户申请释放
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_ATTACH_VIDEOSTAT_STREAM}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放,{@link NET_OUT_ATTACH_VIDEOSTAT_STREAM}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     */
    public LLong CLIENT_AttachVideoStatStream(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

	/**
	 * 统计通道数据回调函数,参数pBuf 指针对应结构体NET_CB_VIDEOSTAT_STREAM
	 */
    public interface fVideoStatStreamCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

    /**
     * 取消订阅统计通道数据, lAttachHandle为CLIENT_AttachVideoStatStream 返回的句柄
     */
    public boolean CLIENT_DetachVideoStatStream(LLong lAttachHandle);

    /**
     * 获取电视墙预案,pInParam与pOutParam内存由用户申请释放
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link org.springblade.modules.iot.dahua.lib.structure.optimized.NET_IN_WM_GET_COLLECTIONS_V1}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link org.springblade.modules.iot.dahua.lib.structure.optimized.NET_OUT_WM_GET_COLLECTIONS_V1}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
     */
    public boolean CLIENT_GetMonitorWallCollectionsV1(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

	/**
	 * 门禁设备开始捕获新卡
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_ACCESSCONTROL_CAPTURE_NEWCARD}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_ACCESSCONTROL_CAPTURE_NEWCARD}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
	 */
    public boolean CLIENT_AccessControlCaptureNewCard(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

	/**
	 * 门禁人证设备获取人脸
     * param[in] pInParam 接口输入参数, 内存资源由用户申请和释放,{@link NET_IN_ACCESSCONTROL_CAPTURE_CMD}
     * param[out]pOutParam 接口输出参数, 内存资源由用户申请和释放 {@link NET_OUT_ACCESSCONTROL_CAPTURE_CMD}
     * param[in] nWaitTime 接口超时时间, 单位毫秒
	 */
    public boolean CLIENT_AccessControlCaptureCmd(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 订阅智能分析结果
     * param[in] 	lLoginID 登录句柄
     * param[in] 	pstInParam 接口输入参数，{@link NET_IN_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC}
     * param[out]	pstOutParam 接口输出参数，{@link NET_OUT_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC}
     * param[in] 	nWaitTime 接口超时时间, 单位毫秒
     * return 返回订阅句柄
     */
    public LLong CLIENT_AttachVideoAnalyseAnalyseProc(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 取消订阅智能分析结果
     * param[in] 	lAttachHandle 订阅句柄
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachVideoAnalyseAnalyseProc(LLong lAttachHandle);

    /**
     * 智能分析结果的回调函数
     * param[out] lAttachHandle 订阅句柄
     * param[out] pstuVideoAnalyseTrackProc 智能分析结果的信息,{@link NET_VIDEO_ANALYSE_ANALYSE_PROC}
     * param[out] dwUser 用户信息
     * return void
     */
    public interface fVideoAnalyseAnalyseProc extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuVideoAnalyseTrackProc,Pointer dwUser);
    }

    /**
     * 开始升级设备程序--扩展支持G以上文件升级
     */
    public LLong CLIENT_StartUpgradeEx2(LLong lLoginID,int emType,Pointer pchFileName,fUpgradeCallBackEx cbUpgrade,Pointer dwUser);

    /**
    * 报警主机设置操作
    * param[in] lLoginID 登录句柄
    * param[in] emType 设置的操作类型,{@link NET_EM_SET_ALARMREGION_INFO}
    * param[in] pstuInParam 枚举对应的入参
    * param[out] pstuOutParam 枚举对应的出参
    * param[in] nWaitTime 接口超时时间, 单位毫秒
    * return void
    */
    public boolean CLIENT_SetAlarmRegionInfo(LLong lLoginID,int emType,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 获取设备序列号
    * param[in] lLoginID 登录句柄
    * param[in] pstInParam 接口输入参数,{@link NET_IN_GET_DEVICESERIALNO_INFO}
    * param[out] pstOutParam 接口输出参数,{@link NET_OUT_GET_DEVICESERIALNO_INFO}
    * param[in] nWaitTime 接口超时时间, 单位毫秒
    * return void
    */
    public boolean CLIENT_GetDeviceSerialNo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
    * 获取设备类型
    * param[in] lLoginID 登录句柄
    * param[in] pstInParam 接口输入参数,{@link NET_IN_GET_DEVICETYPE_INFO}
    * param[out] pstOutParam 接口输出参数,{@link NET_OUT_GET_DEVICETYPE_INFO}
    * param[in] nWaitTime 接口超时时间, 单位毫秒
    * return void
    */
    public boolean CLIENT_GetDeviceType(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * RPC测试
     *param[in]	lLoginID:		登录句柄
     *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_TRANSMIT_CMD}
     *param[out] pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_TRANSMIT_CMD}
     *param[in]	nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_TransmitCmd(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 手动测试
     *param[in]	lLoginID:		登录句柄
     *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_MANUAL_TEST}
     *param[out]pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_MANUAL_TEST}
     *param[in]	nWaitTime:		接口超时时间, 单位毫秒
     *return TRUE表示成功 FALSE表示失败
    */
    public boolean CLIENT_ManualTest(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 添加报警用户
    *param[in]	lLoginID:		登录句柄
    *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_ADD_ALARM_USER}
    *param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_ADD_ALARM_USER}
    *param[in]	nWaitTime:		接口超时时间, 单位毫秒
    *return TRUE表示成功 FALSE表示失败
    */
    public boolean CLIENT_AddAlarmUser(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 修改报警用户
    *param[in]	lLoginID:		登录句柄
    *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_MODIFY_ALARM_USER}
    *param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_MODIFY_ALARM_USER}
    *param[in]	nWaitTime:		接口超时时间, 单位毫秒
    *return TRUE表示成功 FALSE表示失败
    */
    public boolean CLIENT_ModifyAlarmUser(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 修改报警用户密码
    *param[in]	lLoginID:		登录句柄
    *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_MODIFY_ALARM_USER_PASSWORD}
    *param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_MODIFY_ALARM_USER_PASSWORD}
    *param[in]	nWaitTime:		接口超时时间, 单位毫秒
    *return TRUE表示成功 FALSE表示失败
    */
    public boolean CLIENT_ModifyAlarmUserPassword(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 删除报警用户
    *param[in]	lLoginID:		登录句柄
    *param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_DELETE_ALARM_USER}
    *param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_DELETE_ALARM_USER}
    *param[in]	nWaitTime:		接口超时时间, 单位毫秒
    *return TRUE表示成功 FALSE表示失败
    */
    public boolean CLIENT_DeleteAlarmUser(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 订阅无线对码信息接口,pstInParam与pstOutParam内存由用户申请释放
    *param[in]	lLoginID:		登录句柄
    *param[in]	pstInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_ATTACH_LOWRATEWPAN}
    *param[out]	pstOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_ATTACH_LOWRATEWPAN}
    *param[in]	nWaitTime:		接口超时时间, 单位毫秒
    *return 返回订阅句柄
    */
    public LLong CLIENT_AttachLowRateWPAN(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
    * 订阅无线对码信息回调函数原形,lAttachHandle是CLIENT_AttachLowRateWPAN返回值
    *param[out]	lLoginID        登录句柄
    *param[out]	lAttachHandle	订阅句柄
    *param[out]	lAttachHandle	对码信息, 参考{@link NET_CODEID_INFO}
    *param[out]	emError	                对码错误类型, 参考{@link NET_CODEID_ERROR_TYPE}
    *param[out]	dwUser	                用户数据
    */
    public interface fAttachLowRateWPANCB extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer stuBuf,int emError,Pointer dwUser);
    }

    /**
     * 取消订阅无线对码信息接口,lAttachHandle是CLIENT_AttachLowRateWPAN返回值
     * param[in] 	lAttachHandle 订阅句柄
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachLowRateWPAN(LLong lAttachHandle);

    /**
     * 获取画面中心位置目标的距离,pInBuf与pOutBuf内存由用户申请释放
     * param[out] pInBuf 接口输入参数，{@link NET_IN_GET_LASER_DISTANCE}
     * param[out] pOutBuf 接口输出参数，{@link NET_OUT_GET_LASER_DISTANCE}
     * param[out] nWaitTime 接口超时时间, 单位毫秒
     * return void
     */
    public boolean CLIENT_GetLaserDistance(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 获取已添加的设备状态
     * param[in]		lLoginID:			登录句柄
     * param[in]		pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_GET_DEVICE_INFO_EX}
     * param[out]	    pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_GET_DEVICE_INFO_EX}
     * param[in]		nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetDeviceInfoEx(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取目标检测令牌
     * param[in]		lLoginID:	登录句柄
     * param[in]		pInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_FACERSERVER_GETDETEVTTOKEN}
     * param[out]	      pOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_FACERSERVER_GETDETEVTTOKEN}
     * param[in]		nWaitTime:	接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_FaceRServerGetDetectToken(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 查询设备日志条数(pInParam, pOutParam内存由用户申请释放)
     *      * param[in]		lLoginID:	登录句柄
     *      * param[in]		pInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link NET_IN_GETCOUNT_LOG_PARAM}
     *      * param[out]	      pOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link NET_OUT_GETCOUNT_LOG_PARAM}
     *      * param[in]		nWaitTime:	接口超时时间, 单位毫秒
     */
    public boolean CLIENT_QueryDevLogCount(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * 设置状态信息接口
     * param[in]	lLoginID:		登录句柄
     * param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_SET_STATEMANAGER_INFO}
     * param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_SET_STATEMANAGER_INFO}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetStateManager(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取状态信息接口
     * param[in]	lLoginID:		登录句柄
     * param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_GET_STATEMANAGER_INFO}
     * param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_GET_STATEMANAGER_INFO}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetStateManager(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 定位录像回放起始点
     */
    public boolean CLIENT_SeekPlayBack(LLong lPlayHandle,int offsettime,int offsetbyte);

    /**
     * 按时间定位回放起始点
     */
    public boolean CLIENT_SeekPlayBackByTime(LLong lPlayHandle,NET_TIME lpSeekTime);

    /**
     * 多通道预览回放(pParam内存由用户申请释放)
     */
    public LLong CLIENT_MultiPlayBack(LLong lLoginID,Pointer pParam);

    /**
     * 操作设备标定信息
     * param[in]	lLoginID:		登录句柄
     * param[in]	emType:	        入参枚举，决定后续指针参数类型,参考{@link EM_CALIBRATEINFO_OPERATE_TYPE}
     * param[in]	pstuInParam:	接口输入参数, 内存资源由用户申请和释放,参考{@link EM_CALIBRATEINFO_OPERATE_TYPE}
     * param[out]	pstuOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{@link EM_CALIBRATEINFO_OPERATE_TYPE}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_OperateCalibrateInfo(LLong lLoginID,int emType,Pointer pStuInParam,Pointer pStuOutParam,int nWaitTime);

    /**
     * 订阅云台可视域回调函数原型, pBuf -> {@link DH_OUT_PTZ_VIEW_RANGE_STATUS}
     */
    public interface fViewRangeStateCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

    /**
     * 订阅云台可视域,pstuInViewRange与pstuOutViewRange内存由用户申请释放
     * {@link NET_IN_VIEW_RANGE_STATE},{@link NET_OUT_VIEW_RANGE_STATE}
     */
    public LLong CLIENT_AttachViewRangeState(LLong lLoginID,Pointer pstuInViewRange,Pointer pstuOutViewRange,int nWaitTime);

    /**
     * 停止订阅云台可视域,lAttachHandle是CLIENT_AttachViewRangeState返回值
     */
    public boolean CLIENT_DetachViewRangeState(LLong lAttachHandle);

    /**
     * 开始查询日志(目前只支持门禁BSC系列,支持报警主机日志分类查询),pInParam与pOutParam内存由用户申请释放
     * param[in]	lLoginID:		登录句柄
     * param[in]	pInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_START_QUERYLOG}
     * param[out]	pOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_START_QUERYLOG}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return  返回查询句柄
     */
    public LLong CLIENT_StartQueryLog(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取日志(目前只支持门禁BSC系列),pInParam与pOutParam内存由用户申请释放
     * param[in]	lLogID:		查询句柄，CLIENT_StartQueryLog的返回值
     * param[in]	pInParam:	接口输入参数, 内存资源由用户申请和释放,参考{ @link NET_IN_QUERYNEXTLOG}
     * param[out]	pOutParam:	接口输出参数, 内存资源由用户申请和释放,参考{ @link NET_OUT_QUERYNEXTLOG}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_QueryNextLog(LLong lLogID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 结束查询日志(目前只支持门禁BSC系列)
     * param[in]	lLogID:		查询句柄，CLIENT_StartQueryLog的返回值
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_StopQueryLog(LLong lLogID);

    /**
     * 获取窗口位置(pInparam, pOutParam内存由用户申请释放)
     * param[in]	lLoginID:		登录句柄
     * param[in]	pInParam:	        接口输入参数, 内存资源由用户申请和释放,参考{ @link DH_IN_SPLIT_GET_RECT}
     * param[out]	pOutParam:	        接口输出参数, 内存资源由用户申请和释放,参考{ @link DH_OUT_SPLIT_GET_RECT}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetSplitWindowRect(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置窗口位置(pInparam, pOutParam内存由用户申请释放)
     * param[in]	lLoginID:		登录句柄
     * param[in]	pInParam:	        接口输入参数, 内存资源由用户申请和释放,参考{ @link DH_IN_SPLIT_SET_RECT}
     * param[out]	pOutParam:	        接口输出参数, 内存资源由用户申请和释放,参考{ @link DH_OUT_SPLIT_SET_RECT}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetSplitWindowRect(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 设置窗口次序(pInparam, pOutParam内存由用户申请释放)
     * param[in]	lLoginID:		登录句柄
     * param[in]	pInParam:	        接口输入参数, 内存资源由用户申请和释放,参考{@link DH_IN_SPLIT_SET_TOP_WINDOW}
     * param[out]	pOutParam:	        接口输出参数, 内存资源由用户申请和释放,参考{@link DH_OUT_SPLIT_SET_TOP_WINDOW}
     * param[in]	nWaitTime:		接口超时时间, 单位毫秒
     * return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetSplitTopWindow(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取网卡信息(lpInParam, lpOutParam内存由用户申请释放,大小参照emType对应的结构体)
     */
    public boolean CLIENT_QueryNetStat(LLong lLoginID,int emType,Pointer lpInParam,int nInParamLen,Pointer lpOutParam,int nOutParamLen,Pointer pError,int nWaitTime);

    /**
     * 订阅统计区域内的车辆数据或者排队长度信息,pstInParam与pstOutParam内存由用户申请释放
     */
    public LLong CLIENT_AttachVehiclesDistributionData(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam);

    /**
     * 取消订阅统计区域内的车辆数据或者排队长度信息,pstInParam与pstOutParam内存由用户申请释放
     */
    public boolean CLIENT_DetachVehiclesDistributionData(LLong lAttachHandle);

    /**
     * 接口 CLIENT_AttachVehiclesDistributionData 回调函数,pBuf是json和图片数据,nBufLen是pBuf相应长度,用于转发服务
     */
    public interface fNotifyVehiclesDistributionData extends Callback {
        public int invoke(LLong lVehiclesHandle,Pointer pDiagnosisInfo,Pointer dwUser);
    }

    /**
     * @param		pInParam:	参考{@link NET_IN_RADIOMETRY_CURRENTHOTCOLDSPOT_INFO}
     * @param		pOutParam:	参考{@link NET_OUT_RADIOMETRY_CURRENTHOTCOLDSPOT_INFO}
     * @description  获取热成像当前冷（最低的温度）、热（最高的温度）点信息
     */
    public boolean CLIENT_RadiometryGetCurrentHotColdSpotInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @param		pstuInParam:	参考{@link NET_IN_SET_ZONE_ARMODE_INFO}
     * @param		pstuOutParam:	参考{@link NET_OUT_SET_ZONE_ARMODE_INFO}
     * @description  设置单防区布撤防状态
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetZoneArmMode(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param		pstuInParam:	参考{@link NET_IN_GET_ZONE_ARMODE_INFO}
     * @param		pstuOutParam:	参考{@link NET_OUT_GET_ZONE_ARMODE_INFO}
     * @description  获取单防区布撤防状态
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetZoneArmMode(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @description 智能分析结果的回调函数
     * @description param[out] lAttachHandle 订阅句柄
     * @description param[out] pstuVideoAnalyseTrackProc 智能分析结果的信息
     * @description param[out] dwUser 用户信息
     * @description return void
     */
    public interface fVehicleInOutAnalyseProc extends Callback {
/**
         * @param lAttachHandle
         * @param pstuVehicleInOutAnalyseProc: 参考{@link NET_VEHICLE_INOUT_ANALYSE_PROC}
         * @param dwUser
         */
        public void invoke(LLong lAttachHandle,Pointer pstuVehicleInOutAnalyseProc,Pointer dwUser);
    }

    /**
     * @param pstuInParam:  参考{@link NET_IN_ATTACH_TRAFFIC_FLOW_STAT_REAL_FLOW}
     * @param pstuOutParam: 参考{@link NET_OUT_ATTACH_TRAFFIC_FLOW_STAT_REAL_FLOW}
     * @description 订阅交通流量统计
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstInParam 接口输入参数
     * @description param[out] pstOutParam 接口输出参数
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return 返回订阅句柄
     */
    public LLong CLIENT_AttachTrafficFlowStatRealFlow(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @description 取消订阅交通流量统计
     * @description param[in] lAttachHandle 订阅句柄
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachTrafficFlowStatRealFlow(LLong lAttachHandle);

    /**
     * @param pstuInParam:  参考{@link NET_IN_SET_BIND_MODE_INFO}
     * @param pstuOutParam: 参考{@link NET_OUT_SET_BIND_MODE_INFO}
     * @description 设置绑定模式
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetBindMode(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param pstuInParam:  参考{@link NET_IN_GET_BIND_MODE_INFO}
     * @param pstuOutParam: 参考{@link NET_OUT_GET_BIND_MODE_INFO}
     * @description 获取绑定模式
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetBindMode(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param		pstInParam:	参考{@link NET_IN_ATTACH_VIDEO_ANALYSE_TRACK_PROC}
     * @param		pstOutParam:	参考{@link NET_OUT_ATTACH_VIDEO_ANALYSE_TRACK_PROC}
     * @description  订阅外部轨迹
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstInParam 接口输入参数
     * @description param[out]pstOutParam 接口输出参数
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return 订阅句柄
     */
    public LLong CLIENT_AttachVideoAnalyseTrackProc(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @param		pstuVideoAnalyseTrackProc:	参考{@link NET_VIDEO_ANALYSE_TRACK_PROC}
     * @description  外部轨迹的回调函数
     */
    public interface fVideoAnalyseTrackProc extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuVideoAnalyseTrackProc,Pointer dwUser);
    }

    /**
     * @description  取消订阅外部轨迹
     * @description param[in] lAttachHandle 订阅句柄
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachVideoAnalyseTrackProc(LLong lAttachHandle);

    /**
     * @param        pstuInParam:	参考{@link NET_IN_GET_GPS_STATUS_INFO}
     * @param        pstuOutParam:	参考{@link NET_OUT_GET_GPS_STATUS_INFO}
     * @description 获取GPS定位信息
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetGpsStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param pstuInParam:  参考{@link NET_IN_PTZ_SET_ZOOM_VALUE}
     * @param pstuOutParam: 参考{@link NET_OUT_PTZ_SET_ZOOM_VALUE}
     * @description 设置云台变倍
     * @description param[in] lLoginID: 登录句柄
     * @description param[in] pstuInParam: 接口输入参数, 内存资源由用户申请和释放
     * @description param[out] pstuOutParam: 接口输出参数, 内存资源由用户申请和释放
     * @description param[in] nWaitTime: 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_PTZSetZoomValue(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param pstuInParam:  参考{@link NET_IN_SET_VTO_MANAGER_RELATION}
     * @param pstuOutParam: 参考{@link NET_OUT_SET_VTO_MANAGER_RELATION}
     * @description 设置组织节点表
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_SetVTOManagerRelation(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param pstuInParam:  参考{@link NET_IN_GET_VTO_MANAGER_RELATION}
     * @param pstuOutParam: 参考{@link NET_OUT_GET_VTO_MANAGER_RELATION}
     * @description 获取组织树节点
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetVTOManagerRelation(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param        pstInParam:	参考{@link NET_IN_ADD_SOFT_TOUR_POINT_INFO}
     * @param        pstOutParam:	参考{@link NET_OUT_ADD_SOFT_TOUR_POINT_INFO}
     * @description 增加软巡航预置点
     * @description param[in]   lLoginID 登录句柄
     * @description param[in]   pstInParam 接口输入参数
     * @description param[out]  pstOutParam 接口输出参数
     * @description param[in]   nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_AddSoftTourPoint(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @param        pstInParam:	参考{@link NET_IN_REMOVE_SOFT_TOUR_POINT_INFO}
     * @param        pstOutParam:	参考{@link NET_OUT_REMOVE_SOFT_TOUR_POINT_INFO}
     * @description 清除软巡航预置点
     * @description param[in]   lLoginID 登录句柄
     * @description param[in]   pstInParam 接口输入参数
     * @description param[out]  pstOutParam 接口输出参数
     * @description param[in]   nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_RemoveTourPoint(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @param		pstuInParam:	参考{@link NET_IN_INSERT_MULTI_TALK_DEV}
     * @param		pstuOutParam:	参考{@link NET_OUT_INSERT_MULTI_TALK_DEV}
     * @description  下发设备信息
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_InsertMultiTalkDev(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param		pstuInParam:	参考{@link NET_IN_UPDATE_MULTI_TALK_DEV}
     * @param		pstuOutParam:	参考{@link NET_OUT_UPDATE_MULTI_TALK_DEV}
     * @description  批量更新设备信息接口
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstuInParam 接口入参
     * @description param[out] pstuOutParam 接口出参
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_UpdateMultiTalkDev(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @param		pstuInParam:	参考{@link NET_IN_DO_FIND_TALK_DEV}
     * @param		pstuOutParam:	参考{@link NET_OUT_DO_FIND_TALK_DEV}
     * @description  执行信息查询, lFindID为CLIENT_StartFindTalkDev接口返回的查找ID
     * @description param[in] lFindID 查询句柄
     * @description param[in] pstuInParam 接口输入参数
     * @description param[out]pstuOutParam 接口输出参数
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DoFindTalkDev(LLong lFindID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 录像下载--扩展Ex3接口，将所有参数整合在一个结构体，方便后续扩展
     * @param   lLoginID 登录句柄
     * @param	pstuInParam:	参考{@link NET_IN_DOWNLOAD}
     * @param	pstuOutParam:	参考{@link NET_OUT_DOWNLOAD}
     * @param   dwWaitTime 接口超时时间, 单位毫秒
     * @return 下载句柄
     */
    public LLong CLIENT_DownloadByRecordFileEx3(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

 /**
     * @param		pstInParam:	参考{@link NET_IN_ATTACH_HYGROTHERMOGRAPH}
     * @param		pstOutParam:	参考{@link NET_OUT_ATTACH_HYGROTHERMOGRAPH}
     * @description  订阅温湿度实时检测数据
     * @description param[in] lLoginID 登录句柄
     * @description param[in] pstInParam 接口输入参数
     * @description param[out]pstOutParam 接口输出参数
     * @description param[in] nWaitTime 接口超时时间, 单位毫秒
     * @description return 返回订阅句柄
     */
    public LLong CLIENT_AttachHygrothermograph(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @param		pstuHygrothermographInfo:	参考{@link NET_HYGROTHERMOGRAPH_INFO}
     * @description  订阅温湿度实时检测数据回调函数原型, lHygrothermographHandle为CLIENT_AttachHygrothermograph接口的返回值
     * @description param[out] lHygrothermographHandle 订阅句柄
     * @description param[out] pstuHygrothermographInfo 订阅温湿度实时检测数据回调信息
     * @description param[out] dwUser 用户信息
     * @description return void
     */
    public interface fNotifyHygrothermograph extends Callback {
        public void invoke(LLong lHygrothermographHandle,Pointer pstuHygrothermographInfo,Pointer dwUser);
    }

    /**
     * @description  退订温湿度实时检测数据
     * @description param[in] lHygrothermographHandle 订阅句柄
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_DetachHygrothermograph(LLong lHygrothermographHandle);

    /**
     * @description  查询录像下载进度
     * @description param[in] lFileHandle  下载句柄
     * @description param[out] nTotalSize  总文件大小
     * @description param[out] nDownLoadSize  已下载文件大小
     * @description return TRUE表示成功 FALSE表示失败
     */
    public boolean CLIENT_GetDownloadPos(LLong lFileHandle,Pointer nTotalSize,Pointer nDownLoadSize);

    /**
     * @brief 二次录像分析实时结果订阅函数原型
     * @param pstAnalyseResultInfo ， 参考结构体定义 {@link NET_CB_ANALYSE_RESULT_INFO)
     */
    public interface fAnalyseResultCallBack extends Callback {
        public int invoke(LLong lAnalyseHandle,Pointer pstAnalyseResultInfo,Pointer pBuffer,int dwBufSize);
    }

    /**
     * @brief 测温温度数据状态回调函数
     * @param pBuf ， 参考结构体定义 {@link NET_RADIOMETRY_TEMPER_DATA)
     */
    public interface fRadiometryAttachTemperCB extends Callback {
        public void invoke(LLong lAttachTemperHandle,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

    /**
     * @brief 订阅气象信息回调函数原型
     * @param pBuf ， 参考结构体定义 {@link NET_WEATHER_INFO)
     */
    public interface fWeatherInfoCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

    /**
     * @brief 订阅电梯内实时数据接口回调函数原型, lAttachHandle 为 CLIENT_AttachElevatorFloorCounter 接口的返回值
     * @param pstuElevatorFloorCounterInfo ， 参考结构体定义 {@link NET_NOTIFY_ELEVATOR_FLOOR_COUNTER_INFO)
     */
    public interface fNotifyElevatorFloorCounterdata extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuElevatorFloorCounterInfo,Pointer dwUser);
    }

    /**
     * @brief 事件详细信息回调
     * @param pstuNotifyIVSEventDetailInfo ， 参考结构体定义 {@link NET_NOTIFY_IVSEVENT_DETAIL_INFO)
     */
    public interface fNotifyIVSEventDetail extends Callback {
        public void invoke(Pointer pstuNotifyIVSEventDetailInfo,Pointer dwUser);
    }

    /**
     * @brief 收到低功耗设备保活包回调函数原型
     * @param pstLowPowerKeepAliveCallBackInfo ， 参考结构体定义 {@link NET_LOW_POWER_KEEPALIVE_CALLBACK_INFO)
     */
    public interface fLowPowerKeepAliveCallBack extends Callback {
        public void invoke(LLong lLowPowerHandle,Pointer pstLowPowerKeepAliveCallBackInfo);
    }

    /**
     * @brief 订阅历史库以图搜图回调函数原型, lAttachHandle为CLIENT_AttachResultOfFindHistoryByPic接口的返回值
     * @param pstesult ， 参考结构体定义 {@link NET_CB_RESULT_OF_FIND_HISTORY_BYPIC)
     */
    public interface fResultOfFindHistory extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstesult,Pointer pBinBuf,int nBinBufLen,Pointer dwUser);
    }

    /**
     * @brief 订阅485实时数据接口回调函数原型, lAttachHandle 为 CLIENT_AttachIotboxComm 接口的返回值
     * @param pstuIotboxComm ， 参考结构体定义 {@link NET_NOTIFY_IOTBOX_COMM)
     */
    public interface fNotifyIotboxRealdata extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuIotboxComm,Pointer dwUser);
    }

    /**
     * @brief 订阅485实时数据接口回调函数原型, lAttachHandle 为 CLIENT_AttachIotboxComm 接口的返回值
     * @param pstuIotboxCommEx ， 参考结构体定义 {@link NET_NOTIFY_IOTBOX_COMM_EX)
     */
    public interface fNotifyIotboxRealdataEx extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuIotboxCommEx,Pointer dwUser);
    }

    /**
     * @brief 区域流量数据的回调函数
     * @param pstuNotifyAreaFlowInfo ， 参考结构体定义 {@link NET_NOTIFY_AREA_FLOW_INFO)
     */
    public interface fNotifyAreaFlowInfo extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuNotifyAreaFlowInfo,Pointer dwUser);
    }

    /**
     * @brief 接口 CLIENT_AttachTalkState 的回调函数
     * @param pstuState ， 参考结构体定义 {@link NET_TALK_STATE)
     */
    public interface fNotifyTalkState extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuState,int nLen,Pointer dwUser);
    }

    /**
     * @brief 屏幕叠加回调函数原形
     */
    public interface fDrawCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lPlayHandle,Pointer hDC,Pointer dwUser);
    }

    /**
     * @brief CLIENT_AttachStartStreamData的回调函数
     * @param pstuStartStreamData ， 参考结构体定义 {@link NET_CB_START_STREAM_DATA_INFO)
     */
    public interface fStartStreamDataCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuStartStreamData,Pointer dwUser,Pointer pBuffer,int dwBufSize);
    }

    /**
     * @brief 调试日志回调函数
     */
    public interface fAttachSniffer extends Callback {
        public void invoke(LLong lAttchHandle,Pointer pBuffer,int dwBufSize,Pointer dwUser);
    }

    /**
     * @brief 调试日志回调函数
     */
    public interface fDebugInfoCallBack extends Callback {
        public void invoke(LLong lAttchHandle,Pointer pBuffer,int dwBufSize,Pointer dwUser);
    }

    /**
     * @brief 智能分析结果的回调函数
     * @param pstuVideoAnalyseTrackProcEx ， 参考结构体定义 {@link NET_VIDEO_ANALYSE_ANALYSE_PROC_EX)
     */
    public interface fVideoAnalyseAnalyseProcEx extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuVideoAnalyseTrackProcEx,Pointer dwUser);
    }

    /**
     * @brief TTLV异步搜索设备回调(pDevNetInfo内存由SDK内部申请释放)
     * @param pDevNetInfo ， 参考结构体定义 {@link DEVICE_NET_INFO_TTLV)
     */
    public interface fSearchDevicesCBTTLV extends Callback {
        public void invoke(LLong lSearchHandle,Pointer pDevNetInfo,Pointer pUserData);
    }

    /**
     * @brief 向客户端发送录像文件回调函数, lAttachHandle 为 CLIENT_AttachRecordManagerState 返回的结果
     * @param pstuState ， 参考结构体定义 {@link NET_RECORDMANAGER_NOTIFY_INFO)
     */
    public interface fRecordManagerStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuState,int dwStateSize,Pointer dwUser);
    }

    /**
     * @brief CLIENT_AttachNormalUsingJson的回调函数
     * @param pstuAttachNormalInfo ， 参考结构体定义 {@link NET_CB_ATTACH_NORMAL_INFO)
     */
    public interface fAttachNormalCallBack extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pstuAttachNormalInfo,Pointer pBuffer,int dwBufSize,Pointer dwUser);
    }

    /**
     * @brief CLIENT_PostLoginTask 登录结果回调
     * @param pOutParam ， 参考结构体定义 {@link NET_POST_LOGIN_TASK)
     */
    public interface fPostLoginTask extends Callback {
        public void invoke(int dwTaskID,Pointer pOutParam,Pointer dwUser);
    }

    /**
     * @brief 帧信息回调
     * @param pInfo ， 参考结构体定义 {@link NET_FRAME_INFO_CALLBACK_INFO)
     */
    public interface fFrameInfoCallBackEx extends Callback {
        public void invoke(LLong lHandle,Pointer pInfo,Pointer dwUser);
    }

    /**
     * @brief CLIENT_AttachDockEvents 接口回调函数
     * @param pUASDockEvents ， 参考结构体定义 {@link NET_NOTIFY_UAS_DOCK_EVENTS)
     */
    public interface fUAVDockEvents extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pUASDockEvents,Pointer dwUser);
    }

    /**
     * @brief CLIENT_AttachDockInfo 接口输入参数
     * @param pUASDockInfo ， 参考结构体定义 {@link NET_NOTIFY_UAS_DOCK_INFO)
     */
    public interface fUAVDockInfo extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pUASDockInfo,Pointer dwUser);
    }

    /**
     * @brief CLIENT_AttachDockStatus 接口回调函数
     * @param pUASDockInfo ， 参考结构体定义 {@link NET_NOTIFY_UAS_DOCK_STATUS)
     */
    public interface fUAVDockStatus extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pUASDockInfo,Pointer dwUser);
    }

    /**
     * @brief 开包检查结果回调函数
     * @param pInfo ， 参考结构体定义 {@link NET_XRARY_UNPACKING_INFO)
     */
    public interface fXRayUnpackingResult extends Callback {
        public void invoke(LLong lAttachHandle,Pointer pInfo,Pointer dwUser);
    }

    /**
     * @brief 获取当前所有规则温度信息
     * @param pInParam 接口输入参数， 参考结构体定义 NET_IN_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO
     * @param pOutParam 接口输出参数， 参考结构体定义 NET_OUT_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO
     */
    public boolean CLIENT_RadiometryGetCurTemperAll(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取测温项温度的参数值
     * @param pInParam 接口输入参数， 参考结构体定义 NET_IN_RADIOMETRY_GET_TEMPER_INFO
     * @param pOutParam 接口输出参数， 参考结构体定义 NET_OUT_RADIOMETRY_GET_TEMPER_INFO
     */
    public boolean CLIENT_RadiometryGetTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取EAS设备能力集
     * @param pstuInParam 接口输入参数， 参考结构体定义 NET_IN_GET_EAS_DEVICE_CAPS_INFO
     * @param pstuOutParam 接口输出参数， 参考结构体定义 NET_OUT_GET_EAS_DEVICE_CAPS_INFO
     */
    public boolean CLIENT_GetEASDeviceCaps(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设备导出文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_EXPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_EXPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityExportDataEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 设备导入文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_IMPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_IMPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityImportDataEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 准备生成导出文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_PREPARE_EXPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_PREPARE_EXPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityPrepareExportData(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 查询特定数据类型的任务状态接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_GETCAPS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_GETCAPS_INFO}
     */
    public boolean CLIENT_SecurityGetCaps(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 查询当前支持的导入导出数据类型
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_GET_TASK_STATUS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_GET_TASK_STATUS_INFO}
     */
    public boolean CLIENT_SecurityGetTaskStatus(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 添加添加本地分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ADD_LOCAL_ANALYSE_TASK}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ADD_LOCAL_ANALYSE_TASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseAddLocalAnalyseTask(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取设备点位信息
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SCADA_GET_ATTRIBUTE_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SCADA_GET_ATTRIBUTE_INFO}
     */
    public boolean CLIENT_SCADAGetAttributeInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 订阅录像二次分析实时结果 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_SECONDARY_ANALYSE_RESULT}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_SECONDARY_ANALYSE_RESULT}
     */
    public LLong CLIENT_AttachRecordSecondaryAnalyseResult(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 开始录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_STARTTASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_STARTTASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseStartTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 设置日志加密密码
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SET_LOG_ENCRYPT_KEY_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_LOG_ENCRYPT_KEY_INFO}
     */
    public boolean CLIENT_SetLogEncryptKey(LLong lLogID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅测温温度数据,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RADIOMETRY_ATTACH_TEMPER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RADIOMETRY_ATTACH_TEMPER}
     */
    public LLong CLIENT_RadiometryAttachTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 取消订阅录像二次分析实时结果
     */
    public boolean CLIENT_DetachRecordSecondaryAnalyseResult(LLong lAttachHandle);

    /**
     * @brief 删除录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_REMOVETASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_REMOVETASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseRemoveTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 暂停录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_PAUSETASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_PAUSETASK}
     */
    public boolean CLIENT_RecordSecondaryAnalysePauseTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取闸机状态
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ACCESS_GET_ASG_STATE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ACCESS_GET_ASG_STATE}
     */
    public boolean CLIENT_GetASGState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 清除闸机的统计信息方法，pstInParam与pstOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ASGMANAGER_CLEAR_STATISTICS}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ASGMANAGER_CLEAR_STATISTICS}
     */
    public boolean CLIENT_ASGManagerClearStatistics(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 开始放音接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_REMOTE_SPEAK_PLAY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_REMOTE_SPEAK_PLAY}
     */
    public boolean CLIENT_StartRemoteSpeakPlay(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止放音接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_REMOTE_SPEAK_PLAY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_REMOTE_SPEAK_PLAY}
     */
    public boolean CLIENT_StopRemoteSpeakPlay(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 单条测温规则设置更新
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SET_RADIOMETRY_RULE_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_RADIOMETRY_RULE_INFO}
     */
    public boolean CLIENT_SetRadiometryRule(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅气象信息,pstuInParam与pstuOutParam内存由用户申请释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_WEATHER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_WEATHER_INFO}
     */
    public LLong CLIENT_AttachWeatherInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止订阅气象信息,lAttachHandle是CLIENT_AttachWeatherInfo返回值
     */
    public boolean CLIENT_DetachWeatherInfo(LLong lAttachHandle);

    /**
     * @brief 获取气象信息,pInBuf与pOutBuf内存由用户申请释放
     * @param pInBuf 接口输入参数， 参考结构体定义 {@link NET_IN_GET_ATOMSPHDATA}
     * @param pOutBuf 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_ATOMSPHDATA}
     */
    public boolean CLIENT_GetAtomsphData(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * @brief 获取测温区域的参数值, pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RADIOMETRY_RANDOM_REGION_TEMPER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RADIOMETRY_RANDOM_REGION_TEMPER}
     */
    public boolean CLIENT_RadiometryGetRandomRegionTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 分组人员统计--获取摘要信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_NUMBERSTATGROUPSUMMARY_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_NUMBERSTATGROUPSUMMARY_INFO}
     */
    public boolean CLIENT_GetNumberStatGroupSummary(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 订阅电梯内实时数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_ELEVATOR_FLOOR_COUNTER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_ELEVATOR_FLOOR_COUNTER_INFO}
     */
    public LLong CLIENT_AttachElevatorFloorCounter(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取临时token
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_TEMPORARY_TOKEN}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_TEMPORARY_TOKEN}
     */
    public boolean CLIENT_GetTemporaryToken(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅电梯内实时数据
     */
    public boolean CLIENT_DetachElevatorFloorCounter(LLong lAttachHandle);

    /**
     * @brief 获取事件详细信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_IVSEVENT_DETAIL_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_GET_IVSEVENT_DETAIL_INFO}
     */
    public boolean CLIENT_GetIVSEventDetail(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 得到远程指定目录下文件信息（含文件/子目录）pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REMOTE_LIST}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REMOTE_LIST}
     */
    public boolean CLIENT_RemoteList(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 唤醒设备
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_WAKE_UP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_WAKE_UP_INFO}
     */
    public boolean CLIENT_DoWakeUpLowPowerDevcie(LLong lChannelHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 创建低功耗通道
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_CREATE_LOW_POWER_CHANNEL}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_CREATE_LOW_POWER_CHANNEL}
     */
    public LLong CLIENT_CreateLowPowerChannel(LLong lSubBizHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 销毁低功耗通道
     */
    public boolean CLIENT_DestoryLowPowerChannel(LLong lChannelHandle);

    /**
     * @brief 拒绝休眠
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REFUSE_SLEEP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REFUSE_SLEEP_INFO}
     */
    public boolean CLIENT_RefuseLowPowerDevSleep(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取X射线源能力
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_XRAY_SOURCE_CAPS_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_XRAY_SOURCE_CAPS_INFO}
     */
    public boolean CLIENT_GetXRaySourceCaps(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取X射线源累积出束时间
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_XRAY_SOURCE_CUMULATE_TIME_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_XRAY_SOURCE_CUMULATE_TIME_INFO}
     */
    public boolean CLIENT_GetXRaySourceCumulateTime(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查找记录:nFilecount:需要查询的条数, 一般情况出参中nRetRecordNum<nFilecount则相应时间段内的文件查询完毕,但部分设备性能限制,nRetRecordNum<nFilecount不代表查询完毕,建议和CLIENT_QueryRecordCount配套使用,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_SEEK_NEXT_RECORD_PARAM}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_SEEK_NEXT_RECORD_PARAM}
     */
    public boolean CLIENT_FindSeekNextRecord(Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 查询人脸库总空间大小、剩余空间大小、人脸库可导入总条数和剩余可导入条数
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_FACE_RECOGNITION_GROUP_SPACE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_FACE_RECOGNITION_GROUP_SPACE_INFO}
     */
    public boolean CLIENT_GetFaceRecognitionGroupSpaceInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 人员重新建模, pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FACE_RECOGNITION_REABSTRACT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FACE_RECOGNITION_REABSTRACT_INFO}
     */
    public boolean CLIENT_FaceRecognitionReAbstract(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 终止建模
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_FACE_RECOGNITION_REABSTRACT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_FACE_RECOGNITION_REABSTRACT}
     */
    public boolean CLIENT_StopFaceRecognitionReAbstract(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 目标组重新建模, pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FACE_RECOGNITION_GROUP_REABSTRACT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FACE_RECOGNITION_GROUP_REABSTRACT_INFO}
     */
    public boolean CLIENT_FaceRecognitionGroupReAbstract(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取目标导入令牌
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_FACE_RECOGNITION_APPEND_TOKEN}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_FACE_RECOGNITION_APPEND_TOKEN}
     */
    public boolean CLIENT_GetFaceRecognitionAppendToken(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 组成要设置的智能配置信息(lpInBuffer，szOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_PacketIntelliSchemeData(Pointer szCommand,Pointer lpInBuffer,int dwInBufferSize,Pointer szOutBuffer,int dwOutBufferSize);

    /**
     * @brief 解析查询到的智能配置信息(szInBuffer，lpOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_ParseIntelliSchemeData(Pointer szCommand,Pointer szInBuffer,Pointer lpOutBuffer,int dwOutBufferSize,Pointer pReserved);

    /**
     * @brief 新配置接口，查询配置信息(以Json格式，具体见配置SDK)(szOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_IntelliSchemeGetNewDevConfig(LLong lLoginID,Pointer szCommand,int nChannelID,Pointer szOutBuffer,int dwOutBufferSize,IntByReference error,int waittime,Pointer pReserved,int nSchemeID);

    /**
     * @brief 设置指定的智能套餐方案的配置信息
     */
    public boolean CLIENT_IntelliSchemeSetNewDevConfig(LLong lLoginID,Pointer szCommand,int nChannelID,Pointer szInBuffer,int dwInBufferSize,IntByReference error,IntByReference restart,int waittime,int nTransactionID,int nSchemeID);

    /**
     * @brief 订阅历史库以图搜图查询结果, 配合CLIENT_StartFindFaceRecognition使用, pstInParam和pstOutParam由用户申请和释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_RESULT_FINDHISTORY_BYPIC}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_RESULT_FINDHISTORY_BYPIC}
     */
    public LLong CLIENT_AttachResultOfFindHistoryByPic(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅历史库以图搜图查询结果, lFindHandle 为 CLIENT_AttachResultOfFindRegisterByPic接口返回的值
     */
    public boolean CLIENT_DetachResultOfFindHistoryByPic(LLong lFindHandle);

    /**
     * @brief 获取智能套餐方案的基础信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_SCHEME_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_SCHEME_INFO}
     */
    public boolean CLIENT_GetSchemeInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 下发https服务器地址，用于公交线路、报站信息的上报,pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DISPATCH_BUS_HTTPS_SERVERS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DISPATCH_BUS_HTTPS_SERVERS_INFO}
     */
    public boolean CLIENT_DispatchBusHttpsServers(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 订阅485实时数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_IOTBOX_COMM}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_IOTBOX_COMM}
     */
    public LLong CLIENT_AttachIotboxComm(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅实时数据
     */
    public boolean CLIENT_DetachIotboxComm(LLong lAttachHandle);

    /**
     * @brief 订阅485实时数据扩展
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_IOTBOX_COMM_EX}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_IOTBOX_COMM_EX}
     */
    public LLong CLIENT_AttachIotboxCommEx(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅实时数据扩展
     */
    public boolean CLIENT_DetachIotboxCommEx(LLong lAttachHandle);

    /**
     * @brief 订阅区域流量数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_AREA_FLOW}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_ATTACH_AREA_FLOW}
     */
    public LLong CLIENT_AttachAreaFlow(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅前端设备对讲状态,pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_TALK_STATE}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_TALK_STATE}
     */
    public LLong CLIENT_AttachTalkState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅前端设备对讲状态, lAttachHandle为CLIENT_AttachTalkState返回的句柄
     */
    public boolean CLIENT_DetachTalkState(LLong lAttachHandle);

    /**
     * @brief 获取安检机录像文件
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_XRAY_DOWNLOAD_RECORD_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_XRAY_DOWNLOAD_RECORD_INFO}
     */
    public boolean CLIENT_GetXRayDownloadRecord(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置屏幕叠加回调
     * @param cbDraw 接口输入参数， 参考回调函数定义 {@link fDrawCallBack}
     */
    public void CLIENT_RigisterDrawFun(fDrawCallBack cbDraw,Pointer dwUser);

    /**
     * @brief 上传二进制地图图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UPLOAD_MAP_PIC}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UPLOAD_MAP_PIC}
     */
    public boolean CLIENT_UploadMapPic(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 加载地图图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_LOAD_MAP_PIC}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_LOAD_MAP_PIC}
     */
    public boolean CLIENT_LoadMapPic(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取地图已标记信息列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_PIC_MAP_MARK_LIST}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_PIC_MAP_MARK_LIST}
     */
    public boolean CLIENT_GetPicMapMarkList(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 地图上标记通道位置信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MARK_PIC_MAP_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MARK_PIC_MAP_CHANNEL}
     */
    public boolean CLIENT_MarkPicMapChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消通道的地图标记
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UNMARK_PIC_MAP_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UNMARK_PIC_MAP_CHANNEL}
     */
    public boolean CLIENT_UnmarkPicMapChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 重置清空地图（包括地图图片，标记信息）
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RESET_PIC_MAP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RESET_PIC_MAP}
     */
    public boolean CLIENT_ResetPicMap(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 收藏目标事件记录
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     */
    public boolean CLIENT_MarkObjectFavoritesLibraryObjectRecords(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消收藏目标事件记录
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UNMARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UNMARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     */
    public boolean CLIENT_UnmarkObjectFavoritesLibraryObjectRecords(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 检查目标事件记录的收藏状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_CHECK_OBJECT_FAVORITES_LIBRARY_MARK_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_CHECK_OBJECT_FAVORITES_LIBRARY_MARK_STATUS}
     */
    public boolean CLIENT_CheckObjectFavoritesLibraryMarkStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 开始按条件，查找收藏夹内容数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_FIND_OBJECT_FAVORITES_LIBRARY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_FIND_OBJECT_FAVORITES_LIBRARY}
     */
    public LLong CLIENT_StartFindObjectFavoritesLibrary(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取收藏夹内容数据, lFindID为CLIENT_StartFindObjectFavoritesLibrary接口返回的查找ID
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DO_FIND_OBJECT_FAVORITES_LIBRARY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DO_FIND_OBJECT_FAVORITES_LIBRARY}
     */
    public boolean CLIENT_DoFindObjectFavoritesLibrary(LLong lFindID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止查找收藏夹内容数据，清空查询会话, lFindID为CLIENT_StartFindObjectFavoritesLibrary接口返回的查找ID
     */
    public boolean CLIENT_StopFindObjectFavoritesLibrary(LLong lFindID);

    /**
     * @brief 根据目标轨迹过滤规则，开始查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_FIND_OBJECT_MEDIA_FIND}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_FIND_OBJECT_MEDIA_FIND}
     */
    public LLong CLIENT_StartFindObjectMediaFind(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取录像查询结果
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_OBJECT_MEDIA_FIND_FILE}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_OBJECT_MEDIA_FIND_FILE}
     */
    public boolean CLIENT_FindObjectMediaFindFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取目标事件查询结果
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_OBJECT_MEDIA_FIND_EVENT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_OBJECT_MEDIA_FIND_EVENT}
     */
    public boolean CLIENT_FindObjectMediaFindEvent(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_FIND_OBJECT_MEDIA_FIND}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_FIND_OBJECT_MEDIA_FIND}
     */
    public boolean CLIENT_StopFindObjectMediaFind(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取系统参数
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_BOOT_PARAMETER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_BOOT_PARAMETER}
     */
    public boolean CLIENT_GetBootParameter(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 一键操作录播录像的开启/暂停/关闭
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MANUAL_CONTROL_COURSE_RECORD_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MANUAL_CONTROL_COURSE_RECORD_INFO}
     */
    public boolean CLIENT_ManualControlCourseRecord(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置车位检测器车灯亮灯计划,pNetDataIn与pNetDataOut由用户申请内存
     * @param pNetDataIn 接口输入参数， 参考结构体定义 {@link NET_IN_SET_PARKING_SPACE_LIGHT_PLAN}
     * @param pNetDataOut 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_PARKING_SPACE_LIGHT_PLAN}
     */
    public boolean CLIENT_SetParkingSpaceLightPlan(LLong lLoginID,Pointer pNetDataIn,Pointer pNetDataOut,int nWaitTime);

    /**
     * @brief 修改采集文件备注
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MODIFY_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MODIFY_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_ModifyMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 删除采集文件
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DELETE_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DELETE_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_DeleteMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 开始查询采集文件信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_OPEN_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_OPEN_INFO}
     */
    public boolean CLIENT_QueryMediaFileOpen(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查询采集文件信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_QueryMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 关闭采集文件查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_CLOSE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_CLOSE_INFO}
     */
    public boolean CLIENT_QueryMediaFileClose(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 云运维异步诊断接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ASYNC_CHECK_FAULT_CHECK}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ASYNC_CHECK_FAULT_CHECK}
     */
    public boolean CLIENT_AsyncCheckFaultCheck(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 四合一烟感平台下发整机重启
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REBOOT_DEVICE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REBOOT_DEVICE}
     */
    public boolean CLIENT_RebootDevice(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 重启系统,恢复出厂默认(包括清空配置和删除账户)并重启，实现硬复位功能
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RESET_SYSTEM}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RESET_SYSTEM}
     */
    public boolean CLIENT_ResetSystem(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取设备当前状态并绑定导出接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_START_STREAM_DATA}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_START_STREAM_DATA}
     */
    public LLong CLIENT_AttachStartStreamData(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅抓包数据,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_SNIFFER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_SNIFFER}
     */
    public LLong CLIENT_AttachSniffer(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅调试日志回调
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_DBGINFO}
     * @param pOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_ATTACH_DBGINFO}
     */
    public LLong CLIENT_AttachDebugInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 注销一键导出处理
     */
    public boolean CLIENT_DetachStopStreamData(LLong lAttachHandle);

    /**
     * @brief 退订抓包数据
     */
    public boolean CLIENT_DetachSniffer(LLong lAttachHandle);

    /**
     * @brief 退订调试日志回调
     */
    public boolean CLIENT_DetachDebugInfo(LLong lAttachHanle);

    /**
     * @brief 添加Onvif用户， pstInParam、pstOutParam 内存由用户申请、释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ADD_ONVIF_USER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ADD_ONVIF_USER_INFO}
     */
    public boolean CLIENT_AddOnvifUser(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取子模块信息
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_SUBMODULES_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_SUBMODULES_INFO}
     */
    public boolean CLIENT_GetSubModuleInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取软件版本
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SOFTWAREVERSION_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SOFTWAREVERSION_INFO}
     */
    public boolean CLIENT_GetSoftwareVersion(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 开始抓包,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_SNIFFER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_SNIFFER}
     */
    public LLong CLIENT_StartSniffer(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 停止抓包
     */
    public boolean CLIENT_StopSniffer(LLong lLoginID,LLong lSnifferID);

    /**
     * @brief 获取抓包状态,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SNIFFER_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SNIFFER_INFO}
     */
    public boolean CLIENT_GetSnifferInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取抓包能力,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SNIFFER_CAP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SNIFFER_CAP}
     */
    public boolean CLIENT_GetSnifferCaps(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取从片版本信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_PERIPHERAL_CHIP_VERSION}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_PERIPHERAL_CHIP_VERSION}
     */
    public boolean CLIENT_GetPeripheralChipVersion(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 调节光圈
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_PTZ_ADJUST_IRIS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_PTZ_ADJUST_IRIS}
     */
    public boolean CLIENT_PTZAdjustIris(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取CPU温度
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CPU_TEMPERATURE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CPU_TEMPERATURE_INFO}
     */
    public boolean CLIENT_GetCPUTemperature(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅智能分析结果
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC_EX}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC_EX}
     */
    public LLong CLIENT_AttachVideoAnalyseAnalyseProcEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅智能分析结果
     */
    public boolean CLIENT_DetachVideoAnalyseAnalyseProcEx(LLong lAttachHandle);

    /**
     * @brief 持续对焦
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FOCUS_PTZ_CONTINUOUSLY_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FOCUS_PTZ_CONTINUOUSLY_INFO}
     */
    public boolean CLIENT_FocusPTZContinuously(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置电视墙场景,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MONITORWALL_SET_SCENE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MONITORWALL_SET_SCENE}
     */
    public boolean CLIENT_MonitorWallSetScene(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取电视墙场景,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MONITORWALL_GET_SCENE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MONITORWALL_GET_SCENE}
     */
    public boolean CLIENT_MonitorWallGetScene(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置窗口场景信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SPLIT_SET_WINDOWS_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SPLIT_SET_WINDOWS_INFO}
     */
    public boolean CLIENT_SetSplitWindowsInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 根据执法人ID获取该执法人绑定的执法记录仪序列号列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_COLLECT_DEVICES_INFO_BY_TSFID_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_COLLECT_DEVICES_INFO_BY_TSFID_INFO}
     */
    public boolean CLIENT_GetCollectDevicesInfoByTsfId(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取所有 Onvif 用户信息，pstInParam、pstOutParam 内存由用户申请、释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GETONVIF_USERINFO_ALL_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GETONVIF_USERINFO_ALL_INFO}
     */
    public boolean CLIENT_GetOnvifUserInfoAll(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取蜂窝邻区信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_NEIGHBOUR_CELL_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_NEIGHBOUR_CELL_INFO}
     */
    public boolean CLIENT_GetNeighbourCellInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置窗口轮巡显示源(pInparam, pOutParam内存由用户申请释放)
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_TOUR_SOURCE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_TOUR_SOURCE}
     */
    public boolean CLIENT_SetTourSource(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取窗口轮巡显示源(pInparam, pOutParam内存由用户申请释放)
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_TOUR_SOURCE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_TOUR_SOURCE}
     */
    public boolean CLIENT_GetTourSource(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 楼层一键标定
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_AUTO_CALIBRATE}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_AUTO_CALIBRATE}
     */
    public boolean CLIENT_AutoCalibrate(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 手动修改楼层高度并生成气压差信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MODIFY_FLOOR_HEIGHT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MODIFY_FLOOR_HEIGHT}
     */
    public boolean CLIENT_ModifyFloorHeight(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取ElevatorFloorCounter.autoCalibrate下发后的标定状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CALIBRATE_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CALIBRATE_STATUS}
     */
    public boolean CLIENT_GetCalibrateStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 启动程序
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_APP}
     */
    public boolean CLIENT_StartApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 停止程序运行
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_STOP_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_STOP_APP}
     */
    public boolean CLIENT_StopApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 卸载程序
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_REMOVE_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_REMOVE_APP}
     */
    public boolean CLIENT_RemoveApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 第三方APP升级进度和结果的实时上报
     * @param emType 接口输出参数， 参考枚举定义 {@link com.netsdk.lib.enumeration.EM_NET_UPGRADE_INSTALL_TYPE}
     */
    public boolean CLIENT_UpgraderInstall(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaittime);

    /**
     * @brief 获取安装的应用列表
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_INSTALLED_APP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_INSTALLED_APP_INFO}
     */
    public boolean CLIENT_GetInstalledAppInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 通用RPC接口，支持Json传参
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_NORMAL_RPCCALL_USING_JSON}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_NORMAL_RPCCALL_USING_JSON}
     */
    public boolean CLIENT_NormalRpcCallUsingJson(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取存储端口信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_PORTINFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_PORTINFO}
     */
    public boolean CLIENT_GetStoragePortInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 订阅录像状态变化,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_RECORDMANAGER_ATTACH_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_RECORDMANAGER_ATTACH_INFO}
     */
    public LLong CLIENT_AttachRecordManagerState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取外部文件上传的文件名和路径
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_UPLOAD_PATH_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_UPLOAD_PATH_INFO}
     */
    public boolean CLIENT_GetUploadPath(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取指定盘组下的设备成员信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WORK_GROUP_DEVICE_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WORK_GROUP_DEVICE_INFO}
     */
    public boolean CLIENT_GetWorkGroupDeviceInfos(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief json通用订阅接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_NORMAL_USING_JSON}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_NORMAL_USING_JSON}
     */
    public LLong CLIENT_AttachNormalUsingJson(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief json通用订阅退订接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DETACH_NORMAL_USING_JSON}
     */
    public boolean CLIENT_DetachNormalUsingJson(LLong lLoginID,Pointer pstuInParam);

    /**
     * @brief 开启音频录音并得到录音名
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_AUDIO_RECORD_MANAGER_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_AUDIO_RECORD_MANAGER_CHANNEL}
     */
    public boolean CLIENT_StartAudioRecordManagerChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 关闭即时录音
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_STOP_AUDIO_RECORD_MANAGER_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_STOP_AUDIO_RECORD_MANAGER_CHANNEL}
     */
    public boolean CLIENT_StopAudioRecordManagerChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 按组获取视频通道
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CAMERA_ALL_BY_GROUP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CAMERA_ALL_BY_GROUP}
     */
    public boolean CLIENT_MatrixGetCameraAllByGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 投递异步登录任务
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_POST_LOGIN_TASK}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_POST_LOGIN_TASK}
     */
    public int CLIENT_PostLoginTask(Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 取消 CLIENT_PostLoginTask 接口的异步登录任务，dwTaskID 为 CLIENT_PostLoginTask 返回值
     */
    public boolean CLIENT_CancelLoginTask(int dwTaskID);

    /**
     * @brief 设置优化方案 pParam内存由用户申请释放，大小参照emType对应的结构体
     * @param emType 接口输入参数， 参考枚举定义 {@link com.netsdk.lib.enumeration.NET_SYS_ABILITY}
     */
    public boolean CLIENT_SetOptimizeMode(int emType,Pointer pParam);

    /**
     * @brief 查询部门信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_DEPARTMENT_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_DEPARTMENT_INFO}
     */
    public boolean CLIENT_GetTipStaffManagerDepartmentInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查询执法人信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_TIP_STAFF_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_TIP_STAFF_INFO}
     */
    public boolean CLIENT_GetTipStaffManagerTipStaffInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取雷达检测目标数据
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WATERRADAR_OBJECTINFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WATERRADAR_OBJECTINFO}
     */
    public boolean CLIENT_GetWaterRadarObjectInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 报警上传功能,启动服务；dwTimeOut参数已无效
     * @param pfscb 接口输入参数， 参考回调函数定义 {@link com.netsdk.lib.NetSDKLib.fServiceCallBack}
     */
    public LLong CLIENT_StartService(int wPort,Pointer pIp,fServiceCallBack pfscb,int dwTimeOut,Pointer dwUserData);

    /**
     * @brief 设置实时预览帧信息回调
     * @param cbFrameInfo 接口输入参数， 参考回调函数定义 {@link com.netsdk.lib.NetSDKLib.fFrameInfoCallBackEx}
     */
    public boolean CLIENT_SetRealFrameInfoCallBack(LLong lRealHandle,fFrameInfoCallBackEx cbFrameInfo,Pointer dwUser);

    /**
     * @brief 发送按键消息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SEND_XRAY_KEY_MANAGER_KEY_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SEND_XRAY_KEY_MANAGER_KEY_INFO}
     */
    public boolean CLIENT_SendXRayKeyManagerKey(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅测温温度数据,lAttachTemperHandle是 CLIENT_RadiometryAttachTemper 的返回值
     */
    public boolean CLIENT_RadiometryDetachTemper(LLong lAttachTemperHandle);

    /**
     * @brief 获取车流统计摘要信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_FIND_VEHICLE_FLOW_STAT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_FIND_VEHICLE_FLOW_STAT}
     */
    public LLong CLIENT_StartFindVehicleFlowStat(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取车流量统计结果信息, lFindID为 CLIENT_StartFindVehicleFlowStat 接口返回的查询ID
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DO_FIND_VEHICLE_FLOW_STAT}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DO_FIND_VEHICLE_FLOW_STAT}
     */
    public boolean CLIENT_DoFindVehicleFlowStat(LLong lFindID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 结束车流量统计结果查询
     */
    public boolean CLIENT_StopFindVehicleFlowStat(LLong lFindID);

    /**
     * @brief 设置水平旋转组边界值
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_PAN_GROUP_LIMIT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_PAN_GROUP_LIMIT_INFO}
     */
    public boolean CLIENT_PTZSetPanGroupLimit(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int dwWaitTime);

    /**
     * @brief 获取电视墙上屏幕窗口解码信息,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MW_GET_WINODW_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MW_GET_WINDOW_INFO}
     */
    public boolean CLIENT_MonitorWallGetWindowInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_WORKDIRECTORY_GETGROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_WORKDIRECTORY_GETGROUP_INFO}
     */
    public boolean CLIENT_WorkDirectoryGetGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_WORKDIRECTORY_SETGROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_WORKDIRECTORY_SETGROUP_INFO}
     */
    public boolean CLIENT_WorkDirectorySetGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取存储设备信息,pDevice内存由用户申请释放
     * @param pDevice 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_STORAGE_DEVICE}
     */
    public boolean CLIENT_GetStorageDeviceInfo(LLong lLoginID,Pointer pszDevName,Pointer pDevice,int nWaitTime);

    /**
     * @brief 异步格式化设备,格式化进度通过CLIENT_AttachDevStorageDevFormat接口的回调获取  pszDevName与CLIENT_GetStorageDeviceInfo中的pszDevName保持一致
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DEVSTORAGE_FORMAT_PARTITION_ASYN}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DEVSTORAGE_FORMAT_PARTITION_ASYN}
     */
    public boolean CLIENT_DevStorageFormatPartitionAsyn(LLong lLoginID,Pointer pszDevName,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取所有盘组信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_STORAGE_ASSISTANT_GROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_STORAGE_ASSISTANT_GROUP_INFO}
     */
    public boolean CLIENT_GetStorageAssistantGroupInfos(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WORK_DIRECTORY_GROUP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WORK_DIRECTORY_GROUP}
     */
    public boolean CLIENT_SetWorkDirectoryGoup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取工作组内的工作目录名称列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WORK_GROUP_DIRECTORIES}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WORK_GROUP_DIRECTORIES}
     */
    public boolean CLIENT_GetWorkGroupDirectories(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 添加盘组
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ADD_STORAGE_ASSISTANT_WORK_GROUP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ADD_STORAGE_ASSISTANT_WORK_GROUP}
     */
    public boolean CLIENT_AddStorageAssistantWorkGroup(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 删除盘组
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DELETE_STORAGE_ASSISTANT_WORK_GROUP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DELETE_STORAGE_ASSISTANT_WORK_GROUP}
     */
    public boolean CLIENT_DeleteStorageAssistantWorkGroup(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_EVENTS}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_EVENTS}
     */
    public LLong CLIENT_AttachDockEvents(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockEvents(LLong lAttachHandle);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_INFO}
     */
    public LLong CLIENT_AttachDockInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockInfo(LLong lAttachHandle);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_STATUS}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_STATUS}
     */
    public LLong CLIENT_AttachDockStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockStatus(LLong lAttachHandle);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_EVENTS_REPLY}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_EVENTS_REPLY}
     */
    public boolean CLIENT_DockEventsReply(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_PROPERTY_SET}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_PROPERTY_SET}
     */
    public boolean CLIENT_DockPropertySet(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_STATUS_REPLY}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_STATUS_REPLY}
     */
    public boolean CLIENT_DockStatusReply(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FLIGHT_TASK_PREPARE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FLIGHT_TASK_PREPARE}
     */
    public boolean CLIENT_FlightTaskPrepare(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FLIGHT_TASK_EXECUTE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FLIGHT_TASK_EXECUTE}
     */
    public boolean CLIENT_FlightTaskExecute(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_RETURN_HOME}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_RETURN_HOME}
     */
    public boolean CLIENT_ReturnHome(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置运单信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WAYBILL_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WAYBILL_INFO}
     */
    public boolean CLIENT_SetWaybillInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置开包检查结果带图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_UNPACKING_RESULT_WITH_PACKET}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_UNPACKING_RESULT_WITH_PACKET}
     */
    public boolean CLIENT_SetUnpackingResultWithPacket(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief X光机集中判图时 订阅开包检查结果
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_XRAY_ATTACH_UNPACKING}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_XRAY_ATTACH_UNPACKING}
     */
    public LLong CLIENT_XRay_AttachUnpackingResult(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief X光机集中判图时 退订开包检查结果
     */
    public boolean CLIENT_XRay_DetachUnpackingResult(LLong lAttachHandle);

    /**
     * @brief 设置运单开始/结束状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WAYBILL_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WAYBILL_STATUS}
     */
    public boolean CLIENT_SetWaybillStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取工作目录信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_STORAGE_INFO_BY_FILE_TYPE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_STORAGE_INFO_BY_FILE_TYPE}
     */
    public boolean CLIENT_GetStorageInfoByFileType(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

}
