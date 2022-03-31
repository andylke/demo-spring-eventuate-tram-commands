package com.github.andylke.demo.account;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;

  private BigDecimal availableAmount;

  private BigDecimal reservedAmount;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getAvailableAmount() {
    return availableAmount;
  }

  public void setAvailableAmount(BigDecimal availableAmount) {
    this.availableAmount = availableAmount;
  }

  public BigDecimal getReservedAmount() {
    return reservedAmount;
  }

  public void setReservedAmount(BigDecimal reservedAmount) {
    this.reservedAmount = reservedAmount;
  }
}
