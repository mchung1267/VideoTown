package com.mchung.videoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchVideoDto {
    private Long videoId;
    private Long lastStamp;
}
