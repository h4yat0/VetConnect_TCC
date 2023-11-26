package br.vetconnect.api.form.Prontuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProntuarioForm extends RepresentationModel<ProntuarioForm> implements Serializable {
    private  Long id;
    private Long idProntuario;
    private String data;
    private String sintomas;
    private String examesSolicitados;
    private String diagnostico;
    private String prescricao;
    private String observacoes;
}
