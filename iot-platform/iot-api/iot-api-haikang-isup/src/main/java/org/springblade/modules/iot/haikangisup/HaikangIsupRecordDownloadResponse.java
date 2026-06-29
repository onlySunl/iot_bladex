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
	 * 是否成功
	 */
	private Boolean success;

	/**
	 * 下载文件路径
	 */
	private String filePath;

	/**
	 * 下载文件URL
	 */
	private String fileUrl;

	/**
	 * 文件大小（字节）
	 */
	private Long fileSize;

	/**
	 * 错误信息
	 */
	private String errorMessage;

	/**
	 * 下载进度（0-100）
	 */
	private Integer progress;
}
