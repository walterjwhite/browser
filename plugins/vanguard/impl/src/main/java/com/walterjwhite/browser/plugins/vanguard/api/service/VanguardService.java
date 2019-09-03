package com.walterjwhite.browser.plugins.vanguard.api.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.identity.api.model.account.ClientAccount;

public interface VanguardService {
  void login(ClientAccount clientAccount);

  void logout();

  void execute(ClientAccount clientAccount, VanguardPlugin discoverPlugin);

  BrowserService getBrowserService();
}
