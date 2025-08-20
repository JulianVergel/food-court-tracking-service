package com.foodcourt.tracking_service.domain.exception;

import static com.foodcourt.tracking_service.domain.utils.constants.DomainConstants.ACCESS_DENIED_MESSAGE;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(
                ACCESS_DENIED_MESSAGE
        );
    }
}
