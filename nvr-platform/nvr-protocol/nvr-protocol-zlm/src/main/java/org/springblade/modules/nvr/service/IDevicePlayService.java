package org.springblade.modules.nvr.service;


import org.springblade.modules.nvr.domain.QsDevice;
import org.springblade.modules.nvr.domain.StreamInfo;

public interface IDevicePlayService {
    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
