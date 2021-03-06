package io.reactivesw.inventory.application.model;

import io.reactivesw.model.Money;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Order creation event.
 */
@Getter
@Setter
public class OrderCreationEvent {

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Total amount.
   */
  private Money totalAmount;

  /**
   * The Inventory requests.
   */
  private List<InventoryRequest> inventoryRequests;

  /**
   * The Shipping address id.
   */
  private String shippingAddressId;

  /**
   * The Payment method id.
   */
  private String paymentId;
}
