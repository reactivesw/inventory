package io.reactivesw.inventory.application.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Davis on 16/12/10.
 */
@Data
public class PagedQueryResult<T> {
  /**
   * The Offset.
   */
  private Integer offset;

  /**
   * The Count.
   */
  private Integer count;

  /**
   * The Total.
   */
  private Integer total;

  /**
   * The Results.
   */
  private List<T> results;
}
