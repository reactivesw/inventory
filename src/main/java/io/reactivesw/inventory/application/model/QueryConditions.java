package io.reactivesw.inventory.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/11/21.
 */
@Getter
@Setter
public class QueryConditions {

  /**
   * The Expand id.
   */
  private String expandId;

  /**
   * The Version.
   */
  private Integer version;

  /**
   * example name(en="Pro T-Shirt").
   */
  private String where;

  /**
   * name.em
   */
  private String sort;

  /**
   * The Sort order.
   */
  private String sortOrder;

  /**
   * The Page.
   */
  private String page;

  /**
   * The Per page.
   */
  private String perPage;

  /**
   * The Expand.
   */
  private String expand;

  /**
   * The Staged.
   */
  private Boolean staged;

  /**
   * The Staged id.
   */
  private Boolean stagedId;
}
