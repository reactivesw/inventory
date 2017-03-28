package io.reactivesw.inventory.infrastructure.repository;

import io.reactivesw.inventory.domain.model.InventoryEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Inventory Entry Repository.
 */
public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, String> {

  /**
   * Query by sku names list.
   *
   * @param skuNames the sku names
   * @return the list
   */
  @Query(value = "select i from InventoryEntry i where i.sku in ?1")
  List<InventoryEntry> queryBySkuNames(List<String> skuNames);
}
