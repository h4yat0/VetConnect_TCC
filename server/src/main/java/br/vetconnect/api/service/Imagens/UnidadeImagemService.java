package br.vetconnect.api.service.Imagens;

import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import br.vetconnect.api.form.Unidade.UnidadeFormCreate;
import br.vetconnect.api.mapper.Unidade.ImagemUnidadeMapper;
import br.vetconnect.api.repository.Imagens.UnidadeImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnidadeImagemService {

    @Autowired
    private UnidadeImagensRepository repository;

    @Autowired
    private ImagemUnidadeMapper mapper;



    public List<byte[]> salvarImagemUnidade (UnidadeFormCreate formCreate, Long id){
        List<UnidadeImagensEntity> imagens = mapper.formCreateParaEntity(formCreate, id);
        List<byte[]> imagensReturn = new ArrayList<>();
        for(UnidadeImagensEntity imagem : imagens){
            repository.save(imagem);
            imagensReturn.add(imagem.getImagem());
        }
        return imagensReturn;
    }

    public  List<UnidadeImagensEntity> salvarImagemUnidade(List<String> imagens, UnidadeEntity entity){
        List<UnidadeImagensEntity> unidadeImagensEntityList = mapper.formCreateParaEntity(imagens, entity);
        try{
            for(UnidadeImagensEntity imagem : unidadeImagensEntityList){
                repository.save(imagem);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return unidadeImagensEntityList;
    }


    public void alterarImagem(List<String> imagens, UnidadeEntity entity) {
        List<UnidadeImagensEntity> unidadeImagensEntityList = mapper.formCreateParaEntity(imagens, entity);
        try{
            repository.deleteAll();
            for(UnidadeImagensEntity imagem : unidadeImagensEntityList){
                repository.save(imagem);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}
