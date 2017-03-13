package io.reactivesw.inventory.application.controller;

import static io.reactivesw.inventory.infrastructure.Router.INVENTORY_HEALTH_CHECK;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/21.
 */
@RestController
public class IndexController {

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(INVENTORY_HEALTH_CHECK)
  public String index() {
    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
