package com.foodcourt.tracking_service.application.mapper.response;

import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;
import com.foodcourt.tracking_service.domain.model.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceResponseMapper {
    List<TraceResponseDto> toResponseDtoList(List<Trace> traceList);
}
