package com.walterjwhite.browser.plugins.vanguard;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.vanguard.api.service.VanguardService;
import com.walterjwhite.browser.plugins.vanguard.service.DefaultVanguardService;

public class VanguardModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(VanguardService.class).to(DefaultVanguardService.class);

    install(new BrowserServiceModule());
    //    install(new SnakeyamlSerializationServiceModule());
  }
}
