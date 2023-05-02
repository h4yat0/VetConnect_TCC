package com.example.api.controller.Prontuario;


import com.example.api.service.Prontuario.ItemProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemProntuario")
public class ItemProntuarioController {


    @Autowired
    private ItemProntuarioService service;
}
