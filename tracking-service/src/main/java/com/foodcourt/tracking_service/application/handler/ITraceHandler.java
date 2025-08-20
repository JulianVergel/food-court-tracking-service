package com.foodcourt.tracking_service.application.handler;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.dto.response.EmployeePerformanceResponseDto;
import com.foodcourt.tracking_service.application.dto.response.OrderDurationResponseDto;
import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;

import java.util.List;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);
    List<TraceResponseDto> getTraceForOrder(Long orderId);
    List<OrderDurationResponseDto> getOrderDurations(Long restaurantId);
    List<EmployeePerformanceResponseDto> getEmployeeRanking(Long restaurantId);
}
