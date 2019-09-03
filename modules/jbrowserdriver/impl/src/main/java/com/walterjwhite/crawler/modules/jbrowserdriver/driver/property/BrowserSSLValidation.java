package com.walterjwhite.crawler.modules.jbrowserdriver.driver.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public enum BrowserSSLValidation implements ConfigurableProperty {
  @DefaultValue
  trustanything;
}
