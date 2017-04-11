package io.reactivesw.inventory.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.model.Reference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * InventoryEntryDraft.
 */
@Data
@EqualsAndHashCode
public class InventoryEntryDraft {

  /**
   * The Sku.
   */
  @NotNull
  private String sku;

  /**
   * The Quantity on stock.
   */
  @NotNull
  @Min(0)
  private Integer quantityOnStock;

  /**
   * The Restockable in days.
   */
  private Integer restockableInDays;

  /**
   * The Expected delivery.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime expectedDelivery;

  /**
   * Reference to a Channel.
   * Optional.
   */
  private Reference supplyChannel;
}
