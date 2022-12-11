package com.thaunknown.index.api;

public class Users {
  private Integer id;
  private String login;
  private String password;

  public void setId (Integer id) {
    this.id = id;
  }

  public Integer getId () {
    return id;
  }

  public void setLogin (String login) {
    this.login = login;
  }

  public String getLogin () {
    return login;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public String getPassword () {
    return password;
  }
}
