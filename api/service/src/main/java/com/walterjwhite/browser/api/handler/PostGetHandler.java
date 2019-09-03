package com.walterjwhite.browser.api.handler;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;

public interface PostGetHandler {
  void doPostGet(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI);
}
