package com.foodcourt.tracking_service.domain.usecase;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.api.ITraceServicePort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import com.foodcourt.tracking_service.domain.exception.TraceNotFoundException;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceUseCase implements ITraceServicePort {

    private final ITracePersistencePort tracePersistencePort;
    private final IAuthenticatedUserPort authenticatedUserPort;

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
}
