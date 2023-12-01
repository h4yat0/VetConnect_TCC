package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.controller.Agendamento.AgendamentoController;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Agendamento.AgendamentoFormCreate;
import br.vetconnect.api.form.Agendamento.AgendamentoFormReturn;
import br.vetconnect.api.form.AgendamentoEmail;
import br.vetconnect.api.form.EmailFilaDeEspera;
import br.vetconnect.api.form.HorariosDisponiveis;
import br.vetconnect.api.mapper.AgendamentoMapper;
import br.vetconnect.api.repository.Agendamento.AgendamentoRepository;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.repository.AnimalRepository;
import br.vetconnect.api.repository.ClienteRepository;
//import br.vetconnect.api.service.AssociacaoService;
import br.vetconnect.api.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @Autowired
    private AgendamentoMapper mapper;

    @Autowired
    private FilaDeEsperaService filaDeEsperaService;

    @Autowired
    private AssociacaoService associacaoService;




    public AgendamentoFormReturn cadastrarAgendamento(AgendamentoFormCreate form){
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(form.getIdUnidade());
        AnimalEntity animalEntity = animalRepository.buscarAnimal(form.getIdAnimal());
        ClienteEntity clienteEntity = clienteRepository.buscarPorId(form.getIdCliente());
        ServicoEntity servicoEntity = servicoRepository.buscarServico(form.getIdServico());

        AgendamentoEntity resultado = repository.verificaDataHora(form.getDataAgendada(), form.getHoraAgendada(), form.getIdServico(), form.getIdUnidade());

        if(resultado != null && unidadeEntity.getServicos().contains(servicoEntity)){
            return null;
        }else{
            AgendamentoEntity entity = mapper.formCreateParaEntity(form, animalEntity, clienteEntity,unidadeEntity,servicoEntity);

            AgendamentoFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(AgendamentoController.class).cadastroAgendamento(form)).withSelfRel());

            return formReturn;
        }
    }

    public AgendamentoFormReturn editarAgendamento(AgendamentoFormCreate form, Long id){
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(form.getIdUnidade());
        AnimalEntity animalEntity = animalRepository.buscarAnimal(form.getIdAnimal());
        ClienteEntity clienteEntity = clienteRepository.buscarPorId(form.getIdCliente());
        ServicoEntity servicoEntity = servicoRepository.buscarServico(form.getIdServico());

        if(repository.verificaIdDataHora(form.getDataAgendada(), form.getHoraAgendada(), id) != null && unidadeEntity.getServicos().contains(servicoEntity)){
            return null;
        }else{
            AgendamentoEntity entity = mapper.formCreateParaEntityEdit(form, animalEntity, clienteEntity,unidadeEntity,servicoEntity, id);

            AgendamentoFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(AgendamentoController.class).editarAgendamento(form, id)).withSelfRel());

            return formReturn;
        }
    }

    public List<AgendamentoFormReturn> buscarPorIdCliente(Long id) {
        List<AgendamentoFormReturn> formReturnList = mapper.entitysParaFormsReturs(repository.buscarAgendamentos(id));

        if(formReturnList != null && formReturnList.size()>0 ){
            formReturnList.stream().forEach(a -> a.add(linkTo(methodOn(AgendamentoController.class).buscarPorIdCliente(id)).withSelfRel()));
        }

        return formReturnList;
    }

    public List<AgendamentoFormReturn> buscarPorIdAnimal(Long id) {
        List<AgendamentoFormReturn> formReturnList = mapper.entitysParaFormsReturs(repository.buscarAgenamentoAnimal(id));

        if(formReturnList != null && formReturnList.size()>0 ){
            formReturnList.stream().forEach(a -> a.add(linkTo(methodOn(AgendamentoController.class).buscarPorIdAnimal(id)).withSelfRel()));
        }
        return formReturnList;
    }

    public void cancelarAgendamento(Long id) {
        AgendamentoEntity entity = repository.buscarAgendamento(id);
        entity.setStatus('3');
        repository.save(entity);
        filaDeEsperaService.verificaFilaEspera( id);
    }


    public AgendamentoEntity buscarPorId(Long idAgendamento) {
        return repository.buscarAgendamento(idAgendamento);
    }

    public AgendamentoEntity buscarPorDataHoraServicoUnidade(String data, String hora, Long idServico, Long idUnidade){
        return repository.verificaDataHora(data, hora, idServico, idUnidade);
    }

    public EmailFilaDeEspera criaEmail(Long id) {
         return repository.criaEmail(id);
    }

    public List<AgendamentoEmail> criaNotificaoEmail(LocalDate dataAtual, LocalDate dataDaquiDoisDias) {
        return repository.criaNotificaoEmail(String.valueOf(dataAtual), String.valueOf(dataDaquiDoisDias));
    }

    public HorariosDisponiveis buscarHorarios(Long idUnidade, Long idServico, String data) {
        List<String> horariosString = repository.buscarHorariosDisponiveis(idUnidade, idServico, data);
        String tempoServico = associacaoService.tempoServico(idUnidade, idServico);
        UnidadeEntity unidadeEntity = unidadeRepository.buscarUnidade(idUnidade);




        String[] partesInicio = unidadeEntity.getHorarioAbertura().split(":");
        String[] partesFim = unidadeEntity.getHorarioFechamento().split(":");


        int horasInicio = Integer.parseInt(partesInicio[0]);
        int minutosInicio = Integer.parseInt(partesInicio[1]);

        int horasFim = Integer.parseInt(partesFim[0]);
        int minutosFim = Integer.parseInt(partesFim[1]);

        List<String> horariosResultantes = new ArrayList<>();
        LocalTime inicioEspediente = LocalTime.of(horasInicio, minutosInicio);
        LocalTime fimEspediente = LocalTime.of(horasFim, minutosFim);
        LocalTime duracaoHorario = LocalTime.parse(tempoServico);




        while (inicioEspediente.isBefore(fimEspediente)) {
            String horarioAtual = inicioEspediente.toString();


            if (!horariosString.contains(horarioAtual)) {
                horariosResultantes.add(horarioAtual);
            }

            inicioEspediente = inicioEspediente.plusHours(duracaoHorario.getHour())
                    .plusMinutes(duracaoHorario.getMinute())
                    .plusSeconds(duracaoHorario.getSecond());
        }

        HorariosDisponiveis horariosDisponiveis = new HorariosDisponiveis();
        horariosDisponiveis.setHorarios(horariosResultantes);
        return horariosDisponiveis;

    }

}
