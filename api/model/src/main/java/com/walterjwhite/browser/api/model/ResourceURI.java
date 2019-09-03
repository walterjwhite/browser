package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
// @PersistenceCapable
@Entity
public class ResourceURI extends AbstractNamedEntity {
  @OneToMany(cascade = CascadeType.ALL)
  protected Set<BrowserSessionResourceURI> browserSessionResourceURISES;

  public ResourceURI(String uri) {
    super(uri);
  }

  public ResourceURI() {
    super();

    browserSessionResourceURISES = new HashSet<>();
  }
}
