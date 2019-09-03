package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.model.Screenshot;

public interface PageRendererService {
  Screenshot save(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI);
}
