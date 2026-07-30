package org.springblade.fastdfs.client;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.*;
import org.springblade.fastdfs.config.FastDfsProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * FastDFS 客户端
 *
 * @author Chill
 */
@Slf4j
@Component
public class FastDfsClient {

    private final FastDfsProperties properties;

    public FastDfsClient(FastDfsProperties properties) {
        this.properties = properties;
        initClient();
    }

    /**
     * 初始化客户端
     */
    private void initClient() {
        try {
            ClientGlobal.initByTrackers(properties.getTrackerServers());
            ClientGlobal.setGConnectTimeout(properties.getConnectTimeout());
            ClientGlobal.setGNetworkTimeout(properties.getNetworkTimeout());
            ClientGlobal.setGCharset(properties.getCharset());
            log.info("FastDFS 客户端初始化成功，Tracker: {}", properties.getTrackerServers());
        } catch (Exception e) {
            log.error("FastDFS 客户端初始化失败", e);
        }
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件输入流
     * @param fileName    文件名
     * @return 文件路径
     */
    public String upload(InputStream inputStream, String fileName) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] fileBytes = readInputStream(inputStream);
            String extName = getFileExtName(fileName);

            String path = storageClient1.upload_file1(fileBytes, extName, null);
            log.info("文件上传成功: {}", path);
            return path;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败", e);
        }
    }

    /**
     * 上传文件（字节数组）
     *
     * @param bytes    文件字节数组
     * @param fileName 文件名
     * @return 文件路径
     */
    public String upload(byte[] bytes, String fileName) {
        return upload(new ByteArrayInputStream(bytes), fileName);
    }

    /**
     * 下载文件
     *
     * @param path 文件路径
     * @return 文件字节数组
     */
    public byte[] download(String path) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getFetchStorage(trackerServer, path);

            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = storageClient1.download_file1(path);
            log.info("文件下载成功: {}", path);
            return bytes;
        } catch (Exception e) {
            log.error("文件下载失败: {}", path, e);
            throw new RuntimeException("文件下载失败", e);
        }
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     */
    public void delete(String path) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getFetchStorage(trackerServer, path);

            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            int result = storageClient1.delete_file1(path);
            log.info("文件删除成功: {}, 结果: {}", path, result);
        } catch (Exception e) {
            log.error("文件删除失败: {}", path, e);
            throw new RuntimeException("文件删除失败", e);
        }
    }

    /**
     * 获取文件信息
     *
     * @param path 文件路径
     * @return 文件信息
     */
    public FileInfo getFileInfo(String path) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getFetchStorage(trackerServer, path);

            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            return storageClient1.get_file_info1(path);
        } catch (Exception e) {
            log.error("获取文件信息失败: {}", path, e);
            throw new RuntimeException("获取文件信息失败", e);
        }
    }

    /**
     * 读取输入流
     */
    private byte[] readInputStream(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        return buffer;
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtName(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
