package br.vetconnect.api.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
public class HorariosDisponiveis {
    boolean filaDeEspera;
    List<String> horarios;

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public boolean isFilaDeEspera() {
        return filaDeEspera;
    }

    public void setFilaDeEspera(boolean filaDeEspera) {
        this.filaDeEspera = filaDeEspera;
    }
}
