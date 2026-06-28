package org.springblade.modules.iot.haikangisup.service.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 流服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StreamService {
	public static HCISUPStream hcEHomeStream = null;

	public void eStream_Init() {
		log.info("流服务初始化");
	}

	public void eStream_Fini() {
		if (hcEHomeStream != null) {
			hcEHomeStream.NET_ESTREAM_Fini();
		}
	}
}
