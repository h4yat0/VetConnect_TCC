package br.vetconnect.api.mapper;

import br.vetconnect.api.entity.FuncionarioEntity;
import br.vetconnect.api.entity.UnidadeServico.UnidadeEntity;
import br.vetconnect.api.form.Funcionario.FuncionarioFormCreate;
import br.vetconnect.api.form.Funcionario.FuncionarioFormReturn;
import br.vetconnect.api.mapper.Servico.ServicoMapper;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioMapper {

    @Autowired
    private ServicoMapper servicoMapper;

    public FuncionarioFormReturn convertEntityToForm(FuncionarioEntity entity) {
        FuncionarioFormReturn formReturn = new FuncionarioFormReturn();
        formReturn.setId(entity.getId());
        formReturn.setNome(entity.getNome());
        formReturn.setDataNascimento(entity.getDataNascimento());
        formReturn.setHoraDeEntrada(entity.getHoraDeEntrada());
        formReturn.setHoraDeSaida(entity.getHoraDeSaida());
        formReturn.setIdUnidade(entity.getIdUnidade().getId());
        return formReturn;
    }

    public FuncionarioEntity convertFormToEntity(FuncionarioFormCreate formCreate, UnidadeEntity unidadeEntity) {
        FuncionarioEntity entity = new FuncionarioEntity();
        entity.setNome(formCreate.getNome());
        entity.setSenha(formCreate.getSenha());
        entity.setEmail(formCreate.getEmail());
        entity.setDataNascimento(formCreate.getDataNascimento());
        entity.setHoraDeEntrada(formCreate.getHoraDeEntrada());
        entity.setHoraDeSaida(formCreate.getHoraDeSaida());
        entity.setIdUnidade(unidadeEntity);
        return entity;
    }
}
