package com.foodcourt.tracking_service.infrastructure.input.rest;

import com.foodcourt.tracking_service.application.dto.request.TraceRequestDto;
import com.foodcourt.tracking_service.application.dto.response.TraceResponseDto;
import com.foodcourt.tracking_service.application.handler.ITraceHandler;
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

@RestController
@RequestMapping("/api/v1/trace")
@RequiredArgsConstructor
public class TraceRestController {

    private final ITraceHandler traceHandler;

    @Operation(summary = "Realizar un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido realizado correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> saveTrace(@RequestBody TraceRequestDto traceRequestDto) {
        traceHandler.saveTrace(traceRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener la trazabilidad de un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trazabilidad retornada correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, rol incorrecto")
    })
    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_Cliente')")
    public ResponseEntity<List<TraceResponseDto>> getTraceForOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(traceHandler.getTraceForOrder(orderId));
    }
}
