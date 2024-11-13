package com.mchung.payoutservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="advertisement")
@AllArgsConstructor
@Data
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="advertiserId")
    private Long advertiserId;

    @Column (name="originId")
    private Long originId;

    @Column(name="views")
    private Long views;

    @Column(name="adClass")
    private Long adClass;


}
