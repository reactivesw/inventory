package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Davis on 16/12/22.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddReservedQuantityAction implements UpdateAction {
  /**
   * The Add reserved quantity.
   */
  @NotNull
  @Min(1)
  private Integer addReservedQuantity;

  /**
   * get update service name.
   * @return add_reserved_quantity
   */
  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.ADD_RESERVED_QUANTITY;
  }
}
