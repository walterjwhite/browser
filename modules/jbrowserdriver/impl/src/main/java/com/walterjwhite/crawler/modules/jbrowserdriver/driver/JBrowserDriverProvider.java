package com.walterjwhite.crawler.modules.jbrowserdriver.driver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import javax.inject.Inject;
import javax.inject.Provider;
import org.openqa.selenium.WebDriver;

public class JBrowserDriverProvider implements Provider<WebDriver> {
  protected final JBrowserDriverConfiguration jBrowserDriverConfiguration;
  protected final Settings.Builder builder;

  @Inject
  public JBrowserDriverProvider(
      JBrowserDriverConfiguration jBrowserDriverConfiguration, Settings.Builder builder) {
    super();

    this.jBrowserDriverConfiguration = jBrowserDriverConfiguration;
    this.builder = builder;
  }

  @Override
  public WebDriver get() {
    return (new JBrowserDriver(builder.build()));
  }
}
