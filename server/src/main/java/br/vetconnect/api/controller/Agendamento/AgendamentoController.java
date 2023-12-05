package br.vetconnect.api.controller.Agendamento;

import br.vetconnect.api.controller.ClienteController;
import br.vetconnect.api.form.Agendamento.AgendamentoFormCreate;
import br.vetconnect.api.entity.Agendamento.AgendamentoEntity;
import br.vetconnect.api.form.Agendamento.AgendamentoFormReturn;
import br.vetconnect.api.form.Animal.AnimalFormReturn;
import br.vetconnect.api.form.HorariosDisponiveis;
import br.vetconnect.api.service.Agendamento.AgendamentoService;
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
@RequestMapping("api/agendamento")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Agendamento", description = "end points relacionados a agendamentos de consultas")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Operation(summary = "endPoint para realizar o agendamento", description = "endPoint para realizar o agendamento",
            tags = {"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201", content = @Content(schema = @Schema(implementation = AgendamentoFormReturn.class))),
            @ApiResponse(description = "Não foi possivel realizar o agendamento", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PostMapping("v1/agendar")
    public ResponseEntity<?> cadastroAgendamento(@RequestBody AgendamentoFormCreate form){
        AgendamentoFormReturn formReturn = service.cadastrarAgendamento(form);
        if(formReturn == null ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(new Date(), HttpStatus.CONFLICT, "Não foi possivel realizar o agendamento"));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }

    @Operation(summary = "endPoint para editar o agendamento", description = "endPoint para editar o agendamento",
            tags = {"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = AgendamentoFormReturn.class))),
            @ApiResponse(description = "Não foi possivel editar o agendamento", responseCode = "400", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/edit-agendamento/{id}")
    public ResponseEntity<?> editarAgendamento(@RequestBody AgendamentoFormCreate form, @PathVariable Long id){
        AgendamentoFormReturn formReturn = service.editarAgendamento(form, id);
        if(formReturn == null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possivel editar o agendamento"));
        }else{
            return ResponseEntity.ok().body(formReturn);
        }
    }

    @Operation(summary = "endPoint para buscar agendamentos com o id do cliente", description = "endPoint para buscar agendamentos com o id do cliente",
            tags = {"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AgendamentoFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem agendamentos com esse cliente", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("v1/buscarAgendamentos/{id}")
    public ResponseEntity<?> buscarPorIdCliente(@PathVariable Long id){
         List<AgendamentoFormReturn> formReturnList = service.buscarPorIdCliente(id);
         if(formReturnList == null ){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem agendamentos com esse cliente"));
         }else{
             return ResponseEntity.ok().body(formReturnList);
         }
    }

    @Operation(summary = "endPoint para buscar agendamentos com o id do animal", description = "endPoint para buscar agendamentos com o id do animal",
            tags = {"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AgendamentoFormReturn.class)))
            }),
            @ApiResponse(description = "Não tem agendamentos com esse animal", responseCode = "404", content = @Content),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @GetMapping("v1/buscarAgendamentos-animal/{id}")
    public ResponseEntity<?> buscarPorIdAnimal(@PathVariable Long id ){
        List<AgendamentoFormReturn> formReturnList = service.buscarPorIdAnimal(id);
        if(formReturnList == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não tem agendamentos com esse animal"));
        }else{
            return ResponseEntity.ok().body(formReturnList);
        }
    }


    @Operation(summary = "endPoint para cancelar um agendamento", description = "endPoint para cancelar um agendamento",
            tags = {"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = @Content)
    })
    @PutMapping("v1/cancelar-agendamento/{id}")
    public ResponseEntity<?> cancelarAgendamento(@PathVariable Long id){
        try{
            service.cancelarAgendamento(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND, "Não foi possivel cancelar esse agendamento"));
        }
    }

    @Operation(summary = "endPoint para retornar horarios disponiveis do serviço e unidade", description = "endPoint para retornar horarios disponiveis do serviço e unidade",
            tags ={"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HorariosDisponiveis.class)))}),
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))})

    })
    @GetMapping("v1/horarios-disponiveis/{idUnidade}/{idServico}/{data}")
    public ResponseEntity<?> buscarHorarios(@PathVariable Long idUnidade, @PathVariable Long idServico, @PathVariable String data){
        HorariosDisponiveis horarios = service.buscarHorarios(idUnidade, idServico, data);
        return ResponseEntity.ok().body(horarios);
    }

    @Operation(summary = "endPoint para retornar os agendamentos de uma unidade a partir do id do funcionario", description = "endPoint para retornar os agendamentos de uma unidade a partir do id do funcionario",
            tags ={"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HorariosDisponiveis.class)))}),
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))})

    })
    @GetMapping("v1/buscar-por-id-funcionario/{idFuncionario}")
    public ResponseEntity<?> buscarAgendamentoPorIdFuncionario(@PathVariable Long idFuncionario){
        List<AgendamentoFormReturn> agendamentoFormReturns = service.buscarPorIdFuncionario(idFuncionario);
        if(agendamentoFormReturns.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok().body(agendamentoFormReturns);
        }
    }

    @Operation(summary = "endPoint para concluir o agendamento", description = "endPoint para concluir o agendamento",
            tags ={"Agendamento"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "400", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))}),
            @ApiResponse(description = "Sucesso", responseCode = "204"),
            @ApiResponse(description = "Algo inesperado aconteceu", responseCode = "500", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))})

    })
    @PostMapping("v1/concluir-agendamento/{idAgendamento}")
    public ResponseEntity<?> concluirAgendamento(@PathVariable Long idAgendamento){
        boolean agendamentoConcluido = service.concluirAgendamento(idAgendamento);
        if(agendamentoConcluido){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST, "Não foi possível concluir esse agendamento"));
        }
    }

}
