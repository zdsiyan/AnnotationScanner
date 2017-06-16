package com.github.zdsiyan.test.entity;

import com.github.zdsiyan.test.annotation.Mark;

public class Demo {
  private String str1;
  private String str2;
  @Mark(value="code")
  private String str3;
  
  @Mark(value="city")
  private long lg1;
  private long lg2;
  private long lg3;
  
  public String getStr1() {
    return str1;
  }
  public void setStr1(String str1) {
    this.str1 = str1;
  }
  public String getStr2() {
    return str2;
  }
  public void setStr2(String str2) {
    this.str2 = str2;
  }
  public String getStr3() {
    return str3;
  }
  public void setStr3(String str3) {
    this.str3 = str3;
  }
  public long getLg1() {
    return lg1;
  }
  public void setLg1(long lg1) {
    this.lg1 = lg1;
  }
  public long getLg2() {
    return lg2;
  }
  public void setLg2(long lg2) {
    this.lg2 = lg2;
  }
  public long getLg3() {
    return lg3;
  }
  public void setLg3(long lg3) {
    this.lg3 = lg3;
  }
}
