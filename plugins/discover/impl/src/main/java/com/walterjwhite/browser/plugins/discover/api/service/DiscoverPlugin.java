package com.walterjwhite.browser.plugins.discover.api.service;

import com.walterjwhite.browser.api.service.BrowserService;

public interface DiscoverPlugin /*extends Callable*/ {
  void setBrowserService(BrowserService browserService);

  void execute();
}
