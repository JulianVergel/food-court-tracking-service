package com.foodcourt.tracking_service.infrastructure.output.security;

import com.foodcourt.tracking_service.domain.api.IAuthenticatedUserPort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserAdapter implements IAuthenticatedUserPort {
    @Override
    public Long getAuthenticatedUserId() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getClaim("id");
    }
}
