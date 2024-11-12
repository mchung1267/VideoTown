package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.AdWatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdWatchHistoryRepository extends JpaRepository<AdWatchHistory, Long> {
    List<AdWatchHistory> findAdWatchHistoriesByCreatorId(Long creatorId);

}
