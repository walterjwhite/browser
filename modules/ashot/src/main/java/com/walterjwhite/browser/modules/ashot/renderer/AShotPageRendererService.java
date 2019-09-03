package com.walterjwhite.browser.modules.ashot.renderer;

import com.walterjwhite.browser.api.model.BrowserSessionResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.api.service.PageRendererService;
import com.walterjwhite.browser.modules.ashot.renderer.property.AshotScreenshotOutputFormat;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.file.api.service.FileEntityOutputStream;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.property.impl.annotation.Property;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class AShotPageRendererService implements PageRendererService /*, Serializable*/ {
  protected final AShot ashot;

  protected final FileStorageService fileStorageService;
  protected final Repository repository;
  protected final AshotScreenshotOutputFormat ashotScreenshotOutputFormat;

  @Inject
  public AShotPageRendererService(
      AShot ashot,
      FileStorageService fileStorageService,
      Repository repository,
      @Property(AshotScreenshotOutputFormat.class)
          final AshotScreenshotOutputFormat ashotScreenshotOutputFormat) {
    super();

    this.ashot = ashot;
    this.fileStorageService = fileStorageService;
    this.repository = repository;
    this.ashotScreenshotOutputFormat = ashotScreenshotOutputFormat;
  }

  @Transactional
  @Override
  public com.walterjwhite.browser.api.model.Screenshot save(
      BrowserService browserService, BrowserSessionResourceURI browserSessionResourceURI) {
    try {
      final WebDriver webDriver = new Augmenter().augment(browserService.getWebDriver());
      Screenshot screenshot = ashot.takeScreenshot(webDriver);

      try (final FileEntityOutputStream fileEntityOutputStream =
          new FileEntityOutputStream(fileStorageService)) {

        ImageIO.write(
            screenshot.getImage(),
            ashotScreenshotOutputFormat.getExtension(),
            fileEntityOutputStream);
        com.walterjwhite.browser.api.model.Screenshot screenshotEntity =
            new com.walterjwhite.browser.api.model.Screenshot(
                fileEntityOutputStream.getFile(), browserSessionResourceURI);
        repository.create(
            screenshotEntity); // TODO: support differentiating between between create/update

        return screenshotEntity;
      }
    } catch (Exception e) {
      throw new AshotScreenshotException(browserService.getWebDriver().getPageSource(), e);
    }
  }
}
