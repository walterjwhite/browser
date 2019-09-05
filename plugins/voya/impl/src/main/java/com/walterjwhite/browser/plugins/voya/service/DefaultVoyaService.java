package com.walterjwhite.browser.plugins.voya.service;

import com.walterjwhite.browser.api.NavigationException;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.voya.api.model.VoyaCredentials;
import com.walterjwhite.browser.plugins.voya.api.property.VoyaUrl;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaPlugin;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaService;
import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DefaultVoyaService implements VoyaService {
  protected final BrowserService browserService;

  protected final String voyaUrl;
  protected final TokenService tokenService;

  @Inject
  public DefaultVoyaService(
      BrowserService browserService,
      @Property(VoyaUrl.class) final String voyaUrl,
      TokenService tokenService) {
    super();
    this.browserService = browserService;
    this.voyaUrl = voyaUrl;
    this.tokenService = tokenService;
  }

  @Override
  public void execute(VoyaCredentials voyaCredentials, VoyaPlugin voyaPlugin) {
    try {
      login(voyaCredentials);

      voyaPlugin.setBrowserService(browserService);
      voyaPlugin.execute();

      logout();
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  public void login(VoyaCredentials voyaCredentials) {
    navigateToVoya();
    enterUsername(voyaCredentials);
    enterPassword(voyaCredentials);
    submitAuthentication();
    handle2FactorAuthentication();

    // updateAccountBalances();
  }

  protected void navigateToVoya() {
    browserService.get(new ResourceURI(voyaUrl));
  }

  protected void enterUsername(VoyaCredentials voyaCredentials) {
    browserService
        .getWebDriver()
        .findElement(By.id("emailOrUsername"))
        .sendKeys(voyaCredentials.getUsername().getPlainText());
    browserService.renderPage();
  }

  protected void enterPassword(VoyaCredentials voyaCredentials) {
    final WebElement passwordElement = browserService.getWebDriver().findElement(By.id("password"));
    passwordElement.sendKeys(voyaCredentials.getPassword().getPlainText());
    browserService.renderPage();
  }

  protected void submitAuthentication() {
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"doc-main-inner\"]/compose/div/div[1]/login-block/form/div/voya-button/span"))
        .click();

    browserService.renderPage();
  }

  protected void handle2FactorAuthentication() {
    try {
      final WebElement answerElement = browserService.getWebDriver().findElement(By.id("otppswd"));
      final String token = getToken();

      answerElement.sendKeys(token);
      browserService.renderPage();

      browserService.getWebDriver().findElement(By.id("submit-button")).click();
      browserService.renderPage();

      // register this device
      browserService
          .getWebDriver()
          .findElement(
              By.xpath(
                  "//*[@id=\"doc-main-inner\"]/compose/main/form/div[2]/voya-field/div/label/span[2]/b"))
          .click();
      browserService.renderPage();

      // continue
      browserService
          .getWebDriver()
          .findElement(
              By.xpath("//*[@id=\"doc-main-inner\"]/compose/main/form/div[4]/voya-button/span"))
          .click();
      browserService.renderPage();

    } catch (NoSuchElementException e) {
      doNoTokenRequired();
    }
  }

  protected String getToken() {
    return tokenService.getToken();
  }

  protected void doNoTokenRequired() {}

  public void updateAccountBalances() {
    try {
      WebElement webElement =
          browserService
              .getWebDriver()
              .findElement(
                  By.xpath(
                      "//*[@id=\"main-content\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]/span/a/span/span[1]"));
      handleElement(webElement);
    } catch (NoSuchElementException e) {
      throw new NavigationException(browserService.getWebDriver().getPageSource(), e);
    }
  }

  protected void handleElement(WebElement webElement) {}

  public void logout() {
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"doc-header\"]/header/voya-header/div/div[1]/voya-top-nav/div/div/voya-main-nav/div/main-nav-base-menu/div/base-menu-item[5]/a"))
        .click();
    browserService.renderPage();
  }

  @Override
  public BrowserService getBrowserService() {
    return browserService;
  }
}
