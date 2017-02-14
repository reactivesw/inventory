package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode
public class SetExpectedDelivery implements UpdateAction {
  /**
   * The Expected delivery.
   */
  private ZonedDateTime expectedDelivery;

  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.SET_EXPECTED_DELIVERY;
  }
}
