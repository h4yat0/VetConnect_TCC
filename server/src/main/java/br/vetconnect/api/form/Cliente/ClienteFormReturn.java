package br.vetconnect.api.form.Cliente;

import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome", "cpf", "dataNascimento", "telefone", "email", "senha"})
public class ClienteFormReturn extends RepresentationModel<ClienteFormReturn> implements Serializable {

    private Long id;
    private String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dataNascimento;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private byte[] imagem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rua;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bairro;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cidade;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String complemento;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String numero;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cep;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AnimalFormReturn> animalFormReturnList = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FilaDeEsperaFormReturn> filaDeEsperaFormReturns = new ArrayList<>();
}
