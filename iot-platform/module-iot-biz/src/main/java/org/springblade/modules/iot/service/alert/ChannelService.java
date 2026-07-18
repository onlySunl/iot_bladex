

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.controller.admin.alert.vo.ChannelReqVO;
import org.springblade.modules.iot.controller.admin.channelconfig.vo.Channel;

import java.util.List;

/**
 * 通道配置 Service 接口
 *
 * @author EnjoyIot
 */
public interface ChannelService {

    List<Channel> getChannelList(ChannelReqVO reqVO);

}
