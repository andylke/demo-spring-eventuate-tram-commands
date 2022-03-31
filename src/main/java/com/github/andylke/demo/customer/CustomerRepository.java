package com.github.andylke.demo.customer;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Lock(LockModeType.PESSIMISTIC_READ)
  Optional<Customer> findByCustomerId(Long customerId);
}
