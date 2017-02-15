package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.inventory.application.model.action.SetRestockableInDays;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/21.
 */
@Service(value = InventoryEntryActionUtils.SET_RESTOCKABLE_IN_DAYS)
public class SetRestockableInDaysService implements Updater<InventoryEntry, UpdateAction> {

  /**
   * set restockable in days.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    Integer restockableInDays = ((SetRestockableInDays) action).getRestockableInDays();
    entity.setRestockableInDays(restockableInDays);
  }
}
