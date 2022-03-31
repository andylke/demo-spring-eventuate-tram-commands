package com.github.andylke.demo.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import io.eventuate.tram.spring.commands.consumer.TramCommandConsumerConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@Configuration
@Import({TramCommandConsumerConfiguration.class, OptimisticLockingDecoratorConfiguration.class})
public class ReserveCreditCommandConfiguration {

  @Bean
  public ReserveCreditCommandHandler customerCommandHandler() {
    return new ReserveCreditCommandHandler();
  }

  @Bean
  public CommandDispatcher customerCommandDispatcher(
      CommandDispatcherFactory commandDispatcherFactory,
      ReserveCreditCommandHandler customerCommandHandler) {
    return commandDispatcherFactory.make(
        "CustomerCommandHandler", customerCommandHandler.getCommandHandlers());
  }
}
