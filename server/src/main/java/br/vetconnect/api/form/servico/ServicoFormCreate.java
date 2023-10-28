package br.vetconnect.api.form.servico;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoFormCreate {
    String nome;
    BigDecimal preco;

}
