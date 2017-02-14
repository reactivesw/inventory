package io.reactivesw.inventory.infrastructure.validator;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by Davis on 17/2/8.
 */
public final class InventoryEntryValidator {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(InventoryEntryValidator.class);

  /**
   * Instantiates a new Inventory entry validator.
   */
  private InventoryEntryValidator() {
  }

  /**
   * validateNull version.
   *
   * @param entity  the entity
   * @param version the version
   */
  public static void validateVersion(InventoryEntry entity, Integer version) {
    if (!Objects.equals(version, entity.getVersion())) {
      LOG.debug("Version not match, input version : {}, entity version : {}",
          version, entity.getVersion());
      throw new ConflictException("Version not match");
    }
  }
}
