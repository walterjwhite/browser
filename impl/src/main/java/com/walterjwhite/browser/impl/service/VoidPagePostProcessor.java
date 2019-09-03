package com.walterjwhite.browser.impl.service;

import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;

public class VoidPagePostProcessor implements PagePostProcessor {
  @Override
  public void doPostProcess(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {}

  @Override
  public boolean isPostProcess(String uri) {
    return false;
  }
}
