package org.springblade.modules.iot.system.service.tenant.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperCacheServiceImpl;
import com.mqttsnet.basic.context.ContextUtil;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.utils.ArgumentAssert;
import com.mqttsnet.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.common.constant.AppendixType;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.file.service.AppendixService;
import org.springblade.modules.iot.model.enumeration.system.DefTenantStatusEnum;
import org.springblade.modules.iot.model.enumeration.system.TenantConnectTypeEnum;
import org.springblade.modules.iot.model.vo.save.AppendixSaveVO;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.entity.tenant.DefTenantDatasourceConfigRel;
import org.springblade.modules.iot.system.entity.tenant.DefUser;
import org.springblade.modules.iot.system.enumeration.tenant.DefTenantRegisterTypeEnum;
import org.springblade.modules.iot.system.manager.application.DefTenantApplicationRelManager;
import org.springblade.modules.iot.system.manager.application.DefTenantResourceRelManager;
import org.springblade.modules.iot.system.manager.tenant.DefTenantDatasourceConfigRelManager;
import org.springblade.modules.iot.system.manager.tenant.DefTenantManager;
import org.springblade.modules.iot.system.service.tenant.DefTenantService;
import org.springblade.modules.iot.system.service.tenant.DefUserService;
import org.springblade.modules.iot.system.strategy.InitSystemContext;
import org.springblade.modules.iot.system.vo.result.tenant.DefTenantResultVO;
import org.springblade.modules.iot.system.vo.save.tenant.DefTenantInitVO;
import org.springblade.modules.iot.system.vo.save.tenant.DefTenantSaveVO;
import org.springblade.modules.iot.system.vo.update.tenant.DefTenantUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-13
 */
