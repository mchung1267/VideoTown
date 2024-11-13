package com.mchung.payoutservice.repository;

import com.mchung.payoutservice.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(Long id);
}
