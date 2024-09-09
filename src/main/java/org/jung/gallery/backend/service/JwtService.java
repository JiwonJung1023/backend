package org.jung.gallery.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);

    Claims getClaims(String token);

    boolean isvalid(String token);

    int getId(String token);
}
