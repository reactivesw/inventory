package io.reactivesw.inventory.infrastructure.configuration;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * Inventory reserved topic name.
   */
  @Value("${io.reactivesw.message.topic.inventoryreserved.name}")
  private String inventoryReservedName;

  /**
   * Inventory reserved topic version.
   */
  @Value("${io.reactivesw.message.topic.inventoryreserved.version}")
  private Integer inventoryReservedVersion;

  /**
   * Subscriber of order created.
   */
  @Value("${io.reactivesw.message.topic.ordercreated.subscriber}")
  private String orderCreatedSubscriber;
}