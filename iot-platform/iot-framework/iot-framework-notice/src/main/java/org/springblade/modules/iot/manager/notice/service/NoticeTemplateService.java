package org.springblade.modules.iot.manager.notice.service;

import org.springblade.modules.iot.manager.notice.dto.NoticeTemplateDTO;
import org.springblade.modules.iot.manager.notice.model.NoticeTemplate;
import java.util.List;

public interface NoticeTemplateService {

  List<NoticeTemplate> list();

  void save(NoticeTemplate template);

  void saveTemplate(NoticeTemplateDTO templateDTO, String currentUser);

  void delete(Long id);

  NoticeTemplate getById(Long id);

  List<NoticeTemplate> search(String name, String channelType, String status);

  void deleteBatch(List<Long> ids);

  void testTemplate(Long templateId, String receivers, Object params);

  void enableBatch(List<Long> ids);

  void disableBatch(List<Long> ids);
}
