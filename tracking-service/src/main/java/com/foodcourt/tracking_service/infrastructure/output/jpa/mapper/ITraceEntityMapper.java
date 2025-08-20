package com.foodcourt.tracking_service.infrastructure.output.jpa.mapper;

import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.infrastructure.output.jpa.entity.TraceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceEntityMapper {
    TraceEntity toEntity(Trace trace);
    List<Trace> toTraceList(List<TraceEntity> traceEntityList);
}
