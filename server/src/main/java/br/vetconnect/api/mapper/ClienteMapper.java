package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.ClienteEntity;
import br.vetconnect.api.form.Cliente.ClienteFormReturn;
import br.vetconnect.api.form.Cliente.ClienteFormCreate;
import br.vetconnect.api.mapper.Animal.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ClienteMapper {

    @Autowired
    private FilaDeEsperaMapper filaDeEsperaMapper;

    @Autowired
    private AnimalMapper animalMapper;

    public ClienteFormReturn convertEntityToForm(ClienteEntity entity){
        ClienteFormReturn clienteForm = new ClienteFormReturn();
        clienteForm.setId(entity.getId());
        clienteForm.setCpf(entity.getCpf());
        clienteForm.setEmail(entity.getEmail());
        clienteForm.setTelefone(entity.getTelefone());

        clienteForm.setDataNascimento(entity.getDataNascimento());
        clienteForm.setNome(entity.getNome());

        clienteForm.setRua(entity.getRua());
        clienteForm.setBairro(entity.getBairro());
        clienteForm.setCidade(entity.getCidade());
        clienteForm.setEstado(entity.getEstado());
        clienteForm.setNumero(entity.getNumero());
        clienteForm.setCep(entity.getCep());

        if(entity.getComplemento() !=null){
            clienteForm.setComplemento(entity.getComplemento());
        }
        if(entity.getImagem() != null){
            clienteForm.setImagem(entity.getImagem());
        }

        if(entity.getFilaEsperaEntities() != null && !entity.getFilaEsperaEntities().isEmpty()){
            clienteForm.setFilaDeEsperaFormReturns(filaDeEsperaMapper.entitysParaFormReturn(entity.getFilaEsperaEntities()));
        }

        return clienteForm;
    }

    public ClienteFormReturn convertEntityToForm1(ClienteEntity entity){
        ClienteFormReturn clienteForm = new ClienteFormReturn();
        clienteForm.setId(entity.getId());
        clienteForm.setNome(entity.getNome());
        if(!entity.getFilaEsperaEntities().isEmpty()){
            clienteForm.setFilaDeEsperaFormReturns(filaDeEsperaMapper.entitysParaFormReturn(entity.getFilaEsperaEntities()));
        }

        if(!entity.getAnimais().isEmpty()){
            clienteForm.setAnimalFormReturnList(animalMapper.listEntityToFormReturn1(entity.getAnimais()));
        }

        return clienteForm;
    }

    public ClienteEntity convertFormToEntity(ClienteFormCreate form){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(form.getCpf());
        clienteEntity.setEmail(form.getEmail());
        clienteEntity.setSenha(form.getSenha());
        clienteEntity.setTelefone(form.getTelefone());
        clienteEntity.setBairro(form.getBairro());
        clienteEntity.setRua(form.getRua());
        clienteEntity.setCidade(form.getCidade());
        clienteEntity.setEstado(form.getEstado());
        clienteEntity.setCep(form.getCep());

        clienteEntity.setNumero(form.getNumero());

        clienteEntity.setDataNascimento(form.getDataNascimento());
        clienteEntity.setNome(form.getNome());

        if(form.getComplemento() != null){
            clienteEntity.setComplemento(form.getComplemento());
        }
        if(form.getImagem() != null){
            clienteEntity.setImagem(Base64.getDecoder().decode(form.getImagem()));
        }

        return clienteEntity;
    }

    public ClienteEntity convertFormToEntity(ClienteFormCreate form, Long id, String senha){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(id);
        clienteEntity.setCpf(form.getCpf());
        clienteEntity.setEmail(form.getEmail());
        clienteEntity.setSenha(senha);
        clienteEntity.setTelefone(form.getTelefone());
        clienteEntity.setNome(form.getNome());
        clienteEntity.setDataNascimento(form.getDataNascimento());

        clienteEntity.setBairro(form.getBairro());
        clienteEntity.setRua(form.getRua());
        clienteEntity.setCidade(form.getCidade());
        clienteEntity.setEstado(form.getEstado());
        clienteEntity.setCep(form.getCep());
        clienteEntity.setNumero(form.getNumero());

        if(form.getComplemento() != null){
            clienteEntity.setComplemento(form.getComplemento());
        }
        if(form.getImagem() != null){
            clienteEntity.setImagem(Base64.getDecoder().decode(form.getImagem()));
        }

        return clienteEntity;
    }
}
