package com.walterjwhite.browser.plugins.vanguard.service;

import com.walterjwhite.browser.api.NavigationException;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.vanguard.api.service.VanguardPlugin;
import com.walterjwhite.browser.plugins.vanguard.api.service.VanguardService;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.identity.api.service.TokenException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DefaultVanguardService implements VanguardService {
  protected final BrowserService browserService;

  @Inject
  public DefaultVanguardService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  @Override
  public void execute(ClientAccount clientAccount, VanguardPlugin vanguardPlugin) {
    try {
      login(clientAccount);

      vanguardPlugin.setBrowserService(browserService);
      vanguardPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void login(ClientAccount clientAccount) {
    browserService.get(new ResourceURI("https://investor.vanguard.com/home/"));

    browserService
        .getWebDriver()
        .findElement(By.id("USER"))
        .sendKeys(clientAccount.getClientId().getPlainText());
    browserService
        .getWebDriver()
        .findElement(By.id("PASSWORD"))
        .sendKeys(clientAccount.getChallengeResponses().get(0).getResponse().getPlainText());
    browserService.renderPage();

    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"login\"]/span")).click();
    browserService.renderPage();

    // browserService.getWebDriver().findElement(By.id("log-in-button")).click();

    handle2FactorAuthentication();

    updateAccountBalances();
  }

  protected void handle2FactorAuthentication() {
    try {
      final WebElement tokenElement =
          browserService.getWebDriver().findElement(By.id("LoginForm:ANSWER"));

      final String token = getToken();

      tokenElement.sendKeys(token);

      browserService.getWebDriver().findElement(By.id("LoginForm:DEVICE:0")).click();

      browserService.getWebDriver().findElement(By.id("LoginForm:ContinueInput")).click();
      browserService.renderPage();
    } catch (NoSuchElementException e) {
      handleNoTokenRequired();
    }
  }

  protected String getToken() {
    // LOGGER.warn("Please enter the token you received and hit enter:");

    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      return bufferedReader.readLine();
    } catch (IOException e) {
      throw new TokenException(e);
    }
  }

  protected void handleNoTokenRequired() {}

  public synchronized void updateAccountBalances() {
    browserService.renderPage();
    try {
      /*
      FluentWait<WebDriver> fluentWait =
          new FluentWait<>(browserService.getWebDriver())
              .withTimeout(Duration.ofSeconds(30))
              .pollingEvery(Duration.ofMillis(200))
              .ignoring(NoSuchElementException.class);

      Function<WebDriver, WebElement> function =
          new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
              return
                  browserService
                      .getWebDriver()
                      .findElement(
                          By.xpath(
                              "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div/span[2]"));
            }
          };

      fluentWait.until(function);
      browserService.renderPage();

      WebElement webElement = browserService
              .getWebDriver()
              .findElement(
                      By.xpath(
                              "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div/span[2]"));
                              */
      try {
        browserService.getWebDriver().wait(30);

        WebElement webElement =
            browserService
                .getWebDriver()
                .findElement(
                    By.xpath(
                        "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div/span[2]"));

        handleWebElement(webElement);
      } catch (InterruptedException e) {
        throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
      }
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  protected void handleWebElement(WebElement webElement) {}

  public void logout() {
    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"globalNavUtilityBar\"]/div/div/ul/li[5]/a/span"))
        .click();
    browserService.renderPage();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
