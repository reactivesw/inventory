package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.inventory.application.model.action.SetExpectedDelivery;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import io.reactivesw.model.Updater;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 16/12/21.
 */
@Service(value = InventoryEntryActionUtils.SET_EXPECTED_DELIVERY)
public class SetExpectedDeliveryService implements Updater<InventoryEntry, UpdateAction> {

  /**
   * set expected delivery.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    ZonedDateTime expectedDelivery = ((SetExpectedDelivery) action).getExpectedDelivery();
    entity.setExpectedDelivery(expectedDelivery);
  }
}
