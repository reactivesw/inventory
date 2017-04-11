package io.reactivesw.inventory.infrastructure;

/**
 * Created by Davis on 16/12/21.
 */
public class Router {

  /**
   * inventory_entry root.
   */
  public static final String INVENTORY_ENTRY_ROOT = "/";

  /**
   * inventory_entry id.
   */
  public static final String INVENTORY_ENTRY_ID = "inventoryId";

  /**
   * inventory_entry with id.
   */
  public static final String INVENTORY_ENTRY_WITH_ID = INVENTORY_ENTRY_ROOT
      + "{" + INVENTORY_ENTRY_ID + "}";

  /**
   * The constant inventory_health_check.
   */
  public static final String INVENTORY_HEALTH_CHECK = INVENTORY_ENTRY_ROOT + "health";
}
