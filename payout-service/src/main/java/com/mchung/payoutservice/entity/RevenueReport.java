package com.mchung.payoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="revenueReport")
@AllArgsConstructor
public class RevenueReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userId")
    private Long userId;

    @Column(name="effectiveDate")
    private Date effectiveDate;

    @Column(name="revenue")
    private Double revenue;
}
