package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.model.ResourceURI;
import org.openqa.selenium.WebDriver;

public interface BrowserService /*<DriverType>*/ {
  //  void addPagePostProcessorService(PagePostProcessorService pagePostProcessorService);
  //
  //  void addURIFilter(URIFilter uriFilter);

  void get(ResourceURI resourceURI);

  void get(ResourceURI resourceURI, BrowserSessionResourceURI parent);

  void executeScript(final String script);

  void deleteElements(final String query);

  // TODO: support tags here
  // use entity tags ...
  // TODO: push to indexing service
  void renderPage();

  BrowserSessionResourceURI getCurrentResourceURI();

  /*DriverType*/ WebDriver getWebDriver();

  void close();
}
