package br.vetconnect.api.mapper.Animal;

import br.vetconnect.api.entity.AnimalEntity;
import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalMapper {


    public AnimalEntity formCreateToEntity(AnimalFormCreate animalCreate, ClienteEntity clienteEntity){
        AnimalEntity entity = new AnimalEntity();
        entity.setCor(animalCreate.getCor());
        entity.setNome(animalCreate.getNome());
        entity.setPeso(convertValores(animalCreate.getPeso()));
        entity.setEspecie(animalCreate.getEspecie());
        entity.setTamanho(convertValores(animalCreate.getTamanho()));
        entity.setDataNascimento(animalCreate.getDataNascimento());
        entity.setSexo(animalCreate.getSexo());
        if(animalCreate.getRaca() != null){
            entity.setRaca(animalCreate.getRaca());
        }
        entity.setIdCliente(clienteEntity);
        return entity;
    }

    public AnimalFormReturn entityToFormReturn(AnimalEntity animalEntity) {
        AnimalFormReturn animalFormReturn = new AnimalFormReturn();
        animalFormReturn.setId(animalEntity.getId());
        animalFormReturn.setCor(animalEntity.getCor());
        animalFormReturn.setNome(animalEntity.getNome());
        animalFormReturn.setPeso(String.valueOf(animalEntity.getPeso()));
        animalFormReturn.setEspecie(animalEntity.getEspecie());
        animalFormReturn.setTamanho(String.valueOf(animalEntity.getTamanho()));
        animalFormReturn.setDataNascimento(animalEntity.getDataNascimento());
        animalFormReturn.setSexo(animalEntity.getSexo());
        if(animalEntity.getRaca() != null){
            animalFormReturn.setRaca(animalEntity.getRaca());
        }
        animalFormReturn.setIdCliente(animalEntity.getIdCliente().getId());

        return animalFormReturn;
    }

    public List<AnimalFormReturn> listEntityToFormReturn(List<AnimalEntity> animalEntities) {
        if(animalEntities == null || animalEntities.size()==0){
            return null;
        }else{
            List<AnimalFormReturn> formReturnList = new ArrayList<>();
            for (AnimalEntity entity : animalEntities){
                AnimalFormReturn animalFormReturn = new AnimalFormReturn();
                animalFormReturn.setId(entity.getId());
                animalFormReturn.setCor(entity.getCor());
                animalFormReturn.setNome(entity.getNome());
                animalFormReturn.setPeso(String.valueOf(entity.getPeso()));
                animalFormReturn.setEspecie(entity.getEspecie());
                animalFormReturn.setTamanho(String.valueOf(entity.getTamanho()));
                animalFormReturn.setDataNascimento(entity.getDataNascimento());
                animalFormReturn.setSexo(entity.getSexo());
                if(entity.getRaca() != null){
                    animalFormReturn.setRaca(entity.getRaca());
                }
                animalFormReturn.setIdCliente(entity.getIdCliente().getId());
                formReturnList.add(animalFormReturn);
            }
            return formReturnList;
        }


    }

    public AnimalEntity formCreateToEntity(AnimalFormCreate animal, long id, ClienteEntity cliente) {
        AnimalEntity entity = new AnimalEntity();
        entity.setId(id);
        entity.setCor(animal.getCor());
        entity.setNome(animal.getNome());
        entity.setPeso(convertValores(animal.getPeso()));
        entity.setEspecie(animal.getEspecie());
        entity.setTamanho(convertValores(animal.getTamanho()));
        entity.setDataNascimento(animal.getDataNascimento());
        entity.setSexo(animal.getSexo());
        if(animal.getRaca() != null){
            entity.setRaca(animal.getRaca());
        }
        entity.setIdCliente(cliente);
        return entity;
    }


    private BigDecimal convertValores(String valor){
        double valorDouble = Double.parseDouble(valor);
        DecimalFormat format = new DecimalFormat("#,###");
        String valorFormatado = format.format(valorDouble);
        return  new BigDecimal(valorFormatado);
    }
}
