package com.example.amqp;

import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
@Configuration
public class AMQPConfiguration {
    @Bean
    public Queue criaFila(){
        // return  new Queue("pagamento.concluido", false);
        return QueueBuilder.nonDurable("pagamento.concluido").build();
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    //Iniciliza o Rabbit Admin sempre que a aplicação subir
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    //Conversor de mensagens "complexas". Utilizo esse metodo para converter um DTO em uma mensagem valida para
    // o rabbit.
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //Defino o conversor das minhas mensagens
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory conn,Jackson2JsonMessageConverter jsonMessageConverter ){
        var rabbitTemplate =  new RabbitTemplate(conn);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return  rabbitTemplate;
    }
}
