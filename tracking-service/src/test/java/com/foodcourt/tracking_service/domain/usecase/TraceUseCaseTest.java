package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import com.foodcourt.tracking_service.domain.exception.TraceNotFoundException;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceUseCaseTest {

    @Mock
    private ITracePersistencePort tracePersistencePort;

    @Mock
    private IAuthenticatedUserPort authenticatedUserPort;

    @InjectMocks
    private TraceUseCase traceUseCase;

    private Trace trace;
    private Long orderId = 1L;
    private Long customerId = 10L;

    @BeforeEach
    void setUp() {
        trace = new Trace(
                orderId,
                customerId,
                "customer@mail.com",
                LocalDateTime.now(),
                null,
                "PENDIENTE",
                null,
                null
        );
    }

    @Test
    void mustSaveTrace() {
        traceUseCase.saveTrace(trace);
        verify(tracePersistencePort, times(1)).saveTrace(trace);
    }

    @Test
    void getTraceForOrder_Success() {
        List<Trace> expectedTraceList = List.of(trace);
        when(authenticatedUserPort.getAuthenticatedUserId()).thenReturn(customerId);
        when(tracePersistencePort.getTraceForOrder(orderId)).thenReturn(expectedTraceList);

        List<Trace> actualTraceList = traceUseCase.getTraceForOrder(orderId);

        assertNotNull(actualTraceList);
        assertEquals(expectedTraceList.size(), actualTraceList.size());
        verify(tracePersistencePort, times(1)).getTraceForOrder(orderId);
        verify(authenticatedUserPort, times(1)).getAuthenticatedUserId();
    }

    @Test
    void getTraceForOrder_ThrowsAccessDeniedException_WhenUserIsNotOwner() {
        Long wrongCustomerId = 20L;
        List<Trace> traceListFromDb = List.of(trace);
        when(authenticatedUserPort.getAuthenticatedUserId()).thenReturn(wrongCustomerId);
        when(tracePersistencePort.getTraceForOrder(orderId)).thenReturn(traceListFromDb);

        assertThrows(AccessDeniedException.class, () -> {
            traceUseCase.getTraceForOrder(orderId);
        });

        verify(tracePersistencePort, times(1)).getTraceForOrder(orderId);
        verify(authenticatedUserPort, times(1)).getAuthenticatedUserId();
    }

    @Test
    void getTraceForOrder_ThrowsTraceNotFoundException_WhenNoTraceExists() {
        when(tracePersistencePort.getTraceForOrder(orderId)).thenReturn(Collections.emptyList());

        assertThrows(TraceNotFoundException.class, () -> {
            traceUseCase.getTraceForOrder(orderId);
        });

        verify(authenticatedUserPort, never()).getAuthenticatedUserId();
    }
}