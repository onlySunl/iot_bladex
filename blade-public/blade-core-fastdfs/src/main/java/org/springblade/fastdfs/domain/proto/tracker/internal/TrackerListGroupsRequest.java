package org.springblade.fastdfs.domain.proto.tracker.internal;

import org.springblade.fastdfs.domain.proto.CmdConstants;
import org.springblade.fastdfs.domain.proto.FdfsRequest;
import org.springblade.fastdfs.domain.proto.ProtoHead;

/**
 * 列出分组命令
 *
 * @author tobato
 */
public class TrackerListGroupsRequest extends FdfsRequest {

    public TrackerListGroupsRequest() {
        head = new ProtoHead(CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_GROUP);
    }
}
