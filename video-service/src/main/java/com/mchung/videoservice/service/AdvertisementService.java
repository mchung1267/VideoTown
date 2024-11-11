package com.mchung.videoservice.service;

import com.mchung.videoservice.dto.AddAdvertisementDto;
import com.mchung.videoservice.dto.WatchAdDto;
import com.mchung.videoservice.entity.AdWatchHistory;
import com.mchung.videoservice.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {
    List<Advertisement> listAdvertisement(Long videoId);
    AdWatchHistory watchAdvertisement(WatchAdDto watchAdDto, Long lastStamp);
    List<Advertisement> createAdvertisement(AddAdvertisementDto addAdvertisementDto, Long videoId);

}
