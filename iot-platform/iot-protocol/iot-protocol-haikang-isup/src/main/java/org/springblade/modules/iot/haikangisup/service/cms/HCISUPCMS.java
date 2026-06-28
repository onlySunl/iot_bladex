package org.springblade.modules.iot.haikangisup.service.cms;

import com.sun.jna.*;
import com.sun.jna.ptr.*;

/**
 * 海康ISUP CMS SDK接口
 */
public interface HCISUPCMS extends Library {

	HCISUPCMS INSTANCE = Native.load("HCISUPCMS", HCISUPCMS.class);

	// 客户端初始化
	int NET_ECMS_Init();

	// 客户端销毁
	void NET_ECMS_Cleanup();

	// 建立CMS连接
	int NET_ECMS_Login(String svrIP, short wSvrPort, String username, String password);

	// 断开连接
.boolean NET_ECMS_Logout(int lUserID);

	// 获取错误码
	int NET_ECMS_GetLastError();

	// 获取设备配置
	boolean NET_ECMS_GetDevConfig(int lUserID, int dwCommand, Pointer pOutBuff, int dwOutBuffSize);

	// 获取设备配置(条件)
	boolean NET_ECMS_GetDevConfig_V30(int lUserID, String sLanFile, Pointer pCondBuff, int dwCondBuffSize, Pointer pInBuff, int dwInBuffSize, Pointer pOutBuff, int dwOutBuffSize, int dwOutBuffLen, IntByReference pdwBytesReturned, int dwTimeout);

	// 设置设备配置
	boolean NET_ECMS_SetDevConfig(int lUserID, int dwCommand, Pointer pInBuff, int dwInBuffSize);

	// 设置设备配置(条件)
	boolean NET_ECMS_SetDevConfig_V30(int lUserID, String sLanFile, Pointer pCondBuff, int dwCondBuffSize, Pointer pInBuff, int dwInBuffSize, int dwTimeout);

	// 远程控制
	boolean NET_ECMS_RemoteControl(int lUserID, int dwCommand, Pointer lpInBuffer);

	// 启动实时预览
	int NET_ECMS_StartRealPlay(int lUserID, NET_EHOME_PREVIEWINFO inParams, Pointer pProtoData);

	// 停止实时预览
	boolean NET_ECMS_StopRealPlay(int lRealHandle);

	// 启动录像回放
	int NET_ECMS_StartPlayback(int lUserID, NET_EHOME_PLAYBACKINFO inParams, Pointer pUserData);

	// 停止录像回放
	boolean NET_ECMS_StopPlayback(int lPlaybackHandle);

	// 获取回放进度
	boolean NET_ECMS_PlayBackControl(int lPlaybackHandle, int dwControlCode, int dwInValue, IntByReference pdwOutValue);

	// 抓图
	boolean NET_ECMS_CapturePicture(int lRealHandle, String sPicFileName, int dwPicSize);

	// 订阅报警
	int NET_ECMS_StartListen(String svrIP, short wPort, fMSGCallBack cbAlarmCallback, Pointer pUserData);

	// 停止订阅报警
	boolean NET_ECMS_StopListen(int lListenHandle);

	// 设备注册回调
	boolean NET_ECMS_SetDVRMessCallBack(fMSGCallBack fMSGFunc, Pointer pUserData);

	// 设备校时
	boolean NET_ECMS_SetDVRTime(int lUserID, byte[] pDatetime);

	// 远程重启设备
	boolean NET_ECMS_RemoteControl(int lUserID, int dwCommand);

	// 查询录像文件
	int NET_ECMS_FindFile(int lUserID, HCISUPCMS.NET_EHOME_FINDINFO lpFindInfo, int nFileCount, int waittime);

	// 查找下一个录像文件
	NET_EHOME_NEXT_FILE_INFO NET_ECMS_FindNextFile(int lFindHandle, int waittime);

	// 查找结束
	int NET_ECMS_FindClose(int lFindHandle);

	// 下载录像文件
	int NET_ECMS_GetFileByTime(int lUserID, String sServerFileName, String sSavedFileName, HCISUPCMS.NET_EHOME_DOWNLOAD_INFO inParams);

	// 停止下载
	boolean NET_ECMS_StopGetFile(int lFileHandle);

