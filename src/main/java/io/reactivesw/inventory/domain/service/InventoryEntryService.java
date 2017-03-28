package io.reactivesw.inventory.domain.service;

import com.google.common.collect.Lists;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.InventoryEntryDraft;
import io.reactivesw.inventory.application.model.InventoryEntryView;
import io.reactivesw.inventory.application.model.InventoryRequest;
import io.reactivesw.inventory.application.model.mapper.InventoryEntryMapper;
import io.reactivesw.inventory.domain.model.InventoryEntry;
import io.reactivesw.inventory.infrastructure.repository.InventoryEntryRepository;
import io.reactivesw.inventory.infrastructure.update.UpdateAction;
import io.reactivesw.inventory.infrastructure.update.UpdaterService;
import io.reactivesw.inventory.infrastructure.util.InventoryRequestUtils;
import io.reactivesw.inventory.infrastructure.validator.InventoryVersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

/**
 * Created by Davis on 16/12/21.
 */
@Service
public class InventoryEntryService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(InventoryEntryService.class);

  /**
   * The Inventory entry repository.
   */
  @Autowired
  private transient InventoryEntryRepository inventoryEntryRepository;

  /**
   * InventoryEntryUpdateService.
   */
  @Autowired
  private transient UpdaterService updateService;

  /**
   * Create inventory entry inventory entry.
   *
   * @param draft the draft
   * @return the inventory entry
   */
  public InventoryEntryView createInventoryEntry(InventoryEntryDraft draft) {
    LOG.debug("enter createInventoryEntry, inventory entry draft is : {}", draft.toString());
    // TODO: 17/1/4 validate product sku name

    InventoryEntry entity = InventoryEntryMapper.toEntity(draft);
    InventoryEntry savedEntity = inventoryEntryRepository.save(entity);

    InventoryEntryView result = InventoryEntryMapper.toModel(savedEntity);

    LOG.debug("end createInventoryEntry, new InventoryEntry is : {}", result.toString());

    return result;
  }

  /**
   * Delete InventoryEntryEntity.
   *
   * @param id      the id
   * @param version the version
   */
  public void deleteInventoryEntry(String id, Integer version) {
    LOG.debug("enter deleteInventoryEntry, id is : {}, version is : {}", id, version);
    InventoryEntry entity = getInventoryEntryEntity(id);
    InventoryVersionValidator.validate(entity, version);
    inventoryEntryRepository.delete(id);
    LOG.debug("end deleteInventoryEntry, id is : {}, version is : {}", id, version);
  }

  /**
   * Update InventoryEntry.
   *
   * @param id      the id
   * @param version the version
   * @param actions the actions
   * @return the inventory entry
   */
  @Transactional
  public InventoryEntryView updateInventoryEntry(String id, Integer version, List<UpdateAction>
      actions) {
    LOG.debug("enter updateInventoryEntry, id is {}, version is {}, update action is {}",
        id, version, actions);

    InventoryEntry entity = getInventoryEntryEntity(id);
    InventoryVersionValidator.validate(entity, version);

    actions.parallelStream().forEach(action -> {
      updateService.handle(entity, action);
    });

    InventoryEntry updatedEntity = inventoryEntryRepository.save(entity);
    //TODO send message, if slug be updated
    InventoryEntryView result = InventoryEntryMapper.toModel(updatedEntity);

    LOG.debug("end updateInventoryEntry, updated InventoryEntry is {}", result);
    return result;
  }

  /**
   * update inventory by sku names.
   *
   * @param requests update request
   */
  public void updateInventoryBySkuNames(List<InventoryRequest> requests) {
    List<String> skuNames = InventoryRequestUtils.getSkuNames(requests);

    Map<String, Integer> skuQuantity = InventoryRequestUtils.getSkuQuantityMap(requests);

    List<InventoryEntry> inventoryEntryEntities =
        inventoryEntryRepository.queryBySkuNames(skuNames);

    inventoryEntryEntities = updateInventoryByRequestMap(skuQuantity, inventoryEntryEntities);

    inventoryEntryRepository.save(inventoryEntryEntities);
  }

  /**
   * update inventory entry entity.
   *
   * @param skuQuantity            sku name and quantity map
   * @param inventoryEntryEntities list of inventory entry entity
   * @return updated list of inventory entry entity
   */
  private List<InventoryEntry> updateInventoryByRequestMap(Map<String, Integer> skuQuantity,
                                                           List<InventoryEntry>
                                                               inventoryEntryEntities) {
    inventoryEntryEntities.parallelStream().forEach(
        entity -> {
          Integer addReservedQuantity = skuQuantity.get(entity.getSku());
          int srcQuantity = entity.getQuantityOnStock();
          int srcAvailableQuantity = entity.getAvailableQuantity();

          if (addReservedQuantity > srcAvailableQuantity || addReservedQuantity > srcQuantity) {
            throw new ParametersException(
                "addReservedQuantity can't be greater than availableQuantity and quantityOnStock");
          }

          entity.setAvailableQuantity(srcAvailableQuantity - addReservedQuantity);
          entity.setReservedQuantity(entity.getReservedQuantity() + addReservedQuantity);
        }
    );
    return inventoryEntryEntities;
  }

  /**
   * Gets inventory entry by id.
   *
   * @param id the id
   * @return the inventory entry by id
   */
  public InventoryEntryView getInventoryEntryById(String id) {
    LOG.debug("enter getInventoryEntryById, id is : {}", id);

    InventoryEntry entity = getInventoryEntryEntity(id);

    InventoryEntryView result = InventoryEntryMapper.toModel(entity);

    LOG.debug("end getInventoryEntryById, get result is : {}", result.toString());

    return result;
  }

  /**
   * Query by sku names.
   *
   * @param skuNames the sku names
   * @return the list
   */
  public List<InventoryEntryView> queryBySkuNames(List<String> skuNames) {
    LOG.debug("enter queryBySkuNames, sku names is : {}", skuNames);
    List<InventoryEntry> inventoryEntryEntities =
        inventoryEntryRepository.queryBySkuNames(skuNames);

    List<InventoryEntryView> result = Lists.newArrayList();

    if (inventoryEntryEntities != null) {
      result = InventoryEntryMapper.toModel(inventoryEntryEntities);
    }

    LOG.debug("end queryBySkuNames, get InventoryEntries number is : {}", result.size());

    return result;
  }

  /**
   * Gets inventoryentry.
   *
   * @param id the id
   * @return the inventory entry entity
   */
  private InventoryEntry getInventoryEntryEntity(String id) {
    InventoryEntry entity = inventoryEntryRepository.findOne(id);
    if (entity == null) {
      LOG.debug("can not find inventoryentry by id : {}", id);
      throw new NotExistException("InventoryEntry Not Found");
    }
    return entity;
  }
}
