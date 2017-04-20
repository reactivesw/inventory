package io.reactivesw.inventory.infrastructure.repository;

import io.reactivesw.inventory.domain.model.ReservedOrder;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The type Reserved order specification.
 */
public final class ReservedOrderSpecification {

  /**
   * Instantiates a new Reserved order specification.
   */
  private ReservedOrderSpecification() {
  }

  /**
   * Specification for fetch Events.
   *
   * @param orderId the order id
   * @return Specification specification
   */
  public static Specification<ReservedOrder> exist(String orderId) {
    return new Specification<ReservedOrder>() {
      /**
       * predicate builder.
       */
      public Predicate toPredicate(Root<ReservedOrder> root, CriteriaQuery<?> query,
          CriteriaBuilder builder) {
        // Fetch events for two kind of conditions.
        return builder.equal(root.get("orderId"), orderId);
      }
    };
  }
}
