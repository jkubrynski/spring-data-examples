package com.kubrynski.data.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-03-30
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String uuid = UUID.randomUUID().toString();

  public Long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  @Override
  public int hashCode() {
    return uuid.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AbstractEntity)) {
      return false;
    }
    AbstractEntity other = (AbstractEntity) obj;
    return getUuid().equals(other.getUuid());
  }

}
