package com.example.springbootdemo.jobs;

import com.example.springbootdemo.services.ProcessService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Job para procesar los atrasos y faltas
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ProcesoAtrasosFaltasJob extends QuartzJobBean implements InterruptableJob {
    public static final String NAME_JOB = "ProcesoAtrasosFaltasJob";
    public static final String NAME_TRIGGER = "ProcesoAtrasosFaltasJob-TRIGGER";

    @Autowired
    private ProcessService processService;

//    @Autowired
//    private JobService jobService;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap(); // necesario para actualizar el dataMap
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();

        String transactionId = "" + System.currentTimeMillis();
        log.info("ProcesoAtrasosFaltasJob iniciando...");

        // TODO Por cada ejecucion del job solo agarrar dos jis usernames
        // TODO Llamar al proceso que recibe una lista de usernames
        //processService.processAtrasos(jisUsernames);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("[{}] Deteniendo el hilo ", ProcesoAtrasosFaltasJob.class.getSimpleName());
    }

}
