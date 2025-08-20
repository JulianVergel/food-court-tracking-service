package com.foodcourt.tracking_service.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeePerformanceTest {
    @Test
    void testModelGettersSettersAndConstructors() {
        // Arrange
        Long employeeId = 66L;
        Double averageDurationInMinutes = 2.4;

        // Act & Assert: Prueba del constructor con todos los argumentos y los getters
        EmployeePerformance modelWithAllArgs = new EmployeePerformance(employeeId, averageDurationInMinutes);
        assertEquals(employeeId, modelWithAllArgs.getEmployeeId());
        assertEquals(averageDurationInMinutes, modelWithAllArgs.getAverageDurationInMinutes());

        // Act & Assert: Prueba del constructor sin argumentos y los setters
        EmployeePerformance modelWithNoArgs = new EmployeePerformance();
        modelWithNoArgs.setEmployeeId(employeeId);
        modelWithNoArgs.setAverageDurationInMinutes(averageDurationInMinutes);

        assertEquals(employeeId, modelWithNoArgs.getEmployeeId());
        assertEquals(averageDurationInMinutes, modelWithNoArgs.getAverageDurationInMinutes());
    }
}
