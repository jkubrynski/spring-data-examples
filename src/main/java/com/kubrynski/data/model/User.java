package com.kubrynski.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-11
 */
@Entity
public class User extends AbstractEntity {

  private String login;
  private String email;

  @ManyToOne
  private Company company;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
