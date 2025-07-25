package com.foodcourt.tracking_service.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDurationResponseDto {
    private Long orderId;
    private Long durationInMinutes;
}
