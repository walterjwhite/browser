package com.walterjwhite.browser.plugins.prudential.api.service;

import com.walterjwhite.browser.api.service.BrowserService;

public interface PrudentialPlugin /*extends Callable*/ {
  void setBrowserService(BrowserService browserService);

  void execute();
}
