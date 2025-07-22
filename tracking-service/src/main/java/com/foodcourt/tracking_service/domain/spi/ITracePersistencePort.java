package com.foodcourt.tracking_service.domain.spi;

import com.foodcourt.tracking_service.domain.model.Trace;

public interface ITracePersistencePort {
    void saveTrace(Trace trace);
}
