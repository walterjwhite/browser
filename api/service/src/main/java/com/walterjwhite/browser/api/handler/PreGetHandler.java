package com.walterjwhite.browser.api.handler;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;

public interface PreGetHandler {
  void doPreGet(BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI);
}
