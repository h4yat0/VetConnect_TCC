package br.vetconnect.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoForm {
    Long id;
    List<Long> ids;
}
