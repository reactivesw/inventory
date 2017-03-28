package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode
public class AddQuantityAction implements UpdateAction {
  /**
   * The Quantity.
   */
  @NotNull
  @Min(1)
  private Integer quantity;

  /**
   * get update service name.
   *
   * @return add_quantity
   */
  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.ADD_QUANTITY;
  }
}
