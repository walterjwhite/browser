package com.walterjwhite.browser.plugins.discover;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.discover.api.service.DiscoverService;
import com.walterjwhite.browser.plugins.discover.service.DefaultDiscoverService;

public class DiscoverModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(DiscoverService.class).to(DefaultDiscoverService.class);

    install(new BrowserServiceModule());
    //    install(new SnakeyamlSerializationServiceModule());
  }
}
