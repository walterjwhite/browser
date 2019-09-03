package com.walterjwhite.browser.plugins.prudential;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.prudential.api.service.PrudentialService;
import com.walterjwhite.browser.plugins.prudential.service.DefaultPrudentialService;

public class PrudentialModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PrudentialService.class).to(DefaultPrudentialService.class);

    install(new BrowserServiceModule());
    //    install(new SnakeyamlSerializationServiceModule());
  }
}
