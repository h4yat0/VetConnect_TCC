package br.vetconnect.api.service.Prontuario;

import br.vetconnect.api.repository.Prontuario.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExameService {

    @Autowired
    private ExameRepository repository;
}
