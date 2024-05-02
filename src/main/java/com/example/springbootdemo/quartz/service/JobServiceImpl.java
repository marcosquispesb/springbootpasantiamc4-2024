package com.example.springbootdemo.quartz.service;

import com.example.springbootdemo.quartz.config.PersistableCronTriggerFactoryBean;
import com.example.springbootdemo.quartz.config.PersistableSingleTriggerFactoryBean;
import com.example.springbootdemo.rest.exceptions.OperationException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    @Lazy
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ApplicationContext context;

    private JobKey getJobKey(String groupName, String paramJobName) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                if (jobName != null && jobName.equals(paramJobName))
                    return jobKey;
            }
        } catch (Exception e) {
            log.error("Error inesperado al obtener el jobKey, causa:[{}] ", e.getMessage(), e);
            throw e;
        }
        return null;
    }

    @Override
    public boolean existJobName(String groupName, String paramJobName) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                if (jobKey.getName() != null && jobKey.getName().equals(paramJobName))
                    return true;
            }
        } catch (Exception e) {
            log.error("Error inesperado al obtener el jobName. ", e);
        }
        return false;
    }

    @Override
    public boolean scheduleOneTimeJob(String groupName, String jobName, String triggerKey, Class<? extends QuartzJobBean> jobClass, Date startTime, Map<String, String> data, int repeatCount, long repeatInterval, String description) {
        log.info("[{}] [{}] Creando job", groupName, jobName);
        data.put(JobConstants.KEY_ATTEMPT, String.valueOf(0));
        data.put(JobConstants.KEY_MAX_ATTEMPT, String.valueOf(repeatCount));

        JobDetail jobDetail = createJob(jobClass, false, context, jobName, groupName, data, description);
        log.info("[{}] [{}] Creando trigger con nombre: [{}] ", groupName, jobName, triggerKey);
        Date st = new Date(startTime.getTime() - repeatInterval);
        Trigger cronTriggerBean = createSingleTrigger(null, triggerKey, st, SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT, repeatCount, repeatInterval, data);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);
            log.info("[{}] [{}] Job creado exitosamente para ejecutarse en : [{}]", groupName, jobName, dt);
            return true;
        } catch (SchedulerException e) {
            log.error("[{}] [{}] Error al crear el job", groupName, jobName, e);
        }
        return false;
    }

    @Override
    public void scheduleCronJob(String groupName, String jobName, String triggerKey, Class<? extends QuartzJobBean> jobClass, Date startTime, String cronExpression, Map<String, String> data, String description) {
        log.info("[{}] [{}] Creando nuevo Job ", groupName, jobName);
        JobDetail jobDetail = createJob(jobClass, false, context, jobName, groupName, data, description);
        log.info("[{}] [{}] Job creado correctamente ", groupName, jobName);
        try {
            log.info("[{}] [{}] Creando el trigger con la expresion cron: [{}]", groupName, jobName, cronExpression);
            Trigger cronTriggerBean = createCronTrigger(null, triggerKey, startTime, cronExpression, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW, data, description);
            log.info("[{}] [{}] Trigger creado correctamente, proxima fecha de ejecucion: [{}]", groupName, jobName, cronTriggerBean.getNextFireTime());
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            scheduler.scheduleJob(jobDetail, cronTriggerBean);

            log.info("[{}] [{}] Se ha creado exitosamente el trigger:[{}] ", groupName, jobName, triggerKey);
        } catch (ObjectAlreadyExistsException e) {
            log.error("[{}] [{}] Ya existe el cronJob [{}]", groupName, jobName, triggerKey);
            // DON'T THROW NOTHING EXCEPTION
        } catch (SchedulerException e) {
            log.error("[{}] [{}] Error SchedulerException al crear cronJob:[{}] ", groupName, jobName, triggerKey, e);
            throw new OperationException("Error al crear cronJob.");
        } catch (ParseException e) {
            log.error("[{}] [{}] Error  ParseException al crear cronJob:[{}] ", groupName, jobName, triggerKey, e);
            throw new OperationException("Error al crear cronJob.");
        }
    }

    private static JobDetail createJob(Class<? extends QuartzJobBean> jobClass, boolean isDurable,
                                       ApplicationContext context, String jobName, String jobGroup, Map<String, String> data, String description) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(isDurable);
        factoryBean.setApplicationContext(context);
        factoryBean.setName(jobName);
        factoryBean.setDescription(description);
        factoryBean.setGroup(jobGroup);
        if (data != null) {
            factoryBean.setJobDataAsMap(data);
        }
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    private static Trigger createCronTrigger(JobDetail jobDetail, String triggerName, Date startTime, String cronExpression, int misFireInstruction, Map<String, String> data, String description) throws ParseException {
        PersistableCronTriggerFactoryBean factoryBean = new PersistableCronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setDescription(description);
        factoryBean.setMisfireInstruction(misFireInstruction);
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0L);

        if (data != null) {
            factoryBean.setJobDataAsMap(data);
        }

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            throw e;
        }
        return factoryBean.getObject();
    }

    private static Trigger createSingleTrigger(JobDetail jobDetail, String triggerName, Date startTime, int misFireInstruction, int repeatCount, long repeatInterval, Map<String, String> data) {
        PersistableSingleTriggerFactoryBean factoryBean = new PersistableSingleTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setMisfireInstruction(misFireInstruction);
        factoryBean.setRepeatCount(repeatCount);
        factoryBean.setRepeatInterval(repeatInterval);
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0L);
        if (data != null) {
            factoryBean.setJobDataAsMap(data);
        }
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

}

