package com.walterjwhite.browser.plugins.prudential.api.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.identity.api.model.account.ClientAccount;

public interface PrudentialService {
  void login(ClientAccount clientAccount);

  void logout();

  void execute(ClientAccount clientAccount, PrudentialPlugin prudentialPlugin);

  BrowserService getBrowserService();
}
