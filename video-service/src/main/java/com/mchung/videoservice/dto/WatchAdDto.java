package com.mchung.videoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchAdDto {
    private Long adId;
    private Long viewerId;
    private Long creatorId;
}
