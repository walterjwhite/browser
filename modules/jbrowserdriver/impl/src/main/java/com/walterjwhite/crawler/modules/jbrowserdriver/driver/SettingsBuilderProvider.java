package com.walterjwhite.crawler.modules.jbrowserdriver.driver;

import com.machinepublishers.jbrowserdriver.*;
import com.walterjwhite.property.api.enumeration.ProxyType;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;

public class SettingsBuilderProvider implements Provider<Settings.Builder> {
  protected final Settings.Builder builder = Settings.builder();
  protected final JBrowserDriverConfiguration jBrowserDriverConfiguration;

  @Inject
  public SettingsBuilderProvider(JBrowserDriverConfiguration jBrowserDriverConfiguration) {
    super();
    this.jBrowserDriverConfiguration = jBrowserDriverConfiguration;

    builder.logJavascript(true);
    builder.logWire(true);

    // builder.javaOptions("-classpath", jBrowserDriverConfiguration.getUberJarPath());
    builder.timezone(Timezone.AMERICA_NEWYORK);
    builder.ssl(jBrowserDriverConfiguration.getBrowserSSLValidation().name());
    // TODO: configure this
    //    builder.csrf("cryptedStepCheck", "cryptedStepCheck");

    builder.ajaxResourceTimeout(jBrowserDriverConfiguration.getAjaxResourceTimeout());
    builder.ajaxWait(jBrowserDriverConfiguration.getAjaxWait());
    //    builder.blockAds(true);
    //    builder.userAgent(UserAgent.CHROME);
    /*
    builder.userAgent(
        new UserAgent(
            UserAgent.Family.WEBKIT, "walterjwhite.com", "Linux", "Linux", "0.15", "browser"));
    */
    //    builder.userAgent(
    //        new UserAgent(
    //            UserAgent.Family.WEBKIT,
    //            "Google Inc.",
    //            "Linux",
    //            "Ubuntu 18.04",
    //            " Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
    // Chrome/64.0.3282.167 Safari/537.36",
    //            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
    // Chrome/64.0.3282.167 Safari/537.36"));
    final CustomUserAgent userAgent =
        new CustomUserAgent(
            UserAgent.Family.WEBKIT,
            "Google Inc.",
            "Linux",
            "x86_64",
            " Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36");
    builder.userAgent(userAgent);

    // don't wait forever trying to fetch a page ...
    if (jBrowserDriverConfiguration.getTimeout() > 0) {
      builder.connectTimeout(jBrowserDriverConfiguration.getTimeout());
    }

    if (jBrowserDriverConfiguration.isSaveAttachments()) {
      builder.saveAttachments(true);
    }
    if (jBrowserDriverConfiguration.getCachePath() != null
        && !jBrowserDriverConfiguration.getCachePath().isEmpty()) {
      builder.cacheDir(new File(jBrowserDriverConfiguration.getCachePath()));
    }

    if (jBrowserDriverConfiguration.getProxyType() != null
    /*&& proxyType > 0*/ ) {
      builder.proxy(
          new ProxyConfig(
              getProxy(jBrowserDriverConfiguration.getProxyType()),
              jBrowserDriverConfiguration.getProxyHost(),
              jBrowserDriverConfiguration.getProxyPort()));
    }
  }

  protected ProxyConfig.Type getProxy(ProxyType proxyType) {
    if (ProxyType.HTTP.equals(proxyType)) {
      return (ProxyConfig.Type.HTTP);
    }

    return (ProxyConfig.Type.SOCKS);
  }

  @Override
  public Settings.Builder get() {
    return (builder);
  }
}
