package com.walterjwhite.browser.plugins.crawler;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.handler.PostGetHandler;
import com.walterjwhite.browser.plugins.crawler.api.model.CrawlSession;
import com.walterjwhite.browser.plugins.crawler.api.service.CrawlerService;
import com.walterjwhite.browser.plugins.crawler.api.service.ResourceURIQueue;
import com.walterjwhite.browser.plugins.crawler.api.service.Seeder;
import com.walterjwhite.browser.plugins.crawler.generator.DealseaResourceGenerator;
import com.walterjwhite.browser.plugins.crawler.generator.LxerResourceGenerator;
import com.walterjwhite.browser.plugins.crawler.generator.TechbargainsResourceGenerator;
import com.walterjwhite.browser.plugins.crawler.impl.factory.CrawlSessionProvider;
import com.walterjwhite.browser.plugins.crawler.impl.postprocessor.CraigslistPostProcessorService;
import com.walterjwhite.browser.plugins.crawler.impl.postprocessor.DealseaPostProcessorService;
import com.walterjwhite.browser.plugins.crawler.impl.postprocessor.TechbargainsPostProcessorService;
import com.walterjwhite.browser.plugins.crawler.impl.service.DefaultCrawlerService;
import com.walterjwhite.browser.plugins.crawler.impl.service.DefaultResourceURIQueue;
import com.walterjwhite.browser.plugins.crawler.impl.service.DefaultSeeder;

public class CrawlerModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CrawlSession.class).toProvider(CrawlSessionProvider.class);

    bind(CrawlerService.class).to(DefaultCrawlerService.class);
    bind(ResourceURIQueue.class).to(DefaultResourceURIQueue.class);
    bind(Seeder.class).to(DefaultSeeder.class);

    //    bind(DealseaResourceGenerator.class).asEagerSingleton();
    //    bind(LxerResourceGenerator.class).asEagerSingleton();
    //    bind(TechbargainsResourceGenerator.class).asEagerSingleton();
    //
    //    bind(CraigslistPostProcessorService.class).asEagerSingleton();
    //    bind(DealseaPostProcessorService.class).asEagerSingleton();
    //    bind(TechbargainsPostProcessorService.class).asEagerSingleton();

    Multibinder<PostGetHandler> postGetHandlers =
        Multibinder.newSetBinder(binder(), PostGetHandler.class);
    postGetHandlers.addBinding().to(DealseaResourceGenerator.class);
    postGetHandlers.addBinding().to(LxerResourceGenerator.class);
    postGetHandlers.addBinding().to(TechbargainsResourceGenerator.class);

    Multibinder<PagePostProcessor> pagePostProcessorMultibinder =
        Multibinder.newSetBinder(binder(), PagePostProcessor.class);
    pagePostProcessorMultibinder.addBinding().to(CraigslistPostProcessorService.class);
    pagePostProcessorMultibinder.addBinding().to(DealseaPostProcessorService.class);
    pagePostProcessorMultibinder.addBinding().to(TechbargainsPostProcessorService.class);
  }
}
