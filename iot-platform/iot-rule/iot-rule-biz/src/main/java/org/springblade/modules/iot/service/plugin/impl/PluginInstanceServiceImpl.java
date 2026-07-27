package org.springblade.modules.iot.service.plugin.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.tool.api.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.constant.DsConstant;
import org.springblade.modules.iot.constants.PluginConstants;
import org.springblade.modules.iot.entity.plugin.PluginInstance;
import org.springblade.modules.iot.manager.plugin.PluginInstanceManager;
import org.springblade.modules.iot.nacos.facade.NacosFacade;
import org.springblade.modules.iot.nacos.vo.result.NacosInstanceResultVO;
import org.springblade.modules.iot.service.plugin.PluginInstanceService;
import org.springblade.modules.iot.vo.query.plugin.PluginInstancePageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInstanceResultVO;
import org.springblade.modules.iot.vo.result.plugin.PluginNacosInstanceResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInstanceSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInstanceUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * ?????????????????
 * ???????????????????????
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 * @create [2024-08-27 16:02:17] [mqttsnet]
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginInstanceServiceImpl extends BladeServiceImpl<PluginInstanceManager, PluginInstance> implements PluginInstanceService {

    private final NacosFacade nacosApi;


    @Override
    public PluginInstanceSaveVO savePluginInstance(PluginInstanceSaveVO saveVO) {
        log.info("savePluginInstance saveVO:{}", saveVO);
        validateSavePluginInstance(saveVO);

        // ????????????????????????????
        PluginInstance pluginInstance = buildPluginInstanceSaveVO(saveVO);
        baseMapper.save(pluginInstance);

        return BeanPlusUtil.copyProperties(pluginInstance, PluginInstanceSaveVO.class);
    }

    @Override
    public PluginInstanceUpdateVO updatePluginInstance(PluginInstanceUpdateVO updateVO) {
        log.info("updatePluginInstance updateVO:{}", updateVO);
        validateUpdatePluginInstance(updateVO);

        // ?????????????????????????????????????????????????????????
        PluginInstance pluginInstanceBuilder = buildPluginInstanceUpdateVO(updateVO);
        baseMapper.updateById(pluginInstanceBuilder);

        return BeanPlusUtil.copyProperties(pluginInstanceBuilder, PluginInstanceUpdateVO.class);
    }

    @Override
    public List<PluginNacosInstanceResultVO> getAvailableInstances() {
        R<List<NacosInstanceResultVO>> allInstances = nacosApi.getAllInstances(PluginConstants.PLUGIN_INSTANCE_APPLICATION_NAME, PluginConstants.PLUGIN_INSTANCE_GROUP_NAME);
        if (R.isSuccess(allInstances) && allInstances.getData() != null) {
            List<NacosInstanceResultVO> instances = allInstances.getData();
            return instances.stream()
                    .map(instance -> {
                        PluginNacosInstanceResultVO pluginNacosInstanceResultVO = BeanPlusUtil.toBeanIgnoreError(instance, PluginNacosInstanceResultVO.class);
                        pluginNacosInstanceResultVO.setApplicationName(PluginConstants.PLUGIN_INSTANCE_APPLICATION_NAME);
                        return pluginNacosInstanceResultVO;
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<PluginInstanceResultVO> getPluginInstanceResultVOList(PluginInstancePageQuery query) {
        return BeanPlusUtil.toBeanList(baseMapper.getPluginInstanceList(query), PluginInstanceResultVO.class);
    }

    @Override
    public PluginInstanceResultVO getPluginInstanceResultVO(String instanceIdentification) {
        PluginInstance pluginInstance = baseMapper.findOneByInstanceIdentification(instanceIdentification);
        ArgumentAssert.notNull(pluginInstance, "The PluginInstance does not exist");
        return BeanPlusUtil.toBeanIgnoreError(pluginInstance, PluginInstanceResultVO.class);
    }


    @Override
    public PluginInstanceResultVO getPluginInstanceResultVOById(Long id) {
        PluginInstance pluginInstance = baseMapper.getById(id);
        ArgumentAssert.notNull(pluginInstance, "The PluginInstance does not exist");
        return BeanPlusUtil.toBeanIgnoreError(pluginInstance, PluginInstanceResultVO.class);
    }


    @Override
    public Boolean deletePluginInstance(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        PluginInstance pluginInstance = baseMapper.getById(id);
        if (pluginInstance == null) {
            throw new ServiceException("The PluginInstance does not exist");
        }

        // ?????????????????????????????????????????????????????????????????????

        // ?????????????????????
        return baseMapper.removeById(id);
    }


    private PluginInstance buildPluginInstanceSaveVO(PluginInstanceSaveVO saveVO) {
        // ????????????????????????????
        return BeanPlusUtil.copyProperties(saveVO, PluginInstance.class);
    }

    private PluginInstance buildPluginInstanceUpdateVO(PluginInstanceUpdateVO updateVO) {
        PluginInstance result = new PluginInstance();
        result.setId(updateVO.getId());
        result.setInstanceName(updateVO.getInstanceName());
        result.setApplicationName(updateVO.getApplicationName());
        result.setMachineIp(updateVO.getMachineIp());
        result.setPortRangeStart(updateVO.getPortRangeStart());
        result.setPortRangeEnd(updateVO.getPortRangeEnd());
        result.setExtendParams(updateVO.getExtendParams());
        result.setRemark(updateVO.getRemark());
        result.setWeight(updateVO.getWeight());
        result.setHealthy(updateVO.getHealthy());
        result.setEnabled(updateVO.getEnabled());
        result.setEphemeral(updateVO.getEphemeral());
        result.setClusterName(updateVO.getClusterName());
        result.setHeartBeatInterval(updateVO.getHeartBeatInterval());
        result.setHeartBeatTimeOut(updateVO.getHeartBeatTimeOut());
        result.setIpDeleteTimeOut(updateVO.getIpDeleteTimeOut());
        result.setMachinePort(updateVO.getMachinePort());
        result.setCreatedOrgId(ContextUtil.getCurrentDeptId());
        return result;
    }

    private void validateSavePluginInstance(PluginInstanceSaveVO saveVO) {
        // ??????????????
        ArgumentAssert.notNull(saveVO.getInstanceIdentification(), "instanceIdentification cannot be null");
        ArgumentAssert.notNull(saveVO.getInstanceName(), "instanceName cannot be null");
        ArgumentAssert.notNull(saveVO.getApplicationName(), "applicationName cannot be null");
        ArgumentAssert.notNull(saveVO.getMachineIp(), "machineIp cannot be null");
        ArgumentAssert.notNull(saveVO.getPortRangeStart(), "portRangeStart cannot be null");
        ArgumentAssert.notNull(saveVO.getPortRangeEnd(), "portRangeEnd cannot be null");
        ArgumentAssert.notNull(saveVO.getWeight(), "weight cannot be null");
        ArgumentAssert.notNull(saveVO.getHealthy(), "healthy cannot be null");
        ArgumentAssert.notNull(saveVO.getEnabled(), "enabled cannot be null");
        ArgumentAssert.notNull(saveVO.getEphemeral(), "ephemeral cannot be null");
        ArgumentAssert.notNull(saveVO.getClusterName(), "clusterName cannot be null");

        //??????????????????????????????????????????
        PluginInstance existingInstance = baseMapper.getOne(new LambdaQueryWrapper<PluginInstance>().eq(PluginInstance::getInstanceIdentification, saveVO.getInstanceIdentification()));
        if (Objects.nonNull(existingInstance)) {
            throw ServiceException.validFail("??????????n?????????");
        }
    }

    private void validateUpdatePluginInstance(PluginInstanceUpdateVO updateVO) {
        // ????????
        ArgumentAssert.notNull(updateVO.getId(), "id cannot be null");
        // ???????????????????????????
    }

}


