package com.bci.desafio.desafio.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

  private int minLength;
  private int maxLength;
  private boolean requireUppercase;
  private boolean requireLowercase;
  private boolean requireDigit;
  private boolean requireSpecial;

  public PasswordValidator(int minLength, int maxLength, boolean requireUppercase, boolean requireLowercase, boolean requireDigit, boolean requireSpecial) {
      this.minLength = minLength;
      this.maxLength = maxLength;
      this.requireUppercase = requireUppercase;
      this.requireLowercase = requireLowercase;
      this.requireDigit = requireDigit;
      this.requireSpecial = requireSpecial;
  }

  public String getRegex() {
      StringBuilder regex = new StringBuilder("^");
      if (requireLowercase) {
          regex.append("(?=.*[a-z])");
      }
      if (requireUppercase) {
          regex.append("(?=.*[A-Z])");
      }
      if (requireDigit) {
          regex.append("(?=.*\\d)");
      }
      if (requireSpecial) {
          regex.append("(?=.*[@#$%^&+=])");
      }
      regex.append(".{" + minLength + "," + maxLength + "}$");
      return regex.toString();
  }

  public boolean isValidPassword(String password) {
      if (password == null) {
          return false;
      }
      Pattern pattern = Pattern.compile(getRegex());
      Matcher matcher = pattern.matcher(password);
      return matcher.matches();
  }

  /*public static void main(String[] args) {
      PasswordValidator validator = new PasswordValidator(8, 20, true, true, true, true);
      String password = "SecureP@ssw0rd";
      if (validator.isValidPassword(password)) {
          System.out.println("La contraseña es segura.");
      } else {
          System.out.println("La contraseña no es segura.");
      }
  }*/
}
