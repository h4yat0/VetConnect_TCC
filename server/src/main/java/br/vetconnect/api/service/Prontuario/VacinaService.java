package br.vetconnect.api.service.Prontuario;


import br.vetconnect.api.entity.Prontuario.ProntuarioEntity;
import br.vetconnect.api.entity.Prontuario.VacinaEntity;
import br.vetconnect.api.form.Prontuario.VacinaForm;
import br.vetconnect.api.repository.Prontuario.ProntuarioRepository;
import br.vetconnect.api.repository.Prontuario.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository repository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public VacinaEntity salvarVacina(VacinaForm form) {
        ProntuarioEntity prontuario = prontuarioRepository.buscarProntuarioPorIdAnimal(form.getIdProntuario());
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
