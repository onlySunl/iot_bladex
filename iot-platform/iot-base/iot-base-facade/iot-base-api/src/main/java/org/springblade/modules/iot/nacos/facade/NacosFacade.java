package org.springblade.modules.iot.nacos.facade;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.nacos.vo.result.NacosInstanceResultVO;
import org.springblade.modules.iot.nacos.vo.result.NacosListViewResultVO;

import java.util.List;

/**
 *
 * @author tangyh
 * @since 2024/12/20 09:15
 */
public interface NacosFacade {
    R<List<NacosInstanceResultVO>> getAllInstances(String serviceName, String groupName);

    R<NacosListViewResultVO<String>> getServicesOfServer(int pageNo, int pageSize);
}
