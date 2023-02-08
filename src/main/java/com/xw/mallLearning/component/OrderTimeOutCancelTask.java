package com.xw.mallLearning.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTimeOutCancelTask {

    @Scheduled(cron = "0/2 * * * * *")
    private void cancelTimeOutOrder() {
        log.info("schedule task");
    }
}
