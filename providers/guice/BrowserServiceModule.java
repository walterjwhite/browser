package com.walterjwhite.browser.impl.service;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.handler.PreGetHandler;
import com.walterjwhite.browser.api.service.BrowserService;

public class BrowserServiceModule extends AbstractModule {
  @Override
  protected void configure() {

    bind(BrowserService.class).to(DefaultBrowserService.class);

    //    bind(ScreenshotPageRendererService.class).asEagerSingleton();

    Multibinder<PreGetHandler> preGetHandlers =
        Multibinder.newSetBinder(binder(), PreGetHandler.class);

    Multibinder<PagePostProcessor> pagePostProcessors =
        Multibinder.newSetBinder(binder(), PagePostProcessor.class);
    pagePostProcessors.addBinding().to(VoidPagePostProcessor.class);
  }
}
