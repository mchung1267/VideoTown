package com.mchung.payoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Table(name="videos")
@AllArgsConstructor
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="creatorId")
    private Long creatorId;

    @Column(name="revenueClass")
    private Long revenueClass;

    @Column(name="views")
    private Long views;

    @Column(name="length")
    private Long length;

    @Column(name="totalLength")
    private Long totalLength;

    @Column(name="adCoordination")
    private String adCoordination;

    @Column(name="uploadDate")
    private Timestamp uploadDate;

    @Column(name="title")
    private String title;



}
