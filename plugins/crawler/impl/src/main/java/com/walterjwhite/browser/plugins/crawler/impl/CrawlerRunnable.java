package com.walterjwhite.browser.plugins.crawler.impl;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.api.service.QueueService;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.transaction.Transactional;

@SubscribeTo(eventClass = CrawlSessionResourceURI.class)
public class CrawlerRunnable extends AbstractRunnable {
  protected final BrowserService browserService;
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public CrawlerRunnable(
      CrawlSession crawlSession,
      BrowserService browserService,
      Provider<Repository> repositoryProvider,
      QueueService queueService) {
    super();
    this.browserService = browserService;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  protected void doCall() throws Exception {
    visit();
  }

  @Transactional
  public void visit() {
    // NOTE: all of the downstream services are handled automatically by observing the
    // @AfterNavigateTo event
    // browserService.get(queued..getResourceURI());

    // entity.setBrowserSessionResourceURI(browserService.getCurrentResourceURI());
    // repositoryProvider.get().merge(entity);
  }
}
