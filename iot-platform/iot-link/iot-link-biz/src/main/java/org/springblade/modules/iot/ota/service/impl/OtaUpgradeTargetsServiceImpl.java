package org.springblade.modules.iot.ota.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiototaserviceimplOtaUpgradeTargetsServiceImpl.java.mapper.OtaUpgradeTargetsMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTargetsResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.enumeration.OtaUpgradeTargetStatusEnum;
import org.springblade.modules.iot.ota.service.OtaUpgradeTargetsService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradeTargetsSaveVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * OTA鍗囩骇鐩爣琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [浠ｇ爜鐢熸垚鍣ㄧ敓鎴怾
 */
@Slf4j
@AllArgsConstructor
@Service
public class OtaUpgradeTargetsServiceImpl extends BaseServiceImpl<OtaUpgradeTargetsMapper, OtaUpgradeTargets> implements OtaUpgradeTargetsService {

    @Override
    public void saveBatchForOtaUpgradeTargets(List<OtaUpgradeTargetsSaveVO> otaUpgradeTargetsSaveVOList) {
        if (CollUtil.isEmpty(otaUpgradeTargetsSaveVOList)) {
            return;
        }
        // 鎵归噺淇濆瓨
        superManager.saveBatch(BeanUtil.toBeanList(otaUpgradeTargetsSaveVOList, OtaUpgradeTargets.class));
    }

    @Override
    public void deleteByTaskId(Long taskId) {
        if (Objects.isNull(taskId)) {
            log.warn("浠诲姟ID涓虹┖锛屾棤娉曞垹闄TA鍗囩骇鐩爣");
            return;
        }
        superManager.remove(Wrappers.<OtaUpgradeTargets>lbQ().eq(OtaUpgradeTargets::getTaskId, taskId));
    }

    /**
     * 鏍规嵁浠诲姟ID鑾峰彇鐩爣璁惧鏍囪瘑鍒楄〃
     *
     * @param taskId 浠诲姟ID
     * @return {@link Optional<List<String>>} 璁惧鏍囪瘑鍒楄〃Optional
     */
    @Override
    public Optional<List<String>> getTargetDevicesByTaskIdOptional(Long taskId) {
        try {
            if (Objects.isNull(taskId)) {
                log.warn("浠诲姟ID涓虹┖锛屾棤娉曡幏鍙栫洰鏍囪澶?);
                return Optional.empty();
            }
            // 鑾峰彇鍗囩骇鐩爣鍒楄〃
            OtaUpgradeTargetsPageQuery query = new OtaUpgradeTargetsPageQuery();
            query.setTaskId(taskId);
            List<OtaUpgradeTargetsResultDTO> targets = getOtaUpgradeTargetsResultDTOList(query);

            if (CollUtil.isEmpty(targets)) {
                log.warn("鏈壘鍒颁换鍔＄殑鐩爣璁惧 - 浠诲姟ID: {}", taskId);
                return Optional.empty();
            }
            // 鎻愬彇鐩爣鍊硷紙璁惧鏍囪瘑銆佸垎缁処D鎴栧尯鍩熺紪鐮侊級
            List<String> targetValues = targets.stream()
                    .map(OtaUpgradeTargetsResultDTO::getTargetValue)
                    .collect(Collectors.toList());

            log.info("鑾峰彇鍒颁换鍔＄洰鏍囧€煎垪琛?- 浠诲姟ID: {}, 鐩爣鏁伴噺: {}", taskId, targetValues.size());
            return Optional.of(targetValues);
        } catch (Exception e) {
            log.error("鑾峰彇浠诲姟鐩爣璁惧鍒楄〃寮傚父 - 浠诲姟ID: {}", taskId, e);
            return Optional.empty();
        }
    }

    /**
     * 鏍规嵁鏌ヨ鏉′欢鑾峰彇鍗囩骇鐩爣淇℃伅鍒楄〃
     *
     * @param query 鏌ヨ鏉′欢
     * @return {@link List<OtaUpgradeTargetsResultDTO>} 鍗囩骇鐩爣淇℃伅鍒楄〃
     */
    @Override
    public List<OtaUpgradeTargetsResultDTO> getOtaUpgradeTargetsResultDTOList(OtaUpgradeTargetsPageQuery query) {
        return BeanUtil.toBeanList(superManager.getOtaUpgradeTargetsList(query), OtaUpgradeTargetsResultDTO.class);
    }

    /**
     * 鏍规嵁浠诲姟ID鍜岀洰鏍囧€兼洿鏂扮洰鏍囩姸鎬?
     *
     * @param taskId                     浠诲姟ID
     * @param targetValue                鐩爣鍊硷紙璁惧鏍囪瘑/鍒嗙粍ID/鍖哄煙缂栫爜锛?
     * @param otaUpgradeTargetStatusEnum 鐩爣鐘舵€?
     * @return 鏇存柊鏄惁鎴愬姛
     */
    @Override
    public boolean updateTargetStatusByTaskAndValue(Long taskId, String targetValue, OtaUpgradeTargetStatusEnum otaUpgradeTargetStatusEnum) {
        try {
            if (Objects.isNull(taskId) || Objects.isNull(targetValue) || Objects.isNull(otaUpgradeTargetStatusEnum)) {
                return false;
            }

            // 妫€鏌ョ洰鏍囩姸鎬佹槸鍚︽湁鏁?
            if (!OtaUpgradeTargetStatusEnum.isValid(otaUpgradeTargetStatusEnum.getValue())) {
                log.warn("鐩爣鐘舵€佹棤鏁?- 浠诲姟ID: {}, 鐩爣鍊? {}, 鐩爣鐘舵€? {}", taskId, targetValue, otaUpgradeTargetStatusEnum);
                return false;
            }

            // 鏇存柊鐩爣鐘舵€?
            boolean updated = superManager.update(Wrappers.<OtaUpgradeTargets>lbU()
                    .set(OtaUpgradeTargets::getTargetStatus, otaUpgradeTargetStatusEnum.getValue())
                    .eq(OtaUpgradeTargets::getTaskId, taskId)
                    .eq(OtaUpgradeTargets::getTargetValue, targetValue));

            if (updated) {
                log.info("鏇存柊鐩爣鐘舵€佹垚鍔?- 浠诲姟ID: {}, 鐩爣鍊? {}, 鐩爣鐘舵€? {}", taskId, targetValue, otaUpgradeTargetStatusEnum.getDesc());
            } else {
                log.warn("鏇存柊鐩爣鐘舵€佸け璐ワ紝鏈壘鍒板尮閰嶈褰?- 浠诲姟ID: {}, 鐩爣鍊? {}", taskId, targetValue);
            }

            return updated;
        } catch (Exception e) {
            log.error("鏇存柊鐩爣鐘舵€佸紓甯?- 浠诲姟ID: {}, 鐩爣鍊? {}, 鐩爣鐘舵€? {}", taskId, targetValue, otaUpgradeTargetStatusEnum, e);
            return false;
        }
    }
}