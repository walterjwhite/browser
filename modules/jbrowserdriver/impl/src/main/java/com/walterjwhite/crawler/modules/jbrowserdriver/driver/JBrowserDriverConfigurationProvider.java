package com.walterjwhite.crawler.modules.jbrowserdriver.driver;

import com.walterjwhite.crawler.modules.jbrowserdriver.driver.property.*;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import com.walterjwhite.property.api.property.ProxyType;
import com.walterjwhite.property.impl.annotation.Property;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Provider;

public class JBrowserDriverConfigurationProvider implements Provider<JBrowserDriverConfiguration> {
  protected final JBrowserDriverConfiguration configuration;

  @Inject
  public JBrowserDriverConfigurationProvider(
      @Property(BrowserCachePath.class) String browserCachePath,
      @Property(ProxyHost.class) Optional proxyHost,
      @Property(ProxyPort.class) Optional proxyPort,
      @Property(ProxyType.class) Optional proxyType,
      @Property(SaveAttachments.class) boolean saveAttachments,
      @Property(Timeout.class) int timeout,
      @Property(UberJarPath.class) String uberJarPath,
      @Property(BrowserSSLValidation.class) BrowserSSLValidation browserSSLValidation,
      @Property(AjaxResourceTimeout.class) int ajaxResourceTimeout,
      @Property(AjaxWait.class) int ajaxWait,
      @Property(LogWire.class) boolean logWire,
      @Property(LogJavascript.class) boolean logJavascript,
      @Property(Debug.class) boolean debug) {

    super();

    if (debug) {
      logWire = true;
      logJavascript = true;
    }

    configuration =
        new JBrowserDriverConfiguration(
            uberJarPath,
            timeout,
            logJavascript,
            logWire,
            saveAttachments,
            browserCachePath,
            proxyType,
            proxyHost,
            proxyPort,
            browserSSLValidation,
            ajaxResourceTimeout,
            ajaxWait);
  }

  @Override
  public JBrowserDriverConfiguration get() {
    return configuration;
  }
}
