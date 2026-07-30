package org.springblade.fastdfs.domain.proto.storage;

import org.springblade.fastdfs.domain.fdfs.MetaData;
import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.FdfsResponse;
import org.springblade.fastdfs.domain.proto.storage.enums.StorageMetadataSetType;
import org.springblade.fastdfs.domain.proto.storage.internal.StorageSetMetadataRequest;

import java.util.Set;

/**
 * 设置文件标签
 *
 * @author tobato
 */
public class StorageSetMetadataCommand extends AbstractFdfsCommand<Void> {

    /**
     * 设置文件标签(元数据)
     *
     * @param groupName
     * @param path
     * @param metaDataSet
     * @param type
     */
    public StorageSetMetadataCommand(String groupName, String path, Set<MetaData> metaDataSet,
                                     StorageMetadataSetType type) {
        this.request = new StorageSetMetadataRequest(groupName, path, metaDataSet, type);
        // 输出响应
        this.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
