package com.foodcourt.tracking_service.infrastructure.output.jpa.repository;

import com.foodcourt.tracking_service.domain.model.EmployeePerformance;
import com.foodcourt.tracking_service.domain.model.OrderDuration;
import com.foodcourt.tracking_service.infrastructure.output.jpa.entity.TraceEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITraceRepository extends MongoRepository<TraceEntity, String> {
    List<TraceEntity> findByOrderIdOrderByDateAsc(Long orderId);

    // NUEVO: Agregación para obtener la duración de los pedidos de un restaurante
    @Aggregation(pipeline = {
            "{ $match: { restaurantId: ?0, newStatus: { $in: ['PENDIENTE', 'ENTREGADO'] } } }",
            "{ $sort: { date: 1 } }",
            "{ $group: { _id: '$orderId', traces: { $push: '$$ROOT' } } }",
            "{ $match: { 'traces.newStatus': 'ENTREGADO' } }",
            "{ $project: { _id: 0, orderId: '$_id', durationInMinutes: { $divide: [ { $subtract: [ { $last: '$traces.date' }, { $first: '$traces.date' } ] }, 60000 ] } } }"
    })
    List<OrderDuration> getOrderDurationsForRestaurant(Long restaurantId);

    // NUEVO: Agregación para obtener el ranking de empleados de un restaurante
    @Aggregation(pipeline = {
            "{ $match: { restaurantId: ?0 } }",
            "{ $sort: { date: 1 } }",
            "{ $group: { _id: '$orderId', employeeId: { $last: '$employeeId' }, startDate: { $first: '$date' }, endDate: { $last: '$$ROOT.date' }, lastStatus: { $last: '$newStatus' } } }",
            "{ $match: { lastStatus: 'ENTREGADO', employeeId: { $ne: null } } }",
            "{ $project: { _id: 0, employeeId: 1, duration: { $subtract: ['$endDate', '$startDate'] } } }",
            "{ $group: { _id: '$employeeId', averageDurationInMinutes: { $avg: { $divide: ['$duration', 60000] } } } }",
            "{ $project: { _id: 0, employeeId: '$_id', averageDurationInMinutes: 1 } }",
            "{ $sort: { averageDurationInMinutes: 1 } }"
    })
    List<EmployeePerformance> getEmployeePerformanceForRestaurant(Long restaurantId);
}
