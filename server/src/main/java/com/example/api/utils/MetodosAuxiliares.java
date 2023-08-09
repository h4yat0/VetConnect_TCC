package com.example.api.utils;

import com.example.api.form.Cliente.ClienteFormCreate;
import com.example.api.form.Cliente.ClienteFormReturn;
import com.example.api.service.ClienteService;
import exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MetodosAuxiliares {


    public Integer idadeUsuario(String dataNascimentoStr){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        int idade = periodo.getYears();
        return idade;
    }

}
