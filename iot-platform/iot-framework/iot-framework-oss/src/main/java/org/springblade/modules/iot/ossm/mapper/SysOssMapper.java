

package org.springblade.modules.iot.ossm.mapper;

import org.springblade.modules.iot.pojo.framework.entity.SysOss;
import org.springblade.modules.iot.persistence.common.BaseMapper;
import java.util.Collection;
import java.util.List;

/** 文件上传 数据层 @Author Lion Li */
public interface SysOssMapper extends BaseMapper<SysOss> {

  List<SysOss> listByIds(Collection<Long> ids);

  List<SysOss> listByUrls(String[] urls);

  int removeByIds(Collection<Long> ids);

  int removeByUrls(String[] ids);
}
