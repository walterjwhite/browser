package com.walterjwhite.browser.plugins.voya.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class VoyaAccountHistory extends AbstractEntity {
  protected LocalDate asOfDate;
  protected VoyaAccount voyaAccount;
  protected BigDecimal balance;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VoyaAccountHistory that = (VoyaAccountHistory) o;
    return Objects.equals(asOfDate, that.asOfDate) && Objects.equals(voyaAccount, that.voyaAccount);
  }

  @Override
  public int hashCode() {

    return Objects.hash(asOfDate, voyaAccount);
  }
}
