package com.foodcourt.tracking_service.domain.exception;

import static com.foodcourt.tracking_service.domain.utils.constants.DomainConstants.TRACE_NOT_FOUND_MESSAGE;

public class TraceNotFoundException extends RuntimeException {
    public TraceNotFoundException() {
        super(
                TRACE_NOT_FOUND_MESSAGE
        );
    }
}
