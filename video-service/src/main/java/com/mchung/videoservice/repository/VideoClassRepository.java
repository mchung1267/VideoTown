package com.mchung.videoservice.repository;

import com.mchung.videoservice.entity.VideoClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoClassRepository extends JpaRepository<VideoClass, Long> {
}
