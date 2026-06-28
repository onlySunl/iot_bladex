package org.springblade.modules.iot.haikangisup.service.stream;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface HCISUPStream extends Library {
	int NET_EHOME_SYSHEAD = 1;
	int NET_EHOME_STREAMDATA = 2;
	int NET_EHOME_STREAMEND = 3;
	int NET_EHOME_DEVICEID_LEN = 256;
	int NET_EHOME_SERIAL_LEN = 12;

	boolean NET_ESTREAM_Init();
	boolean NET_ESTREAM_Fini();
	int NET_ESTREAM_GetLastError();
	boolean NET_ESTREAM_SetSDKLocalCfg(int enumType, Pointer lpInBuff);
	boolean NET_ESTREAM_SetSDKInitCfg(int enumType, Pointer lpInBuff);

	class NET_EHOME_IPADDRESS extends Structure {
		public byte[] szIP = new byte[128];
		public short wPort;
		public byte[] byRes = new byte[2];
	}

	class NET_EHOME_NEWLINK_CB_MSG extends Structure {
		public byte[] szDeviceID = new byte[NET_EHOME_DEVICEID_LEN];
		public int iSessionID;
		public int dwChannelNo;
		public byte byStreamType;
		public byte[] byRes1 = new byte[3];
		public byte[] sDeviceSerial = new byte[NET_EHOME_SERIAL_LEN];
		public byte[] byRes = new byte[112];
	}

	class NET_EHOME_PREVIEW_CB_MSG extends Structure {
		public byte byDataType;
		public byte[] byRes1 = new byte[3];
		public Pointer pRecvdata;
		public int dwDataLen;
		public byte[] byRes2 = new byte[128];
	}

	class NET_EHOME_PLAYBACK_DATA_CB_INFO extends Structure {
		public int dwType;
		public Pointer pData;
		public int dwDataLen;
		public byte[] byRes = new byte[128];
	}

	class BYTE_ARRAY extends Structure {
		public byte[] byValue;
		public BYTE_ARRAY(int iLen) {
			byValue = new byte[iLen];
		}
	}

	interface PREVIEW_NEWLINK_CB extends com.sun.jna.Callback {
		boolean invoke(int lLinkHandle, NET_EHOME_NEWLINK_CB_MSG pNewLinkCBMsg, Pointer pUserData);
	}

	interface PREVIEW_DATA_CB extends com.sun.jna.Callback {
		void invoke(int iPreviewHandle, NET_EHOME_PREVIEW_CB_MSG pPreviewCBMsg, Pointer pUserData);
	}

	interface PLAYBACK_NEWLINK_CB extends com.sun.jna.Callback {
		boolean invoke(int lPlayBackLinkHandle, NET_EHOME_PLAYBACK_DATA_CB_INFO pNewLinkCBMsg, Pointer pUserData);
	}

	interface PLAYBACK_DATA_CB extends com.sun.jna.Callback {
		boolean invoke(int iPlayBackLinkHandle, NET_EHOME_PLAYBACK_DATA_CB_INFO pDataCBInfo, Pointer pUserData);
	}

	interface fExceptionCallBack extends com.sun.jna.Callback {
		void invoke(int dwType, int iUserID, int iHandle, Pointer pUser);
	}
}
