package br.vetconnect.api.controller.Prontuario;


import br.vetconnect.api.service.Prontuario.ItemProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/itemProntuario")
public class ItemProntuarioController {


    @Autowired
    private ItemProntuarioService service;
}