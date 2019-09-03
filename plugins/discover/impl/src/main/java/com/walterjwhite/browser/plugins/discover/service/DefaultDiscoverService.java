package com.walterjwhite.browser.plugins.discover.service;

import com.walterjwhite.browser.api.NavigationException;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.discover.api.service.DiscoverPlugin;
import com.walterjwhite.browser.plugins.discover.api.service.DiscoverService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class DefaultDiscoverService implements DiscoverService {
  protected final BrowserService browserService;

  @Inject
  public DefaultDiscoverService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  @Override
  public void execute(ClientAccount clientAccount, DiscoverPlugin discoverPlugin) {
    try {
      login(clientAccount);

      discoverPlugin.setBrowserService(browserService);
      discoverPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void login(ClientAccount clientAccount) {
    browserService.get(new ResourceURI("https://www.discover.com"));

    browserService
        .getWebDriver()
        .findElement(By.id("userid-content"))
        .sendKeys(clientAccount.getPrincipalId());
    browserService
        .getWebDriver()
        .findElement(By.id("password-content"))
        // TODO: tie this to a standard challenge of type password
        .sendKeys(clientAccount.getChallengeResponses().get(0).getResponse().getPlainText());

    browserService.renderPage();

    // discover thinks our browser is out of date
    // if it isn't the user agent, then it must be some sort of javascript code that is making that
    // determination
    browserService.getWebDriver().findElement(By.id("password-content")).submit();

    browserService.renderPage();

    // browserService.getWebDriver().findElement(By.id("log-in-button")).click();
  }

  public void logout() {
    browserService
        .getWebDriver()
        .findElement(By.xpath("/html/body/div/header/div/div/div[2]/div[2]/ul/li[6]/a"))
        .click();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
