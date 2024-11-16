package com.mchung.payoutservice.service;

public interface DailyRevenueService {
    String getDailyRevenueReport(Long memberId, String term);
}
