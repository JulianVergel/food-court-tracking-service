package com.foodcourt.tracking_service.application.mapper.response;

import com.foodcourt.tracking_service.application.dto.response.EmployeePerformanceResponseDto;
import com.foodcourt.tracking_service.application.dto.response.OrderDurationResponseDto;
import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEfficiencyResponseMapper {
    List<OrderDurationResponseDto> toOrderDurationDtoList(List<OrderDuration> orderDurationList);
    List<EmployeePerformanceResponseDto> toEmployeePerformanceDtoList(List<EmployeePerformance> employeePerformanceList);
}
