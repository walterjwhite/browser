package com.walterjwhite.browser.plugins.pnc.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.pnc.api.service.PNCPlugin;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractPNCPlugin implements PNCPlugin {
  protected BrowserService browserService;
}
