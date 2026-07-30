package org.springblade.fastdfs.domain.proto.tracker;

import org.springblade.fastdfs.domain.fdfs.StorageNodeInfo;
import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.FdfsResponse;
import org.springblade.fastdfs.domain.proto.tracker.internal.TrackerGetFetchStorageRequest;

/**
 * 获取源服务器
 *
 * @author tobato
 */
public class TrackerGetFetchStorageCommand extends AbstractFdfsCommand<StorageNodeInfo> {

    public TrackerGetFetchStorageCommand(String groupName, String path, boolean toUpdate) {
        super.request = new TrackerGetFetchStorageRequest(groupName, path, toUpdate);
        super.response = new FdfsResponse<StorageNodeInfo>() {
            // default response
        };
    }

}
