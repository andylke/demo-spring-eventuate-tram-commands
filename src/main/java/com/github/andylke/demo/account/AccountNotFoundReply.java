package com.github.andylke.demo.account;

public class AccountNotFoundReply {

  private final ReserveCreditRequest request;

  public AccountNotFoundReply() {
    this.request = new ReserveCreditRequest();
  }

  public AccountNotFoundReply(ReserveCreditRequest request) {
    this.request = request;
  }

  public ReserveCreditRequest getRequest() {
    return request;
  }
}
