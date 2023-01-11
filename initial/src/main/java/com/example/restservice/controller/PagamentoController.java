package com.example.restservice.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity<String> GetPagamento(){
        Random random = new Random();
        Message message = new Message(("Pagamento criado com sucesso. ID: " + random.nextInt()).getBytes());
        rabbitTemplate.send("pagamento.concluido", message);
        return new ResponseEntity<>("Sucesso", HttpStatus.OK);
    }
}
