package br.vetconnect.api.service.Agendamento;

import br.vetconnect.api.repository.Agendamento.FichaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichaServicoService {

    @Autowired
    private FichaServicoRepository repository;
}
