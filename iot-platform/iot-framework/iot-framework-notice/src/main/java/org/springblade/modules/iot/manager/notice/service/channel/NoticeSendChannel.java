package org.springblade.modules.iot.manager.notice.service.channel;

import org.springblade.modules.iot.manager.notice.model.NoticeChannel;
import java.util.Map;

public interface NoticeSendChannel {

  boolean support(String type);

  NoticeSendResult send(
      String content, String receivers, NoticeChannel config, Map<String, Object> params);
}
