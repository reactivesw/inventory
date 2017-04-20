package io.reactivesw.inventory.application.service;

import io.reactivesw.exception.ParametersException;
import io.reactivesw.inventory.application.model.InventoryRequest;
import io.reactivesw.inventory.application.model.OrderCreationEvent;
import io.reactivesw.inventory.domain.service.EventMessageService;
import io.reactivesw.inventory.domain.service.InventoryEntryService;
import io.reactivesw.inventory.domain.service.ReservedOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Order creation event handler.
 */
@Service
public class OrderCreationEventHandler {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderCreationEventHandler.class);

  /**
   * The Inventory Service.
   */
  private transient InventoryEntryService inventoryService;

  /**
   * The ReservedOrder Service.
   */
  private transient ReservedOrderService reservedOrderService;

  /**
   * The Event message service.
   */
  private transient EventMessageService eventMessageService;

  /**
   * Instantiates a new Order creation event handler.
   *
   * @param inventoryService the inventory service
   * @param reservedOrderService the reserved order service
   * @param eventMessageService the event message service
   */
  @Autowired
  public OrderCreationEventHandler(InventoryEntryService inventoryService,
      ReservedOrderService reservedOrderService, EventMessageService eventMessageService) {
    this.inventoryService = inventoryService;
    this.reservedOrderService = reservedOrderService;
    this.eventMessageService = eventMessageService;
  }

  /**
   * Handle order creation.
   *
   * @param event the event
   */
  @Transactional
  public void handleOrderCreation(OrderCreationEvent event) {
    LOG.debug("Enter. OrderId: {}.", event.getOrderId());

    if (reservedOrderService.isReservedOrder(event.getOrderId())) {
      LOG.debug("Order: {} is exist.", event.getOrderId());
    } else {
      //1. get request and remove inventory
      List<InventoryRequest> requests = event.getInventoryRequests();
      Boolean reservedStatus = false;
      try {
        inventoryService.updateInventoryBySkuNames(requests);
        reservedOrderService.saveReservedOrder(event.getOrderId());
        //2. store orderId
        reservedStatus = true;
      } catch (ParametersException pEx) {
        LOG.debug("Reserve inventory fail.", pEx);
      }
      //3. store remove inventory event
      eventMessageService.saveReservedOrderEvent(event.getOrderId(), reservedStatus);
    }
    LOG.debug("Exit.");
  }
}
