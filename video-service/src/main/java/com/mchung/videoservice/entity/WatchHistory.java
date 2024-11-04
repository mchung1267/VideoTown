package com.mchung.videoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Table(name="watchHistory")
@AllArgsConstructor
@Data
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="videoId")
    private Long videoId;

    @Column(name="viewerId")
    private Long viewerId;

    @Column(name="viewedAt")
    private Timestamp viewedAt;
    @Column(name="beginsAt")
    private Long beginsAt;
    @Column(name="lastStamp")
    private Long lastStamp;

}
