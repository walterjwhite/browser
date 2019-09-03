package com.walterjwhite.browser.plugins.discover;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.discover.api.property.DiscoverPassword;
import com.walterjwhite.browser.plugins.discover.api.property.DiscoverUsername;
import com.walterjwhite.encryption.property.IVFilePath;
import com.walterjwhite.encryption.property.KeyFilePath;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.google.guice.property.test.PropertyValuePair;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

@RunWith(Arquillian.class)
public class ArquillianWhichBrowserTest {
  // TODO: this won't work as it requires a container adapter and there isn't one for Guice
  @Deployment
  public static Archive<?> createDeployment() {
    return ShrinkWrap.create(JavaArchive.class, "test.jar")
        .addPackage(ArquillianWhichBrowserTest.class.getPackage())
        .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
  }

  @Before
  public void onBefore() {
    GuiceHelper.addModules(
        new DiscoverTestModule(
            getClass(),
            new Reflections(
                "com.walterjwhite",
                TypeAnnotationsScanner.class,
                SubTypesScanner.class,
                FieldAnnotationsScanner.class),
            new PropertyValuePair(KeyFilePath.class),
            new PropertyValuePair(IVFilePath.class),
            new PropertyValuePair(DiscoverUsername.class),
            new PropertyValuePair(DiscoverPassword.class)));
    GuiceHelper.setup();
  }

  @After
  public void onAfter() {
    GuiceHelper.stop();
  }

  @Test
  public void testBrowserVersion() {
    BrowserService browserService =
        GuiceHelper.getGuiceApplicationInjector().getInstance(BrowserService.class);

    browserService.get(new ResourceURI("https://whichbrowser.net/"));
  }
}
