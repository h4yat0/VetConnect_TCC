package br.vetconnect.api.controller.Prontuario;


import br.vetconnect.api.form.Prontuario.ItemProntuarioForm;
import br.vetconnect.api.form.Prontuario.ProntuarioForm;
import br.vetconnect.api.service.Prontuario.ItemProntuarioService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/itemProntuario")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Item Prontuario", description = "end points relacionados aos itens prontuario")
public class ItemProntuarioController {


    @Autowired
    private ItemProntuarioService service;



    @PostMapping("v1/cadastrar")
    @Operation(summary = "endPoint para cadastrar um item prontuario", description = "endPoint para cadastrar um item prontuario",
            tags = {"Item Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ItemProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel cadastrar esse item prontuario", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> cadastrarItemProntuario(@RequestBody ItemProntuarioForm form){
        ItemProntuarioForm prontuarioForm = service.cadastrarItemProntuario(form);
        if(prontuarioForm == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse item prontuario"));
        }else{
            return ResponseEntity.ok().body(prontuarioForm);
        }
    }

    @GetMapping("v1/buscar-itens-prontuarios/{id}")
    @Operation(summary = "endPoint para buscar os itens prontuario", description = "endPoint para buscar os itens prontuarios",
            tags = {"Item Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ItemProntuarioForm.class))
            }),
            @ApiResponse(description = "Não tem nenhum item prontuario nesse prontuario", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> procurarItemProntuarios(@PathVariable Long id){
        List<ItemProntuarioForm> prontuarioForms = service.procurarItemProntuarios(id);
        if(prontuarioForms == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem nenhum item prontuario nesse prontuario"));
        }else{
            return ResponseEntity.ok().body(prontuarioForms);
        }
    }

    @PutMapping("v1/editar/{id}")
    @Operation(summary = "endPoint para editar um item prontuario", description = "endPoint para editar um item prontuario",
            tags = {"Item Prontuario"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ItemProntuarioForm.class))
            }),
            @ApiResponse(description = "Não foi possivel editar esse item prontuario", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> alterarItemProntuario(@RequestBody ItemProntuarioForm form, @PathVariable Long id){
        ItemProntuarioForm prontuarioForm = service.editarItemProntuario(form, id);
        if(prontuarioForm == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel editar esse item prontuario"));
        }else{
            return ResponseEntity.ok().body(prontuarioForm);
        }
    }

    @DeleteMapping("v1/deletar/{id}")
    public ResponseEntity<?> deletarItemProntuario(@PathVariable Long id){
        service.deleteItemProntuario(id);
        return ResponseEntity.noContent().build();
    }


}
