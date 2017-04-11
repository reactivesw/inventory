package io.reactivesw.inventory.application.controller;

import static io.reactivesw.inventory.infrastructure.Router.INVENTORY_HEALTH_CHECK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * health controller.
 */
@RestController
public class HealthController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(INVENTORY_HEALTH_CHECK)
  public String healthCheck() {
    LOG.debug("enter healthCheck");

    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
