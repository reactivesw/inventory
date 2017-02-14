package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode
public class RemoveQuantityAction implements UpdateAction {
  /**
   * The Quantity.
   */
  private Integer quantity;

  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.REMOVE_QUANTITY;
  }
}
