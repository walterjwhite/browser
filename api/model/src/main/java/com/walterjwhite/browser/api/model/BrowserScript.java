package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class BrowserScript extends AbstractNamedEntity {
  @Lob
  @Column(nullable = false)
  protected String script;

  public BrowserScript(String name, String script) {
    super(name);
    this.script = script;
  }
}
