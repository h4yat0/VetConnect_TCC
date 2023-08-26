package br.vetconnect.api.service.Prontuario;

import br.vetconnect.api.repository.Prontuario.ItemProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemProntuarioService {

    @Autowired
    private ItemProntuarioRepository repository;
}
