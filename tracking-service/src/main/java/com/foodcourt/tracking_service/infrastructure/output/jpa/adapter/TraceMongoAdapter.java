package com.foodcourt.tracking_service.infrastructure.output.jpa.adapter;

import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import com.foodcourt.tracking_service.infrastructure.output.jpa.mapper.ITraceEntityMapper;
import com.foodcourt.tracking_service.infrastructure.output.jpa.repository.ITraceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceMongoAdapter implements ITracePersistencePort {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public void saveTrace(Trace trace) {
        traceRepository.save(traceEntityMapper.toEntity(trace));
    }
}
