package com.foodcourt.tracking_service.infrastructure.input.rest;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.dto.response.EmployeePerformanceResponseDto;
import com.foodcourt.tracking_service.application.dto.response.OrderDurationResponseDto;
import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;
import com.foodcourt.tracking_service.application.handler.ITraceHandler;
import com.foodcourt.tracking_service.infrastructure.input.doc.ApiResponsesStandard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foodcourt.tracking_service.infrastructure.input.doc.SwaggerConstants.*;

@RestController
@RequestMapping("/api/v1/trace")
@RequiredArgsConstructor
public class TraceRestController {

    private final ITraceHandler traceHandler;

    @Operation(summary = SAVE_TRACE_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = TRACE_CREATED_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "401", description = UNAUTHORIZED_DESCRIPTION, content = @Content)
    })
    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> saveTrace(@RequestBody TraceRequestDto traceRequestDto) {
        traceHandler.saveTrace(traceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = GET_TRACE_SUMMARY)
    @ApiResponsesStandard
    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_Cliente')")
    public ResponseEntity<List<TraceResponseDto>> getTraceForOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(traceHandler.getTraceForOrder(orderId));
    }

    @Operation(summary = GET_DURATIONS_SUMMARY)
    @ApiResponsesStandard
    @GetMapping("/durations/{restaurantId}")
    @PreAuthorize("hasAuthority('ROLE_Propietario')")
    public ResponseEntity<List<OrderDurationResponseDto>> getOrderDurations(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(traceHandler.getOrderDurations(restaurantId));
    }

    @Operation(summary = GET_RANKING_SUMMARY)
    @ApiResponsesStandard
    @GetMapping("/ranking/{restaurantId}")
    @PreAuthorize("hasAuthority('ROLE_Propietario')")
    public ResponseEntity<List<EmployeePerformanceResponseDto>> getEmployeeRanking(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(traceHandler.getEmployeeRanking(restaurantId));
    }
}
