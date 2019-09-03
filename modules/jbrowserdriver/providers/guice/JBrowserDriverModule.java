package com.walterjwhite.crawler.modules.jbrowserdriver;

import com.google.inject.AbstractModule;
import com.machinepublishers.jbrowserdriver.Settings;
import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.crawler.modules.jbrowserdriver.driver.JBrowserDriverConfiguration;
import com.walterjwhite.crawler.modules.jbrowserdriver.driver.JBrowserDriverConfigurationProvider;
import com.walterjwhite.crawler.modules.jbrowserdriver.driver.JBrowserDriverProvider;
import com.walterjwhite.crawler.modules.jbrowserdriver.driver.SettingsBuilderProvider;
import org.openqa.selenium.WebDriver;

public class JBrowserDriverModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(WebDriver.class).toProvider(JBrowserDriverProvider.class);
    bind(JBrowserDriverConfiguration.class).toProvider(JBrowserDriverConfigurationProvider.class);
    bind(Settings.Builder.class).toProvider(SettingsBuilderProvider.class);

    install(new BrowserServiceModule());
  }
}
