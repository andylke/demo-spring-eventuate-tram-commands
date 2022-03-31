package com.github.andylke.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customers")
public class CustomerRestController {

  @Autowired private CustomerService service;

  @PostMapping
  public ReserveCreditResponse reserveCredit(@RequestBody ReserveCreditRequest request)
      throws CustomerNotFoundException, InsufficientFundException {
    return service.reserveCredit(request);
  }
}
