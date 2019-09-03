package com.walterjwhite.browser.api.handler;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;

public interface PagePostProcessor {
  void doPostProcess(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI);

  boolean isPostProcess(final String uri);
}
