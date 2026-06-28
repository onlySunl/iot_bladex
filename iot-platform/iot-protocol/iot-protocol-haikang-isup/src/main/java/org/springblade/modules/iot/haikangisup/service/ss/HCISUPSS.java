package org.springblade.modules.iot.haikangisup.service.ss;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface HCISUPSS extends Library {
	int MAX_URL_LEN_SS = 4096;
	int MAX_KMS_USER_LEN = 512;
	int MAX_KMS_PWD_LEN = 512;
	int NET_EHOME_SERIAL_LEN = 12;

	boolean NET_ESS_Init();
	boolean NET_ESS_Fini();
	int NET_ESS_GetLastError();
	boolean NET_ESS_SetSDKInitCfg(int iOption, Pointer pAgentInfo);
	int NET_ESS_StartListen(NET_EHOME_SS_LISTEN_PARAM pSSListenParam);
	boolean NET_ESS_StopListen(int iHandle);

	class NET_EHOME_IPADDRESS extends Structure {
		public byte[] szIP = new byte[128];
		public short wPort;
		public byte[] byRes = new byte[2];
	}

	class NET_EHOME_SS_LISTEN_PARAM extends Structure {
		public NET_EHOME_IPADDRESS struAddress = new NET_EHOME_IPADDRESS();
		public byte[] szKMS_UserName = new byte[MAX_KMS_USER_LEN];
		public byte[] szKMS_Password = new byte[MAX_KMS_PWD_LEN];
		public EHomeSSStorageCallBack fnSStorageCb;
		public EHomeSSMsgCallBack fnSSMsgCb;
		public Pointer pUserData;
		public byte[] byRes = new byte[64];
	}

	interface EHomeSSMsgCallBack extends com.sun.jna.Callback {
		boolean invoke(int iHandle, int enumType, Pointer pOutBuffer, int dwOutLen, Pointer pInBuffer, int dwInLen, Pointer pUser);
	}

	interface EHomeSSStorageCallBack extends com.sun.jna.Callback {
		boolean invoke(int iHandle, String pFileName, Pointer pFileBuf, int dwOutLen, Pointer pFilePath, Pointer pUser);
	}
}
