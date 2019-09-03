package com.walterjwhite.browser.plugins.craigslist;

import com.walterjwhite.browser.impl.service.BrowserServiceModule;
import com.walterjwhite.browser.plugins.virgin.pulse.VirginPulseModule;
import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.api.repository.CriteriaBuilderModule;
import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.file.impl.service.DefaultFileStorageModule;
import com.walterjwhite.file.providers.local.service.FileStorageModule;
import com.walterjwhite.google.guice.property.test.GuiceTestModule;
import com.walterjwhite.google.guice.property.test.PropertyValuePair;
import org.reflections.Reflections;

public class VirginPulseTestModule extends GuiceTestModule {
  public VirginPulseTestModule(Class testClass, PropertyValuePair... propertyValuePairs) {
    super(testClass, propertyValuePairs);
  }

  public VirginPulseTestModule(
      Class testClass, Reflections reflections, PropertyValuePair... propertyValuePairs) {
    super(testClass, reflections, propertyValuePairs);
  }

  @Override
  protected void configure() {
    super.configure();

    install(new FileStorageModule());
    install(new DefaultFileStorageModule());
    install(new CompressionModule());
    install(new EncryptionModule());
    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
    install(new CriteriaBuilderModule());

    install(new VirginPulseModule());

    install(new BrowserServiceModule());
  }
}
