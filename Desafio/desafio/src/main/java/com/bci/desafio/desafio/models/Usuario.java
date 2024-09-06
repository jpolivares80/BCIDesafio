package com.bci.desafio.desafio.models;

import java.util.List;

public class Usuario {
  private String name;
  private String email;
  private String password;
  private List<Telefono> phones;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Telefono> getPhones() {
    return phones;
  }

  public void setPhones(List<Telefono> phones) {
    this.phones = phones;
  }

}
