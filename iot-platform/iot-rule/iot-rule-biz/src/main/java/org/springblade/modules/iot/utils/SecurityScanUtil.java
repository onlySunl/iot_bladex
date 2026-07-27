package org.springblade.modules.iot.utils;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * -----------------------------------------------------------------------------
 * File Name: SecurityScanUtil
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/25       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/25 16:43
 */
@Slf4j
public class SecurityScanUtil {
    private static final String REPORT_PATH = "target/dependency-check-report/";

    /**
     * 下载文件并执行安全扫描。
     *
     * @param fileUrl 要扫描的文件URL
     * @return 报告文件的路径，如果扫描失败返回 null
     */
    public static String downloadAndScan(String fileUrl) {
        fileUrl = URLDecoder.decode(fileUrl, StandardCharsets.UTF_8);
        File tempFile = null;
        try {
            // 创建临时文件
            tempFile = File.createTempFile(UUID.randomUUID().toString(), "-" + getFileNameFromUrl(fileUrl));

            // 下载文件到临时文件
            HttpUtil.downloadFile(fileUrl, tempFile);

            // 执行安全扫描
            return runSecurityScan(tempFile.getAbsolutePath());
        } catch (Exception e) {
            log.error("Error during file download or scanning: {}", e.getMessage());
            return null;
        } finally {
            // 确保临时文件被删除
            if (tempFile != null && tempFile.exists()) {
                boolean deleted = tempFile.delete();
                if (deleted) {
                    log.info("Temporary file deleted: {}", tempFile.getAbsolutePath());
                } else {
                    log.warn("Failed to delete temporary file: {}", tempFile.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 执行 Maven 安全扫描，并返回报告文件的路径。
     *
     * @param filePath 本地文件路径
     * @return 报告文件的路径，如果扫描失败返回 null
     */
    public static String runSecurityScan(String filePath) {
        // 生成报告文件路径
        String reportFilePath = REPORT_PATH + UUID.randomUUID().toString() + "-report.html";

        // 构建 Maven 命令
        String mavenCommand = "mvn dependency-check:check -Ddependency-check.failBuildOnCVSS=7 " +
                              "-DscanSet=" + filePath + " -Dformat=HTML -DoutputDirectory=" + reportFilePath;

        // 使用 ProcessBuilder 调用 Maven 命令执行 Dependency-Check
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", mavenCommand);

        try {
            // 启动进程
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                log.info("Security scan completed successfully. Report generated at: {}", reportFilePath);
                return reportFilePath;
            } else {
                log.error("Security scan failed with exit code: {}", exitCode);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            log.error("Error occurred during security scan: {}", e.getMessage());
            return null;
        }
    }

    private static String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
