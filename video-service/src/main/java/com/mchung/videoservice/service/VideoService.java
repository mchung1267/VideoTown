package com.mchung.videoservice.service;

import com.mchung.videoservice.dto.UploadVideoDto;
import com.mchung.videoservice.dto.WatchVideoDto;
import com.mchung.videoservice.entity.Video;
import com.mchung.videoservice.entity.WatchHistory;

public interface VideoService {
    Video uploadVideo(Long userId, UploadVideoDto uploadVideoDto);

    WatchHistory watchVideo(Long userId, WatchVideoDto watchVideoDto);
}
