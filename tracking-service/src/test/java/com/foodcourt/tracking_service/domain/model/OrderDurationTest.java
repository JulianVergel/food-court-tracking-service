package com.foodcourt.tracking_service.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDurationTest {
    @Test
    void testModelGettersSettersAndConstructors() {
        // Arrange
        Long orderId = 101L;
        Long duration = 15L;

        // Act & Assert: Prueba del constructor con todos los argumentos y los getters
        OrderDuration modelWithAllArgs = new OrderDuration(orderId, duration);
        assertEquals(orderId, modelWithAllArgs.getOrderId());
        assertEquals(duration, modelWithAllArgs.getDurationInMinutes());

        // Act & Assert: Prueba del constructor sin argumentos y los setters
        OrderDuration modelWithNoArgs = new OrderDuration();
        modelWithNoArgs.setOrderId(orderId);
        modelWithNoArgs.setDurationInMinutes(duration);

        assertEquals(orderId, modelWithNoArgs.getOrderId());
        assertEquals(duration, modelWithNoArgs.getDurationInMinutes());
    }
}
