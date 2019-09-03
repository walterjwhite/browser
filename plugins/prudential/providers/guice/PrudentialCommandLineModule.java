package com.walterjwhite.browser.plugins.prudential;

import com.walterjwhite.browser.modules.ashot.renderer.AshotModule;
import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.crawler.modules.jbrowserdriver.JBrowserDriverModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.api.repository.CriteriaBuilderModule;
import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.file.impl.service.DefaultFileStorageModule;
import com.walterjwhite.file.providers.local.service.FileStorageModule;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.PropertyManager;
import org.reflections.Reflections;

public class PrudentialCommandLineModule extends AbstractCommandLineModule {

  public PrudentialCommandLineModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, PrudentialOperatingMode.class);
  }

  @Override
  protected void doCliConfigure() {
    bind(AbstractCommandLineHandler.class).to(PrudentialCommandLineHandler.class);

    install(new PrudentialModule());
    install(new FileStorageModule());
    install(new DefaultFileStorageModule());
    install(new CompressionModule());
    install(new EncryptionModule());

    install(new JBrowserDriverModule());
    install(new AshotModule());

    install(new CriteriaBuilderModule());
    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
  }
}
