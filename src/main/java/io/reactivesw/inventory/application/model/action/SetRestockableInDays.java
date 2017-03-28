package io.reactivesw.inventory.application.model.action;

import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * Created by Davis on 16/12/21.
 */
@Data
@EqualsAndHashCode
public class SetRestockableInDays implements UpdateAction {
  /**
   * The Restockable in days.
   */
  @Min(1)
  private Integer restockableInDays;

  /**
   * get update service name.
   * @return set_restockable_in_days
   */
  @Override
  public String getActionName() {
    return InventoryEntryActionUtils.SET_RESTOCKABLE_IN_DAYS;
  }
}
