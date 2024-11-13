package com.mchung.videoservice.service;

import com.mchung.videoservice.dto.AddAdvertisementDto;
import com.mchung.videoservice.dto.WatchAdDto;
import com.mchung.videoservice.entity.AdWatchHistory;
import com.mchung.videoservice.entity.Advertisement;
import com.mchung.videoservice.repository.AdWatchHistoryRepository;
import com.mchung.videoservice.repository.AdvertisementRepository;
import com.mchung.videoservice.repository.VideoRepository;
import com.mchung.videoservice.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdvertisementServiceImpl implements AdvertisementService {
    private final VideoRepository videoRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final AdvertisementRepository advertisementRepository;
    private final AdWatchHistoryRepository adWatchHistoryRepository;

    @Override
    public List<Advertisement> listAdvertisement(Long videoId) {
        return advertisementRepository.findAllByOriginId(videoId).stream().toList();
    }

    @Override
    public List<Advertisement> createAdvertisement(AddAdvertisementDto addAdvertisementDto, Long videoId) {
        int adCount = 0;
        Long length = addAdvertisementDto.getVideoLength();
        List<Advertisement> ads = new ArrayList<>();
        if(length >= 300 && length < 600) {
            adCount = 1;
        }
        if(length >= 600) {
            adCount = 2;
        }
        for(int i = 0; i < adCount; i++) {
            Advertisement advertisement = new Advertisement();
            advertisement.setOriginId(videoId);
            advertisement.setAdvertiserId(addAdvertisementDto.getAdvertiserId());
            advertisement.setViews(0L);
            advertisement.setAdClass(1L);
            ads.add(advertisement);
        }

        return advertisementRepository.saveAll(ads);
    }
    @Override
    public AdWatchHistory watchAdvertisement(WatchAdDto watchAdDto, Long lastStamp) {
        AdWatchHistory adWatchHistory = new AdWatchHistory();
        adWatchHistory.setAdId(watchAdDto.getAdId());
        adWatchHistory.setViewerId(watchAdDto.getViewerId());
        adWatchHistory.setCreatorId(watchAdDto.getCreatorId());
        adWatchHistory.setViewedAt(new Date(System.currentTimeMillis()));
        if(lastStamp >= 15 && lastStamp <= 300) {
            adWatchHistory.setFullyWatched(true);
        } else if(lastStamp >= 330) {
            adWatchHistory.setFullyWatched(true);
        } else {
            adWatchHistory.setFullyWatched(false);
        }
        return adWatchHistoryRepository.save(adWatchHistory);
    }
}
