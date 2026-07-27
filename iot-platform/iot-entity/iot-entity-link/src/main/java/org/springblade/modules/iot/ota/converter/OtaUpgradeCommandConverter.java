package org.springblade.modules.iot.ota.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springblade.modules.iot.ota.dto.OtaUpgradeFileResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradeTasksResultDTO;
import org.springblade.modules.iot.ota.dto.OtaUpgradesResultDTO;
import org.springblade.modules.iot.ota.enumeration.OtaPackageSignMethodEnum;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaCommandRequestParam;
import org.springblade.modules.iot.protocol.vo.param.TopoOtaPullResponseParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * OTA升级命令转换器
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Slf4j
public class OtaUpgradeCommandConverter {

    /**
     * 文件包信息记录
     *
     * @param fileLocation 文件位置（多个URL用逗号分隔）
     * @param fileSign     文件签名
     */
    private record FilePackageInfo(String fileLocation, String fileSign) {
    }

    /**
     * 构建OTA命令请求参数
     *
     * @param deviceIdentification 设备标识
     * @param upgradeTask          升级任务
     * @param upgradePackage       升级包
     * @param fileInfoMap          文件信息映射
     * @return {@link TopoOtaCommandRequestParam} OTA命令请求参数
     */
    public static TopoOtaCommandRequestParam buildOtaCommandRequestParam(String deviceIdentification,
                                                                         OtaUpgradeTasksResultDTO upgradeTask,
                                                                         OtaUpgradesResultDTO upgradePackage,
                                                                         Map<Long, OtaUpgradeFileResultDTO> fileInfoMap) {
        FilePackageInfo packageInfo = extractFilePackageInfo(upgradePackage, fileInfoMap);

        TopoOtaCommandRequestParam param = new TopoOtaCommandRequestParam();
        param.setDeviceIdentification(deviceIdentification);
        param.setProductIdentification(upgradePackage.getProductIdentification());
        param.setOtaTaskId(upgradeTask.getId());
        param.setOtaTaskName(upgradeTask.getTaskName());
        param.setPackageName(upgradePackage.getPackageName());
        param.setPackageType(upgradePackage.getPackageType());
        param.setVersion(upgradePackage.getVersion());
        param.setFileLocation(packageInfo.fileLocation());
        param.setSignMethod(upgradePackage.getSignMethod());
        param.setSign(packageInfo.fileSign());
        param.setDescription(upgradePackage.getDescription());
        param.setCustomInfo(upgradePackage.getCustomInfo());
        return param;
    }

    /**
     * 构建OTA Pull 响应命令请求参数
     *
     * @param deviceIdentification 设备标识
     * @param upgradeTask          升级任务
     * @param upgradePackage       升级包
     * @param fileInfoMap          文件信息映射
     * @return {@link TopoOtaPullResponseParam} OTA命令请求参数
     */
    public static TopoOtaPullResponseParam buildOtaPullResponseParam(String deviceIdentification,
                                                                     OtaUpgradeTasksResultDTO upgradeTask,
                                                                     OtaUpgradesResultDTO upgradePackage,
                                                                     Map<Long, OtaUpgradeFileResultDTO> fileInfoMap) {
        FilePackageInfo packageInfo = extractFilePackageInfo(upgradePackage, fileInfoMap);

        TopoOtaPullResponseParam param = new TopoOtaPullResponseParam();
        param.setDeviceIdentification(deviceIdentification);
        param.setProductIdentification(upgradePackage.getProductIdentification());
        param.setOtaTaskId(upgradeTask.getId());
        param.setOtaTaskName(upgradeTask.getTaskName());
        param.setPackageName(upgradePackage.getPackageName());
        param.setPackageType(upgradePackage.getPackageType());
        param.setVersion(upgradePackage.getVersion());
        param.setFileLocation(packageInfo.fileLocation());
        param.setSignMethod(upgradePackage.getSignMethod());
        param.setSign(packageInfo.fileSign());
        param.setDescription(upgradePackage.getDescription());
        param.setCustomInfo(upgradePackage.getCustomInfo());
        return param;
    }

    /**
     * 提取文件包信息（文件位置和签名）
     *
     * @param upgradePackage 升级包
     * @param fileInfoMap    文件信息映射
     * @return {@link FilePackageInfo} 文件包信息
     */
    private static FilePackageInfo extractFilePackageInfo(OtaUpgradesResultDTO upgradePackage,
                                                          Map<Long, OtaUpgradeFileResultDTO> fileInfoMap) {
        Map<Long, OtaUpgradeFileResultDTO> safeFileInfoMap = Optional.ofNullable(fileInfoMap).orElse(Map.of());
        String fileLocation = Optional.ofNullable(upgradePackage.getFileLocation())
                .map(locations -> parseFileIds(locations).stream()
                        .map(fileId -> Optional.ofNullable(safeFileInfoMap.get(fileId))
                                .map(OtaUpgradeFileResultDTO::getUrl)
                                .orElse(null))
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.joining(",")))
                .orElse("");

        String fileSign = Optional.ofNullable(upgradePackage.getSignMethod())
                .flatMap(OtaPackageSignMethodEnum::fromValue)
                .flatMap(signMethod -> parseFileIds(upgradePackage.getFileLocation()).stream()
                        .map(fileId -> Optional.ofNullable(safeFileInfoMap.get(fileId))
                                .flatMap(file -> file.getFileSign(signMethod))
                                .orElse(null))
                        .filter(Objects::nonNull)
                        .findFirst())
                .orElse(null);

        return new FilePackageInfo(fileLocation, fileSign);
    }

    /**
     * 解析文件ID字符串为列表
     *
     * @param fileLocation 文件位置字符串（逗号分隔的文件ID）
     * @return 文件ID列表
     */
    private static List<Long> parseFileIds(String fileLocation) {
        return Optional.ofNullable(fileLocation)
                .map(locations -> Arrays.stream(locations.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(idStr -> {
                            try {
                                return Long.valueOf(idStr);
                            } catch (NumberFormatException e) {
                                log.warn("Invalid file ID format: {}", idStr, e);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(List.of());
    }
}
