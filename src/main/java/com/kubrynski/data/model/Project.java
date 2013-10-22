package com.kubrynski.data.model;

import javax.persistence.Entity;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
@Entity
public class Project extends AbstractEntity {

  private String name;

  public Project() {
  }

  public Project(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
