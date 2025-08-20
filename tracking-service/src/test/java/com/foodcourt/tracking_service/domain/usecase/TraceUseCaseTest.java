package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import com.foodcourt.tracking_service.domain.exception.TraceNotFoundException;
import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import com.foodcourt.tracking_service.domain.utils.validations.OwnershipValidator;
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

    @Mock
    private OwnershipValidator ownershipValidator;

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
                null,
                5L
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

    @Test
    void mustGetOrderDurations() {
        // Arrange
        Long restaurantId = 1L;
        List<OrderDuration> expectedDurations = List.of(new OrderDuration(101L, 15L));

        // Simulamos que la validación de propiedad no lanza excepción
        doNothing().when(ownershipValidator).validateRestaurantOwnership(restaurantId);
        // Simulamos la respuesta del puerto de persistencia
        when(tracePersistencePort.getOrderDurationsForRestaurant(restaurantId)).thenReturn(expectedDurations);

        // Act
        List<OrderDuration> actualDurations = traceUseCase.getOrderDurationsForRestaurant(restaurantId);

        // Assert
        assertEquals(expectedDurations, actualDurations);
        verify(ownershipValidator, times(1)).validateRestaurantOwnership(restaurantId);
        verify(tracePersistencePort, times(1)).getOrderDurationsForRestaurant(restaurantId);
    }

    @Test
    void mustGetEmployeePerformanceRanking() {
        // Arrange
        Long restaurantId = 1L;
        List<EmployeePerformance> expectedRanking = List.of(new EmployeePerformance(66L, 12.5));

        // Simulamos la validación y la persistencia
        doNothing().when(ownershipValidator).validateRestaurantOwnership(restaurantId);
        when(tracePersistencePort.getEmployeePerformanceForRestaurant(restaurantId)).thenReturn(expectedRanking);

        // Act
        List<EmployeePerformance> actualRanking = traceUseCase.getEmployeePerformanceForRestaurant(restaurantId);

        // Assert
        assertEquals(expectedRanking, actualRanking);
        verify(ownershipValidator, times(1)).validateRestaurantOwnership(restaurantId);
        verify(tracePersistencePort, times(1)).getEmployeePerformanceForRestaurant(restaurantId);
    }
}