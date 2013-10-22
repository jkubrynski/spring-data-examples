package com.kubrynski.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
@Entity
public class Company extends AbstractEntity {

  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<User> users = new HashSet<User>();

  public Company() {
  }

  public Company(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    user.setCompany(this);
    users.add(user);
  }
}
