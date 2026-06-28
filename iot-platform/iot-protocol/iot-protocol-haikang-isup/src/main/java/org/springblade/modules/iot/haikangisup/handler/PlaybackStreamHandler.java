package org.springblade.modules.iot.haikangisup.handler;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.stream.HCISUPStream;
import com.sun.jna.Pointer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 回放流处理器
 */
@Slf4j
public class PlaybackStreamHandler implements HCISUPStream.PLAYBACK_DATA_CB {
	private static final int MAX_PAYLOAD_SIZE = 1400 - 12;

	public static final Map<String, CountDownLatch> downloadLatchMap = new ConcurrentHashMap<>();

	private class RtpConnection {
		LinkedBlockingQueue<byte[]> frameQueue = new LinkedBlockingQueue<>();
		Thread sendThread;
		volatile boolean running = true;
	}

	public final Map<Integer, RtpConnection> connectionMap = new ConcurrentHashMap<>();

	@Override
	public boolean invoke(int iPlayBackLinkHandle, HCISUPStream.NET_EHOME_PLAYBACK_DATA_CB_INFO pDataCBInfo, Pointer pUserData) {
		if (pDataCBInfo.dwType == 2 && pDataCBInfo.pData != null && pDataCBInfo.dwDataLen > 0) {
			try {
				byte[] data = pDataCBInfo.pData.getByteArray(0, pDataCBInfo.dwDataLen);
				log.debug("收到回放数据，大小: {} bytes", data.length);
			} catch (Exception e) {
				log.error("处理回放数据异常", e);
			}
		} else if (pDataCBInfo.dwType == 3) {
			log.info("收到回放停止信令");
		}
		return true;
	}
}
