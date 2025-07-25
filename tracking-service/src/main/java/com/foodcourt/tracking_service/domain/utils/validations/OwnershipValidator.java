package com.foodcourt.tracking_service.domain.utils.validations;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnershipValidator {

    private final IAuthenticatedUserPort authenticatedUserPort;

    public void validateRestaurantOwnership(Long restaurantId) {
        Long ownerRestaurantId = authenticatedUserPort.getOwnerRestaurantId();

        if (ownerRestaurantId == null) {
            throw new AccessDeniedException();
        }

        if (!ownerRestaurantId.equals(restaurantId)) {
            throw new AccessDeniedException();
        }
    }
}
