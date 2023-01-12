package com.example.amqp;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentosConsumerConfiguration {
    @Autowired
    private AMQPConfiguration amqpConfiguration;
    @Bean
    public Queue DetalhePedidosQueue(){
        return QueueBuilder.nonDurable("pagamento.detalhes-pedido").build();
    }

    @Bean
    public Binding bindingPagamentoPedido(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(DetalhePedidosQueue()).to(amqpConfiguration.fanoutExchange());
    }
}
