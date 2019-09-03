package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.enumeration.BrowserScriptActionType;

// import com.walterjwhite.browser.api.model.ResourceURI;
// import org.openqa.selenium.By;

public enum BrowserScriptActionTypeHandler {
  Get(BrowserScriptActionType.Get) {
    protected void doExecute(BrowserService browserService, final String argument) {
      // browserService.get(new ResourceURI(argument));
    }
  },
  FindByID(BrowserScriptActionType.FindByID) {
    protected void doExecute(BrowserService browserService, final String argument) {
      // browserService.getWebDriver().findElement(By.id(argument));
    }
  },
  FindByTag(BrowserScriptActionType.FindByTag) {
    protected void doExecute(BrowserService browserService, final String argument) {
      // browserService.getWebDriver().findElement(By.tagName(argument));
    }
  },
  FindByXPath(BrowserScriptActionType.FindByXPath) {
    protected void doExecute(BrowserService browserService, final String argument) {
      // browserService.getWebDriver().findElement(By.xpath(argument));
    }
  };

  private final BrowserScriptActionType browserScriptActionType;

  BrowserScriptActionTypeHandler(BrowserScriptActionType browserScriptActionType) {
    this.browserScriptActionType = browserScriptActionType;
  }

  protected abstract void doExecute(BrowserService browserService, final String argument);

  public void execute(BrowserService browserService, final String argument) {
    doExecute(browserService, argument);
  }

  public static BrowserScriptActionTypeHandler get(
      BrowserScriptActionType browserScriptActionType) {
    for (BrowserScriptActionTypeHandler browserScriptActionTypeHandler : values()) {
      if (browserScriptActionType.equals(browserScriptActionTypeHandler.browserScriptActionType)) {
        return browserScriptActionTypeHandler;
      }
    }

    throw (new IllegalArgumentException(browserScriptActionType + " is not supported."));
  }
}
