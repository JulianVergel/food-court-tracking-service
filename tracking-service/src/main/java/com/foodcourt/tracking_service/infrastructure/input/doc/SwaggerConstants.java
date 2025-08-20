package com.foodcourt.tracking_service.infrastructure.input.doc;

public class SwaggerConstants {
    private SwaggerConstants() {}

    public static final String SAVE_TRACE_SUMMARY = "Guardar un registro de trazabilidad";
    public static final String GET_TRACE_SUMMARY = "Obtener la trazabilidad de un pedido";
    public static final String GET_DURATIONS_SUMMARY = "Obtener duración de pedidos por restaurante";
    public static final String GET_RANKING_SUMMARY = "Obtener ranking de empleados por restaurante";

    public static final String TRACE_CREATED_DESCRIPTION = "Registro de trazabilidad creado correctamente";
    public static final String DATA_RETURNED_SUCCESSFULLY_DESCRIPTION = "Datos retornados correctamente";
    public static final String UNAUTHORIZED_DESCRIPTION = "No autorizado, token inválido o ausente";
    public static final String ORDER_NOT_FOUND_DESCRIPTION = "Pedido no encontrado";
    public static final String ACCESS_DENIED_DESCRIPTION = "Acceso denegado, el rol no es el correcto";
}
