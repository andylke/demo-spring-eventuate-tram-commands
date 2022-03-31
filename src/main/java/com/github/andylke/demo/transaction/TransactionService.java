package com.github.andylke.demo.transaction;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.andylke.demo.account.ReserveCreditCommand;
import com.github.andylke.demo.account.ReserveCreditRequest;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;

@Service
public class TransactionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

  @Autowired private TransactionRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Autowired private CommandProducer commandProducer;

  @Autowired private MessageConsumer messageConsumer;

  @PostConstruct
  public void postConstruct() {
    messageConsumer.subscribe(
        "ReserveCreditConsumer",
        Collections.singleton("demo-spring-boot-eventuate-tram-commands.reserve-credit-reply"),
        this::handleReserveCreditReply);
  }

  @Transactional
  public AddTransactionResponse addTransaction(AddTransactionRequest request) {
    final Transaction entity = modelMapper.map(request, Transaction.class);

    final Transaction savedEntity = repository.save(entity);
    final AddTransactionResponse response =
        modelMapper.map(savedEntity, AddTransactionResponse.class);

    commandProducer.send(
        "demo-spring-boot-eventuate-tram-commands.reserve-credit-command",
        new ReserveCreditCommand(
            new ReserveCreditRequest(response.getCustomerId(), response.getTransactionAmount())),
        "demo-spring-boot-eventuate-tram-commands.reserve-credit-reply",
        Collections.emptyMap());

    return response;
  }

  private void handleReserveCreditReply(Message message) {
    LOGGER.info("Received reply {}", message);
  }
}
