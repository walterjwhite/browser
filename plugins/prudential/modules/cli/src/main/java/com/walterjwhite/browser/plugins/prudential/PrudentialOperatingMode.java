package com.walterjwhite.browser.plugins.prudential;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum PrudentialOperatingMode implements OperatingMode {
  @DefaultValue
  Default(PrudentialCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  PrudentialOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
