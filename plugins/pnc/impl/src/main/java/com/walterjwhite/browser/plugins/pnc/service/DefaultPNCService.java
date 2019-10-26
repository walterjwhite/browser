package com.walterjwhite.browser.plugins.pnc.service;

import com.walterjwhite.browser.api.NavigationException;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.pnc.api.service.PNCPlugin;
import com.walterjwhite.browser.plugins.pnc.api.service.PNCService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DefaultPNCService implements PNCService {
  protected final BrowserService browserService;

  @Inject
  public DefaultPNCService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  @Override
  public void execute(ClientAccount account, PNCPlugin PNCPlugin) {
    try {
      login(account);

      // we may not want to inject another instance because we would lose state
      PNCPlugin.setBrowserService(browserService);
      PNCPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void login(ClientAccount account) {
    final String[] securityAnswers = new String[] {""};

    browserService.get(new ResourceURI("https://pnc.com"));

    enterUsername(account);
    enterPassword(account);

    browserService.renderPage();

    handle2FactorAuthentication();

    // updateAccountBalances();
  }

  protected void enterUsername(ClientAccount account) {
    browserService
        .getWebDriver()
        .findElement(By.id("userId"))
        .sendKeys(account.getClientId().getPlainText());
    browserService.renderPage();
  }

  protected void enterPassword(ClientAccount account) {
    final WebElement passwordElement =
        browserService.getWebDriver().findElement(By.id("passwordInputField"));
    // TODO: type this to password so that other challenges may be stored
    passwordElement.sendKeys(account.getChallengeResponses().get(0).getResponse().getPlainText());
    browserService.renderPage();

    passwordElement.submit();
  }

  protected void handle2FactorAuthentication() {
    try {
      browserService.getWebDriver().switchTo().frame(0);

      final WebElement questionElement =
          browserService
              .getWebDriver()
              .findElement(
                  By.xpath("//*[@id=\"leftCol_twoThirds\"]/div/div[2]/table/tbody/tr[2]/td[2]"));
      final String question = questionElement.getText().trim();

      final String answer = getAnswer(question);
      final WebElement answerElement = browserService.getWebDriver().findElement(By.id("answer"));

      answerElement.sendKeys(answer);

      browserService.getWebDriver().findElement(By.id("continue")).click();
      browserService.renderPage();
    } catch (NoSuchElementException e) {
      doNoSecurityCodeRequired();
    }
  }

  protected void doNoSecurityCodeRequired() {}

  protected String getAnswer(final String question) {
    // SECURITY QUESTIONS
    // SECURITY - REMOVE
    if ("What was the first name of your first manager?".equalsIgnoreCase(question)) {
      return "";
    } else if ("What is your mother's middle name?".equalsIgnoreCase(question)) {
      return "";
    } else if ("What is your father's middle name?".equalsIgnoreCase(question)) {
      return "";
    }

    throw new IllegalStateException("Unmatched Question:" + question);
  }

  public void updateAccountBalances() {
    try {
      WebElement webElement =
          browserService
              .getWebDriver()
              .findElement(
                  By.xpath(
                      "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]/span/a/span/span[1]"));
      getWebElement(webElement);
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  protected void getWebElement(WebElement webElement) {}

  public void logout() {
    browserService.renderPage();
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"topLinks\"]/ul/li[3]/a")).click();
    browserService.renderPage();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
