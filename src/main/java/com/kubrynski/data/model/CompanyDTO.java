package com.kubrynski.data.model;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public class CompanyDTO {

  public String name;

  public CompanyDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
