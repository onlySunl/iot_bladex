package org.springblade.fastdfs.domain.proto.storage;

import org.springblade.fastdfs.domain.fdfs.MetaData;
import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.storage.internal.StorageGetMetadataRequest;
import org.springblade.fastdfs.domain.proto.storage.internal.StorageGetMetadataResponse;

import java.util.Set;

/**
 * 设置文件标签
 *
 * @author tobato
 */
public class StorageGetMetadataCommand extends AbstractFdfsCommand<Set<MetaData>> {


    /**
     * 设置文件标签(元数据)
     *
     * @param groupName
     * @param path
     */
    public StorageGetMetadataCommand(String groupName, String path) {
        this.request = new StorageGetMetadataRequest(groupName, path);
        // 输出响应
        this.response = new StorageGetMetadataResponse();
    }

}
