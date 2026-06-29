package org.springblade.modules.iot.zlm.service;


import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.domain.StreamInfo;

public interface IDevicePlayService {
    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
