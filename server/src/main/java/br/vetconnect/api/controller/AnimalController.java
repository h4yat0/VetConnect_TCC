package br.vetconnect.api.controller;


import br.vetconnect.api.form.Animal.AnimalFormCreate;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import br.vetconnect.api.service.Animal.AnimalService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/animal")
@Tag(name = "Animal", description = "end points relacionados aos animais")
@SecurityRequirement(name = "bearerAuth")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @Operation(summary = "endPoint para cadastrar um animal", description = "endPoint para cadastrar um animal",
            tags = {"Animal"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalFormReturn.class))
            }),
            @ApiResponse(description = "Não foi possivel cadastrar esse animal", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PostMapping("v1/cadastro")
    public ResponseEntity<?> cadastroAnimal(@RequestBody  @Valid AnimalFormCreate animal){
        AnimalFormReturn animalReturn = animalService.saveAnimal(animal);
        if(animalReturn == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar esse animal"));
        }else{
            return ResponseEntity.ok().body(animalReturn);
        }
    }

    @Operation(summary = "endPoint para buscar os animais do cliente", description = "endPoint para buscar os animais do cliente",
            tags = {"Animal"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AnimalFormReturn.class)))
            }),
            @ApiResponse(description = "Esse cliente não contem animais", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("v1/buscar/{id}")
    public ResponseEntity<?> buscarAnimais(@PathVariable Long id){
        List<AnimalFormReturn> animais = animalService.buscarAnimal(id);
        if(animais == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Esse cliente não contem animais"));
        }else{
            return ResponseEntity.ok().body(animais);
        }
    }

    @Operation(summary = "endPoint para alterar um animal do cliente", description = "endPoint para alterar um animal do cliente",
            tags = {"Animal"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnimalFormReturn.class))
            }),
            @ApiResponse(description = "Não foi possivel alterar esse animal", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/alterar/{id}")
    public ResponseEntity<?> alterarAnimal(@RequestBody @Valid AnimalFormCreate animal, @PathVariable Long id){
        AnimalFormReturn animalReturn = animalService.alterarAnimal(animal, id);
        if(animalReturn == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel alterar esse animal"));
        }else{
            return  ResponseEntity.ok().body(animalReturn);
        }

    }

    @Operation(summary = "endPoint para deletar um animal", description = "endPoint para deletar um animal",
            tags = {"Animal"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Não foi possivel deletar esse animal", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @DeleteMapping("v1/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

}
