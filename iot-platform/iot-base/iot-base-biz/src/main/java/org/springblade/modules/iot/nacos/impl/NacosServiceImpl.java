package org.springblade.modules.iot.nacos.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.nacos.NacosService;
import org.springblade.modules.iot.nacos.vo.result.NacosInstanceResultVO;
import org.springblade.modules.iot.nacos.vo.result.NacosListViewResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * -----------------------------------------------------------------------------
 * File Name: NacosServiceImpl
 * -----------------------------------------------------------------------------
 * Description:
 * Nacos 服务API实现类
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/13       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/13 18:09
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class NacosServiceImpl implements NacosService {
    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;


    @Override
    public void registerInstance(String serviceName, String ip, int port) throws NacosException {
        nacosServiceManager.getNamingService().registerInstance(serviceName, ip, port);
    }

    @Override
    public List<NacosInstanceResultVO> getAllInstances(String serviceName, String groupName) throws NacosException {
        // 获取所有实例
        List<Instance> allInstances = nacosServiceManager.getNamingService().getAllInstances(serviceName, groupName);

        if (CollUtil.isEmpty(allInstances)) {
            return Collections.emptyList();
        }

        // 将 Instance 列表转换为 NacosInstanceResultVO 列表
        return allInstances.stream()
                .map(instance -> {
                    NacosInstanceResultVO resultVO = BeanPlusUtil.toBean(instance, NacosInstanceResultVO.class);

                    // 从元数据中提取时间相关的字段
                    resultVO.setInstanceHeartBeatInterval(instance.getInstanceHeartBeatInterval());
                    resultVO.setInstanceHeartBeatTimeOut(instance.getInstanceHeartBeatTimeOut());
                    resultVO.setIpDeleteTimeout(instance.getIpDeleteTimeout());

                    return resultVO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void batchRegisterInstance(String serviceName, String groupName, List<Instance> instances) throws NacosException {
        nacosServiceManager.getNamingService().batchRegisterInstance(serviceName, groupName, instances);
    }

    @Override
    public void deregisterInstance(String serviceName, String ip, int port) throws NacosException {
        nacosServiceManager.getNamingService().deregisterInstance(serviceName, ip, port);
    }

    @Override
    public NacosListViewResultVO<String> getServicesOfServer(int pageNo, int pageSize) throws NacosException {
        ListView<String> servicesOfServer = nacosServiceManager.getNamingService().getServicesOfServer(pageNo, pageSize);
        return BeanPlusUtil.toBeanIgnoreError(servicesOfServer, NacosListViewResultVO.class);
    }

}
