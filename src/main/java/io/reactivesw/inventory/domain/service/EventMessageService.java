package io.reactivesw.inventory.domain.service;

import io.reactivesw.inventory.application.model.mapper.EventMessageMapper;
import io.reactivesw.inventory.domain.model.EventMessage;
import io.reactivesw.inventory.infrastructure.enums.EventStatus;
import io.reactivesw.inventory.infrastructure.repository.EventMessageRepository;
import io.reactivesw.inventory.infrastructure.repository.EventMessageSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Event Message Service.
 */
@Service
public class EventMessageService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(EventMessageService.class);

  /**
   * Event message repository.
   */
  private transient EventMessageRepository eventMessageRepository;

  /**
   * Instantiates a new Event message service.
   *
   * @param eventMessageRepository the event message repository
   */
  @Autowired
  public EventMessageService(EventMessageRepository eventMessageRepository) {
    this.eventMessageRepository = eventMessageRepository;
  }

  /**
   * Save reserved order event.
   *
   * @param orderId the order id
   * @param status the status
   */
  public void saveReservedOrderEvent(String orderId, Boolean status) {
    LOG.debug("Enter. OrderId: {}, status: {}.", orderId, status);
    EventMessage message = EventMessageMapper.build(orderId, status);

    EventMessage savedMessage = eventMessageRepository.save(message);

    LOG.debug("End. EventId: {}.", savedMessage.getId());
  }


  /**
   * Gets events.
   *
   * @return the events
   */
  public List<EventMessage> getEvents() {
    LOG.debug("Enter.");
    //fetch the first page and fetch 10 event each time. TODO change this to configuration.
    Page<EventMessage> page = eventMessageRepository.findAll(
        EventMessageSpecification.isAvailable(), new PageRequest(0, 10));
    List<EventMessage> events = page.getContent();

    LOG.debug("Fetch events. eventCount: {}, countInDb: {}.", events.size(), page
        .getTotalElements());

    events.stream().forEach(
        event -> {
          if (event.getStatus() == EventStatus.CREATED) {
            //if the event is in "CREATED" status, then change it to "PENDING"
            event.setStatus(EventStatus.PENDING);
          }
        }
    );

    eventMessageRepository.save(events);

    // Log the event data.
    LOG.trace("Fetch events. Events: {}.", Arrays.toString(events.toArray()));
    LOG.debug("Exit.");
    return events;
  }

  /**
   * Delete events.
   *
   * @param messages the messages
   */
  public void deleteEvents(List<EventMessage> messages) {
    LOG.debug("Enter. Message size: {}.", messages.size());
    eventMessageRepository.delete(messages);
    LOG.debug("Exit.");
  }
}