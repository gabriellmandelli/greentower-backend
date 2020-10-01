package com.greentower.api.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtPropertiesConfig {

    String signingKey;
    Long expirationTimeMinutes;

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public Long getExpirationTimeMinutes() {
        return expirationTimeMinutes;
    }

    public void setExpirationTimeMinutes(Long expirationTimeMinutes) {
        this.expirationTimeMinutes = expirationTimeMinutes;
    }
}
