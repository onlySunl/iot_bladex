package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP录像下载响应
 *
 * @author fengcheng
 */
@Data
public class HaikangIsupRecordDownloadResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 下载ID
	 */
	private Long downloadId;

	/**
	 * 下载状态：0-成功，1-失败
	 */
	private int status;

	/**
	 * 下载进度 (0-100)
	 */
	private int progress;

	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 保存的文件路径
	 */
	private String filePath;
}
