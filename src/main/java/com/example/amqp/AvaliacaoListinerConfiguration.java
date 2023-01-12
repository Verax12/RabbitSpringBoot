package com.example.amqp;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoListinerConfiguration {
    @Autowired
    private AMQPConfiguration amqpConfiguration;
    @Value("${spring.queue.avaliacao}")
    private String queueNameAvaliacao;
    @Bean
    public Queue DAvaliacaoPedidosQueue(){
        return QueueBuilder.nonDurable(queueNameAvaliacao).build();
    }

    @Bean
    public Binding bindingAvaliacaoPedido(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(DAvaliacaoPedidosQueue()).to(amqpConfiguration.fanoutExchange());
    }
}
