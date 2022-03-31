package com.github.andylke.demo.customer;

public class CreditReservedReply {

  private ReserveCreditResponse response;

  public CreditReservedReply() {}

  public CreditReservedReply(ReserveCreditResponse response) {
    this.response = response;
  }

  public ReserveCreditResponse getResponse() {
    return response;
  }

  public void setResponse(ReserveCreditResponse response) {
    this.response = response;
  }
}
