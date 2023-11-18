package br.vetconnect.api.form.Funcionario;


import br.vetconnect.api.form.servico.ServicoFormReturn;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;


@Data
public class FuncionarioFormReturn extends RepresentationModel<FuncionarioFormReturn> implements Serializable {
    private Long id;
    private String nome;
    private String dataNascimento;
    private Long idUnidade;
    private String horaDeEntrada;
    private String horaDeSaida;
    private String senha;
}
