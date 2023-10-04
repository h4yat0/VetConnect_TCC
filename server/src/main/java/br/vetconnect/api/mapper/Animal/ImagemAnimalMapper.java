package br.vetconnect.api.mapper.Animal;



import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.imagens.AnimalImagensEntity;
import br.vetconnect.api.entity.imagens.AnimalImagensEntity;
import br.vetconnect.api.form.Animal.AnimalFormCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImagemAnimalMapper {

    @Autowired
    private AnimalMapper mapper;



    public List<AnimalImagensEntity> formCreateParaEntity(List<String> imagens, AnimalEntity entity){
        List<AnimalImagensEntity> entityList = new ArrayList<>();
        for(String img : imagens){
            AnimalImagensEntity imgEntity = new AnimalImagensEntity();
            imgEntity.setAnimal(entity);

            imgEntity.setImagem(Base64.getDecoder().decode(img));
            entityList.add(imgEntity);
        }
        return entityList;
    }
}
