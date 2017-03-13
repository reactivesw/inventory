package io.reactivesw.inventory.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Davis on 16/11/21.
 */
@Getter
@Setter
public class QueryConditions {

  String expandId;

  Integer version;

  /**
   * name(en="Pro T-Shirt")
   */
  String where;

  /**
   * name.em
   */
  String sort;

  String sortOrder;

  String page;

  String perPage;

  String expand;

  Boolean staged;

  Boolean stagedId;
}
