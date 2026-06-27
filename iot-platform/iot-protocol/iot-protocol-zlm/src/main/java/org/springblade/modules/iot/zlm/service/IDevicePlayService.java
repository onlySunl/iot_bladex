package org.springblade.modules.iot.zlm.service;

import org.springblade.modules.iot.qs.api.domain.QsDevice;
import org.springblade.modules.iot.zlm.api.domain.StreamInfo;

public interface IDevicePlayService {

    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
