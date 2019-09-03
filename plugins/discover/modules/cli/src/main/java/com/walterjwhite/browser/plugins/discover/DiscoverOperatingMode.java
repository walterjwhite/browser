package com.walterjwhite.browser.plugins.discover;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum DiscoverOperatingMode implements OperatingMode {
  @DefaultValue
  Default(DiscoverCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  DiscoverOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
