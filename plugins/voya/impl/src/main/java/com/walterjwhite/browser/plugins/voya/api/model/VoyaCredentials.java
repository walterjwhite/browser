package com.walterjwhite.browser.plugins.voya.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.encryption.model.EncryptedEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class VoyaCredentials extends AbstractEntity {

  /*
  @Encrypted(encryptionType = EncryptionType.Encrypt)
  protected String username;

  @EqualsAndHashCode.Exclude
  @EqualsAndHashCode.Exclude
  */ @Embedded protected EncryptedEntity username;

  /*
  @EqualsAndHashCode.Exclude
  @Encrypted(encryptionType = EncryptionType.Encrypt)
  protected String password;

  @EqualsAndHashCode.Exclude
  @EqualsAndHashCode.Exclude
  */ @Embedded protected EncryptedEntity password;

  /*
  public VoyaCredentials(String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  public VoyaCredentials() {
    super();
  }
  */
}
