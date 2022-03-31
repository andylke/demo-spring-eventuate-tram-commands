package com.github.andylke.demo.customer;

import java.math.BigDecimal;

public class ReserveCreditResponse {

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
