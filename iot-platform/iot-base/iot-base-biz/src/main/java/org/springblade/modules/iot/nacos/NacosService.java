package org.springblade.modules.iot.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springblade.modules.iot.nacos.vo.result.NacosInstanceResultVO;
import org.springblade.modules.iot.nacos.vo.result.NacosListViewResultVO;

import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: NacosService
 * -----------------------------------------------------------------------------
 * Description:
 * Naocs Service
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
public interface NacosService {

    /**
     * 注册服务实例
     *
     * @param serviceName 服务名称
     * @param ip          实例的 IP 地址
     * @param port        实例的端口
     * @throws NacosException 如果出现 Nacos 相关异常
     */
    void registerInstance(String serviceName, String ip, int port) throws NacosException;

    /**
     * 获取服务的所有实例
     *
     * @param serviceName 服务名称
     * @param groupName   服务分组名称
     * @return {@link List<NacosInstanceResultVO>} 服务实例列表
     * @throws NacosException 如果出现 Nacos 相关异常
     */
    List<NacosInstanceResultVO> getAllInstances(String serviceName, String groupName) throws NacosException;

    /**
     * 批量注册多个服务实例
     *
     * @param serviceName 服务名称
     * @param groupName   服务分组名称
     * @param instances   实例列表
     * @throws NacosException 如果出现 Nacos 相关异常
     */
    void batchRegisterInstance(String serviceName, String groupName, List<Instance> instances) throws NacosException;

    /**
     * 注销服务实例
     *
     * @param serviceName 服务名称
     * @param ip          实例 IP 地址
     * @param port        实例端口
     * @throws NacosException 如果出现 Nacos 相关异常
     */
    void deregisterInstance(String serviceName, String ip, int port) throws NacosException;

    /**
     * 获取所有服务名称
     *
     * @param pageNo   页码
     * @param pageSize 每页大小
     * @return {@link NacosListViewResultVO} 服务名称列表
     * @throws NacosException 如果出现 Nacos 相关异常
     */
    NacosListViewResultVO<String> getServicesOfServer(int pageNo, int pageSize) throws NacosException;


}
