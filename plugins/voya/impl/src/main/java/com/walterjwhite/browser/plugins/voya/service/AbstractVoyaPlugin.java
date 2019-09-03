package com.walterjwhite.browser.plugins.voya.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.voya.api.service.VoyaPlugin;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractVoyaPlugin implements VoyaPlugin {
  protected BrowserService browserService;
}
