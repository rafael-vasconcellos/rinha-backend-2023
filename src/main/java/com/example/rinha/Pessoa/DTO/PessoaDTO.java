package com.example.rinha.Pessoa.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public class PessoaDTO {
    @NotEmpty
    @Length(max = 100)
    public String nome;

    @NotEmpty
    @Length(max = 32)
    public String apelido;

    @NotEmpty
    public LocalDate nascimento;

    @Length(max = 32)
    public ArrayList<String> stack;

    public PessoaDTO(PessoaRequestPayload pessoaRequestPayload) { 
        this.nome = pessoaRequestPayload.nome;
        this.apelido = pessoaRequestPayload.apelido;
        this.stack = pessoaRequestPayload.stack;
        this.nascimento = LocalDate.parse(pessoaRequestPayload.nascimento);
    }
}
