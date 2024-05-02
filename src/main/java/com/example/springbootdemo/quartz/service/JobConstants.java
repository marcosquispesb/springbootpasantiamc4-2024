package com.example.springbootdemo.quartz.service;

public interface JobConstants {

    String GROUP_NAME = "PASANTIA-2024";

    String KEY_ATTEMPT = "ATTEMPT";
    String KEY_MAX_ATTEMPT = "MAX_ATTEMPT";

    // CRON EXPRESIONS

    // "{SECOND} {MINUTE} {HOUR} {DAY} {MONTH} ? {YEAR}";
    String CRON_X_1_SEG = "0/1 * * * * ?";
    String CRON_X_5_SEG = "0/5 * * * * ?";
    String CRON_X_15_SEG = "0/15 * * * * ?";
    String CRON_X_30_SEG = "0/30 * * * * ?";
    String CRON_X_1_MIN = "0 0/1 * * * ?";
    String CRON_X_3_MIN = "0 0/3 * * * ?";
    String CRON_X_5_MIN = "0 0/5 * * * ?";

    String CRON_X_08_AM = "0 0 8 * * ?";
    String CRON_X_01_AM = "0 0 1 * * ?";
    String CRON_X_1_HR = "0 0 * ? * *";
    String CRON_LAST_DAY = "{SEGUNDO} {MINUTO} {HORA} L * ?";

}
