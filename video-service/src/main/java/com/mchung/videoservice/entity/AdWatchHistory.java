package com.mchung.videoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name="adWatchHistory")
@AllArgsConstructor
public class AdWatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adId")
    private Long adId;

    @Column(name="creatorId")
    private Long creatorId;

    @Column(name="viewerId")
    private Long viewerId;

    @Column(name="viewedAt")
    private Date viewedAt;

    @Column(name="fullyWatched")
    private Boolean fullyWatched;
}
