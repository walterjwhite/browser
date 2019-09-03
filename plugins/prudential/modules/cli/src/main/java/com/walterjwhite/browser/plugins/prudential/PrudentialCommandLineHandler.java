package com.walterjwhite.browser.plugins.prudential;

import com.walterjwhite.browser.plugins.prudential.api.property.PrudentialPassword;
import com.walterjwhite.browser.plugins.prudential.api.property.PrudentialUsername;
import com.walterjwhite.browser.plugins.prudential.api.service.PrudentialService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class PrudentialCommandLineHandler extends AbstractCommandLineHandler {
  protected final PrudentialService prudentialService;
  protected final ClientAccount clientAccount;

  @Inject
  public PrudentialCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(PrudentialUsername.class) String prudentialUsername,
      @Property(PrudentialPassword.class) String prudentialPassword,
      PrudentialService prudentialService) {
    super(shutdownTimeoutInSeconds);

    this.prudentialService = prudentialService;
    clientAccount = new ClientAccount(); // (prudentialUsername, prudentialPassword);
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    prudentialService.login(clientAccount);
    prudentialService.logout();
    // prudentialService.execute(vanguardCredentials, vanguardPlugin);
  }
}
