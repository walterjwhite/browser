package com.walterjwhite.browser.plugins.discover.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.discover.api.service.DiscoverPlugin;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractDiscoverPlugin implements DiscoverPlugin {
  protected BrowserService browserService;
}
