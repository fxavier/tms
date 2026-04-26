package pt.xavier.tms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA configuration.
 *
 * Placed in a dedicated @Configuration class (not on TmsApplication) so that
 * @DataJpaTest slices can load without triggering the full application context,
 * avoiding conflicts with Spring Security auto-configuration.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
