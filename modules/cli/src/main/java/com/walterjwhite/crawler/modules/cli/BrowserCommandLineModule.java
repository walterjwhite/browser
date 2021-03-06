package com.walterjwhite.linux.builder.impl.cli;

import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.criteria.CriteriaBuilderModule;
import com.walterjwhite.datastore.modules.guice.persist.annotation.JPAUnit;
import com.walterjwhite.download.impl.DownloadConfiguration;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.linux.builder.api.model.configuration.BuildConfiguration;
import com.walterjwhite.linux.builder.impl.service.LinuxBuilderModule;
import org.reflections.Reflections;

public class BrowserCommandLineModule extends AbstractCommandLineModule {

  @Property(JPAUnit.class)
  protected String jpaUnit;

  public LinuxBuilderCommandLineModule(Reflections reflections) {
    super(reflections, LinuxBuilderOperatingMode.class);
  }

  @Override
  protected void doConfigure() {
    bind(AbstractCommandLineHandler.class).to(LinuxBuilderCommandLineHandler.class);
    bind(BuildConfiguration.class).toProvider(BuildConfigurationProvider.class);
    bind(DownloadConfiguration.class).toProvider(DownloadConfigurationProvider.class);

    install(new LinuxBuilderModule());
    install(new CriteriaBuilderModule());
    install(new GoogleGuicePersistModule());

    // TODO: install this module inside the google guice create module whilst keeping one set of
    // properties
    install(new JpaPersistModule(jpaUnit));
  }
}
