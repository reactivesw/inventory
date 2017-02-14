package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.action.RemoveReservedQuantityAction;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.update.Updater;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/22.
 */
@Service(value = InventoryEntryActionUtils.REMOVE_RESERVED_QUANTITY)
public class RemoveReservedQuantityService extends Updater {
  /**
   * remove reserved quantity.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    int removeQuantity = ((RemoveReservedQuantityAction) action).getRemoveReservedQuantity();
    int srcQuantity = entity.getQuantityOnStock();
    int srcReservedQuantity = entity.getReservedQuantity();
    if (removeQuantity > srcQuantity || removeQuantity > srcReservedQuantity) {
      throw new ParametersException(
          "remove quantity can not be greater than quantityOnStock or reservedQuantity");
    }
    entity.setQuantityOnStock(srcQuantity - removeQuantity);
    entity.setReservedQuantity(srcReservedQuantity - removeQuantity);
  }
}
