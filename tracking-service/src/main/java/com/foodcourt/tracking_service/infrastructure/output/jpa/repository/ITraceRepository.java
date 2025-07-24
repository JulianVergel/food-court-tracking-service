package com.foodcourt.tracking_service.infrastructure.output.jpa.repository;

import com.foodcourt.tracking_service.infrastructure.output.jpa.entity.TraceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITraceRepository extends MongoRepository<TraceEntity, String> {
    List<TraceEntity> findByOrderIdOrderByDateAsc(Long orderId);
}
