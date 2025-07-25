package com.foodcourt.tracking_service.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePerformanceResponseDto {
    private Long employeeId;
    private Double averageDurationInMinutes;
}
