package com.walterjwhite.crawler.modules.jbrowserdriver.driver.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

// @NOTE: no default value, it MUST be specified!!!
public interface UberJarPath extends ConfigurableProperty {
  @DefaultValue String Default = "deprecated";
}
