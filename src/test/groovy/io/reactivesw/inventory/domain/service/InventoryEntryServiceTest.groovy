package io.reactivesw.inventory.domain.service

import com.google.common.collect.Lists
import io.reactivesw.exception.ConflictException
import io.reactivesw.exception.NotExistException
import io.reactivesw.exception.ParametersException
import io.reactivesw.inventory.application.model.InventoryEntryDraft
import io.reactivesw.inventory.application.model.InventoryRequest
import io.reactivesw.inventory.application.model.action.SetExpectedDelivery
import io.reactivesw.inventory.application.model.mapper.InventoryEntryMapper
import io.reactivesw.inventory.domain.model.InventoryEntry
import io.reactivesw.inventory.infrastructure.repository.InventoryEntryRepository
import io.reactivesw.inventory.infrastructure.update.UpdateAction
import io.reactivesw.inventory.infrastructure.update.UpdaterService
import io.reactivesw.inventory.infrastructure.util.ReferenceTypes
import io.reactivesw.model.Reference
import spock.lang.Specification

import java.time.ZonedDateTime

/**
 * Created by Davis on 16/12/21.
 */
class InventoryEntryServiceTest extends Specification {
    InventoryEntryRepository inventoryEntryRepository = Mock()
    UpdaterService updateService = Mock()
    def inventoryEntryService = new InventoryEntryService(inventoryEntryRepository: inventoryEntryRepository,
            updateService: updateService)
    def inventoryEntryDraft = new InventoryEntryDraft()
    def inventoryEntryEntity = new InventoryEntry()
    def id = "1234566"
    def version = 1
    def skuName = "sku"

    def setup() {
        inventoryEntryDraft.sku = skuName
        inventoryEntryDraft.expectedDelivery = ZonedDateTime.now()
        inventoryEntryDraft.supplyChannel = new Reference(ReferenceTypes.CHANNEL.getType(), "q.ksfdkhsdfdf")
        inventoryEntryDraft.quantityOnStock = 120
        inventoryEntryDraft.restockableInDays = 12

        inventoryEntryEntity = InventoryEntryMapper.toEntity(inventoryEntryDraft)
        inventoryEntryEntity.id = id
        inventoryEntryEntity.version = version
    }

    def "test 1 : create inventory entry"() {
        given:
        inventoryEntryRepository.save(_) >> inventoryEntryEntity

        when:
        def result = inventoryEntryService.createInventoryEntry(inventoryEntryDraft)

        then:
        result != null
    }

    def "test 2.1 : delete inventory entry"() {
        given:
        inventoryEntryEntity.version = version
        inventoryEntryRepository.findOne(id) >> inventoryEntryEntity

        when:
        inventoryEntryService.deleteInventoryEntry(id, version)

        then:
        true
    }

    def "test 2.2 : delete inventory entry and version not match"() {
        given:
        inventoryEntryEntity.version = version + 1
        inventoryEntryRepository.findOne(id) >> inventoryEntryEntity

        when:
        inventoryEntryService.deleteInventoryEntry(id, version)

        then:
        thrown(ConflictException)
    }

    def "test 3.1 : update inventory entry"() {
        given:
        inventoryEntryRepository.findOne(id) >> inventoryEntryEntity
        inventoryEntryRepository.save(_) >> inventoryEntryEntity
        SetExpectedDelivery action = new SetExpectedDelivery(expectedDelivery: ZonedDateTime.now())
        List<UpdateAction> actions = Lists.newArrayList(action)
        updateService.handle(_, _) >> null

        when:
        def result = inventoryEntryService.updateInventoryEntry(id, version, actions)

        then:
        result != null
    }

    def "test 4.1 : get inventory entry by id"() {
        given:
        inventoryEntryRepository.findOne(id) >> inventoryEntryEntity

        when:
        def result = inventoryEntryService.getInventoryEntryById(id)

        then:
        result != null
        result.id == id
    }

    def "test 4.2 : get inventory entry by id and get null entity"() {
        given:
        inventoryEntryRepository.findOne(id) >> null

        when:
        def result = inventoryEntryService.getInventoryEntryById(id)

        then:
        thrown(NotExistException)
    }

    def "test 5.1 : query inventory entry by sku names"() {
        given:
        def skuNames = Lists.newArrayList(skuName)
        def inventoryList = Lists.newArrayList(inventoryEntryEntity)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> inventoryList

        when:
        def result = inventoryEntryService.queryBySkuNames(skuNames)

        then:
        result != null
        result.size() == inventoryList.size()
    }

    def "test 5.2 : query inventory entry by sku names and get null result"() {
        given:
        def skuNames = Lists.newArrayList(skuName)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> null

        when:
        def result = inventoryEntryService.queryBySkuNames(skuNames)

        then:
        result != null
        result.size() == 0
    }

    def "test 6.1 : update inventory entry by list of request"() {
        given:
        InventoryRequest request = new InventoryRequest(skuName: skuName, quantity: 10)
        List<InventoryRequest> requests = Lists.newArrayList(request)
        def skuNames = Lists.newArrayList(skuName)
        def inventoryList = Lists.newArrayList(inventoryEntryEntity)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> inventoryList

        when:
        inventoryEntryService.updateInventoryBySkuNames(requests)

        then:
        true
    }

    def "Test6.2: update inventory by null request"() {
        given:
        List<InventoryRequest> requests = Lists.newArrayList()
        def skuNames = Lists.newArrayList(skuName)
        def inventoryList = Lists.newArrayList(inventoryEntryEntity)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> inventoryList

        when:
        inventoryEntryService.updateInventoryBySkuNames(requests)

        then:
        true
    }

    def "Test6.3: update inventory by null sku"() {
        given:
        InventoryRequest request = new InventoryRequest(quantity: 10)
        List<InventoryRequest> requests = Lists.newArrayList(request)
        def skuNames = Lists.newArrayList(skuName)
        def inventoryList = Lists.newArrayList(inventoryEntryEntity)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> inventoryList

        when:
        inventoryEntryService.updateInventoryBySkuNames(requests)

        then:
        true
    }

    def "test 6.3 : update inventory entry by list of request and quantity more than availabelQuantity and quantityOnStock"() {
        given:
        InventoryRequest request = new InventoryRequest(skuName: skuName, quantity: 130)
        List<InventoryRequest> requests = Lists.newArrayList(request)
        def skuNames = Lists.newArrayList(skuName)
        def inventoryList = Lists.newArrayList(inventoryEntryEntity)
        inventoryEntryRepository.queryBySkuNames(skuNames) >> inventoryList

        when:
        inventoryEntryService.updateInventoryBySkuNames(requests)

        then:
        thrown(ParametersException)
    }
}
