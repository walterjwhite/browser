package com.walterjwhite.browser.plugins.crawler.impl;

import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import com.walterjwhite.queue.api.service.QueueService;
import javax.inject.Inject;

/**
 * Finds all URLs that need crawled and submits them to the queue Isn't this redundant? We merely
 * need to save a CrawlSessionResourceURI and that would fire an event which would then be picked up
 * by the crawler callable This may only need to handle jobs that would exist @ startup through a
 * SQL script
 */
public class CrawlerServiceStartupJob implements StartupAware {
  protected final QueueService queueService;

  @Inject
  public CrawlerServiceStartupJob(QueueService queueService) {
    this.queueService = queueService;
  }

  @Override
  public void onStartup() throws Exception {
    findUrlsToCrawl();
  }

  protected void findUrlsToCrawl() {
    //        try {
    //            for (CrawlSessionResourceURI crawlSessionResourceURI :
    //                    crawlSessionResourceURIRepositoryProvider
    //                            .get()
    //                            .findByCrawlSessionAndNotCrawled(crawlSession)) {
    //                doQueue(crawlSessionResourceURI);
    //            }
    //
    //        } catch (NoResultException e) {
    //            noResultsToCrawl();
    //        }
  }

  //  @Transactional
  //  protected void doQueue(CrawlSessionResourceURI crawlSessionResourceURI) {
  //    crawlSessionResourceURI.setCrawlJobStatus(CrawlJobStatus.Queued);
  //    crawlSessionResourceURIRepositoryProvider.get().create(crawlSessionResourceURI);
  //
  //    queueService.queue(crawlSessionResourceURI);
  //    // LOGGER.info("queuedJob:" + queuedJob);
  //  }

  protected void noResultsToCrawl() {}
}
