package br.vetconnect.api.mapper.Unidade;

import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import br.vetconnect.api.form.Unidade.UnidadeFormCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImagemUnidadeMapper {

    @Autowired
    private UnidadeMapper mapper;

    public List<UnidadeImagensEntity> formCreateParaEntity(UnidadeFormCreate formCreate, Long id){
        List<String> imagens = formCreate.getImagem();
        UnidadeEntity entity = mapper.entitysToFormCreate(formCreate);
        entity.setId(id);
        List<UnidadeImagensEntity> entityList = new ArrayList<>();

        for(String img : imagens){
            UnidadeImagensEntity imgEntity = new UnidadeImagensEntity();
            imgEntity.setUnidade(entity);

            imgEntity.setImagem(Base64.getDecoder().decode(img));
            entityList.add(imgEntity);
        }
        return entityList;
    }
    public List<UnidadeImagensEntity> formCreateParaEntity(List<String> imagens, UnidadeEntity entity){
        List<UnidadeImagensEntity> entityList = new ArrayList<>();
        for(String img : imagens){
            UnidadeImagensEntity imgEntity = new UnidadeImagensEntity();
            imgEntity.setUnidade(entity);

            imgEntity.setImagem(Base64.getDecoder().decode(img));
            entityList.add(imgEntity);
        }
        return entityList;
    }


}
