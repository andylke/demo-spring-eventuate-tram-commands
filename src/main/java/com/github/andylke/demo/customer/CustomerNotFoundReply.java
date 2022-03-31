package com.github.andylke.demo.customer;

public class CustomerNotFoundReply {

  private final ReserveCreditRequest request;

  public CustomerNotFoundReply() {
    this.request = new ReserveCreditRequest();
  }

  public CustomerNotFoundReply(ReserveCreditRequest request) {
    this.request = request;
  }

  public ReserveCreditRequest getRequest() {
    return request;
  }
}
