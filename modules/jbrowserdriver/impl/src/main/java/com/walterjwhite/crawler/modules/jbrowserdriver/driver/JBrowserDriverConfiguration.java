package com.walterjwhite.crawler.modules.jbrowserdriver.driver;

import com.walterjwhite.crawler.modules.jbrowserdriver.driver.property.BrowserSSLValidation;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO: write guava CLI property injection service and setup these properties
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class JBrowserDriverConfiguration {

  protected String uberJarPath;

  protected int timeout;

  protected boolean logJavaScript;
  protected boolean logWire;

  protected boolean saveAttachments;

  protected String cachePath;

  protected Optional<String> proxyType;

  protected Optional<String> proxyHost;

  protected Optional<Integer> proxyPort;

  protected BrowserSSLValidation browserSSLValidation;

  protected int ajaxResourceTimeout;
  protected int ajaxWait;
}
