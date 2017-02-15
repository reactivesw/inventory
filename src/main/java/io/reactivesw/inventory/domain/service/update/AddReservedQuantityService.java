package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.action.AddReservedQuantityAction;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/22.
 */
@Service(value = InventoryEntryActionUtils.ADD_RESERVED_QUANTITY)
public class AddReservedQuantityService implements Updater<InventoryEntry, UpdateAction> {
  /**
   * add reserved quantity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    int addReservedQuantity = ((AddReservedQuantityAction) action).getAddReservedQuantity();
    int srcQuantity = entity.getQuantityOnStock();
    int srcAvailableQuantity = entity.getAvailableQuantity();

    if (addReservedQuantity > srcAvailableQuantity || addReservedQuantity > srcQuantity) {
      throw new ParametersException(
          "addReservedQuantity can not be greater than availabelQuantity and quantityOnStock");
    }

    entity.setAvailableQuantity(srcAvailableQuantity - addReservedQuantity);
    entity.setReservedQuantity(entity.getReservedQuantity() + addReservedQuantity);
  }
}
