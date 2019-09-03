package com.walterjwhite.browser.plugins.craigslist;

import com.walterjwhite.google.guice.GuiceHelper;
import org.junit.After;
import org.junit.Before;

public class VirginPulseTester {
  @Before
  public void onBefore() throws Exception {
    GuiceHelper.addModules(new VirginPulseTestModule(getClass()));

    GuiceHelper.setup();
  }

  @After
  public void onAfter() {
    GuiceHelper.stop();
  }

  //  @Test
  //  public void testPost() throws IOException {
  //    VirginPulseService craigslistService =
  //        GuiceHelper.getGuiceApplicationInjector().getInstance(VirginPulseService.class);
  //    SerializationService serializationService =
  //        GuiceHelper.getGuiceApplicationInjector().getInstance(SerializationService.class);
  //
  //    craigslistService.post(
  //        (CraigslistPost)
  //            serializationService.deserialize(
  //                new FileInputStream(new File("src/test/resources/posts/f90.yaml")),
  //                CraigslistPost.class));
  //  }
}
