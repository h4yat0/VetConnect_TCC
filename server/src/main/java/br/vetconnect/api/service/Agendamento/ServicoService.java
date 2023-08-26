package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<ServicoEntity> getAll(){
        return repository.findAll();
    }

    public List<UnidadeEntity> getById(Long id){
        ServicoEntity entity = repository.buscarServico(id);
        return entity.getUnidades();
    }
}
