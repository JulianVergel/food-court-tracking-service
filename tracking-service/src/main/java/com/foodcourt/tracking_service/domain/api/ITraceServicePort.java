package com.foodcourt.tracking_service.domain.api;

import com.foodcourt.tracking_service.domain.model.Trace;

public interface ITraceServicePort {
    void saveTrace(Trace trace);
}
