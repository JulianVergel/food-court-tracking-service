package com.foodcourt.tracking_service.infrastructure.configuration;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import com.foodcourt.tracking_service.domain.usecase.TraceUseCase;
import com.foodcourt.tracking_service.infrastructure.output.jpa.adapter.TraceMongoAdapter;
import com.foodcourt.tracking_service.infrastructure.output.jpa.mapper.ITraceEntityMapper;
import com.foodcourt.tracking_service.infrastructure.output.jpa.repository.ITraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;
    private final IAuthenticatedUserPort authenticatedUserPort;

    @Bean
    public ITracePersistencePort tracePersistencePort() {
        return new TraceMongoAdapter(traceRepository, traceEntityMapper);
    }

    @Bean
    public ITraceServicePort traceServicePort() {
        return new TraceUseCase(tracePersistencePort(), authenticatedUserPort);
    }
}
