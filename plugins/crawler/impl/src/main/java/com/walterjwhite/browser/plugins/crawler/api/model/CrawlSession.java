package com.walterjwhite.browser.plugins.crawler.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@NoArgsConstructor
@Entity
public class CrawlSession extends AbstractEntity {
  @Column(nullable = false, updatable = false)
  protected LocalDateTime startTime;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Crawl crawl;

  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected List<CrawlSessionResourceURI> crawlSessionResourceURIs = new ArrayList<>();

  public CrawlSession(Crawl crawl) {
    this();
    this.crawl = crawl;
  }
}
