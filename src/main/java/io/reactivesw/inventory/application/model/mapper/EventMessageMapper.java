package io.reactivesw.inventory.application.model.mapper;

import io.reactivesw.inventory.application.model.ReservedOrderEvent;
import io.reactivesw.inventory.domain.model.EventMessage;
import io.reactivesw.inventory.infrastructure.configuration.EventConfig;
import io.reactivesw.inventory.infrastructure.enums.EventStatus;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;

/**
 * EventMessage Mapper class.
 */
public final class EventMessageMapper {

  /**
   * Json serializer.
   */
  private static transient JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * Instantiates a new Event message mapper.
   */
  private EventMessageMapper() {
  }

  /**
   * Build event message.
   *
   * @param orderId the order id
   * @param status  the status
   * @return the event message
   */
  public static EventMessage build(String orderId, boolean status, EventConfig eventConfig) {
    EventMessage message = new EventMessage();

    message.setVersion(eventConfig.getInventoryReservedVersion());
    message.setCreatedAt(System.currentTimeMillis());
    message.setStatus(EventStatus.CREATED);
    message.setTopic(eventConfig.getInventoryReservedName());
    message.setData(jsonSerializer.serialize(ReservedOrderEvent.build(orderId, status)));

    return message;
  }
}
