package com.walterjwhite.browser.plugins.pnc.api.service;

import com.walterjwhite.browser.api.service.BrowserService;

public interface PNCPlugin /*extends Callable*/ {
  void setBrowserService(BrowserService browserService);

  void execute();
}
