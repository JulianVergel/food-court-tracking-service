package com.foodcourt.tracking_service.application.handler.impl;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;
import com.foodcourt.tracking_service.application.handler.ITraceHandler;
import com.foodcourt.tracking_service.application.mapper.request.ITraceRequestMapper;
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

    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        traceServicePort.saveTrace(traceRequestMapper.toTrace(traceRequestDto));
    }

    @Override
    public List<TraceResponseDto> getTraceForOrder(Long orderId) {
        // 1. Llama al caso de uso del dominio, que devuelve una lista de modelos Trace
        var traceModelList = traceServicePort.getTraceForOrder(orderId);

        // 2. Usa el mapper para convertir la lista de modelos a una lista de DTOs de respuesta
        return traceResponseMapper.toResponseDtoList(traceModelList);
    }
}
