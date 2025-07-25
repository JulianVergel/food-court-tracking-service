package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import com.foodcourt.tracking_service.domain.exception.TraceNotFoundException;
import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import com.foodcourt.tracking_service.domain.utils.validations.OwnershipValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceUseCase implements ITraceServicePort {

    private final ITracePersistencePort tracePersistencePort;
    private final IAuthenticatedUserPort authenticatedUserPort;
    private final OwnershipValidator ownershipValidator;

    @Override
    public void saveTrace(Trace trace) {
        tracePersistencePort.saveTrace(trace);
    }

    @Override
    public List<Trace> getTraceForOrder(Long orderId) {
        List<Trace> traceList = tracePersistencePort.getTraceForOrder(orderId);

        if (traceList.isEmpty()) {
            throw new TraceNotFoundException();
        }

        Long authenticatedUserId = authenticatedUserPort.getAuthenticatedUserId();
        Long orderOwnerId = traceList.get(0).getCustomerId();

        if (!orderOwnerId.equals(authenticatedUserId)) {
            throw new AccessDeniedException();
        }

        return traceList;
    }

    @Override
    public List<OrderDuration> getOrderDurationsForRestaurant(Long restaurantId) {
        ownershipValidator.validateRestaurantOwnership(restaurantId);
        return tracePersistencePort.getOrderDurationsForRestaurant(restaurantId);
    }

    @Override
    public List<EmployeePerformance> getEmployeePerformanceForRestaurant(Long restaurantId) {
        ownershipValidator.validateRestaurantOwnership(restaurantId);
        return tracePersistencePort.getEmployeePerformanceForRestaurant(restaurantId);
    }
}
