package com.mchung.videoservice.service;

import com.mchung.videoservice.dto.UploadVideoDto;
import com.mchung.videoservice.dto.WatchVideoDto;
import com.mchung.videoservice.entity.Video;
import com.mchung.videoservice.entity.WatchHistory;
import com.mchung.videoservice.repository.VideoRepository;
import com.mchung.videoservice.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    @Override
    @Transactional
    public Video uploadVideo(Long userId, UploadVideoDto uploadVideoDto) {
        try {
            Video video = createNewVideo(userId, uploadVideoDto);
            return video;
        } catch (DataAccessException e) {
            log.error("동영상 업로드 중 데이터베이스 오류 발생", e);
            throw new IllegalArgumentException("데이터 업로드에 실패했습니다");
        } catch (Exception e) {
            log.error("예기치 못한 오류 발생");
            throw new IllegalArgumentException("저장 중 예기치 않은 오류가 발생했습니다");
        }
    }

    @Override
    public WatchHistory watchVideo(Long userId, WatchVideoDto watchVideoDto) {
        try {
            WatchHistory watchHistory = createWatchHistory(userId, watchVideoDto);
            return watchHistory;
        } catch (DataAccessException e) {
            log.error("동영상 시청 처리 중 데이터베이스 오류 발생", e);
            throw new IllegalArgumentException("영상 시청 처리 중 오류가 발생했습니다");
        } catch (Exception e) {
            log.error("예기치 못한 오류 발생");
            throw new IllegalArgumentException("시청 처리 중 오류가 발생했습니다");
        }
    }

    private WatchHistory createWatchHistory(Long userId, WatchVideoDto watchVideoDto) {
        WatchHistory watchHistory = new WatchHistory();
        List<WatchHistory> existingViews = watchHistoryRepository.findByVideoIdAndViewerId(watchVideoDto.getVideoId(), userId);
        WatchHistory alreadyExist = existingViews.getLast();
        watchHistory.setViewerId(userId);
        watchHistory.setVideoId(watchVideoDto.getVideoId());
        watchHistory.setViewedAt(new Timestamp(System.currentTimeMillis()));
        if (alreadyExist != null) {

            watchHistory.setBeginsAt(alreadyExist.getLastStamp());
            if(alreadyExist.getLastStamp() > watchVideoDto.getLastStamp()) {
                log.error("정상적이지 않은 시청 시간");
                throw new IllegalArgumentException("정상적인 영상 시청 기록이 아닙니다");
            } else {
                watchHistory.setLastStamp(watchVideoDto.getLastStamp());
            }
        } else {
            watchHistory.setBeginsAt(0L);
            watchHistory.setLastStamp(watchVideoDto.getLastStamp());
        }

        return finalizeWatch(watchHistory);
    }
    private WatchHistory finalizeWatch(WatchHistory watchHistory) {
        try {
            return watchHistoryRepository.save(watchHistory);
        } catch (DataAccessException e) {
            log.error("동영상 시청 처리 중 데이터베이스 오류 발생", e);
            throw new IllegalArgumentException("영상 시청 처리 중 오류가 발생했습니다");
        } catch (Exception e) {
            log.error("예기치 못한 오류 발생");
            throw new IllegalArgumentException("시청 처리 중 오류가 발생했습니다");
        }
    }
    private Video createNewVideo(Long userId, UploadVideoDto uploadVideoDto) {
        Video video = new Video();
        video.setCreatorId(userId);
        video.setRevenueClass(1L);
        video.setViews(0L);
        video.setUploadDate(new Timestamp(System.currentTimeMillis()));
        video.setLength(uploadVideoDto.getLength());
        video.setTitle(uploadVideoDto.getTitle());
        return finalizeUpload(video);

    }
    private Video finalizeUpload(Video video) {
        try {
            return videoRepository.save(video);
        } catch (DataAccessException e) {
            log.error("동영상 업로드 중 데이터베이스 오류 발생", e);
            throw new IllegalArgumentException("데이터 업로드에 실패했습니다");
        } catch (Exception e) {
            log.error("예기치 못한 오류 발생");
            throw new IllegalArgumentException("저장 중 예기치 않은 오류가 발생했습니다");
        }
    }

}
