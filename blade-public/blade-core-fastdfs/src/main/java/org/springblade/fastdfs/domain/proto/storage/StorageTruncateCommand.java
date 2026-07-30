package org.springblade.fastdfs.domain.proto.storage;

import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.FdfsResponse;
import org.springblade.fastdfs.domain.proto.storage.internal.StorageTruncateRequest;

/**
 * 文件Truncate命令
 *
 * @author tobato
 */
public class StorageTruncateCommand extends AbstractFdfsCommand<Void> {


    /**
     * StorageTruncateCommand
     *
     * @param path
     * @param fileSize
     */
    public StorageTruncateCommand(String path, long fileSize) {
        super();
        this.request = new StorageTruncateRequest(path, fileSize);
        // 输出响应
        this.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
