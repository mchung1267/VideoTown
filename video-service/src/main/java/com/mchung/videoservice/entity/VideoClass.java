package com.mchung.videoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="videoClass")
@AllArgsConstructor
@Data
public class VideoClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="revenuePerWatch")
    private Float revenuePerWatch;
}
