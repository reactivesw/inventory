package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * Created by Davis on 16/12/22.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RemoveReservedQuantityAction implements UpdateAction {
  /**
   * The Reserved.
   */
  @Min(1)
  private int removeReservedQuantity;

  /**
   * get update service name.
   * @return remove_reserved_quantity
   */
  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.REMOVE_RESERVED_QUANTITY;
  }
}
