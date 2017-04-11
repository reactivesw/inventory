package io.reactivesw.inventory.application.controller;

import io.reactivesw.inventory.application.model.DeleteRequest;
import io.reactivesw.inventory.application.model.InventoryEntryDraft;
import io.reactivesw.inventory.application.model.InventoryEntryView;
import io.reactivesw.inventory.application.model.InventoryRequest;
import io.reactivesw.inventory.domain.service.InventoryEntryService;
import io.reactivesw.inventory.infrastructure.Router;
import io.reactivesw.inventory.infrastructure.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * The type Inventory entry controller.
 */
@RestController
public class InventoryEntryController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(InventoryEntryController.class);

  /**
   * InventoryEntryService.
   */
  private transient InventoryEntryService inventoryEntryService;


  /**
   * Instantiates a new Inventory entry controller.
   *
   * @param inventoryEntryService the inventory entry service
   */
  @Autowired
  public InventoryEntryController(InventoryEntryService inventoryEntryService) {
    this.inventoryEntryService = inventoryEntryService;
  }

  /**
   * Create inventory entry inventory entry view.
   *
   * @param draft the draft
   * @return the inventory entry view
   */
  @PostMapping(Router.INVENTORY_ENTRY_ROOT)
  public InventoryEntryView createInventoryEntry(@RequestBody @Valid InventoryEntryDraft draft) {
    LOG.debug("enter. InventoryEntryDraft: {}.", draft);

    InventoryEntryView result = inventoryEntryService.createInventoryEntry(draft);

    LOG.debug("exit. InventoryEntryView: {}.", result);
    return result;
  }

  /**
   * Delete inventory entry.
   *
   * @param id      the id
   * @param request the version
   */
  @DeleteMapping(Router.INVENTORY_ENTRY_WITH_ID)
  public void deleteInventoryEntry(@PathVariable(value = Router.INVENTORY_ENTRY_ID) String id,
                                   @RequestBody DeleteRequest request) {
    LOG.debug("enter. id: {}, request: {}.", id, request);

    inventoryEntryService.deleteInventoryEntry(id, request.getVersion());

    LOG.debug("exit. id: {}, request: {}.", id, request);
  }

  /**
   * Update inventory entry inventory entry view.
   *
   * @param id            the id
   * @param updateRequest the update request
   * @return the inventory entry view
   */
  @PutMapping(Router.INVENTORY_ENTRY_WITH_ID)
  public InventoryEntryView updateInventoryEntry(@PathVariable(value = Router.INVENTORY_ENTRY_ID)
                                                     String id,
                                                 @RequestBody UpdateRequest updateRequest) {
    LOG.debug("enter. id: {}, UpdateRequest: {}.",
        id, updateRequest);

    InventoryEntryView result = inventoryEntryService.updateInventoryEntry(id,
        updateRequest.getVersion(), updateRequest.getActions());

    LOG.debug("exit. InventoryEntryView: {}.", result);

    return result;
  }

  /**
<<<<<<< HEAD
   * Update inventory entry by list.
=======
   * Update inventory entry by list list.
   * For order service, return void is ok.
>>>>>>> 1344f303e2fceaa4f9dd2ccf1b94eb090016601e
   *
   * @param requests the requests
   * @return the list
   */
  @PutMapping(Router.INVENTORY_ENTRY_ROOT)
  public void updateInventoryEntryByList(@RequestBody List<InventoryRequest>
                                             requests) {
    LOG.debug("enter. requests: {}.", requests);


    inventoryEntryService.updateInventoryBySkuNames(requests);

    LOG.debug("exit updateInventoryEntryByList.");
  }

  /**
   * Gets inventory entry by id.
   *
   * @param id the id
   * @return the inventory entry by id
   */
  @GetMapping(Router.INVENTORY_ENTRY_WITH_ID)
  public InventoryEntryView getInventoryEntryById(@PathVariable(value = Router.INVENTORY_ENTRY_ID)
                                                      String id) {
    LOG.debug("enter. id: {}.", id);

    InventoryEntryView result = inventoryEntryService.getInventoryEntryById(id);

    LOG.debug("exit. InventoryEntryView: {}.", result);

    return result;
  }

  /**
   * Query inventory entries by sku names list.
   *
   * @param skuNames the sku names
   * @return the list
   */
  @GetMapping(Router.INVENTORY_ENTRY_ROOT)
  public List<InventoryEntryView> queryInventoryEntriesBySkuNames(@RequestParam("skuNames")
                                                                      List<String> skuNames) {
    LOG.debug("enter. skuNames: {}.", skuNames);

    List<InventoryEntryView> result = inventoryEntryService.queryBySkuNames(skuNames);

    LOG.debug("exit. List<InventoryEntryView>: {}.",
        result.size());
    LOG.trace("query result List<InventoryEntryView>: {}", result);

    return result;
  }
}