package com.mchung.payoutservice.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mchung.payoutservice.entity.*;
import com.mchung.payoutservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class DailyRevenueServiceImpl implements DailyRevenueService {
    private final AdvertisementRepository advertisementRepository;
    private final AdWatchHistoryRepository adWatchHistoryRepository;
    private final DailyRevenueRepository dailyRevenueRepository;
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final ReveneueReportRepository reveneueReportRepository;

    public String getDailyRevenueReport(Long memberId, String term) {
        Date today = new Date(System.currentTimeMillis());
        RevenueReport revenueReport = reveneueReportRepository.findByUserIdAndEffectiveDate(memberId, today).get();
        List<DailyRevenue> todayList = dailyRevenueRepository.findDailyRevenuesByCreatorIdAndEffectiveDate(memberId, today);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        LinkedList<LinkedHashMap<String, Object>> revenueMap = new LinkedList<>();
        Member member = memberRepository.findById(memberId).get();
        JsonObject jsonObject = new JsonObject();

        for(DailyRevenue dailyRevenue : todayList) {
            LinkedHashMap<String, Object> each = new LinkedHashMap<>();
            Video video = new Video();
            Advertisement advertisement = new Advertisement();
            if(dailyRevenue.getVideoId() != null) {
                video = videoRepository.findById(dailyRevenue.getVideoId()).get();
                each.put("videoTitle", video.getTitle());
                each.put("videoLength", video.getLength());
                each.put("uploadDate", video.getUploadDate());
                each.put("views", video.getViews());

            } else if(dailyRevenue.getAdvertisementId() != null) {
                advertisement = advertisementRepository.findById(dailyRevenue.getAdvertisementId()).get();
                each.put("adViews", advertisement.getViews());
            }
            each.put("revenue", dailyRevenue.getTotalRevenue());
            each.put("revenueDate", dailyRevenue.getEffectiveDate());
            revenueMap.add(each);
        }
        map.put("member", member.getName());
        map.put("date", today);
        map.put("revenueData", revenueMap);

        return new Gson().toJson(map);
    }


}
