package org.springblade.modules.iot.job.api;

import org.springblade.basic.base.R;
import org.springblade.basic.constant.Constants;
import org.springblade.modules.iot.job.dto.JobReturnT;
import org.springblade.modules.iot.job.dto.XxlJobInfoVO;
import org.springblade.modules.iot.job.facade.JobFacade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mqttsnet
 * @date 2021/1/4 11:52 下午
 */
@FeignClient(name = "JobApi", url = "${" + Constants.PROJECT_PREFIX + ".feign.job.job-server:http://127.0.0.1:18767}", path = "/thinglinks-job-admin/api")
public interface JobApi {
    /**
     * 定时发送接口
     *
     * @param xxlJobInfo
     * @return
     */
    @RequestMapping(value = "/jobinfo/save", method = RequestMethod.POST)
    R<String> addTimingTask(@RequestBody XxlJobInfoVO xxlJobInfo, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);


    /**
     * Update job details.
     *
     * @param xxlJobInfo Updated job details.
     * @return Operation result.
     */
    @PostMapping(value = "/jobinfo/update", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    JobReturnT<String> updateJob(@RequestBody XxlJobInfoVO xxlJobInfo, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

    /**
     * Remove a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    @GetMapping(value = "/jobinfo/remove", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    JobReturnT<String> removeJob(@RequestParam("id") Integer id, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

    /**
     * Pause a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    @PostMapping(value = "/jobinfo/stop", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    JobReturnT<String> pauseJob(@RequestBody Integer id, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

    /**
     * Start a job.
     *
     * @param id Job ID.
     * @return Operation result.
     */
    @PostMapping(value = "/jobinfo/start", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    JobReturnT<String> startJob(@RequestBody Integer id, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

    /**
     * Save job details.
     *
     * @param xxlJobInfo Job details.
     * @return Operation result. job id
     */
    @PostMapping("/jobinfo/save")
    JobReturnT<String> saveTimingTask(@RequestBody XxlJobInfoVO xxlJobInfo, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

    /**
     * Load a job by its ID.
     *
     * @param id Job ID.
     * @return Job information.
     */
    @GetMapping("/jobinfo/loadById")
    JobReturnT<XxlJobInfoVO> loadById(@RequestParam("id") Integer id, @RequestHeader(JobFacade.HEADER_NAME) String accessToken);

}
