package com.foodcourt.tracking_service.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TraceResponseDto {
    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private LocalDateTime date;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeEmail;
    private Long restaurantId;
}
