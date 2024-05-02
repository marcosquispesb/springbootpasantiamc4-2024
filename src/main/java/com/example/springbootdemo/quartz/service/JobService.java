package com.example.springbootdemo.quartz.service;

import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

public interface JobService {

    boolean existJobName(String groupName, String paramJobName);

    boolean scheduleOneTimeJob(String groupName, String jobName, String triggerKey, Class<? extends QuartzJobBean> jobClass, Date startTime, Map<String, String> data, int repeatCount, long repeatInterval, String description);

    void scheduleCronJob(String groupName, String jobName, String triggerKey, Class<? extends QuartzJobBean> jobClass, Date startTime, String cronExpression, Map<String, String> data, String description);

}
