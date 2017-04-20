package io.reactivesw.inventory.application.model.mapper;

import io.reactivesw.inventory.application.model.ReservedOrderEvent;
import io.reactivesw.inventory.domain.model.EventMessage;
import io.reactivesw.inventory.infrastructure.enums.EventStatus;
import io.reactivesw.inventory.infrastructure.util.EventTopics;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;

/**
 * EventMessage Mapper class.
 */
public final class EventMessageMapper {

  /**
   * Json serializer.
   */
  private transient static JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * Instantiates a new Event message mapper.
   */
  private EventMessageMapper() {}

  /**
   * Build event message.
   *
   * @param orderId the order id
   * @param status the status
   * @return the event message
   */
  public static EventMessage build(String orderId, boolean status) {
    EventMessage message = new EventMessage();

    // TODO: 17/4/17 change version code to config
    message.setVersion(1);
    message.setCreatedAt(System.currentTimeMillis());
    message.setStatus(EventStatus.CREATED);
    message.setTopic(EventTopics.INVENTORY_RESERVED);
    message.setData(jsonSerializer.serialize(ReservedOrderEvent.build(orderId, status)));

    return message;
  }
}
