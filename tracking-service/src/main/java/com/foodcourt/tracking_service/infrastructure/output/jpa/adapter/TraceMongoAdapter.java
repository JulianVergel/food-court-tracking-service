package com.foodcourt.tracking_service.infrastructure.output.jpa.adapter;

import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.domain.spi.ITracePersistencePort;
import com.foodcourt.tracking_service.infrastructure.output.jpa.entity.TraceEntity;
import com.foodcourt.tracking_service.infrastructure.output.jpa.mapper.ITraceEntityMapper;
import com.foodcourt.tracking_service.infrastructure.output.jpa.repository.ITraceRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceMongoAdapter implements ITracePersistencePort {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public void saveTrace(Trace trace) {
        traceRepository.save(traceEntityMapper.toEntity(trace));
    }

    @Override
    public List<Trace> getTraceForOrder(Long orderId) {
        List<TraceEntity> traceEntityList = traceRepository.findByOrderIdOrderByDateAsc(orderId);
        return traceEntityMapper.toTraceList(traceEntityList);
    }

    @Override
    public List<OrderDuration> getOrderDurationsForRestaurant(Long restaurantId) {
        return traceRepository.getOrderDurationsForRestaurant(restaurantId);
    }

    @Override
    public List<EmployeePerformance> getEmployeePerformanceForRestaurant(Long restaurantId) {
        return traceRepository.getEmployeePerformanceForRestaurant(restaurantId);
    }
}
