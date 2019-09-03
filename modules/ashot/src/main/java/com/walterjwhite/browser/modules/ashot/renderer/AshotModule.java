package com.walterjwhite.browser.modules.ashot.renderer;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.walterjwhite.browser.api.handler.PostGetHandler;
import com.walterjwhite.browser.api.service.PageRendererService;
import ru.yandex.qatools.ashot.AShot;

public class AshotModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(AShot.class).toProvider(AShotProvider.class);

    bind(PageRendererService.class).to(AShotPageRendererService.class);

    Multibinder<PostGetHandler> postGetHandlers =
        Multibinder.newSetBinder(binder(), PostGetHandler.class);
    postGetHandlers.addBinding().to(ScreenshotPageRendererService.class);
  }
}
