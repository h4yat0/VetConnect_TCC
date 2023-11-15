package br.vetconnect.api.service.Agendamento;


import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FilaEsperaAgendamento {


    @Autowired
    @Qualifier("filaDeEsperaService")
    private FilaDeEsperaService filaDeEsperaService;

    @Autowired
    @Qualifier("agendamentoService")
    private AgendamentoService agendamentoService;



    public AgendamentoEntity buscarPorId(Long id){
        return  agendamentoService.buscarPorId(id);
    }



    public void verificaFilaEspera(Long id) {
        filaDeEsperaService.verificaFilaEspera(id);
    }
}
