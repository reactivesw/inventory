package io.reactivesw.inventory.application.model.mapper;

import io.reactivesw.inventory.application.model.InventoryEntryDraft;
import io.reactivesw.inventory.application.model.InventoryEntryView;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.util.ReferenceTypes;
import io.reactivesw.model.Reference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 16/12/21.
 */
public final class InventoryEntryMapper {

  /**
   * DEFAULT_RESERVED_QUANTITY.
   */
  private static final int DEFAULT_RESERVED_QUANTITY = 0;

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(InventoryEntryMapper.class);

  /**
   * private constructor.
   */
  private InventoryEntryMapper() {
  }

  /**
   * Model to entity inventory entry entity.
   *
   * @param model the model
   * @return the inventory entry entity
   */
  public static InventoryEntry mapToEntity(InventoryEntryDraft model) {
    InventoryEntry entity = new InventoryEntry();

    entity.setSku(model.getSku());
    if (model.getSupplyChannel() != null) {
      entity.setSupplyChannel(model.getSupplyChannel().getId());
    }
    entity.setQuantityOnStock(model.getQuantityOnStock());
    entity.setAvailableQuantity(model.getQuantityOnStock() - DEFAULT_RESERVED_QUANTITY);
    entity.setReservedQuantity(DEFAULT_RESERVED_QUANTITY);
    entity.setRestockableInDays(model.getRestockableInDays());
    entity.setExpectedDelivery(model.getExpectedDelivery());
    return entity;
  }

  /**
   * Entity to model inventory entry.
   *
   * @param entity the entity
   * @return the inventory entry
   */
  public static InventoryEntryView mapToModel(InventoryEntry entity) {
    InventoryEntryView model = new InventoryEntryView();

    model.setId(entity.getId());
    model.setSku(entity.getSku());
    model.setQuantityOnStock(entity.getQuantityOnStock());
    model.setVersion(entity.getVersion());
    model.setRestockableInDays(entity.getRestockableInDays());
    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());
    model.setAvailableQuantity(entity.getAvailableQuantity());
    if (StringUtils.isNotBlank(entity.getSupplyChannel())) {
      model.setSupplyChannel(new Reference(ReferenceTypes.CHANNEL.getType(),
          entity.getSupplyChannel()));
    }

    model.setExpectedDelivery(entity.getExpectedDelivery());

    return model;
  }

  public static List<InventoryEntryView> mapToModel(List<InventoryEntry> entities) {
    LOG.debug("enter mapToModel, entity size is : {}", entities.size());

    return entities.parallelStream().map(
        entity -> mapToModel(entity)
    ).collect(Collectors.toList());
  }
}
