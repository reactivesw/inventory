package io.reactivesw.inventory.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Inventory request.
 */
@Getter
@Setter
@ToString
public class InventoryRequest {
  /**
   * The Sku name.
   */
  private String skuName;

  /**
   * The Quantity.
   */
  private Integer quantity;
}