	// 升级设备
	int NET_ECMS_Upgrade(int lUserID, String sFileName);

	// 升级进度回调
	boolean NET_ECMS_SetUpgradeCallBack(int lUpgradeHandle, fUpgradeCallBack cbUpgradeCallback, Pointer pUserData);

	// 停止升级
	boolean NET_ECMS_StopUpgrade(int lUpgradeHandle);

	// 云台控制
	boolean NET_ECMS_PTZControl(int lRealHandle, int dwPTZCommand, int dwStop);

	// 获取设备状态
	boolean NET_ECMS_GetDeviceStatus(int lUserID, int dwCommand, Pointer pOutBuff, int dwOutBuffSize);

	// 透明通道发送
	boolean NET_ECMS_SerialSend(int lRealHandle, byte[] pSendBuf, int dwBufSize);

	// 获取通道能力集
	boolean NET_ECMS_GetDeviceConfig(int lUserID, int dwCommand, int dwChannel, Pointer pOutBuff, int dwOutBuffSize);

	// 设备搜索
	int NET_ECMS_FindDevice(String sBroadcastIP, int dwTimeout, fSearchDeviceCallBack cbSearchCallback, Pointer pUserData);

	// 停止设备搜索
	boolean NET_ECMS_FindDeviceClose(int lFindHandle);

	// 获取CMS配置
	boolean NET_ECMS_GetCMSConfig(int lUserID, String sCommand, Pointer pOutBuff, int dwOutBuffSize);

	// 设置CMS配置
	boolean NET_ECMS_SetCMSConfig(int lUserID, String sCommand, Pointer pInBuff, int dwInBuffSize);

	// 预览参数
	class NET_EHOME_PREVIEWINFO extends Structure {
		public int dwSize;
		public int dwChannel;           // 通道号
		public byte byStreamType;       // 码流类型：0-主码流，1-子码流
		public byte byLinkMode;         // 连接模式：0-TCP，1-UDP
		public byte byAutoBitrate;      // 自动码流
		public byte byRes;              // 保留
		public String sMultiCastIP;     // 多播IP地址
		public fRealDataCallBack rtspCB; // 回调函数
		public Pointer pUser;           // 用户数据
	}

	// 回放参数
	class NET_EHOME_PLAYBACKINFO extends Structure {
		public int dwSize;
		public int dwChannel;           // 通道号
		public byte byStreamType;       // 码流类型
		public byte byRes;              // 保留
		public short wLinkPort;          // 链路端口
		public String sMultiCastIP;     // 多播IP
		public String sDeviceID;        // 设备ID
		public NET_EHOME_TIME startTime; // 开始时间
		public NET_EHOME_TIME stopTime;  // 结束时间
		public String sUser;            // 用户名
		public String sPassword;        // 密码
		public fPlaybackDataCallBack playbackCB; // 回放回调
		public Pointer pUser;           // 用户数据
	}

	// 下载参数
	class NET_EHOME_DOWNLOAD_INFO extends Structure {
		public int dwSize;
		public String sDeviceID;        // 设备ID
		public int dwChannel;           // 通道号
		public NET_EHOME_TIME startTime; // 开始时间
		public NET_EHOME_TIME stopTime; // 结束时间
		public byte byStreamType;       // 码流类型
		public byte byRes;              // 保留
		public byte[] byRes2 = new byte[2];
	}

	// 时间结构
	class NET_EHOME_TIME extends Structure {
		public int dwYear;              // 年
		public int dwMonth;             // 月
		public int dwDay;               // 日
		public int dwHour;              // 时
		public int dwMinute;            // 分
		public int dwSecond;            // 秒
		public int dwMilliSecond;       // 毫秒
		public byte[] szRes = new byte[8];
	}

