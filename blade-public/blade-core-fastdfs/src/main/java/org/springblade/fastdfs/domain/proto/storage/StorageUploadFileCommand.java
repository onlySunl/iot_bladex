package org.springblade.fastdfs.domain.proto.storage;

import org.springblade.fastdfs.domain.fdfs.StorePath;
import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.FdfsResponse;
import org.springblade.fastdfs.domain.proto.storage.internal.StorageUploadFileRequest;

import java.io.InputStream;

/**
 * 文件上传命令
 *
 * @author tobato
 */
public class StorageUploadFileCommand extends AbstractFdfsCommand<StorePath> {


    /**
     * 文件上传命令
     *
     * @param storeIndex
     * @param inputStream
     * @param fileExtName
     * @param fileSize
     * @param isAppenderFile
     */
    public StorageUploadFileCommand(byte storeIndex, InputStream inputStream, String fileExtName, long fileSize,
                                    boolean isAppenderFile) {
        super();
        this.request = new StorageUploadFileRequest(storeIndex, inputStream, fileExtName, fileSize, isAppenderFile);
        // 输出响应
        this.response = new FdfsResponse<StorePath>() {
            // default response
        };
    }

}
