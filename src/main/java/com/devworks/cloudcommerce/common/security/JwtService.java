
package com.devworks.cloudcommerce.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtService {
//    @Value("${security.jwt.secret}")
    private String secret = "tmp";

    private static final String ISSUER = "Cloud Commerce API";

    public String generateToken(Authentication authentication) throws BadRequestException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(authentication.getName())
            .withClaim("roles", roles)
            .withExpiresAt(
                LocalDateTime.now()
                    .plusMinutes(10)
                    .toInstant(ZoneOffset.of("-03:00")
                )
            )
            .sign(Algorithm.HMAC256(secret));
    }

    public LocalDateTime getExpirationDate(String token) throws BadRequestException {
        var jwt = JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(ISSUER)
            .build()
            .verify(token);

        return jwt.getExpiresAt().toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public String getSubject(String token) throws BadRequestException {
        return JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(ISSUER)
            .build()
            .verify(token)
            .getSubject();
    }

    public void validateJwt(String token) throws BadRequestException {
        JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(ISSUER)
            .build()
            .verify(token);
    }

    public List<String> getRoles(String token) throws BadRequestException {
        var decodedJWT = JWT.require(Algorithm.HMAC256(secret))
            .withIssuer(ISSUER)
            .build().verify(token);

        Claim rolesClaim = decodedJWT.getClaim("roles");

        if (rolesClaim.isNull())
            throw new CustomAuthenticationException("Invalid token claims");

        return rolesClaim.asList(String.class);
    }
}