	// 设备信息
	class NET_EHOME_DEVICE_INFO extends Structure {
		public int dwSize;
		public byte[] sSerialNumber = new byte[48]; // 序列号
		public int dwDeviceType;         // 设备类型
		public int dwChannelNumber;      // 通道数
		public int dwChannelAmount;      // 通道总量
		public int dwDiskNumber;         // 磁盘数
		public int dwAlarmInPortNum;     // 报警输入数
		public int dwAlarmOutAmount;     // 报警输出数
		public int dwStartChannel;       // 起始通道
		public int dwAudioChanNum;       // 音频通道数
		public int dwMaxDigitChannelNum; // 最大数字通道数
		public int dwSupportZeroChan;    // 支持零通道
		public int dwStartZeroChan;     // 零通道起始号
		public int dwSmartType;          // 智能类型
		public int dwAudioEncType;      // 音频编码类型
		public byte[] sSIMCardSN = new byte[32]; // SIM卡序列号
		public byte[] sSIMCardPhoneNum = new byte[16]; // SIM卡号码
		public byte[] byRes = new byte[256];
	}

	// EHOME配置
	class NET_EHOME_CONFIG extends Structure {
		public int dwSize;
		public Pointer pCondBuf;         // 条件缓冲区
		public int dwCondSize;           // 条件缓冲区大小
		public Pointer pInBuf;           // 输入缓冲区
		public int dwInSize;             // 输入缓冲区大小
		public Pointer pOutBuf;          // 输出缓冲区
		public int dwOutSize;            // 输出缓冲区大小
	}

	// 查找信息
	class NET_EHOME_FINDINFO extends Structure {
		public int dwSize;
		public int dwChannel;           // 通道号
		public NET_EHOME_TIME startTime; // 开始时间
		public NET_EHOME_TIME stopTime;  // 结束时间
		public byte byFileType;          // 文件类型
		public byte byNeedCard;          // 是否需要卡号
		public byte[] sCardNumber = new byte[32]; // 卡号
		public byte[] byRes = new byte[32];
	}

	// 下一个文件信息
	class NET_EHOME_NEXT_FILE_INFO extends Structure {
		public int dwSize;
		public String sFileName;         // 文件名
		public NET_EHOME_TIME struStartTime; // 开始时间
		public NET_EHOME_TIME struStopTime;  // 结束时间
		public int dwFileSize;           // 文件大小
		public byte byFileType;          // 文件类型
		public byte[] sCardNum = new byte[32]; // 卡号
		public byte[] byRes = new byte[32];
	}

	// 远程控制参数
	class NET_EHOME_REMOTE_CTRL_PARAM extends Structure {
		public int dwSize;
		public Pointer lpCondBuffer;      // 条件缓冲区
		public int dwCondBufferSize;       // 条件缓冲区大小
		public Pointer lpInbuffer;          // 输入缓冲区
		public int dwInBufferSize;          // 输入缓冲区大小
	}

	// PTZ参数
	class NET_EHOME_PTZ_PARAM extends Structure {
		public int dwSize;
		public byte byPTZCmd;              // PTZ命令
		public byte byAction;              // 动作：0-开始，1-停止
		public byte bySpeed;               // 速度
		public byte[] byRes = new byte[3]; // 保留
	}

	// 命令定义
	int NET_ECMS_DEVICECONFIG = 1;
	int NET_EHOME_PTZ_CTRL = 2001;
	int NET_EHOME_REBOOT_DEV = 2002;
	int NET_EHOME_GET_DEVICE_STATUS = 2003;
	int NET_EHOME_GET_CHANNEL_INFO = 2004;

	// 码流类型
	byte STREAM_TYPE_MAIN = 0;
	byte STREAM_TYPE_SUB = 1;

	// 连接模式
	byte LINK_MODE_TCP = 0;
	byte LINK_MODE_UDP = 1;

	// 文件类型
	byte FILE_TYPE_RECORD = 0;
	byte FILE_TYPE_CAPTURE = 1;

	// 回调接口
	interface fRealDataCallBack extends Callback {
		void invoke(int lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, Pointer pUser);
	}

	interface fPlaybackDataCallBack extends Callback {
		void invoke(int lPlaybackHandle, int dwDataType, Pointer pBuffer, int dwBufSize, Pointer pUser);
	}

	interface fMSGCallBack extends Callback {
		void invoke(int lUserID, int dwType, Pointer pData, int dwBufSize, Pointer pUser);
	}

	interface fUpgradeCallBack extends Callback {
		void invoke(int lUpgradeHandle, int dwProgress, Pointer pUser);
	}

	interface fSearchDeviceCallBack extends Callback {
		void invoke(String sDeviceID, String sDeviceIP, short wDevicePort, String sDeviceType, Pointer pUser);
	}
}
