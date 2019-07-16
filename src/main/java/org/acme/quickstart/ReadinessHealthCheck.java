package org.acme.quickstart;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {
  
  @Override
  public HealthCheckResponse call() {
    
    if (Utils.READINESS_FLAG) {
      return HealthCheckResponse.named("Database connection health check").up().build();
    }
    return HealthCheckResponse.named("Database connection health check").down().build();
  }
}
