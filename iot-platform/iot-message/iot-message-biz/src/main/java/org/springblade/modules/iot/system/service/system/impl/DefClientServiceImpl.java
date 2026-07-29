package org.springblade.modules.iot.system.service.system.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperCacheServiceImpl;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.system.DefClient;
import org.springblade.modules.iot.system.manager.system.DefClientManager;
import org.springblade.modules.iot.system.service.system.DefClientService;
import org.springblade.modules.iot.system.vo.save.system.DefClientSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 客户端
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.DEFAULTS)
public class DefClientServiceImpl extends SuperCacheServiceImpl<DefClientManager, Long, DefClient>
        implements DefClientService {

    @Override
    protected <SaveVO> DefClient saveBefore(SaveVO saveVO) {
        DefClientSaveVO defClientSaveVO = (DefClientSaveVO) saveVO;
        DefClient defClient = super.saveBefore(defClientSaveVO);
        defClient.setClientId(RandomUtil.randomString(24));
        defClient.setClientSecret(RandomUtil.randomString(32));
        return defClient;
    }

    @Override
    public DefClient getClient(String clientId, String clientSecret) {
        return superManager.getClient(clientId, clientSecret);
    }
}
