package org.springblade.modules.iot.haikangisup.handler;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.stream.HCISUPStream;
import com.sun.jna.Pointer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 预览流处理器
 */
@Slf4j
public class PreviewStreamHandler implements HCISUPStream.PREVIEW_DATA_CB {
	private int seqNum = 0;
	private int currentTimestamp = 0;
	private final int CLOCK_RATE = 90000;
	private final int FPS = 25;
	private final int TIMESTAMP_INCREMENT = CLOCK_RATE / FPS;

	private class RtpConnection {
		int seqNum = 0;
		int timestamp = 0;
		int rtpPort = 0;
		String ssrc;
		DatagramSocket udpSocket;
		InetAddress targetAddress;
	}

	public final Map<Integer, RtpConnection> connectionMap = new ConcurrentHashMap<>();

	@Override
	public void invoke(int iPreviewHandle, HCISUPStream.NET_EHOME_PREVIEW_CB_MSG pPreviewCBMsg, Pointer pUserData) throws IOException {
		byte[] dataStream = pPreviewCBMsg.pRecvdata.getByteArray(0, pPreviewCBMsg.dwDataLen);
		if (dataStream != null && dataStream.length > 0) {
			if (pPreviewCBMsg.byDataType == 2) {
				RtpConnection connection = connectionMap.get(iPreviewHandle);
				if (connection != null && connection.udpSocket != null) {
					int frameTimestamp = currentTimestamp;
					currentTimestamp += TIMESTAMP_INCREMENT;

					byte pt = 96;
					ByteBuffer buffer = ByteBuffer.allocate(4);
					buffer.order(ByteOrder.BIG_ENDIAN);
					buffer.putInt(Integer.parseInt(connection.ssrc));

					// 发送RTP包
					int packetSize = Math.min(dataStream.length, 1400 - 12);
					byte[] rtpPacket = new byte[12 + packetSize];
					rtpPacket[0] = (byte) 0x80;
					rtpPacket[1] = pt;
					rtpPacket[2] = (byte) ((seqNum >> 8) & 0xFF);
					rtpPacket[3] = (byte) (seqNum & 0xFF);
					rtpPacket[4] = (byte) ((frameTimestamp >> 24) & 0xFF);
					rtpPacket[5] = (byte) ((frameTimestamp >> 16) & 0xFF);
					rtpPacket[6] = (byte) ((frameTimestamp >> 8) & 0xFF);
					rtpPacket[7] = (byte) (frameTimestamp & 0xFF);
					System.arraycopy(dataStream, 0, rtpPacket, 12, packetSize);

					DatagramPacket packet = new DatagramPacket(rtpPacket, rtpPacket.length, connection.targetAddress, connection.rtpPort);
					connection.udpSocket.send(packet);
					seqNum++;
				}
			}
		}
	}
}
