package io.reactivesw.inventory.domain.service;

import io.reactivesw.inventory.domain.model.ReservedOrder;
import io.reactivesw.inventory.infrastructure.repository.ReservedOrderRepository;
import io.reactivesw.inventory.infrastructure.repository.ReservedOrderSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Reserved order service.
 */
@Service
public class ReservedOrderService {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ReservedOrderService.class);

  /**
   * ReservedOrder repository.
   */
  private transient ReservedOrderRepository repository;

  /**
   * Instantiates a new Reserved order service.
   *
   * @param repository the repository
   */
  public ReservedOrderService(ReservedOrderRepository repository) {
    this.repository = repository;
  }


  /**
   * Save reserved order.
   *
   * @param orderId the order id
   */
  public void saveReservedOrder(String orderId) {
    LOG.debug("Enter. OrderId: {}.", orderId);
    ReservedOrder order = new ReservedOrder();
    order.setOrderId(orderId);

    repository.save(order);
    LOG.debug("Exit.");
  }

  /**
   * Is reserved order.
   *
   * @param orderId the order id
   * @return the boolean
   */
  public boolean isReservedOrder(String orderId) {
    LOG.debug("Enter. OrderId: {}.", orderId);

    boolean result = true;

    ReservedOrder orders = repository.findOne(ReservedOrderSpecification.exist(orderId));

    if (orders == null) {
      result = false;
    }

    LOG.debug("Exit. Result: {}.", result);
    return result;
  }
}
