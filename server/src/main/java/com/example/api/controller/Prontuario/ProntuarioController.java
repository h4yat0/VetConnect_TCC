package com.example.api.controller.Prontuario;


import com.example.api.entity.Prontuario.ProntuarioEntity;
import com.example.api.form.Prontuario.ProntuarioForm;
import com.example.api.service.Prontuario.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private ProntuarioService service;


    @PostMapping("/cadastro")
    public ProntuarioEntity salvarProntuario(@RequestBody ProntuarioForm form){
        return service.salvarProntuario(form);
    }

    @GetMapping("/buscar/{id}")
    public ProntuarioEntity buscarProntuario(@PathVariable Long id){
        return  service.buscarPronatuario(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProntuario(@PathVariable Long id){
        service.deleteProntuario(id);
        return ResponseEntity.ok("Deletado");
    }

}
