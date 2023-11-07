package br.vetconnect.api.form.Unidade;




import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeFormCreate {

    @Valid
    @NotBlank
    private String nome;
    @Valid
    @NotBlank
    private String especialidade;
    @Valid
    @NotBlank
    private String horarioAbertura;
    @Valid
    @NotBlank
    private String horarioFechamento;
    @Valid
    @NotBlank
    private String contato;
    @Valid
    @NotBlank
    private String cep;

    @Valid
    @NotBlank
    private String bairro;
    @Valid
    @NotBlank
    private String cidade;
    @Valid
    @NotBlank
    private String estado;
    @Valid
    @NotBlank
    private String complemento;
    @Valid
    @NotBlank
    private String rua;
    @Valid
    @NotBlank
    private String cnpj;

    private String numero;

    private List<String> imagem;


}
