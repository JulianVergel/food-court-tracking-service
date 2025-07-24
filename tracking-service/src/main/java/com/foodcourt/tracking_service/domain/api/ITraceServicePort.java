package com.foodcourt.tracking_service.domain.api;

import com.foodcourt.tracking_service.domain.model.Trace;

import java.util.List;

public interface ITraceServicePort {
    void saveTrace(Trace trace);
    List<Trace> getTraceForOrder(Long orderId);
}
