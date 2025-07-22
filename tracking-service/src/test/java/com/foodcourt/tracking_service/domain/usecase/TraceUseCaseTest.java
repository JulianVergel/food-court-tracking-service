package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TraceUseCaseTest {

    @Mock
    private ITracePersistencePort tracePersistencePort;

    @InjectMocks
    private TraceUseCase traceUseCase;

    private Trace trace;

    @BeforeEach
    void setUp() {
        // Preparamos un objeto Trace de prueba que usaremos en el test
        trace = new Trace(
                123L,
                456L,
                "customer@mail.com",
                LocalDateTime.now(),
                "PENDIENTE",
                "EN_PREPARACION",
                789L,
                "employee@mail.com"
        );
    }

    @Test
    void mustSaveTrace() {
        traceUseCase.saveTrace(trace);

        verify(tracePersistencePort, times(1)).saveTrace(trace);
    }
}
