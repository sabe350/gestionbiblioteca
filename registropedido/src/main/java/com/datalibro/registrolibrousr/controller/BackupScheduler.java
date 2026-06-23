package com.datalibro.registrolibrousr.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.datalibro.registrolibrousr.service.BackupService;

@Component
public class BackupScheduler {

    private final BackupService backupService;

    public BackupScheduler(BackupService backupService){
        this.backupService = backupService;
    }

    @Scheduled(cron = "0 04 23 * * ?")
    public void scheduleBackup(){
        backupService.createBackup();
    }
}
