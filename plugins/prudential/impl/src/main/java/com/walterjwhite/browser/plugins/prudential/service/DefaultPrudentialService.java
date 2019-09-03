package com.walterjwhite.browser.plugins.prudential.service;

import com.walterjwhite.browser.api.NavigationException;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.prudential.api.service.PrudentialPlugin;
import com.walterjwhite.browser.plugins.prudential.api.service.PrudentialService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class DefaultPrudentialService implements PrudentialService {
  protected final BrowserService browserService;

  @Inject
  public DefaultPrudentialService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  @Override
  public void execute(ClientAccount clientAccount, PrudentialPlugin prudentialPlugin) {
    try {
      login(clientAccount);

      prudentialPlugin.setBrowserService(browserService);
      prudentialPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void login(ClientAccount clientAccount) {
    browserService.get(new ResourceURI("https://www.prudential.com/login"));

    browserService
        .getWebDriver()
        .findElement(By.id("username"))
        .sendKeys(clientAccount.getClientId().getPlainText());
    browserService
        .getWebDriver()
        .findElement(By.id("password"))
        .sendKeys(clientAccount.getChallengeResponses().get(0).getResponse().getPlainText());
    browserService.renderPage();

    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"pruws_row2\"]/div/div/div/div[1]/div/section/div[2]/div[2]/div/section/form/div[3]/div/button"))
        .click();
    browserService.renderPage();

    handleSecurityQuestions();
    getAccountBalance();
  }

  protected String getAccountBalance() {
    return browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"summaryWidget\"]/div[1]/div/div/div[1]/p"))
        .getText();
  }

  protected void handleSecurityQuestions() {
    try {
      final String securityQuestion =
          browserService
              .getWebDriver()
              .findElement(
                  By.xpath(
                      "//*[@id=\"maincontent\"]/div[2]/div/div/section/div[3]/htmlwrapper/div/div/div/div/div/div/div/form/div[2]/label"))
              .getText()
              .trim();
      if ("What is your father's middle name?".equalsIgnoreCase(securityQuestion)) {
        browserService.getWebDriver().findElement(By.id("answer")).sendKeys("");
        browserService
            .getWebDriver()
            .findElement(
                By.xpath(
                    "//*[@id=\"maincontent\"]/div[2]/div/div/section/div[3]/htmlwrapper/div/div/div/div/div/div/div/form/div[3]/fieldset/div[1]/label/input"))
            .click();

        browserService.renderPage();

        browserService
            .getWebDriver()
            .findElement(
                By.xpath(
                    "//*[@id=\"maincontent\"]/div[2]/div/div/section/div[3]/htmlwrapper/div/div/div/div/div/div/div/form/div[3]/div/button"))
            .click();
        browserService.renderPage();
      }
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void logout() {
    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"ace-logo-salutation-bar\"]/div[3]/a/span[2]"))
        .click();
    browserService.renderPage();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
