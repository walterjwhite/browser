package com.walterjwhite.browser.modules.ashot.renderer;

import com.walterjwhite.browser.modules.ashot.renderer.property.AshotScrollTimeout;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;
import javax.inject.Provider;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AShotProvider implements Provider<AShot> {
  protected final int scrollTimeout;

  @Inject
  public AShotProvider(@Property(AshotScrollTimeout.class) int scrollTimeout) {
    this.scrollTimeout = scrollTimeout;
  }

  @Override
  public AShot get() {
    // TODO: double-check the scoping of this, create a new instance for each request
    // each browser service should have a single instance
    final AShot ashot = new AShot();
    ashot.shootingStrategy(ShootingStrategies.viewportPasting(scrollTimeout));

    return (ashot);
  }
}
