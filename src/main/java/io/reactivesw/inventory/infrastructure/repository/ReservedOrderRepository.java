package io.reactivesw.inventory.infrastructure.repository;

import io.reactivesw.inventory.domain.model.ReservedOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Reserved order repository.
 */
public interface ReservedOrderRepository extends JpaRepository<ReservedOrder, String>,
    JpaSpecificationExecutor<ReservedOrder> {

}
