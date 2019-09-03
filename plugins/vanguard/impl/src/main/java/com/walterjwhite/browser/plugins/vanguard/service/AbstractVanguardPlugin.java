package com.walterjwhite.browser.plugins.vanguard.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.vanguard.api.service.VanguardPlugin;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractVanguardPlugin implements VanguardPlugin {
  protected BrowserService browserService;
}
