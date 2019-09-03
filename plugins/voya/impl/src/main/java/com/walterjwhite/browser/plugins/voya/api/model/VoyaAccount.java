package com.walterjwhite.browser.plugins.voya.api.model;

import com.walterjwhite.browser.plugins.voya.api.enumeration.AccountType;
import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.ArrayList;
import java.util.List;

public class VoyaAccount extends AbstractNamedEntity {
  protected String accountNumber;
  protected AccountType accountType;
  protected List<VoyaAccountHistory> vanguardAccountHistories = new ArrayList<>();
}
