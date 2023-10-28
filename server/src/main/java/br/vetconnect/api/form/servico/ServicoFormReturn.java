package br.vetconnect.api.form.servico;


import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoFormReturn extends RepresentationModel<ServicoFormReturn> implements Serializable {

    Long id;
    String nome;
    BigDecimal preco;
    List<Long> unidades;


}
