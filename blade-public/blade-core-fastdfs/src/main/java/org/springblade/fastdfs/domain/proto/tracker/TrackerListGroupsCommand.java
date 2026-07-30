package org.springblade.fastdfs.domain.proto.tracker;

import org.springblade.fastdfs.domain.fdfs.GroupState;
import org.springblade.fastdfs.domain.proto.AbstractFdfsCommand;
import org.springblade.fastdfs.domain.proto.tracker.internal.TrackerListGroupsRequest;
import org.springblade.fastdfs.domain.proto.tracker.internal.TrackerListGroupsResponse;

import java.util.List;

/**
 * 列出组命令
 *
 * @author tobato
 */
public class TrackerListGroupsCommand extends AbstractFdfsCommand<List<GroupState>> {

    public TrackerListGroupsCommand() {
        super.request = new TrackerListGroupsRequest();
        super.response = new TrackerListGroupsResponse();
    }

}
