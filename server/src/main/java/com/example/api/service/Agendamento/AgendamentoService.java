package com.example.api.service.Agendamento;

import com.example.api.entity.Agendamento.AgendamentoEntity;
import com.example.api.entity.Agendamento.ServicoEntity;
import com.example.api.entity.Agendamento.UnidadeEntity;
import com.example.api.entity.AnimalEntity;
import com.example.api.entity.ClienteEntity;
import com.example.api.entity.form.Agendamento.AgendamentoForm;
import com.example.api.repository.Agendamento.AgendamentoRepository;
import com.example.api.repository.Agendamento.ServicoRepository;
import com.example.api.repository.Agendamento.UnidadeRepository;
import com.example.api.repository.AnimalRepository;
import com.example.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;


    public AgendamentoEntity cadastrarAgendamento(AgendamentoForm form){
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(form.getIdUnidade());
        AnimalEntity animalEntity = animalRepository.buscarAnimal(form.getIdAnimal());
        ClienteEntity clienteEntity = clienteRepository.burcarPorId(form.getIdCliente());
        ServicoEntity servicoEntity = servicoRepository.buscarServico(form.getIdServico());

        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setDataAgendada(form.getDataAgendada());
        entity.setIdUnidade(unidadeEntity);
        entity.setIdServico(servicoEntity);
        entity.setIdCliente(clienteEntity);
        entity.setIdAnimal(animalEntity);
        entity.setHoraAgendada(form.getHoraAgendada());
        entity.setValorAgendado(form.getValorAgendado());

        return repository.save(entity);

    }

    public List<AgendamentoEntity> buscarPorId(Long id) {
        return repository.buscarAgendamentos(id);
    }
}
