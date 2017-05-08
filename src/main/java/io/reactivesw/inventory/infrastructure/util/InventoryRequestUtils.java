package io.reactivesw.inventory.infrastructure.util;

import io.reactivesw.inventory.application.model.InventoryRequest;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/2/8.
 */
public final class InventoryRequestUtils {

  /**
   * Instantiates a new Inventory request utils.
   */
  private InventoryRequestUtils() {
  }

  /**
   * Gets sku names.
   *
   * @param requests the requests
   * @return the sku names
   */
  public static List<String> getSkuNames(List<InventoryRequest> requests) {
    Predicate<InventoryRequest> predicate = inventoryRequest -> StringUtils
        .isNotBlank(inventoryRequest.getSkuName());
    return requests.stream().filter(predicate).map(InventoryRequest::getSkuName)
        .collect(Collectors.toList());
  }

  /**
   * Gets sku quantity map.
   *
   * @param requests the requests
   * @return the sku quantity map
   */
  public static Map<String, Integer> getSkuQuantityMap(List<InventoryRequest> requests) {
    return requests.parallelStream().collect(
        Collectors.toMap(InventoryRequest::getSkuName, InventoryRequest::getQuantity)
    );
  }
}
