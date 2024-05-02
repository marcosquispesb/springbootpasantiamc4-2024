package com.example.springbootdemo;

import com.example.springbootdemo.jobs.ProcesoAtrasosFaltasJob;
import com.example.springbootdemo.quartz.service.JobConstants;
import com.example.springbootdemo.quartz.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootDemoApplication implements CommandLineRunner {

    private final JobService jobService;

    @Override
    public void run(String... args) throws Exception {
        String groupName = JobConstants.GROUP_NAME;

        String jobName = ProcesoAtrasosFaltasJob.NAME_JOB;
        String triggerName = ProcesoAtrasosFaltasJob.NAME_TRIGGER;
        Map<String, String> dataMap = new HashMap();
        if (!jobService.existJobName(groupName, jobName)) {
            jobService.scheduleCronJob(groupName, jobName, triggerName, ProcesoAtrasosFaltasJob.class, new Date()
                    , JobConstants.CRON_X_3_MIN, dataMap, "Job para procesar las faltas y atrasos");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
        log.info("hola mundo");
    }

}
