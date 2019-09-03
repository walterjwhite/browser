package com.walterjwhite.browser.plugins.vanguard.api.service;

import com.walterjwhite.browser.api.service.BrowserService;

public interface VanguardPlugin /*extends Callable*/ {
  void setBrowserService(BrowserService browserService);

  void execute();
}
