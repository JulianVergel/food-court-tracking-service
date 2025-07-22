package com.foodcourt.tracking_service.infrastructure.output.jpa.repository;

import com.foodcourt.tracking_service.infrastructure.output.jpa.entity.TraceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITraceRepository extends MongoRepository<TraceEntity, String> {
}
