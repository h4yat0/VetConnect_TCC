package br.vetconnect.api.form.Funcionario;

import br.vetconnect.api.form.servico.ServicoFormReturn;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioFormCreate {
    private String nome;
    private String dataNascimento;
    private Long idUnidade;
    private String horaDeEntrada;
    private String horaDeSaida;
    private String email;
    private String senha;
}
