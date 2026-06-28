package org.springblade.modules.iot.haikangisup.service.cms;

import lombok.extern.slf4j.Slf4j;

/**
 * CMS服务
 */
@Slf4j
public class CmsService {

	public static HCISUPCMS hCEhomeCMS;

	static {
		try {
			hCEhomeCMS = HCISUPCMS.INSTANCE;
			int iRet = hCEhomeCMS.NET_ECMS_Init();
			if (iRet != 0) {
				log.error("CMS初始化失败，返回值：{}", iRet);
			} else {
				log.info("CMS初始化成功");
			}
		} catch (Exception e) {
			log.error("加载CMS SDK失败：{}", e.getMessage());
		}
	}

	/**
	 * 清理资源
	 */
	public static void cleanup() {
		if (hCEhomeCMS != null) {
			try {
				hCEhomeCMS.NET_ECMS_Cleanup();
				log.info("CMS清理完成");
			} catch (Exception e) {
				log.error("CMS清理失败：{}", e.getMessage());
			}
		}
	}
}
