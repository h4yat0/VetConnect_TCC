package br.vetconnect.api.service;

import br.vetconnect.api.entity.UnidadeServico.ServicoEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.form.AssociacaoForm;
import br.vetconnect.api.repository.Agendamento.ServicoRepository;
import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.repository.AssociacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociacaoService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private AssociacaoRepository repository;



    public void associacaoUnidadeServico(AssociacaoForm form){
        UnidadeEntity entity = unidadeRepository.buscarUnidade(form.getId());
        List<ServicoEntity> servicoEntities = new ArrayList<>();
        for(Long ids : form.getIds()){
        servicoEntities.add(servicoRepository.buscarServico(ids));
        }
        entity.setServicos(servicoEntities);
        unidadeRepository.save(entity);
    }

    public void associacaoServicoUnidade(AssociacaoForm form){
        ServicoEntity entity = servicoRepository.buscarServico(form.getId());
        List<UnidadeEntity> unidadeEntities = new ArrayList<>();

    }

    public String tempoServico(Long idUnidade, Long idServico){
        return repository.buscaTempoServico(idUnidade, idServico);
    }


}
