package com.walterjwhite.browser.plugins.pnc;

import com.walterjwhite.browser.plugins.pnc.api.property.PNCPassword;
import com.walterjwhite.browser.plugins.pnc.api.property.PNCUsername;
import com.walterjwhite.browser.plugins.pnc.api.service.PNCService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class PNCCommandLineHandler extends AbstractCommandLineHandler {
  protected final PNCService pncService;
  protected final ClientAccount account;

  @Inject
  public PNCCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(PNCUsername.class) String pncUsername,
      @Property(PNCPassword.class) String pncPassword,
      PNCService pncService) {
    super(shutdownTimeoutInSeconds);

    this.pncService = pncService;
    account = new ClientAccount(); // (pncUsername, pncPassword);
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    pncService.login(account);
    pncService.logout();
    // pncService.execute(vanguardCredentials, vanguardPlugin);
  }
}
