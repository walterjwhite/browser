package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.*;

// for subclasses
// @Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
// @PersistenceCapable
@Entity
public class URIFilterConfiguration extends AbstractNamedEntity {
  @Column protected boolean caseInsensitive;

  @Column(nullable = false)
  protected String pattern;

  public URIFilterConfiguration(String pattern) {
    super();
    this.pattern = pattern;
  }
}
