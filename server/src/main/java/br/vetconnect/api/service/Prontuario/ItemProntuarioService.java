package br.vetconnect.api.service.Prontuario;

import br.vetconnect.api.controller.AnimalController;
import br.vetconnect.api.controller.Prontuario.ItemProntuarioController;
import br.vetconnect.api.controller.Prontuario.ProntuarioController;
import br.vetconnect.api.entity.Prontuario.ItemProntuarioEntity;
import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.form.Prontuario.ItemProntuarioForm;
import br.vetconnect.api.mapper.ItemProntuarioMapper;
import br.vetconnect.api.repository.Prontuario.ItemProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ItemProntuarioService {

    @Autowired
    private ItemProntuarioRepository repository;

    @Autowired
    private ProntuarioService prontuarioService;

    @Autowired
    private ItemProntuarioMapper mapper;

    public ItemProntuarioForm cadastrarItemProntuario(ItemProntuarioForm form) {
        ProntuarioEntity entity = prontuarioService.buscarProntuarioPorId(form.getIdProntuario());
        ItemProntuarioEntity itemProntuarioEntity = mapper.formParaEntity(form, entity);
        ItemProntuarioForm formReturn = mapper.entityParaForm(repository.save(itemProntuarioEntity));
        formReturn.add(linkTo(methodOn(ItemProntuarioController.class).cadastrarItemProntuario(form)).withSelfRel());
        return formReturn;
    }

    public List<ItemProntuarioForm> procurarItemProntuarios(Long id) {
        List<ItemProntuarioEntity> entities = repository.procuraPorId(id);
        if(entities == null){
            return  null;
        }else{
            List<ItemProntuarioForm> form = mapper.entitysParaForms(entities);

            form.stream().forEach(a -> a.add(linkTo(methodOn(ItemProntuarioController.class).procurarItemProntuarios(id)).withSelfRel()));

            return form;
        }

    }

    public ItemProntuarioForm editarItemProntuario(ItemProntuarioForm form, Long id) {
        ProntuarioEntity entity = prontuarioService.buscarProntuarioPorId(form.getIdProntuario());
        ItemProntuarioEntity itemProntuarioEntity = mapper.formParaEntity(form, entity, id);
        ItemProntuarioForm formReturn = mapper.entityParaForm(repository.save(itemProntuarioEntity));
        formReturn.add(linkTo(methodOn(ItemProntuarioController.class).cadastrarItemProntuario(form)).withSelfRel());
        return formReturn;
    }

    public void deleteItemProntuario(Long id) {
        repository.deleteById(id);
    }
}
