package com.mchung.payoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="dailyRevenue")
@AllArgsConstructor
public class DailyRevenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="creatorId")
    private Long creatorId;

    @Column(name="videoId")
    private Long videoId;

    @Column(name="advertisementId")
    private Long advertisementId;

    @Column(name="totalRevenue")
    private Long totalRevenue;

    @Column(name="effectiveDate")
    private Date effectiveDate;
}
