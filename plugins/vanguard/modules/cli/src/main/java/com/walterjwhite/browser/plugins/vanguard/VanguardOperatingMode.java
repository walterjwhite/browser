package com.walterjwhite.browser.plugins.vanguard;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum VanguardOperatingMode implements OperatingMode {
  @DefaultValue
  Default(VanguardCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  VanguardOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
