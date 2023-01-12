package com.example.listener;

import com.example.Domain.DTO.PagamentoDTO;
import com.example.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {
    @Autowired
    private PagamentoService pagamentoService;

    @RabbitListener(queues = "pagamento.detalhes-pedido")
    public void getMessage(PagamentoDTO pagamentoMessage){
        pagamentoService.ChangePaymentStatus(pagamentoMessage);
        String message = """
                Pagamento Listner 
                -----------------------------
                Número do pedido: %s
                Valor %s
                Status: %s
                """.formatted(pagamentoMessage.getId(), pagamentoMessage.getValue(), pagamentoMessage.getStatus());

        pagamentoService.ChangePaymentStatus(pagamentoMessage);

        System.out.println("Mensagem Recebida: " + message);
    }

}