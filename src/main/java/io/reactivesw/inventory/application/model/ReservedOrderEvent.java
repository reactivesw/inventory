package io.reactivesw.inventory.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Reserved order event.
 */
@Setter
@Getter
public class ReservedOrderEvent {

  /**
   * the order id.
   */
  private String orderId;

  /**
   * The status.
   */
  private Boolean result;

  /**
   * The message.
   */
  private String message;


  /**
   * Build reserved order event.
   *
   * @param orderId the order id
   * @param status the status
   * @return the reserved order event
   */
  public static ReservedOrderEvent build(String orderId, boolean status) {
    ReservedOrderEvent reservedOrderEvent = new ReservedOrderEvent();

    reservedOrderEvent.setOrderId(orderId);
    reservedOrderEvent.setResult(status);

    return reservedOrderEvent;
  }
}