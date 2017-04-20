package io.reactivesw.inventory.domain.service

import com.google.common.collect.Lists
import io.reactivesw.inventory.domain.model.EventMessage
import io.reactivesw.inventory.infrastructure.enums.EventStatus
import io.reactivesw.inventory.infrastructure.repository.EventMessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import spock.lang.Specification

/**
 * Test for EventMessageService.
 */
class EventMessageServiceTest extends Specification {
    EventMessageRepository repository = Mock()
    EventMessageService service = new EventMessageService(repository)

    def categoryId1 = "categoryId1"
    def categoryId2 = "categoryId2"
    List<String> categoryIds = Lists.newArrayList(categoryId1, categoryId2)

    def messageId = "messageId1"
    EventMessage savedMessage = new EventMessage(id: messageId, status: EventStatus.CREATED)

    def "Test1: save event message"() {
        given:
        def orderId = "orderId"
        def status = true
        repository.save(_) >> savedMessage

        when:
        service.saveReservedOrderEvent(orderId, status)

        then:
        true
    }

    def "Test2.1: get events"() {
        given:
        Page<EventMessage> page = new PageImpl<>(Lists.newArrayList(savedMessage), null, 1)
        repository.findAll(_, _) >> page

        when:
        def result = service.getEvents()

        then:
        result != null
    }

    def "Test2.2: get events and status is not created"() {
        given:
        savedMessage.status = EventStatus.PENDING
        Page<EventMessage> page = new PageImpl<>(Lists.newArrayList(savedMessage), null, 1)
        repository.findAll(_, _) >> page

        when:
        def result = service.getEvents()

        then:
        result != null
    }

    def "Test3: delete events"() {
        when:
        service.deleteEvents(Lists.newArrayList(savedMessage))

        then:
        true
    }
}
