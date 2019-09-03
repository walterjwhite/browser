package com.walterjwhite.browser.plugins.discover.api.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.identity.api.model.account.ClientAccount;

public interface DiscoverService {
  void login(ClientAccount clientAccount);

  void logout();

  void execute(ClientAccount clientAccount, DiscoverPlugin discoverPlugin);

  BrowserService getBrowserService();
}
