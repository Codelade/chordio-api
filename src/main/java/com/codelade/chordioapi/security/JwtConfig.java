package com.codelade.chordioapi.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {
    private String secret = "QWxsIG1vZGVybiBhcHBzIHNob3VsZCB1c2UgcmVhbGx5IGxvbmcgc2VjcmV0cyE";
    private long expirationMs = 2592000000L; // 30 days in ms
}
