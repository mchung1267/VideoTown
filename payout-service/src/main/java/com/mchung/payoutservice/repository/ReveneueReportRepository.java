package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.RevenueReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReveneueReportRepository extends JpaRepository<RevenueReport, Long> {
    Optional<RevenueReport> findByUserIdAndEffectiveDate(Long userId, Date effectiveDate);
    List<RevenueReport> findByUserIdAndEffectiveDateBetween(Long userId, Date begin, Date end);
}
