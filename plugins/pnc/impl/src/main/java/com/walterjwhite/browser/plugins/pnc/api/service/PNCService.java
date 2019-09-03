package com.walterjwhite.browser.plugins.pnc.api.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.identity.api.model.account.ClientAccount;

public interface PNCService {
  void login(ClientAccount clientAccount);

  void logout();

  void execute(ClientAccount clientAccount, PNCPlugin pncPlugin);

  BrowserService getBrowserService();
}
