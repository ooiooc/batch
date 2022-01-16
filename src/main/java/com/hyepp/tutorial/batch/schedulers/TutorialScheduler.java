package com.hyepp.tutorial.batch.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TutorialScheduler {

    private final Job job; // tutorialJob
    private final JobLauncher jobLauncher;

    // 5초마다 실행
    // 일정 주기마다 작성한 Job이 실행되도록 설정
    @Scheduled(fixedDelay = 5 * 1000L)
    public void executeJob() {
        try {
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters() // job parameter 설정
            );                                 // job parameter는 실행되는 Job의 유일한 id 개념
        } catch (JobExecutionException e) {
            System.out.println();
            e.printStackTrace();
        }
    }

}
