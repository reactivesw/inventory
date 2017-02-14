package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import io.reactivesw.model.Reference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SetSupplyChannel implements UpdateAction {
  /**
   * The Supply channel.
   */
  @NotNull
  private Reference supplyChannel;

  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.SET_SUPPLY_CHANNEL;
  }
}
