package br.vetconnect.api.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
