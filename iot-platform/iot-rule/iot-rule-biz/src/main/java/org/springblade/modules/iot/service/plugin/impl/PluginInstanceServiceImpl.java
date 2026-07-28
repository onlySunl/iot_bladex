package org.springblade.modules.iot.service.plugin.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.basic.base.R;
import org.springblade.basic.base.service.impl.SuperServiceImpl;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.converter.Builder;
import org.springblade.basic.database.mybatis.conditions.Wraps;
import org.springblade.basic.exception.BizException;
import org.springblade.basic.utils.ArgumentAssert;
import org.springblade.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
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
 * 业务实现类
 * 插件实例信息表
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
public class PluginInstanceServiceImpl extends SuperServiceImpl<PluginInstanceManager, Long, PluginInstance> implements PluginInstanceService {

    private final NacosFacade nacosApi;


    @Override
    public PluginInstanceSaveVO savePluginInstance(PluginInstanceSaveVO saveVO) {
        log.info("savePluginInstance saveVO:{}", saveVO);
        validateSavePluginInstance(saveVO);

        // 构建插件实例实体
        PluginInstance pluginInstance = buildPluginInstanceSaveVO(saveVO);
        superManager.save(pluginInstance);

        return BeanPlusUtil.copyProperties(pluginInstance, PluginInstanceSaveVO.class);
    }

    @Override
    public PluginInstanceUpdateVO updatePluginInstance(PluginInstanceUpdateVO updateVO) {
        log.info("updatePluginInstance updateVO:{}", updateVO);
        validateUpdatePluginInstance(updateVO);

        // 构建插件实例实体（仅更新指定字段）
        Builder<PluginInstance> pluginInstanceBuilder = buildPluginInstanceUpdateVO(updateVO);
        superManager.updateById(pluginInstanceBuilder.build());

        return BeanPlusUtil.copyProperties(pluginInstanceBuilder.build(), PluginInstanceUpdateVO.class);
    }

    @Override
    public List<PluginNacosInstanceResultVO> getAvailableInstances() {
        R<List<NacosInstanceResultVO>> allInstances = nacosApi.getAllInstances(PluginConstants.PLUGIN_INSTANCE_APPLICATION_NAME, PluginConstants.PLUGIN_INSTANCE_GROUP_NAME);
        if (allInstances.getIsSuccess() && allInstances.getData() != null) {
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
        return BeanPlusUtil.toBeanList(superManager.getPluginInstanceList(query), PluginInstanceResultVO.class);
    }

    @Override
    public PluginInstanceResultVO getPluginInstanceResultVO(String instanceIdentification) {
        PluginInstance pluginInstance = superManager.findOneByInstanceIdentification(instanceIdentification);
        ArgumentAssert.notNull(pluginInstance, "The PluginInstance does not exist");
        return BeanPlusUtil.toBeanIgnoreError(pluginInstance, PluginInstanceResultVO.class);
    }


    @Override
    public PluginInstanceResultVO getPluginInstanceResultVOById(Long id) {
        PluginInstance pluginInstance = superManager.getById(id);
        ArgumentAssert.notNull(pluginInstance, "The PluginInstance does not exist");
        return BeanPlusUtil.toBeanIgnoreError(pluginInstance, PluginInstanceResultVO.class);
    }


    @Override
    public Boolean deletePluginInstance(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        PluginInstance pluginInstance = superManager.getById(id);
        if (pluginInstance == null) {
            throw BizException.wrap("The PluginInstance does not exist");
        }

        // 处理相关联的资源，例如移除插件等（如果有）

        // 删除插件实例
        return superManager.removeById(id);
    }


    private PluginInstance buildPluginInstanceSaveVO(PluginInstanceSaveVO saveVO) {
        // 构建插件实例实体
        return BeanPlusUtil.copyProperties(saveVO, PluginInstance.class);
    }

    private Builder<PluginInstance> buildPluginInstanceUpdateVO(PluginInstanceUpdateVO updateVO) {
        return Builder.of(PluginInstance::new)
                .with(PluginInstance::setId, updateVO.getId())
                .with(PluginInstance::setInstanceName, updateVO.getInstanceName())
                .with(PluginInstance::setApplicationName, updateVO.getApplicationName())
                .with(PluginInstance::setMachineIp, updateVO.getMachineIp())
                .with(PluginInstance::setPortRangeStart, updateVO.getPortRangeStart())
                .with(PluginInstance::setPortRangeEnd, updateVO.getPortRangeEnd())
                .with(PluginInstance::setExtendParams, updateVO.getExtendParams())
                .with(PluginInstance::setRemark, updateVO.getRemark())
                .with(PluginInstance::setWeight, updateVO.getWeight())
                .with(PluginInstance::setHealthy, updateVO.getHealthy())
                .with(PluginInstance::setEnabled, updateVO.getEnabled())
                .with(PluginInstance::setEphemeral, updateVO.getEphemeral())
                .with(PluginInstance::setClusterName, updateVO.getClusterName())
                .with(PluginInstance::setHeartBeatInterval, updateVO.getHeartBeatInterval())
                .with(PluginInstance::setHeartBeatTimeOut, updateVO.getHeartBeatTimeOut())
                .with(PluginInstance::setIpDeleteTimeOut, updateVO.getIpDeleteTimeOut())
                .with(PluginInstance::setMachinePort, updateVO.getMachinePort())
                .with(PluginInstance::setCreatedOrgId, ContextUtil.getCurrentDeptId());
    }

    private void validateSavePluginInstance(PluginInstanceSaveVO saveVO) {
        // 校验字段
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

        //校验 插件实例标识是否唯一
        PluginInstance existingInstance = superManager.getOne(Wraps.<PluginInstance>lbQ().eq(PluginInstance::getInstanceIdentification, saveVO.getInstanceIdentification()));
        if (Objects.nonNull(existingInstance)) {
            throw BizException.validFail("该实例已存在!");
        }
    }

    private void validateUpdatePluginInstance(PluginInstanceUpdateVO updateVO) {
        // 校验ID
        ArgumentAssert.notNull(updateVO.getId(), "id cannot be null");
        // 校验其他字段类似
    }

}


