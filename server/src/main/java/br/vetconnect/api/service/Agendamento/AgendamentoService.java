package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.Agendamento.ServicoEntity;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Agendamento.AgendamentoForm;
import br.vetconnect.api.repository.Agendamento.AgendamentoRepository;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
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
        ClienteEntity clienteEntity = clienteRepository.buscarPorId(form.getIdCliente());
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
