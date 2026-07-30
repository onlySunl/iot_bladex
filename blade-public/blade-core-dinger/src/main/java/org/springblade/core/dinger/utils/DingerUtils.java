package org.springblade.core.dinger.utils;

import org.springblade.core.dinger.constant.DingerConstants;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DingerUtils {

    /**
     * 获取渠道字段，转换为对应的模版集合
     */
    public static List<String> getChannelTemplate(List<Integer> channelList) {
        List<String> templateList = new ArrayList<>();
        if (CollectionUtils.isEmpty(channelList)) {
            return templateList;
        }
        for (Integer channel : channelList) {
            switch (channel) {
                case 0:
                    templateList.add(DingerConstants.DINGTALK_ALARM);
                    break;
                case 1:
                    templateList.add(DingerConstants.WX_ALARM);
                case 2:
                    templateList.add(DingerConstants.FS_ALARM);
                default:
                    break;
            }
        }
        return templateList;
    }

}
