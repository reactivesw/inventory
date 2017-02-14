package io.reactivesw.inventory.domain.service.update;

import io.reactivesw.inventory.application.model.action.SetSupplyChannel;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.update.Updater;
import io.reactivesw.inventory.infrastructure.util.InventoryEntryActionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 16/12/21.
 */
@Service(value = InventoryEntryActionUtils.SET_SUPPLY_CHANNEL)
public class SetSupplyChannelService extends Updater {
  /**
   * set supply channel.
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InventoryEntry entity, UpdateAction action) {
    String channelId = ((SetSupplyChannel) action).getSupplyChannel().getId();
    entity.setSupplyChannel(channelId);
  }
}
