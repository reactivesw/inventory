package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode
public class SetQuantityAction implements UpdateAction {
  /**
   * The Quantity.
   */
  @Min(0)
  private Integer quantity;

  /**
   * get update service name.
   * @return set_quantity
   */
  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.SET_QUANTITY;
  }
}
