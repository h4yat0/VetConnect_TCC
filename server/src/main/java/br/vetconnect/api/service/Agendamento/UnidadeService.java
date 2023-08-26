package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {


    @Autowired
    private UnidadeRepository repository;

    public List<UnidadeEntity> getAll(){
        return repository.findAll();
    }

    public List<ServicoEntity> getById(Long id) {
        UnidadeEntity entity = repository.buscarUnidade(id);
        return entity.getServicos();
    }
}
