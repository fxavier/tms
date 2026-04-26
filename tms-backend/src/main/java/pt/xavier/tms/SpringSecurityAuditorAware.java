package pt.xavier.tms;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Provides the current authenticated user's name for JPA auditing
 * ({@code @CreatedBy} / {@code @LastModifiedBy} fields).
 *
 * <p>Reads the principal name from the Spring Security {@link SecurityContextHolder}.
 * Falls back to {@code "system"} when no authentication is present (e.g. during
 * scheduled tasks or Flyway migrations).
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    private static final String FALLBACK_AUDITOR = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(FALLBACK_AUDITOR);
        }

        String name = authentication.getName();
        if (name == null || name.isBlank()) {
            return Optional.of(FALLBACK_AUDITOR);
        }

        return Optional.of(name);
    }
}
