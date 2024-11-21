package com.mchung.videoservice.controller;

import com.mchung.videoservice.dto.UploadVideoDto;
import com.mchung.videoservice.dto.WatchVideoDto;
import com.mchung.videoservice.entity.Video;
import com.mchung.videoservice.entity.WatchHistory;
import com.mchung.videoservice.service.VideoService;
import com.mchung.videoservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
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
    private final JwtUtil jwtUtil;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to video service";
    }

    @PostMapping("/upload")
    public Video upload(HttpServletRequest request, @RequestBody UploadVideoDto uploadVideoDto, @RequestHeader (name="Authorization") String token) {
        log.info("upload video : {}", uploadVideoDto);
        Claims claims = jwtUtil.getUserInfoFromToken(token.split(" ")[1].trim());
        Long userId = Long.valueOf((int)claims.get("id"));

        Video video = videoService.uploadVideo(userId, uploadVideoDto);

        return video;
    }
    @PostMapping("/watch")
    public WatchHistory watch(HttpServletRequest request, @RequestBody WatchVideoDto watchVideoDto, @RequestHeader (name="Authorization") String token) {
        log.info("watch video : {}", watchVideoDto);
        Claims claims = jwtUtil.getUserInfoFromToken(token.split(" ")[1].trim());
        Long userId = Long.valueOf((int)claims.get("id"));

        WatchHistory watchHistory = videoService.watchVideo(userId, watchVideoDto);
        return watchHistory;
    }
}
