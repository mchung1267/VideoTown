package com.mchung.payoutservice.service;

import com.mchung.payoutservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DailyRevenueServiceImpl implements DailyRevenueService {
    AdvertisementRepository advertisementRepository;
    AdWatchHistoryRepository adWatchHistoryRepository;
    DailyRevenueRepository dailyRevenueRepository;
    MemberRepository memberRepository;
    VideoRepository videoRepository;
    WatchHistoryRepository watchHistoryRepository;


}
