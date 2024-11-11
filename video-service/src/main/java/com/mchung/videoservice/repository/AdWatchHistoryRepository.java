package com.mchung.videoservice.repository;

import com.mchung.videoservice.entity.AdWatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdWatchHistoryRepository extends JpaRepository<AdWatchHistory, Long> {
}
