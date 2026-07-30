package org.springblade.modules.iot.job.facade;

import org.springblade.modules.iot.job.dto.JobReturnT;
import org.springblade.modules.iot.job.dto.XxlJobInfoVO;

/**
 * @author zuihou
 * @since 2024年09月21日00:15:26
 */
public interface JobFacade {
    String HEADER_NAME = "XXL-JOB-ACCESS-TOKEN";

    /**
     * 定时发送接口
     *
     * @param xxlJobInfo 任务
     * @return 任务id
     */
    R<String> addTimingTask(XxlJobInfoVO xxlJobInfo);

    /**
     * Update job details.
     *
     * @param xxlJobInfo Updated job details.
     * @return Operation result.
     */
    JobReturnT<String> updateJob(XxlJobInfoVO xxlJobInfo);

    /**
     * Remove a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    JobReturnT<String> removeJob(Integer id);

    /**
     * Pause a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    JobReturnT<String> pauseJob(Integer id);

    /**
     * Start a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    JobReturnT<String> startJob(Integer id);

    /**
     * Save job details.
     *
     * @param xxlJobInfo Job details.
     * @return Operation result. job id
     */
    JobReturnT<String> saveTimingTask(XxlJobInfoVO xxlJobInfo);


    /**
     * Load a job by its ID.
     *
     * @param id Job ID.
     * @return Job information.
     */
    JobReturnT<XxlJobInfoVO> loadById(Integer id);

}
