package com.foodcourt.tracking_service.domain.api;

public interface IAuthenticatedUserPort {
    Long getAuthenticatedUserId();
    Long getOwnerRestaurantId();
}
