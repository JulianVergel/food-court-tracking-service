package com.foodcourt.tracking_service.application.handler;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);
}
