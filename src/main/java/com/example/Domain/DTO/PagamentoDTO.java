package com.example.Domain.DTO;

import com.example.Domain.DTO.Enum.STATUSPAGAMENTOENUM;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoDTO {
    private Integer id;
    private Date data;
    private String value;
    private STATUSPAGAMENTOENUM status;
}
