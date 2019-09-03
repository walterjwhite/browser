package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class BrowserSessionResourceURI extends AbstractEntity {
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected ResourceURI resourceURI;

  @Column protected int index = 0;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected BrowserSession browserSession;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime dateVisited = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(updatable = false)
  protected BrowserSessionResourceURI parent;

  @EqualsAndHashCode.Exclude
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected Set<BrowserSessionResourceURI> children = new HashSet<>();

  public BrowserSessionResourceURI(
      ResourceURI resourceURI, BrowserSession browserSession, BrowserSessionResourceURI parent) {
    this();

    this.resourceURI = resourceURI;
    this.browserSession = browserSession;
    this.parent = parent;
  }

  public BrowserSessionResourceURI(
      ResourceURI resourceURI,
      BrowserSession browserSession,
      BrowserSessionResourceURI parent,
      int index) {
    this();

    this.resourceURI = resourceURI;
    this.browserSession = browserSession;
    this.parent = parent;
    this.index = index;
  }

  public BrowserSessionResourceURI() {
    super();

    children = new HashSet<>();
  }
}
