package org.springblade.modules.iot.manager.notice.service;

import org.springblade.modules.iot.manager.notice.model.NoticeSendRequest;
import org.springblade.modules.iot.manager.notice.service.channel.NoticeSendResult;

public interface NoticeService {

  void send(NoticeSendRequest req);

  default NoticeSendResult sendR(NoticeSendRequest request) {
    return null;
  }
}
