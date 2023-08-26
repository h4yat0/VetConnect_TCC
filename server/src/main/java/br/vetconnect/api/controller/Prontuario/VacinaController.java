package br.vetconnect.api.controller.Prontuario;

import br.vetconnect.api.entity.Prontuario.VacinaEntity;
import br.vetconnect.api.form.Prontuario.VacinaForm;
import br.vetconnect.api.service.Prontuario.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/vacina")
public class VacinaController {

    @Autowired
    private VacinaService service;


    @PostMapping("/cadastro")
    public VacinaEntity salvarVacinal(@RequestBody VacinaForm form){
        return service.salvarVacina(form);
    }
}
