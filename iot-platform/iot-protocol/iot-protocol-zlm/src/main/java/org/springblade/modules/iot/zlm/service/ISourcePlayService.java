package org.springblade.modules.iot.zlm.service;

import org.springblade.modules.iot.qs.api.domain.QsDevice;
import org.springblade.modules.iot.zlm.api.domain.StreamInfo;

/**
 * 资源能力接入-实时录像
 */
public interface ISourcePlayService {

    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
