package org.springblade.modules.iot.haikangisup.service.alarm;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface HCISUPAlarm extends Library {
	int NET_EALARM_Init();
	boolean NET_EALARM_Fini();
	int NET_EALARM_GetLastError();
	boolean NET_EALARM_SetSDKInitCfg(int iOption, Pointer pAgentInfo);
	boolean NET_EALARM_SetSDKLocalCfg(int iOption, Pointer pAgentInfo);
	int NET_EALARM_StartListen(NET_EHOME_ALARM_LISTEN_PARAM pAlarmListenParam);
	boolean NET_EALARM_StopListen(int iHandle);
	int NET_EALARM_GetDeviceStatus(String sDeviceID, NET_EHOME_ALARM_LISTEN_PARAM pInBuf, Pointer pOutBuf);

	class NET_EHOME_IPADDRESS extends Structure {
		public byte[] szIP = new byte[128];
		public short wPort;
		public byte[] byRes = new byte[2];
	}

	class BYTE_ARRAY extends Structure {
		public byte[] byValue;
		public BYTE_ARRAY(int iLen) {
			byValue = new byte[iLen];
		}
	}

	class NET_EHOME_ALARM_LISTEN_PARAM extends Structure {
		public NET_EHOME_IPADDRESS struAddress = new NET_EHOME_IPADDRESS();
		public ALARM_DATA_CB fnAlarmDataCB;
		public Pointer pUserData;
		public byte[] byRes = new byte[64];
	}

	interface ALARM_DATA_CB extends com.sun.jna.Callback {
		void invoke(int iHandle, NET_EHOME_ALARM_MSG pAlarmInfo, Pointer pUserData);
	}

	class NET_EHOME_ALARM_MSG extends Structure {
		public int dwAlarmType;
		public Pointer pAlarmInfo;
		public int dwAlarmInfoLen;
		public Pointer pXmlBuf;
		public int dwXmlBufLen;
		public byte[] sSerialNumber = new byte[12];
		public Pointer pHttpUrl;
		public int dwHttpUrlLen;
		public byte[] byRes = new byte[12];
	}
}
