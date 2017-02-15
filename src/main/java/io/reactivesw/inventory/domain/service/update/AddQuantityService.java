package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.inventory.application.model.action.AddQuantityAction;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 17/1/3.
 */
@Service(value = InventoryEntryActionUtils.ADD_QUANTITY)
public class AddQuantityService implements Updater<InventoryEntry, UpdateAction> {

  /**
   * add quantity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    int addQuantity = ((AddQuantityAction) action).getQuantity();
    entity.setQuantityOnStock(entity.getQuantityOnStock() + addQuantity);
    entity.setAvailableQuantity(entity.getAvailableQuantity() + addQuantity);
  }
}
