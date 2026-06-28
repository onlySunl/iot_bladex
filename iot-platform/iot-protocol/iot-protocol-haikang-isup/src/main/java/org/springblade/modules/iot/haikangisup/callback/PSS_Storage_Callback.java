package org.springblade.modules.iot.haikangisup.callback;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.ss.HCISUPSS;
import com.sun.jna.Pointer;

/**
 * SS存储回调
 */
@Slf4j
public class PSS_Storage_Callback implements HCISUPSS.EHomeSSStorageCallBack {

	@Override
	public boolean invoke(int iHandle, String pFileName, Pointer pFileBuf, int dwFileLen, Pointer pFilePath, Pointer pUser) {
		log.info("========== 开始处理图片上传回调 ==========");
		log.info("iHandle: {}, pFileName: {}, dwFileLen: {}", iHandle, pFileName, dwFileLen);

		try {
			// 处理文件上传逻辑
			if (pFileBuf != null && dwFileLen > 0) {
				byte[] fileData = pFileBuf.getByteArray(0, dwFileLen);
				log.info("收到文件数据，大小: {} bytes", fileData.length);
			}
		} catch (Exception e) {
			log.error("处理文件上传异常", e);
		}

		log.info("========== 图片上传回调处理完成 ==========");
		return true;
	}
}
