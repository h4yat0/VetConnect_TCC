package br.vetconnect.api.service.Animal;


import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.imagens.AnimalImagensEntity;
import br.vetconnect.api.mapper.Animal.ImagemAnimalMapper;
import br.vetconnect.api.repository.Imagens.AnimalImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImagemAnimalService {

    @Autowired
    private ImagemAnimalMapper mapper;

    @Autowired
    private AnimalImagensRepository repository;

    public List<AnimalImagensEntity> salvarImagemAnimal(List<String> imagens, AnimalEntity entity){
        List<AnimalImagensEntity> AnimalImagensEntityList = mapper.formCreateParaEntity(imagens, entity);
        try{
            for(AnimalImagensEntity imagem : AnimalImagensEntityList){
                repository.save(imagem);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return AnimalImagensEntityList;
    }


    public void alterarImagem(List<String> imagens, AnimalEntity entity) {
        List<AnimalImagensEntity> AnimalImagensEntityList = mapper.formCreateParaEntity(imagens, entity);
        try{
            repository.deleteAll();
            for(AnimalImagensEntity imagem : AnimalImagensEntityList){
                repository.save(imagem);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}
