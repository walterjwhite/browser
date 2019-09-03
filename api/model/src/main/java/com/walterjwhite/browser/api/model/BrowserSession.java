package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class BrowserSession extends AbstractEntity {
  @EqualsAndHashCode.Exclude
  @Column(nullable = false, updatable = false)
  protected LocalDateTime startDateTime = LocalDateTime.now();
  /*
  @ManyToOne
  @JoinColumn
  protected Node node;
  */
  @Column(unique = true, nullable = false, updatable = false)
  protected String uuid = UUID.randomUUID().toString();

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "browserSession")
  protected List<BrowserSessionResourceURI> browserSessionResourceURIs = new ArrayList<>();

  public BrowserSession(List<BrowserSessionResourceURI> browserSessionResourceURIs) {
    this();

    if (browserSessionResourceURIs != null && !browserSessionResourceURIs.isEmpty())
      this.browserSessionResourceURIs.addAll(browserSessionResourceURIs);
  }
}
