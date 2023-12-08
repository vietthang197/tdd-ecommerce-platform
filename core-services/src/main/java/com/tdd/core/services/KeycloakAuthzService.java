package com.tdd.core.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface KeycloakAuthzService {
    boolean isGrant(HttpServletRequest request, HttpServletResponse response);
}
