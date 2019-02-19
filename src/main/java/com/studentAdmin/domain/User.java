package com.studentAdmin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
  @TableId("user_id")
  private long userId;
  @TableField("user_name")
  private String userName;
  @TableField("sex")
  private long sex;
  @TableField("user_password")
  private String userPassword;
  @TableField("user_occupation")
  private String userOccupation;
  @TableField("register_date")
  private java.sql.Timestamp registerDate;
  @TableField("user_address")
  private String userAddress;
  @TableField("user_age")
  private long userAge;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public long getSex() {
    return sex;
  }

  public void setSex(long sex) {
    this.sex = sex;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserOccupation() {
    return userOccupation;
  }

  public void setUserOccupation(String userOccupation) {
    this.userOccupation = userOccupation;
  }


  public java.sql.Timestamp getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(java.sql.Timestamp registerDate) {
    this.registerDate = registerDate;
  }


  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }


  public long getUserAge() {
    return userAge;
  }

  public void setUserAge(long userAge) {
    this.userAge = userAge;
  }

}
