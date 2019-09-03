package com.walterjwhite.browser.plugins.crawler.api.model;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.enumeration.CrawlJobStatus;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
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
public class CrawlSessionResourceURI extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected CrawlSession crawlSession;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected ResourceURI resourceURI;

  @EqualsAndHashCode.Exclude @Column protected int depth;

  //  @Column protected boolean crawled;
  @EqualsAndHashCode.Exclude
  @Enumerated(EnumType.STRING)
  @Column
  protected CrawlJobStatus crawlJobStatus;

  @EqualsAndHashCode.Exclude @ManyToOne @JoinColumn
  protected BrowserSessionResourceURI fromBrowserSessionResourceURI;

  /**
   * NOTE: this field will be empty until it is crawled, perhaps we use this as the criteria to
   * determine what needs crawled
   */
  @EqualsAndHashCode.Exclude @ManyToOne @JoinColumn
  protected BrowserSessionResourceURI browserSessionResourceURI;

  public CrawlSessionResourceURI(
      CrawlSession crawlSession,
      ResourceURI resourceURI,
      int depth,
      BrowserSessionResourceURI fromBrowserSessionResourceURI) {
    super();
    this.crawlSession = crawlSession;
    this.resourceURI = resourceURI;
    this.depth = depth;
    this.fromBrowserSessionResourceURI = fromBrowserSessionResourceURI;
    //    this.crawled = false;
    crawlJobStatus = CrawlJobStatus.New;
  }

  public CrawlSessionResourceURI(CrawlSession crawlSession, ResourceURI resourceURI, int depth) {
    super();
    this.crawlSession = crawlSession;
    this.resourceURI = resourceURI;
    this.depth = depth;
    //    this.crawled = false;
    crawlJobStatus = CrawlJobStatus.New;
  }
}
