package com.foodcourt.tracking_service.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TraceTest {

    @Test
    void testTraceModel() {
        // Arrange: Preparamos los datos de prueba
        Long orderId = 1L;
        Long customerId = 2L;
        String customerEmail = "test@example.com";
        LocalDateTime date = LocalDateTime.now();
        String previousStatus = "PENDIENTE";
        String newStatus = "EN_PREPARACION";
        Long employeeId = 3L;
        String employeeEmail = "employee@example.com";

        // Act & Assert: Probamos el constructor con todos los argumentos y los getters
        Trace traceWithAllArgs = new Trace(orderId, customerId, customerEmail, date, previousStatus, newStatus, employeeId, employeeEmail);

        assertEquals(orderId, traceWithAllArgs.getOrderId());
        assertEquals(customerId, traceWithAllArgs.getCustomerId());
        assertEquals(customerEmail, traceWithAllArgs.getCustomerEmail());
        assertEquals(date, traceWithAllArgs.getDate());
        assertEquals(previousStatus, traceWithAllArgs.getPreviousStatus());
        assertEquals(newStatus, traceWithAllArgs.getNewStatus());
        assertEquals(employeeId, traceWithAllArgs.getEmployeeId());
        assertEquals(employeeEmail, traceWithAllArgs.getEmployeeEmail());

        // Act & Assert: Probamos el constructor sin argumentos y los setters
        Trace traceWithNoArgs = new Trace();
        traceWithNoArgs.setOrderId(orderId);
        traceWithNoArgs.setCustomerId(customerId);
        traceWithNoArgs.setCustomerEmail(customerEmail);
        traceWithNoArgs.setDate(date);
        traceWithNoArgs.setPreviousStatus(previousStatus);
        traceWithNoArgs.setNewStatus(newStatus);
        traceWithNoArgs.setEmployeeId(employeeId);
        traceWithNoArgs.setEmployeeEmail(employeeEmail);

        // Verificamos que los valores se asignaron correctamente
        assertEquals(orderId, traceWithNoArgs.getOrderId());
        assertEquals(customerEmail, traceWithNoArgs.getCustomerEmail());
    }
}
