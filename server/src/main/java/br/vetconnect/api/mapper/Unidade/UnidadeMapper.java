package br.vetconnect.api.mapper.Unidade;

import br.vetconnect.api.entity.Agendamento.UnidadeEntity;
import br.vetconnect.api.entity.imagens.UnidadeImagensEntity;
import br.vetconnect.api.form.Unidade.UnidadeFormCreate;
import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class UnidadeMapper {

    public List<UnidadeFormReturn> formCreateToEntitysSemServicos(List<UnidadeEntity> unidades) {
        List<UnidadeFormReturn> formReturnList = new ArrayList<>();

        if(unidades == null || unidades.size() == 0){
            return null;
        }else{
            for(UnidadeEntity unidade : unidades){
                UnidadeFormReturn formReturn = new UnidadeFormReturn();

                formReturn.setId(unidade.getId());
                formReturn.setNome(unidade.getNome());
                formReturn.setContato(unidade.getContato());
                formReturn.setEspecialidade(unidade.getEspecialidade());
                formReturn.setHorarioFuncionamento(unidade.getHorarioFuncionamento());
                formReturn.setCnpj(unidade.getCnpj());

                formReturn.setCep(unidade.getCep());
                formReturn.setBairro(unidade.getBairro());
                formReturn.setRua(unidade.getRua());
                formReturn.setCidade(unidade.getCidade());
                formReturn.setEstado(unidade.getEstado());
                formReturn.setComplemento(unidade.getComplemento());
                formReturn.setNumero(unidade.getNumero());

                formReturn.setImagem(listaDeImagens(unidade.getImagens()));



                formReturnList.add(formReturn);
            }
            return formReturnList;
        }

    }

    public List<UnidadeFormReturn> formCreateToEntitys(List<UnidadeEntity> unidades) {
        List<UnidadeFormReturn> formReturnList = new ArrayList<>();

        if(unidades == null || unidades.size() == 0){
            return null;
        }else{
            for(UnidadeEntity unidade : unidades){
                UnidadeFormReturn formReturn = new UnidadeFormReturn();

                formReturn.setId(unidade.getId());
                formReturn.setNome(unidade.getNome());
                formReturn.setContato(unidade.getContato());
                formReturn.setCnpj(unidade.getCnpj());

                formReturn.setEspecialidade(unidade.getEspecialidade());
                formReturn.setHorarioFuncionamento(unidade.getHorarioFuncionamento());
                formReturn.setServicos(unidade.getServicos());

                formReturn.setCep(unidade.getCep());
                formReturn.setBairro(unidade.getBairro());
                formReturn.setRua(unidade.getRua());
                formReturn.setCidade(unidade.getCidade());
                formReturn.setEstado(unidade.getEstado());
                formReturn.setComplemento(unidade.getComplemento());
                formReturn.setNumero(unidade.getNumero());

                formReturn.setImagem(listaDeImagens(unidade.getImagens()));

                formReturnList.add(formReturn);
            }
            return formReturnList;
        }

    }

    public UnidadeEntity entitysToFormCreate(UnidadeFormCreate formCreate) {
            UnidadeEntity entity = new UnidadeEntity();
            entity.setContato(formCreate.getContato());
            entity.setEspecialidade(formCreate.getEspecialidade());
            entity.setCnpj(formCreate.getCnpj());

            entity.setNome(formCreate.getNome());
            entity.setHorarioFuncionamento(formCreate.getHorarioFuncionamento());

            entity.setCep(formCreate.getCep().replace("-", ""));
            entity.setRua(formCreate.getRua());
            entity.setBairro(formCreate.getBairro());
            entity.setCidade(formCreate.getCidade());
            entity.setEstado(formCreate.getEstado());
            entity.setComplemento(formCreate.getComplemento());
            entity.setNumero(formCreate.getNumero());


            return entity;
    }

    public UnidadeFormReturn entityParaFormReturn(UnidadeEntity unidade) {
        UnidadeFormReturn formReturn = new UnidadeFormReturn();
        formReturn.setId(unidade.getId());
        formReturn.setNome(unidade.getNome());
        formReturn.setContato(unidade.getContato());
        formReturn.setCnpj(unidade.getCnpj());
        formReturn.setEspecialidade(unidade.getEspecialidade());
        formReturn.setHorarioFuncionamento(unidade.getHorarioFuncionamento());
        formReturn.setCep(unidade.getCep());


        formReturn.setImagem(Collections.singletonList(listaDeImagens(unidade.getImagens()).get(0)));


        formReturn.setBairro(unidade.getBairro());
        formReturn.setRua(unidade.getRua());
        formReturn.setCidade(unidade.getCidade());
        formReturn.setEstado(unidade.getEstado());
        formReturn.setComplemento(unidade.getComplemento());
        formReturn.setNumero(unidade.getNumero());

        if(unidade.getServicos() != null){
            formReturn.setServicos(unidade.getServicos());
        }

        return formReturn;
    }

    public UnidadeEntity formCreateParaEntity(UnidadeFormCreate formCreate, long id) {
        UnidadeEntity entity = new UnidadeEntity();
        entity.setId(id);

        entity.setContato(formCreate.getContato());
        entity.setEspecialidade(formCreate.getEspecialidade());
        entity.setNome(formCreate.getNome());
        entity.setHorarioFuncionamento(formCreate.getHorarioFuncionamento());
        entity.setCnpj(formCreate.getCnpj());

        entity.setCep(formCreate.getCep());
        entity.setRua(formCreate.getRua());
        entity.setCidade(formCreate.getCidade());
        entity.setEstado(formCreate.getEstado());
        entity.setComplemento(formCreate.getComplemento());
        entity.setNumero(formCreate.getNumero());

        return entity;
    }


    public List<byte[]> listaDeImagens(List<UnidadeImagensEntity> entityList){
        List<byte[]> imagens = new ArrayList<>();
        for(UnidadeImagensEntity entity : entityList){
            imagens.add(entity.getImagem());
        }
        return imagens;
    }




}
