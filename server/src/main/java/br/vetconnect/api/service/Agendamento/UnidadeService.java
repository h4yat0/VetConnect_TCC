package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.controller.Agendamento.UnidadeController;
import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import br.vetconnect.api.form.Unidade.UnidadeFormCreate;
import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import br.vetconnect.api.mapper.Unidade.UnidadeMapper;
import br.vetconnect.api.repository.Agendamento.UnidadeRepository;
import br.vetconnect.api.entity.Agendamento.UnidadeEntity;

import br.vetconnect.api.service.Imagens.UnidadeImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UnidadeService {


    @Autowired
    private UnidadeRepository repository;

    @Autowired
    private UnidadeMapper mapper;

    @Autowired
    UnidadeImagemService imagemService;


    public List<UnidadeFormReturn> somenteUnidades(){
        List<UnidadeFormReturn> unidades = mapper.formCreateToEntitysSemServicos(repository.buscarUnidadesComServico());
        if(unidades == null || unidades.size() ==0){
            return null;
        }else{
            unidades.stream().forEach(a -> a.add(linkTo(methodOn(UnidadeController.class).somenteUnidades()).withSelfRel()));
            return unidades;
        }
    }

    public List<UnidadeFormReturn> unidadesServicos() {
        List<UnidadeFormReturn> unidades = mapper.formCreateToEntitys(repository.buscarUnidadesComServico());
        if(unidades == null || unidades.size() ==0){
            return null;
        }else{
            unidades.stream().forEach(a -> a.add(linkTo(methodOn(UnidadeController.class).unidadesServicos()).withSelfRel()));
            return unidades;
        }
    }

    public List<UnidadeFormReturn> todasUnidades() {
        List<UnidadeFormReturn> unidades = mapper.formCreateToEntitys(repository.findAll());
        if(unidades == null || unidades.size() ==0){
            return null;
        }else{
            unidades.stream().forEach(a -> a.add(linkTo(methodOn(UnidadeController.class).todasUnidades()).withSelfRel()));
            return unidades;
        }
    }

    public UnidadeEntity getById(Long id) {
        return  repository.buscarUnidade(id);
    }


    public UnidadeFormReturn saveUnidade(UnidadeFormCreate formCreate) {
        UnidadeEntity entityCnpj = repository.buscarUnidadePorCnpj(formCreate.getCnpj());
        if(entityCnpj != null){
            return null;
        }else{
            UnidadeEntity entity = mapper.entitysToFormCreate(formCreate);
            UnidadeEntity entitySalva = repository.save(entity);
            List<UnidadeImagensEntity> imgs = imagemService.salvarImagemUnidade(formCreate.getImagem(), entitySalva);
            entitySalva.setImagens(imgs);

            UnidadeFormReturn unidadeFormReturn = mapper.entityParaFormReturn(entitySalva);
            unidadeFormReturn.add(linkTo(methodOn(UnidadeController.class).cadastro(formCreate)).withSelfRel());

            return unidadeFormReturn;
        }


    }

    public UnidadeFormReturn alterarUnidade(UnidadeFormCreate formCreate, long id) {
        UnidadeEntity entityCep = repository.buscarUnidadePorCnpj(formCreate.getCep());

        if(entityCep.getId() == id){
            UnidadeEntity entity = mapper.formCreateParaEntity(formCreate, id);
            UnidadeFormReturn formReturn = mapper.entityParaFormReturn(repository.save(entity));
            formReturn.add(linkTo(methodOn(UnidadeController.class).alterarUnidade(formCreate, id)).withSelfRel());
            return formReturn;
        }else{
            return null;
        }
    }

    public void deletar(long id) {
        repository.deleteById(id);
    }


    public void salvarImagemUnidade(List<String> imagens, Long id) {
        UnidadeEntity entity = repository.buscarUnidade(id);
        imagemService.salvarImagemUnidade(imagens, entity);
    }

    public void alterarImagemUnidade(List<String> imagens, Long id){
        UnidadeEntity entity= repository.buscarUnidade(id);
        imagemService.alterarImagem(imagens, entity);
    }

}
