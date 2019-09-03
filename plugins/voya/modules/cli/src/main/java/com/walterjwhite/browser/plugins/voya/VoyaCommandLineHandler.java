package com.walterjwhite.browser.plugins.voya;

import com.walterjwhite.browser.plugins.voya.api.model.VoyaCredentials;
import com.walterjwhite.browser.plugins.voya.api.property.VoyaPassword;
import com.walterjwhite.browser.plugins.voya.api.property.VoyaUsername;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class VoyaCommandLineHandler extends AbstractCommandLineHandler {
  protected final VoyaService voyaService;
  protected final VoyaCredentials voyaCredentials;

  @Inject
  public VoyaCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(VoyaUsername.class) String voyaUsername,
      @Property(VoyaPassword.class) String voyaPassword,
      VoyaService voyaService) {
    super(shutdownTimeoutInSeconds);

    this.voyaService = voyaService;
    voyaCredentials = new VoyaCredentials(); // voyaUsername, voyaPassword);
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    voyaService.login(voyaCredentials);
    voyaService.logout();
    // voyaService.execute(vanguardCredentials, vanguardPlugin);
  }
}
