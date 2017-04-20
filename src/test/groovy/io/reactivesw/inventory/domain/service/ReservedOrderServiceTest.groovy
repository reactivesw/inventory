package io.reactivesw.inventory.domain.service

import io.reactivesw.inventory.domain.model.ReservedOrder
import io.reactivesw.inventory.infrastructure.repository.ReservedOrderRepository
import spock.lang.Specification

/**
 * Test for ReservedOrderService.
 */
class ReservedOrderServiceTest extends Specification {
    ReservedOrderRepository repository = Mock()
    ReservedOrderService service = new ReservedOrderService(repository)

    def orderId = "orderId"
    def reservedOrder = new ReservedOrder()

    def "Test1: save reserved order"() {
        when:
        service.saveReservedOrder(orderId)

        then:
        true
    }

    def "Test2.1: get reserved order"() {
        given:
        repository.findOne(_) >> reservedOrder

        when:
        def result = service.isReservedOrder(orderId)

        then:
        result == false
    }

    def "Test2.2: reserved order not exist"() {
        given:
        repository.findOne(_) >> null

        when:
        def result = service.isReservedOrder(orderId)

        then:
        result == true
    }
}
