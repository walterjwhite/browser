package com.walterjwhite.browser.modules.ashot.renderer;

import com.walterjwhite.browser.api.handler.PostGetHandler;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.api.service.PageRendererService;
import javax.inject.Inject;

/** Automatically take screenshots after navigating to a page. */
public class ScreenshotPageRendererService implements PostGetHandler {
  protected final PageRendererService pageRendererService;

  @Inject
  public ScreenshotPageRendererService(PageRendererService pageRendererService) {
    super();
    this.pageRendererService = pageRendererService;
  }

  @Override
  public void doPostGet(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
    pageRendererService.save(browserService, browserSessionResourceURI);
  }
}
