package com.foodcourt.tracking_service.infrastructure.input.doc;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.foodcourt.tracking_service.infrastructure.input.doc.SwaggerConstants.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = DATA_RETURNED_SUCCESSFULLY_DESCRIPTION),
        @ApiResponse(responseCode = "403", description = ACCESS_DENIED_DESCRIPTION, content = @Content),
        @ApiResponse(responseCode = "404", description = ORDER_NOT_FOUND_DESCRIPTION, content = @Content)
})
public @interface ApiResponsesStandard {
}
