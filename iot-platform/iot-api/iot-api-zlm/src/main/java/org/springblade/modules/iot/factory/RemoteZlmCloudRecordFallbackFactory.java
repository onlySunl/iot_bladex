package org.springblade.modules.iot.factory;

import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.ZlmCloudRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.service.RemoteZlmCloudRecordService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * zlm接口云端接口 服务降级处理
 *
 * @FileName RemoteZlmCloudRecordFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-04-11
 **/
@Component
public class RemoteZlmCloudRecordFallbackFactory implements FallbackFactory<RemoteZlmCloudRecordService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteZlmCloudRecordFallbackFactory.class);

    @Override
    public RemoteZlmCloudRecordService create(Throwable throwable) {

        log.error("zlm接口云端调用失败:{}", throwable.getMessage());

        return new RemoteZlmCloudRecordService(){
            @Override
            public R<Void> task(String inner) {
                return R.fail("zlm接口云端定时查询待删除的录像文件接口失败:" + throwable.getMessage());
            }

            @Override
            public R<List<ZlmCloudRecord>> selectZlmCloudRecordList(ZlmCloudRecord zlmCloudRecord, String inner) {
                return R.fail("zlm接口云端查询录像列表接口失败:" + throwable.getMessage());
            }
        };
    }
}
