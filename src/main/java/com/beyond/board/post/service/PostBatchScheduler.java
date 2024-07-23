//package com.beyond.board.post.service;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PostBatchScheduler {
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job excuteJob;
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void batchSchedule() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters();
//        try {
//            jobLauncher.run(excuteJob, jobParameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}