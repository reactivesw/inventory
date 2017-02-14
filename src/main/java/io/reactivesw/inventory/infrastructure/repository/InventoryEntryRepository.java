package io.reactivesw.inventory.infrastructure.repository;

import io.reactivesw.inventory.domain.model.InventoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Davis on 16/12/21.
 */
public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, String> {

  @Query(value = "select i from InventoryEntryView i where i.sku in ?1")
  List<InventoryEntry> queryBySkuNames(List<String> skuNames);
}
