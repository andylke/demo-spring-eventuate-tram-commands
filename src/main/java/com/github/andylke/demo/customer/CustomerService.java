package com.github.andylke.demo.customer;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Transactional
  public ReserveCreditResponse reserveCredit(ReserveCreditRequest request)
      throws CustomerNotFoundException, InsufficientFundException {
    final Customer entity =
        repository
            .findById(request.getCustomerId())
            .orElseThrow(() -> new CustomerNotFoundException());

    if (entity.getAvailableAmount().compareTo(request.getAmount()) < 0) {
      throw new InsufficientFundException();
    }

    entity.setAvailableAmount(entity.getAvailableAmount().subtract(request.getAmount()));
    entity.setReservedAmount(entity.getReservedAmount().add(request.getAmount()));
    final Customer savedEntity = repository.save(entity);

    final ReserveCreditResponse response =
        modelMapper.map(savedEntity, ReserveCreditResponse.class);

    return response;
  }
}
