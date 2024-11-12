package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByVideoIdAndViewerId(Long videoId, Long userId);
}
