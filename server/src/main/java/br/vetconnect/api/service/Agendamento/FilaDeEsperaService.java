package br.vetconnect.api.service.Agendamento;


import br.vetconnect.api.entity.Agendamento.FilaEsperaEntity;
import br.vetconnect.api.form.FilaDeEsperaFormCreate;
import br.vetconnect.api.form.FilaDeEsperaFormReturn;
import br.vetconnect.api.mapper.FilaDeEsperaMapper;
import br.vetconnect.api.repository.Agendamento.FilaDeEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilaDeEsperaService {

    @Autowired
    private FilaDeEsperaRepository repository;

    @Autowired
    private FilaDeEsperaMapper mapper;


    public FilaDeEsperaFormReturn cadastraEspera(FilaDeEsperaFormCreate formCreate){
        FilaEsperaEntity entity = mapper.formCreateParaEntity(formCreate);
        return null;
    }
}
