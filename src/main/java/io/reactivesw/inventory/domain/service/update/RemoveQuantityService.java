package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.action.RemoveQuantityAction;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.update.Updater;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/21.
 */
@Service(value = InventoryEntryActionUtils.REMOVE_QUANTITY)
public class RemoveQuantityService extends Updater {
  /**
   * remove quantity.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    int removeQuantity = ((RemoveQuantityAction) action).getQuantity();
    int quantityOnStock = entity.getQuantityOnStock();
    int availableQuantity = entity.getAvailableQuantity();

    if (removeQuantity > quantityOnStock || removeQuantity > availableQuantity) {
      throw new ParametersException(
          "can not remove more than quantityOnStock or availableQuantity");
    }

    entity.setQuantityOnStock(quantityOnStock - removeQuantity);
    entity.setAvailableQuantity(availableQuantity - removeQuantity);
  }
}
