package com.foodcourt.tracking_service.domain.spi;

import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import com.foodcourt.tracking_service.domain.model.Trace;

import java.util.List;

public interface ITracePersistencePort {
    void saveTrace(Trace trace);
    List<Trace> getTraceForOrder(Long orderId);
    List<OrderDuration> getOrderDurationsForRestaurant(Long restaurantId);
    List<EmployeePerformance> getEmployeePerformanceForRestaurant(Long restaurantId);
}
