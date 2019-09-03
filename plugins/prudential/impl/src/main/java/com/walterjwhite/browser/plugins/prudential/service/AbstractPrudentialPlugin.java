package com.walterjwhite.browser.plugins.prudential.service;

import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.browser.plugins.prudential.api.service.PrudentialPlugin;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractPrudentialPlugin implements PrudentialPlugin {
  protected BrowserService browserService;
}
