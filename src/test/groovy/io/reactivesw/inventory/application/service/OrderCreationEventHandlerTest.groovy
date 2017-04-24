package io.reactivesw.inventory.application.service

import com.google.common.collect.Lists
import io.reactivesw.exception.ParametersException
import io.reactivesw.inventory.application.model.InventoryRequest
import io.reactivesw.inventory.application.model.OrderCreationEvent
import io.reactivesw.inventory.domain.model.EventMessage
import io.reactivesw.inventory.domain.service.EventMessageService
import io.reactivesw.inventory.domain.service.InventoryEntryService
import io.reactivesw.inventory.domain.service.ReservedOrderService
import io.reactivesw.inventory.infrastructure.configuration.EventConfig
import io.reactivesw.inventory.infrastructure.repository.EventMessageRepository
import spock.lang.Specification

/**
 * Test for OrderCreationEventHandler.
 */
class OrderCreationEventHandlerTest extends Specification {
    InventoryEntryService inventoryService = Mock()
    ReservedOrderService reservedOrderService = Mock()
    EventConfig eventConfig = new EventConfig(orderCreatedSubscriber: "orderCreatedSubscriber")
    EventMessageRepository eventRepository = Mock()
    EventMessageService eventMessageService = new EventMessageService(eventRepository, eventConfig)

    OrderCreationEventHandler handler = new OrderCreationEventHandler(inventoryService, reservedOrderService, eventMessageService)

    def orderCreationEvent = new OrderCreationEvent()
    def orderId = "orderId"
    def skuName = "skuName"
    def quantity = 100
    InventoryRequest request = new InventoryRequest(skuName: skuName, quantity: quantity)
    List<InventoryRequest> requests = Lists.newArrayList(request)

    EventMessage eventMessage = Mock()

    def setup() {
        orderCreationEvent.orderId = orderId
        orderCreationEvent.inventoryRequests = requests
    }

    def "Test1.1: handle Order Creation Event"() {
        given:
        reservedOrderService.isReservedOrder(_) >> false
        eventRepository.save(_) >> eventMessage

        when:
        handler.handleOrderCreation(orderCreationEvent)

        then:
        true
    }

    def "Test1.2: handle Order Creation Event and order id is exist"() {
        given:
        reservedOrderService.isReservedOrder(_) >> true
        eventRepository.save(_) >> eventMessage

        when:
        handler.handleOrderCreation(orderCreationEvent)

        then:
        true
    }

    def "Test1.3: handle Order Creation Event and sku quantity is not enough"() {
        given:
        reservedOrderService.isReservedOrder(_) >> false
        inventoryService.updateInventoryBySkuNames(_) >> { throw new ParametersException("Test") }
        eventRepository.save(_) >> eventMessage

        when:
        handler.handleOrderCreation(orderCreationEvent)

        then:
        true
    }
}
