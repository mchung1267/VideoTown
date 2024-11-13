package com.mchung.payoutservice.scheduler;

import com.mchung.payoutservice.entity.*;
import com.mchung.payoutservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final AdWatchHistoryRepository adWatchHistoryRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final DailyRevenueRepository dailyRevenueRepository;
    private final MemberRepository memberRepository;
    private final AdvertisementRepository advertisementRepository;
    private final VideoRepository videoRepository;

    @Scheduled(cron = "00 59 23 * * *")
    public void updateDailyRevenue() throws InterruptedException {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            double revenue = 0.0;
            Date today = new Date(System.currentTimeMillis());
            List<AdWatchHistory> memberAdsWatched = adWatchHistoryRepository.findAdWatchHistoriesByCreatorIdAndViewedAt(member.getId(), today);
            List<WatchHistory> memberVideosWatched = watchHistoryRepository.findByCreatorIdAndViewedAt(member.getId(), today);
            for (AdWatchHistory adWatchHistory : memberAdsWatched) {
                Advertisement adInHisotry = advertisementRepository.findById(adWatchHistory.getAdId()).get();
                if(adWatchHistory.getFullyWatched()) {
                    if(adInHisotry.getAdClass() == 1) {
                        revenue += 10;
                    } else if(adInHisotry.getAdClass() == 2) {
                        revenue += 12;
                    } else if(adInHisotry.getAdClass() == 3) {
                        revenue += 15;
                    } else if(adInHisotry.getAdClass() == 4) {
                        revenue += 20;
                    }
                }
            }
            for (WatchHistory watchHistory : memberVideosWatched) {
                Video videoInHistory = videoRepository.findById(watchHistory.getVideoId()).get();
                if(watchHistory.getViewDone()) {
                    if(videoInHistory.getRevenueClass() == 1) {
                        revenue += 1;
                    } else if(videoInHistory.getRevenueClass() == 2) {
                        revenue += 1.1;
                    } else if(videoInHistory.getRevenueClass() == 3) {
                        revenue += 1.3;
                    } else if(videoInHistory.getRevenueClass() == 4) {
                        revenue += 1.5;
                    }
                }
            }
        }
    }
}
