package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.DailyRevenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface DailyRevenueRepository extends JpaRepository<DailyRevenue, Long> {
    List<DailyRevenue> findDailyRevenuesByVideoId(Long videoId);
    List<DailyRevenue> findDailyRevenuesByAdvertisementId(Long advertisementId);
    List<DailyRevenue> findDailyRevenuesByCreatorId(Long creatorId);
    List<DailyRevenue> findDailyRevenuesByCreatorIdAndEffectiveDateBetween(Long creatorId, Date a, Date b);

}
