package com.mchung.videoservice.repository;

import com.mchung.videoservice.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
