package org.springblade.modules.iot.job.dto;

import com.mqttsnet.basic.utils.BeanPlusUtil;
import com.mqttsnet.basic.utils.DateUtils;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * job 类
 *
 * @author zuihou
 * @date 2021/1/5 9:23 上午
 */
@Data
@ToString
public class XxlJobInfoVO implements Serializable {

    private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT);

    private Integer id;

    /**
     * 执行器主键ID
     */
    private Integer jobGroup;
    /**
     * 执行器 名
     */
    private String jobGroupName;
    private String jobDesc;
    /**
     * 负责人
     */
    private String author;
    /**
     * 报警邮件
     */
    private String alarmEmail;
    /**
     * 调度类型
     */
    private String scheduleType;
    /**
     * 调度配置，值含义取决于调度类型
     */
    private String scheduleConf;
    /**
     * 调度过期策略
     */
    private String misfireStrategy;
    /**
     * 执行器路由策略
     */
    private String executorRouteStrategy;
    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;
    /**
     * 执行器，任务参数
     */
    private String executorParam;
    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;
    /**
     * 任务执行超时时间，单位秒
     */
    private Integer executorTimeout;
    /**
     * 失败重试次数
     */
    private Integer executorFailRetryCount;

    /**
     * GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
     */
    private String glueType;
    /**
     * GLUE源代码
     */
    private String glueSource;
    /**
     * GLUE备注
     */
    private String glueRemark;
    /**
     * GLUE更新时间
     */
    private Date glueupdatedTime;
    /**
     * 子任务ID，多个逗号分隔
     */
    private String childJobId;
    /**
     * 调度时间
     */
    private String scheduleTime;

    /**
     * 触发状态 0-停止，1-运行
     */
    private Integer triggerStatus;

    public static XxlJobInfoVO create(String jobGroupName, String jobDesc,
                                      LocalDateTime scheduleTime, String executorHandler,
                                      String executorParam) {
        XxlJobInfoVO xxlJobInfo = new XxlJobInfoVO();
        xxlJobInfo.setJobGroupName(jobGroupName);
        xxlJobInfo.setJobDesc(jobDesc);
        xxlJobInfo.setAuthor("admin");
        xxlJobInfo.setAlarmEmail("");
        xxlJobInfo.setScheduleType("CRON");
        xxlJobInfo.setScheduleTime(scheduleTime.format(DTF));
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setExecutorHandler(executorHandler);
        xxlJobInfo.setExecutorParam(executorParam);
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorTimeout(-1);
        xxlJobInfo.setExecutorFailRetryCount(-1);
        xxlJobInfo.setGlueType("BEAN");
        xxlJobInfo.setTriggerStatus(XxlJobTriggerStatusEnum.RUNNING.getValue());
        return xxlJobInfo;
    }

    /**
     * Creates a new instance of XxlJobInfoVO with the given parameters and sets the scheduling time.
     *
     * @param jobGroupName    Name of the job group.
     * @param jobDesc         Description of the job.
     * @param scheduleTime    Time the job is scheduled to run.
     * @param executorHandler Handler to execute the job.
     * @param executorParam   Parameters for the executor.
     * @param triggerStatus   Status of the trigger.
     * @return A fully populated XxlJobInfoVO object.
     */
    public static XxlJobInfoVO createFromScheduleTime(String jobGroupName, String jobDesc,
                                                      LocalDateTime scheduleTime, String executorHandler,
                                                      String executorParam, XxlJobTriggerStatusEnum triggerStatus) {
        XxlJobInfoVO xxlJobInfo = buildXxlJobInfoBase(jobGroupName, jobDesc, executorHandler, executorParam, triggerStatus);
        xxlJobInfo.setScheduleTime(scheduleTime.format(DTF));
        return xxlJobInfo;
    }

    /**
     * Creates a new instance of XxlJobInfoVO with the given parameters and sets the scheduling configuration.
     *
     * @param jobGroupName    Name of the job group.
     * @param jobDesc         Description of the job.
     * @param scheduleConf    Cron expression for scheduling the job.
     * @param executorHandler Handler to execute the job.
     * @param executorParam   Parameters for the executor.
     * @param triggerStatus   Status of the trigger.
     * @return A fully populated XxlJobInfoVO object.
     */
    public static XxlJobInfoVO createFromCronExpression(String jobGroupName, String jobDesc,
                                                        String scheduleConf, String executorHandler,
                                                        String executorParam, XxlJobTriggerStatusEnum triggerStatus) {
        XxlJobInfoVO xxlJobInfo = buildXxlJobInfoBase(jobGroupName, jobDesc, executorHandler, executorParam, triggerStatus);
        xxlJobInfo.setScheduleConf(scheduleConf);
        return xxlJobInfo;
    }

    /**
     * Updates an existing instance of XxlJobInfoVO with the given parameters.
     *
     * @param existingJobInfo The existing job info object.
     * @param jobDesc         Description of the job.
     * @param scheduleConf    Cron expression for scheduling the job.
     * @param executorHandler Handler to execute the job.
     * @param executorParam   Parameters for the executor.
     * @param triggerStatus   Status of the trigger.
     * @return An updated XxlJobInfoVO object.
     */
    public static XxlJobInfoVO updateFromCronExpression(XxlJobInfoVO existingJobInfo, String jobDesc,
                                                        String scheduleConf, String executorHandler, String executorParam, XxlJobTriggerStatusEnum triggerStatus) {

        // Create a new XxlJobInfoVO instance by copying the properties of the existing one.
        XxlJobInfoVO updatedJobInfo = BeanPlusUtil.toBeanIgnoreError(existingJobInfo, XxlJobInfoVO.class);

        // Set the specific properties using method parameters.
        updatedJobInfo.setJobDesc(jobDesc);
        updatedJobInfo.setScheduleConf(scheduleConf);
        updatedJobInfo.setExecutorHandler(executorHandler);
        updatedJobInfo.setExecutorParam(executorParam);
        updatedJobInfo.setTriggerStatus(triggerStatus.getValue());

        return updatedJobInfo;
    }


    /**
     * Builds the base properties for an XxlJobInfoVO object.
     *
     * @param jobGroupName    Name of the job group.
     * @param jobDesc         Description of the job.
     * @param executorHandler Handler to execute the job.
     * @param executorParam   Parameters for the executor.
     * @param triggerStatus   Status of the trigger.
     * @return A partially populated XxlJobInfoVO object.
     */
    private static XxlJobInfoVO buildXxlJobInfoBase(String jobGroupName, String jobDesc,
                                                    String executorHandler, String executorParam, XxlJobTriggerStatusEnum triggerStatus) {
        XxlJobInfoVO xxlJobInfo = new XxlJobInfoVO();
        xxlJobInfo.setJobGroupName(jobGroupName);
        xxlJobInfo.setJobDesc(jobDesc);
        xxlJobInfo.setAuthor("admin");
        xxlJobInfo.setAlarmEmail("");
        xxlJobInfo.setScheduleType("CRON");
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorRouteStrategy("ROUND");
        xxlJobInfo.setExecutorHandler(executorHandler);
        xxlJobInfo.setExecutorParam(executorParam);
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorTimeout(-1);
        xxlJobInfo.setExecutorFailRetryCount(3);
        xxlJobInfo.setGlueType("BEAN");
        xxlJobInfo.setTriggerStatus(triggerStatus.getValue());
        return xxlJobInfo;
    }

}
