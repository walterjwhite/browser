package com.walterjwhite.browser.plugins.voya.api.service;

import com.walterjwhite.browser.api.service.BrowserService;

public interface VoyaPlugin /*extends Callable*/ {
  void setBrowserService(BrowserService browserService);

  void execute();
}
