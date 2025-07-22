package com.foodcourt.tracking_service.application.handler.impl;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.handler.ITraceHandler;
import com.foodcourt.tracking_service.application.mapper.request.ITraceRequestMapper;
import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TraceHandler implements ITraceHandler {

    private final ITraceServicePort traceServicePort;
    private final ITraceRequestMapper traceRequestMapper;

    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        traceServicePort.saveTrace(traceRequestMapper.toTrace(traceRequestDto));
    }
}
