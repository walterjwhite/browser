package com.walterjwhite.crawler.modules.cli;

import com.walterjwhite.browser.plugins.crawler.CrawlerModule;
import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.crawler.impl.service.BrowserServiceModule;
import com.walterjwhite.crawler.impl.service.driver.JBrowserDriverModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.criteria.CriteriaBuilderModule;
import com.walterjwhite.file.impl.service.DefaultFileStorageModule;
import com.walterjwhite.file.providers.local.service.FileStorageModule;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.job.impl.JobModule;

public class BrowserMain {
  public static void main(final String[] arguments) throws Exception {
    GuiceHelper.addModules(
        new JBrowserDriverModule(),
        new BrowserServiceModule(),
        new CrawlerModule(),
        //        new GuavaEventBusModule(),
        //        new GuavaExecutorServiceModule(),
        // new JpaPersistModule(propertyManager.get(JPAUnit.class)),
        new GoogleGuicePersistModule(),
        new DefaultFileStorageModule(),
        new FileStorageModule(),
        new EncryptionModule(),
        new CompressionModule(),
        new CriteriaBuilderModule(),
        new JobModule());
    GuiceHelper.setup();
  }
}
