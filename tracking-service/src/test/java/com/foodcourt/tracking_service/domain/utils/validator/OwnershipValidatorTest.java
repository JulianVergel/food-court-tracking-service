package com.foodcourt.tracking_service.domain.utils.validator;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import com.foodcourt.tracking_service.domain.exception.AccessDeniedException;
import com.foodcourt.tracking_service.domain.utils.validations.OwnershipValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnershipValidatorTest {

    @Mock
    private IAuthenticatedUserPort authenticatedUserPort;

    @InjectMocks
    private OwnershipValidator ownershipValidator;

    @Test
    void validateRestaurantOwnership_Success() {
        Long restaurantId = 1L;
        when(authenticatedUserPort.getOwnerRestaurantId()).thenReturn(restaurantId);

        assertDoesNotThrow(() -> ownershipValidator.validateRestaurantOwnership(restaurantId));
    }

    @Test
    void validateRestaurantOwnership_ThrowsExceptionWhenIdsDoNotMatch() {
        Long ownerRestaurantId = 1L;
        Long requestedRestaurantId = 2L;
        when(authenticatedUserPort.getOwnerRestaurantId()).thenReturn(ownerRestaurantId);

        assertThrows(AccessDeniedException.class, () -> {
            ownershipValidator.validateRestaurantOwnership(requestedRestaurantId);
        });
    }

    @Test
    void validateRestaurantOwnership_ThrowsExceptionWhenOwnerIdIsNull() {
        Long requestedRestaurantId = 1L;
        when(authenticatedUserPort.getOwnerRestaurantId()).thenReturn(null);

        assertThrows(AccessDeniedException.class, () -> {
            ownershipValidator.validateRestaurantOwnership(requestedRestaurantId);
        });
    }
}
