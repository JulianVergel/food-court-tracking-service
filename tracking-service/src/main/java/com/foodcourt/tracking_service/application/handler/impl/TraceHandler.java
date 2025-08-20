package com.foodcourt.tracking_service.application.handler.impl;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.dto.response.EmployeePerformanceResponseDto;
import com.foodcourt.tracking_service.application.dto.response.OrderDurationResponseDto;
import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;
import com.foodcourt.tracking_service.application.handler.ITraceHandler;
import com.foodcourt.tracking_service.application.mapper.request.ITraceRequestMapper;
import com.foodcourt.tracking_service.application.mapper.response.IEfficiencyResponseMapper;
import com.foodcourt.tracking_service.application.mapper.response.ITraceResponseMapper;
import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TraceHandler implements ITraceHandler {

    private final ITraceServicePort traceServicePort;
    private final ITraceRequestMapper traceRequestMapper;
    private final ITraceResponseMapper traceResponseMapper;
    private final IEfficiencyResponseMapper efficiencyResponseMapper;

    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        traceServicePort.saveTrace(traceRequestMapper.toTrace(traceRequestDto));
    }

    @Override
    public List<TraceResponseDto> getTraceForOrder(Long orderId) {
        var traceModelList = traceServicePort.getTraceForOrder(orderId);
        return traceResponseMapper.toResponseDtoList(traceModelList);
    }

    @Override
    public List<OrderDurationResponseDto> getOrderDurations(Long restaurantId) {
        var domainResult = traceServicePort.getOrderDurationsForRestaurant(restaurantId);
        return efficiencyResponseMapper.toOrderDurationDtoList(domainResult);
    }

    @Override
    public List<EmployeePerformanceResponseDto> getEmployeeRanking(Long restaurantId) {
        var domainResult = traceServicePort.getEmployeePerformanceForRestaurant(restaurantId);
        return efficiencyResponseMapper.toEmployeePerformanceDtoList(domainResult);
    }
}
