package com.github.andylke.demo.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;

public class ReserveCreditCommandHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReserveCreditCommandHandler.class);

  private final CommandHandlers commandHandlers =
      CommandHandlersBuilder.fromChannel(
              "demo-spring-boot-eventuate-tram-commands.reserve-credit-command")
          .onMessage(ReserveCreditCommand.class, this::addReservedCredit)
          .build();

  @Autowired private AccountService service;

  public CommandHandlers getCommandHandlers() {
    return commandHandlers;
  }

  public Message addReservedCredit(CommandMessage<ReserveCreditCommand> message) {
    final ReserveCreditCommand command = message.getCommand();
    LOGGER.info("Received ReserveCreditCommand {}", command);

    try {
      return CommandHandlerReplyBuilder.withSuccess(
          new CreditReservedReply(service.reserveCredit(command.getRequest())));

    } catch (AccountNotFoundException e) {
      return CommandHandlerReplyBuilder.withFailure(
          new AccountNotFoundReply(command.getRequest()));

    } catch (InsufficientFundException e) {
      return CommandHandlerReplyBuilder.withFailure(
          new InsufficientFundReply(command.getRequest()));
    }
  }
}
