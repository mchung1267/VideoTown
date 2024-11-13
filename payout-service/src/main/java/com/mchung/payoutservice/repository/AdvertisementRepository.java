package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<Advertisement> findAllByOriginId(Long videoId);
}
