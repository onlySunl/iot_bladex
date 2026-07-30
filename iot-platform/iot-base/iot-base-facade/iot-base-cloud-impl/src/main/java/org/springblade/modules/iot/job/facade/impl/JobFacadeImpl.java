package org.springblade.modules.iot.job.facade.impl;

import org.springblade.basic.base.R;
import org.springblade.basic.constant.Constants;
import org.springblade.modules.iot.job.api.JobApi;
import org.springblade.modules.iot.job.dto.JobReturnT;
import org.springblade.modules.iot.job.dto.XxlJobInfoVO;
import org.springblade.modules.iot.job.facade.JobFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author tangyh
 * @since 2024/9/21 00:15
 */
@Service
@RequiredArgsConstructor
public class JobFacadeImpl implements JobFacade {
    @Value("${" + Constants.PROJECT_PREFIX + ".feign.job.accessToken}")
    private String accessToken;

    @Autowired
    @Lazy  // 一定要延迟加载，否则thinglinks-gateway-server无法启动
    private JobApi jobApi;

    @Override
    public R<String> addTimingTask(XxlJobInfoVO xxlJobInfo) {
        return jobApi.addTimingTask(xxlJobInfo, accessToken);
    }

    @Override
    public JobReturnT<String> updateJob(XxlJobInfoVO xxlJobInfo) {
        return jobApi.updateJob(xxlJobInfo, accessToken);
    }

    @Override
    public JobReturnT<String> removeJob(Integer id) {
        return jobApi.removeJob(id, accessToken);
    }

    @Override
    public JobReturnT<String> pauseJob(Integer id) {
        return jobApi.pauseJob(id, accessToken);
    }

    @Override
    public JobReturnT<String> startJob(Integer id) {
        return jobApi.startJob(id, accessToken);
    }

    @Override
    public JobReturnT<String> saveTimingTask(XxlJobInfoVO xxlJobInfo) {
        return jobApi.saveTimingTask(xxlJobInfo, accessToken);
    }

    @Override
    public JobReturnT<XxlJobInfoVO> loadById(Integer id) {
        return jobApi.loadById(id, accessToken);
    }
}
