package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceUseCase implements ITraceServicePort {

    private final ITracePersistencePort tracePersistencePort;

    @Override
    public void saveTrace(Trace trace) {
        tracePersistencePort.saveTrace(trace);
    }
}
