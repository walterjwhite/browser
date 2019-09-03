package com.walterjwhite.browser.plugins.virgin.pulse;

import com.google.inject.AbstractModule;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.virgin.pulse.api.service.VirginPulseService;
import com.walterjwhite.browser.plugins.virgin.pulse.service.DefaultVirginPulseService;
import com.walterjwhite.serialization.modules.snakeyaml.SnakeyamlSerializationServiceModule;

public class VirginPulseModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(VirginPulseService.class).to(DefaultVirginPulseService.class);

    install(new BrowserServiceModule());
    install(new SnakeyamlSerializationServiceModule());
  }
}
