package com.foodcourt.tracking_service.application.mapper.request;

import com.foodcourt.tracking_service.domain.model.Trace;
import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceRequestMapper {
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    Trace toTrace(TraceRequestDto traceRequestDto);
}
