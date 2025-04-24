package edu.vikadem.mypr.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/*
@author admin
@mypr
@class AuditorAware
@since 24.04.2025 - 19.09

*/
public class AuditorAwareImpl implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
