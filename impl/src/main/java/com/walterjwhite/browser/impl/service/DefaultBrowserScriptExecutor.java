package com.walterjwhite.browser.impl.service;

import com.walterjwhite.browser.api.enumeration.BrowserScriptActionType;
import com.walterjwhite.browser.api.model.BrowserScript;
import com.walterjwhite.browser.api.service.BrowserScriptActionTypeHandler;
import com.walterjwhite.browser.api.service.BrowserScriptExecutor;
import com.walterjwhite.browser.api.service.BrowserService;
import javax.inject.Inject;

public class DefaultBrowserScriptExecutor implements BrowserScriptExecutor {
  protected final BrowserService browserService;

  @Inject
  public DefaultBrowserScriptExecutor(BrowserService browserService) {
    this.browserService = browserService;
  }

  @Override
  public void run(BrowserScript browserScript) {
    for (String line : browserScript.getScript().split("\n")) {
      final String[] lineComponents = line.split(",");

      BrowserScriptActionType browserScriptActionType =
          BrowserScriptActionType.valueOf(lineComponents[0]);
      BrowserScriptActionTypeHandler browserScriptActionTypeHandler =
          BrowserScriptActionTypeHandler.get(browserScriptActionType);
      browserScriptActionTypeHandler.execute(browserService, lineComponents[1]);
    }
  }
}
