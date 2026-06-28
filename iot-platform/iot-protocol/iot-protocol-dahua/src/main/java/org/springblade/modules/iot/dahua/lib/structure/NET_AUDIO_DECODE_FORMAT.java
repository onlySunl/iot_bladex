package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 音频解码格式信息	
 * @date 2021/01/06
 */
public class NET_AUDIO_DECODE_FORMAT extends SdkStructure {
	/**
	 * 音频编码格式   NET_EM_AUDIO_FORMAT
	 * */
    public int              emCompression;
	/**
	 * 音频采样频率：8K ~ 192K
	 * */
    public int              nFrequency;
	/**
	 * 音频采样深度：8,16,24
	 * */
    public int              nDepth;
	/**
	 * 音频打包周期, [10, 250], ms
	 * */
    public int              nPacketPeriod;
	/**
	 * 保留字段
	 * */
    public byte[]           szReserved = new byte[512];
}

