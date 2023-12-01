package br.vetconnect.api.form;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
public class HorariosDisponiveis {

    List<String> horarios;

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }
}
