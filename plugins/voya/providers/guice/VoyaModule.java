package com.walterjwhite.browser.plugins.voya;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaService;
import com.walterjwhite.browser.plugins.voya.service.DefaultVoyaService;

public class VoyaModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(VoyaService.class).to(DefaultVoyaService.class);

    install(new BrowserServiceModule());
    //    install(new SnakeyamlSerializationServiceModule());
  }
}
