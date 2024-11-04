package com.mchung.videoservice.controller;

import com.mchung.videoservice.dto.UploadVideoDto;
import com.mchung.videoservice.dto.WatchVideoDto;
import com.mchung.videoservice.entity.Video;
import com.mchung.videoservice.entity.WatchHistory;
import com.mchung.videoservice.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video-service")
@RequiredArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to video service";
    }

    @PostMapping("/upload")
    public Video upload(HttpServletRequest request, @RequestBody UploadVideoDto uploadVideoDto) {
        log.info("upload video : {}", uploadVideoDto);

        Video video = videoService.uploadVideo(1L, uploadVideoDto);

        return video;
    }
    @PostMapping("/watch")
    public WatchHistory watch(HttpServletRequest request, @RequestBody WatchVideoDto watchVideoDto) {
        log.info("watch video : {}", watchVideoDto);
        WatchHistory watchHistory = videoService.watchVideo(2L, watchVideoDto);
        return watchHistory;
    }
}
