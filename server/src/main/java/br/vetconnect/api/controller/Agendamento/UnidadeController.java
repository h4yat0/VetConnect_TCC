package br.vetconnect.api.controller.Agendamento;

import br.vetconnect.api.form.ImagemUnidadeForm;
import br.vetconnect.api.form.Unidade.UnidadeFormCreate;
import br.vetconnect.api.form.Unidade.UnidadeFormReturn;
import br.vetconnect.api.mapper.Unidade.ImagemUnidadeMapper;
import br.vetconnect.api.service.Agendamento.UnidadeService;
import br.vetconnect.api.service.Imagens.UnidadeImagemService;
import exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("api/unidade")
@Tag(name = "Unidade", description = "end points relacionados as unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService service;


    @Operation(summary = "endPoint para buscar as unidades cadastradas no sistema", description = "endPoint para buscar as unidades cadastradas no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem unidades cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/somente-unidades")
    public ResponseEntity<?> somenteUnidades(){
        List<UnidadeFormReturn> unidades = service.somenteUnidades();
        if(unidades.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem unidades cadastradas no sistema"));
        }else{
            return ResponseEntity.ok().body(unidades);
        }
    }

    @Operation(summary = "endPoint para buscar as unidades cadastradas no sistema e os serviços vinculados", description = "endPoint para buscar as unidades cadastradas no sistema e os serviços vinculados",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem unidades cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/unidades-servicos")
    public ResponseEntity<?> unidadesServicos(){
        List<UnidadeFormReturn> unidades = service.unidadesServicos();
        if(unidades.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem unidades cadastradas no sistema"));
        }else{
            return ResponseEntity.ok().body(unidades);
        }
    }

    @Operation(summary = "endPoint para buscar as unidades cadastradas no sistema mesmo sem serviços", description = "endPoint para buscar as unidades cadastradas no sistema mesmo sem serviços",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem unidades cadastradas no sistema", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("/v1/todas-unidades")
    public ResponseEntity<?> todasUnidades(){
        List<UnidadeFormReturn> unidades = service.todasUnidades();
        if(unidades.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem unidades cadastradas no sistema"));
        }else{
            return ResponseEntity.ok().body(unidades);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para cadastrar uma unidade no sistema", description = "endPoint para cadastrar uma unidade no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeFormReturn.class))
            }),
            @ApiResponse(description = "Ja tem uma unidade cadastrada com esse cnpj", responseCode = "409", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PostMapping("v1/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody UnidadeFormCreate formCreate){
        UnidadeFormReturn unidadeReturn = service.saveUnidade(formCreate);
        if(unidadeReturn == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Ja tem uma unidade cadastrada com esse cnpj"));
        }else{
            return ResponseEntity.ok().body(unidadeReturn);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para cadastrar imagens de unidade no sistema", description = "endPoint para cadastrar imagens de unidade no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204",content = @Content),
            @ApiResponse(description = "Ja tem uma unidade cadastrada com esse cnpj", responseCode = "409", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PostMapping("v1/cadastro-imagem/{id}")
    public ResponseEntity<?> cadastroImagem(@RequestBody ImagemUnidadeForm imagens, @PathVariable Long id){
        service.salvarImagemUnidade(imagens, id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para editar imagens de unidade no sistema", description = "endPoint para editar imagens de unidade no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnidadeFormReturn.class)))
            }),
            @ApiResponse(description = "Ja tem uma unidade cadastrada com esse cnpj", responseCode = "409", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/alterar-imagem/{id}")
    public ResponseEntity<?> alterarImagem(@RequestBody ImagemUnidadeForm imagens, @PathVariable Long id){
        service.alterarImagemUnidade(imagens.getImagem(), id);
        return ResponseEntity.noContent().build();
    }


    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para editar uma unidade no sistema", description = "endPoint para editar uma unidade no sistema",
            tags = {"Unidade"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeFormReturn.class))
            }),
            @ApiResponse(description = "Ja tem uma unidade cadastrada com esse cnpj", responseCode = "409", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/alterar/{id}")
    public ResponseEntity<?> alterarUnidade(@RequestBody UnidadeFormCreate formCreate, @PathVariable  long id){
        UnidadeFormReturn unidadeReturn = service.alterarUnidade(formCreate, id);
        if(unidadeReturn == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Ja tem uma unidade cadastrada com esse cep"));
        }else{
            return ResponseEntity.ok().body(unidadeReturn);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "endPoint para deletar uma unidade no sistema", description = "endPoint para deletar uma unidade no sistema",
              tags = {"Unidade"}, responses = {
        @ApiResponse(description = "Sucesso", responseCode = "204", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeFormReturn.class))
        }),
        @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @DeleteMapping("v1/deletar")
    public ResponseEntity<?> deletar(@PathVariable long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }







}
