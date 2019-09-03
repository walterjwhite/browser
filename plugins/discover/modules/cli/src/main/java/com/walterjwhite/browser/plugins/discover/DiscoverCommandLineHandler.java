package com.walterjwhite.browser.plugins.discover;

import com.walterjwhite.browser.plugins.discover.api.property.DiscoverPassword;
import com.walterjwhite.browser.plugins.discover.api.property.DiscoverUsername;
import com.walterjwhite.browser.plugins.discover.api.service.DiscoverService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class DiscoverCommandLineHandler extends AbstractCommandLineHandler {
  protected final DiscoverService discoverService;
  protected final ClientAccount account;

  @Inject
  public DiscoverCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(DiscoverUsername.class) String discoverUsername,
      @Property(DiscoverPassword.class) String discoverPassword,
      DiscoverService discoverService) {
    super(shutdownTimeoutInSeconds);

    this.discoverService = discoverService;
    account = new ClientAccount();
    //    account.setClientId(new EncryptedEntity(discoverUsername));
    //    account.getChallengeResponses().add(new ChallengeResponse(discoverPassword);
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    discoverService.login(account);
    discoverService.logout();
    // discoverService.execute(vanguardCredentials, vanguardPlugin);
  }
}
