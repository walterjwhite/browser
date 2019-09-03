package com.walterjwhite.browser.plugins.virgin.pulse.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
public class VirginPulseCredentials extends AbstractNamedEntity {
  protected String memberId;
  protected String emailAddress;
  protected transient String password;

  protected String passwordEncrypted;

  protected String passwordSalt;

  public VirginPulseCredentials(
      String name, String description, String memberId, String emailAddress, String password) {
    super(name, description);
    this.memberId = memberId;
    this.emailAddress = emailAddress;
    this.password = password;
  }
}
