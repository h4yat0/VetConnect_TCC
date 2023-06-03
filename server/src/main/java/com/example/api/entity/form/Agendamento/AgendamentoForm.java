package com.example.api.entity.form.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoForm {

    private Long idCliente;
    private Long idAnimal;
    private Long idServico;
}
