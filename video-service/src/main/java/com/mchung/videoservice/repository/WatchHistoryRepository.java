package com.mchung.videoservice.repository;

import com.mchung.videoservice.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
}
