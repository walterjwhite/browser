package com.walterjwhite.browser.plugins.pnc;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.pnc.api.service.PNCService;
import com.walterjwhite.browser.plugins.pnc.service.DefaultPNCService;

public class PNCModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PNCService.class).to(DefaultPNCService.class);

    install(new BrowserServiceModule());
    //    install(new SnakeyamlSerializationServiceModule());
  }
}
