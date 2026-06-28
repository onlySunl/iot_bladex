package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * 关闭课程视频查询入参 {@link NetSDKLib#CLIENT_CloseQueryCourseMediaFile}
 *
 * @author ： 47040
 * @since ： Created in 2020/9/28 19:03
 */
public class NET_IN_QUERY_COURSEMEDIA_FILECLOSE extends SdkStructure {
    /**
     * dwSize
     */
    public int              dwSize;
    /**
     * 查询ID号
     */
    public int              nFindID;

    public NET_IN_QUERY_COURSEMEDIA_FILECLOSE() {
        dwSize = this.size();
    }
}

