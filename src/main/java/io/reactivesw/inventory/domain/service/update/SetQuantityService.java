package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.action.SetQuantityAction;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.update.Updater;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/21.
 */
@Service(value = InventoryEntryActionUtils.SET_QUANTITY)
public class SetQuantityService extends Updater {
  /**
   * set quantity.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    int quantity = ((SetQuantityAction) action).getQuantity();
    if (quantity < entity.getReservedQuantity()) {
      throw new ParametersException("quantityOnStock should large than reservedQuantity");
    }
    entity.setQuantityOnStock(quantity);
    entity.setAvailableQuantity(quantity - entity.getReservedQuantity());
  }
}
