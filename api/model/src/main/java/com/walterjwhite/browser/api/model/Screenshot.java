package com.walterjwhite.browser.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class Screenshot extends AbstractEntity {
  @EqualsAndHashCode.Exclude
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected File file;

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(nullable = false, updatable = false)
  protected BrowserSessionResourceURI browserSessionResourceURI;

  protected LocalDateTime screenshotDateTime = LocalDateTime.now();

  public Screenshot(File file, BrowserSessionResourceURI browserSessionResourceURI) {
    super();
    this.file = file;
    this.browserSessionResourceURI = browserSessionResourceURI;
  }
}
