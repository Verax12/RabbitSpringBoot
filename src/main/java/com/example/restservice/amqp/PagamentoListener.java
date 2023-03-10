package com.example.restservice.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {
    @RabbitListener(queues = "pagamento.concluido")
    public void getMessagem(Message message){
        System.out.println("Mensagem Recebida: " +message.toString());
    }
}
