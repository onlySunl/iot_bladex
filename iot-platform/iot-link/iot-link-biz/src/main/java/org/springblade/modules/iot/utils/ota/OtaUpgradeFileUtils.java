package org.springblade.modules.iot.utils.ota;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import org.springblade.basic.base.R;

import org.springblade.core.oss.OssTemplate;
import org.springblade.modules.iot.ota.dto.OtaUpgradeFileResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * OTA升级文件工具类
 * 用于统一处理OTA升级过程中的文件信息获取和转换
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OtaUpgradeFileUtils {

    private final OssTemplate fileFacade;

    /**
     * 获取OTA升级文件信息映射
     *
     * @param fileIds 文件ID列表
     * @return {@link Map<Long, OtaUpgradeFileResultDTO>} 文件ID到文件信息的映射
     */
    public Map<Long, OtaUpgradeFileResultDTO> getOtaUpgradeFileInfoMap(List<Long> fileIds) {
        if (CollUtil.isEmpty(fileIds)) {
            return Collections.emptyMap();
        }

        try {
            R<Map<Long, FileResultVO>> result = fileFacade.findInfoFromDefById(fileIds);
            if (Boolean.FALSE.equals(result.getIsSuccess()) || Objects.isNull(result.getData())) {
                log.warn("Failed to retrieve file info for file IDs: {}", fileIds);
                return Collections.emptyMap();
            }

            // 转换为 OtaUpgradeFileResultDTO
            return result.getData().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> convertToOtaUpgradeFileResultDTO(entry.getValue()),
                            (v1, v2) -> v1,
                            LinkedHashMap::new
                    ));
        } catch (Exception e) {
            log.error("Failed to retrieve file info for file IDs: {}", fileIds, e);
            return Collections.emptyMap();
        }
    }

    /**
     * 将 FileResultVO 转换为 OtaUpgradeFileResultDTO
     *
     * @param fileResultVO 文件结果VO
     * @return {@link OtaUpgradeFileResultDTO} OTA升级文件结果DTO
     */
    public OtaUpgradeFileResultDTO convertToOtaUpgradeFileResultDTO(FileResultVO fileResultVO) {
        if (Objects.isNull(fileResultVO)) {
            return null;
        }
        return OtaUpgradeFileResultDTO.builder()
                .id(fileResultVO.getId())
                .url(fileResultVO.getUrl())
                .fileMd5(fileResultVO.getFileMd5())
                .fileSha256(fileResultVO.getFileSha256())
                .originalFileName(fileResultVO.getOriginalFileName())
                .size(fileResultVO.getSize())
                .suffix(fileResultVO.getSuffix())
                .build();
    }
}