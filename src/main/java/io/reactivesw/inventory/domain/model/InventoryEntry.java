package io.reactivesw.inventory.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by Davis on 16/11/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "inventory_entry")
@EntityListeners(AuditingEntityListener.class)
public class InventoryEntry {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;


  /**
   * version.
   */
  @Version
  @Column(name = "version")
  private Integer version;

  /**
   * sku name.
   */
  @Column(name = "sku_name")
  private String sku;

  /**
   * supply channel id.
   */
  @Column(name = "supply_channel_id")
  private String supplyChannel;

  /**
   * quantity on stock.
   */
  @Column(name = "quantity_on_stock")
  private Integer quantityOnStock;

  /**
   * available quantity.
   */
  @Column(name = "available_quantity")
  private Integer availableQuantity;

  /**
   * reserved quantity.
   */
  @Column(name = "reserved_quantity")
  private Integer reservedQuantity;

  /**
   * restockable in days.
   */
  @Column(name = "restockable_in_days")
  private Integer restockableInDays;

  /**
   * exapected dalivery.
   */
  @Column(name = "expected_delivery")
  private ZonedDateTime expectedDelivery;
}
