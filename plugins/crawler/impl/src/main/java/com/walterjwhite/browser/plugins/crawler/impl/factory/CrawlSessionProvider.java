package com.walterjwhite.browser.plugins.crawler.impl.factory;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSessionResourceURI;
import com.walterjwhite.browser.plugins.crawler.api.service.Seeder;
import com.walterjwhite.datastore.api.repository.Repository;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.transaction.Transactional;

public class CrawlSessionProvider implements Provider<CrawlSession> {
  protected final Seeder seeder;

  protected final Repository repository;

  @Inject
  public CrawlSessionProvider(Seeder seeder, Repository repository) {
    this.seeder = seeder;
    this.repository = repository;
  }

  @Transactional
  public CrawlSession get() {
    CrawlSession crawlSession = new CrawlSession(seeder.get().getCrawl());
    repository.create(crawlSession);
    addSeedURIs(crawlSession);
    return (repository.create(
        crawlSession)); // TODO: JDO, support differentiating between create / update
  }

  protected void addSeedURIs(CrawlSession crawlSession) {
    for (ResourceURI resourceURI : crawlSession.getCrawl().getSeedResourceURIs()) {
      addSeedURI(crawlSession, resourceURI);
    }
  }

  protected void addSeedURI(CrawlSession crawlSession, ResourceURI resourceURI) {
    crawlSession
        .getCrawlSessionResourceURIs()
        .add(new CrawlSessionResourceURI(crawlSession, resourceURI, 0));
  }
}
