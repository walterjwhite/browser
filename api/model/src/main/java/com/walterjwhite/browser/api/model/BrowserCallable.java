package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

/** This mirrors the Job API ... */
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class BrowserCallable extends AbstractNamedEntity {

  public BrowserCallable(String browserCallableClassName) {
    super(browserCallableClassName);
  }
}
