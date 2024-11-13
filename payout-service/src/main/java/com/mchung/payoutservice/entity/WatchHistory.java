package com.mchung.payoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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

    @Column(name="creatorId")
    private Long creatorId;
    @Column(name="viewedAt")
    private Date viewedAt;
    @Column(name="beginsAt")
    private Long beginsAt;
    @Column(name="lastStamp")
    private Long lastStamp;

    @Column(name="viewDone")
    private Boolean viewDone;

}
