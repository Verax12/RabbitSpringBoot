package com.example.amqp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentosConsumerConfiguration {
    @Value("${spring.queue.pagamentos}")
    private String queueNamePagamento;

    @Autowired
    private AMQPConfiguration amqpConfiguration;
    @Bean
    public Queue DetalhePedidosQueue(){
        return QueueBuilder.nonDurable(queueNamePagamento).build();
    }

    @Bean
    public Binding bindingPagamentoPedido(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(DetalhePedidosQueue()).to(amqpConfiguration.fanoutExchange());
    }
}
