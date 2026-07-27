package org.springblade.modules.iot.ota.service.impl;

import java.util.Collections;
import org.springblade.core.log.exception.ServiceException;
import java.util.List;
import org.springblade.core.log.exception.ServiceException;
import java.util.Objects;
import org.springblade.core.log.exception.ServiceException;
import java.util.Optional;
import org.springblade.core.log.exception.ServiceException;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.log.exception.ServiceException;
import cn.hutool.core.util.StrUtil;
import org.springblade.core.log.exception.ServiceException;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Query;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.log.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.common.utils.BeanUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaPackageSignMethodEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaPackageStatusEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.enumeration.OtaPackageTypeEnum;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.manager.OtaUpgradeTasksManager;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.manager.OtaUpgradesManager;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.service.OtaUpgradesService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesDetailsResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesResultVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradesSaveVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradesUpdateVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.core.log.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springblade.core.log.exception.ServiceException;

/**
 * <p>
 * 业务实现类
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 * @create [2024-01-12 22:36:27] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
public class OtaUpgradesServiceImpl extends BaseServiceImpl<OtaUpgradesMapper, OtaUpgrades> implements OtaUpgradesService {

    private final OtaUpgradeTasksManager otaUpgradeTasksManager;
    /**
     * 写前置校验保留直调 ── 升级包保存 / 更新校验产品存在,必须 DB-fresh。
     */
    private final ProductQueryService productQueryService;
    /**
     * 详情展示读路径走缓存,read-through DB 兜底。
     */
    private final LinkCacheDataHelper linkCacheDataHelper;

    /**
     * Save OTA Upgrade Package
     *
     * @param saveVO 保存参数
     * @return {@link OtaUpgradesSaveVO} 返回结果
     */
    @Override
    public OtaUpgradesSaveVO saveUpgradePackage(OtaUpgradesSaveVO saveVO) {
        log.info("saveUpgradePackage saveVO: {}", saveVO);

        // Validate the saveVO object
        validateOtaUpgradesSaveVO(saveVO);

        // Map the saveVO to your OtaUpgrade entity
        OtaUpgrades otaUpgrade = buildOtaUpgradeFromSaveVO(saveVO);

        // Persist the OtaUpgrade entity using your manager or repository
        superManager.save(otaUpgrade);

        // Map the saved entity back to OtaUpgradesSaveVO if needed
        return BeanUtil.toBeanIgnoreError(otaUpgrade, OtaUpgradesSaveVO.class);
    }

    /**
     * Update OTA Upgrade Package
     *
     * @param updateVO 更新参数
     * @return {@link OtaUpgradesUpdateVO} 返回结果
     */
    @Override
    public OtaUpgradesUpdateVO updateUpgradePackage(OtaUpgradesUpdateVO updateVO) {
        log.info("Updating OTA upgrade package: {}", updateVO);

        // Validate the updateVO object
        validateOtaUpgradesUpdateVO(updateVO);

        //构建参数
        Builder<OtaUpgrades> otaUpgradesBuilder = builderOtaUpgradesUpdateVO(updateVO);

        // Save the updated entity
        superManager.updateById(otaUpgradesBuilder.with(OtaUpgrades::setId, updateVO.getId()).build());

        // Map the updated entity back to OtaUpgradesUpdateVO if needed
        return BeanUtil.toBeanIgnoreError(otaUpgradesBuilder.build(), OtaUpgradesUpdateVO.class);

    }

    /**
     * Update OTA Upgrade Package Status
     *
     * @param id     主键
     * @param status 状态
     * @return {@link Boolean} 返回结果
     */
    @Override
    public Boolean updateOtaUpgradeStatus(Long id, Integer status) {
        ArgumentAssert.notNull(id, "Package ID cannot be null");
        ArgumentAssert.notNull(status, "Status cannot be null");

        // Here you should define your OtaUpgrade entity class which represents your OTA upgrade package
        OtaUpgrades otaUpgrades = superManager.getById(id);
        if (Objects.isNull(otaUpgrades)) {
            throw new ServiceException("OTA upgrade package does not exist");
        }
        if (status.equals(otaUpgrades.getStatus())) {
            throw new ServiceException("The OTA upgrade package status is the same as the current status");
        }

        otaUpgrades.setStatus(status);
        return superManager.updateById(otaUpgrades);
    }

    /**
     * Delete OTA Upgrade Package
     *
     * @param id 主键
     * @return {@link Boolean} 返回结果
     */
    @Override
    public Boolean deleteOtaUpgrade(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        OtaUpgrades otaUpgrade = superManager.getById(id);
        if (Objects.isNull(otaUpgrade)) {
            throw new ServiceException("OTA upgrade package does not exist");
        }

        Query params = new Query<>();
        params.setModel(new OtaUpgradeTasksPageQuery().setUpgradeId(id));
        if (otaUpgradeTasksManager.getOtaUpgradeTasksPage(params).getTotal() > 0) {
            throw new ServiceException("OTA upgrade package is in use and cannot be deleted");
        }
        // Additional checks can be added here if necessary
        return superManager.removeById(id);
    }

    /**
     * Converts OTA upgrades entities to view objects based on specified criteria.
     *
     * @param query The {@link OtaUpgradesPageQuery} object containing the search criteria.
     * @return {@link List<OtaUpgradesResultDTO>} A list of OTA upgrade records that match the given query criteria.
     */
    @Override
    public List<OtaUpgradesResultDTO> getOtaUpgradesResultDTOList(OtaUpgradesPageQuery query) {
        List<OtaUpgrades> otaUpgradesList = superManager.getOtaUpgradesList(query);
        return BeanUtil.toBeanList(otaUpgradesList, OtaUpgradesResultDTO.class);
    }

    @Override
    public Optional<OtaUpgradesResultDTO> getByIdOptional(Long id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        OtaUpgrades otaUpgrades = superManager.getById(id);
        return Optional.of(BeanUtil.toBeanIgnoreError(otaUpgrades, OtaUpgradesResultDTO.class));
    }

    @Override
    public OtaUpgradesDetailsResultVO getUpgradePackageDetails(Long id) {
        ArgumentAssert.notNull(id, "Upgrade package ID cannot be null");
        OtaUpgrades otaUpgrades = superManager.getById(id);
        ArgumentAssert.notNull(otaUpgrades, "OTA upgrade package does not exist");
        OtaUpgradesDetailsResultVO detailsVO = BeanUtil.toBeanIgnoreError(otaUpgrades, OtaUpgradesDetailsResultVO.class);
        // 详情读路径走缓存(read-through 兜底),避免每次详情请求都直查 product 表
        ProductResultVO productResultVO = linkCacheDataHelper
                .getProductCacheVO(otaUpgrades.getProductIdentification())
                .map(p -> BeanUtil.toBeanIgnoreError(p, ProductResultVO.class))
                .orElse(null);
        detailsVO.setProductResult(productResultVO);
        return detailsVO;
    }

    private void validateOtaUpgradesSaveVO(OtaUpgradesSaveVO saveVO) {
        OtaPackageTypeEnum.fromValue(saveVO.getPackageType()).orElseThrow(() -> new ServiceException("Invalid package type"));

        OtaPackageSignMethodEnum.fromValue(saveVO.getSignMethod()).orElseThrow(() -> new ServiceException("Invalid sign method"));

        OtaPackageStatusEnum.fromValue(saveVO.getStatus()).orElseThrow(() -> new ServiceException("Invalid status"));

        if (!VersionValidator.isValidVersion(saveVO.getVersion())) {
            throw new ServiceException("无效版本号");
        }

        // 校验升级包版本号是否重复
        if (superManager.count(Wrappers.<OtaUpgrades>lbQ().eq(OtaUpgrades::getPackageType, saveVO.getPackageType()).eq(OtaUpgrades::getVersion, saveVO.getVersion())) > 0) {
            throw new ServiceException("升级版本号已存在");
        }
    }

    private OtaUpgrades buildOtaUpgradeFromSaveVO(OtaUpgradesSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, OtaUpgrades.class);
    }

    private void validateOtaUpgradesUpdateVO(OtaUpgradesUpdateVO updateVO) {

        OtaUpgrades existingOtaUpgrade = Optional.ofNullable(superManager.getById(updateVO.getId())).orElseThrow(() -> new ServiceException("OTA upgrade package not found"));

        //TODO Validate the updateVO object
        String productIdentification = existingOtaUpgrade.getProductIdentification();

        OtaPackageTypeEnum.fromValue(updateVO.getPackageType()).orElseThrow(() -> new ServiceException("Invalid package type"));

        OtaPackageSignMethodEnum.fromValue(updateVO.getSignMethod()).orElseThrow(() -> new ServiceException("Invalid sign method"));

        OtaPackageStatusEnum.fromValue(updateVO.getStatus()).orElseThrow(() -> new ServiceException("Invalid status"));

        // 校验升级包版本号是否合法
        if (!VersionValidator.isValidVersion(updateVO.getVersion())) {
            throw new ServiceException("无效版本号");
        }

        // 校验升级包版本号是否重复
        if (superManager.count(Wrappers.<OtaUpgrades>lbQ()
                .eq(OtaUpgrades::getPackageType, updateVO.getPackageType())
                .eq(OtaUpgrades::getVersion, updateVO.getVersion())
                .ne(OtaUpgrades::getId, updateVO.getId())) > 0) {
            throw new ServiceException("升级版本号已存在");
        }
    }

    private Builder<OtaUpgrades> builderOtaUpgradesUpdateVO(OtaUpgradesUpdateVO updateVO) {
        return Builder.of(OtaUpgrades::new)
                .with(OtaUpgrades::setAppId, updateVO.getAppId())
                .with(OtaUpgrades::setPackageName, updateVO.getPackageName())
                .with(OtaUpgrades::setPackageType, updateVO.getPackageType())
                .with(OtaUpgrades::setProductIdentification, updateVO.getProductIdentification())
                .with(OtaUpgrades::setVersion, updateVO.getVersion())
                .with(OtaUpgrades::setProductVersionNo, updateVO.getProductVersionNo())
                .with(OtaUpgrades::setFileLocation, updateVO.getFileLocation())
                .with(OtaUpgrades::setSignMethod, updateVO.getSignMethod())
                .with(OtaUpgrades::setStatus, updateVO.getStatus())
                .with(OtaUpgrades::setDescription, updateVO.getDescription())
                .with(OtaUpgrades::setCustomInfo, updateVO.getCustomInfo())
                .with(OtaUpgrades::setRemark, updateVO.getRemark())
                .with(OtaUpgrades::setCreatedOrgId, AuthUtil.getCurrentDeptId());
    }

    /**
     * 根据ID集合查询升级包信息
     *
     * @param ids 升级包ID集合
     * @return {@link List<OtaUpgradesResultVO>} 升级包信息列表
     */
    @Override
    public List<OtaUpgradesResultVO> selectListByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<OtaUpgrades> upgrades = superManager.listByIds(ids);
        return Optional.ofNullable(upgrades)
                .map(upgradeList -> BeanUtil.toBeanList(upgradeList, OtaUpgradesResultVO.class))
                .orElse(Collections.emptyList());
    }

    @Override
    public String resolveProductVersionNo(String productIdentification, String version, Integer packageType) {
        if (StrUtil.isBlank(productIdentification) || StrUtil.isBlank(version)) {
            return null;
        }
        // 同一(产品 + 版本)理论上唯一(saveUpgradePackage 已按 packageType + version 去重),取最新一条兜底多匹配
        return superManager.list(Wrappers.<OtaUpgrades>lbQ()
                        .eq(OtaUpgrades::getProductIdentification, productIdentification)
                        .eq(OtaUpgrades::getVersion, version)
                        .eq(packageType != null, OtaUpgrades::getPackageType, packageType)
                        .isNotNull(OtaUpgrades::getProductVersionNo)
                        .ne(OtaUpgrades::getProductVersionNo, StrUtil.EMPTY)
                        .orderByDesc(OtaUpgrades::getId))
                .stream()
                .map(OtaUpgrades::getProductVersionNo)
                .filter(StrUtil::isNotBlank)
                .findFirst()
                .orElse(null);
    }

}