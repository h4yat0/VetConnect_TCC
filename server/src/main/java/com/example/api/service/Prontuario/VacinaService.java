package com.example.api.service.Prontuario;


import com.example.api.entity.Prontuario.ProntuarioEntity;
import com.example.api.entity.Prontuario.VacinaEntity;
import com.example.api.entity.form.Prontuario.VacinaForm;
import com.example.api.repository.Prontuario.ProntuarioRepository;
import com.example.api.repository.Prontuario.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository repository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public VacinaEntity salvarVacina(VacinaForm form) {
        ProntuarioEntity prontuario = prontuarioRepository.buscarProntuarioPorId(form.getIdProntuario());
        if (prontuario == null) {
            return null;
        } else {
            VacinaEntity entity = new VacinaEntity();
            entity.setNome(form.getNome());
            entity.setDataAplicacao(form.getDataAplicacao());
            entity.setDataValidade(form.getDataValidade());
            entity.setIdProntuario(prontuario);

            if (form.getObservacoes() != null) {
                entity.setObservacoes(form.getObservacoes());
            }
            return repository.save(entity);

        }
    }
}
