package com.mchung.videoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAdvertisementDto {
    Long videoLength;
    Long advertiserId;
}
