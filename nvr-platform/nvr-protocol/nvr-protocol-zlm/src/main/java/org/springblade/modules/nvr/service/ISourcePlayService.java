package org.springblade.modules.nvr.service;

import org.springblade.modules.nvr.domain.QsDevice;
import org.springblade.modules.nvr.domain.StreamInfo;

/**
 * 资源能力接入-实时录像
 */
public interface ISourcePlayService {

    void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback);
}
