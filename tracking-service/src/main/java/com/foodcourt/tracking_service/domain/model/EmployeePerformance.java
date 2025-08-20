package com.foodcourt.tracking_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePerformance {
    private Long employeeId;
    private Double averageDurationInMinutes;
}
