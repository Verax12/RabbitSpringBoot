package com.example.service;

import com.example.Domain.DTO.Enum.STATUSPAGAMENTOENUM;
import com.example.Domain.DTO.PagamentoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
@Service
public class PagamentoService {

    public PagamentoDTO GenerateMockPagamentoDTO(){
        return PagamentoDTO.builder()
                .id(GenerateRandomIdNumbers())
                .data(GetCrrentDate())
                .value("R$:" + GenerateRandomValueNumbers().toString())
                .status(STATUSPAGAMENTOENUM.PENDENTE)
                .build();
    }
    private Integer GenerateRandomIdNumbers(){
        Random random = new Random();
        return random.nextInt();
    }
    private Integer GenerateRandomValueNumbers(){
        Random random = new Random();
        return random.nextInt(1000 - 500);
    }
    private Date GetCrrentDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
    public void ChangePaymentStatus(PagamentoDTO pagamentoDTO){
        pagamentoDTO.setStatus(STATUSPAGAMENTOENUM.PROCESSANDO);
    }
}
