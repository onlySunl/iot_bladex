package org.springblade.modules.iot.ota.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.support.Query;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.modules.iot.ota.enumeration.OtaPackageSignMethodEnum;
import org.springblade.modules.iot.ota.enumeration.OtaPackageStatusEnum;
import org.springblade.modules.iot.ota.enumeration.OtaPackageTypeEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradesService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesDetailsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradesSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradesUpdateVO;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * OTA鍗囩骇鍖?
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
     * 鍐欏墠缃牎楠屼繚鐣欑洿璋?鈹€鈹€ 鍗囩骇鍖呬繚瀛?/ 鏇存柊鏍￠獙浜у搧瀛樺湪,蹇呴』 DB-fresh銆?
     */
    private final ProductQueryService productQueryService;
    /**
     * 璇︽儏灞曠ず璇昏矾寰勮蛋缂撳瓨,read-through DB 鍏滃簳銆?
     */
    private final LinkCacheDataHelper linkCacheDataHelper;

    /**
     * Save OTA Upgrade Package
     *
     * @param saveVO 淇濆瓨鍙傛暟
     * @return {@link OtaUpgradesSaveVO} 杩斿洖缁撴灉
     */
    @Override
    public OtaUpgradesSaveVO saveUpgradePackage(OtaUpgradesSaveVO saveVO) {
        log.info("saveUpgradePackage saveVO: {}", saveVO);
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

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
     * @param updateVO 鏇存柊鍙傛暟
     * @return {@link OtaUpgradesUpdateVO} 杩斿洖缁撴灉
     */
    @Override
    public OtaUpgradesUpdateVO updateUpgradePackage(OtaUpgradesUpdateVO updateVO) {
        log.info("Updating OTA upgrade package: {}", updateVO);
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

        // Validate the updateVO object
        validateOtaUpgradesUpdateVO(updateVO);

        //鏋勫缓鍙傛暟
        Builder<OtaUpgrades> otaUpgradesBuilder = builderOtaUpgradesUpdateVO(updateVO);

        // Save the updated entity
        superManager.updateById(otaUpgradesBuilder.with(OtaUpgrades::setId, updateVO.getId()).build());

        // Map the updated entity back to OtaUpgradesUpdateVO if needed
        return BeanUtil.toBeanIgnoreError(otaUpgradesBuilder.build(), OtaUpgradesUpdateVO.class);

    }

    /**
     * Update OTA Upgrade Package Status
     *
     * @param id     涓婚敭
     * @param status 鐘舵€?
     * @return {@link Boolean} 杩斿洖缁撴灉
     */
    @Override
    public Boolean updateOtaUpgradeStatus(Long id, Integer status) {
        ArgumentAssert.notNull(id, "Package ID cannot be null");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        ArgumentAssert.notNull(status, "Status cannot be null");

        // Here you should define your OtaUpgrade entity class which represents your OTA upgrade package
        OtaUpgrades otaUpgrades = superManager.getById(id);
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        if (Objects.isNull(otaUpgrades)) {
            throw BizException.wrap("OTA upgrade package does not exist");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        }
        if (status.equals(otaUpgrades.getStatus())) {
            throw BizException.wrap("The OTA upgrade package status is the same as the current status");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        }

        otaUpgrades.setStatus(status);
        return superManager.updateById(otaUpgrades);
    }

    /**
     * Delete OTA Upgrade Package
     *
     * @param id 涓婚敭
     * @return {@link Boolean} 杩斿洖缁撴灉
     */
    @Override
    public Boolean deleteOtaUpgrade(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        OtaUpgrades otaUpgrade = superManager.getById(id);
        if (Objects.isNull(otaUpgrade)) {
            throw BizException.wrap("OTA upgrade package does not exist");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        }

        Query params = new Query<>();
        params.setModel(new OtaUpgradeTasksPageQuery().setUpgradeId(id));
        if (otaUpgradeTasksManager.getOtaUpgradeTasksPage(params).getTotal() > 0) {
            throw BizException.wrap("OTA upgrade package is in use and cannot be deleted");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
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
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        OtaUpgrades otaUpgrades = superManager.getById(id);
        ArgumentAssert.notNull(otaUpgrades, "OTA upgrade package does not exist");
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
        OtaUpgradesDetailsResultVO detailsVO = BeanUtil.toBeanIgnoreError(otaUpgrades, OtaUpgradesDetailsResultVO.class);
        // 璇︽儏璇昏矾寰勮蛋缂撳瓨(read-through 鍏滃簳),閬垮厤姣忔璇︽儏璇锋眰閮界洿鏌?product 琛?
        ProductResultVO productResultVO = linkCacheDataHelper
                .getProductCacheVO(otaUpgrades.getProductIdentification())
                .map(p -> BeanUtil.toBeanIgnoreError(p, ProductResultVO.class))
                .orElse(null);
        detailsVO.setProductResult(productResultVO);
        return detailsVO;
    }

    private void validateOtaUpgradesSaveVO(OtaUpgradesSaveVO saveVO) {
        OtaPackageTypeEnum.fromValue(saveVO.getPackageType()).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

        OtaPackageSignMethodEnum.fromValue(saveVO.getSignMethod()).orElseThrow(() -> BizException.wrap("Invalid sign method"));

        OtaPackageStatusEnum.fromValue(saveVO.getStatus()).orElseThrow(() -> BizException.wrap("Invalid status"));

        if (!VersionValidator.isValidVersion(saveVO.getVersion())) {
            throw BizException.wrap("鏃犳晥鐗堟湰鍙?);
        }

        // 鏍￠獙鍗囩骇鍖呯増鏈彿鏄惁閲嶅
        if (superManager.count(Wrappers.<OtaUpgrades>lbQ().eq(OtaUpgrades::getPackageType, saveVO.getPackageType()).eq(OtaUpgrades::getVersion, saveVO.getVersion())) > 0) {
            throw BizException.wrap("鍗囩骇鐗堟湰鍙峰凡瀛樺湪");
        }
    }

    private OtaUpgrades buildOtaUpgradeFromSaveVO(OtaUpgradesSaveVO saveVO) {
        saveVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return BeanUtil.toBeanIgnoreError(saveVO, OtaUpgrades.class);
    }

    private void validateOtaUpgradesUpdateVO(OtaUpgradesUpdateVO updateVO) {

        OtaUpgrades existingOtaUpgrade = Optional.ofNullable(superManager.getById(updateVO.getId())).orElseThrow(() -> BizException.wrap("OTA upgrade package not found"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

        //TODO Validate the updateVO object
        String productIdentification = existingOtaUpgrade.getProductIdentification();

        OtaPackageTypeEnum.fromValue(updateVO.getPackageType()).orElseThrow(() -> BizException.wrap("Invalid package type"));
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;

        OtaPackageSignMethodEnum.fromValue(updateVO.getSignMethod()).orElseThrow(() -> BizException.wrap("Invalid sign method"));

        OtaPackageStatusEnum.fromValue(updateVO.getStatus()).orElseThrow(() -> BizException.wrap("Invalid status"));

        // 鏍￠獙鍗囩骇鍖呯増鏈彿鏄惁鍚堟硶
        if (!VersionValidator.isValidVersion(updateVO.getVersion())) {
            throw BizException.wrap("鏃犳晥鐗堟湰鍙?);
        }

        // 鏍￠獙鍗囩骇鍖呯増鏈彿鏄惁閲嶅
        if (superManager.count(Wrappers.<OtaUpgrades>lbQ()
                .eq(OtaUpgrades::getPackageType, updateVO.getPackageType())
                .eq(OtaUpgrades::getVersion, updateVO.getVersion())
                .ne(OtaUpgrades::getId, updateVO.getId())) > 0) {
            throw BizException.wrap("鍗囩骇鐗堟湰鍙峰凡瀛樺湪");
        }
    }

    private Builder<OtaUpgrades> builderOtaUpgradesUpdateVO(OtaUpgradesUpdateVO updateVO) {
        return new OtaUpgrades()
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
     * 鏍规嵁ID闆嗗悎鏌ヨ鍗囩骇鍖呬俊鎭?
     *
     * @param ids 鍗囩骇鍖匢D闆嗗悎
     * @return {@link List<OtaUpgradesResultVO>} 鍗囩骇鍖呬俊鎭垪琛?
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
        // 鍚屼竴(浜у搧 + 鐗堟湰)鐞嗚涓婂敮涓€(saveUpgradePackage 宸叉寜 packageType + version 鍘婚噸),鍙栨渶鏂颁竴鏉″厹搴曞鍖归厤
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
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradesServiceImpl.java.mapper.OtaUpgradesMapper;
    }

}