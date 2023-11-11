package br.vetconnect.api.form.Unidade;

import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeFormReturn extends RepresentationModel<UnidadeFormReturn> implements Serializable {


    private Long id;
    private String nome;
    private String especialidade;
    private String horarioAbertura;
    private String horarioFechamento;
    private String contato;
    private String cep;
    private List<ServicoEntity> servicos;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    private String cnpj;
    private String rua;
    private List<byte[]> imagem;



}
