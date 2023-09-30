package br.vetconnect.api.form.Animal;

import br.vetconnect.api.form.Cliente.ClienteFormCreate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalFormReturn extends RepresentationModel<AnimalFormReturn> implements Serializable {
    private Long id;
    private Long idCliente;
    private String nome;
    private String cor;
    private String raca;
    private String dataNascimento;
    private String peso;
    private String tamanho;
    private String especie;
    private String sexo;
}
