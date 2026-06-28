package org.springblade.modules.iot.haikangisup.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.stream.HCISUPStream;
import org.springblade.modules.iot.haikangisup.service.stream.StreamService;
import org.springblade.modules.iot.haikangisup.handler.PreviewStreamHandler;
import org.springblade.modules.iot.haikangisup.manager.StreamManager;
import com.sun.jna.Pointer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实时预览数据回调
 */
@Slf4j
@RequiredArgsConstructor
public class FPREVIEW_NEWLINK_CB_FILE implements HCISUPStream.PREVIEW_NEWLINK_CB {

	private final Map<Integer, PreviewStreamHandler> handlerMap = new ConcurrentHashMap<>();

	@Override
	public boolean invoke(int lPreviewHandle, HCISUPStream.NET_EHOME_NEWLINK_CB_MSG pNewLinkCBMsg, Pointer pUserData) throws IOException {
		log.info("预览数据回调参数 lPreviewHandle: {}", lPreviewHandle);

		int iSessionID = pNewLinkCBMsg.iSessionID;
		StreamManager.previewHandSAndSessionIDandMap.put(lPreviewHandle, iSessionID);
		StreamManager.sessionIDAndPreviewHandleMap.put(iSessionID, lPreviewHandle);
		log.info("pNewLinkCBMsg.iSessionID是和预览的时候的sessionID一致的 这个应该是全局唯一 通过sessionID可以确定是哪个摄像头 iSessionID: {}", iSessionID);

		PreviewStreamHandler previewStreamHandler = handlerMap.computeIfAbsent(lPreviewHandle, handle -> {
			log.info("创建新的PreviewStreamHandler实例，句柄: {}", handle);
			PreviewStreamHandler preview = new PreviewStreamHandler();
			StreamManager.sessionIDAndPreviewStreamHandlerMap.put(iSessionID, preview);
			return preview;
		});

		HCISUPStream.NET_EHOME_PREVIEW_DATA_CB_PARAM struDataCB = new HCISUPStream.NET_EHOME_PREVIEW_DATA_CB_PARAM();
		struDataCB.fnPreviewDataCB = previewStreamHandler;

		if (!StreamService.hCEhomeStream.NET_ESTREAM_SetPreviewDataCB(lPreviewHandle, struDataCB)) {
			log.info("NET_ESTREAM_SetPreviewDataCB 失败 err: {}", StreamService.hCEhomeStream.NET_ESTREAM_GetLastError());
			return false;
		}
		return true;
	}
}
