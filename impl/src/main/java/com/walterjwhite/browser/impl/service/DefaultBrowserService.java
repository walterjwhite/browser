package com.walterjwhite.browser.impl.service;

import com.walterjwhite.browser.api.handler.PagePostProcessor;
import com.walterjwhite.browser.api.handler.PostGetHandler;
import com.walterjwhite.browser.api.handler.PreGetHandler;
import com.walterjwhite.browser.api.model.BrowserSession;
import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.api.service.PageRendererService;
import com.walterjwhite.browser.impl.service.util.URIUtil;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.transaction.Transactional;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DefaultBrowserService implements BrowserService {
  protected final Provider<Repository> repositoryProvider;

  protected BrowserSession browserSession;
  protected final WebDriver webDriver;
  protected final PageRendererService pageRendererService;
  protected final Set<PostGetHandler> postGetHandlers;
  protected final Set<PagePostProcessor> pagePostProcessors;
  protected final Set<PreGetHandler> preGetHandlers;

  @Inject
  public DefaultBrowserService(
      Provider<Repository> repositoryProvider,
      BrowserSession browserSession,
      WebDriver webDriver,
      PageRendererService pageRendererService,
      Set<PostGetHandler> postGetHandlers,
      Set<PreGetHandler> preGetHandlers,
      Set<PagePostProcessor> pagePostProcessors) {
    super();

    this.repositoryProvider = repositoryProvider;
    this.browserSession = browserSession;
    this.webDriver = webDriver;
    this.pageRendererService = pageRendererService;

    this.postGetHandlers = postGetHandlers;
    this.preGetHandlers = preGetHandlers;
    this.pagePostProcessors = pagePostProcessors;
  }

  @Transactional
  @Override
  public void get(ResourceURI resourceURI) {
    get(resourceURI, null);
  }

  @Transactional
  @Override
  public void get(ResourceURI resourceURI, BrowserSessionResourceURI parent) {
    BrowserSessionResourceURI browserSessionResourceURI =
        new BrowserSessionResourceURI(resourceURI, browserSession, parent);
    browserSessionResourceURI.setIndex(browserSession.getBrowserSessionResourceURIs().size());
    browserSession.getBrowserSessionResourceURIs().add(browserSessionResourceURI);

    doPreGet(browserSessionResourceURI);
    doGet(resourceURI.getName());
    browserSession =
        repositoryProvider
            .get()
            .create(browserSession); // how to differentiate create/update in JDO (TODO:)
    browserSessionResourceURI =
        browserSession
            .getBrowserSessionResourceURIs()
            .get(browserSession.getBrowserSessionResourceURIs().size() - 1);

    doPagePostProcess(browserSessionResourceURI);
    doPostGet(browserSessionResourceURI);
  }

  protected void doPagePostProcess(BrowserSessionResourceURI browserSessionResourceURI) {
    for (PagePostProcessor pagePostProcessor : pagePostProcessors) {
      if (pagePostProcessor.isPostProcess(
          URIUtil.removeProtocol(browserSessionResourceURI.getResourceURI().getName())))
        pagePostProcessor.doPostProcess(this, browserSessionResourceURI);
    }
  }

  protected void doPostGet(BrowserSessionResourceURI browserSessionResourceURI) {
    for (PostGetHandler postGetHandler : postGetHandlers) {
      postGetHandler.doPostGet(this, browserSessionResourceURI);
    }
  }

  protected void doPreGet(BrowserSessionResourceURI browserSessionResourceURI) {
    for (PreGetHandler preGetHandler : preGetHandlers) {
      preGetHandler.doPreGet(this, browserSessionResourceURI);
    }
  }

  protected void doGet(final String uri) {
    webDriver.get(uri);
  }

  @Override
  public void renderPage() {
    pageRendererService.save(this, getCurrentResourceURI());
  }

  public void executeScript(final String scriptText) {
    final JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript(scriptText);
  }

  /** Wraps query to actually perform the delete. */
  public void deleteElements(final String query) {
    final StringBuilder buffer = new StringBuilder();

    buffer.append("v = ");
    buffer.append(query);
    buffer.append("if(v != null && v.length > 0){v[0].remove();}\n");

    executeScript(buffer.toString());
  }

  @Override
  public BrowserSessionResourceURI getCurrentResourceURI() {
    if (browserSession.getBrowserSessionResourceURIs().size() > 0) {
      return browserSession
          .getBrowserSessionResourceURIs()
          .get(browserSession.getBrowserSessionResourceURIs().size() - 1);
    }

    throw new IllegalStateException("Browser has not visited any resource URIs yet.");
  }

  @Override
  public WebDriver getWebDriver() {
    return webDriver;
  }

  @Override
  public void close() {
    // TODO: cleanup the temporary files, they're littered in /tmp
    webDriver.quit();
  }
}
