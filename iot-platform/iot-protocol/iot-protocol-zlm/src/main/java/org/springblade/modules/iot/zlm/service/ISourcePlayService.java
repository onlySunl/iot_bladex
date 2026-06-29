package org.springblade.modules.iot.zlm.service;

import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.domain.StreamInfo;

/**
 * 资源能力接入-实时录像
 */
public interface ISourcePlayService {

    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
