package com.walterjwhite.browser.plugins.vanguard;

import com.walterjwhite.browser.plugins.vanguard.api.property.VanguardPassword;
import com.walterjwhite.browser.plugins.vanguard.api.property.VanguardUsername;
import com.walterjwhite.browser.plugins.vanguard.api.service.VanguardService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class VanguardCommandLineHandler extends AbstractCommandLineHandler {
  protected final VanguardService vanguardService;
  protected final ClientAccount clientAccount;

  @Inject
  public VanguardCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(VanguardUsername.class) String vanguardUsername,
      @Property(VanguardPassword.class) String vanguardPassword,
      VanguardService vanguardService) {
    super(shutdownTimeoutInSeconds);

    this.vanguardService = vanguardService;
    clientAccount = new ClientAccount(); // (vanguardUsername, vanguardPassword);
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    vanguardService.login(clientAccount);
    vanguardService.logout();
    // vanguardService.execute(credentials, vanguardPlugin);
  }
}
