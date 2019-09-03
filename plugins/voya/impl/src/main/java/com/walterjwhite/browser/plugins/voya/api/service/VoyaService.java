package com.walterjwhite.browser.plugins.voya.api.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.voya.api.model.VoyaCredentials;

public interface VoyaService {
  void login(VoyaCredentials voyaCredentials);

  void logout();

  void execute(VoyaCredentials voyaCredentials, VoyaPlugin voyaPlugin);

  BrowserService getBrowserService();
}
