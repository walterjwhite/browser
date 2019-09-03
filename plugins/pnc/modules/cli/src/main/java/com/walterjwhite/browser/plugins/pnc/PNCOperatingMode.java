package com.walterjwhite.browser.plugins.pnc;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum PNCOperatingMode implements OperatingMode {
  @DefaultValue
  Default(PNCCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  PNCOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
