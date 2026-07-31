package org.springblade.open.plugin.manager.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springblade.open.exp.client.Constant;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExpPluginsFileUtils
 * -----------------------------------------------------------------------------
 * Description:
 * ExpPluginsFileUtils 工具类，提供插件文件操作的通用方法
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/26       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/26 12:37
 */
@Slf4j
public class ExpPluginsFileUtils {

    /**
     * 从给定的 HTTP/HTTPS URL 下载插件文件到 Constant.PLUGINS_PATH_KEY 指定的默认目录。
     * 如果文件已存在则覆盖。文件名根据 URL 自动生成。
     *
     * @param urlString 远程文件的 URL
     * @return 下载到的本地文件对象
     * @throws IOException 如果下载过程中出错
     */
    public static File downloadFileFromURL(String urlString) throws IOException {
        log.info("Initiating download from URL: {}", urlString);
        URL url = decodeAndValidateURL(urlString);

        // 获取或创建下载目录
        String downloadDirectory = System.getProperty(Constant.PLUGINS_PATH_KEY, "exp-plugins");
        File directory = new File(downloadDirectory);
        createDirectoryIfNeeded(directory);

        // 自动生成文件名，使用 URL 文件名或 UUID 作为文件名
        String fileName = new File(url.getPath()).getName();
        if (fileName.isEmpty()) {
            fileName = "plugin-" + UUID.randomUUID() + ".jar";
            log.warn("No filename found in URL. Generated unique filename: {}", fileName);
        }
        File destinationFile = new File(directory, fileName);

        // 如果文件已存在，删除以实现覆盖
        deleteFileIfExists(destinationFile);

        try {
            log.info("Starting download to file: {}", destinationFile.getAbsolutePath());
            HttpFileDownloader.download(url, destinationFile);
            log.info("Successfully downloaded plugin to: {}", destinationFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Download failed for URL: {}. Cleaning up any partially downloaded file.", urlString, e);
            deleteFileIfExists(destinationFile);
            throw new IOException("Failed to download file from URL: " + urlString, e);
        }

        return destinationFile;
    }

    /**
     * 检查并创建目录，如果目录不存在则创建。
     *
     * @param directory 目录文件对象
     * @throws IOException 如果目录创建失败
     */
    private static void createDirectoryIfNeeded(File directory) throws IOException {
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                log.info("Created download directory at: {}", directory.getAbsolutePath());
            } else {
                log.error("Failed to create download directory at: {}", directory.getAbsolutePath());
                throw new IOException("Could not create download directory: " + directory.getAbsolutePath());
            }
        }
    }

    /**
     * 如果文件已存在，则删除文件。
     *
     * @param file 要检查的文件
     * @throws IOException 如果文件删除失败
     */
    private static void deleteFileIfExists(File file) throws IOException {
        if (file.exists()) {
            Files.delete(file.toPath());
            log.info("Deleted existing file to allow overwrite: {}", file.getAbsolutePath());
        }
    }

    /**
     * 验证给定路径是否为有效的 HTTP/HTTPS URL。
     *
     * @param path 文件路径或 URL
     * @return 如果是有效的 HTTP/HTTPS URL 返回 true，否则返回 false
     */
    public static boolean isValidURL(String path) {
        try {
            URL url = decodeAndValidateURL(path);
            boolean isValid = "http".equalsIgnoreCase(url.getProtocol()) || "https".equalsIgnoreCase(url.getProtocol());
            log.debug("URL validation result for {}: {}", path, isValid);
            return isValid;
        } catch (MalformedURLException e) {
            log.warn("Invalid URL format for path {}: {}", path, e.getMessage());
        }
        return false;
    }

    /**
     * 解码并验证 URL 字符串是否合法。
     *
     * @param urlString URL 字符串
     * @return 解码后的 URL 对象
     * @throws MalformedURLException 如果 URL 格式不正确
     */
    private static URL decodeAndValidateURL(String urlString) throws MalformedURLException {
        try {
            String decodedPath = URLDecoder.decode(urlString, StandardCharsets.UTF_8);
            URI uri = new URI(decodedPath);
            URL url = uri.toURL();
            log.debug("Decoded and validated URL: {}", url);
            return url;
        } catch (URISyntaxException | IllegalArgumentException e) {
            log.error("Failed to decode or validate URL: {}", urlString, e);
            throw new MalformedURLException("Invalid URL format: " + urlString);
        }
    }

    /**
     * HttpFileDownloader 是一个下载器类，用于从 URL 下载文件。
     */
    static class HttpFileDownloader {
        /**
         * 从给定的 URL 下载文件到指定位置。
         *
         * @param url         远程文件的 URL
         * @param destination 本地目标文件位置
         * @throws IOException 如果下载过程中出错
         */
        public static void download(URL url, File destination) throws IOException {
            try (var inputStream = url.openStream()) {
                Files.copy(inputStream, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                log.debug("File downloaded to {}", destination.getAbsolutePath());
            } catch (IOException e) {
                deleteFileIfExists(destination);
                throw e;
            }
        }
    }

    /**
     * 删除指定的临时文件。
     *
     * @param file 要删除的文件
     * @throws IOException 如果删除过程中出错
     */
    public static void deleteTempFile(File file) throws IOException {
        if (file != null && file.exists()) {
            Files.delete(file.toPath());
        }
    }
}
