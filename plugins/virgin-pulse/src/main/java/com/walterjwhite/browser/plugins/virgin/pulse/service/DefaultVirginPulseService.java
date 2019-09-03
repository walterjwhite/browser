package com.walterjwhite.browser.plugins.virgin.pulse.service;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseCredentials;
import com.walterjwhite.browser.plugins.virgin.pulse.api.service.VirginPulseService;
import javax.inject.Inject;
import org.openqa.selenium.By;

public class DefaultVirginPulseService implements VirginPulseService {
  protected final BrowserService browserService;

  @Inject
  public DefaultVirginPulseService(BrowserService browserService) {
    super();
    this.browserService = browserService;
  }

  protected void navigateToDashboard() {
    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"percentageCircle\"]/div[2]/ng-transclude/div[1]/div[2]"))
        .click();
  }

  protected void navigateToStats() {
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"core-menuitem-stats\"]")).click();
  }

  protected void navigateToHealthyHabits() {
    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"core-menuitem-tracking\"]"))
        .click();
  }

  protected void logHeartRateTracking() {
    navigateToHealthyHabits();
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"track-no\"]")).click();
  }

  protected void logMood() {
    navigateToHealthyHabits();
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"tracker_10\"]/div/div/div[1]/div/form/div/div[2]/div/div[5]/button/img"))
        .click();
  }

  protected void logSleep(int sleepInHours) {
    navigateToHealthyHabits();
    browserService
        .getWebDriver()
        .findElement(By.xpath("//*[@id=\"tracker_10\"]/div/div/div[1]/di"))
        .sendKeys(Integer.toString(sleepInHours));
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"track-sleep\"]")).click();
  }

  protected void logStairs() {
    navigateToHealthyHabits();
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"track-yes\"]")).click();
  }

  protected void logHealthyBreakfast() {
    navigateToHealthyHabits();
    browserService.getWebDriver().findElement(By.xpath("//*[@id=\"track-yes\"]")).click();
  }

  protected void logWorkout_health() {
    navigateToHealthyHabits();
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"tracker_42\"]/div/div/div[1]/div[1]/steps-converter-input/div/div[1]/div[1]/input"))
        .click();
  }

  protected void logWorkout(int activityTimeInMinutes) {
    navigateToStats();

    browserService
        .getWebDriver()
        .findElement(
            By.xpath("//*[@id=\"activities\"]/activitystats-widget/div/div[2]/div[2]/div/div[1]"))
        .click();

    // Cycling 16-19 mph/25-31 kph
    final String activityName = "Cycling 16-19 mph/25-31 kph";
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"activities\"]/activitystats-widget/div/div[3]/steps-converter-widget/div/div[2]/steps-converter-input/div/div[1]/div[1]/input"))
        .sendKeys(activityName);
    // click the searched result?
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"activities\"]/activitystats-widget/div/div[3]/steps-converter-widget/div/div[2]/steps-converter-input/div/div[1]/div[2]/div[1]"))
        .click();

    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"activities\"]/activitystats-widget/div/div[3]/steps-converter-widget/div/div[2]/steps-converter-input/div/div[2]/div[2]/div[1]/input"))
        .sendKeys(Integer.toString(activityTimeInMinutes));
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"activities\"]/activitystats-widget/div/div[3]/steps-converter-widget/div/div[2]/steps-converter-input/div/div[2]/button"))
        .click();
  }

  protected void logWeight(int weightInPounds) {
    navigateToDashboard();

    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"weight\"]/weightstats-widget/div/div[2]/div[2]/weight-entry-form/div/div[1]/div[1]"))
        .click();

    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"weight\"]/weightstats-widget/div/div[2]/div[2]/weight-entry-form/div/div[2]/div[2]/input"))
        .sendKeys(Integer.toString(weightInPounds));
    browserService
        .getWebDriver()
        .findElement(
            By.xpath(
                "//*[@id=\"weight\"]/weightstats-widget/div/div[2]/div[2]/weight-entry-form/div/div[2]/div[3]/button/span"))
        .click();
  }

  protected void login(VirginPulseCredentials virginPulseCredentials) {
    browserService.get(new ResourceURI("https://member.virginpulse.com/login.aspx"));

    browserService
        .getWebDriver()
        .findElement(By.id("oUserID"))
        .sendKeys(virginPulseCredentials.getEmailAddress());
    browserService
        .getWebDriver()
        .findElement(By.id("txtPlainPassword"))
        .sendKeys(virginPulseCredentials.getPassword());

    browserService.getWebDriver().findElement(By.id("oLogon")).click();
  }

  protected void logout() {
    browserService.getWebDriver().findElement(By.id("core-menuitem-logout")).click();
  }
}
