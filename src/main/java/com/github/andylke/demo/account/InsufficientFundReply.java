package com.github.andylke.demo.account;

public class InsufficientFundReply {

  private final ReserveCreditRequest request;

  public InsufficientFundReply() {
    this.request = new ReserveCreditRequest();
  }

  public InsufficientFundReply(ReserveCreditRequest request) {
    this.request = request;
  }

  public ReserveCreditRequest getRequest() {
    return request;
  }
}
