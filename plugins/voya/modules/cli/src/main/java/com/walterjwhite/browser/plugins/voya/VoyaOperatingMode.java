package com.walterjwhite.browser.plugins.voya;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum VoyaOperatingMode implements OperatingMode {
  @DefaultValue
  Default(VoyaCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  VoyaOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
