package com.mchung.payoutservice.scheduler;

import com.mchung.payoutservice.repository.AdWatchHistoryRepository;
import com.mchung.payoutservice.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final AdWatchHistoryRepository adWatchHistoryRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateDailyRevenue() throws InterruptedException {

    }
}