@Slf4j
@Service
@DS(DsConstant.DEFAULTS)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefTenantServiceImpl extends SuperCacheServiceImpl<DefTenantManager, Long, DefTenant> implements DefTenantService {
    private final InitSystemContext initSystemContext;
    private final DefUserService defUserService;
    private final AppendixService appendixService;
    private final DefTenantDatasourceConfigRelManager defTenantDatasourceConfigRelManager;
    private final DefTenantApplicationRelManager defTenantApplicationRelManager;
    private final DefTenantResourceRelManager defTenantResourceRelManager;


    @Override
    public boolean check(String tenantCode) {
        return superManager.count(Wraps.<DefTenant>lbQ().eq(DefTenant::getCode, tenantCode)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DefTenant register(DefTenantSaveVO defTenantSaveVO) {
        // 1， 保存租户 (默认库)
        DefTenant tenant = BeanPlusUtil.toBean(defTenantSaveVO, DefTenant.class);
        tenant.setStatus(DefTenantStatusEnum.WAITING.getCode());
        tenant.setRegisterType(DefTenantRegisterTypeEnum.REGISTER);
        tenant.setCode(RandomUtil.randomNumbers(8));
        tenant.setReadonly(false);
        DefUser result = defUserService.getByIdCache(ContextUtil.getUserId());
        if (result != null) {
            tenant.setCreatedName(result.getNickName());
        }

        superManager.save(tenant);
        appendixService.save(AppendixSaveVO.build(tenant.getId(), AppendixType.System.DEF__TENANT__LOGO, defTenantSaveVO.getLogo()));
        return tenant;
    }

    @Override
    protected <SaveVO> DefTenant saveBefore(SaveVO saveVO) {
        DefTenantSaveVO defTenantSaveVO = (DefTenantSaveVO) saveVO;
        // 1， 保存租户 (默认库)
        DefTenant tenant = BeanPlusUtil.toBean(defTenantSaveVO, DefTenant.class);
        tenant.setStatus(DefTenantStatusEnum.WAIT_INIT_SCHEMA.getCode());
        tenant.setRegisterType(DefTenantRegisterTypeEnum.CREATE);
        tenant.setReadonly(false);
        if (StrUtil.isEmpty(tenant.getCreatedName())) {
            DefUser result = defUserService.getByIdCache(ContextUtil.getUserId());
            if (result != null) {
                tenant.setCreatedName(result.getNickName());
            }
        }
        return tenant;
    }

    @Override
    protected <SaveVO> void saveAfter(SaveVO saveVO, DefTenant defTenant) {
        DefTenantSaveVO defTenantSaveVO = (DefTenantSaveVO) saveVO;
        appendixService.save(AppendixSaveVO.build(defTenant.getId(), AppendixType.System.DEF__TENANT__LOGO, defTenantSaveVO.getLogo()));

    }

    @Override
    protected <UpdateVO> void updateAfter(UpdateVO updateVO, DefTenant defTenant) {
        DefTenantUpdateVO defTenantUpdateVO = (DefTenantUpdateVO) updateVO;
        appendixService.save(AppendixSaveVO.build(defTenant.getId(), AppendixType.System.DEF__TENANT__LOGO, defTenantUpdateVO.getLogo()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean initData(DefTenantInitVO tenantConnect) {
        return initSystemContext.initData(tenantConnect) && updateTenantStatus(tenantConnect);
    }

    private Boolean updateTenantStatus(DefTenantInitVO initVO) {
        Boolean flag = superManager.update(Wraps.<DefTenant>lbU().set(DefTenant::getStatus, DefTenantStatusEnum.WAIT_INIT_DATASOURCE.getCode())
                .set(DefTenant::getConnectType, initVO.getConnectType())
                .eq(DefTenant::getId, initVO.getId()));
        superManager.delCache(initVO.getId());
        defTenantApplicationRelManager.grantGeneralApplication(initVO.getId());
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        appendixService.removeByBizId(ids, AppendixType.System.DEF__TENANT__LOGO);
        defTenantDatasourceConfigRelManager.remove(Wraps.<DefTenantDatasourceConfigRel>lbQ().in(DefTenantDatasourceConfigRel::getTenantId, ids));
        defTenantApplicationRelManager.deleteByTenantId(ids);
        defTenantResourceRelManager.deleteByTenantId(ids);
        return removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
        appendixService.removeByBizId(ids, AppendixType.System.DEF__TENANT__LOGO);
        removeByIds(ids);
        defTenantDatasourceConfigRelManager.remove(Wraps.<DefTenantDatasourceConfigRel>lbQ().in(DefTenantDatasourceConfigRel::getTenantId, ids));
        defTenantApplicationRelManager.deleteByTenantId(ids);
        defTenantResourceRelManager.deleteByTenantId(ids);
        return initSystemContext.delete(ids);
    }

    @Override
    public List<DefTenant> findNormalTenant() {
        return list(Wraps.<DefTenant>lbQ().eq(DefTenant::getStatus, DefTenantStatusEnum.NORMAL));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(Long id, String status, String reviewComments) {
        ArgumentAssert.notNull(id, "请传递正确的企业ID");
        ArgumentAssert.notNull(status, "请传递正确的状态值");

        if (DefTenantStatusEnum.AGREED.eq(status)) {
            DefTenantInitVO tenantConnect = new DefTenantInitVO();
            tenantConnect.setConnectType(TenantConnectTypeEnum.SYSTEM);
            tenantConnect.setId(id);
            initSystemContext.initData(tenantConnect);

            defTenantApplicationRelManager.grantGeneralApplication(id);
            status = DefTenantStatusEnum.WAIT_INIT_DATASOURCE.getCode();
        }

        boolean update = superManager.update(Wraps.<DefTenant>lbU().set(DefTenant::getStatus, status)
                .set(DefTenant::getReviewComments, reviewComments)
                .eq(DefTenant::getId, id));
        superManager.delCache(id);
        return update;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateState(Long id, Boolean state) {
        ArgumentAssert.notNull(id, "请选择正确的企业进行修改");
        ArgumentAssert.notNull(state, "请传递正确的状态值");
        // 演示环境专用标识，用于WriteInterceptor拦截器判断演示环境需要禁止用户执行sql，若您无需搭建演示环境，可以删除下面一行代码
        ContextUtil.setStop();
        boolean update = superManager.update(Wraps.<DefTenant>lbU().set(DefTenant::getState, state).eq(DefTenant::getId, id));
        superManager.delCache(id);
        return update;
    }

    @Override
    public List<DefTenantResultVO> listTenantByUserId(Long userId) {
        return superManager.listTenantByUserId(userId);
    }
}
