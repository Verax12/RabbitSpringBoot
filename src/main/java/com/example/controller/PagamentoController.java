package com.example.controller;

import com.example.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private  PagamentoService pagamentoService;
    @Value("${spring.pagamento.ex}")
    private String exchangeName;

    @PostMapping
    public ResponseEntity<String> PostPagamento(){
        rabbitTemplate.convertAndSend(exchangeName,"", pagamentoService.GenerateMockPagamentoDTO());
        return new ResponseEntity<>("Sucesso", HttpStatus.OK);
    }

}
